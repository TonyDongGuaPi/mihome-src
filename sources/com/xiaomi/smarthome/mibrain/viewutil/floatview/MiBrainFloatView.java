package com.xiaomi.smarthome.mibrain.viewutil.floatview;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.library.common.util.DisplayUtils;
import com.xiaomi.smarthome.miio.Miio;
import com.ximalaya.ting.android.opensdk.constants.PreferenceConstantsInOpenSdk;
import java.util.Timer;
import java.util.TimerTask;

public class MiBrainFloatView extends ImageView {
    private static final int FREQUENCY = 16;
    private static final int HIDE = 1;
    private static final int KEEP_TO_SIDE = 0;
    private static final int MOVE_SLOWLY = 2;
    private static final String TAG = "MiBrainFloatView";
    /* access modifiers changed from: private */
    public boolean canClick = true;
    private boolean canMove = false;
    private Context context;
    /* access modifiers changed from: private */
    public int count;
    /* access modifiers changed from: private */
    public int darkResource;
    private int defaultResource;
    /* access modifiers changed from: private */
    public float distance;
    private float endY;
    /* access modifiers changed from: private */
    public Handler handler = new Handler(Looper.getMainLooper()) {
        public void handleMessage(Message message) {
            switch (message.what) {
                case 0:
                    MiBrainFloatView.this.setImageResource(MiBrainFloatView.this.darkResource);
                    MiBrainFloatView.this.cancelTimerCount();
                    MiBrainFloatView.this.startSecondTimerCount();
                    return;
                case 1:
                    MiBrainFloatView.this.cancelSecondTimerCount();
                    if (MiBrainFloatView.this.isRight) {
                        boolean unused = MiBrainFloatView.this.isHide = true;
                        MiBrainFloatView.this.setImageResource(MiBrainFloatView.this.rightResource);
                        return;
                    }
                    MiBrainFloatView.this.setImageResource(MiBrainFloatView.this.leftResource);
                    return;
                case 2:
                    try {
                        if (MiBrainFloatView.this.j == MiBrainFloatView.this.count + 1) {
                            boolean unused2 = MiBrainFloatView.this.canClick = true;
                        }
                        int unused3 = MiBrainFloatView.this.count = (int) ((((float) (MiBrainFloatView.this.step * 2)) * Math.abs(MiBrainFloatView.this.distance)) / ((float) MiBrainFloatView.this.screenWidth));
                        if (MiBrainFloatView.this.j <= MiBrainFloatView.this.count) {
                            MiBrainFloatView.this.windowManagerParams.x = (int) (MiBrainFloatView.this.xStart - ((((float) MiBrainFloatView.this.j) * MiBrainFloatView.this.distance) / ((float) MiBrainFloatView.this.count)));
                            MiBrainFloatView.this.windowManager.updateViewLayout(MiBrainFloatView.this.image, MiBrainFloatView.this.windowManagerParams);
                            MiBrainFloatView.access$508(MiBrainFloatView.this);
                            MiBrainFloatView.this.handler.postDelayed(new Runnable() {
                                public void run() {
                                    MiBrainFloatView.this.handler.sendEmptyMessage(2);
                                }
                            }, 16);
                            return;
                        }
                        return;
                    } catch (Exception e) {
                        e.printStackTrace();
                        return;
                    }
                default:
                    return;
            }
        }
    };
    /* access modifiers changed from: private */
    public View image;
    private int imageHeight = -1;
    /* access modifiers changed from: private */
    public boolean isCancel;
    /* access modifiers changed from: private */
    public boolean isHide;
    private boolean isMove = false;
    /* access modifiers changed from: private */
    public boolean isRight = false;
    /* access modifiers changed from: private */
    public boolean isSecondCancel;
    /* access modifiers changed from: private */
    public boolean isTouch = false;
    private boolean isUp;
    /* access modifiers changed from: private */
    public int j;
    /* access modifiers changed from: private */
    public int leftResource;
    private NoDuplicateClickListener mClickListener;
    private MiBrainPreferenceManager mPreferenceManager = null;
    private float mTouchX;
    private float mTouchY;
    private float mX;
    private int maxY;
    private int minY;
    /* access modifiers changed from: private */
    public int rightResource;
    /* access modifiers changed from: private */
    public int screenWidth;
    private TimerTask secondTask;
    private Timer secondTimer;
    private int statusBarHeight;
    /* access modifiers changed from: private */
    public int step;
    private Timer timer;
    private TimerTask timerTask;
    /* access modifiers changed from: private */
    public WindowManager windowManager;
    /* access modifiers changed from: private */
    public WindowManager.LayoutParams windowManagerParams;
    private float x;
    /* access modifiers changed from: private */
    public float xStart;
    private float y;

    static /* synthetic */ int access$508(MiBrainFloatView miBrainFloatView) {
        int i = miBrainFloatView.j;
        miBrainFloatView.j = i + 1;
        return i;
    }

    public MiBrainFloatView(Context context2, WindowManager.LayoutParams layoutParams, WindowManager windowManager2) {
        super(context2);
        this.context = context2;
        this.image = this;
        this.windowManager = windowManager2;
        this.defaultResource = R.drawable.mi_brain_float_view_press;
        this.darkResource = R.drawable.mi_brain_float_view;
        this.leftResource = R.drawable.mi_brain_float_view;
        this.rightResource = R.drawable.mi_brain_float_view;
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeResource(context2.getResources(), R.drawable.mi_brain_float_view_press, options);
        this.imageHeight = options.outHeight;
        this.windowManagerParams = layoutParams;
        this.isMove = false;
        if (context2.getResources().getConfiguration().orientation == 2) {
            this.step = 20;
        } else if (context2.getResources().getConfiguration().orientation == 1) {
            this.step = 12;
        }
        this.statusBarHeight = getStatusHeight(context2);
        this.screenWidth = context2.getResources().getDisplayMetrics().widthPixels;
        this.minY = DisplayUtils.a(45.0f);
        this.maxY = (context2.getResources().getDisplayMetrics().heightPixels - DisplayUtils.a(80.0f)) - this.imageHeight;
        Miio.b(TAG, "context.getResources().getDisplayMetrics().heightPixels" + context2.getResources().getDisplayMetrics().heightPixels + "miny: " + this.minY + "    maxY:  " + this.maxY + "    imageHeight:" + this.imageHeight);
        this.mPreferenceManager = new MiBrainPreferenceManager(context2);
        layoutParams.type = 99;
        layoutParams.format = 1;
        layoutParams.flags = layoutParams.flags | 8 | 32 | 1024;
        layoutParams.gravity = 51;
        if (this.mPreferenceManager.a() == -1.0f) {
            layoutParams.x = context2.getResources().getDisplayMetrics().widthPixels;
        } else {
            layoutParams.x = (int) this.mPreferenceManager.a();
        }
        if (this.mPreferenceManager.b() == -1.0f) {
            layoutParams.y = this.maxY;
        } else {
            layoutParams.y = (int) this.mPreferenceManager.b();
        }
        layoutParams.width = -2;
        layoutParams.height = -2;
        this.isRight = this.mPreferenceManager.d();
        setImageResource(this.darkResource);
        startTimerCount();
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        this.isTouch = true;
        this.isUp = false;
        this.xStart = 0.0f;
        this.x = motionEvent.getRawX();
        this.y = motionEvent.getRawY();
        Miio.b(TAG, "x:  " + this.x + "====Y:   " + this.y);
        switch (motionEvent.getAction()) {
            case 0:
                this.isMove = false;
                this.mTouchX = motionEvent.getX();
                this.mTouchY = motionEvent.getY();
                Miio.b(TAG, "mTouchX" + this.mTouchX + "====mTouchY" + this.mTouchY);
                cancelTimerCount();
                cancelSecondTimerCount();
                break;
            case 1:
                this.isTouch = false;
                float f = (float) (this.screenWidth / 2);
                if (this.isMove) {
                    this.isUp = true;
                    this.mX = this.mTouchX;
                    this.isMove = false;
                    if (this.x <= f) {
                        this.xStart = this.x - this.mTouchX;
                        this.x = 0.0f;
                        this.isRight = false;
                    } else {
                        this.xStart = this.x;
                        this.x = ((float) this.screenWidth) + this.mTouchX + ((float) this.image.getWidth());
                        this.isRight = true;
                    }
                    updateViewPosition();
                    this.mPreferenceManager.a(this.x);
                    this.mPreferenceManager.b(this.endY);
                    this.mPreferenceManager.b(this.isRight);
                    startTimerCount();
                } else {
                    if (this.mClickListener != null && this.canClick && !this.canMove) {
                        setImageResource(this.defaultResource);
                        this.mClickListener.onClick(this);
                    }
                    startTimerCount();
                }
                this.canMove = false;
                this.mTouchY = 0.0f;
                this.mTouchX = 0.0f;
                this.canClick = true;
                break;
            case 2:
                int abs = Math.abs((int) (motionEvent.getX() - this.mTouchX));
                int abs2 = Math.abs((int) (motionEvent.getY() - this.mTouchY));
                if (abs > 5 || abs2 > 5) {
                    if (!this.canMove) {
                        this.canClick = false;
                        break;
                    } else {
                        this.isMove = true;
                        setImageResource(this.defaultResource);
                        updateViewPosition();
                        break;
                    }
                }
        }
        return super.onTouchEvent(motionEvent);
    }

    public boolean isHide() {
        return this.isHide;
    }

    public void setHide(boolean z) {
        this.isHide = z;
    }

    public void setNoDuplicateClickListener(NoDuplicateClickListener noDuplicateClickListener) {
        this.mClickListener = noDuplicateClickListener;
    }

    private void updateViewPosition() {
        if (this.isUp) {
            this.canClick = false;
            this.distance = this.xStart - this.x;
            this.j = 0;
            this.windowManagerParams.y = (int) this.endY;
            this.handler.postDelayed(new Runnable() {
                public void run() {
                    MiBrainFloatView.this.handler.sendEmptyMessage(2);
                }
            }, 16);
            return;
        }
        this.windowManagerParams.x = (int) (this.x - this.mTouchX);
        if (this.y - this.mTouchY >= ((float) this.maxY)) {
            this.endY = (float) this.maxY;
        } else if (this.y - this.mTouchY <= ((float) this.minY)) {
            this.endY = (float) this.minY;
        } else {
            this.endY = this.y - this.mTouchY;
        }
        this.windowManagerParams.y = (int) this.endY;
        this.windowManager.updateViewLayout(this, this.windowManagerParams);
    }

    public void startTimerCount() {
        this.isCancel = false;
        this.timer = new Timer();
        this.timerTask = new TimerTask() {
            public void run() {
                if (!MiBrainFloatView.this.isTouch && !MiBrainFloatView.this.isCancel) {
                    MiBrainFloatView.this.handler.sendEmptyMessage(0);
                }
            }
        };
        this.timer.schedule(this.timerTask, 30);
    }

    public void cancelTimerCount() {
        this.isCancel = true;
        if (this.timer != null) {
            this.timer.cancel();
            this.timer = null;
        }
        if (this.timerTask != null) {
            this.timerTask.cancel();
            this.timerTask = null;
        }
    }

    public void startSecondTimerCount() {
        this.isSecondCancel = false;
        this.secondTimer = new Timer();
        this.secondTask = new TimerTask() {
            public void run() {
                if (!MiBrainFloatView.this.isSecondCancel) {
                    MiBrainFloatView.this.handler.sendEmptyMessage(1);
                }
            }
        };
        this.secondTimer.schedule(this.secondTask, 600);
    }

    public void cancelSecondTimerCount() {
        this.isSecondCancel = true;
        if (this.secondTimer != null) {
            this.secondTimer.cancel();
            this.secondTimer = null;
        }
        if (this.secondTask != null) {
            this.secondTask.cancel();
            this.secondTask = null;
        }
    }

    public static int getStatusHeight(Context context2) {
        try {
            Class<?> cls = Class.forName("com.android.internal.R$dimen");
            return context2.getResources().getDimensionPixelSize(Integer.parseInt(cls.getField(PreferenceConstantsInOpenSdk.B).get(cls.newInstance()).toString()));
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }

    public int getDefaultResource() {
        return this.defaultResource;
    }

    public boolean isCanMove() {
        return this.canMove;
    }

    public void setCanMove(boolean z) {
        this.canMove = z;
    }
}
