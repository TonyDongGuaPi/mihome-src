package com.xiaomi.smarthome.newui.mainpage.devicepage.model;

import com.xiaomi.smarthome.ui.base.EditModeView;
import com.xiaomi.smarthome.ui.base.ViewState;
import java.util.Collections;
import java.util.List;
import java.util.Set;

public class MainPageViewState implements ViewState {
    /* access modifiers changed from: private */

    /* renamed from: a  reason: collision with root package name */
    public final boolean f20690a;
    /* access modifiers changed from: private */
    public final boolean b;
    /* access modifiers changed from: private */
    public final List<MainPageDeviceModel> c;
    private final boolean d;
    private final int e;
    private final EditModeView.EditModeModel f;
    private final Set<MainPageDeviceModel> g;

    public EditModeView.EditModeModel a() {
        return this.f;
    }

    public MainPageViewState(boolean z, boolean z2, List<MainPageDeviceModel> list, boolean z3, int i, EditModeView.EditModeModel editModeModel, Set<MainPageDeviceModel> set) {
        this.f20690a = z;
        this.b = z2;
        this.c = list;
        this.d = z3;
        this.e = i;
        this.f = editModeModel;
        this.g = set;
    }

    public boolean b() {
        return this.f20690a;
    }

    public boolean c() {
        return this.b;
    }

    public List<MainPageDeviceModel> d() {
        return this.c;
    }

    public boolean e() {
        return this.d;
    }

    public Set<MainPageDeviceModel> f() {
        return this.g;
    }

    public int g() {
        return this.e;
    }

    public static final class Builder {

        /* renamed from: a  reason: collision with root package name */
        private boolean f20691a;
        private boolean b;
        private List<MainPageDeviceModel> c;
        private boolean d;
        private int e;
        private EditModeView.EditModeModel f;
        private Set<MainPageDeviceModel> g;

        public Builder() {
            this.c = Collections.emptyList();
        }

        public Builder(MainPageViewState mainPageViewState) {
            if (mainPageViewState != null) {
                this.f20691a = mainPageViewState.f20690a;
                this.b = mainPageViewState.b;
                this.c = mainPageViewState.c;
            }
        }

        public Builder a(boolean z) {
            this.f20691a = z;
            return this;
        }

        public Builder b(boolean z) {
            this.b = z;
            return this;
        }

        public Builder a(List<MainPageDeviceModel> list) {
            this.c = list;
            this.f20691a = false;
            return this;
        }

        public Builder c(boolean z) {
            this.d = z;
            return this;
        }

        public Builder a(EditModeView.EditModeModel editModeModel) {
            this.f = editModeModel;
            return this;
        }

        public MainPageViewState a() {
            return new MainPageViewState(this.f20691a, this.b, this.c, this.d, this.e, this.f, this.g);
        }

        public Builder a(int i) {
            this.e = i;
            return this;
        }
    }
}
