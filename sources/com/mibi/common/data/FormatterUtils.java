package com.mibi.common.data;

import android.widget.EditText;

public class FormatterUtils {

    public enum FormatterType {
        TYPE_NORMAL(new CommonNumberFormatter()),
        TYPE_PHONE(new PhoneFormatter()),
        TYPE_ID_CARD(new IdCardFormatter()),
        TYPE_EMAIL(new EmailFormatter());
        
        private Formatter mFormatter;

        private FormatterType(Formatter formatter) {
            this.mFormatter = formatter;
        }

        public Formatter getFormatter() {
            return this.mFormatter;
        }
    }

    public static void a(EditText editText, FormatterType formatterType) {
        formatterType.getFormatter().a(editText);
    }

    public static String a(String str, FormatterType formatterType) {
        return formatterType.getFormatter().b(str);
    }

    public static String b(String str, FormatterType formatterType) {
        return formatterType.getFormatter().d(str);
    }

    public static String c(String str, FormatterType formatterType) {
        return formatterType.getFormatter().e(str);
    }

    public static boolean d(String str, FormatterType formatterType) {
        return formatterType.getFormatter().a(str);
    }
}
