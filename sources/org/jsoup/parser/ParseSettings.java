package org.jsoup.parser;

import org.jsoup.internal.Normalizer;
import org.jsoup.nodes.Attributes;

public class ParseSettings {

    /* renamed from: a  reason: collision with root package name */
    public static final ParseSettings f3680a = new ParseSettings(false, false);
    public static final ParseSettings b = new ParseSettings(true, true);
    private final boolean c;
    private final boolean d;

    public ParseSettings(boolean z, boolean z2) {
        this.c = z;
        this.d = z2;
    }

    /* access modifiers changed from: package-private */
    public String a(String str) {
        String trim = str.trim();
        return !this.c ? Normalizer.a(trim) : trim;
    }

    /* access modifiers changed from: package-private */
    public String b(String str) {
        String trim = str.trim();
        return !this.d ? Normalizer.a(trim) : trim;
    }

    /* access modifiers changed from: package-private */
    public Attributes a(Attributes attributes) {
        if (!this.d) {
            attributes.f();
        }
        return attributes;
    }
}
