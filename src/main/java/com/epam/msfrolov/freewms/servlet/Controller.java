package com.epam.msfrolov.freewms.servlet;

import com.epam.msfrolov.freewms.action.Action;
import com.epam.msfrolov.freewms.action.ActionFactory;
import com.epam.msfrolov.freewms.action.ActionResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class Controller extends HttpServlet {
    private static final Logger log = LoggerFactory.getLogger(Controller.class);
    private ActionFactory actionFactory;

    @Override
    public void init() throws ServletException {
        actionFactory = new ActionFactory();
    }

    @Override
    public void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");

        String actionName = req.getMethod() + req.getPathInfo();
        log.debug("actionName: {}", actionName);
        Action action = actionFactory.getAction(actionName);
        log.debug("action: {}", action);

        if (action == null) {
            log.debug("action: {}", action.getClass());
            resp.sendError(HttpServletResponse.SC_NOT_FOUND, "Not found");
            return;
        }

        ActionResult result = action.execute(req, resp);

        doForwardOrRedirect(result, req, resp);
    }

    private void doForwardOrRedirect(ActionResult result, HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        log.debug("doForwardOrRedirect isRedirect: {}", result.isRedirect());
        if (result.isRedirect()) {
            String location = req.getContextPath() + "/do" + result.getView();
            log.debug("sendRedirect location: {}", location);
            resp.sendRedirect(location);
//            String location = result.getView();
//            log.debug("sendRedirect location: {}", location);
//            resp.sendRedirect(location);
        } else {
            String path = String.format("WEB-INF/jsp/" + result.getView() + ".jsp");
            log.debug("forward path: {}", path);
            req.getRequestDispatcher(path).forward(req, resp);
        }
    }


}
