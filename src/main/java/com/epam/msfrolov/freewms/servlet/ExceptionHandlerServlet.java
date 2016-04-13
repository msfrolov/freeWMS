package com.epam.msfrolov.freewms.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "ExceptionHandlerServlet", urlPatterns = "/error")
public class ExceptionHandlerServlet extends HttpServlet {

    @Override
    public void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


        Throwable throwable = (Throwable) req
                .getAttribute("javax.servlet.error.exception");
        Integer statusCode = (Integer) req
                .getAttribute("javax.servlet.error.status_code");
        String servletName = (String) req
                .getAttribute("javax.servlet.error.servlet_name");
        if (servletName == null) {
            servletName = "Unknown";
        }
        String requestUri = (String) req
                .getAttribute("javax.servlet.error.request_uri");
        if (requestUri == null) {
            requestUri = "Unknown";
        }

        // Set resp content type
        resp.setContentType("text/html");

        PrintWriter out = resp.getWriter();

        out.write("<%@ taglib prefix=\"c\" uri=\"http://java.sun.com/jsp/jstl/core\" %>");
        out.write("<%@ page contentType=\"text/html;charset=UTF-8\" language=\"java\" %>");
        out.write("<html><head><title>Exception/Error Details</title></head><body>");
        if (statusCode != 500) {
            out.write("<h3>Error Details</h3>");
            out.write("<strong>Status Code</strong>:" + statusCode + "<br>");
            out.write("<strong>Requested URI</strong>:" + requestUri);
        } else {
            out.write("<h3>Exception Details</h3>");
            out.write("<ul><li>Servlet Name:" + servletName + "</li>");
            out.write("<li>Exception Name:" + throwable.getClass().getName() + "</li>");
            out.write("<li>Requested URI:" + requestUri + "</li>");
            out.write("<li>Exception Message:" + throwable.getMessage() + "</li>");
            out.write("</ul>");
        }

        out.write("<br><br>");
        out.write("<a href=<c:redirect url=\"/wms/home\"/></a>");
        out.write("</body></html>");
    }
}
