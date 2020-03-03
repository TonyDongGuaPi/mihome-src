package com.xiaomi.smarthome.ad.view;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.PopupWindow;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.ad.api.Advertisement;
import com.xiaomi.smarthome.frame.FrameManager;
import com.xiaomi.smarthome.framework.page.BaseActivity;

public class PopAdActivity extends BaseActivity {

    /* renamed from: a  reason: collision with root package name */
    private PopAdView f13656a;
    private View b;
    /* access modifiers changed from: private */
    public Advertisement c;

    public void onBackPressed() {
    }

    public static void start(Activity activity, Advertisement advertisement) {
        Intent intent = new Intent(activity, PopAdActivity.class);
        intent.putExtra("advertisement", advertisement);
        activity.startActivity(intent);
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_ad_pop);
        this.c = (Advertisement) getIntent().getParcelableExtra("advertisement");
        this.f13656a = new PopAdView(this);
        this.f13656a.a(this.c);
        this.f13656a.a((View.OnClickListener) new View.OnClickListener() {
            public void onClick(View view) {
                FrameManager.b().k().loadWebView(PopAdActivity.this.c.f(), "");
                PluginAdUtil.a();
                PopAdActivity.this.finish();
            }
        });
        this.f13656a.setOnDismissListener(new PopupWindow.OnDismissListener() {
            public void onDismiss() {
                PopAdActivity.this.finish();
            }
        });
        this.b = findViewById(R.id.float_ad_ancher);
    }

    public void onWindowFocusChanged(boolean z) {
        super.onWindowFocusChanged(z);
        if (z && this.f13656a != null && !this.f13656a.isShowing()) {
            this.f13656a.showAtLocation(this.b, 17, 0, 0);
            PluginAdUtil.a(this.c);
        }
    }

    public void onResume() {
        overridePendingTransition(R.anim.float_ad_enter, R.anim.float_ad_exist);
        super.onResume();
    }

    public void onPause() {
        overridePendingTransition(R.anim.float_ad_enter, R.anim.float_ad_exist);
        super.onPause();
    }
}
