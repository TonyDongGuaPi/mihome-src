package com.xiaomi.passport.ui.settings;

import android.animation.ValueAnimator;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.graphics.Xfermode;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.GravityCompat;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SectionIndexer;
import android.widget.TextView;
import com.xiaomi.passport.ui.R;
import com.xiaomi.smarthome.download.Constants;
import java.lang.ref.WeakReference;
import java.util.Arrays;

@SuppressLint({"AppCompatCustomView"})
public class AlphabetFastIndexer extends ImageView {
    private static final int FADE_DELAYED = 1500;
    private static final int MSG_FADE = 1;
    private static final String STARRED_LABEL = "★";
    public static final String STARRED_TITLE = "!";
    public static final int STATE_DRAGGING = 1;
    public static final int STATE_NONE = 0;
    private Handler mHandler;
    private int mLastAlphabetIndex;
    /* access modifiers changed from: private */
    public int mListScrollState;
    private AdapterView<?> mListView;
    /* access modifiers changed from: private */
    public TextView mOverlay;
    private Drawable mOverlayBackground;
    private int mOverlayLeftMargin;
    private int mOverlayTextColor;
    private int mOverlayTextSize;
    private int mOverlayTopMargin;
    private Runnable mRefreshMaskRunnable;
    private int mState;
    private ValueAnimator.AnimatorUpdateListener mTextHilightAnimListener;
    /* access modifiers changed from: private */
    public TextHilighter mTextHilighter;
    private int mVerticalPosition;

    private static class TextHilighter {
        int mActivatedColor;
        ValueAnimator mAnimator;
        BitmapDrawable mBackground;
        Bitmap mBmpBuffer;
        Canvas mCanvas;
        Xfermode mClearMode;
        Xfermode mDstInMode;
        int mHilightColor;
        String[] mIndexes;
        int mNormalColor;
        Paint mPaint = new Paint();
        Rect mSrcBounds = new Rect();
        Rect mTextBound = new Rect();
        Rect mTextBoundIntersect;
        float mXCenter;
        float mYCenter;

        TextHilighter(Context context, TypedArray typedArray) {
            Resources resources = context.getResources();
            CharSequence[] textArray = typedArray.getTextArray(R.styleable.AlphabetFastIndexer_indexerTable);
            if (textArray != null) {
                this.mIndexes = new String[textArray.length];
                int length = textArray.length;
                int i = 0;
                int i2 = 0;
                while (i < length) {
                    this.mIndexes[i2] = textArray[i].toString();
                    i++;
                    i2++;
                }
            } else {
                this.mIndexes = resources.getStringArray(R.array.alphabet_table);
            }
            this.mNormalColor = typedArray.getColor(R.styleable.AlphabetFastIndexer_indexerTextColor, resources.getColor(R.color.passport_alphabet_indexer_text_color));
            this.mActivatedColor = typedArray.getColor(R.styleable.AlphabetFastIndexer_indexerTextActivatedColor, resources.getColor(R.color.passport_alphabet_indexer_activated_text_color));
            this.mHilightColor = typedArray.getColor(R.styleable.AlphabetFastIndexer_indexerTextHighlightColor, resources.getColor(R.color.passport_alphabet_indexer_highlight_text_color));
            this.mPaint.setTextSize(typedArray.getDimension(R.styleable.AlphabetFastIndexer_indexerTextSize, resources.getDimension(R.dimen.passport_alphabet_indexer_text_size)));
            this.mPaint.setAntiAlias(true);
            this.mPaint.setTextAlign(Paint.Align.CENTER);
            this.mPaint.setTypeface(Typeface.DEFAULT_BOLD);
            Drawable drawable = typedArray.getDrawable(R.styleable.AlphabetFastIndexer_indexerTextHighligtBackground);
            drawable = drawable == null ? resources.getDrawable(R.drawable.passport_alphabet_indexer_text_highlight_bg) : drawable;
            if (drawable != null && (drawable instanceof BitmapDrawable)) {
                this.mBackground = (BitmapDrawable) drawable;
                this.mBmpBuffer = this.mBackground.getBitmap().copy(Bitmap.Config.ARGB_8888, true);
                this.mCanvas = new Canvas(this.mBmpBuffer);
                this.mTextBoundIntersect = new Rect();
                this.mClearMode = new PorterDuffXfermode(PorterDuff.Mode.CLEAR);
                this.mDstInMode = new PorterDuffXfermode(PorterDuff.Mode.DST_IN);
            }
            update(0.0f, 0.0f);
        }

        /* access modifiers changed from: package-private */
        public void update(float f, float f2) {
            this.mXCenter = f;
            this.mYCenter = f2;
            float intrinsicWidth = ((float) this.mBackground.getIntrinsicWidth()) / 2.0f;
            float intrinsicHeight = ((float) this.mBackground.getIntrinsicHeight()) / 2.0f;
            this.mTextBound.set((int) ((this.mXCenter - intrinsicWidth) + 1.0f), (int) (this.mYCenter - intrinsicHeight), (int) (this.mXCenter + intrinsicWidth + 1.0f), (int) (this.mYCenter + intrinsicHeight));
        }

        /* access modifiers changed from: package-private */
        public void draw(Canvas canvas, boolean z, int i, float f, float f2) {
            Paint paint = this.mPaint;
            String str = TextUtils.equals(this.mIndexes[i], "!") ? AlphabetFastIndexer.STARRED_LABEL : this.mIndexes[i];
            paint.getTextBounds(str, 0, str.length(), this.mSrcBounds);
            float width = (float) this.mSrcBounds.width();
            float height = (float) this.mSrcBounds.height();
            paint.setColor(z ? this.mActivatedColor : this.mNormalColor);
            canvas.drawText(str, f, f2 - (((float) (this.mSrcBounds.top + this.mSrcBounds.bottom)) / 2.0f), paint);
            float f3 = width / 2.0f;
            float f4 = height / 2.0f;
            if (this.mTextBoundIntersect.intersect((int) (f - f3), (int) (f2 - f4), (int) (f3 + f), (int) (f4 + f2))) {
                float f5 = f - ((float) this.mTextBound.left);
                float f6 = f2 - ((float) this.mTextBound.top);
                paint.setColor(this.mHilightColor);
                this.mCanvas.drawText(str, f5, f6 - (((float) (this.mSrcBounds.top + this.mSrcBounds.bottom)) / 2.0f), paint);
                this.mTextBoundIntersect.set(this.mTextBound);
            }
        }

        /* access modifiers changed from: package-private */
        public void beginDraw() {
            Paint paint = this.mPaint;
            Xfermode xfermode = paint.getXfermode();
            paint.setXfermode(this.mClearMode);
            this.mCanvas.drawPaint(paint);
            paint.setXfermode(xfermode);
            this.mBackground.setBounds(0, 0, this.mTextBound.width(), this.mTextBound.height());
            this.mBackground.draw(this.mCanvas);
            this.mTextBoundIntersect.set(this.mTextBound);
        }

        /* access modifiers changed from: package-private */
        public void endDraw(Canvas canvas) {
            Paint paint = this.mBackground.getPaint();
            Xfermode xfermode = paint.getXfermode();
            paint.setXfermode(this.mDstInMode);
            this.mBackground.draw(this.mCanvas);
            paint.setXfermode(xfermode);
            canvas.drawBitmap(this.mBmpBuffer, (Rect) null, this.mTextBound, (Paint) null);
        }

        /* access modifiers changed from: package-private */
        public void startSlidding(float f, ValueAnimator.AnimatorUpdateListener animatorUpdateListener) {
            float f2;
            if (this.mAnimator != null) {
                this.mAnimator.cancel();
            }
            if (this.mTextBound == null) {
                f2 = f;
            } else {
                f2 = ((float) (this.mTextBound.top + this.mTextBound.bottom)) / 2.0f;
            }
            this.mAnimator = ValueAnimator.ofFloat(new float[]{f2, f});
            this.mAnimator.addUpdateListener(animatorUpdateListener);
            this.mAnimator.setDuration(200);
            this.mAnimator.start();
        }
    }

    public AlphabetFastIndexer(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public AlphabetFastIndexer(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.mTextHilightAnimListener = new ValueAnimator.AnimatorUpdateListener() {
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                AlphabetFastIndexer.this.mTextHilighter.update(((float) AlphabetFastIndexer.this.getWidth()) / 2.0f, ((Float) valueAnimator.getAnimatedValue()).floatValue());
                AlphabetFastIndexer.this.postInvalidate();
            }
        };
        this.mListScrollState = 0;
        this.mState = 0;
        this.mRefreshMaskRunnable = new Runnable() {
            public void run() {
                AlphabetFastIndexer.this.refreshMask();
            }
        };
        this.mHandler = new Handler() {
            public void handleMessage(Message message) {
                if (message.what == 1 && AlphabetFastIndexer.this.mOverlay != null) {
                    AlphabetFastIndexer.this.mOverlay.setVisibility(8);
                }
            }
        };
        Resources resources = context.getResources();
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.AlphabetFastIndexer, 0, attributeSet.getStyleAttribute() != 0 ? attributeSet.getStyleAttribute() : i);
        this.mTextHilighter = new TextHilighter(context, obtainStyledAttributes);
        this.mOverlayLeftMargin = obtainStyledAttributes.getDimensionPixelOffset(R.styleable.AlphabetFastIndexer_overlayMarginLeft, resources.getDimensionPixelOffset(R.dimen.passport_alphabet_indexer_overlay_offset));
        this.mOverlayTopMargin = obtainStyledAttributes.getDimensionPixelOffset(R.styleable.AlphabetFastIndexer_overlayMarginTop, resources.getDimensionPixelOffset(R.dimen.passport_alphabet_indexer_overlay_padding_top));
        this.mOverlayTextSize = obtainStyledAttributes.getDimensionPixelSize(R.styleable.AlphabetFastIndexer_overlayTextSize, resources.getDimensionPixelSize(R.dimen.passport_alphabet_indexer_overlay_text_size));
        this.mOverlayTextColor = obtainStyledAttributes.getColor(R.styleable.AlphabetFastIndexer_overlayTextColor, resources.getColor(R.color.passport_alphabet_indexer_overlay_text_color));
        this.mOverlayBackground = obtainStyledAttributes.getDrawable(R.styleable.AlphabetFastIndexer_overlayBackground);
        if (this.mOverlayBackground == null) {
            this.mOverlayBackground = resources.getDrawable(R.drawable.passport_alphabet_indexer_overlay);
        }
        Drawable drawable = obtainStyledAttributes.getDrawable(R.styleable.AlphabetFastIndexer_indexerBackground);
        drawable = drawable == null ? resources.getDrawable(R.drawable.passport_alphabet_indexer_bg) : drawable;
        if (Build.VERSION.SDK_INT < 16) {
            setBackgroundDrawable(drawable);
        } else {
            setBackground(drawable);
        }
        obtainStyledAttributes.recycle();
        if (Build.VERSION.SDK_INT >= 17) {
            this.mVerticalPosition = GravityCompat.END;
        } else {
            this.mVerticalPosition = 5;
        }
    }

    public void setVerticalPosition(boolean z) {
        if (Build.VERSION.SDK_INT >= 17) {
            this.mVerticalPosition = z ? GravityCompat.END : GravityCompat.START;
        } else {
            this.mVerticalPosition = z ? 5 : 3;
        }
    }

    public void setOverlayOffset(int i, int i2) {
        this.mOverlayLeftMargin = i;
        this.mOverlayTopMargin = i2;
    }

    /* access modifiers changed from: protected */
    public void onSizeChanged(int i, int i2, int i3, int i4) {
        super.onSizeChanged(i, i2, i3, i4);
        this.mLastAlphabetIndex = -1;
        post(this.mRefreshMaskRunnable);
    }

    /* access modifiers changed from: protected */
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int paddingTop = getPaddingTop();
        int height = (getHeight() - paddingTop) - getPaddingBottom();
        if (height > 0) {
            String[] strArr = this.mTextHilighter.mIndexes;
            float length = ((float) height) / ((float) strArr.length);
            float width = ((float) getWidth()) / 2.0f;
            float f = ((float) paddingTop) + (length / 2.0f);
            if (this.mTextHilighter.mXCenter == 0.0f || this.mTextHilighter.mYCenter == 0.0f) {
                this.mTextHilighter.update(width, f);
            }
            this.mTextHilighter.beginDraw();
            for (int i = 0; i < strArr.length; i++) {
                this.mTextHilighter.draw(canvas, isPressed(), i, width, f);
                f += length;
            }
            this.mTextHilighter.endDraw(canvas);
        }
    }

    public void attatch(AdapterView<?> adapterView) {
        if (this.mListView != adapterView) {
            detach();
            if (adapterView != null) {
                this.mLastAlphabetIndex = -1;
                this.mListView = adapterView;
                Context context = getContext();
                FrameLayout frameLayout = (FrameLayout) getParent();
                this.mOverlay = new TextView(context);
                FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(-2, -2, 17);
                layoutParams.leftMargin = this.mOverlayLeftMargin;
                layoutParams.topMargin = this.mOverlayTopMargin;
                this.mOverlay.setLayoutParams(layoutParams);
                this.mOverlay.measure(View.MeasureSpec.makeMeasureSpec(0, 0), View.MeasureSpec.makeMeasureSpec(0, 0));
                if (Build.VERSION.SDK_INT < 16) {
                    this.mOverlay.setBackgroundDrawable(this.mOverlayBackground);
                } else {
                    this.mOverlay.setBackground(this.mOverlayBackground);
                }
                this.mOverlay.setGravity(17);
                this.mOverlay.setTextSize((float) this.mOverlayTextSize);
                this.mOverlay.setTextColor(this.mOverlayTextColor);
                this.mOverlay.setVisibility(8);
                frameLayout.addView(this.mOverlay);
                FrameLayout.LayoutParams layoutParams2 = (FrameLayout.LayoutParams) getLayoutParams();
                layoutParams2.gravity = this.mVerticalPosition | 48;
                setLayoutParams(layoutParams2);
                refreshMask();
            }
        }
    }

    public void detach() {
        if (this.mListView != null) {
            stop(0);
            ((FrameLayout) getParent()).removeView(this.mOverlay);
            setVisibility(8);
            this.mListView = null;
        }
    }

    public AbsListView.OnScrollListener decorateScrollListener(AbsListView.OnScrollListener onScrollListener) {
        return new OnScrollerDecorator(this, onScrollListener);
    }

    public void drawThumb(CharSequence charSequence) {
        if (this.mState == 0 && this.mListScrollState == 2) {
            drawThumbInternal(charSequence);
        }
    }

    /* access modifiers changed from: private */
    public void refreshMask() {
        int sectionForPosition;
        if (this.mListView != null) {
            int i = 0;
            SectionIndexer sectionIndexer = getSectionIndexer();
            if (!(sectionIndexer == null || (sectionForPosition = sectionIndexer.getSectionForPosition(this.mListView.getFirstVisiblePosition() - getListOffset())) == -1)) {
                String str = (String) sectionIndexer.getSections()[sectionForPosition];
                if (!TextUtils.isEmpty(str)) {
                    i = Arrays.binarySearch(this.mTextHilighter.mIndexes, str);
                }
            }
            if (this.mLastAlphabetIndex != i) {
                this.mLastAlphabetIndex = i;
                if (1 != this.mState) {
                    slidTextHilightBackground(this.mLastAlphabetIndex);
                }
                invalidate();
            }
        }
    }

    public int getIndexerIntrinsicWidth() {
        Drawable background = getBackground();
        if (background != null) {
            return background.getIntrinsicWidth();
        }
        return 0;
    }

    /* JADX WARNING: Removed duplicated region for block: B:10:0x001d  */
    /* JADX WARNING: Removed duplicated region for block: B:14:? A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private android.widget.SectionIndexer getSectionIndexer() {
        /*
            r4 = this;
            android.widget.AdapterView<?> r0 = r4.mListView
            r1 = 0
            if (r0 != 0) goto L_0x0006
            return r1
        L_0x0006:
            android.widget.AdapterView<?> r0 = r4.mListView
            android.widget.Adapter r0 = r0.getAdapter()
        L_0x000c:
            boolean r2 = r0 instanceof android.widget.SectionIndexer
            if (r2 != 0) goto L_0x001b
            boolean r3 = r0 instanceof android.widget.WrapperListAdapter
            if (r3 == 0) goto L_0x001b
            android.widget.WrapperListAdapter r0 = (android.widget.WrapperListAdapter) r0
            android.widget.ListAdapter r0 = r0.getWrappedAdapter()
            goto L_0x000c
        L_0x001b:
            if (r2 == 0) goto L_0x0020
            r1 = r0
            android.widget.SectionIndexer r1 = (android.widget.SectionIndexer) r1
        L_0x0020:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.passport.ui.settings.AlphabetFastIndexer.getSectionIndexer():android.widget.SectionIndexer");
    }

    private int getListOffset() {
        if (this.mListView instanceof ListView) {
            return ((ListView) this.mListView).getHeaderViewsCount();
        }
        return 0;
    }

    private static class OnScrollerDecorator implements AbsListView.OnScrollListener {
        private final WeakReference<AlphabetFastIndexer> mIndexerRef;
        private final AbsListView.OnScrollListener mListener;

        public OnScrollerDecorator(AlphabetFastIndexer alphabetFastIndexer, AbsListView.OnScrollListener onScrollListener) {
            this.mIndexerRef = new WeakReference<>(alphabetFastIndexer);
            this.mListener = onScrollListener;
        }

        public void onScroll(AbsListView absListView, int i, int i2, int i3) {
            AlphabetFastIndexer alphabetFastIndexer = (AlphabetFastIndexer) this.mIndexerRef.get();
            if (alphabetFastIndexer != null) {
                alphabetFastIndexer.refreshMask();
            }
            if (this.mListener != null) {
                this.mListener.onScroll(absListView, i, i2, i3);
            }
        }

        public void onScrollStateChanged(AbsListView absListView, int i) {
            AlphabetFastIndexer alphabetFastIndexer = (AlphabetFastIndexer) this.mIndexerRef.get();
            if (alphabetFastIndexer != null) {
                int unused = alphabetFastIndexer.mListScrollState = i;
            }
            if (this.mListener != null) {
                this.mListener.onScrollStateChanged(absListView, i);
            }
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:12:0x0029, code lost:
        r2 = getPostion(r5.getY(), r0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:13:0x0031, code lost:
        if (r2 >= 0) goto L_0x0039;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:14:0x0033, code lost:
        r4.mListView.setSelection(0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:15:0x0039, code lost:
        scrollTo(r0, r2);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:16:0x003e, code lost:
        if (r4.mTextHilighter == null) goto L_0x007d;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:18:0x0050, code lost:
        if (r5.getY() <= ((float) (getTop() + getPaddingTop()))) goto L_0x007d;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:20:0x0062, code lost:
        if (r5.getY() >= ((float) (getBottom() - getPaddingBottom()))) goto L_0x007d;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:21:0x0064, code lost:
        r4.mTextHilighter.update((float) (getWidth() / 2), r5.getY());
        postInvalidate();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:22:0x0078, code lost:
        stop(1500);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:23:0x007d, code lost:
        return true;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean onTouchEvent(android.view.MotionEvent r5) {
        /*
            r4 = this;
            android.widget.AdapterView<?> r0 = r4.mListView
            r1 = 0
            if (r0 != 0) goto L_0x0009
            r4.stop(r1)
            return r1
        L_0x0009:
            android.widget.SectionIndexer r0 = r4.getSectionIndexer()
            if (r0 != 0) goto L_0x0013
            r4.stop(r1)
            return r1
        L_0x0013:
            int r2 = r5.getAction()
            r2 = r2 & 255(0xff, float:3.57E-43)
            r3 = 1
            switch(r2) {
                case 0: goto L_0x0024;
                case 1: goto L_0x001e;
                case 2: goto L_0x0029;
                case 3: goto L_0x001e;
                default: goto L_0x001d;
            }
        L_0x001d:
            goto L_0x0078
        L_0x001e:
            int r5 = r4.mLastAlphabetIndex
            r4.slidTextHilightBackground(r5)
            goto L_0x0078
        L_0x0024:
            r4.mState = r3
            r4.setPressed(r3)
        L_0x0029:
            float r2 = r5.getY()
            int r2 = r4.getPostion(r2, r0)
            if (r2 >= 0) goto L_0x0039
            android.widget.AdapterView<?> r5 = r4.mListView
            r5.setSelection(r1)
            goto L_0x007d
        L_0x0039:
            r4.scrollTo(r0, r2)
            com.xiaomi.passport.ui.settings.AlphabetFastIndexer$TextHilighter r0 = r4.mTextHilighter
            if (r0 == 0) goto L_0x007d
            float r0 = r5.getY()
            int r1 = r4.getTop()
            int r2 = r4.getPaddingTop()
            int r1 = r1 + r2
            float r1 = (float) r1
            int r0 = (r0 > r1 ? 1 : (r0 == r1 ? 0 : -1))
            if (r0 <= 0) goto L_0x007d
            float r0 = r5.getY()
            int r1 = r4.getBottom()
            int r2 = r4.getPaddingBottom()
            int r1 = r1 - r2
            float r1 = (float) r1
            int r0 = (r0 > r1 ? 1 : (r0 == r1 ? 0 : -1))
            if (r0 >= 0) goto L_0x007d
            com.xiaomi.passport.ui.settings.AlphabetFastIndexer$TextHilighter r0 = r4.mTextHilighter
            int r1 = r4.getWidth()
            int r1 = r1 / 2
            float r1 = (float) r1
            float r5 = r5.getY()
            r0.update(r1, r5)
            r4.postInvalidate()
            goto L_0x007d
        L_0x0078:
            r5 = 1500(0x5dc, float:2.102E-42)
            r4.stop(r5)
        L_0x007d:
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.passport.ui.settings.AlphabetFastIndexer.onTouchEvent(android.view.MotionEvent):boolean");
    }

    /* access modifiers changed from: package-private */
    public void stop(int i) {
        setPressed(false);
        this.mState = 0;
        postInvalidate();
        this.mHandler.removeMessages(1);
        if (i > 0) {
            this.mHandler.sendMessageDelayed(this.mHandler.obtainMessage(1), (long) i);
        } else if (this.mOverlay != null) {
            this.mOverlay.setVisibility(8);
        }
    }

    private int getPostion(float f, SectionIndexer sectionIndexer) {
        Object[] sections = sectionIndexer.getSections();
        if (sections == null) {
            return -1;
        }
        int paddingTop = getPaddingTop();
        int height = (getHeight() - paddingTop) - getPaddingBottom();
        if (height <= 0) {
            return -1;
        }
        int length = (int) (((float) this.mTextHilighter.mIndexes.length) * ((f - ((float) paddingTop)) / ((float) height)));
        if (length < 0) {
            return -1;
        }
        if (length >= this.mTextHilighter.mIndexes.length) {
            return sections.length;
        }
        int binarySearch = Arrays.binarySearch(sections, this.mTextHilighter.mIndexes[length]);
        if (binarySearch < 0) {
            binarySearch = (-binarySearch) - 2;
        }
        if (binarySearch < 0) {
            return 0;
        }
        return binarySearch;
    }

    /* JADX WARNING: Removed duplicated region for block: B:26:0x005f A[LOOP:1: B:23:0x0057->B:26:0x005f, LOOP_END] */
    /* JADX WARNING: Removed duplicated region for block: B:34:0x0086  */
    /* JADX WARNING: Removed duplicated region for block: B:37:0x008d  */
    /* JADX WARNING: Removed duplicated region for block: B:38:0x009f  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void scrollTo(android.widget.SectionIndexer r18, int r19) {
        /*
            r17 = this;
            r0 = r17
            r1 = r18
            r2 = r19
            android.widget.AdapterView<?> r3 = r0.mListView
            int r3 = r3.getCount()
            int r4 = r17.getListOffset()
            float r5 = (float) r3
            r6 = 1065353216(0x3f800000, float:1.0)
            float r6 = r6 / r5
            r5 = 1090519040(0x41000000, float:8.0)
            float r6 = r6 / r5
            java.lang.Object[] r5 = r18.getSections()
            r8 = 1
            if (r5 == 0) goto L_0x00b6
            int r9 = r5.length
            if (r9 <= r8) goto L_0x00b6
            int r9 = r5.length
            if (r2 < r9) goto L_0x0027
            int r10 = r9 + -1
            goto L_0x0028
        L_0x0027:
            r10 = r2
        L_0x0028:
            int r11 = r1.getPositionForSection(r10)
            int r12 = r10 + 1
            int r13 = r9 + -1
            if (r10 >= r13) goto L_0x0037
            int r13 = r1.getPositionForSection(r12)
            goto L_0x0038
        L_0x0037:
            r13 = r3
        L_0x0038:
            if (r13 != r11) goto L_0x004f
            r14 = r10
            r15 = r11
        L_0x003c:
            if (r14 <= 0) goto L_0x004c
            int r14 = r14 + -1
            int r15 = r1.getPositionForSection(r14)
            if (r15 == r11) goto L_0x0047
            goto L_0x0051
        L_0x0047:
            if (r14 != 0) goto L_0x003c
            r14 = r10
            r11 = 0
            goto L_0x0052
        L_0x004c:
            r11 = r10
            r14 = r11
            goto L_0x0052
        L_0x004f:
            r14 = r10
            r15 = r11
        L_0x0051:
            r11 = r14
        L_0x0052:
            int r16 = r12 + 1
            r7 = r12
            r12 = r16
        L_0x0057:
            if (r12 >= r9) goto L_0x0065
            int r8 = r1.getPositionForSection(r12)
            if (r8 != r13) goto L_0x0065
            int r12 = r12 + 1
            int r7 = r7 + 1
            r8 = 1
            goto L_0x0057
        L_0x0065:
            float r1 = (float) r14
            float r8 = (float) r9
            float r1 = r1 / r8
            float r7 = (float) r7
            float r7 = r7 / r8
            float r2 = (float) r2
            float r2 = r2 / r8
            if (r14 != r10) goto L_0x0075
            float r8 = r2 - r1
            int r6 = (r8 > r6 ? 1 : (r8 == r6 ? 0 : -1))
            if (r6 >= 0) goto L_0x0075
            goto L_0x0081
        L_0x0075:
            int r13 = r13 - r15
            float r6 = (float) r13
            float r2 = r2 - r1
            float r6 = r6 * r2
            float r7 = r7 - r1
            float r6 = r6 / r7
            int r1 = java.lang.Math.round(r6)
            int r15 = r15 + r1
        L_0x0081:
            r1 = 1
            int r2 = r3 + -1
            if (r15 <= r2) goto L_0x0087
            r15 = r2
        L_0x0087:
            android.widget.AdapterView<?> r1 = r0.mListView
            boolean r1 = r1 instanceof android.widget.ExpandableListView
            if (r1 == 0) goto L_0x009f
            android.widget.AdapterView<?> r1 = r0.mListView
            android.widget.ExpandableListView r1 = (android.widget.ExpandableListView) r1
            int r15 = r15 + r4
            long r2 = android.widget.ExpandableListView.getPackedPositionForGroup(r15)
            int r2 = r1.getFlatListPosition(r2)
            r3 = 0
            r1.setSelectionFromTop(r2, r3)
            goto L_0x00ec
        L_0x009f:
            r3 = 0
            android.widget.AdapterView<?> r1 = r0.mListView
            boolean r1 = r1 instanceof android.widget.ListView
            if (r1 == 0) goto L_0x00af
            android.widget.AdapterView<?> r1 = r0.mListView
            android.widget.ListView r1 = (android.widget.ListView) r1
            int r15 = r15 + r4
            r1.setSelectionFromTop(r15, r3)
            goto L_0x00ec
        L_0x00af:
            android.widget.AdapterView<?> r1 = r0.mListView
            int r15 = r15 + r4
            r1.setSelection(r15)
            goto L_0x00ec
        L_0x00b6:
            int r1 = r2 * r3
            float r1 = (float) r1
            int r1 = java.lang.Math.round(r1)
            android.widget.AdapterView<?> r2 = r0.mListView
            boolean r2 = r2 instanceof android.widget.ExpandableListView
            if (r2 == 0) goto L_0x00d5
            android.widget.AdapterView<?> r2 = r0.mListView
            android.widget.ExpandableListView r2 = (android.widget.ExpandableListView) r2
            int r1 = r1 + r4
            long r3 = android.widget.ExpandableListView.getPackedPositionForGroup(r1)
            int r1 = r2.getFlatListPosition(r3)
            r3 = 0
            r2.setSelectionFromTop(r1, r3)
            goto L_0x00eb
        L_0x00d5:
            r3 = 0
            android.widget.AdapterView<?> r2 = r0.mListView
            boolean r2 = r2 instanceof android.widget.ListView
            if (r2 == 0) goto L_0x00e5
            android.widget.AdapterView<?> r2 = r0.mListView
            android.widget.ListView r2 = (android.widget.ListView) r2
            int r1 = r1 + r4
            r2.setSelectionFromTop(r1, r3)
            goto L_0x00eb
        L_0x00e5:
            android.widget.AdapterView<?> r2 = r0.mListView
            int r1 = r1 + r4
            r2.setSelection(r1)
        L_0x00eb:
            r11 = -1
        L_0x00ec:
            if (r11 < 0) goto L_0x0105
            if (r5 == 0) goto L_0x0105
            r1 = r5[r11]
            java.lang.String r1 = r1.toString()
            boolean r2 = android.text.TextUtils.isEmpty(r1)
            if (r2 != 0) goto L_0x0105
            r2 = 1
            r3 = 0
            java.lang.CharSequence r1 = r1.subSequence(r3, r2)
            r0.drawThumbInternal(r1)
        L_0x0105:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.passport.ui.settings.AlphabetFastIndexer.scrollTo(android.widget.SectionIndexer, int):void");
    }

    private void drawThumbInternal(CharSequence charSequence) {
        if (this.mListView != null) {
            if (TextUtils.equals(charSequence, "!")) {
                charSequence = STARRED_LABEL;
            }
            this.mOverlay.setText(charSequence);
            if (getVisibility() == 0) {
                this.mOverlay.setVisibility(0);
                this.mHandler.removeMessages(1);
                this.mHandler.sendMessageDelayed(this.mHandler.obtainMessage(1), Constants.x);
            }
        }
    }

    private void slidTextHilightBackground(int i) {
        if (this.mTextHilighter != null) {
            if (i < 0) {
                i = 0;
            }
            int paddingTop = getPaddingTop();
            float height = ((float) ((getHeight() - paddingTop) - getPaddingBottom())) / ((float) this.mTextHilighter.mIndexes.length);
            this.mTextHilighter.startSlidding((((float) i) * height) + ((float) paddingTop) + (height / 2.0f) + 1.0f, this.mTextHilightAnimListener);
        }
    }
}
