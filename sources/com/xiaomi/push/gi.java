package com.xiaomi.push;

import android.os.Bundle;
import android.os.Parcelable;
import android.text.TextUtils;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class gi implements gm {

    /* renamed from: a  reason: collision with root package name */
    private String f12750a;
    private String b;
    private String[] c = null;
    private String[] d = null;
    private String e;
    private List<gi> f = null;

    public gi(String str, String str2, String[] strArr, String[] strArr2) {
        this.f12750a = str;
        this.b = str2;
        this.c = strArr;
        this.d = strArr2;
    }

    public gi(String str, String str2, String[] strArr, String[] strArr2, String str3, List<gi> list) {
        this.f12750a = str;
        this.b = str2;
        this.c = strArr;
        this.d = strArr2;
        this.e = str3;
        this.f = list;
    }

    public static gi a(Bundle bundle) {
        ArrayList arrayList;
        String string = bundle.getString("ext_ele_name");
        String string2 = bundle.getString("ext_ns");
        String string3 = bundle.getString("ext_text");
        Bundle bundle2 = bundle.getBundle("attributes");
        Set<String> keySet = bundle2.keySet();
        String[] strArr = new String[keySet.size()];
        String[] strArr2 = new String[keySet.size()];
        int i = 0;
        for (String str : keySet) {
            strArr[i] = str;
            strArr2[i] = bundle2.getString(str);
            i++;
        }
        if (bundle.containsKey("children")) {
            Parcelable[] parcelableArray = bundle.getParcelableArray("children");
            ArrayList arrayList2 = new ArrayList(parcelableArray.length);
            for (Parcelable parcelable : parcelableArray) {
                arrayList2.add(a((Bundle) parcelable));
            }
            arrayList = arrayList2;
        } else {
            arrayList = null;
        }
        return new gi(string, string2, strArr, strArr2, string3, arrayList);
    }

    public static Parcelable[] a(List<gi> list) {
        return a((gi[]) list.toArray(new gi[list.size()]));
    }

    public static Parcelable[] a(gi[] giVarArr) {
        if (giVarArr == null) {
            return null;
        }
        Parcelable[] parcelableArr = new Parcelable[giVarArr.length];
        for (int i = 0; i < giVarArr.length; i++) {
            parcelableArr[i] = giVarArr[i].f();
        }
        return parcelableArr;
    }

    public String a() {
        return this.f12750a;
    }

    public String a(String str) {
        if (str == null) {
            throw new IllegalArgumentException();
        } else if (this.c == null) {
            return null;
        } else {
            for (int i = 0; i < this.c.length; i++) {
                if (str.equals(this.c[i])) {
                    return this.d[i];
                }
            }
            return null;
        }
    }

    public String b() {
        return this.b;
    }

    public void b(String str) {
        if (!TextUtils.isEmpty(str)) {
            str = gw.a(str);
        }
        this.e = str;
    }

    public String c() {
        return !TextUtils.isEmpty(this.e) ? gw.b(this.e) : this.e;
    }

    public String d() {
        String str;
        StringBuilder sb = new StringBuilder();
        sb.append("<");
        sb.append(this.f12750a);
        if (!TextUtils.isEmpty(this.b)) {
            sb.append(" ");
            sb.append("xmlns=");
            sb.append("\"");
            sb.append(this.b);
            sb.append("\"");
        }
        if (this.c != null && this.c.length > 0) {
            for (int i = 0; i < this.c.length; i++) {
                if (!TextUtils.isEmpty(this.d[i])) {
                    sb.append(" ");
                    sb.append(this.c[i]);
                    sb.append("=\"");
                    sb.append(gw.a(this.d[i]));
                    sb.append("\"");
                }
            }
        }
        if (!TextUtils.isEmpty(this.e)) {
            sb.append(">");
            sb.append(this.e);
        } else if (this.f == null || this.f.size() <= 0) {
            str = "/>";
            sb.append(str);
            return sb.toString();
        } else {
            sb.append(">");
            for (gi d2 : this.f) {
                sb.append(d2.d());
            }
        }
        sb.append("</");
        sb.append(this.f12750a);
        str = ">";
        sb.append(str);
        return sb.toString();
    }

    public Bundle e() {
        Bundle bundle = new Bundle();
        bundle.putString("ext_ele_name", this.f12750a);
        bundle.putString("ext_ns", this.b);
        bundle.putString("ext_text", this.e);
        Bundle bundle2 = new Bundle();
        if (this.c != null && this.c.length > 0) {
            for (int i = 0; i < this.c.length; i++) {
                bundle2.putString(this.c[i], this.d[i]);
            }
        }
        bundle.putBundle("attributes", bundle2);
        if (this.f != null && this.f.size() > 0) {
            bundle.putParcelableArray("children", a(this.f));
        }
        return bundle;
    }

    public Parcelable f() {
        return e();
    }

    public String toString() {
        return d();
    }
}
