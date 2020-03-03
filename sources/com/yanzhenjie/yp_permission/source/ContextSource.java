package com.yanzhenjie.yp_permission.source;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import com.google.android.exoplayer2.C;
import java.lang.reflect.Method;

public class ContextSource extends Source {

    /* renamed from: a  reason: collision with root package name */
    private Context f2471a;

    public ContextSource(Context context) {
        this.f2471a = context;
    }

    public Context a() {
        return this.f2471a;
    }

    public void a(Intent intent) {
        if (this.f2471a instanceof Activity) {
            this.f2471a.startActivity(intent);
            return;
        }
        intent.setFlags(C.ENCODING_PCM_MU_LAW);
        this.f2471a.startActivity(intent);
    }

    public void a(Intent intent, int i) {
        if (this.f2471a instanceof Activity) {
            ((Activity) this.f2471a).startActivityForResult(intent, i);
            return;
        }
        intent.setFlags(C.ENCODING_PCM_MU_LAW);
        this.f2471a.startActivity(intent);
    }

    public boolean a(String str) {
        if (Build.VERSION.SDK_INT < 23) {
            return false;
        }
        if (this.f2471a instanceof Activity) {
            return ((Activity) this.f2471a).shouldShowRequestPermissionRationale(str);
        }
        PackageManager packageManager = this.f2471a.getPackageManager();
        try {
            Method method = packageManager.getClass().getMethod("shouldShowRequestPermissionRationale", new Class[]{String.class});
            if (!method.isAccessible()) {
                method.setAccessible(true);
            }
            return ((Boolean) method.invoke(packageManager, new Object[]{str})).booleanValue();
        } catch (Exception unused) {
            return false;
        }
    }
}
