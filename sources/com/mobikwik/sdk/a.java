package com.mobikwik.sdk;

import com.mobikwik.sdk.lib.Transaction;
import com.mobikwik.sdk.lib.TransactionConfiguration;
import com.mobikwik.sdk.lib.tasks.LoadPaymentsMapping;
import com.mobikwik.sdk.ui.data.b;
import java.util.ArrayList;

class a implements LoadPaymentsMapping.PaymentMappingLoadListener {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ Transaction f8359a;
    final /* synthetic */ ArrayList b;
    final /* synthetic */ b c;
    final /* synthetic */ TransactionConfiguration d;
    final /* synthetic */ MobikwikSDK e;

    a(MobikwikSDK mobikwikSDK, Transaction transaction, ArrayList arrayList, b bVar, TransactionConfiguration transactionConfiguration) {
        this.e = mobikwikSDK;
        this.f8359a = transaction;
        this.b = arrayList;
        this.c = bVar;
        this.d = transactionConfiguration;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:15:0x004e, code lost:
        if (r6.e.b.isShowing() != false) goto L_0x0017;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:5:0x0015, code lost:
        if (r6.e.b.isShowing() != false) goto L_0x0017;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void onMappingLoaded(com.mobikwik.sdk.lib.model.PaymentsMappingAPIResponse r7) {
        /*
            r6 = this;
            r0 = 0
            if (r7 != 0) goto L_0x0032
            com.mobikwik.sdk.MobikwikSDK r7 = r6.e
            android.app.ProgressDialog r7 = r7.b
            if (r7 == 0) goto L_0x0020
            com.mobikwik.sdk.MobikwikSDK r7 = r6.e
            android.app.ProgressDialog r7 = r7.b
            boolean r7 = r7.isShowing()
            if (r7 == 0) goto L_0x0020
        L_0x0017:
            com.mobikwik.sdk.MobikwikSDK r7 = r6.e
            android.app.ProgressDialog r7 = r7.b
            r7.dismiss()
        L_0x0020:
            com.mobikwik.sdk.MobikwikSDK r7 = r6.e
            com.mobikwik.sdk.lib.SDKErrorCodes r1 = com.mobikwik.sdk.lib.SDKErrorCodes.INTERNET_NOT_WORKING
            java.lang.String r1 = r1.getErrorCode()
            com.mobikwik.sdk.lib.SDKErrorCodes r2 = com.mobikwik.sdk.lib.SDKErrorCodes.INTERNET_NOT_WORKING
            java.lang.String r2 = r2.getErrorDescription()
        L_0x002e:
            r7.a((android.content.Intent) r0, (java.lang.String) r1, (java.lang.String) r2)
            return
        L_0x0032:
            com.mobikwik.sdk.lib.model.PaymentsMappingAPIResponse$SDKPaymentMappingBody r1 = r7.getData()
            java.util.ArrayList r1 = r1.getEnabledOptions()
            if (r1 != 0) goto L_0x0051
            com.mobikwik.sdk.MobikwikSDK r7 = r6.e
            android.app.ProgressDialog r7 = r7.b
            if (r7 == 0) goto L_0x0020
            com.mobikwik.sdk.MobikwikSDK r7 = r6.e
            android.app.ProgressDialog r7 = r7.b
            boolean r7 = r7.isShowing()
            if (r7 == 0) goto L_0x0020
            goto L_0x0017
        L_0x0051:
            int r2 = r1.size()
            if (r2 != 0) goto L_0x007b
            com.mobikwik.sdk.MobikwikSDK r7 = r6.e
            android.app.ProgressDialog r7 = r7.b
            if (r7 == 0) goto L_0x0074
            com.mobikwik.sdk.MobikwikSDK r7 = r6.e
            android.app.ProgressDialog r7 = r7.b
            boolean r7 = r7.isShowing()
            if (r7 == 0) goto L_0x0074
            com.mobikwik.sdk.MobikwikSDK r7 = r6.e
            android.app.ProgressDialog r7 = r7.b
            r7.dismiss()
        L_0x0074:
            com.mobikwik.sdk.MobikwikSDK r7 = r6.e
            java.lang.String r1 = "1"
            java.lang.String r2 = "Payment option not enabled"
            goto L_0x002e
        L_0x007b:
            com.mobikwik.sdk.lib.Transaction r2 = r6.f8359a
            com.mobikwik.sdk.lib.payinstrument.PaymentInstrumentType r2 = r2.getPaymentType()
            r3 = 0
            if (r2 != 0) goto L_0x0097
            r2 = 0
        L_0x0085:
            int r4 = r1.size()
            if (r2 >= r4) goto L_0x00ae
            java.util.ArrayList r4 = r6.b
            java.lang.Object r5 = r1.get(r2)
            r4.add(r5)
            int r2 = r2 + 1
            goto L_0x0085
        L_0x0097:
            com.mobikwik.sdk.lib.Transaction r2 = r6.f8359a
            com.mobikwik.sdk.lib.payinstrument.PaymentInstrumentType r2 = r2.getPaymentType()
            boolean r1 = r1.contains(r2)
            if (r1 == 0) goto L_0x0074
            java.util.ArrayList r1 = r6.b
            com.mobikwik.sdk.lib.Transaction r2 = r6.f8359a
            com.mobikwik.sdk.lib.payinstrument.PaymentInstrumentType r2 = r2.getPaymentType()
            r1.add(r2)
        L_0x00ae:
            com.mobikwik.sdk.lib.Transaction r1 = r6.f8359a
            com.mobikwik.sdk.lib.User r1 = r1.getUser()
            if (r1 == 0) goto L_0x00c6
            com.mobikwik.sdk.lib.Transaction r1 = r6.f8359a
            com.mobikwik.sdk.lib.User r1 = r1.getUser()
            java.lang.String r1 = r1.getEmail()
            boolean r1 = com.mobikwik.sdk.lib.utils.Utils.isNull(r1)
            if (r1 == 0) goto L_0x00e4
        L_0x00c6:
            java.util.ArrayList r1 = r6.b
            com.mobikwik.sdk.lib.payinstrument.PaymentInstrumentType r2 = com.mobikwik.sdk.lib.payinstrument.PaymentInstrumentType.MK_WALLET
            boolean r1 = r1.contains(r2)
            if (r1 != 0) goto L_0x00d8
            com.mobikwik.sdk.MobikwikSDK r7 = r6.e
            java.lang.String r1 = "1"
            java.lang.String r2 = "Email id null or blank"
            goto L_0x002e
        L_0x00d8:
            java.util.ArrayList r1 = r6.b
            r1.clear()
            java.util.ArrayList r1 = r6.b
            com.mobikwik.sdk.lib.payinstrument.PaymentInstrumentType r2 = com.mobikwik.sdk.lib.payinstrument.PaymentInstrumentType.MK_WALLET
            r1.add(r2)
        L_0x00e4:
            com.mobikwik.sdk.ui.data.b r1 = r6.c
            com.mobikwik.sdk.lib.model.PaymentsMappingAPIResponse$SDKPaymentMappingBody r7 = r7.getData()
            r1.a((com.mobikwik.sdk.lib.model.PaymentsMappingAPIResponse.SDKPaymentMappingBody) r7)
            com.mobikwik.sdk.lib.TransactionConfiguration r7 = r6.d
            java.lang.String r7 = r7.getFetchSavedCardUrl()
            boolean r1 = com.mobikwik.sdk.lib.utils.Utils.isNull(r7)
            if (r1 != 0) goto L_0x013b
            java.util.ArrayList r1 = r6.b
            com.mobikwik.sdk.lib.payinstrument.PaymentInstrumentType r2 = com.mobikwik.sdk.lib.payinstrument.PaymentInstrumentType.SAVED_CARD
            boolean r1 = r1.contains(r2)
            if (r1 == 0) goto L_0x013b
            com.mobikwik.sdk.lib.Transaction r1 = r6.f8359a
            com.mobikwik.sdk.lib.User r1 = r1.getUser()
            if (r1 == 0) goto L_0x013b
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            r0.append(r7)
            java.lang.String r7 = "?email="
            r0.append(r7)
            com.mobikwik.sdk.lib.Transaction r7 = r6.f8359a
            com.mobikwik.sdk.lib.User r7 = r7.getUser()
            java.lang.String r7 = r7.getEmail()
            r0.append(r7)
            java.lang.String r7 = r0.toString()
            com.mobikwik.sdk.lib.tasks.GetSavedCards r0 = new com.mobikwik.sdk.lib.tasks.GetSavedCards
            com.mobikwik.sdk.MobikwikSDK r1 = r6.e
            com.mobikwik.sdk.b r2 = new com.mobikwik.sdk.b
            r2.<init>(r6)
            r0.<init>(r1, r7, r2)
            java.lang.Void[] r7 = new java.lang.Void[r3]
            r0.execute(r7)
            goto L_0x015f
        L_0x013b:
            com.mobikwik.sdk.MobikwikSDK r7 = r6.e
            android.app.ProgressDialog r7 = r7.b
            if (r7 == 0) goto L_0x0158
            com.mobikwik.sdk.MobikwikSDK r7 = r6.e
            android.app.ProgressDialog r7 = r7.b
            boolean r7 = r7.isShowing()
            if (r7 == 0) goto L_0x0158
            com.mobikwik.sdk.MobikwikSDK r7 = r6.e
            android.app.ProgressDialog r7 = r7.b
            r7.dismiss()
        L_0x0158:
            com.mobikwik.sdk.MobikwikSDK r7 = r6.e
            java.util.ArrayList r1 = r6.b
            r7.a(r1, r0)
        L_0x015f:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.mobikwik.sdk.a.onMappingLoaded(com.mobikwik.sdk.lib.model.PaymentsMappingAPIResponse):void");
    }
}
