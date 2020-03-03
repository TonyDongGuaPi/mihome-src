package com.xiaomi.smarthome.newui.widget.topnavi.indicator.buildins.commonnavigator.titles;

import android.content.Context;
import android.graphics.Paint;
import android.graphics.Rect;
import android.text.TextUtils;
import android.util.AttributeSet;
import com.xiaomi.smarthome.newui.widget.topnavi.indicator.buildins.UIUtil;
import com.xiaomi.smarthome.newui.widget.topnavi.indicator.buildins.commonnavigator.abs.IMeasurablePagerTitleView;
import com.xiaomi.smarthome.newui.widget.topnavi.widgets.TopTab;

public class SimplePagerTitleView extends TopTab implements IMeasurablePagerTitleView {
    protected int mNormalColor;
    protected int mSelectedColor;

    public void onEnter(int i, int i2, float f, boolean z) {
    }

    public void onLeave(int i, int i2, float f, boolean z) {
    }

    public SimplePagerTitleView(Context context) {
        super(context, (AttributeSet) null);
        init(context);
    }

    private void init(Context context) {
        setGravity(17);
        setIncludeFontPadding(false);
        int a2 = UIUtil.a(context, 9.0d);
        setPadding(a2, 0, a2, 0);
        setSingleLine();
        setEllipsize(TextUtils.TruncateAt.END);
        getPaint().setFakeBoldText(true);
    }

    public void onSelected(int i, int i2) {
        setTextColor(this.mSelectedColor);
    }

    public void onDeselected(int i, int i2) {
        setTextColor(this.mNormalColor);
    }

    public int getContentLeft() {
        Rect rect = new Rect();
        getPaint().getTextBounds(getText().toString(), 0, getText().length(), rect);
        return (getLeft() + (getWidth() / 2)) - (rect.width() / 2);
    }

    public int getContentTop() {
        Paint.FontMetrics fontMetrics = getPaint().getFontMetrics();
        return (int) (((float) (getHeight() / 2)) - ((fontMetrics.bottom - fontMetrics.top) / 2.0f));
    }

    public int getContentRight() {
        Rect rect = new Rect();
        getPaint().getTextBounds(getText().toString(), 0, getText().length(), rect);
        return getLeft() + (getWidth() / 2) + (rect.width() / 2);
    }

    public int getContentBottom() {
        Paint.FontMetrics fontMetrics = getPaint().getFontMetrics();
        return (int) (((float) (getHeight() / 2)) + ((fontMetrics.bottom - fontMetrics.top) / 2.0f));
    }

    public int getSelectedColor() {
        return this.mSelectedColor;
    }

    public void setSelectedColor(int i) {
        this.mSelectedColor = i;
    }

    public int getNormalColor() {
        return this.mNormalColor;
    }

    public void setNormalColor(int i) {
        this.mNormalColor = i;
    }
}
