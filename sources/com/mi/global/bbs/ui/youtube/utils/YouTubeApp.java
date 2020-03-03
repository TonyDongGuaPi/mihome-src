package com.mi.global.bbs.ui.youtube.utils;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;

public class YouTubeApp {
    private YouTubeApp() {
    }

    public static void startVideo(@NonNull Context context, @NonNull String str) {
        Uri parse = Uri.parse(YouTubeUrlParser.getVideoUrl(str));
        Intent intent = new Intent("android.intent.action.VIEW", Uri.parse("vnd.youtube:" + str));
        if (context.getPackageManager().queryIntentActivities(intent, 65536).isEmpty()) {
            intent = new Intent("android.intent.action.VIEW", parse);
        }
        context.startActivity(intent);
    }
}
