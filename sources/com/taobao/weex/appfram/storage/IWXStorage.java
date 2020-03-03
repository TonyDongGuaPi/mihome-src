package com.taobao.weex.appfram.storage;

import android.support.annotation.Nullable;
import com.taobao.weex.bridge.JSCallback;

interface IWXStorage {
    void getAllKeys(@Nullable JSCallback jSCallback);

    void getItem(String str, @Nullable JSCallback jSCallback);

    void length(@Nullable JSCallback jSCallback);

    void removeItem(String str, @Nullable JSCallback jSCallback);

    void setItem(String str, String str2, @Nullable JSCallback jSCallback);

    void setItemPersistent(String str, String str2, @Nullable JSCallback jSCallback);
}
