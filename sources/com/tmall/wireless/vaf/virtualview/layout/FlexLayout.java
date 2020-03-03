package com.tmall.wireless.vaf.virtualview.layout;

import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.v4.view.ViewCompat;
import android.util.SparseIntArray;
import android.view.View;
import com.libra.virtualview.common.StringBase;
import com.taobao.weex.el.parse.Operators;
import com.tmall.wireless.vaf.framework.VafContext;
import com.tmall.wireless.vaf.virtualview.core.Layout;
import com.tmall.wireless.vaf.virtualview.core.ViewBase;
import com.tmall.wireless.vaf.virtualview.core.ViewCache;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

@Deprecated
public class FlexLayout extends Layout {
    public static final int ah = 0;
    public static final int ai = 1;
    public static final int aj = 2;
    public static final int ak = 4;
    private static final String al = "FlexLayout_TMTEST";
    private boolean[] aA;
    private int am = 0;
    private int an = 0;
    private int ao = 0;
    private int ap = 0;
    private int aq = 0;
    private Drawable ar;
    private Drawable as;
    private int at;
    private int au;
    private int av;
    private int aw;
    private int[] ax;
    private SparseIntArray ay;
    private List<FlexLine> az = new ArrayList();

    @Retention(RetentionPolicy.SOURCE)
    public @interface AlignContent {
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface AlignItems {
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface DividerMode {
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface FlexDirection {
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface FlexWrap {
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface JustifyContent {
    }

    private boolean r(int i) {
        return i == 0 || i == 1;
    }

    public FlexLayout(VafContext vafContext, ViewCache viewCache) {
        super(vafContext, viewCache);
    }

    /* renamed from: am */
    public Params a() {
        return new Params();
    }

    public void onComMeasure(int i, int i2) {
        if (aq()) {
            this.ax = ap();
        }
        if (this.aA == null || this.aA.length < this.f9381a.size()) {
            this.aA = new boolean[this.f9381a.size()];
        }
        switch (this.am) {
            case 0:
            case 1:
                j(i, i2);
                break;
            case 2:
            case 3:
                i(i, i2);
                break;
            default:
                throw new IllegalStateException("Invalid value for the flex direction is set: " + this.am);
        }
        Arrays.fill(this.aA, false);
    }

    private void i(int i, int i2) {
        int i3;
        int i4;
        Params params;
        int i5;
        int i6;
        int i7 = i;
        int i8 = i2;
        int mode = View.MeasureSpec.getMode(i2);
        int size = View.MeasureSpec.getSize(i2);
        this.az.clear();
        int size2 = this.f9381a.size();
        int ab = ab();
        int ad = ad();
        FlexLine flexLine = new FlexLine();
        int i9 = ab + ad;
        flexLine.e = i9;
        FlexLine flexLine2 = flexLine;
        int i10 = 0;
        int i11 = Integer.MIN_VALUE;
        int i12 = 0;
        int i13 = 0;
        while (i12 < size2) {
            ViewBase n = n(i12);
            if (n == null) {
                a(i12, size2, flexLine2);
            } else if (n.J() == 2) {
                flexLine2.h++;
                a(i12, size2, flexLine2);
            } else {
                Params params2 = (Params) n.ae();
                if (params2.v == 4) {
                    flexLine2.l.add(Integer.valueOf(i12));
                }
                int i14 = params2.b;
                if (params2.w != -1.0f && mode == 1073741824) {
                    i14 = Math.round(((float) size) * params2.w);
                }
                n.measureComponent(a(i7, aa() + ac() + params2.d + params2.f, params2.f9382a), a(i8, ab() + ad() + params2.h + params2.j, i14));
                c(n);
                int combineMeasuredStates = ViewCompat.combineMeasuredStates(i10, 0);
                int max = Math.max(i11, n.getComMeasuredWidth() + params2.d + params2.f);
                Params params3 = params2;
                ViewBase viewBase = n;
                FlexLine flexLine3 = flexLine2;
                i3 = mode;
                i4 = i12;
                if (a(mode, size, flexLine2.e, n.getComMeasuredHeight() + params2.h + params2.j, params3, i12, i13)) {
                    if (flexLine3.h > 0) {
                        a(flexLine3);
                    }
                    flexLine2 = new FlexLine();
                    flexLine2.h = 1;
                    flexLine2.e = i9;
                    params = params3;
                    i6 = viewBase.getComMeasuredWidth() + params.d + params.f;
                    i5 = 0;
                } else {
                    params = params3;
                    flexLine3.h++;
                    i5 = i13 + 1;
                    flexLine2 = flexLine3;
                    i6 = max;
                }
                flexLine2.e += viewBase.getComMeasuredHeight() + params.h + params.j;
                flexLine2.i += params.t;
                flexLine2.j += params.u;
                flexLine2.g = Math.max(flexLine2.g, i6);
                if (l(i4, i5)) {
                    flexLine2.e += this.av;
                }
                a(i4, size2, flexLine2);
                i13 = i5;
                i11 = i6;
                i10 = combineMeasuredStates;
                i12 = i4 + 1;
                mode = i3;
                i7 = i;
            }
            i3 = mode;
            i4 = i12;
            i12 = i4 + 1;
            mode = i3;
            i7 = i;
        }
        int i15 = i;
        c(this.am, i15, i8);
        c(this.am, i15, i8, aa() + ac());
        k(this.am, this.ap);
        b(this.am, i15, i8, i10);
    }

    private void j(int i, int i2) {
        int i3;
        int i4;
        int i5;
        int i6;
        Params params;
        int i7;
        int i8 = i;
        int i9 = i2;
        int mode = View.MeasureSpec.getMode(i);
        int size = View.MeasureSpec.getSize(i);
        this.az.clear();
        int size2 = this.f9381a.size();
        int i10 = this.J;
        int i11 = this.K;
        FlexLine flexLine = new FlexLine();
        int i12 = i10 + i11;
        flexLine.e = i12;
        FlexLine flexLine2 = flexLine;
        int i13 = 0;
        int i14 = Integer.MIN_VALUE;
        int i15 = 0;
        int i16 = 0;
        while (i15 < size2) {
            ViewBase n = n(i15);
            if (n == null) {
                a(i15, size2, flexLine2);
            } else if (n.J() == 2) {
                flexLine2.h++;
                a(i15, size2, flexLine2);
            } else {
                Params params2 = (Params) n.ae();
                if (params2.v == 4) {
                    flexLine2.l.add(Integer.valueOf(i15));
                }
                int i17 = params2.f9382a;
                if (params2.w != -1.0f && mode == 1073741824) {
                    i17 = Math.round(((float) size) * params2.w);
                }
                n.measureComponent(a(i8, aa() + ac() + params2.d + params2.f, i17), a(i9, ab() + ad() + params2.h + params2.j, params2.b));
                c(n);
                int combineMeasuredStates = ViewCompat.combineMeasuredStates(i13, 0);
                int max = Math.max(i14, n.getComMeasuredHeight() + params2.h + params2.j);
                Params params3 = params2;
                ViewBase viewBase = n;
                i3 = mode;
                FlexLine flexLine3 = flexLine2;
                int i18 = i15;
                if (a(mode, size, flexLine2.e, n.getComMeasuredWidth() + params2.d + params2.f, params3, i15, i16)) {
                    if (flexLine3.h > 0) {
                        a(flexLine3);
                    }
                    flexLine2 = new FlexLine();
                    flexLine2.h = 1;
                    flexLine2.e = i12;
                    params = params3;
                    i7 = viewBase.getComMeasuredHeight() + params.h + params.j;
                    i6 = 0;
                } else {
                    params = params3;
                    flexLine3.h++;
                    i6 = i16 + 1;
                    flexLine2 = flexLine3;
                    i7 = max;
                }
                flexLine2.e += viewBase.getComMeasuredWidth() + params.d + params.f;
                flexLine2.i += params.t;
                flexLine2.j += params.u;
                flexLine2.g = Math.max(flexLine2.g, i7);
                i5 = i18;
                if (l(i5, i6)) {
                    flexLine2.e += this.aw;
                    flexLine2.f += this.aw;
                }
                if (this.an != 2) {
                    flexLine2.k = Math.max(flexLine2.k, viewBase.V() + params.h);
                } else {
                    flexLine2.k = Math.max(flexLine2.k, (viewBase.getComMeasuredHeight() - viewBase.V()) + params.j);
                }
                a(i5, size2, flexLine2);
                i4 = i7;
                i16 = i6;
                i13 = combineMeasuredStates;
                i15 = i5 + 1;
                i14 = i4;
                mode = i3;
            }
            i4 = i14;
            i5 = i15;
            i3 = mode;
            i15 = i5 + 1;
            i14 = i4;
            mode = i3;
        }
        int i19 = 0;
        c(this.am, i8, i9);
        if (this.ap == 3) {
            for (FlexLine next : this.az) {
                int i20 = Integer.MIN_VALUE;
                for (int i21 = i19; i21 < i19 + next.h; i21++) {
                    ViewBase n2 = n(i21);
                    Params params4 = (Params) n2.ae();
                    if (this.an != 2) {
                        i20 = Math.max(i20, n2.getComMeasuredHeight() + Math.max(next.k - n2.V(), params4.h) + params4.j);
                    } else {
                        i20 = Math.max(i20, n2.getComMeasuredHeight() + params4.h + Math.max((next.k - n2.getComMeasuredHeight()) + n2.V(), params4.j));
                    }
                }
                next.g = i20;
                i19 += next.h;
            }
        }
        c(this.am, i8, i9, ab() + ad());
        k(this.am, this.ap);
        b(this.am, i8, i9, i13);
    }

    private void b(int i, int i2, int i3, int i4) {
        int i5;
        int i6;
        int i7;
        int i8;
        int mode = View.MeasureSpec.getMode(i2);
        int size = View.MeasureSpec.getSize(i2);
        int mode2 = View.MeasureSpec.getMode(i3);
        int size2 = View.MeasureSpec.getSize(i3);
        switch (i) {
            case 0:
            case 1:
                i5 = an() + ab() + ad();
                i6 = ao();
                break;
            case 2:
            case 3:
                i5 = ao();
                i6 = an() + aa() + ac();
                break;
            default:
                throw new IllegalArgumentException("Invalid flex direction: " + i);
        }
        if (mode == Integer.MIN_VALUE) {
            if (size < i6) {
                i4 = ViewCompat.combineMeasuredStates(i4, 16777216);
            } else {
                size = i6;
            }
            i7 = ViewCompat.resolveSizeAndState(size, i2, i4);
        } else if (mode == 0) {
            i7 = ViewCompat.resolveSizeAndState(i6, i2, i4);
        } else if (mode == 1073741824) {
            if (size < i6) {
                i4 = ViewCompat.combineMeasuredStates(i4, 16777216);
            }
            i7 = ViewCompat.resolveSizeAndState(size, i2, i4);
        } else {
            throw new IllegalStateException("Unknown width mode is set: " + mode);
        }
        if (mode2 == Integer.MIN_VALUE) {
            if (size2 < i5) {
                i4 = ViewCompat.combineMeasuredStates(i4, 256);
                i5 = size2;
            }
            i8 = ViewCompat.resolveSizeAndState(i5, i3, i4);
        } else if (mode2 == 0) {
            i8 = ViewCompat.resolveSizeAndState(i5, i3, i4);
        } else if (mode2 == 1073741824) {
            if (size2 < i5) {
                i4 = ViewCompat.combineMeasuredStates(i4, 256);
            }
            i8 = ViewCompat.resolveSizeAndState(size2, i3, i4);
        } else {
            throw new IllegalStateException("Unknown height mode is set: " + mode2);
        }
        d(i7, i8);
    }

    private void k(int i, int i2) {
        if (i2 == 4) {
            int i3 = 0;
            for (FlexLine next : this.az) {
                int i4 = i3;
                int i5 = 0;
                while (i5 < next.h) {
                    ViewBase n = n(i4);
                    Params params = (Params) n.ae();
                    if (params.v == -1 || params.v == 4) {
                        switch (i) {
                            case 0:
                            case 1:
                                b(n, next.g);
                                break;
                            case 2:
                            case 3:
                                a(n, next.g);
                                break;
                            default:
                                throw new IllegalArgumentException("Invalid flex direction: " + i);
                        }
                    }
                    i5++;
                    i4++;
                }
                i3 = i4;
            }
            return;
        }
        for (FlexLine next2 : this.az) {
            Iterator<Integer> it = next2.l.iterator();
            while (true) {
                if (it.hasNext()) {
                    ViewBase n2 = n(it.next().intValue());
                    switch (i) {
                        case 0:
                        case 1:
                            b(n2, next2.g);
                            break;
                        case 2:
                        case 3:
                            a(n2, next2.g);
                            break;
                        default:
                            throw new IllegalArgumentException("Invalid flex direction: " + i);
                    }
                }
            }
        }
    }

    private void a(ViewBase viewBase, int i) {
        Params params = (Params) viewBase.ae();
        viewBase.measureComponent(View.MeasureSpec.makeMeasureSpec(Math.max((i - params.d) - params.f, 0), 1073741824), View.MeasureSpec.makeMeasureSpec(viewBase.getComMeasuredHeight(), 1073741824));
    }

    private void b(ViewBase viewBase, int i) {
        Params params = (Params) viewBase.ae();
        viewBase.measureComponent(View.MeasureSpec.makeMeasureSpec(viewBase.getComMeasuredWidth(), 1073741824), View.MeasureSpec.makeMeasureSpec(Math.max((i - params.h) - params.j, 0), 1073741824));
    }

    private void c(int i, int i2, int i3, int i4) {
        int i5;
        int i6;
        switch (i) {
            case 0:
            case 1:
                i6 = View.MeasureSpec.getMode(i3);
                i5 = View.MeasureSpec.getSize(i3);
                break;
            case 2:
            case 3:
                i6 = View.MeasureSpec.getMode(i2);
                i5 = View.MeasureSpec.getSize(i2);
                break;
            default:
                throw new IllegalArgumentException("Invalid flex direction: " + i);
        }
        if (i6 == 1073741824) {
            int an2 = an() + i4;
            int i7 = 0;
            if (this.az.size() == 1) {
                this.az.get(0).g = i5 - i4;
            } else if (this.az.size() >= 2 && an2 < i5) {
                switch (this.aq) {
                    case 1:
                        int i8 = i5 - an2;
                        FlexLine flexLine = new FlexLine();
                        flexLine.g = i8;
                        this.az.add(0, flexLine);
                        return;
                    case 2:
                        ArrayList arrayList = new ArrayList();
                        FlexLine flexLine2 = new FlexLine();
                        flexLine2.g = (i5 - an2) / 2;
                        int size = this.az.size();
                        while (i7 < size) {
                            if (i7 == 0) {
                                arrayList.add(flexLine2);
                            }
                            arrayList.add(this.az.get(i7));
                            if (i7 == this.az.size() - 1) {
                                arrayList.add(flexLine2);
                            }
                            i7++;
                        }
                        this.az = arrayList;
                        return;
                    case 3:
                        float size2 = ((float) (i5 - an2)) / ((float) (this.az.size() - 1));
                        ArrayList arrayList2 = new ArrayList();
                        int size3 = this.az.size();
                        float f = 0.0f;
                        while (i7 < size3) {
                            arrayList2.add(this.az.get(i7));
                            if (i7 != this.az.size() - 1) {
                                FlexLine flexLine3 = new FlexLine();
                                if (i7 == this.az.size() - 2) {
                                    flexLine3.g = Math.round(f + size2);
                                    f = 0.0f;
                                } else {
                                    flexLine3.g = Math.round(size2);
                                }
                                f += size2 - ((float) flexLine3.g);
                                if (f > 1.0f) {
                                    flexLine3.g++;
                                    f -= 1.0f;
                                } else if (f < -1.0f) {
                                    flexLine3.g--;
                                    f += 1.0f;
                                }
                                arrayList2.add(flexLine3);
                            }
                            i7++;
                        }
                        this.az = arrayList2;
                        return;
                    case 4:
                        int size4 = (i5 - an2) / (this.az.size() * 2);
                        ArrayList arrayList3 = new ArrayList();
                        FlexLine flexLine4 = new FlexLine();
                        flexLine4.g = size4;
                        for (FlexLine add : this.az) {
                            arrayList3.add(flexLine4);
                            arrayList3.add(add);
                            arrayList3.add(flexLine4);
                        }
                        this.az = arrayList3;
                        return;
                    case 5:
                        float size5 = ((float) (i5 - an2)) / ((float) this.az.size());
                        int size6 = this.az.size();
                        float f2 = 0.0f;
                        while (i7 < size6) {
                            FlexLine flexLine5 = this.az.get(i7);
                            float f3 = ((float) flexLine5.g) + size5;
                            if (i7 == this.az.size() - 1) {
                                f3 += f2;
                                f2 = 0.0f;
                            }
                            int round = Math.round(f3);
                            f2 += f3 - ((float) round);
                            if (f2 > 1.0f) {
                                round++;
                                f2 -= 1.0f;
                            } else if (f2 < -1.0f) {
                                round--;
                                f2 += 1.0f;
                            }
                            flexLine5.g = round;
                            i7++;
                        }
                        return;
                    default:
                        return;
                }
            }
        }
    }

    private int an() {
        int size = this.az.size();
        int i = 0;
        for (int i2 = 0; i2 < size; i2++) {
            FlexLine flexLine = this.az.get(i2);
            if (p(i2)) {
                if (r(this.am)) {
                    i += this.av;
                } else {
                    i += this.aw;
                }
            }
            if (o(i2)) {
                if (r(this.am)) {
                    i += this.av;
                } else {
                    i += this.aw;
                }
            }
            i += flexLine.g;
        }
        return i;
    }

    private boolean o(int i) {
        if (i < 0 || i >= this.az.size()) {
            return false;
        }
        for (int i2 = i + 1; i2 < this.az.size(); i2++) {
            if (this.az.get(i2).h > 0) {
                return false;
            }
        }
        if (r(this.am)) {
            if ((this.at & 4) != 0) {
                return true;
            }
            return false;
        } else if ((this.au & 4) != 0) {
            return true;
        } else {
            return false;
        }
    }

    private boolean p(int i) {
        if (i < 0 || i >= this.az.size()) {
            return false;
        }
        if (q(i)) {
            if (r(this.am)) {
                if ((this.at & 1) != 0) {
                    return true;
                }
                return false;
            } else if ((this.au & 1) != 0) {
                return true;
            } else {
                return false;
            }
        } else if (r(this.am)) {
            if ((this.at & 2) != 0) {
                return true;
            }
            return false;
        } else if ((this.au & 2) != 0) {
            return true;
        } else {
            return false;
        }
    }

    private boolean q(int i) {
        for (int i2 = 0; i2 < i; i2++) {
            if (this.az.get(i2).h > 0) {
                return false;
            }
        }
        return true;
    }

    private void c(int i, int i2, int i3) {
        int i4;
        int i5;
        int a2;
        switch (i) {
            case 0:
            case 1:
                int mode = View.MeasureSpec.getMode(i2);
                int size = View.MeasureSpec.getSize(i2);
                if (mode != 1073741824) {
                    size = ao();
                }
                i4 = size;
                i5 = aa() + ac();
                break;
            case 2:
            case 3:
                int mode2 = View.MeasureSpec.getMode(i3);
                i4 = View.MeasureSpec.getSize(i3);
                if (mode2 != 1073741824) {
                    i4 = ao();
                }
                i5 = ab() + ad();
                break;
            default:
                throw new IllegalArgumentException("Invalid flex direction: " + i);
        }
        int i6 = 0;
        for (FlexLine next : this.az) {
            if (next.e < i4) {
                a2 = b(next, i, i4, i5, i6);
            } else {
                a2 = a(next, i, i4, i5, i6);
            }
            i6 = a2;
        }
    }

    private int a(FlexLine flexLine, int i, int i2, int i3, int i4) {
        float f;
        FlexLine flexLine2 = flexLine;
        int i5 = i2;
        int i6 = flexLine2.e;
        if (flexLine2.j <= 0.0f || i5 > flexLine2.e) {
            return i4 + flexLine2.h;
        }
        float f2 = ((float) (flexLine2.e - i5)) / flexLine2.j;
        flexLine2.e = i3 + flexLine2.f;
        int i7 = 0;
        int i8 = i4;
        boolean z = false;
        float f3 = 0.0f;
        while (i7 < flexLine2.h) {
            ViewBase n = n(i8);
            if (n != null) {
                if (n.J() == 2) {
                    i8++;
                } else {
                    Params params = (Params) n.ae();
                    if (r(i)) {
                        if (!this.aA[i8]) {
                            float comMeasuredWidth = ((float) n.getComMeasuredWidth()) - (params.u * f2);
                            if (i7 == flexLine2.h - 1) {
                                comMeasuredWidth += f3;
                                f3 = 0.0f;
                            }
                            int round = Math.round(comMeasuredWidth);
                            if (round < params.x) {
                                round = params.x;
                                this.aA[i8] = true;
                                flexLine2.j -= params.u;
                                z = true;
                            } else {
                                f3 += comMeasuredWidth - ((float) round);
                                double d = (double) f3;
                                if (d > 1.0d) {
                                    round++;
                                    f3 -= 1.0f;
                                } else if (d < -1.0d) {
                                    round--;
                                    f3 += 1.0f;
                                }
                            }
                            n.measureComponent(View.MeasureSpec.makeMeasureSpec(round, 1073741824), View.MeasureSpec.makeMeasureSpec(n.getComMeasuredHeight(), 1073741824));
                        }
                        flexLine2.e += n.getComMeasuredWidth() + params.d + params.f;
                        f = f2;
                    } else {
                        if (!this.aA[i8]) {
                            float comMeasuredHeight = ((float) n.getComMeasuredHeight()) - (params.u * f2);
                            if (i7 == flexLine2.h - 1) {
                                comMeasuredHeight += f3;
                                f3 = 0.0f;
                            }
                            int round2 = Math.round(comMeasuredHeight);
                            if (round2 < params.y) {
                                round2 = params.y;
                                this.aA[i8] = true;
                                flexLine2.j -= params.u;
                                f = f2;
                                z = true;
                            } else {
                                f3 += comMeasuredHeight - ((float) round2);
                                f = f2;
                                double d2 = (double) f3;
                                if (d2 > 1.0d) {
                                    round2++;
                                    f3 -= 1.0f;
                                } else if (d2 < -1.0d) {
                                    round2--;
                                    f3 += 1.0f;
                                }
                            }
                            n.measureComponent(View.MeasureSpec.makeMeasureSpec(n.getComMeasuredWidth(), 1073741824), View.MeasureSpec.makeMeasureSpec(round2, 1073741824));
                        } else {
                            f = f2;
                        }
                        flexLine2.e += n.getComMeasuredHeight() + params.h + params.j;
                    }
                    i8++;
                    i7++;
                    f2 = f;
                }
            }
            int i9 = i;
            f = f2;
            i7++;
            f2 = f;
        }
        int i10 = i;
        if (z && i6 != flexLine2.e) {
            a(flexLine, i, i2, i3, i4);
        }
        return i8;
    }

    private int b(FlexLine flexLine, int i, int i2, int i3, int i4) {
        float f;
        float f2;
        FlexLine flexLine2 = flexLine;
        int i5 = i2;
        if (flexLine2.i <= 0.0f || i5 < flexLine2.e) {
            return i4 + flexLine2.h;
        }
        int i6 = flexLine2.e;
        float f3 = ((float) (i5 - flexLine2.e)) / flexLine2.i;
        flexLine2.e = i3 + flexLine2.f;
        int i7 = 0;
        int i8 = i4;
        boolean z = false;
        float f4 = 0.0f;
        while (i7 < flexLine2.h) {
            ViewBase n = n(i8);
            if (n != null) {
                if (n.J() == 2) {
                    i8++;
                } else {
                    Params params = (Params) n.ae();
                    if (r(i)) {
                        if (!this.aA[i8]) {
                            float comMeasuredWidth = ((float) n.getComMeasuredWidth()) + (params.t * f3);
                            if (i7 == flexLine2.h - 1) {
                                comMeasuredWidth += f4;
                                f4 = 0.0f;
                            }
                            int round = Math.round(comMeasuredWidth);
                            if (round > params.z) {
                                round = params.z;
                                this.aA[i8] = true;
                                flexLine2.i -= params.t;
                                z = true;
                            } else {
                                f4 += comMeasuredWidth - ((float) round);
                                double d = (double) f4;
                                if (d > 1.0d) {
                                    round++;
                                    Double.isNaN(d);
                                    f4 = (float) (d - 1.0d);
                                } else if (d < -1.0d) {
                                    round--;
                                    Double.isNaN(d);
                                    f4 = (float) (d + 1.0d);
                                }
                            }
                            n.measureComponent(View.MeasureSpec.makeMeasureSpec(round, 1073741824), View.MeasureSpec.makeMeasureSpec(n.getComMeasuredHeight(), 1073741824));
                        }
                        flexLine2.e += n.getComMeasuredWidth() + params.d + params.f;
                        f = f3;
                    } else {
                        if (!this.aA[i8]) {
                            float comMeasuredHeight = ((float) n.getComMeasuredHeight()) + (params.t * f3);
                            if (i7 == flexLine2.h - 1) {
                                comMeasuredHeight += f4;
                                f4 = 0.0f;
                            }
                            int round2 = Math.round(comMeasuredHeight);
                            if (round2 > params.A) {
                                round2 = params.A;
                                this.aA[i8] = true;
                                flexLine2.i -= params.t;
                                f = f3;
                                z = true;
                            } else {
                                f4 += comMeasuredHeight - ((float) round2);
                                f = f3;
                                double d2 = (double) f4;
                                if (d2 > 1.0d) {
                                    round2++;
                                    Double.isNaN(d2);
                                    f2 = (float) (d2 - 1.0d);
                                } else if (d2 < -1.0d) {
                                    round2--;
                                    Double.isNaN(d2);
                                    f2 = (float) (d2 + 1.0d);
                                }
                                f4 = f2;
                            }
                            n.measureComponent(View.MeasureSpec.makeMeasureSpec(n.getComMeasuredWidth(), 1073741824), View.MeasureSpec.makeMeasureSpec(round2, 1073741824));
                        } else {
                            f = f3;
                        }
                        flexLine2.e += n.getComMeasuredHeight() + params.h + params.j;
                    }
                    i8++;
                    i7++;
                    f3 = f;
                }
            }
            int i9 = i;
            f = f3;
            i7++;
            f3 = f;
        }
        int i10 = i;
        if (z && i6 != flexLine2.e) {
            b(flexLine, i, i2, i3, i4);
        }
        return i8;
    }

    private int ao() {
        int i = Integer.MIN_VALUE;
        for (FlexLine flexLine : this.az) {
            i = Math.max(i, flexLine.e);
        }
        return i;
    }

    private boolean a(int i, int i2, int i3, int i4, Params params, int i5, int i6) {
        if (this.an == 0) {
            return false;
        }
        if (params.B) {
            return true;
        }
        if (i == 0) {
            return false;
        }
        if (r(this.am)) {
            if (l(i5, i6)) {
                i4 += this.aw;
            }
            if ((this.au & 4) > 0) {
                i4 += this.aw;
            }
        } else {
            if (l(i5, i6)) {
                i4 += this.av;
            }
            if ((this.at & 4) > 0) {
                i4 += this.av;
            }
        }
        if (i2 < i3 + i4) {
            return true;
        }
        return false;
    }

    private boolean l(int i, int i2) {
        if (m(i, i2)) {
            if (r(this.am)) {
                if ((this.au & 1) != 0) {
                    return true;
                }
                return false;
            } else if ((this.at & 1) != 0) {
                return true;
            } else {
                return false;
            }
        } else if (r(this.am)) {
            if ((this.au & 2) != 0) {
                return true;
            }
            return false;
        } else if ((this.at & 2) != 0) {
            return true;
        } else {
            return false;
        }
    }

    private boolean m(int i, int i2) {
        for (int i3 = 1; i3 <= i2; i3++) {
            ViewBase n = n(i - i3);
            if (n != null && n.J() != 2) {
                return false;
            }
        }
        return true;
    }

    /* JADX WARNING: Removed duplicated region for block: B:10:0x002b  */
    /* JADX WARNING: Removed duplicated region for block: B:11:0x002e  */
    /* JADX WARNING: Removed duplicated region for block: B:16:0x0038  */
    /* JADX WARNING: Removed duplicated region for block: B:18:? A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void c(com.tmall.wireless.vaf.virtualview.core.ViewBase r7) {
        /*
            r6 = this;
            com.tmall.wireless.vaf.virtualview.core.Layout$Params r0 = r7.ae()
            com.tmall.wireless.vaf.virtualview.layout.FlexLayout$Params r0 = (com.tmall.wireless.vaf.virtualview.layout.FlexLayout.Params) r0
            int r1 = r7.getComMeasuredWidth()
            int r2 = r7.getComMeasuredHeight()
            int r3 = r7.getComMeasuredWidth()
            int r4 = r0.x
            r5 = 1
            if (r3 >= r4) goto L_0x001b
            int r1 = r0.x
        L_0x0019:
            r3 = 1
            goto L_0x0027
        L_0x001b:
            int r3 = r7.getComMeasuredWidth()
            int r4 = r0.z
            if (r3 <= r4) goto L_0x0026
            int r1 = r0.z
            goto L_0x0019
        L_0x0026:
            r3 = 0
        L_0x0027:
            int r4 = r0.y
            if (r2 >= r4) goto L_0x002e
            int r2 = r0.y
            goto L_0x0036
        L_0x002e:
            int r4 = r0.A
            if (r2 <= r4) goto L_0x0035
            int r2 = r0.A
            goto L_0x0036
        L_0x0035:
            r5 = r3
        L_0x0036:
            if (r5 == 0) goto L_0x0045
            r0 = 1073741824(0x40000000, float:2.0)
            int r1 = android.view.View.MeasureSpec.makeMeasureSpec(r1, r0)
            int r0 = android.view.View.MeasureSpec.makeMeasureSpec(r2, r0)
            r7.measureComponent(r1, r0)
        L_0x0045:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tmall.wireless.vaf.virtualview.layout.FlexLayout.c(com.tmall.wireless.vaf.virtualview.core.ViewBase):void");
    }

    private void a(int i, int i2, FlexLine flexLine) {
        if (i == i2 - 1 && flexLine.h != 0) {
            a(flexLine);
        }
    }

    private void a(FlexLine flexLine) {
        if (r(this.am)) {
            if ((this.au & 4) > 0) {
                flexLine.e += this.aw;
                flexLine.f += this.aw;
            }
        } else if ((this.at & 4) > 0) {
            flexLine.e += this.av;
            flexLine.f += this.av;
        }
        this.az.add(flexLine);
    }

    public ViewBase n(int i) {
        if (i < 0 || i >= this.ax.length) {
            return null;
        }
        return (ViewBase) this.f9381a.get(this.ax[i]);
    }

    private int[] ap() {
        int size = this.f9381a.size();
        return a(size, s(size));
    }

    private int[] a(int i, List<Order> list) {
        Collections.sort(list);
        if (this.ay == null) {
            this.ay = new SparseIntArray(i);
        }
        this.ay.clear();
        int[] iArr = new int[i];
        int i2 = 0;
        for (Order next : list) {
            iArr[i2] = next.f9396a;
            this.ay.append(i2, next.b);
            i2++;
        }
        return iArr;
    }

    @NonNull
    private List<Order> s(int i) {
        ArrayList arrayList = new ArrayList();
        for (int i2 = 0; i2 < i; i2++) {
            Order order = new Order();
            order.b = ((Params) ((ViewBase) this.f9381a.get(i2)).ae()).s;
            order.f9396a = i2;
            arrayList.add(order);
        }
        return arrayList;
    }

    private boolean aq() {
        int size = this.f9381a.size();
        if (this.ay == null) {
            this.ay = new SparseIntArray(size);
        }
        if (this.ay.size() != size) {
            return true;
        }
        for (int i = 0; i < size; i++) {
            ViewBase viewBase = (ViewBase) this.f9381a.get(i);
            if (viewBase != null && ((Params) viewBase.ae()).s != this.ay.get(i)) {
                return true;
            }
        }
        return false;
    }

    public void onComLayout(boolean z, int i, int i2, int i3, int i4) {
        boolean z2 = true;
        switch (this.am) {
            case 0:
                a(false, i, i2, i3, i4);
                return;
            case 1:
                a(true, i, i2, i3, i4);
                return;
            case 2:
                if (this.an != 2) {
                    z2 = false;
                }
                a(z2, false, i, i2, i3, i4);
                return;
            case 3:
                if (this.an != 2) {
                    z2 = false;
                }
                a(z2, true, i, i2, i3, i4);
                return;
            default:
                throw new IllegalStateException("Invalid flex direction is set: " + this.am);
        }
    }

    private void a(boolean z, boolean z2, int i, int i2, int i3, int i4) {
        float f;
        float f2;
        float f3;
        FlexLine flexLine;
        int i5;
        int i6;
        int i7;
        Params params;
        int ab = ab();
        int ad = ad();
        int ac = ac();
        int aa = i + aa();
        int i8 = i4 - i2;
        int i9 = (i + (i3 - i)) - ac;
        int size = this.az.size();
        int i10 = 0;
        int i11 = 0;
        while (i11 < size) {
            FlexLine flexLine2 = this.az.get(i11);
            if (p(i11)) {
                aa += this.aw;
                i9 -= this.aw;
            }
            int i12 = i9;
            int i13 = aa;
            switch (this.ao) {
                case 0:
                    f2 = (float) (i2 + ab);
                    f = (float) ((i2 + i8) - ad);
                    break;
                case 1:
                    f2 = (float) (((i2 + i8) - flexLine2.e) + ad);
                    f = (float) ((i2 + flexLine2.e) - ab);
                    break;
                case 2:
                    f2 = (((float) (i8 - flexLine2.e)) / 2.0f) + ((float) (i2 + ab));
                    f = ((float) ((i2 + i8) - ad)) - (((float) (i8 - flexLine2.e)) / 2.0f);
                    break;
                case 3:
                    f2 = (float) (i2 + ab);
                    f3 = ((float) (i8 - flexLine2.e)) / (flexLine2.h != 1 ? (float) (flexLine2.h - 1) : 1.0f);
                    f = (float) ((i2 + i8) - ad);
                    break;
                case 4:
                    f3 = flexLine2.h != 0 ? ((float) (i8 - flexLine2.e)) / ((float) flexLine2.h) : 0.0f;
                    float f4 = f3 / 2.0f;
                    f2 = ((float) (i2 + ab)) + f4;
                    f = ((float) ((i2 + i8) - ad)) - f4;
                    break;
                default:
                    throw new IllegalStateException("Invalid justifyContent is set: " + this.ao);
            }
            f3 = 0.0f;
            float max = Math.max(f3, 0.0f);
            float f5 = f;
            int i14 = 0;
            int i15 = i10;
            while (i14 < flexLine2.h) {
                ViewBase n = n(i15);
                if (n != null) {
                    if (n.J() == 2) {
                        i15++;
                    } else {
                        Params params2 = (Params) n.ae();
                        float f6 = f2 + ((float) params2.h);
                        float f7 = f5 - ((float) params2.j);
                        if (l(i15, i14)) {
                            f6 += (float) this.av;
                            f7 -= (float) this.av;
                        }
                        float f8 = f7;
                        float f9 = f6;
                        if (!z) {
                            params = params2;
                            i7 = i15;
                            i6 = i14;
                            flexLine = flexLine2;
                            i5 = i11;
                            if (z2) {
                                a(n, flexLine, false, this.ap, i13, Math.round(f8) - n.getComMeasuredHeight(), i13 + n.getComMeasuredWidth(), Math.round(f8));
                            } else {
                                a(n, flexLine, false, this.ap, i13, Math.round(f9), i13 + n.getComMeasuredWidth(), Math.round(f9) + n.getComMeasuredHeight());
                            }
                        } else if (z2) {
                            params = params2;
                            i7 = i15;
                            i6 = i14;
                            flexLine = flexLine2;
                            i5 = i11;
                            a(n, flexLine2, true, this.ap, i12 - n.getComMeasuredWidth(), Math.round(f8) - n.getComMeasuredHeight(), i12, Math.round(f8));
                        } else {
                            params = params2;
                            i7 = i15;
                            i6 = i14;
                            flexLine = flexLine2;
                            i5 = i11;
                            a(n, flexLine, true, this.ap, i12 - n.getComMeasuredWidth(), Math.round(f9), i12, Math.round(f9) + n.getComMeasuredHeight());
                        }
                        float comMeasuredHeight = f9 + ((float) n.getComMeasuredHeight()) + max + ((float) params.j);
                        i15 = i7 + 1;
                        f5 = f8 - ((((float) n.getComMeasuredHeight()) + max) + ((float) params.h));
                        f2 = comMeasuredHeight;
                        i14 = i6 + 1;
                        i11 = i5;
                        flexLine2 = flexLine;
                    }
                }
                i6 = i14;
                flexLine = flexLine2;
                i5 = i11;
                i14 = i6 + 1;
                i11 = i5;
                flexLine2 = flexLine;
            }
            FlexLine flexLine3 = flexLine2;
            aa = i13 + flexLine3.g;
            i9 = i12 - flexLine3.g;
            i11++;
            i10 = i15;
        }
    }

    private void a(ViewBase viewBase, FlexLine flexLine, boolean z, int i, int i2, int i3, int i4, int i5) {
        Params params = (Params) viewBase.ae();
        if (params.v != -1) {
            i = params.v;
        }
        int i6 = flexLine.g;
        switch (i) {
            case 0:
            case 3:
            case 4:
                if (!z) {
                    viewBase.comLayout(i2 + params.d, i3, i4 + params.d, i5);
                    return;
                } else {
                    viewBase.comLayout(i2 - params.f, i3, i4 - params.f, i5);
                    return;
                }
            case 1:
                if (!z) {
                    viewBase.comLayout(((i2 + i6) - viewBase.getComMeasuredWidth()) - params.f, i3, ((i4 + i6) - viewBase.getComMeasuredWidth()) - params.f, i5);
                    return;
                } else {
                    viewBase.comLayout((i2 - i6) + viewBase.getComMeasuredWidth() + params.d, i3, (i4 - i6) + viewBase.getComMeasuredWidth() + params.d, i5);
                    return;
                }
            case 2:
                int comMeasuredWidth = (i6 - viewBase.getComMeasuredWidth()) / 2;
                if (!z) {
                    viewBase.comLayout(((i2 + comMeasuredWidth) + params.d) - params.f, i3, ((i4 + comMeasuredWidth) + params.d) - params.f, i5);
                    return;
                } else {
                    viewBase.comLayout(((i2 - comMeasuredWidth) + params.d) - params.f, i3, ((i4 - comMeasuredWidth) + params.d) - params.f, i5);
                    return;
                }
            default:
                return;
        }
    }

    private void a(boolean z, int i, int i2, int i3, int i4) {
        float f;
        float f2;
        float f3;
        FlexLine flexLine;
        int i5;
        int i6;
        int i7;
        int i8;
        Params params;
        int aa = aa();
        int ac = ac();
        int i9 = i3 - i;
        int ad = i4 - ad();
        int ab = i2 + ab();
        int size = this.az.size();
        int i10 = 0;
        int i11 = 0;
        while (i11 < size) {
            FlexLine flexLine2 = this.az.get(i11);
            if (p(i11)) {
                ad -= this.av;
                ab += this.av;
            }
            int i12 = ad;
            int i13 = ab;
            switch (this.ao) {
                case 0:
                    f2 = (float) (i + aa);
                    f = (float) (i3 - ac);
                    break;
                case 1:
                    f2 = (float) (((i + i9) - flexLine2.e) + ac);
                    f = (float) ((i + flexLine2.e) - aa);
                    break;
                case 2:
                    f2 = (((float) (i9 - flexLine2.e)) / 2.0f) + ((float) (i + aa));
                    f = ((float) ((i + i9) - ac)) - (((float) (i9 - flexLine2.e)) / 2.0f);
                    break;
                case 3:
                    f2 = (float) (i + aa);
                    f3 = ((float) (i9 - flexLine2.e)) / (flexLine2.h != 1 ? (float) (flexLine2.h - 1) : 1.0f);
                    f = (float) ((i + i9) - ac);
                    break;
                case 4:
                    f3 = flexLine2.h != 0 ? ((float) (i9 - flexLine2.e)) / ((float) flexLine2.h) : 0.0f;
                    float f4 = f3 / 2.0f;
                    f2 = ((float) (i + aa)) + f4;
                    f = ((float) ((i + i9) - ac)) - f4;
                    break;
                default:
                    throw new IllegalStateException("Invalid justifyContent is set: " + this.ao);
            }
            f3 = 0.0f;
            float max = Math.max(f3, 0.0f);
            float f5 = f2;
            float f6 = f;
            int i14 = 0;
            int i15 = i10;
            while (i14 < flexLine2.h) {
                ViewBase n = n(i15);
                if (n != null) {
                    if (n.J() == 2) {
                        i15++;
                    } else {
                        Params params2 = (Params) n.ae();
                        float f7 = f5 + ((float) params2.d);
                        float f8 = f6 - ((float) params2.f);
                        if (l(i15, i14)) {
                            f7 += (float) this.aw;
                            f8 -= (float) this.aw;
                        }
                        float f9 = f7;
                        float f10 = f8;
                        if (this.an != 2) {
                            params = params2;
                            i8 = i15;
                            i5 = i14;
                            flexLine = flexLine2;
                            i7 = i11;
                            i6 = size;
                            if (z) {
                                a(n, flexLine, this.an, this.ap, Math.round(f10) - n.getComMeasuredWidth(), i13, Math.round(f10), i13 + n.getComMeasuredHeight());
                            } else {
                                a(n, flexLine, this.an, this.ap, Math.round(f9), i13, Math.round(f9) + n.getComMeasuredWidth(), i13 + n.getComMeasuredHeight());
                            }
                        } else if (z) {
                            params = params2;
                            i8 = i15;
                            i5 = i14;
                            flexLine = flexLine2;
                            i7 = i11;
                            i6 = size;
                            a(n, flexLine2, this.an, this.ap, Math.round(f10) - n.getComMeasuredWidth(), i12 - n.getComMeasuredHeight(), Math.round(f10), i12);
                        } else {
                            params = params2;
                            i8 = i15;
                            i5 = i14;
                            flexLine = flexLine2;
                            i7 = i11;
                            i6 = size;
                            a(n, flexLine, this.an, this.ap, Math.round(f9), i12 - n.getComMeasuredHeight(), Math.round(f9) + n.getComMeasuredWidth(), i12);
                        }
                        float comMeasuredWidth = f9 + ((float) n.getComMeasuredWidth()) + max + ((float) params.f);
                        i15 = i8 + 1;
                        f6 = f10 - ((((float) n.getComMeasuredWidth()) + max) + ((float) params.d));
                        f5 = comMeasuredWidth;
                        i14 = i5 + 1;
                        i11 = i7;
                        size = i6;
                        flexLine2 = flexLine;
                    }
                }
                i5 = i14;
                flexLine = flexLine2;
                i7 = i11;
                i6 = size;
                i14 = i5 + 1;
                i11 = i7;
                size = i6;
                flexLine2 = flexLine;
            }
            FlexLine flexLine3 = flexLine2;
            int i16 = size;
            ab = i13 + flexLine3.g;
            ad = i12 - flexLine3.g;
            i11++;
            i10 = i15;
        }
    }

    private void a(ViewBase viewBase, FlexLine flexLine, int i, int i2, int i3, int i4, int i5, int i6) {
        Params params = (Params) viewBase.ae();
        if (params.v != -1) {
            i2 = params.v;
        }
        int i7 = flexLine.g;
        switch (i2) {
            case 0:
            case 4:
                if (i != 2) {
                    viewBase.comLayout(i3, i4 + params.h, i5, i6 + params.h);
                    return;
                } else {
                    viewBase.comLayout(i3, i4 - params.j, i5, i6 - params.j);
                    return;
                }
            case 1:
                if (i != 2) {
                    int i8 = i4 + i7;
                    viewBase.comLayout(i3, (i8 - viewBase.getComMeasuredHeight()) - params.j, i5, i8 - params.j);
                    return;
                }
                viewBase.comLayout(i3, (i4 - i7) + viewBase.getComMeasuredHeight() + params.h, i5, (i6 - i7) + viewBase.getComMeasuredHeight() + params.h);
                return;
            case 2:
                int comMeasuredHeight = (i7 - viewBase.getComMeasuredHeight()) / 2;
                if (i != 2) {
                    int i9 = i4 + comMeasuredHeight;
                    viewBase.comLayout(i3, (params.h + i9) - params.j, i5, ((i9 + viewBase.getComMeasuredHeight()) + params.h) - params.j);
                    return;
                }
                int i10 = i4 - comMeasuredHeight;
                viewBase.comLayout(i3, (params.h + i10) - params.j, i5, ((i10 + viewBase.getComMeasuredHeight()) + params.h) - params.j);
                return;
            case 3:
                if (i != 2) {
                    int max = Math.max(flexLine.k - viewBase.V(), params.h);
                    viewBase.comLayout(i3, i4 + max, i5, i6 + max);
                    return;
                }
                int max2 = Math.max((flexLine.k - viewBase.getComMeasuredHeight()) + viewBase.V(), params.j);
                viewBase.comLayout(i3, i4 - max2, i5, i6 - max2);
                return;
            default:
                return;
        }
    }

    /* access modifiers changed from: protected */
    public boolean c(int i, int i2) {
        boolean c = super.c(i, i2);
        if (c) {
            return c;
        }
        switch (i) {
            case StringBase.Z:
                this.ap = i2;
                return true;
            case StringBase.V:
                this.am = i2;
                return true;
            case StringBase.aa:
                this.aq = i2;
                return true;
            case StringBase.W:
                this.an = i2;
                return true;
            case StringBase.Y:
                this.ao = i2;
                return true;
            default:
                return false;
        }
    }

    public static class Params extends Layout.Params {
        private static final int C = 1;
        private static final float D = 0.0f;
        private static final float E = 1.0f;
        private static final int F = 16777215;
        public static final float l = -1.0f;
        public static final int m = -1;
        public static final int n = 0;
        public static final int o = 1;
        public static final int p = 2;
        public static final int q = 3;
        public static final int r = 4;
        public int A;
        public boolean B;
        public int s;
        public float t;
        public float u;
        public int v;
        public float w;
        public int x;
        public int y;
        public int z;

        public Params() {
            this.s = 1;
            this.t = 0.0f;
            this.u = 1.0f;
            this.v = -1;
            this.w = -1.0f;
            this.z = 16777215;
            this.A = 16777215;
            this.s = 1;
            this.t = 0.0f;
            this.u = 1.0f;
            this.v = -1;
            this.w = -1.0f;
            this.x = 0;
            this.y = 0;
            this.z = 16777215;
            this.A = 16777215;
            this.B = false;
        }

        public Params(Params params) {
            this.s = 1;
            this.t = 0.0f;
            this.u = 1.0f;
            this.v = -1;
            this.w = -1.0f;
            this.z = 16777215;
            this.A = 16777215;
            this.s = params.s;
            this.t = params.t;
            this.u = params.u;
            this.v = params.v;
            this.w = params.w;
            this.x = params.x;
            this.y = params.y;
            this.z = params.z;
            this.A = params.A;
            this.B = params.B;
        }

        public boolean c(int i, int i2) {
            boolean c = super.c(i, i2);
            if (c) {
                return c;
            }
            if (i != 1743739820) {
                return false;
            }
            this.t = (float) i2;
            return true;
        }
    }

    public static class Builder implements ViewBase.IBuilder {
        public ViewBase a(VafContext vafContext, ViewCache viewCache) {
            return new FlexLayout(vafContext, viewCache);
        }
    }

    private static class Order implements Comparable<Order> {

        /* renamed from: a  reason: collision with root package name */
        int f9396a;
        int b;

        private Order() {
        }

        /* renamed from: a */
        public int compareTo(@NonNull Order order) {
            if (this.b != order.b) {
                return this.b - order.b;
            }
            return this.f9396a - order.f9396a;
        }

        public String toString() {
            return "Order{order=" + this.b + ", index=" + this.f9396a + Operators.BLOCK_END;
        }
    }
}
