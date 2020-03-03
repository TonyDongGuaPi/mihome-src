package com.yanzhenjie.permission;

import android.os.Handler;
import android.os.Looper;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import com.yanzhenjie.permission.PermissionActivity;
import com.yanzhenjie.permission.checker.DoubleChecker;
import com.yanzhenjie.permission.checker.PermissionChecker;
import com.yanzhenjie.permission.checker.StandardChecker;
import com.yanzhenjie.permission.source.Source;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RequiresApi(api = 23)
class MRequest implements PermissionActivity.PermissionListener, Request, RequestExecutor {

    /* renamed from: a  reason: collision with root package name */
    private static final Handler f2401a = new Handler(Looper.getMainLooper());
    private static final PermissionChecker b = new StandardChecker();
    /* access modifiers changed from: private */
    public static final PermissionChecker c = new DoubleChecker();
    /* access modifiers changed from: private */
    public Source d;
    private String[] e;
    private Rationale f;
    private Action g;
    private Action h;
    private String[] i;

    MRequest(Source source) {
        this.d = source;
    }

    @NonNull
    public Request a(String... strArr) {
        this.e = strArr;
        return this;
    }

    @NonNull
    public Request a(String[]... strArr) {
        ArrayList arrayList = new ArrayList();
        for (String[] asList : strArr) {
            arrayList.addAll(Arrays.asList(asList));
        }
        this.e = (String[]) arrayList.toArray(new String[arrayList.size()]);
        return this;
    }

    @NonNull
    public Request a(Rationale rationale) {
        this.f = rationale;
        return this;
    }

    @NonNull
    public Request a(Action action) {
        this.g = action;
        return this;
    }

    @NonNull
    public Request b(Action action) {
        this.h = action;
        return this;
    }

    public void a() {
        if (this.d.a().getApplicationInfo().targetSdkVersion < 23) {
            a(b(b, this.d, this.e));
            return;
        }
        List<String> b2 = b(b, this.d, this.e);
        this.i = (String[]) b2.toArray(new String[b2.size()]);
        if (this.i.length > 0) {
            List<String> a2 = a(this.d, this.i);
            if (a2.size() <= 0 || this.f == null) {
                b();
            } else {
                this.f.a(this.d.a(), a2, this);
            }
        } else {
            e();
        }
    }

    @RequiresApi(api = 23)
    public void b() {
        PermissionActivity.requestPermission(this.d.a(), this.i, this);
    }

    public void c() {
        b(this.i);
    }

    public void b(@NonNull final String[] strArr) {
        f2401a.postDelayed(new Runnable() {
            public void run() {
                List a2 = MRequest.b(MRequest.c, MRequest.this.d, strArr);
                if (a2.isEmpty()) {
                    MRequest.this.e();
                } else {
                    MRequest.this.a((List<String>) a2);
                }
            }
        }, 250);
    }

    /* access modifiers changed from: private */
    public void e() {
        if (this.g != null) {
            List asList = Arrays.asList(this.e);
            try {
                this.g.onAction(asList);
            } catch (Exception unused) {
                if (this.h != null) {
                    this.h.onAction(asList);
                }
            }
        }
    }

    /* access modifiers changed from: private */
    public void a(@NonNull List<String> list) {
        if (this.h != null) {
            this.h.onAction(list);
        }
    }

    /* access modifiers changed from: private */
    public static List<String> b(PermissionChecker permissionChecker, @NonNull Source source, @NonNull String... strArr) {
        ArrayList arrayList = new ArrayList(1);
        for (String str : strArr) {
            if (!permissionChecker.a(source.a(), str)) {
                arrayList.add(str);
            }
        }
        return arrayList;
    }

    private static List<String> a(@NonNull Source source, @NonNull String... strArr) {
        ArrayList arrayList = new ArrayList(1);
        for (String str : strArr) {
            if (source.a(str)) {
                arrayList.add(str);
            }
        }
        return arrayList;
    }
}
