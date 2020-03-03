package com.mi.global.bbs.ui.youtube.utils;

import android.support.annotation.NonNull;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class YouTubeUrlParser {
    static final String reg = "(?:youtube(?:-nocookie)?\\.com\\/(?:[^\\/\\n\\s]+\\/\\S+\\/|(?:v|e(?:mbed)?)\\/|\\S*?[?&]v=)|youtu\\.be\\/)([a-zA-Z0-9_-]{11})";

    private YouTubeUrlParser() {
    }

    public static String getVideoId(@NonNull String str) {
        Matcher matcher = Pattern.compile(reg, 2).matcher(str);
        if (matcher.find()) {
            return matcher.group(1);
        }
        return null;
    }

    public static String getVideoUrl(@NonNull String str) {
        return "http://youtu.be/" + str;
    }
}
