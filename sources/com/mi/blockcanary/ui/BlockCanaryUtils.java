package com.mi.blockcanary.ui;

import android.text.TextUtils;
import com.mi.blockcanary.BlockCanaryInternals;
import com.mi.blockcanary.internal.BlockInfo;
import com.mi.blockcanary.internal.ProcessUtils;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

final class BlockCanaryUtils {

    /* renamed from: a  reason: collision with root package name */
    private static final List<String> f6757a = new LinkedList();
    private static final List<String> b = new LinkedList();

    BlockCanaryUtils() {
    }

    static {
        f6757a.addAll(BlockCanaryInternals.c().f());
        if (BlockCanaryInternals.c().e() != null) {
            b.addAll(BlockCanaryInternals.c().e());
        }
        if (b.isEmpty()) {
            b.add(ProcessUtils.a());
        }
    }

    public static String a(BlockInfo blockInfo) {
        Iterator<String> it = blockInfo.C.iterator();
        while (it.hasNext()) {
            String next = it.next();
            if (Character.isLetter(next.charAt(0))) {
                String[] split = next.split("\r\n");
                for (String a2 : split) {
                    String a3 = a(a2);
                    if (a3 != null) {
                        return a3;
                    }
                }
                return b(split[0]);
            }
        }
        return "";
    }

    public static boolean b(BlockInfo blockInfo) {
        if (!(!TextUtils.isEmpty(blockInfo.y)) || blockInfo.w < 0) {
            return false;
        }
        return true;
    }

    public static boolean c(BlockInfo blockInfo) {
        Iterator<String> it = blockInfo.C.iterator();
        while (true) {
            if (!it.hasNext()) {
                return false;
            }
            String next = it.next();
            if (Character.isLetter(next.charAt(0))) {
                for (String str : next.split("\r\n")) {
                    for (String startsWith : f6757a) {
                        if (str.startsWith(startsWith)) {
                            return true;
                        }
                    }
                }
                continue;
            }
        }
    }

    public static List<String> a() {
        return b;
    }

    private static String a(String str) {
        for (String startsWith : b) {
            if (str.startsWith(startsWith)) {
                return b(str);
            }
        }
        return null;
    }

    private static String b(String str) {
        int indexOf = str.indexOf(40);
        int indexOf2 = str.indexOf(41);
        return (indexOf < 0 || indexOf2 < 0) ? str : str.substring(indexOf + 1, indexOf2);
    }
}
