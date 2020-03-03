package com.xiaomiyoupin.toast.dialog;

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
import com.xiaomiyoupin.toast.R;
import com.xiaomiyoupin.toast.YPDToast;
import com.xiaomiyoupin.toast.dialog.MLAlertController;
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
        this(context, R.style.YpdV5_AlertDialog);
    }

    protected MLAlertDialog(Context context, int i) {
        this(context, i, 80, 0);
    }

    protected MLAlertDialog(Context context, int i, int i2, int i3) {
        this(context, i, true, (DialogInterface.OnCancelListener) null, i2, i3);
    }

    protected MLAlertDialog(Context context, boolean z, DialogInterface.OnCancelListener onCancelListener) {
        this(context, z, onCancelListener, 80, 0);
    }

    protected MLAlertDialog(Context context, boolean z, DialogInterface.OnCancelListener onCancelListener, int i, int i2) {
        this(context, R.style.YpdV5_AlertDialog, z, onCancelListener, i, i2);
    }

    protected MLAlertDialog(Context context, int i, boolean z, DialogInterface.OnCancelListener onCancelListener, int i2, int i3) {
        super(context, i);
        setCancelable(z);
        setOnCancelListener(onCancelListener);
        this.mAlert = new MLAlertController(YPDToast.getInstance().getApplicationContext(), this, getWindow(), i2);
        this.mContext = context;
        this.mStartY = i3;
    }

    public Button getButton(int i) {
        return this.mAlert.getButton(i);
    }

    public ListView getListView() {
        return this.mAlert.getListView();
    }

    public View getView() {
        return this.mAlert.getView();
    }

    public void setView(View view) {
        this.mAlert.setView(view);
    }

    public EditText getInputView() {
        return (EditText) this.mAlert.getView();
    }

    public CharSequence[] getItemTexts() {
        return this.mItemTexts;
    }

    private void showSoftInput() {
        if (this.mAlert.getView() != null && (this.mAlert.getView() instanceof EditText)) {
            new Timer().schedule(new TimerTask() {
                public void run() {
                    InputMethodManager inputMethodManager = (InputMethodManager) MLAlertDialog.this.getContext().getSystemService("input_method");
                    if (inputMethodManager != null) {
                        inputMethodManager.showSoftInput(MLAlertDialog.this.mAlert.getView(), 2);
                    }
                }
            }, 200);
        }
    }

    private void hideSoftInput() {
        InputMethodManager inputMethodManager;
        if (this.mAlert.getView() != null && (inputMethodManager = (InputMethodManager) this.mContext.getSystemService("input_method")) != null) {
            inputMethodManager.hideSoftInputFromWindow(this.mAlert.getView().getWindowToken(), 0);
        }
    }

    public void dismiss() {
        if (this.mDismissCallBack != null) {
            this.mDismissCallBack.beforeDismissCallBack();
        }
        hideSoftInput();
        super.dismiss();
        if (this.mDismissCallBack != null) {
            this.mDismissCallBack.afterDismissCallBack();
        }
    }

    public void setAudoDismiss(boolean z) {
        this.mAlert.setAudoDismiss(z);
        if (z) {
            this.mAlert.sendDismissMessage();
        }
    }

    public void setDismissCallBack(DismissCallBack dismissCallBack) {
        this.mDismissCallBack = dismissCallBack;
    }

    public void setTitle(CharSequence charSequence) {
        super.setTitle(charSequence);
        this.mAlert.setTitle(charSequence);
    }

    public void show() {
        super.show();
        WindowManager.LayoutParams attributes = getWindow().getAttributes();
        attributes.y = this.mStartY;
        attributes.width = -1;
        attributes.height = -2;
        getWindow().setAttributes(attributes);
        showSoftInput();
    }

    public void setCustomTitle(View view) {
        this.mAlert.setCustomTitle(view);
    }

    public void setMessage(CharSequence charSequence) {
        this.mAlert.setMessage(charSequence);
    }

    public void setView(View view, int i, int i2, int i3, int i4) {
        this.mAlert.setView(view, i, i2, i3, i4);
    }

    public void setButton(int i, CharSequence charSequence, Message message) {
        this.mAlert.setButton(i, charSequence, (DialogInterface.OnClickListener) null, message);
    }

    public void setButton(int i, CharSequence charSequence, DialogInterface.OnClickListener onClickListener) {
        this.mAlert.setButton(i, charSequence, onClickListener, (Message) null);
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

    public void setIcon(Drawable drawable) {
        this.mAlert.setIcon(drawable);
    }

    public void setInverseBackgroundForced(boolean z) {
        this.mAlert.setInverseBackgroundForced(z);
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        this.mAlert.installContent();
    }

    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        return this.mAlert.onKeyDown(i, keyEvent) || super.onKeyDown(i, keyEvent);
    }

    public boolean onKeyUp(int i, KeyEvent keyEvent) {
        return this.mAlert.onKeyUp(i, keyEvent) || super.onKeyUp(i, keyEvent);
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

    public static class Builder {
        private final MLAlertController.AlertParams P = new MLAlertController.AlertParams(this.mContext);
        private Context mContext;
        private Context sourceContext;

        public Builder(Context context) {
            this.sourceContext = context;
            this.mContext = new ContextThemeWrapperPro(ContextCompatUtils.loadRealContext(context));
            this.mContext.setTheme(R.style.YpdV5_AlertDialog);
        }

        public EditText getInputView() {
            return (EditText) this.P.mView;
        }

        public Builder setTitle(int i) {
            this.P.mTitle = this.sourceContext.getText(i);
            return this;
        }

        public Builder setTitle(CharSequence charSequence) {
            this.P.mTitle = charSequence;
            return this;
        }

        public Builder setCustomTitle(View view) {
            this.P.mCustomTitleView = view;
            return this;
        }

        public Builder setMessage(int i) {
            this.P.mMessage = this.sourceContext.getText(i);
            return this;
        }

        public Builder setMessage(CharSequence charSequence) {
            this.P.mMessage = charSequence;
            return this;
        }

        public Builder setMessage(SpannableStringBuilder spannableStringBuilder) {
            this.P.mMesageBuilder = spannableStringBuilder;
            return this;
        }

        public Builder setIcon(int i) {
            this.P.mIcon = this.sourceContext.getResources().getDrawable(i);
            return this;
        }

        public Builder setIcon(Drawable drawable) {
            this.P.mIcon = drawable;
            return this;
        }

        public Builder setPositiveButton(int i, DialogInterface.OnClickListener onClickListener) {
            this.P.mPositiveButtonText = this.sourceContext.getText(i);
            this.P.mPositiveButtonListener = onClickListener;
            return this;
        }

        public Builder setPositiveButton(CharSequence charSequence, DialogInterface.OnClickListener onClickListener) {
            this.P.mPositiveButtonText = charSequence;
            this.P.mPositiveButtonListener = onClickListener;
            return this;
        }

        public Builder setNegativeButton(int i, DialogInterface.OnClickListener onClickListener) {
            this.P.mNegativeButtonText = this.sourceContext.getText(i);
            this.P.mNegativeButtonListener = onClickListener;
            return this;
        }

        public Builder setNegativeButton(CharSequence charSequence, DialogInterface.OnClickListener onClickListener) {
            this.P.mNegativeButtonText = charSequence;
            this.P.mNegativeButtonListener = onClickListener;
            return this;
        }

        public Builder setNeutralButton(int i, DialogInterface.OnClickListener onClickListener) {
            this.P.mNeutralButtonText = this.sourceContext.getText(i);
            this.P.mNeutralButtonListener = onClickListener;
            return this;
        }

        public Builder setNeutralButton(CharSequence charSequence, DialogInterface.OnClickListener onClickListener) {
            this.P.mNeutralButtonText = charSequence;
            this.P.mNeutralButtonListener = onClickListener;
            return this;
        }

        public Builder setCancelable(boolean z) {
            this.P.mCancelable = z;
            return this;
        }

        public Builder setOnCancelListener(DialogInterface.OnCancelListener onCancelListener) {
            this.P.mOnCancelListener = onCancelListener;
            return this;
        }

        public Builder setOnKeyListener(DialogInterface.OnKeyListener onKeyListener) {
            this.P.mOnKeyListener = onKeyListener;
            return this;
        }

        public Builder setItems(int i, DialogInterface.OnClickListener onClickListener) {
            this.P.mItems = this.sourceContext.getResources().getTextArray(i);
            this.P.mOnClickListener = onClickListener;
            return this;
        }

        public Builder setItems(CharSequence[] charSequenceArr, DialogInterface.OnClickListener onClickListener) {
            this.P.mItems = charSequenceArr;
            this.P.mOnClickListener = onClickListener;
            return this;
        }

        public Builder setAdapter(ListAdapter listAdapter, DialogInterface.OnClickListener onClickListener) {
            this.P.mAdapter = listAdapter;
            this.P.mOnClickListener = onClickListener;
            return this;
        }

        public Builder setCursor(Cursor cursor, DialogInterface.OnClickListener onClickListener, String str) {
            this.P.mCursor = cursor;
            this.P.mLabelColumn = str;
            this.P.mOnClickListener = onClickListener;
            return this;
        }

        public Builder setMultiChoiceItems(int i, boolean[] zArr, DialogInterface.OnMultiChoiceClickListener onMultiChoiceClickListener) {
            this.P.mItems = this.sourceContext.getResources().getTextArray(i);
            this.P.mOnCheckboxClickListener = onMultiChoiceClickListener;
            this.P.mCheckedItems = zArr;
            this.P.mIsMultiChoice = true;
            return this;
        }

        public Builder setMultiChoiceItems(CharSequence[] charSequenceArr, boolean[] zArr, DialogInterface.OnMultiChoiceClickListener onMultiChoiceClickListener) {
            this.P.mItems = charSequenceArr;
            this.P.mOnCheckboxClickListener = onMultiChoiceClickListener;
            this.P.mCheckedItems = zArr;
            this.P.mIsMultiChoice = true;
            return this;
        }

        public Builder setMultiChoiceItems(Cursor cursor, String str, String str2, DialogInterface.OnMultiChoiceClickListener onMultiChoiceClickListener) {
            this.P.mCursor = cursor;
            this.P.mOnCheckboxClickListener = onMultiChoiceClickListener;
            this.P.mIsCheckedColumn = str;
            this.P.mLabelColumn = str2;
            this.P.mIsMultiChoice = true;
            return this;
        }

        public Builder setSingleChoiceItems(int i, int i2, DialogInterface.OnClickListener onClickListener) {
            this.P.mItems = this.sourceContext.getResources().getTextArray(i);
            this.P.mOnClickListener = onClickListener;
            this.P.mCheckedItem = i2;
            this.P.mIsSingleChoice = true;
            return this;
        }

        public Builder setSingleChoiceItems(Cursor cursor, int i, String str, DialogInterface.OnClickListener onClickListener) {
            this.P.mCursor = cursor;
            this.P.mOnClickListener = onClickListener;
            this.P.mCheckedItem = i;
            this.P.mLabelColumn = str;
            this.P.mIsSingleChoice = true;
            return this;
        }

        public Builder setSingleChoiceItems(CharSequence[] charSequenceArr, int i, DialogInterface.OnClickListener onClickListener) {
            this.P.mItems = charSequenceArr;
            this.P.mOnClickListener = onClickListener;
            this.P.mCheckedItem = i;
            this.P.mIsSingleChoice = true;
            return this;
        }

        public Builder setSingleChoiceItems(ListAdapter listAdapter, int i, DialogInterface.OnClickListener onClickListener) {
            this.P.mAdapter = listAdapter;
            this.P.mOnClickListener = onClickListener;
            this.P.mCheckedItem = i;
            this.P.mIsSingleChoice = true;
            return this;
        }

        public Builder setOnItemSelectedListener(AdapterView.OnItemSelectedListener onItemSelectedListener) {
            this.P.mOnItemSelectedListener = onItemSelectedListener;
            return this;
        }

        public Builder setView(View view) {
            this.P.mView = view;
            this.P.mViewSpacingSpecified = false;
            return this;
        }

        public Builder setInputView(String str, boolean z) {
            Context applicationContext = YPDToast.getInstance().getApplicationContext();
            View inflate = View.inflate(applicationContext, R.layout.ypd_ml_alert_dialog_input_view, (ViewGroup) null);
            setView(inflate, applicationContext.getResources().getDimensionPixelSize(R.dimen.ypd_alert_dialog_button_panel_padding_horizontal), 0, applicationContext.getResources().getDimensionPixelSize(R.dimen.ypd_alert_dialog_button_panel_padding_horizontal), applicationContext.getResources().getDimensionPixelSize(R.dimen.ypd_alert_dialog_custom_panel_padding_bottom));
            EditText editText = (EditText) inflate.findViewById(R.id.input_text);
            editText.setSingleLine(z);
            if (!TextUtils.isEmpty(str)) {
                editText.setHint(str);
            }
            editText.requestFocus();
            return this;
        }

        public void setCustomTitle(CharSequence charSequence) {
            this.P.mCustomTitle = charSequence;
        }

        public Builder setDismissCallBack(DismissCallBack dismissCallBack) {
            this.P.mDismissCallBack = dismissCallBack;
            return this;
        }

        public Builder setView(View view, int i, int i2, int i3, int i4) {
            this.P.mView = view;
            this.P.mViewSpacingSpecified = true;
            this.P.mViewSpacingLeft = i;
            this.P.mViewSpacingTop = i2;
            this.P.mViewSpacingRight = i3;
            this.P.mViewSpacingBottom = i4;
            return this;
        }

        public Builder setView(View view, int i, int i2, int i3, int i4, boolean z) {
            this.P.mView = view;
            this.P.mViewSpacingSpecified = true;
            this.P.mViewSpacingLeft = i;
            this.P.mViewSpacingTop = i2;
            this.P.mViewSpacingRight = i3;
            this.P.mViewSpacingBottom = i4;
            this.P.mCustomBgTransplant = z;
            return this;
        }

        public Builder setInverseBackgroundForced(boolean z) {
            this.P.mForceInverseBackground = z;
            return this;
        }

        public Builder setRecycleOnMeasureEnabled(boolean z) {
            this.P.mRecycleOnMeasure = z;
            return this;
        }

        public Builder setAudoDismiss(boolean z) {
            this.P.mAutoDismiss = z;
            return this;
        }

        public MLAlertDialog create() {
            MLAlertDialog mLAlertDialog = new MLAlertDialog(this.P.mContext);
            mLAlertDialog.mItemTexts = this.P.mItems;
            this.P.apply(mLAlertDialog.mAlert);
            mLAlertDialog.setCancelable(this.P.mCancelable);
            if (this.P.mCancelable) {
                mLAlertDialog.setCanceledOnTouchOutside(true);
            }
            mLAlertDialog.setOnCancelListener(this.P.mOnCancelListener);
            if (this.P.mOnKeyListener != null) {
                mLAlertDialog.setOnKeyListener(this.P.mOnKeyListener);
            }
            mLAlertDialog.setDismissCallBack(this.P.mDismissCallBack);
            return mLAlertDialog;
        }

        public MLAlertDialog createCenter() {
            MLAlertDialog mLAlertDialog = new MLAlertDialog(this.P.mContext, true, (DialogInterface.OnCancelListener) null, 17, 0);
            mLAlertDialog.mItemTexts = this.P.mItems;
            this.P.apply(mLAlertDialog.mAlert);
            mLAlertDialog.setCancelable(this.P.mCancelable);
            if (this.P.mCancelable) {
                mLAlertDialog.setCanceledOnTouchOutside(true);
            }
            mLAlertDialog.setOnCancelListener(this.P.mOnCancelListener);
            if (this.P.mOnKeyListener != null) {
                mLAlertDialog.setOnKeyListener(this.P.mOnKeyListener);
            }
            mLAlertDialog.setDismissCallBack(this.P.mDismissCallBack);
            return mLAlertDialog;
        }

        public MLAlertDialog show() {
            MLAlertDialog create = create();
            showDialog(create);
            return create;
        }

        public MLAlertDialog showCenter() {
            MLAlertDialog createCenter = createCenter();
            showDialog(createCenter);
            return createCenter;
        }

        private void showDialog(MLAlertDialog mLAlertDialog) {
            mLAlertDialog.show();
            if (mLAlertDialog.getWindow() != null) {
                WindowManager.LayoutParams attributes = mLAlertDialog.getWindow().getAttributes();
                attributes.width = -1;
                attributes.height = -2;
                mLAlertDialog.getWindow().setAttributes(attributes);
            }
        }
    }
}
