package com.xiaomi.smarthome.messagecenter;

import android.graphics.Color;
import android.text.TextUtils;
import android.widget.TextView;
import com.facebook.drawee.view.SimpleDraweeView;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.application.SHApplication;
import com.xiaomi.smarthome.family.api.RemoteFamilyApi;
import com.xiaomi.smarthome.frame.AsyncCallback;
import com.xiaomi.smarthome.frame.Error;
import com.xiaomi.smarthome.framework.api.model.UserInfo;
import com.xiaomi.smarthome.framework.log.LogUtil;
import com.xiaomi.smarthome.framework.redpoint.ServerTimerManager;
import com.xiaomi.smarthome.homeroom.HomeManager;
import com.xiaomi.smarthome.homeroom.UserInfoManager;
import com.xiaomi.smarthome.library.common.dialog.XQProgressDialog;
import com.xiaomi.smarthome.library.common.imagecache.CircleColorLineProcessor;
import com.xiaomi.smarthome.library.common.util.CalendarUtils;
import com.xiaomi.smarthome.miio.db.record.MessageRecord;
import com.xiaomi.smarthome.miio.user.UserMamanger;
import com.xiaomi.smarthome.stat.STAT;
import java.util.HashSet;
import org.json.JSONObject;

public class HomeShareMessageManager extends IMessageManager {

    public static class HomeShareMessage extends IMessage {

        /* renamed from: a  reason: collision with root package name */
        public static final String f10446a = "invite_tips";
        public static final String b = "invite_request";
        public static final int c = 0;
        public static final int d = 1;
        public static final int e = 2;
        private long h;
        private long i;
        private long j;
        private long k;
        private String l;
        private int m;

        public HomeShareMessage(MessageRecord messageRecord, long j2, long j3, long j4, long j5, String str, int i2) {
            this.f = messageRecord;
            this.h = j2;
            this.i = j3;
            this.j = j4;
            this.k = j5;
            this.l = str;
            this.m = i2;
        }

        public long d() {
            return this.h;
        }

        public long e() {
            return this.i;
        }

        public long a() {
            return this.f.receiveTime;
        }

        public void a(SimpleDraweeView simpleDraweeView) {
            if (!TextUtils.isEmpty(this.f.img_url)) {
                UserMamanger.a().b(this.f.img_url, simpleDraweeView, new CircleColorLineProcessor(simpleDraweeView.getResources().getColor(R.color.black_30_transparent), 1));
                return;
            }
            final long f = f();
            if (f == 0) {
                LogUtil.a("HomeShareMessage", "setMainIcon not valid uid:0");
                return;
            }
            final UserInfo a2 = UserInfoManager.a().a(f);
            if (a2 != null) {
                UserMamanger.a().b(a2.c, simpleDraweeView, new CircleColorLineProcessor(simpleDraweeView.getResources().getColor(R.color.black_30_transparent), 1));
                return;
            }
            simpleDraweeView.setTag(R.id.cb_item_tag, Long.valueOf(f));
            HashSet hashSet = new HashSet();
            hashSet.add(Long.valueOf(f));
            final SimpleDraweeView simpleDraweeView2 = simpleDraweeView;
            UserInfoManager.a().a(hashSet, new UserInfoManager.UpdateCompleteCallBack() {
                public void a() {
                    Object tag = simpleDraweeView2.getTag(R.id.cb_item_tag);
                    if (tag != null && (tag instanceof Long) && tag.equals(Long.valueOf(f))) {
                        UserMamanger.a().b(a2.c, simpleDraweeView2, new CircleColorLineProcessor(simpleDraweeView2.getResources().getColor(R.color.black_30_transparent), 1));
                    }
                }
            });
        }

        public long f() {
            if (TextUtils.equals(this.l, "invite_request")) {
                return this.i;
            }
            if (TextUtils.equals(this.l, "invite_tips")) {
                return this.j;
            }
            return 0;
        }

        public void a(TextView textView) {
            if (!e(textView)) {
                textView.setText(this.f.title);
            }
        }

        public void d(TextView textView) {
            textView.setText(CalendarUtils.b(this.f.receiveTime * 1000) + " " + this.f.title);
        }

        public void b(TextView textView) {
            textView.setText(CalendarUtils.b(this.f.receiveTime * 1000));
        }

        public void c(TextView textView) {
            boolean b2 = b();
            int i2 = R.string.smarthome_to_user_status_waiting;
            if (!b2 && TextUtils.equals(this.l, "invite_request")) {
                if (this.m == 1) {
                    i2 = R.string.smarthome_share_accepted;
                } else if (this.m == 2) {
                    i2 = R.string.smarthome_share_rejected;
                } else if (this.m != 0) {
                    i2 = 0;
                } else if (g()) {
                    i2 = R.string.smarthome_share_expired;
                } else {
                    textView.setTextColor(Color.parseColor("#FF9E00"));
                }
                textView.setText(i2);
            } else if (b() || !TextUtils.equals(this.l, "invite_tips")) {
                textView.setVisibility(8);
            } else {
                if (this.m == 1) {
                    i2 = R.string.smarthome_share_accepted;
                } else if (this.m == 2) {
                    i2 = R.string.smarthome_share_rejected;
                } else if (this.m != 0) {
                    i2 = 0;
                } else if (g()) {
                    i2 = R.string.smarthome_share_expired;
                } else {
                    textView.setTextColor(Color.parseColor("#FF9E00"));
                }
                textView.setText(i2);
            }
        }

        public boolean b() {
            if (TextUtils.equals(this.l, "invite_request") && this.m == 0 && !g()) {
                return true;
            }
            return false;
        }

        public boolean g() {
            return this.k * 1000 < System.currentTimeMillis() + ServerTimerManager.a(SHApplication.getAppContext()).c();
        }

        public void a(final XQProgressDialog xQProgressDialog) {
            STAT.d.aU();
            RemoteFamilyApi.a().a(this.i, this.h, 1, (AsyncCallback<JSONObject, Error>) new AsyncCallback<JSONObject, Error>() {
                /* renamed from: a */
                public void onSuccess(JSONObject jSONObject) {
                    HomeManager.a().a((HomeManager.UpdateHomeCallback) new HomeManager.UpdateHomeCallback() {
                        public void a() {
                            SHApplication.getGlobalHandler().postDelayed(new Runnable() {
                                public void run() {
                                    xQProgressDialog.dismiss();
                                }
                            }, 3000);
                        }
                    });
                }

                public void onFailure(Error error) {
                    xQProgressDialog.dismiss();
                }
            });
        }

        public void b(final XQProgressDialog xQProgressDialog) {
            RemoteFamilyApi.a().a(this.i, this.h, 2, (AsyncCallback<JSONObject, Error>) new AsyncCallback<JSONObject, Error>() {
                /* renamed from: a */
                public void onSuccess(JSONObject jSONObject) {
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
            JSONObject jSONObject = new JSONObject(messageRecord.params);
            return new HomeShareMessage(messageRecord, jSONObject.optLong("home_id", 0), jSONObject.optLong("home_owner", 0), jSONObject.optLong("share_to", 0), jSONObject.optLong("expire", 0), jSONObject.optString("type", ""), jSONObject.optInt("status", 0));
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
