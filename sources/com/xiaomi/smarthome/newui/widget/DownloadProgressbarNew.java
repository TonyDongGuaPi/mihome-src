package com.xiaomi.smarthome.newui.widget;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.ProgressBar;

public class DownloadProgressbarNew extends ProgressBar implements ProgressItemView {
    public DownloadProgressbarNew(Context context, @Nullable AttributeSet attributeSet) {
        super(context, attributeSet);
        a();
    }

    private void a() {
        setProgress(0);
    }

    public void setStart() {
        setProgress(0);
        setVisibility(0);
    }

    public void setSuccess() {
        setProgress(0);
        setVisible(false);
    }

    public void setFail() {
        setProgress(0);
        setVisible(false);
    }

    public void setCancel() {
        setProgress(0);
        setVisible(false);
    }

    public void setVisible(boolean z) {
        setVisibility(z ? 0 : 8);
    }

    public float getPercent() {
        return (float) getProgress();
    }

    public void setPercent(float f) {
        setProgress((int) f);
    }
}
