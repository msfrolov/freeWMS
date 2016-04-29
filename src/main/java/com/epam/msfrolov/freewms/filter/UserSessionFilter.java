package com.epam.msfrolov.freewms.filter;

import com.epam.msfrolov.freewms.model.User;
import com.epam.msfrolov.freewms.model.UserRole;
import com.epam.msfrolov.freewms.util.FileManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Properties;

@WebFilter(filterName = "1UserSessionFilter", urlPatterns = "/wms/*")
public class UserSessionFilter implements Filter {
    private static final String FORBIDDEN_PAGE = "/WEB-INF/jsp/forbidden.jsp";
    private static final String NOTICE__PAGE = "/WEB-INF/jsp/notice.jsp";
    private static final Logger log = LoggerFactory.getLogger(UserSessionFilter.class);
    private Properties accessRequiredProp;
    private Properties accessLevelsProp;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        accessRequiredProp = FileManager.getProperties("properties/access.required.properties");
        accessLevelsProp = FileManager.getProperties("properties/access.levels.properties");
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws IOException, ServletException {
        doFilter((HttpServletRequest) req, (HttpServletResponse) resp, chain);
    }

    @Override
    public void destroy() {
    }

    public void doFilter(HttpServletRequest req, HttpServletResponse resp, FilterChain chain) throws IOException, ServletException {
        String pathInfo = req.getPathInfo();
        Object o;
        try {
            o = req.getSession(false).getAttribute("user");
        } catch (NullPointerException e) {
            o = null;
        }
        UserRole role;
        if (o == null)
            role = UserRole.GUEST;
        else
            role = ((User) o).getRole();
        if (role == null) role = UserRole.GUEST;
        log.debug("UserSessionFilter pathInfo: {}", pathInfo);
        log.debug("UserSessionFilter user: {}", o);
        log.debug("UserSessionFilter role: {}", role);
        if (checkAccess(pathInfo, role)) {
            chain.doFilter(req, resp);
        } else {
            if (role == UserRole.GUEST) {
                log.debug("  Please, sign in or sign up");
                notice(req, resp, chain);
            } else {
                log.debug("  Forbidden!");
                forbidden(req, resp, chain);
            }
        }
    }


    private boolean checkAccess(String pathInfo, UserRole role) {
        int currentAccessLevel = getAccessLevel(role);
        try {
            int requiredLevel = Integer.parseInt(accessRequiredProp.getProperty(pathInfo));
            log.debug("Access current {} required {}", currentAccessLevel, requiredLevel);
            if (requiredLevel <= currentAccessLevel) return true;
            else return false;
        } catch (Exception e) {
            throw new FilterException("failed to get the level of access", e);
        }
    }

    private int getAccessLevel(UserRole role) {
        Integer id = role.getId();
        log.debug("get role id: {}", id);
        int i;
        try {
            i = Integer.parseInt(accessLevelsProp.getProperty(String.valueOf(id)));
        }catch (Exception e){
            throw new FilterException("failed to get the level of access", e);
        }
        log.debug("get access level: {}", i);
        return i;
    }

    private void forbidden(HttpServletRequest req, HttpServletResponse resp, FilterChain chain) throws ServletException, IOException {
        int statusCode = 403;
        String requestUri = req.getMethod() + req.getPathInfo();
        req.setAttribute("statusCode", statusCode);
        req.setAttribute("reqUri", requestUri);
        String path = FORBIDDEN_PAGE;
        log.debug("RequestDispatcher");
        log.debug("Status code: {}", statusCode);
        log.debug("URI: {}", requestUri);
        req.getRequestDispatcher(path).forward(req, resp);
    }


    private void notice(HttpServletRequest req, HttpServletResponse resp, FilterChain chain) throws ServletException, IOException {
        String path = NOTICE__PAGE;
        log.debug("RequestDispatcher");
        req.getRequestDispatcher(path).forward(req, resp);
    }


}