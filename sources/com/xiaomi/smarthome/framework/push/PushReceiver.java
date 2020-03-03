package com.xiaomi.smarthome.framework.push;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import com.xiaomi.mipush.sdk.MiPushCommandMessage;
import com.xiaomi.mipush.sdk.MiPushMessage;
import com.xiaomi.mipush.sdk.PushMessageReceiver;
import com.xiaomi.smarthome.app.startup.StartupCheckList;
import com.xiaomi.smarthome.application.SHApplication;

public class PushReceiver extends PushMessageReceiver {
    public void onCommandResult(final Context context, final MiPushCommandMessage miPushCommandMessage) {
        if (miPushCommandMessage != null) {
            PushManager.a().c();
            new Handler(Looper.getMainLooper()).post(new Runnable() {
                public void run() {
                    StartupCheckList.a((StartupCheckList.CheckListCallback) new StartupCheckList.CheckListCallback() {
                        public void a() {
                        }

                        public void b() {
                        }

                        public void c() {
                        }

                        public void d() {
                        }

                        public void e() {
                            PushReceiver.this.doWorkOnCommandResult(context, miPushCommandMessage);
                        }
                    });
                }
            });
        }
    }

    public void onReceivePassThroughMessage(final Context context, final MiPushMessage miPushMessage) {
        if (miPushMessage != null) {
            PushManager.a().c();
            new Handler(Looper.getMainLooper()).post(new Runnable() {
                public void run() {
                    StartupCheckList.a((StartupCheckList.CheckListCallback) new StartupCheckList.CheckListCallback() {
                        public void a() {
                        }

                        public void b() {
                        }

                        public void c() {
                        }

                        public void d() {
                        }

                        public void e() {
                            PushReceiver.this.doWorkOnReceiveMessage(context, miPushMessage);
                        }
                    });
                }
            });
        }
    }

    public void onNotificationMessageClicked(final Context context, final MiPushMessage miPushMessage) {
        if (miPushMessage != null) {
            PushManager.a().c();
            new Handler(Looper.getMainLooper()).post(new Runnable() {
                public void run() {
                    StartupCheckList.a((StartupCheckList.CheckListCallback) new StartupCheckList.CheckListCallback() {
                        public void a() {
                        }

                        public void b() {
                        }

                        public void c() {
                        }

                        public void d() {
                        }

                        public void e() {
                            PushReceiver.this.doWorkOnReceiveMessage(context, miPushMessage);
                        }
                    });
                }
            });
        }
    }

    /* access modifiers changed from: package-private */
    public void doWorkOnCommandResult(Context context, MiPushCommandMessage miPushCommandMessage) {
        SHApplication.getPushManager().d.obtainMessage(1, miPushCommandMessage).sendToTarget();
    }

    /* access modifiers changed from: package-private */
    public void doWorkOnReceiveMessage(Context context, MiPushMessage miPushMessage) {
        SHApplication.getPushManager().d.obtainMessage(2, miPushMessage).sendToTarget();
    }
}
