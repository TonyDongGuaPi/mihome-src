package com.xiaomi.smarthome.newui.widget;

import android.content.Context;
import android.content.res.Resources;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Checkable;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.xiaomi.smarthome.R;

public class IsCheckedableView extends RelativeLayout implements Checkable {

    /* renamed from: a  reason: collision with root package name */
    private TextView f20868a;
    private ImageView b;
    private View c;
    private boolean d;

    public IsCheckedableView(Context context) {
        super(context);
        a(context);
    }

    public IsCheckedableView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        a(context);
    }

    public IsCheckedableView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        a(context);
    }

    private void a(Context context) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.item_window_home_list_v2, this, true);
        this.f20868a = (TextView) inflate.findViewById(R.id.name);
        this.b = (ImageView) inflate.findViewById(R.id.icon);
        this.c = inflate.findViewById(R.id.divider_item);
    }

    public void setChecked(boolean z) {
        Resources resources;
        int i;
        this.d = z;
        this.b.setVisibility(this.d ? 0 : 4);
        TextView textView = this.f20868a;
        if (z) {
            resources = getResources();
            i = R.color.card_bg_status_on;
        } else {
            resources = getResources();
            i = R.color.black;
        }
        textView.setTextColor(resources.getColor(i));
    }

    public boolean isChecked() {
        return this.d;
    }

    public void toggle() {
        setChecked(this.d);
    }

    public void setText(String str) {
        this.f20868a.setText(str);
    }

    public void setDividerVisivle(boolean z) {
        this.c.setVisibility(z ? 0 : 8);
    }

    public void setActivated(boolean z) {
        super.setActivated(z);
    }
}
