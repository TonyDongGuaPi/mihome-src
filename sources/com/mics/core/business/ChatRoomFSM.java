package com.mics.core.business;

import com.mics.core.business.fsm.processor.BaseProcessor;
import com.mics.core.business.fsm.processor.ProcessorHuman;
import com.mics.core.business.fsm.processor.ProcessorIdle;
import com.mics.core.business.fsm.processor.ProcessorInTransfer;
import com.mics.core.business.fsm.processor.ProcessorLeavingMessage;
import com.mics.core.business.fsm.processor.ProcessorNetworkError;
import com.mics.core.business.fsm.processor.ProcessorOverLost;
import com.mics.core.business.fsm.processor.ProcessorOverNegative;
import com.mics.core.business.fsm.processor.ProcessorOverPositive;
import com.mics.core.business.fsm.processor.ProcessorRobot;
import com.mics.core.business.fsm.processor.ProcessorStart;
import com.mics.core.fsm.Config;
import com.mics.core.fsm.Event;
import com.mics.core.fsm.State;
import com.mics.fsm.StateMachine;
import com.mics.fsm.StateMachineConfig;

public abstract class ChatRoomFSM extends ChatRoom {
    private StateMachine b;
    private BaseProcessor c;
    private BaseProcessor d;
    private BaseProcessor e;
    private BaseProcessor f;
    private BaseProcessor g;
    private BaseProcessor h;
    private BaseProcessor i;
    private BaseProcessor j;
    private BaseProcessor k;
    private BaseProcessor l;

    protected ChatRoomFSM(String str) {
        super(str);
        G();
        H();
    }

    private void G() {
        this.c = new ProcessorIdle();
        this.d = new ProcessorStart();
        this.e = new ProcessorRobot();
        this.f = new ProcessorLeavingMessage();
        this.g = new ProcessorHuman();
        this.h = new ProcessorInTransfer();
        this.i = new ProcessorOverPositive();
        this.j = new ProcessorOverNegative();
        this.k = new ProcessorOverLost();
        this.l = new ProcessorNetworkError();
        this.c.a((ChatRoom) this);
        this.d.a((ChatRoom) this);
        this.e.a((ChatRoom) this);
        this.f.a((ChatRoom) this);
        this.g.a((ChatRoom) this);
        this.h.a((ChatRoom) this);
        this.i.a((ChatRoom) this);
        this.j.a((ChatRoom) this);
        this.k.a((ChatRoom) this);
        this.l.a((ChatRoom) this);
    }

    private void H() {
        this.b = StateMachine.a((StateMachineConfig) new Config());
        this.b.a((Enum) State.IDLE, (StateMachine.Processor) this.c);
        this.b.a((Enum) State.START, (StateMachine.Processor) this.d);
        this.b.a((Enum) State.ROBOT, (StateMachine.Processor) this.e);
        this.b.a((Enum) State.IN_QUEUE, (StateMachine.Processor) this.h);
        this.b.a((Enum) State.MESSAGE, (StateMachine.Processor) this.f);
        this.b.a((Enum) State.HUMAN, (StateMachine.Processor) this.g);
        this.b.a((Enum) State.OVER_POSITIVE, (StateMachine.Processor) this.i);
        this.b.a((Enum) State.OVER_NEGATIVE, (StateMachine.Processor) this.j);
        this.b.a((Enum) State.OVER_LOST, (StateMachine.Processor) this.k);
        this.b.a((Enum) State.NETWORK_ERROR, (StateMachine.Processor) this.l);
    }

    /* access modifiers changed from: protected */
    public StateMachine F() {
        return this.b;
    }

    /* access modifiers changed from: protected */
    public void a(IChatView iChatView) {
        super.a(iChatView);
        this.b.a((Enum) Event.OPEN, new Object[0]);
    }
}
