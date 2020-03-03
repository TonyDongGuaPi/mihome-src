package com.huawei.hms.update.d;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

public class a extends BroadcastReceiver {

    /* renamed from: a  reason: collision with root package name */
    private Handler f5920a;

    public a(Handler handler) {
        this.f5920a = handler;
    }

    public void onReceive(Context context, Intent intent) {
        Bundle extras;
        if (intent != null) {
            try {
                intent.getStringExtra("TestIntent");
                String action = intent.getAction();
                if ("com.huawei.appmarket.service.downloadservice.Receiver".equals(action)) {
                    Bundle extras2 = intent.getExtras();
                    if (extras2 != null) {
                        Message message = new Message();
                        message.what = 101;
                        message.obj = extras2;
                        this.f5920a.sendMessage(message);
                    }
                } else if ("com.huawei.appmarket.service.downloadservice.progress.Receiver".equals(action)) {
                    Bundle extras3 = intent.getExtras();
                    if (extras3 != null) {
                        Message message2 = new Message();
                        message2.what = 102;
                        message2.obj = extras3;
                        this.f5920a.sendMessage(message2);
                    }
                } else if ("com.huawei.appmarket.service.installerservice.Receiver".equals(action) && (extras = intent.getExtras()) != null) {
                    Message message3 = new Message();
                    message3.what = 103;
                    message3.obj = extras;
                    this.f5920a.sendMessage(message3);
                }
            } catch (Exception e) {
                com.huawei.hms.support.log.a.d("SilentInstallReceive", "intent has some error" + e.getMessage());
            }
        }
    }
}
