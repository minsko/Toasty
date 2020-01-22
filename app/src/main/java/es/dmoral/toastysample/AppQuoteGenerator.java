package es.dmoral.toastysample;

import android.content.res.AssetManager;
import android.content.res.Resources;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import es.dmoral.toasty.QuoteGenerator;

public class AppQuoteGenerator implements QuoteGenerator {
    private static final Random rand = new Random();
    public String getRandomQuote(AssetManager mgr) {
        return getRandomQuote(mgr, "quotes.txt");
    }

    public String getRandomQuote(AssetManager mgr, String fileName) {
        try {
            List<String> quotes = readQuotes(mgr.open(fileName));
            return quotes.get(rand.nextInt(quotes.size()));
        } catch (IOException e) {
            Log.e("TOASTY", "getRandomQuote: ", e);
            e.printStackTrace();
            return "I got an IOException - Matt Insko";
        }
    }

    public String getRandomQuote(Resources res) {
        return getRandomQuote(res, es.dmoral.toasty.R.raw.quotes);
    }

    public String getRandomQuote(Resources res, int resID) {

        try {
            List<String> quotes = readQuotes(res.openRawResource(resID));
            return quotes.get(rand.nextInt(quotes.size()));
        } catch (IOException e) {
            Log.e("TOASTY", "getRandomQuote: ", e);
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
