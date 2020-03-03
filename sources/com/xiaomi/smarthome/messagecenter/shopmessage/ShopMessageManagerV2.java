package com.xiaomi.smarthome.messagecenter.shopmessage;

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
import com.xiaomi.smarthome.messagecenter.IMessageManager;
import com.xiaomi.smarthome.miio.db.record.MessageRecordShop;
import com.xiaomi.smarthome.miio.user.UserMamanger;
import com.xiaomi.youpin.app_sdk.url_dispatch.UrlDispatchManger;
import org.json.JSONException;
import org.json.JSONObject;

public class ShopMessageManagerV2 extends IMessageManager {
    private MessageRecordShop g;

    public class ShopMessageV2 {
        private MessageRecordShop b;
        private String c;
        private String d;

        public void a(XQProgressDialog xQProgressDialog) {
        }

        public void b(XQProgressDialog xQProgressDialog) {
        }

        public boolean d() {
            return false;
        }

        public ShopMessageV2() {
        }

        public void a(MessageRecordShop messageRecordShop, String str, String str2) {
            this.b = messageRecordShop;
            this.c = str;
            this.d = str2;
        }

        public MessageRecordShop a() {
            return this.b;
        }

        public void b() {
            if (this.c.equals("0")) {
                UrlDispatchManger.a().c(this.d);
            } else if (this.c.equals("1")) {
                Bundle bundle = new Bundle();
                bundle.putInt("source", 4);
                OpenApi.a(SmartHomeMainActivity.class, bundle, false, Constants.CALLIGRAPHY_TAG_PRICE);
            } else if (this.c.equals("2")) {
                UrlDispatchManger.a().c(String.format("https://home.mi.com/shop/detail?gid=%s", new Object[]{this.d}));
            } else if (this.c.equals("3")) {
                Bundle bundle2 = new Bundle();
                try {
                    JSONObject jSONObject = new JSONObject(this.d);
                    bundle2.putString("aid", jSONObject.optString("id"));
                    UrlDispatchManger.a().c(String.format("https://home.mi.com/shop/lotteryaddress?aid=%s", new Object[]{jSONObject.optString("id")}));
                } catch (Exception unused) {
                }
            }
        }

        public void a(TextView textView) {
            c(textView);
        }

        public long c() {
            return this.b.receiveTime;
        }

        public void a(SimpleDraweeView simpleDraweeView) {
            if (TextUtils.isEmpty(this.b.img_url) || this.b.img_url.equals("0")) {
                simpleDraweeView.setController((PipelineDraweeController) ((PipelineDraweeControllerBuilder) ((PipelineDraweeControllerBuilder) Fresco.newDraweeControllerBuilder().setImageRequest(ImageRequestBuilder.newBuilderWithSource(CommonUtils.c((int) R.drawable.message_center_icon_gaft_default)).setPostprocessor(new CircleColorLineProcessor(simpleDraweeView.getResources().getColor(R.color.message_icon_color_orange), 1)).build())).setOldController(simpleDraweeView.getController())).build());
                return;
            }
            UserMamanger.a().b(this.b.img_url, simpleDraweeView, new CircleColorLineProcessor(simpleDraweeView.getResources().getColor(R.color.message_icon_color_orange), 1));
        }

        public void b(TextView textView) {
            textView.setText(this.b.title);
        }

        public void c(TextView textView) {
            textView.setText(CalendarUtils.b(this.b.receiveTime * 1000) + " " + this.b.content);
        }

        public void d(TextView textView) {
            if (textView != null) {
                textView.setVisibility(8);
            }
        }

        public String e() {
            if (this.b == null) {
                return null;
            }
            return this.b.msgId;
        }
    }

    public ShopMessageV2 a(MessageRecordShop messageRecordShop) {
        try {
            JSONObject jSONObject = new JSONObject(messageRecordShop.params);
            String optString = jSONObject.optString("type");
            String optString2 = jSONObject.optString("param");
            ShopMessageV2 shopMessageV2 = new ShopMessageV2();
            try {
                shopMessageV2.a(messageRecordShop, optString, optString2);
                return shopMessageV2;
            } catch (JSONException unused) {
                return shopMessageV2;
            }
        } catch (JSONException unused2) {
            return null;
        }
    }
}
