package com.facebook.react.views.scroll;

public enum ScrollEventType {
    BEGIN_DRAG,
    END_DRAG,
    SCROLL,
    MOMENTUM_BEGIN,
    MOMENTUM_END;

    public static String getJSEventName(ScrollEventType scrollEventType) {
        switch (scrollEventType) {
            case BEGIN_DRAG:
                return "topScrollBeginDrag";
            case END_DRAG:
                return "topScrollEndDrag";
            case SCROLL:
                return "topScroll";
            case MOMENTUM_BEGIN:
                return "topMomentumScrollBegin";
            case MOMENTUM_END:
                return "topMomentumScrollEnd";
            default:
                throw new IllegalArgumentException("Unsupported ScrollEventType: " + scrollEventType);
        }
    }

    public String getJSEventName() {
        return getJSEventName(this);
    }
}
