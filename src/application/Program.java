package application;

import entities.Account;

import java.util.Scanner;

public class Program {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("Enter account number: ");
        Integer number = sc.nextInt();
        sc.nextLine();
        System.out.println("Enter account holder: ");
        String holder = sc.nextLine();
        System.out.println("Initial deposit (y/n)? ");
        char resp = sc.next().toLowerCase().charAt(0);
        Account acc;

        if (resp == 'y') {
            System.out.println("Enter initial deposit value: ");
            double initialDeposit = sc.nextDouble();
            acc = new Account(number, holder, initialDeposit);
            System.out.println("Account data: ");
            System.out.println(acc.toString());
        } else {
            acc = new Account(number, holder);
            System.out.println("Account data: ");
            System.out.println(acc.toString());
        }

        System.out.println();

        System.out.println("Enter a deposit value: ");
        double amount = sc.nextDouble();
        acc.deposit(amount);
        System.out.println();
        System.out.println("Updated account data: ");
        System.out.println(acc.toString());

        System.out.println();

        System.out.println("Enter a withdraw value: ");
        amount = sc.nextDouble();
        acc.withdraw(amount);
        System.out.println();
        System.out.println("Updated account data: ");
        System.out.println(acc.toString());
    }
}