package com.xiaomi.payment.pay;

import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.mibi.common.base.BasePaymentTask;
import com.mibi.common.base.TaskManager;
import com.mibi.common.data.Session;
import com.xiaomi.payment.data.MibiConstants;
import com.xiaomi.payment.platform.R;
import com.xiaomi.payment.task.BaseCheckRiskAndPayTask;
import com.xiaomi.payment.task.CheckRiskTask;
import com.xiaomi.payment.task.VerifyRegetSmsCodeTask;
import com.xiaomi.payment.ui.fragment.SMSCodeFragment;

public class PaymentVerifySMSCodeFragment extends SMSCodeFragment {
    private VerifyRegetSmsCodeTaskAdapter B;
    private VerifyCheckSMSCodeTaskAdapter C;
    protected String t;

    /* access modifiers changed from: protected */
    public void b_(Bundle bundle) {
        super.b_(bundle);
        this.t = bundle.getString(MibiConstants.dl);
        if (TextUtils.isEmpty(this.t)) {
            throw new IllegalArgumentException("PhoneNum should not be null in PaymentVerifySMSCodeFragment");
        }
    }

    public View a(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View a2 = super.a(layoutInflater, viewGroup, bundle);
        int length = this.t.length();
        if (length > 4) {
            this.t = this.t.substring(length - 4);
        }
        this.y.setText(getString(R.string.mibi_summary_sms_code, new Object[]{this.t}));
        return a2;
    }

    public void c(Bundle bundle) {
        super.c(bundle);
        e(60);
    }

    /* access modifiers changed from: protected */
    public SMSCodeFragment.RegetSmsCodeTaskAdapter K() {
        if (this.B == null) {
            this.B = new VerifyRegetSmsCodeTaskAdapter(getActivity(), this.b, this.z);
        }
        return this.B;
    }

    /* access modifiers changed from: protected */
    /* renamed from: L */
    public VerifyCheckSMSCodeTaskAdapter M() {
        if (this.C == null) {
            this.C = new VerifyCheckSMSCodeTaskAdapter(getActivity(), this.b, this.z);
        }
        return this.C;
    }

    protected class VerifyRegetSmsCodeTaskAdapter extends SMSCodeFragment.RegetSmsCodeTaskAdapter<VerifyRegetSmsCodeTask, Void, BasePaymentTask.Result> {
        public VerifyRegetSmsCodeTaskAdapter(Context context, Session session, TaskManager taskManager) {
            super(context, taskManager, new VerifyRegetSmsCodeTask(context, session));
        }
    }

    protected class VerifyCheckSMSCodeTaskAdapter extends SMSCodeFragment.CheckSMSCodeTaskAdapter<CheckRiskTask, Void, BaseCheckRiskAndPayTask.Result> {
        public VerifyCheckSMSCodeTaskAdapter(Context context, Session session, TaskManager taskManager) {
            super(context, taskManager, new CheckRiskTask(context, session));
        }

        /* access modifiers changed from: protected */
        /* renamed from: a */
        public boolean b(String str, int i, BaseCheckRiskAndPayTask.Result result) {
            if (i == 7003) {
                a(str, i, result);
                PaymentVerifySMSCodeFragment.this.x.setEnabled(true);
                return true;
            } else if (i != 1985 && i != 7002) {
                return false;
            } else {
                Bundle bundle = new Bundle();
                bundle.putSerializable(MibiConstants.cI, result);
                bundle.putInt(MibiConstants.da, i);
                PaymentVerifySMSCodeFragment.this.b(1104, bundle);
                PaymentVerifySMSCodeFragment.this.E();
                return true;
            }
        }
    }
}
