package com.mics.core.business.fsm.processor;

import com.mics.core.business.ChatExecutors;
import com.mics.core.business.ChatRoom;

public class ProcessorHuman extends BaseProcessor {
    public void a(ChatRoom chatRoom) {
        super.a(chatRoom);
    }

    public void a(Object... objArr) {
        a((CharSequence) getClass().getSimpleName() + " enter");
        ChatExecutors.d().execute(new Runnable() {
            public void run() {
                if (ProcessorHuman.this.b()) {
                    ProcessorHuman.this.c().j();
                }
            }
        });
    }

    public void d() {
        a((CharSequence) getClass().getSimpleName() + " exit");
    }
}
