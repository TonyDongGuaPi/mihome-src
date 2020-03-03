package com.xiaomi.smarthome.ui.base;

import com.xiaomi.smarthome.ui.base.ViewState;
import io.reactivex.Observable;
import java.util.List;

public interface EditModeView<VS extends ViewState> extends BaseSHMvpView<VS> {

    /* renamed from: a  reason: collision with root package name */
    public static final int f22776a = 1;
    public static final int b = 2;
    public static final int c = 3;
    public static final int d = 4;
    public static final int e = 5;
    public static final int f = 6;

    public static class EditModeModel<D> {

        /* renamed from: a  reason: collision with root package name */
        public int f22777a;
        public List<D> b;
    }

    Observable<EditModeModel> onEditModeAction();
}
