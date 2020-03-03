package com.tmall.wireless.vaf.virtualview.core;

import android.support.v4.util.ArrayMap;
import android.view.View;
import com.libra.TextUtils;
import com.libra.Utils;
import com.mobikwik.sdk.lib.utils.PaymentOptionsDecoder;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;

public class ViewCache {

    /* renamed from: a  reason: collision with root package name */
    private static final String f9388a = "ViewCache_TMTEST";
    private List<ViewBase> b = new ArrayList();
    private ArrayMap<ViewBase, List<Item>> c = new ArrayMap<>();
    private Object d;
    private View e;

    private interface Parser {
        Object a(Object obj);

        String a();

        boolean a(String str);
    }

    public static class Item {

        /* renamed from: a  reason: collision with root package name */
        public static final String f9389a = "_flag_invalidate_";
        protected static final String b = "rp";
        public static final int f = 0;
        public static final int g = 1;
        public static final int h = 2;
        public static final int i = 3;
        public static final int j = 4;
        public static final int k = 5;
        public static final int l = 6;
        public static final int m = 7;
        public static final int n = 8;
        private static final Object o = new Object();
        public ViewBase c;
        public int d;
        public String e;
        private Parser p;
        private ArrayMap<Integer, Object> q;
        private int r;

        public Item(ViewBase viewBase) {
            this(viewBase, 0);
        }

        public Item(ViewBase viewBase, int i2) {
            this.q = new ArrayMap<>();
            this.r = 0;
            this.c = viewBase;
            this.d = i2;
        }

        public Item(ViewBase viewBase, int i2, String str, int i3) {
            this.q = new ArrayMap<>();
            this.r = 0;
            this.c = viewBase;
            this.d = i2;
            this.e = str;
            this.r = i3;
            if (this.e == null) {
                return;
            }
            if (Utils.b(str)) {
                this.p = new ThreeUnknownELParser();
                this.p.a(this.e);
                return;
            }
            this.p = new SimpleELParser();
            this.p.a(this.e);
        }

        public void a(Object obj, boolean z) {
            Object obj2 = this.q.get(Integer.valueOf(obj.hashCode()));
            if (obj2 == null) {
                obj2 = this.p.a(obj);
                if (obj2 == null) {
                    obj2 = o;
                } else {
                    String f2 = Utils.f(obj2);
                    int i2 = this.r;
                    if (i2 == 3) {
                        obj2 = Integer.valueOf(Utils.c(f2));
                    } else if (i2 != 8) {
                        switch (i2) {
                            case 5:
                                if (!"invisible".equals(f2)) {
                                    if (!"gone".equals(f2)) {
                                        obj2 = 1;
                                        break;
                                    } else {
                                        obj2 = 2;
                                        break;
                                    }
                                } else {
                                    obj2 = 0;
                                    break;
                                }
                            case 6:
                                String[] split = f2.split(PaymentOptionsDecoder.pipeSeparator);
                                int i3 = 0;
                                for (String trim : split) {
                                    String trim2 = trim.trim();
                                    if (!TextUtils.a("left", trim2)) {
                                        if (!TextUtils.a("right", trim2)) {
                                            if (!TextUtils.a("h_center", trim2)) {
                                                if (!TextUtils.a("top", trim2)) {
                                                    if (!TextUtils.a("bottom", trim2)) {
                                                        if (!TextUtils.a("v_center", trim2)) {
                                                            if (!TextUtils.a("center", trim2)) {
                                                                obj2 = Integer.valueOf(i3);
                                                                break;
                                                            } else {
                                                                i3 = i3 | 4 | 32;
                                                            }
                                                        } else {
                                                            i3 |= 32;
                                                        }
                                                    } else {
                                                        i3 |= 16;
                                                    }
                                                } else {
                                                    i3 |= 8;
                                                }
                                            } else {
                                                i3 |= 4;
                                            }
                                        } else {
                                            i3 |= 2;
                                        }
                                    } else {
                                        i3 |= 1;
                                    }
                                }
                                obj2 = Integer.valueOf(i3);
                        }
                    } else {
                        String[] split2 = f2.split(PaymentOptionsDecoder.pipeSeparator);
                        int i4 = 0;
                        for (String trim3 : split2) {
                            String trim4 = trim3.trim();
                            if (TextUtils.a("bold", trim4)) {
                                i4 |= 1;
                            } else if (TextUtils.a("italic", trim4)) {
                                i4 |= 2;
                            } else if (TextUtils.a("styleStrike", trim4)) {
                                i4 |= 8;
                            }
                        }
                        obj2 = Integer.valueOf(i4);
                    }
                }
                this.q.put(Integer.valueOf(obj.hashCode()), obj2);
            }
            if (obj2 != o) {
                switch (this.r) {
                    case 0:
                        if (obj2 instanceof Number) {
                            Integer d2 = Utils.d(obj2);
                            if (d2 != null) {
                                this.c.c(this.d, d2.intValue());
                                return;
                            }
                            return;
                        }
                        String obj3 = obj2.toString();
                        if (obj3.endsWith(b)) {
                            Integer d3 = Utils.d(obj3.substring(0, obj3.length() - 2));
                            if (d3 != null) {
                                this.c.b(this.d, d3.intValue());
                                return;
                            }
                            return;
                        }
                        Integer d4 = Utils.d(obj2);
                        if (d4 != null) {
                            this.c.c(this.d, d4.intValue());
                            return;
                        }
                        return;
                    case 1:
                        if (obj2 instanceof Number) {
                            Float b2 = Utils.b(obj2);
                            if (b2 != null) {
                                this.c.b(this.d, b2.floatValue());
                                return;
                            }
                            return;
                        }
                        String obj4 = obj2.toString();
                        if (obj4.endsWith(b)) {
                            Float b3 = Utils.b((Object) obj4.substring(0, obj4.length() - 2));
                            if (b3 != null) {
                                this.c.a(this.d, b3.floatValue());
                                return;
                            }
                            return;
                        }
                        Float b4 = Utils.b(obj2);
                        if (b4 != null) {
                            this.c.b(this.d, b4.floatValue());
                            return;
                        }
                        return;
                    case 2:
                        this.c.a(this.d, Utils.f(obj2));
                        return;
                    case 3:
                    case 5:
                    case 6:
                    case 8:
                        Integer d5 = Utils.d(obj2);
                        if (d5 != null) {
                            this.c.c(this.d, d5.intValue());
                            return;
                        }
                        return;
                    case 4:
                        Boolean a2 = Utils.a(obj2);
                        if (a2 != null) {
                            this.c.c(this.d, a2.booleanValue() ? 1 : 0);
                            return;
                        } else {
                            this.c.c(this.d, 0);
                            return;
                        }
                    case 7:
                        if (this.c.b(this.d, obj2)) {
                            return;
                        }
                        if (z) {
                            this.c.d(obj2);
                            return;
                        } else {
                            this.c.c(obj2);
                            return;
                        }
                    default:
                        return;
                }
            }
        }

        public void a() {
            this.q.clear();
        }

        public void a(int i2) {
            this.q.remove(Integer.valueOf(i2));
        }
    }

    public void a(View view) {
        this.e = view;
    }

    public View a() {
        return this.e;
    }

    public void a(Object obj) {
        this.d = obj;
    }

    public Object b() {
        return this.d;
    }

    public List<ViewBase> c() {
        return this.b;
    }

    public void d() {
        if (this.b != null) {
            this.b.clear();
            this.b = null;
        }
        if (this.c != null) {
            int size = this.c.size();
            for (int i = 0; i < size; i++) {
                List valueAt = this.c.valueAt(i);
                if (valueAt != null) {
                    int size2 = valueAt.size();
                    for (int i2 = 0; i2 < size2; i2++) {
                        ((Item) valueAt.get(i2)).a();
                    }
                }
            }
            this.c.clear();
            this.c = null;
        }
    }

    public void a(ViewBase viewBase) {
        a(viewBase, 0, (String) null, 0);
    }

    public void a(ViewBase viewBase, int i, String str, int i2) {
        List list = this.c.get(viewBase);
        if (list == null) {
            list = new ArrayList();
            this.c.put(viewBase, list);
            this.b.add(viewBase);
        }
        list.add(new Item(viewBase, i, str, i2));
    }

    public List<Item> b(ViewBase viewBase) {
        return this.c.get(viewBase);
    }

    private static class SimpleELParser implements Parser {

        /* renamed from: a  reason: collision with root package name */
        private static final char f9390a = '$';
        private static final char b = '{';
        private static final char c = '}';
        private static final int d = 2;
        private static final int e = 3;
        private static final int f = 4;
        private static final char g = '.';
        private static final char h = '[';
        private static final char i = ']';
        private List<Object> j;
        private String k;
        private int l;

        private SimpleELParser() {
            this.j = new LinkedList();
        }

        public String a() {
            return this.k;
        }

        public boolean a(String str) {
            if (str == null || str.length() == 0) {
                return false;
            }
            this.k = str;
            int length = str.length();
            this.j.clear();
            if (str.charAt(0) == '$' && str.charAt(1) == '{') {
                int i2 = length - 1;
                if (str.charAt(i2) == '}') {
                    StringBuilder sb = new StringBuilder();
                    this.l = 2;
                    for (int i3 = 2; i3 < i2; i3++) {
                        char charAt = str.charAt(i3);
                        if (charAt != '.') {
                            if (charAt != '[') {
                                if (charAt != ']') {
                                    sb.append(charAt);
                                } else if (this.l != 3) {
                                    return false;
                                } else {
                                    String sb2 = sb.toString();
                                    try {
                                        this.j.add(Integer.valueOf(Integer.parseInt(sb2)));
                                    } catch (NumberFormatException unused) {
                                        this.j.add(sb2);
                                    }
                                    sb.delete(0, sb.length());
                                    this.l = 4;
                                }
                            } else if (this.l != 2) {
                                return false;
                            } else {
                                if (sb.length() > 0) {
                                    String sb3 = sb.toString();
                                    try {
                                        this.j.add(Integer.valueOf(Integer.parseInt(sb3)));
                                    } catch (NumberFormatException unused2) {
                                        this.j.add(sb3);
                                    }
                                    sb.delete(0, sb.length());
                                }
                                this.l = 3;
                            }
                        } else if (this.l == 3) {
                            sb.append(charAt);
                        } else if (this.l == 4) {
                            this.l = 2;
                        } else {
                            String sb4 = sb.toString();
                            try {
                                this.j.add(Integer.valueOf(Integer.parseInt(sb4)));
                            } catch (NumberFormatException unused3) {
                                this.j.add(sb4);
                            }
                            sb.delete(0, sb.length());
                        }
                    }
                    if (this.l == 2) {
                        String sb5 = sb.toString();
                        try {
                            this.j.add(Integer.valueOf(Integer.parseInt(sb5)));
                        } catch (NumberFormatException unused4) {
                            this.j.add(sb5);
                        }
                    }
                    return true;
                }
            }
            return false;
        }

        public Object a(Object obj) {
            Object obj2 = null;
            if (this.j.size() <= 0) {
                return this.k;
            }
            if (obj == null) {
                return null;
            }
            int i2 = 0;
            int size = this.j.size();
            while (i2 < size) {
                Object obj3 = this.j.get(i2);
                if (obj3 instanceof String) {
                    String obj4 = obj3.toString();
                    if (!obj4.equalsIgnoreCase("this")) {
                        if (!(obj instanceof JSONObject)) {
                            return obj2;
                        }
                        obj = ((JSONObject) obj).opt(obj4);
                    }
                } else if (!(obj3 instanceof Integer)) {
                    continue;
                    i2++;
                    obj = obj2;
                } else if (!(obj instanceof JSONArray)) {
                    return obj2;
                } else {
                    obj = ((JSONArray) obj).opt(((Integer) obj3).intValue());
                }
                obj2 = obj;
                i2++;
                obj = obj2;
            }
            return obj2;
        }
    }

    private static class ThreeUnknownELParser implements Parser {

        /* renamed from: a  reason: collision with root package name */
        private static final char f9391a = '@';
        private static final char b = '?';
        private static final char c = ':';
        private static final int d = 1;
        private static final int e = 2;
        private static final int f = 3;
        private static final char g = '{';
        private static final char h = '}';
        private int i;
        private SimpleELParser j;
        private SimpleELParser k;
        private SimpleELParser l;
        private String m;

        private ThreeUnknownELParser() {
        }

        public boolean a(String str) {
            if (str == null || str.length() == 0) {
                return false;
            }
            this.m = str;
            int length = str.length();
            if (str.charAt(0) == '@' && str.charAt(1) == '{') {
                int i2 = length - 1;
                if (str.charAt(i2) == '}') {
                    StringBuilder sb = new StringBuilder();
                    this.i = 1;
                    for (int i3 = 2; i3 < i2; i3++) {
                        char charAt = str.charAt(i3);
                        if (charAt != ':') {
                            if (charAt != '?') {
                                sb.append(charAt);
                            } else if (this.i == 1) {
                                this.j = new SimpleELParser();
                                this.j.a(sb.toString().trim());
                                sb.delete(0, sb.length());
                                this.i = 2;
                            }
                        } else if (this.i == 2) {
                            this.k = new SimpleELParser();
                            this.k.a(sb.toString().trim());
                            sb.delete(0, sb.length());
                            this.i = 3;
                        }
                    }
                    if (this.i == 3) {
                        this.l = new SimpleELParser();
                        this.l.a(sb.toString().trim());
                    }
                    return true;
                }
            }
            return false;
        }

        public String a() {
            return this.m;
        }

        /* JADX WARNING: Code restructure failed: missing block: B:19:0x0048, code lost:
            if (com.libra.TextUtils.a(((java.lang.String) r0).toLowerCase(), "false") != false) goto L_0x006f;
         */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public java.lang.Object a(java.lang.Object r6) {
            /*
                r5 = this;
                com.tmall.wireless.vaf.virtualview.core.ViewCache$SimpleELParser r0 = r5.j
                if (r0 == 0) goto L_0x0081
                com.tmall.wireless.vaf.virtualview.core.ViewCache$SimpleELParser r0 = r5.k
                if (r0 == 0) goto L_0x0081
                com.tmall.wireless.vaf.virtualview.core.ViewCache$SimpleELParser r0 = r5.l
                if (r0 == 0) goto L_0x0081
                if (r6 == 0) goto L_0x007f
                com.tmall.wireless.vaf.virtualview.core.ViewCache$SimpleELParser r0 = r5.j
                java.lang.Object r0 = r0.a((java.lang.Object) r6)
                r1 = 1
                r2 = 0
                if (r0 != 0) goto L_0x0019
                goto L_0x006f
            L_0x0019:
                boolean r3 = r0 instanceof java.lang.Boolean
                if (r3 == 0) goto L_0x0025
                java.lang.Boolean r0 = (java.lang.Boolean) r0
                boolean r1 = r0.booleanValue()
                r2 = r1
                goto L_0x006f
            L_0x0025:
                boolean r3 = r0 instanceof java.lang.String
                if (r3 == 0) goto L_0x004b
                r3 = r0
                java.lang.CharSequence r3 = (java.lang.CharSequence) r3
                boolean r4 = com.libra.TextUtils.a(r3)
                if (r4 == 0) goto L_0x0033
                goto L_0x006f
            L_0x0033:
                java.lang.String r4 = "null"
                boolean r3 = com.libra.TextUtils.a(r3, r4)
                if (r3 == 0) goto L_0x003c
                goto L_0x006f
            L_0x003c:
                java.lang.String r0 = (java.lang.String) r0
                java.lang.String r0 = r0.toLowerCase()
                java.lang.String r3 = "false"
                boolean r0 = com.libra.TextUtils.a(r0, r3)
                if (r0 == 0) goto L_0x006e
                goto L_0x006f
            L_0x004b:
                boolean r3 = r0 instanceof org.json.JSONObject
                if (r3 == 0) goto L_0x0058
                org.json.JSONObject r0 = (org.json.JSONObject) r0
                int r0 = r0.length()
                if (r0 != 0) goto L_0x006e
                goto L_0x006f
            L_0x0058:
                boolean r3 = r0 instanceof org.json.JSONArray
                if (r3 == 0) goto L_0x0065
                org.json.JSONArray r0 = (org.json.JSONArray) r0
                int r0 = r0.length()
                if (r0 != 0) goto L_0x006e
                goto L_0x006f
            L_0x0065:
                java.lang.Object r3 = org.json.JSONObject.NULL
                boolean r0 = r0.equals(r3)
                if (r0 == 0) goto L_0x006e
                goto L_0x006f
            L_0x006e:
                r2 = 1
            L_0x006f:
                if (r2 == 0) goto L_0x0078
                com.tmall.wireless.vaf.virtualview.core.ViewCache$SimpleELParser r0 = r5.k
                java.lang.Object r6 = r0.a((java.lang.Object) r6)
                goto L_0x0083
            L_0x0078:
                com.tmall.wireless.vaf.virtualview.core.ViewCache$SimpleELParser r0 = r5.l
                java.lang.Object r6 = r0.a((java.lang.Object) r6)
                goto L_0x0083
            L_0x007f:
                r6 = 0
                goto L_0x0083
            L_0x0081:
                java.lang.String r6 = r5.m
            L_0x0083:
                return r6
            */
            throw new UnsupportedOperationException("Method not decompiled: com.tmall.wireless.vaf.virtualview.core.ViewCache.ThreeUnknownELParser.a(java.lang.Object):java.lang.Object");
        }
    }
}
