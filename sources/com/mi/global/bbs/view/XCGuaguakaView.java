package com.mi.global.bbs.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import com.mi.global.bbs.R;
import com.mi.global.bbs.model.GiftInfo;
import com.mi.global.bbs.ui.activity.ActivitiesActivity;
import com.mi.global.bbs.utils.ScratchUtil;
import com.mi.util.Coder;

public class XCGuaguakaView extends View {
    private final int COMPLETE_PERCENT;
    private final int GET_GIFT_PERCENT;
    public final int SWIPE_PAINT_WIDTH;
    public final int TEXT_DES_SIZE;
    public final int TEXT_SIZE;
    public final int TITLE_TEXT_SIZE;
    private int guaguakaHeight;
    private int guaguakaWidth;
    /* access modifiers changed from: private */
    public Bitmap mBitmap;
    /* access modifiers changed from: private */
    public volatile boolean mCanGetGift;
    private Canvas mCanvas;
    /* access modifiers changed from: private */
    public volatile boolean mCompleted;
    private String mDesText1;
    private String mDesText2;
    private Rect mDesTextBound1;
    private Rect mDesTextBound2;
    private Paint mDesTextPaint;
    private int mDesTextSize;
    private String mGiftText;
    private Rect mGiftTextBound;
    private Paint mGiftTextPaint;
    private int mGiftTextSize;
    /* access modifiers changed from: private */
    public volatile boolean mHasGetGift;
    private int mLastX;
    private int mLastY;
    private onCanGetGiftListener mOnCanGetGiftListener;
    private OnCompleteListener mOnCompleteListener;
    private Paint mOutBmpPaint;
    private Bitmap mOutterBitmap;
    private Paint mOutterPaint;
    private Path mPath;
    private Runnable mRunnable;
    private Paint mTitlePaint;
    private String mTitleText;
    private Rect mTitleTextBound;

    public interface OnCompleteListener {
        void complete();
    }

    public interface onCanGetGiftListener {
        void onCanGetGift();
    }

    public void setOnCompleteListener(OnCompleteListener onCompleteListener) {
        this.mOnCompleteListener = onCompleteListener;
    }

    public void setmOnCanGetGiftListener(onCanGetGiftListener oncangetgiftlistener) {
        this.mOnCanGetGiftListener = oncangetgiftlistener;
    }

    public XCGuaguakaView(Context context) {
        this(context, (AttributeSet) null);
    }

    public XCGuaguakaView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public XCGuaguakaView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.TEXT_SIZE = 20;
        this.TEXT_DES_SIZE = 10;
        this.TITLE_TEXT_SIZE = 20;
        this.SWIPE_PAINT_WIDTH = 15;
        this.COMPLETE_PERCENT = 30;
        this.GET_GIFT_PERCENT = 10;
        this.mCompleted = false;
        this.mCanGetGift = false;
        this.mHasGetGift = false;
        this.mRunnable = new Runnable() {
            public void run() {
                int width = XCGuaguakaView.this.getWidth();
                int height = XCGuaguakaView.this.getHeight();
                int i = width * height;
                float f = (float) i;
                int[] iArr = new int[i];
                XCGuaguakaView.this.mBitmap.getPixels(iArr, 0, width, 0, 0, width, height);
                int i2 = 0;
                float f2 = 0.0f;
                while (i2 < width) {
                    float f3 = f2;
                    for (int i3 = 0; i3 < height; i3++) {
                        if (iArr[(i3 * width) + i2] == 0) {
                            f3 += 1.0f;
                        }
                    }
                    i2++;
                    f2 = f3;
                }
                if (f2 > 0.0f && f > 0.0f) {
                    int i4 = (int) ((f2 * 100.0f) / f);
                    Log.v("czm", "percent=" + i4);
                    if (!XCGuaguakaView.this.mHasGetGift && i4 > 10) {
                        boolean unused = XCGuaguakaView.this.mCanGetGift = true;
                        XCGuaguakaView.this.postInvalidate();
                    }
                    if (i4 > 30) {
                        boolean unused2 = XCGuaguakaView.this.mCompleted = true;
                        XCGuaguakaView.this.postInvalidate();
                    }
                }
            }
        };
        initView();
    }

    private void initView() {
        this.mOutterPaint = new Paint();
        this.mOutBmpPaint = new Paint();
        this.mTitlePaint = new Paint();
        this.mPath = new Path();
        this.mOutterBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.bbs_guaguaka_bg);
        this.mGiftText = "";
        this.mDesText1 = "";
        this.mDesText2 = "";
        this.mTitleText = ScratchUtil.getGameScratchTitle();
        this.mGiftTextBound = new Rect();
        this.mDesTextBound1 = new Rect();
        this.mDesTextBound2 = new Rect();
        this.mTitleTextBound = new Rect();
        this.mGiftTextPaint = new Paint();
        this.mDesTextPaint = new Paint();
        this.mDesTextSize = Coder.a(getContext(), 10.0f);
        this.mGiftTextSize = Coder.a(getContext(), 20.0f);
    }

    /* access modifiers changed from: protected */
    public void onMeasure(int i, int i2) {
        super.onMeasure(i, i2);
        this.guaguakaWidth = getMeasuredWidth();
        this.guaguakaHeight = getMeasuredHeight();
        this.mBitmap = Bitmap.createBitmap(this.guaguakaWidth, this.guaguakaHeight, Bitmap.Config.ARGB_8888);
        this.mCanvas = new Canvas(this.mBitmap);
        setOutterPaint();
        setOutBmpPaint();
        setTextPaint();
        setDesTextPaint();
        setTitleTextPaint();
        this.mCanvas.drawRoundRect(new RectF(0.0f, 0.0f, (float) this.guaguakaWidth, (float) this.guaguakaHeight), 30.0f, 30.0f, this.mOutBmpPaint);
        this.mCanvas.drawBitmap(this.mOutterBitmap, (Rect) null, new RectF(0.0f, 0.0f, (float) this.guaguakaWidth, (float) this.guaguakaHeight), (Paint) null);
        this.mCanvas.drawText(this.mTitleText, (float) ((this.guaguakaWidth / 2) - (this.mTitleTextBound.width() / 2)), (float) ((this.guaguakaHeight / 2) + (this.mTitleTextBound.height() / 2)), this.mTitlePaint);
    }

    private void setOutterPaint() {
        this.mOutterPaint.setColor(Color.parseColor("#c3c3c3"));
        this.mOutterPaint.setAntiAlias(true);
        this.mOutterPaint.setDither(true);
        this.mOutterPaint.setStrokeJoin(Paint.Join.ROUND);
        this.mOutterPaint.setStrokeCap(Paint.Cap.ROUND);
        this.mOutterPaint.setStyle(Paint.Style.STROKE);
        this.mOutterPaint.setStrokeWidth((float) Coder.a(getContext(), 15.0f));
    }

    private void setOutBmpPaint() {
        this.mOutBmpPaint.setColor(Color.parseColor("#c3c3c3"));
        this.mOutBmpPaint.setAntiAlias(true);
        this.mOutBmpPaint.setDither(true);
        this.mOutBmpPaint.setStrokeJoin(Paint.Join.ROUND);
        this.mOutBmpPaint.setStrokeCap(Paint.Cap.ROUND);
        this.mOutBmpPaint.setStyle(Paint.Style.FILL);
        this.mOutBmpPaint.setStrokeWidth((float) Coder.a(getContext(), 10.0f));
    }

    private void setTextPaint() {
        this.mGiftTextPaint.setColor(Color.parseColor(ActivitiesActivity.ONLINE_ACTIVITY_TAB_TEXT_COLOR_SELECT));
        this.mGiftTextPaint.setStyle(Paint.Style.FILL);
        this.mGiftTextPaint.setTypeface(Typeface.DEFAULT_BOLD);
        this.mGiftTextPaint.setTextSize((float) this.mGiftTextSize);
        this.mGiftTextPaint.getTextBounds(this.mGiftText, 0, this.mGiftText.length(), this.mGiftTextBound);
    }

    private void setDesTextPaint() {
        this.mDesTextPaint.setColor(com.libra.Color.b);
        this.mDesTextPaint.setStyle(Paint.Style.FILL);
        this.mDesTextPaint.setTextSize((float) this.mDesTextSize);
        this.mDesTextPaint.getTextBounds(this.mDesText1, 0, this.mDesText1.length(), this.mDesTextBound1);
        this.mDesTextPaint.getTextBounds(this.mDesText2, 0, this.mDesText2.length(), this.mDesTextBound2);
    }

    private void setTitleTextPaint() {
        this.mTitlePaint.setColor(-1);
        this.mTitlePaint.setStyle(Paint.Style.FILL);
        this.mTitlePaint.setTextSize((float) Coder.a(getContext(), 20.0f));
        this.mTitlePaint.getTextBounds(this.mTitleText, 0, this.mTitleText.length(), this.mTitleTextBound);
    }

    /* access modifiers changed from: protected */
    public void onDraw(Canvas canvas) {
        canvas.drawRect(new RectF(0.0f, 0.0f, (float) this.guaguakaWidth, (float) this.guaguakaHeight), this.mTitlePaint);
        canvas.drawText(this.mGiftText, (float) ((getWidth() / 2) - (this.mGiftTextBound.width() / 2)), (float) ((getHeight() / 2) - (this.mGiftTextBound.height() / 2)), this.mGiftTextPaint);
        canvas.drawText(this.mDesText1, (float) ((getWidth() / 2) - (this.mDesTextBound1.width() / 2)), (float) ((getHeight() / 2) + this.mGiftTextBound.height()), this.mDesTextPaint);
        canvas.drawText(this.mDesText2, (float) ((getWidth() / 2) - (this.mDesTextBound2.width() / 2)), (float) ((getHeight() / 2) + this.mGiftTextBound.height() + this.mDesTextBound1.height()), this.mDesTextPaint);
        if (this.mCompleted && this.mOnCompleteListener != null) {
            this.mOnCompleteListener.complete();
        }
        if (!this.mHasGetGift && this.mCanGetGift && this.mOnCanGetGiftListener != null) {
            this.mOnCanGetGiftListener.onCanGetGift();
            this.mHasGetGift = true;
        }
        if (!this.mCompleted) {
            drawPath();
            canvas.drawBitmap(this.mBitmap, 0.0f, 0.0f, (Paint) null);
        }
    }

    private void drawPath() {
        this.mOutterPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_OUT));
        this.mCanvas.drawPath(this.mPath, this.mOutterPaint);
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        int action = motionEvent.getAction();
        int x = (int) motionEvent.getX();
        int y = (int) motionEvent.getY();
        switch (action) {
            case 0:
                this.mLastX = x;
                this.mLastY = y;
                this.mPath.moveTo((float) this.mLastX, (float) this.mLastY);
                break;
            case 1:
                new Thread(this.mRunnable).start();
                break;
            case 2:
                int abs = Math.abs(x - this.mLastX);
                int abs2 = Math.abs(y - this.mLastY);
                if (abs > 3 || abs2 > 3) {
                    this.mPath.lineTo((float) x, (float) y);
                }
                this.mLastX = x;
                this.mLastY = y;
                break;
        }
        invalidate();
        return true;
    }

    public void setGiftContent(GiftInfo giftInfo) {
        this.mGiftText = giftInfo.getName();
        try {
            String[] split = giftInfo.getDesc().split(",");
            this.mDesText1 = split[0];
            this.mDesText2 = split[1];
        } catch (Exception unused) {
            this.mDesText1 = "check  at mi.com/in ";
            this.mDesText2 = "use before 22.09.2016";
        }
        invalidate();
    }
}
