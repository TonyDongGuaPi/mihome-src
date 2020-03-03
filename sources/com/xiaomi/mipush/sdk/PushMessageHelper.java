package com.xiaomi.mipush.sdk;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ResolveInfo;
import android.text.TextUtils;
import com.xiaomi.push.ib;
import com.xiaomi.push.ir;
import java.util.List;

public class PushMessageHelper {

    /* renamed from: a  reason: collision with root package name */
    public static final String f11522a = "message_type";
    public static final int b = 1;
    public static final int c = 2;
    public static final int d = 3;
    public static final int e = 4;
    public static final int f = 5;
    public static final String g = "error_message";
    public static final String h = "error_type";
    public static final String i = "error_lack_of_permission";
    public static final String j = "key_message";
    public static final String k = "key_command";
    public static final int l = 1;
    public static final int m = 2;
    private static int n;

    public static int a(Context context) {
        if (n == 0) {
            a(b(context) ? 1 : 2);
        }
        return n;
    }

    public static MiPushCommandMessage a(String str, List<String> list, long j2, String str2, String str3) {
        MiPushCommandMessage miPushCommandMessage = new MiPushCommandMessage();
        miPushCommandMessage.setCommand(str);
        miPushCommandMessage.setCommandArguments(list);
        miPushCommandMessage.setResultCode(j2);
        miPushCommandMessage.setReason(str2);
        miPushCommandMessage.setCategory(str3);
        return miPushCommandMessage;
    }

    public static MiPushMessage a(ir irVar, ib ibVar, boolean z) {
        MiPushMessage miPushMessage = new MiPushMessage();
        miPushMessage.setMessageId(irVar.a());
        if (!TextUtils.isEmpty(irVar.d())) {
            miPushMessage.setMessageType(1);
            miPushMessage.setAlias(irVar.d());
        } else if (!TextUtils.isEmpty(irVar.c())) {
            miPushMessage.setMessageType(2);
            miPushMessage.setTopic(irVar.c());
        } else if (!TextUtils.isEmpty(irVar.f())) {
            miPushMessage.setMessageType(3);
            miPushMessage.setUserAccount(irVar.f());
        } else {
            miPushMessage.setMessageType(0);
        }
        miPushMessage.setCategory(irVar.e());
        if (irVar.a() != null) {
            miPushMessage.setContent(irVar.a().c());
        }
        if (ibVar != null) {
            if (TextUtils.isEmpty(miPushMessage.getMessageId())) {
                miPushMessage.setMessageId(ibVar.a());
            }
            if (TextUtils.isEmpty(miPushMessage.getTopic())) {
                miPushMessage.setTopic(ibVar.b());
            }
            miPushMessage.setDescription(ibVar.d());
            miPushMessage.setTitle(ibVar.c());
            miPushMessage.setNotifyType(ibVar.a());
            miPushMessage.setNotifyId(ibVar.c());
            miPushMessage.setPassThrough(ibVar.b());
            miPushMessage.setExtra(ibVar.a());
        }
        miPushMessage.setNotified(z);
        return miPushMessage;
    }

    public static ib a(MiPushMessage miPushMessage) {
        ib ibVar = new ib();
        ibVar.a(miPushMessage.getMessageId());
        ibVar.b(miPushMessage.getTopic());
        ibVar.d(miPushMessage.getDescription());
        ibVar.c(miPushMessage.getTitle());
        ibVar.c(miPushMessage.getNotifyId());
        ibVar.a(miPushMessage.getNotifyType());
        ibVar.b(miPushMessage.getPassThrough());
        ibVar.a(miPushMessage.getExtra());
        return ibVar;
    }

    private static void a(int i2) {
        n = i2;
    }

    public static void a(Context context, MiPushCommandMessage miPushCommandMessage) {
        Intent intent = new Intent("com.xiaomi.mipush.RECEIVE_MESSAGE");
        intent.setPackage(context.getPackageName());
        intent.putExtra("message_type", 3);
        intent.putExtra(k, miPushCommandMessage);
        new PushServiceReceiver().onReceive(context, intent);
    }

    private static boolean a(Context context, Intent intent) {
        try {
            List<ResolveInfo> queryBroadcastReceivers = context.getPackageManager().queryBroadcastReceivers(intent, 32);
            return queryBroadcastReceivers != null && !queryBroadcastReceivers.isEmpty();
        } catch (Exception unused) {
            return true;
        }
    }

    public static boolean b(Context context) {
        Intent intent = new Intent("com.xiaomi.mipush.RECEIVE_MESSAGE");
        intent.setClassName(context.getPackageName(), "com.xiaomi.mipush.sdk.PushServiceReceiver");
        return a(context, intent);
    }

    public static void c(Context context) {
        Intent intent = new Intent("com.xiaomi.mipush.RECEIVE_MESSAGE");
        intent.setPackage(context.getPackageName());
        intent.putExtra("message_type", 4);
        new PushServiceReceiver().onReceive(context, intent);
    }
}
