package com.yanzhenjie.yp_permission;

import android.content.Context;
import android.net.Uri;
import android.os.Build;
import android.support.v4.app.Fragment;
import com.yanzhenjie.yp_permission.checker.PermissionChecker;
import com.yanzhenjie.yp_permission.checker.StandardChecker;
import com.yanzhenjie.yp_permission.source.ContextSource;
import com.yanzhenjie.yp_permission.source.FragmentSource;
import com.yanzhenjie.yp_permission.source.Source;
import com.yanzhenjie.yp_permission.source.SupportFragmentSource;
import java.io.File;
import java.util.List;

public class AndPermission {

    /* renamed from: a  reason: collision with root package name */
    private static final PermissionChecker f2427a = new StandardChecker();

    public static Options a(Fragment fragment) {
        return new Options(new SupportFragmentSource(fragment));
    }

    public static Options a(android.app.Fragment fragment) {
        return new Options(new FragmentSource(fragment));
    }

    public static Options a(Context context) {
        return new Options(new ContextSource(context));
    }

    public static boolean a(Fragment fragment, List<String> list) {
        return a((Source) new SupportFragmentSource(fragment), list);
    }

    public static boolean a(android.app.Fragment fragment, List<String> list) {
        return a((Source) new FragmentSource(fragment), list);
    }

    public static boolean a(Context context, List<String> list) {
        return a((Source) new ContextSource(context), list);
    }

    private static boolean a(Source source, List<String> list) {
        for (String a2 : list) {
            if (!source.a(a2)) {
                return true;
            }
        }
        return false;
    }

    public static boolean a(Fragment fragment, String... strArr) {
        return a((Source) new SupportFragmentSource(fragment), strArr);
    }

    public static boolean a(android.app.Fragment fragment, String... strArr) {
        return a((Source) new FragmentSource(fragment), strArr);
    }

    public static boolean a(Context context, String... strArr) {
        return a((Source) new ContextSource(context), strArr);
    }

    private static boolean a(Source source, String... strArr) {
        for (String a2 : strArr) {
            if (!source.a(a2)) {
                return true;
            }
        }
        return false;
    }

    @Deprecated
    public static Setting b(Fragment fragment) {
        return a(fragment).a().a();
    }

    @Deprecated
    public static Setting b(android.app.Fragment fragment) {
        return a(fragment).a().a();
    }

    @Deprecated
    public static Setting b(Context context) {
        return a(context).a().a();
    }

    public static boolean b(Fragment fragment, String... strArr) {
        return b(fragment.getContext(), strArr);
    }

    public static boolean b(android.app.Fragment fragment, String... strArr) {
        return b((Context) fragment.getActivity(), strArr);
    }

    public static boolean b(Context context, String... strArr) {
        return f2427a.a(context, strArr);
    }

    public static boolean a(Fragment fragment, String[]... strArr) {
        return a(fragment.getContext(), strArr);
    }

    public static boolean a(android.app.Fragment fragment, String[]... strArr) {
        return a((Context) fragment.getActivity(), strArr);
    }

    public static boolean a(Context context, String[]... strArr) {
        for (String[] a2 : strArr) {
            if (!f2427a.a(context, a2)) {
                return false;
            }
        }
        return true;
    }

    public static Uri a(Fragment fragment, File file) {
        return a(fragment.getContext(), file);
    }

    public static Uri a(android.app.Fragment fragment, File file) {
        return a((Context) fragment.getActivity(), file);
    }

    public static Uri a(Context context, File file) {
        if (Build.VERSION.SDK_INT < 24) {
            return Uri.fromFile(file);
        }
        return FileProvider.getUriForFile(context, context.getPackageName() + ".file.path.share", file);
    }

    private AndPermission() {
    }
}
