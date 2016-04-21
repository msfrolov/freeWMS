package com.epam.msfrolov.freewms.action;

import com.epam.msfrolov.freewms.service.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ProductCardDeleteAction implements Action {
    private static final Logger log = LoggerFactory.getLogger(ProductCardDeleteAction.class);

    @Override
    public ActionResult execute(HttpServletRequest req, HttpServletResponse resp) {
        try (ProductService productService = new ProductService()) {
            String prodId = req.getParameter("prodId");
            int i = Integer.parseInt(prodId);
            productService.deleteProduct(i);

        }
        String page_number = req.getParameter("page_number");
        return new ActionResult("products_catalog?page_number=" + page_number, true);
    }
}
