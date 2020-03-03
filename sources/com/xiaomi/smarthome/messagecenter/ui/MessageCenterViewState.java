package com.xiaomi.smarthome.messagecenter.ui;

import com.xiaomi.smarthome.ui.base.BaseModel;
import com.xiaomi.smarthome.ui.base.PullRefreshViewState;
import java.util.ArrayList;
import java.util.List;

public class MessageCenterViewState extends PullRefreshViewState {

    /* renamed from: a  reason: collision with root package name */
    boolean f10560a;
    List<BaseModel> b = new ArrayList();

    public boolean a() {
        return this.f10560a;
    }

    public void a(boolean z) {
        this.f10560a = z;
    }

    public List<BaseModel> b() {
        return this.b;
    }

    public void a(List<BaseModel> list) {
        this.b = list;
    }

    public static final class Builder {

        /* renamed from: a  reason: collision with root package name */
        boolean f10561a;
        List<BaseModel> b = new ArrayList();
        protected boolean c;
        protected Error d;
        protected boolean e;
        protected Error f;

        public Builder(MessageCenterViewState messageCenterViewState) {
            if (messageCenterViewState != null) {
                this.f10561a = messageCenterViewState.a();
                this.b = messageCenterViewState.b();
                this.c = messageCenterViewState.c();
                this.d = messageCenterViewState.d();
                this.e = messageCenterViewState.e();
                this.f = messageCenterViewState.f();
            }
        }

        public MessageCenterViewState a() {
            MessageCenterViewState messageCenterViewState = new MessageCenterViewState();
            messageCenterViewState.a(this.f10561a);
            messageCenterViewState.a(this.b);
            messageCenterViewState.b(this.c);
            messageCenterViewState.a(this.d);
            messageCenterViewState.c(this.e);
            messageCenterViewState.b(this.f);
            return messageCenterViewState;
        }
    }
}
