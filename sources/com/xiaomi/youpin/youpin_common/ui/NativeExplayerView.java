package com.xiaomi.youpin.youpin_common.ui;

import android.content.Context;
import com.xiaomi.youpin.youpin_common.widget.video.BaseExoplayerView;
import java.util.List;

public class NativeExplayerView extends BaseExoplayerView {
    ExoPlayerListener mExoPlayerListener;

    public interface ExoPlayerListener {
        void callLoad(double d, double d2, int i, int i2, List list, List list2);

        void callProgressChanged(double d, double d2, double d3);
    }

    public NativeExplayerView(Context context) {
        super(context);
        constructView();
    }

    public void setExoPlayerListener(ExoPlayerListener exoPlayerListener) {
        this.mExoPlayerListener = exoPlayerListener;
    }

    /* access modifiers changed from: protected */
    public void callProgressChanged(double d, double d2, double d3) {
        if (this.mExoPlayerListener != null) {
            this.mExoPlayerListener.callProgressChanged(d, d2, d3);
        }
    }

    /* access modifiers changed from: protected */
    public void callLoad(double d, double d2, int i, int i2, List list, List list2) {
        if (this.mExoPlayerListener != null) {
            this.mExoPlayerListener.callLoad(d, d2, i, i2, list, list2);
        }
    }
}
