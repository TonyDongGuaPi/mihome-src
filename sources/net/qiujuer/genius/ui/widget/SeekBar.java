package net.qiujuer.genius.ui.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;

public class SeekBar extends AbsSeekBar {

    /* renamed from: a  reason: collision with root package name */
    private OnSeekBarChangeListener f3161a;

    public interface OnSeekBarChangeListener {
        void a(SeekBar seekBar);

        void a(SeekBar seekBar, int i, boolean z);

        void b(SeekBar seekBar);
    }

    public SeekBar(Context context) {
        super(context);
    }

    public SeekBar(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public SeekBar(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    public SeekBar(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
    }

    public void setOnSeekBarChangeListener(OnSeekBarChangeListener onSeekBarChangeListener) {
        this.f3161a = onSeekBarChangeListener;
    }

    /* access modifiers changed from: protected */
    public void onProgressChanged(int i, boolean z) {
        super.onProgressChanged(i, z);
        if (this.f3161a != null) {
            this.f3161a.a(this, i, z);
        }
    }

    /* access modifiers changed from: protected */
    public void onStartTrackingTouch() {
        super.onStartTrackingTouch();
        if (this.f3161a != null) {
            this.f3161a.a(this);
        }
    }

    /* access modifiers changed from: protected */
    public void onStopTrackingTouch() {
        super.onStopTrackingTouch();
        if (this.f3161a != null) {
            this.f3161a.b(this);
        }
    }

    public void onInitializeAccessibilityEvent(AccessibilityEvent accessibilityEvent) {
        super.onInitializeAccessibilityEvent(accessibilityEvent);
        accessibilityEvent.setClassName(SeekBar.class.getName());
    }

    public void onInitializeAccessibilityNodeInfo(AccessibilityNodeInfo accessibilityNodeInfo) {
        super.onInitializeAccessibilityNodeInfo(accessibilityNodeInfo);
        accessibilityNodeInfo.setClassName(SeekBar.class.getName());
    }
}
