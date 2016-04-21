package com.epam.msfrolov.freewms.action;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class HomeShowAction implements Action {
    private static final Logger log = LoggerFactory.getLogger(HomeShowAction.class);
    private ActionResult result;

    public HomeShowAction() {
        this.result = new ActionResult("home");
    }

    @Override
    public ActionResult execute(HttpServletRequest req, HttpServletResponse resp) {
        return result;
    }
}



