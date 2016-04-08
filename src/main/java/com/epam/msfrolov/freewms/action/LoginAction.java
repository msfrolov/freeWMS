package com.epam.msfrolov.freewms.action;

import com.epam.msfrolov.freewms.dao.DaoFactory;
import com.epam.msfrolov.freewms.dao.UserDao;
import com.epam.msfrolov.freewms.model.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginAction implements Action {
    private ActionResult home = new ActionResult("home", true);
    private ActionResult loginAgain = new ActionResult("login");


    @Override
    public ActionResult execute(HttpServletRequest req, HttpServletResponse resp) {
        String login = req.getParameter("login");
        String password = req.getParameter("password");

//        //todo: use service instead dao
//        UserDao userDao = DaoFactory.getInstance().getUserDao();
//
//        //todo: use salted hash instead password
//        User user = userDao.findByCredentials(login, password);
        User user = new User();
        user.setName(login);
        user.setPassword(password);

        if (user != null) {
            req.getSession().setAttribute("user", user);
            return home;
        } else {
            req.setAttribute("loginError", "login or password incorrect");
            return loginAgain;
        }

    }
}
