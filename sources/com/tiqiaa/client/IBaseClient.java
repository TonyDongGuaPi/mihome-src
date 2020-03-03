package com.tiqiaa.client;

public interface IBaseClient {

    public interface BaseCallBack {
        public static final int ERROR_CODE_AUTHED_FAILED = 4;
        public static final int ERROR_CODE_FAILURE = 1;
        public static final int ERROR_CODE_FORBIDDEN = 6;
        public static final int ERROR_CODE_NO_NET = 2;
        public static final int ERROR_CODE_NO_TOKEN = 3;
        public static final int ERROR_CODE_PARAM_FAULT = 5;
        public static final int ERROR_CODE_SUCCESS = 0;
    }
}
