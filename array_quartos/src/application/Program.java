package application;

import entities.Rent;

import java.util.Scanner;

public class Program {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n;
        Rent[] rooms = new Rent[10];
        System.out.print("How many rooms will be rented? ");
        n = sc.nextInt();
        sc.nextLine();
        for (int i = 0; i < n; i++) {
            System.out.println("Rent #" + (i + 1));
            System.out.println("Name: ");
            String name = sc.nextLine();
            System.out.println("Email: ");
            String email = sc.nextLine();
            System.out.println("Room: ");
            int room = sc.nextInt();
            if (rooms[room] == null) {
                rooms[room] = new Rent(name, email, room);
            } else {
                System.out.println("Quarto já ocupado!");
                i--;
            }
            sc.nextLine();
        }

        System.out.println();

        System.out.println("Busy rooms: ");
        for (int i = 0; i < 10; i++) {
            if (rooms[i] != null) {
                System.out.println(rooms[i].toString());
            }
        }

        sc.close();
    }
}