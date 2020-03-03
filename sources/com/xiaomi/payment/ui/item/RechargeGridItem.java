package com.xiaomi.payment.ui.item;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.mibi.common.data.Image;
import com.xiaomi.payment.platform.R;
import com.xiaomi.payment.recharge.RechargeType;

public class RechargeGridItem extends LinearLayout {

    /* renamed from: a  reason: collision with root package name */
    private ImageView f12529a;
    private TextView b;
    private TextView c;
    private RechargeType d;
    private Image.ThumbnailFormat e;
    private Drawable f;

    public RechargeGridItem(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        a();
    }

    private void a() {
        this.e = Image.ThumbnailFormat.b(getResources().getDimensionPixelSize(R.dimen.mibi_recharge_grid_item_icon_width), 1);
        this.f = getResources().getDrawable(R.drawable.mibi_ic_recharge_item_default);
    }

    public void bind() {
        this.f12529a = (ImageView) findViewById(R.id.icon);
        this.b = (TextView) findViewById(R.id.label);
        this.c = (TextView) findViewById(R.id.sub_label);
    }

    public void rebind(RechargeType rechargeType) {
        this.d = rechargeType;
        this.b.setText(rechargeType.mTitle);
        if (!TextUtils.isEmpty(rechargeType.mTitleHint)) {
            this.c.setVisibility(0);
            this.c.setText(rechargeType.mTitleHint);
        }
        if (!TextUtils.isEmpty(rechargeType.mIcon)) {
            Image.a(getContext()).a(rechargeType.mIcon, this.e).a(this.f).a(this.f12529a);
        } else if (rechargeType.mLocalIconRes != -1) {
            this.f12529a.setImageDrawable(getResources().getDrawable(rechargeType.mLocalIconRes));
        } else {
            this.f12529a.setImageDrawable(this.f);
        }
    }

    public RechargeType getRechargeType() {
        return this.d;
    }
}
