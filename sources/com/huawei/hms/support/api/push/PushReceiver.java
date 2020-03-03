package com.huawei.hms.support.api.push;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import com.taobao.weex.el.parse.Operators;
import com.xiaomi.smarthome.auth.AuthConstants;
import java.io.UnsupportedEncodingException;
import java.util.concurrent.Executors;

public abstract class PushReceiver extends BroadcastReceiver {

    public interface BOUND_KEY {
        public static final String deviceTokenKey = "deviceToken";
        public static final String pushMsgKey = "pushMsg";
        public static final String pushNotifyId = "pushNotifyId";
        public static final String pushStateKey = "pushState";
        public static final String receiveTypeKey = "receiveType";
    }

    public enum Event {
        NOTIFICATION_OPENED,
        NOTIFICATION_CLICK_BTN
    }

    enum c {
        ReceiveType_Init,
        ReceiveType_Token,
        ReceiveType_Msg,
        ReceiveType_PushState,
        ReceiveType_NotifyClick,
        ReceiveType_ClickBtn
    }

    public void onEvent(Context context, Event event, Bundle bundle) {
    }

    public void onPushMsg(Context context, byte[] bArr, String str) {
    }

    public void onPushState(Context context, boolean z) {
    }

    public void onToken(Context context, String str) {
    }

    public void onToken(Context context, String str, Bundle bundle) {
        onToken(context, str);
    }

    public boolean onPushMsg(Context context, byte[] bArr, Bundle bundle) {
        String str = "";
        if (bundle != null) {
            str = bundle.getString(BOUND_KEY.deviceTokenKey);
        }
        onPushMsg(context, bArr, str);
        return true;
    }

    public final void onReceive(Context context, Intent intent) {
        try {
            if (com.huawei.hms.support.log.a.a()) {
                com.huawei.hms.support.log.a.a("PushReceiver", "enter PushMsgReceiver:onReceive(Intent:" + intent.getAction() + " pkgName:" + context.getPackageName() + Operators.BRACKET_END_STR);
            }
            String action = intent.getAction();
            if ("com.huawei.android.push.intent.REGISTRATION".equals(action) && intent.hasExtra(AuthConstants.e)) {
                a(context, intent);
            } else if ("com.huawei.android.push.intent.RECEIVE".equals(action) && intent.hasExtra("msg_data")) {
                b(context, intent);
            } else if ("com.huawei.android.push.intent.CLICK".equals(action) && intent.hasExtra("click")) {
                c(context, intent);
            } else if ("com.huawei.android.push.intent.CLICK".equals(action) && intent.hasExtra("clickBtn")) {
                d(context, intent);
            } else if ("com.huawei.intent.action.PUSH_STATE".equals(action)) {
                e(context, intent);
            } else if (com.huawei.hms.support.log.a.a()) {
                com.huawei.hms.support.log.a.b("PushReceiver", "message can't be recognised:" + intent.toUri(0));
            }
        } catch (Exception e) {
            if (com.huawei.hms.support.log.a.d()) {
                com.huawei.hms.support.log.a.d("PushReceiver", "call onReceive(intent:" + intent + ") cause:" + e.getMessage());
            }
        }
    }

    private void a(Context context, Intent intent) throws UnsupportedEncodingException {
        byte[] byteArrayExtra = intent.getByteArrayExtra(AuthConstants.e);
        if (byteArrayExtra != null) {
            String str = new String(byteArrayExtra, "UTF-8");
            if (com.huawei.hms.support.log.a.a()) {
                com.huawei.hms.support.log.a.a("PushReceiver", "get a deviceToken:" + com.huawei.hms.support.api.push.a.a.b.c.a(str));
            }
            boolean a2 = new com.huawei.hms.support.api.push.a.a.a.c(context, "push_client_self_info").a("hasRequestToken");
            String a3 = com.huawei.hms.support.api.push.a.a.a.a(context, "push_client_self_info", "token_info");
            if (com.huawei.hms.support.log.a.a()) {
                com.huawei.hms.support.log.a.a("PushReceiver", "my oldtoken is :" + com.huawei.hms.support.api.push.a.a.b.c.a(a3));
            }
            if (a2 || !str.equals(a3)) {
                if (com.huawei.hms.support.log.a.a()) {
                    com.huawei.hms.support.log.a.a("PushReceiver", "push client begin to receive the token");
                }
                Executors.newSingleThreadExecutor().execute(new b(context, str));
                Bundle bundle = new Bundle();
                bundle.putString(BOUND_KEY.deviceTokenKey, str);
                bundle.putByteArray(BOUND_KEY.pushMsgKey, (byte[]) null);
                bundle.putInt(BOUND_KEY.receiveTypeKey, c.ReceiveType_Token.ordinal());
                if (intent.getExtras() != null) {
                    bundle.putAll(intent.getExtras());
                }
                Executors.newSingleThreadExecutor().execute(new a(context, bundle));
            } else if (com.huawei.hms.support.log.a.a()) {
                com.huawei.hms.support.log.a.a("PushReceiver", "get a deviceToken, but do not requested token, and new token is equals old token");
            }
            if (a2 && !str.equals(a3) && com.huawei.hms.support.log.a.a()) {
                com.huawei.hms.support.log.a.a("PushReceiver", "begin to report active state tag");
            }
        } else if (com.huawei.hms.support.log.a.a()) {
            com.huawei.hms.support.log.a.a("PushReceiver", "get a deviceToken, but it is null");
        }
    }

    private void b(Context context, Intent intent) throws UnsupportedEncodingException {
        f(context, intent);
        boolean a2 = new com.huawei.hms.support.api.push.a.a.a.c(context, "push_switch").a("normal_msg_enable");
        if (com.huawei.hms.support.log.a.a()) {
            com.huawei.hms.support.log.a.a("PushReceiver", "closePush_Normal:" + a2);
        }
        if (!a2) {
            byte[] byteArrayExtra = intent.getByteArrayExtra("msg_data");
            byte[] byteArrayExtra2 = intent.getByteArrayExtra(AuthConstants.e);
            if (byteArrayExtra != null && byteArrayExtra2 != null) {
                if (com.huawei.hms.support.log.a.a()) {
                    com.huawei.hms.support.log.a.a("PushReceiver", "PushReceiver receive a message success");
                }
                String str = new String(byteArrayExtra2, "UTF-8");
                Bundle bundle = new Bundle();
                bundle.putString(BOUND_KEY.deviceTokenKey, str);
                bundle.putByteArray(BOUND_KEY.pushMsgKey, byteArrayExtra);
                bundle.putInt(BOUND_KEY.receiveTypeKey, c.ReceiveType_Msg.ordinal());
                Executors.newSingleThreadExecutor().execute(new a(context, bundle));
            } else if (com.huawei.hms.support.log.a.a()) {
                com.huawei.hms.support.log.a.a("PushReceiver", "PushReceiver receive a message, but message is empty.");
            }
        } else if (com.huawei.hms.support.log.a.a()) {
            com.huawei.hms.support.log.a.a("PushReceiver", "close switch is true, message not dispatch");
        }
    }

    private void c(Context context, Intent intent) {
        String stringExtra = intent.getStringExtra("click");
        Bundle bundle = new Bundle();
        bundle.putString(BOUND_KEY.pushMsgKey, stringExtra);
        bundle.putInt(BOUND_KEY.receiveTypeKey, c.ReceiveType_NotifyClick.ordinal());
        Executors.newSingleThreadExecutor().execute(new a(context, bundle));
    }

    private void d(Context context, Intent intent) {
        String stringExtra = intent.getStringExtra("clickBtn");
        int intExtra = intent.getIntExtra("notifyId", 0);
        Bundle bundle = new Bundle();
        bundle.putString(BOUND_KEY.pushMsgKey, stringExtra);
        bundle.putInt(BOUND_KEY.pushNotifyId, intExtra);
        bundle.putInt(BOUND_KEY.receiveTypeKey, c.ReceiveType_ClickBtn.ordinal());
        Executors.newSingleThreadExecutor().execute(new a(context, bundle));
    }

    private void e(Context context, Intent intent) {
        boolean booleanExtra = intent.getBooleanExtra("push_state", false);
        Bundle bundle = new Bundle();
        bundle.putBoolean(BOUND_KEY.pushStateKey, booleanExtra);
        bundle.putInt(BOUND_KEY.receiveTypeKey, c.ReceiveType_PushState.ordinal());
        Executors.newSingleThreadExecutor().execute(new a(context, bundle));
    }

    private class a implements Runnable {
        private Context b;
        private Bundle c;

        public a(Context context, Bundle bundle) {
            this.b = context;
            this.c = bundle;
        }

        public void run() {
            try {
                if (this.c != null) {
                    int i = this.c.getInt(BOUND_KEY.receiveTypeKey);
                    if (i >= 0) {
                        if (i < c.values().length) {
                            switch (c.values()[i]) {
                                case ReceiveType_Token:
                                    PushReceiver.this.onToken(this.b, this.c.getString(BOUND_KEY.deviceTokenKey), this.c);
                                    return;
                                case ReceiveType_Msg:
                                    byte[] byteArray = this.c.getByteArray(BOUND_KEY.pushMsgKey);
                                    if (byteArray != null) {
                                        PushReceiver.this.onPushMsg(this.b, byteArray, this.c);
                                        return;
                                    }
                                    return;
                                case ReceiveType_PushState:
                                    PushReceiver.this.onPushState(this.b, this.c.getBoolean(BOUND_KEY.pushStateKey));
                                    return;
                                case ReceiveType_NotifyClick:
                                    PushReceiver.this.onEvent(this.b, Event.NOTIFICATION_OPENED, this.c);
                                    return;
                                case ReceiveType_ClickBtn:
                                    PushReceiver.this.onEvent(this.b, Event.NOTIFICATION_CLICK_BTN, this.c);
                                    return;
                                default:
                                    return;
                            }
                        }
                    }
                    if (com.huawei.hms.support.log.a.a()) {
                        com.huawei.hms.support.log.a.a("PushReceiver", "invalid receiverType:" + i);
                    }
                }
            } catch (Exception e) {
                if (com.huawei.hms.support.log.a.a()) {
                    com.huawei.hms.support.log.a.a("PushReceiver", "call EventThread(ReceiveType cause:" + e.getMessage());
                }
            }
        }
    }

    private static class b implements Runnable {

        /* renamed from: a  reason: collision with root package name */
        Context f5888a;
        String b;

        public b(Context context, String str) {
            this.f5888a = context;
            this.b = str;
        }

        public void run() {
            com.huawei.hms.support.api.push.a.a.a.c cVar = new com.huawei.hms.support.api.push.a.a.a.c(this.f5888a, "push_client_self_info");
            cVar.a("hasRequestToken", false);
            cVar.d("token_info");
            com.huawei.hms.support.api.push.a.a.a.a(this.f5888a, "push_client_self_info", "token_info", this.b);
        }
    }

    private void f(Context context, Intent intent) {
        if (context != null && intent != null) {
            String stringExtra = intent.getStringExtra("msgIdStr");
            if (!TextUtils.isEmpty(stringExtra) && com.huawei.hms.support.api.push.a.a.a(context)) {
                Intent intent2 = new Intent("com.huawei.android.push.intent.MSG_RESPONSE");
                intent2.putExtra("msgIdStr", stringExtra);
                intent2.setPackage("android");
                intent2.setFlags(32);
                if (com.huawei.hms.support.log.a.a()) {
                    com.huawei.hms.support.log.a.a("PushReceiver", "send msg response broadcast to frameworkPush");
                }
                context.sendBroadcast(intent2);
            }
        }
    }
}
