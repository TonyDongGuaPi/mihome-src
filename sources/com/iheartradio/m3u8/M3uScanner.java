package com.iheartradio.m3u8;

import java.io.InputStream;
import java.util.Locale;
import java.util.Scanner;

class M3uScanner {

    /* renamed from: a  reason: collision with root package name */
    private final Scanner f5943a;
    private final boolean b;
    private final StringBuilder c = new StringBuilder();
    private boolean d;

    M3uScanner(InputStream inputStream, Encoding encoding) {
        this.f5943a = new Scanner(inputStream, encoding.value).useLocale(Locale.US).useDelimiter(Constants.l);
        this.b = encoding.supportsByteOrderMark;
    }

    /* access modifiers changed from: package-private */
    public String a() {
        return this.c.toString();
    }

    /* access modifiers changed from: package-private */
    public boolean b() {
        return this.f5943a.hasNext();
    }

    /* access modifiers changed from: package-private */
    public String c() throws ParseException {
        String next = this.f5943a.next();
        if (this.b && !this.d) {
            if (!next.isEmpty() && next.charAt(0) == 65279) {
                next = next.substring(1);
            }
            this.d = true;
        }
        StringBuilder sb = this.c;
        sb.append(next);
        sb.append("\n");
        return next;
    }
}
