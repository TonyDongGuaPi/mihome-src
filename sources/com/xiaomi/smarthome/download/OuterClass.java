package com.xiaomi.smarthome.download;

public class OuterClass {
    /* access modifiers changed from: private */

    /* renamed from: a  reason: collision with root package name */
    public String f15568a;

    public void a(InnerClass innerClass) {
        this.f15568a = innerClass.b;
    }

    public class InnerClass {
        /* access modifiers changed from: private */
        public String b;

        public InnerClass() {
        }

        public String a() {
            return OuterClass.this.f15568a;
        }
    }
}
