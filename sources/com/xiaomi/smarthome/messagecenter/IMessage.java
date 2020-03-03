package com.xiaomi.smarthome.messagecenter;

import android.app.Activity;
import android.text.TextUtils;
import android.widget.TextView;
import com.facebook.drawee.view.SimpleDraweeView;
import com.xiaomi.smarthome.library.common.dialog.XQProgressDialog;
import com.xiaomi.smarthome.library.common.util.StringUtil;
import com.xiaomi.smarthome.messagecenter.DevicePushMessageManager;
import com.xiaomi.smarthome.miio.db.record.MessageRecord;
import org.json.JSONException;
import org.json.JSONObject;

public abstract class IMessage {

    /* renamed from: a  reason: collision with root package name */
    private long f10452a;
    public MessageRecord f;
    protected JSONObject g = null;

    public abstract void a(TextView textView);

    public abstract void a(SimpleDraweeView simpleDraweeView);

    public abstract void a(XQProgressDialog xQProgressDialog);

    public abstract void b(TextView textView);

    public abstract void b(XQProgressDialog xQProgressDialog);

    public abstract boolean b();

    public abstract String c();

    public abstract void c(TextView textView);

    public void d(TextView textView) {
    }

    public long a() {
        return this.f10452a;
    }

    public boolean g() {
        if (this.g == null) {
            return false;
        }
        long optLong = this.g.optLong("expire_time", -1);
        if (optLong == -1 || optLong == 0 || System.currentTimeMillis() <= optLong * 1000) {
            return false;
        }
        return true;
    }

    public boolean h() {
        if (this.f == null || TextUtils.isEmpty(this.f.is_new) || a() < 1479106800 || !TextUtils.equals(this.f.is_new, "1")) {
            return false;
        }
        if (!TextUtils.equals(this.f.messageType, "6") || !(this instanceof DevicePushMessageManager.DevicePushMessage)) {
            return true;
        }
        DevicePushMessageManager.DevicePushMessage devicePushMessage = (DevicePushMessageManager.DevicePushMessage) this;
        return DevicePushRedpointHelper.a(devicePushMessage.b, devicePushMessage.f.receiveTime);
    }

    public boolean i() {
        if (this.f == null || TextUtils.isEmpty(this.f.is_new) || a() < 1479106800 || !TextUtils.equals(this.f.is_new, "1")) {
            return false;
        }
        return true;
    }

    public boolean j() {
        if (!h()) {
            return false;
        }
        if (this instanceof DevicePushMessageManager.DevicePushMessage) {
            return NewMsgLocalHelper.b(c());
        }
        return true;
    }

    public void a(Activity activity) {
        NewMsgLocalHelper.a(c());
    }

    /* access modifiers changed from: protected */
    public boolean k() {
        if (this.g == null && this.f != null && !TextUtils.isEmpty(this.f.params)) {
            try {
                this.g = new JSONObject(this.f.params);
            } catch (JSONException unused) {
            }
        }
        return this.g != null && !this.g.isNull("is_new_message") && this.g.optInt("is_new_message") == 1;
    }

    public boolean e(TextView textView) {
        if (!k() || this.g == null) {
            return false;
        }
        return a(this, this.g.optString("user_name"), this.g.optString("title"), textView, 5);
    }

    public boolean a(TextView textView, String str, int i) {
        if (!k() || this.g == null) {
            return false;
        }
        return a(this, this.g.optString("user_name"), str, textView, i);
    }

    public static boolean a(IMessage iMessage, String str, String str2, TextView textView, int i) {
        String str3;
        if (!iMessage.k()) {
            return false;
        }
        if (!TextUtils.isEmpty(str) && !TextUtils.isEmpty(str2)) {
            String a2 = StringUtil.a(textView.getContext(), str, textView.getTextSize(), textView, i);
            if (str2.contains("%s")) {
                str3 = str2.replace("%s", a2);
            } else {
                str3 = a2 + str2;
            }
            textView.setText(str3);
            return true;
        } else if (TextUtils.isEmpty(str2)) {
            return false;
        } else {
            textView.setText(str2);
            return true;
        }
    }
}
