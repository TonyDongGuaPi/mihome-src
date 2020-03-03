package com.drew.metadata;

import com.drew.lang.annotations.NotNull;
import java.util.HashMap;

public final class ErrorDirectory extends Directory {
    @NotNull
    public String a() {
        return "Error";
    }

    @NotNull
    public String v(int i) {
        return "";
    }

    public boolean w(int i) {
        return false;
    }

    public ErrorDirectory() {
    }

    public ErrorDirectory(String str) {
        super.a(str);
    }

    /* access modifiers changed from: protected */
    @NotNull
    public HashMap<Integer, String> b() {
        return new HashMap<>();
    }

    public void a(int i, @NotNull Object obj) {
        throw new UnsupportedOperationException(String.format("Cannot add value to %s.", new Object[]{ErrorDirectory.class.getName()}));
    }
}
