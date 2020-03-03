package com.mics.core.business;

import android.support.annotation.MainThread;
import android.text.TextUtils;
import com.mics.constant.API;
import com.mics.core.MiCS;
import com.mics.core.business.ChatDataSource;
import com.mics.core.data.request.SendText;
import com.mics.core.data.response.BaseResponse;
import com.mics.core.data.response.MessageResponse;
import com.mics.core.data.response.QueryServiceListResponse;
import com.mics.core.data.response.QueuePositionResponse;
import com.mics.core.data.response.SendResponse;
import com.mics.core.data.response.SessionCreateResponse;
import com.mics.core.data.response.UploadResponse;
import com.mics.core.data.ws.NotifyEvent;
import com.mics.core.data.ws.NotifyJoinRoom;
import com.mics.core.data.ws.NotifyMessage;
import com.mics.core.fsm.Event;
import com.mics.core.fsm.State;
import com.mics.fsm.StateMachine;
import com.mics.network.HttpUtil;
import com.mics.network.ProgressRequestBody;
import com.mics.util.FileUtils;
import com.mics.util.GsonUtil;
import com.mics.util.HtmlText;
import com.mics.util.Logger;
import com.mics.widget.util.MiCSToastManager;
import java.io.File;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;

public class ChatRoomImpl extends ChatRoomFSM {
    private static final String d = "RobotOnService";
    private static final String e = "HumanOnService";
    private static final String f = "LeavingMessage";
    private static final String g = "WaitingForHuman";
    private static final String h = "PretendWaitingForHuman";
    private static final String i = "ClosePositive";
    private static final String j = "CloseNegative";
    /* access modifiers changed from: private */
    public StateMachine b = F();
    /* access modifiers changed from: private */
    public ChatDataSource c = new ChatDataSource();
    /* access modifiers changed from: private */
    public String k;
    /* access modifiers changed from: private */
    public String l;
    /* access modifiers changed from: private */
    public String m;
    /* access modifiers changed from: private */
    public volatile long n;
    /* access modifiers changed from: private */
    public final List<String> o = new Vector();

    public void a(int i2) {
    }

    public void h(String str) {
    }

    ChatRoomImpl(String str) {
        super(str);
    }

    /* access modifiers changed from: protected */
    public void a(String str, Object obj) {
        super.a(str, obj);
        if (obj instanceof SessionCreateResponse) {
            SessionCreateResponse sessionCreateResponse = (SessionCreateResponse) obj;
            if (sessionCreateResponse.getCode() == 0) {
                SessionCreateResponse.Data data = sessionCreateResponse.getData();
                if (data != null) {
                    this.l = data.getSessionId();
                    this.k = data.getSessionState();
                    this.m = data.getCurrentSubSessionId();
                    d(data.getMerchantLogo() == null ? g() : data.getMerchantLogo());
                    c(data.getMerchantName() == null ? f() : data.getMerchantName());
                    Logger.b("changed: %s", obj);
                    if (data.isClosed()) {
                        this.k = i() ? i : j;
                    }
                    this.b.a((Enum) Event.OPEN, new Object[0]);
                    return;
                }
                return;
            }
            this.k = j;
        }
    }

    public Enum u() {
        return this.b.a();
    }

    /* access modifiers changed from: private */
    public void G() {
        Logger.b("state = %s", this.k);
        if (d.equals(this.k)) {
            this.b.a((Enum) Event.JOIN_ROBOT, new Object[0]);
        } else if (g.equals(this.k) || h.equals(this.k)) {
            this.b.a((Enum) Event.QUEUE, new Object[0]);
        } else if (e.equals(this.k)) {
            this.b.a((Enum) Event.JOIN_HUMAN, new Object[0]);
        } else if (i.equals(this.k)) {
            this.b.a((Enum) Event.CLOSE, new Object[0]);
            this.b.a((Enum) Event.RESET, new Object[0]);
        } else if (j.equals(this.k)) {
            this.b.a((Enum) Event.OVER, new Object[0]);
            this.b.a((Enum) Event.RESET, new Object[0]);
        } else if (f.equals(this.k)) {
            this.b.a((Enum) Event.TO_LEAVE_MESSAGE, new Object[0]);
        }
    }

    /* access modifiers changed from: protected */
    public void a(IChatView iChatView) {
        super.a(iChatView);
        ChatExecutors.d().execute(new Runnable() {
            public void run() {
                if (ChatRoomImpl.this.l()) {
                    ChatRoomImpl.this.k().a((ChatRoom) ChatRoomImpl.this);
                    ChatRoomImpl.this.k().a(ChatRoomImpl.this.c.a());
                    if (ChatRoomImpl.this.b.a() == State.ROBOT) {
                        ChatRoomImpl.this.k().d();
                    } else if (ChatRoomImpl.this.b.a() == State.HUMAN) {
                        ChatRoomImpl.this.k().j();
                    } else if (ChatRoomImpl.this.b.a() == State.IN_QUEUE) {
                        ChatRoomImpl.this.k().h();
                    } else if (ChatRoomImpl.this.b.a() == State.IDLE) {
                        ChatRoomImpl.this.k().a();
                    } else if (ChatRoomImpl.this.b.a() == State.NETWORK_ERROR) {
                        ChatRoomImpl.this.k().l();
                    } else if (ChatRoomImpl.this.b.a() == State.START) {
                        ChatRoomImpl.this.k().b();
                    } else if (ChatRoomImpl.this.b.a() == State.MESSAGE) {
                        ChatRoomImpl.this.k().g();
                    }
                }
            }
        });
    }

    public void e(String str) {
        Logger.a("received message %s", str);
        NotifyMessage notifyMessage = (NotifyMessage) GsonUtil.a(str, (Type) NotifyMessage.class);
        if (notifyMessage != null) {
            long requestId = notifyMessage.getRequestId();
            if (requestId == 0) {
                NotifyEvent notifyEvent = (NotifyEvent) GsonUtil.a(str, (Type) NotifyEvent.class);
                if (notifyEvent != null && notifyEvent.getCode() == 0) {
                    a(notifyEvent);
                }
            } else if (requestId == j()) {
                NotifyJoinRoom notifyJoinRoom = (NotifyJoinRoom) GsonUtil.a(str, (Type) NotifyJoinRoom.class);
                if (notifyJoinRoom != null && notifyJoinRoom.getCode() == 0) {
                    NotifyJoinRoom.Body body = notifyJoinRoom.getBody();
                    if (!(body == null || body.getNewMessages() == null)) {
                        if (this.c.a().size() > 0) {
                            b(1001);
                            ChatDataSource.Data data = new ChatDataSource.Data();
                            data.a(8);
                            data.d("以上为历史消息");
                            data.c(1001);
                            data.a(false);
                            this.c.b(data);
                        }
                        List<MessageResponse.Data.Message> newMessages = body.getNewMessages();
                        for (int i2 = 0; i2 < body.getNewMessages().size(); i2++) {
                            MessageResponse.Data.Message message = newMessages.get(i2);
                            if (message != null) {
                                ChatDataSource.Data a2 = a(message);
                                if (this.c.a(a2)) {
                                    if (!a2.l() && a2.f() > 0) {
                                        ChatDataSource.Data data2 = new ChatDataSource.Data();
                                        data2.a(7);
                                        data2.b(a2.q());
                                        data2.a(false);
                                        this.c.b(data2);
                                    }
                                    this.c.b(a2);
                                }
                            }
                        }
                    }
                    I();
                }
                Logger.a("join %s room success!", h());
                G();
                y();
            }
        }
    }

    private void a(NotifyEvent notifyEvent) {
        NotifyEvent.ReadOffset readOffset;
        NotifyEvent.Body body = notifyEvent.getBody();
        if (body != null) {
            if (TextUtils.equals("NEW_CHAT_EVENT", body.getNotifyType())) {
                J();
            } else if (TextUtils.equals("COMMAND_EVENT", body.getNotifyType()) || TextUtils.equals("CONTENT_PUSH", body.getNotifyType())) {
                NotifyEvent.Content content = (NotifyEvent.Content) GsonUtil.a(body.getContent(), (Type) NotifyEvent.Content.class);
                if (content != null) {
                    if (TextUtils.equals("SESSION_STATE_CHANGE", content.getEvent())) {
                        H();
                    } else {
                        a(content);
                    }
                    if (content.getAdditionMsg() != null) {
                        for (String a2 : content.getAdditionMsg()) {
                            a((NotifyEvent.Content) GsonUtil.a(a2, (Type) NotifyEvent.Content.class));
                        }
                    }
                }
            } else if (TextUtils.equals("READ_OFFSET_PUSH", body.getNotifyType()) && (readOffset = (NotifyEvent.ReadOffset) GsonUtil.a(body.getContent(), (Type) NotifyEvent.ReadOffset.class)) != null) {
                long readOffset2 = readOffset.getReadOffset();
                String role = readOffset.getRole();
                String userId = readOffset.getUserId();
                if (TextUtils.equals("kf", role)) {
                    Logger.a("客服（%s）已读的最大消息Id = %s", userId, Long.valueOf(readOffset2));
                } else if (TextUtils.equals("customer", role)) {
                    Logger.a("用户（%s）已读的最大消息Id = %s", userId, Long.valueOf(readOffset2));
                }
            }
        }
    }

    private void a(NotifyEvent.Content content) {
        MessageResponse.Data.RobotQuestion robotQuestion;
        if (content != null) {
            if (TextUtils.equals("SYSTEM_MSG", content.getEvent())) {
                if (TextUtils.equals("text", content.getType())) {
                    ChatDataSource.Data data = new ChatDataSource.Data();
                    data.a(3);
                    data.d(content.getContent());
                    data.a(false);
                    this.c.b(data);
                    c(data);
                } else if (TextUtils.equals("news", content.getType()) && (robotQuestion = (MessageResponse.Data.RobotQuestion) GsonUtil.a(content.getContent(), (Type) MessageResponse.Data.RobotQuestion.class)) != null && TextUtils.equals(robotQuestion.getFormat(), "news")) {
                    ChatDataSource.Data data2 = new ChatDataSource.Data();
                    data2.a(5);
                    data2.d(content.getContent());
                    data2.a(false);
                    this.c.b(data2);
                    c(data2);
                }
            } else if (TextUtils.equals("HUMAN_SCORE_INVITATION", content.getEvent()) && l()) {
                ChatExecutors.d().execute(new Runnable() {
                    public void run() {
                        if (ChatRoomImpl.this.l()) {
                            ChatRoomImpl.this.k().b(3);
                        }
                    }
                });
            }
        }
    }

    private void b(int i2) {
        if (i2 != 0) {
            synchronized (this.c.a()) {
                Iterator<ChatDataSource.Data> it = this.c.a().iterator();
                while (it.hasNext()) {
                    if (it.next().o() == i2) {
                        it.remove();
                        return;
                    }
                }
            }
        }
    }

    /* access modifiers changed from: private */
    public void H() {
        ChatExecutors.a().execute(new Runnable() {
            public void run() {
                SessionCreateResponse a2 = ChatController.a(ChatRoomImpl.this.l);
                Logger.a("querySession: %s", a2);
                ChatRoomImpl.this.a(ChatRoomImpl.this.h(), (Object) a2);
                ChatRoomImpl.this.G();
            }
        });
    }

    public void y() {
        ChatExecutors.a().execute(new Runnable() {
            public void run() {
                QueryServiceListResponse.Data.Groups groups;
                QueryServiceListResponse c = ChatController.c(ChatRoomImpl.this.l);
                boolean z = true;
                if (c == null || c.getCode() != 0) {
                    Object[] objArr = new Object[1];
                    objArr[0] = c != null ? c.toString() : "null";
                    Logger.d("Error:%s", objArr);
                    return;
                }
                QueryServiceListResponse.Data data = c.getData();
                if (data != null) {
                    boolean z2 = data.getGroups() != null;
                    if (data.getGroups().size() <= 0) {
                        z = false;
                    }
                    if ((z && z2) && (groups = data.getGroups().get(0)) != null && groups.getContents() != null) {
                        ArrayList arrayList = new ArrayList();
                        for (QueryServiceListResponse.Data.Groups.Contents next : groups.getContents()) {
                            if (next != null) {
                                ChatDataSource.Service service = new ChatDataSource.Service();
                                service.c(next.getCode());
                                service.b(next.getImg());
                                service.a(next.getName());
                                arrayList.add(service);
                            }
                        }
                        ChatRoomImpl.this.a((List<ChatDataSource.Service>) arrayList);
                    }
                }
            }
        });
    }

    public void z() {
        super.z();
        ChatExecutors.a().execute(new Runnable() {
            public void run() {
                QueuePositionResponse d = ChatController.d(ChatRoomImpl.this.l);
                if (d != null && d.getCode() == 0 && d.getData() != null) {
                    final int position = d.getData().getPosition();
                    ChatExecutors.d().execute(new Runnable() {
                        public void run() {
                            if (ChatRoomImpl.this.l()) {
                                ChatRoomImpl.this.k().a(position);
                            }
                        }
                    });
                } else if (d != null && d.getCode() != 0) {
                    ChatRoomImpl.this.H();
                }
            }
        });
    }

    private boolean i(String str) {
        synchronized (this.o) {
            for (String equals : this.o) {
                if (TextUtils.equals(equals, str)) {
                    return true;
                }
            }
            return false;
        }
    }

    public void a(final String str, final String str2) {
        super.a(str, str2);
        if (!i(str)) {
            this.o.add(str);
            final ChatDataSource.Data data = new ChatDataSource.Data();
            data.a(3);
            data.b(System.currentTimeMillis());
            data.d("正在进入留言模式，请稍等");
            data.b(1);
            B().add(data);
            if (l()) {
                k().a(data);
            }
            ChatExecutors.a().execute(new Runnable() {
                public void run() {
                    BaseResponse c2 = ChatController.c(ChatRoomImpl.this.l, str2);
                    if (c2 == null || c2.getCode() != 0) {
                        data.d("进入留言模式失败，请重试");
                        ChatRoomImpl.this.d(data);
                        ChatRoomImpl.this.o.remove(str);
                        return;
                    }
                    ChatRoomImpl.this.b.a((Enum) Event.TO_LEAVE_MESSAGE, new Object[0]);
                    data.d("您已进入留言模式，直接发送消息即可留言");
                    ChatRoomImpl.this.d(data);
                }
            });
        }
    }

    /* access modifiers changed from: private */
    public void a(final List<ChatDataSource.Service> list) {
        ChatExecutors.d().execute(new Runnable() {
            public void run() {
                if (ChatRoomImpl.this.l()) {
                    ChatRoomImpl.this.k().c(list);
                }
            }
        });
    }

    public void v() {
        this.b.a((Enum) Event.NETWORK_LOST, new Object[0]);
    }

    public void w() {
        this.b.a((Enum) Event.RETRY_FAILURE, new Object[0]);
        this.b.a((Enum) Event.OVER, new Object[0]);
        this.b.a((Enum) Event.RESET, new Object[0]);
    }

    public void x() {
        this.b.a((Enum) Event.NETWORK_RECONNECTION, State.NETWORK_ERROR);
    }

    public void q() {
        super.q();
        this.c.e();
    }

    @MainThread
    public void a(ChatDataSource.Data data) {
        data.b(true);
        data.a(this.l);
        data.a(true);
        this.c.b(data);
        if (l()) {
            k().a(data);
        }
    }

    @MainThread
    public List<ChatDataSource.Data> B() {
        return this.c.a();
    }

    public List<ChatDataSource.Data> C() {
        return this.c.d();
    }

    @MainThread
    public void f(final String str) {
        if (l()) {
            k().e();
        }
        ChatExecutors.a().execute(new Runnable() {
            public void run() {
                BaseResponse b2 = ChatController.b(str, ChatRoomImpl.this.l);
                if (b2 != null && b2.getCode() == 0) {
                    ChatRoomImpl.this.b.a((Enum) Event.QUEUE, new Object[0]);
                } else if (b2 == null || b2.getCode() != 405) {
                    ChatExecutors.d().execute(new Runnable() {
                        public void run() {
                            if (ChatRoomImpl.this.l()) {
                                ChatRoomImpl.this.k().f();
                            }
                        }
                    });
                } else {
                    ChatDataSource.Data a2 = ChatRoomImpl.this.j(str);
                    ChatRoomImpl.this.B().add(a2);
                    ChatRoomImpl.this.c(a2);
                    ChatExecutors.d().execute(new Runnable() {
                        public void run() {
                            if (ChatRoomImpl.this.l()) {
                                ChatRoomImpl.this.k().f();
                            }
                        }
                    });
                }
            }
        });
    }

    public void a(String str, final String str2, final boolean z) {
        synchronized (this.c.a()) {
            Iterator<ChatDataSource.Data> it = this.c.a().iterator();
            while (true) {
                if (!it.hasNext()) {
                    break;
                }
                ChatDataSource.Data next = it.next();
                if (TextUtils.equals(next.a(), str)) {
                    next.b(6);
                    d(next);
                    break;
                }
            }
        }
        ChatExecutors.a().execute(new Runnable() {
            public void run() {
                ChatController.a(str2, z, ChatRoomImpl.this.l);
            }
        });
    }

    public void a(int i2, String str, boolean z, String str2) {
        final int i3 = i2;
        final String str3 = str;
        final boolean z2 = z;
        final String str4 = str2;
        ChatExecutors.a().execute(new Runnable() {
            public void run() {
                BaseResponse a2 = ChatController.a(i3, str3, z2, ChatRoomImpl.this.m, ChatRoomImpl.this.l, str4);
                if (a2 != null && a2.getCode() == 0) {
                    ChatExecutors.d().execute(new Runnable() {
                        public void run() {
                            MiCSToastManager.a().a("评价成功", 2);
                        }
                    });
                }
            }
        });
    }

    @MainThread
    public void g(String str) {
        final String a2 = HtmlText.a(str);
        final ChatDataSource.Data data = new ChatDataSource.Data();
        data.a(true);
        data.a(this.l);
        data.d(a2);
        data.a(1);
        data.b(1);
        this.c.b(data);
        if (l()) {
            k().a(data);
        }
        ChatExecutors.a().execute(new Runnable() {
            public void run() {
                SendText d = ChatController.d(ChatRoomImpl.this.h(), a2);
                data.c(d.getUmsgId());
                ChatRoomImpl.this.c.a(data.d());
                SendResponse b2 = ChatController.b(d);
                if (b2 == null || b2.getCode() != 0) {
                    Logger.c("Error: %s", b2);
                    data.b(4);
                    ChatRoomImpl.this.d(data);
                    return;
                }
                data.a(b2.getData());
                data.b(3);
                ChatRoomImpl.this.d(data);
                ChatRoomImpl.this.b(data);
            }
        });
    }

    @MainThread
    public void a(File[] fileArr) {
        if (fileArr != null && fileArr.length != 0) {
            Logger.a("image size = %s", Integer.valueOf(fileArr.length));
            for (final File file : fileArr) {
                final ChatDataSource.Data data = new ChatDataSource.Data();
                data.a(true);
                data.a(this.l);
                data.e(file.getAbsolutePath());
                data.a(2);
                data.b(1);
                this.c.b(data);
                if (l()) {
                    k().a(data);
                }
                ChatExecutors.b().execute(new Runnable() {
                    public void run() {
                        data.b(2);
                        ChatRoomImpl.this.d(data);
                        File a2 = FileUtils.a(FileUtils.a(file.getAbsolutePath(), data.a()));
                        data.e(a2.toURI().toString());
                        final String a3 = HttpUtil.a(API.l(), a2, (ProgressRequestBody.ProgressListener) new ProgressRequestBody.ProgressListener() {
                            public void a(long j, long j2, File file) {
                                data.a((((float) j2) * 1.0f) / ((float) j));
                                ChatRoomImpl.this.d(data);
                            }
                        });
                        ChatExecutors.a().execute(new Runnable() {
                            public void run() {
                                UploadResponse uploadResponse = (UploadResponse) GsonUtil.a(a3, (Type) UploadResponse.class);
                                if (uploadResponse == null || uploadResponse.getData() == null) {
                                    data.b(4);
                                } else {
                                    String content = uploadResponse.getData().getContent();
                                    if (!TextUtils.isEmpty(content)) {
                                        SendText d = ChatController.d(ChatRoomImpl.this.h(), content);
                                        data.c(d.getUmsgId());
                                        ChatRoomImpl.this.c.a(data.d());
                                        SendResponse a2 = ChatController.a(d);
                                        if (a2 == null || a2.getCode() != 0) {
                                            data.b(4);
                                        } else {
                                            data.a(a2.getData());
                                            data.b(3);
                                            ChatRoomImpl.this.b(data);
                                        }
                                    } else {
                                        data.b(4);
                                    }
                                }
                                ChatRoomImpl.this.d(data);
                            }
                        });
                    }
                });
            }
        }
    }

    /* access modifiers changed from: private */
    public void b(ChatDataSource.Data data) {
        if (l()) {
            long e2 = data.e();
            if (e2 > c()) {
                a(e2);
            }
            if (e2 > d()) {
                int f2 = data.f();
                String h2 = f2 == 1 ? data.h() : f2 == 2 ? "[图片]" : "";
                b(data.e());
                b(h2);
            }
            if (e2 > this.n) {
                A();
            }
        }
    }

    /* access modifiers changed from: private */
    public void c(final ChatDataSource.Data data) {
        ChatExecutors.d().execute(new Runnable() {
            public void run() {
                if (!data.l()) {
                    String h = (data.f() == 1 || data.f() == 3) ? data.h() : data.f() == 2 ? "[图片]" : "";
                    if (!TextUtils.isEmpty(h) && ChatRoomImpl.this.f7643a != null) {
                        ChatRoomImpl.this.f7643a.a(ChatRoomImpl.this.n(), ChatRoomImpl.this.m(), ChatRoomImpl.this.f(), ChatRoomImpl.this.g(), h, ChatRoomImpl.this.a());
                    }
                }
                if (ChatRoomImpl.this.l()) {
                    ChatRoomImpl.this.k().a(data);
                }
            }
        });
    }

    /* access modifiers changed from: private */
    public void d(final ChatDataSource.Data data) {
        if (l()) {
            ChatExecutors.d().execute(new Runnable() {
                public void run() {
                    if (ChatRoomImpl.this.l()) {
                        ChatRoomImpl.this.k().a(data.a());
                    }
                }
            });
        }
    }

    private void I() {
        if (l()) {
            ChatExecutors.d().execute(new Runnable() {
                public void run() {
                    if (ChatRoomImpl.this.l()) {
                        ChatRoomImpl.this.k().a(ChatRoomImpl.this.c.a());
                    }
                }
            });
        }
    }

    /* access modifiers changed from: private */
    public void b(final List<ChatDataSource.Data> list) {
        if (l()) {
            ChatExecutors.d().execute(new Runnable() {
                public void run() {
                    if (ChatRoomImpl.this.l()) {
                        ChatRoomImpl.this.k().b((List<ChatDataSource.Data>) list);
                    }
                }
            });
        }
    }

    public void D() {
        a(true);
        ChatExecutors.a().execute(new Runnable() {
            public void run() {
                ChatController.b(ChatRoomImpl.this.l);
                String unused = ChatRoomImpl.this.k = ChatRoomImpl.i;
                ChatRoomImpl.this.G();
            }
        });
    }

    private void J() {
        ChatExecutors.a().execute(new Runnable() {
            public void run() {
                MessageResponse a2;
                MessageResponse.Data data;
                if (ChatRoomImpl.this.c.a().size() >= 0) {
                    long h = ChatRoomImpl.this.K();
                    if (h >= 0 && (a2 = ChatController.a(ChatRoomImpl.this.h(), h, 10)) != null && a2.getCode() == 0 && (data = a2.getData()) != null && data.getMessageList() != null) {
                        for (int i = 0; i < data.getMessageList().size(); i++) {
                            MessageResponse.Data.Message message = data.getMessageList().get(i);
                            if (message != null) {
                                ChatDataSource.Data a3 = ChatRoomImpl.this.a(message);
                                if (ChatRoomImpl.this.c.a(a3)) {
                                    if (!a3.l() && a3.f() > 0) {
                                        ChatDataSource.Data data2 = new ChatDataSource.Data();
                                        data2.a(7);
                                        data2.b(a3.q());
                                        data2.a(false);
                                        ChatRoomImpl.this.c.b(data2);
                                        ChatRoomImpl.this.c(data2);
                                    }
                                    ChatRoomImpl.this.c.b(a3);
                                    ChatRoomImpl.this.c(a3);
                                }
                            }
                        }
                    }
                }
            }
        });
    }

    public void E() {
        if (this.c.b()) {
            if (!this.c.c()) {
                b((List<ChatDataSource.Data>) null);
                return;
            }
            this.c.a(false);
            ChatExecutors.a().execute(new Runnable() {
                public void run() {
                    MessageResponse.Data.Message.RobotAnswer robotAnswer;
                    if (ChatRoomImpl.this.c.a().size() >= 0) {
                        long i = ChatRoomImpl.this.L();
                        if (i >= 0) {
                            MessageResponse b = ChatController.b(ChatRoomImpl.this.h(), i, 50);
                            if (b == null || b.getCode() != 0) {
                                Object[] objArr = new Object[1];
                                objArr[0] = b != null ? b.toString() : "null";
                                Logger.c("Error: %s", objArr);
                            } else {
                                MessageResponse.Data data = b.getData();
                                if (!(data == null || data.getMessageList() == null)) {
                                    ArrayList arrayList = new ArrayList();
                                    for (int i2 = 0; i2 < data.getMessageList().size(); i2++) {
                                        MessageResponse.Data.Message message = data.getMessageList().get(i2);
                                        if (message != null) {
                                            if (!TextUtils.isEmpty(message.getExtraInfo()) && (robotAnswer = (MessageResponse.Data.Message.RobotAnswer) GsonUtil.a(message.getExtraInfo(), (Type) MessageResponse.Data.Message.RobotAnswer.class)) != null) {
                                                robotAnswer.setNeedEval(0);
                                                message.setExtraInfo(GsonUtil.a(robotAnswer));
                                            }
                                            ChatDataSource.Data a2 = ChatRoomImpl.this.a(message);
                                            if (!a2.l() && a2.f() > 0) {
                                                ChatDataSource.Data data2 = new ChatDataSource.Data();
                                                data2.a(7);
                                                data2.b(a2.q());
                                                data2.a(false);
                                                arrayList.add(data2);
                                            }
                                            arrayList.add(a2);
                                        }
                                    }
                                    if (!data.isHasMore()) {
                                        ChatDataSource.Data data3 = new ChatDataSource.Data();
                                        data3.a(8);
                                        data3.d("无更多历史消息");
                                        data3.a(false);
                                        arrayList.add(0, data3);
                                    }
                                    ChatRoomImpl.this.b((List<ChatDataSource.Data>) arrayList);
                                    ChatRoomImpl.this.c.a(0, (List<ChatDataSource.Data>) arrayList);
                                    ChatRoomImpl.this.c.b(data.isHasMore());
                                }
                            }
                        }
                    }
                    ChatRoomImpl.this.c.a(true);
                }
            });
        }
    }

    @MainThread
    public void A() {
        super.A();
        ChatExecutors.a().execute(new Runnable() {
            public void run() {
                BaseResponse a2;
                long h = ChatRoomImpl.this.K();
                if (h > ChatRoomImpl.this.n && (a2 = ChatController.a(ChatRoomImpl.this.h(), h)) != null && a2.getCode() == 0) {
                    long unused = ChatRoomImpl.this.n = h;
                    ChatExecutors.d().execute(new Runnable() {
                        public void run() {
                            if (MiCS.a().g() != null) {
                                MiCS.a().g().onMessageRead(ChatRoomImpl.this.a(), ChatRoomImpl.this.h());
                            }
                        }
                    });
                }
            }
        });
    }

    /* access modifiers changed from: private */
    public synchronized long K() {
        List<ChatDataSource.Data> a2 = this.c.a();
        synchronized (this.c.a()) {
            if (a2.size() == 0) {
                return 0;
            }
            for (int size = a2.size() - 1; size >= 0; size--) {
                long e2 = a2.get(size).e();
                if (e2 > 0) {
                    return e2;
                }
            }
            return -1;
        }
    }

    /* access modifiers changed from: private */
    public synchronized long L() {
        List<ChatDataSource.Data> a2 = this.c.a();
        synchronized (this.c.a()) {
            if (a2.size() == 0) {
                return 0;
            }
            for (int i2 = 0; i2 < a2.size(); i2++) {
                long e2 = a2.get(i2).e();
                if (e2 > 0) {
                    return e2;
                }
            }
            return -1;
        }
    }

    /* access modifiers changed from: private */
    public ChatDataSource.Data a(MessageResponse.Data.Message message) {
        String str;
        int type = message.getType();
        if (l()) {
            if (type > 0 && message.getMsgId() > d()) {
                if (type == 1 || type == 2) {
                    str = message.getAbstractContent();
                } else {
                    str = "";
                }
                b(message.getMsgId());
                b(str);
            }
            if (message.getMsgId() > c()) {
                a(message.getMsgId());
            }
        }
        ChatDataSource.Data data = new ChatDataSource.Data();
        data.h(message.getExtraInfo());
        data.a(message.getMsgId());
        data.c(message.getUmsgId());
        data.b(message.getCreateTime());
        data.g(message.getFromUserName());
        data.b(3);
        data.a(TextUtils.equals(message.getFromUserId(), MiCS.a().n()));
        if (!data.l()) {
            String fromUserId = message.getFromUserId();
            if (!(!TextUtils.isEmpty(fromUserId) && fromUserId.endsWith("@robot"))) {
                data.f(g());
            }
        }
        data.a(type);
        if (type == 1) {
            MessageResponse.Data.RobotQuestion robotQuestion = (MessageResponse.Data.RobotQuestion) GsonUtil.a(message.getContent(), (Type) MessageResponse.Data.RobotQuestion.class);
            if (robotQuestion == null) {
                data.d(message.getContent());
            } else if (TextUtils.equals(robotQuestion.getFormat(), "text")) {
                data.a(4);
                data.d(robotQuestion.getContent());
            } else if (TextUtils.equals(robotQuestion.getFormat(), "news")) {
                data.a(5);
                data.d(message.getContent());
            } else {
                data.a(1);
                data.d("[未知消息格式]");
            }
        } else if (type == 2) {
            data.e(message.getContent());
        }
        return data;
    }

    /* access modifiers changed from: private */
    public ChatDataSource.Data j(String str) {
        MessageResponse.Data.RobotQuestion robotQuestion = new MessageResponse.Data.RobotQuestion();
        robotQuestion.setFormat("text");
        robotQuestion.setTemplate("很抱歉当前暂无人工客服在线，您可以与米兔继续聊天。\n或者留言说明问题，人工客服在线后将第一时间为您处理。${k0}");
        ArrayList arrayList = new ArrayList();
        MessageResponse.Data.RobotQuestion.Params params = new MessageResponse.Data.RobotQuestion.Params();
        params.setKey("k0");
        params.setType("toLeaveMessage");
        params.setShowText("☞去留言");
        arrayList.add(params);
        robotQuestion.setParams(arrayList);
        ChatDataSource.Data data = new ChatDataSource.Data();
        data.d(robotQuestion.getContent());
        data.a(false);
        data.a(9);
        data.h(str);
        data.b(3);
        data.a(this.l);
        data.b(System.currentTimeMillis());
        return data;
    }
}
