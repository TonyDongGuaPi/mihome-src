package com.xiaomi.youpin.youpin_common;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.v4.content.LocalBroadcastManager;
import android.text.TextUtils;
import com.xiaomi.miot.store.api.ICallback;
import com.xiaomi.miot.store.api.IShareProvider;
import com.xiaomi.youpin.log.LogUtils;
import com.xiaomi.youpin.share.ShareAsyncTask;
import com.xiaomi.youpin.share.ShareEventUtil;
import com.xiaomi.youpin.share.ShareManager;
import com.xiaomi.youpin.share.ShareObject;
import com.xiaomi.youpin.share.model.ShareChannel;
import java.util.HashMap;

public class AppStoreShareInitUtil {

    /* renamed from: a  reason: collision with root package name */
    public static final int f23778a = 0;
    public static final int b = 2;
    private static final String c = "AppStoreShareInitUtil";
    private static final int d = 1;
    private static final String e = "result";
    private static final String f = "com.tencent.mm";
    private static final String g = "com.sina.weibo";
    private static final String h = "com.sina.weibog3";

    public static boolean a(Context context, String str) {
        if (context == null) {
            return false;
        }
        try {
            context.getPackageManager().getPackageInfo(str, 0);
            return true;
        } catch (PackageManager.NameNotFoundException unused) {
            return false;
        }
    }

    public static void a(ICallback iCallback, int i) {
        if (iCallback != null) {
            HashMap hashMap = new HashMap();
            hashMap.put("result", Integer.valueOf(i));
            iCallback.callback(hashMap);
        }
    }

    public static void a(StoreApiManager storeApiManager) {
        storeApiManager.a((IShareProvider) new WxShare(ShareChannel.b));
        storeApiManager.a((IShareProvider) new WxShare(ShareChannel.f23683a));
        storeApiManager.a((IShareProvider) new WeiboShare());
        storeApiManager.a((IShareProvider) new Clipboard());
    }

    public static class WxShare implements IShareProvider {

        /* renamed from: a  reason: collision with root package name */
        String f23783a;
        /* access modifiers changed from: private */
        public ShareReceiver b;

        public boolean onActivityResult(int i, int i2, Intent intent) {
            return false;
        }

        public WxShare(String str) {
            this.f23783a = str;
        }

        public String name() {
            return this.f23783a;
        }

        @SuppressLint({"StaticFieldLeak"})
        public void share(final Activity activity, String str, final ICallback iCallback) {
            if (!AppStoreShareInitUtil.a(activity.getApplicationContext(), "com.tencent.mm")) {
                AppStoreShareInitUtil.a(iCallback, 1);
                return;
            }
            new ShareAsyncTask() {
                /* access modifiers changed from: protected */
                /* renamed from: a */
                public void onPostExecute(ShareObject shareObject) {
                    boolean z;
                    if (!activity.isFinishing()) {
                        if (isCancelled()) {
                            AppStoreShareInitUtil.a(iCallback, 2);
                        } else if (shareObject == null) {
                            AppStoreShareInitUtil.a(iCallback, 2);
                        } else {
                            if (WxShare.this.b != null) {
                                LocalBroadcastManager.getInstance(activity.getApplicationContext()).unregisterReceiver(WxShare.this.b);
                            }
                            ShareReceiver unused = WxShare.this.b = new ShareReceiver(iCallback);
                            ShareEventUtil.e(activity, WxShare.this.b);
                            try {
                                if (TextUtils.equals(ShareChannel.f23683a, WxShare.this.f23783a)) {
                                    z = ShareManager.c(activity.getApplicationContext(), shareObject);
                                } else {
                                    z = ShareManager.b(activity.getApplicationContext(), shareObject);
                                }
                                if (z) {
                                }
                            } finally {
                                LocalBroadcastManager.getInstance(activity.getApplicationContext()).unregisterReceiver(WxShare.this.b);
                            }
                        }
                    }
                }

                /* access modifiers changed from: protected */
                public void onCancelled() {
                    super.onCancelled();
                    LocalBroadcastManager.getInstance(activity.getApplicationContext()).unregisterReceiver(WxShare.this.b);
                }
            }.execute(new String[]{str});
        }
    }

    public static class WeiboShare implements IShareProvider {
        /* access modifiers changed from: private */

        /* renamed from: a  reason: collision with root package name */
        public ShareReceiver f23781a;

        public String name() {
            return "weibo";
        }

        public boolean onActivityResult(int i, int i2, Intent intent) {
            return false;
        }

        @SuppressLint({"StaticFieldLeak"})
        public void share(final Activity activity, String str, final ICallback iCallback) {
            if (AppStoreShareInitUtil.a(activity.getApplicationContext(), "com.sina.weibo") || AppStoreShareInitUtil.a(activity.getApplicationContext(), AppStoreShareInitUtil.h)) {
                new ShareAsyncTask() {
                    /* access modifiers changed from: protected */
                    /* renamed from: a */
                    public void onPostExecute(ShareObject shareObject) {
                        if (isCancelled()) {
                            AppStoreShareInitUtil.a(iCallback, 2);
                        } else if (shareObject == null) {
                            AppStoreShareInitUtil.a(iCallback, 2);
                        } else {
                            if (WeiboShare.this.f23781a != null) {
                                LocalBroadcastManager.getInstance(activity.getApplicationContext()).unregisterReceiver(WeiboShare.this.f23781a);
                            }
                            ShareReceiver unused = WeiboShare.this.f23781a = new ShareReceiver(iCallback);
                            ShareEventUtil.e(activity, WeiboShare.this.f23781a);
                            ShareManager.a(activity.getApplicationContext(), shareObject, true);
                        }
                    }

                    /* access modifiers changed from: protected */
                    public void onCancelled() {
                        super.onCancelled();
                        LocalBroadcastManager.getInstance(activity.getApplicationContext()).unregisterReceiver(WeiboShare.this.f23781a);
                    }
                }.execute(new String[]{str});
                return;
            }
            AppStoreShareInitUtil.a(iCallback, 1);
        }
    }

    public static class Clipboard implements IShareProvider {
        public String name() {
            return ShareChannel.d;
        }

        public boolean onActivityResult(int i, int i2, Intent intent) {
            return false;
        }

        @SuppressLint({"StaticFieldLeak"})
        public void share(final Activity activity, String str, final ICallback iCallback) {
            new ShareAsyncTask() {
                /* access modifiers changed from: protected */
                /* renamed from: a */
                public void onPostExecute(ShareObject shareObject) {
                    if (isCancelled()) {
                        AppStoreShareInitUtil.a(iCallback, 2);
                    } else if (shareObject == null) {
                        AppStoreShareInitUtil.a(iCallback, 2);
                    } else {
                        ((ClipboardManager) activity.getApplicationContext().getSystemService(ShareChannel.d)).setText(shareObject.i());
                        AppStoreShareInitUtil.a(iCallback, 0);
                    }
                }
            }.execute(new String[]{str});
        }
    }

    private static class ShareReceiver extends BroadcastReceiver {

        /* renamed from: a  reason: collision with root package name */
        private ICallback f23780a;

        public ShareReceiver(ICallback iCallback) {
            this.f23780a = iCallback;
        }

        public void onReceive(Context context, Intent intent) {
            LogUtils.v(AppStoreShareInitUtil.c, "ShareReceiver onReceive.");
            ShareEventUtil.f(context, this);
            if (intent != null && !TextUtils.isEmpty(intent.getAction()) && ShareEventUtil.g.equals(intent.getAction())) {
                int b = ShareEventUtil.b(intent);
                String c = ShareEventUtil.c(intent);
                LogUtils.d(AppStoreShareInitUtil.c, "received share result,errorCode:" + b + ",errMsg:" + c);
                if (this.f23780a != null) {
                    AppStoreShareInitUtil.a(this.f23780a, b);
                }
            }
        }
    }
}
