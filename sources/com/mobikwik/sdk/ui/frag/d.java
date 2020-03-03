package com.mobikwik.sdk.ui.frag;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import com.mobikwik.sdk.R;
import com.mobikwik.sdk.lib.Constants;
import com.mobikwik.sdk.lib.Transaction;
import com.mobikwik.sdk.lib.TransactionConfiguration;
import com.mobikwik.sdk.lib.model.PaymentsMappingAPIResponse;
import com.mobikwik.sdk.lib.payapi.CCAvenuePaymentApi;
import com.mobikwik.sdk.lib.utils.Network;
import com.mobikwik.sdk.lib.utils.PaymentOptionsDecoder;
import com.mobikwik.sdk.ui.data.b;

public class d extends Fragment {

    /* renamed from: a  reason: collision with root package name */
    a f8394a;
    private PaymentsMappingAPIResponse.PaymentsMapping b;
    /* access modifiers changed from: private */
    public Activity c;
    private Transaction d;
    private TransactionConfiguration e;
    private boolean f;

    public interface a {
        void a(Bundle bundle);
    }

    /* access modifiers changed from: private */
    public void a(Intent intent, boolean z, int i) {
        int i2;
        String bankCodeForPaymentOption;
        PaymentsMappingAPIResponse.PaymentsMapping paymentsMapping;
        String[] debitCardMappings;
        if (z) {
            i2 = i + 1;
            bankCodeForPaymentOption = PaymentOptionsDecoder.getBankCodeForPaymentOption(this.b.getCreditCardMappings(), i2);
            paymentsMapping = this.b;
            debitCardMappings = this.b.getCreditCardMappings();
        } else {
            i2 = i + 1;
            bankCodeForPaymentOption = PaymentOptionsDecoder.getBankCodeForPaymentOption(this.b.getDebitCardMappings(), i2);
            paymentsMapping = this.b;
            debitCardMappings = this.b.getDebitCardMappings();
        }
        String pGUrlForPaymentOption = PaymentOptionsDecoder.getPGUrlForPaymentOption(paymentsMapping, debitCardMappings, i2);
        if (bankCodeForPaymentOption.compareTo("NULL") == 0) {
            return;
        }
        if (!bankCodeForPaymentOption.startsWith(PaymentsMappingAPIResponse.PG_CCAV)) {
            Bundle bundle = new Bundle();
            bundle.putString(Constants.LABEL_BANK_ID, bankCodeForPaymentOption);
            bundle.putString(Constants.LABEL_POST_URL, pGUrlForPaymentOption);
            if (this.f8394a != null) {
                this.f8394a.a(bundle);
            }
        } else if (Network.isConnected(this.c)) {
            String amount = this.d.getAmount();
            if (this.f) {
                amount = b.b((Context) this.c).e();
            }
            String substring = bankCodeForPaymentOption.substring(bankCodeForPaymentOption.indexOf(":") + 1, bankCodeForPaymentOption.length());
            String cCAVMerchantIdForAmount = PaymentOptionsDecoder.getCCAVMerchantIdForAmount(amount, this.f, this.b);
            String pgResponseUrl = this.e.getPgResponseUrl();
            String checksumUrl = this.e.getChecksumUrl();
            boolean z2 = this.f;
            CCAvenuePaymentApi cCAvenuePaymentApi = new CCAvenuePaymentApi(this.d.getUser(), amount, this.d.getOrderId(), substring, "NonMoto", this.e.getMode(), this.f, this.e.getMbkId(), pGUrlForPaymentOption, pgResponseUrl, checksumUrl, cCAVMerchantIdForAmount);
            Intent intent2 = new Intent();
            intent2.putExtra("KEY_API", cCAvenuePaymentApi);
            intent2.putExtra("KEY_IS_WALLET", this.f);
            intent2.putExtra("KEY_PG_AMOUNT", amount);
            intent2.putExtra("KEY_RESPONSE_URL", this.e.getPgResponseUrl());
            this.c.startActivityForResult(intent2, 0);
        }
    }

    public void onAttach(Activity activity) {
        super.onAttach(activity);
        if (activity instanceof a) {
            this.f8394a = (a) activity;
        }
        this.c = activity;
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        this.d = b.b((Context) this.c).d();
        this.e = b.b((Context) this.c).f();
        this.f = b.b((Context) this.c).j();
        this.b = this.f ? b.b((Context) this.c).c().getBankMappingMobikwik() : b.b((Context) this.c).c().getBankMappingMerchant();
        View inflate = layoutInflater.inflate(R.layout.mk_frag_debit_cards, viewGroup, false);
        ListView listView = (ListView) inflate.findViewById(R.id.list_debitcards);
        listView.setAdapter(new ArrayAdapter(this.c, 17367043, PaymentOptionsDecoder.getNames(this.b.getDebitCardMappings())));
        listView.setOnItemClickListener(new e(this));
        if (PaymentOptionsDecoder.isAllCardNonCCAvenue(this.b.getDebitCardMappings())) {
            a(this.c.getIntent(), false, 0);
        }
        return inflate;
    }
}
