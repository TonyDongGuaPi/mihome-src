package com.mi.global.shop.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.util.AttributeSet;
import com.mi.global.shop.R;
import com.mi.global.shop.ShopApp;
import com.mi.global.shop.locale.LocaleHelper;

public class PhoneNumberEdit extends CustomEditTextView {
    private String mPrefix = "";
    private Rect mPrefixRect = new Rect();

    public PhoneNumberEdit(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        if (LocaleHelper.g()) {
            this.mPrefix = ShopApp.g().getString(R.string.user_address_phoneareacode);
        }
    }

    public void setPrefix(String str) {
        this.mPrefix = str;
    }

    /* access modifiers changed from: protected */
    public void onMeasure(int i, int i2) {
        getPaint().getTextBounds(this.mPrefix, 0, this.mPrefix.length(), this.mPrefixRect);
        Rect rect = this.mPrefixRect;
        rect.right = (int) (((float) rect.right) + getPaint().measureText(" "));
        super.onMeasure(i, i2);
    }

    /* access modifiers changed from: protected */
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawText(this.mPrefix, (float) super.getCompoundPaddingLeft(), (float) getBaseline(), getPaint());
    }

    public int getCompoundPaddingLeft() {
        return super.getCompoundPaddingLeft() + this.mPrefixRect.width() + 10;
    }
}
