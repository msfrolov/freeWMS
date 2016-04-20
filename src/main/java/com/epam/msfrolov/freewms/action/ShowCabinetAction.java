package com.epam.msfrolov.freewms.action;

import com.epam.msfrolov.freewms.model.Individual;
import com.epam.msfrolov.freewms.model.User;
import com.epam.msfrolov.freewms.service.UserService;
import com.epam.msfrolov.freewms.util.Validator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ShowCabinetAction implements Action {
    private static final Logger log = LoggerFactory.getLogger(ShowCabinetAction.class);
    private ActionResult productCard = new ActionResult("Cabinet");

    @Override
    public ActionResult execute(HttpServletRequest req, HttpServletResponse resp) {
        try (UserService userService = new UserService()) {
            User user = userInit(req, userService);
            usersIndividInit(userService, user);

        }
    }

    private void usersIndividInit(UserService userService, User user) {
        if (user.getIndividual() == null) {
            Individual individ = new Individual();
            individ.setName(user.getName());
            user.setIndividual(individ);
            boolean success = userService.updateUser(user);
            if (!success) throw new ActionException("failed to save user changes");
        }
    }

    private User userInit(HttpServletRequest req, UserService userService) {
        User user;
        String userId = req.getParameter("userId");
        if (userId == null || !Validator.isValid(userId, Validator.DIGITS_MIN1_MAX9)) {
            user = (User) req.getSession(false).getAttribute("user");
            if (user == null) throw new ActionException("session user is not found");
        } else {
            int id = Integer.parseInt(userId);
            if (id < 1) throw new ActionException("incorrect parameters for the user search (negative id)");
            user = userService.findById(id);
            if (user == null)
                throw new ActionException("incorrect parameters for the user search (user is not found)");
        }
        return user;
    }
}
