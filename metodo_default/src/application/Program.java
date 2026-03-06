/*
* Fazer um programa para ler uma quantia e a duração em meses de um
empréstimo. Informar o valor a ser pago depois de decorrido o prazo do
empréstimo, conforme regras de juros do Brasil. A regra de cálculo de
juros do Brasil é juro composto padrão de 2% ao mês.
* */

package application;

import model.services.BrazilInterestService;
import model.services.InterestService;

import java.util.Scanner;

public class Program {
    static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Amount: ");
        double amount = sc.nextDouble();
        System.out.print("Months: ");
        int months = sc.nextInt();
        InterestService service = new BrazilInterestService(2.0);
        System.out.printf("Payment after %d months: %.2f%n", months, service.payment(amount, months));
        sc.close();
    }
}
