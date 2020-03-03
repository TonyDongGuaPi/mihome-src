package com.mi.global.shop.imageselector;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import java.util.ArrayList;

public class MultiImageSelector {

    /* renamed from: a  reason: collision with root package name */
    public static final String f6908a = "select_result";
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
        activity.startActivityForResult(b(activity), i);
    }

    private Intent b(Context context) {
        Intent intent = new Intent(context, MultiImageSelectorActivity.class);
        intent.putExtra("show_camera", this.b);
        intent.putExtra("max_select_count", this.c);
        if (this.e != null) {
            intent.putStringArrayListExtra("default_list", this.e);
        }
        intent.putExtra("select_count_mode", this.d);
        return intent;
    }
}
