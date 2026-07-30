package com.rodrigo.soliddemo;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    static void main() {
        int bottlesNum = 10;
        String word = "bottles";

        while (bottlesNum > 0) {

            if (bottlesNum == 1) {
                word = "bottle";
            }

            System.out.println(bottlesNum + " green " + word + " hanging on the wall");
            System.out.println("And if one green bottle should accidentally fall");
            bottlesNum -= 1;

            if (bottlesNum > 0) {
                System.out.println("There'll be " + bottlesNum + " green " + word + " hanging on the wall");
            } else {
                System.out.println("There'll be no green bottles hanging on the wall");
            }
        }
    }
}
