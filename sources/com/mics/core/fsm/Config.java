package com.mics.core.fsm;

import com.mics.fsm.StateMachine;
import com.mics.fsm.StateMachineConfig;
import com.mics.fsm.Transition;

public class Config implements StateMachineConfig {
    public void a(StateMachine stateMachine) {
        stateMachine.a((Enum) State.IDLE);
    }

    public void a(Transition transition) {
        transition.a().a(State.IDLE).c(Event.OPEN).b(State.START).a().a().a(State.START).c(Event.JOIN_ROBOT).b(State.ROBOT).a().a().a(State.START).c(Event.JOIN_HUMAN).b(State.HUMAN).a().a().a(State.START).c(Event.OPEN_FAILURE).b(State.START).a().a().a(State.START).c(Event.RETRY_FAILURE).b(State.OVER_LOST).a().a().a(State.START).c(Event.NETWORK_LOST).b(State.NETWORK_ERROR).a().a().a(State.START).c(Event.QUEUE).b(State.IN_QUEUE).a().a().a(State.START).c(Event.TO_LEAVE_MESSAGE).b(State.MESSAGE).a().a().a(State.ROBOT).c(Event.QUEUE).b(State.IN_QUEUE).a().a().a(State.ROBOT).c(Event.OVER).b(State.OVER_NEGATIVE).a().a().a(State.ROBOT).c(Event.TO_LEAVE_MESSAGE).b(State.MESSAGE).a().a().a(State.ROBOT).c(Event.REMOTE_LOST).b(State.START).a().a().a(State.ROBOT).c(Event.NETWORK_LOST).b(State.NETWORK_ERROR).a().a().a(State.IN_QUEUE).c(Event.JOIN_HUMAN).b(State.HUMAN).a().a().a(State.IN_QUEUE).c(Event.REMOTE_LOST).b(State.START).a().a().a(State.IN_QUEUE).c(Event.OVER).b(State.OVER_NEGATIVE).a().a().a(State.IN_QUEUE).c(Event.CLOSE).b(State.OVER_POSITIVE).a().a().a(State.IN_QUEUE).c(Event.NETWORK_LOST).b(State.NETWORK_ERROR).a().a().a(State.HUMAN).c(Event.OVER).b(State.OVER_NEGATIVE).a().a().a(State.HUMAN).c(Event.CLOSE).b(State.OVER_POSITIVE).a().a().a(State.HUMAN).c(Event.REMOTE_LOST).b(State.START).a().a().a(State.HUMAN).c(Event.NETWORK_LOST).b(State.NETWORK_ERROR).a().a().a(State.MESSAGE).c(Event.OVER).b(State.OVER_NEGATIVE).a().a().a(State.MESSAGE).c(Event.CLOSE).b(State.OVER_POSITIVE).a().a().a(State.MESSAGE).c(Event.NETWORK_LOST).b(State.NETWORK_ERROR).a().a().a(State.NETWORK_ERROR).c(Event.NETWORK_RECONNECTION).b(State.START).a().a().a(State.OVER_NEGATIVE).c(Event.RESET).b(State.IDLE).a().a().a(State.OVER_POSITIVE).c(Event.RESET).b(State.IDLE).a().a().a(State.OVER_LOST).c(Event.RESET).b(State.IDLE).a();
        transition.b();
    }
}
