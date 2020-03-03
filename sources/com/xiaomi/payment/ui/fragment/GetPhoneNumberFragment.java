package com.xiaomi.payment.ui.fragment;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import com.mibi.common.base.BaseFragment;
import com.mibi.common.data.FormatterUtils;
import com.xiaomi.payment.data.MibiConstants;
import com.xiaomi.payment.platform.R;

public class GetPhoneNumberFragment extends BaseFragment {
    private Button t;
    /* access modifiers changed from: private */
    public EditText u;
    /* access modifiers changed from: private */
    public TextView v;
    private View.OnClickListener w = new View.OnClickListener() {
        public void onClick(View view) {
            String a2 = FormatterUtils.a(GetPhoneNumberFragment.this.u.getText().toString().trim(), FormatterUtils.FormatterType.TYPE_PHONE);
            if (!FormatterUtils.d(a2, FormatterUtils.FormatterType.TYPE_PHONE)) {
                GetPhoneNumberFragment.this.v.setVisibility(0);
                GetPhoneNumberFragment.this.v.setText(GetPhoneNumberFragment.this.getString(R.string.mibi_get_phone_format_error));
                return;
            }
            SharedPreferences.Editor edit = GetPhoneNumberFragment.this.b.l().edit();
            edit.putString(MibiConstants.dl, a2);
            edit.apply();
            GetPhoneNumberFragment.this.v.setVisibility(4);
            Bundle bundle = new Bundle();
            bundle.putString(MibiConstants.dl, a2);
            GetPhoneNumberFragment.this.b(-1, bundle);
            GetPhoneNumberFragment.this.E();
        }
    };

    public View a(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = layoutInflater.inflate(R.layout.mibi_get_phone_num, (ViewGroup) null);
        this.t = (Button) inflate.findViewById(R.id.button_confirm);
        this.t.setOnClickListener(this.w);
        this.u = (EditText) inflate.findViewById(R.id.edit_phone_num);
        this.v = (TextView) inflate.findViewById(R.id.error_info);
        FormatterUtils.a(this.u, FormatterUtils.FormatterType.TYPE_PHONE);
        return inflate;
    }

    public void c(Bundle bundle) {
        super.c(bundle);
        a(R.string.mibi_title_sms_code);
        c_(false);
        this.u.setText(this.b.l().getString(MibiConstants.dl, ""));
        this.u.requestFocus();
    }
}
