package com.epam.msfrolov.freewms.action;

import com.epam.msfrolov.freewms.model.Product;
import com.epam.msfrolov.freewms.service.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class ProductsCatalogShowAction implements Action {
    private static final Logger log = LoggerFactory.getLogger(ProductsCatalogShowAction.class);
    private static final int DEFAULT_PAGE_NUMBER = 1;
    private static final int DEFAULT_PAGE_SIZE = 10;
    private ActionResult productsCatalog = new ActionResult("products_catalog");

    @Override
    public ActionResult execute(HttpServletRequest req, HttpServletResponse resp) {
        int pageNumber;
        try {
            String pageString = req.getParameter("page_number");
            pageNumber = Integer.parseInt(pageString);
            if (pageNumber < 1) throw new ActionException("incorrect parameters");
        } catch (Exception e) {
            log.debug("set the default page", e);
            pageNumber = DEFAULT_PAGE_NUMBER;
        }
        log.debug("current page number {}", pageNumber);
        List<Product> products;
        try (ProductService productService = new ProductService()) {
            products = productService.getProductsForPage(pageNumber, DEFAULT_PAGE_SIZE);
        }
        req.setAttribute("products_list", products);
        return productsCatalog;
    }
}
