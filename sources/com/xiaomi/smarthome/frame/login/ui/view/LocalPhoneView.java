package com.xiaomi.smarthome.frame.login.ui.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.frame.login.MjLoginRouter;
import com.xiaomi.smarthome.library.common.util.DisplayUtils;

public class LocalPhoneView extends LinearLayout {

    /* renamed from: a  reason: collision with root package name */
    private ImageView f16302a;
    private TextView b;
    private TextView c;
    private TextView d;
    /* access modifiers changed from: private */
    public ImageView e;

    public LocalPhoneView(Context context) {
        this(context, (AttributeSet) null);
    }

    public LocalPhoneView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public LocalPhoneView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        a(attributeSet);
    }

    private void a(AttributeSet attributeSet) {
        LayoutInflater.from(getContext()).inflate(R.layout.smarthome_login_view_local_phone, this, true);
        setClickable(true);
        setOrientation(1);
        this.f16302a = (ImageView) findViewById(R.id.login_phone_local_icon);
        this.b = (TextView) findViewById(R.id.sh_login_copyright);
        this.c = (TextView) findViewById(R.id.login_phone_local_phone);
        this.d = (TextView) findViewById(R.id.login_phone_local_tag);
        this.e = (ImageView) findViewById(R.id.login_phone_local_clear);
        this.c.addTextChangedListener(new TextWatcher() {
            public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }

            public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }

            public void afterTextChanged(Editable editable) {
                if (TextUtils.isEmpty(editable)) {
                    LocalPhoneView.this.e.setVisibility(4);
                } else {
                    LocalPhoneView.this.e.setVisibility(0);
                }
            }
        });
        if (attributeSet != null) {
            TypedArray obtainStyledAttributes = getContext().obtainStyledAttributes(attributeSet, com.xiaomi.smarthome.sdk.R.styleable.LocalPhoneView, 0, 0);
            int indexCount = obtainStyledAttributes.getIndexCount();
            for (int i = 0; i < indexCount; i++) {
                if (obtainStyledAttributes.getIndex(i) == 0) {
                    setIconShow(obtainStyledAttributes.getBoolean(0, false));
                }
            }
            obtainStyledAttributes.recycle();
        }
    }

    public void setPhone(String str, String str2, final String str3) {
        this.c.setText(str);
        if (TextUtils.isEmpty(str2)) {
            this.b.setVisibility(4);
            return;
        }
        this.b.setVisibility(0);
        this.b.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if (!TextUtils.isEmpty(str3)) {
                    MjLoginRouter.d(LocalPhoneView.this.getContext(), str3);
                }
            }
        });
    }

    public void setTag(String str) {
        if (TextUtils.isEmpty(str)) {
            this.d.setVisibility(8);
            return;
        }
        this.d.setVisibility(0);
        this.d.setText(str);
    }

    public void setIconShow(boolean z) {
        LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) this.b.getLayoutParams();
        if (z) {
            layoutParams.setMargins(DisplayUtils.d(getContext(), 12.0f), 0, 0, 0);
            this.f16302a.setVisibility(0);
            return;
        }
        layoutParams.setMargins(DisplayUtils.d(getContext(), 3.0f), 0, 0, 0);
        this.f16302a.setVisibility(8);
    }
}
