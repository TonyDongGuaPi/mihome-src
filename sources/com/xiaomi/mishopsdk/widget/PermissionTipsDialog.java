package com.xiaomi.mishopsdk.widget;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;
import com.xiaomi.mishopsdk.R;

public class PermissionTipsDialog extends Dialog {
    private TextView mCancleText;
    private TextView mDesc;
    /* access modifiers changed from: private */
    public OkListener mOkListener;
    private TextView mTitle;

    public interface OkListener {
        void onOkClicked();
    }

    public PermissionTipsDialog(Context context) {
        super(context);
    }

    public PermissionTipsDialog(Context context, int i, OkListener okListener) {
        super(context, i);
        this.mOkListener = okListener;
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.mishopsdk_permission_tips_dialog);
        WindowManager.LayoutParams attributes = getWindow().getAttributes();
        attributes.gravity = 80;
        onWindowAttributesChanged(attributes);
        setCanceledOnTouchOutside(false);
        setCancelable(false);
    }

    public void setContentView(int i) {
        super.setContentView(i);
        this.mTitle = (TextView) findViewById(R.id.mishopsdk_tips_title);
        this.mDesc = (TextView) findViewById(R.id.mishopsdk_tips_desc);
        this.mCancleText = (TextView) findViewById(R.id.mishopsdk_cancle_text);
        this.mCancleText.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                PermissionTipsDialog.this.dismiss();
                if (PermissionTipsDialog.this.mOkListener != null) {
                    PermissionTipsDialog.this.mOkListener.onOkClicked();
                }
            }
        });
    }

    public void show(String str, String str2) {
        super.show();
        this.mDesc.setText(str2);
        this.mTitle.setText(str);
    }
}
