package com.xiaomi.push.service;

import android.annotation.TargetApi;
import android.app.Service;
import android.app.job.JobParameters;
import android.app.job.JobService;
import android.content.Intent;
import android.os.Binder;
import android.os.Build;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import com.xiaomi.channel.commonutils.logger.b;
import com.xiaomi.push.ba;
import com.xiaomi.push.fe;

public class XMJobService extends Service {

    /* renamed from: a  reason: collision with root package name */
    static Service f12843a;

    /* renamed from: a  reason: collision with other field name */
    private IBinder f227a = null;

    @TargetApi(21)
    static class a extends JobService {

        /* renamed from: a  reason: collision with root package name */
        Binder f12844a;

        /* renamed from: a  reason: collision with other field name */
        private Handler f228a;

        /* renamed from: com.xiaomi.push.service.XMJobService$a$a  reason: collision with other inner class name */
        private static class C0088a extends Handler {

            /* renamed from: a  reason: collision with root package name */
            JobService f12845a;

            C0088a(JobService jobService) {
                super(jobService.getMainLooper());
                this.f12845a = jobService;
            }

            public void handleMessage(Message message) {
                if (message.what == 1) {
                    JobParameters jobParameters = (JobParameters) message.obj;
                    b.a("Job finished " + jobParameters.getJobId());
                    this.f12845a.jobFinished(jobParameters, false);
                    if (jobParameters.getJobId() == 1) {
                        fe.a(false);
                    }
                }
            }
        }

        a(Service service) {
            this.f12844a = null;
            this.f12844a = (Binder) ba.a((Object) this, "onBind", new Intent());
            ba.a((Object) this, "attachBaseContext", service);
        }

        public boolean onStartJob(JobParameters jobParameters) {
            b.a("Job started " + jobParameters.getJobId());
            Intent intent = new Intent(this, XMPushService.class);
            intent.setAction("com.xiaomi.push.timer");
            intent.setPackage(getPackageName());
            startService(intent);
            if (this.f228a == null) {
                this.f228a = new C0088a(this);
            }
            this.f228a.sendMessage(Message.obtain(this.f228a, 1, jobParameters));
            return true;
        }

        public boolean onStopJob(JobParameters jobParameters) {
            b.a("Job stop " + jobParameters.getJobId());
            return false;
        }
    }

    static Service a() {
        return f12843a;
    }

    public IBinder onBind(Intent intent) {
        return this.f227a != null ? this.f227a : new Binder();
    }

    public void onCreate() {
        super.onCreate();
        if (Build.VERSION.SDK_INT >= 21) {
            this.f227a = new a(this).f12844a;
        }
        f12843a = this;
    }

    public void onDestroy() {
        super.onDestroy();
        f12843a = null;
    }
}
