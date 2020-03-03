package com.xiaomi.smarthome.messagecenter;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.TextView;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.backends.pipeline.PipelineDraweeController;
import com.facebook.drawee.backends.pipeline.PipelineDraweeControllerBuilder;
import com.facebook.drawee.view.SimpleDraweeView;
import com.facebook.imagepipeline.request.ImageRequestBuilder;
import com.google.android.exoplayer2.C;
import com.xiaomi.mishopsdk.util.Constants;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.SmartHomeMainActivity;
import com.xiaomi.smarthome.application.SHApplication;
import com.xiaomi.smarthome.device.Device;
import com.xiaomi.smarthome.device.SmartHomeDeviceManager;
import com.xiaomi.smarthome.framework.navigate.PageUrl;
import com.xiaomi.smarthome.framework.openapi.OpenApi;
import com.xiaomi.smarthome.framework.statistic.StatHelper;
import com.xiaomi.smarthome.framework.update.ui.UpdateActivity;
import com.xiaomi.smarthome.library.common.dialog.XQProgressDialog;
import com.xiaomi.smarthome.library.common.imagecache.CircleColorLineProcessor;
import com.xiaomi.smarthome.library.common.util.CalendarUtils;
import com.xiaomi.smarthome.library.common.util.CommonUtils;
import com.xiaomi.smarthome.miio.db.record.MessageRecord;
import com.xiaomi.smarthome.miio.miband.utils.BandConstants;
import com.xiaomi.smarthome.miio.user.UserMamanger;
import org.json.JSONException;
import org.json.JSONObject;

public class CommonMessageManager extends IMessageManager {

    public class CommonMessage extends IMessage {
        private String b;

        public void a(XQProgressDialog xQProgressDialog) {
        }

        public void b(XQProgressDialog xQProgressDialog) {
        }

        public boolean b() {
            return false;
        }

        public CommonMessage() {
        }

        public void a(MessageRecord messageRecord, String str) {
            this.f = messageRecord;
            this.b = str;
        }

        public long a() {
            return this.f.receiveTime;
        }

        public void a(SimpleDraweeView simpleDraweeView) {
            if (TextUtils.isEmpty(this.f.img_url) || this.f.img_url.equals("0")) {
                simpleDraweeView.setController((PipelineDraweeController) ((PipelineDraweeControllerBuilder) ((PipelineDraweeControllerBuilder) Fresco.newDraweeControllerBuilder().setImageRequest(ImageRequestBuilder.newBuilderWithSource(CommonUtils.c((int) R.drawable.message_center_icon_news_default)).setPostprocessor(new CircleColorLineProcessor(simpleDraweeView.getResources().getColor(R.color.message_icon_color_orange), 1)).build())).setOldController(simpleDraweeView.getController())).build());
                return;
            }
            UserMamanger.a().b(this.f.img_url, simpleDraweeView, new CircleColorLineProcessor(simpleDraweeView.getResources().getColor(R.color.message_icon_color_green), 1));
        }

        public void a(TextView textView) {
            if (!e(textView)) {
                textView.setText(this.f.title);
            }
        }

        public void b(TextView textView) {
            if (!k()) {
                textView.setText(CalendarUtils.b(this.f.receiveTime * 1000) + " " + this.f.content);
            } else if (this.g == null || this.g.isNull("content")) {
                textView.setText(CalendarUtils.b(this.f.receiveTime * 1000) + " " + this.f.title);
            } else {
                textView.setText(CalendarUtils.b(this.f.receiveTime * 1000) + " " + this.g.optString("content"));
            }
        }

        public void c(TextView textView) {
            textView.setVisibility(8);
        }

        public String c() {
            if (this.f == null) {
                return null;
            }
            return this.f.msgId;
        }

        public void a(Activity activity) {
            CommonMessageManager.a(this.g);
        }
    }

    public static boolean a(JSONObject jSONObject) {
        if (jSONObject == null) {
            return false;
        }
        StatHelper.a(jSONObject);
        String optString = jSONObject.optString("subtype");
        if (TextUtils.isEmpty(optString)) {
            return false;
        }
        if (TextUtils.equals(optString, "scene_log")) {
            return b(jSONObject);
        }
        if (TextUtils.equals(optString, "device")) {
            return e(jSONObject);
        }
        if (TextUtils.equals(optString, PageUrl.j)) {
            return a();
        }
        if (TextUtils.equals(optString, "device_list")) {
            return c(jSONObject);
        }
        if (TextUtils.equals(optString, "update")) {
            return d(jSONObject);
        }
        return false;
    }

    public static boolean b(JSONObject jSONObject) {
        Bundle bundle = new Bundle();
        bundle.putInt(SmartHomeMainActivity.INTENT_KEY_REQUEST_CODE, 8);
        OpenApi.b(SmartHomeMainActivity.class, bundle, true, Constants.CALLIGRAPHY_TAG_PRICE);
        return true;
    }

    public static boolean a() {
        Bundle bundle = new Bundle();
        bundle.putInt(SmartHomeMainActivity.INTENT_KEY_REQUEST_CODE, 3);
        OpenApi.b(SmartHomeMainActivity.class, bundle, true, Constants.CALLIGRAPHY_TAG_PRICE);
        return true;
    }

    public static boolean c(JSONObject jSONObject) {
        OpenApi.a(SmartHomeMainActivity.class, (Bundle) null, true, Constants.CALLIGRAPHY_TAG_PRICE);
        return false;
    }

    public static boolean d(JSONObject jSONObject) {
        OpenApi.a(UpdateActivity.class, (Bundle) null, true, Constants.CALLIGRAPHY_TAG_PRICE);
        return false;
    }

    public static boolean e(JSONObject jSONObject) {
        Device b;
        String optString = jSONObject.optString("device_id");
        if (TextUtils.isEmpty(optString) || (b = SmartHomeDeviceManager.a().b(optString)) == null || !b.isOnline) {
            return false;
        }
        String optString2 = jSONObject.optString("model");
        if (!TextUtils.isEmpty(optString2) && !TextUtils.equals(optString2, b.model)) {
            return false;
        }
        Intent intent = new Intent();
        intent.addFlags(C.ENCODING_PCM_MU_LAW);
        intent.setAction("android.intent.action.VIEW");
        intent.setPackage(SHApplication.getAppContext().getPackageName());
        intent.setData(Uri.parse(BandConstants.g + optString2 + "?did=" + optString));
        SHApplication.getAppContext().startActivity(intent);
        return true;
    }

    public IMessage a(MessageRecord messageRecord) {
        try {
            String optString = new JSONObject(messageRecord.params).optString("subtype");
            CommonMessage commonMessage = new CommonMessage();
            try {
                commonMessage.a(messageRecord, optString);
                return commonMessage;
            } catch (JSONException unused) {
                return commonMessage;
            }
        } catch (JSONException unused2) {
            return null;
        }
    }
}
