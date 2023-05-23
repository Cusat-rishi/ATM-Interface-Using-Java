package com.system.atm;
import java.util.ArrayList;
import java.util.List;

public class TransactionHistory {
    private List<Transaction> transactions;

    public TransactionHistory() {
        transactions = new ArrayList<>();
    }

    public void addTransaction(String type, double amount) {
        Transaction transaction = new Transaction(type, amount);
        transactions.add(transaction);
    }

    public void showTransactionHistory() {
        if (transactions.isEmpty()) {
            System.out.println("No transactions found.");
        } else {
            System.out.println("Transaction History:");
            for (Transaction transaction : transactions) {
                System.out.println(transaction.getType() + ": Rs" + transaction.getAmount());
            }
        }
    }
}
