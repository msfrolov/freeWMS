package com.epam.msfrolov.freewms.service;

import com.epam.msfrolov.freewms.dao.Dao;
import com.epam.msfrolov.freewms.dao.DaoException;
import com.epam.msfrolov.freewms.dao.DaoFactory;
import com.epam.msfrolov.freewms.model.Product;
import com.epam.msfrolov.freewms.model.User;
import com.epam.msfrolov.freewms.model.UserRole;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProductService implements AutoCloseable {
    private static final Logger log = LoggerFactory.getLogger(ProductService.class);
    private Dao<Product> productDao;
    private DaoFactory daoFactory;

    public ProductService() {
        log.debug("ProductService() constructor");
        daoFactory = DaoFactory.newInstance();
        log.debug("ProductService() constructor: DaoFactory.newInstance()");
        productDao = daoFactory.createDaoEntity(Product.class);
        log.debug("ProductService() constructor: daoFactory.createDaoEntity(Product.class);");
    }



    @Override
    public void close() {
        if (daoFactory != null)
            daoFactory.close();
    }

    public List<Product> getProductsForPage(int pageNumber, int pageSize) {
        int start = pageSize * (pageNumber-1) +1;
        log.debug("pageNumber {}", pageNumber);
        log.debug("pageSize {}", pageSize);
        log.debug("start {}", start);
        List<Product> products = productDao.findAll(start, pageSize);
        log.debug("number of products in the list: {}", products.size());
        return products;
    }

    public Product findProduct(int prodId) {
        log.debug("find product by id {}", prodId);
        Product product = productDao.findById(prodId);
        log.debug("product {}", product);
        return product;
    }
}