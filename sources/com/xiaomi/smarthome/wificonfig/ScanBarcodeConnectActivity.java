package com.xiaomi.smarthome.wificonfig;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import com.xiaomi.qrcode.ScanBarcodeActivity;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.device.api.DeviceApi;
import com.xiaomi.smarthome.device.utils.DeviceTagInterface;
import com.xiaomi.smarthome.frame.AsyncCallback;
import com.xiaomi.smarthome.frame.Error;
import com.xiaomi.smarthome.framework.page.BaseActivity;
import com.xiaomi.smarthome.library.common.dialog.MLAlertDialog;
import com.xiaomi.smarthome.library.common.dialog.XQProgressDialog;
import com.xiaomi.smarthome.smartconfig.SmartConfigMainActivity;
import org.json.JSONObject;

public class ScanBarcodeConnectActivity extends BaseActivity {
    static final int SCAN_BARCODE = 1;
    XQProgressDialog mXQProgressDialogSimple;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.scan_barcode_connect_activity);
        findViewById(R.id.module_a_3_return_btn).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                ScanBarcodeConnectActivity.this.finish();
            }
        });
        ((TextView) findViewById(R.id.module_a_3_return_title)).setText(R.string.scan_barcode);
        Intent intent = new Intent();
        intent.setClass(this, ScanBarcodeActivity.class);
        startActivityForResult(intent, 1);
    }

    /* access modifiers changed from: protected */
    public void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (i != 1) {
            return;
        }
        if (i2 == -1) {
            verifyQrCode(intent.getStringExtra("scan_result"));
        } else {
            finish();
        }
    }

    /* access modifiers changed from: package-private */
    public void showProgress() {
        dismissProgress();
        this.mXQProgressDialogSimple = XQProgressDialog.a(this, "", getString(R.string.camera_waiting));
        this.mXQProgressDialogSimple.show();
    }

    /* access modifiers changed from: package-private */
    public void dismissProgress() {
        if (this.mXQProgressDialogSimple != null) {
            this.mXQProgressDialogSimple.dismiss();
            this.mXQProgressDialogSimple = null;
        }
    }

    /* access modifiers changed from: package-private */
    public void verifyQrCode(String str) {
        showProgress();
        DeviceApi.getInstance().verifyQrcode(this, str, new AsyncCallback<JSONObject, Error>() {
            /* renamed from: a */
            public void onSuccess(JSONObject jSONObject) {
                ScanBarcodeConnectActivity.this.dismissProgress();
                if (jSONObject != null) {
                    String optString = jSONObject.optString("model");
                    String optString2 = jSONObject.optString(DeviceTagInterface.e);
                    String optString3 = jSONObject.optString("bssid");
                    Intent intent = new Intent(ScanBarcodeConnectActivity.this.getContext(), SmartConfigMainActivity.class);
                    intent.putExtra("scan_device_model", optString);
                    intent.putExtra(DeviceTagInterface.e, optString2);
                    intent.putExtra("bssid", optString3);
                    intent.putExtra("model", optString);
                    intent.putExtra("strategy_id", 5);
                    ScanBarcodeConnectActivity.this.startActivity(intent);
                    ScanBarcodeConnectActivity.this.finish();
                }
            }

            public void onFailure(Error error) {
                ScanBarcodeConnectActivity.this.dismissProgress();
                String string = ScanBarcodeConnectActivity.this.getContext().getString(R.string.barcode_bind_error, new Object[]{error.b()});
                MLAlertDialog.Builder builder = new MLAlertDialog.Builder(ScanBarcodeConnectActivity.this.getContext());
                builder.b((CharSequence) string);
                builder.a((int) R.string.retry, (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Intent intent = new Intent();
                        intent.setClass(ScanBarcodeConnectActivity.this, ScanBarcodeActivity.class);
                        ScanBarcodeConnectActivity.this.startActivityForResult(intent, 1);
                    }
                });
                builder.b((int) R.string.cancel, (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialogInterface, int i) {
                        ScanBarcodeConnectActivity.this.finish();
                    }
                });
                builder.d();
            }
        });
    }
}
