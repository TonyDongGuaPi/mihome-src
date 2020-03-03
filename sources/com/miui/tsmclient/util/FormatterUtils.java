package com.miui.tsmclient.util;

import android.widget.EditText;

public class FormatterUtils {

    public enum FormatterType {
        TYPE_NORMAL(new CommonNumberFormatter()),
        TYPE_VALID_DATE(new ValidDateFormatter()),
        TYPE_PHONE(new PhoneFormatter()),
        TYPE_ID_CARD(new IdCardFormatter());
        
        private Formatter mFormatter;

        private FormatterType(Formatter formatter) {
            this.mFormatter = formatter;
        }

        public Formatter getFormatter() {
            return this.mFormatter;
        }
    }

    public static void setFormatter(EditText editText, FormatterType formatterType) {
        formatterType.getFormatter().bindFormattingTextWatcher(editText);
    }

    public static String clean(String str) {
        return clean(str, FormatterType.TYPE_NORMAL);
    }

    public static String clean(String str, FormatterType formatterType) {
        return formatterType.getFormatter().clean(str);
    }

    public static String format(String str, FormatterType formatterType) {
        return formatterType.getFormatter().format(str);
    }
}
