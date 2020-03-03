package com.xiaomi.smarthome.framework.login.ui;

import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.widget.RelativeLayout;
import com.facebook.drawee.drawable.ScalingUtils;
import com.facebook.drawee.generic.GenericDraweeHierarchyBuilder;
import com.facebook.drawee.view.SimpleDraweeView;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.framework.page.BaseActivity;
import com.xiaomi.smarthome.library.common.util.CommonUtils;

public class ImagePreviewActivity extends BaseActivity {

    /* renamed from: a  reason: collision with root package name */
    private SimpleDraweeView f16580a;
    private String b;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.b = getIntent().getStringExtra("url");
        a();
    }

    private void a() {
        setContentView(R.layout.image_preview_activity);
        this.f16580a = (SimpleDraweeView) findViewById(R.id.image);
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        int i = displayMetrics.widthPixels;
        RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) this.f16580a.getLayoutParams();
        if (layoutParams != null) {
            layoutParams.width = i;
            layoutParams.height = i;
        }
        this.f16580a.setLayoutParams(layoutParams);
        this.f16580a.setHierarchy(new GenericDraweeHierarchyBuilder(this.f16580a.getResources()).setFadeDuration(200).setActualImageScaleType(ScalingUtils.ScaleType.FIT_XY).build());
        if (TextUtils.isEmpty(this.b)) {
            this.f16580a.setImageURI(CommonUtils.c((int) R.drawable.user_default));
        } else {
            this.f16580a.setImageURI(Uri.parse(this.b));
        }
    }
}
