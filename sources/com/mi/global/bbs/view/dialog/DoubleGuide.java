package com.mi.global.bbs.view.dialog;

import android.app.Activity;
import android.content.Context;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.mi.global.bbs.R;
import com.yuyh.library.bean.HighlightArea;
import com.yuyh.library.bean.Message;
import com.yuyh.library.bean.TipsView;
import com.yuyh.library.support.HShape;
import com.yuyh.library.support.OnStateChangedListener;
import com.yuyh.library.view.EasyGuideView;
import java.util.ArrayList;
import java.util.List;

public class DoubleGuide {
    /* access modifiers changed from: private */
    public boolean dismissAnyWhere;
    /* access modifiers changed from: private */
    public OnStateChangedListener listener;
    private Activity mActivity;
    /* access modifiers changed from: private */
    public List<HighlightArea> mAreas;
    private Confirm mConfirm;
    private EasyGuideView mGuideView;
    private List<TipsView> mIndicators;
    private List<Message> mMessages;
    private FrameLayout mParentView;
    private LinearLayout mTipView;
    /* access modifiers changed from: private */
    public boolean performViewClick;

    public DoubleGuide(Activity activity) {
        this(activity, (List<HighlightArea>) null, (List<TipsView>) null, (List<Message>) null, (Confirm) null, true, false);
    }

    public DoubleGuide(Activity activity, List<HighlightArea> list, List<TipsView> list2, List<Message> list3, Confirm confirm, boolean z, boolean z2) {
        this.mAreas = new ArrayList();
        this.mIndicators = new ArrayList();
        this.mMessages = new ArrayList();
        this.mActivity = activity;
        this.mAreas = list;
        this.mIndicators = list2;
        this.mMessages = list3;
        this.mConfirm = confirm;
        this.dismissAnyWhere = z;
        this.performViewClick = z2;
        this.mParentView = (FrameLayout) this.mActivity.getWindow().getDecorView();
    }

    public void setOnStateChangedListener(OnStateChangedListener onStateChangedListener) {
        this.listener = onStateChangedListener;
    }

    public void show() {
        this.mGuideView = new EasyGuideView(this.mActivity);
        this.mGuideView.setHightLightAreas(this.mAreas);
        this.mTipView = new LinearLayout(this.mActivity);
        this.mTipView.setGravity(1);
        this.mTipView.setLayoutParams(new RelativeLayout.LayoutParams(-1, -2));
        this.mTipView.setOrientation(1);
        if (this.mIndicators != null) {
            for (TipsView next : this.mIndicators) {
                addView(next.f2565a, next.c, next.d, next.e);
            }
        }
        if (this.mMessages != null) {
            int dip2px = dip2px(this.mActivity, 5.0f);
            for (Message next2 : this.mMessages) {
                TextView textView = new TextView(this.mActivity);
                textView.setLayoutParams(new ViewGroup.LayoutParams(-1, -2));
                textView.setPadding(dip2px, dip2px, dip2px, dip2px);
                textView.setGravity(17);
                textView.setText(next2.f2564a);
                textView.setTextColor(-1);
                textView.setTextSize(next2.b == -1 ? 12.0f : (float) next2.b);
                this.mTipView.addView(textView);
            }
        }
        if (this.mConfirm != null) {
            TextView textView2 = new TextView(this.mActivity);
            textView2.setGravity(17);
            textView2.setText(this.mConfirm.text);
            textView2.setTextColor(-1);
            textView2.setTextSize(this.mConfirm.textSize == -1 ? 13.0f : (float) this.mConfirm.textSize);
            textView2.setBackgroundResource(R.drawable.doubule_guide_round_shape);
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-2, -2);
            layoutParams.topMargin = dip2px(this.mActivity, 10.0f);
            textView2.setLayoutParams(layoutParams);
            int dip2px2 = dip2px(this.mActivity, 26.0f);
            int dip2px3 = dip2px(this.mActivity, 10.0f);
            textView2.setPadding(dip2px2, dip2px3, dip2px2, dip2px3);
            textView2.setOnClickListener(this.mConfirm.listener != null ? this.mConfirm.listener : new View.OnClickListener() {
                public void onClick(View view) {
                    DoubleGuide.this.dismiss();
                }
            });
            this.mTipView.addView(textView2);
        }
        addView(this.mTipView, Integer.MAX_VALUE, this.mConfirm.marginTop, new RelativeLayout.LayoutParams(-1, -1));
        this.mParentView.addView(this.mGuideView, new FrameLayout.LayoutParams(-1, -1));
        if (this.dismissAnyWhere || this.performViewClick) {
            this.mGuideView.setOnTouchListener(new View.OnTouchListener() {
                public boolean onTouch(View view, MotionEvent motionEvent) {
                    if (motionEvent.getAction() != 1) {
                        return true;
                    }
                    if (DoubleGuide.this.mAreas.size() > 0) {
                        for (HighlightArea highlightArea : DoubleGuide.this.mAreas) {
                            View view2 = highlightArea.f2563a;
                            if (view2 != null && DoubleGuide.this.inRangeOfView(view2, motionEvent)) {
                                DoubleGuide.this.dismiss();
                                if (DoubleGuide.this.listener != null) {
                                    DoubleGuide.this.listener.onHeightlightViewClick(view2);
                                }
                                if (DoubleGuide.this.performViewClick) {
                                    view2.performClick();
                                }
                            } else if (DoubleGuide.this.dismissAnyWhere) {
                                DoubleGuide.this.dismiss();
                            }
                        }
                        return false;
                    }
                    DoubleGuide.this.dismiss();
                    return false;
                }
            });
        }
        if (this.listener != null) {
            this.listener.onShow();
        }
    }

    public void dismiss() {
        this.mGuideView.recyclerBitmap();
        if (this.mParentView.indexOfChild(this.mGuideView) > 0) {
            this.mParentView.removeView(this.mGuideView);
            if (this.listener != null) {
                this.listener.onDismiss();
            }
        }
    }

    private void addView(View view, int i, int i2, RelativeLayout.LayoutParams layoutParams) {
        if (layoutParams == null) {
            layoutParams = new RelativeLayout.LayoutParams(-2, -2);
        }
        if (i == Integer.MAX_VALUE) {
            layoutParams.addRule(14, -1);
        } else if (i < 0) {
            layoutParams.addRule(11, -1);
            layoutParams.rightMargin = -i;
        } else {
            layoutParams.leftMargin = i;
        }
        if (i2 == Integer.MAX_VALUE) {
            layoutParams.addRule(15, -1);
        } else if (i2 < 0) {
            layoutParams.addRule(12, -1);
            layoutParams.bottomMargin = -i2;
        } else {
            layoutParams.topMargin = i2;
        }
        this.mGuideView.addView(view, layoutParams);
    }

    public static class Builder {
        Activity activity;
        List<HighlightArea> areas = new ArrayList();
        Confirm confirm;
        boolean dismissAnyWhere = true;
        List<Message> messages = new ArrayList();
        boolean performViewClick;
        List<TipsView> views = new ArrayList();

        public Builder(Activity activity2) {
            this.activity = activity2;
        }

        public Builder addHightArea(View view, @HShape int i) {
            this.areas.add(new HighlightArea(view, i));
            return this;
        }

        public Builder addHightLightArea(HighlightArea highlightArea) {
            this.areas.add(highlightArea);
            return this;
        }

        public Builder addIndicator(int i, int i2, int i3) {
            ImageView imageView = new ImageView(this.activity);
            imageView.setImageResource(i);
            this.views.add(new TipsView((View) imageView, i2, i3));
            return this;
        }

        public Builder addView(View view, int i, int i2) {
            this.views.add(new TipsView(view, i, i2));
            return this;
        }

        public Builder addView(View view, int i, int i2, RelativeLayout.LayoutParams layoutParams) {
            this.views.add(new TipsView(view, i, i2, layoutParams));
            return this;
        }

        public Builder addMessage(String str, int i) {
            this.messages.add(new Message(str, i));
            return this;
        }

        public Builder setPositiveButton(String str, int i) {
            this.confirm = new Confirm(str, i);
            return this;
        }

        public Builder setPositiveButton(String str, int i, int i2) {
            this.confirm = new Confirm(str, i, i2);
            return this;
        }

        public Builder setPositiveButton(String str, int i, View.OnClickListener onClickListener) {
            this.confirm = new Confirm(str, i, onClickListener);
            return this;
        }

        public Builder dismissAnyWhere(boolean z) {
            this.dismissAnyWhere = z;
            return this;
        }

        public Builder performViewClick(boolean z) {
            this.performViewClick = z;
            return this;
        }

        public DoubleGuide build() {
            return new DoubleGuide(this.activity, this.areas, this.views, this.messages, this.confirm, this.dismissAnyWhere, this.performViewClick);
        }
    }

    public boolean isShowing() {
        return this.mParentView.indexOfChild(this.mGuideView) > 0;
    }

    public boolean inRangeOfView(View view, MotionEvent motionEvent) {
        int[] iArr = new int[2];
        view.getLocationOnScreen(iArr);
        int i = iArr[0];
        int i2 = iArr[1];
        if (motionEvent.getX() < ((float) i) || motionEvent.getX() > ((float) (i + view.getWidth())) || motionEvent.getY() < ((float) i2) || motionEvent.getY() > ((float) (i2 + view.getHeight()))) {
            return false;
        }
        return true;
    }

    public static int dip2px(Context context, float f) {
        return (int) ((f * context.getResources().getDisplayMetrics().density) + 0.5f);
    }

    public static class Confirm {
        public View.OnClickListener listener;
        public int marginTop = 0;
        public String text;
        public int textSize = -1;

        public Confirm(String str) {
            this.text = str;
        }

        public Confirm(String str, int i) {
            this.text = str;
            this.textSize = i;
        }

        public Confirm(String str, int i, int i2) {
            this.text = str;
            this.textSize = i;
            this.marginTop = i2;
        }

        public Confirm(String str, int i, View.OnClickListener onClickListener) {
            this.text = str;
            this.textSize = i;
            this.listener = onClickListener;
        }
    }
}
