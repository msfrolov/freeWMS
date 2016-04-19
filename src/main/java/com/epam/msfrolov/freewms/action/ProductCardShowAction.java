package com.epam.msfrolov.freewms.action;

import com.epam.msfrolov.freewms.model.Measure;
import com.epam.msfrolov.freewms.model.Product;
import com.epam.msfrolov.freewms.model.ProductType;
import com.epam.msfrolov.freewms.service.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class ProductCardShowAction implements Action {
    private static final Logger log = LoggerFactory.getLogger(ProductCardShowAction.class);
    private ActionResult productCard = new ActionResult("product_card");

    @Override
    public ActionResult execute(HttpServletRequest req, HttpServletResponse resp) {
        String prodIdString = req.getParameter("prodId");
        try {
            int prodId = Integer.parseInt(prodIdString);
            if (prodId < 0) throw new ActionException("incorrect parameter (negative id)");
            try (ProductService productService = new ProductService()) {
                Product product = productService.findProduct(prodId);
                List<ProductType> productTypes = productService.findAllProductType();
                List<Measure> measureList = productService.findAllMeasure();
                if (product == null) throw new ActionException("product not found");
                log.debug("product  {}", product);
                log.debug("productTypes  {}", productTypes);
                log.debug("measureList  {}", measureList);
                req.setAttribute("product", product);
                req.setAttribute("typeList", productTypes);
                req.setAttribute("measureList", measureList);
                return productCard;
            }
        } catch (NumberFormatException e) {
            throw new ActionException("incorrect parameter (invalid characters)", e);
        }
    }
}

