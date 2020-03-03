package com.mi.global.shop.widget;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import com.mi.global.shop.R;

public class AddAndSubView extends LinearLayout {
    static final int DEFAULT = 0;
    CustomButtonView addButton;
    LinearLayout centerLinearLayout;
    Context context;
    CustomTextView editText;
    int editTextHeight;
    int editTextLayoutHeight;
    int editTextLayoutWidth;
    int editTextMinHeight;
    int editTextMinimumHeight;
    int editTextMinimumWidth;
    LinearLayout leftLinearLayout;
    LinearLayout mainLinearLayout;
    int maxNum;
    int minNum;
    int num;
    OnNumChangeListener onNumChangeListener;
    LinearLayout rightLinearLayout;
    CustomButtonView subButton;

    public interface OnNumChangeListener {
        void a(View view, int i);
    }

    public AddAndSubView(Context context2) {
        super(context2);
        this.context = context2;
        this.num = 0;
        a();
    }

    public AddAndSubView(Context context2, int i) {
        super(context2);
        this.context = context2;
        this.num = i;
        a();
    }

    public AddAndSubView(Context context2, AttributeSet attributeSet) {
        super(context2, attributeSet);
        this.context = context2;
        this.num = 0;
        a();
    }

    public void setEnable(Boolean bool) {
        this.addButton.setEnabled(bool.booleanValue());
        this.subButton.setEnabled(bool.booleanValue());
        this.editText.setEnabled(bool.booleanValue());
        if (bool.booleanValue()) {
            if (this.num <= this.minNum) {
                this.subButton.setEnabled(false);
            }
            if (this.num >= this.maxNum) {
                this.addButton.setEnabled(false);
            }
        }
    }

    private void a() {
        c();
        d();
        e();
        b();
        g();
        h();
        this.maxNum = Integer.MAX_VALUE;
        this.minNum = 0;
        this.subButton.setEnabled(false);
    }

    private void b() {
        if (this.addButton != null) {
            this.addButton.setBackgroundResource(R.drawable.shop_btn_add_bg);
        }
        if (this.subButton != null) {
            this.subButton.setBackgroundResource(R.drawable.shop_btn_sub_bg);
        }
        if (this.mainLinearLayout != null) {
            this.mainLinearLayout.setBackgroundResource(R.drawable.shop_border_confirm_payment_black);
        }
    }

    private void c() {
        this.editTextLayoutWidth = -1;
        this.editTextLayoutHeight = -1;
        this.editTextMinimumWidth = -1;
        this.editTextMinimumHeight = -1;
        this.editTextMinHeight = -1;
        this.editTextHeight = -1;
    }

    private void d() {
        this.mainLinearLayout = new LinearLayout(this.context);
        this.leftLinearLayout = new LinearLayout(this.context);
        this.centerLinearLayout = new LinearLayout(this.context);
        this.rightLinearLayout = new LinearLayout(this.context);
        this.addButton = new CustomButtonView(this.context);
        this.subButton = new CustomButtonView(this.context);
        this.editText = new CustomTextView(this.context);
        this.addButton.setTag("+");
        this.subButton.setTag("-");
        this.editText.setFocusable(false);
        this.editText.setFocusableInTouchMode(false);
        this.editText.setText(String.valueOf(this.num));
    }

    private void e() {
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-1, -1);
        this.addButton.setLayoutParams(layoutParams);
        this.subButton.setLayoutParams(layoutParams);
        this.editText.setLayoutParams(layoutParams);
        this.editText.setGravity(17);
        f();
        layoutParams.gravity = 17;
        this.centerLinearLayout.setLayoutParams(layoutParams);
        this.centerLinearLayout.setFocusable(true);
        this.centerLinearLayout.setFocusableInTouchMode(true);
        layoutParams.width = -2;
        layoutParams.weight = 1.0f;
        this.leftLinearLayout.setLayoutParams(layoutParams);
        this.rightLinearLayout.setLayoutParams(layoutParams);
        layoutParams.width = -1;
        this.mainLinearLayout.setLayoutParams(layoutParams);
        this.mainLinearLayout.setOrientation(0);
    }

    public void setViewsLayoutParm(int i, int i2) {
        this.mainLinearLayout.setLayoutParams(new LinearLayout.LayoutParams(a(this.context, (float) i), a(this.context, (float) i2)));
    }

    public void setButtonLayoutParm(int i, int i2) {
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(a(this.context, (float) i), a(this.context, (float) i2));
        this.addButton.setLayoutParams(layoutParams);
        this.subButton.setLayoutParams(layoutParams);
        this.leftLinearLayout.setLayoutParams(layoutParams);
        this.rightLinearLayout.setLayoutParams(layoutParams);
    }

    private int a(Context context2, float f) {
        return (int) ((f * context2.getResources().getDisplayMetrics().density) + 0.5f);
    }

    private int b(Context context2, float f) {
        return (int) ((f / context2.getResources().getDisplayMetrics().density) + 0.5f);
    }

    private int c(Context context2, float f) {
        return (int) ((f / context2.getResources().getDisplayMetrics().scaledDensity) + 0.5f);
    }

    private int d(Context context2, float f) {
        return (int) ((f * context2.getResources().getDisplayMetrics().scaledDensity) + 0.5f);
    }

    private void f() {
        if (this.editTextMinimumWidth < 0) {
            this.editTextMinimumWidth = a(this.context, 80.0f);
        }
        this.editText.setMinimumWidth(a(this.context, (float) this.editTextMinimumWidth));
        this.centerLinearLayout.setMinimumWidth(a(this.context, (float) this.editTextMinimumWidth));
        if (this.editTextHeight > 0) {
            if (this.editTextMinHeight >= 0 && this.editTextMinHeight > this.editTextHeight) {
                this.editTextHeight = this.editTextMinHeight;
            }
            this.editText.setHeight(a(this.context, (float) this.editTextHeight));
        }
        if (this.editTextLayoutHeight > 0) {
            if (this.editTextMinimumHeight > 0 && this.editTextMinimumHeight > this.editTextLayoutHeight) {
                this.editTextLayoutHeight = this.editTextMinimumHeight;
            }
            LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) this.editText.getLayoutParams();
            layoutParams.height = a(this.context, (float) this.editTextLayoutHeight);
            this.editText.setLayoutParams(layoutParams);
            this.centerLinearLayout.setLayoutParams(layoutParams);
        }
        if (this.editTextLayoutWidth > 0) {
            if (this.editTextMinimumWidth > 0 && this.editTextMinimumWidth > this.editTextLayoutWidth) {
                this.editTextLayoutWidth = this.editTextMinimumWidth;
            }
            LinearLayout.LayoutParams layoutParams2 = (LinearLayout.LayoutParams) this.editText.getLayoutParams();
            layoutParams2.width = a(this.context, (float) this.editTextLayoutWidth);
            this.editText.setLayoutParams(layoutParams2);
            this.centerLinearLayout.setLayoutParams(layoutParams2);
        }
    }

    private void g() {
        this.mainLinearLayout.addView(this.leftLinearLayout, 0);
        this.mainLinearLayout.addView(this.centerLinearLayout, 1);
        this.mainLinearLayout.addView(this.rightLinearLayout, 2);
        this.leftLinearLayout.addView(this.subButton);
        this.centerLinearLayout.addView(this.editText);
        this.rightLinearLayout.addView(this.addButton);
        addView(this.mainLinearLayout);
    }

    public void setNum(int i) {
        this.num = i;
        this.editText.setText(String.valueOf(i));
        if (i >= this.maxNum) {
            this.num = this.maxNum;
            this.addButton.setEnabled(false);
        }
        if (i <= this.minNum) {
            this.num = this.minNum;
            this.subButton.setEnabled(false);
        }
        if (i > this.minNum) {
            this.subButton.setEnabled(true);
        }
        if (i < this.maxNum) {
            this.addButton.setEnabled(true);
        }
    }

    public void setMax(int i) {
        if (i >= this.minNum) {
            this.maxNum = i;
        }
    }

    public void setMin(int i) {
        if (i <= this.maxNum && i >= 0) {
            this.minNum = i;
        }
    }

    public int getNum() {
        if (this.editText.getText().toString() != null) {
            return Integer.parseInt(this.editText.getText().toString());
        }
        return 0;
    }

    public void setEditTextMinimumWidth(int i) {
        if (i > 0) {
            this.editTextMinimumWidth = i;
            this.editText.setMinimumWidth(a(this.context, (float) i));
        }
    }

    public void setEditTextMinimumHeight(int i) {
        if (i > 0) {
            this.editTextMinimumHeight = i;
            this.editText.setMinimumHeight(a(this.context, (float) i));
        }
    }

    public void setEditTextMinHeight(int i) {
        if (i > 0) {
            this.editTextMinHeight = i;
            this.editText.setMinHeight(a(this.context, (float) i));
        }
    }

    public void setEditTextHeight(int i) {
        this.editTextHeight = i;
        f();
    }

    public void setEditTextLayoutWidth(int i) {
        this.editTextLayoutWidth = i;
        f();
    }

    public void setEditTextLayoutHeight(int i) {
        this.editTextLayoutHeight = i;
        f();
    }

    public void setTextSize(int i) {
        this.editText.setTextSize((float) i);
    }

    public void setButtonBgDrawable(Drawable drawable, Drawable drawable2) {
        this.addButton.setBackgroundDrawable(drawable);
        this.subButton.setBackgroundDrawable(drawable2);
        this.addButton.setText("");
        this.subButton.setText("");
    }

    public void setButtonBgResource(int i, int i2) {
        this.addButton.setBackgroundResource(i);
        this.subButton.setBackgroundResource(i2);
        this.addButton.setText("");
        this.subButton.setText("");
    }

    public void setButtonBgColor(int i, int i2) {
        this.addButton.setBackgroundColor(i);
        this.subButton.setBackgroundColor(i2);
    }

    public void setOnNumChangeListener(OnNumChangeListener onNumChangeListener2) {
        this.onNumChangeListener = onNumChangeListener2;
    }

    private void h() {
        this.addButton.setOnClickListener(new OnButtonClickListener());
        this.subButton.setOnClickListener(new OnButtonClickListener());
    }

    class OnButtonClickListener implements View.OnClickListener {
        OnButtonClickListener() {
        }

        public void onClick(View view) {
            String charSequence = AddAndSubView.this.editText.getText().toString();
            if (charSequence == null || charSequence.equals("")) {
                AddAndSubView.this.num = 0;
                AddAndSubView.this.editText.setText(String.valueOf(AddAndSubView.this.num));
            } else if (view.getTag().equals("+")) {
                AddAndSubView addAndSubView = AddAndSubView.this;
                int i = addAndSubView.num + 1;
                addAndSubView.num = i;
                if (i > AddAndSubView.this.maxNum) {
                    AddAndSubView addAndSubView2 = AddAndSubView.this;
                    addAndSubView2.num--;
                    AddAndSubView.this.setNum(AddAndSubView.this.num);
                    return;
                }
                AddAndSubView.this.setNum(AddAndSubView.this.num);
                if (AddAndSubView.this.onNumChangeListener != null) {
                    AddAndSubView.this.onNumChangeListener.a(AddAndSubView.this, AddAndSubView.this.num);
                }
            } else if (view.getTag().equals("-")) {
                AddAndSubView addAndSubView3 = AddAndSubView.this;
                int i2 = addAndSubView3.num - 1;
                addAndSubView3.num = i2;
                if (i2 < AddAndSubView.this.minNum) {
                    AddAndSubView.this.num++;
                    AddAndSubView.this.setNum(AddAndSubView.this.num);
                    return;
                }
                AddAndSubView.this.setNum(AddAndSubView.this.num);
                if (AddAndSubView.this.onNumChangeListener != null) {
                    AddAndSubView.this.onNumChangeListener.a(AddAndSubView.this, AddAndSubView.this.num);
                }
            }
        }
    }
}
