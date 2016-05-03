package com.epam.msfrolov.freewms.action;

import com.epam.msfrolov.freewms.model.Measure;
import com.epam.msfrolov.freewms.model.Product;
import com.epam.msfrolov.freewms.model.ProductType;
import com.epam.msfrolov.freewms.service.ProductService;
import com.epam.msfrolov.freewms.util.AppException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.epam.msfrolov.freewms.util.Validator.*;

public class ProductCardUpdateAction implements Action {
    private static final Logger log = LoggerFactory.getLogger(ProductCardUpdateAction.class);
    private ActionResult productsCatalog = new ActionResult("products_catalog", true);
    private ActionResult productCard = new ActionResult("product_card");

    @Override
    public ActionResult execute(HttpServletRequest req, HttpServletResponse resp) {
        boolean save = "save".equalsIgnoreCase(req.getParameter("Save"));
        boolean close = "close".equalsIgnoreCase(req.getParameter("Close"));
        log.debug("Save {}", save);
        log.debug("Close {}", close);
        if (save) {
            try (ProductService productService = new ProductService()) {
                log.debug("- click on the button Save");
                Product product = null;
                Map<String, String> violation = new HashMap<>();
                String idString = req.getParameter("EditId");
                boolean addProd = "add_prod".equalsIgnoreCase(req.getParameter("add_prod"));
                if (!addProd) {
                    if (!isValid(idString, DIGITS_MIN1_MAX9))
                        violation.put("id", "the product with such id was not found (not a number)");
                    else {
                        int id = Integer.parseInt(idString);
                        if (id < 1) violation.put("id", "the product with such id was not found (negative id)");
                        else if ((product = productService.findProduct(id)) == null)
                            violation.put("id", "product with such id is missing or deleted");
                    }
                }
                if (product == null) product = new Product();
                String name = req.getParameter("EditName");
                if (!isValid(name, LETTERS_DIGITS_WS))
                    violation.put("name", "incorrect characters (only letters, digits and space character)");
                else product.setName(name);
                String typeId = req.getParameter("EditType");//type id
                if (!isValid(typeId, DIGITS_MIN1_MAX9)) violation.put("type", "selected the wrong product type");
                else {
                    ProductType productType = productService.findProdTypeById(Integer.parseInt(typeId));
                    product.setType(productType);
                }
                String measureId = req.getParameter("EditMeasure");//measure id
                if (!isValid(measureId, DIGITS_MIN1_MAX9)) violation.put("measure", "selected the wrong measure");
                else {
                    Measure measure = productService.findMeasureById(Integer.parseInt(measureId));
                    product.setMeasure(measure);
                }
                String description = req.getParameter("EditDescription");
                if (!isValid(description, LETTERS_DIGITS_WS) && !"".equals(description))
                    violation.put("description", "incorrect characters in the field description");
                else product.setDescription(description);
                String barcode = req.getParameter("EditBarcode");
                if (!isValid(barcode, DIGITS) && !"".equals(barcode))
                    violation.put("barcode", "incorrect characters in the field description");
                else product.setBarcode(barcode);
                boolean success = false;
                if (violation.isEmpty()) {
                    if (addProd) {
                        log.debug("add new product");
                        product = productService.addProduct(product);
                        if (product != null) success = true;
                        log.debug("Data saved successfully = {}", success);
                    } else {
                        log.debug("save change in product fields");
                        success = productService.saveProduct(product);
                        log.debug("Data saved successfully = {}", success);
                    }
                }
                List<ProductType> productTypes = productService.findAllProductType();
                List<Measure> measureList = productService.findAllMeasure();
                req.setAttribute("violation", violation);
                req.setAttribute("product", product);
                req.setAttribute("success", success);
                req.setAttribute("typeList", productTypes);
                req.setAttribute("measureList", measureList);
                return productCard;
            }
        } else if (close) {
            log.debug("- click on the button Close");
            return productsCatalog;
        } else throw new AppException("condition was not provided");
    }
}
