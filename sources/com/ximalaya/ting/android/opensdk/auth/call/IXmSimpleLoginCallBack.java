package com.ximalaya.ting.android.opensdk.auth.call;

import com.ximalaya.ting.android.opensdk.datatrasfer.IDataCallBack;
import java.util.HashMap;

public interface IXmSimpleLoginCallBack {
    void a(HashMap<String, String> hashMap, IDataCallBack<String> iDataCallBack);
}