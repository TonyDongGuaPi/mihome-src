package com.xiaomi.smarthome.device.utils;

import android.content.Context;
import android.content.DialogInterface;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.device.Device;
import com.xiaomi.smarthome.library.common.dialog.MLAlertDialog;
import com.xiaomi.smarthome.library.common.util.StringUtil;
import com.xiaomi.smarthome.library.common.util.XMStringUtils;
import com.xiaomi.smarthome.library.common.widget.CommonFlowGroup;
import java.util.Timer;
import java.util.TimerTask;
import miui.app.AlertDialog;

public class ClientRemarkInputView extends LinearLayout {

    /* renamed from: a  reason: collision with root package name */
    private RenameInterface f15424a = null;
    private MLAlertDialog b;
    private AlertDialog c;
    /* access modifiers changed from: private */
    public EditText d;
    private TextView e;
    private View f;
    private CommonFlowGroup g;
    private TextView h;
    private boolean i = true;
    private int j = 20;
    Device mDevice;

    public interface RenameInterface {
        void modifyBackName(String str);
    }

    public ClientRemarkInputView(Context context) {
        super(context);
    }

    public ClientRemarkInputView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    /* access modifiers changed from: protected */
    public void onFinishInflate() {
        super.onFinishInflate();
        this.d = (EditText) findViewById(R.id.client_remark_input_view_edit);
        this.e = (TextView) findViewById(R.id.client_remark_input_view_text);
        this.f = findViewById(R.id.client_remark_input_view_clear);
        this.g = (CommonFlowGroup) findViewById(R.id.room_tag);
        this.h = (TextView) findViewById(R.id.tv_room_tag);
        this.d.addTextChangedListener(new TextWatcher() {
            public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }

            public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }

            public void afterTextChanged(Editable editable) {
                ClientRemarkInputView.this.a();
            }
        });
        this.d.requestFocus();
        new Timer().schedule(new TimerTask() {
            public void run() {
                ((InputMethodManager) ClientRemarkInputView.this.getContext().getSystemService("input_method")).showSoftInput(ClientRemarkInputView.this.d, 2);
            }
        }, 200);
        this.f.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                ClientRemarkInputView.this.d.setText((CharSequence) null);
                ClientRemarkInputView.this.d.setError((CharSequence) null);
            }
        });
    }

    public void initialize(RenameInterface renameInterface, MLAlertDialog mLAlertDialog, String str, String str2, boolean z) {
        this.mDevice = null;
        this.f15424a = renameInterface;
        this.b = mLAlertDialog;
        this.i = z;
        String c2 = XMStringUtils.c(str);
        String c3 = XMStringUtils.c(str2);
        this.d.setText(c2);
        this.d.setSelection(Math.min(c2.length(), this.d.length()));
        this.d.setHint(c3);
        a();
    }

    public void initialize(RenameInterface renameInterface, MLAlertDialog mLAlertDialog, String str, String str2, boolean z, int i2) {
        this.mDevice = null;
        this.f15424a = renameInterface;
        this.b = mLAlertDialog;
        this.i = z;
        String c2 = XMStringUtils.c(str);
        String c3 = XMStringUtils.c(str2);
        this.d.setText(c2);
        this.d.setSelection(this.d.length());
        this.d.setHint(c3);
        this.j = i2;
        a();
    }

    public void initialize(RenameInterface renameInterface, MLAlertDialog mLAlertDialog, String str) {
        this.mDevice = null;
        this.f15424a = renameInterface;
        this.b = mLAlertDialog;
        String c2 = XMStringUtils.c(str);
        this.d.setText(c2);
        if (this.d.length() > 0) {
            EditText editText = this.d;
            int i2 = 30;
            if (this.d.length() <= 30) {
                i2 = this.d.length();
            }
            editText.setSelection(i2);
        }
        this.d.setHint(c2);
        a();
    }

    public void initialize(RenameInterface renameInterface, MLAlertDialog mLAlertDialog, Device device, int i2) {
        this.mDevice = device;
        this.f15424a = renameInterface;
        this.b = mLAlertDialog;
        String c2 = XMStringUtils.c(device.name);
        if (c2.length() > i2) {
            c2 = c2.substring(0, i2);
        }
        this.d.setText(c2);
        this.d.setSelection(Math.min(c2.length(), this.d.length()));
        this.d.setHint(c2);
        a();
    }

    public void initialize(RenameInterface renameInterface, AlertDialog alertDialog, Device device, int i2) {
        this.mDevice = device;
        this.f15424a = renameInterface;
        this.c = alertDialog;
        String c2 = XMStringUtils.c(device.name);
        if (c2.length() > i2) {
            c2 = c2.substring(0, i2);
        }
        this.d.setText(c2);
        this.d.setSelection(c2.length());
        this.d.setHint(c2);
        a();
    }

    public void setHint(String str) {
        this.d.setHint(XMStringUtils.c(str));
    }

    public void setInputText(String str) {
        String c2 = XMStringUtils.c(str);
        this.d.setText(c2);
        try {
            this.d.setSelection(Math.min(c2.length(), this.d.length()));
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    /* access modifiers changed from: private */
    public void a() {
        if (this.i) {
            Button button = null;
            if (this.b != null) {
                button = this.b.getButton(-1);
            } else if (this.c != null) {
                button = this.c.getButton(-1);
            }
            if (button != null) {
                String obj = this.d.getText().toString();
                if (TextUtils.isEmpty(obj) || StringUtil.a((CharSequence) obj) > this.j * 2) {
                    button.setEnabled(false);
                } else {
                    button.setEnabled(true);
                }
            }
        }
    }

    public EditText getEditText() {
        return this.d;
    }

    public CommonFlowGroup getCommonFlowGroup() {
        return this.g;
    }

    public TextView getTitleRoomRecommend() {
        return this.h;
    }

    public String getInputText() {
        return XMStringUtils.c(this.d.getText().toString());
    }

    public void onConfirm(DialogInterface dialogInterface) {
        if (this.f15424a != null) {
            String obj = this.d.getText().toString();
            if (TextUtils.isEmpty(obj)) {
                this.d.setError(getContext().getString(R.string.back_name_less_limit_not_null));
            } else if (obj.length() > 40) {
                this.d.setError(getContext().getString(R.string.room_name_too_long));
            } else {
                if (dialogInterface != null && (dialogInterface instanceof MLAlertDialog)) {
                    ((MLAlertDialog) dialogInterface).setAudoDismiss(true);
                }
                this.f15424a.modifyBackName(obj);
            }
        }
    }

    public void setAlertText(String str) {
        if (TextUtils.isEmpty(str)) {
            this.e.setVisibility(8);
            return;
        }
        this.e.setText(str);
        this.e.setVisibility(0);
    }

    public void setNeedVerifyConfirm(boolean z) {
        this.i = z;
    }

    public void setLimitSize(int i2) {
        this.j = i2;
    }
}
