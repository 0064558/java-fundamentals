package com.rodrigo.soliddemo;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main() {
        //TIP Press <shortcut actionId="ShowIntentionActions"/> with your caret at the highlighted text
        // to see how IntelliJ IDEA suggests fixing it.
        List<String> strings = List.of("I", "am", "a", "list", "of", "Strings");

        System.out.println("strings = " + strings);

        List<String> result = strings
                .stream()
                .limit(4)
                .toList();

        System.out.println("result = " + result);
    }
}
