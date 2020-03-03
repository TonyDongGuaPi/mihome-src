package com.xiaomi.base.utils;

public class SpringSystem extends BaseSpringSystem {
    private SpringSystem(SpringLooper springLooper) {
        super(springLooper);
    }

    public static SpringSystem e() {
        return new SpringSystem((SpringLooper) null);
    }
}
