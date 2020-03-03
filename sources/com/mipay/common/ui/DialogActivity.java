package com.mipay.common.ui;

import android.util.DisplayMetrics;
import android.view.WindowManager;
import com.mipay.common.base.BaseActivity;

public class DialogActivity extends BaseActivity {
    private void g() {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        ((WindowManager) getSystemService("window")).getDefaultDisplay().getMetrics(displayMetrics);
        WindowManager.LayoutParams attributes = getWindow().getAttributes();
        attributes.width = Math.min(displayMetrics.widthPixels, displayMetrics.heightPixels);
        attributes.height = -2;
        attributes.gravity = 80;
    }

    /* access modifiers changed from: protected */
    public void a() {
        super.a();
        g();
    }
}
