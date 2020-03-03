package com.xiaomi.mipush.sdk;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.text.TextUtils;
import com.miui.tsmclient.model.ErrorCode;
import com.xiaomi.channel.commonutils.logger.b;
import com.xiaomi.mipush.sdk.PushMessageHandler;
import com.xiaomi.push.ai;
import com.xiaomi.push.fd;
import com.xiaomi.push.fi;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class MessageHandleService extends BaseService {

    /* renamed from: a  reason: collision with root package name */
    private static ConcurrentLinkedQueue<a> f11509a = new ConcurrentLinkedQueue<>();
    private static ExecutorService b = new ThreadPoolExecutor(1, 1, 15, TimeUnit.SECONDS, new LinkedBlockingQueue());

    public static class a {

        /* renamed from: a  reason: collision with root package name */
        private PushMessageReceiver f11510a;
        private Intent b;

        public a(Intent intent, PushMessageReceiver pushMessageReceiver) {
            this.f11510a = pushMessageReceiver;
            this.b = intent;
        }

        public PushMessageReceiver a() {
            return this.f11510a;
        }

        public Intent b() {
            return this.b;
        }
    }

    protected static void a(Context context, Intent intent) {
        if (intent != null) {
            b(context);
        }
    }

    public static void addJob(Context context, a aVar) {
        if (aVar != null) {
            f11509a.add(aVar);
            b(context);
            startService(context);
        }
    }

    private static void b(Context context) {
        if (!b.isShutdown()) {
            b.execute(new ab(context));
        }
    }

    /* access modifiers changed from: private */
    public static void c(Context context) {
        fd a2;
        String packageName;
        int i;
        String str;
        String[] stringArrayExtra;
        try {
            a poll = f11509a.poll();
            if (poll != null) {
                PushMessageReceiver a3 = poll.a();
                Intent b2 = poll.b();
                int intExtra = b2.getIntExtra("message_type", 1);
                if (intExtra != 1) {
                    switch (intExtra) {
                        case 3:
                            MiPushCommandMessage miPushCommandMessage = (MiPushCommandMessage) b2.getSerializableExtra(PushMessageHelper.k);
                            b.a("(Local) begin execute onCommandResult, command=" + miPushCommandMessage.getCommand() + ", resultCode=" + miPushCommandMessage.getResultCode() + ", reason=" + miPushCommandMessage.getReason());
                            a3.onCommandResult(context, miPushCommandMessage);
                            if (TextUtils.equals(miPushCommandMessage.getCommand(), fi.COMMAND_REGISTER.f70a)) {
                                a3.onReceiveRegisterResult(context, miPushCommandMessage);
                                if (miPushCommandMessage.getResultCode() != 0) {
                                    return;
                                }
                            } else {
                                return;
                            }
                            break;
                        case 4:
                            return;
                        case 5:
                            if (PushMessageHelper.i.equals(b2.getStringExtra("error_type")) && (stringArrayExtra = b2.getStringArrayExtra("error_message")) != null) {
                                b.a("begin execute onRequirePermissions, lack of necessary permissions");
                                a3.onRequirePermissions(context, stringArrayExtra);
                                return;
                            }
                            return;
                        default:
                            return;
                    }
                } else {
                    PushMessageHandler.a a4 = at.a(context).a(b2);
                    int intExtra2 = b2.getIntExtra("eventMessageType", -1);
                    if (a4 == null) {
                        return;
                    }
                    if (a4 instanceof MiPushMessage) {
                        MiPushMessage miPushMessage = (MiPushMessage) a4;
                        if (!miPushMessage.isArrivedMessage()) {
                            a3.onReceiveMessage(context, miPushMessage);
                        }
                        if (miPushMessage.getPassThrough() == 1) {
                            fd.a(context.getApplicationContext()).a(context.getPackageName(), b2, 2004, "call passThrough callBack");
                            b.a("begin execute onReceivePassThroughMessage from " + miPushMessage.getMessageId());
                            a3.onReceivePassThroughMessage(context, miPushMessage);
                            return;
                        } else if (miPushMessage.isNotified()) {
                            if (intExtra2 == 1000) {
                                a2 = fd.a(context.getApplicationContext());
                                packageName = context.getPackageName();
                                i = 1007;
                                str = "call notification callBack";
                            } else {
                                a2 = fd.a(context.getApplicationContext());
                                packageName = context.getPackageName();
                                i = ErrorCode.DATA_NOT_READY;
                                str = "call business callBack";
                            }
                            a2.a(packageName, b2, i, str);
                            b.a("begin execute onNotificationMessageClicked from　" + miPushMessage.getMessageId());
                            a3.onNotificationMessageClicked(context, miPushMessage);
                            return;
                        } else {
                            a3.onNotificationMessageArrived(context, miPushMessage);
                            return;
                        }
                    } else if (a4 instanceof MiPushCommandMessage) {
                        MiPushCommandMessage miPushCommandMessage2 = (MiPushCommandMessage) a4;
                        b.a("begin execute onCommandResult, command=" + miPushCommandMessage2.getCommand() + ", resultCode=" + miPushCommandMessage2.getResultCode() + ", reason=" + miPushCommandMessage2.getReason());
                        a3.onCommandResult(context, miPushCommandMessage2);
                        if (TextUtils.equals(miPushCommandMessage2.getCommand(), fi.COMMAND_REGISTER.f70a)) {
                            a3.onReceiveRegisterResult(context, miPushCommandMessage2);
                            if (miPushCommandMessage2.getResultCode() != 0) {
                                return;
                            }
                        } else {
                            return;
                        }
                    } else {
                        return;
                    }
                }
                h.d(context);
            }
        } catch (RuntimeException e) {
            b.a((Throwable) e);
        }
    }

    public static void startService(Context context) {
        Intent intent = new Intent();
        intent.setComponent(new ComponentName(context, MessageHandleService.class));
        ai.a(context).a((Runnable) new aa(context, intent));
    }

    /* access modifiers changed from: protected */
    public boolean a() {
        return f11509a != null && f11509a.size() > 0;
    }

    public IBinder onBind(Intent intent) {
        return null;
    }

    public void onStart(Intent intent, int i) {
        super.onStart(intent, i);
    }
}
