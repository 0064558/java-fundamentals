package application;

import entities.Product;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Program {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        List<Product> list = new ArrayList<>();

        System.out.println("Enter file full path: ");
        String path = sc.nextLine();

        File sourceFile = new File(path);
        String sourceFolder = sourceFile.getParent();

        boolean success = new File(sourceFolder + "/out").mkdir();

        String targetFileStr = sourceFolder + "/out/summary.csv";

        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            String line = br.readLine();

            while (line != null) {
                String[] fields = line.split(",");
                String name = fields[0].trim();
                Double price = Double.parseDouble(fields[1].trim());
                Integer quantity = Integer.parseInt(fields[2].trim());
                Product product = new Product(name, price, quantity);
                list.add(product);
                line = br.readLine();
            }

            try (BufferedWriter bw = new BufferedWriter(new FileWriter(targetFileStr))) {
                for (Product p : list) {
                    bw.write(p.getName() + ", " + String.format("%.2f", p.total()));
                    bw.newLine();
                }

                System.out.println(targetFileStr + " generated!");

            } catch (IOException e) {
                System.out.println("Error: " + e.getMessage());
            }

        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }

        sc.close();
    }
}
