package br.com.rmso.jokes;

import java.util.Random;

public class Jokes {

    private Random random = new Random();

    private final String jokesList[] = {
            "Q: What did one wall say to the other wall?\n" +
                    "A: I’ll meet you at the corner.",
            "Q: What gets wetter the more it dries?\n" +
                    "A: A towel",
            "Q: Which hand is better to write with?\n" +
                    "A: Neither, it’s best to write with a pen.",
            "Q: Why do bicycles fall over?\n" +
                    "A: Because they are too tired!"
    };

    public String getJoke(){
        int position = random.nextInt(jokesList.length);
        return jokesList[position];
    }
}
