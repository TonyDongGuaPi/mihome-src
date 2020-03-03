package com.xiaomi.smarthome.miio.camera.face.widget;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.library.common.dialog.MLAlertDialog;
import com.xiaomi.smarthome.library.common.util.StringUtil;
import com.xiaomi.smarthome.library.common.util.ToastUtil;
import com.xiaomi.smarthome.miio.camera.face.util.FaceUtils;

public class FaceRenameDialog extends MLAlertDialog {
    private String figureName;
    /* access modifiers changed from: private */
    public EditText mEditText;
    /* access modifiers changed from: private */
    public OnNameInputListener mOnNameInputListener;
    private TextView tvError;

    public interface OnNameInputListener {
        void onNameInput(String str);
    }

    public FaceRenameDialog(Context context, String str) {
        super(context, 2131559365, 17, 0);
        this.figureName = str;
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        View inflate = LayoutInflater.from(this.mContext).inflate(R.layout.face_rename_dialog, (ViewGroup) null);
        this.mEditText = (EditText) inflate.findViewById(R.id.input_text);
        this.tvError = (TextView) inflate.findViewById(R.id.tv_error);
        this.mEditText.setText(this.figureName);
        this.mEditText.setSelection(this.figureName.length());
        setTitle(R.string.face_mark_dialog_title);
        setView(inflate);
        setAudoDismiss(false);
        setNegativeButton(R.string.camera_cancel, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });
        setPositiveButton(R.string.camera_sure, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
                String obj = FaceRenameDialog.this.mEditText.getText().toString();
                if (TextUtils.isEmpty(obj)) {
                    ToastUtil.a((int) R.string.add_feature_empty_tips);
                } else if (StringUtil.a((CharSequence) obj) > 10) {
                    ToastUtil.a((int) R.string.add_feature_max_tips);
                } else if (FaceUtils.containsEmoji(obj)) {
                    ToastUtil.a((int) R.string.no_emoij_tips);
                } else if (FaceRenameDialog.this.mOnNameInputListener != null) {
                    FaceRenameDialog.this.mOnNameInputListener.onNameInput(obj);
                }
            }
        });
        super.onCreate(bundle);
        initEditText();
    }

    private void initEditText() {
        this.mEditText.addTextChangedListener(new TextWatcher() {
            public void afterTextChanged(Editable editable) {
            }

            public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }

            public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
                int a2;
                if (StringUtil.a(charSequence) > 10 && (a2 = StringUtil.a(charSequence, 10)) < charSequence.length()) {
                    FaceRenameDialog.this.mEditText.setText(charSequence.subSequence(0, a2));
                    FaceRenameDialog.this.mEditText.setSelection(a2);
                }
            }
        });
    }

    public void showError() {
        this.tvError.setVisibility(0);
    }

    public void setOnNameInputListener(OnNameInputListener onNameInputListener) {
        this.mOnNameInputListener = onNameInputListener;
    }

    public void setPositiveButton(int i, DialogInterface.OnClickListener onClickListener) {
        setButton(-1, (CharSequence) getContext().getString(i), onClickListener);
    }

    public void setNegativeButton(int i, DialogInterface.OnClickListener onClickListener) {
        setButton(-2, (CharSequence) getContext().getString(i), onClickListener);
    }
}
