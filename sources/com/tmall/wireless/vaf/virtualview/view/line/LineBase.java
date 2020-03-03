package com.tmall.wireless.vaf.virtualview.view.line;

import android.util.Log;
import com.libra.Utils;
import com.libra.virtualview.common.StringBase;
import com.taobao.weex.el.parse.Operators;
import com.tmall.wireless.vaf.framework.VafContext;
import com.tmall.wireless.vaf.virtualview.core.ViewBase;
import com.tmall.wireless.vaf.virtualview.core.ViewCache;

public abstract class LineBase extends ViewBase {
    private static final String al = "LineBase_TMTEST";

    /* renamed from: a  reason: collision with root package name */
    protected boolean f9406a = true;
    protected int ah = -16777216;
    protected int ai = 1;
    protected int aj = 1;
    protected float[] ak = {3.0f, 5.0f, 3.0f, 5.0f};

    public LineBase(VafContext vafContext, ViewCache viewCache) {
        super(vafContext, viewCache);
    }

    public boolean b() {
        return this.f9406a;
    }

    public int am() {
        return this.ai;
    }

    public int an() {
        return this.aj;
    }

    public int ao() {
        return this.ah;
    }

    /* access modifiers changed from: protected */
    public boolean b(int i, float f) {
        if (i != 793104392) {
            return false;
        }
        this.ai = Utils.b((double) f);
        if (this.ai > 0) {
            return true;
        }
        this.ai = 1;
        return true;
    }

    /* access modifiers changed from: protected */
    public boolean a(int i, String str) {
        boolean a2 = super.a(i, str);
        int i2 = 0;
        if (a2) {
            return a2;
        }
        if (i == 94842723) {
            this.c.a(this, StringBase.G, str, 3);
        } else if (i != 1037639619) {
            return false;
        } else {
            if (str != null) {
                String trim = str.trim();
                if (!trim.startsWith(Operators.ARRAY_START_STR) || !trim.endsWith(Operators.ARRAY_END_STR)) {
                    Log.e(al, "no match []");
                } else {
                    String[] split = trim.substring(1, trim.length() - 1).split(",");
                    if (split.length <= 0 || (split.length & 1) != 0) {
                        Log.e(al, "length invalidate:" + split.length);
                    } else {
                        float[] fArr = new float[split.length];
                        while (i2 < split.length) {
                            try {
                                fArr[i2] = Float.parseFloat(split[i2]);
                                i2++;
                            } catch (NumberFormatException unused) {
                            }
                        }
                        if (i2 == split.length) {
                            this.ak = fArr;
                        }
                    }
                }
            }
        }
        return true;
    }

    /* access modifiers changed from: protected */
    public boolean c(int i, int i2) {
        boolean c = super.c(i, i2);
        boolean z = false;
        if (c) {
            return c;
        }
        if (i == -1439500848) {
            if (i2 != 0) {
                z = true;
            }
            this.f9406a = z;
        } else if (i == 94842723) {
            this.ah = i2;
        } else if (i == 109780401) {
            this.aj = i2;
        } else if (i != 793104392) {
            return false;
        } else {
            this.ai = Utils.b((double) i2);
            if (this.ai <= 0) {
                this.ai = 1;
            }
        }
        return true;
    }

    /* access modifiers changed from: protected */
    public boolean b(int i, int i2) {
        boolean b = super.b(i, i2);
        if (b) {
            return b;
        }
        if (i != 793104392) {
            return false;
        }
        this.ai = Utils.a((double) i2);
        if (this.ai <= 0) {
            this.ai = 1;
        }
        return true;
    }

    /* access modifiers changed from: protected */
    public boolean a(int i, float f) {
        boolean a2 = super.a(i, f);
        if (a2) {
            return a2;
        }
        if (i != 793104392) {
            return false;
        }
        this.ai = Utils.a((double) f);
        if (this.ai <= 0) {
            this.ai = 1;
        }
        return true;
    }
}
