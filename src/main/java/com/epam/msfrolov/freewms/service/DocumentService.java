package com.epam.msfrolov.freewms.service;

import com.epam.msfrolov.freewms.dao.Dao;
import com.epam.msfrolov.freewms.dao.QueryDesigner;
import com.epam.msfrolov.freewms.model.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.sql.rowset.CachedRowSet;
import java.sql.SQLException;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class DocumentService extends Service {
    public static final QueryDesigner BALANCE_PRODUCTS_IN_WAREHOUSES = new QueryDesigner()
            .insertInto().text("BALANCE_PRODUCTS_IN_WAREHOUSES")
            .ob().text("PRODUCT").comma().text("WAREHOUSE").comma().text("BALANCE").comma().text("DATE").cb()
            .values().ob().question().comma().question().comma().question().comma().question().cb();
    public static final QueryDesigner TURNOVER_PRODUCTS_WITH_COUNTERPARTIES = new QueryDesigner()
            .insertInto().text("TURNOVER_PRODUCTS_WITH_COUNTERPARTIES")
            .ob().text("PRODUCT").comma().text("COUNTERPART").comma().text("TURNOVER").comma().text("DATE").cb()
            .values().ob().question().comma().question().comma().question().comma().question().cb();
    private static final Logger log = LoggerFactory.getLogger(DocumentService.class);
    private Dao<Product> productDao;
    private Dao<TableLine> tableLineDao;
    private Dao<Counterpart> counterpartDao;
    private Dao<Warehouse> warehouseDao;
    private Dao<ReceiptDocument> receiptDocumentDao;
    private Dao<MoveDocument> moveDocumentDao;
    private Dao<ExpenseDocument> expenseDocumentDao;

    public DocumentService() {
        super();
        productDao = daoFactory.createDaoEntity(Product.class);
        tableLineDao = daoFactory.createDaoEntity(TableLine.class);
        counterpartDao = daoFactory.createDaoEntity(Counterpart.class);
        warehouseDao = daoFactory.createDaoEntity(Warehouse.class);
        receiptDocumentDao = daoFactory.createDaoEntity(ReceiptDocument.class);
        moveDocumentDao = daoFactory.createDaoEntity(MoveDocument.class);
        expenseDocumentDao = daoFactory.createDaoEntity(ExpenseDocument.class);
    }

    @Override
    public void close() {
        super.close();
    }

    public List<Product> findAllProduct() {
        List<Product> all = productDao.findAll();
        all.sort(Product.COMPARE_NAME);
        return all;
    }

    public Product findProduct(int productId) {
        return productDao.findById(productId);
    }

    public TableLine insertTableLine(TableLine tableLine, Document document) {
        log.debug("document: {}", document);
        return tableLineDao.insert(tableLine, document);
    }

    public List<Counterpart> findAllCounterparts() {
        List<Counterpart> all = counterpartDao.findAll();
        all.sort(Counterpart.COMPARE_NAME);
        return all;
    }

    public List<Warehouse> findAllWarehouse() {
        return warehouseDao.findAll();
    }

    public Counterpart findCounterpartById(int i) {
        return counterpartDao.findById(i);
    }

    public Warehouse findWarehouseById(int i) {
        return warehouseDao.findById(i);
    }

    public boolean insertReceiptDocument(ReceiptDocument document) {
        return insertDocument(document, receiptDocumentDao);
    }

    public boolean insertMoveDocument(MoveDocument document) {
        return insertDocument(document, moveDocumentDao);
    }

    public boolean insertExpenseDocument(ExpenseDocument document) {
        return insertDocument(document, expenseDocumentDao);
    }

    private <T extends Document> boolean insertDocument(T document, Dao<T> DocumentDao) {
        try {
            daoFactory.startTransaction();
            document = DocumentDao.insert(document);
            if (document == null) throw new ServiceException("failed to save the document in the database");
            final Document finalDocument = document;
            Iterator<TableLine> iterator = document.iterator();
            while (iterator.hasNext()) {
                TableLine dl = iterator.next();
                dl = insertTableLine(dl, finalDocument);
                if (dl == null) throw new ServiceException("failed to save the document line");
            }
            boolean success;
            success = balanceProductsInWarehouses(document);
            if (!success)
                throw new ServiceException("failed to write data into the table BALANCE_PRODUCTS_IN_WAREHOUSES");
            success = turnoverProductsWithCounterparties(document);
            if (!success)
                throw new ServiceException("failed to write data into the table TURNOVER_PRODUCTS_WITH_COUNTERPARTIES");
            daoFactory.commit();
            return true;
        } catch (Exception e) {
            daoFactory.rollback();
            log.debug("rollback", e);
            return false;
        }
    }

    private boolean balanceProductsInWarehouses(Document document) {
        final boolean[] mainSuccess = {true};
        if (document.getClass() == ReceiptDocument.class) {
            ReceiptDocument receiptDocument = (ReceiptDocument) document;
            receiptDocument.forEach(tableLine -> executeQuery(mainSuccess, tableLine.getProduct().getId(),
                    receiptDocument.getRecipient().getId(), tableLine.getCount(),
                    receiptDocument.getDate().format(DateTimeFormatter.ISO_LOCAL_DATE),
                    BALANCE_PRODUCTS_IN_WAREHOUSES));
        } else if (document.getClass() == MoveDocument.class) {
            MoveDocument moveDocument = (MoveDocument) document;
            moveDocument.forEach(tableLine -> executeQuery(mainSuccess, tableLine.getProduct().getId(),
                    moveDocument.getRecipient().getId(), tableLine.getCount(),
                    moveDocument.getDate().format(DateTimeFormatter.ISO_LOCAL_DATE),
                    BALANCE_PRODUCTS_IN_WAREHOUSES));
            MoveDocument moveDocument1 = (MoveDocument) document;
            moveDocument1.forEach(tableLine ->
                    executeQuery(mainSuccess, tableLine.getProduct().getId(),
                            moveDocument1.getSender().getId(), tableLine.getCount() * -1,
                            moveDocument1.getDate().format(DateTimeFormatter.ISO_LOCAL_DATE),
                            BALANCE_PRODUCTS_IN_WAREHOUSES));
        } else if (document.getClass() == ExpenseDocument.class) {
            ExpenseDocument expenseDocument = (ExpenseDocument) document;
            expenseDocument.forEach(tableLine ->
                    executeQuery(mainSuccess, tableLine.getProduct().getId(),
                            expenseDocument.getSender().getId(), tableLine.getCount() * -1,
                            expenseDocument.getDate().format(DateTimeFormatter.ISO_LOCAL_DATE),
                            BALANCE_PRODUCTS_IN_WAREHOUSES));
        }
        return mainSuccess[0];
    }

    private boolean turnoverProductsWithCounterparties(Document document) {
        final boolean[] mainSuccess = {true};
        if (document.getClass() == ReceiptDocument.class) {
            ReceiptDocument receiptDocument = (ReceiptDocument) document;
            receiptDocument.forEach(tableLine -> executeQuery(mainSuccess, tableLine.getProduct().getId(),
                    receiptDocument.getSender().getId(), tableLine.getCount(),
                    receiptDocument.getDate().format(DateTimeFormatter.ISO_LOCAL_DATE),
                    TURNOVER_PRODUCTS_WITH_COUNTERPARTIES));
        } else if (document.getClass() == ExpenseDocument.class) {
            ExpenseDocument expenseDocument = (ExpenseDocument) document;
            expenseDocument.forEach(tableLine ->
                    executeQuery(mainSuccess, tableLine.getProduct().getId(),
                            expenseDocument.getRecipient().getId(), tableLine.getCount() * -1,
                            expenseDocument.getDate().format(DateTimeFormatter.ISO_LOCAL_DATE),
                            TURNOVER_PRODUCTS_WITH_COUNTERPARTIES));
        }
        return mainSuccess[0];
    }

    private void executeQuery(boolean[] mainSuccess, Integer id, Integer id2, int e,
                              String format, QueryDesigner queryDesigner) {
        List<Object> param = new ArrayList<>();
        param.add(id);
        param.add(id2);
        param.add(e);
        param.add(format);
        boolean success = receiptDocumentDao.queryDesigner(queryDesigner, param);
        if (!success) mainSuccess[0] = false;
    }

    public List<Map<String, String>> documentsList(int pageNumber, int pageSize) {
        String query = "SELECT\n" +
                "  TYPE,\n" +
                "  ID,\n" +
                "  DATE,\n" +
                "  SENDER,\n" +
                "  RECIPIENT,\n" +
                "  COMMENT\n" +
                "FROM\n" +
                "  (SELECT\n" +
                "     RD.ID   AS ID,\n" +
                "     DATE,\n" +
                "     CP.NAME AS SENDER,\n" +
                "     WH.NAME AS RECIPIENT,\n" +
                "     COMMENT,\n" +
                "     'RECIPIENT'       AS TYPE\n" +
                "   FROM RECEIPT_DOCUMENT AS RD\n" +
                "     LEFT JOIN COUNTERPART AS CP ON RD.SENDER = CP.ID\n" +
                "     LEFT JOIN WAREHOUSE AS WH ON RD.RECIPIENT = WH.ID\n" +
                "   WHERE RD.DELETION_MARK = FALSE\n" +
                "\n" +
                "   UNION\n" +
                "\n" +
                "   SELECT\n" +
                "     MD.ID    AS ID,\n" +
                "     DATE,\n" +
                "     WH.NAME  AS SENDER,\n" +
                "     WH2.NAME AS RECIPIENT,\n" +
                "     COMMENT,\n" +
                "     'MOVE'        AS TYPE\n" +
                "   FROM MOVE_DOCUMENT AS MD\n" +
                "     LEFT JOIN WAREHOUSE AS WH ON MD.SENDER = WH.ID\n" +
                "     LEFT JOIN WAREHOUSE AS WH2 ON MD.RECIPIENT = WH2.ID\n" +
                "   WHERE MD.DELETION_MARK = FALSE\n" +
                "\n" +
                "   UNION\n" +
                "\n" +
                "   SELECT\n" +
                "     ED.ID   AS ID,\n" +
                "     DATE,\n" +
                "     WH.NAME AS SENDER,\n" +
                "     CP.NAME AS RECIPIENT,\n" +
                "     COMMENT,\n" +
                "     'EXPENSE'       AS TYPE\n" +
                "   FROM EXPENSE_DOCUMENT AS ED\n" +
                "     LEFT JOIN WAREHOUSE AS WH ON ED.SENDER = WH.ID\n" +
                "     LEFT JOIN COUNTERPART AS CP ON ED.RECIPIENT = CP.ID\n" +
                "   WHERE ED.DELETION_MARK = FALSE) AS TB\n" +
                "ORDER BY ID,SENDER,RECIPIENT LIMIT ? OFFSET ?";

        QueryDesigner queryDesigner = new QueryDesigner();
        queryDesigner.text(query);
        List<Object> param = new ArrayList<>();
        param.add(pageSize);
        int offSet = (pageNumber - 1) * pageSize;
        param.add(offSet);
        CachedRowSet cachedRowSet = productDao.queryDesignerResultSet(queryDesigner, param);
        List<Map<String, String>> documentList = new ArrayList<>();
        try {
            while (cachedRowSet.next()) {
                HashMap<String, String> map = new HashMap<>();
                map.put("type", cachedRowSet.getString("TYPE"));
                map.put("id", cachedRowSet.getString("ID"));
                map.put("date", cachedRowSet.getString("DATE"));
                map.put("sender", cachedRowSet.getString("SENDER"));
                map.put("recipient", cachedRowSet.getString("RECIPIENT"));
                map.put("comment", cachedRowSet.getString("COMMENT"));
                documentList.add(map);
            }
        } catch (SQLException e) {
            throw new ServiceException("failed to generate report (unreadable data in DB)", e);
        }
        return documentList;
    }

    public int documentsListSize() {
        String query = "SELECT COUNT(*) FROM (\n" +
                "  SELECT ID FROM RECEIPT_DOCUMENT WHERE DELETION_MARK = FALSE\n" +
                "  UNION\n" +
                "  SELECT ID FROM MOVE_DOCUMENT WHERE DELETION_MARK = FALSE\n" +
                "  UNION\n" +
                "  SELECT ID FROM EXPENSE_DOCUMENT WHERE DELETION_MARK = FALSE\n" +
                ")";
        QueryDesigner queryDesigner = new QueryDesigner();
        queryDesigner.text(query);
        List<Object> param = new ArrayList<>();
        CachedRowSet cachedRowSet = productDao.queryDesignerResultSet(queryDesigner, param);
        try {
            cachedRowSet.next();
            return cachedRowSet.getInt(1);
        } catch (SQLException e) {
            throw new ServiceException("failed to generate report (unreadable data in DB)", e);
        }
    }
}
