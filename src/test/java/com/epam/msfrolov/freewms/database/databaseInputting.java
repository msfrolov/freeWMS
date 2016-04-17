package com.epam.msfrolov.freewms.database;

import com.epam.msfrolov.freewms.dao.Dao;
import com.epam.msfrolov.freewms.dao.DaoFactory;
import com.epam.msfrolov.freewms.model.Measure;
import com.epam.msfrolov.freewms.model.Product;
import com.epam.msfrolov.freewms.model.ProductType;
import com.epam.msfrolov.freewms.util.FileManager;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class databaseInputting {


    private Random rnd = new Random(System.currentTimeMillis());

    public void fillProductsWorkwear() throws Exception {
        List<String> strings = FileManager.readFileToList("products_workwear");
        DaoFactory daoFactory = DaoFactory.newInstance();
        Dao<Product> daoProduct = daoFactory.createDaoEntity(Product.class);
        Dao<ProductType> daoProductType = daoFactory.createDaoEntity(ProductType.class);
        Dao<Measure> daoMeasure = daoFactory.createDaoEntity(Measure.class);
        strings.forEach(p -> {
            Map<String, Object> map = new HashMap<>();
            map.put("name", p);
            List<Product> products = daoProduct.findByFields(map);
            if (products.isEmpty()) {
                Product product = new Product();
                product.setName(p);
                ProductType productType = daoProductType.findById(1);
                product.setType(productType);
                product.setBarcode(getRandom());
                Measure measure = daoMeasure.findById(4);
                product.setMeasure(measure);
                daoProduct.insert(product);
            }
        });
    }

    public void fillProductsHeadwear() throws Exception {
        List<String> strings = FileManager.readFileToList("products_headwear");
        DaoFactory daoFactory = DaoFactory.newInstance();
        Dao<Product> daoProduct = daoFactory.createDaoEntity(Product.class);
        Dao<ProductType> daoProductType = daoFactory.createDaoEntity(ProductType.class);
        Dao<Measure> daoMeasure = daoFactory.createDaoEntity(Measure.class);
        strings.forEach(p -> {
            Map<String, Object> map = new HashMap<>();
            map.put("name", p);
            List<Product> products = daoProduct.findByFields(map);
            if (products.isEmpty()) {
                Product product = new Product();
                product.setName(p);
                ProductType productType = daoProductType.findById(2);
                product.setType(productType);
                product.setBarcode(getRandom());
                Measure measure = daoMeasure.findById(4);
                product.setMeasure(measure);
                daoProduct.insert(product);
            }
        });
    }

    private String getRandom() {
        int min = 6125484;
        int max = 9841535;
        int number = min + rnd.nextInt(max - min + 1);
        return String.valueOf(number);
    }
}