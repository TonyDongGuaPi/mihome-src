package com.xiaomi.smarthome.messagecenter.shopmessage;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import com.xiaomi.smarthome.application.SHApplication;
import com.xiaomi.smarthome.family.api.RemoteFamilyApi;
import com.xiaomi.smarthome.family.api.ShopApi;
import com.xiaomi.smarthome.frame.AsyncCallback;
import com.xiaomi.smarthome.frame.Error;
import com.xiaomi.smarthome.library.common.util.MessageHandlerThread;
import com.xiaomi.smarthome.messagecenter.shopmessage.ShopMessageManagerV2;
import com.xiaomi.smarthome.miio.db.record.MessageRecordShop;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class ShopTypeMsgManager {

    /* renamed from: a  reason: collision with root package name */
    public static final int f10491a = 9;
    public static final int b = 10;
    public static final int c = 11;
    public static final int d = 12;
    public static final int e = 13;
    public static final int f = 14;
    private static ShopTypeMsgManager n;
    /* access modifiers changed from: private */
    public List<DataloadListener> g = new ArrayList();
    /* access modifiers changed from: private */
    public boolean h = false;
    private ShopMessageManagerV2 i = new ShopMessageManagerV2();
    /* access modifiers changed from: private */
    public List<ShopMessageManagerV2.ShopMessageV2> j = new ArrayList();
    /* access modifiers changed from: private */
    public ShopMessageManagerV2.ShopMessageV2 k = null;
    private MessageHandlerThread l = new MessageHandlerThread("get_messages");
    /* access modifiers changed from: private */
    public ThreadHandler m;
    /* access modifiers changed from: private */
    public Handler o = new Handler() {
        public void handleMessage(Message message) {
            boolean z = true;
            switch (message.what) {
                case 9:
                    List list = (List) message.obj;
                    if (list != null) {
                        ShopTypeMsgManager.this.j.addAll(list);
                        Collections.sort(ShopTypeMsgManager.this.j, new Comparator<ShopMessageManagerV2.ShopMessageV2>() {
                            /* renamed from: a */
                            public int compare(ShopMessageManagerV2.ShopMessageV2 shopMessageV2, ShopMessageManagerV2.ShopMessageV2 shopMessageV22) {
                                return (int) (shopMessageV22.c() - shopMessageV2.c());
                            }
                        });
                    } else {
                        z = false;
                    }
                    if (z) {
                        for (DataloadListener a2 : ShopTypeMsgManager.this.g) {
                            a2.a((List<ShopMessageManagerV2.ShopMessageV2>) list);
                        }
                    } else {
                        for (DataloadListener c : ShopTypeMsgManager.this.g) {
                            c.c(10);
                        }
                    }
                    boolean unused = ShopTypeMsgManager.this.h = false;
                    return;
                case 10:
                    List list2 = (List) message.obj;
                    if (list2 != null) {
                        ShopTypeMsgManager.this.j.addAll(list2);
                        Collections.sort(ShopTypeMsgManager.this.j, new Comparator<ShopMessageManagerV2.ShopMessageV2>() {
                            /* renamed from: a */
                            public int compare(ShopMessageManagerV2.ShopMessageV2 shopMessageV2, ShopMessageManagerV2.ShopMessageV2 shopMessageV22) {
                                return (int) (shopMessageV22.c() - shopMessageV2.c());
                            }
                        });
                    } else {
                        z = false;
                    }
                    if (z) {
                        for (DataloadListener a3 : ShopTypeMsgManager.this.g) {
                            a3.a(10);
                        }
                    } else {
                        for (DataloadListener b : ShopTypeMsgManager.this.g) {
                            b.b(10);
                        }
                    }
                    boolean unused2 = ShopTypeMsgManager.this.h = false;
                    return;
                case 11:
                    List list3 = (List) message.obj;
                    Log.e("AllTypeMessage", "get cache size - " + list3.size());
                    if (list3 != null) {
                        ShopTypeMsgManager.this.j.clear();
                        ShopTypeMsgManager.this.j.addAll(list3);
                        Collections.sort(ShopTypeMsgManager.this.j, new Comparator<ShopMessageManagerV2.ShopMessageV2>() {
                            /* renamed from: a */
                            public int compare(ShopMessageManagerV2.ShopMessageV2 shopMessageV2, ShopMessageManagerV2.ShopMessageV2 shopMessageV22) {
                                return (int) (shopMessageV22.c() - shopMessageV2.c());
                            }
                        });
                    } else {
                        z = false;
                    }
                    if (z) {
                        for (DataloadListener a4 : ShopTypeMsgManager.this.g) {
                            a4.a(11);
                        }
                    } else {
                        for (DataloadListener b2 : ShopTypeMsgManager.this.g) {
                            b2.b(11);
                        }
                    }
                    Message obtainMessage = ShopTypeMsgManager.this.m.obtainMessage();
                    obtainMessage.what = 13;
                    if (ShopTypeMsgManager.this.j.size() > 0) {
                        obtainMessage.obj = Long.valueOf(((ShopMessageManagerV2.ShopMessageV2) ShopTypeMsgManager.this.j.get(0)).c());
                    } else {
                        obtainMessage.obj = 0L;
                    }
                    ShopTypeMsgManager.this.m.sendMessage(obtainMessage);
                    return;
                default:
                    return;
            }
        }
    };

    public interface DataloadListener {
        void a(int i);

        void a(List<ShopMessageManagerV2.ShopMessageV2> list);

        void b(int i);

        void c(int i);
    }

    public interface MsgListener {
        void a();

        void b();
    }

    public static class ShopMessageData {

        /* renamed from: a  reason: collision with root package name */
        public List<MessageRecordShop> f10502a;
        public long b;
        public int c;
    }

    public static ShopTypeMsgManager a() {
        if (n == null) {
            n = new ShopTypeMsgManager();
        }
        return n;
    }

    class ThreadHandler extends Handler {
        public ThreadHandler(Looper looper) {
            super(looper);
        }

        public void handleMessage(Message message) {
            switch (message.what) {
                case 12:
                    List<MessageRecordShop> queryAll = MessageRecordShop.queryAll();
                    Message obtainMessage = ShopTypeMsgManager.this.o.obtainMessage();
                    obtainMessage.what = 11;
                    obtainMessage.obj = ShopTypeMsgManager.this.a(queryAll);
                    ShopTypeMsgManager.this.o.sendMessage(obtainMessage);
                    return;
                case 13:
                    RemoteFamilyApi.a().b(SHApplication.getAppContext(), ((Long) message.obj).longValue(), (AsyncCallback<ShopMessageData, Error>) new AsyncCallback<ShopMessageData, Error>() {
                        /* renamed from: a */
                        public void onSuccess(ShopMessageData shopMessageData) {
                            ShopTypeMsgManager.this.j.clear();
                            List a2 = ShopTypeMsgManager.this.a(shopMessageData.f10502a);
                            Message obtainMessage = ShopTypeMsgManager.this.o.obtainMessage();
                            obtainMessage.what = 10;
                            obtainMessage.obj = a2;
                            ShopTypeMsgManager.this.o.sendMessage(obtainMessage);
                        }

                        public void onFailure(Error error) {
                            Message obtainMessage = ShopTypeMsgManager.this.o.obtainMessage();
                            obtainMessage.what = 10;
                            ShopTypeMsgManager.this.o.sendMessage(obtainMessage);
                        }
                    });
                    return;
                case 14:
                    RemoteFamilyApi.a().a(SHApplication.getAppContext(), ((Long) message.obj).longValue(), (AsyncCallback<ShopMessageData, Error>) new AsyncCallback<ShopMessageData, Error>() {
                        /* renamed from: a */
                        public void onSuccess(ShopMessageData shopMessageData) {
                            List a2 = ShopTypeMsgManager.this.a(shopMessageData.f10502a);
                            Message obtainMessage = ShopTypeMsgManager.this.o.obtainMessage();
                            obtainMessage.what = 9;
                            obtainMessage.obj = a2;
                            ShopTypeMsgManager.this.o.sendMessage(obtainMessage);
                        }

                        public void onFailure(Error error) {
                            Message obtainMessage = ShopTypeMsgManager.this.o.obtainMessage();
                            obtainMessage.what = 9;
                            ShopTypeMsgManager.this.o.sendMessage(obtainMessage);
                        }
                    });
                    return;
                default:
                    return;
            }
        }
    }

    public void a(long j2) {
        if (!this.h) {
            this.h = true;
            Message obtainMessage = this.m.obtainMessage(14);
            obtainMessage.obj = Long.valueOf(j2);
            this.m.sendMessage(obtainMessage);
        }
    }

    public void a(DataloadListener dataloadListener) {
        if (dataloadListener != null) {
            this.g.remove(dataloadListener);
            this.g.add(dataloadListener);
        }
    }

    public void b(DataloadListener dataloadListener) {
        if (dataloadListener != null) {
            this.g.remove(dataloadListener);
        }
    }

    /* access modifiers changed from: private */
    public List<ShopMessageManagerV2.ShopMessageV2> a(List<MessageRecordShop> list) {
        ArrayList arrayList = new ArrayList();
        if (list == null) {
            return arrayList;
        }
        for (MessageRecordShop next : list) {
            if (this.i != null) {
                ShopMessageManagerV2.ShopMessageV2 a2 = this.i.a(next);
                if (a2 != null) {
                    arrayList.add(a2);
                }
            } else {
                MessageRecordShop.delete(next.msgId);
            }
        }
        return arrayList;
    }

    public void b() {
        if (!this.h) {
            this.h = true;
            if (!this.l.isAlive()) {
                this.l.start();
            }
            this.m = new ThreadHandler(this.l.getLooper());
            Message obtainMessage = this.m.obtainMessage();
            obtainMessage.what = 13;
            obtainMessage.obj = 0L;
            this.m.sendMessage(obtainMessage);
        }
    }

    public List<ShopMessageManagerV2.ShopMessageV2> c() {
        return this.j;
    }

    public ShopMessageManagerV2.ShopMessageV2 d() {
        return this.k;
    }

    public void c(final DataloadListener dataloadListener) {
        ShopApi.a().a(SHApplication.getAppContext(), (AsyncCallback<ShopMessageData, Error>) new AsyncCallback<ShopMessageData, Error>() {
            /* renamed from: a */
            public void onSuccess(ShopMessageData shopMessageData) {
                List a2 = ShopTypeMsgManager.this.a(shopMessageData.f10502a);
                if (a2 == null || a2.size() == 0) {
                    if (dataloadListener != null) {
                        dataloadListener.a(0);
                    }
                    ShopMessageManagerV2.ShopMessageV2 unused = ShopTypeMsgManager.this.k = null;
                    return;
                }
                Collections.sort(a2, new Comparator<ShopMessageManagerV2.ShopMessageV2>() {
                    /* renamed from: a */
                    public int compare(ShopMessageManagerV2.ShopMessageV2 shopMessageV2, ShopMessageManagerV2.ShopMessageV2 shopMessageV22) {
                        return (int) (shopMessageV22.c() - shopMessageV2.c());
                    }
                });
                ShopMessageManagerV2.ShopMessageV2 unused2 = ShopTypeMsgManager.this.k = (ShopMessageManagerV2.ShopMessageV2) a2.get(0);
                if (dataloadListener != null) {
                    dataloadListener.a(1);
                }
            }

            public void onFailure(Error error) {
                ShopMessageManagerV2.ShopMessageV2 unused = ShopTypeMsgManager.this.k = null;
                if (dataloadListener != null) {
                    dataloadListener.b(error.a());
                }
            }
        });
    }

    public void d(final DataloadListener dataloadListener) {
        RemoteFamilyApi.a().b(SHApplication.getAppContext(), 0, (AsyncCallback<ShopMessageData, Error>) new AsyncCallback<ShopMessageData, Error>() {
            /* renamed from: a */
            public void onSuccess(ShopMessageData shopMessageData) {
                List a2 = ShopTypeMsgManager.this.a(shopMessageData.f10502a);
                Collections.sort(a2, new Comparator<ShopMessageManagerV2.ShopMessageV2>() {
                    /* renamed from: a */
                    public int compare(ShopMessageManagerV2.ShopMessageV2 shopMessageV2, ShopMessageManagerV2.ShopMessageV2 shopMessageV22) {
                        return (int) (shopMessageV22.c() - shopMessageV2.c());
                    }
                });
                List unused = ShopTypeMsgManager.this.j = a2;
                if (dataloadListener != null) {
                    dataloadListener.a(a2 == null ? 0 : a2.size());
                }
            }

            public void onFailure(Error error) {
                if (dataloadListener != null) {
                    dataloadListener.b(error.a());
                }
            }
        });
    }

    public void a(long j2, final DataloadListener dataloadListener) {
        RemoteFamilyApi.a().a(SHApplication.getAppContext(), j2, (AsyncCallback<ShopMessageData, Error>) new AsyncCallback<ShopMessageData, Error>() {
            /* renamed from: a */
            public void onSuccess(ShopMessageData shopMessageData) {
                List a2 = ShopTypeMsgManager.this.a(shopMessageData.f10502a);
                Collections.sort(a2, new Comparator<ShopMessageManagerV2.ShopMessageV2>() {
                    /* renamed from: a */
                    public int compare(ShopMessageManagerV2.ShopMessageV2 shopMessageV2, ShopMessageManagerV2.ShopMessageV2 shopMessageV22) {
                        return (int) (shopMessageV22.c() - shopMessageV2.c());
                    }
                });
                ShopTypeMsgManager.this.j.addAll(a2);
                if (dataloadListener != null) {
                    dataloadListener.a((List<ShopMessageManagerV2.ShopMessageV2>) a2);
                }
            }

            public void onFailure(Error error) {
                if (dataloadListener != null) {
                    dataloadListener.c(error.a());
                }
            }
        });
    }
}
