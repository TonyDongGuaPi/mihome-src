package in.cashify.otex;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AlertDialog;

public class PermissionActivity extends FragmentActivity {

    public class a implements DialogInterface.OnClickListener {
        public a() {
        }

        public void onClick(DialogInterface dialogInterface, int i) {
            dialogInterface.dismiss();
            PermissionActivity.this.finish();
        }
    }

    public void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
    }

    public void onStart() {
        super.onStart();
        new AlertDialog.Builder(this).setMessage((CharSequence) "Please grant permission to Diagnose your device").setPositiveButton((CharSequence) "OK", (DialogInterface.OnClickListener) new a()).show();
    }
}
