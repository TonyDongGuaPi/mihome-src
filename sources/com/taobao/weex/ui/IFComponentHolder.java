package com.taobao.weex.ui;

import com.taobao.weex.bridge.Invoker;
import com.taobao.weex.bridge.JavascriptInvokable;

public interface IFComponentHolder extends JavascriptInvokable, ComponentCreator {
    Invoker getPropertyInvoker(String str);

    void loadIfNonLazy();
}
