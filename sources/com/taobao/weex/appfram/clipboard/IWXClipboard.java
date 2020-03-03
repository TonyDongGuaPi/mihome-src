package com.taobao.weex.appfram.clipboard;

import android.support.annotation.Nullable;
import com.taobao.weex.bridge.JSCallback;

interface IWXClipboard {
    void getString(@Nullable JSCallback jSCallback);

    void setString(String str);
}