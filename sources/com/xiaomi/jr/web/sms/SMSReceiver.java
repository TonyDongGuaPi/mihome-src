package com.xiaomi.jr.web.sms;

import android.annotation.TargetApi;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.text.TextUtils;
import com.xiaomi.jr.common.utils.Algorithms;
import com.xiaomi.jr.common.utils.MifiLog;
import com.xiaomi.jr.common.utils.Utils;
import com.xiaomi.jr.web.sms.SMSMonitor;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

@TargetApi(19)
public class SMSReceiver extends BroadcastReceiver {

    /* renamed from: a  reason: collision with root package name */
    private static final String f11074a = "MiFiSMSReceiver";
    private boolean b = false;
    private String c;
    private List<WeakReference<SMSMonitor.SmsVerificationCodeListener>> d = new ArrayList();

    public void onReceive(Context context, Intent intent) {
        Bundle extras;
        Object[] objArr;
        SmsMessage createFromPdu;
        String action = intent.getAction();
        MifiLog.e(f11074a, "SMSReceiver");
        if ("android.provider.Telephony.SMS_RECEIVED".equals(action) && (extras = intent.getExtras()) != null && (objArr = (Object[]) extras.get("pdus")) != null && objArr.length > 0 && (createFromPdu = SmsMessage.createFromPdu((byte[]) objArr[0])) != null) {
            String messageBody = createFromPdu.getMessageBody();
            String originatingAddress = createFromPdu.getOriginatingAddress();
            long timestampMillis = createFromPdu.getTimestampMillis();
            MifiLog.e(f11074a, "message from: " + originatingAddress + ", message body: " + messageBody + ", message date: " + timestampMillis);
            String a2 = SMSMonitor.a(context, messageBody, this.c);
            StringBuilder sb = new StringBuilder();
            sb.append("verifyCode:");
            sb.append(a2);
            MifiLog.b(f11074a, sb.toString());
            if (!TextUtils.isEmpty(a2)) {
                postResultAndStop(context, a2);
            }
        }
    }

    public void start(Context context, String str, SMSMonitor.SmsVerificationCodeListener smsVerificationCodeListener) {
        Utils.a();
        if (!this.b) {
            this.b = true;
            this.c = str;
            context.registerReceiver(this, new IntentFilter("android.provider.Telephony.SMS_RECEIVED"));
        }
        Algorithms.a(this.d, smsVerificationCodeListener);
    }

    public void stop(Context context) {
        Utils.a();
        Algorithms.a(this.d);
        if (this.b) {
            try {
                context.unregisterReceiver(this);
            } catch (IllegalArgumentException e) {
                MifiLog.e(f11074a, "Failed to unregister sms receiver. " + e.getMessage());
            }
            this.b = false;
        }
    }

    public void postResultAndStop(Context context, String str) {
        for (WeakReference next : this.d) {
            if (next.get() != null) {
                ((SMSMonitor.SmsVerificationCodeListener) next.get()).onReceive(str);
            }
        }
        this.d.clear();
        stop(context);
    }
}
