package com.tmall.wireless.vaf.virtualview.view.grid;

import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import com.libra.Utils;
import com.libra.virtualview.common.StringBase;
import com.tmall.wireless.vaf.framework.VafContext;
import com.tmall.wireless.vaf.framework.cm.ContainerService;
import com.tmall.wireless.vaf.virtualview.core.IContainer;
import com.tmall.wireless.vaf.virtualview.core.NativeViewBase;
import com.tmall.wireless.vaf.virtualview.core.ViewBase;
import com.tmall.wireless.vaf.virtualview.core.ViewCache;
import com.tmall.wireless.vaf.virtualview.event.EventData;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Grid extends NativeViewBase {
    private static final String ah = "Grid_TMTEST";
    private GridImp ai;

    public boolean m() {
        return true;
    }

    public Grid(VafContext vafContext, ViewCache viewCache) {
        super(vafContext, viewCache);
        this.ai = new GridImp(vafContext.m());
        this.ai.setVirtualView(this);
        this.f9383a = this.ai;
    }

    public void e() {
        super.e();
        b();
    }

    private void b() {
        ContainerService q = this.W.q();
        int childCount = this.ai.getChildCount();
        for (int i = 0; i < childCount; i++) {
            q.a((IContainer) this.ai.getChildAt(i));
        }
        this.ai.removeAllViews();
    }

    public void c(Object obj) {
        super.c(obj);
        if (obj instanceof JSONObject) {
            obj = ((JSONObject) obj).optJSONArray(ai());
        }
        b();
        if (obj instanceof JSONArray) {
            JSONArray jSONArray = (JSONArray) obj;
            ContainerService q = this.W.q();
            int length = jSONArray.length();
            for (int i = 0; i < length; i++) {
                try {
                    JSONObject jSONObject = jSONArray.getJSONObject(i);
                    String optString = jSONObject.optString("type");
                    if (!TextUtils.isEmpty(optString)) {
                        View a2 = q.a(optString);
                        if (a2 != null) {
                            ViewBase virtualView = ((IContainer) a2).getVirtualView();
                            virtualView.b((Object) jSONObject);
                            this.ai.addView(a2);
                            if (virtualView.E()) {
                                this.W.e().a(1, EventData.a(this.W, virtualView));
                            }
                            virtualView.c();
                        } else {
                            Log.e(ah, "create view failed");
                        }
                    } else {
                        Log.e(ah, "get type failed");
                    }
                } catch (JSONException e) {
                    Log.e(ah, "get json object failed:" + e);
                }
            }
            return;
        }
        Log.e(ah, "setData not array");
    }

    public void f() {
        super.f();
        this.ai.setAutoDimDirection(this.G);
        this.ai.setAutoDimX(this.H);
        this.ai.setAutoDimY(this.I);
    }

    /* access modifiers changed from: protected */
    public boolean b(int i, float f) {
        boolean b = super.b(i, f);
        if (b) {
            return b;
        }
        if (i == 196203191) {
            this.ai.setItemVerticalMargin(Utils.b((double) f));
            return true;
        } else if (i == 1671241242) {
            this.ai.setItemHeight(Utils.b((double) f));
            return true;
        } else if (i != 2129234981) {
            return false;
        } else {
            this.ai.setItemHorizontalMargin(Utils.b((double) f));
            return true;
        }
    }

    /* access modifiers changed from: protected */
    public boolean c(int i, int i2) {
        if (i == -669528209) {
            this.ai.setColumnCount(i2);
        } else if (i == 196203191) {
            this.ai.setItemVerticalMargin(Utils.b((double) i2));
        } else if (i == 1671241242) {
            this.ai.setItemHeight(Utils.b((double) i2));
        } else if (i != 2129234981) {
            return super.c(i, i2);
        } else {
            this.ai.setItemHorizontalMargin(Utils.b((double) i2));
        }
        return true;
    }

    /* access modifiers changed from: protected */
    public boolean a(int i, String str) {
        if (i == 196203191) {
            this.c.a(this, StringBase.aS, str, 1);
            return true;
        } else if (i != 2129234981) {
            return super.a(i, str);
        } else {
            this.c.a(this, StringBase.aR, str, 1);
            return true;
        }
    }

    /* access modifiers changed from: protected */
    public boolean a(int i, float f) {
        if (i == 196203191) {
            this.ai.setItemVerticalMargin(Utils.a((double) f));
        } else if (i == 1671241242) {
            this.ai.setItemHeight(Utils.a((double) f));
        } else if (i != 2129234981) {
            return super.a(i, f);
        } else {
            this.ai.setItemHorizontalMargin(Utils.a((double) f));
        }
        return true;
    }

    /* access modifiers changed from: protected */
    public boolean b(int i, int i2) {
        if (i == 196203191) {
            this.ai.setItemVerticalMargin(Utils.a((double) i2));
        } else if (i == 1671241242) {
            this.ai.setItemHeight(Utils.a((double) i2));
        } else if (i != 2129234981) {
            return super.b(i, i2);
        } else {
            this.ai.setItemHorizontalMargin(Utils.a((double) i2));
        }
        return true;
    }

    public static class Builder implements ViewBase.IBuilder {
        public ViewBase a(VafContext vafContext, ViewCache viewCache) {
            return new Grid(vafContext, viewCache);
        }
    }
}