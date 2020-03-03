package com.mi.global.bbs.ui.youtube.utils;

import android.support.annotation.NonNull;
import com.mi.global.bbs.ui.youtube.enums.Quality;

public class YouTubeThumbnail {
    public static final String IMG_YOUTUBE_COM_VI = "http://img.youtube.com/vi/";

    private YouTubeThumbnail() {
    }

    public static String getUrlFromVideoId(@NonNull String str, @NonNull Quality quality) {
        switch (quality) {
            case FIRST:
                return IMG_YOUTUBE_COM_VI + str + "/0.jpg";
            case SECOND:
                return IMG_YOUTUBE_COM_VI + str + "/1.jpg";
            case THIRD:
                return IMG_YOUTUBE_COM_VI + str + "/2.jpg";
            case FOURTH:
                return IMG_YOUTUBE_COM_VI + str + "/3.jpg";
            case MAXIMUM:
                return IMG_YOUTUBE_COM_VI + str + "/maxresdefault.jpg";
            case STANDARD_DEFINITION:
                return IMG_YOUTUBE_COM_VI + str + "/sddefault.jpg";
            case MEDIUM:
                return IMG_YOUTUBE_COM_VI + str + "/mqdefault.jpg";
            case HIGH:
                return IMG_YOUTUBE_COM_VI + str + "/hqdefault.jpg";
            default:
                return IMG_YOUTUBE_COM_VI + str + "/default.jpg";
        }
    }
}
