package com.xiaomi.payment.ui.component;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import com.mibi.common.data.Image;
import com.xiaomi.payment.data.UnevenGridData;
import com.xiaomi.payment.platform.R;
import com.xiaomi.payment.ui.adapter.HomeGridAdapter;

public abstract class BaseHomeGridCommonView extends FrameLayout {

    /* renamed from: a  reason: collision with root package name */
    private boolean f12449a = false;
    /* access modifiers changed from: private */
    public UnevenGridData.SingleGridItemInfo b;
    /* access modifiers changed from: private */
    public HomeGridAdapter.GridItemClickListener c;
    private ImageView d;
    private Drawable e;
    private String f;
    private View.OnClickListener g = new View.OnClickListener() {
        public void onClick(View view) {
            if (BaseHomeGridCommonView.this.c != null) {
                BaseHomeGridCommonView.this.c.a(BaseHomeGridCommonView.this.b);
            }
        }
    };

    public BaseHomeGridCommonView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.e = context.getResources().getDrawable(R.drawable.mibi_ic_home_item_default);
        setOnClickListener(this.g);
    }

    /* access modifiers changed from: protected */
    public void onFinishInflate() {
        super.onFinishInflate();
        this.d = (ImageView) findViewById(R.id.image);
    }

    /* access modifiers changed from: protected */
    public void setImageUrl(String str) {
        if (!TextUtils.isEmpty(str)) {
            this.f12449a = true;
            this.f = str;
        }
    }

    public void bindItemInfo(UnevenGridData.SingleGridItemInfo singleGridItemInfo) {
        this.b = singleGridItemInfo;
    }

    public void setOnGridItemClickListener(HomeGridAdapter.GridItemClickListener gridItemClickListener) {
        this.c = gridItemClickListener;
    }

    /* access modifiers changed from: protected */
    public void onMeasure(int i, int i2) {
        super.onMeasure(i, i2);
        if (this.f12449a) {
            int measuredWidth = this.d.getMeasuredWidth();
            int measuredHeight = this.d.getMeasuredHeight();
            if (measuredWidth > 0 && measuredHeight > 0 && !TextUtils.isEmpty(this.f)) {
                Image.a(getContext()).a(this.f, Image.ThumbnailFormat.a(measuredWidth, measuredHeight, 1)).a(this.e).a(this.d);
            }
        }
        this.f12449a = false;
    }
}
