package com.xiaomi.payment.task.rxjava;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.telephony.SmsManager;
import android.telephony.TelephonyManager;
import com.mibi.common.exception.PaymentException;
import com.mibi.common.rxjava.RxTask;
import com.xiaomi.payment.receiver.SmsBroadcastReceiver;
import com.xiaomi.payment.task.rxjava.RxSendSMSTask.Result;
import java.util.concurrent.CountDownLatch;

public class RxSendSMSTask<TaskResult extends Result> extends RxTask<TaskResult> {

    /* renamed from: a  reason: collision with root package name */
    public static final int f12432a = 101;
    public static final int b = 102;
    public static final int c = 103;
    public static final int d = 104;
    public static final int e = 105;
    protected Context f;
    protected String g;
    protected String h;
    protected String i;

    public static class Result {

        /* renamed from: a  reason: collision with root package name */
        public int f12433a;
    }

    public RxSendSMSTask(Context context, String str, String str2, String str3, Class<TaskResult> cls) {
        super(cls);
        this.f = context;
        this.g = str;
        this.h = str2;
        this.i = str3;
    }

    /* access modifiers changed from: protected */
    public void a(TaskResult taskresult) throws PaymentException {
        if (this.g.isEmpty() || this.h.isEmpty()) {
            taskresult.f12433a = 104;
        } else if (this.i.isEmpty()) {
            taskresult.f12433a = 105;
        } else if (!((TelephonyManager) this.f.getApplicationContext().getSystemService("phone")).hasIccCard()) {
            taskresult.f12433a = 101;
        } else {
            Intent intent = new Intent(this.i);
            intent.setPackage(this.f.getPackageName());
            PendingIntent broadcast = PendingIntent.getBroadcast(this.f, 0, intent, 1073741824);
            CountDownLatch countDownLatch = new CountDownLatch(1);
            IntentFilter intentFilter = new IntentFilter(this.i);
            SmsBroadcastReceiver smsBroadcastReceiver = new SmsBroadcastReceiver();
            smsBroadcastReceiver.setCountDownLatch(countDownLatch);
            smsBroadcastReceiver.reset();
            this.f.registerReceiver(smsBroadcastReceiver, intentFilter);
            a(broadcast);
            try {
                countDownLatch.await();
            } catch (InterruptedException e2) {
                e2.printStackTrace();
            }
            int result = smsBroadcastReceiver.getResult();
            switch (result) {
                case 1:
                    taskresult.f12433a = 103;
                    break;
                case 2:
                    taskresult.f12433a = 102;
                    break;
                case 3:
                    taskresult.f12433a = 103;
                    break;
                default:
                    taskresult.f12433a = result;
                    break;
            }
            this.f.unregisterReceiver(smsBroadcastReceiver);
        }
    }

    private void a(PendingIntent pendingIntent) {
        SmsManager.getDefault().sendTextMessage(this.g, (String) null, this.h, pendingIntent, (PendingIntent) null);
    }
}
