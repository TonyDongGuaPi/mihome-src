package com.mics.core.business.fsm.processor;

import com.mics.core.business.ChatRoom;
import com.mics.core.business.IChatView;
import com.mics.fsm.StateMachine;
import com.mics.util.Logger;

public abstract class BaseProcessor implements StateMachine.Processor {

    /* renamed from: a  reason: collision with root package name */
    private ChatRoom f7671a;

    public void a(ChatRoom chatRoom) {
        this.f7671a = chatRoom;
    }

    /* access modifiers changed from: protected */
    public ChatRoom a() {
        return this.f7671a;
    }

    /* access modifiers changed from: protected */
    public boolean b() {
        return this.f7671a != null && this.f7671a.l();
    }

    /* access modifiers changed from: protected */
    public IChatView c() {
        if (this.f7671a == null) {
            return null;
        }
        return this.f7671a.k();
    }

    public static void a(CharSequence charSequence) {
        Logger.a(String.valueOf(charSequence), new Object[0]);
    }
}
