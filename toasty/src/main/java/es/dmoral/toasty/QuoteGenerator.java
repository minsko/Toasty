package es.dmoral.toasty;

import android.content.res.AssetManager;
import android.content.res.Resources;

public interface QuoteGenerator {
    String getRandomQuote(AssetManager mgr);

    String getRandomQuote(AssetManager mgr, String fileName);

    String getRandomQuote(Resources res);

    String getRandomQuote(Resources res, int resID);
}
