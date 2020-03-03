package com.xiaomi.smarthome.device.utils;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;

public interface DeviceTagInterface<T> {
    public static final String A = "category_info_updated_action";
    public static final String B = "param_device_tag_type";
    public static final String C = "param_device_count";
    public static final int D = 1;
    public static final int E = 2048;
    public static final int F = 1000;
    public static final int G = 1001;
    public static final int H = 1000;
    public static final int I = 2001;
    public static final int J = 1000;
    public static final int K = 3001;
    public static final int L = 1000;
    public static final int M = -1;
    public static final int N = 0;
    public static final int O = 1;
    public static final int P = 2;
    public static final int Q = 3;
    public static final int R = 4;
    public static final int S = 5;
    public static final int T = 6;
    public static final int U = 7;
    public static final int V = 8;

    /* renamed from: a  reason: collision with root package name */
    public static final String f15434a = "router_info";
    public static final String b = "router_location_last_time";
    public static final String c = "bssid";
    public static final String d = "pair_bssid";
    public static final String e = "ssid";
    public static final String f = "remark";
    public static final String g = "location";
    public static final String h = "custom_info_updated_action";
    public static final String i = "custom_tag_info";
    public static final String j = "did";
    public static final String k = "tag";
    public static final String l = "custom_tag_last_time";
    public static final String m = "ver";
    public static final String n = "order";
    public static final String o = "dt";
    public static final String p = "dto";
    public static final String q = "o";
    public static final String r = "t";
    public static final String s = "ts";
    public static final String t = "tc";
    public static final String u = "custom_tag_version_info";
    public static final String v = "custom_tag_order_info";
    public static final String w = "custom_tag_router_info";
    public static final String x = "tag_info_updated_action";
    public static final String y = "tag_info_edited_action";
    public static final String z = "tag_info_edited_new_action";

    public static class Category {

        /* renamed from: a  reason: collision with root package name */
        public String f15435a;
        public String b;
        public int c;
        public String d;
    }

    public interface IDeviceTagListener {
        void a();
    }

    public interface IRoomConfigListener {
        void a();
    }

    List<String> a(T t2);

    Map<String, Set<String>> a(int i2);

    void a(IDeviceTagListener iDeviceTagListener);

    void a(IRoomConfigListener iRoomConfigListener);

    void a(String str);

    void a(String str, String str2);

    void a(String str, String str2, Set<String> set);

    void a(String str, Set<String> set);

    void a(List<T> list);

    boolean a();

    boolean a(int i2, String str);

    boolean a(int i2, String str, String str2);

    String b();

    String b(int i2, String str, String str2);

    String b(String str);

    String b(String str, String str2);

    List<String> b(int i2);

    Set<String> b(int i2, String str);

    void b(IDeviceTagListener iDeviceTagListener);

    void b(List<T> list);

    Category c(String str);

    List<String> c();

    List<String> c(int i2, String str);

    void c(String str, String str2);

    void c(List<T> list);

    Category d(String str);

    Map<String, Set<String>> d();

    void d(String str, String str2);

    String e(String str);

    Set<String> e(String str, String str2);

    void e();

    int f();

    String f(String str);

    CharSequence g();

    void g(String str);

    Category h(String str);

    void h();

    Category i(String str);

    void i();

    Map<String, List<String>> j(String str);

    void j();

    String k(String str);

    void k();

    List<Integer> l();

    List<Integer> m();

    Collection<Category> n();

    List<String> o();

    Set<Integer> p();

    Map<String, Set<String>> q();

    List<T> r();

    void s();

    void t();

    String u();
}
