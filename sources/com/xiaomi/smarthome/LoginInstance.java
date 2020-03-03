package com.xiaomi.smarthome;

import com.xiaomi.smarthome.application.SHApplication;
import com.xiaomi.smarthome.frame.LoginInterface;

public class LoginInstance extends LoginInterface {
    public static void a() {
        f15995a = new LoginInstance();
    }

    public boolean b() {
        return SHApplication.getStateNotifier().a() == 4;
    }
}
