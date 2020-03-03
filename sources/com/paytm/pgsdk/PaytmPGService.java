package com.paytm.pgsdk;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import com.mi.blockcanary.internal.BlockInfo;
import java.util.Map;

public class PaytmPGService {
    private static volatile PaytmPGService h = null;
    private static final String k = "https://pguat.paytm.com/oltp/HANDLER_INTERNAL/TXNSTATUS";
    private static final String l = "https://secure.paytm.in/oltp/HANDLER_INTERNAL/TXNSTATUS";
    private static final String m = "https://pguat.paytm.com:8448/CAS/ChecksumGenerator";
    private static final String n = "https://secure.paytm.in/oltp-web/generateChecksum";
    private static final String o = "https://pguat.paytm.com/oltp/HANDLER_INTERNAL/CANCEL_TXN";
    private static final String p = "https://secure.paytm.in/oltp/HANDLER_INTERNAL/CANCEL_TXN";
    private static final String q = "https://pguat.paytm.com/oltp-web/processTransaction";
    private static final String r = "https://secure.paytm.in/oltp-web/processTransaction";

    /* renamed from: a  reason: collision with root package name */
    public volatile PaytmOrder f8545a;
    public volatile PaytmClientCertificate b;
    protected volatile String c;
    protected volatile String d;
    protected volatile PaytmPaymentTransactionCallback e;
    protected volatile PaytmRefundCallback f;
    protected volatile PaytmStatusQueryCallback g;
    private volatile boolean i;
    private volatile String j;

    protected static synchronized PaytmPGService a() {
        PaytmPGService paytmPGService;
        synchronized (PaytmPGService.class) {
            try {
                if (h == null) {
                    PaytmUtility.a("Creating an instance of Paytm PG Service...");
                    h = new PaytmPGService();
                    PaytmUtility.a("Created a new instance of Paytm PG Service.");
                }
            } catch (Exception e2) {
                PaytmUtility.a(e2);
            }
            paytmPGService = h;
        }
        return paytmPGService;
    }

    public static synchronized PaytmPGService b() {
        PaytmPGService a2;
        synchronized (PaytmPGService.class) {
            a2 = a();
            a2.j = k;
            a2.c = o;
            a2.d = q;
        }
        return a2;
    }

    public static synchronized PaytmPGService c() {
        PaytmPGService a2;
        synchronized (PaytmPGService.class) {
            a2 = a();
            a2.j = l;
            a2.c = p;
            a2.d = r;
        }
        return a2;
    }

    public synchronized void a(PaytmOrder paytmOrder, PaytmClientCertificate paytmClientCertificate) {
        this.f8545a = paytmOrder;
        this.b = paytmClientCertificate;
    }

    public void a(Context context) {
        ApplicationInfo b2 = b(context);
        boolean z = false;
        if (b2 != null) {
            int i2 = b2.flags & 2;
            b2.flags = i2;
            if (i2 != 0) {
                z = true;
            }
            Log.a(z);
            return;
        }
        Log.a(false);
    }

    public synchronized void a(Context context, boolean z, boolean z2, PaytmPaymentTransactionCallback paytmPaymentTransactionCallback) {
        try {
            a(context);
            if (!PaytmUtility.a(context)) {
                d();
                paytmPaymentTransactionCallback.a();
            } else if (!this.i) {
                Bundle bundle = new Bundle();
                if (this.f8545a != null) {
                    for (Map.Entry next : this.f8545a.a().entrySet()) {
                        PaytmUtility.a(((String) next.getKey()) + BlockInfo.c + ((String) next.getValue()));
                        bundle.putString((String) next.getKey(), (String) next.getValue());
                    }
                }
                PaytmUtility.a("Starting the Service...");
                Intent intent = new Intent(context, PaytmPGActivity.class);
                intent.putExtra("Parameters", bundle);
                intent.putExtra("HIDE_HEADER", z);
                intent.putExtra("SEND_ALL_CHECKSUM_RESPONSE_PARAMETERS_TO_PG_SERVER", z2);
                this.i = true;
                this.e = paytmPaymentTransactionCallback;
                ((Activity) context).startActivity(intent);
                PaytmUtility.a("Service Started.");
            } else {
                PaytmUtility.a("Service is already running.");
            }
        } catch (Exception e2) {
            d();
            PaytmUtility.a(e2);
        }
        return;
    }

    /* access modifiers changed from: protected */
    public synchronized void d() {
        h = null;
        PaytmUtility.a("Service Stopped.");
    }

    private ApplicationInfo b(Context context) {
        try {
            return context.getPackageManager().getApplicationInfo(context.getPackageName(), 0);
        } catch (PackageManager.NameNotFoundException e2) {
            e2.printStackTrace();
            return null;
        }
    }
}
