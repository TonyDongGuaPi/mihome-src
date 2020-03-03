package com.xiaomi.smarthome.ui.base;

import com.hannesdorfmann.mosby3.mvp.MvpView;
import com.xiaomi.smarthome.ui.base.ViewState;

public interface BaseSHMvpView<VS extends ViewState> extends MvpView {
    void render(VS vs);
}
