package com.xiaomi.smarthome.ui.base;

import com.xiaomi.smarthome.ui.base.ViewState;
import io.reactivex.Observable;

public interface MvpLCEView<VS extends ViewState> extends BaseSHMvpView<VS> {
    Observable<Boolean> a();

    Observable<Boolean> b();

    Observable<Boolean> c();
}
