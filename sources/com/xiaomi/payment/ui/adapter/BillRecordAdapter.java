package com.xiaomi.payment.ui.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.mibi.common.data.ArrayAdapter;
import com.mibi.common.data.Utils;
import com.xiaomi.payment.data.MibiConstants;
import com.xiaomi.payment.platform.R;
import com.xiaomi.payment.task.BillRecordTask;
import java.util.ArrayList;
import java.util.Calendar;

public class BillRecordAdapter extends ArrayAdapter<BillRecordTask.Result.BillRecordItem> {
    private LayoutInflater c;
    private Calendar d = Calendar.getInstance();

    public BillRecordAdapter(Context context) {
        super(context);
        this.c = LayoutInflater.from(context);
    }

    public View a(Context context, int i, BillRecordTask.Result.BillRecordItem billRecordItem, ViewGroup viewGroup) {
        return this.c.inflate(R.layout.mibi_bill_list_item, viewGroup, false);
    }

    public void a(View view, int i, BillRecordTask.Result.BillRecordItem billRecordItem) {
        if (billRecordItem != null) {
            TextView textView = (TextView) view.findViewById(R.id.bill_fee);
            ((TextView) view.findViewById(R.id.title)).setText(billRecordItem.mBillRecordDesc);
            this.d.setTimeInMillis(billRecordItem.mBillTime);
            ((TextView) view.findViewById(R.id.record_time)).setText(DateFormat.format(this.f7498a.getString(R.string.mibi_format_bill_date), this.d).toString());
            String str = "";
            if (TextUtils.equals(billRecordItem.mBillRecordType, MibiConstants.dn)) {
                if (billRecordItem.mBillFee > 0) {
                    textView.setTextColor(this.f7498a.getResources().getColor(R.color.mibi_text_color_record_flow_in));
                    str = "+";
                } else {
                    textView.setTextColor(this.f7498a.getResources().getColor(R.color.mibi_text_color_record_flow_out));
                    str = "-";
                }
            } else if (TextUtils.equals(billRecordItem.mBillRecordType, "trade")) {
                if (billRecordItem.mBillFee > 0) {
                    textView.setTextColor(this.f7498a.getResources().getColor(R.color.mibi_text_color_record_flow_out));
                    str = "-";
                } else {
                    textView.setTextColor(this.f7498a.getResources().getColor(R.color.mibi_text_color_record_flow_in));
                    str = "+";
                }
            }
            long abs = Math.abs(billRecordItem.mBillFee);
            textView.setText(this.f7498a.getString(R.string.mibi_unit_value, new Object[]{str + Utils.a(abs)}));
            return;
        }
        throw new IllegalStateException("BillRecordItem data is null at this position " + i);
    }

    public ArrayList<BillRecordTask.Result.BillRecordItem> a() {
        return this.b;
    }
}
