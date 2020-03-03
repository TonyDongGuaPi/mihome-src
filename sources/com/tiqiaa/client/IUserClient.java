package com.tiqiaa.client;

import com.tiqiaa.client.IBaseClient;
import com.tiqiaa.remote.entity.User;

public interface IUserClient extends IBaseClient {

    public interface CallBackOnGetPasswordDone extends IBaseClient.BaseCallBack {
        public static final int ERROR_CODE_GET_PW_USER_NOT_EXISTS = 3001;
        public static final int ERROR_CODE__GET_PW_EMAIL_SEND_FAILED = 3002;

        void onGetPasswordDone(int i);
    }

    public interface CallBackOnLoginDone extends IBaseClient.BaseCallBack {
        public static final int ERROR_CODE_NOT_REGISTERED = 2001;
        public static final int ERROR_CODE_PW_WRONG = 2002;

        void onLoginDone(int i, User user);
    }

    public interface CallBackOnRegisterDone extends IBaseClient.BaseCallBack {
        public static final int ERROR_CODE_PHONE_EMAIL_INVALID = 1001;
        public static final int ERROR_CODE_PHONE_OR_EMAIL_EXISTS = 1002;

        void onRegisterDone(int i);
    }

    public interface CallBackOnRetrievePasswordDone extends IBaseClient.BaseCallBack {
        public static final int ERROR_CODE_RETRIEVE_PW_AUTH_FAILED = 4003;
        public static final int ERROR_CODE_RETRIEVE_PW_EMAIL_SEND_FAILED = 4002;
        public static final int ERROR_CODE_RETRIEVE_PW_USER_NOT_EXISTS = 4001;

        void onRetrievePasswordDone(int i);
    }

    public interface CallBackOnUpdateUserDone extends IBaseClient.BaseCallBack {
        public static final int ERROR_CODE_UPDATE_USER_OLD_PW_INCORRECT = 5001;

        void onUpdateUserDone(int i);
    }

    void forgetPassword(String str, String str2, CallBackOnGetPasswordDone callBackOnGetPasswordDone);

    void login(String str, String str2, String str3, CallBackOnLoginDone callBackOnLoginDone);

    void register(String str, String str2, String str3, String str4, CallBackOnRegisterDone callBackOnRegisterDone);

    void retrievePassword(User user, CallBackOnRetrievePasswordDone callBackOnRetrievePasswordDone);

    void update_user(User user, CallBackOnUpdateUserDone callBackOnUpdateUserDone);
}
