package com.xiaomi.smarthome.mibrain.viewutil.waveview;

import android.animation.Animator;
import android.content.Context;
import android.graphics.Canvas;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import com.xiaomi.smarthome.R;

public class AudioRecordWaveView2 extends FrameLayout {
    private static final int b = 0;
    private static final int c = 1;

    /* renamed from: a  reason: collision with root package name */
    private MiBrainWaveView f10744a;
    private int d = 0;
    /* access modifiers changed from: private */
    public ImageView e;
    /* access modifiers changed from: private */
    public View f;
    /* access modifiers changed from: private */
    public RippleBackground g;
    private boolean h = false;

    public void receiveVoice(double d2) {
    }

    public AudioRecordWaveView2(@NonNull Context context) {
        super(context);
        a();
    }

    public AudioRecordWaveView2(@NonNull Context context, @Nullable AttributeSet attributeSet) {
        super(context, attributeSet);
        a();
    }

    public AudioRecordWaveView2(@NonNull Context context, @Nullable AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        a();
    }

    private void a() {
        LayoutInflater.from(getContext()).inflate(R.layout.audio_record_wave_layout_2, this);
        this.g = (RippleBackground) findViewById(R.id.ripple_anim_bg);
        this.f = findViewById(R.id.audio_record_bg);
        this.e = (ImageView) findViewById(R.id.voice_icon);
        this.e.setOnTouchListener(new View.OnTouchListener() {

            /* renamed from: a  reason: collision with root package name */
            MotionEvent f10745a;
            /* access modifiers changed from: private */
            public boolean c = false;

            public boolean onTouch(View view, MotionEvent motionEvent) {
                this.f10745a = motionEvent;
                int action = motionEvent.getAction();
                if (action != 3) {
                    switch (action) {
                        case 0:
                            this.c = false;
                            AudioRecordWaveView2.this.e.animate().scaleX(1.2f).scaleY(1.2f).setDuration(150).setListener(new Animator.AnimatorListener() {
                                public void onAnimationRepeat(Animator animator) {
                                }

                                public void onAnimationStart(Animator animator) {
                                }

                                public void onAnimationEnd(Animator animator) {
                                    if (AnonymousClass1.this.f10745a.getAction() != 0) {
                                        boolean unused = AnonymousClass1.this.c = true;
                                        AudioRecordWaveView2.this.e.animate().scaleX(1.0f).scaleY(1.0f).setDuration(150).start();
                                    }
                                }

                                public void onAnimationCancel(Animator animator) {
                                    boolean unused = AnonymousClass1.this.c = false;
                                }
                            }).start();
                            break;
                        case 1:
                            break;
                    }
                }
                if (this.c) {
                    AudioRecordWaveView2.this.e.animate().scaleX(1.0f).scaleY(1.0f).setDuration(150).start();
                }
                return false;
            }
        });
    }

    public void setVoiceIconClickListener(View.OnClickListener onClickListener) {
        this.e.setOnClickListener(onClickListener);
    }

    public void startAnimation() {
        if (!this.g.isRippleAnimationRunning()) {
            this.g.startRippleAnimation(new Animator.AnimatorListener() {
                public void onAnimationCancel(Animator animator) {
                }

                public void onAnimationEnd(Animator animator) {
                }

                public void onAnimationRepeat(Animator animator) {
                }

                public void onAnimationStart(Animator animator) {
                    AudioRecordWaveView2.this.g.postDelayed(new Runnable() {
                        public void run() {
                            AudioRecordWaveView2.this.f.setVisibility(0);
                        }
                    }, 300);
                }
            });
        }
    }

    private void b() {
        this.g.stopRippleAnimation();
        this.f.setVisibility(8);
    }

    /* access modifiers changed from: protected */
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        this.g.stopRippleAnimation();
    }

    public void enterListeningUI() {
        this.d = 1;
        startAnimation();
    }

    public void exitListeningUI() {
        this.d = 0;
        b();
    }

    /* access modifiers changed from: protected */
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
    }
}
