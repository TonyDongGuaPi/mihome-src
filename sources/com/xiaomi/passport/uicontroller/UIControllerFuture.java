package com.xiaomi.passport.uicontroller;

import com.xiaomi.accountsdk.futureservice.ClientFuture;

public abstract class UIControllerFuture<ModelDataType, UIDataType> extends ClientFuture<ModelDataType, UIDataType> {

    public interface UICallback<UIDataType> extends ClientFuture.ClientCallback<UIDataType> {
    }

    /* access modifiers changed from: protected */
    public abstract UIDataType convertModelDataToUIData(ModelDataType modeldatatype) throws Throwable;

    protected UIControllerFuture(UICallback<UIDataType> uICallback) {
        super(uICallback);
    }

    /* access modifiers changed from: protected */
    public final UIDataType convertServerDataToClientData(ModelDataType modeldatatype) throws Throwable {
        return convertModelDataToUIData(modeldatatype);
    }
}
