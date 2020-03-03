package com.mics.core.fsm;

public enum State {
    IDLE,
    START,
    ROBOT,
    IN_QUEUE,
    MESSAGE,
    HUMAN,
    OVER_POSITIVE,
    OVER_NEGATIVE,
    OVER_LOST,
    NETWORK_ERROR
}
