package com.yanzhenjie.permission;

import android.support.annotation.NonNull;
import com.yanzhenjie.permission.checker.PermissionChecker;
import com.yanzhenjie.permission.checker.StrictChecker;
import com.yanzhenjie.permission.source.Source;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class LRequest implements Request {

    /* renamed from: a  reason: collision with root package name */
    private static final PermissionChecker f2400a = new StrictChecker();
    private Source b;
    private String[] c;
    private Action d;
    private Action e;

    @NonNull
    public Request a(Rationale rationale) {
        return this;
    }

    LRequest(Source source) {
        this.b = source;
    }

    @NonNull
    public Request a(String... strArr) {
        this.c = strArr;
        return this;
    }

    @NonNull
    public Request a(String[]... strArr) {
        ArrayList arrayList = new ArrayList();
        for (String[] asList : strArr) {
            arrayList.addAll(Arrays.asList(asList));
        }
        this.c = (String[]) arrayList.toArray(new String[arrayList.size()]);
        return this;
    }

    @NonNull
    public Request a(Action action) {
        this.d = action;
        return this;
    }

    @NonNull
    public Request b(Action action) {
        this.e = action;
        return this;
    }

    public void a() {
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
                this.d.onAction(asList);
            } catch (Exception unused) {
                if (this.e != null) {
                    this.e.onAction(asList);
                }
            }
        }
    }

    private void a(@NonNull List<String> list) {
        if (this.e != null) {
            this.e.onAction(list);
        }
    }

    private static List<String> a(@NonNull Source source, @NonNull String... strArr) {
        ArrayList arrayList = new ArrayList(1);
        for (String str : strArr) {
            if (!f2400a.a(source.a(), str)) {
                arrayList.add(str);
            }
        }
        return arrayList;
    }
}
