package com.xiaomi.smarthome.messagecenter;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import com.xiaomi.smarthome.library.common.util.MessageHandlerThread;
import com.xiaomi.smarthome.messagecenter.DevicePushMessageManager;
import com.xiaomi.smarthome.miio.db.record.MessageRecord;
import com.xiaomi.smarthome.miio.db.record.MessageRecordTypeList;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AllTypeMsgManager implements MessageCenterPageDataInterface {

    /* renamed from: a  reason: collision with root package name */
    public static final int f10426a = 1479106800;
    public static final int b = 9;
    public static final int c = 10;
    public static final int d = 11;
    public static final int e = 12;
    public static final int f = 13;
    public static final int g = 14;
    private static AllTypeMsgManager o;
    /* access modifiers changed from: private */
    public List<DataloadListener> h = new ArrayList();
    /* access modifiers changed from: private */
    public boolean i = false;
    private Map<String, IMessageManager> j = new HashMap();
    /* access modifiers changed from: private */
    public List<IMessage> k = new ArrayList();
    private List<IMessage> l = new ArrayList();
    private MessageHandlerThread m = new MessageHandlerThread("get_messages");
    /* access modifiers changed from: private */
    public ThreadHandler n;
    /* access modifiers changed from: private */
    public Handler p = new Handler(Looper.getMainLooper()) {
        public void handleMessage(Message message) {
            boolean z = true;
            switch (message.what) {
                case 9:
                    List list = (List) message.obj;
                    if (list != null) {
                        AllTypeMsgManager.this.k.addAll(list);
                        Collections.sort(AllTypeMsgManager.this.k, new Comparator<IMessage>() {
                            /* renamed from: a */
                            public int compare(IMessage iMessage, IMessage iMessage2) {
                                return (int) (iMessage2.a() - iMessage.a());
                            }
                        });
                    } else {
                        z = false;
                    }
                    if (z) {
                        for (DataloadListener a2 : AllTypeMsgManager.this.h) {
                            a2.a((List<IMessage>) list);
                        }
                    } else {
                        for (DataloadListener c : AllTypeMsgManager.this.h) {
                            c.c(10);
                        }
                    }
                    boolean unused = AllTypeMsgManager.this.i = false;
                    return;
                case 10:
                    List list2 = (List) message.obj;
                    if (list2 != null) {
                        AllTypeMsgManager.this.k.addAll(list2);
                        Collections.sort(AllTypeMsgManager.this.k, new Comparator<IMessage>() {
                            /* renamed from: a */
                            public int compare(IMessage iMessage, IMessage iMessage2) {
                                return (int) (iMessage2.a() - iMessage.a());
                            }
                        });
                    } else {
                        z = false;
                    }
                    if (z) {
                        for (DataloadListener a3 : AllTypeMsgManager.this.h) {
                            a3.a(10);
                        }
                    } else {
                        for (DataloadListener b : AllTypeMsgManager.this.h) {
                            b.b(10);
                        }
                    }
                    boolean unused2 = AllTypeMsgManager.this.i = false;
                    return;
                case 11:
                    List list3 = (List) message.obj;
                    Log.e("AllTypeMessage", "get cache size - " + list3.size());
                    if (list3 != null) {
                        AllTypeMsgManager.this.k.clear();
                        AllTypeMsgManager.this.k.addAll(list3);
                        Collections.sort(AllTypeMsgManager.this.k, new Comparator<IMessage>() {
                            /* renamed from: a */
                            public int compare(IMessage iMessage, IMessage iMessage2) {
                                return (int) (iMessage2.a() - iMessage.a());
                            }
                        });
                    } else {
                        z = false;
                    }
                    if (z) {
                        for (DataloadListener a4 : AllTypeMsgManager.this.h) {
                            a4.a(11);
                        }
                    } else {
                        for (DataloadListener b2 : AllTypeMsgManager.this.h) {
                            b2.b(11);
                        }
                    }
                    Message obtainMessage = AllTypeMsgManager.this.p.obtainMessage();
                    obtainMessage.what = 13;
                    if (AllTypeMsgManager.this.k.size() > 0) {
                        obtainMessage.obj = Long.valueOf(((IMessage) AllTypeMsgManager.this.k.get(0)).a());
                    } else {
                        obtainMessage.obj = 0L;
                    }
                    AllTypeMsgManager.this.n.sendMessage(obtainMessage);
                    return;
                default:
                    return;
            }
        }
    };

    public interface DataloadListener {
        void a(int i);

        void a(List<IMessage> list);

        void b(int i);

        void c(int i);
    }

    public static class MessageData {

        /* renamed from: a  reason: collision with root package name */
        public List<MessageRecord> f10431a;
        public List<MessageRecord> b;
        public long c;
        public int d;
    }

    public interface MsgListener {
        void a();

        void b();
    }

    public void a(long j2) {
    }

    public void e() {
    }

    public static AllTypeMsgManager a() {
        if (o == null) {
            o = new AllTypeMsgManager();
        }
        return o;
    }

    public AllTypeMsgManager() {
        this.j.put("1", new ShareMessageManager());
        this.j.put("5", new FamilyMessageManager());
        this.j.put("3", new WifiPwdChangedMessageManager());
        this.j.put("6", new DevicePushMessageManager());
        this.j.put("7", new CommonMessageManager());
        this.j.put("8", new HomeShareMessageManager());
    }

    public List<String> b() {
        ArrayList arrayList = new ArrayList();
        arrayList.add("7");
        arrayList.add("6");
        arrayList.add("3");
        return arrayList;
    }

    public List<String> c() {
        ArrayList arrayList = new ArrayList();
        arrayList.add("1");
        arrayList.add("8");
        return arrayList;
    }

    public Map<String, IMessageManager> d() {
        return Collections.unmodifiableMap(this.j);
    }

    class ThreadHandler extends Handler {
        public ThreadHandler(Looper looper) {
            super(looper);
        }

        public void handleMessage(Message message) {
            if (message.what == 12) {
                List<MessageRecord> queryAll = MessageRecord.queryAll();
                Message obtainMessage = AllTypeMsgManager.this.p.obtainMessage();
                obtainMessage.what = 11;
                obtainMessage.obj = AllTypeMsgManager.this.a(queryAll);
                AllTypeMsgManager.this.p.sendMessage(obtainMessage);
            }
        }
    }

    public void a(DataloadListener dataloadListener) {
        if (dataloadListener != null) {
            this.h.remove(dataloadListener);
            this.h.add(dataloadListener);
        }
    }

    public void b(DataloadListener dataloadListener) {
        if (dataloadListener != null) {
            this.h.remove(dataloadListener);
        }
    }

    public IMessageManager a(MessageRecordTypeList messageRecordTypeList) {
        return this.j.get(messageRecordTypeList.messageType);
    }

    /* access modifiers changed from: private */
    public List<IMessage> a(List<MessageRecord> list) {
        ArrayList arrayList = new ArrayList();
        for (MessageRecord next : list) {
            IMessageManager iMessageManager = this.j.get(next.messageType);
            if (iMessageManager != null) {
                IMessage a2 = iMessageManager.a(next);
                if (a2 != null) {
                    if (a2 instanceof DevicePushMessageManager.DevicePushMessage) {
                        NewMsgLocalHelper.a(a2);
                    }
                    arrayList.add(a2);
                }
            } else {
                MessageRecord.delete(next.msgId);
            }
        }
        return arrayList;
    }

    public List<IMessage> f() {
        return this.k;
    }

    public List<IMessage> g() {
        return this.l;
    }
}
