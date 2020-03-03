package com.drew.metadata.mp4;

import com.drew.imaging.mp4.Mp4Handler;
import com.drew.metadata.Metadata;
import com.drew.metadata.mp4.boxes.HandlerBox;
import com.drew.metadata.mp4.media.Mp4HintHandler;
import com.drew.metadata.mp4.media.Mp4MetaHandler;
import com.drew.metadata.mp4.media.Mp4SoundHandler;
import com.drew.metadata.mp4.media.Mp4TextHandler;
import com.drew.metadata.mp4.media.Mp4VideoHandler;

public class Mp4HandlerFactory {

    /* renamed from: a  reason: collision with root package name */
    public static Long f5249a = null;
    public static Long b = null;
    public static Long c = null;
    public static Long d = null;
    public static String e = null;
    private static final String f = "soun";
    private static final String g = "vide";
    private static final String h = "hint";
    private static final String i = "text";
    private static final String j = "meta";
    private Mp4Handler k;

    public Mp4HandlerFactory(Mp4Handler mp4Handler) {
        this.k = mp4Handler;
    }

    public Mp4Handler a(HandlerBox handlerBox, Metadata metadata) {
        String a2 = handlerBox.a();
        if (a2.equals(f)) {
            return new Mp4SoundHandler(metadata);
        }
        if (a2.equals(g)) {
            return new Mp4VideoHandler(metadata);
        }
        if (a2.equals("hint")) {
            return new Mp4HintHandler(metadata);
        }
        if (a2.equals("text")) {
            return new Mp4TextHandler(metadata);
        }
        if (a2.equals("meta")) {
            return new Mp4MetaHandler(metadata);
        }
        return this.k;
    }
}
