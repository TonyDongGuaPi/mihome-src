package com.xiaomi.smarthome.messagecenter;

import android.app.Activity;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.TextView;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.backends.pipeline.PipelineDraweeController;
import com.facebook.drawee.backends.pipeline.PipelineDraweeControllerBuilder;
import com.facebook.drawee.view.SimpleDraweeView;
import com.facebook.imagepipeline.request.ImageRequestBuilder;
import com.xiaomi.mishopsdk.util.Constants;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.SmartHomeMainActivity;
import com.xiaomi.smarthome.framework.openapi.OpenApi;
import com.xiaomi.smarthome.library.common.dialog.XQProgressDialog;
import com.xiaomi.smarthome.library.common.imagecache.CircleColorLineProcessor;
import com.xiaomi.smarthome.library.common.util.CalendarUtils;
import com.xiaomi.smarthome.library.common.util.CommonUtils;
import com.xiaomi.smarthome.miio.db.record.MessageRecord;
import com.xiaomi.smarthome.miio.user.UserMamanger;
import com.xiaomi.youpin.app_sdk.url_dispatch.UrlDispatchManger;
import org.json.JSONException;
import org.json.JSONObject;

public class ShopMessageManager extends IMessageManager {

    public class ShopMessage extends IMessage {
        private String b;
        private String c;

        public void a(XQProgressDialog xQProgressDialog) {
        }

        public void b(XQProgressDialog xQProgressDialog) {
        }

        public boolean b() {
            return false;
        }

        public ShopMessage() {
        }

        public void a(MessageRecord messageRecord, String str, String str2) {
            this.f = messageRecord;
            this.b = str;
            this.c = str2;
        }

        public MessageRecord d() {
            return this.f;
        }

        public void a(Activity activity) {
            if (this.b.equals("0")) {
                UrlDispatchManger.a().c(this.c);
            } else if (this.b.equals("1")) {
                Bundle bundle = new Bundle();
                bundle.putInt("source", 4);
                OpenApi.a(SmartHomeMainActivity.class, bundle, false, Constants.CALLIGRAPHY_TAG_PRICE);
            } else if (this.b.equals("2")) {
                UrlDispatchManger.a().c(String.format("https://home.mi.com/shop/detail?gid=%s", new Object[]{this.c}));
            } else if (this.b.equals("3")) {
                Bundle bundle2 = new Bundle();
                try {
                    JSONObject jSONObject = new JSONObject(this.c);
                    bundle2.putString("aid", jSONObject.optString("id"));
                    UrlDispatchManger.a().c(String.format("https://home.mi.com/shop/lotteryaddress?aid=%s", new Object[]{jSONObject.optString("id")}));
                } catch (Exception unused) {
                }
            } else {
                UrlDispatchManger.a().c("https://home.mi.com/shop/msglist");
            }
        }

        public long a() {
            return this.f.receiveTime;
        }

        public void a(SimpleDraweeView simpleDraweeView) {
            if (TextUtils.isEmpty(this.f.img_url) || this.f.img_url.equals("0")) {
                simpleDraweeView.setController((PipelineDraweeController) ((PipelineDraweeControllerBuilder) ((PipelineDraweeControllerBuilder) Fresco.newDraweeControllerBuilder().setImageRequest(ImageRequestBuilder.newBuilderWithSource(CommonUtils.c((int) R.drawable.message_center_icon_gaft_default)).setPostprocessor(new CircleColorLineProcessor(simpleDraweeView.getResources().getColor(R.color.message_icon_color_orange), 1)).build())).setOldController(simpleDraweeView.getController())).build());
                return;
            }
            UserMamanger.a().b(this.f.img_url, simpleDraweeView, new CircleColorLineProcessor(simpleDraweeView.getResources().getColor(R.color.message_icon_color_orange), 1));
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
            if (textView != null) {
                textView.setVisibility(8);
            }
        }

        public String c() {
            if (this.f == null) {
                return null;
            }
            return this.f.msgId;
        }
    }

    public IMessage a(MessageRecord messageRecord) {
        try {
            JSONObject jSONObject = new JSONObject(messageRecord.params);
            String optString = jSONObject.optString("type");
            String optString2 = jSONObject.optString("param");
            ShopMessage shopMessage = new ShopMessage();
            try {
                shopMessage.a(messageRecord, optString, optString2);
                return shopMessage;
            } catch (JSONException unused) {
                return shopMessage;
            }
        } catch (JSONException unused2) {
            return null;
        }
    }
}
