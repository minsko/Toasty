package es.dmoral.toasty;

import android.content.res.AssetManager;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class QuoteGenerator {
    private static final Random rand = new Random();
    public String getRandomQuote(AssetManager mgr) {
        try {
            List<String> quotes = readQuotes(mgr.open("quotes.txt"));
            return quotes.get(rand.nextInt(quotes.size()));
        } catch (IOException e) {
            return "I got an IOException - Matt Insko";
        }
    }

    private List<String> readQuotes(InputStream in) throws IOException {
        List<String> quotes = new ArrayList<>();
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new InputStreamReader(in));
            String line = reader.readLine();
            while (line != null) {
                if (!line.startsWith("#")) {
                    quotes.add(line);
                }
                line = reader.readLine();
            }
        } finally {
            if (reader != null) {
                reader.close();
            }
        }
        return quotes;
    }
}
