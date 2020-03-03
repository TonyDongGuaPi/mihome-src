package com.xiaomi.smarthome.library.apache.http.protocol;

public final class HTTP {
    public static final String A = "application/octet-stream";
    public static final String B = "text/plain";
    public static final String C = "; charset=";
    public static final String D = "application/octet-stream";

    /* renamed from: a  reason: collision with root package name */
    public static final int f18448a = 13;
    public static final int b = 10;
    public static final int c = 32;
    public static final int d = 9;
    public static final String e = "Transfer-Encoding";
    public static final String f = "Content-Length";
    public static final String g = "Content-Type";
    public static final String h = "Content-Encoding";
    public static final String i = "Expect";
    public static final String j = "Connection";
    public static final String k = "Host";
    public static final String l = "User-Agent";
    public static final String m = "Date";
    public static final String n = "Server";
    public static final String o = "100-continue";
    public static final String p = "Close";
    public static final String q = "Keep-Alive";
    public static final String r = "chunked";
    public static final String s = "identity";
    public static final String t = "UTF-8";
    public static final String u = "UTF-16";
    public static final String v = "US-ASCII";
    public static final String w = "ASCII";
    public static final String x = "ISO-8859-1";
    public static final String y = "ISO-8859-1";
    public static final String z = "US-ASCII";

    public static boolean a(char c2) {
        return c2 == ' ' || c2 == 9 || c2 == 13 || c2 == 10;
    }

    private HTTP() {
    }
}
