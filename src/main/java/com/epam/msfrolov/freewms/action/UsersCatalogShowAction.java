package com.epam.msfrolov.freewms.action;

import com.epam.msfrolov.freewms.model.Product;
import com.epam.msfrolov.freewms.model.User;
import com.epam.msfrolov.freewms.model.UserRole;
import com.epam.msfrolov.freewms.service.UserService;
import com.epam.msfrolov.freewms.util.Validator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class UsersCatalogShowAction implements Action {
    private static final Logger log = LoggerFactory.getLogger(UsersCatalogShowAction.class);
    private static final int DEFAULT_PAGE_NUMBER = 1;
    private static final int DEFAULT_PAGE_SIZE = 10;
    private ActionResult usersCatalog = new ActionResult("users_catalog");

    @Override
    public ActionResult execute(HttpServletRequest req, HttpServletResponse resp) {
        Integer pageNumber;
        String pageString = req.getParameter("page_number");
        if (Validator.isValid(pageString, Validator.DIGITS_MIN1_MAX9)) {
            pageNumber = Integer.parseInt(pageString);
            if (pageNumber < 1) {
                log.debug("set the default page ");
                pageNumber = DEFAULT_PAGE_NUMBER;
            }
        } else {
            log.debug("set the default page ");
            pageNumber = DEFAULT_PAGE_NUMBER;
        }
        log.debug("current page number {}", pageNumber);
        List<Product> products;
        try (UserService userService = new UserService()) {
            List<User> usersList = userService.findAllUsers();
            int elemNumber = usersList.size();
            int totalPages = (int) Math.ceil((double) elemNumber / DEFAULT_PAGE_SIZE);
            req.setAttribute("total_pages", totalPages);
            usersList = userService.getUsersForPage(pageNumber, DEFAULT_PAGE_SIZE);
            req.setAttribute("users_list", usersList);
        }
        boolean isAdmin;
        try {
            User user = (User) req.getSession(false).getAttribute("user");
            isAdmin = user.getRole().equals(UserRole.ADMIN);
        } catch (Exception e) {
            isAdmin = false;
        }
        log.debug("isAdmin: {}", isAdmin);

        req.setAttribute("page_number", pageNumber);
        req.setAttribute("isAdmin", isAdmin);
        return usersCatalog;
    }
}
