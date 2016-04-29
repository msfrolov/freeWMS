package com.epam.msfrolov.freewms.action;

import com.epam.msfrolov.freewms.model.Gender;
import com.epam.msfrolov.freewms.model.Individual;
import com.epam.msfrolov.freewms.model.User;
import com.epam.msfrolov.freewms.model.UserRole;
import com.epam.msfrolov.freewms.service.UserService;
import com.epam.msfrolov.freewms.util.Validator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class CabinetShowAction implements Action {
    private static final Logger log = LoggerFactory.getLogger(CabinetShowAction.class);
    private ActionResult productCard = new ActionResult("cabinet");

    @Override
    public ActionResult execute(HttpServletRequest req, HttpServletResponse resp) {
        try (UserService userService = new UserService()) {
            User user = userInit(req, userService);
            usersIndividInit(userService, user);
            List<UserRole> listRole = userService.getAllRoles();
            List<Gender> listGender = userService.getGender();


            req.setAttribute("role_list", listRole);
            req.setAttribute("gender_list", listGender);
            req.setAttribute("user_cabinet", user);
            req.setAttribute("individ_cabinet", user.getIndividual());
            log.debug("{}", listRole);
            log.debug("{}", listGender);
            log.debug("{}", user);
            log.debug("{}", user.getIndividual());
            return productCard;
        }
    }

    private void usersIndividInit(UserService userService, User user) {
        if (user.getIndividual() == null) {
            Individual individ = new Individual();
            individ.setName("New user");
            individ = userService.insertIndivid(individ);
            user.setIndividual(individ);
            boolean success = userService.updateUser(user);
            if (!success) throw new ActionException("failed to save user changes");
        }
    }

    private User userInit(HttpServletRequest req, UserService userService) {
        User user;
        String userId = req.getParameter("userId");
        if (userId == null || !Validator.isValid(userId, Validator.DIGITS_MIN1_MAX9)) {
            Object userObject = req.getSession(false).getAttribute("user");
            if (userObject == null) throw new ActionException("session user is not found");
            else user = (User) userObject;
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
