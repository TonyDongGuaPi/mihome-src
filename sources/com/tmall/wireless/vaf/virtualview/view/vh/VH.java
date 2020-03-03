package com.tmall.wireless.vaf.virtualview.view.vh;

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

public class VH extends NativeViewBase {
    private static final String ah = "VH_TMTEST";
    private VHImp ai;

    public boolean m() {
        return true;
    }

    public VH(VafContext vafContext, ViewCache viewCache) {
        super(vafContext, viewCache);
        this.ai = new VHImp(vafContext.m());
        this.f9383a = this.ai;
    }

    public ViewBase b(int i) {
        return ((IContainer) this.ai.getChildAt(i)).getVirtualView();
    }

    private void b() {
        ContainerService q = this.W.q();
        int childCount = this.ai.getChildCount();
        for (int i = 0; i < childCount; i++) {
            q.a((IContainer) this.ai.getChildAt(i));
        }
        this.ai.removeAllViews();
    }

    public void b(int i, String str) {
        b();
        ContainerService q = this.W.q();
        while (i > 0) {
            this.ai.addView(q.a(str));
            i--;
        }
    }

    public void c(Object obj) {
        super.c(obj);
        if (obj instanceof JSONObject) {
            obj = ((JSONObject) obj).optJSONArray(ai());
        }
        if (obj instanceof JSONArray) {
            JSONArray jSONArray = (JSONArray) obj;
            int length = jSONArray.length();
            b();
            ContainerService q = this.W.q();
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
                            virtualView.c();
                            if (virtualView.E()) {
                                this.W.e().a(1, EventData.a(this.W, virtualView));
                            }
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
        Log.e(ah, "setData not array:" + obj);
    }

    /* access modifiers changed from: protected */
    public boolean b(int i, float f) {
        boolean b = super.b(i, f);
        if (b) {
            return b;
        }
        if (i == 1671241242) {
            this.ai.setItemHeight(Utils.b((double) f));
            return true;
        } else if (i == 1810961057) {
            this.ai.setItemMargin(Utils.b((double) f));
            return true;
        } else if (i != 2146088563) {
            return false;
        } else {
            this.ai.setItemWidth(Utils.b((double) f));
            return true;
        }
    }

    /* access modifiers changed from: protected */
    public boolean c(int i, int i2) {
        boolean c = super.c(i, i2);
        if (c) {
            return c;
        }
        if (i == -1439500848) {
            this.ai.setOrientation(i2);
            return true;
        } else if (i == 1671241242) {
            this.ai.setItemHeight(Utils.b((double) i2));
            return true;
        } else if (i == 1810961057) {
            this.ai.setItemMargin(Utils.b((double) i2));
            return true;
        } else if (i != 2146088563) {
            return false;
        } else {
            this.ai.setItemWidth(Utils.b((double) i2));
            return true;
        }
    }

    /* access modifiers changed from: protected */
    public boolean a(int i, float f) {
        boolean a2 = super.a(i, f);
        if (a2) {
            return a2;
        }
        if (i == 1671241242) {
            this.ai.setItemHeight(Utils.a((double) f));
            return true;
        } else if (i == 1810961057) {
            this.ai.setItemMargin(Utils.a((double) f));
            return true;
        } else if (i != 2146088563) {
            return false;
        } else {
            this.ai.setItemWidth(Utils.a((double) f));
            return true;
        }
    }

    /* access modifiers changed from: protected */
    public boolean b(int i, int i2) {
        boolean b = super.b(i, i2);
        if (b) {
            return b;
        }
        if (i == 1671241242) {
            this.ai.setItemHeight(Utils.a((double) i2));
            return true;
        } else if (i == 1810961057) {
            this.ai.setItemMargin(Utils.a((double) i2));
            return true;
        } else if (i != 2146088563) {
            return false;
        } else {
            this.ai.setItemWidth(Utils.a((double) i2));
            return true;
        }
    }

    /* access modifiers changed from: protected */
    public boolean a(int i, String str) {
        if (i == 1671241242) {
            this.c.a(this, StringBase.K, str, 1);
            return true;
        } else if (i == 1810961057) {
            this.c.a(this, StringBase.bb, str, 1);
            return true;
        } else if (i != 2146088563) {
            return super.a(i, str);
        } else {
            this.c.a(this, StringBase.ba, str, 1);
            return true;
        }
    }

    public static class Builder implements ViewBase.IBuilder {
        public ViewBase a(VafContext vafContext, ViewCache viewCache) {
            return new VH(vafContext, viewCache);
        }
    }
}
