package com.xiaomi.youpin.mimcmsg.api;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Base64;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.share.internal.MessengerShareContentUtility;
import com.google.gson.Gson;
import com.xiaomi.miot.store.api.MiotStoreApi;
import com.xiaomi.plugin.XmPluginHostApi;
import com.xiaomi.youpin.log.LogUtils;
import com.xiaomi.youpin.mimcmsg.MIMCMsgManager;
import com.xiaomi.youpin.mimcmsg.pojo.BaseBean;
import com.xiaomi.youpin.mimcmsg.pojo.HistoryResponse;
import com.xiaomi.youpin.mimcmsg.pojo.MIMCCustomMsg;
import com.xiaomi.youpin.mimcmsg.pojo.MIMCHistoryMsg;
import com.xiaomi.youpin.mimcmsg.pojo.RecentSessionResponse;
import com.xiaomi.youpin.network.bean.NetError;
import com.xiaomi.youpin.network.bean.NetRequest;
import com.xiaomi.youpin.youpin_network.YouPinHttpsApi;
import com.xiaomi.youpin.youpin_network.http.AsyncHandler;
import com.xiaomi.youpin.youpin_network.http.HttpApi;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import okhttp3.Call;
import okhttp3.Request;
import okhttp3.Response;

public class MIMCApi {

    /* renamed from: a  reason: collision with root package name */
    public static final String f23639a = "MIMC";
    public static final String b = "MSG_BADGE_TYPE";
    private static final String c = "https://mimc.chat.xiaomi.net/api/msg/p2p/queryOnCount/";
    private static final String d = (MIMCMsgManager.d() ? "http://st.youpin.mi.com/api/base/msgauth/token" : "https://m.xiaomiyoupin.com/api/base/msgauth/token");
    private static final String e = "https://mimc.chat.xiaomi.net/api/contact/v2?msgExtraFlag=true";
    private static final String f = "https://mimc.chat.xiaomi.net/api/msg/p2p/extra/multiupdate";
    /* access modifiers changed from: private */
    public static Gson g = new Gson();
    /* access modifiers changed from: private */
    public static ArrayList<Bundle> h = new ArrayList<>();

    public static <T extends BaseBean> void a(String str, String str2, HashMap<String, Object> hashMap, final Class<T> cls, final IHttpCallback<T> iHttpCallback) {
        int i;
        if (MIMCMsgManager.a().b() != null && !TextUtils.isEmpty(MIMCMsgManager.a().b().m())) {
            String str3 = null;
            if (hashMap != null && !hashMap.isEmpty()) {
                str3 = g.toJson((Object) hashMap);
            }
            String m = MIMCMsgManager.a().b().m();
            if ("GET".equals(str)) {
                i = 2;
            } else {
                boolean equals = "POST".equals(str);
                i = 1;
            }
            HttpApi.a(new NetRequest.Builder().a(i).b(1).b(str3).b(false).a(str2).a("token", m).a(), (AsyncHandler) new AsyncHandler() {
                public void a(Object obj, Response response) {
                }

                public void a(Call call, IOException iOException) {
                }

                public void a(Response response) {
                    if (response != null && response.body() != null && iHttpCallback != null) {
                        try {
                            BaseBean baseBean = (BaseBean) MIMCApi.g.fromJson(response.body().string(), cls);
                            if (baseBean == null || !baseBean.isSuccess()) {
                                iHttpCallback.a(baseBean.getCode(), baseBean.getMessage());
                            } else {
                                iHttpCallback.a(baseBean);
                            }
                        } catch (Exception e) {
                            iHttpCallback.a(0, e.getMessage());
                        }
                    }
                }

                public void a(NetError netError, Exception exc, Response response) {
                    if (iHttpCallback != null) {
                        iHttpCallback.a(0, exc.getMessage());
                    }
                }
            });
        }
    }

    public static String a() {
        String str = null;
        try {
            Response execute = YouPinHttpsApi.a().b().newCall(new Request.Builder().url(d).get().build()).execute();
            if (!(execute == null || execute.body() == null)) {
                str = execute.body().string();
            }
        } catch (IOException e2) {
            e2.printStackTrace();
        }
        LogUtils.d("MIMC", "token结果 : " + str);
        return str;
    }

    public static void a(String str, String str2, int i, final IHistoryCallback iHistoryCallback) {
        HashMap hashMap = new HashMap();
        hashMap.put("toAccount", XmPluginHostApi.instance().getAccountId());
        hashMap.put("fromAccount", str);
        hashMap.put("utcToTime", str2);
        hashMap.put("count", i + "");
        a("POST", c, hashMap, HistoryResponse.class, new IHttpCallback<HistoryResponse>() {
            public void a(HistoryResponse historyResponse) {
                ArrayList arrayList = new ArrayList();
                if (historyResponse != null && historyResponse.isSuccess()) {
                    List<MIMCCustomMsg> messages = (historyResponse.getData() == null || ((MIMCHistoryMsg) historyResponse.getData()).getMessages().isEmpty()) ? null : ((MIMCHistoryMsg) historyResponse.getData()).getMessages();
                    if (messages != null && messages.size() > 0) {
                        for (MIMCCustomMsg next : messages) {
                            Bundle bundle = new Bundle();
                            bundle.putString("sequence", next.getSequence());
                            bundle.putString(MessengerShareContentUtility.ATTACHMENT_PAYLOAD, new String(Base64.decode(next.getPayload(), 2)));
                            bundle.putString("timestamp", next.getTs() + "");
                            bundle.putString("fromAccount", next.getFromAccount());
                            bundle.putString("toAccount", next.getToAccount());
                            bundle.putString("bizType", next.getBizType());
                            bundle.putString("extra", next.getExtra());
                            arrayList.add(bundle);
                        }
                    }
                    LogUtils.d("MIMC", "请求的结果 : " + historyResponse.toString());
                }
                if (iHistoryCallback != null) {
                    iHistoryCallback.a(arrayList, (String) null);
                }
            }

            public void a(int i, String str) {
                if (iHistoryCallback != null) {
                    iHistoryCallback.a((List) null, TextUtils.isEmpty(str) ? "网络错误" : str);
                }
                LogUtils.d("MIMC", "错误信息 : " + str);
            }
        });
    }

    public static void a(ReadableMap readableMap, String str, final IMutiUpdateCallback iMutiUpdateCallback) {
        HashMap hashMap = new HashMap();
        hashMap.put("toAccount", XmPluginHostApi.instance().getAccountId());
        hashMap.put("fromAccount", str);
        hashMap.put("sequenceExtraMap", readableMap.toHashMap());
        a("POST", f, hashMap, BaseBean.class, new IHttpCallback<BaseBean>() {
            public void a(BaseBean baseBean) {
                MIMCApi.a((IRecentCallback) null);
                if (iMutiUpdateCallback != null) {
                    iMutiUpdateCallback.a(baseBean.toJson(), (String) null);
                }
            }

            public void a(int i, String str) {
                if (iMutiUpdateCallback != null) {
                    iMutiUpdateCallback.a((String) null, TextUtils.isEmpty(str) ? "网络错误" : str);
                }
                LogUtils.d("MIMC", "消息更新失败了，原因   : " + str);
            }
        });
    }

    public static void a(final IRecentCallback iRecentCallback) {
        if (iRecentCallback == null || h.isEmpty()) {
            a("GET", e, (HashMap<String, Object>) null, RecentSessionResponse.class, new IHttpCallback<RecentSessionResponse>() {
                public void a(RecentSessionResponse recentSessionResponse) {
                    if (!(recentSessionResponse == null || !recentSessionResponse.isSuccess() || recentSessionResponse.getData() == null)) {
                        LogUtils.d("MIMC", "请求的结果 : " + recentSessionResponse.toString());
                        int i = 0;
                        List<RecentSessionResponse.RecentItemBean> contacts = ((RecentSessionResponse.ContactsBean) recentSessionResponse.getData()).getContacts();
                        if (contacts != null && !contacts.isEmpty()) {
                            MIMCApi.h.clear();
                            for (RecentSessionResponse.RecentItemBean next : contacts) {
                                if (next != null) {
                                    Bundle bundle = new Bundle();
                                    bundle.putString("ID", next.getId());
                                    bundle.putString("timestamp", next.getTimestamp());
                                    bundle.putString("userType", next.getUserType());
                                    bundle.putString("extra", next.getExtra());
                                    if (next.getLastMessage() != null) {
                                        bundle.putString("fromUuid", next.getLastMessage().getFromUuid());
                                        bundle.putString("fromAccount", next.getLastMessage().getFromAccount());
                                        bundle.putString(MessengerShareContentUtility.ATTACHMENT_PAYLOAD, new String(Base64.decode(next.getLastMessage().getPayload(), 2)));
                                        bundle.putString("bizType", next.getLastMessage().getBizType());
                                        bundle.putString("sequence", next.getLastMessage().getSequence());
                                        bundle.putString("msgExtra", next.getLastMessage().getMsgExtra());
                                        if (next.getLastMessage().getExtraBean() == null || !next.getLastMessage().getExtraBean().isRead()) {
                                            i++;
                                        }
                                    }
                                    MIMCApi.h.add(bundle);
                                }
                            }
                            MIMCApi.b(i);
                        }
                    }
                    if (!MIMCApi.h.isEmpty()) {
                        MIMCApi.b((ArrayList<Bundle>) MIMCApi.h);
                    }
                    if (iRecentCallback != null) {
                        iRecentCallback.a(MIMCApi.h, (String) null);
                    }
                }

                public void a(int i, String str) {
                    LogUtils.d("MIMC", "错误信息 : " + str);
                    if (iRecentCallback != null) {
                        IRecentCallback iRecentCallback = iRecentCallback;
                        if (TextUtils.isEmpty(str)) {
                            str = "网络错误";
                        }
                        iRecentCallback.a((List) null, str);
                    }
                }
            });
        } else {
            iRecentCallback.a(h, (String) null);
        }
    }

    /* access modifiers changed from: private */
    public static void b(ArrayList<Bundle> arrayList) {
        HashMap hashMap = new HashMap();
        hashMap.put("messageList", arrayList);
        MiotStoreApi.a().sendJsEvent(MIMCMsgManager.e, hashMap);
    }

    /* access modifiers changed from: private */
    public static void b(int i) {
        XmPluginHostApi.instance().setBadge(b, "", i);
    }
}
