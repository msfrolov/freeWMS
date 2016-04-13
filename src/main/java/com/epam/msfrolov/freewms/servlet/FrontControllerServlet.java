package com.epam.msfrolov.freewms.servlet;

import com.epam.msfrolov.freewms.action.Action;
import com.epam.msfrolov.freewms.action.ActionFactory;
import com.epam.msfrolov.freewms.action.ActionResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

@WebServlet(name = "FrontControllerServlet", urlPatterns = "/wms/*")
public class FrontControllerServlet extends HttpServlet {
    private static final Logger log = LoggerFactory.getLogger(FrontControllerServlet.class);
    private ActionFactory actionFactory;

    @Override
    public void init() throws ServletException {
        actionFactory = new ActionFactory();
    }

    @Override
    public void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        String actionName = req.getMethod() + req.getPathInfo();
        log.debug("service actionName: {}", actionName);
        Map<String, String[]> parameterMap = req.getParameterMap();
        for (Map.Entry en:parameterMap.entrySet()){
            System.out.println("---------"+ en.getKey() + " " +en.getValue());
        }

        Action action = actionFactory.getAction(actionName);


        if (action == null) {
            resp.sendError(HttpServletResponse.SC_NOT_FOUND, "Not found");
            return;
        }
        log.debug("service getResult: {}", actionName);
        ActionResult result = action.execute(req, resp);
        log.debug("service getView: {}", result.getView());
        log.debug("service isRedirect: {}", result.isRedirect());

        doForwardOrRedirect(result, req, resp);
    }

    private void doForwardOrRedirect(ActionResult result, HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (result.isRedirect()) {
            String location = req.getContextPath() + "/wms/" + result.getView();
            log.debug("redirect - location: {}", location);
            resp.sendRedirect(location);
        } else {
            String path = "/WEB-INF/jsp/" + result.getView() + ".jsp";
            log.debug("forward - path: {}", path);
            req.getRequestDispatcher(path).forward(req, resp);
        }
    }
}













