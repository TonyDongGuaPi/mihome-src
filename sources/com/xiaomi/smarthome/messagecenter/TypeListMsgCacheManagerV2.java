package com.xiaomi.smarthome.messagecenter;

import android.content.Intent;
import android.support.v4.content.LocalBroadcastManager;
import com.xiaomi.smarthome.application.SHApplication;
import com.xiaomi.smarthome.family.api.RemoteFamilyApi;
import com.xiaomi.smarthome.frame.AsyncCallback;
import com.xiaomi.smarthome.frame.Error;
import com.xiaomi.smarthome.frame.ErrorCode;
import com.xiaomi.smarthome.homeroom.UserInfoManager;
import com.xiaomi.smarthome.messagecenter.HomeShareMessageManager;
import com.xiaomi.smarthome.miio.db.record.MessageRecordTypeList;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

public class TypeListMsgCacheManagerV2 {

    /* renamed from: a  reason: collision with root package name */
    public static final String f10476a = "message_center_updated";
    public static final String b = "result_code";
    private static TypeListMsgCacheManagerV2 d;
    /* access modifiers changed from: private */
    public List<MessageRecordTypeList> c = new ArrayList();
    private Map<String, IMessageManager> e = new HashMap();

    public interface MessageLoaderCallback {
        void a();

        void a(Error error);

        void a(List<MessageRecordTypeList> list);
    }

    private TypeListMsgCacheManagerV2() {
        this.e.put("1", new ShareMessageManager());
        this.e.put("5", new FamilyMessageManager());
        this.e.put("3", new WifiPwdChangedMessageManager());
        this.e.put("6", new DevicePushMessageManager());
        this.e.put("7", new CommonMessageManager());
        this.e.put("8", new HomeShareMessageManager());
    }

    public static TypeListMsgCacheManagerV2 a() {
        if (d == null) {
            synchronized (TypeListMsgCacheManagerV2.class) {
                if (d == null) {
                    d = new TypeListMsgCacheManagerV2();
                }
            }
        }
        return d;
    }

    public void a(long j, final AsyncCallback<List<IMessage>, Error> asyncCallback) {
        RemoteFamilyApi.a().a(SHApplication.getAppContext(), j, 200, (AsyncCallback<List<MessageRecordTypeList>, Error>) new AsyncCallback<List<MessageRecordTypeList>, Error>() {
            /* renamed from: a */
            public void onSuccess(List<MessageRecordTypeList> list) {
                List<IMessage> a2 = TypeListMsgCacheManagerV2.this.a(list);
                if (asyncCallback != null) {
                    asyncCallback.onSuccess(a2);
                }
                MessageRecordTypeList.deleteAll();
                if (a2 == null || a2.isEmpty()) {
                    List unused = TypeListMsgCacheManagerV2.this.c = new ArrayList();
                    TypeListMsgCacheManagerV2.this.a(ErrorCode.SUCCESS.getCode());
                    return;
                }
                MessageRecordTypeList.batchInsert(list);
                TypeListMsgCacheManagerV2.this.c.addAll(list);
            }

            public void onFailure(Error error) {
                if (asyncCallback != null) {
                    asyncCallback.onFailure(error);
                }
                TypeListMsgCacheManagerV2.this.a(error == null ? ErrorCode.ERROR_UNLOGIN.getCode() : error.a());
            }
        });
    }

    public List<IMessage> b() {
        return a(MessageRecordTypeList.queryAll());
    }

    public List<IMessage> a(List<MessageRecordTypeList> list) {
        IMessageManager iMessageManager;
        IMessage a2;
        if (list == null || list.isEmpty()) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        HashSet hashSet = new HashSet();
        for (int i = 0; i < list.size(); i++) {
            MessageRecordTypeList messageRecordTypeList = list.get(i);
            if (!(messageRecordTypeList == null || (iMessageManager = this.e.get(messageRecordTypeList.messageType)) == null || (a2 = iMessageManager.a(messageRecordTypeList.toMsgRecord())) == null)) {
                arrayList.add(a2);
                if (a2 instanceof HomeShareMessageManager.HomeShareMessage) {
                    long f = ((HomeShareMessageManager.HomeShareMessage) a2).f();
                    if (f != 0 && UserInfoManager.a().a(f) == null) {
                        hashSet.add(Long.valueOf(f));
                    }
                }
            }
        }
        if (!hashSet.isEmpty()) {
            UserInfoManager.a().a(hashSet, (UserInfoManager.UpdateCompleteCallBack) null);
        }
        return arrayList;
    }

    public void b(long j, final AsyncCallback<List<IMessage>, Error> asyncCallback) {
        RemoteFamilyApi.a().a(SHApplication.getAppContext(), j, 100, (AsyncCallback<List<MessageRecordTypeList>, Error>) new AsyncCallback<List<MessageRecordTypeList>, Error>() {
            /* renamed from: a */
            public void onSuccess(List<MessageRecordTypeList> list) {
                List<IMessage> a2 = TypeListMsgCacheManagerV2.this.a(list);
                if (asyncCallback != null) {
                    asyncCallback.onSuccess(a2);
                }
                if (a2 == null || a2.isEmpty()) {
                    TypeListMsgCacheManagerV2.this.a(ErrorCode.SUCCESS.getCode());
                } else {
                    TypeListMsgCacheManagerV2.this.c.addAll(list);
                }
            }

            public void onFailure(Error error) {
                if (asyncCallback != null) {
                    asyncCallback.onFailure(error);
                }
                TypeListMsgCacheManagerV2.this.a(error == null ? ErrorCode.ERROR_UNLOGIN.getCode() : error.a());
            }
        });
    }

    public void a(String str, List<IMessage> list) {
        this.c.removeAll(list);
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < list.size(); i++) {
            arrayList.add(list.get(i).c());
        }
        MessageRecordTypeList.batchDelete(arrayList);
    }

    public void c() {
        this.c = new ArrayList();
    }

    /* access modifiers changed from: private */
    public void a(int i) {
        Intent intent = new Intent(f10476a);
        intent.putExtra("result_code", i);
        LocalBroadcastManager.getInstance(SHApplication.getAppContext()).sendBroadcast(intent);
    }

    public List<MessageRecordTypeList> d() {
        return this.c;
    }
}
