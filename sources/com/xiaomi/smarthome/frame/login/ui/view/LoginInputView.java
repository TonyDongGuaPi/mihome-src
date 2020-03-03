package com.xiaomi.smarthome.frame.login.ui.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.DrawableRes;
import android.support.annotation.StringRes;
import android.text.Editable;
import android.text.InputFilter;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.ToggleButton;
import com.xiaomi.smarthome.R;

public class LoginInputView extends LinearLayout {

    /* renamed from: a  reason: collision with root package name */
    private TextView f16308a;
    private ImageView b;
    /* access modifiers changed from: private */
    public EditText c;
    /* access modifiers changed from: private */
    public View d;
    private ToggleButton e;
    /* access modifiers changed from: private */
    public View f;
    /* access modifiers changed from: private */
    public View g;
    /* access modifiers changed from: private */
    public boolean h;
    /* access modifiers changed from: private */
    public View.OnClickListener i;
    /* access modifiers changed from: private */
    public CompoundButton.OnCheckedChangeListener j;

    public LoginInputView(Context context) {
        this(context, (AttributeSet) null);
    }

    public LoginInputView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public LoginInputView(Context context, AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        this.h = true;
        a(attributeSet);
    }

    private void a(AttributeSet attributeSet) {
        LayoutInflater.from(getContext()).inflate(R.layout.smarthome_login_view_new_input, this, true);
        setLayoutParams(new FrameLayout.LayoutParams(-1, -2));
        setGravity(16);
        setOrientation(1);
        this.f16308a = (TextView) findViewById(R.id.sh_login_title);
        this.b = (ImageView) findViewById(R.id.login_view_input_icon);
        this.c = (EditText) findViewById(R.id.login_view_input_edit);
        this.d = findViewById(R.id.login_view_input_clear);
        this.f = findViewById(R.id.Login_input_divider_normal);
        this.g = findViewById(R.id.Login_input_divider_focus);
        this.d.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                LoginInputView.this.c.setText("");
                LoginInputView.this.c.requestFocus();
                if (LoginInputView.this.i != null) {
                    LoginInputView.this.i.onClick(view);
                }
            }
        });
        this.e = (ToggleButton) findViewById(R.id.sh_login_toggle);
        this.e.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                if (LoginInputView.this.j != null) {
                    LoginInputView.this.j.onCheckedChanged(compoundButton, z);
                }
            }
        });
        this.c.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            public void onFocusChange(View view, boolean z) {
                if (z) {
                    LoginInputView.this.f.setVisibility(8);
                    LoginInputView.this.g.setVisibility(0);
                    return;
                }
                LoginInputView.this.f.setVisibility(0);
                LoginInputView.this.g.setVisibility(8);
            }
        });
        if (attributeSet != null) {
            TypedArray obtainStyledAttributes = getContext().obtainStyledAttributes(attributeSet, com.xiaomi.smarthome.sdk.R.styleable.LoginInputView, 0, 0);
            int indexCount = obtainStyledAttributes.getIndexCount();
            int i2 = 0;
            int i3 = 0;
            for (int i4 = 0; i4 < indexCount; i4++) {
                int index = obtainStyledAttributes.getIndex(i4);
                if (index == 6) {
                    this.h = obtainStyledAttributes.getBoolean(6, true);
                    setIsShowClear(this.h);
                } else if (index == 2) {
                    this.c.setInputType(obtainStyledAttributes.getInt(2, 1));
                } else if (index == 1) {
                    this.c.setFilters(new InputFilter[]{new InputFilter.LengthFilter(obtainStyledAttributes.getInt(1, 11))});
                } else if (index == 0) {
                    this.c.setHint(obtainStyledAttributes.getString(0));
                } else if (index == 9) {
                    setIsShowToggle(obtainStyledAttributes.getBoolean(9, false));
                } else if (index == 10) {
                    setToggleBackground(obtainStyledAttributes.getResourceId(10, 0));
                } else if (index == 5) {
                    setIconImg(obtainStyledAttributes.getResourceId(5, R.drawable.default_icon_user1_nor));
                } else if (index == 8) {
                    setIsShowTitle(obtainStyledAttributes.getBoolean(8, false));
                } else if (index == 7) {
                    setIconShow(obtainStyledAttributes.getBoolean(7, false));
                } else if (index == 3) {
                    i2 = obtainStyledAttributes.getResourceId(3, 0);
                } else if (index == 4) {
                    i3 = obtainStyledAttributes.getResourceId(4, 0);
                }
            }
            setToogleContentDescription(i2, i3);
            obtainStyledAttributes.recycle();
        }
        this.c.addTextChangedListener(new TextWatcher() {
            public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }

            public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }

            public void afterTextChanged(Editable editable) {
                if (!LoginInputView.this.h) {
                    return;
                }
                if (TextUtils.isEmpty(editable.toString().trim())) {
                    LoginInputView.this.d.setVisibility(8);
                } else {
                    LoginInputView.this.d.setVisibility(0);
                }
            }
        });
    }

    public void setInputMaxLength(int i2) {
        if (i2 > 0) {
            this.c.setFilters(new InputFilter[]{new InputFilter.LengthFilter(i2)});
        }
    }

    public void setIconShow(boolean z) {
        this.b.setVisibility(z ? 0 : 8);
    }

    public void setClearClickListener(View.OnClickListener onClickListener) {
        this.i = onClickListener;
    }

    public void setIsShowClear(boolean z) {
        this.h = z;
        if (!z) {
            this.d.setVisibility(8);
        }
    }

    public EditText getEditText() {
        return this.c;
    }

    public void setIsShowToggle(boolean z) {
        this.e.setVisibility(z ? 0 : 8);
    }

    public void setIconImg(@DrawableRes int i2) {
        this.b.setImageResource(i2);
    }

    public void setToggleBackground(@DrawableRes int i2) {
        this.e.setBackgroundResource(i2);
    }

    public void setToogleContentDescription(@StringRes int i2, @StringRes int i3) {
        if (i2 != 0 && i3 != 0) {
            String string = getContext().getResources().getString(i2);
            String string2 = getContext().getResources().getString(i3);
            if (this.e.isChecked() && !TextUtils.isEmpty(string2)) {
                this.e.setContentDescription(string2);
            } else if (!this.e.isChecked() && !TextUtils.isEmpty(string)) {
                this.e.setContentDescription(string);
            }
        }
    }

    public void setToggleIsChecked(boolean z) {
        this.e.setChecked(z);
    }

    public void setToggleCheckChangedListener(CompoundButton.OnCheckedChangeListener onCheckedChangeListener) {
        this.j = onCheckedChangeListener;
    }

    public void setIsShowTitle(boolean z) {
        this.f16308a.setVisibility(z ? 4 : 8);
    }
}
