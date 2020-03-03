package com.horcrux.svg;

import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.ReadableType;
import com.horcrux.svg.TextProperties;

class FontData {

    /* renamed from: a  reason: collision with root package name */
    static final double f5808a = 12.0d;
    static final FontData n = new FontData();
    private static final double o = 0.0d;
    private static final double p = 0.0d;
    private static final double q = 0.0d;
    private static final String r = "kerning";
    private static final String s = "fontData";
    private static final String t = "textAnchor";
    private static final String u = "wordSpacing";
    private static final String v = "letterSpacing";
    private static final String w = "textDecoration";
    private static final String x = "fontFeatureSettings";
    private static final String y = "fontVariantLigatures";
    final double b;
    final String c;
    final TextProperties.FontStyle d;
    final ReadableMap e;
    final TextProperties.FontWeight f;
    final String g;
    final TextProperties.FontVariantLigatures h;
    final TextProperties.TextAnchor i;
    final double j;
    final double k;
    final double l;
    final boolean m;
    private final TextProperties.TextDecoration z;

    private FontData() {
        this.e = null;
        this.c = "";
        this.d = TextProperties.FontStyle.normal;
        this.f = TextProperties.FontWeight.Normal;
        this.g = "";
        this.h = TextProperties.FontVariantLigatures.normal;
        this.i = TextProperties.TextAnchor.start;
        this.z = TextProperties.TextDecoration.None;
        this.m = false;
        this.j = 0.0d;
        this.b = f5808a;
        this.k = 0.0d;
        this.l = 0.0d;
    }

    private double a(String str, double d2, double d3) {
        return PropHelper.a(str, 0.0d, d2, d3);
    }

    FontData(ReadableMap readableMap, FontData fontData, double d2) {
        double d3 = fontData.b;
        if (!readableMap.hasKey("fontSize")) {
            this.b = d3;
        } else if (readableMap.getType("fontSize") == ReadableType.Number) {
            this.b = readableMap.getDouble("fontSize");
        } else {
            this.b = PropHelper.a(readableMap.getString("fontSize"), d3, 1.0d, d3);
        }
        this.e = readableMap.hasKey(s) ? readableMap.getMap(s) : fontData.e;
        this.c = readableMap.hasKey("fontFamily") ? readableMap.getString("fontFamily") : fontData.c;
        this.d = readableMap.hasKey("fontStyle") ? TextProperties.FontStyle.valueOf(readableMap.getString("fontStyle")) : fontData.d;
        this.f = readableMap.hasKey("fontWeight") ? TextProperties.FontWeight.getEnum(readableMap.getString("fontWeight")) : fontData.f;
        this.g = readableMap.hasKey(x) ? readableMap.getString(x) : fontData.g;
        this.h = readableMap.hasKey(y) ? TextProperties.FontVariantLigatures.valueOf(readableMap.getString(y)) : fontData.h;
        this.i = readableMap.hasKey(t) ? TextProperties.TextAnchor.valueOf(readableMap.getString(t)) : fontData.i;
        this.z = readableMap.hasKey("textDecoration") ? TextProperties.TextDecoration.getEnum(readableMap.getString("textDecoration")) : fontData.z;
        boolean hasKey = readableMap.hasKey(r);
        this.m = hasKey || fontData.m;
        this.j = hasKey ? a(readableMap.getString(r), d2, this.b) : fontData.j;
        this.k = readableMap.hasKey(u) ? a(readableMap.getString(u), d2, this.b) : fontData.k;
        this.l = readableMap.hasKey("letterSpacing") ? a(readableMap.getString("letterSpacing"), d2, this.b) : fontData.l;
    }
}
