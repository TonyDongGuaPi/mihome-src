package com.xiaomi.smarthome.newui.widget.micards;

public class CardStatusObserver {
    private static CardStatus sCardStatus = CardStatus.HIDING;

    public enum CardStatus {
        SHOWING,
        HIDING,
        SHOW_ANIMATION,
        HIDE_ANIMATION
    }

    static void setCardStatus(CardStatus cardStatus) {
        sCardStatus = cardStatus;
    }

    public static CardStatus getCardStatus() {
        return sCardStatus;
    }
}
