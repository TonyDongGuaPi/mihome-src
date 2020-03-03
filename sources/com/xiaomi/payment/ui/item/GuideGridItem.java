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
import com.xiaomi.payment.task.rxjava.RxHomePageGridTask;

public class GuideGridItem extends LinearLayout {
    /* access modifiers changed from: private */

    /* renamed from: a  reason: collision with root package name */
    public ImageView f12524a;
    private TextView b;
    private TextView c;
    private RxHomePageGridTask.Result.GuideItemType d;
    private Image.ThumbnailFormat e;
    private Drawable f;

    public GuideGridItem(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        a();
    }

    private void a() {
        this.e = Image.ThumbnailFormat.b(getResources().getDimensionPixelSize(R.dimen.mibi_guide_grid_item_image_width), 2);
        this.f = getResources().getDrawable(R.drawable.mibi_guide_default);
    }

    public void bind() {
        this.f12524a = (ImageView) findViewById(R.id.image);
        this.b = (TextView) findViewById(R.id.title);
        this.c = (TextView) findViewById(R.id.summary);
    }

    public void rebind(RxHomePageGridTask.Result.GuideItemType guideItemType) {
        this.d = guideItemType;
        this.b.setText(guideItemType.b);
        this.c.setText(guideItemType.c);
        if (TextUtils.isEmpty(guideItemType.f12423a)) {
            this.f12524a.setImageDrawable(this.f);
        } else {
            Image.a(getContext()).a(guideItemType.f12423a, this.e).a(this.f).a((Image.LoadProcessCallBack) new Image.LoadProcessCallBack() {
                public void a() {
                }

                public void a(String str) {
                }

                public void a(Drawable drawable) {
                    GuideGridItem.this.f12524a.setImageDrawable(drawable);
                }
            });
        }
    }

    public RxHomePageGridTask.Result.GuideItemType getGuideItemType() {
        return this.d;
    }
}
