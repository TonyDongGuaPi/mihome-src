package com.wx.wheelview.widget;

import android.app.AlertDialog;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.libra.Color;
import com.wx.wheelview.adapter.ArrayWheelAdapter;
import com.wx.wheelview.common.WheelConstants;
import com.wx.wheelview.util.WheelUtils;
import com.wx.wheelview.widget.WheelView;
import java.util.Arrays;
import java.util.List;

public class WheelViewDialog implements View.OnClickListener {

    /* renamed from: a  reason: collision with root package name */
    private TextView f9888a;
    private View b;
    private View c;
    private WheelView<String> d;
    private WheelView.WheelViewStyle e;
    private TextView f;
    private AlertDialog g;
    private Context h;
    private OnDialogItemClickListener i;
    /* access modifiers changed from: private */
    public int j;
    /* access modifiers changed from: private */
    public String k;

    public interface OnDialogItemClickListener {
        void a(int i, String str);
    }

    public WheelViewDialog(Context context) {
        this.h = context;
        c();
    }

    private void c() {
        LinearLayout linearLayout = new LinearLayout(this.h);
        linearLayout.setOrientation(1);
        linearLayout.setPadding(WheelUtils.a(this.h, 20.0f), 0, WheelUtils.a(this.h, 20.0f), 0);
        this.f9888a = new TextView(this.h);
        this.f9888a.setTextColor(WheelConstants.t);
        this.f9888a.setTextSize(2, 16.0f);
        this.f9888a.setGravity(17);
        linearLayout.addView(this.f9888a, new LinearLayout.LayoutParams(-1, WheelUtils.a(this.h, 50.0f)));
        this.b = new View(this.h);
        this.b.setBackgroundColor(WheelConstants.t);
        linearLayout.addView(this.b, new LinearLayout.LayoutParams(-1, WheelUtils.a(this.h, 2.0f)));
        this.d = new WheelView<>(this.h);
        this.d.setSkin(WheelView.Skin.Holo);
        this.d.setWheelAdapter(new ArrayWheelAdapter(this.h));
        this.e = new WheelView.WheelViewStyle();
        this.e.c = Color.c;
        this.e.h = 1.2f;
        this.d.setStyle(this.e);
        this.d.setOnWheelItemSelectedListener(new WheelView.OnWheelItemSelectedListener<String>() {
            /* renamed from: a */
            public void onItemSelected(int i, String str) {
                int unused = WheelViewDialog.this.j = i;
                String unused2 = WheelViewDialog.this.k = str;
            }
        });
        linearLayout.addView(this.d, new ViewGroup.MarginLayoutParams(-1, -2));
        this.c = new View(this.h);
        this.c.setBackgroundColor(WheelConstants.t);
        linearLayout.addView(this.c, new LinearLayout.LayoutParams(-1, WheelUtils.a(this.h, 1.0f)));
        this.f = new TextView(this.h);
        this.f.setTextColor(WheelConstants.t);
        this.f.setTextSize(2, 12.0f);
        this.f.setGravity(17);
        this.f.setClickable(true);
        this.f.setOnClickListener(this);
        this.f.setText("OK");
        linearLayout.addView(this.f, new LinearLayout.LayoutParams(-1, WheelUtils.a(this.h, 45.0f)));
        this.g = new AlertDialog.Builder(this.h).create();
        this.g.setView(linearLayout);
        this.g.setCanceledOnTouchOutside(false);
    }

    public WheelViewDialog a(OnDialogItemClickListener onDialogItemClickListener) {
        this.i = onDialogItemClickListener;
        return this;
    }

    public WheelViewDialog a(int i2) {
        this.f9888a.setTextColor(i2);
        this.b.setBackgroundColor(i2);
        this.c.setBackgroundColor(i2);
        this.f.setTextColor(i2);
        this.e.d = i2;
        this.e.b = i2;
        return this;
    }

    public WheelViewDialog a(String str) {
        this.f9888a.setText(str);
        return this;
    }

    public WheelViewDialog b(int i2) {
        this.f9888a.setTextColor(i2);
        return this;
    }

    public WheelViewDialog c(int i2) {
        this.f9888a.setTextSize((float) i2);
        return this;
    }

    public WheelViewDialog b(String str) {
        this.f.setText(str);
        return this;
    }

    public WheelViewDialog d(int i2) {
        this.f.setTextColor(i2);
        return this;
    }

    public WheelViewDialog e(int i2) {
        this.f.setTextSize((float) i2);
        return this;
    }

    public WheelViewDialog f(int i2) {
        this.d.setWheelSize(i2);
        return this;
    }

    public WheelViewDialog a(boolean z) {
        this.d.setLoop(z);
        return this;
    }

    public WheelViewDialog g(int i2) {
        this.d.setSelection(i2);
        return this;
    }

    public WheelViewDialog a(String[] strArr) {
        this.d.setWheelData(Arrays.asList(strArr));
        return this;
    }

    public WheelViewDialog a(List<String> list) {
        this.d.setWheelData(list);
        return this;
    }

    public WheelViewDialog a() {
        if (!this.g.isShowing()) {
            this.g.show();
        }
        return this;
    }

    public WheelViewDialog b() {
        if (this.g.isShowing()) {
            this.g.dismiss();
        }
        return this;
    }

    public void onClick(View view) {
        b();
        if (this.i != null) {
            this.i.a(this.j, this.k);
        }
    }
}
