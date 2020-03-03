package com.huawei.hms.update.e;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.huawei.android.hms.base.R;
import java.text.NumberFormat;

public class h extends b {

    /* renamed from: a  reason: collision with root package name */
    private ProgressBar f5925a;
    private TextView b;
    private DialogInterface.OnKeyListener c = new a();

    public AlertDialog a() {
        AlertDialog.Builder builder = new AlertDialog.Builder(f(), g());
        View inflate = View.inflate(f(), R.layout.hms_download_progress, (ViewGroup) null);
        builder.setView(inflate);
        builder.setCancelable(false);
        builder.setOnKeyListener(this.c);
        this.f5925a = (ProgressBar) inflate.findViewById(R.id.download_info_progress);
        this.b = (TextView) inflate.findViewById(R.id.hms_progress_text);
        a(0, 0);
        return builder.create();
    }

    /* access modifiers changed from: package-private */
    public void a(int i, int i2) {
        Activity f = f();
        if (f == null || f.isFinishing()) {
            com.huawei.hms.support.log.a.c("DownloadProgress", "In setDownloading, The activity is null or finishing.");
        } else if (this.b != null && this.f5925a != null) {
            int i3 = 0;
            if (i >= 0 && i2 > 0) {
                i3 = (int) ((((long) i) * 100) / ((long) i2));
            }
            this.f5925a.setProgress(i3);
            this.b.setText(NumberFormat.getPercentInstance().format((double) (((float) i3) / 100.0f)));
        }
    }

    private static class a implements DialogInterface.OnKeyListener {
        private a() {
        }

        public boolean onKey(DialogInterface dialogInterface, int i, KeyEvent keyEvent) {
            return i == 4 && keyEvent.getRepeatCount() == 0;
        }
    }
}
