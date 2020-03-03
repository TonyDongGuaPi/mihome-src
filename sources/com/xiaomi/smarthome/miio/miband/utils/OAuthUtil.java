package com.xiaomi.smarthome.miio.miband.utils;

import android.accounts.OperationCanceledException;
import android.app.Activity;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.Looper;
import com.xiaomi.account.openauth.XMAuthericationException;
import com.xiaomi.account.openauth.XiaomiOAuthConstants;
import com.xiaomi.account.openauth.XiaomiOAuthFuture;
import com.xiaomi.account.openauth.XiaomiOAuthResults;
import com.xiaomi.account.openauth.XiaomiOAuthorize;
import com.xiaomi.smarthome.framework.api.AsyncResponseCallback;
import java.io.IOException;

public class OAuthUtil {

    /* renamed from: a  reason: collision with root package name */
    public static final int f19477a = 37121;
    /* access modifiers changed from: private */
    public static final Handler b = new Handler(Looper.getMainLooper());

    public static void a(Activity activity, AsyncResponseCallback<XiaomiOAuthResults> asyncResponseCallback) {
        a(new XiaomiOAuthorize().setAppId(MiBandOAuthSetting.a()).setRedirectUrl(MiBandOAuthSetting.e()).setScope(MiBandOAuthSetting.f()).startGetAccessToken(activity), asyncResponseCallback);
    }

    public static void a(Activity activity, String str, String str2, String str3, AsyncResponseCallback<String> asyncResponseCallback) {
        a(new XiaomiOAuthorize().callOpenApi(activity, MiBandOAuthSetting.a(), XiaomiOAuthConstants.OPEN_API_PATH_PROFILE, str, str2, str3), asyncResponseCallback);
    }

    private static <V> void a(final XiaomiOAuthFuture<V> xiaomiOAuthFuture, final AsyncResponseCallback<V> asyncResponseCallback) {
        new AsyncTask<Void, Void, V>() {

            /* renamed from: a  reason: collision with root package name */
            Exception f19478a;

            /* access modifiers changed from: protected */
            /* renamed from: a */
            public V doInBackground(Void... voidArr) {
                try {
                    return xiaomiOAuthFuture.getResult();
                } catch (IOException e) {
                    this.f19478a = e;
                    return null;
                } catch (OperationCanceledException e2) {
                    this.f19478a = e2;
                    return null;
                } catch (XMAuthericationException e3) {
                    this.f19478a = e3;
                    return null;
                }
            }

            /* access modifiers changed from: protected */
            public void onPostExecute(final V v) {
                if (asyncResponseCallback != null) {
                    if (v != null) {
                        OAuthUtil.b.post(new Runnable() {
                            public void run() {
                                asyncResponseCallback.a(v);
                            }
                        });
                    } else if (this.f19478a != null) {
                        OAuthUtil.b.post(new Runnable() {
                            public void run() {
                                asyncResponseCallback.a(AnonymousClass1.this.f19478a instanceof OperationCanceledException ? 37121 : -1);
                            }
                        });
                    } else {
                        OAuthUtil.b.post(new Runnable() {
                            public void run() {
                                asyncResponseCallback.a(-1);
                            }
                        });
                    }
                }
            }
        }.execute(new Void[0]);
    }
}
