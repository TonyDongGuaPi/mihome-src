package com.xiaomi.smarthome.newui.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Checkable;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.xiaomi.smarthome.R;

public class ChangeHomeListItemView extends RelativeLayout implements Checkable {

    /* renamed from: a  reason: collision with root package name */
    private TextView f20824a;
    private TextView b;
    private ImageView c;
    private View d;
    private boolean e;

    public ChangeHomeListItemView(Context context) {
        super(context);
        a(context);
    }

    public ChangeHomeListItemView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        a(context);
    }

    public ChangeHomeListItemView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        a(context);
    }

    private void a(Context context) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.item_change_home_list, this, true);
        this.f20824a = (TextView) inflate.findViewById(R.id.name);
        this.b = (TextView) inflate.findViewById(R.id.subtitle);
        this.c = (ImageView) inflate.findViewById(R.id.icon);
        this.d = inflate.findViewById(R.id.divider_item);
    }

    public void setChecked(boolean z) {
        this.e = z;
        this.c.setVisibility(this.e ? 0 : 4);
    }

    public boolean isChecked() {
        return this.e;
    }

    public void toggle() {
        setChecked(this.e);
    }

    public void setText(String str) {
        this.f20824a.setText(str);
    }

    public void setSubText(String str) {
        this.b.setText(str);
    }

    public void setDividerVisivle(boolean z) {
        this.d.setVisibility(z ? 0 : 8);
    }

    public void setActivated(boolean z) {
        super.setActivated(z);
    }
}
