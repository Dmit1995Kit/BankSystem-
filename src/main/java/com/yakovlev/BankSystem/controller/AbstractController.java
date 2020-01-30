package com.yakovlev.BankSystem.controller;

import com.yakovlev.BankSystem.domain.service.UserService;
import com.yakovlev.BankSystem.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;

public class AbstractController {
    private static final Logger logger = LoggerFactory.getLogger(AbstractController.class);

    @Autowired
   protected UserService userService;

    User getCurrentUser() {
        try {
            User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            return userService.getUserById(user.getId());
        } catch (ClassCastException ex) {
            logger.info("GET: " + ex.getMessage());
            return null;
        }
    }
}
