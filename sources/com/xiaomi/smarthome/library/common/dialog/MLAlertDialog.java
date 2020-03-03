package com.xiaomi.smarthome.library.common.dialog;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.database.Cursor;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Message;
import android.text.SpannableStringBuilder;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import com.xiaomi.smarthome.library.R;
import com.xiaomi.smarthome.library.common.dialog.MLAlertController;
import java.util.Timer;
import java.util.TimerTask;

public class MLAlertDialog extends Dialog implements DialogInterface {
    /* access modifiers changed from: private */
    public MLAlertController mAlert;
    protected Context mContext;
    private DismissCallBack mDismissCallBack;
    public CharSequence[] mItemTexts;
    private int mStartY;

    public interface DismissCallBack {
        void afterDismissCallBack();

        void beforeDismissCallBack();
    }

    protected MLAlertDialog(Context context) {
        this(context, R.style.V5_AlertDialog);
    }

    protected MLAlertDialog(Context context, int i) {
        this(context, i, 80, 0);
    }

    protected MLAlertDialog(Context context, int i, int i2, int i3) {
        super(context, i);
        this.mStartY = 0;
        this.mAlert = new MLAlertController(context, this, getWindow(), i2);
        this.mContext = context;
        this.mStartY = i3;
    }

    protected MLAlertDialog(Context context, boolean z, DialogInterface.OnCancelListener onCancelListener) {
        super(context, R.style.V5_AlertDialog);
        this.mStartY = 0;
        setCancelable(z);
        setOnCancelListener(onCancelListener);
        this.mAlert = new MLAlertController(context, this, getWindow(), 80);
        this.mContext = context;
    }

    protected MLAlertDialog(Context context, boolean z, DialogInterface.OnCancelListener onCancelListener, int i, int i2) {
        super(context, R.style.V5_AlertDialog);
        this.mStartY = 0;
        setCancelable(z);
        setOnCancelListener(onCancelListener);
        this.mAlert = new MLAlertController(context, this, getWindow(), i);
        this.mContext = context;
        this.mStartY = i2;
    }

    public Button getButton(int i) {
        return this.mAlert.b(i);
    }

    public ListView getListView() {
        return this.mAlert.c();
    }

    public View getView() {
        return this.mAlert.d();
    }

    public EditText getInputView() {
        return (EditText) this.mAlert.d();
    }

    public CharSequence[] getItemTexts() {
        return this.mItemTexts;
    }

    private void showSoftInput() {
        if (this.mAlert.d() != null && (this.mAlert.d() instanceof EditText)) {
            new Timer().schedule(new TimerTask() {
                public void run() {
                    ((InputMethodManager) MLAlertDialog.this.getContext().getSystemService("input_method")).showSoftInput(MLAlertDialog.this.mAlert.d(), 2);
                }
            }, 200);
        }
    }

    private void hideSoftInput() {
        Context context = this.mContext;
        if (context != null && this.mAlert.d() != null) {
            ((InputMethodManager) context.getSystemService("input_method")).hideSoftInputFromWindow(this.mAlert.d().getWindowToken(), 0);
        }
    }

    public void dismiss() {
        if (this.mDismissCallBack != null) {
            this.mDismissCallBack.beforeDismissCallBack();
        }
        hideSoftInput();
        try {
            super.dismiss();
            this.mContext = null;
        } catch (Exception unused) {
        }
        if (this.mDismissCallBack != null) {
            this.mDismissCallBack.afterDismissCallBack();
        }
    }

    public void setAudoDismiss(boolean z) {
        this.mAlert.a(z);
        if (z) {
            this.mAlert.a();
        }
    }

    public void setDismissCallBack(DismissCallBack dismissCallBack) {
        this.mDismissCallBack = dismissCallBack;
    }

    public void setTitle(CharSequence charSequence) {
        super.setTitle(charSequence);
        this.mAlert.a(charSequence);
    }

    public void show() {
        super.show();
        WindowManager.LayoutParams attributes = getWindow().getAttributes();
        attributes.y = this.mStartY;
        attributes.width = -1;
        attributes.height = -2;
        getWindow().setAttributes(attributes);
    }

    public void setCustomTitle(View view) {
        this.mAlert.b(view);
    }

    public void setMessage(CharSequence charSequence) {
        this.mAlert.b(charSequence);
    }

    public void setView(View view) {
        this.mAlert.c(view);
    }

    public void setView(View view, int i, int i2, int i3, int i4) {
        this.mAlert.a(view, i, i2, i3, i4);
    }

    public void setButton(int i, CharSequence charSequence, Message message) {
        this.mAlert.a(i, charSequence, (DialogInterface.OnClickListener) null, message);
    }

    public void setButton(int i, CharSequence charSequence, DialogInterface.OnClickListener onClickListener) {
        this.mAlert.a(i, charSequence, onClickListener, (Message) null);
    }

    @Deprecated
    public void setButton(CharSequence charSequence, Message message) {
        setButton(-1, charSequence, message);
    }

    @Deprecated
    public void setButton2(CharSequence charSequence, Message message) {
        setButton(-2, charSequence, message);
    }

    @Deprecated
    public void setButton3(CharSequence charSequence, Message message) {
        setButton(-3, charSequence, message);
    }

    @Deprecated
    public void setButton(CharSequence charSequence, DialogInterface.OnClickListener onClickListener) {
        setButton(-1, charSequence, onClickListener);
    }

    @Deprecated
    public void setButton2(CharSequence charSequence, DialogInterface.OnClickListener onClickListener) {
        setButton(-2, charSequence, onClickListener);
    }

    @Deprecated
    public void setButton3(CharSequence charSequence, DialogInterface.OnClickListener onClickListener) {
        setButton(-3, charSequence, onClickListener);
    }

    public void setIcon(int i) {
        this.mAlert.a(i);
    }

    public void setIcon(Drawable drawable) {
        this.mAlert.a(drawable);
    }

    public void setInverseBackgroundForced(boolean z) {
        this.mAlert.c(z);
    }

    public void setBtnTextColor(int i, int i2) {
        this.mAlert.a(i, i2);
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        this.mAlert.b();
    }

    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        return this.mAlert.a(i, keyEvent) || super.onKeyDown(i, keyEvent);
    }

    public boolean onKeyUp(int i, KeyEvent keyEvent) {
        return this.mAlert.b(i, keyEvent) || super.onKeyUp(i, keyEvent);
    }

    public static class Builder {

        /* renamed from: a  reason: collision with root package name */
        private final MLAlertController.AlertParams f1550a;
        private Context b;

        public EditText a() {
            return (EditText) this.f1550a.u;
        }

        public Builder(Context context) {
            this.b = context;
            this.f1550a = new MLAlertController.AlertParams(context);
        }

        public Builder a(int i) {
            this.f1550a.e = this.f1550a.f18571a.getText(i);
            return this;
        }

        public Builder a(CharSequence charSequence) {
            this.f1550a.e = charSequence;
            return this;
        }

        public Builder a(View view) {
            this.f1550a.f = view;
            return this;
        }

        public Builder b(int i) {
            this.f1550a.g = this.f1550a.f18571a.getText(i);
            return this;
        }

        public Builder b(CharSequence charSequence) {
            this.f1550a.g = charSequence;
            return this;
        }

        public Builder a(SpannableStringBuilder spannableStringBuilder) {
            this.f1550a.h = spannableStringBuilder;
            return this;
        }

        public Builder c(int i) {
            this.f1550a.c = i;
            return this;
        }

        public Builder a(Drawable drawable) {
            this.f1550a.d = drawable;
            return this;
        }

        public Builder a(int i, DialogInterface.OnClickListener onClickListener) {
            this.f1550a.i = this.f1550a.f18571a.getText(i);
            this.f1550a.j = onClickListener;
            return this;
        }

        public Builder a(CharSequence charSequence, DialogInterface.OnClickListener onClickListener) {
            this.f1550a.i = charSequence;
            this.f1550a.j = onClickListener;
            return this;
        }

        public Builder b(int i, DialogInterface.OnClickListener onClickListener) {
            this.f1550a.k = this.f1550a.f18571a.getText(i);
            this.f1550a.l = onClickListener;
            return this;
        }

        public Builder b(CharSequence charSequence, DialogInterface.OnClickListener onClickListener) {
            this.f1550a.k = charSequence;
            this.f1550a.l = onClickListener;
            return this;
        }

        public Builder c(int i, DialogInterface.OnClickListener onClickListener) {
            this.f1550a.m = this.f1550a.f18571a.getText(i);
            this.f1550a.n = onClickListener;
            return this;
        }

        public Builder c(CharSequence charSequence, DialogInterface.OnClickListener onClickListener) {
            this.f1550a.m = charSequence;
            this.f1550a.n = onClickListener;
            return this;
        }

        public Builder a(boolean z) {
            this.f1550a.o = z;
            return this;
        }

        public Builder a(DialogInterface.OnCancelListener onCancelListener) {
            this.f1550a.p = onCancelListener;
            return this;
        }

        public Builder a(DialogInterface.OnKeyListener onKeyListener) {
            this.f1550a.q = onKeyListener;
            return this;
        }

        public Builder d(int i, DialogInterface.OnClickListener onClickListener) {
            this.f1550a.r = this.f1550a.f18571a.getResources().getTextArray(i);
            this.f1550a.t = onClickListener;
            return this;
        }

        public Builder a(CharSequence[] charSequenceArr, DialogInterface.OnClickListener onClickListener) {
            this.f1550a.r = charSequenceArr;
            this.f1550a.t = onClickListener;
            return this;
        }

        public Builder a(ListAdapter listAdapter, DialogInterface.OnClickListener onClickListener) {
            this.f1550a.s = listAdapter;
            this.f1550a.t = onClickListener;
            return this;
        }

        public Builder a(Cursor cursor, DialogInterface.OnClickListener onClickListener, String str) {
            this.f1550a.F = cursor;
            this.f1550a.G = str;
            this.f1550a.t = onClickListener;
            return this;
        }

        public Builder a(int i, boolean[] zArr, DialogInterface.OnMultiChoiceClickListener onMultiChoiceClickListener) {
            this.f1550a.r = this.f1550a.f18571a.getResources().getTextArray(i);
            this.f1550a.E = onMultiChoiceClickListener;
            this.f1550a.A = zArr;
            this.f1550a.B = true;
            return this;
        }

        public Builder a(CharSequence[] charSequenceArr, boolean[] zArr, DialogInterface.OnMultiChoiceClickListener onMultiChoiceClickListener) {
            this.f1550a.r = charSequenceArr;
            this.f1550a.E = onMultiChoiceClickListener;
            this.f1550a.A = zArr;
            this.f1550a.B = true;
            return this;
        }

        public Builder a(Cursor cursor, String str, String str2, DialogInterface.OnMultiChoiceClickListener onMultiChoiceClickListener) {
            this.f1550a.F = cursor;
            this.f1550a.E = onMultiChoiceClickListener;
            this.f1550a.H = str;
            this.f1550a.G = str2;
            this.f1550a.B = true;
            return this;
        }

        public Builder a(int i, int i2, DialogInterface.OnClickListener onClickListener) {
            this.f1550a.r = this.f1550a.f18571a.getResources().getTextArray(i);
            this.f1550a.t = onClickListener;
            this.f1550a.D = i2;
            this.f1550a.C = true;
            return this;
        }

        public Builder a(Cursor cursor, int i, String str, DialogInterface.OnClickListener onClickListener) {
            this.f1550a.F = cursor;
            this.f1550a.t = onClickListener;
            this.f1550a.D = i;
            this.f1550a.G = str;
            this.f1550a.C = true;
            return this;
        }

        public Builder a(CharSequence[] charSequenceArr, int i, DialogInterface.OnClickListener onClickListener) {
            this.f1550a.r = charSequenceArr;
            this.f1550a.t = onClickListener;
            this.f1550a.D = i;
            this.f1550a.C = true;
            return this;
        }

        public Builder a(ListAdapter listAdapter, int i, DialogInterface.OnClickListener onClickListener) {
            this.f1550a.s = listAdapter;
            this.f1550a.t = onClickListener;
            this.f1550a.D = i;
            this.f1550a.C = true;
            return this;
        }

        public Builder a(AdapterView.OnItemSelectedListener onItemSelectedListener) {
            this.f1550a.J = onItemSelectedListener;
            return this;
        }

        public Builder b(View view) {
            this.f1550a.u = view;
            this.f1550a.z = false;
            return this;
        }

        public Builder a(String str, boolean z) {
            Context context = this.b;
            if (context != null) {
                View inflate = View.inflate(context, R.layout.ml_alert_dialog_input_view, (ViewGroup) null);
                a(inflate, context.getResources().getDimensionPixelSize(R.dimen.alertdialog_button_panel_padding_horizontal), 0, context.getResources().getDimensionPixelSize(R.dimen.alertdialog_button_panel_padding_horizontal), context.getResources().getDimensionPixelSize(R.dimen.alertdialog_custom_panel_padding_bottom));
                EditText editText = (EditText) inflate.findViewById(R.id.input_text);
                editText.setSingleLine(z);
                if (!TextUtils.isEmpty(str)) {
                    editText.setHint(str);
                }
                editText.requestFocus();
            }
            return this;
        }

        public void c(CharSequence charSequence) {
            this.f1550a.O = charSequence;
        }

        public Builder a(DismissCallBack dismissCallBack) {
            this.f1550a.N = dismissCallBack;
            return this;
        }

        public Builder a(View view, int i, int i2, int i3, int i4) {
            this.f1550a.u = view;
            this.f1550a.z = true;
            this.f1550a.v = i;
            this.f1550a.w = i2;
            this.f1550a.x = i3;
            this.f1550a.y = i4;
            return this;
        }

        public Builder a(View view, int i, int i2, int i3, int i4, boolean z) {
            this.f1550a.u = view;
            this.f1550a.z = true;
            this.f1550a.v = i;
            this.f1550a.w = i2;
            this.f1550a.x = i3;
            this.f1550a.y = i4;
            this.f1550a.P = z;
            return this;
        }

        public Builder b(boolean z) {
            this.f1550a.I = z;
            return this;
        }

        public Builder c(boolean z) {
            this.f1550a.L = z;
            return this;
        }

        public Builder d(boolean z) {
            this.f1550a.M = z;
            return this;
        }

        public MLAlertDialog b() {
            MLAlertDialog mLAlertDialog = new MLAlertDialog(this.f1550a.f18571a);
            mLAlertDialog.mItemTexts = this.f1550a.r;
            this.f1550a.a(mLAlertDialog.mAlert);
            mLAlertDialog.setCancelable(this.f1550a.o);
            if (this.f1550a.o) {
                mLAlertDialog.setCanceledOnTouchOutside(true);
            }
            mLAlertDialog.setOnCancelListener(this.f1550a.p);
            if (this.f1550a.q != null) {
                mLAlertDialog.setOnKeyListener(this.f1550a.q);
            }
            mLAlertDialog.setDismissCallBack(this.f1550a.N);
            return mLAlertDialog;
        }

        public MLAlertDialog c() {
            MLAlertDialog mLAlertDialog = new MLAlertDialog(this.f1550a.f18571a, true, (DialogInterface.OnCancelListener) null, 17, 0);
            mLAlertDialog.mItemTexts = this.f1550a.r;
            this.f1550a.a(mLAlertDialog.mAlert);
            mLAlertDialog.setCancelable(this.f1550a.o);
            if (this.f1550a.o) {
                mLAlertDialog.setCanceledOnTouchOutside(true);
            }
            mLAlertDialog.setOnCancelListener(this.f1550a.p);
            if (this.f1550a.q != null) {
                mLAlertDialog.setOnKeyListener(this.f1550a.q);
            }
            mLAlertDialog.setDismissCallBack(this.f1550a.N);
            return mLAlertDialog;
        }

        public MLAlertDialog d() {
            MLAlertDialog b2 = b();
            b2.show();
            WindowManager.LayoutParams attributes = b2.getWindow().getAttributes();
            attributes.width = -1;
            attributes.height = -2;
            b2.getWindow().setAttributes(attributes);
            return b2;
        }

        public MLAlertDialog e() {
            MLAlertDialog c = c();
            c.show();
            WindowManager.LayoutParams attributes = c.getWindow().getAttributes();
            attributes.width = -1;
            attributes.height = -2;
            c.getWindow().setAttributes(attributes);
            return c;
        }

        public Builder a(int i, int i2) {
            switch (i2) {
                case -3:
                    this.f1550a.S = i;
                    break;
                case -2:
                    this.f1550a.R = i;
                    break;
                case -1:
                    this.f1550a.Q = i;
                    break;
                default:
                    throw new IllegalArgumentException("Button does not exist");
            }
            return this;
        }
    }

    public void setContentPanelHeight(int i) {
        View findViewById = getWindow().findViewById(R.id.contentPanel);
        if (findViewById != null) {
            LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) findViewById.getLayoutParams();
            layoutParams.weight = 0.0f;
            layoutParams.height = i;
            findViewById.setLayoutParams(layoutParams);
        }
    }
}
