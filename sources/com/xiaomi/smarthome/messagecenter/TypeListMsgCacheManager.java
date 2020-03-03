package com.xiaomi.smarthome.messagecenter;

import android.os.AsyncTask;
import android.os.Looper;
import android.text.TextUtils;
import android.util.Pair;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.stmt.QueryBuilder;
import com.xiaomi.smarthome.application.SHApplication;
import com.xiaomi.smarthome.frame.Error;
import com.xiaomi.smarthome.frame.core.CoreApi;
import com.xiaomi.smarthome.library.common.util.AsyncTaskUtils;
import com.xiaomi.smarthome.library.common.util.PreferenceUtils;
import com.xiaomi.smarthome.miio.db.TypeListMsgDBHelper;
import com.xiaomi.smarthome.miio.db.record.MessageRecordTypeList;
import io.reactivex.disposables.Disposable;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import org.json.JSONException;
import org.json.JSONObject;

public class TypeListMsgCacheManager {
    public static long b = 0;
    private static final int c = 5000;
    private static TypeListMsgCacheManager h;

    /* renamed from: a  reason: collision with root package name */
    volatile boolean f10471a = false;
    /* access modifiers changed from: private */
    public List<MessageRecordTypeList> d = new ArrayList();
    /* access modifiers changed from: private */
    public List<Pair<DevicePushData, List<IMessage>>> e = new ArrayList();
    /* access modifiers changed from: private */
    public List<IMessage> f = new ArrayList();
    private MessageLoaderCallback g = null;
    private Disposable i;
    private volatile AtomicBoolean j = new AtomicBoolean(false);

    public interface MessageLoaderCallback {
        void a();

        void a(Error error);

        void a(List<MessageRecordTypeList> list);

        void b();
    }

    private TypeListMsgCacheManager() {
    }

    public static TypeListMsgCacheManager a() {
        if (h == null) {
            synchronized (TypeListMsgCacheManager.class) {
                if (h == null) {
                    h = new TypeListMsgCacheManager();
                }
            }
        }
        return h;
    }

    public void a(MessageLoaderCallback messageLoaderCallback) {
        this.g = messageLoaderCallback;
    }

    public static class DevicePushData {

        /* renamed from: a  reason: collision with root package name */
        public String f10475a;
        public String b;

        public int hashCode() {
            return TextUtils.isEmpty(this.f10475a) ? super.hashCode() : this.f10475a.hashCode();
        }

        public boolean equals(Object obj) {
            if (!(obj instanceof DevicePushData)) {
                return false;
            }
            if (TextUtils.equals(this.f10475a, ((DevicePushData) obj).f10475a)) {
                return true;
            }
            return super.equals(obj);
        }
    }

    public void b(MessageLoaderCallback messageLoaderCallback) {
        this.g = null;
    }

    public void c(MessageLoaderCallback messageLoaderCallback) {
        h = null;
        b();
        b(messageLoaderCallback);
        this.d.clear();
        this.e.clear();
        this.f.clear();
    }

    private void f() {
        MessageLoaderCallback messageLoaderCallback = this.g;
        if (messageLoaderCallback != null) {
            messageLoaderCallback.a((List<MessageRecordTypeList>) null);
        }
    }

    private void g() {
        MessageLoaderCallback messageLoaderCallback = this.g;
        if (messageLoaderCallback != null) {
            messageLoaderCallback.a();
        }
    }

    private void h() {
        MessageLoaderCallback messageLoaderCallback = this.g;
        if (messageLoaderCallback != null) {
            messageLoaderCallback.b();
        }
    }

    private void a(Error error) {
        MessageLoaderCallback messageLoaderCallback = this.g;
        if (messageLoaderCallback != null) {
            messageLoaderCallback.a(error);
        }
    }

    public void b() {
        if (this.i != null && !this.i.isDisposed()) {
            this.i.dispose();
        }
    }

    private static class RxJavaExceptionError extends Exception {
        private Object object;

        public RxJavaExceptionError(Object obj) {
            this.object = obj;
        }

        public Object get() {
            return this.object;
        }
    }

    public List<Pair<DevicePushData, List<IMessage>>> c() {
        return this.e;
    }

    public List<IMessage> d() {
        return this.f;
    }

    private void a(List<MessageRecordTypeList> list, List<Pair<DevicePushData, List<IMessage>>> list2, List<IMessage> list3) {
        List list4;
        if (Looper.getMainLooper() != Looper.myLooper()) {
            for (int i2 = 0; i2 < list.size(); i2++) {
                MessageRecordTypeList messageRecordTypeList = list.get(i2);
                if (messageRecordTypeList != null) {
                    IMessageManager a2 = AllTypeMsgManager.a().a(messageRecordTypeList);
                    if (!TextUtils.equals(messageRecordTypeList.messageType, "6")) {
                        list3.add(a2.a(messageRecordTypeList.toMsgRecord()));
                    } else if (TextUtils.isEmpty(messageRecordTypeList.params)) {
                        list3.add(a2.a(messageRecordTypeList.toMsgRecord()));
                    } else {
                        try {
                            JSONObject jSONObject = new JSONObject(messageRecordTypeList.params);
                            if (jSONObject.isNull("body")) {
                                list3.add(a2.a(messageRecordTypeList.toMsgRecord()));
                            } else {
                                JSONObject optJSONObject = jSONObject.optJSONObject("body");
                                if (optJSONObject.isNull("did")) {
                                    list3.add(a2.a(messageRecordTypeList.toMsgRecord()));
                                } else {
                                    String optString = optJSONObject.optString("did");
                                    String optString2 = optJSONObject.optString("model");
                                    if (!TextUtils.isEmpty(optString)) {
                                        if (!TextUtils.isEmpty(optString2)) {
                                            if (CoreApi.a().d(optString2) != null) {
                                                Pair<DevicePushData, List<IMessage>> b2 = b(optString);
                                                if (b2 == null) {
                                                    list4 = null;
                                                } else {
                                                    list4 = (List) b2.second;
                                                }
                                                if (b2 == null || list4 == null) {
                                                    list4 = new ArrayList();
                                                    DevicePushData devicePushData = new DevicePushData();
                                                    devicePushData.f10475a = optString;
                                                    devicePushData.b = optString2;
                                                    list2.add(new Pair(devicePushData, list4));
                                                }
                                                list4.add(a2.a(messageRecordTypeList.toMsgRecord()));
                                                Collections.sort(list4, new Comparator<IMessage>() {
                                                    /* renamed from: a */
                                                    public int compare(IMessage iMessage, IMessage iMessage2) {
                                                        return (int) (iMessage2.a() - iMessage.a());
                                                    }
                                                });
                                            }
                                        }
                                    }
                                    list3.add(a2.a(messageRecordTypeList.toMsgRecord()));
                                }
                            }
                        } catch (JSONException e2) {
                            list3.add(a2.a(messageRecordTypeList.toMsgRecord()));
                            e2.printStackTrace();
                        }
                    }
                }
            }
            a(list2);
            return;
        }
        throw new RuntimeException();
    }

    private void a(List<Pair<DevicePushData, List<IMessage>>> list) {
        boolean z;
        if (!list.isEmpty()) {
            int i2 = 0;
            while (i2 < list.size()) {
                Pair pair = list.get(i2);
                if (pair != null) {
                    List list2 = (List) pair.second;
                    if (list2 == null || list2.size() == 0) {
                        list.remove(i2);
                        i2--;
                    } else {
                        int i3 = 0;
                        while (true) {
                            if (i3 >= list2.size()) {
                                z = true;
                                break;
                            } else if (((IMessage) list2.get(i3)).f.receiveTime >= e()) {
                                z = false;
                                break;
                            } else {
                                i3++;
                            }
                        }
                        if (z) {
                            list.remove(i2);
                            i2--;
                        }
                    }
                }
                i2++;
            }
        }
    }

    private Pair<DevicePushData, List<IMessage>> b(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        for (int i2 = 0; i2 < this.e.size(); i2++) {
            Pair<DevicePushData, List<IMessage>> pair = this.e.get(i2);
            if (pair != null && pair.first != null && TextUtils.equals(str, ((DevicePushData) pair.first).f10475a)) {
                return pair;
            }
        }
        return null;
    }

    /* access modifiers changed from: private */
    public void i() {
        if (Looper.getMainLooper() != Looper.myLooper()) {
            List<MessageRecordTypeList> queryAllByRange = MessageRecordTypeList.queryAllByRange(System.currentTimeMillis() / 1000, 0, 5000);
            if (!(queryAllByRange == null || this.d == null)) {
                this.d = queryAllByRange;
            }
            this.e = new ArrayList();
            this.f = new ArrayList();
            a(queryAllByRange, this.e, this.f);
            return;
        }
        throw new RuntimeException();
    }

    private boolean b(List<MessageRecordTypeList> list) {
        if (Looper.getMainLooper() != Looper.myLooper()) {
            boolean z = false;
            if (!(list == null || list.size() == 0)) {
                long j2 = list.get(0).receiveTime;
                long j3 = list.get(list.size() - 1).receiveTime;
                List<MessageRecordTypeList> a2 = a(j2, j3);
                if (a2 != null && a2.size() != 0) {
                    this.d.addAll(list);
                    List<MessageRecordTypeList> c2 = c(list);
                    if (c2 == null || c2.size() < list.size()) {
                        z = true;
                    }
                    if (c2 != null && c2.size() > 0) {
                        MessageRecordTypeList.batchInsert(c2);
                        c2.clear();
                    }
                } else if (list != null) {
                    if (Math.min(j2, j3) < e()) {
                        ArrayList arrayList = new ArrayList();
                        arrayList.add(list.get(list.size() - 1));
                        for (int size = list.size() - 2; size >= 0; size--) {
                            MessageRecordTypeList messageRecordTypeList = list.get(size);
                            if (messageRecordTypeList.receiveTime >= e()) {
                                break;
                            }
                            if (messageRecordTypeList != null && !TextUtils.equals(messageRecordTypeList.is_new, "1")) {
                                arrayList.add(messageRecordTypeList);
                            }
                        }
                        list.removeAll(arrayList);
                        z = true;
                    }
                    this.d.addAll(list);
                    d(list);
                }
            }
            a(list, this.e, this.f);
            return z;
        }
        throw new RuntimeException();
    }

    private List<MessageRecordTypeList> c(List<MessageRecordTypeList> list) {
        if (Looper.getMainLooper() != Looper.myLooper()) {
            long j2 = list.get(0).receiveTime;
            long j3 = list.get(list.size() - 1).receiveTime;
            try {
                HashSet hashSet = new HashSet();
                for (MessageRecordTypeList messageRecordTypeList : list) {
                    hashSet.add(messageRecordTypeList.msgId);
                }
                new ArrayList();
                Dao<MessageRecordTypeList, Integer> b2 = TypeListMsgDBHelper.a(SHApplication.getAppContext()).b();
                QueryBuilder<MessageRecordTypeList, Integer> queryBuilder = b2.queryBuilder();
                queryBuilder.where().between("receiveTime", Long.valueOf(j3), Long.valueOf(j2));
                queryBuilder.orderBy("receiveTime", false);
                List<MessageRecordTypeList> query = b2.query(queryBuilder.prepare());
                if (query != null) {
                    if (query.size() != 0) {
                        long j4 = query.get(0).receiveTime;
                        if (j4 >= j2) {
                            return null;
                        }
                        int i2 = 0;
                        for (MessageRecordTypeList messageRecordTypeList2 : list) {
                            if (messageRecordTypeList2.receiveTime > j4) {
                                i2++;
                            }
                        }
                        if (i2 > 0) {
                            return list.subList(0, i2 + 1);
                        }
                        return null;
                    }
                }
                return list;
            } catch (SQLException e2) {
                e2.printStackTrace();
            }
        } else {
            throw new RuntimeException();
        }
    }

    private void d(List<MessageRecordTypeList> list) {
        if (Looper.getMainLooper() != Looper.myLooper()) {
            MessageRecordTypeList.batchInsert(list);
            return;
        }
        throw new RuntimeException();
    }

    private List<MessageRecordTypeList> a(long j2, long j3) {
        if (Looper.getMainLooper() != Looper.myLooper()) {
            List<MessageRecordTypeList> queryAllByRange = MessageRecordTypeList.queryAllByRange(j2, j3);
            if (queryAllByRange != null && queryAllByRange.size() > 2) {
                Collections.sort(queryAllByRange, new Comparator<MessageRecordTypeList>() {
                    /* renamed from: a */
                    public int compare(MessageRecordTypeList messageRecordTypeList, MessageRecordTypeList messageRecordTypeList2) {
                        return (int) (messageRecordTypeList2.receiveTime - messageRecordTypeList.receiveTime);
                    }
                });
            }
            return queryAllByRange;
        }
        throw new RuntimeException();
    }

    public void a(String str, List<IMessage> list) {
        List<MessageRecordTypeList> list2 = this.d;
        List<Pair<DevicePushData, List<IMessage>>> list3 = this.e;
        list2.removeAll(list);
        ArrayList arrayList = new ArrayList();
        int i2 = 0;
        while (true) {
            if (i2 >= list3.size()) {
                break;
            }
            Pair pair = list3.get(i2);
            if (!TextUtils.equals(str, ((DevicePushData) pair.first).f10475a)) {
                i2++;
            } else {
                try {
                    ((List) pair.second).removeAll(list);
                    break;
                } catch (Exception e2) {
                    e2.printStackTrace();
                }
            }
        }
        for (int i3 = 0; i3 < list.size(); i3++) {
            arrayList.add(list.get(i3).c());
        }
        MessageRecordTypeList.batchDelete(arrayList);
    }

    public void a(final String str) {
        if (!TextUtils.isEmpty(str)) {
            AsyncTaskUtils.a(new AsyncTask<Object, Object, Object>() {
                /* access modifiers changed from: protected */
                public Object doInBackground(Object... objArr) {
                    TypeListMsgCacheManager.this.i();
                    Iterator it = TypeListMsgCacheManager.this.e.iterator();
                    ArrayList arrayList = new ArrayList();
                    while (true) {
                        if (!it.hasNext()) {
                            break;
                        }
                        Pair pair = (Pair) it.next();
                        if (TextUtils.equals(str, ((DevicePushData) pair.first).f10475a)) {
                            int i = 0;
                            while (i < ((List) pair.second).size()) {
                                try {
                                    arrayList.add(((IMessage) ((List) pair.second).get(i)).c());
                                    i++;
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            }
                            it.remove();
                        }
                    }
                    if (!arrayList.isEmpty()) {
                        MessageRecordTypeList.batchDelete(arrayList);
                    }
                    TypeListMsgCacheManager.this.d.clear();
                    TypeListMsgCacheManager.this.e.clear();
                    TypeListMsgCacheManager.this.f.clear();
                    return null;
                }
            }, new Object[0]);
        }
    }

    public static long e() {
        if (b == 0) {
            b = PreferenceUtils.b(SHApplication.getAppContext(), "installTime", 0) / 1000;
        }
        return b;
    }
}
