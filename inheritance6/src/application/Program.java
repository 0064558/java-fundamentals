package application;

import entities.Company;
import entities.Individual;
import entities.TaxPlayer;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Program {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        List<TaxPlayer> list = new ArrayList<>();

        System.out.print("Enter the number of tax players: ");
        int n = sc.nextInt();

        for (int i = 0; i < n; i++) {
            sc.nextLine();
            System.out.println("Tax payer #" + (i + 1) + " data: ");
            System.out.print("Individual company (i/c)? ");
            String resp = sc.nextLine();
            if (resp.equals("i")) {
                System.out.print("Name: ");
                String name = sc.nextLine();
                System.out.print("Anual income: ");
                Double anualIncoming = sc.nextDouble();
                System.out.print("Health expenditures: ");
                Double healthExpenditures = sc.nextDouble();
                //Individual individual = new Individual(name, anualIncoming, healthExpenditures);
                list.add(new Individual(name, anualIncoming, healthExpenditures));
            } else if (resp.equals("c")) {
                System.out.print("Name: ");
                String name = sc.nextLine();
                System.out.print("Anual income: ");
                Double anualIncoming = sc.nextDouble();
                System.out.print("Number of employees: ");
                Integer numberOfEmployees = sc.nextInt();
                //Company company = new Company(name, anualIncoming, numberOfEmployees);
                list.add(new Company(name, anualIncoming, numberOfEmployees));
            }
        }

        double sum = 0.0;
        System.out.println("-----------------------------------------------------------");
        System.out.println("\nTAXES PAID: ");
        for (TaxPlayer tp : list) {
            System.out.println("Name: " + tp.getName() + " | Tax: $" + String.format("%.2f", tp.tax()));
            sum += tp.tax();
        }

        System.out.printf("Total Taxes: %.2f", sum);
        sc.close();
    }
}
