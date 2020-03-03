package com.mibi.common.account;

public class AccountChangeEvent {

    /* renamed from: a  reason: collision with root package name */
    public static final int f7438a = 0;
    public static final int b = 1;
    public static final int c = 2;
    private int d;

    public AccountChangeEvent(int i) {
        this.d = i;
    }

    public void a(int i) {
        this.d = i;
    }

    public int a() {
        return this.d;
    }
}
