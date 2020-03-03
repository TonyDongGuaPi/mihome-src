package com.tmall.wireless.vaf.virtualview.core;

import android.support.v4.util.ArrayMap;
import android.support.v4.util.SparseArrayCompat;
import android.util.Log;
import com.tmall.wireless.vaf.framework.VafContext;
import com.tmall.wireless.vaf.virtualview.core.Adapter;
import com.tmall.wireless.vaf.virtualview.event.EventData;
import java.util.concurrent.atomic.AtomicInteger;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class ArrayAdapter extends Adapter {
    private static final String f = "ArrayAdapter_TMTEST";
    private AtomicInteger g = new AtomicInteger(0);
    private ArrayMap<String, Integer> h = new ArrayMap<>();
    private SparseArrayCompat<String> i = new SparseArrayCompat<>();
    private JSONArray j;

    public ArrayAdapter(VafContext vafContext) {
        super(vafContext);
    }

    public void a(Object obj) {
        if (obj == null) {
            this.j = null;
        } else if (obj instanceof JSONArray) {
            this.j = (JSONArray) obj;
        } else {
            Log.e(f, "setData failed:" + obj);
        }
    }

    public int a() {
        if (this.j != null) {
            return this.j.length();
        }
        return 0;
    }

    public int b(int i2) {
        if (this.j == null) {
            return 0;
        }
        try {
            String optString = this.j.getJSONObject(i2).optString("type");
            if (this.h.containsKey(optString)) {
                return this.h.get(optString).intValue();
            }
            int andIncrement = this.g.getAndIncrement();
            this.h.put(optString, Integer.valueOf(andIncrement));
            this.i.put(andIncrement, optString);
            return andIncrement;
        } catch (JSONException unused) {
            return 0;
        }
    }

    public void a(Adapter.ViewHolder viewHolder, int i2) {
        try {
            Object obj = this.j.get(i2);
            if (obj instanceof JSONObject) {
                JSONObject jSONObject = (JSONObject) obj;
                ViewBase virtualView = ((IContainer) viewHolder.f9380a).getVirtualView();
                if (virtualView != null) {
                    virtualView.b((Object) jSONObject);
                }
                if (virtualView.E()) {
                    this.b.e().a(1, EventData.a(this.b, virtualView));
                }
                virtualView.c();
                return;
            }
            Log.e(f, "failed");
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public Adapter.ViewHolder c(int i2) {
        return new Adapter.ViewHolder(this.e.a(this.i.get(i2), this.d));
    }
}
