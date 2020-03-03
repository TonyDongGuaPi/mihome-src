package com.mi.blockcanary;

import android.content.Context;
import com.mi.blockcanary.internal.BlockInfo;
import java.io.File;
import java.util.LinkedList;
import java.util.List;

public class BlockCanaryContext implements BlockInterceptor {

    /* renamed from: a  reason: collision with root package name */
    private static Context f6739a;
    private static BlockCanaryContext b;

    public String a() {
        return "unknown";
    }

    public void a(Context context, BlockInfo blockInfo) {
    }

    public boolean a(File[] fileArr, File file) {
        return false;
    }

    public String b() {
        return "uid";
    }

    public String c() {
        return "unknown";
    }

    public boolean d() {
        return false;
    }

    public List<String> e() {
        return null;
    }

    public boolean g() {
        return false;
    }

    public boolean h() {
        return true;
    }

    public int k() {
        return -1;
    }

    public String l() {
        return "/blockcanary/";
    }

    public boolean m() {
        return false;
    }

    public boolean n() {
        return true;
    }

    static void a(Context context, BlockCanaryContext blockCanaryContext) {
        f6739a = context;
        b = blockCanaryContext;
    }

    public static BlockCanaryContext i() {
        if (b != null) {
            return b;
        }
        throw new RuntimeException("BlockCanaryContext null");
    }

    public Context j() {
        return f6739a;
    }

    public void a(File file) {
        throw new UnsupportedOperationException();
    }

    public List<String> f() {
        LinkedList linkedList = new LinkedList();
        linkedList.add("org.chromium");
        return linkedList;
    }
}
