package com.yakovlev.BankSystem.domain.service;

import com.yakovlev.BankSystem.model.Bank;
import com.yakovlev.BankSystem.model.Passport;
import com.yakovlev.BankSystem.model.User;

import java.util.Collection;

public interface UserService {

    Collection<User> getAllUsers();

    Collection<User> getAllUnconfirmedUsers();

    Collection<User> getAllConfirmedUsers();

    User getUserById(long userId);

    void saveUser(User user);

    void deleteUser(User user);

    void saveBank(Bank bank);

    void savePassport(Passport passport);

    void addRoleEmployee(long userId);
}
