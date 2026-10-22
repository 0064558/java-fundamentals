package com.rodrigo.demo;

import java.util.ArrayList;
import java.util.List;

public class GradeService {

    public double calculateAverage(List<Integer> grades) {
        double soma = 0.0;

        if (grades.isEmpty()) {
            return 0.0;
        }

        for (Integer grade : grades) {
            soma += grade;
        }

        return soma / grades.size();
    }

    public Integer findHighestGrade(List<Integer> grades) {

        if (grades.isEmpty()) {
            return null;
        }

        Integer highestGrade = grades.getFirst();

        for (Integer grade : grades) {
            if (grade > highestGrade) {
                highestGrade = grade;
            }
        }

        return highestGrade;
    }

    public List<Integer> filterPassingGrades(List<Integer> grades) {

        List<Integer> passingGrades = new ArrayList<>();

        for (Integer grade : grades) {
            if (grade >= 6) {
                passingGrades.add(grade);
            }
        }

        return passingGrades;
    }
}
