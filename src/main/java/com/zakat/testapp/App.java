package com.zakat.testapp;

import org.apache.log4j.Logger;

import java.io.FileReader;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;


public class App {
    public static void main(String[] args) {
        List<Account> accountList = new ArrayList<>();
        //Create list of Account with 4 unique id
        for (int i = 0; i < 4; i++) {
            char firstNameOfAccountSymbol = (char) ('A' + (char) (Math.random() * 26) + Math.round(Math.random()) * ('a' - 'A'));
            int secondNameOfAccountSymbol = (int) (Math.random() * 10);
            String generateAccountId = Character.toString(firstNameOfAccountSymbol).toUpperCase() + secondNameOfAccountSymbol;
            Account createdAccount = new Account(generateAccountId);
            accountList.add(createdAccount);
        }

        //check total amount funds on accounts before start transactions
        System.out.println(accountList);
        getMoneySumFromAllAccounts(accountList);

        //send task to ES and start transactions between random accounts from accounts list

        ExecutorService executorService = Executors.newFixedThreadPool(3);
        for (int i = 0; i < 30; i++) {
            Random random = new Random();
            int firstAccId = random.nextInt(4);
            int secondAccId = random.nextInt(4);
            while (firstAccId == secondAccId) {
                secondAccId = random.nextInt(4);
            }
            executorService.submit(new AccountTransaction(accountList.get(firstAccId), accountList.get(secondAccId)));
        }
        executorService.shutdown();
        try {
            executorService.awaitTermination(5, TimeUnit.MINUTES);
        } catch (InterruptedException e) {
            e.printStackTrace();
            logger.error("Thread is interrupted");
        }


        //check total amount funds on accounts after end of transactions

        System.out.println(accountList);
        getMoneySumFromAllAccounts(accountList);

    }

    final static Logger logger = Logger.getLogger(FileReader.class);

         // this method сhecks the total amount of funds on all accounts

    public static void getMoneySumFromAllAccounts(List<Account> accountList) {

        int totalAmount = accountList.stream().map(e -> e.getMoney()).mapToInt(Integer::intValue).sum();
        logger.info("Total amount on all accounts = " + totalAmount);
    }


}
