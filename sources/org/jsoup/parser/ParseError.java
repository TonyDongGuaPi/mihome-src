package org.jsoup.parser;

public class ParseError {

    /* renamed from: a  reason: collision with root package name */
    private int f3678a;
    private String b;

    ParseError(int i, String str) {
        this.f3678a = i;
        this.b = str;
    }

    ParseError(int i, String str, Object... objArr) {
        this.b = String.format(str, objArr);
        this.f3678a = i;
    }

    public String a() {
        return this.b;
    }

    public int b() {
        return this.f3678a;
    }

    public String toString() {
        return this.f3678a + ": " + this.b;
    }
}
