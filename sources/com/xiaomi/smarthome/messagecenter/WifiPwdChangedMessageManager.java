package com.xiaomi.smarthome.messagecenter;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;
import com.facebook.drawee.view.SimpleDraweeView;
import com.xiaomi.mishopsdk.util.Constants;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.framework.openapi.OpenApi;
import com.xiaomi.smarthome.framework.statistic.StatHelper;
import com.xiaomi.smarthome.library.common.dialog.XQProgressDialog;
import com.xiaomi.smarthome.library.common.util.CalendarUtils;
import com.xiaomi.smarthome.library.common.util.CommonUtils;
import com.xiaomi.smarthome.miio.db.record.MessageRecord;
import com.xiaomi.smarthome.miio.page.MessageCenterWifiChangePwdActivity;

public class WifiPwdChangedMessageManager extends IMessageManager {

    public class DevicePushMessage extends IMessage {
        private MessageRecord b;

        public void a(XQProgressDialog xQProgressDialog) {
        }

        public void b(XQProgressDialog xQProgressDialog) {
        }

        public boolean b() {
            return false;
        }

        public DevicePushMessage() {
        }

        public void a(MessageRecord messageRecord) {
            this.b = messageRecord;
        }

        public MessageRecord d() {
            return this.b;
        }

        public void a(Activity activity) {
            Bundle bundle = new Bundle();
            bundle.putString(MessageCenterWifiChangePwdActivity.INTENT_KEY_MSG_RECORD, this.b.params);
            OpenApi.a(MessageCenterWifiChangePwdActivity.class, bundle, false, Constants.CALLIGRAPHY_TAG_PRICE);
            StatHelper.F();
        }

        public long a() {
            return this.b.receiveTime;
        }

        public void a(SimpleDraweeView simpleDraweeView) {
            simpleDraweeView.setImageURI(CommonUtils.c((int) R.drawable.wifi_icon));
        }

        public void a(TextView textView) {
            if (!e(textView)) {
                textView.setText(this.b.title);
            }
        }

        public void b(TextView textView) {
            if (!k()) {
                textView.setText(CalendarUtils.b(this.b.receiveTime * 1000) + " " + this.b.content);
            } else if (this.g == null || this.g.isNull("content")) {
                textView.setText(CalendarUtils.b(this.b.receiveTime * 1000) + " " + this.b.title);
            } else {
                textView.setText(CalendarUtils.b(this.b.receiveTime * 1000) + " " + this.g.optString("content"));
            }
        }

        public void c(TextView textView) {
            if (textView != null) {
                textView.setVisibility(8);
            }
        }

        public String c() {
            if (this.b == null) {
                return null;
            }
            return this.b.msgId;
        }
    }

    public IMessage a(MessageRecord messageRecord) {
        DevicePushMessage devicePushMessage = new DevicePushMessage();
        devicePushMessage.a(messageRecord);
        return devicePushMessage;
    }
}
