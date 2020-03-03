package com.mi.global.bbs.utils;

public interface StatConstants {

    public static final class Key {
        public static final String DEVICE_ID = "device_id";
        public static final String ORDER_ID = "order_id";
        public static final String TYPE = "type";
        public static final String USER_ID = "user_id";
    }

    public static final class Type {
        public static final int EXTRA_TYPE_DEVICE_ID_TO_ORDER_ID = 1;
        public static final int EXTRA_TYPE_USER_ID = 2;
    }
}
