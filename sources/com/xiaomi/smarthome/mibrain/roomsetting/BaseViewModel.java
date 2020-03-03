package com.xiaomi.smarthome.mibrain.roomsetting;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

public abstract class BaseViewModel<T> extends ViewModel {
    /* access modifiers changed from: protected */

    /* renamed from: a  reason: collision with root package name */
    public MutableLiveData<T> f10663a = new MutableLiveData<>();

    public abstract MutableLiveData<T> a();
}
