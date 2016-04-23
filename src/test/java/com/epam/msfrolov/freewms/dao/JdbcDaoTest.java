package com.epam.msfrolov.freewms.dao;

import com.epam.msfrolov.freewms.model.Product;
import org.junit.Test;

import static org.junit.Assert.*;

public class JdbcDaoTest {

    @Test
    public void testDao() throws Exception {
        DaoFactory daoFactory = DaoFactory.newInstance();
        Dao<Product> daoEntity = daoFactory.createDaoEntity(Product.class);
        Product prodFromDb = daoEntity.findById(13);
        Product newProd = new Product();
        newProd.setId(13);
        assertTrue(prodFromDb.equals(newProd));
    }
}