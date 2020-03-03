package com.mics.constant;

import android.util.Pair;
import com.mics.util.Preferences;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class API {

    /* renamed from: a  reason: collision with root package name */
    private static final String f7623a = "https://ypsupport2.kefu.mi.com";
    private static final String b = "https://ypsupport2.kefu.mi.com";
    private static final String c = "http://ypsupport2.kefu.test.mi.com";
    private static final String d = "mobile-api";
    private static final String e = "wss://gateway.kefu.mi.com";
    private static final String f = "ws://10.40.18.87:11111";
    private static final String g = "api-host";
    private static final String h = "api-ws-host";
    private static List<Pair<String, String>> i;
    private static List<Pair<String, String>> j;
    private static Map<String, String> k = new HashMap();

    private static String z() {
        return t() + "/" + d;
    }

    public static String a() {
        return z() + "/session/create";
    }

    public static String b() {
        return z() + "/session/query";
    }

    public static String c() {
        return z() + "/session/close";
    }

    public static String d() {
        return z() + "/session/queryServiceList";
    }

    public static String e() {
        return z() + "/session/chooseService";
    }

    public static String f() {
        return z() + "/session/queryHumanScoreConfig";
    }

    public static String g() {
        return z() + "/session/submitHumanScore";
    }

    public static String h() {
        return z() + "/session/submitRobotEvaluation";
    }

    public static String i() {
        return z() + "/session/toLeaveMessage";
    }

    public static String j() {
        return z() + "/session/queryQueuePosition";
    }

    public static String k() {
        return z() + "/chat/send";
    }

    public static String l() {
        return z() + "/chat/upload";
    }

    public static String m() {
        return z() + "/chat/pullMessage";
    }

    public static String n() {
        return z() + "/chat/pullHistoryMessage";
    }

    public static String o() {
        return z() + "/chat/chatList";
    }

    public static String p() {
        return z() + "/chat/readOffset";
    }

    public static String q() {
        return z() + "/chat/hide";
    }

    public static String r() {
        return z() + "/switch/version";
    }

    public static List<Pair<String, String>> s() {
        if (i == null) {
            i = new ArrayList();
            Pair pair = new Pair("https://ypsupport2.kefu.mi.com", "正式");
            Pair pair2 = new Pair(c, "测试");
            i.add(pair);
            i.add(pair2);
        }
        return i;
    }

    public static String t() {
        return a(g, "https://ypsupport2.kefu.mi.com");
    }

    public static String u() {
        return a(s(), t());
    }

    public static void a(String str) {
        a(s(), g, str);
    }

    public static String v() {
        return x();
    }

    public static List<Pair<String, String>> w() {
        if (j == null) {
            j = new ArrayList();
            Pair pair = new Pair(e, "正式");
            Pair pair2 = new Pair(f, "测试");
            j.add(pair);
            j.add(pair2);
        }
        return j;
    }

    public static String x() {
        return a(h, e);
    }

    public static String y() {
        return a(w(), x());
    }

    public static void b(String str) {
        a(w(), h, str);
    }

    private static String a(String str, String str2) {
        String str3 = k.get(str);
        if (str3 == null) {
            str3 = Preferences.a(str);
            if (str3 == null) {
                str3 = str2;
            }
            k.put(str, str3);
        }
        return str3;
    }

    private static String a(List<Pair<String, String>> list, String str) {
        for (Pair next : list) {
            if (((String) next.first).equals(str)) {
                return (String) next.second;
            }
        }
        return null;
    }

    private static void a(List<Pair<String, String>> list, String str, String str2) {
        for (Pair<String, String> pair : list) {
            if (((String) pair.first).equals(str2)) {
                k.remove(str);
                Preferences.a(str, str2);
                return;
            }
        }
    }
}
