package com.zhy.view.flowlayout;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Rect;
import android.os.Bundle;
import android.os.Parcelable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import com.mobikwik.sdk.lib.utils.PaymentOptionsDecoder;
import com.zhy.view.flowlayout.TagAdapter;
import java.util.HashSet;
import java.util.Set;

public class TagFlowLayout extends FlowLayout implements TagAdapter.OnDataChangedListener {
    private static final String d = "TagFlowLayout";
    private static final String i = "key_choose_pos";
    private static final String j = "key_default";

    /* renamed from: a  reason: collision with root package name */
    private TagAdapter f2571a;
    private boolean b;
    private int c;
    private MotionEvent e;
    private Set<Integer> f;
    private OnSelectListener g;
    private OnTagClickListener h;

    public interface OnSelectListener {
        void a(Set<Integer> set);
    }

    public interface OnTagClickListener {
        boolean a(View view, int i, FlowLayout flowLayout);
    }

    public TagFlowLayout(Context context, AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        this.b = true;
        this.c = -1;
        this.f = new HashSet();
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.TagFlowLayout);
        this.b = obtainStyledAttributes.getBoolean(R.styleable.TagFlowLayout_auto_select_effect, true);
        this.c = obtainStyledAttributes.getInt(R.styleable.TagFlowLayout_max_select, -1);
        obtainStyledAttributes.recycle();
        if (this.b) {
            setClickable(true);
        }
    }

    public TagFlowLayout(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public TagFlowLayout(Context context) {
        this(context, (AttributeSet) null);
    }

    /* access modifiers changed from: protected */
    public void onMeasure(int i2, int i3) {
        int childCount = getChildCount();
        for (int i4 = 0; i4 < childCount; i4++) {
            TagView tagView = (TagView) getChildAt(i4);
            if (tagView.getVisibility() != 8 && tagView.getTagView().getVisibility() == 8) {
                tagView.setVisibility(8);
            }
        }
        super.onMeasure(i2, i3);
    }

    public void setOnSelectListener(OnSelectListener onSelectListener) {
        this.g = onSelectListener;
        if (this.g != null) {
            setClickable(true);
        }
    }

    public void setOnTagClickListener(OnTagClickListener onTagClickListener) {
        this.h = onTagClickListener;
        if (onTagClickListener != null) {
            setClickable(true);
        }
    }

    public void setAdapter(TagAdapter tagAdapter) {
        this.f2571a = tagAdapter;
        this.f2571a.a((TagAdapter.OnDataChangedListener) this);
        this.f.clear();
        a();
    }

    private void a() {
        removeAllViews();
        TagAdapter tagAdapter = this.f2571a;
        HashSet<Integer> a2 = this.f2571a.a();
        for (int i2 = 0; i2 < tagAdapter.b(); i2++) {
            View a3 = tagAdapter.a(this, i2, tagAdapter.a(i2));
            TagView tagView = new TagView(getContext());
            a3.setDuplicateParentStateEnabled(true);
            if (a3.getLayoutParams() != null) {
                tagView.setLayoutParams(a3.getLayoutParams());
            } else {
                ViewGroup.MarginLayoutParams marginLayoutParams = new ViewGroup.MarginLayoutParams(-2, -2);
                marginLayoutParams.setMargins(dip2px(getContext(), 5.0f), dip2px(getContext(), 5.0f), dip2px(getContext(), 5.0f), dip2px(getContext(), 5.0f));
                tagView.setLayoutParams(marginLayoutParams);
            }
            tagView.addView(a3);
            addView(tagView);
            if (a2.contains(Integer.valueOf(i2))) {
                tagView.setChecked(true);
            }
            if (this.f2571a.a(i2, tagAdapter.a(i2))) {
                this.f.add(Integer.valueOf(i2));
                tagView.setChecked(true);
            }
        }
        this.f.addAll(a2);
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        if (motionEvent.getAction() == 1) {
            this.e = MotionEvent.obtain(motionEvent);
        }
        return super.onTouchEvent(motionEvent);
    }

    public boolean performClick() {
        if (this.e == null) {
            return super.performClick();
        }
        this.e = null;
        TagView a2 = a((int) this.e.getX(), (int) this.e.getY());
        int a3 = a(a2);
        if (a2 == null) {
            return true;
        }
        a(a2, a3);
        if (this.h != null) {
            return this.h.a(a2.getTagView(), a3, this);
        }
        return true;
    }

    public void setMaxSelectCount(int i2) {
        if (this.f.size() > i2) {
            Log.w(d, "you has already select more than " + i2 + " views , so it will be clear .");
            this.f.clear();
        }
        this.c = i2;
    }

    public Set<Integer> getSelectedList() {
        return new HashSet(this.f);
    }

    private void a(TagView tagView, int i2) {
        if (this.b) {
            if (tagView.isChecked()) {
                tagView.setChecked(false);
                this.f.remove(Integer.valueOf(i2));
            } else if (this.c == 1 && this.f.size() == 1) {
                Integer next = this.f.iterator().next();
                ((TagView) getChildAt(next.intValue())).setChecked(false);
                tagView.setChecked(true);
                this.f.remove(next);
                this.f.add(Integer.valueOf(i2));
            } else if (this.c <= 0 || this.f.size() < this.c) {
                tagView.setChecked(true);
                this.f.add(Integer.valueOf(i2));
            } else {
                return;
            }
            if (this.g != null) {
                this.g.a(new HashSet(this.f));
            }
        }
    }

    public TagAdapter getAdapter() {
        return this.f2571a;
    }

    /* access modifiers changed from: protected */
    public Parcelable onSaveInstanceState() {
        Bundle bundle = new Bundle();
        bundle.putParcelable(j, super.onSaveInstanceState());
        String str = "";
        if (this.f.size() > 0) {
            for (Integer intValue : this.f) {
                str = str + intValue.intValue() + "|";
            }
            str = str.substring(0, str.length() - 1);
        }
        bundle.putString(i, str);
        return bundle;
    }

    /* access modifiers changed from: protected */
    public void onRestoreInstanceState(Parcelable parcelable) {
        if (parcelable instanceof Bundle) {
            Bundle bundle = (Bundle) parcelable;
            String string = bundle.getString(i);
            if (!TextUtils.isEmpty(string)) {
                for (String parseInt : string.split(PaymentOptionsDecoder.pipeSeparator)) {
                    int parseInt2 = Integer.parseInt(parseInt);
                    this.f.add(Integer.valueOf(parseInt2));
                    TagView tagView = (TagView) getChildAt(parseInt2);
                    if (tagView != null) {
                        tagView.setChecked(true);
                    }
                }
            }
            super.onRestoreInstanceState(bundle.getParcelable(j));
            return;
        }
        super.onRestoreInstanceState(parcelable);
    }

    private int a(View view) {
        int childCount = getChildCount();
        for (int i2 = 0; i2 < childCount; i2++) {
            if (getChildAt(i2) == view) {
                return i2;
            }
        }
        return -1;
    }

    private TagView a(int i2, int i3) {
        int childCount = getChildCount();
        for (int i4 = 0; i4 < childCount; i4++) {
            TagView tagView = (TagView) getChildAt(i4);
            if (tagView.getVisibility() != 8) {
                Rect rect = new Rect();
                tagView.getHitRect(rect);
                if (rect.contains(i2, i3)) {
                    return tagView;
                }
            }
        }
        return null;
    }

    public void onChanged() {
        this.f.clear();
        a();
    }

    public static int dip2px(Context context, float f2) {
        return (int) ((f2 * context.getResources().getDisplayMetrics().density) + 0.5f);
    }
}
