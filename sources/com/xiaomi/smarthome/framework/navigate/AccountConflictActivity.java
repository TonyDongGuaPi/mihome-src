package com.xiaomi.smarthome.framework.navigate;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.widget.Toast;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.application.SHApplication;
import com.xiaomi.smarthome.frame.AsyncCallback;
import com.xiaomi.smarthome.frame.Error;
import com.xiaomi.smarthome.framework.login.logic.LoginManager;
import com.xiaomi.smarthome.library.common.dialog.MLAlertDialog;
import com.xiaomi.smarthome.library.common.util.DisplayUtils;

public class AccountConflictActivity extends FragmentActivity {
    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        DisplayUtils.e(this);
        super.onCreate(bundle);
        if (Build.VERSION.SDK_INT != 26 || !DisplayUtils.d(this)) {
            setRequestedOrientation(1);
        }
        new MLAlertDialog.Builder(this).b((CharSequence) getString(R.string.open_device_account_erro)).a((int) R.string.ok_button, (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
                LoginManager.a().logout(new AsyncCallback<Void, Error>() {
                    /* renamed from: a */
                    public void onSuccess(Void voidR) {
                        Intent intent = new Intent(SHApplication.getAppContext(), SmartHomeLauncher.class);
                        intent.setData(Uri.parse("https://home.mi.com/main/login"));
                        AccountConflictActivity.this.startActivity(intent);
                        AccountConflictActivity.this.finish();
                    }

                    public void onFailure(Error error) {
                        Toast.makeText(AccountConflictActivity.this.getApplicationContext(), AccountConflictActivity.this.getString(R.string.open_device_logout_error), 1).show();
                        AccountConflictActivity.this.finish();
                    }
                });
            }
        }).a((DialogInterface.OnCancelListener) new DialogInterface.OnCancelListener() {
            public void onCancel(DialogInterface dialogInterface) {
                AccountConflictActivity.this.finish();
            }
        }).b((int) R.string.cancel, (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
                AccountConflictActivity.this.finish();
            }
        }).d();
    }
}
