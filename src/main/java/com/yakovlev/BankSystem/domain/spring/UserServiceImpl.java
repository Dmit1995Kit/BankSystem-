package com.yakovlev.BankSystem.domain.spring;

import com.yakovlev.BankSystem.dao.BankDao;
import com.yakovlev.BankSystem.dao.PassportDao;
import com.yakovlev.BankSystem.dao.UserDao;
import com.yakovlev.BankSystem.domain.service.UserService;
import com.yakovlev.BankSystem.model.Bank;
import com.yakovlev.BankSystem.model.Passport;
import com.yakovlev.BankSystem.model.User;
import com.yakovlev.BankSystem.model.UserRole;
import com.yakovlev.BankSystem.model.enums.ListRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;

@Service
public class UserServiceImpl implements UserService, UserDetailsService {

    @Autowired
    UserDao userDao;

    @Autowired
    BankDao bankDao;

    @Autowired
    PassportDao passportDao;

    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userDao.getById(Long.parseLong(username));

        if (user == null) throw new UsernameNotFoundException("username: " + username + " not found!");

        return user;
    }

    @Override
    public Collection<User> getAllUsers() {
        return userDao.getAll();
    }

    @Override
    public Collection<User> getAllUnconfirmedUsers() {
        return userDao.getAllUnconfirmed();
    }

    @Override
    public Collection<User> getAllConfirmedUsers() {
        return userDao.getAllConfirmed();
    }

    @Override
    public User getUserById(long userId) {
        return userDao.getById(userId);
    }

    @Override
    public void saveUser(User user) {
       user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
    }

    @Override
    public void deleteUser(User user) {
        userDao.delete(user);
    }

    @Override
    public void saveBank(Bank bank) {
          bankDao.saveBank(bank);
    }

    @Override
    public void savePassport(Passport passport) {
           User user = getUserById(passport.getId());
           passport.setUser(user);
           UserRole userRole = new UserRole();
           userRole.setId(1);
           userRole.setListRole(ListRole.ROLE_CLIENT);
           userRole.addUser(user);
           user.addUserRole(userRole);
           userDao.saveOrUpdate(user);
    }

    @Override
    public void addRoleEmployee(long userId) {

        User user = getUserById(userId);
        UserRole userRole = new UserRole();
        userRole.setId(2);
        userRole.setListRole(ListRole.ROLE_EMPLOYEE);
        userRole.addUser(user);
        user.addUserRole(userRole);
        userDao.saveOrUpdate(user);
    }
}
