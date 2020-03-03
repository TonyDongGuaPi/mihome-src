package com.miuipub.internal.variable;

import android.graphics.Bitmap;
import android.view.View;

public abstract class Android_View_View_class {
    public abstract Bitmap createSnapshot(View view, Bitmap.Config config, int i, boolean z);

    public abstract void setLeftDirectly(View view, int i);

    public abstract void setRightDirectly(View view, int i);

    public abstract void setScrollXDirectly(View view, int i);

    public abstract void setScrollYDirectly(View view, int i);

    public static class Factory extends AbsClassFactory {
        private Android_View_View_class Android_View_View_class;

        private static class Holder {
            static final Factory INSTANCE = new Factory();

            private Holder() {
            }
        }

        private Factory() {
            this.Android_View_View_class = (Android_View_View_class) create("Android_View_View_class");
        }

        public static Factory getInstance() {
            return Holder.INSTANCE;
        }

        public Android_View_View_class get() {
            return this.Android_View_View_class;
        }
    }
}
