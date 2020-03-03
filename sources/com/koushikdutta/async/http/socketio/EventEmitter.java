package com.koushikdutta.async.http.socketio;

import com.koushikdutta.async.util.HashList;
import java.util.Iterator;
import java.util.List;
import org.json.JSONArray;

public class EventEmitter {

    /* renamed from: a  reason: collision with root package name */
    HashList<EventCallback> f6211a = new HashList<>();

    interface OnceCallback extends EventCallback {
    }

    /* access modifiers changed from: package-private */
    public void a(String str, JSONArray jSONArray, Acknowledge acknowledge) {
        List list = (List) this.f6211a.get(str);
        if (list != null) {
            Iterator it = list.iterator();
            while (it.hasNext()) {
                EventCallback eventCallback = (EventCallback) it.next();
                eventCallback.a(str, jSONArray, acknowledge);
                if (eventCallback instanceof OnceCallback) {
                    it.remove();
                }
            }
        }
    }

    public void a(String str, EventCallback eventCallback) {
        c(str, eventCallback);
    }

    public void b(String str, final EventCallback eventCallback) {
        c(str, new OnceCallback() {
            public void a(String str, JSONArray jSONArray, Acknowledge acknowledge) {
                eventCallback.a(str, jSONArray, acknowledge);
            }
        });
    }

    public void c(String str, EventCallback eventCallback) {
        this.f6211a.add(str, eventCallback);
    }

    public void d(String str, EventCallback eventCallback) {
        List list = (List) this.f6211a.get(str);
        if (list != null) {
            list.remove(eventCallback);
        }
    }
}
