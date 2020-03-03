package com.xiaomi.payment.ui.fragment;

import android.os.Bundle;
import android.text.TextUtils;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.mibi.common.base.BaseFragment;
import com.mibi.common.component.FormattableEditText;
import com.mibi.common.data.FormatterUtils;
import com.mibi.common.data.Utils;
import com.xiaomi.payment.data.MibiConstants;
import com.xiaomi.payment.platform.R;
import com.xiaomi.payment.task.BillRecordTask;
import java.util.Calendar;

public class RecordDetailFragment extends BaseFragment {
    private TextView A;
    private TextView B;
    private TextView C;
    private TextView D;
    private RECORD_DETAIL_TYPE E;
    private BillRecordTask.Result.BillRecordItem F;
    private TextView t;
    private FormattableEditText u;
    private TextView v;
    private TextView w;
    private TextView x;
    private TextView y;
    private TextView z;

    private enum RECORD_DETAIL_TYPE {
        recharge,
        trade;

        public static RECORD_DETAIL_TYPE fromString(String str) {
            if (TextUtils.equals(str, MibiConstants.dn)) {
                return recharge;
            }
            if (TextUtils.equals(str, "trade")) {
                return trade;
            }
            return null;
        }
    }

    public View a(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = layoutInflater.inflate(R.layout.mibi_bill_detail, viewGroup, false);
        this.t = (TextView) inflate.findViewById(R.id.title);
        this.t.getPaint().setFakeBoldText(true);
        this.u = (FormattableEditText) inflate.findViewById(R.id.orderNo);
        this.u.getPaint().setFakeBoldText(true);
        this.u.setFormatType(FormatterUtils.FormatterType.TYPE_NORMAL);
        this.v = (TextView) inflate.findViewById(R.id.account_1_title);
        this.w = (TextView) inflate.findViewById(R.id.account_1_value);
        this.x = (TextView) inflate.findViewById(R.id.account_2_value);
        this.y = (TextView) inflate.findViewById(R.id.name_title);
        this.z = (TextView) inflate.findViewById(R.id.name_value);
        this.A = (TextView) inflate.findViewById(R.id.mibi_title);
        this.B = (TextView) inflate.findViewById(R.id.mibi_value);
        this.C = (TextView) inflate.findViewById(R.id.time_title);
        this.D = (TextView) inflate.findViewById(R.id.time_value);
        return inflate;
    }

    /* access modifiers changed from: protected */
    public void b_(Bundle bundle) {
        super.b_(bundle);
        this.F = (BillRecordTask.Result.BillRecordItem) bundle.getSerializable(MibiConstants.dm);
        this.E = RECORD_DETAIL_TYPE.fromString(this.F.mBillRecordType);
    }

    public void c(Bundle bundle) {
        super.c(bundle);
        if (this.E == null) {
            return;
        }
        if (this.E == RECORD_DETAIL_TYPE.recharge) {
            a(R.string.mibi_detail_recharge_title);
            K();
            M();
        } else if (this.E == RECORD_DETAIL_TYPE.trade) {
            a(R.string.mibi_detail_consume_title);
            L();
            N();
        }
    }

    private void K() {
        this.t.setText(R.string.mibi_detail_recharge_order);
        this.u.setTextColor(getResources().getColor(R.color.mibi_text_color_record_detail_recharge_order));
        this.v.setText(R.string.mibi_detail_recharge_account);
        this.y.setText(R.string.mibi_detail_recharge_method);
        this.A.setText(R.string.mibi_detail_recharge_amount);
        this.C.setText(R.string.mibi_detail_recharge_time);
    }

    private void L() {
        this.t.setText(R.string.mibi_detail_consume_order);
        this.u.setTextColor(getResources().getColor(R.color.mibi_text_color_record_detail_consume_order));
        this.v.setText(R.string.mibi_detail_consume_account);
        this.y.setText(R.string.mibi_detail_consume_name);
        this.A.setText(R.string.mibi_detail_consume_amount);
        this.C.setText(R.string.mibi_detail_consume_time);
    }

    private void M() {
        this.u.setText(this.F.mBillId);
        this.w.setText(this.F.mBenefitID);
        this.x.setText(this.F.mPayUserID);
        this.z.setText(this.F.mChargeType);
        String b = Utils.b(this.F.mBillFee);
        this.B.setText(getString(R.string.mibi_fee, new Object[]{b}).toString());
        Calendar instance = Calendar.getInstance();
        instance.setTimeInMillis(this.F.mBillTime);
        this.D.setText(DateFormat.format(getString(R.string.mibi_format_time), instance).toString());
    }

    private void N() {
        this.u.setText(this.F.mBillId);
        this.w.setText(this.F.mBenefitID);
        this.x.setText(this.F.mPayUserID);
        this.z.setText(this.F.mBillRecordDesc);
        this.B.setText(this.F.mConsumeDetail);
        Calendar instance = Calendar.getInstance();
        instance.setTimeInMillis(this.F.mBillTime);
        this.D.setText(DateFormat.format(getString(R.string.mibi_format_time), instance).toString());
    }
}
