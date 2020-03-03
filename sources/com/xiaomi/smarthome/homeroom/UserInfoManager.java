package com.xiaomi.smarthome.homeroom;

import android.util.LongSparseArray;
import com.xiaomi.smarthome.application.SHApplication;
import com.xiaomi.smarthome.family.api.RemoteFamilyApi;
import com.xiaomi.smarthome.frame.AsyncCallback;
import com.xiaomi.smarthome.frame.Error;
import com.xiaomi.smarthome.framework.api.model.UserInfo;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class UserInfoManager {

    /* renamed from: a  reason: collision with root package name */
    private static UserInfoManager f18224a;
    /* access modifiers changed from: private */
    public LongSparseArray<UserInfo> b = new LongSparseArray<>();

    public interface UpdateCompleteCallBack {
        void a();
    }

    public static UserInfoManager a() {
        if (f18224a == null) {
            synchronized (UserInfoManager.class) {
                if (f18224a == null) {
                    f18224a = new UserInfoManager();
                }
            }
        }
        return f18224a;
    }

    private UserInfoManager() {
    }

    public void a(Set<Long> set, final UpdateCompleteCallBack updateCompleteCallBack) {
        RemoteFamilyApi.a().a(SHApplication.getAppContext(), (List<Long>) new ArrayList(set), (AsyncCallback<List<UserInfo>, Error>) new AsyncCallback<List<UserInfo>, Error>() {
            /* renamed from: a */
            public void onSuccess(List<UserInfo> list) {
                int i = 0;
                while (i < list.size()) {
                    try {
                        UserInfo userInfo = list.get(i);
                        if (userInfo != null) {
                            UserInfoManager.this.b.put(Long.parseLong(userInfo.f16462a), userInfo);
                        }
                        i++;
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                if (updateCompleteCallBack != null) {
                    updateCompleteCallBack.a();
                }
            }

            public void onFailure(Error error) {
                if (updateCompleteCallBack != null) {
                    updateCompleteCallBack.a();
                }
            }
        });
    }

    public UserInfo a(long j) {
        UserInfo userInfo = this.b.get(j);
        if (userInfo != null) {
            return userInfo;
        }
        HashSet hashSet = new HashSet();
        hashSet.add(Long.valueOf(j));
        a(hashSet, (UpdateCompleteCallBack) null);
        return null;
    }

    public void a(UserInfo userInfo) {
        this.b.put(Long.parseLong(userInfo.f16462a), userInfo);
    }
}
