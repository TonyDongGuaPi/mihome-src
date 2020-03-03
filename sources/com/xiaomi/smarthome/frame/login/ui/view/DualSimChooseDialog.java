package com.xiaomi.smarthome.frame.login.ui.view;

import android.content.Context;
import android.content.DialogInterface;
import android.text.TextUtils;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.library.common.dialog.MLAlertDialog;
import com.xiaomi.youpin.login.api.phone.LocalPhoneDetailInfo;
import java.util.List;

public class DualSimChooseDialog {

    /* renamed from: a  reason: collision with root package name */
    private MLAlertDialog f16299a;
    private Context b;
    /* access modifiers changed from: private */
    public List<LocalPhoneDetailInfo> c;

    public interface OnSimChooseListener {
        void a();

        void a(LocalPhoneDetailInfo localPhoneDetailInfo);
    }

    public DualSimChooseDialog(Context context) {
        this.b = context;
    }

    public void a(List<LocalPhoneDetailInfo> list, final OnSimChooseListener onSimChooseListener) {
        if (list != null && list.size() == 2) {
            this.c = list;
            CharSequence[] charSequenceArr = new CharSequence[2];
            for (int i = 0; i < 2; i++) {
                LocalPhoneDetailInfo localPhoneDetailInfo = list.get(i);
                if (TextUtils.isEmpty(localPhoneDetailInfo.b.userName)) {
                    charSequenceArr[i] = localPhoneDetailInfo.b.phone;
                } else {
                    charSequenceArr[i] = this.b.getString(R.string.login_dual_sim_item, new Object[]{localPhoneDetailInfo.b.phone, localPhoneDetailInfo.b.userName});
                }
            }
            this.f16299a = new MLAlertDialog.Builder(this.b).a((CharSequence) this.b.getString(R.string.login_dual_sim_title)).a(charSequenceArr, 0, (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialogInterface, int i) {
                    List a2;
                    if (!(onSimChooseListener == null || (a2 = DualSimChooseDialog.this.c) == null || a2.size() <= i)) {
                        onSimChooseListener.a((LocalPhoneDetailInfo) a2.get(i));
                    }
                    dialogInterface.dismiss();
                }
            }).b((CharSequence) this.b.getString(R.string.cancel), (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialogInterface, int i) {
                    if (onSimChooseListener != null) {
                        onSimChooseListener.a();
                    }
                }
            }).a(false).b();
            this.f16299a.show();
        }
    }
}
