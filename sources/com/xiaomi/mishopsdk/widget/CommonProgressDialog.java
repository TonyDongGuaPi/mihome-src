package com.xiaomi.mishopsdk.widget;

import android.app.Dialog;
import android.content.Context;
import android.widget.TextView;
import com.xiaomi.mishopsdk.R;

public class CommonProgressDialog extends Dialog {
    private TextView mMessageView = ((TextView) findViewById(R.id.mishopsdk_message_view));

    public CommonProgressDialog(Context context) {
        super(context, R.style.mishopsdk_Widget_Dialog_Progress);
        setCanceledOnTouchOutside(false);
        setContentView(R.layout.mishopsdk_common_progress_dialog);
    }

    public void setMessage(String str) {
        this.mMessageView.setText(str);
    }

    public void setMessage(int i) {
        this.mMessageView.setText(i);
    }
}
