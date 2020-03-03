package com.xiaomi.plugin;

import android.widget.TextView;

public abstract class RedpointManager {
    public static final int CART_COUNT = 2;
    public static final int MESSAGE_NEWS_COUNT = 1;
    public static final int NEED_COMMENT_ORDER_COUNT = 7;
    public static final int NEW_VERSION = 3;
    public static final int PAID_ORDER_COUNT = 8;
    public static final int PINWEI_COUNT = 10;
    public static final int REFUND_ORDER_COUNT = 9;
    public static final int UNPAID_ORDER_COUNT = 4;
    public static final int USER_NEW_ARRIVER_COUNT = 5;
    public static final int USER_PROFILE_COUNT = 6;

    public interface BadgeUpdateListener {
        void onUpdate();
    }

    public interface RedpointListener {
        void onRefresh();
    }

    public abstract void addBadgeUpdateListener(BadgeUpdateListener badgeUpdateListener);

    public abstract void addRedPoint(RedpointListener redpointListener);

    public abstract void clearBadge();

    public abstract int getBadgeCount();

    public abstract int getBadgeCount(String str);

    public abstract int getBadgeCount(String str, String str2);

    public abstract int getRedPoint(int i);

    public abstract void removeBadgeUpdateListener(BadgeUpdateListener badgeUpdateListener);

    public abstract void removeRedPoint(RedpointListener redpointListener);

    public abstract void setBadge(String str, String str2, int i);

    public abstract void setRedPoint(int i, int i2);

    public abstract void setRedPointView(int i, TextView textView);

    public abstract void update();
}
