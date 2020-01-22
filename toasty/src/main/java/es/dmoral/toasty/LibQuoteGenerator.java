package es.dmoral.toasty;

import android.content.res.AssetManager;
import android.content.res.Resources;
import android.util.TypedValue;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class LibQuoteGenerator implements QuoteGenerator {
    private static final Random rand = new Random();
    @Override
    public String getRandomQuote(AssetManager mgr) {
        return getRandomQuote(mgr, "quotes.txt");
    }

    @Override
    public String getRandomQuote(AssetManager mgr, String fileName) {
//        if (true) {
//            return "My Asset Quote";
//        }
        try {
            List<String> quotes = readQuotes(mgr.open(fileName, AssetManager.ACCESS_STREAMING));
            return quotes.get(rand.nextInt(quotes.size()));
        } catch (IOException e) {
            return "I got an IOException - Matt Insko";
        }
    }

    @Override
    public String getRandomQuote(Resources res) {
        return getRandomQuote(res, R.raw.quotes);
    }

    @Override
    public String getRandomQuote(Resources res, int resID) {

        try {
            TypedValue value = new TypedValue();
            List<String> quotes = readQuotes(res.openRawResource(resID, value));
            return quotes.get(rand.nextInt(quotes.size()));
        } catch (IOException e) {
            e.printStackTrace();
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
