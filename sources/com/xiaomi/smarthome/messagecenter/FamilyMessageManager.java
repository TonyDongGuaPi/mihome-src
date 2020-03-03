package com.xiaomi.smarthome.messagecenter;

import android.text.TextUtils;
import android.widget.TextView;
import com.facebook.drawee.view.SimpleDraweeView;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.application.SHApplication;
import com.xiaomi.smarthome.family.api.RemoteFamilyApi;
import com.xiaomi.smarthome.frame.AsyncCallback;
import com.xiaomi.smarthome.frame.Error;
import com.xiaomi.smarthome.framework.statistic.StatHelper;
import com.xiaomi.smarthome.library.common.dialog.XQProgressDialog;
import com.xiaomi.smarthome.library.common.imagecache.CircleColorLineProcessor;
import com.xiaomi.smarthome.library.common.util.CalendarUtils;
import com.xiaomi.smarthome.library.common.util.StringUtil;
import com.xiaomi.smarthome.miio.db.record.MessageRecord;
import com.xiaomi.smarthome.miio.user.UserMamanger;
import org.json.JSONException;
import org.json.JSONObject;

public class FamilyMessageManager extends IMessageManager {

    public static class FamilyMessage extends IMessage {

        /* renamed from: a  reason: collision with root package name */
        private String f10443a;

        public void a(MessageRecord messageRecord, String str) {
            this.f = messageRecord;
            this.f10443a = str;
        }

        public long a() {
            return this.f.receiveTime;
        }

        public void a(SimpleDraweeView simpleDraweeView) {
            UserMamanger.a().b(this.f.img_url, simpleDraweeView, new CircleColorLineProcessor(simpleDraweeView.getResources().getColor(R.color.black_30_transparent), 1));
        }

        public void a(TextView textView) {
            if (!e(textView)) {
                textView.setText(this.f.title);
            }
        }

        public void d(TextView textView) {
            String str;
            if (!e(textView)) {
                textView.setText(CalendarUtils.b(this.f.receiveTime * 1000) + " " + this.f.title);
            } else if (k() && this.g != null) {
                String optString = this.g.optString("user_name");
                String optString2 = this.g.optString("title");
                if (!TextUtils.isEmpty(optString) && !TextUtils.isEmpty(optString2)) {
                    String a2 = StringUtil.a(textView.getContext(), optString, textView.getTextSize(), textView, 5);
                    if (optString2.contains("%s")) {
                        str = optString2.replace("%s", a2);
                    } else {
                        str = a2 + optString2;
                    }
                    textView.setText(CalendarUtils.b(this.f.receiveTime * 1000) + " " + str);
                } else if (!TextUtils.isEmpty(optString2)) {
                    textView.setText(CalendarUtils.b(this.f.receiveTime * 1000) + " " + optString2);
                }
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
            int i;
            if (b() || !this.f10443a.equals("addRelation")) {
                textView.setVisibility(8);
                return;
            }
            textView.setVisibility(0);
            if (this.f.status == 1) {
                i = R.string.smarthome_share_accepted;
            } else {
                i = this.f.status == 2 ? R.string.smarthome_share_rejected : R.string.smarthome_share_expired;
            }
            textView.setText(i);
        }

        public boolean b() {
            if (g() || this.f10443a == null || this.f.status != 0 || !this.f10443a.equals("addRelation")) {
                return false;
            }
            return true;
        }

        public void a(final XQProgressDialog xQProgressDialog) {
            RemoteFamilyApi.a().c(SHApplication.getAppContext(), this.f.senderUserId, (AsyncCallback<Void, Error>) new AsyncCallback<Void, Error>() {
                /* renamed from: a */
                public void onSuccess(Void voidR) {
                    MessageRecord.delete(FamilyMessage.this.f.msgId);
                    xQProgressDialog.dismiss();
                }

                public void onFailure(Error error) {
                    xQProgressDialog.dismiss();
                }
            });
            StatHelper.G();
        }

        public void b(final XQProgressDialog xQProgressDialog) {
            RemoteFamilyApi.a().d(SHApplication.getAppContext(), this.f.senderUserId, new AsyncCallback<Void, Error>() {
                /* renamed from: a */
                public void onSuccess(Void voidR) {
                    FamilyMessage.this.f.status = 2;
                    FamilyMessage.this.f.update();
                    xQProgressDialog.dismiss();
                }

                public void onFailure(Error error) {
                    xQProgressDialog.dismiss();
                }
            });
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
            String optString = new JSONObject(messageRecord.params).optString("subtype", "");
            FamilyMessage familyMessage = new FamilyMessage();
            try {
                familyMessage.a(messageRecord, optString);
                return familyMessage;
            } catch (JSONException unused) {
                return familyMessage;
            }
        } catch (JSONException unused2) {
            return null;
        }
    }
}
