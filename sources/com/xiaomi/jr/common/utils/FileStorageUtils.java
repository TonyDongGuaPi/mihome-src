package com.xiaomi.jr.common.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class FileStorageUtils {

    /* renamed from: a  reason: collision with root package name */
    private static final List<String> f1412a = new ArrayList();
    private static final Random b = new Random();

    static {
        f1412a.add("http://f1.market.xiaomi.com");
        f1412a.add("http://f2.market.xiaomi.com");
        f1412a.add("http://f3.market.xiaomi.com");
        f1412a.add("http://f4.market.xiaomi.com");
        f1412a.add("http://f5.market.xiaomi.com");
        f1412a.add("http://f1.market.mi-img.com");
        f1412a.add("http://f2.market.mi-img.com");
        f1412a.add("http://f3.market.mi-img.com");
        f1412a.add("http://f4.market.mi-img.com");
        f1412a.add("http://f5.market.mi-img.com");
    }

    public static String a() {
        return f1412a.get(b.nextInt(f1412a.size())) + "/download/";
    }
}
