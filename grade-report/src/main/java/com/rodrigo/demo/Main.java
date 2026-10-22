package com.rodrigo.demo;

import java.util.List;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        List<Integer> grades = List.of(7, 4, 10, 6, 3);
        GradeService gradeService = new GradeService();

        double media = gradeService.calculateAverage(grades);
        Integer highestGrade = gradeService.findHighestGrade(grades);
        List<Integer> passingGrades = gradeService.filterPassingGrades(grades);

        System.out.println("Notas: " + grades);
        System.out.println("Média: " + media);
        System.out.println("Maior nota: " + highestGrade);
        System.out.println("Notas aprovadas: " + passingGrades);
    }
}
