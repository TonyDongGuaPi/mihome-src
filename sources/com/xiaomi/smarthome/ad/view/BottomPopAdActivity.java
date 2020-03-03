package com.xiaomi.smarthome.ad.view;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import com.xiaomi.smarthome.ad.api.Advertisement;
import com.xiaomi.smarthome.framework.page.BaseActivity;

public class BottomPopAdActivity extends BaseActivity {

    /* renamed from: a  reason: collision with root package name */
    private Advertisement f13652a;

    public static void start(Activity activity, Advertisement advertisement) {
        Intent intent = new Intent(activity, BottomPopAdActivity.class);
        intent.putExtra("advertisement", advertisement);
        activity.startActivity(intent);
    }

    /* access modifiers changed from: protected */
    public void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
        this.f13652a = (Advertisement) getIntent().getParcelableExtra("advertisement");
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        super.onResume();
    }

    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}
