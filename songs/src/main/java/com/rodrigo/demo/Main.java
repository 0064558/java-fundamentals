package com.rodrigo.demo;

import com.rodrigo.demo.mock.Songs;

import java.util.List;
import java.util.stream.Collectors;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    static void main(String[] args) {
        // exibir todas as músicas
        List<Song> songs = new Songs().getSongs();
        songs.forEach(s -> System.out.println(s.toString()));

        System.out.println("----------------------------------------------------------------------------------");

        System.out.println("Rock songs: ");

        List<Song> rockSongs = songs.stream()
                .filter(s -> s.getGenre().contains("Rock"))
                .toList();

        rockSongs.forEach(System.out::println);

        System.out.println("----------------------------------------------------------------------------------");

        System.out.println("Musicas: dos Beatles, Que começam com H, Lançadas depois de 1995");

        List<Song> beatlesStartWithHafter1995 = songs.stream()
                .filter(s -> s.getArtist().equals("The Beatles") || s.getTitle().startsWith("H") || s.getYear() > 1995)
                .toList();

        beatlesStartWithHafter1995.forEach(System.out::println);

    }
}
