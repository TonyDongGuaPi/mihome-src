package com.mics.core.business;

import android.os.SystemClock;
import android.support.annotation.WorkerThread;
import android.text.TextUtils;
import android.util.Pair;
import com.mics.core.business.ChatRoom;
import com.mics.core.data.business.ChatList;
import com.mics.core.data.response.BaseResponse;
import com.mics.core.data.response.ChatListResponse;
import com.mics.core.data.response.MessageResponse;
import com.mics.core.data.ws.NotifyMessage;
import com.mics.core.ws.GlobalWS;
import com.mics.core.ws.GlobalWSListener;
import com.mics.network.GoCallback;
import com.mics.network.GoFailure;
import com.mics.network.NetworkManager;
import com.mics.util.GsonUtil;
import com.mics.util.Logger;
import com.mics.widget.reminder.MessageReminder;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class ChatManager implements ChatRoom.LifecycleCallback, ChatRoom.MessageReminder, GlobalWSListener, NetworkManager.NetworkListener {

    /* renamed from: a  reason: collision with root package name */
    private static volatile ChatManager f7632a;
    /* access modifiers changed from: private */
    public final Map<String, ChatRoom> b = new HashMap();
    /* access modifiers changed from: private */
    public List<ChatRoom> c = new ArrayList();
    private Map<String, Long> d = new HashMap();

    private ChatManager() {
        GlobalWS.a().a((GlobalWSListener) this);
    }

    public static ChatManager a() {
        if (f7632a == null) {
            synchronized (ChatManager.class) {
                if (f7632a == null) {
                    f7632a = new ChatManager();
                }
            }
        }
        return f7632a;
    }

    public void a(final IChatView iChatView, final String str, final String str2) {
        GlobalWS.a().b();
        NetworkManager.a().a((NetworkManager.NetworkListener) this);
        ChatExecutors.c().execute(new Runnable() {
            public void run() {
                ChatRoom a2 = ChatManager.this.e(str);
                a2.a(iChatView);
                a2.a(str2);
                if (a2.t()) {
                    return;
                }
                if (a2.r()) {
                    String h = a2.h();
                    long a3 = GlobalWS.a().a(h);
                    Logger.a("join() - join room; and result = %d; roomId = %s; hasCreated = %s", Long.valueOf(a3), h, Boolean.valueOf(a2.t()));
                    if (a3 > 0) {
                        a2.c(a3);
                    } else if (a3 == -1) {
                        Logger.c("用户尚未登录", new Object[0]);
                    } else if (a3 == -2) {
                        ChatManager.this.c.add(a2);
                    }
                } else if (NetworkManager.b()) {
                    a2.w();
                } else {
                    a2.v();
                }
            }
        });
    }

    /* access modifiers changed from: private */
    public ChatRoom e(String str) {
        ChatRoom chatRoom;
        synchronized (this.b) {
            chatRoom = this.b.get(str);
            if (chatRoom == null) {
                chatRoom = new ChatRoomImpl(str);
                chatRoom.a((ChatRoom.MessageReminder) a());
                chatRoom.a((ChatRoom.LifecycleCallback) a());
                this.b.put(str, chatRoom);
            }
        }
        return chatRoom;
    }

    public void a(final String str) {
        ChatExecutors.c().execute(new Runnable() {
            public void run() {
                final ChatRoom chatRoom = (ChatRoom) ChatManager.this.b.get(str);
                if (chatRoom != null) {
                    ChatExecutors.d().execute(new Runnable() {
                        public void run() {
                            if (chatRoom.l()) {
                                chatRoom.k().m();
                            }
                        }
                    });
                }
            }
        });
    }

    public void b(String str) {
        this.d.put(str, Long.valueOf(SystemClock.elapsedRealtime()));
    }

    public boolean c(String str) {
        long j;
        boolean z;
        Iterator<String> it = this.d.keySet().iterator();
        while (true) {
            if (!it.hasNext()) {
                j = 0;
                z = false;
                break;
            }
            String next = it.next();
            if (TextUtils.equals(next, str)) {
                j = this.d.get(next).longValue();
                z = true;
                break;
            }
        }
        long elapsedRealtime = SystemClock.elapsedRealtime() - j;
        Logger.c("period = %s", Long.valueOf(elapsedRealtime));
        if (!z || elapsedRealtime > 2000) {
            return false;
        }
        return true;
    }

    public void a(IChatView iChatView) {
        Logger.a("Activity %s, onResume.", iChatView.getClass().getSimpleName());
        this.d.clear();
    }

    public void b(IChatView iChatView) {
        Logger.a("Activity onPause.", new Object[0]);
        this.d.clear();
    }

    public void b() {
        Logger.a("Activity onFinish.", new Object[0]);
        this.d.clear();
    }

    public void a(boolean z, boolean z2, String str, String str2, String str3, String str4) {
        boolean z3;
        Iterator<ChatRoom> it = this.b.values().iterator();
        boolean z4 = false;
        while (true) {
            z3 = true;
            if (!it.hasNext()) {
                break;
            } else if (it.next().n()) {
                z4 = true;
            }
        }
        MessageReminder a2 = MessageReminder.a();
        if (!z4 && !z2) {
            z3 = false;
        }
        a2.a(z3, z, str, str2, str3, str4);
    }

    public void c() {
        ChatExecutors.c().execute(new Runnable() {
            public void run() {
                Iterator it = ChatManager.this.b.values().iterator();
                while (it.hasNext()) {
                    ((ChatRoom) it.next()).D();
                    it.remove();
                }
                ChatManager.this.c.clear();
            }
        });
    }

    public void d() {
        Logger.b("open", new Object[0]);
        ChatExecutors.c().execute(new Runnable() {
            public void run() {
                for (ChatRoom x : ChatManager.this.b.values()) {
                    x.x();
                }
                Iterator it = ChatManager.this.c.iterator();
                while (it.hasNext()) {
                    ChatRoom chatRoom = (ChatRoom) it.next();
                    long a2 = GlobalWS.a().a(chatRoom.h());
                    Logger.a("onOpen() cache join room; and result = %d", Long.valueOf(a2));
                    if (a2 > 0) {
                        chatRoom.c(a2);
                        it.remove();
                    } else if (a2 == -1) {
                        Logger.c("用户尚未登录", new Object[0]);
                    } else if (a2 == -2) {
                        Logger.d("异常情况；理论上WebSocket不会为空", new Object[0]);
                    }
                }
            }
        });
    }

    public void d(String str) {
        Logger.b("message = " + str, new Object[0]);
        NotifyMessage notifyMessage = (NotifyMessage) GsonUtil.a(str, (Type) NotifyMessage.class);
        if (notifyMessage != null) {
            long requestId = notifyMessage.getRequestId();
            if (requestId == 0) {
                for (ChatRoom next : this.b.values()) {
                    String h = next.h();
                    if (h != null && str.contains(h)) {
                        next.e(str);
                        return;
                    }
                }
                return;
            }
            for (ChatRoom next2 : this.b.values()) {
                if (next2.j() == requestId) {
                    next2.e(str);
                    return;
                }
            }
        }
    }

    public void e() {
        synchronized (this.b) {
            for (ChatRoom w : this.b.values()) {
                w.w();
            }
        }
    }

    public void f() {
        synchronized (this.b) {
            for (ChatRoom v : this.b.values()) {
                v.v();
            }
        }
    }

    public void g() {
        synchronized (this.b) {
            for (ChatRoom w : this.b.values()) {
                w.w();
            }
        }
    }

    public void a(Throwable th) {
        synchronized (this.b) {
            for (ChatRoom w : this.b.values()) {
                w.w();
            }
        }
    }

    public void a(int i, boolean z) {
        if (z) {
            GlobalWS.a().b();
            Logger.a("网络状态变化为 type = %d；GlobalWS.getInstance().start();", Integer.valueOf(i));
        }
        Logger.a("网络状态变化为 type = %d；isActive = %s", Integer.valueOf(i), Boolean.valueOf(z));
    }

    public void a(final String str, final GoCallback<BaseResponse> goCallback) {
        ChatExecutors.a().execute(new Runnable() {
            public void run() {
                BaseResponse e = ChatController.e(str);
                if (e != null && e.getCode() == 0 && goCallback != null) {
                    goCallback.a(e.toString(), e);
                } else if (goCallback != null) {
                    goCallback.a(e == null ? null : e.toString(), (GoFailure) null);
                }
            }
        });
    }

    public void a(final GoCallback<ChatList> goCallback) {
        if (goCallback != null) {
            ChatExecutors.a().execute(new Runnable() {
                public void run() {
                    Pair<String, ChatListResponse> a2 = ChatController.a();
                    ChatListResponse chatListResponse = (ChatListResponse) a2.second;
                    final String str = (String) a2.first;
                    if (chatListResponse == null || chatListResponse.getCode() != 0 || chatListResponse.getData() == null) {
                        ChatExecutors.d().execute(new Runnable() {
                            public void run() {
                                goCallback.a(str, (GoFailure) null);
                            }
                        });
                        return;
                    }
                    final ChatList chatList = new ChatList();
                    chatList.setData(new ArrayList());
                    for (ChatListResponse.Data next : chatListResponse.getData()) {
                        if (next != null) {
                            String roomId = next.getRoomId();
                            String merchantId = next.getMerchantId();
                            String logoUrl = next.getLogoUrl();
                            String merchantName = next.getMerchantName();
                            long updateTime = next.getUpdateTime();
                            Pair a3 = ChatManager.this.a(next.getMerchantId(), next.getRoomId(), next.getMaxMsgId());
                            if (a3 != null) {
                                String str2 = (String) a3.second;
                                long longValue = ((Long) a3.first).longValue() - next.getMaxReadMsgId();
                                if (longValue < 0) {
                                    longValue = 0;
                                }
                                ChatList.Data data = new ChatList.Data();
                                data.setMsgRoomId(roomId);
                                data.setMsgMerchantId(merchantId);
                                data.setMsgMerchantLogo(logoUrl);
                                data.setMsgTitle(merchantName);
                                data.setMsgContent(str2);
                                data.setMsgUnReadCount(longValue);
                                data.setMsgUpdateTime(updateTime);
                                chatList.getData().add(data);
                            }
                        }
                    }
                    ChatExecutors.d().execute(new Runnable() {
                        public void run() {
                            goCallback.a(str, chatList);
                        }
                    });
                }
            });
        }
    }

    /* access modifiers changed from: private */
    @WorkerThread
    public Pair<Long, String> a(String str, String str2, long j) {
        ChatRoom e = e(str);
        String e2 = e.e();
        long d2 = e.d();
        if (j > e.c()) {
            MessageResponse a2 = ChatController.a(str2, 0, 20);
            if (!(a2 == null || a2.getCode() != 0 || a2.getData() == null || a2.getData().getMessageList() == null)) {
                if (!a2.getData().isHasMore()) {
                    e.a(j);
                }
                int size = a2.getData().getMessageList().size();
                if (size == 0) {
                    return null;
                }
                for (int i = size - 1; i >= 0; i--) {
                    MessageResponse.Data.Message message = a2.getData().getMessageList().get(i);
                    if (message != null) {
                        int type = message.getType();
                        if (type == 1) {
                            e.a(j);
                            e.b(message.getAbstractContent());
                            e.b(message.getMsgId());
                            return new Pair<>(Long.valueOf(message.getMsgId()), message.getAbstractContent());
                        } else if (type == 2) {
                            e.a(j);
                            e.b("[图片]");
                            e.b(message.getMsgId());
                            return new Pair<>(Long.valueOf(message.getMsgId()), "[图片]");
                        }
                    }
                }
            }
            return null;
        } else if (TextUtils.isEmpty(e2) || d2 <= 0) {
            return null;
        } else {
            return new Pair<>(Long.valueOf(d2), e2);
        }
    }

    public void b(final GoCallback<Object> goCallback) {
        ChatExecutors.a().execute(new Runnable() {
            public void run() {
                BaseResponse b2 = ChatController.b();
                if (b2 != null && b2.getCode() == 0 && b2.getData() != null) {
                    String obj = b2.getData().toString();
                    if (obj.equalsIgnoreCase("v2")) {
                        if (goCallback != null) {
                            goCallback.a("v2", null);
                        }
                    } else if (obj.equalsIgnoreCase("v1")) {
                        if (goCallback != null) {
                            goCallback.a("v1", null);
                        }
                    } else if (goCallback != null) {
                        goCallback.a(obj, (GoFailure) null);
                    }
                } else if (goCallback != null) {
                    goCallback.a(b2 == null ? "null" : b2.toString(), (GoFailure) null);
                }
            }
        });
    }
}
