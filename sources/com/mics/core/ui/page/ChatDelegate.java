package com.mics.core.ui.page;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.text.TextUtils;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.mics.R;
import com.mics.core.MiCS;
import com.mics.core.business.ChatDataSource;
import com.mics.core.business.ChatManager;
import com.mics.core.business.ChatRoom;
import com.mics.core.business.IChatView;
import com.mics.core.data.business.ChatParams;
import com.mics.core.fsm.State;
import com.mics.core.ui.data.BaseData;
import com.mics.core.ui.data.ChatDataParser;
import com.mics.core.ui.kit.Hint;
import com.mics.core.ui.page.ChatHumanScorePopHelper;
import com.mics.util.GsonUtil;
import com.mics.util.KeyboardUtils;
import com.mics.util.Logger;
import com.mics.widget.CategoryPop;
import com.mics.widget.CategoryPopAdapter;
import com.mics.widget.NewMessageHintManager;
import com.mics.widget.RecyclerViewScrollCompat;
import com.mics.widget.SpringView.widget.SpringView;
import com.mics.widget.TitleBar;
import com.mics.widget.reminder.MessageReminder;
import com.mics.widget.util.ChatUtils;
import com.mics.widget.util.MiCSToastManager;
import com.mics.widget.util.StatusBarUtils;
import com.xiaomi.youpin.youpin_constants.UrlConstants;
import java.io.File;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class ChatDelegate implements IChatView {
    private Timer A;
    private int B = Integer.MAX_VALUE;
    private int C;
    private boolean D = false;

    /* renamed from: a  reason: collision with root package name */
    private Activity f7715a;
    private TitleBar b;
    private OnTitleRightClickListenerForActionOver c;
    /* access modifiers changed from: private */
    public List<Object> d = new ArrayList();
    private CategoryPopAdapter e;
    /* access modifiers changed from: private */
    public CategoryPop f;
    private CategoryPop g;
    private ChatHumanScorePopHelper h;
    private RelativeLayout i;
    private SpringView j;
    /* access modifiers changed from: private */
    public RecyclerViewScrollCompat k;
    /* access modifiers changed from: private */
    public LinearLayoutManager l;
    private ChatAdapter m;
    private List<Object> n;
    private TextView o;
    /* access modifiers changed from: private */
    public NewMessageHintManager p;
    private RelativeLayout q;
    private TextView r;
    /* access modifiers changed from: private */
    public int s = 0;
    private String t;
    private String u;
    private String v;
    private ChatParams.Goods w;
    private boolean x;
    private String y;
    /* access modifiers changed from: private */
    public ChatRoom z;

    public void a() {
    }

    static ChatDelegate a(Activity activity) {
        return new ChatDelegate(activity);
    }

    private ChatDelegate(Activity activity) {
        this.f7715a = activity;
        this.C = (int) TypedValue.applyDimension(1, 56.0f, activity.getResources().getDisplayMetrics());
        v();
        w();
        x();
    }

    private void v() {
        this.b = (TitleBar) d(R.id.tb_chat);
        this.j = (SpringView) d(R.id.spring_chat);
        this.k = (RecyclerViewScrollCompat) d(R.id.rv_chat);
        this.i = (RelativeLayout) d(R.id.rl_cs_network_hint);
        this.q = (RelativeLayout) d(R.id.rl_chat_new_msg_hint);
        this.r = (TextView) d(R.id.tv_chat_new_msg_hint);
        this.o = (TextView) d(R.id.tv_tool_cover);
        this.o.setVisibility(8);
    }

    private void w() {
        this.b.setOnTitleLeftClickListener(new TitleBar.OnTitleLeftClickListener() {
            public void a() {
                KeyboardUtils.a();
            }
        });
        this.c = new OnTitleRightClickListenerForActionOver();
        this.l = new LinearLayoutManager(this.f7715a);
        this.k.setLayoutManager(this.l);
        this.n = new ArrayList();
        this.m = new ChatAdapter(this.f7715a, this.n);
        this.m.a(this);
        this.k.setAdapter(this.m);
        this.k.addTrigger(new RecyclerViewScrollCompat.Trigger() {
            public boolean a(float f) {
                return f < 0.5f;
            }

            public void a(boolean z) {
                if (z) {
                    int unused = ChatDelegate.this.s = 0;
                    ChatDelegate.this.p.a();
                }
            }
        });
        this.k.addTrigger(new RecyclerViewScrollCompat.Trigger() {
            public boolean a(float f) {
                return f < 2.0f;
            }

            public void a(boolean z) {
                if (ChatDelegate.this.s == 0) {
                    if (z) {
                        int unused = ChatDelegate.this.s = 0;
                        ChatDelegate.this.p.a();
                        return;
                    }
                    ChatDelegate.this.p.a("回到底部");
                }
            }
        });
        this.e = new CategoryPopAdapter(this.d);
        this.e.a((CategoryPop.Adapter.OnItemClickListener) new CategoryPop.Adapter.OnItemClickListener() {
            public void a(int i, View view) {
                if (i >= 0 && i < ChatDelegate.this.d.size()) {
                    Object obj = ChatDelegate.this.d.get(i);
                    if ((obj instanceof Bundle) && ChatDelegate.this.z != null) {
                        ChatDelegate.this.z.f(((Bundle) obj).getString("code"));
                        ChatDelegate.this.f.c();
                    }
                }
            }
        });
        this.f = new CategoryPop.Builder(this.f7715a).c(1).a((CategoryPop.Adapter) this.e).a(true).a("请选择咨询类型").b(StatusBarUtils.a((Context) this.f7715a)).a(LayoutInflater.from(this.f7715a)).k();
        this.h = new ChatHumanScorePopHelper();
        this.g = new CategoryPop.Builder(this.f7715a).a(false).a((PopupWindow.OnDismissListener) this.h).c(-1).a(R.layout.mics_pop_comment).a(this.f7715a.getString(R.string.mics_rate_title)).a(LayoutInflater.from(this.f7715a)).k();
        this.h.a(this.g);
        this.h.a((ChatHumanScorePopHelper.OnSubmitListener) new ChatHumanScorePopHelper.OnSubmitListener() {
            public void a(int i, ChatHumanScorePopHelper.Score score) {
                ChatDelegate.this.a(score.d(), score.a(), score.c(), score.b());
            }

            public void a(int i) {
                if (i == 2) {
                    if (ChatDelegate.this.z != null) {
                        ChatDelegate.this.z.D();
                    }
                    ChatDelegate.this.m();
                }
            }
        });
        this.q.setVisibility(8);
        this.p = NewMessageHintManager.a(this.q, this.r);
        this.q.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                int unused = ChatDelegate.this.s = 0;
                ChatDelegate.this.p.a();
                int itemCount = ChatDelegate.this.l.getItemCount() - 1;
                if (itemCount > 0) {
                    ChatDelegate.this.k.smoothScrollToPosition(itemCount);
                }
            }
        });
    }

    private void x() {
        this.y = this.f7715a.getIntent().getStringExtra(UrlConstants.customerService);
        ChatParams chatParams = (ChatParams) GsonUtil.a(this.y, (Type) ChatParams.class);
        if (chatParams == null) {
            if (MiCS.e()) {
                MiCSToastManager.a().a((CharSequence) "intent缺少必要参数customerService ！！！");
            }
            this.f7715a.finish();
            return;
        }
        this.t = chatParams.getMerchantId();
        this.w = chatParams.getGoods();
        this.u = chatParams.getGid();
        ChatParams.Order order = chatParams.getOrder();
        this.v = order != null ? order.getOrderId() : null;
        this.x = false;
    }

    private <T extends View> T d(int i2) {
        return this.f7715a.findViewById(i2);
    }

    public void r() {
        ChatManager.a().a((IChatView) this, this.t, this.u);
    }

    public void a(ChatRoom chatRoom) {
        this.z = chatRoom;
        Logger.a("onCreate -> onResume; ChatRoom = %s", chatRoom.f());
        n();
    }

    public void n() {
        this.D = false;
        MessageReminder.a((Context) this.f7715a, this.t);
        if (this.z != null) {
            this.z.o();
        }
    }

    public void o() {
        if (this.z != null) {
            this.z.p();
        }
    }

    public void p() {
        z();
        if (this.z != null) {
            this.z.q();
        }
    }

    public void b() {
        this.b.setTitleText("在线客服");
        this.i.setVisibility(8);
        this.b.setRightText("连接中");
        this.o.setText("等待客服连接中");
        this.o.setVisibility(0);
        this.b.setOnTitleRightClickListener((TitleBar.OnTitleRightClickListener) null);
    }

    public void c() {
        r();
    }

    public void d() {
        this.b.setTitleText("机器人会话中");
        this.b.setRightText("转人工");
        this.o.setVisibility(8);
        this.b.setOnTitleRightClickListener(new TitleBar.OnTitleRightClickListener() {
            public void a() {
                ChatDelegate.this.s();
            }
        });
    }

    public void s() {
        if (u() == State.ROBOT && this.z != null) {
            if (this.d.size() > 0) {
                this.f.b();
            } else {
                this.z.y();
            }
        }
    }

    public void a(String str, String str2) {
        if (this.z != null) {
            this.z.a(str, str2);
        }
    }

    public void c(List<ChatDataSource.Service> list) {
        this.d.clear();
        for (ChatDataSource.Service next : list) {
            Bundle bundle = new Bundle();
            bundle.putString("icon", next.b());
            bundle.putString("name", next.a());
            bundle.putString("code", next.c());
            this.d.add(bundle);
        }
        this.e.b();
    }

    public void e() {
        this.b.setRightText("连接中");
        this.b.setOnTitleRightClickListener((TitleBar.OnTitleRightClickListener) null);
    }

    public void f() {
        d();
    }

    public void g() {
        this.b.setTitleText("留言中");
        this.b.setRightText("结束");
        this.o.setVisibility(8);
        this.b.setOnTitleRightClickListener(this.c);
    }

    public void h() {
        this.b.setTitleText("排队中");
        this.b.setRightText("结束");
        this.o.setVisibility(8);
        this.b.setOnTitleRightClickListener(this.c);
        y();
    }

    public void i() {
        z();
    }

    public void a(int i2) {
        if (i2 <= 0) {
            i2 = 1;
        }
        if (i2 == 99999) {
            this.b.setTitleText("排队中");
            this.B = i2;
        } else if (i2 < this.B) {
            this.b.setTitleText(String.format("排队中（还有%s人）", new Object[]{Integer.valueOf(i2)}));
            this.B = i2;
        }
    }

    private void y() {
        if (this.z != null) {
            if (this.A == null) {
                this.A = new Timer();
            }
            this.A.scheduleAtFixedRate(new TimerTask() {
                public void run() {
                    ChatDelegate.this.z.z();
                }
            }, 0, 5000);
        }
    }

    private void z() {
        if (this.A != null) {
            this.A.cancel();
            this.A = null;
        }
    }

    public void j() {
        if (this.z != null) {
            this.b.setTitleText("人工客服会话中");
            this.b.setRightText("结束");
            this.o.setVisibility(8);
            this.b.setOnTitleRightClickListener(new TitleBar.OnTitleRightClickListener() {
                public void a() {
                    ChatDelegate.this.b(2);
                }
            });
            if (!(this.w == null && this.v == null) && !this.x) {
                this.x = true;
                ChatDataSource.Data data = new ChatDataSource.Data();
                data.a(6);
                data.h(this.y);
                this.z.a(data);
            }
        }
    }

    public void b(int i2) {
        this.g.b();
        this.h.a(i2);
        this.k.touchDown();
    }

    public void k() {
        this.b.setTitleText("在线客服");
        this.b.setRightText("重新咨询");
        this.o.setText("当前会话已结束");
        this.o.setVisibility(0);
        this.b.setOnTitleRightClickListener(new TitleBar.OnTitleRightClickListener() {
            public void a() {
                ChatDelegate.this.r();
            }
        });
        this.k.touchDown();
    }

    private class OnTitleRightClickListenerForActionOver implements TitleBar.OnTitleRightClickListener {
        private OnTitleRightClickListenerForActionOver() {
        }

        public void a() {
            if (ChatDelegate.this.z != null) {
                ChatDelegate.this.z.D();
            }
            ChatDelegate.this.m();
        }
    }

    public void l() {
        this.i.setVisibility(0);
        this.b.setRightText("连接中");
        this.b.setOnTitleRightClickListener((TitleBar.OnTitleRightClickListener) null);
    }

    public void m() {
        z();
        this.f7715a.finish();
    }

    public void a(String str) {
        Object parse;
        ChatDataSource.Data d2 = d(str);
        int c2 = c(str);
        if (c2 >= 0 && c2 < this.n.size() && (parse = ChatDataParser.parse(d2)) != null) {
            this.n.set(c2, parse);
            this.m.notifyItemChanged(c2);
        }
    }

    public void b(String str) {
        int c2 = c(str);
        if (c2 >= 0 && c2 < this.n.size()) {
            this.n.remove(c2);
            this.m.notifyItemRemoved(c2);
        }
    }

    private int c(String str) {
        for (int i2 = 0; i2 < this.n.size(); i2++) {
            Object obj = this.n.get(i2);
            if (obj instanceof BaseData) {
                if (TextUtils.equals(((BaseData) obj).getId(), str)) {
                    return i2;
                }
            } else if ((obj instanceof Hint.Data) && TextUtils.equals(((Hint.Data) obj).b(), str)) {
                return i2;
            }
        }
        return -1;
    }

    private ChatDataSource.Data d(String str) {
        if (this.z == null) {
            return null;
        }
        synchronized (this.z.B()) {
            for (ChatDataSource.Data next : this.z.B()) {
                if (TextUtils.equals(next.a(), str)) {
                    return next;
                }
            }
            return null;
        }
    }

    public void a(ChatDataSource.Data data) {
        if (data != null && this.z != null) {
            boolean z2 = false;
            Logger.a("data id = %s", data.a());
            Object parse = ChatDataParser.parse(data);
            if (parse != null) {
                if (ChatUtils.b(this.k) || data.l()) {
                    z2 = true;
                }
                this.n.add(parse);
                this.m.notifyItemInserted(this.n.size() - 1);
                this.z.A();
                a(z2);
                if (!z2 && (parse instanceof BaseData)) {
                    this.s++;
                    this.p.a("新消息");
                }
            }
        }
    }

    public void a(List<ChatDataSource.Data> list) {
        Logger.b("received list!!!", new Object[0]);
        this.j.onFinishFreshAndLoad();
        if (this.z != null) {
            this.z.A();
            if (list != null && list.size() > 0) {
                boolean isEmpty = this.n.isEmpty();
                this.n.clear();
                synchronized (this.z.B()) {
                    for (ChatDataSource.Data parse : list) {
                        Object parse2 = ChatDataParser.parse(parse);
                        if (parse2 != null) {
                            this.n.add(parse2);
                        }
                    }
                }
                this.m.notifyDataSetChanged();
                a(isEmpty);
            }
        }
    }

    public void b(List<ChatDataSource.Data> list) {
        this.j.onFinishFreshAndLoad();
        if (list != null) {
            int i2 = 0;
            for (int size = list.size() - 1; size >= 0; size--) {
                Object parse = ChatDataParser.parse(list.get(size));
                if (parse != null) {
                    this.n.add(0, parse);
                    i2++;
                }
            }
            this.m.notifyItemRangeInserted(0, i2);
            this.k.smoothScrollBy(0, -this.C);
        }
    }

    public void t() {
        if (this.z != null) {
            this.z.E();
        }
    }

    public void a(String str, String str2, boolean z2) {
        if (this.z != null) {
            this.z.a(str, str2, z2);
        }
    }

    public void a(int i2, String str, boolean z2, String str2) {
        if (this.z != null) {
            this.z.a(i2, str, z2, str2);
        }
    }

    /* access modifiers changed from: package-private */
    public void c(int i2) {
        if (MiCS.a().f() != null) {
            MiCS.a().f().a(this.f7715a, i2);
            this.D = true;
        }
    }

    public void a(String str, int i2, int i3, int i4, int i5) {
        int i6;
        if (MiCS.a().f() != null && this.z != null) {
            List<ChatDataSource.Data> C2 = this.z.C();
            ArrayList arrayList = new ArrayList();
            int i7 = -1;
            if (C2 != null) {
                for (int i8 = 0; i8 < C2.size(); i8++) {
                    arrayList.add(C2.get(i8).i());
                    if (TextUtils.equals(str, C2.get(i8).a())) {
                        i7 = i8;
                    }
                }
                i6 = i7;
            } else {
                i6 = -1;
            }
            MiCS.a().f().a(arrayList, i6, i2, i3, i4, i5);
            this.D = true;
        }
    }

    public boolean q() {
        return this.D;
    }

    private void a(boolean z2) {
        if (z2) {
            ChatUtils.a(this.k);
            return;
        }
        this.k.scrollBy(0, 1);
        this.k.scrollBy(0, -1);
    }

    public void a(CharSequence charSequence) {
        if (this.z != null) {
            this.z.g(charSequence.toString());
        }
    }

    /* access modifiers changed from: package-private */
    public void a(String[] strArr) {
        if (strArr != null && strArr.length != 0 && this.z != null) {
            File[] fileArr = new File[strArr.length];
            for (int i2 = 0; i2 < strArr.length; i2++) {
                fileArr[i2] = new File(strArr[i2]);
            }
            Logger.a("send image size = %s. got image size = %s.", Integer.valueOf(fileArr.length), Integer.valueOf(strArr.length));
            this.z.a(fileArr);
        }
    }

    public Enum u() {
        if (this.z != null) {
            return this.z.u();
        }
        return null;
    }
}
