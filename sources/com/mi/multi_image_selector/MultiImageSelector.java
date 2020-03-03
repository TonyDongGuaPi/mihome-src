package com.mi.multi_image_selector;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.widget.Toast;
import java.util.ArrayList;

public class MultiImageSelector {

    /* renamed from: a  reason: collision with root package name */
    public static final String f7377a = "select_result";
    private static MultiImageSelector f;
    private boolean b = true;
    private int c = 9;
    private int d = 1;
    private ArrayList<String> e;

    @Deprecated
    private MultiImageSelector(Context context) {
    }

    private MultiImageSelector() {
    }

    @Deprecated
    public static MultiImageSelector a(Context context) {
        if (f == null) {
            f = new MultiImageSelector(context);
        }
        return f;
    }

    public static MultiImageSelector a() {
        if (f == null) {
            f = new MultiImageSelector();
        }
        return f;
    }

    public MultiImageSelector a(boolean z) {
        this.b = z;
        return f;
    }

    public MultiImageSelector a(int i) {
        this.c = i;
        return f;
    }

    public MultiImageSelector b() {
        this.d = 0;
        return f;
    }

    public MultiImageSelector c() {
        this.d = 1;
        return f;
    }

    public MultiImageSelector a(ArrayList<String> arrayList) {
        this.e = arrayList;
        return f;
    }

    public void a(Activity activity, int i) {
        a(activity, i, false);
    }

    public void a(Activity activity, int i, boolean z) {
        if (b(activity)) {
            activity.startActivityForResult(a((Context) activity, z), i);
        } else {
            Toast.makeText(activity, R.string.mis_error_no_permission, 0).show();
        }
    }

    public void a(Fragment fragment, int i) {
        a(fragment, i, false);
    }

    public void a(Fragment fragment, int i, boolean z) {
        Context context = fragment.getContext();
        if (b(context)) {
            fragment.startActivityForResult(a(context, z), i);
        } else {
            Toast.makeText(context, R.string.mis_error_no_permission, 0).show();
        }
    }

    private boolean b(Context context) {
        if (Build.VERSION.SDK_INT < 16 || ContextCompat.checkSelfPermission(context, "android.permission.READ_EXTERNAL_STORAGE") == 0) {
            return true;
        }
        return false;
    }

    private Intent c(Context context) {
        return a(context, false);
    }

    private Intent a(Context context, boolean z) {
        Intent intent = new Intent(context, MultiImageSelectorActivity.class);
        intent.putExtra("show_camera", this.b);
        intent.putExtra("max_select_count", this.c);
        intent.putExtra("show_full_upload", z);
        if (this.e != null) {
            intent.putStringArrayListExtra("default_list", this.e);
        }
        intent.putExtra("select_count_mode", this.d);
        return intent;
    }
}
