package com.xiaomi.youpin;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v4.content.LocalBroadcastManager;
import android.text.TextUtils;
import android.util.Base64;
import com.mics.core.MiCS;
import com.mics.core.business.ChatManager;
import com.mics.core.data.business.ChatList;
import com.mics.core.data.business.ChatParams;
import com.mics.core.data.response.BaseResponse;
import com.mics.core.ui.page.SettingsActivity;
import com.mics.network.GoCallback;
import com.mics.network.GoFailure;
import com.mics.util.GsonUtil;
import com.mics.widget.reminder.MessageReminder;
import com.mics.widget.util.MiCSToastManager;
import com.miui.tsmclient.entity.CardInfo;
import com.xiaomi.plugin.AccountInfo;
import com.xiaomi.plugin.Callback;
import com.xiaomi.plugin.XmPluginHostApi;
import com.xiaomi.smarthome.application.SHApplication;
import com.xiaomi.smarthome.core.server.internal.account.AccountManager;
import com.xiaomi.smarthome.family.FamilyMemberData;
import com.xiaomi.youpin.common.AppIdManager;
import com.xiaomi.youpin.log.LogUtils;
import com.xiaomi.youpin.youpin_common.StoreApiManager;
import com.xiaomi.youpin.youpin_common.StoreApiProvider;
import com.xiaomi.youpin.youpin_common.api.StoreBaseCallback;
import com.xiaomi.youpin.youpin_common.login.MiServiceTokenInfo;
import com.xiaomi.youpin.youpin_common.login.YouPinCookieUtils;
import com.xiaomi.youpin.youpin_constants.UrlConstants;
import com.xiaomi.youpin.youpin_network.YouPinHttpsApi;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

public class MiCSHelper {

    /* renamed from: a  reason: collision with root package name */
    private static final String f23150a = "com.xiaomi.youpin.action.on_logout";
    private static final String b = "com.xiaomi.youpin.action.on_login";
    private static volatile MiCSHelper g;
    private List<Bundle> c = new ArrayList();
    /* access modifiers changed from: private */
    public boolean d = false;
    /* access modifiers changed from: private */
    public String e = null;
    /* access modifiers changed from: private */
    public boolean f = false;

    public static MiCSHelper a() {
        if (g == null) {
            synchronized (MiCSHelper.class) {
                if (g == null) {
                    g = new MiCSHelper();
                }
            }
        }
        g.f();
        return g;
    }

    private void f() {
        if (TextUtils.isEmpty(this.e) && !this.f) {
            this.f = true;
            ChatManager.a().b((GoCallback<Object>) new GoCallback<Object>() {
                public void a(String str, Object obj) {
                    boolean unused = MiCSHelper.this.f = false;
                    if (!TextUtils.isEmpty(str)) {
                        String unused2 = MiCSHelper.this.e = str;
                    }
                }

                public void a(String str, GoFailure goFailure) {
                    boolean unused = MiCSHelper.this.f = false;
                }
            });
        }
    }

    private boolean g() {
        return TextUtils.equals("v1", this.e);
    }

    public static void b() {
        a();
    }

    public static void c() {
        StoreApiProvider b2 = StoreApiManager.a().b();
        if (b2 != null) {
            b2.a("ypsupport2", (StoreBaseCallback<MiServiceTokenInfo>) new StoreBaseCallback<MiServiceTokenInfo>() {
                public void onFail(int i, String str) {
                }

                /* renamed from: a */
                public void onSuccess(MiServiceTokenInfo miServiceTokenInfo) {
                    YouPinCookieUtils.a(miServiceTokenInfo);
                }
            });
        }
    }

    public void a(Context context, String str) {
        if (g()) {
            HashMap hashMap = new HashMap();
            hashMap.put(UrlConstants.customerService, Base64.encodeToString(str.getBytes(), 0));
            XmPluginHostApi.instance().openUrl(UrlConstants.generateUrlParams(UrlConstants.customerService, hashMap));
        } else if (XmPluginHostApi.instance().isDevMode()) {
            Intent intent = new Intent(context, SettingsActivity.class);
            intent.putExtra("data", str);
            context.startActivity(intent);
        } else {
            MiCS.a(context, (ChatParams) GsonUtil.a(str, ChatParams.class));
        }
    }

    public void a(final String str, String str2) {
        if (g()) {
            HashMap hashMap = new HashMap();
            hashMap.put("deleteChat", true);
            hashMap.put("brandId", str);
            hashMap.put("skillId", str2);
            XmPluginHostApi.instance().openUrl(UrlConstants.generateUrlParams(UrlConstants.serviceCenter, hashMap));
            return;
        }
        ChatManager.a().a(str, (GoCallback<BaseResponse>) new GoCallback<BaseResponse>() {
            public void a(String str, GoFailure goFailure) {
            }

            public void a(String str, BaseResponse baseResponse) {
                XmPluginHostApi.instance().setBadge("MCBT_SERVICE_ONLINE", str, 0);
                MiCSHelper.this.e();
            }
        });
    }

    public void d() {
        if (g()) {
            HashMap hashMap = new HashMap();
            hashMap.put("requestChatList", true);
            XmPluginHostApi.instance().openUrl(UrlConstants.generateUrlParams(UrlConstants.serviceCenter, hashMap));
            return;
        }
        XmPluginHostApi.instance().setSharedValue("send_chatMsg", "chatList", (Object) new ArrayList(this.c));
    }

    public void e() {
        if (g()) {
            HashMap hashMap = new HashMap();
            hashMap.put("requestChatListRemote", true);
            XmPluginHostApi.instance().openUrl(UrlConstants.generateUrlParams(UrlConstants.serviceCenter, hashMap));
        } else if (!this.d) {
            this.d = true;
            ChatManager.a().a((GoCallback<ChatList>) new GoCallback<ChatList>() {
                public void a(String str, ChatList chatList) {
                    boolean unused = MiCSHelper.this.d = false;
                    if (chatList != null && chatList.getData() != null) {
                        MiCSHelper.this.a(chatList.getData());
                        MiCSHelper.this.i();
                    }
                }

                public void a(String str, GoFailure goFailure) {
                    boolean unused = MiCSHelper.this.d = false;
                }
            });
        }
    }

    /* access modifiers changed from: private */
    public void a(List<ChatList.Data> list) {
        this.c.clear();
        for (ChatList.Data next : list) {
            int i = next.getMsgUnReadCount() > 0 ? 1 : 0;
            Bundle bundle = new Bundle();
            bundle.putString("roomId", next.getMsgRoomId());
            bundle.putString(CardInfo.KEY_CARD_GROUP_ID, next.getMsgMerchantId());
            bundle.putString("skillId", "");
            bundle.putString("avatar", next.getMsgMerchantLogo());
            bundle.putString(FamilyMemberData.d, next.getMsgTitle());
            bundle.putString("content", next.getMsgContent());
            bundle.putLong("time", next.getMsgUpdateTime());
            bundle.putInt("unreadCount", i);
            XmPluginHostApi.instance().setBadge("MCBT_SERVICE_ONLINE", next.getMsgRoomId(), i);
            this.c.add(bundle);
        }
        h();
    }

    private void h() {
        Collections.sort(this.c, $$Lambda$MiCSHelper$NlWhzzk_39Pn1DciCPh6kwg9PaM.INSTANCE);
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ int a(Bundle bundle, Bundle bundle2) {
        long j = bundle2.getLong("time") - bundle.getLong("time");
        if (j == 0) {
            return 0;
        }
        return j > 0 ? 1 : -1;
    }

    /* access modifiers changed from: private */
    public void i() {
        LogUtils.d(getClass().getName(), "nativeMsg_onChatUpdate");
        HashMap hashMap = new HashMap();
        hashMap.put("code", 0);
        XmPluginHostApi.instance().sendJsEvent("nativeMsg_onChatUpdate", hashMap);
    }

    private MiCSHelper() {
        j();
        k();
    }

    private void j() {
        boolean booleanValue = ((Boolean) XmPluginHostApi.instance().getPreferenceValue("isKefuTestEnv", false)).booleanValue();
        MiCS.a(SHApplication.getAppContext(), new MiCS.Config.Builder().e(booleanValue ? "cn.web.ypshop" : "cn.app.ypshop-new").f(booleanValue ? "ropS1C92pb" : "Ue14n5oStM").a(AppIdManager.a().c()).a(XmPluginHostApi.instance().isDevMode()).a());
        MiCS.a(YouPinHttpsApi.a().b());
        MiCS.a().a((MiCS.UrlDispatchInterceptor) $$Lambda$MiCSHelper$UyKdLsheupQNyduuV40lyzp5A4M.INSTANCE);
        MiCS.a().a((MiCS.GalleryDispatchInterceptor) new MiCS.GalleryDispatchInterceptor() {
            public void a(Activity activity, int i) {
                XmPluginHostApi.instance().openUrl(activity, UrlConstants.picture_pick, i);
            }

            public void a(List<String> list, int i, int i2, int i3, int i4, int i5) {
                StringBuilder sb = new StringBuilder();
                if (list != null) {
                    for (String append : list) {
                        sb.append(append);
                        sb.append(",");
                    }
                }
                HashMap hashMap = new HashMap();
                hashMap.put("images", sb.toString());
                hashMap.put("index", Integer.valueOf(i));
                XmPluginHostApi.instance().openUrl(UrlConstants.generateUrlParams(UrlConstants.yp_imagebrowser, hashMap));
            }

            public String[] a(Intent intent) {
                return intent.getStringArrayExtra("images");
            }
        });
        MiCSToastManager.a().a((MiCSToastManager.IMiCSToast) $$Lambda$MiCSHelper$JCA7wXeHEIDLHceDbek82LUf1p0.INSTANCE);
        MessageReminder.a().a((MessageReminder.IAction) new MessageReminder.IAction() {
            public void a(Activity activity, ChatParams chatParams) {
                if (chatParams.getMerchantId() == null) {
                    HashMap hashMap = new HashMap();
                    hashMap.put("type", UserMode.f23179a);
                    XmPluginHostApi.instance().startChat(activity, hashMap);
                    MessageReminder.a((Context) activity, (String) null);
                    return;
                }
                HashMap hashMap2 = new HashMap();
                hashMap2.put(UrlConstants.customerService, GsonUtil.a(chatParams));
                XmPluginHostApi.instance().startChat(activity, hashMap2);
            }

            public void a(String str, String str2, int i, PendingIntent pendingIntent) {
                XmPluginHostApi.instance().showNotification(str, str2, i, pendingIntent);
            }
        });
        MessageReminder.a().a((MessageReminder.OnMessageReminder) new MessageReminder.OnMessageReminder() {
            public final void onMessage(boolean z, boolean z2, String str, String str2, String str3, String str4) {
                MiCSHelper.this.a(z, z2, str, str2, str3, str4);
            }
        });
        MiCS.a().a((MiCS.OnMessageReadListener) new MiCS.OnMessageReadListener() {
            public final void onMessageRead(Object[] objArr) {
                MiCSHelper.this.a(objArr);
            }
        });
        m();
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void a(boolean z, boolean z2, String str, String str2, String str3, String str4) {
        if (!z) {
            e();
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void a(Object[] objArr) {
        e();
    }

    private void k() {
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("com.xiaomi.youpin.action.on_login");
        intentFilter.addAction("com.xiaomi.youpin.action.on_logout");
        LocalBroadcastManager.getInstance(SHApplication.getAppContext()).registerReceiver(new BroadcastReceiver() {
            public void onReceive(Context context, Intent intent) {
                if (intent != null && intent.getAction() != null) {
                    String action = intent.getAction();
                    char c = 65535;
                    int hashCode = action.hashCode();
                    if (hashCode != 1282107876) {
                        if (hashCode == 1288284111 && action.equals("com.xiaomi.youpin.action.on_login")) {
                            c = 0;
                        }
                    } else if (action.equals("com.xiaomi.youpin.action.on_logout")) {
                        c = 1;
                    }
                    switch (c) {
                        case 0:
                            MiCSHelper.this.l();
                            MiCSHelper.this.m();
                            return;
                        case 1:
                            MiCS.a().a((String) null, (String) null, (String) null, 0);
                            MessageReminder.a(context);
                            ChatManager.a().c();
                            return;
                        default:
                            return;
                    }
                }
            }
        }, intentFilter);
    }

    /* access modifiers changed from: private */
    public void l() {
        if (StoreApiManager.a() != null && StoreApiManager.a().b() != null && StoreApiManager.a().b().k()) {
            StoreApiManager.a().b().a("ypsupport2", (StoreBaseCallback<MiServiceTokenInfo>) new StoreBaseCallback<MiServiceTokenInfo>() {
                public void onFail(int i, String str) {
                }

                /* renamed from: a */
                public void onSuccess(MiServiceTokenInfo miServiceTokenInfo) {
                    if (miServiceTokenInfo != null) {
                        AccountManager.a().a(miServiceTokenInfo.f23799a, miServiceTokenInfo.c, miServiceTokenInfo.d, miServiceTokenInfo.f, miServiceTokenInfo.e);
                        YouPinCookieUtils.a(miServiceTokenInfo.f23799a, miServiceTokenInfo.b);
                        YouPinCookieUtils.b(miServiceTokenInfo.f23799a, miServiceTokenInfo.c);
                    }
                }
            });
        }
    }

    /* access modifiers changed from: private */
    public void m() {
        XmPluginHostApi.instance().getAccountInfo(new Callback<AccountInfo>() {
            /* renamed from: a */
            public void onCache(AccountInfo accountInfo) {
            }

            public void onFailure(int i, String str) {
            }

            /* renamed from: a */
            public void onSuccess(AccountInfo accountInfo, boolean z) {
                MiCS.a().a(accountInfo.mUserId, accountInfo.mNickName, accountInfo.mAvatarAddress, accountInfo.mGender == AccountInfo.Gender.MALE ? 0 : 1);
                boolean unused = MiCSHelper.this.d = false;
                MiCSHelper.this.e();
            }
        });
    }
}
