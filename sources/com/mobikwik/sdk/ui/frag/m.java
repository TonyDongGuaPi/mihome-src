package com.mobikwik.sdk.ui.frag;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import com.mobikwik.sdk.R;
import com.mobikwik.sdk.lib.Transaction;
import com.mobikwik.sdk.lib.model.PaymentsMappingAPIResponse;
import com.mobikwik.sdk.lib.utils.PaymentOptionsDecoder;
import com.mobikwik.sdk.ui.data.b;

public class m extends Fragment {

    /* renamed from: a  reason: collision with root package name */
    private PaymentsMappingAPIResponse.PaymentsMapping f8403a;
    private Activity b;
    private Transaction c;
    private String d;
    private b e;

    private void a(Spinner spinner) {
        int bankPositionForBankId = PaymentOptionsDecoder.getBankPositionForBankId(this.f8403a.getNetBankingMappings(), this.b.getPreferences(0).getString("key_bank_id", ""));
        if (bankPositionForBankId > 0) {
            spinner.setSelection(bankPositionForBankId);
        }
    }

    private void a(String str) {
        SharedPreferences.Editor edit = this.b.getPreferences(0).edit();
        edit.putString("key_bank_id", str);
        edit.commit();
    }

    /* JADX WARNING: type inference failed for: r2v5, types: [java.io.Serializable] */
    /* JADX WARNING: type inference failed for: r5v9, types: [com.mobikwik.sdk.lib.payapi.ZaakpayPaymentAPI] */
    /* JADX WARNING: type inference failed for: r5v10, types: [com.mobikwik.sdk.lib.payapi.MBKPaymentAPI] */
    /* JADX WARNING: type inference failed for: r5v11, types: [com.mobikwik.sdk.lib.payapi.CCAvenuePaymentApi] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void a(int r19) {
        /*
            r18 = this;
            r0 = r18
            r1 = r19
            android.app.Activity r2 = r0.b
            boolean r2 = com.mobikwik.sdk.lib.utils.Network.isConnected(r2)
            if (r2 != 0) goto L_0x000d
            return
        L_0x000d:
            com.mobikwik.sdk.lib.model.PaymentsMappingAPIResponse$PaymentsMapping r2 = r0.f8403a
            java.lang.String[] r2 = r2.getNetBankingMappings()
            java.lang.String r2 = com.mobikwik.sdk.lib.utils.PaymentOptionsDecoder.getBankCodeForPaymentOption(r2, r1)
            r0.a((java.lang.String) r2)
            com.mobikwik.sdk.lib.model.PaymentsMappingAPIResponse$PaymentsMapping r3 = r0.f8403a
            com.mobikwik.sdk.lib.model.PaymentsMappingAPIResponse$PaymentsMapping r4 = r0.f8403a
            java.lang.String[] r4 = r4.getNetBankingMappings()
            java.lang.String r3 = com.mobikwik.sdk.lib.utils.PaymentOptionsDecoder.getPGUrlForPaymentOption(r3, r4, r1)
            if (r1 == 0) goto L_0x01bc
            com.mobikwik.sdk.ui.data.b r1 = r0.e
            com.mobikwik.sdk.lib.TransactionConfiguration r1 = r1.f()
            java.lang.String r1 = r1.getPgResponseUrl()
            java.lang.String r4 = "CCAVE"
            boolean r4 = r2.startsWith(r4)
            if (r4 == 0) goto L_0x00b2
            java.lang.String r4 = ":"
            int r4 = r2.indexOf(r4)
            int r4 = r4 + 1
            int r5 = r2.length()
            java.lang.String r9 = r2.substring(r4, r5)
            java.lang.String r2 = r0.d
            com.mobikwik.sdk.ui.data.b r4 = r0.e
            boolean r4 = r4.j()
            com.mobikwik.sdk.lib.model.PaymentsMappingAPIResponse$PaymentsMapping r5 = r0.f8403a
            java.lang.String r17 = com.mobikwik.sdk.lib.utils.PaymentOptionsDecoder.getCCAVMerchantIdForAmount(r2, r4, r5)
            com.mobikwik.sdk.ui.data.b r2 = r0.e
            com.mobikwik.sdk.lib.TransactionConfiguration r2 = r2.f()
            java.lang.String r2 = r2.getPgResponseUrl()
            com.mobikwik.sdk.ui.data.b r4 = r0.e
            com.mobikwik.sdk.lib.TransactionConfiguration r4 = r4.f()
            java.lang.String r16 = r4.getChecksumUrl()
            com.mobikwik.sdk.ui.data.b r4 = r0.e
            boolean r4 = r4.j()
            if (r4 == 0) goto L_0x007e
            android.app.Activity r2 = r0.b
            com.mobikwik.sdk.ui.data.b r2 = com.mobikwik.sdk.ui.data.b.b((android.content.Context) r2)
            java.lang.String r2 = r2.k()
        L_0x007e:
            r15 = r2
            com.mobikwik.sdk.lib.payapi.CCAvenuePaymentApi r2 = new com.mobikwik.sdk.lib.payapi.CCAvenuePaymentApi
            com.mobikwik.sdk.lib.Transaction r4 = r0.c
            com.mobikwik.sdk.lib.User r6 = r4.getUser()
            java.lang.String r7 = r0.d
            com.mobikwik.sdk.lib.Transaction r4 = r0.c
            java.lang.String r8 = r4.getOrderId()
            java.lang.String r10 = "netBanking"
            com.mobikwik.sdk.ui.data.b r4 = r0.e
            com.mobikwik.sdk.lib.TransactionConfiguration r4 = r4.f()
            java.lang.String r11 = r4.getMode()
            com.mobikwik.sdk.ui.data.b r4 = r0.e
            boolean r12 = r4.j()
            com.mobikwik.sdk.ui.data.b r4 = r0.e
            com.mobikwik.sdk.lib.TransactionConfiguration r4 = r4.f()
            java.lang.String r13 = r4.getMbkId()
            r5 = r2
            r14 = r3
            r5.<init>(r6, r7, r8, r9, r10, r11, r12, r13, r14, r15, r16, r17)
            goto L_0x018a
        L_0x00b2:
            java.lang.String r4 = "MBK"
            boolean r4 = r2.startsWith(r4)
            if (r4 == 0) goto L_0x0116
            java.lang.String r3 = ":"
            int r3 = r2.indexOf(r3)
            int r3 = r3 + 1
            int r4 = r2.length()
            java.lang.String r6 = r2.substring(r3, r4)
            com.mobikwik.sdk.lib.payapi.MBKPaymentAPI r2 = new com.mobikwik.sdk.lib.payapi.MBKPaymentAPI
            com.mobikwik.sdk.lib.Transaction r3 = r0.c
            com.mobikwik.sdk.lib.User r7 = r3.getUser()
            java.lang.String r8 = r0.d
            com.mobikwik.sdk.lib.Transaction r3 = r0.c
            java.lang.String r9 = r3.getOrderId()
            com.mobikwik.sdk.ui.data.b r3 = r0.e
            com.mobikwik.sdk.lib.TransactionConfiguration r3 = r3.f()
            java.lang.String r10 = r3.getMode()
            com.mobikwik.sdk.ui.data.b r3 = r0.e
            com.mobikwik.sdk.lib.TransactionConfiguration r3 = r3.f()
            java.lang.String r11 = r3.getMbkId()
            com.mobikwik.sdk.lib.Transaction r3 = r0.c
            java.lang.String r12 = r3.getAmount()
            r13 = 1
            android.app.Activity r3 = r0.b
            com.mobikwik.sdk.lib.Transaction r4 = r0.c
            com.mobikwik.sdk.lib.User r4 = r4.getUser()
            java.lang.String r14 = com.mobikwik.sdk.ui.data.a.a(r3, r4)
            com.mobikwik.sdk.ui.data.b r3 = r0.e
            com.mobikwik.sdk.lib.TransactionConfiguration r3 = r3.f()
            java.lang.String r15 = r3.getMerchantName()
            com.mobikwik.sdk.ui.data.b r3 = r0.e
            java.lang.String r16 = r3.i()
            r5 = r2
            r5.<init>(r6, r7, r8, r9, r10, r11, r12, r13, r14, r15, r16)
            goto L_0x018a
        L_0x0116:
            java.lang.String r4 = r0.d
            com.mobikwik.sdk.ui.data.b r5 = r0.e
            boolean r5 = r5.j()
            com.mobikwik.sdk.lib.model.PaymentsMappingAPIResponse$PaymentsMapping r6 = r0.f8403a
            java.lang.String r16 = com.mobikwik.sdk.lib.utils.PaymentOptionsDecoder.getZaakpayMerchantIdForAmount(r4, r5, r6)
            java.lang.String r4 = ":"
            int r4 = r2.indexOf(r4)
            int r4 = r4 + 1
            int r5 = r2.length()
            java.lang.String r6 = r2.substring(r4, r5)
            com.mobikwik.sdk.ui.data.b r2 = r0.e
            com.mobikwik.sdk.lib.TransactionConfiguration r2 = r2.f()
            java.lang.String r2 = r2.getPgResponseUrl()
            com.mobikwik.sdk.ui.data.b r4 = r0.e
            com.mobikwik.sdk.lib.TransactionConfiguration r4 = r4.f()
            java.lang.String r15 = r4.getChecksumUrl()
            com.mobikwik.sdk.ui.data.b r4 = r0.e
            boolean r4 = r4.j()
            if (r4 == 0) goto L_0x015a
            android.app.Activity r2 = r0.b
            com.mobikwik.sdk.ui.data.b r2 = com.mobikwik.sdk.ui.data.b.b((android.content.Context) r2)
            java.lang.String r2 = r2.k()
        L_0x015a:
            r14 = r2
            com.mobikwik.sdk.lib.payapi.ZaakpayPaymentAPI r2 = new com.mobikwik.sdk.lib.payapi.ZaakpayPaymentAPI
            com.mobikwik.sdk.lib.Transaction r4 = r0.c
            com.mobikwik.sdk.lib.User r7 = r4.getUser()
            java.lang.String r8 = r0.d
            com.mobikwik.sdk.lib.Transaction r4 = r0.c
            java.lang.String r9 = r4.getOrderId()
            com.mobikwik.sdk.ui.data.b r4 = r0.e
            com.mobikwik.sdk.lib.TransactionConfiguration r4 = r4.f()
            java.lang.String r10 = r4.getMode()
            com.mobikwik.sdk.ui.data.b r4 = r0.e
            boolean r11 = r4.j()
            com.mobikwik.sdk.ui.data.b r4 = r0.e
            com.mobikwik.sdk.lib.TransactionConfiguration r4 = r4.f()
            java.lang.String r12 = r4.getMbkId()
            r5 = r2
            r13 = r3
            r5.<init>(r6, r7, r8, r9, r10, r11, r12, r13, r14, r15, r16)
        L_0x018a:
            android.content.Intent r3 = new android.content.Intent
            r3.<init>()
            android.app.Activity r4 = r0.b
            android.content.Context r4 = r4.getBaseContext()
            java.lang.Class<com.mobikwik.sdk.PGWebView> r5 = com.mobikwik.sdk.PGWebView.class
            r3.setClass(r4, r5)
            java.lang.String r4 = "KEY_API"
            r3.putExtra(r4, r2)
            java.lang.String r2 = "KEY_IS_WALLET"
            com.mobikwik.sdk.ui.data.b r4 = r0.e
            boolean r4 = r4.j()
            r3.putExtra(r2, r4)
            java.lang.String r2 = "KEY_PG_AMOUNT"
            java.lang.String r4 = r0.d
            r3.putExtra(r2, r4)
            java.lang.String r2 = "KEY_RESPONSE_URL"
            r3.putExtra(r2, r1)
            android.app.Activity r1 = r0.b
            r2 = 0
            r1.startActivityForResult(r3, r2)
        L_0x01bc:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.mobikwik.sdk.ui.frag.m.a(int):void");
    }

    public void onAttach(Activity activity) {
        super.onAttach(activity);
        this.b = activity;
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        this.c = b.b((Context) this.b).d();
        this.e = b.b((Context) this.b);
        this.f8403a = this.e.j() ? b.b((Context) this.b).c().getBankMappingMobikwik() : b.b((Context) this.b).c().getBankMappingMerchant();
        this.d = this.e.j() ? b.b((Context) this.b).e() : this.c.getAmount();
        View inflate = layoutInflater.inflate(R.layout.mk_frag_netbanking, viewGroup, false);
        Spinner spinner = (Spinner) inflate.findViewById(R.id.spinner_netBanking);
        spinner.setAdapter(new ArrayAdapter(this.b, 17367043, PaymentOptionsDecoder.getNames(this.f8403a.getNetBankingMappings())));
        inflate.findViewById(R.id.btnNetbankingContinue).setOnClickListener(new n(this, spinner));
        a(spinner);
        return inflate;
    }
}
