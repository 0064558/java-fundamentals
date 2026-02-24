package application;

import entities.Comment;
import entities.Post;

import java.text.ParseException;
import java.text.SimpleDateFormat;

public class Program {
    static void main() throws ParseException {

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

        Comment c1 = new Comment("Have a nice trip!");
        Comment c2 = new Comment("Wow that's awesome!");

        Post p1 = new Post (sdf.parse(
                "21/06/2018 13:05:44"),
                "Traveling to New Zealand",
                "I'm going to visit this wonderful country!",
                12);
        p1.addComent(c1);
        p1.addComent(c2);


        System.out.println(p1.toString());

        Comment c3 = new Comment("Nice trip!");
        Comment c4 = new Comment("Wow!");

        Post p2 = new Post(sdf.parse("23/06/2018 15:42:26"),
                "Good night guys",
                "See you tomorrow",
                5);
        p2.addComent(c3);
        p2.addComent(c4);

        System.out.println(p2.toString());

    }
}
