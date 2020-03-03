package com.tiqiaa.icontrol.util;

public class UserVerifier {
    static final String emailRegex = "^([a-zA-Z0-9_\\-\\.]+)@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.)|(([a-zA-Z0-9\\-]+\\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\\]?)$";
    static final String nameRegex = "^[a-zA-Z0-9_一-龥]{5,20}+$";
    static final String pwRegex = "^[a-z,A-Z0-9_]{6,16}$";
    static final String telRegex = "[1][3578]\\d{9}";

    public static boolean isValidPhone(String str) {
        if (str == null || str.equals("")) {
            return false;
        }
        return str.matches(telRegex);
    }

    public static boolean isValidEmail(String str) {
        if (str == null || str.equals("")) {
            return false;
        }
        return str.matches(emailRegex);
    }

    public static boolean isValidName(String str) {
        if (str == null || str.equals("")) {
            return false;
        }
        return str.matches(nameRegex);
    }

    public static boolean isValidPassword(String str) {
        if (str == null || str.equals("")) {
            return false;
        }
        return str.matches(pwRegex);
    }
}
