package com.xiaomi.smarthome.acp;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import com.xiaomi.smarthome.library.common.dialog.MLAlertDialog;

public class ACPCrashDialog extends FragmentActivity {
    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        requestWindowFeature(1);
        if (Build.VERSION.SDK_INT != 26) {
            setRequestedOrientation(1);
        }
        a();
    }

    private void a() {
        new MLAlertDialog.Builder(this).a(R.string.app_name).b((CharSequence) getString(R.string.open_tips)).b(R.string.cancel, (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
                ACPCrashDialog.this.finish();
            }
        }).a((CharSequence) "ok", (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
                Intent intent = new Intent(ACPCrashDialog.this.getApplicationContext(), ACPService.class);
                intent.setAction(ACPService.ACTION_ACP);
                intent.putExtra(ACPService.INTENT_KEY_REPEATED_CRASH, 2);
                ACPCrashDialog.this.startService(intent);
                ACPCrashDialog.this.finish();
            }
        }).b().show();
    }
}
