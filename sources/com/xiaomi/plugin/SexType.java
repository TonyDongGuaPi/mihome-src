package com.xiaomi.plugin;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

public class SexType {
    public static final String FEMALE = "female";
    public static final String MALE = "male";

    @Retention(RetentionPolicy.SOURCE)
    public @interface Type {
    }
}
