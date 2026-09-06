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
        System.out.println("Todas as músicas: ");
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


        System.out.println("----------------------------------------------------------------------------------");

        System.out.println("Todos os gêneros: ");
        List<String> allGenres = songs.stream()
                .map(s -> s.getGenre().trim()) // Map indica como mapear de um tipo para outro, Trim serve para remover espaços em branco do inicio e fim de uma string.
                .distinct() // Distinct serve para eliminar elementos repetidos.
                .toList();
        System.out.println(allGenres);

        System.out.println("----------------------------------------------------------------------------------");

        String songTitle = "With a Little Help from My Friends";
        List<String> result = songs.stream()
                .filter(song -> song.getTitle().equals(songTitle)) // Filtra a música que tenha o título igual a songTitle
                .map(song -> song.getArtist()) // Mapeia a lista de músicas para uma lista dos artistas
                .filter(artist -> !artist.equals("The Beatles")) // Filtra os artistas que não sejam igual a The Beatles.
                .toList(); // Converte para lista
        System.out.println(result); // Resultado vazio, pois não tem nenhum artista diferente dos Beatles que tenham um música igual a songTitle.

    }
}
