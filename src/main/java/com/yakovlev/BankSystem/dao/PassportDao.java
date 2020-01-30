package com.yakovlev.BankSystem.dao;

import com.yakovlev.BankSystem.model.Passport;

import java.util.Collection;

public interface PassportDao {

    public Collection<Passport> getAll();

    public Passport getById(long passportId);

    public void savePassport(Passport passport);

    public void deletePassport(Passport passport);
}
