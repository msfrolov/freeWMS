package com.epam.msfrolov.freewms.service;

import com.epam.msfrolov.freewms.dao.Dao;
import com.epam.msfrolov.freewms.dao.QueryDesigner;
import com.epam.msfrolov.freewms.model.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class OrderService extends Service {

    public static final QueryDesigner PRODUCT_BALANCE_QUERY = new QueryDesigner().select().text("PRODUCT").comma().sum().ob().text("BALANCE").cb()
            .from().text("BALANCE_PRODUCTS_IN_WAREHOUSES").where().text("WAREHOUSE")
            .equal().question().and().text("DATE").equalsOrLessThan().question()
            .groupBy().text("PRODUCT");
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

    public List<TableLine> calculateProdBalance(Integer pageNumber, int defaultPageSize, Warehouse warehouse, LocalDate date) {
        List<TableLine> tableLines = new ArrayList<>();
        List<Object> param = new ArrayList<>();
        param.add(warehouse.getId());
        param.add(date.format(DateTimeFormatter.ISO_LOCAL_DATE));
        ResultSet resultSet = tableLineDao.queryDesignerResultSet(PRODUCT_BALANCE_QUERY, param);
        try {
            while (resultSet.next()) {
                int product = resultSet.getInt("PRODUCT");
                int balance = resultSet.getInt("BALANCE");
                TableLine tableLine = new TableLine();
                Product byId = productDao.findById(product);
                tableLine.setProduct(byId);
                tableLine.setCount(balance);
                tableLines.add(tableLine);
            }
            return tableLines;
        } catch (SQLException e) {
            throw new ServiceException("failed to generate a report");
        }
    }
}
