package com.yanzhenjie.yp_permission.runtime;

import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import com.yanzhenjie.yp_permission.Setting;
import com.yanzhenjie.yp_permission.runtime.setting.RuntimeSetting;
import com.yanzhenjie.yp_permission.source.Source;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Runtime {

    /* renamed from: a  reason: collision with root package name */
    private static final PermissionRequestFactory f2467a;
    private static List<String> b;
    private Source c;

    public interface PermissionRequestFactory {
        PermissionRequest a(Source source);
    }

    static {
        if (Build.VERSION.SDK_INT >= 23) {
            f2467a = new MRequestFactory();
        } else {
            f2467a = new LRequestFactory();
        }
    }

    public Runtime(Source source) {
        this.c = source;
    }

    public PermissionRequest a(String... strArr) {
        b(strArr);
        return f2467a.a(this.c).a(strArr);
    }

    public PermissionRequest a(String[]... strArr) {
        ArrayList arrayList = new ArrayList();
        for (String[] asList : strArr) {
            arrayList.addAll(Arrays.asList(asList));
        }
        return a((String[]) arrayList.toArray(new String[arrayList.size()]));
    }

    public Setting a() {
        return new RuntimeSetting(this.c);
    }

    private void b(String... strArr) {
        if (b == null) {
            b = a(this.c.a());
        }
        if (strArr == null || strArr.length == 0) {
            throw new IllegalArgumentException("Please enter at least one permission.");
        }
        int length = strArr.length;
        int i = 0;
        while (i < length) {
            String str = strArr[i];
            if (b.contains(str)) {
                i++;
            } else {
                throw new IllegalStateException(String.format("The permission %1$s is not registered in manifest.xml", new Object[]{str}));
            }
        }
    }

    private static List<String> a(Context context) {
        try {
            String[] strArr = context.getPackageManager().getPackageInfo(context.getPackageName(), 4096).requestedPermissions;
            if (strArr != null && strArr.length != 0) {
                return Collections.unmodifiableList(Arrays.asList(strArr));
            }
            throw new IllegalStateException("You did not register any permissions in the manifest.xml.");
        } catch (PackageManager.NameNotFoundException unused) {
            throw new AssertionError("Package name cannot be found.");
        }
    }
}
