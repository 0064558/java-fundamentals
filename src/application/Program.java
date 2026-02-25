package application;

import entities.Circle;
import entities.Rectangle;
import entities.Shape;
import entities.enums.Color;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Program {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        List<Shape> shapes = new ArrayList<>();

        System.out.print("Enter the number of shapes: ");
        int n = sc.nextInt();

        for (int i = 0; i < n; i++) {
            sc.nextLine();
            System.out.println("Shape #" + (i + 1) + " data: ");
            System.out.print("Rectangle or Circle (r/c)? ");
            String resp = sc.nextLine();
            if (resp.equals("r")) {
                System.out.print("Color (BLACK/BLUE/RED): ");
                String color = sc.nextLine();
                System.out.print("Width: ");
                Double width = sc.nextDouble();
                System.out.print("Height: ");
                Double height = sc.nextDouble();
                Shape rectangle = new Rectangle(Color.valueOf(color), width, height);
                shapes.add(rectangle);
            } else if (resp.equals("c")) {
                System.out.print("Color (BLACK/BLUE/RED): ");
                String color = sc.nextLine();
                System.out.print("Radius: ");
                Double radius = sc.nextDouble();
                Shape circle = new Circle(Color.valueOf(color), radius);
                shapes.add(circle);
            }
        }

        System.out.println("------------------------------------------------");
        System.out.println("\nSHAPE AREAS: ");
        for (Shape s : shapes) {
            System.out.println(String.format("%.2f", s.area()));
        }
    }
}
