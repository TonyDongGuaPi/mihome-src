package com.unionpay.mobile.android.widgets;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.text.Editable;
import android.text.InputFilter;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.text.method.KeyListener;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.unionpay.mobile.android.utils.g;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public final class u extends LinearLayout implements View.OnClickListener {

    /* renamed from: a  reason: collision with root package name */
    private Context f9805a = null;
    /* access modifiers changed from: private */
    public EditText b = null;
    /* access modifiers changed from: private */
    public ImageView c = null;
    private boolean d = true;
    /* access modifiers changed from: private */
    public b e = null;
    /* access modifiers changed from: private */
    public a f = null;
    private int g;
    private Drawable h;
    private TextView i = null;
    private LinearLayout j;
    private View.OnClickListener k = new v(this);
    private TextWatcher l = new w(this);
    private View.OnFocusChangeListener m = new x(this);

    public interface a {
        void a(boolean z);
    }

    public interface b extends a {
        void a_();

        void e();
    }

    public u(Context context) {
        super(context);
        this.f9805a = context;
        setOrientation(0);
        this.g = com.unionpay.mobile.android.global.a.n;
        setLayoutParams(new LinearLayout.LayoutParams(-1, -1));
        setFocusable(true);
        TextView textView = new TextView(this.f9805a);
        textView.setPadding(g.a(this.f9805a, 10.0f), 0, 0, 0);
        textView.setEms(4);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-2, -1);
        layoutParams.gravity = 19;
        addView(textView, layoutParams);
        textView.setGravity(19);
        textView.setTextSize(com.unionpay.mobile.android.global.b.k);
        textView.setTextColor(-13421773);
        this.i = textView;
        this.i.setVisibility(8);
        RelativeLayout relativeLayout = new RelativeLayout(this.f9805a);
        relativeLayout.setGravity(21);
        LinearLayout.LayoutParams layoutParams2 = new LinearLayout.LayoutParams(-1, -1);
        layoutParams.gravity = 19;
        addView(relativeLayout, layoutParams2);
        LinearLayout linearLayout = new LinearLayout(this.f9805a);
        linearLayout.setGravity(21);
        linearLayout.setId(linearLayout.hashCode());
        RelativeLayout.LayoutParams layoutParams3 = new RelativeLayout.LayoutParams(-2, -1);
        layoutParams3.addRule(11, -1);
        layoutParams3.addRule(15, -1);
        layoutParams3.rightMargin = g.a(this.f9805a, 10.0f);
        linearLayout.setVisibility(8);
        relativeLayout.addView(linearLayout, layoutParams3);
        this.j = linearLayout;
        RelativeLayout relativeLayout2 = new RelativeLayout(this.f9805a);
        relativeLayout2.setGravity(16);
        RelativeLayout.LayoutParams layoutParams4 = new RelativeLayout.LayoutParams(-1, -1);
        layoutParams4.addRule(9, -1);
        layoutParams4.addRule(15, -1);
        layoutParams4.addRule(0, linearLayout.getId());
        layoutParams4.rightMargin = g.a(this.f9805a, 10.0f);
        relativeLayout.addView(relativeLayout2, layoutParams4);
        this.c = new ImageView(this.f9805a);
        this.c.setId(this.c.hashCode());
        this.c.setBackgroundDrawable(this.h);
        this.c.setOnClickListener(this.k);
        this.c.setVisibility(8);
        this.c.setId(this.c.hashCode());
        this.c.setAdjustViewBounds(true);
        int a2 = g.a(this.f9805a, 30.0f);
        RelativeLayout.LayoutParams layoutParams5 = new RelativeLayout.LayoutParams(a2, a2);
        layoutParams5.addRule(11, -1);
        layoutParams5.addRule(15, -1);
        relativeLayout2.addView(this.c, layoutParams5);
        this.b = new EditText(this.f9805a);
        this.b.setSingleLine();
        this.b.setTextSize(com.unionpay.mobile.android.global.b.k);
        this.b.setTextColor(-10066330);
        this.b.setHintTextColor(-6710887);
        this.b.setBackgroundDrawable((Drawable) null);
        this.b.setGravity(16);
        this.b.setPadding(g.a(this.f9805a, 10.0f), 0, 0, 0);
        this.b.addTextChangedListener(this.l);
        if (this.d) {
            this.b.setOnFocusChangeListener(this.m);
        }
        RelativeLayout.LayoutParams layoutParams6 = new RelativeLayout.LayoutParams(-1, -2);
        layoutParams6.addRule(0, this.c.getId());
        layoutParams6.addRule(15, -1);
        layoutParams6.addRule(9, -1);
        relativeLayout2.addView(this.b, layoutParams6);
    }

    static /* synthetic */ boolean b(u uVar) {
        return (uVar.b == null || uVar.b().length() == 0 || !uVar.d) ? false : true;
    }

    public final TextView a(String str) {
        if (this.i != null && !TextUtils.isEmpty(str)) {
            this.i.setVisibility(0);
            this.i.setText(str);
        }
        return this.i;
    }

    public final u a(Drawable drawable) {
        if (drawable != null) {
            this.c.setBackgroundDrawable(drawable);
        }
        return this;
    }

    public final void a() {
        this.d = false;
        if (this.b != null) {
            this.b.setKeyListener((KeyListener) null);
            this.b.setFocusable(false);
            if (this.c != null && this.c.getVisibility() == 0) {
                this.c.setVisibility(8);
            }
        }
    }

    public final void a(int i2) {
        if (this.b != null) {
            this.b.setInputType(i2);
        }
    }

    public final void a(InputFilter inputFilter) {
        InputFilter[] inputFilterArr = {inputFilter};
        if (this.b != null) {
            InputFilter[] filters = this.b.getFilters();
            if (filters == null) {
                this.b.setFilters(inputFilterArr);
                return;
            }
            InputFilter[] inputFilterArr2 = new InputFilter[(filters.length + 1)];
            System.arraycopy(filters, 0, inputFilterArr2, 0, filters.length);
            System.arraycopy(inputFilterArr, 0, inputFilterArr2, filters.length, 1);
            this.b.setFilters(inputFilterArr2);
        }
    }

    public final void a(TextWatcher textWatcher) {
        if (this.b != null && textWatcher != null) {
            this.b.addTextChangedListener(textWatcher);
        }
    }

    public final void a(View view, LinearLayout.LayoutParams layoutParams) {
        if (view != null && this.j != null) {
            this.j.addView(view, layoutParams);
            this.j.setVisibility(0);
        }
    }

    public final void a(TextView.OnEditorActionListener onEditorActionListener) {
        if (this.b != null && this.d) {
            this.b.setOnEditorActionListener(onEditorActionListener);
        }
    }

    public final void a(a aVar) {
        this.f = aVar;
    }

    public final void a(b bVar) {
        this.e = bVar;
        if (this.b != null) {
            this.b.setOnClickListener(this);
        }
    }

    public final String b() {
        if (this.b != null) {
            return this.b.getText().toString();
        }
        return null;
    }

    public final void b(int i2) {
        if (this.b != null) {
            this.b.setSelection(i2);
        }
    }

    public final void b(String str) {
        if (this.b != null && str != null) {
            this.b.setHint(str);
        }
    }

    public final Editable c() {
        if (this.b != null) {
            return this.b.getText();
        }
        return null;
    }

    public final void c(String str) {
        if (this.b != null && str != null) {
            this.b.setText(str);
        }
    }

    public final void d() {
        if (this.b != null) {
            this.b.setLongClickable(false);
        }
    }

    public final void e() {
        if (this.b != null) {
            this.b.setText("");
            if (this.e != null) {
                this.e.e();
            }
        }
    }

    public final void f() {
        ((Activity) this.f9805a).getWindow().setSoftInputMode(3);
        int i2 = Build.VERSION.SDK_INT;
        String str = i2 >= 16 ? "setShowSoftInputOnFocus" : i2 >= 14 ? "setSoftInputShownOnFocus" : null;
        if (str == null) {
            this.b.setInputType(0);
            return;
        }
        try {
            Method method = EditText.class.getMethod(str, new Class[]{Boolean.TYPE});
            method.setAccessible(true);
            method.invoke(this.b, new Object[]{false});
        } catch (NoSuchMethodException e2) {
            this.b.setInputType(0);
            e2.printStackTrace();
        } catch (IllegalAccessException e3) {
            e3.printStackTrace();
        } catch (IllegalArgumentException e4) {
            e4.printStackTrace();
        } catch (InvocationTargetException e5) {
            e5.printStackTrace();
        }
    }

    public final void onClick(View view) {
        if (this.e != null) {
            this.e.a_();
        }
    }

    public final void setOnTouchListener(View.OnTouchListener onTouchListener) {
        if (this.b != null) {
            this.b.setOnTouchListener(onTouchListener);
        }
    }
}
