package com.xiaomi.jr.base;

import android.text.TextUtils;
import com.xiaomi.jr.common.utils.Utils;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.regex.Pattern;

public abstract class OnceIdManager {

    /* renamed from: a  reason: collision with root package name */
    private static final String f10333a = "#";
    private static final int b = 8;
    private final Pattern c;
    private List<String> d;
    private String e;
    private IUserPreference f;

    protected OnceIdManager(IUserPreference iUserPreference, String str, String str2) {
        Utils.a();
        this.f = iUserPreference;
        this.e = str;
        this.d = c(str);
        this.c = TextUtils.isEmpty(str2) ? null : Pattern.compile(str2);
    }

    private List<String> c(String str) {
        ArrayList arrayList = new ArrayList();
        String a2 = this.f.a(str);
        if (!TextUtils.isEmpty(a2)) {
            Collections.addAll(arrayList, a2.split("#"));
        }
        return arrayList;
    }

    public void a(String str) {
        if (!TextUtils.isEmpty(str)) {
            this.d.add(0, str);
            if (this.d.size() > 8) {
                this.d.subList(8, this.d.size()).clear();
            }
            int size = this.d.size();
            StringBuilder sb = new StringBuilder(this.d.get(0));
            for (int i = 1; i < size; i++) {
                sb.append("#");
                sb.append(this.d.get(i));
            }
            this.f.a(this.e, sb.toString());
        }
    }

    public boolean b(String str) {
        Utils.a();
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        if (this.c == null || this.c.matcher(str).matches()) {
            return !this.d.contains(str);
        }
        return false;
    }
}
