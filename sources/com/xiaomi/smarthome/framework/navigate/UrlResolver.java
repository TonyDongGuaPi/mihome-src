package com.xiaomi.smarthome.framework.navigate;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.text.TextUtils;
import android.widget.Toast;
import com.xiaomi.router.api.SceneManager;
import com.xiaomi.smarthome.AppStateNotifier;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.SmartHomeMainActivity;
import com.xiaomi.smarthome.application.SHApplication;
import com.xiaomi.smarthome.auth.bindaccount.ThirdAccountGroupListActivity;
import com.xiaomi.smarthome.core.entity.plugin.PluginDownloadTask;
import com.xiaomi.smarthome.core.entity.plugin.PluginError;
import com.xiaomi.smarthome.core.entity.plugin.PluginRecord;
import com.xiaomi.smarthome.device.Device;
import com.xiaomi.smarthome.device.SmartHomeDeviceManager;
import com.xiaomi.smarthome.device.api.DeviceApi;
import com.xiaomi.smarthome.device.api.DeviceStat;
import com.xiaomi.smarthome.frame.AsyncCallback;
import com.xiaomi.smarthome.frame.Error;
import com.xiaomi.smarthome.frame.core.CoreApi;
import com.xiaomi.smarthome.frame.plugin.PluginApi;
import com.xiaomi.smarthome.frame.plugin.RunningProcess;
import com.xiaomi.smarthome.framework.navigate.PageUrl;
import com.xiaomi.smarthome.framework.openapi.ApiConst;
import com.xiaomi.smarthome.globalnavbutton.GlobalNavButtonManager;
import com.xiaomi.smarthome.homeroom.MultiHomeDeviceManager;
import com.xiaomi.smarthome.library.common.dialog.MLAlertDialog;
import com.xiaomi.smarthome.library.common.dialog.XQProgressDialog;
import com.xiaomi.smarthome.library.common.dialog.XQProgressHorizontalDialog;
import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class UrlResolver {

    /* renamed from: a  reason: collision with root package name */
    public static final String f16667a = "UrlResolver";
    public static final String b = "yunyi.camera.broadcast";

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r14v30, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r10v3, resolved type: java.lang.String} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static boolean a(android.content.Context r13, android.net.Uri r14, boolean r15) {
        /*
            if (r13 == 0) goto L_0x02f3
            if (r14 == 0) goto L_0x02f3
            r0 = 1
            java.lang.String r1 = "https"
            java.lang.String r2 = r14.getScheme()     // Catch:{ Exception -> 0x002b }
            boolean r1 = r1.equalsIgnoreCase(r2)     // Catch:{ Exception -> 0x002b }
            if (r1 == 0) goto L_0x002f
            com.xiaomi.plugin.XmPluginHostApi r1 = com.xiaomi.plugin.XmPluginHostApi.instance()     // Catch:{ Exception -> 0x002b }
            java.lang.String r2 = r14.toString()     // Catch:{ Exception -> 0x002b }
            boolean r1 = r1.isYoupinHost(r2)     // Catch:{ Exception -> 0x002b }
            if (r1 == 0) goto L_0x002f
            com.xiaomi.plugin.XmPluginHostApi r1 = com.xiaomi.plugin.XmPluginHostApi.instance()     // Catch:{ Exception -> 0x002b }
            java.lang.String r2 = r14.toString()     // Catch:{ Exception -> 0x002b }
            r1.openUrl(r2)     // Catch:{ Exception -> 0x002b }
            return r0
        L_0x002b:
            r1 = move-exception
            r1.printStackTrace()
        L_0x002f:
            com.xiaomi.smarthome.framework.navigate.UrlResolver$Parameter r1 = com.xiaomi.smarthome.framework.navigate.UrlResolver.Parameter.a(r14)
            java.lang.String r2 = r14.getPath()
            boolean r3 = com.xiaomi.smarthome.framework.navigate.PageUrl.a((java.lang.String) r2)
            r4 = 9
            r5 = 0
            r6 = 268435456(0x10000000, float:2.5243549E-29)
            if (r3 == 0) goto L_0x009d
            java.lang.Class r14 = com.xiaomi.smarthome.framework.navigate.PageUrl.a((android.net.Uri) r14, (com.xiaomi.smarthome.framework.navigate.UrlResolver.Parameter) r1)
            if (r14 == 0) goto L_0x01cf
            android.content.Intent r15 = new android.content.Intent
            r15.<init>(r13, r14)
            java.util.Map<java.lang.String, java.lang.String> r1 = r1.b
            java.util.Set r1 = r1.entrySet()
            java.util.Iterator r1 = r1.iterator()
        L_0x0057:
            boolean r2 = r1.hasNext()
            if (r2 == 0) goto L_0x0073
            java.lang.Object r2 = r1.next()
            java.util.Map$Entry r2 = (java.util.Map.Entry) r2
            java.lang.Object r3 = r2.getKey()
            java.lang.String r3 = (java.lang.String) r3
            java.lang.Object r2 = r2.getValue()
            java.lang.String r2 = (java.lang.String) r2
            r15.putExtra(r3, r2)
            goto L_0x0057
        L_0x0073:
            java.lang.Class<com.xiaomi.smarthome.SmartHomeMainActivity> r1 = com.xiaomi.smarthome.SmartHomeMainActivity.class
            if (r14 != r1) goto L_0x008c
            boolean r14 = com.xiaomi.smarthome.framework.navigate.NavigateUtil.a()
            if (r14 == 0) goto L_0x0083
            r14 = 603979776(0x24000000, float:2.7755576E-17)
            r15.setFlags(r14)
            goto L_0x0086
        L_0x0083:
            r15.setFlags(r6)
        L_0x0086:
            java.lang.String r14 = "source"
            r15.putExtra(r14, r4)
            goto L_0x008f
        L_0x008c:
            r15.setFlags(r6)
        L_0x008f:
            boolean r14 = r13 instanceof com.xiaomi.smarthome.framework.navigate.SmartHomeLauncher
            if (r14 == 0) goto L_0x0099
            com.xiaomi.smarthome.framework.navigate.SmartHomeLauncher r13 = (com.xiaomi.smarthome.framework.navigate.SmartHomeLauncher) r13
            r13.startActivityForResult(r15, r0)
            goto L_0x009c
        L_0x0099:
            r13.startActivity(r15)
        L_0x009c:
            return r0
        L_0x009d:
            boolean r3 = com.xiaomi.smarthome.framework.navigate.PageUrl.g(r2)
            if (r3 == 0) goto L_0x0118
            java.lang.String r1 = "/shop/main"
            boolean r1 = r2.startsWith(r1)
            r2 = 67108864(0x4000000, float:1.5046328E-36)
            if (r1 == 0) goto L_0x00d9
            android.content.Intent r15 = new android.content.Intent
            java.lang.Class<com.xiaomi.smarthome.SmartHomeMainActivity> r1 = com.xiaomi.smarthome.SmartHomeMainActivity.class
            r15.<init>(r13, r1)
            boolean r1 = com.xiaomi.smarthome.framework.navigate.NavigateUtil.a()
            if (r1 == 0) goto L_0x00bd
            r15.setFlags(r2)
        L_0x00bd:
            java.lang.String r1 = "source"
            r15.putExtra(r1, r4)
            r15.addFlags(r6)
            java.lang.String r1 = "page"
            java.lang.String r2 = "shop"
            r15.putExtra(r1, r2)
            java.lang.String r1 = "url"
            java.lang.String r14 = r14.toString()
            r15.putExtra(r1, r14)
            r13.startActivity(r15)
            goto L_0x0117
        L_0x00d9:
            if (r15 == 0) goto L_0x00e7
            com.xiaomi.youpin.app_sdk.url_dispatch.UrlDispatchManger r13 = com.xiaomi.youpin.app_sdk.url_dispatch.UrlDispatchManger.a()
            java.lang.String r14 = r14.toString()
            r13.c(r14)
            goto L_0x0117
        L_0x00e7:
            android.content.Intent r15 = new android.content.Intent
            java.lang.Class<com.xiaomi.smarthome.SmartHomeMainActivity> r1 = com.xiaomi.smarthome.SmartHomeMainActivity.class
            r15.<init>(r13, r1)
            boolean r1 = com.xiaomi.smarthome.framework.navigate.NavigateUtil.a()
            if (r1 == 0) goto L_0x00f7
            r15.setFlags(r2)
        L_0x00f7:
            android.os.Bundle r1 = new android.os.Bundle
            r1.<init>()
            java.lang.String r3 = "url"
            java.lang.String r14 = r14.toString()
            r1.putString(r3, r14)
            r15.putExtras(r1)
            java.lang.String r14 = "source"
            r1 = 4
            r15.putExtra(r14, r1)
            r15.addFlags(r2)
            r15.addFlags(r6)
            r13.startActivity(r15)
        L_0x0117:
            return r0
        L_0x0118:
            boolean r15 = com.xiaomi.smarthome.framework.navigate.PageUrl.b((java.lang.String) r2)
            if (r15 == 0) goto L_0x01d0
            com.xiaomi.smarthome.framework.navigate.PageUrl$DevicePageParam r9 = com.xiaomi.smarthome.framework.navigate.PageUrl.h(r2)
            if (r9 == 0) goto L_0x01cf
            java.lang.String r14 = r9.f16633a
            boolean r14 = android.text.TextUtils.isEmpty(r14)
            if (r14 != 0) goto L_0x01cf
            com.xiaomi.smarthome.frame.core.CoreApi r14 = com.xiaomi.smarthome.frame.core.CoreApi.a()
            java.lang.String r15 = r9.f16633a
            boolean r14 = r14.c((java.lang.String) r15)
            if (r14 == 0) goto L_0x01cf
            java.lang.String r14 = r9.f16633a
            java.lang.String r15 = "yunyi.camera.broadcast"
            boolean r14 = r14.equals(r15)
            if (r14 == 0) goto L_0x0147
            boolean r13 = a((android.content.Context) r13)
            return r13
        L_0x0147:
            com.xiaomi.smarthome.frame.core.CoreApi r14 = com.xiaomi.smarthome.frame.core.CoreApi.a()
            java.lang.String r15 = r9.f16633a
            com.xiaomi.smarthome.core.entity.plugin.PluginRecord r8 = r14.d((java.lang.String) r15)
            if (r8 == 0) goto L_0x01cf
            android.content.Intent r11 = new android.content.Intent
            r11.<init>()
            java.lang.String r14 = ""
            java.util.Map<java.lang.String, java.lang.String> r15 = r1.b
            java.util.Set r15 = r15.entrySet()
            java.util.Iterator r15 = r15.iterator()
            r10 = r14
        L_0x0165:
            boolean r14 = r15.hasNext()
            if (r14 == 0) goto L_0x0196
            java.lang.Object r14 = r15.next()
            java.util.Map$Entry r14 = (java.util.Map.Entry) r14
            java.lang.Object r1 = r14.getKey()
            java.lang.String r1 = (java.lang.String) r1
            java.lang.Object r2 = r14.getValue()
            java.lang.String r2 = (java.lang.String) r2
            r11.putExtra(r1, r2)
            java.lang.Object r1 = r14.getKey()
            java.lang.String r1 = (java.lang.String) r1
            java.lang.String r2 = "did"
            boolean r1 = r1.equalsIgnoreCase(r2)
            if (r1 == 0) goto L_0x0165
            java.lang.Object r14 = r14.getValue()
            r10 = r14
            java.lang.String r10 = (java.lang.String) r10
            goto L_0x0165
        L_0x0196:
            java.lang.String r14 = "mihome_page_navigate_path"
            java.lang.String r15 = r9.b
            r11.putExtra(r14, r15)
            r14 = 0
            boolean r15 = r13 instanceof android.app.Activity
            if (r15 == 0) goto L_0x01a7
            com.xiaomi.smarthome.framework.navigate.UrlResolver$SendMessageCallbackImpl r14 = new com.xiaomi.smarthome.framework.navigate.UrlResolver$SendMessageCallbackImpl
            r14.<init>(r13)
        L_0x01a7:
            r12 = r14
            com.xiaomi.smarthome.frame.core.CoreApi r14 = com.xiaomi.smarthome.frame.core.CoreApi.a()
            boolean r14 = r14.l()
            if (r14 == 0) goto L_0x01b7
            r7 = r13
            b(r7, r8, r9, r10, r11, r12)
            goto L_0x01ce
        L_0x01b7:
            android.content.IntentFilter r13 = new android.content.IntentFilter
            java.lang.String r14 = "ClientApiStub.onCoreReady"
            r13.<init>(r14)
            com.xiaomi.smarthome.framework.navigate.UrlResolver$1 r14 = new com.xiaomi.smarthome.framework.navigate.UrlResolver$1
            r14.<init>(r8, r9, r10, r12)
            android.content.Context r15 = com.xiaomi.smarthome.application.SHApplication.getAppContext()
            android.support.v4.content.LocalBroadcastManager r15 = android.support.v4.content.LocalBroadcastManager.getInstance(r15)
            r15.registerReceiver(r14, r13)
        L_0x01ce:
            return r0
        L_0x01cf:
            return r5
        L_0x01d0:
            boolean r15 = com.xiaomi.smarthome.framework.navigate.PageUrl.f(r2)
            if (r15 == 0) goto L_0x01de
            java.lang.String r13 = "TOUCH"
            java.lang.String r14 = "share"
            com.xiaomi.smarthome.shop.analytics.MIOTStat.Log(r13, r14)
            return r0
        L_0x01de:
            boolean r15 = com.xiaomi.smarthome.framework.navigate.PageUrl.e(r2)
            if (r15 == 0) goto L_0x01e8
            a((android.content.Context) r13, (com.xiaomi.smarthome.framework.navigate.UrlResolver.Parameter) r1)
            return r5
        L_0x01e8:
            boolean r15 = com.xiaomi.smarthome.framework.navigate.PageUrl.c((java.lang.String) r2)
            if (r15 == 0) goto L_0x0205
            android.content.Intent r15 = new android.content.Intent
            java.lang.Class<com.xiaomi.smarthome.device.choosedevice.ChooseDeviceActivity> r1 = com.xiaomi.smarthome.device.choosedevice.ChooseDeviceActivity.class
            r15.<init>(r13, r1)
            java.lang.String r1 = "url"
            java.lang.String r14 = r14.toString()
            r15.putExtra(r1, r14)
            r15.setFlags(r6)
            r13.startActivity(r15)
            return r0
        L_0x0205:
            boolean r15 = com.xiaomi.smarthome.framework.navigate.PageUrl.d((java.lang.String) r2)
            if (r15 == 0) goto L_0x0219
            android.content.Intent r14 = new android.content.Intent
            java.lang.Class<com.xiaomi.smarthome.messagecenter.ui.MessageCenterActivity> r15 = com.xiaomi.smarthome.messagecenter.ui.MessageCenterActivity.class
            r14.<init>(r13, r15)
            r14.setFlags(r6)
            r13.startActivity(r14)
            return r0
        L_0x0219:
            java.lang.String r15 = r14.getHost()
            boolean r15 = com.xiaomi.smarthome.framework.navigate.PageUrl.a((java.lang.String) r15, (java.lang.String) r2)
            if (r15 == 0) goto L_0x025c
            java.util.Map r14 = com.xiaomi.smarthome.framework.navigate.PageUrl.d((android.net.Uri) r14)
            if (r14 == 0) goto L_0x025b
            boolean r15 = r14.isEmpty()
            if (r15 != 0) goto L_0x025b
            java.lang.String r15 = "did"
            java.lang.Object r15 = r14.get(r15)
            java.lang.String r15 = (java.lang.String) r15
            java.lang.String r1 = "sr_id"
            java.lang.Object r14 = r14.get(r1)
            java.lang.String r14 = (java.lang.String) r14
            boolean r1 = android.text.TextUtils.isEmpty(r15)
            if (r1 != 0) goto L_0x025b
            boolean r1 = android.text.TextUtils.isEmpty(r14)
            if (r1 != 0) goto L_0x025b
            com.xiaomi.smarthome.scenenew.manager.PluginRecommendSceneManager r1 = com.xiaomi.smarthome.scenenew.manager.PluginRecommendSceneManager.a()
            android.content.Intent r14 = r1.a((android.content.Context) r13, (java.lang.String) r15, (java.lang.String) r14)
            if (r14 == 0) goto L_0x025b
            r14.setFlags(r6)
            r13.startActivity(r14)
        L_0x025b:
            return r0
        L_0x025c:
            java.lang.String r15 = r14.getHost()
            boolean r15 = android.text.TextUtils.isEmpty(r15)
            if (r15 != 0) goto L_0x02b3
            java.lang.String r15 = "home.mi.com"
            java.lang.String r1 = r14.getHost()
            boolean r15 = android.text.TextUtils.equals(r15, r1)
            if (r15 != 0) goto L_0x027e
            java.lang.String r15 = r14.getHost()
            java.lang.String r1 = ".home.mi.com"
            boolean r15 = r15.endsWith(r1)
            if (r15 == 0) goto L_0x02b3
        L_0x027e:
            java.lang.String r15 = "https"
            java.lang.String r1 = r14.getScheme()
            boolean r15 = r15.equalsIgnoreCase(r1)
            if (r15 == 0) goto L_0x02b3
            java.lang.String r15 = "/download"
            java.lang.String r1 = r14.getPath()
            boolean r15 = android.text.TextUtils.equals(r15, r1)
            if (r15 != 0) goto L_0x02b3
            android.content.Intent r15 = new android.content.Intent
            android.content.Context r1 = com.xiaomi.smarthome.application.SHApplication.getAppContext()
            java.lang.Class<com.xiaomi.smarthome.operation.js_sdk.OperationCommonWebViewActivity> r2 = com.xiaomi.smarthome.operation.js_sdk.OperationCommonWebViewActivity.class
            r15.<init>(r1, r2)
            r1 = 335544320(0x14000000, float:6.4623485E-27)
            r15.addFlags(r1)
            java.lang.String r1 = "url"
            java.lang.String r14 = r14.toString()
            r15.putExtra(r1, r14)
            r13.startActivity(r15)
            return r0
        L_0x02b3:
            java.lang.String r15 = r14.getScheme()
            java.lang.String r1 = "https"
            boolean r15 = android.text.TextUtils.equals(r15, r1)
            if (r15 != 0) goto L_0x02c0
            return r5
        L_0x02c0:
            java.lang.String r15 = r14.getHost()
            boolean r1 = android.text.TextUtils.isEmpty(r15)
            if (r1 == 0) goto L_0x02cb
            return r5
        L_0x02cb:
            java.lang.String r1 = ".mi.com"
            boolean r1 = r15.endsWith(r1)
            if (r1 != 0) goto L_0x02dc
            java.lang.String r1 = ".xiaomi.com"
            boolean r15 = r15.endsWith(r1)
            if (r15 != 0) goto L_0x02dc
            return r5
        L_0x02dc:
            android.content.Intent r15 = new android.content.Intent
            java.lang.Class<com.xiaomi.smarthome.framework.webview.WebShellActivity> r1 = com.xiaomi.smarthome.framework.webview.WebShellActivity.class
            r15.<init>(r13, r1)
            java.lang.String r1 = "url"
            java.lang.String r14 = r14.toString()
            r15.putExtra(r1, r14)
            r15.setFlags(r6)
            r13.startActivity(r15)
            return r0
        L_0x02f3:
            java.lang.RuntimeException r13 = new java.lang.RuntimeException
            java.lang.String r14 = "Context and Uri can not be null!"
            r13.<init>(r14)
            throw r13
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.smarthome.framework.navigate.UrlResolver.a(android.content.Context, android.net.Uri, boolean):boolean");
    }

    public static boolean a(final Context context) {
        Device device;
        List<Device> d = SmartHomeDeviceManager.a().d();
        int i = 0;
        while (true) {
            if (i >= d.size()) {
                device = null;
                break;
            } else if (b.equals(d.get(i).model)) {
                device = d.get(i);
                break;
            } else {
                i++;
            }
        }
        if (device != null) {
            a(context, device);
            return true;
        }
        MLAlertDialog.Builder builder = new MLAlertDialog.Builder(context);
        builder.b((int) R.string.yunyi_virtual_camera_create);
        builder.a((int) R.string.ok_button, (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
                final XQProgressDialog xQProgressDialog = new XQProgressDialog(context);
                xQProgressDialog.show();
                DeviceApi.getInstance().createVirtalDevice(context, UrlResolver.b, new AsyncCallback<Device, Error>() {
                    /* renamed from: a */
                    public void onSuccess(Device device) {
                        SmartHomeDeviceManager.a().b(device);
                        UrlResolver.a(context, device);
                        xQProgressDialog.dismiss();
                        Toast.makeText(context, R.string.yunyi_virtual_camera_create_success, 0).show();
                    }

                    public void onFailure(Error error) {
                        xQProgressDialog.dismiss();
                        Toast.makeText(context, R.string.yunyi_virtual_camera_create_fail, 0).show();
                    }
                });
            }
        });
        builder.b((int) R.string.cancel, (DialogInterface.OnClickListener) null);
        builder.d();
        return true;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:3:0x0004, code lost:
        r5 = com.xiaomi.smarthome.frame.core.CoreApi.a().d(b);
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static boolean a(final android.content.Context r12, final com.xiaomi.smarthome.device.Device r13) {
        /*
            r0 = 0
            if (r13 != 0) goto L_0x0004
            return r0
        L_0x0004:
            com.xiaomi.smarthome.frame.core.CoreApi r1 = com.xiaomi.smarthome.frame.core.CoreApi.a()
            java.lang.String r2 = "yunyi.camera.broadcast"
            com.xiaomi.smarthome.core.entity.plugin.PluginRecord r5 = r1.d((java.lang.String) r2)
            if (r5 == 0) goto L_0x0054
            com.xiaomi.smarthome.framework.location.SHLocationManager r0 = com.xiaomi.smarthome.framework.location.SHLocationManager.a()
            android.location.Location r0 = r0.b()
            if (r0 != 0) goto L_0x0027
            com.xiaomi.smarthome.framework.location.SHLocationManager r0 = com.xiaomi.smarthome.framework.location.SHLocationManager.a()
            com.xiaomi.smarthome.framework.navigate.UrlResolver$3 r1 = new com.xiaomi.smarthome.framework.navigate.UrlResolver$3
            r1.<init>(r13, r12, r5)
            r0.a((com.xiaomi.smarthome.framework.location.SHLocationManager.LocationCallback) r1)
            goto L_0x0052
        L_0x0027:
            double r1 = r0.getLatitude()
            r13.latitude = r1
            double r0 = r0.getLongitude()
            r13.longitude = r0
            android.content.Intent r7 = new android.content.Intent
            r7.<init>()
            r0 = 0
            boolean r1 = r12 instanceof android.app.Activity
            if (r1 == 0) goto L_0x0042
            com.xiaomi.smarthome.framework.navigate.UrlResolver$SendMessageCallbackImpl r0 = new com.xiaomi.smarthome.framework.navigate.UrlResolver$SendMessageCallbackImpl
            r0.<init>(r12)
        L_0x0042:
            r11 = r0
            com.xiaomi.smarthome.frame.plugin.PluginApi r3 = com.xiaomi.smarthome.frame.plugin.PluginApi.getInstance()
            r6 = 1
            com.xiaomi.smarthome.device.api.DeviceStat r8 = r13.newDeviceStat()
            r9 = 0
            r10 = 0
            r4 = r12
            r3.sendMessage(r4, r5, r6, r7, r8, r9, r10, r11)
        L_0x0052:
            r12 = 1
            return r12
        L_0x0054:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.smarthome.framework.navigate.UrlResolver.a(android.content.Context, com.xiaomi.smarthome.device.Device):boolean");
    }

    /* access modifiers changed from: private */
    public static void b(Context context, PluginRecord pluginRecord, PageUrl.DevicePageParam devicePageParam, String str, Intent intent, PluginApi.SendMessageCallback sendMessageCallback) {
        PageUrl.DevicePageParam devicePageParam2 = devicePageParam;
        PluginApi.SendMessageCallback sendMessageCallback2 = sendMessageCallback;
        if (!TextUtils.isEmpty(str)) {
            Device a2 = a(str);
            if (a2 == null) {
                TimeoutRunnableWrapper timeoutRunnableWrapper = new TimeoutRunnableWrapper();
                final TimeoutRunnableWrapper timeoutRunnableWrapper2 = timeoutRunnableWrapper;
                final String str2 = str;
                final PageUrl.DevicePageParam devicePageParam3 = devicePageParam;
                final Context context2 = context;
                final PluginRecord pluginRecord2 = pluginRecord;
                final Intent intent2 = intent;
                final PluginApi.SendMessageCallback sendMessageCallback3 = sendMessageCallback;
                final AnonymousClass4 r0 = new SmartHomeDeviceManager.IClientDeviceListener() {
                    public void a(int i) {
                        SmartHomeDeviceManager.a().c((SmartHomeDeviceManager.IClientDeviceListener) this);
                        SHApplication.getGlobalWorkerHandler().removeCallbacks(timeoutRunnableWrapper2.f16679a);
                        Device a2 = UrlResolver.a(str2);
                        if (a2 == null) {
                            return;
                        }
                        if (TextUtils.isEmpty(devicePageParam3.b) || !devicePageParam3.b.equalsIgnoreCase("/")) {
                            PluginApi.getInstance().sendMessage(context2, pluginRecord2, 4, intent2, (DeviceStat) null, (RunningProcess) null, false, sendMessageCallback3);
                            return;
                        }
                        PluginApi.getInstance().sendMessage(context2, pluginRecord2, 1, intent2, a2.newDeviceStat(), (RunningProcess) null, false, sendMessageCallback3);
                    }

                    public void a(int i, Device device) {
                        SmartHomeDeviceManager.a().c((SmartHomeDeviceManager.IClientDeviceListener) this);
                        SHApplication.getGlobalWorkerHandler().removeCallbacks(timeoutRunnableWrapper2.f16679a);
                    }

                    public void b(int i) {
                        SmartHomeDeviceManager.a().c((SmartHomeDeviceManager.IClientDeviceListener) this);
                        SHApplication.getGlobalWorkerHandler().removeCallbacks(timeoutRunnableWrapper2.f16679a);
                    }
                };
                SmartHomeDeviceManager.a().a((SmartHomeDeviceManager.IClientDeviceListener) r0);
                SmartHomeDeviceManager.a().p();
                AnonymousClass5 r02 = new Runnable() {
                    public void run() {
                        SmartHomeDeviceManager.a().c(r0);
                    }
                };
                timeoutRunnableWrapper.f16679a = r02;
                SHApplication.getGlobalWorkerHandler().postDelayed(r02, 20000);
            } else if (TextUtils.isEmpty(devicePageParam2.b) || !devicePageParam2.b.equalsIgnoreCase("/")) {
                PluginApi.getInstance().sendMessage(context, pluginRecord, 4, intent, a2.newDeviceStat(), (RunningProcess) null, false, sendMessageCallback);
            } else {
                PluginApi.getInstance().sendMessage(context, pluginRecord, 1, intent, a2.newDeviceStat(), (RunningProcess) null, false, sendMessageCallback);
            }
        } else if (sendMessageCallback2 != null) {
            sendMessageCallback2.onSendFailure(new Error(-1, "did is null"));
        }
    }

    public static Device a(String str) {
        List<Device> k;
        Device device = null;
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        List<Device> d = SmartHomeDeviceManager.a().d();
        if (d != null) {
            Iterator<Device> it = d.iterator();
            while (true) {
                if (!it.hasNext()) {
                    break;
                }
                Device next = it.next();
                if (str.equalsIgnoreCase(next.did)) {
                    device = next;
                    break;
                }
            }
        }
        if (device != null || (k = SmartHomeDeviceManager.a().k()) == null) {
            return device;
        }
        for (Device next2 : k) {
            if (str.equalsIgnoreCase(next2.did)) {
                return next2;
            }
        }
        return device;
    }

    public static DeviceStat a(com.xiaomi.smarthome.core.entity.device.Device device) {
        DeviceStat deviceStat = new DeviceStat();
        deviceStat.did = device.k();
        deviceStat.name = device.m();
        deviceStat.mac = device.n();
        deviceStat.model = device.l();
        deviceStat.ip = device.t();
        deviceStat.parentId = device.A();
        deviceStat.parentModel = device.B();
        deviceStat.authFlag = device.V() ? 1 : 0;
        deviceStat.bindFlag = (device.S() || device.W()) ? 1 : 0;
        deviceStat.token = device.s();
        deviceStat.userId = CoreApi.a().s();
        if (device.I() != null) {
            deviceStat.location = device.I().ordinal();
        }
        deviceStat.latitude = device.v();
        deviceStat.longitude = device.u();
        deviceStat.bssid = device.x();
        deviceStat.pid = device.f();
        deviceStat.rssi = device.r();
        deviceStat.isOnline = device.o();
        deviceStat.resetFlag = device.q();
        deviceStat.ssid = device.w();
        deviceStat.ownerName = device.C();
        deviceStat.ownerId = device.D();
        deviceStat.version = device.J();
        deviceStat.property.clear();
        deviceStat.extrainfo = device.G();
        deviceStat.showMode = device.y();
        deviceStat.event = device.H();
        deviceStat.permitLevel = device.p();
        deviceStat.isSetPinCode = device.M();
        return deviceStat;
    }

    public static class Parameter {

        /* renamed from: a  reason: collision with root package name */
        public String f16677a;
        public Map<String, String> b = new HashMap();

        public static Parameter a(Uri uri) {
            Parameter parameter = new Parameter();
            parameter.f16677a = uri.getPath();
            parameter.b = new HashMap();
            for (String next : uri.getQueryParameterNames()) {
                parameter.b.put(next, uri.getQueryParameter(next));
            }
            return parameter;
        }

        public String toString() {
            return "page: " + this.f16677a + " params: " + this.b.toString();
        }
    }

    public static class SendMessageCallbackImpl extends PluginApi.SendMessageCallback {

        /* renamed from: a  reason: collision with root package name */
        WeakReference<Context> f16678a;
        XQProgressHorizontalDialog b;

        public SendMessageCallbackImpl(Context context) {
            this.f16678a = new WeakReference<>(context);
            this.b = new XQProgressHorizontalDialog(context);
            this.b.setMessage(context.getString(R.string.plugin_downloading));
            this.b.a(true);
            this.b.c();
        }

        public void onDownloadStart(PluginRecord pluginRecord, PluginDownloadTask pluginDownloadTask) {
            super.onDownloadStart(pluginRecord, pluginDownloadTask);
            this.b.show();
        }

        public void onDownloadProgress(PluginRecord pluginRecord, float f) {
            super.onDownloadProgress(pluginRecord, f);
            this.b.a(100, (int) (f * 100.0f));
        }

        public void onDownloadCancel() {
            this.b.dismiss();
        }

        public void onDownloadFailure(PluginError pluginError) {
            this.b.dismiss();
            if (this.f16678a.get() != null) {
                Toast.makeText((Context) this.f16678a.get(), R.string.plugin_download_fail, 1).show();
            }
        }

        public void onDownloadSuccess(PluginRecord pluginRecord) {
            super.onDownloadSuccess(pluginRecord);
            this.b.dismiss();
        }
    }

    private static class TimeoutRunnableWrapper {

        /* renamed from: a  reason: collision with root package name */
        public Runnable f16679a;

        private TimeoutRunnableWrapper() {
        }
    }

    static void a(Context context, Parameter parameter) {
        final String str;
        if (context instanceof Activity) {
            final Map<String, String> map = parameter.b;
            if (map == null) {
                str = "";
            } else {
                str = map.get("user_id");
            }
            final Activity activity = (Activity) context;
            SHApplication.getStateNotifier().a((AppStateNotifier.LoginCallback) new AppStateNotifier.LoginCallback() {
                public void a() {
                    if (TextUtils.isEmpty(str) || SHApplication.getStateNotifier().a() != 4 || CoreApi.a().s().equals(str)) {
                        String str = map == null ? "" : (String) map.get(ApiConst.m);
                        if (TextUtils.isEmpty(str)) {
                            activity.finish();
                            return;
                        }
                        GlobalNavButtonManager.a().a(str);
                        Intent intent = new Intent(activity, ThirdAccountGroupListActivity.class);
                        intent.addFlags(268468224);
                        intent.addFlags(8388608);
                        SHApplication.getAppContext().startActivity(intent);
                        activity.finish();
                        return;
                    }
                    new MLAlertDialog.Builder(activity).a((int) R.string.account_error_title).b((CharSequence) activity.getString(R.string.account_error_msg, new Object[]{""})).c((CharSequence) activity.getString(R.string.auth_check_config), (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialogInterface, int i) {
                            activity.finish();
                        }
                    }).a((MLAlertDialog.DismissCallBack) new MLAlertDialog.DismissCallBack() {
                        public void beforeDismissCallBack() {
                        }

                        public void afterDismissCallBack() {
                            activity.finish();
                        }
                    }).d();
                }

                public void b() {
                    MultiHomeDeviceManager.a().b();
                    SmartHomeDeviceManager.a().v();
                    SceneManager.x().y();
                    SmartHomeDeviceManager.a().p();
                    activity.startActivity(new Intent(activity, SmartHomeMainActivity.class));
                    activity.finish();
                }
            });
        }
    }
}
