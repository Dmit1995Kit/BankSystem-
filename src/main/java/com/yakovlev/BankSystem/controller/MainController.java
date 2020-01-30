package com.yakovlev.BankSystem.controller;

import com.yakovlev.BankSystem.domain.service.UserService;
import com.yakovlev.BankSystem.domain.validator.UserValidator;
import com.yakovlev.BankSystem.model.User;
import com.yakovlev.BankSystem.model.UserRole;
import com.yakovlev.BankSystem.model.enums.ListRole;
import com.yakovlev.BankSystem.utiles.JsonResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Locale;
import java.util.Map;
import java.util.Set;

@Controller
public class MainController {

    private static final Logger logger = LoggerFactory.getLogger(MainController.class);

    @Autowired
    private UserService userService;

    @Autowired
    private UserValidator userValidator;

    @Autowired
    private MessageSource msgSrc;

    @RequestMapping(value = "/", method = {RequestMethod.GET, RequestMethod.HEAD})
    public String login(Model model) {
        return "login";
    }

    @RequestMapping(value = "/access_denied", method = {RequestMethod.GET, RequestMethod.HEAD})
    public String accessDenied() {
        return "access_denied";
    }

    @RequestMapping(value = "/dashboard/redirect", method = {RequestMethod.GET, RequestMethod.HEAD})
    public String dashboardRedirect() {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (user == null) return "redirect:/";
        Set<UserRole> roles = user.getUserRoles();
        for (UserRole role : roles) {
            if (role.getListRole() == ListRole.ROLE_CLIENT) {
                return "redirect:/dashboard/client/main";
            } else if (role.getListRole() == ListRole.ROLE_EMPLOYEE) {
                return "redirect:/dashboard/employee/main";
            } else if (role.getListRole() == ListRole.ROLE_ADMIN) {
                return "redirect:/dashboard/admin/main";
            }
        }
        return "access_denied";
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
    public @ResponseBody JsonResponse createNewUser( User user) {

        logger.info("POST: /register");
        Map<String, String> data = userValidator.validate(user);
        if (!data.isEmpty()) {
            return new JsonResponse("ERROR", data);
        }
        userService.saveUser(user);
        data.put("message", msgSrc.getMessage("registerform.successMessage", null, Locale.getDefault()));
        return new JsonResponse("OK", data);
    }

}