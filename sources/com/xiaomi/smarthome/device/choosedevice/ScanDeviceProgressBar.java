package com.xiaomi.smarthome.device.choosedevice;

import android.content.Context;
import android.graphics.drawable.ClipDrawable;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.FrameLayout;
import android.widget.ImageView;
import com.xiaomi.smarthome.R;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Handler;

public class ScanDeviceProgressBar extends FrameLayout {
    /* access modifiers changed from: private */

    /* renamed from: a  reason: collision with root package name */
    public ClipDrawable f15346a;
    /* access modifiers changed from: private */
    public OnTimeOutListener b;
    /* access modifiers changed from: private */
    public int c;
    /* access modifiers changed from: private */
    public int d;
    /* access modifiers changed from: private */
    public Timer e;
    /* access modifiers changed from: private */
    public int f;
    private Handler g;

    public interface OnTimeOutListener {
        void onTimeOut();
    }

    public ScanDeviceProgressBar(Context context) {
        this(context, (AttributeSet) null);
    }

    public ScanDeviceProgressBar(Context context, @Nullable AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public ScanDeviceProgressBar(Context context, @Nullable AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.c = 30000;
        this.d = 0;
        this.f = 50;
        a();
    }

    private void a() {
        setBackgroundDrawable(getResources().getDrawable(R.drawable.scan_progress_bg));
        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(-1, -1);
        ImageView imageView = new ImageView(getContext());
        imageView.setImageDrawable(getResources().getDrawable(R.drawable.scan_progress_clip_drawable));
        this.f15346a = (ClipDrawable) imageView.getDrawable();
        addView(imageView, layoutParams);
    }

    public void setTime(int i) {
        this.c = i;
    }

    public void setPercent(int i) {
        this.d = i;
        this.f15346a.setLevel((i * 10000) / 100);
    }

    public void start() {
        if (this.e == null) {
            this.e = new Timer();
            this.e.schedule(new TimerTask() {
                public void run() {
                    ScanDeviceProgressBar.this.post(new Runnable() {
                        public void run() {
                            int level = ScanDeviceProgressBar.this.f15346a.getLevel() + ((ScanDeviceProgressBar.this.f * 10000) / ScanDeviceProgressBar.this.c);
                            if (level < 10000) {
                                ScanDeviceProgressBar.this.f15346a.setLevel(level);
                                int unused = ScanDeviceProgressBar.this.d = (level * 100) / 10000;
                            } else if (ScanDeviceProgressBar.this.f15346a.getLevel() < 10000) {
                                ScanDeviceProgressBar.this.f15346a.setLevel(10000);
                                if (ScanDeviceProgressBar.this.e != null) {
                                    ScanDeviceProgressBar.this.e.cancel();
                                }
                                if (ScanDeviceProgressBar.this.b != null) {
                                    ScanDeviceProgressBar.this.b.onTimeOut();
                                }
                            }
                        }
                    });
                }
            }, 0, (long) this.f);
        }
    }

    public void stop() {
        if (this.e != null) {
            this.e.cancel();
            this.e = null;
        }
    }

    public void reset() {
        stop();
        this.f15346a.setLevel(0);
        this.d = 0;
    }

    public int getPercent() {
        return this.d;
    }

    public void registerTimeOutListener(OnTimeOutListener onTimeOutListener) {
        this.b = onTimeOutListener;
    }
}
