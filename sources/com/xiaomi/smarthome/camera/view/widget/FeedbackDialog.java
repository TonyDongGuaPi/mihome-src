package com.xiaomi.smarthome.camera.view.widget;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.SpannableStringBuilder;
import android.text.method.LinkMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.library.common.dialog.MLAlertDialog;

public class FeedbackDialog extends MLAlertDialog {
    private CheckBox mCheckBox;
    /* access modifiers changed from: private */
    public DialogInterface.OnClickListener mOnClickListener;
    private String model;

    /* renamed from: tv  reason: collision with root package name */
    private TextView f13925tv;

    public FeedbackDialog(Context context) {
        super(context);
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        View inflate = LayoutInflater.from(this.mContext).inflate(R.layout.nobady_subtitle, (ViewGroup) null);
        this.mCheckBox = (CheckBox) inflate.findViewById(R.id.cbNobodySubtitle);
        this.mCheckBox.setVisibility(8);
        this.f13925tv = (TextView) inflate.findViewById(R.id.tvNobodySubtitle);
        new SpannableStringBuilder();
        String string = this.mContext.getResources().getString(R.string.nobody_subtitle);
        if (string.contains("。")) {
            string = string.substring(0, string.lastIndexOf("。") + 1);
        } else if (string.contains(".")) {
            string = string.substring(0, string.lastIndexOf(".") + 1);
        }
        this.f13925tv.setText(string);
        this.f13925tv.setMovementMethod(LinkMovementMethod.getInstance());
        this.f13925tv.setLineSpacing(0.0f, 1.5f);
        this.mCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                Button button = FeedbackDialog.this.getButton(-1);
                if (z) {
                    button.setEnabled(true);
                    button.setTextColor(FeedbackDialog.this.mContext.getResources().getColor(R.color.video_player_selected_color));
                    return;
                }
                button.setEnabled(false);
                button.setTextColor(FeedbackDialog.this.mContext.getResources().getColor(R.color.grey));
            }
        });
        this.mCheckBox.setChecked(true);
        setView(inflate);
        setCancelable(false);
        setAudoDismiss(false);
        setNegativeButton(R.string.camera_cancel, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });
        setPositiveButton(R.string.nobody_feedback, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
                if (FeedbackDialog.this.mOnClickListener != null) {
                    FeedbackDialog.this.mOnClickListener.onClick(dialogInterface, i);
                }
            }
        });
        super.onCreate(bundle);
    }

    public void setPositiveButton(int i, DialogInterface.OnClickListener onClickListener) {
        setButton(-1, (CharSequence) getContext().getString(i), onClickListener);
    }

    public void setNegativeButton(int i, DialogInterface.OnClickListener onClickListener) {
        setButton(-2, (CharSequence) getContext().getString(i), onClickListener);
    }

    public void setOnClickListener(DialogInterface.OnClickListener onClickListener) {
        this.mOnClickListener = onClickListener;
    }

    public void setModel(String str) {
        this.model = str;
    }
}
