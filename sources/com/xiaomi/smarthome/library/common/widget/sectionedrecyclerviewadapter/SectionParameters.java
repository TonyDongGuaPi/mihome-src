package com.xiaomi.smarthome.library.common.widget.sectionedrecyclerviewadapter;

import android.support.annotation.LayoutRes;

public class SectionParameters {
    @LayoutRes

    /* renamed from: a  reason: collision with root package name */
    public final Integer f19065a;
    @LayoutRes
    public final Integer b;
    @LayoutRes
    public final int c;
    @LayoutRes
    public final Integer d;
    @LayoutRes
    public final Integer e;
    @LayoutRes
    public final Integer f;

    public static class Builder {
        /* access modifiers changed from: private */

        /* renamed from: a  reason: collision with root package name */
        public final int f19066a;
        /* access modifiers changed from: private */
        @LayoutRes
        public Integer b;
        /* access modifiers changed from: private */
        @LayoutRes
        public Integer c;
        /* access modifiers changed from: private */
        @LayoutRes
        public Integer d;
        /* access modifiers changed from: private */
        @LayoutRes
        public Integer e;
        /* access modifiers changed from: private */
        @LayoutRes
        public Integer f;

        public Builder(@LayoutRes int i) {
            this.f19066a = i;
        }

        public Builder a(@LayoutRes int i) {
            this.b = Integer.valueOf(i);
            return this;
        }

        public Builder b(@LayoutRes int i) {
            this.c = Integer.valueOf(i);
            return this;
        }

        public Builder c(@LayoutRes int i) {
            this.d = Integer.valueOf(i);
            return this;
        }

        public Builder d(@LayoutRes int i) {
            this.e = Integer.valueOf(i);
            return this;
        }

        public Builder e(@LayoutRes int i) {
            this.f = Integer.valueOf(i);
            return this;
        }

        public SectionParameters a() {
            return new SectionParameters(this);
        }
    }

    private SectionParameters(Builder builder) {
        this.f19065a = builder.b;
        this.b = builder.c;
        this.c = builder.f19066a;
        this.d = builder.d;
        this.e = builder.e;
        this.f = builder.f;
    }
}
