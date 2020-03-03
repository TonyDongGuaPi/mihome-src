package com.squareup.picasso.mishop;

public enum MemoryPolicy {
    NO_CACHE(1),
    NO_STORE(2);
    
    final int index;

    static boolean shouldReadFromMemoryCache(int i) {
        return (i & NO_CACHE.index) == 0;
    }

    static boolean shouldWriteToMemoryCache(int i) {
        return (i & NO_STORE.index) == 0;
    }

    public static boolean shouldUseMemoryCache(int i) {
        return (NO_CACHE.index & i) == 0 && (i & NO_STORE.index) == 0;
    }

    private MemoryPolicy(int i) {
        this.index = i;
    }
}
