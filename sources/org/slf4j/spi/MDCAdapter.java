package org.slf4j.spi;

import java.util.Map;

public interface MDCAdapter {
    String a(String str);

    void a();

    void a(String str, String str2);

    void a(Map map);

    void b(String str);

    Map c();
}
