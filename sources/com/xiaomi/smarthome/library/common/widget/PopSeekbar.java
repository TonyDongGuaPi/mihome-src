package com.xiaomi.smarthome.library.common.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.library.common.util.DisplayUtils;

public class PopSeekbar extends BaseSeekbar {

    /* renamed from: a  reason: collision with root package name */
    private OnSeekBarChangeListener f18920a;
    private PopupWindow b;
    protected ImageView mImgPopInner;
    protected boolean mIsTouchingThumb;
    protected View mPopView;
    protected int mShowMax;
    protected TextView mTxtPopProgress;

    public interface OnSeekBarChangeListener {
        void a(PopSeekbar popSeekbar);

        void a(PopSeekbar popSeekbar, int i, boolean z);

        void b(PopSeekbar popSeekbar);
    }

    public PopSeekbar(Context context) {
        this(context, (AttributeSet) null);
        a();
    }

    public PopSeekbar(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mIsTouchingThumb = false;
        this.mShowMax = 10;
        a();
    }

    private void a() {
        this.mPopView = LayoutInflater.from(getContext()).inflate(R.layout.color_seekbar_pop, (ViewGroup) null);
        this.mTxtPopProgress = (TextView) this.mPopView.findViewById(R.id.txt_pop_progress);
        this.mImgPopInner = (ImageView) this.mPopView.findViewById(R.id.img_pop_inner);
        this.b = new PopupWindow(this.mPopView, this.mPopView.getWidth(), this.mPopView.getHeight(), false);
        this.b.setAnimationStyle(R.style.PopupNoneAnimStyle);
    }

    /* access modifiers changed from: package-private */
    public void onProgressRefresh(float f, boolean z) {
        super.onProgressRefresh(f, z);
        if (this.mIsTouchingThumb) {
            showSeekPopup();
        } else {
            hideSeekPopup();
        }
        if (this.f18920a != null) {
            this.f18920a.a(this, getProgress(), z);
        }
    }

    public void setOnSeekBarChangeListener(OnSeekBarChangeListener onSeekBarChangeListener) {
        this.f18920a = onSeekBarChangeListener;
    }

    /* access modifiers changed from: package-private */
    public void onStartTrackingTouch() {
        this.mIsTouchingThumb = true;
        if (this.f18920a != null) {
            this.f18920a.a(this);
        }
    }

    /* access modifiers changed from: package-private */
    public void onStopTrackingTouch() {
        this.mIsTouchingThumb = false;
        hideSeekPopup();
        if (this.f18920a != null) {
            this.f18920a.b(this);
        }
    }

    public void hideSeekPopup() {
        if (this.b != null && this.b.isShowing()) {
            this.b.dismiss();
        }
    }

    public void showSeekPopup() {
        int b2 = (b(this.mPopView) + getHeight()) - DisplayUtils.a(getContext(), 8.0f);
        if (this.b != null) {
            try {
                int centerX = this.mThumb.getBounds().centerX() - (a(this.mPopView) / 2);
                if (!this.b.isShowing()) {
                    this.b.showAsDropDown(this, centerX, -b2);
                }
                this.b.update(this, centerX, -b2, a(this.mPopView), b(this.mPopView));
                if (this.mTxtPopProgress.getVisibility() == 0) {
                    TextView textView = this.mTxtPopProgress;
                    textView.setText("" + getShowProgress());
                }
            } catch (Exception unused) {
            }
        }
    }

    private int a(View view) {
        try {
            view.measure(View.MeasureSpec.makeMeasureSpec(0, 0), View.MeasureSpec.makeMeasureSpec(0, 0));
        } catch (Exception unused) {
        }
        return view.getMeasuredWidth();
    }

    private int b(View view) {
        try {
            view.measure(View.MeasureSpec.makeMeasureSpec(0, 0), View.MeasureSpec.makeMeasureSpec(0, 0));
        } catch (Exception unused) {
        }
        return view.getMeasuredHeight();
    }

    public void setShowProgress(int i) {
        setProgress(calcRealProgress(i));
    }

    public int getShowProgress() {
        return calcShowProgress(getProgress());
    }

    /* access modifiers changed from: protected */
    public int calcShowProgress(int i) {
        return (i * this.mShowMax) / getMax();
    }

    /* access modifiers changed from: protected */
    public int calcRealProgress(int i) {
        return (i * getMax()) / this.mShowMax;
    }

    public void setShowMax(int i) {
        this.mShowMax = i;
    }

    public int getShowMax() {
        return this.mShowMax;
    }
}
