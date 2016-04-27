package com.epam.msfrolov.freewms.action;

import com.epam.msfrolov.freewms.model.User;
import com.epam.msfrolov.freewms.model.UserRole;
import com.epam.msfrolov.freewms.service.UserService;
import com.epam.msfrolov.freewms.util.AppException;
import com.epam.msfrolov.freewms.util.Validator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SignInAction implements Action {
    private static final Logger log = LoggerFactory.getLogger(SignInAction.class);
    private ActionResult home = new ActionResult("home", true);
    private ActionResult signInAgain = new ActionResult("home");

    @Override
    public ActionResult execute(HttpServletRequest req, HttpServletResponse resp) {

        String login = req.getParameter("login");
        String password = req.getParameter("password");
        boolean signIn = req.getParameter("sign_in") != null;
        boolean signUp = req.getParameter("sign_up") != null;
        log.debug("SignInAction execute: ");
        log.debug(" login {}", login);
        log.debug(" password {}", password);
        log.debug(" sign_in {}", signIn);
        log.debug(" sign_up {}", signUp);
        if (!Validator.isValid(login, Validator.LETTERS_DIGITS_WS_MIN5_MAX32)
                || !Validator.isValid(password, Validator.LETTERS_DIGITS_WS_MIN5_MAX32)) {
            log.debug("login or password is not isValid");
            req.setAttribute("signInError", "login or password is not valid");
            return signInAgain;
        }
        log.debug("try (", signUp);
        try (UserService userService = new UserService()) {
            log.debug("new UserService()");
            User user = new User();
            user.setName(login);
            user.setPassword(password);
            user.setRole(UserRole.USER);
            if (signIn) {
                log.debug("if (signIn) ");
                user = userService.signIn(user);
                return checkUser(req, user, "login or password incorrect");
            } else if (signUp) {
                log.debug("if (signUp)");
                user = userService.signUp(user);
                return checkUser(req, user, "login is not unique, try again");
            } else throw new AppException("condition was not provided");
        }
    }

    private ActionResult checkUser(HttpServletRequest req, User user, String errorMessage) {
        if (user == null) {
            log.debug("checkUser signInAgain");
            req.setAttribute("signInError", errorMessage);
            return signInAgain;
        } else {
            log.debug("checkUser home");
            req.getSession().setAttribute("user", user);
            return home;
        }
    }
}
