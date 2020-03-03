package com.xiaomi.smarthome.notificationquickop.views;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.notificationquickop.NotiQuickOpManager;
import java.util.List;

public class QuickOpView extends LinearLayout {

    /* renamed from: a  reason: collision with root package name */
    private LayoutInflater f20991a = null;
    private View b;
    private View c;
    private View d;
    private LoopBannerView e;

    public QuickOpView(Context context) {
        super(context);
        a();
    }

    public QuickOpView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        a();
    }

    public QuickOpView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        a();
    }

    private void a() {
        this.f20991a = LayoutInflater.from(getContext());
        this.f20991a.inflate(R.layout.notification_quick_op_view, this);
        this.b = findViewById(R.id.left_btn);
        this.b.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
            }
        });
        this.c = findViewById(R.id.right_btn);
        this.c.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
            }
        });
        this.d = findViewById(R.id.power_btn);
        this.d.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
            }
        });
    }

    public void update(List<NotiQuickOpManager.QuickOpItem> list, int i) {
        this.e.update(list, i);
    }
}
