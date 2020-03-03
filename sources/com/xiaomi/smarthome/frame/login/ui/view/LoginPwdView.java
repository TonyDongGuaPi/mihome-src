package com.xiaomi.smarthome.frame.login.ui.view;

import android.content.Context;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.ViewFlipper;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.frame.login.util.LoginHistoryUtil;
import com.xiaomi.smarthome.library.common.util.DisplayUtils;

public class LoginPwdView extends LinearLayout {
    /* access modifiers changed from: private */

    /* renamed from: a  reason: collision with root package name */
    public ViewFlipper f16328a;
    private LocalPhoneView b;
    /* access modifiers changed from: private */
    public LoginInputView c;
    /* access modifiers changed from: private */
    public EditText d;
    private TextView e;
    private LoginInputView f;
    /* access modifiers changed from: private */
    public EditText g;
    private TextView h;
    /* access modifiers changed from: private */
    public ListView i;
    /* access modifiers changed from: private */
    public LoginHistoryAdapter j;
    /* access modifiers changed from: private */
    public OnPwdLoginListener k;

    public LoginPwdView(Context context) {
        this(context, (AttributeSet) null);
    }

    public LoginPwdView(Context context, @Nullable AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public LoginPwdView(Context context, @Nullable AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        a();
    }

    private void a() {
        LayoutInflater.from(getContext()).inflate(R.layout.smarthome_login_pwd_view, this, true);
        setOrientation(1);
        int d2 = DisplayUtils.d(getContext(), 13.0f);
        setPadding(d2, 0, d2, 0);
        this.f16328a = (ViewFlipper) findViewById(R.id.sh_login_pwd_input_first);
        this.b = (LocalPhoneView) findViewById(R.id.sh_login_phone_register);
        this.c = (LoginInputView) findViewById(R.id.sh_login_username_group);
        this.d = this.c.getEditText();
        this.e = (TextView) this.c.findViewById(R.id.country_phone_code);
        this.f = (LoginInputView) findViewById(R.id.sh_login_pwd_group);
        this.g = this.f.getEditText();
        this.h = (TextView) findViewById(R.id.sh_login_btn);
        this.i = (ListView) findViewById(R.id.sh_login_username_recently);
        this.f16328a.setInAnimation(AnimationUtils.loadAnimation(getContext(), R.anim.login_fade_in));
        this.f16328a.setOutAnimation(AnimationUtils.loadAnimation(getContext(), R.anim.login_fade_out));
        this.j = new LoginHistoryAdapter(getContext());
        this.i.setAdapter(this.j);
        this.i.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
                LoginPwdView.this.d.setText((String) LoginPwdView.this.j.getItem(i));
                LoginPwdView.this.g.setText("");
                LoginPwdView.this.g.requestFocus();
                LoginPwdView.this.c.setToggleIsChecked(false);
            }
        });
        this.j.a(LoginHistoryUtil.b(getContext()));
        this.j.notifyDataSetChanged();
        b();
        this.d.addTextChangedListener(new TextWatcher() {
            public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }

            public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }

            public void afterTextChanged(Editable editable) {
                LoginPwdView.this.b();
            }
        });
        this.g.addTextChangedListener(new TextWatcher() {
            public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }

            public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }

            public void afterTextChanged(Editable editable) {
                LoginPwdView.this.b();
            }
        });
        this.c.setToggleCheckChangedListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                if (z) {
                    LoginPwdView.this.i.setVisibility(0);
                } else {
                    LoginPwdView.this.i.setVisibility(8);
                }
            }
        });
        this.f.setToggleCheckChangedListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                int selectionStart = LoginPwdView.this.g.getSelectionStart();
                if (z) {
                    LoginPwdView.this.g.setInputType(144);
                } else {
                    LoginPwdView.this.g.setInputType(129);
                }
                LoginPwdView.this.g.setSelection(selectionStart);
            }
        });
        this.h.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if (LoginPwdView.this.f16328a.getDisplayedChild() == 0) {
                    if (LoginPwdView.this.k != null) {
                        LoginPwdView.this.k.a(LoginPwdView.this.getPwd(), "", "");
                    }
                } else if (LoginPwdView.this.k != null) {
                    LoginPwdView.this.k.a(LoginPwdView.this.getUserName(), LoginPwdView.this.getPwd(), "", "");
                }
            }
        });
        this.b.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                LoginPwdView.this.g.setText("");
                LoginPwdView.this.showUserName();
            }
        });
    }

    public void showLocalPhone(String str, String str2, String str3) {
        this.f16328a.setDisplayedChild(0);
        this.b.setPhone(str, str2, str3);
        this.b.setTag("");
        this.g.requestFocus();
        this.h.setText(R.string.login_account_login);
    }

    public void showUserName() {
        this.f16328a.setDisplayedChild(1);
        this.d.requestFocus();
        this.h.setText(R.string.login_home_pwd_ensure);
    }

    public void showUserName(String str) {
        showUserName();
        this.d.setText(str);
    }

    public void reset() {
        this.d.requestFocus();
        this.d.setText("");
        this.g.setText("");
    }

    public void clearPassword() {
        this.g.setText("");
    }

    public String getUserName() {
        return this.e.getText() + this.d.getText().toString().trim();
    }

    public String getPwd() {
        return this.g.getText().toString().trim();
    }

    public void setOnPwdLoginListener(OnPwdLoginListener onPwdLoginListener) {
        this.k = onPwdLoginListener;
    }

    /* access modifiers changed from: private */
    public void b() {
        if (this.f16328a.getDisplayedChild() == 0) {
            this.h.setEnabled(d());
        } else {
            this.h.setEnabled(c() && d());
        }
    }

    private boolean c() {
        return !TextUtils.isEmpty(getUserName());
    }

    private boolean d() {
        return !TextUtils.isEmpty(getPwd());
    }

    public LoginInputView getUserNameGroup() {
        return this.c;
    }
}
