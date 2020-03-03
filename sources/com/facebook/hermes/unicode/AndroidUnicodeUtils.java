package com.facebook.hermes.unicode;

import com.facebook.proguard.annotations.DoNotStrip;
import java.text.Collator;
import java.text.DateFormat;
import java.text.Normalizer;
import java.util.Locale;

@DoNotStrip
public class AndroidUnicodeUtils {
    @DoNotStrip
    public static int localeCompare(String str, String str2) {
        return Collator.getInstance().compare(str, str2);
    }

    @DoNotStrip
    public static String dateFormat(double d, boolean z, boolean z2) {
        DateFormat dateFormat;
        if (z && z2) {
            dateFormat = DateFormat.getDateTimeInstance(2, 2);
        } else if (z) {
            dateFormat = DateFormat.getDateInstance(2);
        } else if (z2) {
            dateFormat = DateFormat.getTimeInstance(2);
        } else {
            throw new RuntimeException("Bad dateFormat configuration");
        }
        return dateFormat.format(Long.valueOf((long) d)).toString();
    }

    @DoNotStrip
    public static String convertToCase(String str, int i, boolean z) {
        Locale locale = z ? Locale.getDefault() : Locale.ENGLISH;
        switch (i) {
            case 0:
                return str.toUpperCase(locale);
            case 1:
                return str.toLowerCase(locale);
            default:
                throw new RuntimeException("Invalid target case");
        }
    }

    @DoNotStrip
    public static String normalize(String str, int i) {
        switch (i) {
            case 0:
                return Normalizer.normalize(str, Normalizer.Form.NFC);
            case 1:
                return Normalizer.normalize(str, Normalizer.Form.NFD);
            case 2:
                return Normalizer.normalize(str, Normalizer.Form.NFKC);
            case 3:
                return Normalizer.normalize(str, Normalizer.Form.NFKD);
            default:
                throw new RuntimeException("Invalid form");
        }
    }
}
