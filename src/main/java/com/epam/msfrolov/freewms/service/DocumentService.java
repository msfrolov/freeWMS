package com.epam.msfrolov.freewms.service;

import com.epam.msfrolov.freewms.dao.Dao;
import com.epam.msfrolov.freewms.model.Document;
import com.epam.msfrolov.freewms.model.Product;
import com.epam.msfrolov.freewms.model.TableLine;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class DocumentService extends Service {
    private static final Logger log = LoggerFactory.getLogger(DocumentService.class);
    private Dao<Product> productDao;
    private Dao<TableLine> tableLineDao;

    public DocumentService() {
        super();
        productDao = daoFactory.createDaoEntity(Product.class);
        tableLineDao = daoFactory.createDaoEntity(TableLine.class);
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
}
