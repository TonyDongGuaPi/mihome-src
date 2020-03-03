package com.xiaomi.payment.ui.item;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.xiaomi.payment.platform.R;

public class MenuListItem extends LinearLayout {

    /* renamed from: a  reason: collision with root package name */
    private TextView f12526a;
    private ImageView b;
    private ItemData c;

    public static class ItemData {

        /* renamed from: a  reason: collision with root package name */
        public String f12527a;
        public String b;
        public boolean c;
    }

    public MenuListItem(Context context) {
        super(context);
    }

    public MenuListItem(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public void bind() {
        this.f12526a = (TextView) findViewById(R.id.title);
        this.b = (ImageView) findViewById(R.id.bubble_image);
    }

    public void rebind(ItemData itemData) {
        this.c = itemData;
        this.f12526a.setText(itemData.f12527a);
        this.b.setVisibility(itemData.c ? 0 : 8);
    }

    public void setItemData(ItemData itemData) {
        this.c = itemData;
    }

    public ItemData getItemData() {
        return this.c;
    }
}
