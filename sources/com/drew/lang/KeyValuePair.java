package com.drew.lang;

import com.drew.lang.annotations.NotNull;
import com.drew.metadata.StringValue;

public class KeyValuePair {

    /* renamed from: a  reason: collision with root package name */
    private final String f5197a;
    private final StringValue b;

    public KeyValuePair(@NotNull String str, @NotNull StringValue stringValue) {
        this.f5197a = str;
        this.b = stringValue;
    }

    @NotNull
    public String a() {
        return this.f5197a;
    }

    @NotNull
    public StringValue b() {
        return this.b;
    }
}
