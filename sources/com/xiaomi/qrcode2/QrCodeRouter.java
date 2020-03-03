package com.xiaomi.qrcode2;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v4.content.LocalBroadcastManager;
import android.text.TextUtils;
import com.google.android.exoplayer2.C;

public class QrCodeRouter {
    public static void a(Context context, String str, final QrCodeCallback qrCodeCallback) {
        AnonymousClass1 r0 = new BroadcastReceiver() {
            public void onReceive(Context context, Intent intent) {
                LocalBroadcastManager.getInstance(context).unregisterReceiver(this);
                String str = "";
                if (intent != null) {
                    str = intent.getStringExtra("scan_result");
                }
                if (TextUtils.isEmpty(str)) {
                    qrCodeCallback.onFail(-1, "");
                } else {
                    qrCodeCallback.onSuccess(str);
                }
            }
        };
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(ScanBarcodeActivity.ScanBarcodeResultBroadCast);
        LocalBroadcastManager.getInstance(context).registerReceiver(r0, intentFilter);
        Intent intent = new Intent(context, ScanBarcodeActivity.class);
        intent.putExtra("title", str);
        if (!(context instanceof Activity)) {
            intent.addFlags(C.ENCODING_PCM_MU_LAW);
        }
        context.startActivity(intent);
    }
}
