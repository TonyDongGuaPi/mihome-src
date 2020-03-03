package com.xiaomi.jr.account;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import com.google.android.exoplayer2.C;
import com.xiaomi.jr.common.utils.MifiLog;

public class AccountResultActivity extends Activity {
    public static final int REQUEST_CODE_ADD_ACCOUNT = 1;
    public static final int REQUEST_CODE_USER_INTERACTION = 2;

    /* renamed from: a  reason: collision with root package name */
    private static final String f10273a = "AccountResultActivity";
    private static final String b = "request_code";

    public void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
        requestWindowFeature(1);
        Intent intent = (Intent) getIntent().getParcelableExtra("intent");
        int intExtra = getIntent().getIntExtra(b, 1);
        if (intent != null) {
            startActivityForResult(intent, intExtra);
        }
    }

    /* access modifiers changed from: protected */
    public void onActivityResult(int i, int i2, Intent intent) {
        Intent intent2;
        super.onActivityResult(i, i2, intent);
        int i3 = -1;
        boolean z = true;
        if (i == 1) {
            if (i2 != -1) {
                if (intent == null || (intent2 = (Intent) intent.getParcelableExtra("intent")) == null) {
                    i3 = 4;
                } else {
                    MifiLog.b(f10273a, "Login cancelled, continue to another login.");
                    startActivityForResult(intent2, 1);
                    return;
                }
            }
            XiaomiAccountManager.k().a(getApplicationContext(), i3);
        } else if (i == 2) {
            if (i2 != -1) {
                z = false;
            }
            XiaomiServiceTokenHelper.a(z);
        }
        finish();
    }

    public static void startActivity(Context context, Intent intent, int i) {
        Bundle bundle = new Bundle();
        bundle.putParcelable("intent", intent);
        Intent intent2 = new Intent();
        intent2.setClass(context, AccountResultActivity.class);
        intent2.putExtras(bundle);
        intent2.putExtra(b, i);
        intent2.addFlags(C.ENCODING_PCM_MU_LAW);
        context.startActivity(intent2);
    }
}
