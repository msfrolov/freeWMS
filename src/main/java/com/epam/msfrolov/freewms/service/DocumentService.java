package com.epam.msfrolov.freewms.service;

import com.epam.msfrolov.freewms.dao.Dao;
import com.epam.msfrolov.freewms.model.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Iterator;
import java.util.List;

public class DocumentService extends Service {
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
        return productDao.findAll();
    }

    public Product findProduct(int productId) {
        return productDao.findById(productId);
    }

    public TableLine insertTableLine(TableLine tableLine, Document document) {
        log.debug("document: {}", document);
        return tableLineDao.insert(tableLine, document);
    }

    public List<Counterpart> findAllCounterparts() {
        return counterpartDao.findAll();
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
            daoFactory.commit();
            return true;
        } catch (Exception e) {
            daoFactory.rollback();
            log.debug("rollback", e);
            return false;
        }
    }

}
