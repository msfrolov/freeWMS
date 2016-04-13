package com.epam.msfrolov.freewms.action;

import com.epam.msfrolov.freewms.model.User;
import com.epam.msfrolov.freewms.service.UserService;
import com.epam.msfrolov.freewms.util.AppException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginAction implements Action {
    private static final Logger log = LoggerFactory.getLogger(LoginAction.class);
    private ActionResult home = new ActionResult("home", true);
    private ActionResult signInAgain = new ActionResult("signin");

    @Override
    public ActionResult execute(HttpServletRequest req, HttpServletResponse resp) {

        String login = req.getParameter("login");
        String password = req.getParameter("password");
        boolean signIn = req.getParameter("sign_in") != null;
        boolean signUp = req.getParameter("sign_up") != null;
        log.debug("LoginAction execute: ");
        log.debug(" login {}", login);
        log.debug(" password {}", password);
        log.debug(" sign_in {}", signIn);
        log.debug(" sign_up {}", signUp);
        if (login == null || password == null) {
            req.setAttribute("loginError", "login or password incorrect");
            return signInAgain;
        }
        try (UserService userService = new UserService()) {
            User user = new User();
            user.setName(login);
            user.setPassword(password);
            if (signIn) {
                user = userService.signIn(user);
                return checkUser(req, user, "login or password incorrect");
            } else if (signUp) {
                user = userService.signUp(user);
                return checkUser(req, user, "login is not unique, try again");
            } else throw new AppException("condition was not provided");
        }
    }

    private ActionResult checkUser(HttpServletRequest req, User user, String errorMessage) {
        if (user == null) {
            req.setAttribute("loginError", errorMessage);
            return signInAgain;
        } else {
            req.getSession().setAttribute("user", user);
            return home;
        }
    }
}
