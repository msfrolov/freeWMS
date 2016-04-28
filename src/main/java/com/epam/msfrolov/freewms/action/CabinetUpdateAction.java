package com.epam.msfrolov.freewms.action;

import com.epam.msfrolov.freewms.model.Gender;
import com.epam.msfrolov.freewms.model.User;
import com.epam.msfrolov.freewms.model.UserRole;
import com.epam.msfrolov.freewms.service.UserService;
import com.epam.msfrolov.freewms.util.AppException;
import com.epam.msfrolov.freewms.util.Validator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.epam.msfrolov.freewms.util.Validator.DIGITS_MIN1_MAX9;
import static com.epam.msfrolov.freewms.util.Validator.isValid;

public class CabinetUpdateAction implements Action {
    private static final Logger log = LoggerFactory.getLogger(CabinetUpdateAction.class);
    private ActionResult cabinet = new ActionResult("cabinet");
    private ActionResult home = new ActionResult("home");


    @Override
    public ActionResult execute(HttpServletRequest req, HttpServletResponse resp) {
        boolean save = "Save".equalsIgnoreCase(req.getParameter("Save"));
        boolean close = "Close".equalsIgnoreCase(req.getParameter("Close"));
        log.debug("Save {}", save);
        log.debug("Close {}", close);
        if (save) {
            try (UserService userService = new UserService()) {
                String userID = req.getParameter("EditId");
                User user = null;
                log.debug("userID: {}", userID);
                Map<String, String> violation = new HashMap<>();
                String idString = req.getParameter("EditId");
                if (!isValid(idString, DIGITS_MIN1_MAX9)) {
                    violation.put("id", "user with such id was not found (not a number)");
                } else {
                    int id = Integer.parseInt(idString);
                    if (id < 1) violation.put("id", "user with such id was not found (negative id)");
                    else if ((user = userService.findById(id)) == null) {
                        violation.put("id", "user with such id is missing or deleted");
                    }
                }
                if (user == null) user = new User();
                String roleString = req.getParameter("EditRole");
                UserRole userRole = null;
                if (!isValid(roleString, DIGITS_MIN1_MAX9))
                    violation.put("role", "role not found (not a number)");
                else {
                    int roleId = Integer.parseInt(roleString);
                    if (roleId < 1) violation.put("role", "role not found (negative id)");
                    else {
                        userRole = userService.findUserRoleById(roleId);
                        if (userRole == null) violation.put("role", "role with such id is missing or deleted");
                    }
                }

                boolean allowEditRole = false;
                try {
                    User userSes = (User) req.getSession(false).getAttribute("user");
                    if (userSes.getRole().equals(UserRole.ADMIN)) allowEditRole = true;
                } catch (Exception e) {
                    log.debug("the user is not an admin"); //this exception does not have to handle
                }
                boolean roleHasBeenModified = false;
                try {
                    String currentRoleString = req.getParameter("CurrentRole");
                    int currentRoleId = Integer.parseInt(currentRoleString);
                    UserRole currentRole = userService.findUserRoleById(currentRoleId);
                    log.debug("currentRole: {}", currentRole);
                    log.debug("user.getRole(): {}", user.getRole());
                    if (currentRole != null && userRole != null && !currentRole.equals(userRole)) {
                        roleHasBeenModified = true;
                    }
                } catch (Exception e) {
                    log.debug("the role has not been changed"); //this exception does not have to handle
                }
                if (roleHasBeenModified && !allowEditRole)
                    violation.put("role", "no access to change the role");
                //noinspection ConstantConditions
                if (roleHasBeenModified && allowEditRole && userRole != null) {
                    user.setRole(userRole);
                }
                String stringIndName = req.getParameter("EditIndName");
                if (!Validator.isValid(stringIndName, Validator.LETTERS_DIGITS_WS))
                    violation.put("individName", "incorrect characters in the field first name");
                else user.getIndividual().setName(stringIndName);
                String stringIndLName = req.getParameter("EditIndLastName");
                if (!Validator.isValid(stringIndLName, Validator.LETTERS_DIGITS_WS))
                    violation.put("individLName", "incorrect characters in the field last name");
                else user.getIndividual().setLastName(stringIndLName);
                String stringGender = req.getParameter("EditGender");
                try {
                    Gender gender = userService.findGender(Integer.parseInt(stringGender));
                    if (gender == null)
                        violation.put("gender", "gender was not found");
                    else
                        user.getIndividual().setGender(gender);
                } catch (Exception e) {
                    violation.put("gender", "incorrect value");
                }
                boolean success = false;
                if (violation.isEmpty())
                    success = userService.updateUserAndIndivid(user, req, violation);
                List<UserRole> listRole = userService.getAllRoles();
                List<Gender> listGender = userService.getGender();
                req.setAttribute("role_list", listRole);
                req.setAttribute("gender_list", listGender);
                req.setAttribute("user_cabinet", user);
                req.setAttribute("individ_cabinet", user.getIndividual());
                req.setAttribute("success", success);
                req.setAttribute("violation", violation);
                log.debug("listRole {}", listRole);
                log.debug("listGender {}", listGender);
                log.debug("user {}", user);
                log.debug("user.getIndividual() {}", user.getIndividual());
                return cabinet;
            }
        } else if (close) {
            log.debug("- click on the button Close");
            return home;
        } else throw new AppException("condition was not provided");
    }

}
