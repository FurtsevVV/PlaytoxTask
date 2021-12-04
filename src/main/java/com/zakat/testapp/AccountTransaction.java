package com.zakat.testapp;

import java.util.Random;

public class AccountTransaction implements Runnable {

    private Account account1;
    private Account account2;

    public AccountTransaction(Account account1, Account account2) {
        this.account1 = account1;
        this.account2 = account2;
    }

    @Override
    public void run() {

        Random random = new Random();
        int sleepTime = random.ints(1000, 2000).findFirst().getAsInt();
        try {
            Thread.sleep(sleepTime);
        } catch (InterruptedException e) {

            e.printStackTrace();

        }
        int seedTransactions = random.nextInt(3);
        //if seedTransactions=1 money withdraw from Account1 to Account2
        //if seedTransactions=2 money withdraw from Account2 to Account1
        int amountOfTransaction = random.nextInt(10000);

        if (seedTransactions == 1) {
            if (account1.withdraw(amountOfTransaction)) {
                account2.deposit(amountOfTransaction);
            }
        } else {
            if (account2.withdraw(amountOfTransaction)) {
                account1.deposit(amountOfTransaction);
            }
        }
    }
}
