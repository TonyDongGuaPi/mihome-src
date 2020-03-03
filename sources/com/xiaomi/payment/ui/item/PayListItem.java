package com.xiaomi.payment.ui.item;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.widget.Checkable;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.mibi.common.data.Image;
import com.xiaomi.payment.platform.R;
import com.xiaomi.payment.recharge.RechargeType;

public class PayListItem extends LinearLayout implements Checkable {

    /* renamed from: a  reason: collision with root package name */
    private static final int[] f12528a = {16842912};
    private ImageView b;
    private TextView c;
    private TextView d;
    private RechargeType e;
    private Image.ThumbnailFormat f;
    private Drawable g;
    private boolean h;

    public PayListItem(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        a();
    }

    private void a() {
        this.f = Image.ThumbnailFormat.b(getResources().getDimensionPixelSize(R.dimen.mibi_recharge_grid_item_icon_width), 1);
        this.g = getResources().getDrawable(R.drawable.mibi_ic_recharge_item_default);
    }

    public void bind() {
        this.b = (ImageView) findViewById(R.id.icon);
        this.c = (TextView) findViewById(R.id.label);
        this.d = (TextView) findViewById(R.id.sub_label);
    }

    public void rebind(RechargeType rechargeType) {
        this.e = rechargeType;
        if (rechargeType != null) {
            this.c.setText(rechargeType.mTitle);
            if (!TextUtils.isEmpty(rechargeType.mTitleHint)) {
                this.d.setVisibility(0);
                this.d.setText(rechargeType.mTitleHint);
            }
            if (!TextUtils.isEmpty(rechargeType.mIcon)) {
                Image.a(getContext()).a(rechargeType.mIcon, this.f).a(this.g).a(this.b);
            } else if (rechargeType.mLocalIconRes != -1) {
                this.b.setImageDrawable(getResources().getDrawable(rechargeType.mLocalIconRes));
            } else {
                this.b.setImageDrawable(this.g);
            }
        } else {
            throw new IllegalArgumentException("mRechargeType in PayType should not be null");
        }
    }

    public RechargeType getPayType() {
        return this.e;
    }

    public void setChecked(boolean z) {
        if (this.h != z) {
            this.h = z;
            refreshDrawableState();
        }
    }

    public boolean isChecked() {
        return this.h;
    }

    public void toggle() {
        setChecked(!this.h);
    }

    /* access modifiers changed from: protected */
    public int[] onCreateDrawableState(int i) {
        if (!this.h) {
            return super.onCreateDrawableState(i);
        }
        int[] onCreateDrawableState = super.onCreateDrawableState(i + 1);
        mergeDrawableStates(onCreateDrawableState, f12528a);
        return onCreateDrawableState;
    }
}
