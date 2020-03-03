package miuipub.hybrid;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.net.Uri;
import android.view.KeyEvent;
import com.miuipub.internal.hybrid.HybridManager;
import miuipub.hybrid.GeolocationPermissions;

public class HybridChromeClient {

    /* renamed from: a  reason: collision with root package name */
    private HybridManager f2935a;

    public void a(String str, GeolocationPermissions.Callback callback) {
    }

    public void a(HybridView hybridView, String str) {
    }

    public void a(HybridManager hybridManager) {
        this.f2935a = hybridManager;
    }

    public boolean a(HybridView hybridView, String str, String str2, final JsResult jsResult) {
        new AlertDialog.Builder(this.f2935a.b()).setMessage(str2).setPositiveButton(17039370, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
                jsResult.a();
            }
        }).setOnCancelListener(new DialogInterface.OnCancelListener() {
            public void onCancel(DialogInterface dialogInterface) {
                jsResult.b();
            }
        }).setOnKeyListener(new DialogInterface.OnKeyListener() {
            public boolean onKey(DialogInterface dialogInterface, int i, KeyEvent keyEvent) {
                if (i != 4) {
                    return true;
                }
                jsResult.a();
                return false;
            }
        }).show();
        return true;
    }

    public boolean b(HybridView hybridView, String str, String str2, final JsResult jsResult) {
        new AlertDialog.Builder(this.f2935a.b()).setMessage(str2).setPositiveButton(17039370, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
                jsResult.a();
            }
        }).setNegativeButton(17039360, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
                jsResult.b();
            }
        }).setOnCancelListener(new DialogInterface.OnCancelListener() {
            public void onCancel(DialogInterface dialogInterface) {
                jsResult.b();
            }
        }).setOnKeyListener(new DialogInterface.OnKeyListener() {
            public boolean onKey(DialogInterface dialogInterface, int i, KeyEvent keyEvent) {
                if (i != 4) {
                    return true;
                }
                jsResult.a();
                return false;
            }
        }).show();
        return true;
    }

    public void a(HybridView hybridView, int i) {
        hybridView.setProgress(i);
    }

    public void a(ValueCallback<Uri> valueCallback, String str, String str2) {
        valueCallback.a(null);
    }
}
