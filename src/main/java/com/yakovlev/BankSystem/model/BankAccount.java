package com.yakovlev.BankSystem.model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "bank_account")
public class BankAccount extends AbstractModel implements Serializable {

    private static final long serialVersionUID = 1497422987895711030L;

    @Column(name = "currency")
    private double currency;
    //валюта

    @Column(name = "amount")
    private double amount;
    //кол-во

    @Column(name = "amount_of_credit")
    private double amountOfCredit;
    //сумма кредита
    //найти клиента
    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    public BankAccount() {
    }



    public double getCurrency() {
        return currency;
    }

    public void setCurrency(double currency) {
        this.currency = currency;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public double getAmountOfCredit() {
        return amountOfCredit;
    }

    public void setAmountOfCredit(double amountOfCredit) {
        this.amountOfCredit = amountOfCredit;
    }

}
