package com.yanzhenjie.permission;

import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import com.yanzhenjie.permission.setting.PermissionSetting;
import com.yanzhenjie.permission.source.AppActivitySource;
import com.yanzhenjie.permission.source.ContextSource;
import com.yanzhenjie.permission.source.FragmentSource;
import com.yanzhenjie.permission.source.Source;
import com.yanzhenjie.permission.source.SupportFragmentSource;
import java.util.List;

public class AndPermission {

    /* renamed from: a  reason: collision with root package name */
    private static final RequestFactory f2398a;

    private interface RequestFactory {
        Request a(Source source);
    }

    static {
        if (Build.VERSION.SDK_INT >= 23) {
            f2398a = new MRequestFactory();
        } else {
            f2398a = new LRequestFactory();
        }
    }

    public static boolean a(@NonNull Activity activity, @NonNull List<String> list) {
        return a((Source) new AppActivitySource(activity), list);
    }

    public static boolean a(@NonNull Fragment fragment, @NonNull List<String> list) {
        return a((Source) new SupportFragmentSource(fragment), list);
    }

    public static boolean a(@NonNull android.app.Fragment fragment, @NonNull List<String> list) {
        return a((Source) new FragmentSource(fragment), list);
    }

    public static boolean a(@NonNull Context context, @NonNull List<String> list) {
        return a((Source) new ContextSource(context), list);
    }

    private static boolean a(@NonNull Source source, @NonNull List<String> list) {
        for (String a2 : list) {
            if (!source.a(a2)) {
                return true;
            }
        }
        return false;
    }

    public static boolean a(@NonNull Activity activity, @NonNull String... strArr) {
        return a((Source) new AppActivitySource(activity), strArr);
    }

    public static boolean a(@NonNull Fragment fragment, @NonNull String... strArr) {
        return a((Source) new SupportFragmentSource(fragment), strArr);
    }

    public static boolean a(@NonNull android.app.Fragment fragment, @NonNull String... strArr) {
        return a((Source) new FragmentSource(fragment), strArr);
    }

    public static boolean a(@NonNull Context context, @NonNull String... strArr) {
        return a((Source) new ContextSource(context), strArr);
    }

    private static boolean a(@NonNull Source source, @NonNull String... strArr) {
        for (String a2 : strArr) {
            if (!source.a(a2)) {
                return true;
            }
        }
        return false;
    }

    @NonNull
    public static SettingService a(@NonNull Activity activity) {
        return new PermissionSetting(new AppActivitySource(activity));
    }

    @NonNull
    public static SettingService a(@NonNull Fragment fragment) {
        return new PermissionSetting(new SupportFragmentSource(fragment));
    }

    @NonNull
    public static SettingService a(@NonNull android.app.Fragment fragment) {
        return new PermissionSetting(new FragmentSource(fragment));
    }

    @NonNull
    public static SettingService a(@NonNull Context context) {
        return new PermissionSetting(new ContextSource(context));
    }

    @NonNull
    public static Request b(@NonNull Activity activity) {
        return f2398a.a(new AppActivitySource(activity));
    }

    @NonNull
    public static Request b(@NonNull Fragment fragment) {
        return f2398a.a(new SupportFragmentSource(fragment));
    }

    @NonNull
    public static Request b(@NonNull android.app.Fragment fragment) {
        return f2398a.a(new FragmentSource(fragment));
    }

    @NonNull
    public static Request b(@NonNull Context context) {
        return f2398a.a(new ContextSource(context));
    }

    private AndPermission() {
    }

    private static class LRequestFactory implements RequestFactory {
        private LRequestFactory() {
        }

        public Request a(Source source) {
            return new LRequest(source);
        }
    }

    @RequiresApi(api = 23)
    private static class MRequestFactory implements RequestFactory {
        private MRequestFactory() {
        }

        public Request a(Source source) {
            return new MRequest(source);
        }
    }
}
