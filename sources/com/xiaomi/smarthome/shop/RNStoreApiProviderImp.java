package com.xiaomi.smarthome.shop;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v4.content.LocalBroadcastManager;
import android.text.TextUtils;
import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.Callback;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.WritableArray;
import com.tencent.bugly.crashreport.CrashReport;
import com.xiaomi.miot.store.api.MiotStoreApi;
import com.xiaomi.miot.store.common.MiotStoreConstant;
import com.xiaomi.miot.store.common.RNAppStoreApiManager;
import com.xiaomi.plugin.XmPluginHostApi;
import com.xiaomi.smarthome.SmartHomeMainActivity;
import com.xiaomi.smarthome.application.SHApplication;
import com.xiaomi.smarthome.frame.core.CoreApi;
import com.xiaomi.smarthome.framework.api.AsyncResponseCallback;
import com.xiaomi.smarthome.framework.commonapi.CommonApiV2;
import com.xiaomi.smarthome.shop.utils.DeviceShopConstants;
import com.xiaomi.youpin.MiCSHelper;
import com.xiaomi.youpin.RnMaskActivity;
import com.xiaomi.youpin.ServiceTokenUtil;
import com.xiaomi.youpin.app_sdk.rn.AbsRNStoreApiProviderImpl;
import com.xiaomi.youpin.app_sdk.url_dispatch.UrlDispatchManger;
import com.xiaomi.youpin.log.LogUtils;
import com.xiaomi.youpin.login.AsyncCallback;
import com.xiaomi.youpin.login.api.third_part.ThirdPartAccountBindUtil;
import com.xiaomi.youpin.login.entity.Error;
import com.xiaomi.youpin.login.okhttpApi.api.AccountManagerUtil;
import com.xiaomi.youpin.mimcmsg.api.IHistoryCallback;
import com.xiaomi.youpin.mimcmsg.api.IMutiUpdateCallback;
import com.xiaomi.youpin.mimcmsg.api.IRecentCallback;
import com.xiaomi.youpin.mimcmsg.api.MIMCApi;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.List;
import java.util.Map;

public class RNStoreApiProviderImp extends AbsRNStoreApiProviderImpl {

    /* renamed from: a  reason: collision with root package name */
    private static final String f22137a = "RNStoreApiProviderImp";

    public void onActivityPause(Activity activity) {
    }

    public void onActivityResume(Activity activity) {
    }

    public void showAppstoreComment() {
    }

    public void onReactContextInitialed() {
        if (CoreApi.a().D()) {
            RNAppStoreApiManager.a().m();
        } else {
            MiotStoreApi.a().updateAccount();
        }
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("action_on_login_success");
        intentFilter.addAction("action_on_logout");
        LocalBroadcastManager.getInstance(SHApplication.getAppContext()).registerReceiver(new BroadcastReceiver() {
            public void onReceive(Context context, Intent intent) {
                RNAppStoreApiManager.a().a(ServiceTokenUtil.a());
            }
        }, intentFilter);
    }

    public boolean isRNDebug() {
        return DeviceShopConstants.a();
    }

    public void handleException(Throwable th) {
        String className;
        String methodName;
        if (th != null) {
            th.printStackTrace();
            CrashReport.postCatchedException(th);
            if (th.getStackTrace() != null && th.getStackTrace().length != 0) {
                if (th == null) {
                    className = "";
                } else {
                    className = th.getStackTrace()[0].getClassName();
                }
                String str = className;
                if (th == null) {
                    methodName = "";
                } else {
                    methodName = th.getStackTrace()[0].getMethodName();
                }
                String str2 = methodName;
                StringWriter stringWriter = new StringWriter();
                th.printStackTrace(new PrintWriter(stringWriter));
                String obj = stringWriter.toString();
                if (obj.length() > 10000) {
                    obj = obj.substring(0, 10000);
                }
                SHApplication.getCommonApiV2().a(SHApplication.getAppContext(), 0, 0, str, str2, obj, (AsyncResponseCallback<Void>) null);
            }
        }
    }

    public String shouldOpenUrl(String str, String str2) {
        Activity activity = MiotStoreApi.a().getActivity();
        if (!(activity instanceof SmartHomeMainActivity) && !(activity instanceof RnMaskActivity)) {
            return UrlDispatchManger.a().a(activity, str2, false, -1) ? "" : str2;
        }
        if (str2.contains("miotstore_transition_type=1")) {
            Intent intent = new Intent(MiotStoreConstant.e);
            intent.putExtra("url", str2);
            LocalBroadcastManager.getInstance(activity).sendBroadcast(intent);
            return "";
        }
        LocalBroadcastManager.getInstance(activity).sendBroadcast(new Intent(MiotStoreConstant.d));
        UrlDispatchManger.a().a(activity, str2, -1);
        return "";
    }

    public void openUrl(String str, String str2) {
        Activity activity = MiotStoreApi.a().getActivity();
        if ((activity instanceof SmartHomeMainActivity) || (activity instanceof RnMaskActivity)) {
            if (str.contains("miotstore_transition_type=1")) {
                Intent intent = new Intent(MiotStoreConstant.e);
                intent.putExtra("url", str);
                LocalBroadcastManager.getInstance(activity).sendBroadcast(intent);
                return;
            }
            LocalBroadcastManager.getInstance(activity).sendBroadcast(new Intent(MiotStoreConstant.d));
            UrlDispatchManger.a().a(activity, str, -1);
        } else if (!str.contains("miotstore_transition_type=1")) {
            UrlDispatchManger.a().a(activity, str, -1);
        }
    }

    public void handleHiddenException(String str, Throwable th) {
        if (th != null) {
            a(th);
            return;
        }
        CommonApiV2 commonApiV2 = SHApplication.getCommonApiV2();
        Context appContext = SHApplication.getAppContext();
        if (str == null) {
            str = "";
        }
        commonApiV2.a(appContext, 0, 0, "JSUpdateManager", "updateJS", str, (AsyncResponseCallback<Void>) null);
    }

    private void a(Throwable th) {
        String methodName;
        String className = th == null ? "" : th.getStackTrace()[0].getClassName();
        if (th == null) {
            methodName = "";
        } else {
            methodName = th.getStackTrace()[0].getMethodName();
        }
        String str = methodName;
        StringWriter stringWriter = new StringWriter();
        th.printStackTrace(new PrintWriter(stringWriter));
        String obj = stringWriter.toString();
        if (obj.length() > 10000) {
            obj = obj.substring(0, 10000);
        }
        SHApplication.getCommonApiV2().a(SHApplication.getAppContext(), 0, 0, className, str, obj, (AsyncResponseCallback<Void>) null);
    }

    public void bindWeixin(final Callback callback) {
        boolean z = false;
        if (CoreApi.a().q()) {
            String s = CoreApi.a().s();
            String u = CoreApi.a().u();
            String b = AccountManagerUtil.b(XmPluginHostApi.instance().context());
            if ((CoreApi.a().v() && !TextUtils.isEmpty(b) && b.equalsIgnoreCase(s)) || !CoreApi.a().v()) {
                z = true;
            }
            ThirdPartAccountBindUtil.a(s, u, z, XmPluginHostApi.instance().getPassToken(), new AsyncCallback<Void, Error>() {
                public void a(Void voidR) {
                    if (callback != null) {
                        callback.invoke(0);
                    }
                }

                public void a(Error error) {
                    if (callback != null) {
                        callback.invoke(-1);
                    }
                }
            });
        } else if (callback != null) {
            callback.invoke(-1);
        }
    }

    public int startCustomerServiceChat(Context context, Map<String, String> map) {
        return XmPluginHostApi.instance().startChat(context, map);
    }

    public void getChatList(Callback callback) {
        MiCSHelper.a().d();
        LogUtils.d(getClass().getName(), "getChatList");
        Object sharedValue = XmPluginHostApi.instance().getSharedValue("send_chatMsg", "chatList");
        Object[] objArr = new Object[2];
        objArr[0] = Integer.valueOf(sharedValue != null ? 0 : -1);
        objArr[1] = (sharedValue == null || !(sharedValue instanceof List)) ? null : Arguments.fromList((List) sharedValue);
        callback.invoke(objArr);
    }

    public void deleteChat(String str, String str2, Callback callback) {
        MiCSHelper.a().a(str, str2);
        callback.invoke(0);
    }

    public void setBadge(String str, String str2, int i) {
        XmPluginHostApi.instance().setBadge(str, str2, i);
    }

    public void getBadgeCount(String str, String str2, Callback callback) {
        int badgeCount = XmPluginHostApi.instance().getBadgeCount(str, str2);
        if (callback != null) {
            callback.invoke(Integer.valueOf(badgeCount));
        }
    }

    public void getRecentContactList(final Callback callback) {
        MIMCApi.a((IRecentCallback) new IRecentCallback() {
            public void a(List list, String str) {
                WritableArray writableArray;
                if (callback != null) {
                    boolean z = list == null || list.size() == 0;
                    Callback callback = callback;
                    Object[] objArr = new Object[2];
                    objArr[0] = Integer.valueOf(TextUtils.isEmpty(str) ? 0 : -1);
                    if (z) {
                        writableArray = null;
                    } else {
                        writableArray = Arguments.fromList(list);
                    }
                    objArr[1] = writableArray;
                    callback.invoke(objArr);
                }
            }
        });
    }

    public void getMessageHistory(String str, String str2, int i, final Callback callback) {
        MIMCApi.a(str, str2, i, new IHistoryCallback() {
            public void a(List list, String str) {
                WritableArray writableArray;
                if (callback != null) {
                    boolean z = list == null || list.size() == 0;
                    Callback callback = callback;
                    Object[] objArr = new Object[2];
                    objArr[0] = Integer.valueOf(TextUtils.isEmpty(str) ? 0 : -1);
                    if (z) {
                        writableArray = null;
                    } else {
                        writableArray = Arguments.fromList(list);
                    }
                    objArr[1] = writableArray;
                    callback.invoke(objArr);
                }
            }
        });
    }

    public void multiupdateExtra(ReadableMap readableMap, String str, final Callback callback) {
        MIMCApi.a(readableMap, str, new IMutiUpdateCallback() {
            public void a(String str, String str2) {
                Callback callback = callback;
                Object[] objArr = new Object[2];
                objArr[0] = Integer.valueOf(TextUtils.isEmpty(str2) ? 0 : -1);
                objArr[1] = str;
                callback.invoke(objArr);
            }
        });
    }
}
