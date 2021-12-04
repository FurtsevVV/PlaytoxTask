package com.zakat.testapp;

import org.apache.log4j.Logger;

import java.io.FileReader;
import java.util.Objects;

public class Account {

    final static Logger logger = Logger.getLogger(FileReader.class);


    private String id;
    private int money;

    public Account() {
        money = 10000;
    }

    public Account(String id) {

        this.id = id;
        this.money = 10000;
        logger.info("Account with id " + id + " and balance = " + money + " was created");

    }

    public void deposit(int amount) {
        logger.info("Deposit to account id = " + id + ". Balance =  " + money + ", deposit = " + amount + ", new balance = " + (money + amount));

        money = money + amount;

    }

    public boolean withdraw(int amount) {
        if (amount <= money) {
            logger.info("Withdraw to account id = " + id + ". Balance =  " + money + ", withdraw = " + amount + ", new balance = " + (money - amount));

            money = money - amount;
            return true;

        } else
            logger.info("Withdraw rejected to account id = " + id + ". Balance =  " + money + ", withdraw = " + amount + ", not enough funds");

        return false;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    @Override
    public String toString() {
        return "Account{" +
                "id='" + id + '\'' +
                ", money=" + money +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Account account = (Account) o;
        return money == account.money &&
                Objects.equals(id, account.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, money);
    }
}
