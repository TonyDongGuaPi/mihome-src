package com.xiaomi.smarthome.device.bluetooth.ui;

import android.animation.ValueAnimator;
import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.UiThread;
import android.text.SpannableString;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.airbnb.lottie.LottieAnimationView;
import com.airbnb.lottie.LottieComposition;
import com.airbnb.lottie.OnCompositionLoadedListener;
import com.taobao.weex.el.parse.Operators;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.application.SHApplication;
import com.xiaomi.smarthome.framework.log.LogUtil;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class CommonBindView extends RelativeLayout {
    public static final int APP_CONNECT_DEVICE_ANIMATION = 1;
    public static final int APP_TRANSFER_DEVICE_ANIMATION = 2;
    public static final int DEVICE_CONNECT_NETWORK_ANIMATION = 3;
    public static final int DOWNLOAD_PLUGIN_ANIMATION = 4;

    /* renamed from: a  reason: collision with root package name */
    private static final String f15261a = "CommonBindView";
    private static final int b = 16;
    /* access modifiers changed from: private */
    public ViewHandler c;
    private CommonBindProgressView d;
    private View e;
    /* access modifiers changed from: private */
    public LottieAnimationView f;
    private TextView g;
    private Button h;
    private TextView i;
    private LinearLayout j;
    private LinearLayout k;
    private View l;
    private Button m;
    private Button n;
    private ImageView o;
    private LayoutInflater p;
    /* access modifiers changed from: private */
    public View.OnClickListener q;
    /* access modifiers changed from: private */
    public View.OnClickListener r;
    /* access modifiers changed from: private */
    public View.OnClickListener s;
    /* access modifiers changed from: private */
    public View.OnClickListener t;
    /* access modifiers changed from: private */
    public ValueAnimator u;
    /* access modifiers changed from: private */
    public Map<Integer, String> v = new HashMap();
    private Map<Integer, Integer> w = new HashMap();
    /* access modifiers changed from: private */
    public Map<Integer, LottieComposition> x = new ConcurrentHashMap();
    private int y = -1;

    public enum StepStatus {
        LOADING,
        SUCCESS,
        FAILED
    }

    public CommonBindView(Context context) {
        super(context);
        a();
    }

    public CommonBindView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        a();
    }

    public CommonBindView(Context context, AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        a();
    }

    private void a() {
        this.c = new ViewHandler();
        this.p = LayoutInflater.from(getContext());
        this.p.inflate(R.layout.common_bind_layout, this);
        this.d = (CommonBindProgressView) findViewById(R.id.progress_view);
        this.e = findViewById(R.id.progress_background);
        this.f = (LottieAnimationView) findViewById(R.id.progress_lottie_view);
        this.g = (TextView) findViewById(R.id.progress_title);
        this.h = (Button) findViewById(R.id.common_btn);
        this.i = (TextView) findViewById(R.id.connect_failed_tips);
        this.j = (LinearLayout) findViewById(R.id.step_container);
        this.o = (ImageView) findViewById(R.id.final_icon);
        this.l = findViewById(R.id.left_right_btn);
        this.m = (Button) findViewById(R.id.left_btn);
        this.n = (Button) findViewById(R.id.right_btn);
        this.h.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                CommonBindView.this.c.removeMessages(16);
                if (CommonBindView.this.q != null) {
                    CommonBindView.this.q.onClick(view);
                }
            }
        });
        this.m.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if (CommonBindView.this.r != null) {
                    CommonBindView.this.r.onClick(view);
                }
            }
        });
        this.n.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if (CommonBindView.this.s != null) {
                    CommonBindView.this.s.onClick(view);
                }
            }
        });
        this.i.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if (CommonBindView.this.t != null) {
                    CommonBindView.this.t.onClick(view);
                }
            }
        });
        this.v.put(1, "common_bind_lottie_animation/app_connect_device");
        this.v.put(2, "common_bind_lottie_animation/app_transfer_device");
        this.v.put(3, "common_bind_lottie_animation/device_connect_server");
        this.v.put(4, "common_bind_lottie_animation/download_plugin");
        this.w.put(1, 2000);
        this.w.put(2, 1000);
        this.w.put(3, 1000);
        this.w.put(4, 1000);
        this.u = ValueAnimator.ofFloat(new float[]{0.0f, 1.0f});
        this.u.setRepeatCount(-1);
        this.u.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                if (CommonBindView.this.f != null && CommonBindView.this.u.isRunning()) {
                    Float f = (Float) CommonBindView.this.u.getAnimatedValue();
                    CommonBindView.this.f.setProgress(((Float) CommonBindView.this.u.getAnimatedValue()).floatValue());
                }
            }
        });
        LogUtil.a(f15261a, "start fromAssetFileName");
        Context context = getContext();
        LottieComposition.Factory.fromAssetFileName(context, this.v.get(1) + "/data.json", new OnCompositionLoadedListener() {
            public void onCompositionLoaded(LottieComposition lottieComposition) {
                LogUtil.a(CommonBindView.f15261a, "1 onCompositionLoaded");
                CommonBindView.this.x.put(1, lottieComposition);
            }
        });
        this.c.postDelayed(new Runnable() {
            public void run() {
                Context context = CommonBindView.this.getContext();
                LottieComposition.Factory.fromAssetFileName(context, ((String) CommonBindView.this.v.get(2)) + "/data.json", new OnCompositionLoadedListener() {
                    public void onCompositionLoaded(LottieComposition lottieComposition) {
                        LogUtil.a(CommonBindView.f15261a, "2 onCompositionLoaded");
                        CommonBindView.this.x.put(2, lottieComposition);
                    }
                });
            }
        }, 800);
        this.c.postDelayed(new Runnable() {
            public void run() {
                Context context = CommonBindView.this.getContext();
                LottieComposition.Factory.fromAssetFileName(context, ((String) CommonBindView.this.v.get(3)) + "/data.json", new OnCompositionLoadedListener() {
                    public void onCompositionLoaded(LottieComposition lottieComposition) {
                        LogUtil.a(CommonBindView.f15261a, "3 onCompositionLoaded");
                        CommonBindView.this.x.put(3, lottieComposition);
                    }
                });
            }
        }, 1000);
        this.c.postDelayed(new Runnable() {
            public void run() {
                Context context = CommonBindView.this.getContext();
                LottieComposition.Factory.fromAssetFileName(context, ((String) CommonBindView.this.v.get(4)) + "/data.json", new OnCompositionLoadedListener() {
                    public void onCompositionLoaded(LottieComposition lottieComposition) {
                        LogUtil.a(CommonBindView.f15261a, "4 onCompositionLoaded");
                        CommonBindView.this.x.put(4, lottieComposition);
                    }
                });
            }
        }, 1200);
    }

    public void setCommonBtnVisibility(int i2) {
        this.h.setVisibility(i2);
    }

    public void setLeftRightBtnVisibility(int i2) {
        this.l.setVisibility(i2);
    }

    public void setCommonBtnText(String str) {
        this.h.setText(str);
    }

    public void setCommonBtnListener(View.OnClickListener onClickListener) {
        this.q = onClickListener;
    }

    public void setLeftBtnListener(View.OnClickListener onClickListener) {
        this.r = onClickListener;
    }

    public void setRightBtnListener(View.OnClickListener onClickListener) {
        this.s = onClickListener;
    }

    public void setConnectFailedTipsVisibility(int i2) {
        this.i.setVisibility(i2);
    }

    public void setConnectFailedTipsText(String str) {
        this.i.setText(str);
    }

    public void setConnectFailedTipsListener(View.OnClickListener onClickListener) {
        this.t = onClickListener;
    }

    public void setProgress(int i2) {
        this.d.setProgress(i2);
    }

    @UiThread
    public void startProgressAnimation(int i2) {
        LogUtil.a(f15261a, "startProgressAnimation step = " + i2);
        if (this.x.get(Integer.valueOf(i2)) == null) {
            LogUtil.a(f15261a, "startProgressAnimation step = " + i2 + ", lottieComposition is null");
            a(i2, 5);
            return;
        }
        a(i2);
    }

    /* access modifiers changed from: private */
    public void a(final int i2, final int i3) {
        LogUtil.a(f15261a, "startRetry step = " + i2 + ", retryTime = " + i3);
        SHApplication.getGlobalHandler().postDelayed(new Runnable() {
            public void run() {
                if (((LottieComposition) CommonBindView.this.x.get(Integer.valueOf(i2))) != null) {
                    CommonBindView.this.a(i2);
                } else if (i3 > 0) {
                    CommonBindView.this.a(i2, i3 - 1);
                }
            }
        }, 500);
    }

    /* access modifiers changed from: private */
    public void a(int i2) {
        LogUtil.a(f15261a, "startReady step = " + i2);
        if (i2 == 1) {
            b(i2);
        } else {
            c(i2);
        }
    }

    /* access modifiers changed from: private */
    public void b(int i2) {
        stopProgressAnimation();
        LottieComposition lottieComposition = this.x.get(Integer.valueOf(i2));
        if (this.f != null && lottieComposition != null) {
            this.f.setComposition(lottieComposition);
            LottieAnimationView lottieAnimationView = this.f;
            lottieAnimationView.setImageAssetsFolder(this.v.get(Integer.valueOf(i2)) + "/images");
            this.u.setDuration((long) this.w.get(Integer.valueOf(i2)).intValue());
            this.u.start();
            Animation loadAnimation = AnimationUtils.loadAnimation(getContext(), R.anim.fade_in);
            loadAnimation.setDuration(300);
            this.f.startAnimation(loadAnimation);
        }
    }

    private void c(final int i2) {
        if (this.f != null) {
            Animation loadAnimation = AnimationUtils.loadAnimation(getContext(), R.anim.ftue_fade_out);
            loadAnimation.setDuration(300);
            loadAnimation.setAnimationListener(new Animation.AnimationListener() {
                public void onAnimationRepeat(Animation animation) {
                }

                public void onAnimationStart(Animation animation) {
                }

                public void onAnimationEnd(Animation animation) {
                    CommonBindView.this.b(i2);
                }
            });
            this.f.cancelAnimation();
            this.f.startAnimation(loadAnimation);
        }
    }

    public void stopProgressAnimation() {
        if (this.f != null) {
            this.f.cancelAnimation();
        }
        if (this.u != null) {
            this.u.cancel();
        }
    }

    /* access modifiers changed from: protected */
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        this.q = null;
        this.r = null;
        this.s = null;
        this.t = null;
        stopProgressAnimation();
        this.c.removeCallbacksAndMessages((Object) null);
    }

    public void addNextStep(int i2, int i3) {
        addNextStep(getResources().getString(i2), getResources().getString(i3));
    }

    public void addNextStep(String str, String str2) {
        this.k = (LinearLayout) this.p.inflate(R.layout.common_bind_step_item, this.j, false);
        this.j.addView(this.k);
        a(StepStatus.LOADING, (CharSequence) str);
        a(str2, false);
    }

    public int getStepIndex() {
        return this.j.getChildCount();
    }

    public void updateCurrentStep(StepStatus stepStatus, int i2, int i3) {
        updateCurrentStep(stepStatus, getResources().getString(i2), i3, false);
    }

    public void updateCurrentStep(StepStatus stepStatus, String str, int i2) {
        updateCurrentStep(stepStatus, str, i2, false);
    }

    public void updateCurrentStep(StepStatus stepStatus, CharSequence charSequence, int i2, boolean z) {
        a(stepStatus, charSequence);
        a(getResources().getString(i2), z);
    }

    public void setCurrentIndex(int i2) {
        this.y = i2;
    }

    public int getCurrentIndex() {
        return this.y;
    }

    public void setBindSuccess() {
        this.c.removeMessages(16);
        setCommonBtnText(getResources().getText(R.string.common_finish) + " (" + 3 + Operators.BRACKET_END_STR);
        Message message = new Message();
        message.what = 16;
        message.arg1 = 2;
        this.c.sendMessageDelayed(message, 1000);
        this.d.setVisibility(8);
        this.e.setBackgroundResource(0);
        this.g.setText(R.string.ble_new_add_device_success_title);
        stopProgressAnimation();
        this.f.setVisibility(8);
        this.o.setVisibility(0);
        this.o.setImageResource(R.drawable.common_bind_success);
    }

    public void setBindFailed(int i2) {
        this.c.removeMessages(16);
        this.d.setVisibility(8);
        this.e.setBackgroundResource(0);
        stopProgressAnimation();
        this.f.setVisibility(8);
        this.o.setVisibility(0);
        this.o.setImageResource(i2);
    }

    public void resetUi() {
        this.h.setVisibility(4);
        this.l.setVisibility(4);
        this.r = null;
        this.s = null;
        this.i.setVisibility(8);
        this.t = null;
        this.d.setProgress(0);
        this.d.setVisibility(0);
        this.e.setBackgroundResource(R.drawable.common_bind_view_bg_shape);
        stopProgressAnimation();
        this.o.setVisibility(8);
        this.f.setVisibility(0);
        this.j.removeAllViews();
        this.k = null;
    }

    public void restoreUi() {
        this.h.setVisibility(4);
        this.l.setVisibility(4);
        this.i.setVisibility(8);
        this.d.setProgress(0);
        this.d.setVisibility(0);
        this.e.setBackgroundResource(R.drawable.common_bind_view_bg_shape);
        stopProgressAnimation();
        this.o.setVisibility(8);
        this.f.setVisibility(0);
    }

    private void a(StepStatus stepStatus, CharSequence charSequence) {
        ClickableSpan[] clickableSpanArr;
        if (this.k != null) {
            ImageView imageView = (ImageView) this.k.findViewById(R.id.step_icon);
            TextView textView = (TextView) this.k.findViewById(R.id.step_title);
            if (imageView != null && textView != null) {
                if (!(!(charSequence instanceof SpannableString) || (clickableSpanArr = (ClickableSpan[]) ((SpannableString) charSequence).getSpans(0, charSequence.length(), ClickableSpan.class)) == null || clickableSpanArr.length == 0)) {
                    textView.setMovementMethod(LinkMovementMethod.getInstance());
                }
                textView.setText(charSequence);
                switch (stepStatus) {
                    case LOADING:
                        imageView.setImageResource(R.drawable.common_bind_loading_icon);
                        Animation loadAnimation = AnimationUtils.loadAnimation(getContext(), R.anim.rotate_infinite);
                        loadAnimation.setDuration(1000);
                        imageView.startAnimation(loadAnimation);
                        textView.setTextColor(getResources().getColor(R.color.black));
                        return;
                    case SUCCESS:
                        imageView.clearAnimation();
                        imageView.setImageResource(R.drawable.common_bind_success_icon);
                        textView.setTextColor(getResources().getColor(R.color.class_Y));
                        return;
                    case FAILED:
                        imageView.clearAnimation();
                        imageView.setImageResource(R.drawable.common_bind_failed_icon);
                        textView.setTextColor(getResources().getColor(R.color.class_Y));
                        return;
                    default:
                        return;
                }
            }
        }
    }

    private void a(String str, boolean z) {
        this.g.setText(str);
        if (z) {
            this.g.setTextColor(getResources().getColor(R.color.common_bind_error_title_color));
        } else {
            this.g.setTextColor(getResources().getColor(R.color.class_V));
        }
    }

    private class ViewHandler extends Handler {
        private ViewHandler() {
        }

        public void handleMessage(Message message) {
            super.handleMessage(message);
            if (message.what == 16) {
                if (message.arg1 != 0) {
                    CommonBindView commonBindView = CommonBindView.this;
                    commonBindView.setCommonBtnText(CommonBindView.this.getResources().getText(R.string.common_finish) + " (" + message.arg1 + Operators.BRACKET_END_STR);
                    Message message2 = new Message();
                    message2.arg1 = message.arg1 + -1;
                    message2.what = 16;
                    CommonBindView.this.c.sendMessageDelayed(message2, 1000);
                } else if (CommonBindView.this.q != null) {
                    CommonBindView.this.q.onClick((View) null);
                }
            }
        }
    }
}
