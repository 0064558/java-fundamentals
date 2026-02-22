package application;

import entities.Employee;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Program {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        List<Employee> list = new ArrayList<>();
        System.out.printf("How many employees will be registered? ");
        int n = sc.nextInt();
        sc.nextLine();
        boolean idExists;
        Integer id;

        for (int i = 0; i < n; i++) {
            System.out.println("Employee #" + (i + 1) + ": ");

            do {
                System.out.printf("ID: ");
                id = sc.nextInt();
                idExists = false;
                for (Employee e : list) {
                    if (e.getId().equals(id)) {
                        idExists = true;
                        System.out.println("ID already taken!");
                        break;
                    }
                }
            } while (idExists);

            sc.nextLine();
            System.out.printf("Name: ");
            String name = sc.nextLine();
            System.out.printf("Salary: ");
            Double salary = sc.nextDouble();
            sc.nextLine();
            list.add(new Employee(id, name, salary));
        }

        System.out.printf("Enter the employee id that will have a salary increase: ");
        id = sc.nextInt();
        Integer finalId = id;
        double percentage;
        Employee emp = list.stream().filter(e -> e.getId().equals(finalId)).findFirst().orElse(null);
        if (emp == null) {
            System.out.println("ID NOT EXISTS!");
        } else {
            System.out.printf("Enter the percentage: ");
            percentage = sc.nextDouble();
            emp.increaseSalary(percentage);
        }

        System.out.println("\nList of employees: ");
        for (Employee e : list) {
            System.out.println(e.toString());
        }

        sc.close();
    }
}
