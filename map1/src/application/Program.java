/*
* Na contagem de votos de uma eleição, são gerados vários registros
de votação contendo o nome do candidato e a quantidade de votos
(formato .csv) que ele obteve em uma urna de votação. Você deve
fazer um programa para ler os registros de votação a partir de um
arquivo, e daí gerar um relatório consolidado com os totais de cada
candidato.
* */

package application;

import entities.Candidate;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;


public class Program {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("Enter file full path: ");
        String path = sc.nextLine();

        try (BufferedReader br = new BufferedReader(new FileReader(path))) {

            Map<Candidate, Integer> candidates = new HashMap<>();

            String line = br.readLine();
            while (line != null) {
                String[] fields = line.split(",");
                String name = fields[0];
                int qtdVotes = Integer.parseInt(fields[1]);
                Candidate key = new Candidate(name, 0);
                if (candidates.containsKey(key) == true) {
                    int current = candidates.get(key);
                    candidates.put(key, current + qtdVotes);
                } else {
                    candidates.put(key, qtdVotes);
                }
                line = br.readLine();
            }

            System.out.println("-----------------------------------");
            for (Map.Entry<Candidate, Integer> entry : candidates.entrySet()) {
                String candidateName = entry.getKey().getName();
                Integer totalVotes = entry.getValue();
                System.out.println(candidateName + ": " + totalVotes);
            }
            
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
        sc.close();
    }
}
