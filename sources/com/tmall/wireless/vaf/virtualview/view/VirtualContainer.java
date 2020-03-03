package com.tmall.wireless.vaf.virtualview.view;

import android.view.View;
import android.view.ViewGroup;
import com.tmall.wireless.vaf.framework.VafContext;
import com.tmall.wireless.vaf.framework.cm.ContainerService;
import com.tmall.wireless.vaf.virtualview.core.IContainer;
import com.tmall.wireless.vaf.virtualview.core.IView;
import com.tmall.wireless.vaf.virtualview.core.Layout;
import com.tmall.wireless.vaf.virtualview.core.ViewBase;
import com.tmall.wireless.vaf.virtualview.core.ViewCache;
import com.tmall.wireless.vaf.virtualview.event.EventData;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;

@Deprecated
public class VirtualContainer extends ViewBase {

    /* renamed from: a  reason: collision with root package name */
    private static final String f9402a = "VContainer_TMTEST";
    private int ah = -1;
    private IView ai;

    public VirtualContainer(VafContext vafContext, ViewCache viewCache) {
        super(vafContext, viewCache);
    }

    public int b() {
        return this.ah;
    }

    public void e() {
        super.e();
        if (this.ai != null) {
            this.W.q().a((IContainer) this.ai);
            ((ViewGroup) this.c.a()).removeView((View) this.ai);
            this.ai = null;
        }
    }

    public void onComMeasure(int i, int i2) {
        if (this.ai != null) {
            this.ai.onComMeasure(i, i2);
        }
    }

    public void onComLayout(boolean z, int i, int i2, int i3, int i4) {
        if (this.ai != null) {
            this.ai.onComLayout(z, i, i2, i3, i4);
        }
    }

    public int getComMeasuredWidth() {
        if (this.ai != null) {
            return this.ai.getComMeasuredWidth();
        }
        return 0;
    }

    public int getComMeasuredHeight() {
        if (this.ai != null) {
            return this.ai.getComMeasuredHeight();
        }
        return 0;
    }

    public void measureComponent(int i, int i2) {
        if (this.ai != null) {
            this.ai.measureComponent(i, i2);
        }
    }

    public void comLayout(int i, int i2, int i3, int i4) {
        super.comLayout(i, i2, i3, i4);
        if (this.ai != null) {
            this.ai.comLayout(i, i2, i3, i4);
        }
    }

    /* access modifiers changed from: protected */
    public void a(ViewBase viewBase) {
        if (viewBase instanceof Layout) {
            List<ViewBase> b = ((Layout) viewBase).b();
            if (b != null) {
                int size = b.size();
                for (int i = 0; i < size; i++) {
                    a(b.get(i));
                }
                return;
            }
            return;
        }
        View g_ = viewBase.g_();
        if (g_ != null) {
            ((ViewGroup) this.c.a()).addView(g_);
        }
    }

    /* access modifiers changed from: protected */
    public void b(ViewBase viewBase) {
        if (viewBase instanceof Layout) {
            List<ViewBase> b = ((Layout) viewBase).b();
            if (b != null) {
                int size = b.size();
                for (int i = 0; i < size; i++) {
                    b(b.get(i));
                }
                return;
            }
            return;
        }
        View g_ = viewBase.g_();
        if (g_ != null) {
            ((ViewGroup) this.c.a()).removeView(g_);
        }
    }

    public void c(Object obj) {
        JSONObject optJSONObject;
        super.c(obj);
        ContainerService q = this.W.q();
        if (this.ai != null) {
            q.a((IContainer) this.ai);
            ((ViewGroup) this.c.a()).removeView((View) this.ai);
        }
        if (obj instanceof JSONArray) {
            JSONArray jSONArray = (JSONArray) obj;
            if (this.ah < jSONArray.length() && (optJSONObject = jSONArray.optJSONObject(this.ah)) != null) {
                this.ai = (IView) q.a(optJSONObject.optString("type"));
                if (this.ai != null) {
                    ViewBase virtualView = ((IContainer) this.ai).getVirtualView();
                    virtualView.b((Object) optJSONObject);
                    ((ViewGroup) this.c.a()).addView((View) this.ai);
                    if (virtualView.E()) {
                        this.W.e().a(1, EventData.a(this.W, virtualView));
                    }
                }
            }
        }
    }

    /* access modifiers changed from: protected */
    public boolean c(int i, int i2) {
        boolean c = super.c(i, i2);
        if (c) {
            return c;
        }
        if (i != 106006350) {
            return false;
        }
        this.ah = i2;
        return true;
    }

    public static class Builder implements ViewBase.IBuilder {
        public ViewBase a(VafContext vafContext, ViewCache viewCache) {
            return new VirtualContainer(vafContext, viewCache);
        }
    }
}
