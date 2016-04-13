package com.epam.msfrolov.freewms.action;

import com.epam.msfrolov.freewms.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginAction implements Action {
    private ActionResult home = new ActionResult("home", true);
    private ActionResult loginAgain = new ActionResult("signin");
    private static final Logger log = LoggerFactory.getLogger(LoginAction.class);

    @Override
    public ActionResult execute(HttpServletRequest req, HttpServletResponse resp) {

        log.debug("LoginAction execute");
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        String sign_in = req.getParameter("sign_in");
        String sign_up = req.getParameter("sign_up");


        log.debug(login);
        log.debug(password);
        log.debug("sign_in {}",sign_in);
        log.debug("sign_up {}", sign_up);


//        //todo: use service instead dao
//        UserDao userDao = DaoFactory.getInstance().getUserDao();
//
//        //todo: use salted hash instead password
//        User user = userDao.findByCredentials(login, password);
        User user = new User();
        if (login != null && password !=null) {
            user.setName(login);
            user.setPassword(password);
            log.debug("new User ", user);
        }else
            log.debug("user or password null");
        if (user.getName() != null) {
            req.getSession().setAttribute("user", user);
            return home;
        } else {
            req.setAttribute("loginError", "login or password incorrect");
            return loginAgain;
        }

    }
}
