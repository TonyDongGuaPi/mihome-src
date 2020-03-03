package com.miuipub.internal.hybrid;

import com.taobao.weex.el.parse.Operators;
import java.util.TreeSet;

public class ConfigUtils {

    /* renamed from: a  reason: collision with root package name */
    private static final String f8253a = "timestamp";
    private static final String b = "vendor";
    private static final String c = "features";
    private static final String d = "params";
    private static final String e = "name";
    private static final String f = "value";
    private static final String g = "permissions";
    private static final String h = "origin";
    private static final String i = "subdomains";

    private ConfigUtils() {
    }

    public static String a(Config config) {
        return Operators.BLOCK_START_STR + "timestamp" + ":" + config.a().b() + "," + b + ":" + "\"" + config.b() + "\"" + "," + b(config) + "," + d(config) + "}";
    }

    private static String b(Config config) {
        return "features" + ":" + Operators.ARRAY_START_STR + c(config) + Operators.ARRAY_END_STR;
    }

    private static String c(Config config) {
        StringBuilder sb = new StringBuilder();
        TreeSet<String> treeSet = new TreeSet<>(config.d().keySet());
        if (treeSet.isEmpty()) {
            return "";
        }
        for (String str : treeSet) {
            sb.append(Operators.BLOCK_START_STR);
            sb.append("name");
            sb.append(":");
            sb.append("\"");
            sb.append(str);
            sb.append("\"");
            sb.append(",");
            sb.append("params");
            sb.append(":");
            sb.append(Operators.ARRAY_START_STR);
            sb.append(a(config.c(str)));
            sb.append(Operators.ARRAY_END_STR);
            sb.append("}");
            sb.append(",");
        }
        return sb.substring(0, sb.length() - 1);
    }

    private static String a(Feature feature) {
        StringBuilder sb = new StringBuilder();
        TreeSet<String> treeSet = new TreeSet<>(feature.b().keySet());
        if (treeSet.isEmpty()) {
            return "";
        }
        for (String str : treeSet) {
            sb.append(Operators.BLOCK_START_STR);
            sb.append("name");
            sb.append(":");
            sb.append("\"");
            sb.append(str);
            sb.append("\"");
            sb.append(",");
            sb.append("value");
            sb.append(":");
            sb.append("\"");
            sb.append(feature.b(str));
            sb.append("\"");
            sb.append("}");
            sb.append(",");
        }
        return sb.substring(0, sb.length() - 1);
    }

    private static String d(Config config) {
        return "permissions" + ":" + Operators.ARRAY_START_STR + e(config) + Operators.ARRAY_END_STR;
    }

    private static Object e(Config config) {
        StringBuilder sb = new StringBuilder();
        TreeSet<String> treeSet = new TreeSet<>(config.h().keySet());
        if (treeSet.isEmpty()) {
            return "";
        }
        for (String str : treeSet) {
            sb.append(Operators.BLOCK_START_STR);
            sb.append("origin");
            sb.append(":");
            sb.append("\"");
            sb.append(str);
            sb.append("\"");
            sb.append(",");
            sb.append(i);
            sb.append(":");
            sb.append(config.e(str).b());
            sb.append("}");
            sb.append(",");
        }
        return sb.substring(0, sb.length() - 1);
    }
}
