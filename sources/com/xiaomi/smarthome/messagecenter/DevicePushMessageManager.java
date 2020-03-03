package com.xiaomi.smarthome.messagecenter;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.TextView;
import com.facebook.drawee.view.SimpleDraweeView;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.core.entity.plugin.PluginDownloadTask;
import com.xiaomi.smarthome.core.entity.plugin.PluginError;
import com.xiaomi.smarthome.core.entity.plugin.PluginRecord;
import com.xiaomi.smarthome.device.Device;
import com.xiaomi.smarthome.device.DeviceFactory;
import com.xiaomi.smarthome.frame.Error;
import com.xiaomi.smarthome.frame.core.CoreApi;
import com.xiaomi.smarthome.frame.plugin.PluginApi;
import com.xiaomi.smarthome.frame.plugin.RunningProcess;
import com.xiaomi.smarthome.framework.plugin.mpk.MpkPluginApi;
import com.xiaomi.smarthome.library.common.dialog.XQProgressDialog;
import com.xiaomi.smarthome.library.common.dialog.XQProgressHorizontalDialog;
import com.xiaomi.smarthome.library.common.imagecache.CircleColorLineProcessor;
import com.xiaomi.smarthome.library.common.util.CalendarUtils;
import com.xiaomi.smarthome.library.common.util.CommonUtils;
import com.xiaomi.smarthome.miio.db.record.MessageRecord;
import com.xiaomi.smarthome.miio.user.UserMamanger;
import org.json.JSONException;
import org.json.JSONObject;

public class DevicePushMessageManager extends IMessageManager {
    public static boolean a(String str) {
        return false;
    }

    public class DevicePushMessage extends IMessage {

        /* renamed from: a  reason: collision with root package name */
        public String f10436a;
        public String b;
        private String d;
        private Device e;
        private String h;
        private long i;
        private long j;
        private long k;
        private String l;
        private String m;

        public void a(XQProgressDialog xQProgressDialog) {
        }

        public void b(XQProgressDialog xQProgressDialog) {
        }

        public boolean b() {
            return false;
        }

        public DevicePushMessage() {
        }

        public void a(MessageRecord messageRecord, String str, String str2, String str3, long j2, String str4, long j3, long j4, String str5, String str6) {
            this.e = DeviceFactory.g(str2, str);
            this.f = messageRecord;
            this.f10436a = str;
            this.b = str2;
            this.h = str3;
            this.i = j2;
            this.l = str4;
            this.m = str5;
            this.d = str6;
            this.j = j3;
            this.k = j4;
        }

        public MessageRecord d() {
            return this.f;
        }

        public long e() {
            return this.j;
        }

        public long f() {
            return this.k;
        }

        /* access modifiers changed from: private */
        /* JADX WARNING: Removed duplicated region for block: B:33:0x00b1  */
        /* JADX WARNING: Removed duplicated region for block: B:35:0x00b7  */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public void a(final android.app.Activity r9, boolean r10) {
            /*
                r8 = this;
                super.a((android.app.Activity) r9)
                r0 = 0
                org.json.JSONObject r1 = new org.json.JSONObject     // Catch:{ Exception -> 0x0027 }
                java.lang.String r2 = r8.d     // Catch:{ Exception -> 0x0027 }
                r1.<init>(r2)     // Catch:{ Exception -> 0x0027 }
                java.lang.String r2 = "event"
                java.lang.String r2 = r1.optString(r2)     // Catch:{ Exception -> 0x0027 }
                boolean r3 = android.text.TextUtils.isEmpty(r2)     // Catch:{ Exception -> 0x0027 }
                if (r3 != 0) goto L_0x0028
                java.lang.String r3 = "voicecall"
                boolean r2 = r3.equals(r2)     // Catch:{ Exception -> 0x0027 }
                if (r2 == 0) goto L_0x0028
                java.lang.String r2 = "event"
                java.lang.String r3 = "voicecall_msgcenter"
                r1.put(r2, r3)     // Catch:{ Exception -> 0x0027 }
                goto L_0x0028
            L_0x0027:
                r1 = r0
            L_0x0028:
                java.lang.String r2 = r8.d
                if (r1 == 0) goto L_0x0030
                java.lang.String r2 = r1.toString()
            L_0x0030:
                r1 = 0
                org.json.JSONObject r3 = new org.json.JSONObject     // Catch:{ JSONException -> 0x00a0 }
                r3.<init>(r2)     // Catch:{ JSONException -> 0x00a0 }
                java.lang.String r4 = "did"
                java.lang.String r3 = r3.optString(r4)     // Catch:{ JSONException -> 0x00a0 }
                com.xiaomi.smarthome.device.SmartHomeDeviceManager r4 = com.xiaomi.smarthome.device.SmartHomeDeviceManager.a()     // Catch:{ JSONException -> 0x00a0 }
                com.xiaomi.smarthome.device.Device r3 = r4.b((java.lang.String) r3)     // Catch:{ JSONException -> 0x00a0 }
                if (r3 != 0) goto L_0x00a5
                com.xiaomi.smarthome.homeroom.HomeManager r4 = com.xiaomi.smarthome.homeroom.HomeManager.a()     // Catch:{ JSONException -> 0x009e }
                long r5 = r8.j     // Catch:{ JSONException -> 0x009e }
                java.lang.String r5 = java.lang.String.valueOf(r5)     // Catch:{ JSONException -> 0x009e }
                com.xiaomi.smarthome.homeroom.model.Home r4 = r4.j((java.lang.String) r5)     // Catch:{ JSONException -> 0x009e }
                if (r10 == 0) goto L_0x0094
                if (r4 == 0) goto L_0x0094
                boolean r10 = r4.p()     // Catch:{ JSONException -> 0x009e }
                if (r10 == 0) goto L_0x005f
                goto L_0x0094
            L_0x005f:
                java.lang.StringBuilder r10 = new java.lang.StringBuilder     // Catch:{ JSONException -> 0x009e }
                r10.<init>()     // Catch:{ JSONException -> 0x009e }
                r4 = 2131496183(0x7f0c0cf7, float:1.8615924E38)
                java.lang.String r4 = r9.getString(r4)     // Catch:{ JSONException -> 0x009e }
                r10.append(r4)     // Catch:{ JSONException -> 0x009e }
                r4 = 2131497825(0x7f0c1361, float:1.8619254E38)
                java.lang.String r4 = r9.getString(r4)     // Catch:{ JSONException -> 0x009e }
                r10.append(r4)     // Catch:{ JSONException -> 0x009e }
                java.lang.String r10 = r10.toString()     // Catch:{ JSONException -> 0x009e }
                com.xiaomi.smarthome.library.common.dialog.XQProgressHorizontalDialog r10 = com.xiaomi.smarthome.library.common.dialog.XQProgressHorizontalDialog.b(r9, r10)     // Catch:{ JSONException -> 0x009e }
                r10.show()     // Catch:{ JSONException -> 0x009e }
                com.xiaomi.smarthome.homeroom.homedevicelist.SharedHomeDeviceManager r4 = com.xiaomi.smarthome.homeroom.homedevicelist.SharedHomeDeviceManager.b()     // Catch:{ JSONException -> 0x009e }
                long r5 = r8.e()     // Catch:{ JSONException -> 0x009e }
                com.xiaomi.smarthome.messagecenter.DevicePushMessageManager$DevicePushMessage$1 r7 = new com.xiaomi.smarthome.messagecenter.DevicePushMessageManager$DevicePushMessage$1     // Catch:{ JSONException -> 0x009e }
                r7.<init>(r9, r10)     // Catch:{ JSONException -> 0x009e }
                r4.a((long) r5, (com.xiaomi.smarthome.frame.AsyncCallback) r7)     // Catch:{ JSONException -> 0x009e }
                return
            L_0x0094:
                r10 = 2131494619(0x7f0c06db, float:1.8612752E38)
                com.xiaomi.smarthome.library.common.util.ToastUtil.a((int) r10)     // Catch:{ JSONException -> 0x009e }
                com.xiaomi.smarthome.framework.statistic.StatHelper.b((boolean) r1)     // Catch:{ JSONException -> 0x009e }
                return
            L_0x009e:
                r10 = move-exception
                goto L_0x00a2
            L_0x00a0:
                r10 = move-exception
                r3 = r0
            L_0x00a2:
                r10.printStackTrace()
            L_0x00a5:
                com.xiaomi.smarthome.frame.core.CoreApi r10 = com.xiaomi.smarthome.frame.core.CoreApi.a()
                java.lang.String r4 = r8.f10436a
                com.xiaomi.smarthome.core.entity.plugin.PluginRecord r10 = r10.d((java.lang.String) r4)
                if (r10 != 0) goto L_0x00b7
                java.lang.String r9 = ""
                com.xiaomi.smarthome.scene.push.ScenePushListener.a(r9, r2, r1, r0)
                return
            L_0x00b7:
                boolean r4 = r10.k()
                if (r4 != 0) goto L_0x00c7
                boolean r4 = r10.l()
                if (r4 != 0) goto L_0x00c7
                com.xiaomi.smarthome.messagecenter.DevicePushMessageManager.a(r9, r2, r10, r3, r0)
                goto L_0x00cc
            L_0x00c7:
                java.lang.String r9 = ""
                com.xiaomi.smarthome.scene.push.ScenePushListener.a(r9, r2, r1, r0)
            L_0x00cc:
                r9 = 1
                com.xiaomi.smarthome.framework.statistic.StatHelper.b((boolean) r9)
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.smarthome.messagecenter.DevicePushMessageManager.DevicePushMessage.a(android.app.Activity, boolean):void");
        }

        public void a(Activity activity) {
            a(activity, true);
        }

        public long a() {
            return this.f.receiveTime;
        }

        public void a(SimpleDraweeView simpleDraweeView) {
            if (this.e != null) {
                DeviceFactory.a(this.e, simpleDraweeView, R.drawable.device_list_phone_no, new CircleColorLineProcessor(simpleDraweeView.getResources().getColor(R.color.black_30_transparent), 1), false);
            } else if (TextUtils.isEmpty(this.f.img_url) || this.f.img_url.equals("0")) {
                simpleDraweeView.setImageURI(CommonUtils.c((int) R.drawable.device_list_phone_no));
            } else {
                UserMamanger.a().b(this.f.img_url, simpleDraweeView, new CircleColorLineProcessor(simpleDraweeView.getResources().getColor(R.color.black_30_transparent), 1));
            }
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

    public static void a(Activity activity, String str, PluginRecord pluginRecord, Device device, PluginApi.SendMessageCallback sendMessageCallback) {
        Intent intent;
        Activity activity2 = activity;
        final XQProgressHorizontalDialog b = XQProgressHorizontalDialog.b(activity, activity.getString(R.string.plugin_downloading) + pluginRecord.p() + activity.getString(R.string.plugin));
        final PluginDownloadTask pluginDownloadTask = new PluginDownloadTask();
        final boolean z = !pluginRecord.l() && !pluginRecord.k();
        try {
            JSONObject jSONObject = new JSONObject(str);
            intent = MpkPluginApi.getIntent(jSONObject.optString("did"), jSONObject.optString("event"), jSONObject.optLong("time"), jSONObject.optString("extra"), false);
        } catch (JSONException e) {
            e.printStackTrace();
            intent = null;
        }
        final Activity activity3 = activity;
        final PluginRecord pluginRecord2 = pluginRecord;
        final PluginApi.SendMessageCallback sendMessageCallback2 = sendMessageCallback;
        PluginApi.getInstance().sendMessage(activity, pluginRecord, 2, intent, device.newDeviceStat(), (RunningProcess) null, false, new PluginApi.SendMessageCallback() {
            public void onDownloadStart(PluginRecord pluginRecord, PluginDownloadTask pluginDownloadTask) {
                pluginDownloadTask.a(pluginDownloadTask);
                if (!activity3.isFinishing()) {
                    if (Build.VERSION.SDK_INT < 17 || !activity3.isDestroyed()) {
                        if (b != null) {
                            b.a(100, 0);
                            b.c();
                            b.setCancelable(true);
                            b.show();
                            b.setOnCancelListener(new DialogInterface.OnCancelListener() {
                                public void onCancel(DialogInterface dialogInterface) {
                                    CoreApi.a().a(pluginRecord2.o(), pluginDownloadTask, (CoreApi.CancelPluginDownloadCallback) null);
                                }
                            });
                        }
                        if (sendMessageCallback2 != null) {
                            sendMessageCallback2.onDownloadStart(pluginRecord, pluginDownloadTask);
                        }
                    }
                }
            }

            public void onDownloadProgress(PluginRecord pluginRecord, float f2) {
                if (z) {
                    int i = (int) (100.0f * f2);
                    if (i >= 99) {
                        i = 99;
                    }
                    if (b != null) {
                        b.a(100, i);
                    }
                } else if (b != null) {
                    b.a(100, (int) (100.0f * f2));
                }
                if (sendMessageCallback2 != null) {
                    sendMessageCallback2.onDownloadProgress(pluginRecord, f2);
                }
            }

            public void onDownloadSuccess(PluginRecord pluginRecord) {
                if (!z && b != null) {
                    b.dismiss();
                }
                if (sendMessageCallback2 != null) {
                    sendMessageCallback2.onDownloadSuccess(pluginRecord);
                }
            }

            public void onDownloadFailure(PluginError pluginError) {
                if (!z && b != null) {
                    b.dismiss();
                }
                if (sendMessageCallback2 != null) {
                    sendMessageCallback2.onDownloadFailure(pluginError);
                }
            }

            public void onDownloadCancel() {
                if (!z && b != null) {
                    b.dismiss();
                }
                if (sendMessageCallback2 != null) {
                    sendMessageCallback2.onDownloadCancel();
                }
            }

            public void onSendSuccess(Bundle bundle) {
                if (z && b != null) {
                    b.dismiss();
                }
                if (sendMessageCallback2 != null) {
                    sendMessageCallback2.onSendSuccess(bundle);
                }
            }

            public void onSendFailure(Error error) {
                if (z && b != null) {
                    b.dismiss();
                }
                if (sendMessageCallback2 != null) {
                    sendMessageCallback2.onSendFailure(error);
                }
            }

            public void onSendCancel() {
                if (z && b != null) {
                    b.dismiss();
                }
                if (sendMessageCallback2 != null) {
                    sendMessageCallback2.onSendCancel();
                }
            }
        });
    }

    public IMessage a(MessageRecord messageRecord) {
        MessageRecord messageRecord2 = messageRecord;
        try {
            String optString = new JSONObject(messageRecord2.params).optString("body");
            if (optString == null) {
                return null;
            }
            JSONObject jSONObject = new JSONObject(optString);
            String optString2 = jSONObject.optString("model");
            String optString3 = jSONObject.optString("did");
            String optString4 = jSONObject.optString("event");
            long optLong = jSONObject.optLong("time");
            String optString5 = jSONObject.optString("extra");
            long j = messageRecord2.homeId;
            long j2 = messageRecord2.homeOwner;
            try {
                DevicePushMessage devicePushMessage = new DevicePushMessage();
                try {
                    devicePushMessage.a(messageRecord, optString2, optString3, optString4, optLong, optString5, j, j2, messageRecord2.params, optString);
                    return devicePushMessage;
                } catch (JSONException unused) {
                    return devicePushMessage;
                }
            } catch (JSONException unused2) {
                return null;
            }
        } catch (JSONException unused3) {
            return null;
        }
    }
}
