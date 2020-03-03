package com.mi.global.shop.buy.cod;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import com.mi.global.shop.R;
import com.mi.global.shop.ShopApp;
import com.mi.global.shop.activity.BaseActivity;
import com.mi.global.shop.widget.CommonButton;
import com.mi.global.shop.widget.PhoneNumberEdit;
import com.mi.log.LogUtil;

public class ChangeTelAcitivty extends BaseActivity implements TextWatcher, View.OnClickListener {
    public static final String TAG = "changeTelActivity";
    public static final int TEL_NUMBER_LENGTH = 10;

    /* renamed from: a  reason: collision with root package name */
    private PhoneNumberEdit f6879a = null;
    private CommonButton b = null;
    private CommonButton c = null;
    private View d;

    public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
    }

    public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        LogUtil.b(TAG, "onCreate");
        super.onCreate(bundle);
        this.mCartView.setVisibility(4);
        this.d = findViewById(R.id.title_bar_home);
        this.d.setVisibility(0);
        this.d.setOnClickListener(this);
        setCustomContentView(R.layout.shop_buy_confirm_payment_cod_changetel);
        setTitle(R.string.buy_confirm_COD_changetel_title);
        this.f6879a = (PhoneNumberEdit) findViewById(R.id.buy_confirm_payment_cod_change_tel_tel_text);
        PhoneNumberEdit phoneNumberEdit = this.f6879a;
        phoneNumberEdit.setPrefix(ShopApp.g().getString(R.string.user_address_phoneareacode) + "  ");
        this.b = (CommonButton) findViewById(R.id.buy_confirm_payment_cod_change_tel_confirm);
        this.c = (CommonButton) findViewById(R.id.buy_confirm_payment_cod_change_tel_cancel);
        this.b.setOnClickListener(this);
        this.b.setEnabled(false);
        this.c.setOnClickListener(this);
        String stringExtra = getIntent().getStringExtra("tel");
        if (stringExtra != null) {
            this.f6879a.setText(stringExtra);
        }
        this.f6879a.addTextChangedListener(this);
    }

    public void onClick(View view) {
        if (view == this.b) {
            String obj = this.f6879a.getText().toString();
            Intent intent = new Intent();
            intent.putExtra("tel", obj);
            setResult(-1, intent);
            finish();
        } else if (view == this.c) {
            setResult(0);
            finish();
        } else if (view == this.d) {
            setResult(0);
            finish();
        }
    }

    public void afterTextChanged(Editable editable) {
        editable.toString();
        if (editable.toString().getBytes().length == 10) {
            this.b.setEnabled(true);
        } else {
            this.b.setEnabled(false);
        }
    }
}
