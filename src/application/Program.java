package application;

import entities.ImportedProduct;
import entities.Product;
import entities.usedProduct;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Program {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        List<Product> products = new ArrayList<>();

        System.out.print("Enter the number of products: ");
        int n = sc.nextInt();

        for (int i = 0; i < n; i++) {
            sc.nextLine();
            System.out.println("Product #" + (i + 1) + " data: ");
            System.out.print("Common, used or imported (c/u/i)? ");
            String resp = sc.nextLine();
            if (resp.equals("c")) {
                System.out.print("Name: ");
                String name = sc.nextLine();
                System.out.print("Price: ");
                Double price = sc.nextDouble();
                Product common = new Product(name, price);
                products.add(common);
            } else if (resp.equals("u")) {
                System.out.print("Name: ");
                String name = sc.nextLine();
                System.out.print("Price: ");
                Double price = sc.nextDouble();
                sc.nextLine();
                System.out.print("Manufacture Date (DD/MM/YYYY): ");
                String dateStr = sc.nextLine();
                LocalDate manufactureDate = LocalDate.parse(dateStr, fmt);
                Product used = new usedProduct(name, price, manufactureDate);
                products.add(used);
            } else if (resp.equals("i")) {
                System.out.print("Name: ");
                String name = sc.nextLine();
                System.out.print("Price: ");
                Double price = sc.nextDouble();
                System.out.print("Customs fee: ");
                Double customFee = sc.nextDouble();
                Product imported = new ImportedProduct(name, price, customFee);
                products.add(imported);
            }
        }

        System.out.println("\n----------------------------------------------------");
        System.out.println("\nPRICE TAGS: ");

        for (Product p : products) {
            System.out.println(p.priceTag());
        }

        sc.close();
    }
}
