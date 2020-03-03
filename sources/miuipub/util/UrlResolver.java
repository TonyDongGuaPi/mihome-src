package miuipub.util;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.os.Binder;
import com.miuipub.internal.util.UrlResolverHelper;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class UrlResolver {

    /* renamed from: a  reason: collision with root package name */
    private static Method f3024a;

    private UrlResolver() {
    }

    public static ResolveInfo a(Context context, PackageManager packageManager, Intent intent) {
        return a(context, true, packageManager, intent, (String) null, 0, (List<ResolveInfo>) null, 0);
    }

    private static ResolveInfo a(Context context, boolean z, PackageManager packageManager, Intent intent, String str, int i, List<ResolveInfo> list, int i2) {
        Uri data;
        String host;
        boolean z2 = false;
        if (z) {
            list = packageManager.queryIntentActivities(intent, 0);
        }
        ArrayList arrayList = new ArrayList();
        int i3 = -1;
        for (int i4 = 0; i4 < list.size(); i4++) {
            ResolveInfo resolveInfo = list.get(i4);
            if (resolveInfo.activityInfo.packageName.equals("com.android.browser")) {
                i3 = i4;
            } else if (UrlResolverHelper.c(resolveInfo.activityInfo.packageName)) {
                arrayList.add(resolveInfo);
            } else {
                try {
                    long clearCallingIdentity = Binder.clearCallingIdentity();
                    PackageInfo packageInfo = packageManager.getPackageInfo(resolveInfo.activityInfo.packageName, 0);
                    Binder.restoreCallingIdentity(clearCallingIdentity);
                    if (!(packageInfo.applicationInfo == null || (1 & packageInfo.applicationInfo.flags) == 0)) {
                        arrayList.add(resolveInfo);
                    }
                } catch (PackageManager.NameNotFoundException unused) {
                }
            }
        }
        if (arrayList.size() == 1) {
            return (ResolveInfo) arrayList.get(0);
        }
        if (arrayList.size() > 1 || (data = intent.getData()) == null || (host = data.getHost()) == null || !UrlResolverHelper.b(host)) {
            return null;
        }
        String a2 = UrlResolverHelper.a(data);
        if (a2 != null) {
            Uri parse = Uri.parse(a2);
            if (UrlResolverHelper.d(parse.getScheme())) {
                parse = UrlResolverHelper.e(a2);
                z2 = true;
            }
            intent.setData(parse);
            if (!z) {
                return null;
            }
            if (!z2) {
                return a(context, packageManager, intent);
            }
            context.startActivity(intent);
            return new ResolveInfo();
        } else if (i3 == -1 || z) {
            return null;
        } else {
            return list.get(i3);
        }
    }
}
