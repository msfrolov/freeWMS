package com.epam.msfrolov.freewms.servlet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "ExceptionHandlerServlet", urlPatterns = "/error")
public class ExceptionHandlerServlet extends HttpServlet {
    private static final Logger log = LoggerFactory.getLogger(ExceptionHandlerServlet.class);
    private static final String ERROR_PAGE = "/WEB-INF/jsp/error_page.jsp";

    @Override
    public void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Throwable thr = (Throwable) req.getAttribute("javax.servlet.error.exception");
        Class thrClass = (Class) req.getAttribute("javax.servlet.error.exception_type");
        int statusCode = (int) req.getAttribute("javax.servlet.error.status_code");
        String requestUri = (String) req.getAttribute("javax.servlet.error.request_uri");
        String errorMessage = (String) req.getAttribute("javax.servlet.error.message");
        if (requestUri != null) {
            requestUri = "Unknown";
        }
        log.debug("Status code: {}", statusCode);
        log.debug("URI: {}", requestUri);
        log.debug("Error message : {}", errorMessage);
        log.debug("Exception type : {}", thrClass);
        if (thr != null) {
            log.debug("Throwable : {}", thr.getMessage());
            thr.printStackTrace();
        }
        String path = ERROR_PAGE;
        req.getRequestDispatcher(path).forward(req, resp);
    }
}
