package com.xiaomi.smarthome.ad.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.ad.api.AdPosition;
import com.xiaomi.smarthome.ad.api.Advertisement;
import com.xiaomi.smarthome.frame.FrameManager;

public class NoticeAdView extends FrameLayout {
    /* access modifiers changed from: private */

    /* renamed from: a  reason: collision with root package name */
    public Advertisement f13653a;

    public NoticeAdView(Context context) {
        super(context);
        a();
    }

    private void a() {
        LayoutInflater.from(getContext().getApplicationContext()).inflate(R.layout.view_ad_notice, this, true);
        setLayoutParams(new FrameLayout.LayoutParams(-1, -2));
    }

    public void setNotice(AdPosition adPosition) {
        this.f13653a = PluginAdUtil.a(adPosition);
        ((TextView) findViewById(R.id.title)).setText(this.f13653a.g());
        findViewById(R.id.title).requestFocus();
        setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                FrameManager.b().k().loadWebView(NoticeAdView.this.f13653a.f(), "");
                PluginAdUtil.a();
            }
        });
        findViewById(R.id.ad_notice_close).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                ViewGroup viewGroup = (ViewGroup) NoticeAdView.this.getParent();
                if (viewGroup != null) {
                    viewGroup.removeView(NoticeAdView.this);
                }
                PluginAdUtil.b(NoticeAdView.this.f13653a);
            }
        });
        PluginAdUtil.a(this.f13653a);
    }

    /* access modifiers changed from: protected */
    public void onWindowVisibilityChanged(int i) {
        super.onWindowVisibilityChanged(i);
    }
}
