package com.mi.global.bbs.view.cardpager;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.AttributeSet;
import android.view.MotionEvent;
import com.mi.global.bbs.R;
import java.lang.ref.WeakReference;
import java.lang.reflect.Field;
import java.util.List;

public class CardBanner<T> extends ViewPager {
    private boolean autoScroll;
    /* access modifiers changed from: private */
    public int currentItem;
    /* access modifiers changed from: private */
    public boolean isAutoScrolling;
    private CardBanner<T>.AutoScrollTask mAutoScrollTask;
    private float mBaseElevation;
    /* access modifiers changed from: private */
    public CardFragmentPagerAdapter mCardFragmentPagerAdapter;
    private float mCardMargin;
    private float mCardRadius;
    private float mFloatElevation;
    /* access modifiers changed from: private */
    public boolean mIsLoop;
    private boolean mScalingEnabled;
    private float mSideCardWidth;
    /* access modifiers changed from: private */
    public int realSize;
    /* access modifiers changed from: private */
    public boolean scrollDirection;
    /* access modifiers changed from: private */
    public long scrollDuration;
    private ViewPagerScroller scroller;

    static /* synthetic */ int access$404(CardBanner cardBanner) {
        int i = cardBanner.currentItem + 1;
        cardBanner.currentItem = i;
        return i;
    }

    static /* synthetic */ int access$406(CardBanner cardBanner) {
        int i = cardBanner.currentItem - 1;
        cardBanner.currentItem = i;
        return i;
    }

    public CardBanner(Context context) {
        this(context, (AttributeSet) null);
    }

    public void setScalble(boolean z) {
        this.mScalingEnabled = z;
    }

    public CardBanner(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.autoScroll = false;
        this.isAutoScrolling = false;
        this.scrollDirection = true;
        this.scrollDuration = 3000;
        setClipToPadding(false);
        setOffscreenPageLimit(2);
        TypedArray obtainStyledAttributes = getContext().obtainStyledAttributes(attributeSet, R.styleable.CardBanner);
        this.mIsLoop = obtainStyledAttributes.getBoolean(R.styleable.CardBanner_isLoop, false);
        this.mCardRadius = obtainStyledAttributes.getDimension(R.styleable.CardBanner_cardRadius, dp2px(4.0f));
        this.mCardMargin = obtainStyledAttributes.getDimension(R.styleable.CardBanner_cardMargin, dp2px(8.0f));
        this.mSideCardWidth = obtainStyledAttributes.getDimension(R.styleable.CardBanner_sideCardWidth, dp2px(16.0f));
        this.mBaseElevation = obtainStyledAttributes.getDimension(R.styleable.CardBanner_baseElevation, dp2px(0.0f));
        this.mFloatElevation = obtainStyledAttributes.getDimension(R.styleable.CardBanner_floatElevation, dp2px(0.0f));
        obtainStyledAttributes.recycle();
        initScroller();
    }

    private void initBanner() {
        setPadding((int) dp2px(this.mSideCardWidth), 0, (int) dp2px(this.mSideCardWidth), 0);
        setOffscreenPageLimit(2);
        if (this.mIsLoop) {
            post(new Runnable() {
                public void run() {
                    CardBanner.this.setCurrentItem(2);
                }
            });
        }
        CardTransformerListener cardTransformerListener = new CardTransformerListener(this.mCardFragmentPagerAdapter, this.mScalingEnabled);
        addOnPageChangeListener(cardTransformerListener);
        cardTransformerListener.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            public void onPageScrollStateChanged(int i) {
            }

            public void onPageSelected(int i) {
            }

            public void onPageScrolled(int i, float f, int i2) {
                double d = (double) f;
                if ((d < 0.05d || d > 0.95d) && CardBanner.this.mIsLoop) {
                    if (i == CardBanner.this.mCardFragmentPagerAdapter.getCount() - 2) {
                        CardBanner.this.setCurrentItem(2, false);
                    }
                    if (i == 1) {
                        CardBanner.this.setCurrentItem(CardBanner.this.mCardFragmentPagerAdapter.getCount() - 3, false);
                    }
                }
            }
        });
    }

    public void setViewHolders(List<ViewHolder<T>> list, List<T> list2) {
        if (list2 != null && list2.size() != 0) {
            this.mCardFragmentPagerAdapter = new CardFragmentPagerAdapter(((AppCompatActivity) getContext()).getSupportFragmentManager(), list, list2, (int) this.mCardMargin, this.mBaseElevation, this.mFloatElevation, this.mIsLoop);
            setAdapter(this.mCardFragmentPagerAdapter);
            this.realSize = list2.size();
            initBanner();
        }
    }

    public void initScroller() {
        try {
            Field declaredField = ViewPager.class.getDeclaredField("mScroller");
            declaredField.setAccessible(true);
            this.scroller = new ViewPagerScroller(getContext());
            declaredField.set(this, this.scroller);
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e2) {
            e2.printStackTrace();
        }
    }

    public void setAutoScroll(boolean z) {
        if (this.realSize >= 2) {
            this.autoScroll = z;
            startScroll();
        }
    }

    public void setScrollDuration(int i) {
        this.scrollDuration = (long) i;
    }

    public void setScrollTime(int i) {
        this.scroller.setScrollTime(i);
    }

    public void setIsLoop(boolean z) {
        this.mIsLoop = z;
    }

    public void startScroll() {
        if (!this.isAutoScrolling && this.autoScroll) {
            this.mAutoScrollTask = new AutoScrollTask(this);
            postDelayed(this.mAutoScrollTask, this.scrollDuration);
            this.isAutoScrolling = true;
        }
    }

    public void stopScroll() {
        if (this.isAutoScrolling) {
            this.isAutoScrolling = false;
            removeCallbacks(this.mAutoScrollTask);
        }
    }

    public boolean dispatchTouchEvent(MotionEvent motionEvent) {
        switch (motionEvent.getAction()) {
            case 0:
                if (this.isAutoScrolling) {
                    stopScroll();
                    break;
                }
                break;
            case 1:
            case 3:
            case 4:
                if (this.autoScroll) {
                    startScroll();
                    break;
                }
                break;
        }
        return super.dispatchTouchEvent(motionEvent);
    }

    class AutoScrollTask implements Runnable {
        final WeakReference<CardBanner> mCardBannerWeakReference;

        public AutoScrollTask(CardBanner cardBanner) {
            this.mCardBannerWeakReference = new WeakReference<>(cardBanner);
        }

        public void run() {
            CardBanner cardBanner = (CardBanner) this.mCardBannerWeakReference.get();
            if (cardBanner != null && CardBanner.this.isAutoScrolling) {
                if (CardBanner.this.mIsLoop) {
                    cardBanner.setCurrentItem(CardBanner.this.scrollDirection ? CardBanner.access$404(CardBanner.this) : CardBanner.access$406(CardBanner.this));
                    CardBanner.this.postDelayed(this, CardBanner.this.scrollDuration);
                    return;
                }
                int unused = CardBanner.this.currentItem = CardBanner.this.getCurrentItem();
                if (CardBanner.this.currentItem == CardBanner.this.realSize - 1) {
                    boolean unused2 = CardBanner.this.scrollDirection = false;
                }
                if (CardBanner.this.currentItem == 0) {
                    boolean unused3 = CardBanner.this.scrollDirection = true;
                }
                if (CardBanner.this.scrollDirection) {
                    cardBanner.setCurrentItem(CardBanner.access$404(CardBanner.this));
                } else {
                    cardBanner.setCurrentItem(CardBanner.access$406(CardBanner.this));
                }
                CardBanner.this.postDelayed(this, CardBanner.this.scrollDuration);
            }
        }
    }

    public float dp2px(float f) {
        return f * getContext().getResources().getDisplayMetrics().density;
    }
}
