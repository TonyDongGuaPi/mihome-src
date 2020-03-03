package com.mics.core.fsm;

public enum Event {
    OPEN,
    JOIN_ROBOT,
    QUEUE,
    JOIN_HUMAN,
    TO_LEAVE_MESSAGE,
    OVER,
    CLOSE,
    NETWORK_LOST,
    NETWORK_RECONNECTION,
    RETRY_FAILURE,
    RESET,
    OPEN_FAILURE,
    REMOTE_LOST
}
