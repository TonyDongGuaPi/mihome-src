package com.xiaomi.smarthome.family.api;

import android.content.Context;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.util.Log;
import cn.com.fmsh.communication.message.constants.Constants;
import com.alipay.sdk.authjs.a;
import com.coloros.mcssdk.mode.CommandMessage;
import com.drew.metadata.mov.QuickTimeAtomTypes;
import com.facebook.react.modules.appstate.AppStateModule;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.miui.tsmclient.net.TSMAuthContants;
import com.payu.custombrowser.util.CBConstant;
import com.xiaomi.channel.sdk.ShareConstants;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.application.SHApplication;
import com.xiaomi.smarthome.core.entity.net.Crypto;
import com.xiaomi.smarthome.core.entity.net.KeyValuePair;
import com.xiaomi.smarthome.core.entity.net.NetRequest;
import com.xiaomi.smarthome.device.Device;
import com.xiaomi.smarthome.devicelistswitch.model.ModelOperations;
import com.xiaomi.smarthome.family.FamilyItemData;
import com.xiaomi.smarthome.family.FamilyMemberData;
import com.xiaomi.smarthome.family.api.entity.WXDeviceShareLinkResult;
import com.xiaomi.smarthome.frame.AsyncCallback;
import com.xiaomi.smarthome.frame.AsyncHandle;
import com.xiaomi.smarthome.frame.Error;
import com.xiaomi.smarthome.frame.ErrorCode;
import com.xiaomi.smarthome.frame.JsonParser;
import com.xiaomi.smarthome.frame.core.CoreApi;
import com.xiaomi.smarthome.framework.api.AsyncResponseCallback;
import com.xiaomi.smarthome.framework.api.TimeZoneApi;
import com.xiaomi.smarthome.framework.api.model.MyTimeZone;
import com.xiaomi.smarthome.framework.api.model.UserInfo;
import com.xiaomi.smarthome.framework.log.LogUtil;
import com.xiaomi.smarthome.framework.log.LogUtilGrey;
import com.xiaomi.smarthome.framework.openapi.ApiConst;
import com.xiaomi.smarthome.framework.page.TimezoneActivity;
import com.xiaomi.smarthome.globalsetting.GlobalSetting;
import com.xiaomi.smarthome.homeroom.HomeManager;
import com.xiaomi.smarthome.homeroom.UserInfoManager;
import com.xiaomi.smarthome.homeroom.model.Home;
import com.xiaomi.smarthome.homeroom.model.Room;
import com.xiaomi.smarthome.library.commonapi.SystemApi;
import com.xiaomi.smarthome.library.http.ClientUtil;
import com.xiaomi.smarthome.library.http.HttpApi;
import com.xiaomi.smarthome.library.http.Request;
import com.xiaomi.smarthome.library.http.async.AsyncHandler;
import com.xiaomi.smarthome.library.http.async.HttpAsyncHandle;
import com.xiaomi.smarthome.library.http.async.TextAsyncHandler;
import com.xiaomi.smarthome.messagecenter.shopmessage.ShopTypeMsgManager;
import com.xiaomi.smarthome.mibrain.anothernamesetting.AnotherNameManager;
import com.xiaomi.smarthome.mibrain.roomsetting.model.ControllableDevice;
import com.xiaomi.smarthome.miio.db.record.FamilyRecord;
import com.xiaomi.smarthome.miio.db.record.MessageRecord;
import com.xiaomi.smarthome.miio.db.record.MessageRecordShop;
import com.xiaomi.smarthome.miio.db.record.MessageRecordTypeList;
import com.xiaomi.smarthome.miio.message.p0.model.P0MessageList;
import com.xiaomi.smarthome.miio.page.ShareDeviceDetail;
import com.xiaomi.smarthome.miio.page.deviceophistory.DeviceOpHistoryGroupData;
import com.xiaomi.smarthome.miio.page.msgcentersetting.model.DeviceMsgSettingChild;
import com.xiaomi.smarthome.multikey.PowerMultikeyBean;
import com.xiaomi.smarthome.scene.location.model.UsrLocInfo;
import com.xiaomi.smarthome.setting.ServerRouteUtil;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;
import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Response;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import tv.danmaku.ijk.media.player.IjkMediaPlayer;

public class RemoteFamilyApi {

    /* renamed from: a  reason: collision with root package name */
    public static final String f15782a = "RemoteFamilyApi";
    private static final Object b = new Object();
    private static final int c = 300;
    private static RemoteFamilyApi d;
    private OkHttpClient e = ClientUtil.a();

    /* access modifiers changed from: private */
    public static /* synthetic */ JSONObject c(JSONObject jSONObject) throws JSONException {
        return jSONObject;
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ JSONObject d(JSONObject jSONObject) throws JSONException {
        return jSONObject;
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ JSONObject e(JSONObject jSONObject) throws JSONException {
        return jSONObject;
    }

    private RemoteFamilyApi() {
    }

    public static RemoteFamilyApi a() {
        if (d == null) {
            synchronized (b) {
                if (d == null) {
                    d = new RemoteFamilyApi();
                }
            }
        }
        return d;
    }

    public AsyncHandle a(Context context, String str, AsyncCallback<UserInfo, Error> asyncCallback) {
        ArrayList arrayList = new ArrayList();
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("id", str);
        } catch (JSONException unused) {
        }
        arrayList.add(new KeyValuePair("data", jSONObject.toString()));
        return CoreApi.a().a(context, new NetRequest.Builder().a("POST").b("/home/profile").b((List<KeyValuePair>) arrayList).a(), new JsonParser<UserInfo>() {
            /* renamed from: a */
            public UserInfo parse(JSONObject jSONObject) throws JSONException {
                return UserInfo.a(jSONObject);
            }
        }, Crypto.RC4, asyncCallback);
    }

    public AsyncHandle a(Context context, List<Long> list, AsyncCallback<List<UserInfo>, Error> asyncCallback) {
        ArrayList arrayList = new ArrayList();
        JSONObject jSONObject = new JSONObject();
        try {
            JSONArray jSONArray = new JSONArray();
            if (list != null) {
                int i = 100;
                if (list.size() <= 100) {
                    i = list.size();
                }
                for (int i2 = 0; i2 < i; i2++) {
                    jSONArray.put(list.get(i2) + "");
                }
            }
            jSONObject.put("uids", jSONArray);
        } catch (JSONException unused) {
        }
        arrayList.add(new KeyValuePair("data", jSONObject.toString()));
        return CoreApi.a().a(context, new NetRequest.Builder().a("POST").b("/home/profiles").b((List<KeyValuePair>) arrayList).a(), new JsonParser<List<UserInfo>>() {
            /* renamed from: a */
            public List<UserInfo> parse(JSONObject jSONObject) throws JSONException {
                JSONArray optJSONArray;
                ArrayList arrayList = new ArrayList();
                Context appContext = SHApplication.getAppContext();
                appContext.getSharedPreferences("shared_user_info_list_" + CoreApi.a().s(), 0).edit().putString("content", jSONObject.toString()).commit();
                if (jSONObject.isNull("list") || (optJSONArray = jSONObject.optJSONArray("list")) == null) {
                    return arrayList;
                }
                for (int i = 0; i < optJSONArray.length(); i++) {
                    UserInfo a2 = UserInfo.a(optJSONArray.optJSONObject(i));
                    if (a2 != null && !TextUtils.isEmpty(a2.f16462a) && !TextUtils.isEmpty(a2.b) && !TextUtils.isEmpty(a2.f16462a) && !TextUtils.equals("-1", a2.f16462a)) {
                        arrayList.add(a2);
                    }
                }
                return arrayList;
            }
        }, Crypto.RC4, asyncCallback);
    }

    @Deprecated
    public AsyncHandle b(Context context, String str, AsyncCallback<JSONObject, Error> asyncCallback) {
        ArrayList arrayList = new ArrayList();
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("name", IjkMediaPlayer.OnNativeInvokeListener.ARG_FAMILIY);
            jSONObject.put("display_name", IjkMediaPlayer.OnNativeInvokeListener.ARG_FAMILIY);
        } catch (JSONException unused) {
        }
        arrayList.add(new KeyValuePair("data", jSONObject.toString()));
        return CoreApi.a().a(context, new NetRequest.Builder().a("POST").b("/group/new").b((List<KeyValuePair>) arrayList).a(), new JsonParser<JSONObject>() {
            /* renamed from: a */
            public JSONObject parse(JSONObject jSONObject) throws JSONException {
                return jSONObject;
            }
        }, Crypto.RC4, asyncCallback);
    }

    public AsyncHandle a(Context context, final Map<String, Long> map, AsyncCallback<JSONArray, Error> asyncCallback) {
        if (map == null || map.isEmpty()) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        JSONObject jSONObject = new JSONObject();
        try {
            Iterator<String> it = map.keySet().iterator();
            if (it.hasNext()) {
                jSONObject.put("begin_at", map.get(it.next()));
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        arrayList.add(new KeyValuePair("data", jSONObject.toString()));
        return CoreApi.a().a(context, new NetRequest.Builder().a("GET").b("/v2/message/v2/check_new_msg").b((List<KeyValuePair>) arrayList).a(), new JsonParser<JSONArray>() {
            /* renamed from: a */
            public JSONArray parse(JSONObject jSONObject) throws JSONException {
                boolean optBoolean = jSONObject.optBoolean("result", false);
                JSONArray jSONArray = new JSONArray();
                for (String put : map.keySet()) {
                    JSONObject jSONObject2 = new JSONObject();
                    jSONObject2.put("type", put);
                    jSONObject2.put("count", optBoolean ? 1 : 0);
                    jSONArray.put(jSONObject2);
                }
                return jSONArray;
            }
        }, Crypto.RC4, asyncCallback);
    }

    public AsyncHandle a(Context context, List<String> list, List<String> list2, AsyncCallback<String, Error> asyncCallback) {
        ArrayList arrayList = new ArrayList();
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put(CommandMessage.COMMAND, "share_to_family_request");
            JSONArray jSONArray = new JSONArray();
            for (String put : list) {
                jSONArray.put(put);
            }
            jSONObject.put("dids", jSONArray);
            JSONArray jSONArray2 = new JSONArray();
            for (String put2 : list2) {
                jSONArray2.put(put2);
            }
            jSONObject.put("userids", jSONArray2);
        } catch (JSONException unused) {
        }
        arrayList.add(new KeyValuePair("data", jSONObject.toString()));
        return CoreApi.a().a(context, new NetRequest.Builder().a("POST").b("/share/share_to_family_request").b((List<KeyValuePair>) arrayList).a(), new JsonParser<String>() {
            /* renamed from: a */
            public String parse(JSONObject jSONObject) throws JSONException {
                return jSONObject.toString();
            }
        }, Crypto.RC4, asyncCallback);
    }

    public AsyncHandle a(Context context, long j, AsyncCallback<ShopTypeMsgManager.ShopMessageData, Error> asyncCallback) {
        ArrayList arrayList = new ArrayList();
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("timestamp", j);
            JSONObject jSONObject2 = new JSONObject();
            jSONObject2.put("parameters", jSONObject);
            jSONObject2.put("action", "List");
            jSONObject2.put("model", Constants.XMLNode.XMLMessage.MESSAGE);
            JSONObject jSONObject3 = new JSONObject();
            jSONObject3.put(Constants.XMLNode.XMLMessage.MESSAGE, jSONObject2);
            arrayList.add(new KeyValuePair("data", jSONObject3.toString()));
        } catch (Exception | JSONException unused) {
        }
        return CoreApi.a().a(context, new NetRequest.Builder().a("POST").b("/shop/gpipe").b((List<KeyValuePair>) arrayList).a(), new JsonParser<ShopTypeMsgManager.ShopMessageData>() {
            /* renamed from: a */
            public ShopTypeMsgManager.ShopMessageData parse(JSONObject jSONObject) throws JSONException {
                JSONObject optJSONObject;
                ShopTypeMsgManager.ShopMessageData shopMessageData = new ShopTypeMsgManager.ShopMessageData();
                ArrayList arrayList = new ArrayList();
                shopMessageData.f10502a = arrayList;
                shopMessageData.b = jSONObject.optLong("timestamp");
                shopMessageData.c = jSONObject.optInt("count");
                JSONArray optJSONArray = jSONObject.optJSONArray("messages");
                int length = optJSONArray.length();
                for (int i = 0; i < length; i++) {
                    JSONObject optJSONObject2 = optJSONArray.optJSONObject(i);
                    MessageRecordShop messageRecordShop = new MessageRecordShop();
                    messageRecordShop.userId = optJSONObject2.optString("uid");
                    messageRecordShop.msgId = optJSONObject2.optString("msg_id");
                    messageRecordShop.senderUserId = optJSONObject2.optString("sender_uid");
                    messageRecordShop.messageType = optJSONObject2.optString("type");
                    messageRecordShop.receiveTime = Long.valueOf(optJSONObject2.optString("last_modify")).longValue();
                    messageRecordShop.title = optJSONObject2.optString("title");
                    messageRecordShop.content = optJSONObject2.optString("content");
                    messageRecordShop.img_url = optJSONObject2.optString("img_url");
                    if (!optJSONObject2.isNull("params") && (optJSONObject = optJSONObject2.optJSONObject("params")) != null) {
                        messageRecordShop.params = optJSONObject.toString();
                        messageRecordShop.valid = optJSONObject2.optString(TSMAuthContants.PARAM_VALID);
                        messageRecordShop.is_new = optJSONObject2.optString("is_new");
                        messageRecordShop.is_read = optJSONObject2.optString("is_read");
                        if (optJSONObject2.has("status")) {
                            messageRecordShop.status = optJSONObject2.optInt("status");
                        }
                        arrayList.add(messageRecordShop);
                    }
                }
                MessageRecordShop.deleteAll();
                MessageRecordShop.batchInsert(arrayList);
                return shopMessageData;
            }
        }, Crypto.RC4, asyncCallback);
    }

    private MessageRecord a(JSONObject jSONObject) {
        JSONObject optJSONObject;
        MessageRecord messageRecord = new MessageRecord();
        messageRecord.userId = jSONObject.optString("uid");
        messageRecord.msgId = jSONObject.optString("msg_id");
        messageRecord.senderUserId = jSONObject.optString("sender_uid");
        messageRecord.messageType = jSONObject.optString("type");
        messageRecord.receiveTime = Long.valueOf(jSONObject.optString("last_modify")).longValue();
        messageRecord.title = jSONObject.optString("title");
        messageRecord.content = jSONObject.optString("content");
        messageRecord.img_url = jSONObject.optString("img_url");
        messageRecord.valid = jSONObject.optString(TSMAuthContants.PARAM_VALID);
        messageRecord.is_new = jSONObject.optString("is_new");
        messageRecord.is_read = jSONObject.optString("is_read");
        if (jSONObject.has("status")) {
            messageRecord.status = jSONObject.optInt("status");
        }
        if (!jSONObject.isNull("params") && (optJSONObject = jSONObject.optJSONObject("params")) != null) {
            messageRecord.params = optJSONObject.toString();
        }
        return messageRecord;
    }

    public AsyncHandle b(Context context, long j, AsyncCallback<ShopTypeMsgManager.ShopMessageData, Error> asyncCallback) {
        ArrayList arrayList = new ArrayList();
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("timestamp", j);
            JSONObject jSONObject2 = new JSONObject();
            jSONObject2.put("parameters", jSONObject);
            jSONObject2.put("action", "List");
            jSONObject2.put("model", Constants.XMLNode.XMLMessage.MESSAGE);
            JSONObject jSONObject3 = new JSONObject();
            jSONObject3.put(Constants.XMLNode.XMLMessage.MESSAGE, jSONObject2);
            arrayList.add(new KeyValuePair("data", jSONObject3.toString()));
        } catch (Exception | JSONException unused) {
        }
        return CoreApi.a().a(context, new NetRequest.Builder().a("POST").b("/shop/gpipe").b((List<KeyValuePair>) arrayList).a(), new JsonParser<ShopTypeMsgManager.ShopMessageData>() {
            /* renamed from: a */
            public ShopTypeMsgManager.ShopMessageData parse(JSONObject jSONObject) throws JSONException {
                JSONObject optJSONObject;
                ShopTypeMsgManager.ShopMessageData shopMessageData = new ShopTypeMsgManager.ShopMessageData();
                ArrayList arrayList = new ArrayList();
                shopMessageData.f10502a = arrayList;
                shopMessageData.b = jSONObject.optLong("timestamp");
                shopMessageData.c = jSONObject.optInt("count");
                JSONArray optJSONArray = jSONObject.optJSONArray("messages");
                int length = optJSONArray.length();
                for (int i = 0; i < length; i++) {
                    JSONObject optJSONObject2 = optJSONArray.optJSONObject(i);
                    MessageRecordShop messageRecordShop = new MessageRecordShop();
                    messageRecordShop.userId = optJSONObject2.optString("uid");
                    messageRecordShop.msgId = optJSONObject2.optString("msg_id");
                    messageRecordShop.senderUserId = optJSONObject2.optString("sender_uid");
                    messageRecordShop.messageType = optJSONObject2.optString("type");
                    messageRecordShop.receiveTime = Long.valueOf(optJSONObject2.optString("last_modify")).longValue();
                    messageRecordShop.title = optJSONObject2.optString("title");
                    messageRecordShop.content = optJSONObject2.optString("content");
                    messageRecordShop.img_url = optJSONObject2.optString("img_url");
                    if (!optJSONObject2.isNull("params") && (optJSONObject = optJSONObject2.optJSONObject("params")) != null) {
                        messageRecordShop.params = optJSONObject.toString();
                        messageRecordShop.valid = optJSONObject2.optString(TSMAuthContants.PARAM_VALID);
                        messageRecordShop.is_new = optJSONObject2.optString("is_new");
                        messageRecordShop.is_read = optJSONObject2.optString("is_read");
                        if (optJSONObject2.has("status")) {
                            messageRecordShop.status = optJSONObject2.optInt("status");
                        }
                        arrayList.add(messageRecordShop);
                    }
                }
                MessageRecordShop.deleteAll();
                MessageRecordShop.batchInsert(arrayList);
                return shopMessageData;
            }
        }, Crypto.RC4, asyncCallback);
    }

    public AsyncHandle a(Context context, int i, String str, String[] strArr, AsyncCallback<Void, Error> asyncCallback) {
        if (strArr == null || strArr.length == 0) {
            if (asyncCallback != null) {
                asyncCallback.sendFailureMessage(new Error(-9999, ""));
            }
            return new AsyncHandle(null);
        }
        ArrayList arrayList = new ArrayList();
        JSONObject jSONObject = new JSONObject();
        JSONArray jSONArray = new JSONArray();
        for (String parseLong : strArr) {
            jSONArray.put(Long.parseLong(parseLong));
        }
        try {
            jSONObject.put("msg_ids", jSONArray);
            jSONObject.put("type", i);
            jSONObject.put("did", str);
        } catch (JSONException unused) {
        }
        arrayList.add(new KeyValuePair("data", jSONObject.toString()));
        return CoreApi.a().a(context, new NetRequest.Builder().a("POST").b("/v2/message/v2/delete").b((List<KeyValuePair>) arrayList).a(), (JsonParser) null, Crypto.RC4, asyncCallback);
    }

    public AsyncHandle b(Context context, List<String> list, List<String> list2, AsyncCallback<Void, Error> asyncCallback) {
        if (list == null || list.size() == 0 || list2 == null || list2.size() == 0 || list.size() != list2.size()) {
            if (asyncCallback != null) {
                asyncCallback.sendFailureMessage(new Error(-9999, ""));
            }
            return new AsyncHandle(null);
        }
        ArrayList arrayList = new ArrayList();
        JSONObject jSONObject = new JSONObject();
        JSONArray jSONArray = new JSONArray();
        for (int i = 0; i < list.size(); i++) {
            String str = list.get(i);
            String str2 = list2.get(i);
            JSONObject jSONObject2 = new JSONObject();
            try {
                jSONObject2.put("msgId", str);
                jSONObject2.put("did", str2);
                jSONArray.put(jSONObject2);
            } catch (JSONException e2) {
                e2.printStackTrace();
            }
        }
        try {
            jSONObject.put("devList", jSONArray);
        } catch (JSONException unused) {
        }
        arrayList.add(new KeyValuePair("data", jSONObject.toString()));
        return CoreApi.a().a(context, new NetRequest.Builder().a("POST").b("/share/batch_del_share_list").b((List<KeyValuePair>) arrayList).a(), (JsonParser) null, Crypto.RC4, asyncCallback);
    }

    public AsyncHandle c(Context context, long j, AsyncCallback<Void, Error> asyncCallback) {
        ArrayList arrayList = new ArrayList();
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("timestamp", j);
        } catch (JSONException unused) {
        }
        arrayList.add(new KeyValuePair("data", jSONObject.toString()));
        return CoreApi.a().a(context, new NetRequest.Builder().a("POST").b("/message/clear").b((List<KeyValuePair>) arrayList).a(), (JsonParser) null, Crypto.RC4, asyncCallback);
    }

    public AsyncHandle a(Context context, List<String> list, List<String> list2, String str, AsyncCallback<JSONObject, Error> asyncCallback) {
        if (list != null && list.size() != 0) {
            ArrayList arrayList = new ArrayList();
            JSONObject jSONObject = new JSONObject();
            try {
                JSONArray jSONArray = new JSONArray();
                for (String put : list) {
                    jSONArray.put(put);
                }
                jSONObject.put("didList", jSONArray);
                JSONArray jSONArray2 = new JSONArray();
                for (String put2 : list2) {
                    jSONArray2.put(put2);
                }
                jSONObject.put("receiver", jSONArray2);
                if (!TextUtils.isEmpty(str)) {
                    jSONObject.put("type", str);
                }
            } catch (JSONException unused) {
            }
            arrayList.add(new KeyValuePair("data", jSONObject.toString()));
            return CoreApi.a().a(context, new NetRequest.Builder().a("POST").b("/share/share_request_batch").b((List<KeyValuePair>) arrayList).a(), new JsonParser<JSONObject>() {
                /* renamed from: a */
                public JSONObject parse(JSONObject jSONObject) throws JSONException {
                    return jSONObject;
                }
            }, Crypto.RC4, asyncCallback);
        } else if (asyncCallback == null) {
            return null;
        } else {
            asyncCallback.onFailure(new Error(ErrorCode.ERROR_UNKNOWN_ERROR.getCode(), ""));
            return null;
        }
    }

    public AsyncHandle a(Context context, String str, String str2, String str3, AsyncCallback<String, Error> asyncCallback) {
        ArrayList arrayList = new ArrayList();
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put(CommandMessage.COMMAND, "share_request");
            jSONObject.put("did", str);
            jSONObject.put("userid", str2);
            if (!TextUtils.isEmpty(str3)) {
                jSONObject.put("type", str3);
            }
        } catch (JSONException unused) {
        }
        arrayList.add(new KeyValuePair("data", jSONObject.toString()));
        return CoreApi.a().a(context, new NetRequest.Builder().a("POST").b("/share/share_request").b((List<KeyValuePair>) arrayList).a(), new JsonParser<String>() {
            /* renamed from: a */
            public String parse(JSONObject jSONObject) throws JSONException {
                return jSONObject.toString();
            }
        }, Crypto.RC4, asyncCallback);
    }

    public AsyncHandle a(Context context, String str, String str2, int i, AsyncCallback<Void, Error> asyncCallback) {
        ArrayList arrayList = new ArrayList();
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("msg_id", str2);
            jSONObject.put("inv_id", i);
            jSONObject.put("value", str);
        } catch (JSONException unused) {
        }
        arrayList.add(new KeyValuePair("data", jSONObject.toString()));
        return CoreApi.a().a(context, new NetRequest.Builder().a("POST").b("/share/share_response").b((List<KeyValuePair>) arrayList).a(), (JsonParser) null, Crypto.RC4, asyncCallback);
    }

    public AsyncHandle a(Context context, int i, String str, AsyncCallback<List<ShareDeviceDetail.ShareUser>, Error> asyncCallback) {
        ArrayList arrayList = new ArrayList();
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("pid", i);
            jSONObject.put("did", str);
        } catch (JSONException unused) {
        }
        arrayList.add(new KeyValuePair("data", jSONObject.toString()));
        return CoreApi.a().a(context, new NetRequest.Builder().a("POST").b("/share/get_share_user").b((List<KeyValuePair>) arrayList).a(), new JsonParser<List<ShareDeviceDetail.ShareUser>>() {
            /* renamed from: a */
            public List<ShareDeviceDetail.ShareUser> parse(JSONObject jSONObject) throws JSONException {
                ArrayList arrayList = new ArrayList();
                JSONArray optJSONArray = jSONObject.optJSONArray("list");
                int length = optJSONArray.length();
                for (int i = 0; i < length; i++) {
                    JSONObject jSONObject2 = (JSONObject) optJSONArray.get(i);
                    ShareDeviceDetail.ShareUser shareUser = new ShareDeviceDetail.ShareUser();
                    shareUser.f19679a = jSONObject2.optString("userid");
                    shareUser.b = jSONObject2.optString(FamilyMemberData.d);
                    if (TextUtils.isEmpty(shareUser.b)) {
                        shareUser.b = shareUser.f19679a;
                    }
                    shareUser.c = jSONObject2.optString("icon");
                    shareUser.d = jSONObject2.optLong("sharetime");
                    shareUser.e = jSONObject2.optInt("status");
                    if (!jSONObject2.isNull("isReadOnly")) {
                        shareUser.f = jSONObject2.optBoolean("isReadOnly") ? 1 : 2;
                    }
                    arrayList.add(shareUser);
                }
                return arrayList;
            }
        }, Crypto.RC4, asyncCallback);
    }

    public AsyncHandle a(Context context, AsyncCallback<HashMap<String, List<ShareDeviceDetail.ShareUser>>, Error> asyncCallback) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new KeyValuePair("data", new JSONObject().toString()));
        return CoreApi.a().a(context, new NetRequest.Builder().a("POST").b("/share/device_share_list").b((List<KeyValuePair>) arrayList).a(), new JsonParser<HashMap<String, List<ShareDeviceDetail.ShareUser>>>() {
            /* renamed from: a */
            public HashMap<String, List<ShareDeviceDetail.ShareUser>> parse(JSONObject jSONObject) throws JSONException {
                HashMap<String, List<ShareDeviceDetail.ShareUser>> hashMap = new HashMap<>();
                JSONObject optJSONObject = jSONObject.optJSONObject("list");
                if (optJSONObject == null) {
                    JSONArray optJSONArray = jSONObject.optJSONArray("list");
                    if (optJSONArray != null) {
                        optJSONArray.length();
                    }
                    return hashMap;
                }
                Iterator<String> keys = optJSONObject.keys();
                while (keys.hasNext()) {
                    String next = keys.next();
                    List list = hashMap.get(next);
                    if (list == null) {
                        list = new ArrayList();
                        hashMap.put(next, list);
                    }
                    JSONArray jSONArray = optJSONObject.getJSONArray(next);
                    int length = jSONArray.length();
                    for (int i = 0; i < length; i++) {
                        JSONObject jSONObject2 = (JSONObject) jSONArray.get(i);
                        ShareDeviceDetail.ShareUser shareUser = new ShareDeviceDetail.ShareUser();
                        shareUser.f19679a = jSONObject2.optString("userid");
                        shareUser.b = jSONObject2.optString(FamilyMemberData.d);
                        if (TextUtils.isEmpty(shareUser.b)) {
                            shareUser.b = shareUser.f19679a;
                        }
                        shareUser.c = jSONObject2.optString("icon");
                        shareUser.d = jSONObject2.optLong("mtime");
                        list.add(shareUser);
                    }
                }
                return hashMap;
            }
        }, Crypto.RC4, asyncCallback);
    }

    public AsyncHandle a(Context context, List<String> list, int i, String str, AsyncCallback<List<String>, Error> asyncCallback) {
        JSONArray jSONArray = new JSONArray();
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("did", str);
            jSONObject.put("pid", i);
            JSONArray jSONArray2 = new JSONArray();
            for (String put : list) {
                jSONArray2.put(put);
            }
            jSONObject.put("userid", jSONArray2);
            jSONArray.put(jSONObject);
        } catch (JSONException unused) {
        }
        ArrayList arrayList = new ArrayList();
        JSONObject jSONObject2 = new JSONObject();
        try {
            jSONObject2.put("list", jSONArray);
            arrayList.add(new KeyValuePair("data", jSONObject2.toString()));
            return CoreApi.a().a(context, new NetRequest.Builder().a("POST").b("/share/delete_user").b((List<KeyValuePair>) arrayList).a(), new JsonParser<List<String>>() {
                /* renamed from: a */
                public List<String> parse(JSONObject jSONObject) throws JSONException {
                    ArrayList arrayList = new ArrayList();
                    JSONArray optJSONArray = jSONObject.optJSONArray("list");
                    int length = optJSONArray.length();
                    for (int i = 0; i < length; i++) {
                        JSONObject jSONObject2 = (JSONObject) optJSONArray.get(i);
                        String optString = jSONObject2.optString("did");
                        if (jSONObject2.optInt("ret") != 1) {
                            arrayList.add(optString);
                        }
                    }
                    return arrayList;
                }
            }, Crypto.RC4, asyncCallback);
        } catch (JSONException unused2) {
            if (asyncCallback != null) {
                asyncCallback.sendFailureMessage(new Error(ErrorCode.INVALID.getCode(), ""));
            }
            return new AsyncHandle(null);
        }
    }

    public AsyncHandle a(Context context, String str, int i, String str2, AsyncCallback<List<String>, Error> asyncCallback) {
        JSONArray jSONArray = new JSONArray();
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("did", str2);
            jSONObject.put("pid", i);
            JSONArray jSONArray2 = new JSONArray();
            jSONArray2.put(str);
            jSONObject.put("userid", jSONArray2);
            jSONArray.put(jSONObject);
        } catch (JSONException unused) {
        }
        ArrayList arrayList = new ArrayList();
        JSONObject jSONObject2 = new JSONObject();
        try {
            jSONObject2.put("list", jSONArray);
            arrayList.add(new KeyValuePair("data", jSONObject2.toString()));
            return CoreApi.a().a(context, new NetRequest.Builder().a("POST").b("/share/delete_user").b((List<KeyValuePair>) arrayList).a(), new JsonParser<List<String>>() {
                /* renamed from: a */
                public List<String> parse(JSONObject jSONObject) throws JSONException {
                    ArrayList arrayList = new ArrayList();
                    JSONArray optJSONArray = jSONObject.optJSONArray("list");
                    int length = optJSONArray.length();
                    for (int i = 0; i < length; i++) {
                        JSONObject jSONObject2 = (JSONObject) optJSONArray.get(i);
                        String optString = jSONObject2.optString("did");
                        if (jSONObject2.optInt("ret") != 1) {
                            arrayList.add(optString);
                        }
                    }
                    return arrayList;
                }
            }, Crypto.RC4, asyncCallback);
        } catch (JSONException unused2) {
            if (asyncCallback != null) {
                asyncCallback.sendFailureMessage(new Error(ErrorCode.INVALID.getCode(), ""));
            }
            return new AsyncHandle(null);
        }
    }

    public AsyncHandle a(Context context, String str, int i, AsyncCallback<JSONObject, Error> asyncCallback) {
        ArrayList arrayList = new ArrayList();
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("did", str);
            jSONObject.put("pid", i);
            arrayList.add(new KeyValuePair("data", jSONObject.toString()));
            return CoreApi.a().a(context, new NetRequest.Builder().a("POST").b("/share/cancel").b((List<KeyValuePair>) arrayList).a(), new JsonParser<JSONObject>() {
                /* renamed from: a */
                public JSONObject parse(JSONObject jSONObject) throws JSONException {
                    return jSONObject;
                }
            }, Crypto.RC4, asyncCallback);
        } catch (JSONException unused) {
            if (asyncCallback != null) {
                asyncCallback.sendFailureMessage(new Error(ErrorCode.INVALID.getCode(), ""));
            }
            return new AsyncHandle(null);
        }
    }

    public AsyncHandle b(Context context, AsyncCallback<List<FamilyRecord>, Error> asyncCallback) {
        ArrayList arrayList = new ArrayList();
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("data", "");
            arrayList.add(new KeyValuePair("data", jSONObject.toString()));
            return CoreApi.a().a(context, new NetRequest.Builder().a("POST").b("/family/getlist").b((List<KeyValuePair>) arrayList).a(), new JsonParser<List<FamilyRecord>>() {
                /* renamed from: a */
                public List<FamilyRecord> parse(JSONObject jSONObject) throws JSONException {
                    ArrayList arrayList = new ArrayList();
                    JSONArray optJSONArray = jSONObject.optJSONArray("list");
                    FamilyRecord.deleteAll();
                    for (int i = 0; i < optJSONArray.length(); i++) {
                        JSONObject jSONObject2 = optJSONArray.getJSONObject(i);
                        FamilyRecord familyRecord = new FamilyRecord();
                        familyRecord.userId = CoreApi.a().s();
                        familyRecord.tUserId = jSONObject2.optString("uid_other");
                        familyRecord.relation_id = jSONObject2.optString(FamilyRecord.FIELD_RELATION_ID);
                        familyRecord.relationship = jSONObject2.optString(FamilyMemberData.d);
                        familyRecord.nickName = jSONObject2.optString("name");
                        familyRecord.url = jSONObject2.optString("icon");
                        familyRecord.status = jSONObject2.optString("status");
                        FamilyRecord.insert(familyRecord);
                        arrayList.add(familyRecord);
                    }
                    return arrayList;
                }
            }, Crypto.RC4, asyncCallback);
        } catch (JSONException unused) {
            if (asyncCallback != null) {
                asyncCallback.sendFailureMessage(new Error(ErrorCode.INVALID.getCode(), ""));
            }
            return new AsyncHandle(null);
        }
    }

    @Deprecated
    public AsyncHandle b(Context context, String str, String str2, String str3, AsyncCallback<Void, Error> asyncCallback) {
        ArrayList arrayList = new ArrayList();
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("uid_other", str);
            jSONObject.put(FamilyRecord.FIELD_RELATION_ID, str3);
            jSONObject.put(FamilyMemberData.d, str2);
            arrayList.add(new KeyValuePair("data", jSONObject.toString()));
            return CoreApi.a().a(context, new NetRequest.Builder().a("POST").b("/family/newrelation").b((List<KeyValuePair>) arrayList).a(), (JsonParser) null, Crypto.RC4, asyncCallback);
        } catch (JSONException unused) {
            if (asyncCallback != null) {
                asyncCallback.sendFailureMessage(new Error(ErrorCode.INVALID.getCode(), ""));
            }
            return new AsyncHandle(null);
        }
    }

    public AsyncHandle c(Context context, String str, AsyncCallback<Void, Error> asyncCallback) {
        ArrayList arrayList = new ArrayList();
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("uid_other", str);
            arrayList.add(new KeyValuePair("data", jSONObject.toString()));
            return CoreApi.a().a(context, new NetRequest.Builder().a("POST").b("/family/acceptrelation").b((List<KeyValuePair>) arrayList).a(), (JsonParser) null, Crypto.RC4, asyncCallback);
        } catch (JSONException unused) {
            if (asyncCallback != null) {
                asyncCallback.sendFailureMessage(new Error(ErrorCode.INVALID.getCode(), ""));
            }
            return new AsyncHandle(null);
        }
    }

    public AsyncHandle d(Context context, String str, AsyncCallback<Void, Error> asyncCallback) {
        ArrayList arrayList = new ArrayList();
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("uid_other", str);
            arrayList.add(new KeyValuePair("data", jSONObject.toString()));
            return CoreApi.a().a(context, new NetRequest.Builder().a("POST").b("/family/denyrelation").b((List<KeyValuePair>) arrayList).a(), (JsonParser) null, Crypto.RC4, asyncCallback);
        } catch (JSONException unused) {
            if (asyncCallback != null) {
                asyncCallback.sendFailureMessage(new Error(ErrorCode.INVALID.getCode(), ""));
            }
            return new AsyncHandle(null);
        }
    }

    @Deprecated
    public AsyncHandle a(Context context, String str, String str2, AsyncCallback<Void, Error> asyncCallback) {
        ArrayList arrayList = new ArrayList();
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("uid_other", str);
            jSONObject.put(FamilyMemberData.d, str2);
            arrayList.add(new KeyValuePair("data", jSONObject.toString()));
            return CoreApi.a().a(context, new NetRequest.Builder().a("POST").b("/family/updatenickname").b((List<KeyValuePair>) arrayList).a(), (JsonParser) null, Crypto.RC4, asyncCallback);
        } catch (JSONException unused) {
            if (asyncCallback != null) {
                asyncCallback.sendFailureMessage(new Error(ErrorCode.INVALID.getCode(), ""));
            }
            return new AsyncHandle(null);
        }
    }

    @Deprecated
    public AsyncHandle e(Context context, String str, AsyncCallback<Void, Error> asyncCallback) {
        ArrayList arrayList = new ArrayList();
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("uid_other", str);
            arrayList.add(new KeyValuePair("data", jSONObject.toString()));
            return CoreApi.a().a(context, new NetRequest.Builder().a("POST").b("/family/revokerelation").b((List<KeyValuePair>) arrayList).a(), (JsonParser) null, Crypto.RC4, asyncCallback);
        } catch (JSONException unused) {
            if (asyncCallback != null) {
                asyncCallback.sendFailureMessage(new Error(ErrorCode.INVALID.getCode(), ""));
            }
            return new AsyncHandle(null);
        }
    }

    public AsyncHandle c(Context context, AsyncCallback<JSONObject, Error> asyncCallback) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new KeyValuePair("data", (String) null));
        return CoreApi.a().a(context, new NetRequest.Builder().a("POST").b("/user/smart_index").b((List<KeyValuePair>) arrayList).a(), new JsonParser<JSONObject>() {
            /* renamed from: a */
            public JSONObject parse(JSONObject jSONObject) throws JSONException {
                return jSONObject;
            }
        }, Crypto.RC4, asyncCallback);
    }

    public HttpAsyncHandle a(Context context, String str, Map<String, Object> map, String str2, final AsyncResponseCallback<Object> asyncResponseCallback) {
        ArrayList arrayList = new ArrayList();
        if (map != null && !map.isEmpty()) {
            for (String next : map.keySet()) {
                arrayList.add(new com.xiaomi.smarthome.library.http.KeyValuePair(next, map.get(next).toString()));
            }
        }
        return HttpApi.a(this.e, new Request.Builder().a(str2).b(str).a((List<com.xiaomi.smarthome.library.http.KeyValuePair>) arrayList).a(), (AsyncHandler) new TextAsyncHandler() {
            /* renamed from: a */
            public void onSuccess(String str, Response response) {
                if (asyncResponseCallback != null) {
                    asyncResponseCallback.a(str);
                }
            }

            public void onFailure(com.xiaomi.smarthome.library.http.Error error, Exception exc, Response response) {
                if (asyncResponseCallback != null) {
                    asyncResponseCallback.a(ErrorCode.ERROR_INVALID_REQUEST.getCode(), "");
                }
            }
        });
    }

    public AsyncHandle f(Context context, String str, AsyncCallback<JSONObject, Error> asyncCallback) {
        ArrayList arrayList = new ArrayList();
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("name", str);
            arrayList.add(new KeyValuePair("data", jSONObject.toString()));
            return CoreApi.a().a(context, new NetRequest.Builder().a("POST").b("/family/create").b((List<KeyValuePair>) arrayList).a(), new JsonParser<JSONObject>() {
                /* renamed from: a */
                public JSONObject parse(JSONObject jSONObject) throws JSONException {
                    return jSONObject;
                }
            }, Crypto.RC4, asyncCallback);
        } catch (JSONException unused) {
            if (asyncCallback != null) {
                asyncCallback.sendFailureMessage(new Error(ErrorCode.INVALID.getCode(), ""));
            }
            return new AsyncHandle(null);
        }
    }

    public AsyncHandle c(Context context, String str, String str2, String str3, AsyncCallback<Void, Error> asyncCallback) {
        ArrayList arrayList = new ArrayList();
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("user_id", str);
            jSONObject.put(FamilyItemData.b, str2);
            jSONObject.put(FamilyMemberData.f, str3);
            NetRequest a2 = new NetRequest.Builder().a("POST").b("/family/add_member").b((List<KeyValuePair>) arrayList).a();
            arrayList.add(new KeyValuePair("data", jSONObject.toString()));
            return CoreApi.a().a(context, a2, (JsonParser) null, Crypto.RC4, asyncCallback);
        } catch (JSONException unused) {
            if (asyncCallback != null) {
                asyncCallback.sendFailureMessage(new Error(ErrorCode.INVALID.getCode(), ""));
            }
            return new AsyncHandle(null);
        }
    }

    public AsyncHandle b(Context context, String str, String str2, AsyncCallback<Void, Error> asyncCallback) {
        ArrayList arrayList = new ArrayList();
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("user_id", str);
            jSONObject.put(FamilyItemData.b, str2);
            arrayList.add(new KeyValuePair("data", jSONObject.toString()));
            return CoreApi.a().a(context, new NetRequest.Builder().a("POST").b("/family/delete_member").b((List<KeyValuePair>) arrayList).a(), (JsonParser) null, Crypto.RC4, asyncCallback);
        } catch (JSONException unused) {
            if (asyncCallback != null) {
                asyncCallback.sendFailureMessage(new Error(ErrorCode.INVALID.getCode(), ""));
            }
            return new AsyncHandle(null);
        }
    }

    public AsyncHandle g(Context context, String str, AsyncCallback<Void, Error> asyncCallback) {
        ArrayList arrayList = new ArrayList();
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put(FamilyItemData.b, str);
            arrayList.add(new KeyValuePair("data", jSONObject.toString()));
            return CoreApi.a().a(context, new NetRequest.Builder().a("POST").b("/family/quit").b((List<KeyValuePair>) arrayList).a(), (JsonParser) null, Crypto.RC4, asyncCallback);
        } catch (JSONException unused) {
            if (asyncCallback != null) {
                asyncCallback.sendFailureMessage(new Error(ErrorCode.INVALID.getCode(), ""));
            }
            return new AsyncHandle(null);
        }
    }

    public AsyncHandle c(Context context, String str, String str2, AsyncCallback<Void, Error> asyncCallback) {
        ArrayList arrayList = new ArrayList();
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("did", str2);
            jSONObject.put(FamilyItemData.b, str);
            arrayList.add(new KeyValuePair("data", jSONObject.toString()));
            return CoreApi.a().a(context, new NetRequest.Builder().a("POST").b("/family/add_device").b((List<KeyValuePair>) arrayList).a(), (JsonParser) null, Crypto.RC4, asyncCallback);
        } catch (JSONException unused) {
            if (asyncCallback != null) {
                asyncCallback.sendFailureMessage(new Error(ErrorCode.INVALID.getCode(), ""));
            }
            return new AsyncHandle(null);
        }
    }

    public AsyncHandle d(Context context, String str, String str2, AsyncCallback<Void, Error> asyncCallback) {
        ArrayList arrayList = new ArrayList();
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("did", str2);
            jSONObject.put(FamilyItemData.b, str);
            arrayList.add(new KeyValuePair("data", jSONObject.toString()));
            return CoreApi.a().a(context, new NetRequest.Builder().a("POST").b("/family/remove_device").b((List<KeyValuePair>) arrayList).a(), (JsonParser) null, Crypto.RC4, asyncCallback);
        } catch (JSONException unused) {
            if (asyncCallback != null) {
                asyncCallback.sendFailureMessage(new Error(ErrorCode.INVALID.getCode(), ""));
            }
            return new AsyncHandle(null);
        }
    }

    public AsyncHandle d(Context context, AsyncCallback<JSONObject, Error> asyncCallback) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new KeyValuePair("data", new JSONObject().toString()));
        return CoreApi.a().a(context, new NetRequest.Builder().a("POST").b("/family/list").b((List<KeyValuePair>) arrayList).a(), new JsonParser<JSONObject>() {
            /* renamed from: a */
            public JSONObject parse(JSONObject jSONObject) throws JSONException {
                return jSONObject;
            }
        }, Crypto.RC4, asyncCallback);
    }

    public AsyncHandle h(Context context, String str, AsyncCallback<JSONObject, Error> asyncCallback) {
        ArrayList arrayList = new ArrayList();
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put(FamilyItemData.b, str);
            arrayList.add(new KeyValuePair("data", jSONObject.toString()));
            return CoreApi.a().a(context, new NetRequest.Builder().a("POST").b("/family/device_list").b((List<KeyValuePair>) arrayList).a(), new JsonParser<JSONObject>() {
                /* renamed from: a */
                public JSONObject parse(JSONObject jSONObject) throws JSONException {
                    return jSONObject;
                }
            }, Crypto.RC4, asyncCallback);
        } catch (JSONException unused) {
            if (asyncCallback != null) {
                asyncCallback.sendFailureMessage(new Error(ErrorCode.INVALID.getCode(), ""));
            }
            return new AsyncHandle(null);
        }
    }

    public AsyncHandle i(Context context, String str, AsyncCallback<JSONObject, Error> asyncCallback) {
        ArrayList arrayList = new ArrayList();
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put(FamilyItemData.b, str);
            arrayList.add(new KeyValuePair("data", jSONObject.toString()));
            return CoreApi.a().a(context, new NetRequest.Builder().a("POST").b("/family/member_list").b((List<KeyValuePair>) arrayList).a(), new JsonParser<JSONObject>() {
                /* renamed from: a */
                public JSONObject parse(JSONObject jSONObject) throws JSONException {
                    return jSONObject;
                }
            }, Crypto.RC4, asyncCallback);
        } catch (JSONException unused) {
            if (asyncCallback != null) {
                asyncCallback.sendFailureMessage(new Error(ErrorCode.INVALID.getCode(), ""));
            }
            return new AsyncHandle(null);
        }
    }

    public AsyncHandle j(Context context, String str, AsyncCallback<JSONObject, Error> asyncCallback) {
        ArrayList arrayList = new ArrayList();
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put(FamilyItemData.b, str);
            arrayList.add(new KeyValuePair("data", jSONObject.toString()));
            return CoreApi.a().a(context, new NetRequest.Builder().a("POST").b("/family/detail").b((List<KeyValuePair>) arrayList).a(), new JsonParser<JSONObject>() {
                /* renamed from: a */
                public JSONObject parse(JSONObject jSONObject) throws JSONException {
                    return jSONObject;
                }
            }, Crypto.RC4, asyncCallback);
        } catch (JSONException unused) {
            if (asyncCallback != null) {
                asyncCallback.sendFailureMessage(new Error(ErrorCode.INVALID.getCode(), ""));
            }
            return new AsyncHandle(null);
        }
    }

    public AsyncHandle d(Context context, String str, String str2, String str3, AsyncCallback<Void, Error> asyncCallback) {
        ArrayList arrayList = new ArrayList();
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put(FamilyItemData.b, str);
            jSONObject.put("uid_other", str2);
            jSONObject.put(FamilyMemberData.d, str3);
            arrayList.add(new KeyValuePair("data", jSONObject.toString()));
            return CoreApi.a().a(context, new NetRequest.Builder().a("POST").b("/family/updatenickname").b((List<KeyValuePair>) arrayList).a(), (JsonParser) null, Crypto.RC4, asyncCallback);
        } catch (JSONException unused) {
            if (asyncCallback != null) {
                asyncCallback.sendFailureMessage(new Error(ErrorCode.INVALID.getCode(), ""));
            }
            return new AsyncHandle(null);
        }
    }

    public AsyncHandle k(Context context, String str, AsyncCallback<String, Error> asyncCallback) {
        ArrayList arrayList = new ArrayList();
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("userid", str);
        } catch (JSONException unused) {
        }
        arrayList.add(new KeyValuePair("data", jSONObject.toString()));
        return CoreApi.a().a(context, new NetRequest.Builder().a("POST").b("/share/receive_share_list").b((List<KeyValuePair>) arrayList).a(), new JsonParser<String>() {
            /* renamed from: a */
            public String parse(JSONObject jSONObject) throws JSONException {
                return jSONObject.toString();
            }
        }, Crypto.RC4, asyncCallback);
    }

    public AsyncHandle l(Context context, String str, AsyncCallback<String, Error> asyncCallback) {
        ArrayList arrayList = new ArrayList();
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("userid", str);
        } catch (JSONException unused) {
        }
        arrayList.add(new KeyValuePair("data", jSONObject.toString()));
        return CoreApi.a().a(context, new NetRequest.Builder().a("POST").b("/home/smart_life_picture").b((List<KeyValuePair>) arrayList).a(), new JsonParser<String>() {
            /* renamed from: a */
            public String parse(JSONObject jSONObject) throws JSONException {
                return jSONObject.toString();
            }
        }, Crypto.RC4, asyncCallback);
    }

    public AsyncHandle a(Context context, List<String> list, String str, AsyncCallback<WXDeviceShareLinkResult, Error> asyncCallback) {
        ArrayList arrayList = new ArrayList();
        JSONObject jSONObject = new JSONObject();
        try {
            JSONArray jSONArray = new JSONArray();
            for (String put : list) {
                jSONArray.put(put);
            }
            jSONObject.put("dids", jSONArray);
            if (!TextUtils.isEmpty(str)) {
                jSONObject.put("type", str);
            }
        } catch (Exception unused) {
        }
        arrayList.add(new KeyValuePair("data", jSONObject.toString()));
        return CoreApi.a().a(context, new NetRequest.Builder().a("POST").b("/share/create_share_sns").b((List<KeyValuePair>) arrayList).a(), new JsonParser<WXDeviceShareLinkResult>() {
            /* renamed from: a */
            public WXDeviceShareLinkResult parse(JSONObject jSONObject) throws JSONException {
                WXDeviceShareLinkResult wXDeviceShareLinkResult = new WXDeviceShareLinkResult();
                wXDeviceShareLinkResult.f15876a = jSONObject.getString(ApiConst.l);
                return wXDeviceShareLinkResult;
            }
        }, Crypto.RC4, asyncCallback);
    }

    public AsyncHandle e(Context context, AsyncCallback<JSONObject, Error> asyncCallback) {
        ArrayList arrayList = new ArrayList();
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put(CommandMessage.COMMAND, "getSwitch");
        } catch (Exception unused) {
        }
        arrayList.add(new KeyValuePair("data", jSONObject.toString()));
        return CoreApi.a().a(context, new NetRequest.Builder().a("POST").b("/mipush/switch").b((List<KeyValuePair>) arrayList).a(), new JsonParser<JSONObject>() {
            /* renamed from: a */
            public JSONObject parse(JSONObject jSONObject) throws JSONException {
                return jSONObject;
            }
        }, Crypto.RC4, asyncCallback);
    }

    public AsyncHandle a(Context context, JSONObject jSONObject, AsyncCallback<Void, Error> asyncCallback) {
        ArrayList arrayList = new ArrayList();
        JSONObject jSONObject2 = new JSONObject();
        try {
            jSONObject2.put(CommandMessage.COMMAND, "setSwitch");
            jSONObject2.put("param", jSONObject);
        } catch (Exception unused) {
        }
        arrayList.add(new KeyValuePair("data", jSONObject2.toString()));
        return CoreApi.a().a(context, new NetRequest.Builder().a("POST").b("/mipush/switch").b((List<KeyValuePair>) arrayList).a(), (JsonParser) null, Crypto.RC4, asyncCallback);
    }

    public AsyncHandle a(Home home, int i, List<DeviceMsgSettingChild> list, AsyncCallback<Void, Error> asyncCallback) {
        ArrayList arrayList = new ArrayList();
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("switch", i);
            if (home != null) {
                jSONObject.put("owner_uid", home.o());
                jSONObject.put("home_id", Long.parseLong(home.j()));
            }
            if (list != null) {
                JSONObject jSONObject2 = new JSONObject();
                for (int i2 = 0; i2 < list.size(); i2++) {
                    DeviceMsgSettingChild deviceMsgSettingChild = list.get(i2);
                    if (deviceMsgSettingChild != null) {
                        jSONObject2.put(deviceMsgSettingChild.f, deviceMsgSettingChild.j);
                    }
                }
                jSONObject.put("device_setting", jSONObject2);
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        arrayList.add(new KeyValuePair("data", jSONObject.toString()));
        return CoreApi.a().a(SHApplication.getAppContext(), new NetRequest.Builder().a("POST").b("/v2/mipush/set_dev_msg_switch").b((List<KeyValuePair>) arrayList).a(), (JsonParser) null, Crypto.RC4, asyncCallback);
    }

    public AsyncHandle a(Home home, String str, List<String> list, AsyncCallback<JSONObject, Error> asyncCallback, JSONObject jSONObject) {
        try {
            ArrayList arrayList = new ArrayList();
            JSONObject jSONObject2 = new JSONObject();
            if (home != null) {
                jSONObject2.put("owner_uid", home.o());
                jSONObject2.put("home_id", Long.parseLong(home.j()));
            }
            if (!TextUtils.isEmpty(str)) {
                String str2 = str;
                jSONObject2.put("start_did", str);
            } else {
                String str3 = str;
            }
            jSONObject2.put("limit", 300);
            JSONArray jSONArray = new JSONArray();
            if (TextUtils.isEmpty(str) && list != null) {
                for (String put : list) {
                    jSONArray.put(put);
                }
                if (jSONArray.length() > 0) {
                    jSONObject2.put("addition_did", jSONArray);
                }
            }
            arrayList.add(new KeyValuePair("data", jSONObject2.toString()));
            NetRequest a2 = new NetRequest.Builder().a("POST").b("/v2/mipush/get_dev_msg_switch").b((List<KeyValuePair>) arrayList).a();
            final AsyncCallback<JSONObject, Error> asyncCallback2 = asyncCallback;
            final JSONObject jSONObject3 = jSONObject;
            final NetRequest netRequest = a2;
            final Home home2 = home;
            new AsyncHandle.AsyncHandleWrap().a(CoreApi.a().a(SHApplication.getAppContext(), a2, $$Lambda$RemoteFamilyApi$_qs5ce1Ga0MMclTis85pWtCVr8E.INSTANCE, Crypto.RC4, new AsyncCallback<JSONObject, Error>() {
                /* renamed from: a */
                public void onSuccess(JSONObject jSONObject) {
                    if (jSONObject != null) {
                        if (!(jSONObject3 == null || netRequest == null || jSONObject.isNull("device_setting"))) {
                            JSONArray optJSONArray = jSONObject3.optJSONArray("device_setting");
                            JSONArray jSONArray = new JSONArray();
                            for (int i = 0; i < optJSONArray.length(); i++) {
                                if (!optJSONArray.isNull(i)) {
                                    jSONArray.put(optJSONArray.opt(i));
                                }
                            }
                            JSONArray optJSONArray2 = jSONObject.optJSONArray("device_setting");
                            for (int i2 = 0; i2 < optJSONArray2.length(); i2++) {
                                if (!optJSONArray2.isNull(i2)) {
                                    jSONArray.put(optJSONArray2.opt(i2));
                                }
                            }
                            try {
                                jSONObject.put("device_setting", jSONArray);
                            } catch (JSONException e2) {
                                e2.printStackTrace();
                            }
                        }
                        String optString = jSONObject.optString("next_start_did");
                        if (!jSONObject.optBoolean("has_more") || TextUtils.isEmpty(optString)) {
                            LogUtilGrey.a("RemoteFamilyApi", "getMiPushDeviceSwitch onSuccess onepage");
                            if (asyncCallback2 != null) {
                                asyncCallback2.onSuccess(jSONObject);
                                return;
                            }
                            return;
                        }
                        RemoteFamilyApi.this.a(home2, optString, (List<String>) null, (AsyncCallback<JSONObject, Error>) asyncCallback2, jSONObject);
                    } else if (asyncCallback2 != null) {
                        asyncCallback2.onFailure(new Error(-1, "getMiPushDeviceSwitch onFailure result null"));
                    }
                }

                public void onFailure(Error error) {
                    if (asyncCallback2 != null) {
                        asyncCallback2.onFailure(error);
                    }
                    LogUtilGrey.a("RemoteFamilyApi", "getMiPushDeviceSwitch onFailure");
                }
            }));
            return null;
        } catch (Exception unused) {
            LogUtilGrey.a("RemoteFamilyApi", "getMiPushDeviceSwitch onFailure");
            return null;
        }
    }

    public AsyncHandle f(Context context, AsyncCallback<List<UsrLocInfo>, Error> asyncCallback) {
        ArrayList arrayList = new ArrayList();
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put(CommandMessage.COMMAND, "getUserLocationInfo");
        } catch (Exception unused) {
        }
        arrayList.add(new KeyValuePair("data", jSONObject.toString()));
        return CoreApi.a().a(context, new NetRequest.Builder().a("POST").b("/user/location").b((List<KeyValuePair>) arrayList).a(), new JsonParser<List<UsrLocInfo>>() {
            /* renamed from: a */
            public List<UsrLocInfo> parse(JSONObject jSONObject) throws JSONException {
                return null;
            }
        }, Crypto.RC4, asyncCallback);
    }

    public AsyncHandle a(Context context, UsrLocInfo usrLocInfo, AsyncCallback<Integer, Error> asyncCallback) {
        ArrayList arrayList = new ArrayList();
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put(CommandMessage.COMMAND, "setUserLocationInfo");
            JSONArray jSONArray = new JSONArray();
            jSONArray.put(usrLocInfo.a());
            jSONObject.put("params", jSONArray);
        } catch (Exception unused) {
        }
        arrayList.add(new KeyValuePair("data", jSONObject.toString()));
        return CoreApi.a().a(context, new NetRequest.Builder().a("POST").b("/user/location").b((List<KeyValuePair>) arrayList).a(), new JsonParser<Integer>() {
            /* renamed from: a */
            public Integer parse(JSONObject jSONObject) throws JSONException {
                return null;
            }
        }, Crypto.RC4, asyncCallback);
    }

    public AsyncHandle b(Context context, UsrLocInfo usrLocInfo, AsyncCallback<Void, Error> asyncCallback) {
        ArrayList arrayList = new ArrayList();
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put(CommandMessage.COMMAND, "delUserLocationInfo");
            JSONArray jSONArray = new JSONArray();
            jSONArray.put(usrLocInfo.b());
            jSONObject.put("id", jSONArray);
        } catch (Exception unused) {
        }
        arrayList.add(new KeyValuePair("data", jSONObject.toString()));
        return CoreApi.a().a(context, new NetRequest.Builder().a("POST").b("/user/location").b((List<KeyValuePair>) arrayList).a(), (JsonParser) null, Crypto.RC4, asyncCallback);
    }

    public HttpAsyncHandle g(Context context, final AsyncCallback<List<ModelOperations>, Error> asyncCallback) {
        Request request;
        ArrayList arrayList = new ArrayList();
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put(TSMAuthContants.PARAM_LANGUAGE, "zh_CN");
            jSONObject.put("name", "android_device_list_switch");
            jSONObject.put("version", "1");
        } catch (Exception unused) {
        }
        arrayList.add(new KeyValuePair("data", jSONObject.toString()));
        try {
            request = new Request.Builder().a("GET").b(b(jSONObject)).a();
        } catch (UnsupportedEncodingException e2) {
            e2.printStackTrace();
            request = null;
        }
        if (request != null) {
            return HttpApi.a(request, (AsyncHandler) new AsyncHandler() {
                public void onFailure(com.xiaomi.smarthome.library.http.Error error, Exception exc, Response response) {
                }

                public void onSuccess(Object obj, Response response) {
                }

                public void processResponse(Response response) {
                    try {
                        JSONObject jSONObject = new JSONObject(response.body().string());
                        if (jSONObject.isNull("result")) {
                            if (asyncCallback != null) {
                                asyncCallback.onFailure(new Error(0, "no result field in response"));
                            }
                        } else if (jSONObject.optJSONObject("result").isNull("content") && asyncCallback != null) {
                            asyncCallback.onFailure(new Error(0, "not "));
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    } catch (JSONException e2) {
                        e2.printStackTrace();
                    }
                }

                public void processFailure(Call call, IOException iOException) {
                    if (asyncCallback != null) {
                        asyncCallback.onFailure(new Error(0, iOException.getMessage()));
                    }
                }
            });
        }
        if (asyncCallback != null) {
            asyncCallback.onFailure(new Error(0, "UnsupportedEncodingException:" + jSONObject.toString()));
        }
        return null;
    }

    @NonNull
    private String b(JSONObject jSONObject) throws UnsupportedEncodingException {
        return ServerRouteUtil.a(SHApplication.getAppContext()) + "/app/service/getappconfig?data=" + URLEncoder.encode(jSONObject.toString(), "UTF-8");
    }

    public AsyncHandle a(Context context, String str, long j, AsyncCallback<List<DeviceOpHistoryGroupData>, Error> asyncCallback) {
        ArrayList arrayList = new ArrayList();
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("did", str);
            jSONObject.put("time", j);
            jSONObject.put("limit", 50);
        } catch (Exception unused) {
        }
        arrayList.add(new KeyValuePair("data", jSONObject.toString()));
        return CoreApi.a().a(context, new NetRequest.Builder().a("POST").b("/device/get_opt_log").b((List<KeyValuePair>) arrayList).a(), new JsonParser<List<DeviceOpHistoryGroupData>>() {
            /* renamed from: a */
            public List<DeviceOpHistoryGroupData> parse(JSONObject jSONObject) throws JSONException {
                return DeviceOpHistoryGroupData.a(jSONObject);
            }
        }, Crypto.RC4, asyncCallback);
    }

    public AsyncHandle m(Context context, String str, AsyncCallback<JSONObject, Error> asyncCallback) {
        ArrayList arrayList = new ArrayList();
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put(CBConstant.SENDER, str);
            jSONObject.put(CommandMessage.COMMAND, "querybysender");
        } catch (Exception unused) {
        }
        arrayList.add(new KeyValuePair("data", jSONObject.toString()));
        return CoreApi.a().a(context, new NetRequest.Builder().a("POST").b("/share/share_history_query").b((List<KeyValuePair>) arrayList).a(), new JsonParser<JSONObject>() {
            /* renamed from: a */
            public JSONObject parse(JSONObject jSONObject) throws JSONException {
                return jSONObject;
            }
        }, Crypto.RC4, asyncCallback);
    }

    public AsyncHandle n(Context context, String str, AsyncCallback<JSONObject, Error> asyncCallback) {
        ArrayList arrayList = new ArrayList();
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("code", str);
        } catch (Exception unused) {
        }
        arrayList.add(new KeyValuePair("data", jSONObject.toString()));
        return CoreApi.a().a(context, new NetRequest.Builder().a("POST").b("/device/add_no_net_device").b((List<KeyValuePair>) arrayList).a(), new JsonParser<JSONObject>() {
            /* renamed from: a */
            public JSONObject parse(JSONObject jSONObject) throws JSONException {
                return jSONObject;
            }
        }, Crypto.RC4, asyncCallback);
    }

    public AsyncHandle a(Context context, final Map<String, Long> map, long j, AsyncCallback<JSONArray, Error> asyncCallback) {
        if (map == null || map.isEmpty()) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        JSONObject jSONObject = new JSONObject();
        try {
            Iterator<String> it = map.keySet().iterator();
            if (it.hasNext()) {
                String next = it.next();
                jSONObject.put("begin_at", j);
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        arrayList.add(new KeyValuePair("data", jSONObject.toString()));
        return CoreApi.a().a(context, new NetRequest.Builder().a("POST").b("/v2/message/v2/check_new_msg").b((List<KeyValuePair>) arrayList).a(), new JsonParser<JSONArray>() {
            /* renamed from: a */
            public JSONArray parse(JSONObject jSONObject) throws JSONException {
                boolean optBoolean = jSONObject.optBoolean("result", false);
                JSONArray jSONArray = new JSONArray();
                for (String put : map.keySet()) {
                    JSONObject jSONObject2 = new JSONObject();
                    jSONObject2.put("type", put);
                    jSONObject2.put("count", optBoolean ? 1 : 0);
                    jSONArray.put(jSONObject2);
                }
                return jSONArray;
            }
        }, Crypto.RC4, asyncCallback);
    }

    public AsyncHandle o(Context context, final String str, AsyncCallback<Boolean, Error> asyncCallback) {
        ArrayList arrayList = new ArrayList();
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("type", str);
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        arrayList.add(new KeyValuePair("data", jSONObject.toString()));
        return CoreApi.a().a(context, new NetRequest.Builder().a("POST").b("/service/server_user_guide").b((List<KeyValuePair>) arrayList).a(), new JsonParser<Boolean>() {
            /* renamed from: a */
            public Boolean parse(JSONObject jSONObject) throws JSONException {
                if (jSONObject == null || jSONObject.isNull(str)) {
                    return false;
                }
                try {
                    return Boolean.valueOf(jSONObject.optBoolean(str, false));
                } catch (Exception e) {
                    e.printStackTrace();
                    return false;
                }
            }
        }, Crypto.RC4, asyncCallback);
    }

    public AsyncHandle h(Context context, AsyncCallback<JSONArray, Error> asyncCallback) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new KeyValuePair("data", new JSONObject().toString()));
        return CoreApi.a().a(context, new NetRequest.Builder().a("POST").b("/service/getadconfig").b((List<KeyValuePair>) arrayList).a(), new JsonParser<JSONArray>() {
            /* renamed from: a */
            public JSONArray parse(JSONObject jSONObject) throws JSONException {
                if (jSONObject == null || jSONObject.isNull("result")) {
                    return null;
                }
                try {
                    return jSONObject.optJSONArray("result");
                } catch (Exception e) {
                    e.printStackTrace();
                    return null;
                }
            }
        }, Crypto.RC4, asyncCallback);
    }

    public AsyncHandle a(Context context, ArrayList<String> arrayList, AsyncCallback<JSONObject, Error> asyncCallback) {
        JSONObject jSONObject = new JSONObject();
        try {
            UserInfo a2 = UserInfoManager.a().a(Long.parseLong(CoreApi.a().s()));
            String str = "";
            if (a2 != null && !TextUtils.isEmpty(a2.e)) {
                str = context.getResources().getString(R.string.user_name_home_suffix, new Object[]{a2.e});
            }
            jSONObject.put("home_name", str);
            JSONArray jSONArray = new JSONArray();
            Iterator<String> it = arrayList.iterator();
            while (it.hasNext()) {
                jSONArray.put(HomeManager.k(it.next()));
            }
            jSONObject.put("room", jSONArray);
            JSONObject jSONObject2 = new JSONObject();
            jSONObject2.put("bssid", "");
            jSONObject2.put("type", "transfer");
            jSONObject2.put("para", jSONObject);
            ArrayList arrayList2 = new ArrayList();
            arrayList2.add(new KeyValuePair("data", jSONObject2.toString()));
            return CoreApi.a().a(SHApplication.getAppContext(), new NetRequest.Builder().a("POST").b("/homeroom/addroom").b((List<KeyValuePair>) arrayList2).a(), new JsonParser<JSONObject>() {
                /* renamed from: a */
                public JSONObject parse(JSONObject jSONObject) throws JSONException {
                    return jSONObject;
                }
            }, Crypto.RC4, asyncCallback);
        } catch (JSONException e2) {
            e2.printStackTrace();
            return null;
        }
    }

    public AsyncHandle i(Context context, AsyncCallback<JSONObject, Error> asyncCallback) {
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("bssid", "");
            jSONObject.put("type", "transfer");
            jSONObject.put("check", 1);
            ArrayList arrayList = new ArrayList();
            arrayList.add(new KeyValuePair("data", jSONObject.toString()));
            return CoreApi.a().a(SHApplication.getAppContext(), new NetRequest.Builder().a("POST").b("/homeroom/addroom").b((List<KeyValuePair>) arrayList).a(), new JsonParser<JSONObject>() {
                /* renamed from: a */
                public JSONObject parse(JSONObject jSONObject) throws JSONException {
                    return jSONObject;
                }
            }, Crypto.RC4, asyncCallback);
        } catch (JSONException e2) {
            e2.printStackTrace();
            return null;
        }
    }

    public AsyncHandle j(Context context, AsyncCallback<JSONObject, Error> asyncCallback) {
        try {
            JSONObject jSONObject = new JSONObject();
            ArrayList arrayList = new ArrayList();
            arrayList.add(new KeyValuePair("data", jSONObject.toString()));
            return CoreApi.a().a(SHApplication.getAppContext(), new NetRequest.Builder().a("POST").b("/homeroom/getroom").b((List<KeyValuePair>) arrayList).a(), new JsonParser<JSONObject>() {
                /* renamed from: a */
                public JSONObject parse(JSONObject jSONObject) throws JSONException {
                    return jSONObject;
                }
            }, Crypto.RC4, asyncCallback);
        } catch (Exception e2) {
            e2.printStackTrace();
            return null;
        }
    }

    public AsyncHandle a(Context context, String str, String str2, List<String> list, String str3, AsyncCallback<JSONObject, Error> asyncCallback) {
        try {
            JSONObject jSONObject = new JSONObject();
            ArrayList arrayList = new ArrayList();
            jSONObject.put("type", "addroom");
            jSONObject.put("name", str);
            jSONObject.put("parent_id", str2);
            jSONObject.put("icon", str3);
            if (list != null && !list.isEmpty()) {
                JSONArray jSONArray = new JSONArray();
                for (int i = 0; i < list.size(); i++) {
                    String str4 = list.get(i);
                    if (!TextUtils.isEmpty(str4)) {
                        jSONArray.put(str4);
                    }
                }
                jSONObject.put("did", jSONArray);
            }
            arrayList.add(new KeyValuePair("data", jSONObject.toString()));
            return CoreApi.a().a(SHApplication.getAppContext(), new NetRequest.Builder().a("POST").b("/homeroom/addroom").b((List<KeyValuePair>) arrayList).a(), new JsonParser<JSONObject>() {
                /* renamed from: a */
                public JSONObject parse(JSONObject jSONObject) throws JSONException {
                    return jSONObject;
                }
            }, Crypto.RC4, asyncCallback);
        } catch (Exception e2) {
            e2.printStackTrace();
            return null;
        }
    }

    public AsyncHandle a(Context context, Room room, List<String> list, List<String> list2, AsyncCallback<JSONObject, Error> asyncCallback) {
        try {
            JSONObject jSONObject = new JSONObject();
            ArrayList arrayList = new ArrayList();
            jSONObject.put("type", "set");
            jSONObject.put("id", room.d());
            jSONObject.put("name", room.e());
            jSONObject.put("icon", room.a());
            if (list != null && !list.isEmpty()) {
                JSONArray jSONArray = new JSONArray();
                for (int i = 0; i < list.size(); i++) {
                    String str = list.get(i);
                    if (!TextUtils.isEmpty(str)) {
                        jSONArray.put(str);
                    }
                }
                jSONObject.put("add", jSONArray);
            }
            if (list2 != null && !list2.isEmpty()) {
                JSONArray jSONArray2 = new JSONArray();
                for (int i2 = 0; i2 < list2.size(); i2++) {
                    String str2 = list2.get(i2);
                    if (!TextUtils.isEmpty(str2)) {
                        jSONArray2.put(str2);
                    }
                }
                jSONObject.put("delete", jSONArray2);
            }
            arrayList.add(new KeyValuePair("data", jSONObject.toString()));
            return CoreApi.a().a(SHApplication.getAppContext(), new NetRequest.Builder().a("POST").b("/homeroom/ctrroom").b((List<KeyValuePair>) arrayList).a(), new JsonParser<JSONObject>() {
                /* renamed from: a */
                public JSONObject parse(JSONObject jSONObject) throws JSONException {
                    return jSONObject;
                }
            }, Crypto.RC4, asyncCallback);
        } catch (Exception e2) {
            e2.printStackTrace();
            return null;
        }
    }

    public AsyncHandle a(Home home, String str, AsyncCallback<JSONObject, Error> asyncCallback) {
        try {
            JSONObject jSONObject = new JSONObject();
            ArrayList arrayList = new ArrayList();
            jSONObject.put("id", home.j());
            jSONObject.put("type", str);
            if (TextUtils.equals(str, "set")) {
                jSONObject.put("name", home.k());
                jSONObject.put(AppStateModule.APP_STATE_BACKGROUND, home.e());
                try {
                    if (!TextUtils.isEmpty(home.b())) {
                        jSONObject.put("latitude", Double.parseDouble(home.b()));
                    }
                    if (!TextUtils.isEmpty(home.c())) {
                        jSONObject.put("longitude", Double.parseDouble(home.c()));
                    }
                } catch (Exception e2) {
                    e2.printStackTrace();
                }
                jSONObject.put("city_id", home.a());
            }
            arrayList.add(new KeyValuePair("data", jSONObject.toString()));
            return CoreApi.a().a(SHApplication.getAppContext(), new NetRequest.Builder().a("POST").b("/homeroom/ctrhome").b((List<KeyValuePair>) arrayList).a(), new JsonParser<JSONObject>() {
                /* renamed from: a */
                public JSONObject parse(JSONObject jSONObject) throws JSONException {
                    return jSONObject;
                }
            }, Crypto.RC4, asyncCallback);
        } catch (Exception e3) {
            e3.printStackTrace();
            return null;
        }
    }

    public AsyncHandle a(Home home, Room room, Device device, AsyncCallback<JSONObject, Error> asyncCallback) {
        try {
            JSONObject jSONObject = new JSONObject();
            ArrayList arrayList = new ArrayList();
            jSONObject.put("id", room == null ? home.j() : room.d());
            jSONObject.put("did", device.did);
            arrayList.add(new KeyValuePair("data", jSONObject.toString()));
            return CoreApi.a().a(SHApplication.getAppContext(), new NetRequest.Builder().a("POST").b("/homeroom/bind_device_to_room").b((List<KeyValuePair>) arrayList).a(), new JsonParser<JSONObject>() {
                /* renamed from: a */
                public JSONObject parse(JSONObject jSONObject) throws JSONException {
                    return jSONObject;
                }
            }, Crypto.RC4, asyncCallback);
        } catch (Exception e2) {
            e2.printStackTrace();
            return null;
        }
    }

    public AsyncHandle a(Home home, Room room, List<String> list, AsyncCallback<JSONObject, Error> asyncCallback) {
        try {
            JSONObject jSONObject = new JSONObject();
            ArrayList arrayList = new ArrayList();
            jSONObject.put("id", room == null ? home.j() : room.d());
            if (list != null && !list.isEmpty()) {
                JSONArray jSONArray = new JSONArray();
                for (int i = 0; i < list.size(); i++) {
                    String str = list.get(i);
                    if (!TextUtils.isEmpty(str)) {
                        jSONArray.put(str);
                    }
                }
                jSONObject.put("dids", jSONArray);
            }
            arrayList.add(new KeyValuePair("data", jSONObject.toString()));
            return CoreApi.a().a(SHApplication.getAppContext(), new NetRequest.Builder().a("POST").b("/homeroom/batch_device_to_room").b((List<KeyValuePair>) arrayList).a(), new JsonParser<JSONObject>() {
                /* renamed from: a */
                public JSONObject parse(JSONObject jSONObject) throws JSONException {
                    return jSONObject;
                }
            }, Crypto.RC4, asyncCallback);
        } catch (Exception e2) {
            e2.printStackTrace();
            return null;
        }
    }

    public AsyncHandle a(Context context, Room room, AsyncCallback<JSONObject, Error> asyncCallback) {
        try {
            JSONObject jSONObject = new JSONObject();
            ArrayList arrayList = new ArrayList();
            jSONObject.put("type", "delete");
            jSONObject.put("id", room.d());
            arrayList.add(new KeyValuePair("data", jSONObject.toString()));
            return CoreApi.a().a(SHApplication.getAppContext(), new NetRequest.Builder().a("POST").b("/homeroom/ctrroom").b((List<KeyValuePair>) arrayList).a(), new JsonParser<JSONObject>() {
                /* renamed from: a */
                public JSONObject parse(JSONObject jSONObject) throws JSONException {
                    return jSONObject;
                }
            }, Crypto.RC4, asyncCallback);
        } catch (Exception e2) {
            e2.printStackTrace();
            return null;
        }
    }

    public AsyncHandle a(List<String> list, AsyncCallback<JSONObject, Error> asyncCallback) {
        try {
            JSONObject jSONObject = new JSONObject();
            ArrayList arrayList = new ArrayList();
            jSONObject.put("ids", new JSONArray(list));
            arrayList.add(new KeyValuePair("data", jSONObject.toString()));
            return CoreApi.a().a(SHApplication.getAppContext(), new NetRequest.Builder().a("POST").b("/homeroom/batchdelroom").b((List<KeyValuePair>) arrayList).a(), new JsonParser<JSONObject>() {
                /* renamed from: a */
                public JSONObject parse(JSONObject jSONObject) throws JSONException {
                    return jSONObject;
                }
            }, Crypto.RC4, asyncCallback);
        } catch (Exception e2) {
            e2.printStackTrace();
            return null;
        }
    }

    public AsyncHandle a(Home home, AsyncCallback<JSONObject, Error> asyncCallback) {
        try {
            JSONObject jSONObject = new JSONObject();
            ArrayList arrayList = new ArrayList();
            jSONObject.put("bssid", home.f());
            jSONObject.put("name", home.k());
            jSONObject.put(AppStateModule.APP_STATE_BACKGROUND, home.e());
            jSONObject.put("icon", home.h());
            jSONObject.put("city_id", home.a());
            if (!TextUtils.isEmpty(home.b()) && !TextUtils.isEmpty(home.c()) && !TextUtils.equals("0", home.b()) && !TextUtils.equals("0", home.c())) {
                jSONObject.put("latitude", Double.parseDouble(home.b()));
                jSONObject.put("longitude", Double.parseDouble(home.c()));
            }
            JSONArray jSONArray = new JSONArray();
            for (int i = 0; i < home.d().size(); i++) {
                String d2 = home.d().get(i).d();
                if (!TextUtils.isEmpty(d2)) {
                    jSONArray.put(d2);
                }
            }
            jSONObject.put("roomid", jSONArray);
            arrayList.add(new KeyValuePair("data", jSONObject.toString()));
            return CoreApi.a().a(SHApplication.getAppContext(), new NetRequest.Builder().a("POST").b("/homeroom/addhome").b((List<KeyValuePair>) arrayList).a(), new JsonParser<JSONObject>() {
                /* renamed from: a */
                public JSONObject parse(JSONObject jSONObject) throws JSONException {
                    return jSONObject;
                }
            }, Crypto.RC4, asyncCallback);
        } catch (Exception e2) {
            e2.printStackTrace();
            return null;
        }
    }

    public AsyncHandle b(Home home, AsyncCallback<JSONObject, Error> asyncCallback) {
        try {
            JSONObject jSONObject = new JSONObject();
            ArrayList arrayList = new ArrayList();
            jSONObject.put("home_owner", home.o());
            jSONObject.put("home_id", Long.parseLong(home.j()));
            arrayList.add(new KeyValuePair("data", jSONObject.toString()));
            return CoreApi.a().a(SHApplication.getAppContext(), new NetRequest.Builder().a("POST").b("/v2/homeroom/member_list").b((List<KeyValuePair>) arrayList).a(), new JsonParser<JSONObject>() {
                /* renamed from: a */
                public JSONObject parse(JSONObject jSONObject) throws JSONException {
                    return jSONObject;
                }
            }, Crypto.RC4, asyncCallback);
        } catch (Exception e2) {
            e2.printStackTrace();
            return null;
        }
    }

    public AsyncHandle a(long j, String str, AsyncCallback<JSONObject, Error> asyncCallback) {
        try {
            JSONObject jSONObject = new JSONObject();
            ArrayList arrayList = new ArrayList();
            jSONObject.put(ShareConstants.n, j);
            jSONObject.put("home_id", Long.parseLong(str));
            arrayList.add(new KeyValuePair("data", jSONObject.toString()));
            return CoreApi.a().a(SHApplication.getAppContext(), new NetRequest.Builder().a("POST").b("/v2/homeroom/member_invite").b((List<KeyValuePair>) arrayList).a(), new JsonParser<JSONObject>() {
                /* renamed from: a */
                public JSONObject parse(JSONObject jSONObject) throws JSONException {
                    return jSONObject;
                }
            }, Crypto.RC4, asyncCallback);
        } catch (Exception e2) {
            e2.printStackTrace();
            return null;
        }
    }

    public AsyncHandle a(List<Long> list, String str, AsyncCallback<JSONObject, Error> asyncCallback) {
        try {
            JSONObject jSONObject = new JSONObject();
            ArrayList arrayList = new ArrayList();
            jSONObject.put("target_list", new JSONArray(list));
            jSONObject.put("home_id", Long.parseLong(str));
            arrayList.add(new KeyValuePair("data", jSONObject.toString()));
            return CoreApi.a().a(SHApplication.getAppContext(), new NetRequest.Builder().a("POST").b("/v2/homeroom/member_invite_batch").b((List<KeyValuePair>) arrayList).a(), new JsonParser<JSONObject>() {
                /* renamed from: a */
                public JSONObject parse(JSONObject jSONObject) throws JSONException {
                    return jSONObject;
                }
            }, Crypto.RC4, asyncCallback);
        } catch (Exception e2) {
            e2.printStackTrace();
            return null;
        }
    }

    public AsyncHandle a(List<Long> list, long j, long j2, AsyncCallback<JSONObject, Error> asyncCallback) {
        try {
            JSONObject jSONObject = new JSONObject();
            ArrayList arrayList = new ArrayList();
            jSONObject.put("home_owner", j);
            jSONObject.put(ShareConstants.n, new JSONArray(list));
            jSONObject.put("home_id", j2);
            arrayList.add(new KeyValuePair("data", jSONObject.toString()));
            return CoreApi.a().a(SHApplication.getAppContext(), new NetRequest.Builder().a("POST").b("/v2/homeroom/member_remove").b((List<KeyValuePair>) arrayList).a(), new JsonParser<JSONObject>() {
                /* renamed from: a */
                public JSONObject parse(JSONObject jSONObject) throws JSONException {
                    return jSONObject;
                }
            }, Crypto.RC4, asyncCallback);
        } catch (Exception e2) {
            e2.printStackTrace();
            return null;
        }
    }

    public AsyncHandle a(AsyncCallback<JSONObject, Error> asyncCallback) {
        try {
            JSONObject jSONObject = new JSONObject();
            ArrayList arrayList = new ArrayList();
            jSONObject.put("fetch_sended", true);
            jSONObject.put("fetch_received", true);
            arrayList.add(new KeyValuePair("data", jSONObject.toString()));
            return CoreApi.a().a(SHApplication.getAppContext(), new NetRequest.Builder().a("POST").b("/v2/homeroom/invite_history").b((List<KeyValuePair>) arrayList).a(), new JsonParser<JSONObject>() {
                /* renamed from: a */
                public JSONObject parse(JSONObject jSONObject) throws JSONException {
                    return jSONObject;
                }
            }, Crypto.RC4, asyncCallback);
        } catch (Exception e2) {
            e2.printStackTrace();
            return null;
        }
    }

    public AsyncHandle a(long j, long j2, int i, AsyncCallback<JSONObject, Error> asyncCallback) {
        try {
            JSONObject jSONObject = new JSONObject();
            ArrayList arrayList = new ArrayList();
            jSONObject.put("home_owner", j);
            jSONObject.put("home_id", j2);
            jSONObject.put("status", i);
            arrayList.add(new KeyValuePair("data", jSONObject.toString()));
            return CoreApi.a().a(SHApplication.getAppContext(), new NetRequest.Builder().a("POST").b("/v2/homeroom/member_response").b((List<KeyValuePair>) arrayList).a(), new JsonParser<JSONObject>() {
                /* renamed from: a */
                public JSONObject parse(JSONObject jSONObject) throws JSONException {
                    return jSONObject;
                }
            }, Crypto.RC4, asyncCallback);
        } catch (Exception e2) {
            e2.printStackTrace();
            return null;
        }
    }

    public AsyncHandle a(String str, AsyncCallback<JSONObject, Error> asyncCallback) {
        JSONObject jSONObject = new JSONObject();
        try {
            ArrayList arrayList = new ArrayList();
            jSONObject.put("home_id", Long.parseLong(str));
            arrayList.add(new KeyValuePair("data", jSONObject.toString()));
            return CoreApi.a().a(SHApplication.getAppContext(), new NetRequest.Builder().a("POST").b("/v2/homeroom/wechat/member_invite").b((List<KeyValuePair>) arrayList).a(), $$Lambda$RemoteFamilyApi$TK0nlt5dUSVBlDyFlAikkKFf4.INSTANCE, Crypto.RC4, asyncCallback);
        } catch (JSONException e2) {
            e2.printStackTrace();
            return null;
        }
    }

    public AsyncHandle b(String str, AsyncCallback<JSONObject, Error> asyncCallback) {
        JSONObject jSONObject = new JSONObject();
        try {
            ArrayList arrayList = new ArrayList();
            jSONObject.put(ApiConst.l, str);
            arrayList.add(new KeyValuePair("data", jSONObject.toString()));
            return CoreApi.a().a(SHApplication.getAppContext(), new NetRequest.Builder().a("POST").b("/v2/homeroom/wechat/member_response").b((List<KeyValuePair>) arrayList).a(), $$Lambda$RemoteFamilyApi$Fvo9zZCuz9BfPmfFXE7iIuh481g.INSTANCE, Crypto.RC4, asyncCallback);
        } catch (JSONException e2) {
            e2.printStackTrace();
            return null;
        }
    }

    public AsyncHandle b(AsyncCallback<JSONObject, Error> asyncCallback) {
        try {
            return CoreApi.a().a(SHApplication.getAppContext(), new NetRequest.Builder().a("POST").b("/v2/thirdcloud2cloud/group_list").a(), new JsonParser<JSONObject>() {
                /* renamed from: a */
                public JSONObject parse(JSONObject jSONObject) throws JSONException {
                    return jSONObject;
                }
            }, Crypto.RC4, asyncCallback);
        } catch (Exception e2) {
            e2.printStackTrace();
            return null;
        }
    }

    public AsyncHandle b(List<String> list, final AsyncCallback<JSONObject, Error> asyncCallback) {
        if (list == null || list.isEmpty()) {
            if (asyncCallback != null) {
                asyncCallback.onFailure(new Error(Integer.MIN_VALUE, "empty groupIds list"));
            }
            return null;
        }
        StringBuilder sb = new StringBuilder();
        for (String parseLong : list) {
            sb.append(Long.parseLong(parseLong) + ",");
        }
        sb.delete(sb.length() - 1, sb.length());
        final String sb2 = sb.toString();
        final AsyncHandle.AsyncHandleWrap asyncHandleWrap = new AsyncHandle.AsyncHandleWrap();
        asyncHandleWrap.a(a(sb2, (String) null, (AsyncCallback<JSONObject, Error>) new AsyncCallback<JSONObject, Error>() {
            private JSONObject e;
            private JSONArray f;

            /* renamed from: a */
            public void onSuccess(JSONObject jSONObject) {
                if (jSONObject != null) {
                    if (this.e == null) {
                        this.e = jSONObject;
                    }
                    try {
                        if (!jSONObject.isNull("list")) {
                            Object obj = jSONObject.get("list");
                            if (obj instanceof JSONArray) {
                                JSONArray jSONArray = (JSONArray) obj;
                                if (this.f == null) {
                                    this.e = jSONObject;
                                    this.f = jSONArray;
                                } else {
                                    for (int i = 0; i < jSONArray.length(); i++) {
                                        this.f.put(jSONArray.opt(i));
                                    }
                                }
                            }
                        }
                    } catch (JSONException e2) {
                        LogUtil.b("RemoteFamilyApi", Log.getStackTraceString(e2));
                    }
                    if (jSONObject.optBoolean("has_more")) {
                        asyncHandleWrap.a(RemoteFamilyApi.this.a(sb2, jSONObject.optString("next_start_did"), (AsyncCallback<JSONObject, Error>) this));
                    } else if (asyncCallback != null) {
                        asyncCallback.onSuccess(this.e);
                    }
                } else if (asyncCallback != null) {
                    asyncCallback.onSuccess(jSONObject);
                }
            }

            public void onFailure(Error error) {
                asyncCallback.onFailure(error);
            }
        }));
        return asyncHandleWrap;
    }

    public AsyncHandle a(String str, String str2, AsyncCallback<JSONObject, Error> asyncCallback) {
        try {
            JSONObject jSONObject = new JSONObject();
            ArrayList arrayList = new ArrayList();
            jSONObject.put("group_ids", str);
            jSONObject.put("limit", 300);
            if (str2 != null) {
                jSONObject.put("start_did", str2);
            }
            arrayList.add(new KeyValuePair("data", jSONObject.toString()));
            return CoreApi.a().a(SHApplication.getAppContext(), new NetRequest.Builder().a("POST").b("/v2/thirdcloud2cloud/device_list").b((List<KeyValuePair>) arrayList).a(), new JsonParser<JSONObject>() {
                /* renamed from: a */
                public JSONObject parse(JSONObject jSONObject) throws JSONException {
                    return jSONObject;
                }
            }, Crypto.RC4, asyncCallback);
        } catch (Exception e2) {
            e2.printStackTrace();
            return null;
        }
    }

    @Deprecated
    public AsyncHandle b(String str, String str2, AsyncCallback<JSONObject, Error> asyncCallback) {
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2)) {
            if (asyncCallback != null) {
                asyncCallback.onFailure(new Error(Integer.MIN_VALUE, "empty did"));
            }
            return null;
        }
        ArrayList arrayList = new ArrayList();
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("did", str);
            JSONObject jSONObject2 = new JSONObject();
            jSONObject2.put("timezone", str2);
            jSONObject.put("extra_data", jSONObject2);
            arrayList.add(new KeyValuePair("data", jSONObject.toString()));
            return CoreApi.a().a(SHApplication.getAppContext(), new NetRequest.Builder().a("POST").b("/v2/device/set_extra_data").b((List<KeyValuePair>) arrayList).a(), new JsonParser<JSONObject>() {
                /* renamed from: a */
                public JSONObject parse(JSONObject jSONObject) throws JSONException {
                    return jSONObject;
                }
            }, Crypto.RC4, asyncCallback);
        } catch (Exception unused) {
            return null;
        }
    }

    public AsyncHandle a(String str, String str2, JSONObject jSONObject, AsyncCallback<JSONObject, Error> asyncCallback) {
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2)) {
            if (asyncCallback != null) {
                asyncCallback.onFailure(new Error(Integer.MIN_VALUE, "empty did"));
            }
            return null;
        }
        ArrayList arrayList = new ArrayList();
        JSONObject jSONObject2 = new JSONObject();
        try {
            jSONObject2.put("did", str);
            jSONObject2.put("timezone", str2);
            boolean z = false;
            if (jSONObject != null) {
                z = jSONObject.optBoolean(TimezoneActivity.KEY_SYNC_DEVICE);
            }
            jSONObject2.put(TimezoneActivity.KEY_SYNC_DEVICE, z);
            arrayList.add(new KeyValuePair("data", jSONObject2.toString()));
            return CoreApi.a().a(SHApplication.getAppContext(), new NetRequest.Builder().a("POST").b("/v2/device/set_timezone").b((List<KeyValuePair>) arrayList).a(), new JsonParser<JSONObject>() {
                /* renamed from: a */
                public JSONObject parse(JSONObject jSONObject) throws JSONException {
                    return jSONObject;
                }
            }, Crypto.RC4, asyncCallback);
        } catch (Exception unused) {
            return null;
        }
    }

    public AsyncHandle c(String str, AsyncCallback<MyTimeZone, Error> asyncCallback) {
        if (TextUtils.isEmpty(str)) {
            if (asyncCallback != null) {
                asyncCallback.onFailure(new Error(Integer.MIN_VALUE, "empty did"));
            }
            return null;
        }
        ArrayList arrayList = new ArrayList();
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("did", str);
            JSONArray jSONArray = new JSONArray();
            jSONArray.put("timezone");
            jSONObject.put(QuickTimeAtomTypes.h, jSONArray);
            arrayList.add(new KeyValuePair("data", jSONObject.toString()));
            return CoreApi.a().a(SHApplication.getAppContext(), new NetRequest.Builder().a("POST").b("/v2/device/get_extra_data").b((List<KeyValuePair>) arrayList).a(), new JsonParser<MyTimeZone>() {
                /* renamed from: a */
                public MyTimeZone parse(JSONObject jSONObject) throws JSONException {
                    TimeZone timeZone;
                    MyTimeZone myTimeZone = new MyTimeZone();
                    JSONObject optJSONObject = jSONObject.optJSONObject("data");
                    if (!(optJSONObject == null || optJSONObject == JSONObject.NULL)) {
                        String optString = optJSONObject.optString("timezone");
                        myTimeZone.c(optString);
                        if (!TextUtils.isEmpty(optString) && (timeZone = TimeZone.getTimeZone(optString)) != null) {
                            myTimeZone.a(TimeZoneApi.a(optString, SHApplication.getAppContext()));
                            myTimeZone.b(timeZone.getDisplayName(false, 0));
                        }
                    }
                    return myTimeZone;
                }
            }, Crypto.RC4, asyncCallback);
        } catch (Exception unused) {
            return null;
        }
    }

    public AsyncHandle d(String str, AsyncCallback<JSONObject, Error> asyncCallback) {
        if (TextUtils.isEmpty(str)) {
            if (asyncCallback != null) {
                asyncCallback.onFailure(new Error(Integer.MIN_VALUE, "empty groupIds list"));
            }
            return null;
        }
        try {
            JSONObject jSONObject = new JSONObject();
            ArrayList arrayList = new ArrayList();
            jSONObject.put(FirebaseAnalytics.Param.GROUP_ID, Long.parseLong(str));
            arrayList.add(new KeyValuePair("data", jSONObject.toString()));
            return CoreApi.a().a(SHApplication.getAppContext(), new NetRequest.Builder().a("POST").b("/v2/thirdcloud2cloud/sync").b((List<KeyValuePair>) arrayList).a(), new JsonParser<JSONObject>() {
                /* renamed from: a */
                public JSONObject parse(JSONObject jSONObject) throws JSONException {
                    return jSONObject;
                }
            }, Crypto.RC4, asyncCallback);
        } catch (Exception e2) {
            e2.printStackTrace();
            return null;
        }
    }

    public AsyncHandle e(String str, AsyncCallback<JSONObject, Error> asyncCallback) {
        if (TextUtils.isEmpty(str)) {
            if (asyncCallback != null) {
                asyncCallback.onFailure(new Error(Integer.MIN_VALUE, "empty groupIds list"));
            }
            return null;
        }
        try {
            JSONObject jSONObject = new JSONObject();
            ArrayList arrayList = new ArrayList();
            jSONObject.put(FirebaseAnalytics.Param.GROUP_ID, Long.parseLong(str));
            arrayList.add(new KeyValuePair("data", jSONObject.toString()));
            return CoreApi.a().a(SHApplication.getAppContext(), new NetRequest.Builder().a("POST").b("/v2/thirdcloud2cloud/unbind").b((List<KeyValuePair>) arrayList).a(), new JsonParser<JSONObject>() {
                /* renamed from: a */
                public JSONObject parse(JSONObject jSONObject) throws JSONException {
                    return jSONObject;
                }
            }, Crypto.RC4, asyncCallback);
        } catch (Exception e2) {
            e2.printStackTrace();
            return null;
        }
    }

    public AsyncHandle c(AsyncCallback<JSONObject, Error> asyncCallback) {
        try {
            new ArrayList().add(new KeyValuePair("data", new JSONObject().toString()));
            return CoreApi.a().a(SHApplication.getAppContext(), new NetRequest.Builder().a("POST").b("/v2/oauth/getstates").a(), new JsonParser<JSONObject>() {
                /* renamed from: a */
                public JSONObject parse(JSONObject jSONObject) throws JSONException {
                    return jSONObject;
                }
            }, Crypto.RC4, asyncCallback);
        } catch (Exception e2) {
            e2.printStackTrace();
            return null;
        }
    }

    public AsyncHandle d(AsyncCallback<JSONObject, Error> asyncCallback) {
        try {
            JSONObject jSONObject = new JSONObject();
            ArrayList arrayList = new ArrayList();
            arrayList.add(new KeyValuePair("data", jSONObject.toString()));
            return CoreApi.a().a(SHApplication.getAppContext(), new NetRequest.Builder().a("POST").b("/v2/home/getunsetdids").b((List<KeyValuePair>) arrayList).a(), new JsonParser<JSONObject>() {
                /* renamed from: a */
                public JSONObject parse(JSONObject jSONObject) throws JSONException {
                    return jSONObject;
                }
            }, Crypto.RC4, asyncCallback);
        } catch (Exception e2) {
            e2.printStackTrace();
            return null;
        }
    }

    public AsyncHandle c(List<String> list, AsyncCallback<JSONObject, Error> asyncCallback) {
        try {
            JSONObject jSONObject = new JSONObject();
            JSONArray jSONArray = new JSONArray();
            for (int i = 0; i < list.size(); i++) {
                String str = list.get(i);
                if (!TextUtils.isEmpty(str)) {
                    jSONArray.put(str);
                }
            }
            jSONObject.put("dids", jSONArray);
            ArrayList arrayList = new ArrayList();
            arrayList.add(new KeyValuePair("data", jSONObject.toString()));
            return CoreApi.a().a(SHApplication.getAppContext(), new NetRequest.Builder().a("POST").b("/v2/home/removeunsetdids").b((List<KeyValuePair>) arrayList).a(), new JsonParser<JSONObject>() {
                /* renamed from: a */
                public JSONObject parse(JSONObject jSONObject) throws JSONException {
                    return jSONObject;
                }
            }, Crypto.RC4, asyncCallback);
        } catch (Exception e2) {
            e2.printStackTrace();
            return null;
        }
    }

    public AsyncHandle d(List<String> list, AsyncCallback<JSONObject, Error> asyncCallback) {
        try {
            JSONObject jSONObject = new JSONObject();
            ArrayList arrayList = new ArrayList();
            long currentTimeMillis = System.currentTimeMillis() / 1000;
            JSONArray jSONArray = new JSONArray();
            for (int i = 0; i < list.size(); i++) {
                JSONObject jSONObject2 = new JSONObject();
                jSONObject2.put("uid", new Long(list.get(i)));
                jSONObject2.put("time_stamp", currentTimeMillis);
                jSONArray.put(jSONObject2);
            }
            jSONObject.put("info", jSONArray);
            arrayList.add(new KeyValuePair("data", jSONObject.toString()));
            return CoreApi.a().a(SHApplication.getAppContext(), new NetRequest.Builder().a("POST").b("/v2/share/add_sharelist_block_uid").b((List<KeyValuePair>) arrayList).a(), new JsonParser<JSONObject>() {
                /* renamed from: a */
                public JSONObject parse(JSONObject jSONObject) throws JSONException {
                    return jSONObject;
                }
            }, Crypto.RC4, asyncCallback);
        } catch (Exception e2) {
            e2.printStackTrace();
            return null;
        }
    }

    public AsyncHandle e(List<Long> list, AsyncCallback<JSONObject, Error> asyncCallback) {
        try {
            JSONObject jSONObject = new JSONObject();
            ArrayList arrayList = new ArrayList();
            jSONObject.put("uids", new JSONArray(list));
            arrayList.add(new KeyValuePair("data", jSONObject.toString()));
            return CoreApi.a().a(SHApplication.getAppContext(), new NetRequest.Builder().a("POST").b("/v2/share/del_sharelist_block_uid").b((List<KeyValuePair>) arrayList).a(), new JsonParser<JSONObject>() {
                /* renamed from: a */
                public JSONObject parse(JSONObject jSONObject) throws JSONException {
                    return jSONObject;
                }
            }, Crypto.RC4, asyncCallback);
        } catch (Exception e2) {
            e2.printStackTrace();
            return null;
        }
    }

    public AsyncHandle e(AsyncCallback<JSONObject, Error> asyncCallback) {
        try {
            JSONObject jSONObject = new JSONObject();
            ArrayList arrayList = new ArrayList();
            arrayList.add(new KeyValuePair("data", jSONObject.toString()));
            return CoreApi.a().a(SHApplication.getAppContext(), new NetRequest.Builder().a("POST").b("/v2/share/sharelist_block_list").b((List<KeyValuePair>) arrayList).a(), new JsonParser<JSONObject>() {
                /* renamed from: a */
                public JSONObject parse(JSONObject jSONObject) throws JSONException {
                    return jSONObject;
                }
            }, Crypto.RC4, asyncCallback);
        } catch (Exception e2) {
            e2.printStackTrace();
            return null;
        }
    }

    public AsyncHandle f(AsyncCallback<Boolean, Error> asyncCallback) {
        try {
            JSONObject jSONObject = new JSONObject();
            JSONArray jSONArray = new JSONArray();
            jSONArray.put("rate_switch");
            jSONObject.put(QuickTimeAtomTypes.h, jSONArray);
            ArrayList arrayList = new ArrayList();
            arrayList.add(new KeyValuePair("data", jSONObject.toString()));
            return CoreApi.a().a(SHApplication.getAppContext(), new NetRequest.Builder().a("POST").b("/v2/user/switch_control").b((List<KeyValuePair>) arrayList).a(), new JsonParser<Boolean>() {
                /* renamed from: a */
                public Boolean parse(JSONObject jSONObject) throws JSONException {
                    if (jSONObject != null) {
                        try {
                            if (!jSONObject.isNull("rate_switch")) {
                                JSONObject optJSONObject = jSONObject.optJSONObject("rate_switch");
                                if (optJSONObject != null) {
                                    if (!optJSONObject.isNull("data")) {
                                        return Boolean.valueOf(TextUtils.equals(optJSONObject.optString("data", ""), "on"));
                                    }
                                }
                                return false;
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                            return false;
                        }
                    }
                    return false;
                }
            }, Crypto.RC4, asyncCallback);
        } catch (Exception e2) {
            e2.printStackTrace();
            return null;
        }
    }

    public AsyncHandle f(List<String> list, AsyncCallback<JSONObject, Error> asyncCallback) {
        if (list != null) {
            try {
                if (list.size() != 0) {
                    JSONObject jSONObject = new JSONObject();
                    JSONArray jSONArray = new JSONArray();
                    for (String put : list) {
                        jSONArray.put(put);
                    }
                    jSONObject.put("dids", jSONArray);
                    ArrayList arrayList = new ArrayList();
                    arrayList.add(new KeyValuePair("data", jSONObject.toString()));
                    return CoreApi.a().a(SHApplication.getAppContext(), new NetRequest.Builder().a("POST").b("/v2/miotspec/voice_device_list").b((List<KeyValuePair>) arrayList).a(), new JsonParser<JSONObject>() {
                        /* renamed from: a */
                        public JSONObject parse(JSONObject jSONObject) throws JSONException {
                            return jSONObject;
                        }
                    }, Crypto.RC4, asyncCallback);
                }
            } catch (Exception e2) {
                e2.printStackTrace();
                return null;
            }
        }
        asyncCallback.onFailure(new Error(-1, "no request data dids"));
        return null;
    }

    public AsyncHandle f(String str, AsyncCallback<JSONObject, Error> asyncCallback) {
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("device_id", str);
            jSONObject.put(com.xiaomi.verificationsdk.internal.Constants.d, 0);
            jSONObject.put("tip_type", "name");
            ArrayList arrayList = new ArrayList();
            arrayList.add(new KeyValuePair("data", jSONObject.toString()));
            return CoreApi.a().a(SHApplication.getAppContext(), new NetRequest.Builder().a("POST").b("/v2/voicectrl/ai_device_tips").b((List<KeyValuePair>) arrayList).a(), new JsonParser<JSONObject>() {
                /* renamed from: a */
                public JSONObject parse(JSONObject jSONObject) throws JSONException {
                    return jSONObject;
                }
            }, Crypto.RC4, asyncCallback);
        } catch (Exception e2) {
            e2.printStackTrace();
            return null;
        }
    }

    public AsyncHandle a(String str, List<String> list, AsyncCallback<JSONObject, Error> asyncCallback) {
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("device_id", str);
            jSONObject.put(com.xiaomi.verificationsdk.internal.Constants.d, 0);
            jSONObject.put("tip_type", "name");
            if (list != null && !list.isEmpty()) {
                JSONArray jSONArray = new JSONArray();
                for (int i = 0; i < list.size(); i++) {
                    jSONArray.put(list.get(i));
                }
                jSONObject.put("ctrl_did", jSONArray);
            }
            ArrayList arrayList = new ArrayList();
            arrayList.add(new KeyValuePair("data", jSONObject.toString()));
            return CoreApi.a().a(SHApplication.getAppContext(), new NetRequest.Builder().a("POST").b("/v2/voicectrl/ai_device_tips").b((List<KeyValuePair>) arrayList).a(), new JsonParser<JSONObject>() {
                /* renamed from: a */
                public JSONObject parse(JSONObject jSONObject) throws JSONException {
                    return jSONObject;
                }
            }, Crypto.RC4, asyncCallback);
        } catch (Exception e2) {
            e2.printStackTrace();
            return null;
        }
    }

    public AsyncHandle g(String str, AsyncCallback<JSONObject, Error> asyncCallback) {
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("room_id", Long.parseLong(str));
            ArrayList arrayList = new ArrayList();
            arrayList.add(new KeyValuePair("data", jSONObject.toString()));
            return CoreApi.a().a(SHApplication.getAppContext(), new NetRequest.Builder().a("POST").b("/v2/voicectrl/get_room_maindev").b((List<KeyValuePair>) arrayList).a(), new JsonParser<JSONObject>() {
                /* renamed from: a */
                public JSONObject parse(JSONObject jSONObject) throws JSONException {
                    return jSONObject;
                }
            }, Crypto.RC4, asyncCallback);
        } catch (Exception e2) {
            e2.printStackTrace();
            return null;
        }
    }

    public AsyncHandle a(String str, String str2, boolean z, List<String> list, List<ControllableDevice> list2, AsyncCallback<JSONObject, Error> asyncCallback) {
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("room_id", Long.parseLong(str));
            if (list != null) {
                JSONArray jSONArray = new JSONArray();
                for (int i = 0; i < list.size(); i++) {
                    String str3 = list.get(i);
                    if (!TextUtils.isEmpty(str3)) {
                        jSONArray.put(str3);
                    }
                }
                jSONObject.put("ctrl_dids", jSONArray);
            }
            JSONObject jSONObject2 = new JSONObject();
            jSONObject2.put("intent", str2);
            jSONObject2.put("use_default", z);
            JSONArray jSONArray2 = new JSONArray();
            if (list2 != null) {
                for (int i2 = 0; i2 < list2.size(); i2++) {
                    ControllableDevice controllableDevice = list2.get(i2);
                    if (controllableDevice != null) {
                        JSONObject jSONObject3 = new JSONObject();
                        jSONObject3.put("did", controllableDevice.a());
                        jSONObject3.put("desc", controllableDevice.b());
                        jSONArray2.put(jSONObject3);
                    }
                }
                jSONObject2.put("device_list", jSONArray2);
            }
            jSONObject.put("detail", jSONObject2);
            ArrayList arrayList = new ArrayList();
            arrayList.add(new KeyValuePair("data", jSONObject.toString()));
            return CoreApi.a().a(SHApplication.getAppContext(), new NetRequest.Builder().a("POST").b("/v2/voicectrl/set_room_maindev").b((List<KeyValuePair>) arrayList).a(), new JsonParser<JSONObject>() {
                /* renamed from: a */
                public JSONObject parse(JSONObject jSONObject) throws JSONException {
                    return jSONObject;
                }
            }, Crypto.RC4, asyncCallback);
        } catch (Exception e2) {
            e2.printStackTrace();
            return null;
        }
    }

    public AsyncHandle g(AsyncCallback<JSONObject, Error> asyncCallback) {
        try {
            JSONObject jSONObject = new JSONObject();
            ArrayList arrayList = new ArrayList();
            arrayList.add(new KeyValuePair("data", jSONObject.toString()));
            return CoreApi.a().a(SHApplication.getAppContext(), new NetRequest.Builder().a("POST").b("/v2/voicectrl/ai_category").b((List<KeyValuePair>) arrayList).a(), new JsonParser<JSONObject>() {
                /* renamed from: a */
                public JSONObject parse(JSONObject jSONObject) throws JSONException {
                    return jSONObject;
                }
            }, Crypto.RC4, asyncCallback);
        } catch (Exception e2) {
            e2.printStackTrace();
            return null;
        }
    }

    public AsyncHandle c(String str, String str2, AsyncCallback<JSONObject, Error> asyncCallback) {
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("room_id", Long.parseLong(str));
            jSONObject.put("did", str2);
            ArrayList arrayList = new ArrayList();
            arrayList.add(new KeyValuePair("data", jSONObject.toString()));
            return CoreApi.a().a(SHApplication.getAppContext(), new NetRequest.Builder().a("POST").b("/v2/voicectrl/ai_cate_devlist").b((List<KeyValuePair>) arrayList).a(), new JsonParser<JSONObject>() {
                /* renamed from: a */
                public JSONObject parse(JSONObject jSONObject) throws JSONException {
                    return jSONObject;
                }
            }, Crypto.RC4, asyncCallback);
        } catch (Exception e2) {
            e2.printStackTrace();
            return null;
        }
    }

    public AsyncHandle h(AsyncCallback<JSONObject, Error> asyncCallback) {
        try {
            JSONObject jSONObject = new JSONObject();
            ArrayList arrayList = new ArrayList();
            arrayList.add(new KeyValuePair("data", jSONObject.toString()));
            return CoreApi.a().a(SHApplication.getAppContext(), new NetRequest.Builder().a("POST").b("/v2/voicectrl/ctrl_voice_models").b((List<KeyValuePair>) arrayList).a(), new JsonParser<JSONObject>() {
                /* renamed from: a */
                public JSONObject parse(JSONObject jSONObject) throws JSONException {
                    return jSONObject;
                }
            }, Crypto.RC4, asyncCallback);
        } catch (Exception e2) {
            e2.printStackTrace();
            return null;
        }
    }

    public AsyncHandle h(String str, AsyncCallback<JSONObject, Error> asyncCallback) {
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("did", str);
            jSONObject.put("type", "alias");
            ArrayList arrayList = new ArrayList();
            arrayList.add(new KeyValuePair("data", jSONObject.toString()));
            return CoreApi.a().a(SHApplication.getAppContext(), new NetRequest.Builder().a("POST").b("/v2/device/get_voice_conf").b((List<KeyValuePair>) arrayList).a(), new JsonParser<JSONObject>() {
                /* renamed from: a */
                public JSONObject parse(JSONObject jSONObject) throws JSONException {
                    return jSONObject;
                }
            }, Crypto.RC4, asyncCallback);
        } catch (Exception e2) {
            e2.printStackTrace();
            return null;
        }
    }

    public AsyncHandle a(String str, List<String> list, AsyncCallback<JSONObject, Error> asyncCallback, String str2, List<PowerMultikeyBean> list2) {
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("did", str);
            jSONObject.put("type", "alias");
            jSONObject.put("reset", false);
            if (TextUtils.isEmpty(str2)) {
                str2 = "common";
            }
            JSONArray jSONArray = new JSONArray();
            for (String put : list) {
                jSONArray.put(put);
            }
            JSONObject jSONObject2 = new JSONObject();
            jSONObject2.put(str2, jSONArray);
            if (list2 != null && !TextUtils.equals("common", str2)) {
                for (PowerMultikeyBean next : list2) {
                    if (!TextUtils.equals(str2, next.b())) {
                        List<String> a2 = AnotherNameManager.a().a(str, (AsyncCallback<List<String>, Error>) null, false, next.b());
                        if (a2 != null) {
                            if (!a2.isEmpty()) {
                                JSONArray jSONArray2 = new JSONArray();
                                for (String put2 : a2) {
                                    jSONArray2.put(put2);
                                }
                                jSONObject2.put(next.b(), jSONArray2);
                            }
                        }
                    }
                }
            }
            jSONObject.put("value", jSONObject2);
            ArrayList arrayList = new ArrayList();
            arrayList.add(new KeyValuePair("data", jSONObject.toString()));
            return CoreApi.a().a(SHApplication.getAppContext(), new NetRequest.Builder().a("POST").b("/v2/device/set_voice_conf").b((List<KeyValuePair>) arrayList).a(), new JsonParser<JSONObject>() {
                /* renamed from: a */
                public JSONObject parse(JSONObject jSONObject) throws JSONException {
                    return jSONObject;
                }
            }, Crypto.RC4, asyncCallback);
        } catch (Exception e2) {
            e2.printStackTrace();
            return null;
        }
    }

    public AsyncHandle a(int i, AsyncCallback<JSONObject, Error> asyncCallback) {
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put(a.g, i);
            ArrayList arrayList = new ArrayList();
            arrayList.add(new KeyValuePair("data", jSONObject.toString()));
            return CoreApi.a().a(SHApplication.getAppContext(), new NetRequest.Builder().a("POST").b("/v2/message/get_msg_alert").b((List<KeyValuePair>) arrayList).a(), new JsonParser<JSONObject>() {
                /* renamed from: a */
                public JSONObject parse(JSONObject jSONObject) throws JSONException {
                    return jSONObject;
                }
            }, Crypto.RC4, asyncCallback);
        } catch (Exception e2) {
            e2.printStackTrace();
            return null;
        }
    }

    public AsyncHandle g(List<?> list, AsyncCallback<JSONObject, Error> asyncCallback) {
        try {
            JSONObject jSONObject = new JSONObject();
            JSONArray jSONArray = new JSONArray();
            for (int i = 0; i < list.size(); i++) {
                String str = (String) list.get(i);
                if (!TextUtils.isEmpty(str)) {
                    jSONArray.put(str);
                }
            }
            jSONObject.put("msgIds", jSONArray);
            ArrayList arrayList = new ArrayList();
            arrayList.add(new KeyValuePair("data", jSONObject.toString()));
            return CoreApi.a().a(SHApplication.getAppContext(), new NetRequest.Builder().a("POST").b("/v2/message/get_msg_by_ids").b((List<KeyValuePair>) arrayList).a(), new JsonParser<JSONObject>() {
                /* renamed from: a */
                public JSONObject parse(JSONObject jSONObject) throws JSONException {
                    return jSONObject;
                }
            }, Crypto.RC4, asyncCallback);
        } catch (Exception e2) {
            e2.printStackTrace();
            return null;
        }
    }

    public AsyncHandle i(AsyncCallback<P0MessageList, Error> asyncCallback) {
        try {
            JSONObject jSONObject = new JSONObject();
            ArrayList arrayList = new ArrayList();
            arrayList.add(new KeyValuePair("data", jSONObject.toString()));
            return CoreApi.a().a(SHApplication.getAppContext(), new NetRequest.Builder().a("POST").b("/v2/message/get_p0_alert").b((List<KeyValuePair>) arrayList).a(), new JsonParser<P0MessageList>() {
                /* renamed from: a */
                public P0MessageList parse(JSONObject jSONObject) throws JSONException {
                    return P0MessageList.a(jSONObject);
                }
            }, Crypto.RC4, asyncCallback);
        } catch (Exception e2) {
            e2.printStackTrace();
            return null;
        }
    }

    public AsyncHandle a(String str, String str2, String str3, AsyncCallback<JSONObject, Error> asyncCallback) {
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("msgId", str);
            jSONObject.put("did", str2);
            jSONObject.put("alertType", str3);
            ArrayList arrayList = new ArrayList();
            arrayList.add(new KeyValuePair("data", jSONObject.toString()));
            return CoreApi.a().a(SHApplication.getAppContext(), new NetRequest.Builder().a("POST").b("/v2/message/clear_p0_alert").b((List<KeyValuePair>) arrayList).a(), new JsonParser<JSONObject>() {
                /* renamed from: a */
                public JSONObject parse(JSONObject jSONObject) throws JSONException {
                    return jSONObject;
                }
            }, Crypto.RC4, asyncCallback);
        } catch (Exception e2) {
            e2.printStackTrace();
            return null;
        }
    }

    public AsyncHandle i(String str, AsyncCallback<JSONObject, Error> asyncCallback) {
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("homeId", Long.valueOf(str));
            Home j = HomeManager.a().j(str);
            if (j != null && !j.p()) {
                jSONObject.put("ownerId", j.o());
            }
            ArrayList arrayList = new ArrayList();
            arrayList.add(new KeyValuePair("data", jSONObject.toString()));
            return CoreApi.a().a(SHApplication.getAppContext(), new NetRequest.Builder().a("POST").b("/v2/room/device_descriptions_by_home").b((List<KeyValuePair>) arrayList).a(), new JsonParser<JSONObject>() {
                /* renamed from: a */
                public JSONObject parse(JSONObject jSONObject) throws JSONException {
                    return jSONObject;
                }
            }, Crypto.RC4, asyncCallback);
        } catch (Exception e2) {
            e2.printStackTrace();
            return null;
        }
    }

    public AsyncHandle a(String str, Home home, AsyncCallback<JSONObject, Error> asyncCallback) {
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("roomId", Long.valueOf(str));
            if (home != null && !home.p()) {
                jSONObject.put("ownerId", home.o());
            }
            ArrayList arrayList = new ArrayList();
            arrayList.add(new KeyValuePair("data", jSONObject.toString()));
            return CoreApi.a().a(SHApplication.getAppContext(), new NetRequest.Builder().a("POST").b("/v2/room/device_description").b((List<KeyValuePair>) arrayList).a(), new JsonParser<JSONObject>() {
                /* renamed from: a */
                public JSONObject parse(JSONObject jSONObject) throws JSONException {
                    return jSONObject;
                }
            }, Crypto.RC4, asyncCallback);
        } catch (Exception e2) {
            e2.printStackTrace();
            return null;
        }
    }

    public AsyncHandle j(String str, AsyncCallback<JSONObject, Error> asyncCallback) {
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("roomId", Long.valueOf(str));
            ArrayList arrayList = new ArrayList();
            arrayList.add(new KeyValuePair("data", jSONObject.toString()));
            return CoreApi.a().a(SHApplication.getAppContext(), new NetRequest.Builder().a("POST").b("/v2/room/avaiable_options").b((List<KeyValuePair>) arrayList).a(), new JsonParser<JSONObject>() {
                /* renamed from: a */
                public JSONObject parse(JSONObject jSONObject) throws JSONException {
                    return jSONObject;
                }
            }, Crypto.RC4, asyncCallback);
        } catch (Exception e2) {
            e2.printStackTrace();
            return null;
        }
    }

    public AsyncHandle a(String str, JSONObject jSONObject, AsyncCallback<JSONObject, Error> asyncCallback) {
        try {
            JSONObject jSONObject2 = new JSONObject();
            jSONObject2.put("roomId", Long.valueOf(str));
            jSONObject2.put("param", jSONObject);
            ArrayList arrayList = new ArrayList();
            arrayList.add(new KeyValuePair("data", jSONObject2.toString()));
            return CoreApi.a().a(SHApplication.getAppContext(), new NetRequest.Builder().a("POST").b("/v2/room/excute_option").b((List<KeyValuePair>) arrayList).a(), new JsonParser<JSONObject>() {
                /* renamed from: a */
                public JSONObject parse(JSONObject jSONObject) throws JSONException {
                    return jSONObject;
                }
            }, Crypto.RC4, asyncCallback);
        } catch (Exception e2) {
            e2.printStackTrace();
            return null;
        }
    }

    public AsyncHandle a(Context context, long j, int i, AsyncCallback<List<MessageRecordTypeList>, Error> asyncCallback) {
        ArrayList arrayList = new ArrayList();
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("timestamp", j);
            jSONObject.put("limit", i);
        } catch (Exception | JSONException unused) {
        }
        arrayList.add(new KeyValuePair("data", jSONObject.toString()));
        return CoreApi.a().a(context, new NetRequest.Builder().a("POST").b("/v2/message/v2/typelist").b((List<KeyValuePair>) arrayList).a(), new JsonParser<List<MessageRecordTypeList>>() {
            /* renamed from: a */
            public List<MessageRecordTypeList> parse(JSONObject jSONObject) throws JSONException {
                if (jSONObject != null && GlobalSetting.q) {
                    LogUtil.a("typelist", jSONObject.toString());
                }
                ArrayList arrayList = new ArrayList();
                JSONArray optJSONArray = jSONObject.optJSONArray("messages");
                int length = optJSONArray.length();
                for (int i = 0; i < length; i++) {
                    arrayList.add(MessageRecordTypeList.parseMessageRecord(optJSONArray.optJSONObject(i)));
                }
                return arrayList;
            }
        }, Crypto.RC4, asyncCallback);
    }

    public AsyncHandle a(Context context, String str, long j, int i, AsyncCallback<List<MessageRecordTypeList>, Error> asyncCallback) {
        ArrayList arrayList = new ArrayList();
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("did", str);
            jSONObject.put("type", 6);
            jSONObject.put("timestamp", j);
            jSONObject.put("limit", i);
            jSONObject.put("force_read", true);
        } catch (Exception | JSONException unused) {
        }
        arrayList.add(new KeyValuePair("data", jSONObject.toString()));
        return CoreApi.a().a(context, new NetRequest.Builder().a("POST").b("/v2/message/v2/list").b((List<KeyValuePair>) arrayList).a(), new JsonParser<List<MessageRecordTypeList>>() {
            /* renamed from: a */
            public List<MessageRecordTypeList> parse(JSONObject jSONObject) throws JSONException {
                if (jSONObject != null && GlobalSetting.q) {
                    LogUtil.a("getMessageListByDid", jSONObject.toString());
                }
                ArrayList arrayList = new ArrayList();
                JSONArray optJSONArray = jSONObject.optJSONArray("messages");
                int length = optJSONArray.length();
                for (int i = 0; i < length; i++) {
                    arrayList.add(MessageRecordTypeList.parseMessageRecord(optJSONArray.optJSONObject(i)));
                }
                return arrayList;
            }
        }, Crypto.RC4, asyncCallback);
    }

    public AsyncHandle a(Context context, int i, long j, int i2, AsyncCallback<List<MessageRecordTypeList>, Error> asyncCallback) {
        ArrayList arrayList = new ArrayList();
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("type", i);
            jSONObject.put("timestamp", j);
            jSONObject.put("limit", i2);
            jSONObject.put("force_read", true);
        } catch (Exception | JSONException unused) {
        }
        arrayList.add(new KeyValuePair("data", jSONObject.toString()));
        return CoreApi.a().a(context, new NetRequest.Builder().a("POST").b("/v2/message/v2/list").b((List<KeyValuePair>) arrayList).a(), new JsonParser<List<MessageRecordTypeList>>() {
            /* renamed from: a */
            public List<MessageRecordTypeList> parse(JSONObject jSONObject) throws JSONException {
                if (jSONObject != null && GlobalSetting.q) {
                    LogUtil.a("getMessageListByDid", jSONObject.toString());
                }
                ArrayList arrayList = new ArrayList();
                JSONArray optJSONArray = jSONObject.optJSONArray("messages");
                int length = optJSONArray.length();
                for (int i = 0; i < length; i++) {
                    arrayList.add(MessageRecordTypeList.parseMessageRecord(optJSONArray.optJSONObject(i)));
                }
                return arrayList;
            }
        }, Crypto.RC4, asyncCallback);
    }

    public AsyncHandle a(Context context, long j, long j2, int i, String str, AsyncCallback<JSONObject, Error> asyncCallback) {
        ArrayList arrayList = new ArrayList();
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("home_owner", j);
            jSONObject.put("home_id", j2);
            jSONObject.put("limit", i);
            jSONObject.put("start_did", str);
        } catch (Exception | JSONException unused) {
        }
        arrayList.add(new KeyValuePair("data", jSONObject.toString()));
        return CoreApi.a().a(context, new NetRequest.Builder().a("POST").b("/v2/home/home_device_list").b((List<KeyValuePair>) arrayList).a(), new JsonParser<JSONObject>() {
            /* renamed from: a */
            public JSONObject parse(JSONObject jSONObject) throws JSONException {
                if (jSONObject != null && GlobalSetting.q) {
                    LogUtil.a("getHomeDeviceList", jSONObject.toString());
                }
                return jSONObject;
            }
        }, Crypto.RC4, asyncCallback);
    }

    public AsyncHandle h(List<Home> list, AsyncCallback<JSONObject, Error> asyncCallback) {
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("fetch_own", true);
            jSONObject.put("fetch_share", true);
            if (list != null && !list.isEmpty()) {
                JSONArray jSONArray = new JSONArray();
                for (int i = 0; i < list.size(); i++) {
                    Home home = list.get(i);
                    if (home != null) {
                        if (!home.p()) {
                            JSONObject jSONObject2 = new JSONObject();
                            jSONObject2.put("home_owner", home.o());
                            jSONObject2.put("home_id", Long.parseLong(home.j()));
                            jSONArray.put(jSONObject2);
                        }
                    }
                }
                jSONObject.put("fetch_home", jSONArray);
            }
            ArrayList arrayList = new ArrayList();
            arrayList.add(new KeyValuePair("data", jSONObject.toString()));
            return CoreApi.a().a(SHApplication.getAppContext(), new NetRequest.Builder().a("POST").b("/v2/user/get_device_cnt").b((List<KeyValuePair>) arrayList).a(), new JsonParser<JSONObject>() {
                /* renamed from: a */
                public JSONObject parse(JSONObject jSONObject) throws JSONException {
                    return jSONObject;
                }
            }, Crypto.RC4, asyncCallback);
        } catch (Exception e2) {
            e2.printStackTrace();
            return null;
        }
    }

    public AsyncHandle i(List<String> list, AsyncCallback<JSONObject, Error> asyncCallback) {
        if (list == null || list.isEmpty()) {
            if (asyncCallback != null) {
                asyncCallback.onFailure(new Error(-1, "invalid serviceKey parameters"));
            }
            return null;
        }
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("platform", "android");
            jSONObject.put("version", String.valueOf(SystemApi.a().e(SHApplication.getAppContext())));
            JSONArray jSONArray = new JSONArray();
            for (int i = 0; i < list.size(); i++) {
                String str = list.get(i);
                if (!TextUtils.isEmpty(str)) {
                    jSONArray.put(str);
                }
            }
            jSONObject.put("grey_key", jSONArray);
            ArrayList arrayList = new ArrayList();
            arrayList.add(new KeyValuePair("data", jSONObject.toString()));
            return CoreApi.a().a(SHApplication.getAppContext(), new NetRequest.Builder().a("POST").b("/v2/user/check_gray").b((List<KeyValuePair>) arrayList).a(), new JsonParser<JSONObject>() {
                /* renamed from: a */
                public JSONObject parse(JSONObject jSONObject) throws JSONException {
                    return jSONObject;
                }
            }, Crypto.RC4, asyncCallback);
        } catch (Exception e2) {
            e2.printStackTrace();
            return null;
        }
    }
}
