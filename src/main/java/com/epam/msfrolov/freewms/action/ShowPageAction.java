package com.epam.msfrolov.freewms.action;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ShowPageAction implements Action {
    private ActionResult result;
    private static final Logger log = LoggerFactory.getLogger(ShowPageAction.class);
    public ShowPageAction(String page) {
        log.debug("Constructor ShowPageAction page {}", page);
        this.result = new ActionResult(page);
    }

    @Override
    public ActionResult execute(HttpServletRequest req, HttpServletResponse resp) {
        log.debug("Execute ShowPageAction result page {} isRes {}", result.getView(), result.isRedirect());
        return result;
    }
}



