package com.xiaomi.jr.permission;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Looper;
import com.google.android.exoplayer2.C;
import com.xiaomi.jr.common.lifecycle.LifecycledObjects;
import java.util.List;

public class Request {
    private static Handler e = new Handler(Looper.getMainLooper());

    /* renamed from: a  reason: collision with root package name */
    private Context f10997a;
    private Callback b;
    private List<String> c;
    private PermissionDialogDelegate d;

    public interface Callback {

        /* renamed from: com.xiaomi.jr.permission.Request$Callback$-CC  reason: invalid class name */
        public final /* synthetic */ class CC {
            public static void $default$b(Callback callback) {
            }
        }

        void a();

        void a(String str);

        void b();
    }

    private Request() {
    }

    public static Request a(Context context) {
        Request request = new Request();
        request.f10997a = context;
        return request;
    }

    public Request a(List<String> list) {
        this.c = list;
        return this;
    }

    public Request a(Callback callback) {
        this.b = callback;
        return this;
    }

    public Request a(PermissionDialogDelegate permissionDialogDelegate) {
        this.d = permissionDialogDelegate;
        return this;
    }

    public Callback a() {
        return this.b;
    }

    public PermissionDialogDelegate b() {
        return this.d;
    }

    public void c() {
        if (!this.c.isEmpty()) {
            LifecycledObjects.a(this, this.f10997a);
            Intent intent = new Intent();
            intent.setClass(this.f10997a, PermissionActivity.class);
            intent.putExtra("permissions", (String[]) this.c.toArray(new String[this.c.size()]));
            intent.putExtra(PermissionActivity.KEY_REQUEST_OBJECT_ID, hashCode());
            if (!(this.f10997a instanceof Activity)) {
                intent.addFlags(C.ENCODING_PCM_MU_LAW);
            }
            this.f10997a.startActivity(intent);
        } else if (this.b != null) {
            e.post(new Runnable() {
                public final void run() {
                    Request.this.d();
                }
            });
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void d() {
        this.b.a();
    }
}
