package com.xiaomi.smarthome.library.common.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.ImageView;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.library.common.widget.ExpandableItemIndicator;

class ExpandableItemIndicatorImplImageView extends ExpandableItemIndicator.Impl {

    /* renamed from: a  reason: collision with root package name */
    private ImageView f18836a;

    ExpandableItemIndicatorImplImageView() {
    }

    public void a(Context context, AttributeSet attributeSet, int i, ExpandableItemIndicator expandableItemIndicator) {
        this.f18836a = (ImageView) LayoutInflater.from(context).inflate(R.layout.widget_expandable_item_indicator_imageview, expandableItemIndicator, true).findViewById(R.id.image_view);
    }

    public void a(boolean z, boolean z2) {
        this.f18836a.setImageResource(z ? R.drawable.std_home_btn_expanding : R.drawable.std_home_btn_collasping);
    }
}
