package com.yanzhenjie.yp_permission.runtime;

import com.yanzhenjie.yp_permission.Action;
import com.yanzhenjie.yp_permission.Rationale;
import com.yanzhenjie.yp_permission.checker.PermissionChecker;
import com.yanzhenjie.yp_permission.checker.StrictChecker;
import com.yanzhenjie.yp_permission.source.Source;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class LRequest implements PermissionRequest {

    /* renamed from: a  reason: collision with root package name */
    private static final PermissionChecker f2463a = new StrictChecker();
    private Source b;
    private String[] c;
    private Action<List<String>> d;
    private Action<List<String>> e;

    public PermissionRequest a(Rationale<List<String>> rationale) {
        return this;
    }

    LRequest(Source source) {
        this.b = source;
    }

    public PermissionRequest a(String... strArr) {
        this.c = strArr;
        return this;
    }

    public PermissionRequest a(Action<List<String>> action) {
        this.d = action;
        return this;
    }

    public PermissionRequest b(Action<List<String>> action) {
        this.e = action;
        return this;
    }

    public void ac_() {
        List<String> a2 = a(this.b, this.c);
        if (a2.isEmpty()) {
            b();
        } else {
            a(a2);
        }
    }

    private void b() {
        if (this.d != null) {
            List asList = Arrays.asList(this.c);
            try {
                this.d.a(asList);
            } catch (Exception unused) {
                if (this.e != null) {
                    this.e.a(asList);
                }
            }
        }
    }

    private void a(List<String> list) {
        if (this.e != null) {
            this.e.a(list);
        }
    }

    private static List<String> a(Source source, String... strArr) {
        ArrayList arrayList = new ArrayList(1);
        for (String str : strArr) {
            if (!f2463a.a(source.a(), str)) {
                arrayList.add(str);
            }
        }
        return arrayList;
    }
}
