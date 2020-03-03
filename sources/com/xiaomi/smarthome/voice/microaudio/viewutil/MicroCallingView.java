package com.xiaomi.smarthome.voice.microaudio.viewutil;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.AttributeSet;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.facebook.drawee.generic.GenericDraweeHierarchy;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.framework.log.LogUtil;
import com.xiaomi.smarthome.library.common.util.DisplayUtils;
import com.xiaomi.smarthome.mibrain.viewutil.RadarLayout;

public class MicroCallingView extends RelativeLayout {

    /* renamed from: a  reason: collision with root package name */
    private static final int f22810a = 800;
    /* access modifiers changed from: private */
    public Handler b;
    /* access modifiers changed from: private */
    public boolean c;
    /* access modifiers changed from: private */
    public int d;
    GenericDraweeHierarchy hierarchy;
    TextView mCallingTV;
    Context mContext;
    RadarLayout mRadarLayout;
    RadarLayout mRingLayout;

    /* access modifiers changed from: private */
    public String a(int i) {
        switch (i) {
            case 0:
                return " ";
            case 1:
                return " .";
            case 2:
                return " ..";
            case 3:
                return " ...";
            default:
                return " ";
        }
    }

    static /* synthetic */ int access$108(MicroCallingView microCallingView) {
        int i = microCallingView.d;
        microCallingView.d = i + 1;
        return i;
    }

    public MicroCallingView(Context context) {
        this(context, (AttributeSet) null);
    }

    public MicroCallingView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet, 0);
        this.b = new Handler(Looper.getMainLooper()) {
            public void handleMessage(Message message) {
                LogUtil.a("MicroCallingView", "msg.what  " + message.what);
                MicroCallingView.this.mCallingTV.setText(MicroCallingView.this.a(message.what % 4));
                MicroCallingView.access$108(MicroCallingView.this);
                if (MicroCallingView.this.c) {
                    MicroCallingView.this.b.sendEmptyMessageDelayed(MicroCallingView.this.d, 300);
                }
            }
        };
        this.c = true;
        this.d = 0;
    }

    public MicroCallingView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.b = new Handler(Looper.getMainLooper()) {
            public void handleMessage(Message message) {
                LogUtil.a("MicroCallingView", "msg.what  " + message.what);
                MicroCallingView.this.mCallingTV.setText(MicroCallingView.this.a(message.what % 4));
                MicroCallingView.access$108(MicroCallingView.this);
                if (MicroCallingView.this.c) {
                    MicroCallingView.this.b.sendEmptyMessageDelayed(MicroCallingView.this.d, 300);
                }
            }
        };
        this.c = true;
        this.d = 0;
        this.mContext = context;
    }

    /* access modifiers changed from: protected */
    public void onFinishInflate() {
        super.onFinishInflate();
        this.mRadarLayout = (RadarLayout) findViewById(R.id.micro_calling_radar_layout);
        this.mRingLayout = (RadarLayout) findViewById(R.id.micro_call_radar_layout_ring);
        this.mCallingTV = (TextView) findViewById(R.id.micro_calling_tv);
        a();
    }

    private void a() {
        this.mRadarLayout.setCount(1);
        this.mRadarLayout.setBitmap(getLogoBitmap());
        this.mRadarLayout.setColor(getResources().getColor(R.color.micro_calling_bg));
        this.mRadarLayout.setRepeat(0);
        this.mRadarLayout.setDuration(800);
        this.mRadarLayout.setUseRing(false);
        this.mRadarLayout.setScale(0.9f, 1.0f);
        this.mRadarLayout.setRepeat(100);
        this.mRingLayout.setCount(1);
        this.mRingLayout.setColor(getResources().getColor(R.color.micro_calling_ring_bg));
        this.mRingLayout.setRepeat(0);
        this.mRingLayout.setDuration(800);
        this.mRingLayout.setUseRing(true);
        this.mRingLayout.setScale(0.3f, 1.0f);
        this.mRingLayout.setRepeat(100);
        this.mRingLayout.setAlpha(1.0f, 0.0f);
    }

    private Bitmap getLogoBitmap() {
        Bitmap decodeResource = BitmapFactory.decodeResource(getResources(), R.drawable.micro_audio_logo_v2);
        int width = decodeResource.getWidth();
        int height = decodeResource.getHeight();
        int a2 = DisplayUtils.a(65.0f);
        Matrix matrix = new Matrix();
        matrix.postScale(((float) a2) / ((float) width), ((float) DisplayUtils.a(65.0f)) / ((float) height));
        return Bitmap.createBitmap(decodeResource, 0, 0, width, height, matrix, true);
    }

    public void show() {
        setVisibility(0);
        this.mRadarLayout.start();
        this.mRingLayout.start();
        b();
    }

    public void hide() {
        setVisibility(8);
        this.mRadarLayout.stop();
        this.mRingLayout.stop();
        c();
    }

    private void b() {
        this.c = true;
        this.d = 0;
        this.b.sendEmptyMessageDelayed(this.d, 300);
    }

    private void c() {
        this.c = false;
        this.d = 0;
    }
}
