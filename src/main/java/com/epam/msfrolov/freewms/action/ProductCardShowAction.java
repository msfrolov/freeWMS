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
        boolean addProd = "add_prod".equalsIgnoreCase(req.getParameter("add_prod"));
        try (ProductService productService = new ProductService()) {
            if (!addProd) {
                int prodId = Integer.parseInt(prodIdString);
                if (prodId < 0) throw new ActionException("incorrect parameter (negative id)");
                Product product = productService.findProduct(prodId);
                if (product == null) throw new ActionException("product not found");
                log.debug("product  {}", product);
                req.setAttribute("product", product);
            }
            List<ProductType> productTypes = productService.findAllProductType();
            List<Measure> measureList = productService.findAllMeasure();
            log.debug("productTypes  {}", productTypes);
            log.debug("measureList  {}", measureList);
            req.setAttribute("typeList", productTypes);
            req.setAttribute("measureList", measureList);
            req.setAttribute("add_prod", req.getParameter("add_prod"));
            return productCard;

        } catch (NumberFormatException e) {
            throw new ActionException("incorrect parameter (invalid characters)", e);
        }
    }
}

