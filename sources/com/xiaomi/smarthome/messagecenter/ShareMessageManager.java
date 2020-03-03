package com.xiaomi.smarthome.messagecenter;

import android.app.Dialog;
import android.text.TextUtils;
import android.widget.TextView;
import android.widget.Toast;
import com.facebook.drawee.view.SimpleDraweeView;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.application.SHApplication;
import com.xiaomi.smarthome.device.Device;
import com.xiaomi.smarthome.device.DeviceFactory;
import com.xiaomi.smarthome.device.SmartHomeDeviceManager;
import com.xiaomi.smarthome.family.api.RemoteFamilyApi;
import com.xiaomi.smarthome.frame.AsyncCallback;
import com.xiaomi.smarthome.frame.Error;
import com.xiaomi.smarthome.framework.statistic.StatHelper;
import com.xiaomi.smarthome.library.common.dialog.XQProgressDialog;
import com.xiaomi.smarthome.library.common.imagecache.CircleColorLineProcessor;
import com.xiaomi.smarthome.library.common.util.CalendarUtils;
import com.xiaomi.smarthome.library.common.util.CommonUtils;
import com.xiaomi.smarthome.library.common.util.StringUtil;
import com.xiaomi.smarthome.miio.db.record.MessageRecord;
import org.json.JSONException;

public class ShareMessageManager extends IMessageManager {
    private static final long g = 259200000;

    public static class ShareMessage extends IMessage {

        /* renamed from: a  reason: collision with root package name */
        private Device f10468a;
        private long b;
        private String c;
        private int d;
        private String e;

        public ShareMessage(String str, long j, String str2, String str3, int i) {
            this.f10468a = DeviceFactory.g(str3, str);
            this.b = j;
            this.c = str3;
            this.d = i;
            this.e = str2;
        }

        public void a(MessageRecord messageRecord) {
            this.f = messageRecord;
        }

        public long a() {
            return this.f.receiveTime;
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

        public Device d() {
            return this.f10468a;
        }

        public void a(SimpleDraweeView simpleDraweeView) {
            if (this.f10468a != null) {
                DeviceFactory.a(this.f10468a, simpleDraweeView, R.drawable.device_list_phone_no, new CircleColorLineProcessor(simpleDraweeView.getResources().getColor(R.color.black_30_transparent), 1), false);
            } else {
                simpleDraweeView.setImageURI(CommonUtils.c((int) R.drawable.device_list_phone_no));
            }
        }

        public void a(TextView textView) {
            if (!e(textView) && this.f10468a != null) {
                textView.setText(this.f10468a.name);
            }
        }

        public void b(TextView textView) {
            if (!k()) {
                textView.setText(CalendarUtils.b(this.f.receiveTime * 1000) + " " + this.f.content);
            } else if (this.g != null && !this.g.isNull("content")) {
                textView.setText(CalendarUtils.b(this.f.receiveTime * 1000) + " " + this.g.optString("content"));
            } else if (this.f10468a != null) {
                textView.setText(CalendarUtils.b(this.f.receiveTime * 1000) + " " + this.f10468a.name);
            }
        }

        public void c(TextView textView) {
            if (b() || !this.e.equals("share_request")) {
                textView.setVisibility(8);
                return;
            }
            textView.setVisibility(0);
            if (this.d == 1) {
                textView.setText(R.string.smarthome_share_accepted);
            } else if (this.d == 2) {
                textView.setText(R.string.smarthome_share_rejected);
            } else {
                textView.setText(R.string.smarthome_share_expired);
            }
        }

        public boolean b() {
            if (g() || this.e == null || this.d > 0 || !this.e.equals("share_request")) {
                return false;
            }
            return true;
        }

        public void a(XQProgressDialog xQProgressDialog) {
            a((Dialog) xQProgressDialog, "accept");
        }

        public void b(XQProgressDialog xQProgressDialog) {
            a((Dialog) xQProgressDialog, "reject");
        }

        public String c() {
            if (this.f == null) {
                return null;
            }
            return this.f.msgId;
        }

        public int e() {
            return (int) this.b;
        }

        /* access modifiers changed from: package-private */
        public void a(final Dialog dialog, String str) {
            RemoteFamilyApi.a().a(SHApplication.getAppContext(), str, this.f.msgId, (int) this.b, (AsyncCallback<Void, Error>) new AsyncCallback<Void, Error>() {
                /* renamed from: a */
                public void onSuccess(Void voidR) {
                    MessageRecord.delete(ShareMessage.this.f.msgId);
                    if (dialog != null) {
                        dialog.dismiss();
                    }
                    SmartHomeDeviceManager.a().p();
                }

                public void onFailure(Error error) {
                    if (dialog != null) {
                        dialog.dismiss();
                    }
                    if (error.a() == -6) {
                        Toast.makeText(SHApplication.getAppContext(), R.string.smarthome_share_expired_toast, 0).show();
                        ShareMessage.this.a(1);
                        return;
                    }
                    Toast.makeText(SHApplication.getAppContext(), R.string.handle_error, 0).show();
                }
            });
            StatHelper.H();
        }

        /* access modifiers changed from: private */
        public void a(long j) {
            if (this.g != null) {
                try {
                    this.g.put("expire_time", j);
                    this.f.params = this.g.toString();
                    this.f.update();
                } catch (JSONException unused) {
                }
            }
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:19:0x004e A[Catch:{ JSONException -> 0x0085 }] */
    /* JADX WARNING: Removed duplicated region for block: B:20:0x0056 A[Catch:{ JSONException -> 0x0085 }] */
    /* JADX WARNING: Removed duplicated region for block: B:23:0x005f A[Catch:{ JSONException -> 0x0085 }] */
    /* JADX WARNING: Removed duplicated region for block: B:24:0x0069 A[Catch:{ JSONException -> 0x0085 }] */
    /* JADX WARNING: Removed duplicated region for block: B:27:0x0072 A[Catch:{ JSONException -> 0x0085 }] */
    /* JADX WARNING: Removed duplicated region for block: B:28:0x007a A[Catch:{ JSONException -> 0x0085 }] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public com.xiaomi.smarthome.messagecenter.IMessage a(com.xiaomi.smarthome.miio.db.record.MessageRecord r12) {
        /*
            r11 = this;
            r0 = 0
            org.json.JSONObject r1 = new org.json.JSONObject     // Catch:{ JSONException -> 0x0085 }
            java.lang.String r2 = r12.params     // Catch:{ JSONException -> 0x0085 }
            r1.<init>(r2)     // Catch:{ JSONException -> 0x0085 }
            r2 = 0
            java.lang.String r4 = "inv_id"
            boolean r4 = r1.has(r4)     // Catch:{ JSONException -> 0x0085 }
            if (r4 == 0) goto L_0x0018
            java.lang.String r2 = "inv_id"
            long r2 = r1.optLong(r2)     // Catch:{ JSONException -> 0x0085 }
        L_0x0018:
            r6 = r2
            java.lang.String r2 = "did"
            boolean r2 = r1.has(r2)     // Catch:{ JSONException -> 0x0085 }
            r3 = 0
            if (r2 == 0) goto L_0x002a
            java.lang.String r2 = "did"
            java.lang.String r2 = r1.optString(r2)     // Catch:{ JSONException -> 0x0085 }
        L_0x0028:
            r9 = r2
            goto L_0x0046
        L_0x002a:
            java.lang.String r2 = "dids"
            boolean r2 = r1.isNull(r2)     // Catch:{ JSONException -> 0x0085 }
            if (r2 != 0) goto L_0x0045
            java.lang.String r2 = "dids"
            org.json.JSONArray r2 = r1.optJSONArray(r2)     // Catch:{ JSONException -> 0x0085 }
            if (r2 == 0) goto L_0x0045
            int r4 = r2.length()     // Catch:{ JSONException -> 0x0085 }
            if (r4 <= 0) goto L_0x0045
            java.lang.String r2 = r2.optString(r3)     // Catch:{ JSONException -> 0x0085 }
            goto L_0x0028
        L_0x0045:
            r9 = r0
        L_0x0046:
            java.lang.String r2 = "status"
            boolean r2 = r1.has(r2)     // Catch:{ JSONException -> 0x0085 }
            if (r2 == 0) goto L_0x0056
            java.lang.String r2 = "status"
            int r2 = r1.optInt(r2)     // Catch:{ JSONException -> 0x0085 }
            r10 = r2
            goto L_0x0057
        L_0x0056:
            r10 = 0
        L_0x0057:
            java.lang.String r2 = "type"
            boolean r2 = r1.has(r2)     // Catch:{ JSONException -> 0x0085 }
            if (r2 == 0) goto L_0x0069
            java.lang.String r2 = "type"
            java.lang.String r3 = ""
            java.lang.String r2 = r1.optString(r2, r3)     // Catch:{ JSONException -> 0x0085 }
            r8 = r2
            goto L_0x006a
        L_0x0069:
            r8 = r0
        L_0x006a:
            java.lang.String r2 = "model"
            boolean r2 = r1.has(r2)     // Catch:{ JSONException -> 0x0085 }
            if (r2 == 0) goto L_0x007a
            java.lang.String r2 = "model"
            java.lang.String r1 = r1.optString(r2)     // Catch:{ JSONException -> 0x0085 }
            r5 = r1
            goto L_0x007b
        L_0x007a:
            r5 = r0
        L_0x007b:
            com.xiaomi.smarthome.messagecenter.ShareMessageManager$ShareMessage r1 = new com.xiaomi.smarthome.messagecenter.ShareMessageManager$ShareMessage     // Catch:{ JSONException -> 0x0085 }
            r4 = r1
            r4.<init>(r5, r6, r8, r9, r10)     // Catch:{ JSONException -> 0x0085 }
            r1.a((com.xiaomi.smarthome.miio.db.record.MessageRecord) r12)     // Catch:{ JSONException -> 0x0086 }
            goto L_0x0086
        L_0x0085:
            r1 = r0
        L_0x0086:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.smarthome.messagecenter.ShareMessageManager.a(com.xiaomi.smarthome.miio.db.record.MessageRecord):com.xiaomi.smarthome.messagecenter.IMessage");
    }
}
