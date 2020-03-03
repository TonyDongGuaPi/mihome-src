package com.mi.global.bbs.view.dialog;

import android.app.Dialog;
import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.mi.global.bbs.R;
import com.mi.model.UpdaterInfo;
import java.util.ArrayList;

public class UpdateDialog extends Dialog {
    private Context context;
    OnUpdateClickListener onUpdateClickListener;
    @BindView(2131494172)
    LinearLayout updateDesContent;
    @BindView(2131494170)
    TextView updateDialogBt;
    @BindView(2131494171)
    ImageView updateDialogCloseBt;

    public interface OnUpdateClickListener {
        void onUpdate();
    }

    public UpdateDialog(Context context2) {
        super(context2, R.style.UpdateDialog);
        this.context = context2;
        setContentView(R.layout.bbs_update_dialog_layout);
        ButterKnife.bind((Dialog) this);
        WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams();
        Window window = getWindow();
        layoutParams.copyFrom(window.getAttributes());
        layoutParams.width = -1;
        layoutParams.height = -1;
        window.setAttributes(layoutParams);
        this.updateDialogBt.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                UpdateDialog.this.dismiss();
                if (UpdateDialog.this.onUpdateClickListener != null) {
                    UpdateDialog.this.onUpdateClickListener.onUpdate();
                }
            }
        });
        this.updateDialogCloseBt.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                UpdateDialog.this.dismiss();
            }
        });
    }

    public void setUpdateMsg(UpdaterInfo updaterInfo) {
        this.updateDesContent.removeAllViews();
        ArrayList<UpdaterInfo.DescType> arrayList = updaterInfo.f7375a;
        for (int i = 0; i < arrayList.size(); i++) {
            View inflate = LayoutInflater.from(this.context).inflate(R.layout.bbs_update_list_item, (ViewGroup) null, false);
            TextView textView = (TextView) inflate.findViewById(R.id.update_log_type);
            TextView textView2 = (TextView) inflate.findViewById(R.id.update_log_desc);
            textView.setText(arrayList.get(i).mDescType);
            if (TextUtils.isEmpty(arrayList.get(i).mDescType)) {
                textView.setVisibility(8);
            }
            textView2.setText(arrayList.get(i).mDesc);
            this.updateDesContent.addView(inflate);
        }
    }

    public void setOnUpdateClickListener(OnUpdateClickListener onUpdateClickListener2) {
        this.onUpdateClickListener = onUpdateClickListener2;
    }
}
