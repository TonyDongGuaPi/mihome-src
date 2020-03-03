package com.iheartradio.m3u8;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;

final class Constants {
    public static final String A = "EXT-X-STREAM-INF";
    public static final String B = "EXT-X-I-FRAME-STREAM-INF";
    public static final String C = "BANDWIDTH";
    public static final String D = "AVERAGE-BANDWIDTH";
    public static final String E = "CODECS";
    public static final String F = "RESOLUTION";
    public static final String G = "FRAME-RATE";
    public static final String H = "VIDEO";
    public static final String I = "PROGRAM-ID";
    public static final String J = "AUDIO";
    public static final String K = "SUBTITLES";
    public static final String L = "CLOSED-CAPTIONS";
    public static final String M = "EXT-X-PLAYLIST-TYPE";
    public static final String N = "EXT-X-PROGRAM-DATE-TIME";
    public static final String O = "EXT-X-TARGETDURATION";
    public static final String P = "EXT-X-START";
    public static final String Q = "TIME-OFFSET";
    public static final String R = "PRECISE";
    public static final String S = "EXT-X-MEDIA-SEQUENCE";
    public static final String T = "EXT-X-ALLOW-CACHE";
    public static final String U = "EXT-X-ENDLIST";
    public static final String V = "EXT-X-I-FRAMES-ONLY";
    public static final String W = "EXT-X-DISCONTINUITY";
    public static final String X = "EXTINF";
    public static final String Y = "EXT-X-KEY";
    public static final String Z = "METHOD";

    /* renamed from: a  reason: collision with root package name */
    public static final String f5939a = "application/vnd.apple.mpegurl";
    private static final String aA = "-?\\d*\\.?\\d*";
    private static final String aB = "\\d{4}-\\d{2}-\\d{2}T\\d{2}:\\d{2}:\\d{2}(?:\\.\\d{3})?(?:Z?|\\+\\d{2}:\\d{2})?";
    public static final String aa = "IV";
    public static final String ab = "KEYFORMAT";
    public static final String ac = "KEYFORMATVERSIONS";
    public static final String ad = "YES";
    public static final String ae = "NO";
    public static final Pattern af = Pattern.compile("^0[x|X]([0-9A-F]+)$");
    public static final Pattern ag = Pattern.compile("^(\\d+)x(\\d+)$");
    public static final Pattern ah = Pattern.compile("^#EXT-X-VERSION:(\\d+)$");
    public static final Pattern ai = Pattern.compile("^#EXT-X-TARGETDURATION:(\\d+)$");
    public static final Pattern aj = Pattern.compile("^#EXT-X-MEDIA-SEQUENCE:(\\d+)$");
    public static final Pattern ak = Pattern.compile("^#EXT-X-PLAYLIST-TYPE:(EVENT|VOD)$");
    public static final Pattern al = Pattern.compile("^#EXT-X-PROGRAM-DATE-TIME:(\\d{4}-\\d{2}-\\d{2}T\\d{2}:\\d{2}:\\d{2}(?:\\.\\d{3})?(?:Z?|\\+\\d{2}:\\d{2})?)$");
    public static final Pattern am = Pattern.compile("^CC[1-4]|SERVICE(?:[1-9]|[1-5]\\d|6[0-3])$");
    public static final Pattern an = Pattern.compile("^#EXTINF:(-?\\d*\\.?\\d*)(?:,(.+)?)?$");
    public static final Pattern ao = Pattern.compile("^#EXT-X-ENDLIST$");
    public static final Pattern ap = Pattern.compile("^#EXT-X-I-FRAMES-ONLY");
    public static final Pattern aq = Pattern.compile("^#EXT-X-DISCONTINUITY$");
    public static final int[] ar = {239, 187, 191};
    public static final char as = '﻿';
    public static final int at = 5;
    public static final int au = 16;
    public static final int av = 32;
    public static final String aw = "identity";
    public static final String ax = "NONE";
    public static final List<Integer> ay = Arrays.asList(new Integer[]{1});
    private static final String az = "\\d+";
    public static final String b = "audio/mpegurl";
    public static final String c = "=";
    public static final char d = ',';
    public static final String e = Character.toString(',');
    public static final String f = e;
    public static final String g = "/";
    public static final String h = "#";
    public static final String i = "#EXT";
    public static final String j = ":";
    public static final String k = "\n";
    public static final String l = "\\r?\\n";
    public static final String m = "EXTM3U";
    public static final String n = "EXT-X-VERSION";
    public static final String o = "URI";
    public static final String p = "EXT-X-MEDIA";
    public static final String q = "TYPE";
    public static final String r = "GROUP-ID";
    public static final String s = "LANGUAGE";
    public static final String t = "ASSOC-LANGUAGE";
    public static final String u = "NAME";
    public static final String v = "DEFAULT";
    public static final String w = "AUTOSELECT";
    public static final String x = "FORCED";
    public static final String y = "INSTREAM-ID";
    public static final String z = "CHARACTERISTICS";

    Constants() {
    }
}
