package com.xiaomi.payment.ui.fragment;

import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import com.libra.Color;
import com.mibi.common.base.BaseFragment;
import com.mibi.common.base.BasePaymentTask;
import com.mibi.common.base.BasePaymentTaskAdapter;
import com.mibi.common.base.TaskAdapter;
import com.mibi.common.base.TaskManager;
import com.mibi.common.component.ProgressButton;
import com.mibi.common.data.SortedParameter;
import com.mibi.common.data.Utils;
import com.xiaomi.payment.platform.R;
import com.xiaomi.payment.task.CountDownTask;

public abstract class SMSCodeFragment extends BaseFragment {
    protected static final int u = 60;
    protected String A;
    /* access modifiers changed from: private */
    public TextView B;
    private CountDownTaskAdapter C;
    private View.OnClickListener D = new View.OnClickListener() {
        public void onClick(View view) {
            String obj = SMSCodeFragment.this.t.getText().toString();
            if (TextUtils.isEmpty(obj)) {
                SMSCodeFragment.this.B.setVisibility(0);
                SMSCodeFragment.this.B.setText(SMSCodeFragment.this.getString(R.string.mibi_sms_code_empty));
                return;
            }
            SMSCodeFragment.this.B.setVisibility(8);
            SMSCodeFragment.this.d(obj);
        }
    };
    private View.OnClickListener E = new View.OnClickListener() {
        public void onClick(View view) {
            SMSCodeFragment.this.N();
            SMSCodeFragment.this.x.setEnabled(true);
            SMSCodeFragment.this.w.setEnabled(false);
        }
    };
    /* access modifiers changed from: private */
    public EditText t;
    protected TextView v;
    protected ProgressButton w;
    /* access modifiers changed from: protected */
    public ProgressButton x;
    protected TextView y;
    protected TaskManager z;

    /* access modifiers changed from: protected */
    public abstract RegetSmsCodeTaskAdapter K();

    /* access modifiers changed from: protected */
    public abstract CheckSMSCodeTaskAdapter M();

    public View a(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = layoutInflater.inflate(R.layout.mibi_payment_verify_sms_code, (ViewGroup) null);
        this.v = (TextView) inflate.findViewById(R.id.layout_action_bar).findViewById(R.id.action_bar_title);
        this.w = (ProgressButton) inflate.findViewById(R.id.button_reget_sms_code);
        this.w.setOnClickListener(this.E);
        this.x = (ProgressButton) inflate.findViewById(R.id.button_confirm);
        this.x.setOnClickListener(this.D);
        this.t = (EditText) inflate.findViewById(R.id.edit_sms_code);
        this.B = (TextView) inflate.findViewById(R.id.sms_code_error_info);
        this.y = (TextView) inflate.findViewById(R.id.sms_code_summary);
        return inflate;
    }

    /* access modifiers changed from: protected */
    public void b_(Bundle bundle) {
        super.b_(bundle);
        this.A = bundle.getString("processId");
        this.z = q();
    }

    public void c(Bundle bundle) {
        super.c(bundle);
        this.v.setText(R.string.mibi_title_sms_code);
        d(false);
    }

    /* access modifiers changed from: protected */
    public void N() {
        K().start();
    }

    /* access modifiers changed from: protected */
    public void d(String str) {
        M().a(str);
    }

    /* access modifiers changed from: protected */
    public void e(int i) {
        if (this.C == null) {
            this.C = new CountDownTaskAdapter(this.z);
        }
        this.C.a(i);
    }

    private class CountDownTaskAdapter extends TaskAdapter<CountDownTask, Integer, Void> {
        private int e;

        public CountDownTaskAdapter(TaskManager taskManager) {
            super(taskManager, new CountDownTask(true));
        }

        public void a(int i) {
            this.e = i;
            start();
        }

        /* access modifiers changed from: protected */
        public SortedParameter j() {
            SortedParameter sortedParameter = new SortedParameter();
            sortedParameter.a("count", (Object) Integer.valueOf(this.e));
            return sortedParameter;
        }

        /* renamed from: a */
        public void onProgressUpdate(Integer num) {
            SMSCodeFragment.this.w.setText(SMSCodeFragment.this.getString(R.string.mibi_button_count_down_reget, new Object[]{Integer.valueOf(num.intValue())}));
        }

        public void onTaskStart() {
            SMSCodeFragment.this.w.setText(SMSCodeFragment.this.getString(R.string.mibi_button_count_down_reget, new Object[]{Integer.valueOf(this.e)}));
            SMSCodeFragment.this.w.setTextColor(Color.c);
            SMSCodeFragment.this.w.setEnabled(false);
        }

        /* renamed from: a */
        public void onTaskComplete(Void voidR) {
            SMSCodeFragment.this.w.setText(R.string.mibi_button_reget);
            SMSCodeFragment.this.w.setTextColor(SMSCodeFragment.this.getResources().getColor(R.color.mibi_text_color_payment_get_sms_code));
            SMSCodeFragment.this.w.setEnabled(true);
        }

        /* renamed from: b */
        public void onTaskCancelled(Void voidR) {
            SMSCodeFragment.this.w.setText(R.string.mibi_button_reget);
            SMSCodeFragment.this.w.setTextColor(SMSCodeFragment.this.getResources().getColor(R.color.mibi_text_color_payment_get_sms_code));
            SMSCodeFragment.this.w.setEnabled(true);
        }
    }

    protected abstract class RegetSmsCodeTaskAdapter<TaskType extends BasePaymentTask<Progress, TaskResult>, Progress, TaskResult extends BasePaymentTask.Result> extends BasePaymentTaskAdapter<TaskType, Progress, TaskResult> {
        public RegetSmsCodeTaskAdapter(Context context, TaskManager taskManager, TaskType tasktype) {
            super(context, taskManager, tasktype);
        }

        /* access modifiers changed from: protected */
        public void c() {
            SMSCodeFragment.this.w.startProgress();
            SMSCodeFragment.this.B.setVisibility(8);
        }

        /* access modifiers changed from: protected */
        public boolean d() {
            SMSCodeFragment.this.w.stopProgress();
            return true;
        }

        /* access modifiers changed from: protected */
        public SortedParameter j() {
            SortedParameter sortedParameter = new SortedParameter();
            sortedParameter.a("processId", (Object) SMSCodeFragment.this.A);
            return sortedParameter;
        }

        /* access modifiers changed from: protected */
        public void c(BasePaymentTask.Result result) {
            SMSCodeFragment.this.e(60);
        }

        /* access modifiers changed from: protected */
        /* renamed from: c */
        public void a(String str, int i, TaskResult taskresult) {
            SMSCodeFragment.this.B.setVisibility(0);
            SMSCodeFragment.this.B.setText(str);
        }
    }

    protected abstract class CheckSMSCodeTaskAdapter<TaskType extends BasePaymentTask<Progress, TaskResult>, Progress, TaskResult extends BasePaymentTask.Result> extends BasePaymentTaskAdapter<TaskType, Progress, TaskResult> {
        protected String f;

        public CheckSMSCodeTaskAdapter(Context context, TaskManager taskManager, TaskType tasktype) {
            super(context, taskManager, tasktype);
        }

        public void a(String str) {
            this.f = str;
            start();
        }

        /* access modifiers changed from: protected */
        public SortedParameter j() {
            SortedParameter sortedParameter = new SortedParameter();
            sortedParameter.a("processId", (Object) SMSCodeFragment.this.A);
            if (!TextUtils.isEmpty(this.f)) {
                sortedParameter.a("smsCode", (Object) this.f);
            }
            return sortedParameter;
        }

        /* access modifiers changed from: protected */
        public void c() {
            SMSCodeFragment.this.B.setVisibility(8);
            SMSCodeFragment.this.x.startProgress();
        }

        /* access modifiers changed from: protected */
        public boolean d() {
            SMSCodeFragment.this.x.stopProgress();
            return true;
        }

        /* access modifiers changed from: protected */
        public void c(TaskResult taskresult) {
            SMSCodeFragment.this.b(-1, (Bundle) null);
            SMSCodeFragment.this.E();
        }

        /* access modifiers changed from: protected */
        /* renamed from: c */
        public void a(String str, int i, TaskResult taskresult) {
            SMSCodeFragment.this.B.setVisibility(0);
            SMSCodeFragment.this.B.setText(str);
            SMSCodeFragment.this.x.setEnabled(false);
        }

        /* access modifiers changed from: protected */
        public void a(int i, int i2, TaskResult taskresult) {
            super.a(i, i2, taskresult);
            SMSCodeFragment.this.x.setEnabled(true);
        }
    }

    public void o() {
        super.o();
        Utils.a((Context) getActivity(), (View) this.t, false);
    }
}
