package com.mobikwik.sdk.ui.frag;

import android.text.Editable;
import android.text.InputFilter;
import android.text.TextWatcher;
import android.widget.EditText;
import com.mobikwik.sdk.R;
import com.mobikwik.sdk.lib.Constants;
import com.mobikwik.sdk.lib.utils.CardUtils;

class b implements TextWatcher {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ a f8392a;

    b(a aVar) {
        this.f8392a = aVar;
    }

    public void afterTextChanged(Editable editable) {
        EditText editText;
        int i;
        EditText b;
        int i2;
        int i3;
        this.f8392a.q = CardUtils.detectType(editable.toString());
        this.f8392a.t.setFilters(new InputFilter.LengthFilter[]{"amex".equalsIgnoreCase(this.f8392a.q) ? new InputFilter.LengthFilter(4) : new InputFilter.LengthFilter(3)});
        if ("maestro".equalsIgnoreCase(this.f8392a.q)) {
            this.f8392a.getView().findViewById(R.id.text_maestro_note).setVisibility(0);
        } else {
            this.f8392a.getView().findViewById(R.id.text_maestro_note).setVisibility(8);
        }
        if (editable.toString().length() <= 12 || !this.f8392a.b(true)) {
            editText = this.f8392a.v;
            i = R.drawable.mk_cvv_text_bg;
        } else {
            editText = this.f8392a.v;
            i = R.drawable.mk_edit_text_bg;
        }
        editText.setBackgroundResource(i);
        if (this.f8392a.q.compareTo("visa") == 0) {
            b = this.f8392a.v;
            i2 = R.drawable.mk_ic_card;
            i3 = R.drawable.visa;
        } else if (this.f8392a.q.compareTo("mastercard") == 0) {
            b = this.f8392a.v;
            i2 = R.drawable.mk_ic_card;
            i3 = R.drawable.mk_master;
        } else if (this.f8392a.q.compareTo("diners") == 0) {
            b = this.f8392a.v;
            i2 = R.drawable.mk_ic_card;
            i3 = R.drawable.mk_diners;
        } else if (this.f8392a.q.compareTo("discover") == 0) {
            b = this.f8392a.v;
            i2 = R.drawable.mk_ic_card;
            i3 = R.drawable.mk_discover;
        } else if (this.f8392a.q.compareTo("maestro") == 0) {
            b = this.f8392a.v;
            i2 = R.drawable.mk_ic_card;
            i3 = R.drawable.mk_maestro;
        } else if (this.f8392a.q.compareTo("amex") == 0) {
            b = this.f8392a.v;
            i2 = R.drawable.mk_ic_card;
            i3 = R.drawable.mk_amex;
        } else if (this.f8392a.q.compareTo(Constants.CARD_TYPE_RUPAY) == 0) {
            b = this.f8392a.v;
            i2 = R.drawable.mk_ic_card;
            i3 = R.drawable.mk_rupay;
        } else {
            this.f8392a.v.setCompoundDrawablesWithIntrinsicBounds(R.drawable.mk_ic_card, 0, 0, 0);
            return;
        }
        b.setCompoundDrawablesWithIntrinsicBounds(i2, 0, i3, 0);
    }

    public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
    }

    public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
    }
}
