package com.epam.msfrolov.freewms.service;

import com.epam.msfrolov.freewms.dao.Dao;
import com.epam.msfrolov.freewms.dao.QueryDesigner;
import com.epam.msfrolov.freewms.model.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.sql.rowset.CachedRowSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class OrderService extends Service {

    //    public static final QueryDesigner PRODUCT_BALANCE_QUERY = new QueryDesigner().select().text("PRODUCT").comma().sum().ob().text("BALANCE").cb()
//            .from().text("BALANCE_PRODUCTS_IN_WAREHOUSES").where().text("WAREHOUSE")
//            .equal().question().and().text("DATE").equalsOrLessThan().question()
//            .groupBy().text("PRODUCT");
//    SELECT PRODUCT,  SUM  (BALANCE)  FROM BALANCE_PRODUCTS_IN_WAREHOUSES
//    INNER JOIN  PRODUCT ON BALANCE_PRODUCTS_IN_WAREHOUSES.PRODUCT = PRODUCT.ID
//    WHERE WAREHOUSE =  1  AND DATE <= '2016-04-25' AND DELETION_MARK = FALSE
//    GROUP BY PRODUCT
    private static final String PRODUCT = "PRODUCT";
    private static final String WAREHOUSE = "WAREHOUSE";
    private static final String BALANCE = "BALANCE";
    private static final String DATE = "DATE";
    private static final String BALANCE_TB = "BALANCE_PRODUCTS_IN_WAREHOUSES";

    public static final QueryDesigner PRODUCT_BALANCE_QUERY = new QueryDesigner().select().text(PRODUCT).comma().sum().ob().text(BALANCE).cb()
            .from().text(BALANCE_TB)
            .innerJoin().text(PRODUCT)
            .on().text(BALANCE_TB).dot().text(PRODUCT).equal().text(PRODUCT).dot().id()
            .where()
            .text(WAREHOUSE).equal().question()
            .and().text(DATE).equalsOrLessThan().question()
            .and().deletionMark().equal().bool(false)
            .groupBy().text(PRODUCT);
    private static final Logger log = LoggerFactory.getLogger(OrderService.class);
    private Dao<Product> productDao;
    private Dao<TableLine> tableLineDao;
    private Dao<Counterpart> counterpartDao;
    private Dao<Warehouse> warehouseDao;
    private Dao<ReceiptDocument> receiptDocumentDao;
    private Dao<MoveDocument> moveDocumentDao;
    private Dao<ExpenseDocument> expenseDocumentDao;

    public OrderService() {
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


    public Warehouse findWarehouseById(Integer warehouseId) {
        return warehouseDao.findById(warehouseId);
    }

    public List<TableLine> calculateProdBalance(Integer pageNumber, int pageSize, Warehouse warehouse, LocalDate date, HttpServletRequest req) {
        List<TableLine> tableLines = new ArrayList<>();
        List<Object> param = new ArrayList<>();
        param.add(warehouse.getId());
        param.add(date);
        CachedRowSet cachedRowSet = tableLineDao.queryDesignerResultSet(PRODUCT_BALANCE_QUERY, param);
        try {
            while (cachedRowSet.next()) {
                int product = cachedRowSet.getInt(1);
                int balance = cachedRowSet.getInt(2);
                TableLine tableLine = new TableLine();
                Product byId = productDao.findById(product);
                log.debug("Product id     {}", product);
                log.debug("Product entity {}", byId);
                log.debug("Balance        {}", balance);
                tableLine.setProduct(byId);
                tableLine.setCount(balance);
                tableLines.add(tableLine);
            }
            int start = (pageNumber - 1) * pageSize;
            int end = start + pageSize;
            int listSize = tableLines.size();
            if (listSize <= start) return new ArrayList<>();
            else if (listSize < end) end = listSize;
            int totalPages = (int) Math.ceil((double) tableLines.size() / pageSize);
            req.setAttribute("total_pages", totalPages);


            return tableLines.subList(start, end);
        } catch (SQLException e) {
            throw new ServiceException("failed to generate a report", e);
        }
    }

    public List<Warehouse> findAllWarehouse() {
        return warehouseDao.findAll();
    }
}
