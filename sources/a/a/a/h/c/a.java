package a.a.a.h.c;

import android.support.annotation.IdRes;
import android.support.annotation.IntRange;
import android.support.v4.view.PagerAdapter;
import android.util.SparseArray;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import in.cashify.otex.R;
import in.cashify.otex.widget.CircleRoadProgress;
import in.cashify.otex.widget.DiagnoseHeaderView;
import java.util.ArrayList;
import java.util.List;

public class a extends PagerAdapter {

    /* renamed from: a  reason: collision with root package name */
    public final List<C0016a> f416a = new ArrayList();
    public View b;
    public SparseArray<DiagnoseHeaderView> c = new SparseArray<>();

    /* renamed from: a.a.a.h.c.a$a  reason: collision with other inner class name */
    public static class C0016a {
        @IntRange(from = 0, to = 3)

        /* renamed from: a  reason: collision with root package name */
        public int f417a;
        @IntRange(from = 0, to = 4)
        public int b;
        public long c;
        public CircleRoadProgress.b d;
        @IdRes
        public int e;
        @IdRes
        public int f;
        @IdRes
        public int g;
        public String h;
        public String i;

        public C0016a(@IntRange(from = 0, to = 3) int i2, int i3, int i4, String str) {
            this.f417a = i2;
            this.e = i3;
            this.f = i4;
            this.h = str;
        }

        public int a() {
            return this.f417a;
        }

        public void a(int i2) {
            this.e = i2;
        }

        public void a(long j) {
            this.c = j;
        }

        public void a(CircleRoadProgress.b bVar) {
            this.d = bVar;
        }

        public void a(String str) {
            this.i = str;
        }

        public int b() {
            return this.e;
        }

        public void b(int i2) {
            this.f417a = i2;
        }

        public int c() {
            return this.f;
        }

        public void c(int i2) {
            this.g = i2;
        }

        public int d() {
            return this.b;
        }

        public void d(int i2) {
            this.b = i2;
        }

        public long e() {
            return this.c;
        }

        public String f() {
            return this.h;
        }

        public CircleRoadProgress.b g() {
            return this.d;
        }

        public String h() {
            return this.i;
        }
    }

    public C0016a a(int i) {
        if (i < 0 || i >= this.f416a.size()) {
            return null;
        }
        return this.f416a.get(i);
    }

    public View a() {
        return this.b;
    }

    public final void a(DiagnoseHeaderView diagnoseHeaderView, C0016a aVar, ImageView imageView) {
        View findViewById = diagnoseHeaderView.findViewById(R.id.visualizer_view);
        ImageView imageView2 = (ImageView) diagnoseHeaderView.findViewById(R.id.image_shape);
        if (aVar.a() != 3) {
            if (imageView != null) {
                imageView.setVisibility(0);
            }
            if (findViewById != null) {
                findViewById.setVisibility(8);
            }
            if (imageView2 == null) {
                return;
            }
        } else if (aVar.d() == 2) {
            if (imageView != null) {
                imageView.setVisibility(0);
            }
            if (findViewById != null) {
                findViewById.setVisibility(8);
            }
            if (imageView2 == null) {
                return;
            }
        } else {
            if (imageView != null) {
                imageView.setVisibility(8);
            }
            if (findViewById != null) {
                findViewById.setVisibility(0);
            }
            if (imageView2 != null) {
                imageView2.setVisibility(0);
                return;
            }
            return;
        }
        imageView2.setVisibility(8);
    }

    public void a(List<C0016a> list) {
        this.f416a.addAll(list);
        notifyDataSetChanged();
    }

    public final void b(DiagnoseHeaderView diagnoseHeaderView, C0016a aVar, ImageView imageView) {
        View findViewById = diagnoseHeaderView.findViewById(R.id.cameraPreview);
        ImageView imageView2 = (ImageView) diagnoseHeaderView.findViewById(R.id.image_shape);
        if (aVar.a() != 3) {
            if (imageView != null) {
                imageView.setVisibility(0);
            }
            if (findViewById != null) {
                findViewById.setVisibility(8);
            }
            if (imageView2 != null) {
                imageView2.setVisibility(8);
            }
        } else if (aVar.d() == 2) {
            if (imageView != null) {
                imageView.setVisibility(0);
            }
            if (imageView2 != null) {
                imageView2.setVisibility(8);
            }
            if (findViewById != null) {
                findViewById.setVisibility(8);
            }
        } else {
            if (imageView != null) {
                imageView.setVisibility(8);
            }
            if (imageView2 != null) {
                imageView2.setVisibility(0);
            }
            if (findViewById != null) {
                findViewById.setVisibility(0);
            }
        }
    }

    public void destroyItem(ViewGroup viewGroup, int i, Object obj) {
        viewGroup.removeView((View) obj);
    }

    public int getCount() {
        return this.f416a.size();
    }

    public int getItemPosition(Object obj) {
        int indexOf = obj instanceof C0016a ? this.f416a.indexOf(obj) : -1;
        if (indexOf >= 0) {
            return indexOf;
        }
        return -2;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:111:0x0201, code lost:
        if (r15 != null) goto L_0x0197;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:25:0x006e, code lost:
        if (r1.a() == 3) goto L_0x0094;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:27:0x0080, code lost:
        if (r1.a() == 3) goto L_0x0094;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:29:0x0092, code lost:
        if (r1.a() == 3) goto L_0x0094;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:57:0x00f3, code lost:
        if (r1.a() == 3) goto L_0x0111;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:61:0x0101, code lost:
        if (r1.a() == 3) goto L_0x0111;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:65:0x010f, code lost:
        if (r1.a() == 3) goto L_0x0111;
     */
    /* JADX WARNING: Removed duplicated region for block: B:22:0x005a  */
    /* JADX WARNING: Removed duplicated region for block: B:28:0x0083  */
    /* JADX WARNING: Removed duplicated region for block: B:32:0x0098  */
    /* JADX WARNING: Removed duplicated region for block: B:51:0x00e0  */
    /* JADX WARNING: Removed duplicated region for block: B:62:0x0104  */
    @android.support.annotation.NonNull
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.lang.Object instantiateItem(android.view.ViewGroup r14, int r15) {
        /*
            r13 = this;
            r0 = 0
            if (r15 >= 0) goto L_0x000c
            java.util.List<a.a.a.h.c.a$a> r1 = r13.f416a
            int r1 = r1.size()
            if (r15 < r1) goto L_0x000c
            return r0
        L_0x000c:
            java.util.List<a.a.a.h.c.a$a> r1 = r13.f416a
            java.lang.Object r1 = r1.get(r15)
            a.a.a.h.c.a$a r1 = (a.a.a.h.c.a.C0016a) r1
            java.lang.String r2 = r1.h()
            android.util.SparseArray<in.cashify.otex.widget.DiagnoseHeaderView> r3 = r13.c
            int r3 = r3.indexOfKey(r15)
            r4 = 1641107963(0x61d151fb, float:4.826596E20)
            r5 = 108103(0x1a647, float:1.51485E-40)
            r6 = -1596016259(0xffffffffa0deb97d, float:-3.7731009E-19)
            r7 = -1
            r8 = 0
            r9 = 2
            r10 = 1
            r11 = 3
            if (r3 >= 0) goto L_0x00aa
            int r3 = r2.hashCode()
            if (r3 == r6) goto L_0x004d
            if (r3 == r5) goto L_0x0043
            if (r3 == r4) goto L_0x0039
            goto L_0x0057
        L_0x0039:
            java.lang.String r3 = "front_camera"
            boolean r3 = r2.equals(r3)
            if (r3 == 0) goto L_0x0057
            r3 = 0
            goto L_0x0058
        L_0x0043:
            java.lang.String r3 = "mic"
            boolean r3 = r2.equals(r3)
            if (r3 == 0) goto L_0x0057
            r3 = 2
            goto L_0x0058
        L_0x004d:
            java.lang.String r3 = "back_camera"
            boolean r3 = r2.equals(r3)
            if (r3 == 0) goto L_0x0057
            r3 = 1
            goto L_0x0058
        L_0x0057:
            r3 = -1
        L_0x0058:
            if (r3 == 0) goto L_0x0083
            if (r3 == r10) goto L_0x0071
            if (r3 == r9) goto L_0x005f
            goto L_0x0096
        L_0x005f:
            in.cashify.otex.widget.DiagnoseMicHeaderView r0 = new in.cashify.otex.widget.DiagnoseMicHeaderView
            android.content.Context r3 = r14.getContext()
            int r12 = in.cashify.otex.R.layout.layout_diagnose_header_mic
            r0.<init>(r3, r12)
            int r3 = r1.a()
            if (r3 != r11) goto L_0x0096
            goto L_0x0094
        L_0x0071:
            in.cashify.otex.widget.DiagnoseCameraHeaderView r0 = new in.cashify.otex.widget.DiagnoseCameraHeaderView
            android.content.Context r3 = r14.getContext()
            int r12 = in.cashify.otex.R.layout.layout_diagnose_header_camera
            r0.<init>(r3, r12)
            int r3 = r1.a()
            if (r3 != r11) goto L_0x0096
            goto L_0x0094
        L_0x0083:
            in.cashify.otex.widget.DiagnoseCameraHeaderView r0 = new in.cashify.otex.widget.DiagnoseCameraHeaderView
            android.content.Context r3 = r14.getContext()
            int r12 = in.cashify.otex.R.layout.layout_diagnose_header_camera
            r0.<init>(r3, r12)
            int r3 = r1.a()
            if (r3 != r11) goto L_0x0096
        L_0x0094:
            r13.b = r0
        L_0x0096:
            if (r0 != 0) goto L_0x00a3
            in.cashify.otex.widget.DiagnoseHeaderView r0 = new in.cashify.otex.widget.DiagnoseHeaderView
            android.content.Context r3 = r14.getContext()
            int r12 = in.cashify.otex.R.layout.layout_diagnose_header
            r0.<init>(r3, r12)
        L_0x00a3:
            android.util.SparseArray<in.cashify.otex.widget.DiagnoseHeaderView> r3 = r13.c
            r3.put(r15, r0)
            goto L_0x0113
        L_0x00aa:
            android.util.SparseArray<in.cashify.otex.widget.DiagnoseHeaderView> r3 = r13.c
            java.lang.Object r15 = r3.valueAt(r15)
            android.view.View r15 = (android.view.View) r15
            if (r15 == 0) goto L_0x0113
            int r3 = r2.hashCode()
            if (r3 == r6) goto L_0x00d3
            if (r3 == r5) goto L_0x00c9
            if (r3 == r4) goto L_0x00bf
            goto L_0x00dd
        L_0x00bf:
            java.lang.String r3 = "front_camera"
            boolean r3 = r2.equals(r3)
            if (r3 == 0) goto L_0x00dd
            r3 = 0
            goto L_0x00de
        L_0x00c9:
            java.lang.String r3 = "mic"
            boolean r3 = r2.equals(r3)
            if (r3 == 0) goto L_0x00dd
            r3 = 2
            goto L_0x00de
        L_0x00d3:
            java.lang.String r3 = "back_camera"
            boolean r3 = r2.equals(r3)
            if (r3 == 0) goto L_0x00dd
            r3 = 1
            goto L_0x00de
        L_0x00dd:
            r3 = -1
        L_0x00de:
            if (r3 == 0) goto L_0x0104
            if (r3 == r10) goto L_0x00f6
            if (r3 == r9) goto L_0x00e8
            r0 = r15
            in.cashify.otex.widget.DiagnoseHeaderView r0 = (in.cashify.otex.widget.DiagnoseHeaderView) r0
            goto L_0x0113
        L_0x00e8:
            boolean r3 = r15 instanceof in.cashify.otex.widget.DiagnoseMicHeaderView
            if (r3 == 0) goto L_0x0113
            r0 = r15
            in.cashify.otex.widget.DiagnoseMicHeaderView r0 = (in.cashify.otex.widget.DiagnoseMicHeaderView) r0
            int r15 = r1.a()
            if (r15 != r11) goto L_0x0113
            goto L_0x0111
        L_0x00f6:
            boolean r3 = r15 instanceof in.cashify.otex.widget.DiagnoseCameraHeaderView
            if (r3 == 0) goto L_0x0113
            r0 = r15
            in.cashify.otex.widget.DiagnoseCameraHeaderView r0 = (in.cashify.otex.widget.DiagnoseCameraHeaderView) r0
            int r15 = r1.a()
            if (r15 != r11) goto L_0x0113
            goto L_0x0111
        L_0x0104:
            boolean r3 = r15 instanceof in.cashify.otex.widget.DiagnoseCameraHeaderView
            if (r3 == 0) goto L_0x0113
            r0 = r15
            in.cashify.otex.widget.DiagnoseCameraHeaderView r0 = (in.cashify.otex.widget.DiagnoseCameraHeaderView) r0
            int r15 = r1.a()
            if (r15 != r11) goto L_0x0113
        L_0x0111:
            r13.b = r0
        L_0x0113:
            if (r0 != 0) goto L_0x0120
            in.cashify.otex.widget.DiagnoseHeaderView r0 = new in.cashify.otex.widget.DiagnoseHeaderView
            android.content.Context r15 = r14.getContext()
            int r3 = in.cashify.otex.R.layout.layout_diagnose_header
            r0.<init>(r15, r3)
        L_0x0120:
            int r15 = in.cashify.otex.R.id.image_diagnose_icon
            android.view.View r15 = r0.findViewById(r15)
            android.widget.ImageView r15 = (android.widget.ImageView) r15
            int r3 = in.cashify.otex.R.id.tv_test_name
            android.view.View r3 = r0.findViewById(r3)
            android.widget.TextView r3 = (android.widget.TextView) r3
            java.lang.String r12 = r1.f()
            r3.setText(r12)
            int r3 = in.cashify.otex.R.id.circleProgress
            android.view.View r3 = r0.findViewById(r3)
            in.cashify.otex.widget.CircleRoadProgress r3 = (in.cashify.otex.widget.CircleRoadProgress) r3
            int r12 = r2.hashCode()
            if (r12 == r6) goto L_0x015e
            if (r12 == r5) goto L_0x0154
            if (r12 == r4) goto L_0x014a
            goto L_0x0167
        L_0x014a:
            java.lang.String r4 = "front_camera"
            boolean r2 = r2.equals(r4)
            if (r2 == 0) goto L_0x0167
            r7 = 0
            goto L_0x0167
        L_0x0154:
            java.lang.String r4 = "mic"
            boolean r2 = r2.equals(r4)
            if (r2 == 0) goto L_0x0167
            r7 = 2
            goto L_0x0167
        L_0x015e:
            java.lang.String r4 = "back_camera"
            boolean r2 = r2.equals(r4)
            if (r2 == 0) goto L_0x0167
            r7 = 1
        L_0x0167:
            if (r7 == 0) goto L_0x0172
            if (r7 == r10) goto L_0x0172
            if (r7 == r9) goto L_0x016e
            goto L_0x0175
        L_0x016e:
            r13.a(r0, r1, r15)
            goto L_0x0175
        L_0x0172:
            r13.b(r0, r1, r15)
        L_0x0175:
            int r2 = r1.a()
            if (r2 != r9) goto L_0x019f
            android.content.Context r2 = r14.getContext()
            int r4 = in.cashify.otex.R.color.arc_fail_color
            int r2 = android.support.v4.content.ContextCompat.getColor(r2, r4)
            r3.setRoadColor(r2)
            if (r15 == 0) goto L_0x0204
            android.content.Context r2 = r14.getContext()
            int r3 = in.cashify.otex.R.color.arc_fail_color
        L_0x0190:
            int r2 = android.support.v4.content.ContextCompat.getColor(r2, r3)
            r15.setColorFilter(r2)
        L_0x0197:
            int r1 = r1.c()
            r15.setImageResource(r1)
            goto L_0x0204
        L_0x019f:
            int r2 = r1.a()
            if (r2 != r10) goto L_0x01bb
            android.content.Context r2 = r14.getContext()
            int r4 = in.cashify.otex.R.color.arc_pass_color
            int r2 = android.support.v4.content.ContextCompat.getColor(r2, r4)
            r3.setRoadColor(r2)
            if (r15 == 0) goto L_0x0204
            android.content.Context r2 = r14.getContext()
            int r3 = in.cashify.otex.R.color.arc_pass_color
            goto L_0x0190
        L_0x01bb:
            int r2 = r1.a()
            if (r2 != r11) goto L_0x0201
            if (r15 == 0) goto L_0x01ca
            int r2 = r1.b()
            r15.setImageResource(r2)
        L_0x01ca:
            int r15 = r1.d()
            switch(r15) {
                case 1: goto L_0x01f2;
                case 2: goto L_0x01d6;
                case 3: goto L_0x0204;
                case 4: goto L_0x01d2;
                default: goto L_0x01d1;
            }
        L_0x01d1:
            goto L_0x0204
        L_0x01d2:
            r3.a()
            goto L_0x0204
        L_0x01d6:
            int r15 = r1.g
            if (r15 == 0) goto L_0x01e3
            int r15 = r1.g
            r3.setArcLoadingColor(r15)
        L_0x01e3:
            long r4 = r1.e()
            in.cashify.otex.widget.CircleRoadProgress$b r15 = r1.g()
            r3.a((long) r4, (in.cashify.otex.widget.CircleRoadProgress.b) r15)
            int unused = r1.g = r8
            goto L_0x0204
        L_0x01f2:
            long r4 = r1.e()
            in.cashify.otex.widget.CircleRoadProgress$b r15 = r1.g()
            r3.b((long) r4, (in.cashify.otex.widget.CircleRoadProgress.b) r15)
            r1.d(r11)
            goto L_0x0204
        L_0x0201:
            if (r15 == 0) goto L_0x0204
            goto L_0x0197
        L_0x0204:
            r14.addView(r0)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: a.a.a.h.c.a.instantiateItem(android.view.ViewGroup, int):java.lang.Object");
    }

    public boolean isViewFromObject(View view, Object obj) {
        return view == obj;
    }
}
