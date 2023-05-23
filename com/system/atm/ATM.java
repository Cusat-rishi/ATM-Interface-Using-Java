package com.system.atm;
import java.util.Scanner;
class ATM {
    private User user;
    private TransactionHistory transactionHistory;

    public ATM() {
        user = null;
        transactionHistory = new TransactionHistory();
    }

    public void start() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter User ID: ");
        String userId = scanner.nextLine();
        System.out.print("Enter User PIN: ");
        String userPin = scanner.nextLine();

        if (authenticateUser(userId, userPin)) {
            showMenu();
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline character

            switch (choice) {
                case 1:
                    transactionHistory.showTransactionHistory();
                    break;
                case 2:
                    performWithdrawal(scanner);
                    break;
                case 3:
                    performDeposit(scanner);
                    break;
                case 4:
                    performTransfer(scanner);
                    break;
                case 5:
                    System.out.println("Thank you for using the ATM. Goodbye!");
                    break;
                default:
                    System.out.println("Invalid choice.");
                    break;
            }
        } else {
            System.out.println("Authentication failed. Please try again.");
        }
    }

    private boolean authenticateUser(String userId, String userPin) {
        
        if (userId.equals("rk2023") && userPin.equals("2001")) {
            user = new User(userId, userPin, 1000.0);
            return true;
        }
        return false;
    }
    private void showMenu() {
        System.out.println("\nATM Menu:");
        System.out.println("1. Transaction History");
        System.out.println("2. Withdraw");
        System.out.println("3. Deposit");
        System.out.println("4. Transfer");
        System.out.println("5. Quit");
        System.out.print("Enter your choice: ");
    }

    private void performWithdrawal(Scanner scanner) {
        System.out.print("Enter the amount to withdraw: Rs");
        double amount = scanner.nextDouble();

        if (amount <= 0) {
            System.out.println("Invalid amount.");
        } else if (amount > user.getBalance()) {
            System.out.println("Insufficient balance.");
        } else {
            user.updateBalance(-amount);
            transactionHistory.addTransaction("Withdrawal", amount);
            System.out.println("Withdrawal successful. Remaining balance: Rs" + user.getBalance());
        }
    }

    private void performDeposit(Scanner scanner) {
        System.out.print("Enter the amount to deposit: Rs.");
        double amount = scanner.nextDouble();

        if (amount <= 0) {
            System.out.println("Invalid amount.");
        } else {
            user.updateBalance(amount);
            transactionHistory.addTransaction("Deposit", amount);
            System.out.println("Deposit successful. New balance: Rs" + user.getBalance());
        }
    }

    private void performTransfer(Scanner scanner) {
        System.out.print("Enter the recipient's account number: ");
        String recipientAccount = scanner.nextLine();

        System.out.print("Enter the amount to transfer: Rs");
        double amount = scanner.nextDouble();

        if (amount <= 0) {
            System.out.println("Invalid amount.");
        } else if (amount > user.getBalance()) {
            System.out.println("Insufficient balance.");
        } else {
            // Add logic here to transfer the amount to the recipient's account
            // For simplicity, we'll just update the balances of both accounts
            user.updateBalance(-amount);
            transactionHistory.addTransaction("Transfer to " + recipientAccount, amount);
            System.out.println("Transfer successful. Remaining balance: Rs" + user.getBalance());
        }
    }
}