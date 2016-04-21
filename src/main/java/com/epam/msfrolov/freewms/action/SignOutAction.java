package com.epam.msfrolov.freewms.action;

import org.slf4j.Logger;
        import org.slf4j.LoggerFactory;

        import javax.servlet.http.HttpServletRequest;
        import javax.servlet.http.HttpServletResponse;

public class SignOutAction implements Action {
    private static final Logger log = LoggerFactory.getLogger(SignOutAction.class);
    private ActionResult welcome = new ActionResult("signin");

    @Override
    public ActionResult execute(HttpServletRequest req, HttpServletResponse resp) {
        req.getSession().invalidate();
        resp.setHeader("Pragma","no-cache");
        resp.setHeader("Cache-Control","no-store");
        resp.setHeader("Expires","0");
        resp.setDateHeader("Expires",-1);
        return welcome;
    }
}
