package com.epam.msfrolov.freewms.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

@WebFilter(filterName = "0CharEncodingFilter", urlPatterns = "/wms/*")
public class CharEncodingFilter implements Filter {

    public static final String ENV = "UTF-8";

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws IOException, ServletException {
        req.setCharacterEncoding(ENV);
        chain.doFilter(req, resp);
    }

    @Override
    public void destroy() {
    }
}
