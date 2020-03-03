package com.xiaomi.miot.support.monitor.core.block;

import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.text.TextUtils;
import android.util.Printer;
import com.xiaomi.miot.support.monitor.MiotMonitorManager;
import com.xiaomi.miot.support.monitor.config.MiotMonitorConfig;
import com.xiaomi.miot.support.monitor.core.tasks.BaseTask;
import com.xiaomi.miot.support.monitor.core.tasks.MiotApmTask;
import com.xiaomi.miot.support.monitor.exceptions.MiotMonitorBaseException;
import com.xiaomi.miot.support.monitor.exceptions.MiotMonitorBlockException;
import com.xiaomi.miot.support.monitor.report.IReport;
import org.json.JSONException;
import org.json.JSONObject;

public class BlockTask extends BaseTask {

    /* renamed from: a  reason: collision with root package name */
    private final String f11460a = "BlockTask";
    private HandlerThread e = new HandlerThread("blockThread");
    private Handler f;
    private long g;
    private Runnable h = new Runnable() {
        public void run() {
            if (BlockTask.this.f()) {
                BlockTask.this.a(Looper.getMainLooper().getThread().getStackTrace());
            }
        }
    };

    public String a() {
        return MiotApmTask.j;
    }

    public void b() {
        try {
            super.b();
            this.g = MiotMonitorManager.a().c().c.threshold_ms;
            if (!this.e.isAlive()) {
                this.e.start();
                this.f = new Handler(this.e.getLooper());
                Looper.getMainLooper().setMessageLogging(new Printer() {
                    private static final String b = ">>>>> Dispatching";
                    private static final String c = "<<<<< Finished";

                    public void println(String str) {
                        if (BlockTask.this.f()) {
                            if (str.startsWith(b)) {
                                BlockTask.this.c();
                            }
                            if (str.startsWith(c)) {
                                BlockTask.this.d();
                            }
                        }
                    }
                });
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    public void c() {
        this.f.postDelayed(this.h, this.g);
    }

    public void d() {
        this.f.removeCallbacks(this.h);
    }

    /* access modifiers changed from: private */
    public void a(StackTraceElement[] stackTraceElementArr) {
        if (f()) {
            MiotMonitorConfig c = MiotMonitorManager.a().c();
            MiotMonitorBlockException miotMonitorBlockException = new MiotMonitorBlockException();
            miotMonitorBlockException.setStackTrace(stackTraceElementArr);
            if (TextUtils.equals(c.c.report_type, "1")) {
                c.f1475a.a(IReport.Func_type.BLOCK, c.c.report_type, (MiotMonitorBaseException) miotMonitorBlockException);
                return;
            }
            try {
                c.f1475a.a(IReport.Func_type.BLOCK, c.c.report_type, new JSONObject().put("stack", miotMonitorBlockException.getLogInfo()));
            } catch (JSONException e2) {
                e2.printStackTrace();
            }
        }
    }
}
