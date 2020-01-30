package com.yakovlev.BankSystem.dao;

import com.yakovlev.BankSystem.model.BankAccount;


import java.util.Collection;

public interface BankAccountDao {

    Collection<BankAccount> getAll();

//    List<Client> getClient();

    BankAccount getBankAccountById(long bankAccountId);

    void saveBankAccount (BankAccount bankAccount);

    void editBankAccount(BankAccount bankAccount);

    void deleteBankAccount(BankAccount bankAccount);

}
