package application;

import models.entities.Contract;
import models.entities.Installment;
import models.services.ContractService;
import models.services.PaypalService;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class Program {
    public static void main(String[] args) {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        Scanner sc = new Scanner(System.in);
        System.out.println("Enter contract data: ");
        System.out.print("Number: ");
        int number = sc.nextInt();
        System.out.print("Data (dd/MM/yyyy): ");
        LocalDate date = LocalDate.parse(sc.next(), dtf);
        System.out.print("Contract value: ");
        double totalValue = sc.nextDouble();

        Contract contract = new Contract(number, date, totalValue);

        System.out.print("Enter with number of installments: ");
        int months = sc.nextInt();

        ContractService contractService = new ContractService(new PaypalService());
        contractService.processContract(contract, months);

        System.out.println("\nInstallments: ");
        for (Installment i : contract.getInstallmentnList()) {
            System.out.println(i.toString());
        }
        sc.close();
    }
}
