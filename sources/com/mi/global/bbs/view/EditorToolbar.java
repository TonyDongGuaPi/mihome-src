package com.mi.global.bbs.view;

import android.content.Context;
import android.graphics.drawable.StateListDrawable;
import android.os.Build;
import android.support.annotation.DrawableRes;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.mi.global.bbs.R;

public class EditorToolbar extends LinearLayout {
    TextView countView;
    private LinearLayout.LayoutParams defaultLayoutParams;
    /* access modifiers changed from: private */
    public OnToolbarItemClickListener onToolbarItemClickListener;
    private LinearLayout.LayoutParams questionLayoutParams;

    public interface OnToolbarItemClickListener {
        void onItemClick(EditorToolBarItemLayout editorToolBarItemLayout, int i);
    }

    public EditorToolbar(Context context) {
        this(context, (AttributeSet) null);
    }

    public EditorToolbar(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.defaultLayoutParams = new LinearLayout.LayoutParams(0, -1, 1.0f);
        this.questionLayoutParams = new LinearLayout.LayoutParams(getResources().getDimensionPixelSize(R.dimen.qa_edit_tool_width), -1);
        setOrientation(0);
        setLayoutDirection(3);
    }

    private EditorToolBarItemLayout getToolbarItem(@DrawableRes int i, int i2) {
        StateListDrawable stateListDrawable = new StateListDrawable();
        stateListDrawable.addState(new int[0], getResources().getDrawable(i));
        return getToolbarItem(stateListDrawable, i2);
    }

    private EditorToolBarItemLayout getToolbarItem(StateListDrawable stateListDrawable, final int i) {
        EditorToolBarItemLayout editorToolBarItemLayout = new EditorToolBarItemLayout(getContext());
        ImageView imageView = new ImageView(getContext());
        imageView.setImageDrawable(stateListDrawable);
        imageView.setDuplicateParentStateEnabled(true);
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-2, -2);
        layoutParams.addRule(13);
        editorToolBarItemLayout.addView(imageView, layoutParams);
        editorToolBarItemLayout.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if (EditorToolbar.this.onToolbarItemClickListener != null) {
                    EditorToolbar.this.onToolbarItemClickListener.onItemClick((EditorToolBarItemLayout) view, i);
                }
            }
        });
        return editorToolBarItemLayout;
    }

    private EditorToolBarItemLayout getQueationToolbarItem(@DrawableRes int i, int i2) {
        StateListDrawable stateListDrawable = new StateListDrawable();
        stateListDrawable.addState(new int[0], getResources().getDrawable(i));
        return getQuestionToolbarItem(stateListDrawable, i2);
    }

    private EditorToolBarItemLayout getQuestionToolbarItem(StateListDrawable stateListDrawable, final int i) {
        EditorToolBarItemLayout editorToolBarItemLayout = new EditorToolBarItemLayout(getContext());
        ImageView imageView = new ImageView(getContext());
        imageView.setImageDrawable(stateListDrawable);
        int applyDimension = (int) TypedValue.applyDimension(1, 38.0f, getResources().getDisplayMetrics());
        int applyDimension2 = (int) TypedValue.applyDimension(1, 20.0f, getResources().getDisplayMetrics());
        int applyDimension3 = (int) TypedValue.applyDimension(1, 5.0f, getResources().getDisplayMetrics());
        int applyDimension4 = (int) TypedValue.applyDimension(1, 4.0f, getResources().getDisplayMetrics());
        int applyDimension5 = (int) TypedValue.applyDimension(1, 1.0f, getResources().getDisplayMetrics());
        int applyDimension6 = (int) TypedValue.applyDimension(1, 3.0f, getResources().getDisplayMetrics());
        imageView.setPaddingRelative(applyDimension2, 0, applyDimension2, 0);
        imageView.setDuplicateParentStateEnabled(true);
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-2, -2);
        layoutParams.addRule(13);
        editorToolBarItemLayout.addView(imageView, layoutParams);
        if (i == 2) {
            if (this.countView == null) {
                this.countView = new TextView(getContext());
                this.countView.setTextSize((float) applyDimension6);
                this.countView.setTextColor(-1);
                this.countView.setBackgroundResource(R.drawable.question_invite_bg);
                this.countView.setPaddingRelative(applyDimension4, 0, applyDimension4, applyDimension5);
            }
            RelativeLayout.LayoutParams layoutParams2 = new RelativeLayout.LayoutParams(-2, -2);
            layoutParams2.setMargins(applyDimension, applyDimension3, 0, applyDimension2);
            if (Build.VERSION.SDK_INT >= 17) {
                layoutParams2.setMarginStart(applyDimension);
                layoutParams2.setMarginEnd(0);
            } else {
                layoutParams2.leftMargin = getResources().getDimensionPixelSize(applyDimension);
                layoutParams2.rightMargin = getResources().getDimensionPixelSize(0);
            }
            editorToolBarItemLayout.addView(this.countView, layoutParams2);
            this.countView.setVisibility(8);
        }
        editorToolBarItemLayout.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if (EditorToolbar.this.onToolbarItemClickListener != null) {
                    EditorToolbar.this.onToolbarItemClickListener.onItemClick((EditorToolBarItemLayout) view, i);
                }
            }
        });
        return editorToolBarItemLayout;
    }

    public void addToolbarItem(@DrawableRes int[] iArr) {
        int length;
        if (iArr != null && (length = iArr.length) > 0) {
            if (getChildCount() > 0) {
                removeAllViews();
            }
            for (int i = 0; i < length; i++) {
                addView(getToolbarItem(iArr[i], i), this.defaultLayoutParams);
            }
        }
    }

    public void addQuestionToolbarItem(@DrawableRes int[] iArr) {
        int length;
        if (iArr != null && (length = iArr.length) > 0) {
            if (getChildCount() > 0) {
                removeAllViews();
            }
            for (int i = 0; i < length; i++) {
                addView(getQueationToolbarItem(iArr[i], i), this.questionLayoutParams);
            }
        }
    }

    public void setOnToolbarItemClickListener(OnToolbarItemClickListener onToolbarItemClickListener2) {
        this.onToolbarItemClickListener = onToolbarItemClickListener2;
    }
}
