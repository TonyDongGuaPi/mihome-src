package com.huawei.hms.update.e;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import com.huawei.android.hms.base.R;

public class d extends b {
    public AlertDialog a() {
        ProgressDialog progressDialog = new ProgressDialog(f(), g());
        progressDialog.setMessage(f().getString(R.string.hms_checking));
        progressDialog.setCanceledOnTouchOutside(false);
        return progressDialog;
    }
}
