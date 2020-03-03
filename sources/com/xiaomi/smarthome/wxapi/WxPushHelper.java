package com.xiaomi.smarthome.wxapi;

import android.app.Activity;
import android.app.Dialog;
import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.InsetDrawable;
import android.os.AsyncTask;
import android.os.Environment;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.mi.global.bbs.utils.Constants;
import com.tencent.mm.sdk.modelbiz.JumpToBizProfile;
import com.tencent.mm.sdk.openapi.IWXAPI;
import com.tencent.mm.sdk.openapi.WXAPIFactory;
import com.unionpay.tsmservice.mi.data.Constant;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.device.api.Callback;
import com.xiaomi.smarthome.device.api.Parser;
import com.xiaomi.smarthome.device.api.XmPluginHostApi;
import com.xiaomi.smarthome.frame.FrameManager;
import com.xiaomi.smarthome.frame.plugin.host.PluginHostApi;
import com.xiaomi.smarthome.globalsetting.GlobalSetting;
import com.xiaomi.smarthome.library.common.widget.nestscroll.UIUtils;
import com.xiaomi.smarthome.smartconfig.SmartConfigDataProvider;
import java.io.File;
import java.net.URL;
import java.net.URLConnection;
import java.util.Locale;
import org.aspectj.runtime.internal.AroundClosure;
import org.json.JSONException;
import org.json.JSONObject;

public class WxPushHelper {

    /* renamed from: a  reason: collision with root package name */
    public static final String f22974a = "connect.camera.";
    public static final String b = "/wx/app/v1/status";
    public static final String c = "/wx/app/v1/getTicket";
    public static final String d = "/wx/app/v1/put/pushSwitch";
    public static final String e = "/wx/app/v1/get/pushSwitch";
    public static final String f = "GET";
    public static final String g = "POST";
    public static final int h = -9999;
    public static final File i = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM), Constant.DEVICE_XIAOMI);
    public static final File j = new File(i, "local");
    private static final String k = "WxPushHelper";
    /* access modifiers changed from: private */
    public static boolean l = false;
    private static WxPushHelper m;
    private static IWXAPI n;
    private Bitmap o = null;
    /* access modifiers changed from: private */
    public Dialog p = null;
    private Dialog q;
    /* access modifiers changed from: private */
    public boolean r;
    /* access modifiers changed from: private */
    public boolean s = false;
    /* access modifiers changed from: private */
    public AsyncTask<String, Void, Integer> t;

    private WxPushHelper() {
    }

    public static WxPushHelper a() {
        if (m == null) {
            synchronized (WxPushHelper.class) {
                if (m == null) {
                    m = new WxPushHelper();
                }
            }
        }
        return m;
    }

    public void a(Activity activity, String str, String str2, boolean z, int i2, Callback<Boolean> callback) {
        final Activity activity2 = activity;
        final Callback<Boolean> callback2 = callback;
        final int i3 = i2;
        final String str3 = str;
        final String str4 = str2;
        final boolean z2 = z;
        String str5 = str;
        String str6 = str2;
        a().a(str, str2, (Callback<JSONObject>) new Callback<JSONObject>() {
            /* renamed from: a */
            public void onSuccess(JSONObject jSONObject) {
                if (jSONObject != null) {
                    try {
                        JSONObject jSONObject2 = jSONObject.getJSONObject("data");
                        if (jSONObject2 != null) {
                            boolean optBoolean = jSONObject2.optBoolean("binding");
                            boolean optBoolean2 = jSONObject2.optBoolean(Constants.TitleMenu.MENU_FOLLOW);
                            if (!optBoolean) {
                                if (!WxPushHelper.a().a(activity2.getApplicationContext()).isWXAppInstalled()) {
                                    Toast.makeText(activity2, activity2.getApplicationContext().getText(R.string.wx_not_installed), 0).show();
                                    if (callback2 != null) {
                                        callback2.onFailure(-9999, "WeiXin is not installed!");
                                        return;
                                    }
                                    return;
                                }
                                final Dialog dialog = new Dialog(activity2);
                                View inflate = LayoutInflater.from(activity2.getApplicationContext()).inflate(R.layout.mjwx_bind_alert_dialog, (ViewGroup) null);
                                FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(-1, -2, 80);
                                layoutParams.setMargins(UIUtils.a(8), UIUtils.a(8), UIUtils.a(8), UIUtils.a(8));
                                inflate.setLayoutParams(layoutParams);
                                ((TextView) inflate.findViewById(R.id.message)).setText(R.string.wx_push_tip);
                                Button button = (Button) inflate.findViewById(R.id.btnPositive);
                                button.setText(activity2.getApplicationContext().getString(R.string.wx_push_bind));
                                button.setOnClickListener(new View.OnClickListener() {
                                    public void onClick(View view) {
                                        FrameManager.b().k().openWxBindActivity(activity2, i3);
                                        boolean unused = WxPushHelper.l = true;
                                        dialog.dismiss();
                                    }
                                });
                                Button button2 = (Button) inflate.findViewById(R.id.btnNegative);
                                button2.setText(activity2.getApplicationContext().getString(R.string.cancel));
                                button2.setOnClickListener(new View.OnClickListener() {
                                    public void onClick(View view) {
                                        dialog.dismiss();
                                    }
                                });
                                dialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
                                    public void onDismiss(DialogInterface dialogInterface) {
                                        if (!WxPushHelper.l && callback2 != null) {
                                            callback2.onFailure(-9999, "wx mj bind cancelled by user!");
                                        }
                                        boolean unused = WxPushHelper.l = false;
                                    }
                                });
                                dialog.setContentView(inflate);
                                Window window = dialog.getWindow();
                                if (window != null) {
                                    window.setGravity(80);
                                    window.setBackgroundDrawable(new InsetDrawable(new ColorDrawable(0), 0, 0, 0, 0));
                                    WindowManager.LayoutParams attributes = window.getAttributes();
                                    attributes.width = -1;
                                    attributes.height = -2;
                                    window.setAttributes(attributes);
                                }
                                dialog.show();
                            } else if (!optBoolean2) {
                                WxPushHelper.a().a(activity2, str3, str4, (Callback<Boolean>) callback2);
                            } else {
                                WxPushHelper.a().a(str3, str4, z2, (Callback<Boolean>) callback2);
                            }
                        } else if (callback2 != null) {
                            callback2.onFailure(-1, "Get wx status data is NULL!");
                        }
                    } catch (JSONException e2) {
                        e2.printStackTrace();
                    }
                }
            }

            public void onFailure(int i, String str) {
                if (callback2 != null) {
                    callback2.onFailure(i, str);
                }
            }
        });
    }

    public void a(String str, String str2, final Callback<JSONObject> callback) {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("did", str2);
            jSONObject.put("region", "DEFAULT");
        } catch (JSONException e2) {
            e2.printStackTrace();
        }
        PluginHostApi.instance().callSmartHomeApi(str, f22974a, b, "GET", jSONObject, new Callback<JSONObject>() {
            /* renamed from: a */
            public void onSuccess(JSONObject jSONObject) {
                if (callback != null) {
                    callback.onSuccess(jSONObject);
                }
            }

            public void onFailure(int i, String str) {
                Log.e(WxPushHelper.k, "Get wx status failed! Error: " + i + ", Info: " + str);
                if (callback != null) {
                    callback.onFailure(i, str);
                }
            }
        }, Parser.DEFAULT_PARSER);
    }

    public void a(final Activity activity, String str, final String str2, final Callback<Boolean> callback) {
        if (!a(activity.getApplicationContext()).isWXAppInstalled()) {
            Toast.makeText(activity, activity.getApplicationContext().getText(R.string.wx_not_installed), 0).show();
            if (callback != null) {
                callback.onFailure(-9999, "WeiXin is not installed!");
                return;
            }
            return;
        }
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("did", str2);
            jSONObject.put("region", "DEFAULT");
        } catch (JSONException e2) {
            e2.printStackTrace();
        }
        PluginHostApi.instance().callSmartHomeApi(str, f22974a, c, "GET", jSONObject, new Callback<JSONObject>() {
            /* renamed from: a */
            public void onSuccess(JSONObject jSONObject) {
                String str;
                try {
                    str = jSONObject.getJSONObject("data").optString(SmartConfigDataProvider.l);
                } catch (JSONException e) {
                    e.printStackTrace();
                    str = "";
                }
                WxPushHelper.this.b(activity, str2, str, callback);
            }

            public void onFailure(int i, String str) {
                Log.e(WxPushHelper.k, "Get wx ticket failed! Error: " + i + ", Info: " + str);
                if (callback != null) {
                    callback.onFailure(i, str);
                }
            }
        }, Parser.DEFAULT_PARSER);
    }

    public void a(String str, String str2, boolean z, final Callback<Boolean> callback) {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("did", str2);
            jSONObject.put("open", z);
            jSONObject.put("region", "DEFAULT");
        } catch (JSONException e2) {
            e2.printStackTrace();
        }
        PluginHostApi.instance().callSmartHomeApi(str, f22974a, d, "POST", jSONObject, new Callback<JSONObject>() {
            /* renamed from: a */
            public void onSuccess(JSONObject jSONObject) {
                if (callback != null) {
                    callback.onSuccess(true);
                }
                Log.e(WxPushHelper.k, " set WX push onSuccess ");
            }

            public void onFailure(int i, String str) {
                Log.e(WxPushHelper.k, " set WX push error " + i + " info " + str);
                if (callback != null) {
                    callback.onFailure(i, str);
                }
            }
        }, Parser.DEFAULT_PARSER);
    }

    public void b(String str, String str2, final Callback<Boolean> callback) {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("did", str2);
            jSONObject.put("region", "DEFAULT");
        } catch (JSONException e2) {
            e2.printStackTrace();
        }
        PluginHostApi.instance().callSmartHomeApi(str, f22974a, e, "GET", jSONObject, new Callback<JSONObject>() {
            /* renamed from: a */
            public void onSuccess(JSONObject jSONObject) {
                boolean z;
                JSONException e;
                if (callback != null) {
                    try {
                        JSONObject jSONObject2 = jSONObject.getJSONObject("data");
                        if (jSONObject2 != null) {
                            z = jSONObject2.optBoolean("pushSwitch", false);
                            try {
                                Log.e(WxPushHelper.k, "Get wx push status = " + z);
                            } catch (JSONException e2) {
                                e = e2;
                            }
                        } else {
                            z = false;
                        }
                    } catch (JSONException e3) {
                        e = e3;
                        z = false;
                        e.printStackTrace();
                        callback.onSuccess(Boolean.valueOf(z));
                    }
                    callback.onSuccess(Boolean.valueOf(z));
                }
            }

            public void onFailure(int i, String str) {
                Log.e(WxPushHelper.k, "Get wx push error " + i + " info " + str);
                if (callback != null) {
                    callback.onFailure(i, str);
                }
            }
        }, Parser.DEFAULT_PARSER);
    }

    public IWXAPI a(Context context) {
        if (n == null) {
            n = WXAPIFactory.createWXAPI(context, GlobalSetting.e, true);
            n.registerApp(GlobalSetting.e);
        }
        return n;
    }

    /* access modifiers changed from: private */
    public void b(final Activity activity, final String str, String str2, final Callback<Boolean> callback) {
        this.t = new AsyncTask<String, Void, Integer>() {

            /* renamed from: com.xiaomi.smarthome.wxapi.WxPushHelper$6$AjcClosure1 */
            public class AjcClosure1 extends AroundClosure {
                public AjcClosure1(Object[] objArr) {
                    super(objArr);
                }

                public Object run(Object[] objArr) {
                    Object[] objArr2 = this.state;
                    return AnonymousClass6.a((AnonymousClass6) objArr2[0], (URL) objArr2[1]);
                }
            }

            /* access modifiers changed from: protected */
            /* JADX WARNING: Removed duplicated region for block: B:40:0x00f1 A[SYNTHETIC, Splitter:B:40:0x00f1] */
            /* JADX WARNING: Removed duplicated region for block: B:49:0x010d A[SYNTHETIC, Splitter:B:49:0x010d] */
            /* JADX WARNING: Removed duplicated region for block: B:55:0x0118 A[SYNTHETIC, Splitter:B:55:0x0118] */
            /* JADX WARNING: Unknown top exception splitter block from list: {B:46:0x00fb=Splitter:B:46:0x00fb, B:37:0x00df=Splitter:B:37:0x00df} */
            /* renamed from: a */
            /* Code decompiled incorrectly, please refer to instructions dump. */
            public java.lang.Integer doInBackground(java.lang.String... r10) {
                /*
                    r9 = this;
                    r0 = 0
                    r10 = r10[r0]
                    java.lang.StringBuilder r1 = new java.lang.StringBuilder
                    r1.<init>()
                    java.lang.String r2 = "https://mp.weixin.qq.com/cgi-bin/showqrcode?ticket="
                    r1.append(r2)
                    java.lang.String r10 = java.net.URLEncoder.encode(r10)
                    r1.append(r10)
                    java.lang.String r10 = r1.toString()
                    com.xiaomi.smarthome.wxapi.WxPushHelper r1 = com.xiaomi.smarthome.wxapi.WxPushHelper.this
                    java.lang.String r2 = r3
                    java.lang.String r1 = r1.a((java.lang.String) r2)
                    boolean r2 = android.text.TextUtils.isEmpty(r1)
                    if (r2 == 0) goto L_0x002c
                    r10 = -1
                    java.lang.Integer r10 = java.lang.Integer.valueOf(r10)
                    return r10
                L_0x002c:
                    java.lang.String r2 = "WxPushHelper"
                    java.lang.StringBuilder r3 = new java.lang.StringBuilder
                    r3.<init>()
                    java.lang.String r4 = "WxQrcode pic file path: "
                    r3.append(r4)
                    r3.append(r1)
                    java.lang.String r3 = r3.toString()
                    android.util.Log.e(r2, r3)
                    r2 = 0
                    r3 = 1024(0x400, float:1.435E-42)
                    r4 = -2
                    byte[] r3 = new byte[r3]     // Catch:{ MalformedURLException -> 0x00fa, IOException -> 0x00de }
                    java.io.FileOutputStream r5 = new java.io.FileOutputStream     // Catch:{ MalformedURLException -> 0x00fa, IOException -> 0x00de }
                    r5.<init>(r1)     // Catch:{ MalformedURLException -> 0x00fa, IOException -> 0x00de }
                    java.net.URL r2 = new java.net.URL     // Catch:{ MalformedURLException -> 0x00d8, IOException -> 0x00d5, all -> 0x00d3 }
                    r2.<init>(r10)     // Catch:{ MalformedURLException -> 0x00d8, IOException -> 0x00d5, all -> 0x00d3 }
                    com.xiaomi.miot.support.monitor.aop.trace.TraceNetTrafficMonitor r10 = com.xiaomi.miot.support.monitor.aop.trace.TraceNetTrafficMonitor.b()     // Catch:{ MalformedURLException -> 0x00d8, IOException -> 0x00d5, all -> 0x00d3 }
                    java.net.URLConnection r10 = r10.b(r2)     // Catch:{ MalformedURLException -> 0x00d8, IOException -> 0x00d5, all -> 0x00d3 }
                    java.net.HttpURLConnection r10 = (java.net.HttpURLConnection) r10     // Catch:{ MalformedURLException -> 0x00d8, IOException -> 0x00d5, all -> 0x00d3 }
                    r2 = 5000(0x1388, float:7.006E-42)
                    r10.setConnectTimeout(r2)     // Catch:{ MalformedURLException -> 0x00d8, IOException -> 0x00d5, all -> 0x00d3 }
                    r10.setReadTimeout(r2)     // Catch:{ MalformedURLException -> 0x00d8, IOException -> 0x00d5, all -> 0x00d3 }
                    java.lang.String r2 = "connection"
                    java.lang.String r6 = "Keep-Alive"
                    r10.setRequestProperty(r2, r6)     // Catch:{ MalformedURLException -> 0x00d8, IOException -> 0x00d5, all -> 0x00d3 }
                    r10.connect()     // Catch:{ MalformedURLException -> 0x00d8, IOException -> 0x00d5, all -> 0x00d3 }
                    int r2 = r10.getResponseCode()     // Catch:{ MalformedURLException -> 0x00d8, IOException -> 0x00d5, all -> 0x00d3 }
                    r6 = 200(0xc8, float:2.8E-43)
                    if (r2 == r6) goto L_0x0083
                    java.lang.Integer r10 = java.lang.Integer.valueOf(r2)     // Catch:{ MalformedURLException -> 0x00d8, IOException -> 0x00d5, all -> 0x00d3 }
                    r5.close()     // Catch:{ IOException -> 0x007e }
                    goto L_0x0082
                L_0x007e:
                    r0 = move-exception
                    r0.printStackTrace()
                L_0x0082:
                    return r10
                L_0x0083:
                    java.lang.String r6 = "WxPushHelper"
                    java.lang.StringBuilder r7 = new java.lang.StringBuilder     // Catch:{ MalformedURLException -> 0x00d8, IOException -> 0x00d5, all -> 0x00d3 }
                    r7.<init>()     // Catch:{ MalformedURLException -> 0x00d8, IOException -> 0x00d5, all -> 0x00d3 }
                    java.lang.String r8 = "qr code: "
                    r7.append(r8)     // Catch:{ MalformedURLException -> 0x00d8, IOException -> 0x00d5, all -> 0x00d3 }
                    r7.append(r2)     // Catch:{ MalformedURLException -> 0x00d8, IOException -> 0x00d5, all -> 0x00d3 }
                    java.lang.String r2 = r7.toString()     // Catch:{ MalformedURLException -> 0x00d8, IOException -> 0x00d5, all -> 0x00d3 }
                    android.util.Log.e(r6, r2)     // Catch:{ MalformedURLException -> 0x00d8, IOException -> 0x00d5, all -> 0x00d3 }
                    java.io.InputStream r10 = r10.getInputStream()     // Catch:{ MalformedURLException -> 0x00d8, IOException -> 0x00d5, all -> 0x00d3 }
                L_0x009d:
                    int r2 = r3.length     // Catch:{ MalformedURLException -> 0x00d8, IOException -> 0x00d5, all -> 0x00d3 }
                    int r2 = r10.read(r3, r0, r2)     // Catch:{ MalformedURLException -> 0x00d8, IOException -> 0x00d5, all -> 0x00d3 }
                    if (r2 < 0) goto L_0x00a8
                    r5.write(r3, r0, r2)     // Catch:{ MalformedURLException -> 0x00d8, IOException -> 0x00d5, all -> 0x00d3 }
                    goto L_0x009d
                L_0x00a8:
                    r5.flush()     // Catch:{ MalformedURLException -> 0x00d8, IOException -> 0x00d5, all -> 0x00d3 }
                    java.io.File r10 = new java.io.File     // Catch:{ MalformedURLException -> 0x00d8, IOException -> 0x00d5, all -> 0x00d3 }
                    r10.<init>(r1)     // Catch:{ MalformedURLException -> 0x00d8, IOException -> 0x00d5, all -> 0x00d3 }
                    android.net.Uri r10 = android.net.Uri.fromFile(r10)     // Catch:{ MalformedURLException -> 0x00d8, IOException -> 0x00d5, all -> 0x00d3 }
                    android.content.Intent r1 = new android.content.Intent     // Catch:{ MalformedURLException -> 0x00d8, IOException -> 0x00d5, all -> 0x00d3 }
                    java.lang.String r2 = "android.intent.action.MEDIA_SCANNER_SCAN_FILE"
                    r1.<init>(r2, r10)     // Catch:{ MalformedURLException -> 0x00d8, IOException -> 0x00d5, all -> 0x00d3 }
                    com.xiaomi.smarthome.device.api.XmPluginHostApi r10 = com.xiaomi.smarthome.frame.plugin.host.PluginHostApi.instance()     // Catch:{ MalformedURLException -> 0x00d8, IOException -> 0x00d5, all -> 0x00d3 }
                    android.content.Context r10 = r10.context()     // Catch:{ MalformedURLException -> 0x00d8, IOException -> 0x00d5, all -> 0x00d3 }
                    r10.sendBroadcast(r1)     // Catch:{ MalformedURLException -> 0x00d8, IOException -> 0x00d5, all -> 0x00d3 }
                    r5.close()     // Catch:{ IOException -> 0x00ca }
                    goto L_0x00ce
                L_0x00ca:
                    r10 = move-exception
                    r10.printStackTrace()
                L_0x00ce:
                    java.lang.Integer r10 = java.lang.Integer.valueOf(r0)
                    return r10
                L_0x00d3:
                    r10 = move-exception
                    goto L_0x0116
                L_0x00d5:
                    r10 = move-exception
                    r2 = r5
                    goto L_0x00df
                L_0x00d8:
                    r10 = move-exception
                    r2 = r5
                    goto L_0x00fb
                L_0x00db:
                    r10 = move-exception
                    r5 = r2
                    goto L_0x0116
                L_0x00de:
                    r10 = move-exception
                L_0x00df:
                    r10.printStackTrace()     // Catch:{ all -> 0x00db }
                    java.lang.String r0 = "WxPushHelper"
                    java.lang.String r10 = r10.toString()     // Catch:{ all -> 0x00db }
                    android.util.Log.e(r0, r10)     // Catch:{ all -> 0x00db }
                    java.lang.Integer r10 = java.lang.Integer.valueOf(r4)     // Catch:{ all -> 0x00db }
                    if (r2 == 0) goto L_0x00f9
                    r2.close()     // Catch:{ IOException -> 0x00f5 }
                    goto L_0x00f9
                L_0x00f5:
                    r0 = move-exception
                    r0.printStackTrace()
                L_0x00f9:
                    return r10
                L_0x00fa:
                    r10 = move-exception
                L_0x00fb:
                    r10.printStackTrace()     // Catch:{ all -> 0x00db }
                    java.lang.String r0 = "WxPushHelper"
                    java.lang.String r10 = r10.toString()     // Catch:{ all -> 0x00db }
                    android.util.Log.e(r0, r10)     // Catch:{ all -> 0x00db }
                    java.lang.Integer r10 = java.lang.Integer.valueOf(r4)     // Catch:{ all -> 0x00db }
                    if (r2 == 0) goto L_0x0115
                    r2.close()     // Catch:{ IOException -> 0x0111 }
                    goto L_0x0115
                L_0x0111:
                    r0 = move-exception
                    r0.printStackTrace()
                L_0x0115:
                    return r10
                L_0x0116:
                    if (r5 == 0) goto L_0x0120
                    r5.close()     // Catch:{ IOException -> 0x011c }
                    goto L_0x0120
                L_0x011c:
                    r0 = move-exception
                    r0.printStackTrace()
                L_0x0120:
                    throw r10
                */
                throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.smarthome.wxapi.WxPushHelper.AnonymousClass6.doInBackground(java.lang.String[]):java.lang.Integer");
            }

            static final URLConnection a(AnonymousClass6 r0, URL url) {
                return url.openConnection();
            }

            /* access modifiers changed from: protected */
            /* renamed from: a */
            public void onPostExecute(Integer num) {
                super.onPostExecute(num);
                boolean unused = WxPushHelper.this.r = true;
                WxPushHelper.this.c();
                activity.isFinishing();
                if (num.intValue() == 0) {
                    WxPushHelper.this.a(activity, str, (Callback<Boolean>) callback);
                }
            }

            /* access modifiers changed from: protected */
            public void onPreExecute() {
                super.onPreExecute();
                WxPushHelper.this.a(activity, (Callback<Boolean>) callback);
            }
        };
        this.t.execute(new String[]{str2});
    }

    /* access modifiers changed from: private */
    public void a(final Activity activity, String str, final Callback<Boolean> callback) {
        if (this.p != null && this.p.isShowing()) {
            this.p.dismiss();
        }
        String a2 = a(str);
        View inflate = LayoutInflater.from(activity.getApplicationContext()).inflate(R.layout.wx_guid_tip, (ViewGroup) null);
        this.p = new Dialog(activity);
        ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams(a(activity, 288.0f), a(activity, 369.0f));
        this.p.requestWindowFeature(1);
        this.p.setContentView(inflate, layoutParams);
        Window window = this.p.getWindow();
        if (window != null) {
            window.setGravity(17);
            WindowManager.LayoutParams attributes = window.getAttributes();
            attributes.width = -2;
            attributes.height = -2;
            window.setAttributes(attributes);
        }
        ImageView imageView = (ImageView) inflate.findViewById(R.id.wx_guid_album);
        Locale locale = activity.getApplicationContext().getResources().getConfiguration().locale;
        if (!(locale == null || locale.getLanguage() == null || locale.getLanguage().equals("zh"))) {
            imageView.setImageResource(R.drawable.wx_push_4);
        }
        ImageView imageView2 = (ImageView) inflate.findViewById(R.id.guid_wx_qcord);
        if (this.o != null && !this.o.isRecycled()) {
            this.o.recycle();
        }
        this.o = BitmapFactory.decodeFile(a2);
        imageView2.setImageBitmap(this.o);
        inflate.findViewById(R.id.wx_push_ok).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                try {
                    Intent intent = new Intent();
                    intent.setComponent(new ComponentName("com.tencent.mm", "com.tencent.mm.ui.LauncherUI"));
                    intent.putExtra("LauncherUI.From.Scaner.Shortcut", true);
                    intent.setFlags(335544320);
                    intent.setAction("android.intent.action.VIEW");
                    activity.startActivity(intent);
                    boolean unused = WxPushHelper.this.s = true;
                } catch (Exception e) {
                    e.printStackTrace();
                }
                if (!WxPushHelper.this.s) {
                    IWXAPI createWXAPI = XmPluginHostApi.instance().createWXAPI(activity.getApplicationContext(), true);
                    JumpToBizProfile.Req req = new JumpToBizProfile.Req();
                    req.toUserName = "gh_81a506b5df46";
                    req.profileType = 1;
                    req.extMsg = "";
                    createWXAPI.sendReq(req);
                    boolean unused2 = WxPushHelper.this.s = true;
                }
                if (WxPushHelper.this.p != null && WxPushHelper.this.p.isShowing()) {
                    WxPushHelper.this.p.dismiss();
                }
            }
        });
        this.p.setCancelable(true);
        this.p.setOnDismissListener(new DialogInterface.OnDismissListener() {
            public void onDismiss(DialogInterface dialogInterface) {
                if (!WxPushHelper.this.s && callback != null) {
                    callback.onFailure(-9999, "wx bind cancelled!");
                }
                boolean unused = WxPushHelper.this.s = false;
            }
        });
        this.p.show();
    }

    /* access modifiers changed from: private */
    public void a(Activity activity, final Callback<Boolean> callback) {
        c();
        this.q = new Dialog(activity);
        View inflate = LayoutInflater.from(activity.getApplicationContext()).inflate(R.layout.wx_progress_dialog, (ViewGroup) null);
        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(-1, -2, 80);
        layoutParams.setMargins(UIUtils.a(8), UIUtils.a(8), UIUtils.a(8), UIUtils.a(8));
        inflate.setLayoutParams(layoutParams);
        ((TextView) inflate.findViewById(R.id.progress_message)).setText(activity.getApplicationContext().getString(R.string.smb_waiting));
        this.q.setOnDismissListener(new DialogInterface.OnDismissListener() {
            public void onDismiss(DialogInterface dialogInterface) {
                if (!WxPushHelper.this.r && callback != null) {
                    callback.onFailure(-9999, "wx bind cancelled by user!");
                }
                boolean unused = WxPushHelper.this.r = false;
            }
        });
        ((TextView) inflate.findViewById(R.id.cancel_btn)).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                WxPushHelper.this.c();
                if (WxPushHelper.this.t != null && !WxPushHelper.this.t.isCancelled()) {
                    WxPushHelper.this.t.cancel(true);
                }
            }
        });
        this.q.setContentView(inflate);
        Window window = this.q.getWindow();
        if (window != null) {
            window.setGravity(80);
            window.setBackgroundDrawable(new InsetDrawable(new ColorDrawable(0), UIUtils.a(8), UIUtils.a(8), UIUtils.a(8), UIUtils.a(8)));
            WindowManager.LayoutParams attributes = window.getAttributes();
            attributes.width = -1;
            attributes.height = -2;
            window.setAttributes(attributes);
        }
        this.q.show();
    }

    /* access modifiers changed from: private */
    public void c() {
        if (this.q != null && this.q.isShowing()) {
            this.q.dismiss();
        }
        this.q = null;
    }

    /* access modifiers changed from: private */
    public String a(String str) {
        String b2 = b(str);
        if (TextUtils.isEmpty(b2)) {
            return null;
        }
        File file = new File(b2);
        if (!file.exists()) {
            file.mkdirs();
        }
        return new File(file, "wxQcord.jpg").getAbsolutePath();
    }

    private String b(String str) {
        String str2 = j + File.separator + str;
        if (TextUtils.isEmpty(str2)) {
            return null;
        }
        File file = new File(str2);
        if (!file.exists()) {
            file.mkdirs();
        }
        return str2;
    }

    private static int a(Activity activity, float f2) {
        return (int) ((f2 * activity.getResources().getDisplayMetrics().density) + 0.5f);
    }
}
