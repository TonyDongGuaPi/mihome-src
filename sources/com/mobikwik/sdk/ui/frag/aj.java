package com.mobikwik.sdk.ui.frag;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.mobikwik.sdk.lib.SDKErrorCodes;
import com.mobikwik.sdk.lib.Transaction;
import com.mobikwik.sdk.lib.TransactionConfiguration;
import com.mobikwik.sdk.lib.model.PaymentsMappingAPIResponse;
import com.mobikwik.sdk.lib.model.SavedCardResponse;
import com.mobikwik.sdk.lib.utils.PaymentOptionsDecoder;
import com.mobikwik.sdk.lib.utils.Utils;
import com.mobikwik.sdk.ui.data.b;

public class aj extends Fragment {

    /* renamed from: a  reason: collision with root package name */
    private PaymentsMappingAPIResponse.PaymentsMapping.ZaakPayPublicKey f8391a;
    protected String b;
    protected String c;
    String d = null;
    String e;
    protected String f;
    protected String g;
    int h = 0;
    protected SavedCardResponse.CardDetails i;
    protected boolean j;
    protected Activity k;
    protected Transaction l;
    protected TransactionConfiguration m;
    protected PaymentsMappingAPIResponse.PaymentsMapping n;
    protected String o;
    protected String p;
    protected String q;
    protected boolean r;
    private boolean s;
    private String t;

    /* access modifiers changed from: protected */
    public String a() {
        return null;
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v7, resolved type: com.mobikwik.sdk.lib.payapi.ZaakpayPaymentAPI} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v14, resolved type: com.mobikwik.sdk.lib.payapi.ZaakpayPaymentAPI} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v22, resolved type: com.mobikwik.sdk.lib.payapi.MBKPaymentAPI} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v28, resolved type: com.mobikwik.sdk.lib.payapi.ZaakpayPaymentAPI} */
    /* access modifiers changed from: protected */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void b() {
        /*
            r15 = this;
            android.app.Activity r0 = r15.k
            boolean r0 = com.mobikwik.sdk.lib.utils.Network.isConnected(r0)
            if (r0 == 0) goto L_0x016b
            com.mobikwik.sdk.lib.payinstrument.Card$Builder r0 = new com.mobikwik.sdk.lib.payinstrument.Card$Builder
            r0.<init>()
            java.lang.String r1 = r15.g
            r0.setCardCVV(r1)
            com.mobikwik.sdk.lib.model.SavedCardResponse$CardDetails r1 = r15.i
            if (r1 != 0) goto L_0x0035
            java.lang.String r1 = r15.b
            com.mobikwik.sdk.lib.payinstrument.Card$Builder r1 = r0.setCardExpMonth(r1)
            java.lang.String r2 = r15.c
            com.mobikwik.sdk.lib.payinstrument.Card$Builder r1 = r1.setCardExpYear(r2)
            java.lang.String r2 = r15.f
            com.mobikwik.sdk.lib.payinstrument.Card$Builder r1 = r1.setCardNumber(r2)
            java.lang.String r2 = r15.a()
            r1.setNameOnCard(r2)
            boolean r1 = r15.j
            r0.setStoreThisCard(r1)
            goto L_0x003c
        L_0x0035:
            com.mobikwik.sdk.lib.model.SavedCardResponse$CardDetails r1 = r15.i
            java.lang.String r1 = r1.cardId
            r0.setCardId(r1)
        L_0x003c:
            com.mobikwik.sdk.lib.payinstrument.Card r3 = r0.build()
            com.mobikwik.sdk.lib.model.PaymentsMappingAPIResponse$PaymentsMapping r0 = r15.n
            java.lang.String[] r0 = r0.getCreditCardMappings()
            r1 = 1
            java.lang.String r0 = com.mobikwik.sdk.lib.utils.PaymentOptionsDecoder.getBankCodeForPaymentOption(r0, r1)
            java.lang.String r2 = "MBK"
            boolean r0 = r0.startsWith(r2)
            if (r0 == 0) goto L_0x00a3
            r15.s = r1
            android.app.Activity r0 = r15.k
            com.mobikwik.sdk.ui.data.b r0 = com.mobikwik.sdk.ui.data.b.b((android.content.Context) r0)
            com.mobikwik.sdk.lib.model.PaymentsMappingAPIResponse$PaymentsMapping r1 = r15.n
            com.mobikwik.sdk.lib.model.PaymentsMappingAPIResponse$PaymentsMapping$MbkPublicKey r4 = r1.getMbkKey()
            com.mobikwik.sdk.lib.payapi.MBKPaymentAPI r1 = new com.mobikwik.sdk.lib.payapi.MBKPaymentAPI
            com.mobikwik.sdk.lib.Transaction r2 = r15.l
            com.mobikwik.sdk.lib.User r5 = r2.getUser()
            java.lang.String r6 = r15.p
            com.mobikwik.sdk.lib.Transaction r2 = r15.l
            java.lang.String r7 = r2.getOrderId()
            com.mobikwik.sdk.lib.TransactionConfiguration r2 = r15.m
            java.lang.String r8 = r2.getMode()
            com.mobikwik.sdk.lib.TransactionConfiguration r2 = r15.m
            java.lang.String r9 = r2.getMbkId()
            com.mobikwik.sdk.lib.Transaction r2 = r15.l
            java.lang.String r10 = r2.getAmount()
            r11 = 1
            android.app.Activity r2 = r15.k
            com.mobikwik.sdk.lib.Transaction r12 = r15.l
            com.mobikwik.sdk.lib.User r12 = r12.getUser()
            java.lang.String r12 = com.mobikwik.sdk.ui.data.a.a(r2, r12)
            com.mobikwik.sdk.lib.TransactionConfiguration r2 = r0.f()
            java.lang.String r13 = r2.getMerchantName()
            java.lang.String r14 = r0.i()
            r2 = r1
            r2.<init>(r3, r4, r5, r6, r7, r8, r9, r10, r11, r12, r13, r14)
            r0 = r1
            goto L_0x013b
        L_0x00a3:
            java.lang.String r0 = r15.p
            boolean r2 = r15.r
            com.mobikwik.sdk.lib.model.PaymentsMappingAPIResponse$PaymentsMapping r4 = r15.n
            java.lang.String r0 = com.mobikwik.sdk.lib.utils.PaymentOptionsDecoder.getZaakpayMerchantIdForAmount(r0, r2, r4)
            r15.e = r0
            java.lang.String r0 = r15.e
            r2 = 0
            if (r0 != 0) goto L_0x00c6
        L_0x00b4:
            android.app.Activity r0 = r15.k
            com.mobikwik.sdk.lib.SDKErrorCodes r1 = com.mobikwik.sdk.lib.SDKErrorCodes.UNEXPECTED_ERROR
            java.lang.String r1 = r1.getErrorCode()
            com.mobikwik.sdk.lib.SDKErrorCodes r3 = com.mobikwik.sdk.lib.SDKErrorCodes.UNEXPECTED_ERROR
            java.lang.String r3 = r3.getErrorDescription()
            com.mobikwik.sdk.lib.utils.Utils.sendResultBack(r0, r2, r1, r3)
            return
        L_0x00c6:
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            java.lang.String r4 = "ZaakpayCardPaymentFragment.payNow() "
            r0.append(r4)
            java.lang.String r4 = r15.e
            r0.append(r4)
            java.lang.String r0 = r0.toString()
            com.mobikwik.sdk.lib.utils.Utils.print(r0)
            java.lang.String r0 = r15.e
            com.mobikwik.sdk.lib.model.PaymentsMappingAPIResponse$PaymentsMapping r4 = r15.n
            com.mobikwik.sdk.lib.model.PaymentsMappingAPIResponse$PaymentsMapping$ZaakPayPublicKey r0 = com.mobikwik.sdk.lib.utils.PaymentOptionsDecoder.getZaakpayKeyForMerchantId(r0, r4)
            r15.f8391a = r0
            com.mobikwik.sdk.lib.model.PaymentsMappingAPIResponse$PaymentsMapping$ZaakPayPublicKey r0 = r15.f8391a
            if (r0 != 0) goto L_0x00eb
            goto L_0x00b4
        L_0x00eb:
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            java.lang.String r2 = "ZaakpayCardPaymentFragment.payNow() "
            r0.append(r2)
            com.mobikwik.sdk.lib.model.PaymentsMappingAPIResponse$PaymentsMapping$ZaakPayPublicKey r2 = r15.f8391a
            r0.append(r2)
            java.lang.String r0 = r0.toString()
            com.mobikwik.sdk.lib.utils.Utils.print(r0)
            com.mobikwik.sdk.lib.model.PaymentsMappingAPIResponse$PaymentsMapping r0 = r15.n
            com.mobikwik.sdk.lib.model.PaymentsMappingAPIResponse$PaymentsMapping r2 = r15.n
            java.lang.String[] r2 = r2.getCreditCardMappings()
            java.lang.String r11 = com.mobikwik.sdk.lib.utils.PaymentOptionsDecoder.getPGUrlForPaymentOption(r0, r2, r1)
            com.mobikwik.sdk.lib.TransactionConfiguration r0 = r15.m
            java.lang.String r13 = r0.getChecksumUrl()
            com.mobikwik.sdk.lib.payapi.ZaakpayPaymentAPI r0 = new com.mobikwik.sdk.lib.payapi.ZaakpayPaymentAPI
            com.mobikwik.sdk.lib.model.PaymentsMappingAPIResponse$PaymentsMapping$ZaakPayPublicKey r4 = r15.f8391a
            com.mobikwik.sdk.lib.Transaction r1 = r15.l
            com.mobikwik.sdk.lib.User r5 = r1.getUser()
            java.lang.String r6 = r15.p
            com.mobikwik.sdk.lib.Transaction r1 = r15.l
            java.lang.String r7 = r1.getOrderId()
            com.mobikwik.sdk.lib.TransactionConfiguration r1 = r15.m
            java.lang.String r8 = r1.getMode()
            boolean r9 = r15.r
            com.mobikwik.sdk.lib.TransactionConfiguration r1 = r15.m
            java.lang.String r10 = r1.getMbkId()
            java.lang.String r12 = r15.t
            java.lang.String r14 = r15.e
            r2 = r0
            r2.<init>(r3, r4, r5, r6, r7, r8, r9, r10, r11, r12, r13, r14)
        L_0x013b:
            android.content.Intent r1 = new android.content.Intent
            r1.<init>()
            android.app.Activity r2 = r15.k
            android.content.Context r2 = r2.getBaseContext()
            java.lang.Class<com.mobikwik.sdk.PGWebView> r3 = com.mobikwik.sdk.PGWebView.class
            r1.setClass(r2, r3)
            java.lang.String r2 = "KEY_API"
            r1.putExtra(r2, r0)
            java.lang.String r0 = "KEY_IS_WALLET"
            boolean r2 = r15.r
            r1.putExtra(r0, r2)
            java.lang.String r0 = "KEY_PG_AMOUNT"
            java.lang.String r2 = r15.p
            r1.putExtra(r0, r2)
            java.lang.String r0 = "KEY_RESPONSE_URL"
            java.lang.String r2 = r15.t
            r1.putExtra(r0, r2)
            android.app.Activity r0 = r15.k
            r2 = 0
            r0.startActivityForResult(r1, r2)
        L_0x016b:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.mobikwik.sdk.ui.frag.aj.b():void");
    }

    public void onAttach(Activity activity) {
        super.onAttach(activity);
        this.k = activity;
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        String pgResponseUrl;
        this.l = b.b((Context) this.k).d();
        this.m = b.b((Context) this.k).f();
        this.o = this.m.getMerchantName();
        this.r = b.b((Context) this.k).j();
        if (this.r) {
            this.n = b.b((Context) this.k).c().getBankMappingMobikwik();
            this.p = b.b((Context) this.k).e();
            pgResponseUrl = b.b((Context) this.k).k();
        } else {
            this.n = b.b((Context) this.k).c().getBankMappingMerchant();
            this.p = this.l.getAmount();
            pgResponseUrl = this.m.getPgResponseUrl();
        }
        this.t = pgResponseUrl;
        Utils.print("ZaakpayCardPaymentFragment Amount " + this.p);
        this.e = PaymentOptionsDecoder.getZaakpayMerchantIdForAmount(this.p, this.r, this.n);
        Utils.print("ZaakpayCardPaymentFragment merchantId " + this.e);
        if (this.e != null) {
            this.f8391a = PaymentOptionsDecoder.getZaakpayKeyForMerchantId(this.e, this.n);
            Utils.print("ZaakpayCardPaymentFragment zaakpayKey " + this.f8391a);
            if (this.f8391a != null) {
                return super.onCreateView(layoutInflater, viewGroup, bundle);
            }
        }
        Utils.sendResultBack(this.k, (Intent) null, SDKErrorCodes.UNEXPECTED_ERROR.getErrorCode(), SDKErrorCodes.UNEXPECTED_ERROR.getErrorDescription());
        return null;
    }
}
