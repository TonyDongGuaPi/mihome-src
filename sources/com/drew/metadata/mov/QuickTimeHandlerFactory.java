package com.drew.metadata.mov;

import com.drew.imaging.quicktime.QuickTimeHandler;
import com.drew.metadata.Metadata;
import com.drew.metadata.mov.media.QuickTimeMusicHandler;
import com.drew.metadata.mov.media.QuickTimeSoundHandler;
import com.drew.metadata.mov.media.QuickTimeSubtitleHandler;
import com.drew.metadata.mov.media.QuickTimeTextHandler;
import com.drew.metadata.mov.media.QuickTimeTimecodeHandler;
import com.drew.metadata.mov.media.QuickTimeVideoHandler;
import com.drew.metadata.mov.metadata.QuickTimeDataHandler;
import com.drew.metadata.mov.metadata.QuickTimeDirectoryHandler;

public class QuickTimeHandlerFactory {

    /* renamed from: a  reason: collision with root package name */
    public static Long f5237a = null;
    public static Long b = null;
    public static Long c = null;
    public static Long d = null;
    private static final String e = "mdir";
    private static final String f = "mdta";
    private static final String g = "soun";
    private static final String h = "vide";
    private static final String i = "tmcd";
    private static final String j = "text";
    private static final String k = "sbtl";
    private static final String l = "musi";
    private QuickTimeHandler m;

    public QuickTimeHandlerFactory(QuickTimeHandler quickTimeHandler) {
        this.m = quickTimeHandler;
    }

    public QuickTimeHandler a(String str, Metadata metadata) {
        if (str.equals(e)) {
            return new QuickTimeDirectoryHandler(metadata);
        }
        if (str.equals(f)) {
            return new QuickTimeDataHandler(metadata);
        }
        if (str.equals(g)) {
            return new QuickTimeSoundHandler(metadata);
        }
        if (str.equals(h)) {
            return new QuickTimeVideoHandler(metadata);
        }
        if (str.equals("tmcd")) {
            return new QuickTimeTimecodeHandler(metadata);
        }
        if (str.equals("text")) {
            return new QuickTimeTextHandler(metadata);
        }
        if (str.equals("sbtl")) {
            return new QuickTimeSubtitleHandler(metadata);
        }
        if (str.equals(l)) {
            return new QuickTimeMusicHandler(metadata);
        }
        return this.m;
    }
}
