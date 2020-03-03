package com.xiaomi.smarthome.miio.update;

import android.app.Dialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.text.TextUtils;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.framework.page.BaseActivity;
import com.xiaomi.smarthome.library.common.widget.PieProgressBar;

public class ForceUpdateActivity extends BaseActivity {
    Dialog mProgressDialog = null;

    /* access modifiers changed from: package-private */
    public void dismissProgressDialog() {
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(new FrameLayout(this));
    }

    /* access modifiers changed from: protected */
    public void onStart() {
        super.onStart();
        if (AppUpdateManger.a().a((Context) this, AppUpdateManger.g())) {
            AppUpdateManger.a((Context) this);
        } else {
            showProgressDialog();
        }
    }

    /* access modifiers changed from: protected */
    public void onStop() {
        super.onStop();
        if (AppUpdateManger.u) {
            finish();
        }
    }

    /* access modifiers changed from: protected */
    public void onDestroy() {
        super.onDestroy();
        if (AppUpdateManger.u) {
            dismissProgressDialog();
            System.exit(0);
        }
    }

    /* access modifiers changed from: package-private */
    public void showProgressDialog() {
        if (this.mProgressDialog == null) {
            final Dialog dialog = new Dialog(this, R.style.LockActivityTheme);
            dialog.setContentView(R.layout.dialog_download_progress);
            final PieProgressBar pieProgressBar = (PieProgressBar) dialog.findViewById(R.id.pg_download);
            pieProgressBar.setPercentView((TextView) dialog.findViewById(R.id.txt_status));
            final AnonymousClass1 r2 = new BroadcastReceiver() {
                public void onReceive(Context context, Intent intent) {
                    String stringExtra = intent.getStringExtra("status");
                    if (!TextUtils.isEmpty(stringExtra)) {
                        if (stringExtra.equals(NotificationCompat.CATEGORY_PROGRESS)) {
                            int intExtra = intent.getIntExtra(NotificationCompat.CATEGORY_PROGRESS, 0);
                            if (intExtra > 0 && intExtra < 100) {
                                pieProgressBar.setPercent((float) intExtra);
                            }
                        } else if (stringExtra.equals("success")) {
                            AppUpdateManger.a((Context) ForceUpdateActivity.this);
                        } else {
                            dialog.dismiss();
                            Toast.makeText(ForceUpdateActivity.this, R.string.app_upgrade_failed_smarthome, 0).show();
                            ForceUpdateActivity.this.finish();
                        }
                    }
                }
            };
            dialog.setOnShowListener(new DialogInterface.OnShowListener() {
                public void onShow(DialogInterface dialogInterface) {
                    ForceUpdateActivity.this.registerReceiver(r2, new IntentFilter(AppUpdateManger.c));
                }
            });
            dialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
                public void onDismiss(DialogInterface dialogInterface) {
                    ForceUpdateActivity.this.unregisterReceiver(r2);
                }
            });
            dialog.setCancelable(false);
            this.mProgressDialog = dialog;
        }
        if (!this.mProgressDialog.isShowing()) {
            this.mProgressDialog.show();
            AppUpdateManger.a().a(true);
            AppUpdateManger.a().a((Context) this, true, AppUpdateManger.i(), BitmapFactory.decodeResource(getResources(), R.drawable.ic_launcher));
        }
    }
}
