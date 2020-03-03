package com.xiaomi.youpin.network.bean;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.SOURCE)
public @interface FileType {
    public static final String DATA = "data";
    public static final String FILE = "file";
    public static final String PIC = "pic";
    public static final String USER_FILE = "userfile";
}
