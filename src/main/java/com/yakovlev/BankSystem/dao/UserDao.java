package com.yakovlev.BankSystem.dao;

import com.yakovlev.BankSystem.model.User;

import java.util.Collection;

public interface UserDao {

    Collection<User> getAll();

    User getById(long userId);

    Collection<User> getAllUnconfirmed();

    Collection<User> getAllConfirmed();

    void save(User user);

    void saveOrUpdate(User user);

    void delete(User user);
}
