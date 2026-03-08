/*
* Fazer um programa para ler os dados (nome, email e salário)
de funcionários a partir de um arquivo em formato .csv.
*
Em seguida mostrar, em ordem alfabética, o email dos
funcionários cujo salário seja superior a um dado valor
fornecido pelo usuário.
*
Mostrar também a soma dos salários dos funcionários cujo
nome começa com a letra 'M'.
* */

package application;

import entities.Employee;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Program {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter file full path: ");
        String path = sc.nextLine();
        System.out.print("Enter salary: ");
        double salary = sc.nextDouble();

        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            List<Employee> list = new ArrayList<>();
            String line = br.readLine();
            while (line != null) {
                String[] fields = line.split(",");
                String name = fields[0];
                String email = fields[1];
                double salaryCsv = Double.parseDouble(fields[2]);
                list.add(new Employee(name, email, salaryCsv));
                line = br.readLine();
            }

            double finalSalary = salary;
            List<String> emails = list.stream()
                    .filter(e -> e.getSalary() > finalSalary)
                    .map(e -> e.getEmail())
                    .sorted(Comparator.naturalOrder())
                    .toList();
            System.out.println("Email of people whose salary is more than " + salary + ": ");
            emails.forEach(System.out::println);

            char firstLetter = 'M';
            double sum = list.stream()
                    .filter(e -> e.getName().charAt(0) == firstLetter)
                    .mapToDouble(e -> e.getSalary())
                    .sum();
            System.out.println("Sum of salary of people whose name starts with " + firstLetter + ": " + String.format("%.2f", sum));

        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
        sc.close();
    }
}
