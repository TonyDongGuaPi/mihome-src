package com.mics.core.business.fsm.processor;

import com.mics.core.business.ChatExecutors;
import com.mics.core.business.ChatRoom;

public class ProcessorIdle extends BaseProcessor {
    public void a(ChatRoom chatRoom) {
        super.a(chatRoom);
    }

    public void a(Object... objArr) {
        a((CharSequence) getClass().getSimpleName() + " enter");
        a().s();
        ChatExecutors.d().execute(new Runnable() {
            public void run() {
                if (ProcessorIdle.this.b()) {
                    ProcessorIdle.this.c().a();
                }
            }
        });
    }

    public void d() {
        a((CharSequence) getClass().getSimpleName() + " exit");
    }
}
