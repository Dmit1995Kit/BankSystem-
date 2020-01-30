package com.yakovlev.BankSystem.dao;

import com.yakovlev.BankSystem.model.Bank;

import java.util.Collection;

public interface BankDao {

    Collection<Bank> getAll();

    Bank getBankById(long bankId);

    void saveBank(Bank bank);

    void editBank(Bank bank);

    void deleteBank(Bank bank);

//    Collection<Client> getAllClients();
//
//    Collection<Worker> getAllWorkers();
}
