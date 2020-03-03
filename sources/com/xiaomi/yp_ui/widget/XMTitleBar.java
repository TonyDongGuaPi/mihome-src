package com.xiaomi.yp_ui.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.support.annotation.DrawableRes;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import com.xiaomi.yp_ui.R;

public class XMTitleBar extends FrameLayout {

    /* renamed from: a  reason: collision with root package name */
    private TextView f1604a;
    private ImageView b;
    private TextView c;
    private ImageView d;
    private TextView e;
    private TextView f;
    private ImageView g;
    private View h;
    private ImageView i;
    private EditText j;
    private LayoutInflater k;
    private float l;
    private float m;

    public XMTitleBar(Context context) {
        this(context, (AttributeSet) null);
    }

    public XMTitleBar(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public XMTitleBar(Context context, AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        Drawable drawable;
        String str;
        boolean z;
        String str2;
        String str3;
        Drawable drawable2;
        String str4;
        Drawable drawable3;
        String str5;
        setBackgroundResource(R.drawable.yp_common_title_bar_bg_white);
        Drawable drawable4 = null;
        if (attributeSet != null) {
            TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.XMTitleBar);
            drawable = obtainStyledAttributes.getDrawable(R.styleable.XMTitleBar_leftImage);
            str5 = obtainStyledAttributes.getString(R.styleable.XMTitleBar_leftText);
            drawable3 = obtainStyledAttributes.getDrawable(R.styleable.XMTitleBar_titleImage);
            str4 = obtainStyledAttributes.getString(R.styleable.XMTitleBar_titleText);
            drawable2 = obtainStyledAttributes.getDrawable(R.styleable.XMTitleBar_rightImage);
            str3 = obtainStyledAttributes.getString(R.styleable.XMTitleBar_rightText);
            str2 = obtainStyledAttributes.getString(R.styleable.XMTitleBar_rightButton);
            z = obtainStyledAttributes.getBoolean(R.styleable.XMTitleBar_isSearch, false);
            this.l = obtainStyledAttributes.getDimension(R.styleable.XMTitleBar_left_width, 0.0f);
            this.m = obtainStyledAttributes.getDimension(R.styleable.XMTitleBar_right_width, 0.0f);
            if (z) {
                drawable4 = obtainStyledAttributes.getDrawable(R.styleable.XMTitleBar_searchIcon);
                str = obtainStyledAttributes.getString(R.styleable.XMTitleBar_searchHintText);
            } else {
                str = null;
            }
            obtainStyledAttributes.recycle();
        } else {
            drawable = null;
            str5 = null;
            drawable3 = null;
            str4 = null;
            drawable2 = null;
            str3 = null;
            str2 = null;
            str = null;
            z = false;
        }
        this.k = LayoutInflater.from(getContext());
        if (z) {
            this.h = this.k.inflate(R.layout.yp_titlebar_search, this, false);
            this.i = (ImageView) this.h.findViewById(R.id.titlebar_search_icon);
            this.j = (EditText) this.h.findViewById(R.id.titlebar_search_content);
            this.i.setImageDrawable(drawable4);
            this.j.setHint(str);
            addView(this.h);
        }
        if (drawable != null) {
            setupLeftImageButton(drawable);
        } else if (!TextUtils.isEmpty(str5)) {
            setupLeftTextButton(str5);
        } else {
            a(false);
        }
        if (drawable2 != null) {
            setupRightImageButton(drawable2);
        } else if (!TextUtils.isEmpty(str3)) {
            setupRightTextButton(str3);
        } else if (!TextUtils.isEmpty(str2)) {
            setupRightButton(str3);
        }
        if (drawable3 != null) {
            setupTitleImage(drawable3);
        } else if (!TextUtils.isEmpty(str4)) {
            setupTitleText(str4);
        }
    }

    private void a(boolean z) {
        if (this.h != null && z) {
            ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) this.h.getLayoutParams();
            marginLayoutParams.leftMargin = (int) (getResources().getDisplayMetrics().density * 45.0f);
            this.h.setLayoutParams(marginLayoutParams);
        }
    }

    public void controlSearchViewEditable(boolean z) {
        this.j.setInputType(z ? 1 : 0);
    }

    public void setupLeftImageButton(@DrawableRes int i2) {
        setupLeftImageButton(getResources().getDrawable(i2));
    }

    public void setupLeftImageButton(Drawable drawable) {
        if (this.b == null) {
            this.b = (ImageView) this.k.inflate(R.layout.yp_titlebar_left_image, this, false);
            this.b.setId(R.id.bar_left_button);
            a(this.b, this.l);
            addView(this.b);
        }
        this.b.setImageDrawable(drawable);
        a(true);
    }

    public void setupLeftTextButton(String str) {
        if (this.f1604a == null) {
            this.f1604a = (TextView) this.k.inflate(R.layout.yp_titlebar_left_text, this, false);
            this.f1604a.setId(R.id.bar_left_button);
            a(this.f1604a, this.l);
            addView(this.f1604a);
        }
        this.f1604a.setText(str);
        a(true);
    }

    public void setupRightImageButton(Drawable drawable) {
        if (this.g == null) {
            this.g = (ImageView) this.k.inflate(R.layout.yp_titlebar_right_image, this, false);
            this.g.setId(R.id.bar_right_button);
            a(this.g, this.m);
            addView(this.g);
        }
        this.g.setImageDrawable(drawable);
    }

    public void setupRightTextButton(String str) {
        if (this.e == null) {
            this.e = (TextView) this.k.inflate(R.layout.yp_titlebar_right_text, this, false);
            this.e.setId(R.id.bar_right_button);
            a(this.e, this.m);
            addView(this.e);
        }
        this.e.setText(str);
    }

    public void setupRightButton(String str) {
        if (this.f == null) {
            this.f = (TextView) this.k.inflate(R.layout.yp_titlebar_right_button, this, false);
            this.f.setId(R.id.bar_right_button);
            a(this.f, this.m);
            addView(this.f);
        }
        this.f.setText(str);
    }

    private void a(View view, float f2) {
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        if (layoutParams != null && f2 > 0.0f) {
            layoutParams.width = (int) f2;
        }
    }

    public void setupTitleImage(Drawable drawable) {
        if (this.d == null) {
            this.d = (ImageView) this.k.inflate(R.layout.yp_titlebar_title_image, this, false);
            this.d.setId(R.id.bar_title);
            addView(this.d);
        }
        this.d.setImageDrawable(drawable);
    }

    public void setupTitleText(String str) {
        if (this.c == null) {
            this.c = (TextView) this.k.inflate(R.layout.yp_titlebar_title_text, this, false);
            this.c.setId(R.id.bar_title);
            addView(this.c);
        }
        this.c.setText(str);
    }

    public void setLeftClickListener(View.OnClickListener onClickListener) {
        if (this.b != null) {
            this.b.setOnClickListener(onClickListener);
        }
        if (this.f1604a != null) {
            this.f1604a.setOnClickListener(onClickListener);
        }
    }

    public void setRightClickListener(View.OnClickListener onClickListener) {
        if (this.g != null) {
            this.g.setOnClickListener(onClickListener);
        }
        if (this.e != null) {
            this.e.setOnClickListener(onClickListener);
        }
        if (this.f != null) {
            this.f.setOnClickListener(onClickListener);
        }
    }

    public void setTitleClickListener(View.OnClickListener onClickListener) {
        if (this.d != null) {
            this.d.setOnClickListener(onClickListener);
        }
        if (this.c != null) {
            this.c.setOnClickListener(onClickListener);
        }
    }

    public String getSearchText() {
        if (this.j != null) {
            return this.j.getText().toString();
        }
        return null;
    }

    public void setupSearchViewHint(String str) {
        if (this.j != null) {
            this.j.setHint(str);
        }
    }

    public void clearSearchText() {
        if (this.j != null) {
            this.j.setText((CharSequence) null);
        }
    }

    public TextView getLeftTextView() {
        return this.f1604a;
    }

    public ImageView getLeftImageView() {
        return this.b;
    }

    public TextView getTitleTextView() {
        return this.c;
    }

    public ImageView getTitleImageView() {
        return this.d;
    }

    public TextView getRightTextView() {
        return this.e;
    }

    public TextView getRightButtonView() {
        return this.f;
    }

    public ImageView getRightImageView() {
        return this.g;
    }

    public ImageView getSearchImageView() {
        return this.i;
    }

    public EditText getSearchEditView() {
        return this.j;
    }
}
