package com.epam.msfrolov.freewms.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class HomeShowAction implements Action {
    private ActionResult result;

    public HomeShowAction() {
        this.result = new ActionResult("home");
    }

    @Override
    public ActionResult execute(HttpServletRequest req, HttpServletResponse resp) {
        return result;
    }
}



