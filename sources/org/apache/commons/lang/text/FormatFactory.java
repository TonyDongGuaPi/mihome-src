package org.apache.commons.lang.text;

import java.text.Format;
import java.util.Locale;

public interface FormatFactory {
    Format a(String str, String str2, Locale locale);
}
