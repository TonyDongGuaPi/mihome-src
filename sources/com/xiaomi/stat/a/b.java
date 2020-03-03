package com.xiaomi.stat.a;

import android.text.TextUtils;
import com.mi.blockcanary.internal.BlockInfo;
import com.xiaomi.stat.a.l;

public class b {

    /* renamed from: a  reason: collision with root package name */
    public static final int f22999a = 0;
    public static final int b = 1;
    private String c;
    private int d;
    private boolean e;
    private boolean f;

    public b(String str, int i, boolean z) {
        this.c = str;
        this.d = i;
        this.e = z;
        this.f = TextUtils.isEmpty(str);
    }

    public boolean a(String str, String str2, boolean z) {
        if (!TextUtils.equals(str, this.c) || this.e != z) {
            return false;
        }
        if (this.d == 0) {
            return true;
        }
        if (!this.f || !TextUtils.equals(str2, l.a.h)) {
            return false;
        }
        return true;
    }

    public String a() {
        StringBuilder sb = new StringBuilder();
        sb.append(j.i);
        if (this.f) {
            sb.append(" is null");
        } else {
            sb.append(" = \"");
            sb.append(this.c);
            sb.append("\"");
        }
        if (this.d != 0) {
            sb.append(" and ");
            sb.append("eg");
            sb.append(" = \"");
            sb.append(l.a.h);
            sb.append("\"");
        }
        sb.append(" and ");
        sb.append(j.j);
        sb.append(BlockInfo.c);
        sb.append(this.e ? 1 : 0);
        return sb.toString();
    }
}
