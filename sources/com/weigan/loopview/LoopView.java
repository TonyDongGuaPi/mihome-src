package com.weigan.loopview;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.os.Handler;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

public class LoopView extends View {
    private static final int b = ((int) (Resources.getSystem().getDisplayMetrics().density * 15.0f));
    private static final float c = 2.0f;
    private static final int d = 9;

    /* renamed from: a  reason: collision with root package name */
    private float f9866a = 1.05f;
    int centerTextColor;
    int change;
    int dividerColor;
    String[] drawingStrings;
    private Context e;
    private GestureDetector f;
    int firstLineY;
    private ScheduledFuture<?> g;
    private Paint h;
    int halfCircumference;
    Handler handler;
    private Paint i;
    int initPosition;
    boolean isLoop;
    List<String> items;
    int itemsVisibleCount;
    private Paint j;
    private int k;
    private int l = 0;
    float lineSpacingMultiplier;
    private float m;
    ScheduledExecutorService mExecutor = Executors.newSingleThreadScheduledExecutor();
    int maxTextHeight;
    int measuredHeight;
    int measuredWidth;
    private Rect n = new Rect();
    private int o;
    OnItemSelectedListener onItemSelectedListener;
    int outerTextColor;
    private int p;
    int preCurrentIndex;
    int radius;
    int secondLineY;
    long startTime = 0;
    int textSize;
    int totalScrollY;

    public enum ACTION {
        CLICK,
        FLING,
        DAGGLE
    }

    public void setLineSpacingMultiplier(float f2) {
        if (f2 > 1.0f) {
            this.lineSpacingMultiplier = f2;
        }
    }

    public void setCenterTextColor(int i2) {
        this.centerTextColor = i2;
        this.i.setColor(i2);
    }

    public void setOuterTextColor(int i2) {
        this.outerTextColor = i2;
        this.h.setColor(i2);
    }

    public void setDividerColor(int i2) {
        this.dividerColor = i2;
        this.j.setColor(i2);
    }

    public LoopView(Context context) {
        super(context);
        a(context, (AttributeSet) null);
    }

    public LoopView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        a(context, attributeSet);
    }

    public LoopView(Context context, AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        a(context, attributeSet);
    }

    private void a(Context context, AttributeSet attributeSet) {
        this.e = context;
        this.handler = new MessageHandler(this);
        this.f = new GestureDetector(context, new LoopViewGestureListener(this));
        this.f.setIsLongpressEnabled(false);
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.androidWheelView);
        this.textSize = obtainStyledAttributes.getInteger(R.styleable.androidWheelView_awv_textsize, b);
        this.textSize = (int) (Resources.getSystem().getDisplayMetrics().density * ((float) this.textSize));
        this.lineSpacingMultiplier = obtainStyledAttributes.getFloat(R.styleable.androidWheelView_awv_lineSpace, c);
        this.centerTextColor = obtainStyledAttributes.getInteger(R.styleable.androidWheelView_awv_centerTextColor, -13553359);
        this.outerTextColor = obtainStyledAttributes.getInteger(R.styleable.androidWheelView_awv_outerTextColor, -5263441);
        this.dividerColor = obtainStyledAttributes.getInteger(R.styleable.androidWheelView_awv_dividerTextColor, -3815995);
        this.itemsVisibleCount = obtainStyledAttributes.getInteger(R.styleable.androidWheelView_awv_itemsVisibleCount, 9);
        if (this.itemsVisibleCount % 2 == 0) {
            this.itemsVisibleCount = 9;
        }
        this.isLoop = obtainStyledAttributes.getBoolean(R.styleable.androidWheelView_awv_isLoop, true);
        obtainStyledAttributes.recycle();
        this.drawingStrings = new String[this.itemsVisibleCount];
        this.totalScrollY = 0;
        this.initPosition = -1;
        a();
    }

    public void setItemsVisibleCount(int i2) {
        if (i2 % 2 != 0 && i2 != this.itemsVisibleCount) {
            this.itemsVisibleCount = i2;
            this.drawingStrings = new String[this.itemsVisibleCount];
        }
    }

    private void a() {
        this.h = new Paint();
        this.h.setColor(this.outerTextColor);
        this.h.setAntiAlias(true);
        this.h.setTypeface(Typeface.MONOSPACE);
        this.h.setTextSize((float) this.textSize);
        this.i = new Paint();
        this.i.setColor(this.centerTextColor);
        this.i.setAntiAlias(true);
        this.i.setTextScaleX(this.f9866a);
        this.i.setTypeface(Typeface.MONOSPACE);
        this.i.setTextSize((float) this.textSize);
        this.j = new Paint();
        this.j.setColor(this.dividerColor);
        this.j.setAntiAlias(true);
    }

    private void b() {
        if (this.items != null) {
            this.measuredWidth = getMeasuredWidth();
            this.measuredHeight = getMeasuredHeight();
            if (this.measuredWidth != 0 && this.measuredHeight != 0) {
                this.o = getPaddingLeft();
                this.p = getPaddingRight();
                this.measuredWidth -= this.p;
                this.i.getTextBounds("星期", 0, 2, this.n);
                this.maxTextHeight = this.n.height();
                double d2 = (double) this.measuredHeight;
                Double.isNaN(d2);
                this.halfCircumference = (int) ((d2 * 3.141592653589793d) / 2.0d);
                this.maxTextHeight = (int) (((float) this.halfCircumference) / (this.lineSpacingMultiplier * ((float) (this.itemsVisibleCount - 1))));
                this.radius = this.measuredHeight / 2;
                this.firstLineY = (int) ((((float) this.measuredHeight) - (this.lineSpacingMultiplier * ((float) this.maxTextHeight))) / c);
                this.secondLineY = (int) ((((float) this.measuredHeight) + (this.lineSpacingMultiplier * ((float) this.maxTextHeight))) / c);
                if (this.initPosition == -1) {
                    if (this.isLoop) {
                        this.initPosition = (this.items.size() + 1) / 2;
                    } else {
                        this.initPosition = 0;
                    }
                }
                this.preCurrentIndex = this.initPosition;
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void smoothScroll(ACTION action) {
        cancelFuture();
        if (action == ACTION.FLING || action == ACTION.DAGGLE) {
            float f2 = this.lineSpacingMultiplier * ((float) this.maxTextHeight);
            this.l = (int) (((((float) this.totalScrollY) % f2) + f2) % f2);
            if (((float) this.l) > f2 / c) {
                this.l = (int) (f2 - ((float) this.l));
            } else {
                this.l = -this.l;
            }
        }
        this.g = this.mExecutor.scheduleWithFixedDelay(new SmoothScrollTimerTask(this, this.l), 0, 10, TimeUnit.MILLISECONDS);
    }

    /* access modifiers changed from: protected */
    public final void scrollBy(float f2) {
        cancelFuture();
        this.g = this.mExecutor.scheduleWithFixedDelay(new InertiaTimerTask(this, f2), 0, (long) 10, TimeUnit.MILLISECONDS);
    }

    public void cancelFuture() {
        if (this.g != null && !this.g.isCancelled()) {
            this.g.cancel(true);
            this.g = null;
        }
    }

    public void setNotLoop() {
        this.isLoop = false;
    }

    public final void setTextSize(float f2) {
        if (f2 > 0.0f) {
            this.textSize = (int) (this.e.getResources().getDisplayMetrics().density * f2);
            this.h.setTextSize((float) this.textSize);
            this.i.setTextSize((float) this.textSize);
        }
    }

    public final void setInitPosition(int i2) {
        if (i2 < 0) {
            this.initPosition = 0;
        } else if (this.items != null && this.items.size() > i2) {
            this.initPosition = i2;
        }
    }

    public final void setListener(OnItemSelectedListener onItemSelectedListener2) {
        this.onItemSelectedListener = onItemSelectedListener2;
    }

    public final void setItems(List<String> list) {
        this.items = list;
        b();
        invalidate();
    }

    public final int getSelectedItem() {
        return this.k;
    }

    /* access modifiers changed from: protected */
    public final void onItemSelected() {
        if (this.onItemSelectedListener != null) {
            postDelayed(new OnItemSelectedRunnable(this), 200);
        }
    }

    public void setScaleX(float f2) {
        this.f9866a = f2;
    }

    public void setCurrentPosition(int i2) {
        if (this.items != null && !this.items.isEmpty()) {
            int size = this.items.size();
            if (i2 >= 0 && i2 < size && i2 != this.k) {
                this.initPosition = i2;
                this.totalScrollY = 0;
                this.l = 0;
                invalidate();
            }
        }
    }

    /* access modifiers changed from: protected */
    public void onDraw(Canvas canvas) {
        if (this.items != null) {
            this.change = (int) (((float) this.totalScrollY) / (this.lineSpacingMultiplier * ((float) this.maxTextHeight)));
            this.preCurrentIndex = this.initPosition + (this.change % this.items.size());
            if (!this.isLoop) {
                if (this.preCurrentIndex < 0) {
                    this.preCurrentIndex = 0;
                }
                if (this.preCurrentIndex > this.items.size() - 1) {
                    this.preCurrentIndex = this.items.size() - 1;
                }
            } else {
                if (this.preCurrentIndex < 0) {
                    this.preCurrentIndex = this.items.size() + this.preCurrentIndex;
                }
                if (this.preCurrentIndex > this.items.size() - 1) {
                    this.preCurrentIndex -= this.items.size();
                }
            }
            int i2 = (int) (((float) this.totalScrollY) % (this.lineSpacingMultiplier * ((float) this.maxTextHeight)));
            for (int i3 = 0; i3 < this.itemsVisibleCount; i3++) {
                int i4 = this.preCurrentIndex - ((this.itemsVisibleCount / 2) - i3);
                if (this.isLoop) {
                    while (i4 < 0) {
                        i4 += this.items.size();
                    }
                    while (i4 > this.items.size() - 1) {
                        i4 -= this.items.size();
                    }
                    this.drawingStrings[i3] = this.items.get(i4);
                } else if (i4 < 0) {
                    this.drawingStrings[i3] = "";
                } else if (i4 > this.items.size() - 1) {
                    this.drawingStrings[i3] = "";
                } else {
                    this.drawingStrings[i3] = this.items.get(i4);
                }
            }
            Canvas canvas2 = canvas;
            canvas2.drawLine((float) this.o, (float) this.firstLineY, (float) this.measuredWidth, (float) this.firstLineY, this.j);
            canvas2.drawLine((float) this.o, (float) this.secondLineY, (float) this.measuredWidth, (float) this.secondLineY, this.j);
            for (int i5 = 0; i5 < this.itemsVisibleCount; i5++) {
                canvas.save();
                float f2 = ((float) this.maxTextHeight) * this.lineSpacingMultiplier;
                double d2 = (double) ((((float) i5) * f2) - ((float) i2));
                Double.isNaN(d2);
                double d3 = (double) this.halfCircumference;
                Double.isNaN(d3);
                double d4 = (d2 * 3.141592653589793d) / d3;
                if (d4 >= 3.141592653589793d || d4 <= 0.0d) {
                    canvas.restore();
                } else {
                    double d5 = (double) this.radius;
                    double cos = Math.cos(d4);
                    double d6 = (double) this.radius;
                    Double.isNaN(d6);
                    Double.isNaN(d5);
                    double d7 = d5 - (cos * d6);
                    double sin = Math.sin(d4);
                    double d8 = (double) this.maxTextHeight;
                    Double.isNaN(d8);
                    int i6 = (int) (d7 - ((sin * d8) / 2.0d));
                    canvas.translate(0.0f, (float) i6);
                    canvas.scale(1.0f, (float) Math.sin(d4));
                    if (i6 <= this.firstLineY && this.maxTextHeight + i6 >= this.firstLineY) {
                        canvas.save();
                        canvas.clipRect(0, 0, this.measuredWidth, this.firstLineY - i6);
                        canvas.drawText(this.drawingStrings[i5], (float) a(this.drawingStrings[i5], this.h, this.n), (float) this.maxTextHeight, this.h);
                        canvas.restore();
                        canvas.save();
                        canvas.clipRect(0, this.firstLineY - i6, this.measuredWidth, (int) f2);
                        canvas.drawText(this.drawingStrings[i5], (float) a(this.drawingStrings[i5], this.i, this.n), (float) this.maxTextHeight, this.i);
                        canvas.restore();
                    } else if (i6 <= this.secondLineY && this.maxTextHeight + i6 >= this.secondLineY) {
                        canvas.save();
                        canvas.clipRect(0, 0, this.measuredWidth, this.secondLineY - i6);
                        canvas.drawText(this.drawingStrings[i5], (float) a(this.drawingStrings[i5], this.i, this.n), (float) this.maxTextHeight, this.i);
                        canvas.restore();
                        canvas.save();
                        canvas.clipRect(0, this.secondLineY - i6, this.measuredWidth, (int) f2);
                        canvas.drawText(this.drawingStrings[i5], (float) a(this.drawingStrings[i5], this.h, this.n), (float) this.maxTextHeight, this.h);
                        canvas.restore();
                    } else if (i6 < this.firstLineY || this.maxTextHeight + i6 > this.secondLineY) {
                        canvas.clipRect(0, 0, this.measuredWidth, (int) f2);
                        canvas.drawText(this.drawingStrings[i5], (float) a(this.drawingStrings[i5], this.h, this.n), (float) this.maxTextHeight, this.h);
                    } else {
                        canvas.clipRect(0, 0, this.measuredWidth, (int) f2);
                        canvas.drawText(this.drawingStrings[i5], (float) a(this.drawingStrings[i5], this.i, this.n), (float) this.maxTextHeight, this.i);
                        this.k = this.items.indexOf(this.drawingStrings[i5]);
                    }
                    canvas.restore();
                }
            }
        }
    }

    private int a(String str, Paint paint, Rect rect) {
        paint.getTextBounds(str, 0, str.length(), rect);
        return (((this.measuredWidth - this.o) - ((int) (((float) rect.width()) * this.f9866a))) / 2) + this.o;
    }

    /* access modifiers changed from: protected */
    public void onMeasure(int i2, int i3) {
        super.onMeasure(i2, i3);
        b();
    }

    /* access modifiers changed from: protected */
    public void onLayout(boolean z, int i2, int i3, int i4, int i5) {
        super.onLayout(z, i2, i3, i4, i5);
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        boolean onTouchEvent = this.f.onTouchEvent(motionEvent);
        float f2 = this.lineSpacingMultiplier * ((float) this.maxTextHeight);
        int action = motionEvent.getAction();
        if (action == 0) {
            this.startTime = System.currentTimeMillis();
            cancelFuture();
            this.m = motionEvent.getRawY();
            if (getParent() != null) {
                getParent().requestDisallowInterceptTouchEvent(true);
            }
        } else if (action != 2) {
            if (!onTouchEvent) {
                double acos = Math.acos((double) ((((float) this.radius) - motionEvent.getY()) / ((float) this.radius)));
                double d2 = (double) this.radius;
                Double.isNaN(d2);
                double d3 = acos * d2;
                double d4 = (double) (f2 / c);
                Double.isNaN(d4);
                double d5 = d3 + d4;
                double d6 = (double) f2;
                Double.isNaN(d6);
                this.l = (int) ((((float) (((int) (d5 / d6)) - (this.itemsVisibleCount / 2))) * f2) - (((((float) this.totalScrollY) % f2) + f2) % f2));
                if (System.currentTimeMillis() - this.startTime > 120) {
                    smoothScroll(ACTION.DAGGLE);
                } else {
                    smoothScroll(ACTION.CLICK);
                }
            }
            if (getParent() != null) {
                getParent().requestDisallowInterceptTouchEvent(false);
            }
        } else {
            this.m = motionEvent.getRawY();
            this.totalScrollY = (int) (((float) this.totalScrollY) + (this.m - motionEvent.getRawY()));
            if (!this.isLoop) {
                float f3 = ((float) (-this.initPosition)) * f2;
                float size = ((float) ((this.items.size() - 1) - this.initPosition)) * f2;
                if (((float) this.totalScrollY) < f3) {
                    this.totalScrollY = (int) f3;
                } else if (((float) this.totalScrollY) > size) {
                    this.totalScrollY = (int) size;
                }
            }
        }
        invalidate();
        return true;
    }
}
