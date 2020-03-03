package com.xiaomi.smarthome.mibrain.viewutil.waveview;

import android.animation.Animator;
import android.animation.TimeInterpolator;
import android.content.Context;
import android.graphics.Canvas;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Interpolator;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import com.xiaomi.smarthome.R;

public class AudioRecordWaveView extends FrameLayout {

    /* renamed from: a  reason: collision with root package name */
    private static final int f10735a = 0;
    private static final int b = 1;
    private static final float h = 1.1f;
    private static final float i = 1.2f;
    private static final long j = 1500;
    private int c = 0;
    private ImageView d;
    private TextView e;
    /* access modifiers changed from: private */
    public View f;
    /* access modifiers changed from: private */
    public volatile boolean g = false;
    private TimeInterpolator k = new TimeInterpolator() {
        public float getInterpolation(float f) {
            return f < 0.5f ? f * 2.0f : 2.0f - (f * 2.0f);
        }
    };

    public void receiveVoice(double d2) {
    }

    public AudioRecordWaveView(@NonNull Context context) {
        super(context);
        a();
    }

    public AudioRecordWaveView(@NonNull Context context, @Nullable AttributeSet attributeSet) {
        super(context, attributeSet);
        a();
    }

    public AudioRecordWaveView(@NonNull Context context, @Nullable AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        a();
    }

    private void a() {
        LayoutInflater.from(getContext()).inflate(R.layout.audio_record_wave_layout, this);
        this.f = findViewById(R.id.audio_record_wave_bg);
        this.d = (ImageView) findViewById(R.id.voice_icon);
        this.e = (TextView) findViewById(R.id.voice_tv);
        setOnTouchListener(new View.OnTouchListener() {

            /* renamed from: a  reason: collision with root package name */
            MotionEvent f10736a;
            /* access modifiers changed from: private */
            public boolean c = false;

            public boolean onTouch(View view, MotionEvent motionEvent) {
                this.f10736a = motionEvent;
                int action = motionEvent.getAction();
                if (action != 3) {
                    switch (action) {
                        case 0:
                            this.c = false;
                            AudioRecordWaveView.this.f.animate().scaleX(AudioRecordWaveView.h).scaleY(AudioRecordWaveView.i).setInterpolator(new Interpolator() {
                                public float getInterpolation(float f) {
                                    return f;
                                }
                            }).setDuration(750).setListener(new Animator.AnimatorListener() {
                                public void onAnimationRepeat(Animator animator) {
                                }

                                public void onAnimationStart(Animator animator) {
                                }

                                public void onAnimationEnd(Animator animator) {
                                    boolean unused = AnonymousClass1.this.c = true;
                                    if (AnonymousClass1.this.f10736a.getAction() != 0) {
                                        AudioRecordWaveView.this.f.animate().scaleX(1.0f).scaleY(1.0f).setDuration(750).setListener(new Animator.AnimatorListener() {
                                            public void onAnimationCancel(Animator animator) {
                                            }

                                            public void onAnimationRepeat(Animator animator) {
                                            }

                                            public void onAnimationStart(Animator animator) {
                                            }

                                            public void onAnimationEnd(Animator animator) {
                                                AudioRecordWaveView.this.b();
                                            }
                                        }).start();
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
                    AudioRecordWaveView.this.f.animate().scaleX(1.0f).scaleY(1.0f).setListener(new Animator.AnimatorListener() {
                        public void onAnimationCancel(Animator animator) {
                        }

                        public void onAnimationRepeat(Animator animator) {
                        }

                        public void onAnimationStart(Animator animator) {
                        }

                        public void onAnimationEnd(Animator animator) {
                            AudioRecordWaveView.this.b();
                        }
                    }).setInterpolator(new Interpolator() {
                        public float getInterpolation(float f) {
                            return f;
                        }
                    }).setDuration(750).start();
                }
                return false;
            }
        });
    }

    public void setVoiceIconClickListener(View.OnClickListener onClickListener) {
        setOnClickListener(onClickListener);
    }

    public void startAnimation() {
        this.g = true;
        this.e.setText(R.string.mi_brain_recording);
    }

    /* access modifiers changed from: private */
    public void b() {
        this.f.animate().setListener(new Animator.AnimatorListener() {
            public void onAnimationCancel(Animator animator) {
            }

            public void onAnimationRepeat(Animator animator) {
            }

            public void onAnimationStart(Animator animator) {
            }

            public void onAnimationEnd(Animator animator) {
                if (AudioRecordWaveView.this.g) {
                    AudioRecordWaveView.this.b();
                }
            }
        }).scaleX(h).scaleY(i).setInterpolator(this.k).setDuration(1500).start();
    }

    private void c() {
        this.g = false;
        this.e.setText(R.string.mi_brain_click_to_record);
    }

    /* access modifiers changed from: protected */
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
    }

    public void enterListeningUI() {
        this.c = 1;
        startAnimation();
    }

    public void exitListeningUI() {
        this.c = 0;
        c();
    }

    /* access modifiers changed from: protected */
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
    }
}
