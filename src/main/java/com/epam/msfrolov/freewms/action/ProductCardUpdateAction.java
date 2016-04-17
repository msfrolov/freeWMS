package com.epam.msfrolov.freewms.action;

import com.epam.msfrolov.freewms.util.AppException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ProductCardUpdateAction implements Action {
    private static final Logger log = LoggerFactory.getLogger(ProductCardUpdateAction.class);
    private ActionResult productCard = new ActionResult("product_card");

    @Override
    public ActionResult execute(HttpServletRequest req, HttpServletResponse resp) {
        String method = req.getMethod();



        return productCard;
    }
}
