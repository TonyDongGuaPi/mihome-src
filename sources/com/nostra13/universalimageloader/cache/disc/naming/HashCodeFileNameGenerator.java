package com.nostra13.universalimageloader.cache.disc.naming;

public class HashCodeFileNameGenerator implements FileNameGenerator {
    public String a(String str) {
        return String.valueOf(str.hashCode());
    }
}
