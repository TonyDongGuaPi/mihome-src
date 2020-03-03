package com.xiaomi.payment.channel;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.mibi.common.base.TaskManager;
import com.mibi.common.data.Session;
import com.mibi.common.data.SortedParameter;
import com.xiaomi.payment.data.MibiConstants;
import com.xiaomi.payment.platform.R;
import com.xiaomi.payment.task.CheckTySMSCodeTask;
import com.xiaomi.payment.task.TyUnicomRegetSmsCodeTask;
import com.xiaomi.payment.ui.fragment.SMSCodeFragment;

public class TyUnicomSMSCodeFragment extends SMSCodeFragment {
    private String B;
    private long C;
    private TyRegetSmsCodeTaskAdapter D;
    private TyCheckSMSCodeTaskAdapter E;
    protected String t;

    /* access modifiers changed from: protected */
    public void b_(Bundle bundle) {
        super.b_(bundle);
        this.B = bundle.getString(MibiConstants.eZ);
        this.C = bundle.getLong(MibiConstants.f12224de);
        this.t = bundle.getString(MibiConstants.dl);
    }

    public View a(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View a2 = super.a(layoutInflater, viewGroup, bundle);
        this.y.setText(getString(R.string.mibi_get_phone_sms_prompt, new Object[]{this.t}));
        return a2;
    }

    public void c(Bundle bundle) {
        super.c(bundle);
        N();
    }

    /* access modifiers changed from: protected */
    public SMSCodeFragment.RegetSmsCodeTaskAdapter K() {
        if (this.D == null) {
            this.D = new TyRegetSmsCodeTaskAdapter(getActivity(), this.b, this.z);
        }
        this.D.a(this.B, this.C, this.t);
        return this.D;
    }

    /* access modifiers changed from: protected */
    /* renamed from: L */
    public TyCheckSMSCodeTaskAdapter M() {
        if (this.E == null) {
            this.E = new TyCheckSMSCodeTaskAdapter(getActivity(), this.b, this.z);
        }
        return this.E;
    }

    protected class TyCheckSMSCodeTaskAdapter extends SMSCodeFragment.CheckSMSCodeTaskAdapter<CheckTySMSCodeTask, Void, CheckTySMSCodeTask.Result> {
        public TyCheckSMSCodeTaskAdapter(Context context, Session session, TaskManager taskManager) {
            super(context, taskManager, new CheckTySMSCodeTask(context, session));
        }

        /* access modifiers changed from: protected */
        /* renamed from: a */
        public boolean b(String str, int i, CheckTySMSCodeTask.Result result) {
            if (i != 7003) {
                return super.b(str, i, result);
            }
            a(str, i, result);
            TyUnicomSMSCodeFragment.this.x.setEnabled(true);
            return true;
        }
    }

    protected class TyRegetSmsCodeTaskAdapter extends SMSCodeFragment.RegetSmsCodeTaskAdapter<TyUnicomRegetSmsCodeTask, Void, TyUnicomRegetSmsCodeTask.Result> {
        private long g;
        private String h;
        private String i;

        public TyRegetSmsCodeTaskAdapter(Context context, Session session, TaskManager taskManager) {
            super(context, taskManager, new TyUnicomRegetSmsCodeTask(context, session));
        }

        public void a(String str, long j, String str2) {
            this.h = str;
            this.g = j;
            this.i = str2;
            start();
        }

        /* access modifiers changed from: protected */
        public SortedParameter j() {
            SortedParameter j = super.j();
            j.a(MibiConstants.eZ, (Object) this.h);
            j.a(MibiConstants.dq, (Object) Long.valueOf(this.g));
            j.a(MibiConstants.fm, (Object) this.i);
            return j;
        }
    }
}
