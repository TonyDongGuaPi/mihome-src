package com.xiaomi.miio;

import java.util.HashMap;

public class MiioTimestampCache {

    /* renamed from: a  reason: collision with root package name */
    private static HashMap<Long, Record> f11151a = new HashMap<>();
    private static int b = 6;

    static class Record {

        /* renamed from: a  reason: collision with root package name */
        public long f11152a;
        public int b;
        public long c;

        Record() {
        }
    }

    public static int a(long j) {
        if (f11151a.get(Long.valueOf(j)) == null) {
            return -1;
        }
        Record record = f11151a.get(Long.valueOf(j));
        int currentTimeMillis = (int) ((System.currentTimeMillis() - record.c) / 1000);
        if (currentTimeMillis > b) {
            return -1;
        }
        return record.b + currentTimeMillis;
    }

    public static void a(long j, int i) {
        Record record;
        if (f11151a.get(Long.valueOf(j)) != null) {
            record = f11151a.get(Long.valueOf(j));
        } else {
            record = new Record();
        }
        record.f11152a = j;
        record.b = i;
        record.c = System.currentTimeMillis();
        f11151a.put(Long.valueOf(j), record);
    }
}
