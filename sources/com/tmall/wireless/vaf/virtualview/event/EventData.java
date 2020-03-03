package com.tmall.wireless.vaf.virtualview.event;

import android.app.Activity;
import android.view.MotionEvent;
import android.view.View;
import com.tmall.wireless.vaf.framework.VafContext;
import com.tmall.wireless.vaf.virtualview.core.ViewBase;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class EventData {

    /* renamed from: a  reason: collision with root package name */
    protected static List<EventData> f9394a = new LinkedList();
    public ViewBase b;
    public Activity c;
    public VafContext d;
    public View e;
    public MotionEvent f;
    public HashMap<String, Object> g = new HashMap<>();

    public static void a() {
        f9394a.clear();
    }

    public EventData(VafContext vafContext, ViewBase viewBase) {
        this.d = vafContext;
        this.c = vafContext.r();
        this.b = viewBase;
    }

    public EventData(VafContext vafContext, ViewBase viewBase, View view, MotionEvent motionEvent) {
        this.d = vafContext;
        this.c = vafContext.r();
        this.b = viewBase;
        this.e = view;
        this.f = motionEvent;
    }

    public void b() {
        a(this);
        this.b = null;
        this.c = null;
        this.d = null;
        this.e = null;
        this.f = null;
    }

    public static EventData a(VafContext vafContext, ViewBase viewBase) {
        View view;
        if (viewBase != null) {
            view = viewBase.g_();
            if (view == null && viewBase.l() != null) {
                view = viewBase.l().a();
            }
        } else {
            view = null;
        }
        return a(vafContext, viewBase, view, (MotionEvent) null);
    }

    public static EventData a(VafContext vafContext, ViewBase viewBase, View view, MotionEvent motionEvent) {
        if (f9394a.size() <= 0) {
            return new EventData(vafContext, viewBase, view, motionEvent);
        }
        EventData remove = f9394a.remove(0);
        remove.b = viewBase;
        remove.e = view;
        remove.d = vafContext;
        remove.c = vafContext.r();
        return remove;
    }

    protected static void a(EventData eventData) {
        if (eventData != null) {
            f9394a.add(eventData);
        }
    }
}
