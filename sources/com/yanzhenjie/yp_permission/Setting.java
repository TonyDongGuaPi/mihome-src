package com.yanzhenjie.yp_permission;

public interface Setting extends SettingService {

    public interface Action {
        void a();
    }

    Setting a(Action action);

    void b();
}
