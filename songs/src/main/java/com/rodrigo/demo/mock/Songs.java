package com.rodrigo.demo.mock;

import com.rodrigo.demo.Song;

import java.util.List;

public class Songs {
    public List<Song> getSongs() {
        return List.of(
                new Song("$10", "Hitchhiker", "Electronic", 2016, 183),
                new Song("Havana", "Camila Cabello", "R&B", 2017, 324),
                new Song("Cassidy", "Grateful Dead", "Rock", 1972, 123),
                new Song("The Less I Know The Better", "Tame Impala", "Psychedelic Rock", 2015, 456),
                new Song("Bohemian Rhapsody", "Queen", "Rock", 1975, 789),
                new Song("Shape of You", "Ed Sheeran", "Pop", 2017, 567),
                new Song("With a Little Help from My Friends", "The Beatles", "Rock", 1967, 168),
                new Song("Come Together", "The Beatles", " Blues Rock", 1968, 173),
                new Song("Blinding Lights", "The Weeknd", "Synthwave", 2019, 890),
                new Song("Smells Like Teen Spirit", "Nirvana", "Grunge", 1991, 345),
                new Song("Billie Jean", "Michael Jackson", "Pop", 1982, 678),
                new Song("Rolling in the Deep", "Adele", "Soul", 2010, 456),
                new Song("Lose Yourself", "Eminem", "Hip Hop", 2002, 234),
                new Song("Stairway to Heaven", "Led Zeppelin", "Rock", 1971, 567),
                new Song("Hey Jude", "The Beatles", "Rock", 1968, 890),
                new Song("Hotel California", "Eagles", "Rock", 1976, 345),
                new Song("Sweet Child o' Mine", "Guns N' Roses", "Rock", 1987, 678),
                new Song("Uptown Funk", "Mark Ronson ft. Bruno Mars", " Funk", 2014, 456),
                new Song("Rolling Stones", "Paint It Black", "Rock", 1966, 234),
                new Song("Imagine", "John Lennon", "Rock", 1971, 567),
                new Song("Thriller", "Michael Jackson", "Pop", 1982, 890),
                new Song("Wonderwall", "Oasis", "Britpop", 1995, 345),
                new Song("Hey Ya!", "OutKast", "Hip Hop", 2003, 678),
                new Song("All of Me", "John Legend", "R&B", 2013, 456)
        );
    }
}
