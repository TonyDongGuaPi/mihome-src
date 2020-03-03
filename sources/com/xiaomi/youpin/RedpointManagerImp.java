package com.xiaomi.youpin;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v4.content.LocalBroadcastManager;
import android.util.SparseIntArray;
import android.widget.TextView;
import cn.com.fmsh.communication.message.constants.Constants;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.xiaomi.miot.store.common.MiotStoreConstant;
import com.xiaomi.plugin.Callback;
import com.xiaomi.plugin.JsonParserUtils;
import com.xiaomi.plugin.Parser;
import com.xiaomi.plugin.RedpointManager;
import com.xiaomi.plugin.RequestParams;
import com.xiaomi.plugin.XmPluginHostApi;
import com.xiaomi.smarthome.application.SHApplication;
import com.xiaomi.smarthome.frame.core.CoreApi;
import com.xiaomi.youpin.mimcmsg.api.IRecentCallback;
import com.xiaomi.youpin.mimcmsg.api.MIMCApi;
import com.xiaomi.youpin.youpin_network.util.YouPinParamsUtil;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class RedpointManagerImp extends RedpointManager {
    private static final String d = "UnpaidOrderListCount";
    private static final String e = "PaidOrderListCount";
    private static final String f = "RefundOrderListCount";

    /* renamed from: a  reason: collision with root package name */
    Set<RedpointManager.RedpointListener> f23170a = new HashSet();
    boolean b = false;
    SparseIntArray c = new SparseIntArray();

    public RedpointManagerImp() {
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("action_on_login_success");
        intentFilter.addAction("action_on_logout");
        intentFilter.addAction(MiotStoreConstant.f);
        LocalBroadcastManager.getInstance(SHApplication.getAppContext()).registerReceiver(new BroadcastReceiver() {
            public void onReceive(Context context, Intent intent) {
                if (!CoreApi.a().D()) {
                    try {
                        if ("action_on_logout".equals(intent.getAction())) {
                            RedpointManagerImp.this.c.clear();
                            for (RedpointManager.RedpointListener onRefresh : RedpointManagerImp.this.f23170a) {
                                onRefresh.onRefresh();
                            }
                        } else if ("action_on_login_success".equals(intent.getAction())) {
                            RedpointManagerImp.this.update();
                        } else if (MiotStoreConstant.f.equals(intent.getAction())) {
                            RedpointManagerImp.this.update();
                        }
                    } catch (Throwable th) {
                        th.printStackTrace();
                    }
                }
            }
        }, intentFilter);
    }

    public void addRedPoint(RedpointManager.RedpointListener redpointListener) {
        this.f23170a.add(redpointListener);
    }

    public void removeRedPoint(RedpointManager.RedpointListener redpointListener) {
        this.f23170a.remove(redpointListener);
    }

    public int getRedPoint(int i) {
        if (i == 3) {
            return 0;
        }
        if (i == 6) {
            return getRedPoint(3) + getRedPoint(5);
        }
        if (i == 1) {
            return getBadgeCount();
        }
        return this.c.get(i);
    }

    public void setRedPointView(int i, TextView textView) {
        if (textView != null) {
            int redPoint = getRedPoint(i);
            if (i == 3 || i == 6 || i == 5 || i == 1) {
                if (redPoint <= 0) {
                    textView.setVisibility(8);
                } else {
                    textView.setVisibility(0);
                }
            } else if (redPoint <= 0) {
                textView.setVisibility(8);
            } else if (redPoint < 100) {
                textView.setVisibility(0);
                textView.setText("" + redPoint);
            } else {
                textView.setVisibility(0);
                textView.setText("99+");
            }
        }
    }

    public void setRedPoint(int i, int i2) {
        if (i != 3) {
            this.c.put(i, i2);
            for (RedpointManager.RedpointListener onRefresh : this.f23170a) {
                onRefresh.onRefresh();
            }
        }
    }

    public void setBadge(String str, String str2, int i) {
        RedPointManager4MessageCenter.a().a(str, str2, i);
    }

    public int getBadgeCount(String str, String str2) {
        return RedPointManager4MessageCenter.a().a(str, str2);
    }

    public int getBadgeCount(String str) {
        return RedPointManager4MessageCenter.a().a(str);
    }

    public int getBadgeCount() {
        return RedPointManager4MessageCenter.a().c();
    }

    public void addBadgeUpdateListener(RedpointManager.BadgeUpdateListener badgeUpdateListener) {
        RedPointManager4MessageCenter.a().a(badgeUpdateListener);
    }

    public void removeBadgeUpdateListener(RedpointManager.BadgeUpdateListener badgeUpdateListener) {
        RedPointManager4MessageCenter.a().b(badgeUpdateListener);
    }

    public void clearBadge() {
        RedPointManager4MessageCenter.a().b();
    }

    public void update() {
        if (!this.b) {
            ArrayList<RequestParams> arrayList = new ArrayList<>();
            arrayList.add(new RequestParams("Cart", "GetCount"));
            arrayList.add(new RequestParams(Constants.XMLNode.XMLMessage.MESSAGE, "Count"));
            JsonObject jsonObject = new JsonObject();
            jsonObject.addProperty("status", "3");
            RequestParams requestParams = new RequestParams("Order", "GetListCount", jsonObject);
            requestParams.key = d;
            arrayList.add(requestParams);
            JsonObject jsonObject2 = new JsonObject();
            jsonObject2.addProperty("status", "4");
            RequestParams requestParams2 = new RequestParams("Order", "GetListCount", jsonObject2);
            requestParams2.key = e;
            arrayList.add(requestParams2);
            JsonObject jsonObject3 = new JsonObject();
            jsonObject3.addProperty("status", "17");
            RequestParams requestParams3 = new RequestParams("Order", "GetListCount", jsonObject3);
            requestParams3.key = f;
            arrayList.add(requestParams3);
            JsonObject jsonObject4 = new JsonObject();
            jsonObject4.addProperty("page", "Homepage");
            arrayList.add(new RequestParams("Notice", "Reddot", jsonObject4));
            arrayList.add(new RequestParams("Order", "GetNeedCommentCount"));
            JsonObject jsonObject5 = new JsonObject();
            for (RequestParams requestParams4 : arrayList) {
                jsonObject5.add(requestParams4.key, requestParams4.toJsonObjectForMulti());
            }
            this.b = true;
            if (AppInitialManagerImp.a().c()) {
                MiCSHelper.a().e();
            }
            XmPluginHostApi.instance().sendMijiaShopRequest(YouPinParamsUtil.f23868a, jsonObject5, new Callback<SparseIntArray>() {
                /* renamed from: a */
                public void onCache(SparseIntArray sparseIntArray) {
                }

                /* renamed from: a */
                public void onSuccess(SparseIntArray sparseIntArray, boolean z) {
                    RedpointManagerImp.this.b = false;
                    if (sparseIntArray != null) {
                        RedpointManagerImp.this.c = sparseIntArray;
                        for (RedpointManager.RedpointListener onRefresh : RedpointManagerImp.this.f23170a) {
                            onRefresh.onRefresh();
                        }
                    }
                }

                public void onFailure(int i, String str) {
                    RedpointManagerImp.this.b = false;
                }
            }, new Parser<SparseIntArray>() {
                /* renamed from: a */
                public SparseIntArray parse(JsonElement jsonElement) {
                    if (jsonElement == null) {
                        return null;
                    }
                    SparseIntArray sparseIntArray = new SparseIntArray();
                    JsonObject asJsonObject = jsonElement.getAsJsonObject();
                    XmPluginHostApi.instance().setBadge("MCBT_OTHER", "", JsonParserUtils.getInt(asJsonObject, new String[]{"MessageCount", "data", "count"}));
                    sparseIntArray.put(2, JsonParserUtils.getInt(asJsonObject, new String[]{"CartGetCount", "data"}));
                    sparseIntArray.put(4, JsonParserUtils.getInt(asJsonObject, new String[]{RedpointManagerImp.d, "data"}));
                    sparseIntArray.put(8, JsonParserUtils.getInt(asJsonObject, new String[]{RedpointManagerImp.e, "data"}));
                    sparseIntArray.put(9, JsonParserUtils.getInt(asJsonObject, new String[]{RedpointManagerImp.f, "data"}));
                    sparseIntArray.put(5, JsonParserUtils.getInt(asJsonObject, new String[]{"NoticeReddot", "data", "invite", "data"}));
                    sparseIntArray.put(7, JsonParserUtils.getInt(asJsonObject, new String[]{"OrderGetNeedCommentCount", "data"}));
                    return sparseIntArray;
                }
            }, false);
            MIMCApi.a((IRecentCallback) null);
        }
    }
}
