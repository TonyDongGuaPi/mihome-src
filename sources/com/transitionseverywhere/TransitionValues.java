package com.transitionseverywhere;

import android.support.v4.util.ArrayMap;
import android.view.View;
import java.util.ArrayList;
import java.util.Map;

public class TransitionValues {

    /* renamed from: a  reason: collision with root package name */
    public View f9482a;
    public final Map<String, Object> b = new ArrayMap();
    final ArrayList<Transition> c = new ArrayList<>();

    public boolean equals(Object obj) {
        if (!(obj instanceof TransitionValues)) {
            return false;
        }
        TransitionValues transitionValues = (TransitionValues) obj;
        return this.f9482a == transitionValues.f9482a && this.b.equals(transitionValues.b);
    }

    public int hashCode() {
        return (this.f9482a.hashCode() * 31) + this.b.hashCode();
    }

    public String toString() {
        String str = (("TransitionValues@" + Integer.toHexString(hashCode()) + ":\n") + "    view = " + this.f9482a + "\n") + "    values:";
        for (String next : this.b.keySet()) {
            str = str + "    " + next + ": " + this.b.get(next) + "\n";
        }
        return str;
    }
}
