package com.tiqiaa.client.impl;

import android.content.Context;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.tiqiaa.client.IUserClient;
import com.tiqiaa.icontrol.util.NetUtils;
import com.tiqiaa.icontrol.util.TQResponse;
import com.tiqiaa.icontrol.util.TiqiaaService;
import com.tiqiaa.icontrol.util.Utils;
import com.tiqiaa.remote.entity.User;

public class UserClient implements IUserClient {
    private static final String BASE_USER_URL = (TiqiaaService.isLocalServer() ? "http://192.168.0.108:8080/tqir/tjtt/user" : "https://irdna.izazamall.com/tqir/tjtt/user");
    /* access modifiers changed from: private */
    public static User currentUser;
    private NetUtils client;

    public void forgetPassword(String str, String str2, IUserClient.CallBackOnGetPasswordDone callBackOnGetPasswordDone) {
    }

    public void retrievePassword(User user, IUserClient.CallBackOnRetrievePasswordDone callBackOnRetrievePasswordDone) {
    }

    public void update_user(User user, IUserClient.CallBackOnUpdateUserDone callBackOnUpdateUserDone) {
    }

    public UserClient(Context context) {
        this.client = new NetUtils(context);
    }

    public void register(String str, String str2, String str3, String str4, final IUserClient.CallBackOnRegisterDone callBackOnRegisterDone) {
        User user = new User();
        user.setPhone(str);
        user.setEmail(str2);
        user.setName(str3);
        user.setPassword(str4);
        this.client.doPost(String.valueOf(BASE_USER_URL) + "/register", user, new RequestCallBack<String>() {
            public void onSuccess(ResponseInfo<String> responseInfo) {
                if (responseInfo.d != 200 || responseInfo.f6335a == null) {
                    callBackOnRegisterDone.onRegisterDone(0);
                    return;
                }
                TQResponse tQResponse = (TQResponse) Utils.JsonParseObject((String) responseInfo.f6335a, TQResponse.class);
                if (tQResponse == null) {
                    callBackOnRegisterDone.onRegisterDone(1);
                } else if (tQResponse.getErrcode() == 10000) {
                    callBackOnRegisterDone.onRegisterDone(0);
                } else if (tQResponse.getErrcode() == 10101) {
                    callBackOnRegisterDone.onRegisterDone(1001);
                } else if (tQResponse.getErrcode() == 10102) {
                    callBackOnRegisterDone.onRegisterDone(1002);
                } else if (tQResponse.getErrcode() == 10003) {
                    callBackOnRegisterDone.onRegisterDone(3);
                } else if (tQResponse.getErrcode() == 10005) {
                    callBackOnRegisterDone.onRegisterDone(4);
                } else if (tQResponse.getErrcode() == 10002 || tQResponse.getErrcode() == 10004) {
                    callBackOnRegisterDone.onRegisterDone(5);
                } else {
                    callBackOnRegisterDone.onRegisterDone(1);
                }
            }

            public void onFailure(HttpException httpException, String str) {
                callBackOnRegisterDone.onRegisterDone(1);
            }
        });
    }

    public void login(String str, String str2, String str3, final IUserClient.CallBackOnLoginDone callBackOnLoginDone) {
        User user = new User();
        user.setEmail(str2);
        user.setPhone(str);
        user.setPassword(str3);
        this.client.doPost(String.valueOf(BASE_USER_URL) + "/login", user, new RequestCallBack<String>() {
            public void onFailure(HttpException httpException, String str) {
                callBackOnLoginDone.onLoginDone(1, (User) null);
            }

            public void onSuccess(ResponseInfo<String> responseInfo) {
                if (responseInfo.d != 200 || responseInfo.f6335a == null) {
                    callBackOnLoginDone.onLoginDone(1, (User) null);
                    return;
                }
                TQResponse tQResponse = (TQResponse) Utils.JsonParseObject((String) responseInfo.f6335a, TQResponse.class);
                if (tQResponse == null) {
                    callBackOnLoginDone.onLoginDone(1, (User) null);
                } else if (tQResponse.getErrcode() == 10000) {
                    User user = (User) tQResponse.getData(User.class);
                    callBackOnLoginDone.onLoginDone(0, user);
                    UserClient.currentUser = user;
                } else if (tQResponse.getErrcode() == 10101) {
                    callBackOnLoginDone.onLoginDone(2001, (User) null);
                } else if (tQResponse.getErrcode() == 10202) {
                    callBackOnLoginDone.onLoginDone(2002, (User) null);
                } else if (tQResponse.getErrcode() == 10003) {
                    callBackOnLoginDone.onLoginDone(3, (User) null);
                } else if (tQResponse.getErrcode() == 10005) {
                    callBackOnLoginDone.onLoginDone(4, (User) null);
                } else if (tQResponse.getErrcode() == 10201) {
                    callBackOnLoginDone.onLoginDone(2001, (User) null);
                } else if (tQResponse.getErrcode() == 10002 || tQResponse.getErrcode() == 10004) {
                    callBackOnLoginDone.onLoginDone(5, (User) null);
                } else {
                    callBackOnLoginDone.onLoginDone(1, (User) null);
                }
            }
        });
    }

    public static User getCurrentUser() {
        return currentUser;
    }
}
