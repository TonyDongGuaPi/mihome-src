package com.xiaomi.infra.galaxy.fds.model;

import com.google.common.collect.LinkedListMultimap;
import com.xiaomi.infra.galaxy.fds.Common;
import com.xiaomi.infra.galaxy.fds.auth.signature.Utils;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class FDSObjectMetadata {

    /* renamed from: a  reason: collision with root package name */
    public static final String f10166a = "x-xiaomi-meta-";
    private final Map<String, String> b = new HashMap();

    public enum PredefinedMetadata {
        CacheControl("cache-control"),
        ContentEncoding("content-encoding"),
        ContentLength("content-length"),
        ContentRange(Common.w),
        LastModified("last-modified"),
        ContentMD5(Common.k),
        ContentType("content-type"),
        LastChecked(Common.t),
        UploadTime(Common.u);
        
        private final String header;

        private PredefinedMetadata(String str) {
            this.header = str;
        }

        public String getHeader() {
            return this.header;
        }
    }

    public void a(String str, String str2) {
        f(str);
        this.b.put(str, str2);
    }

    public void b(String str, String str2) {
        f(str);
        this.b.put(str, str2);
    }

    public void a(Map<String, String> map) {
        for (Map.Entry next : map.entrySet()) {
            f((String) next.getKey());
            this.b.put(next.getKey(), next.getValue());
        }
    }

    public String a() {
        return this.b.get("cache-control");
    }

    public void a(String str) {
        this.b.put("cache-control", str);
    }

    public String b() {
        return this.b.get("content-encoding");
    }

    public void b(String str) {
        this.b.put("content-encoding", str);
    }

    public long c() {
        String str = this.b.get("content-length");
        if (str != null) {
            return Long.parseLong(str);
        }
        return -1;
    }

    public void a(long j) {
        this.b.put("content-length", Long.toString(j));
    }

    public String d() {
        return this.b.get(Common.w);
    }

    public void a(long j, long j2, long j3) {
        Map<String, String> map = this.b;
        map.put(Common.w, "bytes " + j + "-" + j2 + "/" + j3);
    }

    public Date e() {
        String str = this.b.get("last-modified");
        if (str != null) {
            return Utils.a(str);
        }
        return null;
    }

    public void a(Date date) {
        this.b.put("last-modified", Utils.a(date));
    }

    public String f() {
        return this.b.get(Common.t);
    }

    public void c(String str) {
        this.b.put(Common.t, str);
    }

    public String g() {
        return this.b.get(Common.k);
    }

    public void d(String str) {
        this.b.put(Common.k, str);
    }

    public String h() {
        return this.b.get("content-type");
    }

    public void e(String str) {
        this.b.put("content-type", str);
    }

    public Map<String, String> i() {
        return Collections.unmodifiableMap(this.b);
    }

    public static FDSObjectMetadata a(LinkedListMultimap<String, String> linkedListMultimap) {
        FDSObjectMetadata fDSObjectMetadata = new FDSObjectMetadata();
        HashMap hashMap = new HashMap();
        for (Map.Entry entry : linkedListMultimap.entries()) {
            String lowerCase = ((String) entry.getKey()).toLowerCase();
            String str = (String) entry.getValue();
            if (!hashMap.containsKey(lowerCase)) {
                hashMap.put(lowerCase, str);
                if (lowerCase.startsWith("x-xiaomi-meta-")) {
                    fDSObjectMetadata.a(lowerCase, str);
                }
            }
        }
        for (PredefinedMetadata predefinedMetadata : PredefinedMetadata.values()) {
            String str2 = (String) hashMap.get(predefinedMetadata.getHeader());
            if (str2 != null && !str2.isEmpty()) {
                fDSObjectMetadata.a(predefinedMetadata.getHeader(), str2);
            }
        }
        return fDSObjectMetadata;
    }

    private static void f(String str) {
        boolean startsWith = str.startsWith("x-xiaomi-meta-");
        if (!startsWith) {
            PredefinedMetadata[] values = PredefinedMetadata.values();
            int length = values.length;
            int i = 0;
            while (true) {
                if (i >= length) {
                    break;
                } else if (str.equals(values[i].getHeader())) {
                    startsWith = true;
                    break;
                } else {
                    i++;
                }
            }
        }
        if (!startsWith) {
            throw new RuntimeException("Invalid metadata: " + str, (Throwable) null);
        }
    }
}
