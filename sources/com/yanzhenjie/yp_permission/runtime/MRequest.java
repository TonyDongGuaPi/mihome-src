package com.yanzhenjie.yp_permission.runtime;

import android.content.Context;
import android.util.Log;
import com.yanzhenjie.yp_permission.Action;
import com.yanzhenjie.yp_permission.PermissionActivity;
import com.yanzhenjie.yp_permission.Rationale;
import com.yanzhenjie.yp_permission.RequestExecutor;
import com.yanzhenjie.yp_permission.checker.DoubleChecker;
import com.yanzhenjie.yp_permission.checker.PermissionChecker;
import com.yanzhenjie.yp_permission.checker.StandardChecker;
import com.yanzhenjie.yp_permission.source.Source;
import com.yanzhenjie.yp_permission.util.MainExecutor;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class MRequest implements PermissionActivity.RequestListener, RequestExecutor, PermissionRequest {

    /* renamed from: a  reason: collision with root package name */
    private static final MainExecutor f2464a = new MainExecutor();
    private static final PermissionChecker b = new StandardChecker();
    private static final PermissionChecker c = new DoubleChecker();
    private Source d;
    private String[] e;
    private Rationale<List<String>> f = new Rationale<List<String>>() {
        public void a(Context context, List<String> list, RequestExecutor requestExecutor) {
            requestExecutor.b();
        }
    };
    private Action<List<String>> g;
    private Action<List<String>> h;
    private String[] i;

    MRequest(Source source) {
        this.d = source;
    }

    public PermissionRequest a(String... strArr) {
        this.e = strArr;
        return this;
    }

    public PermissionRequest a(Rationale<List<String>> rationale) {
        this.f = rationale;
        return this;
    }

    public PermissionRequest a(Action<List<String>> action) {
        this.g = action;
        return this;
    }

    public PermissionRequest b(Action<List<String>> action) {
        this.h = action;
        return this;
    }

    public void ac_() {
        List<String> a2 = a(b, this.d, this.e);
        this.i = (String[]) a2.toArray(new String[a2.size()]);
        if (this.i.length > 0) {
            List<String> a3 = a(this.d, this.i);
            if (a3.size() > 0) {
                this.f.a(this.d.a(), a3, this);
            } else {
                b();
            }
        } else {
            d();
        }
    }

    public void b() {
        PermissionActivity.requestPermission(this.d.a(), this.i, this);
    }

    public void c() {
        d();
    }

    public void a() {
        f2464a.a(new Runnable() {
            public void run() {
                MRequest.this.d();
            }
        }, 100);
    }

    /* access modifiers changed from: private */
    public void d() {
        List<String> a2 = a(c, this.d, this.e);
        if (a2.isEmpty()) {
            e();
        } else {
            a(a2);
        }
    }

    private void e() {
        if (this.g != null) {
            List asList = Arrays.asList(this.e);
            try {
                this.g.a(asList);
            } catch (Exception e2) {
                Log.e("AndPermission", "Please check the onGranted() method body for bugs.", e2);
                if (this.h != null) {
                    this.h.a(asList);
                }
            }
        }
    }

    private void a(List<String> list) {
        if (this.h != null) {
            this.h.a(list);
        }
    }

    private static List<String> a(PermissionChecker permissionChecker, Source source, String... strArr) {
        ArrayList arrayList = new ArrayList(1);
        for (String str : strArr) {
            if (!permissionChecker.a(source.a(), str)) {
                arrayList.add(str);
            }
        }
        return arrayList;
    }

    private static List<String> a(Source source, String... strArr) {
        ArrayList arrayList = new ArrayList(1);
        for (String str : strArr) {
            if (source.a(str)) {
                arrayList.add(str);
            }
        }
        return arrayList;
    }
}
