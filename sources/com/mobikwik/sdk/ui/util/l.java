package com.mobikwik.sdk.ui.util;

import android.widget.TextView;
import com.mobikwik.sdk.R;
import com.mobikwik.sdk.lib.Constants;
import com.mobikwik.sdk.ui.data.b;

public class l {
    public static int a(String str) {
        return "visa".equalsIgnoreCase(str) ? R.drawable.visa : "mastercard".equalsIgnoreCase(str) ? R.drawable.mk_master : "maestro".equalsIgnoreCase(str) ? R.drawable.mk_maestro : "discover".equalsIgnoreCase(str) ? R.drawable.mk_discover : "diners".equalsIgnoreCase(str) ? R.drawable.mk_diners : "amex".equalsIgnoreCase(str) ? R.drawable.mk_amex : Constants.CARD_TYPE_RUPAY.equalsIgnoreCase(str) ? R.drawable.mk_rupay : R.drawable.mk_ic_card;
    }

    public static void a(TextView textView, String str, String str2) {
        String replace;
        String charSequence;
        String str3;
        b b = b.b(textView.getContext());
        if (!b.j()) {
            textView.setText(R.string.card_page_payto_msg_pg);
            textView.setText(textView.getText().toString().replace(Constants.PLACEHOLDER_MERCHANT_NAME, b.f().getMerchantName()));
            charSequence = textView.getText().toString();
            str3 = Constants.PLACEHOLDER_AMOUNT;
        } else if (!b.f().isDebitWallet()) {
            textView.setText(R.string.card_page_payto_msg);
            charSequence = textView.getText().toString();
            str3 = "@payamt@";
        } else {
            if (str.equals(str2)) {
                textView.setText(R.string.add_money_wallet);
            } else {
                textView.setText(R.string.add_money_wallet_partial);
                textView.setText(textView.getText().toString().replace("@addamt@", str2));
            }
            textView.setText(textView.getText().toString().replace("@payamt@", str));
            replace = textView.getText().toString().replace("@mm@", b.f().getMerchantName());
            textView.setText(replace);
        }
        replace = charSequence.replace(str3, str);
        textView.setText(replace);
    }
}
