package com.xiaomi.mishopsdk.Listener;

import com.xiaomi.shop2.model.BaseUserCentralCounterInfo;
import java.util.Observable;

@Deprecated
public class PointObservable extends Observable {
    private BaseUserCentralCounterInfo counterInfo;

    public BaseUserCentralCounterInfo getCounterInfo() {
        return this.counterInfo;
    }

    public void setCounterInfo(BaseUserCentralCounterInfo baseUserCentralCounterInfo) {
        if (this.counterInfo != baseUserCentralCounterInfo) {
            this.counterInfo = baseUserCentralCounterInfo;
            setChanged();
            notifyObservers();
        }
    }

    public void notifyChanged() {
        if (this.counterInfo != null) {
            setChanged();
            notifyObservers();
        }
    }
}
