package org.slf4j.helpers;

import java.util.Map;
import org.slf4j.spi.MDCAdapter;

public class NOPMakerAdapter implements MDCAdapter {
    public String a(String str) {
        return null;
    }

    public void a() {
    }

    public void a(String str, String str2) {
    }

    public void a(Map map) {
    }

    public void b(String str) {
    }

    public Map c() {
        return null;
    }
}
