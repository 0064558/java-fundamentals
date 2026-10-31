package com.rodrigo.demo;

import java.util.List;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    static void main() {
        //TIP Press <shortcut actionId="ShowIntentionActions"/> with your caret at the highlighted text
        // to see how IntelliJ IDEA suggests fixing it.
        IO.println(String.format("Hello and welcome!"));

        // Create a list of training days with the following values: 10, 15, 20, 25, 30, 5, 10, 20, 25, 30, 35, 40, 45, 50, 55, 60
        List<Integer> trainingDays = List.of(25, 30, 0, 20, 40);

        trainingSequence(trainingDays);

        System.out.println("----------------- Http Status Code -------------------");

        List<Integer> httpStatusCode = List.of(200, 500, 502, 200, 404, 503, 504, 201);
        int sequence = findSequence(httpStatusCode);
        System.out.println(sequence);

    }

    public static int findSequence(List<Integer> httpStatusCode) {
        if (httpStatusCode.isEmpty()) {
            return 0;
        }

        int errorCount = 0;
        int bestStreak = 0;

        for (Integer statusCode : httpStatusCode) {
            if (statusCode >=400 && statusCode <= 599) {
                errorCount++;

                if (errorCount > bestStreak) {
                    bestStreak = errorCount;
                }
            } else {
                errorCount = 0;
            }
        }
        return bestStreak;
    }

    public static void trainingSequence(List<Integer> days) {
        if (days == null || days.isEmpty()) {
            IO.println("No training days provided.");
            return;
        }

        // Current streak é o número de dias consecutivos em que o usuário treinou por pelo menos 20 minutos.
        int currentStreak = 0;
        // Best streak é o maior número de dias consecutivos em que o usuário treinou por pelo menos 20 minutos.
        int bestStreak = 0;
        // Day number é o número do dia atual no loop.
        int dayNumber = 0;
        // Best start é o número do dia da melhor sequencia.
        int bestStart = 0;


        for (Integer minutes : days) {
            // Incrementa o número do dia atual.
            dayNumber++;
            if (minutes >=20) {
                // Incrementa o currentStreak se o usuário treinou por pelo menos 20 minutos.
                currentStreak++;

                // Atualiza o bestStreak se o currentStreak for maior que o bestStreak.
                if (currentStreak > bestStreak) {
                    bestStreak = currentStreak;
                    bestStart = dayNumber - currentStreak + 1; // Atualiza o bestStart para o dia em que o usuário começou a treinar por pelo menos 20 minutos.
                }
            } else {
                currentStreak = 0;
            }
        }

        // Imprime os resultados.
        System.out.println("Current streak: " + currentStreak);
        System.out.println("Best streak: " + bestStreak);
        System.out.println("Best start: " + bestStart);
    }
}

