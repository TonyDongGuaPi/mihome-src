package com.xiaomi.jr.account;

import android.accounts.Account;
import android.content.Context;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.support.annotation.WorkerThread;
import android.text.TextUtils;
import com.xiaomi.accountsdk.account.XMPassport;
import com.xiaomi.accountsdk.account.data.XiaomiUserCoreInfo;
import com.xiaomi.accountsdk.request.AccessDeniedException;
import com.xiaomi.accountsdk.request.AuthenticationFailureException;
import com.xiaomi.accountsdk.request.CipherException;
import com.xiaomi.accountsdk.request.InvalidResponseException;
import com.xiaomi.accountsdk.request.SimpleRequestForAccount;
import com.xiaomi.accountsdk.utils.AccountLog;
import com.xiaomi.jr.account.MiFiAccountUtils;
import com.xiaomi.jr.common.utils.ThreadUtils;
import com.xiaomi.passport.accountmanager.MiAccountManager;
import com.xiaomi.passport.data.XMPassportInfo;
import com.xiaomi.passport.utils.AccountHelper;
import com.xiaomi.passport.utils.CUserIdUtil;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class MiFiAccountUtils {

    public interface FetchAccountAvatarCallback {
        void a(Drawable drawable);
    }

    @WorkerThread
    public static XiaomiUserCoreInfo a(Context context) {
        XMPassportInfo build = XMPassportInfo.build(context, "passportapi");
        ArrayList arrayList = new ArrayList();
        arrayList.add(XiaomiUserCoreInfo.Flag.BASE_INFO);
        arrayList.add(XiaomiUserCoreInfo.Flag.BIND_ADDRESS);
        arrayList.add(XiaomiUserCoreInfo.Flag.EXTRA_INFO);
        arrayList.add(XiaomiUserCoreInfo.Flag.SETTING_INFO);
        return a(context, build, (List<XiaomiUserCoreInfo.Flag>) arrayList);
    }

    @WorkerThread
    public static String b(Context context) {
        if (c(context) == null) {
            return null;
        }
        return new CUserIdUtil(context).getCUserId();
    }

    public static void a(Context context, int i, FetchAccountAvatarCallback fetchAccountAvatarCallback) {
        if (c(context) == null) {
            fetchAccountAvatarCallback.a((Drawable) null);
            AccountCache.f10271a.a();
            return;
        }
        ThreadUtils.b(new Runnable(context, fetchAccountAvatarCallback, i) {
            private final /* synthetic */ Context f$0;
            private final /* synthetic */ MiFiAccountUtils.FetchAccountAvatarCallback f$1;
            private final /* synthetic */ int f$2;

            {
                this.f$0 = r1;
                this.f$1 = r2;
                this.f$2 = r3;
            }

            public final void run() {
                MiFiAccountUtils.a(this.f$0, this.f$1, this.f$2);
            }
        });
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ void a(Context context, FetchAccountAvatarCallback fetchAccountAvatarCallback, int i) {
        XMPassportInfo build = XMPassportInfo.build(context, "passportapi");
        if (build == null) {
            fetchAccountAvatarCallback.a((Drawable) null);
            return;
        }
        try {
            XiaomiUserCoreInfo xiaomiUserCoreInfo = AccountHelper.getXiaomiUserCoreInfo(build, (String) null, (List<XiaomiUserCoreInfo.Flag>) null);
            if (xiaomiUserCoreInfo != null) {
                String str = xiaomiUserCoreInfo.avatarAddress;
                if (!TextUtils.isEmpty(str)) {
                    Drawable a2 = AccountCache.f10271a.a(str);
                    if (a2 != null) {
                        fetchAccountAvatarCallback.a(a2);
                        return;
                    }
                    InputStream stream = SimpleRequestForAccount.getAsStream(str, (Map<String, String>) null, (Map<String, String>) null).getStream();
                    Drawable a3 = AvatarUtils.a(BitmapFactory.decodeStream(stream), i);
                    if (a3 != null) {
                        AccountCache.f10271a.a(str, a2);
                    }
                    fetchAccountAvatarCallback.a(a3);
                    stream.close();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        fetchAccountAvatarCallback.a((Drawable) null);
    }

    private static XiaomiUserCoreInfo a(Context context, XMPassportInfo xMPassportInfo, List<XiaomiUserCoreInfo.Flag> list) {
        if (xMPassportInfo == null) {
            AccountLog.w("SysHelper", "passportInfo is null");
            return null;
        }
        int i = 0;
        while (i < 2) {
            try {
                return XMPassport.getXiaomiUserCoreInfo(xMPassportInfo, "passportapi", list);
            } catch (InvalidResponseException e) {
                AccountLog.e("SysHelper", "invalid response when get user info", e);
            } catch (CipherException e2) {
                AccountLog.e("SysHelper", "CipherException when get user info", e2);
            } catch (IOException e3) {
                AccountLog.e("SysHelper", "IOException when get user info", e3);
            } catch (AuthenticationFailureException e4) {
                AccountLog.e("SysHelper", "auth failure when get user info", e4);
                xMPassportInfo.refreshAuthToken(context);
                i++;
            } catch (AccessDeniedException e5) {
                AccountLog.e("SysHelper", "access denied when get user info", e5);
            }
        }
        return null;
    }

    public static Account c(Context context) {
        Account[] accountsByType = MiAccountManager.get(context).getAccountsByType("com.xiaomi");
        if (accountsByType.length != 0) {
            return accountsByType[0];
        }
        return null;
    }
}
