package com.mics.core.business.fsm.processor;

import com.mics.core.business.ChatExecutors;
import com.mics.core.business.ChatRoom;
import com.mics.core.fsm.State;

public class ProcessorStart extends BaseProcessor {
    public void a(ChatRoom chatRoom) {
        super.a(chatRoom);
    }

    public void a(final Object... objArr) {
        a((CharSequence) getClass().getSimpleName() + " enter");
        a().a(false);
        ChatExecutors.d().execute(new Runnable() {
            public void run() {
                if (ProcessorStart.this.b()) {
                    if (objArr != null && objArr.length > 0 && objArr[0] == State.NETWORK_ERROR) {
                        ProcessorStart.this.c().c();
                    }
                    ProcessorStart.this.c().b();
                }
            }
        });
    }

    public void d() {
        a((CharSequence) getClass().getSimpleName() + " exit");
    }
}
