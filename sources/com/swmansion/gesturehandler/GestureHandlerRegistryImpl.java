package com.swmansion.gesturehandler;

import android.view.View;
import java.util.ArrayList;
import java.util.WeakHashMap;

public class GestureHandlerRegistryImpl implements GestureHandlerRegistry {

    /* renamed from: a  reason: collision with root package name */
    private WeakHashMap<View, ArrayList<GestureHandler>> f8878a = new WeakHashMap<>();

    public <T extends GestureHandler> T a(View view, T t) {
        ArrayList arrayList = this.f8878a.get(view);
        if (arrayList == null) {
            ArrayList arrayList2 = new ArrayList(1);
            arrayList2.add(t);
            this.f8878a.put(view, arrayList2);
        } else {
            arrayList.add(t);
        }
        return t;
    }

    public ArrayList<GestureHandler> a(View view) {
        return this.f8878a.get(view);
    }
}
