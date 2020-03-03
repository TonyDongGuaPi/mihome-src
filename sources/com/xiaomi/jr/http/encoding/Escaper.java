package com.xiaomi.jr.http.encoding;

public interface Escaper {
    Appendable a(Appendable appendable);

    String a(String str);
}
