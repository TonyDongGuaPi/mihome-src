package com.xiaomi.passport.ui.internal;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;
import com.xiaomi.account.diagnosis.task.CollectAndUploadDiagnosisTask;
import com.xiaomi.passport.ui.R;
import kotlin.Metadata;

@Metadata(bv = {1, 0, 2}, d1 = {"\u0000\u0016\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u000e\u0010\u0004\u001a\n \u0006*\u0004\u0018\u00010\u00050\u0005H\n¢\u0006\u0002\b\u0007"}, d2 = {"<anonymous>", "", "success", "", "traceId", "", "kotlin.jvm.PlatformType", "onFinished"}, k = 3, mv = {1, 1, 10})
final class CommonErrorHandler$uploadLog$task$1 implements CollectAndUploadDiagnosisTask.Callback {
    final /* synthetic */ Context $context;
    final /* synthetic */ ProgressDialog $progress;

    CommonErrorHandler$uploadLog$task$1(Context context, ProgressDialog progressDialog) {
        this.$context = context;
        this.$progress = progressDialog;
    }

    public final void onFinished(boolean z, String str) {
        if ((this.$context instanceof Activity) && !((Activity) this.$context).isFinishing()) {
            this.$progress.dismiss();
            AlertDialog.Builder builder = new AlertDialog.Builder(this.$context);
            if (!z || TextUtils.isEmpty(str)) {
                builder.setMessage((CharSequence) this.$context.getString(R.string.diagnosis_log_send_failed));
            } else {
                builder.setMessage((CharSequence) this.$context.getString(R.string.diagnosis_log_sent_format, new Object[]{str}));
            }
            builder.setPositiveButton(R.string.ok, (DialogInterface.OnClickListener) null);
            builder.setCancelable(false);
            builder.show();
        }
    }
}
