package com.xiaomi.smarthome.newui.card.profile;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Rect;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Handler;
import android.os.Looper;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.Interpolator;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import com.xiaomi.account.openauth.XiaomiOAuthConstants;
import com.xiaomi.smarthome.library.common.util.DisplayUtils;
import com.xiaomi.smarthome.miotcard.R;
import com.xiaomi.smarthome.newui.card.BrightIndicateView;
import com.xiaomi.smarthome.newui.card.ProductModel;
import com.xiaomi.smarthome.newui.card.profile.TouchView;
import com.xiaomi.smarthome.newui.card.yeelight.WifiParticleSystem;
import com.xiaomi.smarthome.newui.card.yeelight.modifiers.AlphaModifier;
import com.xiaomi.smarthome.newui.card.yeelight.modifiers.ParticleModifier;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class YeelightControlView extends RelativeLayout {
    public static final int CONTROL_TYPE_COLORS = 3;
    public static final int CONTROL_TYPE_FLOW = 4;
    public static final int CONTROL_TYPE_GUARD = 5;
    public static final int CONTROL_TYPE_NAN = 0;
    public static final int CONTROL_TYPE_NL = 6;
    public static final int CONTROL_TYPE_OFF = 1;
    public static final int CONTROL_TYPE_SUNSHINE = 2;

    /* renamed from: a  reason: collision with root package name */
    private static final String f20579a = "YeelightControlView";
    private static final int b = 12000;
    private static final int c = 1000;
    private static final int d = 600;
    private static final float e = 0.3f;
    private static final float f = 0.8f;
    private static final float g = 1.0f;
    private static final float h = 1.2f;
    private static final float i = 1.4f;
    private static final float j = 1.7f;
    private static final float k = 7.0f;
    private ImageView A;
    private BrightIndicateView B;
    private boolean C = false;
    private AnimationDrawable D;
    /* access modifiers changed from: private */
    public int E = 2000;
    private Timer F;
    private TimerTask G;
    private TimerTask H;
    private WifiParticleSystem I;
    private WifiParticleSystem J;
    /* access modifiers changed from: private */
    public View K;
    /* access modifiers changed from: private */
    public View L;
    private Bitmap M;
    private WifiParticleSystem N;
    private WifiParticleSystem O;
    /* access modifiers changed from: private */
    public boolean P = false;
    /* access modifiers changed from: private */
    public TouchDirection Q;
    /* access modifiers changed from: private */
    public GestureDetector R;
    /* access modifiers changed from: private */
    public float S = 0.0f;
    /* access modifiers changed from: private */
    public float T = 0.0f;
    private float U = 0.0f;
    private float V = 0.0f;
    private float W = 0.0f;
    private float aa = 0.0f;
    private float[] ab = new float[3];
    /* access modifiers changed from: private */
    public boolean ac = false;
    /* access modifiers changed from: private */
    public int ad;
    private boolean ae;
    private int af;
    /* access modifiers changed from: private */
    public int ag;
    /* access modifiers changed from: private */
    public int ah;
    /* access modifiers changed from: private */
    public int ai;
    /* access modifiers changed from: private */
    public int aj;
    /* access modifiers changed from: private */
    public int ak;
    /* access modifiers changed from: private */
    public int[] al;
    Animation alpha_in = AnimationUtils.loadAnimation(getContext(), R.anim.control_view_alpha_in);
    Animation alpha_out = AnimationUtils.loadAnimation(getContext(), R.anim.control_view_alpha_out);
    private SharedPreferences am;
    private int an;
    private boolean ao = false;
    private boolean ap = false;
    /* access modifiers changed from: private */
    public boolean aq = false;
    private boolean ar = false;
    private Uri as;
    private View at;
    /* access modifiers changed from: private */
    public ActionListener au;
    /* access modifiers changed from: private */
    public Handler av = new Handler(Looper.getMainLooper()) {
    };
    /* access modifiers changed from: private */
    public Timer aw;
    /* access modifiers changed from: private */
    public SendCommandTimerTask ax;
    /* access modifiers changed from: private */
    public Runnable ay = new Runnable() {
        public void run() {
            YeelightControlView.this.m();
            TouchDirection unused = YeelightControlView.this.Q = TouchDirection.NAN;
        }
    };
    private Activity az;
    Animation bottom_in = AnimationUtils.loadAnimation(getContext(), R.anim.control_view_in_from_bottom);
    Animation bottom_out = AnimationUtils.loadAnimation(getContext(), R.anim.control_view_out_from_bottom);
    private int l;
    private int m;
    protected int mHeight;
    protected int mWidth;
    /* access modifiers changed from: private */
    public ImageView n;
    private int o = 0;
    /* access modifiers changed from: private */
    public ProductModel p;
    private FrameLayout q;
    private RelativeLayout r;
    private Button s;
    private FrameLayout t;
    Animation top_in = AnimationUtils.loadAnimation(getContext(), R.anim.control_view_in_from_top);
    Animation top_out = AnimationUtils.loadAnimation(getContext(), R.anim.control_view_out_from_top);
    /* access modifiers changed from: private */
    public TouchView u;
    private LinearLayout v;
    private LinearLayout w;
    /* access modifiers changed from: private */
    public ImageView x;
    private ImageView y;
    private SeekBar z;

    public interface ActionListener {
        public static final int b = 0;
        public static final int c = 1;

        void a();

        void a(int i);

        void a(int i, int[] iArr, int i2);

        void a(boolean z);

        void a(boolean z, int i);

        void b();

        void b(int i);

        void c();

        void c(int i);

        void d();

        void d(int i);

        void e();

        void e(int i);

        void f();

        void f(int i);

        void g();

        void g(int i);

        void h();

        void i();
    }

    public enum TouchDirection {
        NAN,
        HORIZONTAL,
        VERTICAL
    }

    private float a(float f2, float f3, float f4, float f5, float f6) {
        return f2 + (((f3 - f2) * (f4 - f5)) / (f6 - f5));
    }

    public YeelightControlView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        initView(context, attributeSet);
    }

    public YeelightControlView(Context context) {
        super(context);
        initView(context, (AttributeSet) null);
    }

    public void setProductModel(ProductModel productModel) {
        setProductModel(productModel, productModel.c(), productModel.d());
    }

    public void setProductModel(ProductModel productModel, int i2, int i3) {
        this.p = productModel;
        this.l = i2;
        this.m = i3;
        if (productModel.b() && !this.ar) {
            RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) this.w.getLayoutParams();
            layoutParams.topMargin = DisplayUtils.a(80.0f);
            layoutParams.leftMargin = DisplayUtils.a(47.0f);
            layoutParams.rightMargin = DisplayUtils.a(47.0f);
            this.w.setLayoutParams(layoutParams);
        }
        this.B.setWhiteType();
    }

    public synchronized LightState getLightState() {
        LightState lightState;
        lightState = null;
        if (this.ad != 0) {
            lightState = new LightState(this.ad == 1 ? "off" : "on", this.af, getBright(), this.aj, this.ak, this.ae);
            if (this.ad == 6) {
                lightState.a(true);
                lightState.d(2700);
            }
        }
        return lightState;
    }

    public void updateView(LightState lightState) {
        this.ag = lightState.m();
        if (!lightState.k()) {
            if (lightState.e()) {
                this.ad = 6;
                this.ai = lightState.n();
            } else {
                this.ad = 2;
                this.aj = lightState.o();
            }
        }
        setModel(this.ad);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:12:0x001a, code lost:
        if (r12.equals(r0) == false) goto L_0x001c;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:74:0x01b8, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized void initState(com.xiaomi.smarthome.newui.card.profile.LightState r12, boolean r13) {
        /*
            r11 = this;
            monitor-enter(r11)
            com.xiaomi.smarthome.newui.card.profile.LightState r0 = r11.getLightState()     // Catch:{ all -> 0x01b9 }
            int r1 = r11.ad     // Catch:{ all -> 0x01b9 }
            r2 = 1
            if (r1 != r2) goto L_0x0012
            boolean r1 = r12.k()     // Catch:{ all -> 0x01b9 }
            if (r1 == 0) goto L_0x0012
            monitor-exit(r11)
            return
        L_0x0012:
            if (r0 == 0) goto L_0x001c
            if (r12 == 0) goto L_0x01b7
            boolean r0 = r12.equals(r0)     // Catch:{ all -> 0x01b9 }
            if (r0 != 0) goto L_0x01b7
        L_0x001c:
            int r0 = r12.l()     // Catch:{ all -> 0x01b9 }
            r11.af = r0     // Catch:{ all -> 0x01b9 }
            boolean r0 = r12.q()     // Catch:{ all -> 0x01b9 }
            r11.ae = r0     // Catch:{ all -> 0x01b9 }
            int r0 = r12.m()     // Catch:{ all -> 0x01b9 }
            r11.ag = r0     // Catch:{ all -> 0x01b9 }
            int r0 = r12.n()     // Catch:{ all -> 0x01b9 }
            if (r0 != 0) goto L_0x0037
            int r0 = r11.ai     // Catch:{ all -> 0x01b9 }
            goto L_0x003b
        L_0x0037:
            int r0 = r12.n()     // Catch:{ all -> 0x01b9 }
        L_0x003b:
            r11.ai = r0     // Catch:{ all -> 0x01b9 }
            int r0 = r11.ag     // Catch:{ all -> 0x01b9 }
            r11.ah = r0     // Catch:{ all -> 0x01b9 }
            int r0 = r12.o()     // Catch:{ all -> 0x01b9 }
            r11.aj = r0     // Catch:{ all -> 0x01b9 }
            int r0 = r12.p()     // Catch:{ all -> 0x01b9 }
            r11.ak = r0     // Catch:{ all -> 0x01b9 }
            com.xiaomi.smarthome.newui.card.ProductModel r0 = r11.p     // Catch:{ all -> 0x01b9 }
            com.xiaomi.smarthome.newui.card.ProductModel$CapabilityType r1 = com.xiaomi.smarthome.newui.card.ProductModel.CapabilityType.CONTROL_CT_H     // Catch:{ all -> 0x01b9 }
            boolean r0 = r0.a((com.xiaomi.smarthome.newui.card.ProductModel.CapabilityType) r1)     // Catch:{ all -> 0x01b9 }
            if (r0 != 0) goto L_0x005b
            r0 = 4000(0xfa0, float:5.605E-42)
            r11.aj = r0     // Catch:{ all -> 0x01b9 }
        L_0x005b:
            com.xiaomi.smarthome.newui.card.ProductModel r0 = r11.p     // Catch:{ all -> 0x01b9 }
            com.xiaomi.smarthome.newui.card.ProductModel$CapabilityType r1 = com.xiaomi.smarthome.newui.card.ProductModel.CapabilityType.BTN_COLOR     // Catch:{ all -> 0x01b9 }
            boolean r0 = r0.a((com.xiaomi.smarthome.newui.card.ProductModel.CapabilityType) r1)     // Catch:{ all -> 0x01b9 }
            r1 = 2
            if (r0 != 0) goto L_0x0068
            r11.af = r1     // Catch:{ all -> 0x01b9 }
        L_0x0068:
            com.xiaomi.smarthome.newui.card.ProductModel r0 = r11.p     // Catch:{ all -> 0x01b9 }
            com.xiaomi.smarthome.newui.card.ProductModel$CapabilityType r3 = com.xiaomi.smarthome.newui.card.ProductModel.CapabilityType.BTN_FLOW     // Catch:{ all -> 0x01b9 }
            boolean r0 = r0.a((com.xiaomi.smarthome.newui.card.ProductModel.CapabilityType) r3)     // Catch:{ all -> 0x01b9 }
            r3 = 0
            if (r0 != 0) goto L_0x0075
            r11.ae = r3     // Catch:{ all -> 0x01b9 }
        L_0x0075:
            com.xiaomi.smarthome.newui.card.AdvancedFlow r0 = r12.s()     // Catch:{ all -> 0x01b9 }
            if (r0 == 0) goto L_0x00e1
            com.xiaomi.smarthome.newui.card.AdvancedFlow r0 = r12.s()     // Catch:{ all -> 0x01b9 }
            int[] r0 = r0.g()     // Catch:{ all -> 0x01b9 }
            r11.al = r0     // Catch:{ all -> 0x01b9 }
            com.xiaomi.smarthome.newui.card.AdvancedFlow r0 = r12.s()     // Catch:{ all -> 0x01b9 }
            java.util.List r0 = r0.c()     // Catch:{ all -> 0x01b9 }
            if (r0 == 0) goto L_0x00b1
            com.xiaomi.smarthome.newui.card.AdvancedFlow r0 = r12.s()     // Catch:{ all -> 0x01b9 }
            java.util.List r0 = r0.c()     // Catch:{ all -> 0x01b9 }
            boolean r0 = r0.isEmpty()     // Catch:{ all -> 0x01b9 }
            if (r0 == 0) goto L_0x009e
            goto L_0x00b1
        L_0x009e:
            com.xiaomi.smarthome.newui.card.AdvancedFlow r0 = r12.s()     // Catch:{ all -> 0x01b9 }
            java.util.List r0 = r0.c()     // Catch:{ all -> 0x01b9 }
            java.lang.Object r0 = r0.get(r3)     // Catch:{ all -> 0x01b9 }
            com.xiaomi.smarthome.newui.card.FlowItem r0 = (com.xiaomi.smarthome.newui.card.FlowItem) r0     // Catch:{ all -> 0x01b9 }
            int r0 = r0.d()     // Catch:{ all -> 0x01b9 }
            goto L_0x00b3
        L_0x00b1:
            int r0 = r11.ag     // Catch:{ all -> 0x01b9 }
        L_0x00b3:
            r11.ah = r0     // Catch:{ all -> 0x01b9 }
            com.xiaomi.smarthome.newui.card.AdvancedFlow r0 = r12.s()     // Catch:{ all -> 0x01b9 }
            java.util.List r0 = r0.c()     // Catch:{ all -> 0x01b9 }
            if (r0 == 0) goto L_0x00e1
            com.xiaomi.smarthome.newui.card.AdvancedFlow r0 = r12.s()     // Catch:{ all -> 0x01b9 }
            java.util.List r0 = r0.c()     // Catch:{ all -> 0x01b9 }
            boolean r0 = r0.isEmpty()     // Catch:{ all -> 0x01b9 }
            if (r0 != 0) goto L_0x00e1
            com.xiaomi.smarthome.newui.card.AdvancedFlow r0 = r12.s()     // Catch:{ all -> 0x01b9 }
            java.util.List r0 = r0.c()     // Catch:{ all -> 0x01b9 }
            java.lang.Object r0 = r0.get(r3)     // Catch:{ all -> 0x01b9 }
            com.xiaomi.smarthome.newui.card.FlowItem r0 = (com.xiaomi.smarthome.newui.card.FlowItem) r0     // Catch:{ all -> 0x01b9 }
            int r0 = r0.a()     // Catch:{ all -> 0x01b9 }
            r11.E = r0     // Catch:{ all -> 0x01b9 }
        L_0x00e1:
            int[] r0 = r11.al     // Catch:{ all -> 0x01b9 }
            r4 = 4
            r5 = 3
            if (r0 != 0) goto L_0x0131
            int[] r0 = new int[r4]     // Catch:{ all -> 0x01b9 }
            r11.al = r0     // Catch:{ all -> 0x01b9 }
            int r0 = r11.ag     // Catch:{ all -> 0x01b9 }
            float r0 = com.xiaomi.smarthome.newui.card.profile.YelightColorUtils.b(r0)     // Catch:{ all -> 0x01b9 }
            float[] r6 = new float[r5]     // Catch:{ all -> 0x01b9 }
            r7 = 1092616192(0x41200000, float:10.0)
            r6[r3] = r7     // Catch:{ all -> 0x01b9 }
            r8 = 1065353216(0x3f800000, float:1.0)
            r6[r2] = r8     // Catch:{ all -> 0x01b9 }
            r6[r1] = r0     // Catch:{ all -> 0x01b9 }
            int[] r9 = r11.al     // Catch:{ all -> 0x01b9 }
            float[] r10 = new float[r5]     // Catch:{ all -> 0x01b9 }
            r10[r3] = r7     // Catch:{ all -> 0x01b9 }
            r10[r2] = r8     // Catch:{ all -> 0x01b9 }
            r10[r1] = r0     // Catch:{ all -> 0x01b9 }
            int r0 = android.graphics.Color.HSVToColor(r10)     // Catch:{ all -> 0x01b9 }
            r9[r3] = r0     // Catch:{ all -> 0x01b9 }
            r0 = 1116471296(0x428c0000, float:70.0)
            r6[r3] = r0     // Catch:{ all -> 0x01b9 }
            int[] r0 = r11.al     // Catch:{ all -> 0x01b9 }
            int r7 = android.graphics.Color.HSVToColor(r6)     // Catch:{ all -> 0x01b9 }
            r0[r2] = r7     // Catch:{ all -> 0x01b9 }
            r0 = 1126170624(0x43200000, float:160.0)
            r6[r3] = r0     // Catch:{ all -> 0x01b9 }
            int[] r0 = r11.al     // Catch:{ all -> 0x01b9 }
            int r7 = android.graphics.Color.HSVToColor(r6)     // Catch:{ all -> 0x01b9 }
            r0[r1] = r7     // Catch:{ all -> 0x01b9 }
            r0 = 1132920832(0x43870000, float:270.0)
            r6[r3] = r0     // Catch:{ all -> 0x01b9 }
            int[] r0 = r11.al     // Catch:{ all -> 0x01b9 }
            int r3 = android.graphics.Color.HSVToColor(r6)     // Catch:{ all -> 0x01b9 }
            r0[r5] = r3     // Catch:{ all -> 0x01b9 }
        L_0x0131:
            boolean r0 = r12.k()     // Catch:{ all -> 0x01b9 }
            if (r0 == 0) goto L_0x013a
            r11.ad = r2     // Catch:{ all -> 0x01b9 }
            goto L_0x016d
        L_0x013a:
            boolean r12 = r12.e()     // Catch:{ all -> 0x01b9 }
            if (r12 == 0) goto L_0x014b
            r12 = 6
            r11.ad = r12     // Catch:{ all -> 0x01b9 }
            com.xiaomi.smarthome.newui.card.BrightIndicateView r12 = r11.B     // Catch:{ all -> 0x01b9 }
            int r0 = r11.ai     // Catch:{ all -> 0x01b9 }
            r12.setValue(r0)     // Catch:{ all -> 0x01b9 }
            goto L_0x016d
        L_0x014b:
            boolean r12 = r11.ae     // Catch:{ all -> 0x01b9 }
            if (r12 == 0) goto L_0x0159
            r11.ad = r4     // Catch:{ all -> 0x01b9 }
            com.xiaomi.smarthome.newui.card.BrightIndicateView r12 = r11.B     // Catch:{ all -> 0x01b9 }
            int r0 = r11.ah     // Catch:{ all -> 0x01b9 }
            r12.setValue(r0)     // Catch:{ all -> 0x01b9 }
            goto L_0x016d
        L_0x0159:
            int r12 = r11.af     // Catch:{ all -> 0x01b9 }
            if (r12 != r2) goto L_0x0160
            r11.ad = r5     // Catch:{ all -> 0x01b9 }
            goto L_0x0166
        L_0x0160:
            int r12 = r11.af     // Catch:{ all -> 0x01b9 }
            if (r12 != r1) goto L_0x0166
            r11.ad = r1     // Catch:{ all -> 0x01b9 }
        L_0x0166:
            com.xiaomi.smarthome.newui.card.BrightIndicateView r12 = r11.B     // Catch:{ all -> 0x01b9 }
            int r0 = r11.ag     // Catch:{ all -> 0x01b9 }
            r12.setValue(r0)     // Catch:{ all -> 0x01b9 }
        L_0x016d:
            if (r13 == 0) goto L_0x01b2
            com.xiaomi.smarthome.newui.card.profile.YeelightControlView$ActionListener r12 = r11.au     // Catch:{ all -> 0x01b9 }
            if (r12 == 0) goto L_0x01b2
            int r12 = r11.ad     // Catch:{ all -> 0x01b9 }
            if (r12 != r2) goto L_0x017d
            com.xiaomi.smarthome.newui.card.profile.YeelightControlView$ActionListener r12 = r11.au     // Catch:{ all -> 0x01b9 }
            r12.d()     // Catch:{ all -> 0x01b9 }
            goto L_0x01b2
        L_0x017d:
            int r12 = r11.ad     // Catch:{ all -> 0x01b9 }
            if (r12 != r1) goto L_0x0195
            com.xiaomi.smarthome.newui.card.profile.YeelightControlView$ActionListener r12 = r11.au     // Catch:{ all -> 0x01b9 }
            r12.c()     // Catch:{ all -> 0x01b9 }
            com.xiaomi.smarthome.newui.card.profile.YeelightControlView$ActionListener r12 = r11.au     // Catch:{ all -> 0x01b9 }
            int r13 = r11.ag     // Catch:{ all -> 0x01b9 }
            r12.a((int) r13)     // Catch:{ all -> 0x01b9 }
            com.xiaomi.smarthome.newui.card.profile.YeelightControlView$ActionListener r12 = r11.au     // Catch:{ all -> 0x01b9 }
            int r13 = r11.aj     // Catch:{ all -> 0x01b9 }
            r12.b(r13)     // Catch:{ all -> 0x01b9 }
            goto L_0x01b2
        L_0x0195:
            int r12 = r11.ad     // Catch:{ all -> 0x01b9 }
            if (r12 != r5) goto L_0x01ad
            com.xiaomi.smarthome.newui.card.profile.YeelightControlView$ActionListener r12 = r11.au     // Catch:{ all -> 0x01b9 }
            r12.c()     // Catch:{ all -> 0x01b9 }
            com.xiaomi.smarthome.newui.card.profile.YeelightControlView$ActionListener r12 = r11.au     // Catch:{ all -> 0x01b9 }
            int r13 = r11.ag     // Catch:{ all -> 0x01b9 }
            r12.a((int) r13)     // Catch:{ all -> 0x01b9 }
            com.xiaomi.smarthome.newui.card.profile.YeelightControlView$ActionListener r12 = r11.au     // Catch:{ all -> 0x01b9 }
            int r13 = r11.ak     // Catch:{ all -> 0x01b9 }
            r12.c(r13)     // Catch:{ all -> 0x01b9 }
            goto L_0x01b2
        L_0x01ad:
            com.xiaomi.smarthome.newui.card.profile.YeelightControlView$ActionListener r12 = r11.au     // Catch:{ all -> 0x01b9 }
            r12.c()     // Catch:{ all -> 0x01b9 }
        L_0x01b2:
            int r12 = r11.ad     // Catch:{ all -> 0x01b9 }
            r11.setModel(r12)     // Catch:{ all -> 0x01b9 }
        L_0x01b7:
            monitor-exit(r11)
            return
        L_0x01b9:
            r12 = move-exception
            monitor-exit(r11)
            throw r12
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.smarthome.newui.card.profile.YeelightControlView.initState(com.xiaomi.smarthome.newui.card.profile.LightState, boolean):void");
    }

    public void setColorsFlow(int[] iArr) {
        if (iArr == null) {
            return;
        }
        if (this.ad == 4 || this.ad == 0) {
            this.ad = 4;
            if (this.au != null) {
                this.au.a(this.E, iArr != null ? iArr : this.al, this.ah);
            }
            a(iArr, this.ah);
        }
    }

    public void addActionListener(ActionListener actionListener) {
        this.au = actionListener;
    }

    public void onResume() {
        setModel(this.ad);
    }

    public void onPause() {
        onPause(false);
    }

    public void onPause(boolean z2) {
        g();
        e();
        j();
        k();
        n();
        if (z2) {
            this.ad = 0;
        }
    }

    public void setTitleLayout(FrameLayout frameLayout) {
        this.q = frameLayout;
    }

    public void setTopButtonHeight(int i2) {
        int i3;
        if (i2 == 0) {
            i3 = DisplayUtils.a(70.0f);
        } else {
            i3 = i2 + DisplayUtils.a(90.0f);
        }
        ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) this.v.getLayoutParams();
        marginLayoutParams.topMargin = i3;
        this.v.setLayoutParams(marginLayoutParams);
        ViewGroup.MarginLayoutParams marginLayoutParams2 = (ViewGroup.MarginLayoutParams) this.w.getLayoutParams();
        marginLayoutParams2.topMargin = i3;
        this.w.setLayoutParams(marginLayoutParams2);
    }

    /* access modifiers changed from: protected */
    public void onMeasure(int i2, int i3) {
        this.mWidth = View.MeasureSpec.getSize(i2);
        this.mHeight = View.MeasureSpec.getSize(i3);
        l();
        super.onMeasure(i2, i3);
    }

    /* access modifiers changed from: protected */
    public void initView(Context context, AttributeSet attributeSet) {
        LayoutInflater.from(context).inflate(R.layout.widget_control_view, this, true);
        this.ad = 0;
        this.r = (RelativeLayout) findViewById(R.id.control_root);
        this.s = (Button) findViewById(R.id.control_button_top);
        this.t = (FrameLayout) findViewById(R.id.control_button_container);
        this.u = (TouchView) findViewById(R.id.control_view);
        this.v = (LinearLayout) findViewById(R.id.control_top_flow);
        this.w = (LinearLayout) findViewById(R.id.control_top_colorsunshine);
        this.x = (ImageView) findViewById(R.id.control_colorpicker);
        this.y = (ImageView) findViewById(R.id.control_colorpointer);
        this.z = (SeekBar) findViewById(R.id.control_top_flow_seekbar);
        this.A = (ImageView) findViewById(R.id.control_top_flow_pointer);
        this.B = (BrightIndicateView) findViewById(R.id.control_bright_indicate);
        this.K = new View(context);
        this.L = new View(context);
        this.s.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if (YeelightControlView.this.au != null && YeelightControlView.this.ad == 4) {
                    YeelightControlView.this.au.e();
                }
            }
        });
        this.Q = TouchDirection.NAN;
        this.R = new GestureDetector(context, new MyOnGestureListener());
        this.R.setIsLongpressEnabled(false);
        this.u.setOnTouchListener(new MyOnTouchListener());
    }

    private void setModel(int i2) {
        switch (i2) {
            case 1:
                if (this.au != null) {
                    this.au.a(true, 0);
                    this.B.setWhiteType();
                }
                a();
                if (this.P) {
                    this.P = false;
                    m();
                    j();
                    k();
                    return;
                }
                return;
            case 2:
                if (this.au != null) {
                    this.au.a(false, 1);
                    if (!this.p.a().equals(ProductModel.n) || !this.p.a().equals(ProductModel.e)) {
                        this.B.setBlackType();
                    } else {
                        this.B.setWhiteType();
                    }
                }
                setSunshineMode(false);
                return;
            case 3:
                if (this.au != null) {
                    this.au.a(false, 0);
                    this.B.setWhiteType();
                }
                c();
                return;
            case 4:
                if (this.au != null) {
                    this.au.a(false, 0);
                    this.B.setWhiteType();
                }
                d();
                return;
            case 5:
                if (this.au != null) {
                    this.au.a(false, 1);
                    this.B.setBlackType();
                }
                setSunshineMode(true);
                return;
            case 6:
                if (this.au != null) {
                    this.au.a(false, 1);
                    if (!this.p.a().equals(ProductModel.n)) {
                        this.B.setBlackType();
                    } else {
                        this.B.setWhiteType();
                    }
                }
                b();
                return;
            default:
                return;
        }
    }

    private void a() {
        e();
        a(false);
        this.u.setCurrentMode(TouchView.ControlMode.SWITCH);
        Drawable offMask = getOffMask();
        if (offMask == null) {
            this.r.setBackgroundDrawable(new ColorDrawable(-16777216));
            this.u.setCurrentMode(TouchView.ControlMode.SUNSHINE);
        } else {
            f();
            this.r.setBackgroundDrawable(offMask);
        }
        this.ad = 1;
    }

    private void setSunshineMode(boolean z2) {
        g();
        e();
        a(false);
        this.u.setCurrentMode(TouchView.ControlMode.SUNSHINE);
        this.r.setBackgroundDrawable((Drawable) null);
        this.ab[0] = 35.0f;
        this.ab[1] = YelightColorUtils.a(this.aj, this.l, this.m);
        this.ab[2] = YelightColorUtils.b(this.ag);
        this.r.setBackgroundColor(Color.HSVToColor(this.ab));
        this.ad = z2 ? 5 : 2;
        this.af = 2;
        this.ae = false;
        this.B.setValue(this.ag);
    }

    private void b() {
        g();
        e();
        a(false);
        this.u.setCurrentMode(TouchView.ControlMode.NIGHTLIGHT);
        this.r.setBackgroundDrawable((Drawable) null);
        this.ab[0] = 35.0f;
        this.ab[1] = YelightColorUtils.a(2700, this.l, this.m);
        this.ab[2] = YelightColorUtils.b(this.ai);
        this.r.setBackgroundColor(Color.HSVToColor(this.ab));
        this.ad = 6;
        this.ae = false;
        this.B.setValue(this.ai);
        if (this.aq) {
            this.n.setVisibility(0);
        } else {
            this.n.setVisibility(8);
        }
    }

    private void c() {
        g();
        e();
        a(false);
        this.u.setCurrentMode(TouchView.ControlMode.COLOR);
        this.r.setBackgroundDrawable((Drawable) null);
        Color.colorToHSV(this.ak, this.ab);
        this.ab[2] = YelightColorUtils.b(this.ag);
        this.r.setBackgroundColor(Color.HSVToColor(this.ab));
        this.u.startAnimation(AnimationUtils.loadAnimation(getContext(), R.anim.bg_fade_in));
        this.ad = 3;
        this.af = 1;
        this.ae = false;
        this.B.setValue(this.ag);
    }

    private void d() {
        g();
        a(true);
        this.u.setCurrentMode(TouchView.ControlMode.COLORFLOW);
        this.r.setBackgroundDrawable((Drawable) null);
        this.u.startAnimation(AnimationUtils.loadAnimation(getContext(), R.anim.bg_fade_in));
        this.ad = 4;
        this.ab[2] = YelightColorUtils.b(this.ah);
        this.ae = true;
        a(this.al, this.ah);
        this.B.setValue(this.ah);
        a(this.E);
    }

    /* access modifiers changed from: private */
    public void a(int[] iArr, int i2) {
        if (iArr != null) {
            int[] iArr2 = new int[iArr.length];
            for (int i3 = 0; i3 < iArr.length; i3++) {
                float[] fArr = new float[3];
                Color.colorToHSV(iArr[i3], fArr);
                fArr[2] = YelightColorUtils.b(this.ah);
                iArr2[i3] = Color.HSVToColor(fArr);
            }
            if (this.D != null) {
                this.D.stop();
            }
            this.D = new AnimationDrawable();
            for (int i4 = 0; i4 < iArr.length; i4++) {
                this.D.addFrame(new ColorDrawable(iArr2[i4]), this.E);
            }
            this.r.setBackgroundDrawable(this.D);
            this.D.setEnterFadeDuration(this.E / 2);
            this.D.setExitFadeDuration(this.E / 2);
            this.D.setOneShot(false);
            this.D.start();
            this.al = iArr;
        }
    }

    private void e() {
        if (this.D != null) {
            this.D.stop();
            this.r.setBackgroundDrawable((Drawable) null);
            this.D = null;
        }
    }

    private void f() {
        g();
        this.r.addView(this.K);
        this.r.addView(this.L);
        this.F = new Timer();
        this.H = new TimerTask() {
            public void run() {
                ((Activity) YeelightControlView.this.getContext()).runOnUiThread(new Runnable() {
                    public void run() {
                        Random random = new Random();
                        float nextFloat = ((float) (YeelightControlView.this.mWidth / 8)) + (((float) ((YeelightControlView.this.mWidth * 6) / 8)) * random.nextFloat());
                        float nextFloat2 = ((float) (YeelightControlView.this.mHeight / 5)) + (((float) ((YeelightControlView.this.mHeight * 3) / 5)) * random.nextFloat());
                        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(YeelightControlView.this.mWidth / 80, YeelightControlView.this.mHeight / 80);
                        layoutParams.setMargins((int) nextFloat, (int) nextFloat2, 0, 0);
                        YeelightControlView.this.K.setLayoutParams(layoutParams);
                        YeelightControlView.this.a(YeelightControlView.this.K);
                    }
                });
            }
        };
        this.F.schedule(this.H, 0, 2000);
        this.G = new TimerTask() {
            public void run() {
                ((Activity) YeelightControlView.this.getContext()).runOnUiThread(new Runnable() {
                    public void run() {
                        Random random = new Random();
                        float nextFloat = ((float) (YeelightControlView.this.mWidth / 2)) + (((float) ((YeelightControlView.this.mWidth * 1) / 3)) * random.nextFloat());
                        float nextFloat2 = ((float) (YeelightControlView.this.mHeight / 6)) + (((float) ((YeelightControlView.this.mHeight * 1) / 2)) * random.nextFloat());
                        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(YeelightControlView.this.mWidth / 80, YeelightControlView.this.mHeight / 80);
                        layoutParams.setMargins((int) nextFloat, (int) nextFloat2, 0, 0);
                        YeelightControlView.this.L.setLayoutParams(layoutParams);
                    }
                });
            }
        };
        this.F.schedule(this.G, 2000, 6000);
    }

    private void g() {
        if (this.G != null) {
            this.G.cancel();
            this.G = null;
        }
        if (this.H != null) {
            this.H.cancel();
            this.H = null;
        }
        if (this.F != null) {
            this.F.cancel();
            this.F = null;
        }
        h();
        i();
        this.r.removeView(this.K);
        this.r.removeView(this.L);
    }

    /* access modifiers changed from: private */
    public void a(View view) {
        if (this.I != null) {
            this.I.b();
            this.I = null;
        }
        this.I = new WifiParticleSystem((Activity) getContext(), 80, R.drawable.night_star, 600);
        this.I.b((float) h, (float) h);
        this.I.a(0.0f, 0.0f);
        this.I.c(0.0f, 0.0f);
        this.I.a(300, (Interpolator) new AccelerateDecelerateInterpolator());
        this.I.b(view, 1);
    }

    private void h() {
        if (this.I != null) {
            this.I.b();
            this.I = null;
        }
    }

    private void b(View view) {
        if (this.J != null) {
            this.J.b();
            this.J = null;
        }
        this.J = new WifiParticleSystem((Activity) getContext(), 80, R.drawable.night_meteor, 800, R.id.main_root);
        this.J.b(1.6f, 2.0f);
        this.J.c(0.0f, 0.0f);
        this.J.a(400, (Interpolator) new AccelerateDecelerateInterpolator());
        this.J.a(0.2f, 0.4f, 145, 145);
        this.J.b(view, 1);
    }

    private void i() {
        if (this.J != null) {
            this.J.b();
            this.J = null;
        }
    }

    private void a(MotionEvent motionEvent) {
        if (this.N != null) {
            this.N.a();
            this.N.b();
            this.N = null;
        }
        if (getContext() instanceof Activity) {
            Activity activity = (Activity) getContext();
            if (!activity.isFinishing()) {
                this.N = new WifiParticleSystem(activity, 100, R.drawable.particle_dark, 650, R.id.partical_content);
                this.N.b(2);
                this.N.b((float) e, 0.8f);
                this.N.a(1.0f, (float) h);
                this.N.c(40.0f, 180.0f);
                this.N.b(100, 100);
                this.N.a((int) motionEvent.getRawX(), (int) motionEvent.getRawY(), 20);
            }
        }
    }

    private void b(MotionEvent motionEvent) {
        if (this.O != null) {
            this.O.a();
            this.O.b();
            this.O = null;
        }
        this.O = new WifiParticleSystem((Activity) getContext(), 100, R.drawable.particle_dark_light, 120, R.id.partical_content);
        this.O.b(1);
        this.O.b((float) i, (float) j);
        this.O.b(255, 180);
        this.O.a((ParticleModifier) new AlphaModifier(255, 100, 20, 100));
        this.O.a((int) motionEvent.getRawX(), (int) motionEvent.getRawY(), 70);
    }

    /* access modifiers changed from: private */
    public void c(MotionEvent motionEvent) {
        if (this.N != null) {
            float y2 = motionEvent.getY() / ((float) this.mHeight);
            float f2 = e - ((y2 * y2) * k);
            this.N.b(f2, 0.8f);
            Random random = new Random();
            if (random.nextFloat() > a(0.1f, 1.0f, motionEvent.getY(), (float) this.mHeight, 300.0f)) {
                this.N.b(f2, 0.8f);
            } else {
                this.N.b(0.0f, 0.0f);
            }
            this.N.c((int) motionEvent.getRawX(), (int) motionEvent.getRawY());
        }
    }

    /* access modifiers changed from: private */
    public void d(MotionEvent motionEvent) {
        if (this.O != null) {
            float y2 = motionEvent.getY() / ((float) this.mHeight);
            this.O.b(i - ((y2 * y2) * k), (float) j);
            this.O.c((int) motionEvent.getRawX(), (int) motionEvent.getRawY());
        }
    }

    /* access modifiers changed from: private */
    public void j() {
        if (this.N != null) {
            this.N.a();
            this.N.b();
            this.N = null;
        }
    }

    /* access modifiers changed from: private */
    public void k() {
        if (this.O != null) {
            this.O.a();
            this.O.b();
            this.O = null;
        }
    }

    private void l() {
        this.U = 360.0f / ((float) this.mWidth);
        this.V = (0.4f / ((float) this.mWidth)) * 2.0f;
        this.W = 0.45f / ((float) this.mHeight);
        this.aa = (float) (XiaomiOAuthConstants.SCOPE_MI_CLOUD_GALLERY / (this.z.getWidth() == 0 ? 1080 : this.z.getWidth()));
    }

    /* access modifiers changed from: private */
    public void a(TouchDirection touchDirection) {
        if (!this.P) {
            if (this.au != null) {
                this.au.a(false);
            }
            if (touchDirection == TouchDirection.NAN || !this.C) {
                if (this.Q != TouchDirection.HORIZONTAL) {
                    if (this.ad == 4) {
                        this.v.clearAnimation();
                        this.v.setVisibility(4);
                        this.s.startAnimation(this.alpha_out);
                    }
                    this.B.startAnimation(this.alpha_in);
                } else if (this.ad == 4) {
                    this.B.clearAnimation();
                    this.B.setVisibility(4);
                    this.s.startAnimation(this.alpha_out);
                    this.v.startAnimation(this.alpha_in);
                } else {
                    this.w.startAnimation(this.alpha_in);
                }
            } else if (this.Q != touchDirection) {
                if (this.Q == TouchDirection.HORIZONTAL) {
                    this.B.startAnimation(this.alpha_out);
                    if (this.ad == 4) {
                        this.s.clearAnimation();
                        this.s.setVisibility(4);
                        this.v.startAnimation(this.alpha_in);
                    } else {
                        this.w.startAnimation(this.alpha_in);
                    }
                } else {
                    this.B.startAnimation(this.alpha_in);
                    if (this.ad == 4) {
                        this.s.clearAnimation();
                        this.s.setVisibility(4);
                        this.v.startAnimation(this.alpha_out);
                    } else {
                        this.w.startAnimation(this.alpha_out);
                    }
                }
            }
            this.C = true;
            if (this.au != null) {
                this.au.a();
            }
        }
    }

    /* access modifiers changed from: private */
    public void m() {
        if (this.au != null) {
            this.au.a(true);
        }
        if (!this.P || this.C) {
            if (this.ad == 4) {
                this.s.startAnimation(this.alpha_in);
            }
            if (this.Q == TouchDirection.HORIZONTAL) {
                if (this.ad == 4) {
                    this.v.startAnimation(this.alpha_out);
                } else {
                    this.w.startAnimation(this.alpha_out);
                }
                this.B.clearAnimation();
            } else {
                if (this.ad == 4) {
                    this.v.clearAnimation();
                } else {
                    this.w.clearAnimation();
                }
                this.B.startAnimation(this.alpha_out);
            }
            this.Q = TouchDirection.NAN;
            this.C = false;
            if (this.au != null) {
                this.au.b();
            }
            this.u.dismissCircle();
        }
    }

    private void a(boolean z2) {
        if (!this.P) {
            this.s.clearAnimation();
            this.v.clearAnimation();
            this.w.clearAnimation();
            this.B.clearAnimation();
            this.v.setVisibility(4);
            this.w.setVisibility(4);
            this.B.setVisibility(4);
            if (z2) {
                this.s.setVisibility(0);
            } else {
                this.s.setVisibility(4);
            }
        }
    }

    /* access modifiers changed from: private */
    public void a(float f2) {
        float f3 = 0.0f - f2;
        float[] fArr = this.ab;
        fArr[2] = fArr[2] + (this.W * f3);
        if (this.ab[2] > 0.95f) {
            this.ab[2] = 0.95f;
        }
        if (this.ab[2] < 0.5f) {
            this.ab[2] = 0.5f;
        }
        int i2 = this.ad;
        if (i2 != 6) {
            switch (i2) {
                case 2:
                    a(35.0f, this.ab[1], this.ab[2]);
                    break;
                case 3:
                    a(this.ab[0], 0.8f, this.ab[2]);
                    break;
            }
        } else {
            a(35.0f, this.ab[1], this.ab[2]);
        }
        int a2 = YelightColorUtils.a(this.ab[2]);
        if (this.ad == 4) {
            this.ah = a2;
        } else if (this.ad == 6) {
            this.ai = a2;
        } else {
            this.ag = a2;
        }
        this.B.setValue(a2);
        if (this.au != null) {
            this.au.g(a2);
        }
    }

    /* access modifiers changed from: private */
    public boolean a(float f2, boolean z2) {
        if (z2) {
            float[] fArr = this.ab;
            fArr[1] = fArr[1] - (this.V * f2);
        } else {
            float[] fArr2 = this.ab;
            fArr2[1] = fArr2[1] + (this.V * f2);
        }
        if (this.ab[1] > 0.8f) {
            this.ab[1] = 0.8f - (this.ab[1] - 0.8f);
            z2 = !z2;
        }
        if (this.ab[1] < 0.4f) {
            this.ab[1] = (0.4f - this.ab[1]) + 0.4f;
            z2 = !z2;
        }
        a(35.0f, this.ab[1], this.ab[2]);
        this.u.setCircleRadio(TouchDirection.HORIZONTAL);
        this.aj = (int) YelightColorUtils.a(this.ab[1], this.l, this.m);
        if (!this.ap || this.aj <= 4000) {
            b(this.ab[1], z2);
            if (this.au != null) {
                this.au.f(this.aj);
            }
            return z2;
        }
        this.aj = 4000;
        if (z2) {
            float[] fArr3 = this.ab;
            fArr3[1] = fArr3[1] + (this.V * f2);
        } else {
            float[] fArr4 = this.ab;
            fArr4[1] = fArr4[1] - (this.V * f2);
        }
        if (this.au != null) {
            this.au.f(this.aj);
        }
        return z2;
    }

    /* access modifiers changed from: private */
    public void b(float f2) {
        float[] fArr = this.ab;
        fArr[0] = fArr[0] + (this.U * f2);
        if (this.ab[0] > 360.0f || this.ab[0] == 360.0f) {
            this.ab[0] = 0.0f;
        }
        if (this.ab[0] < 0.0f) {
            this.ab[0] = 360.0f;
        }
        a(this.ab[0], 0.8f, this.ab[2]);
        this.u.setCircleRadio(TouchDirection.HORIZONTAL);
        c(this.ab[0]);
        this.ak = Color.HSVToColor(this.ab);
        if (this.au != null) {
            this.au.e(this.ak);
        }
    }

    private void a(float f2, float f3, float f4) {
        this.ab[0] = f2;
        this.ab[1] = f3;
        this.ab[2] = f4;
        this.r.setBackgroundColor(Color.HSVToColor(this.ab));
    }

    private void b(float f2, boolean z2) {
        int i2;
        int width = this.x.getWidth();
        int x2 = (int) this.x.getX();
        if (!this.x.isShown()) {
            width = ((this.w.getWidth() - this.w.getPaddingLeft()) - this.w.getPaddingRight()) - (e(6.0f) * 2);
            x2 = e(6.0f);
        }
        int i3 = (int) (((((float) width) / 2.0f) / 0.4f) * (0.8f - f2));
        if (z2) {
            i2 = (width / 2) + i3;
        } else {
            i2 = (width / 2) - i3;
        }
        double d2 = (double) this.mWidth;
        Double.isNaN(d2);
        int i4 = (int) (d2 * 0.04d);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(i4, i4);
        layoutParams.setMargins((x2 + i2) - (i4 / 2), 0, 0, 0);
        this.y.setLayoutParams(layoutParams);
    }

    private void c(float f2) {
        int width = this.x.getWidth();
        int x2 = (int) this.x.getX();
        if (!this.x.isShown()) {
            width = ((this.w.getWidth() - this.w.getPaddingLeft()) - this.w.getPaddingRight()) - (e(6.0f) * 2);
            x2 = e(6.0f);
        }
        double d2 = (double) this.mWidth;
        Double.isNaN(d2);
        int i2 = (int) (d2 * 0.04d);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(i2, i2);
        layoutParams.setMargins((x2 + ((int) ((f2 / 360.0f) * ((float) width)))) - (i2 / 2), 0, 0, 0);
        this.y.setLayoutParams(layoutParams);
    }

    /* access modifiers changed from: private */
    public void d(float f2) {
        this.E = (int) (((float) this.E) - (this.aa * f2));
        if (this.E > 12000) {
            this.E = 12000;
        }
        if (this.E < 1000) {
            this.E = 1000;
        }
        if (this.D != null) {
            this.D.setEnterFadeDuration(this.E / 2);
            this.D.setExitFadeDuration(this.E / 2);
        }
        a(this.E);
    }

    private void a(int i2) {
        double d2 = (double) this.mWidth;
        Double.isNaN(d2);
        int i3 = (int) (d2 * 0.04d);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(i3, i3);
        Rect bounds = this.z.getProgressDrawable().getBounds();
        int i4 = (int) ((((float) (12000 - i2)) / 11000.0f) * ((float) (bounds.right - bounds.left)));
        this.z.setMax(bounds.right - bounds.left);
        this.z.setProgress(i4);
        layoutParams.setMargins(((i4 + ((this.z.getWidth() - (bounds.right - bounds.left)) / 2)) - (i3 / 2)) + ((int) this.z.getX()), 0, 0, 0);
        this.A.setLayoutParams(layoutParams);
    }

    public static class SimpleActionListener implements ActionListener {
        public void a() {
        }

        public void a(int i) {
        }

        public void a(boolean z) {
        }

        public void a(boolean z, int i) {
        }

        public void b() {
        }

        public void b(int i) {
        }

        public void c() {
        }

        public void c(int i) {
        }

        public void d() {
        }

        public void d(int i) {
        }

        public void e() {
        }

        public void e(int i) {
        }

        public void f() {
        }

        public void f(int i) {
        }

        public void g() {
        }

        public void g(int i) {
        }

        public void h() {
        }

        public void i() {
        }

        public void a(int i, int[] iArr, int i2) {
            if (iArr != null) {
                String str = "interval:" + i + " ";
                for (int i3 = 0; i3 < iArr.length; i3++) {
                    str = str + i3 + ":0x" + String.format("%x", new Object[]{Integer.valueOf(iArr[i3])}) + "  ";
                }
            }
        }
    }

    private class MyOnTouchListener implements View.OnTouchListener {
        private MyOnTouchListener() {
        }

        public boolean onTouch(View view, MotionEvent motionEvent) {
            YeelightControlView.this.av.removeCallbacks(YeelightControlView.this.ay);
            if (YeelightControlView.this.ad == 1) {
                return true;
            }
            int action = motionEvent.getAction();
            if (action != 3) {
                switch (action) {
                    case 0:
                        switch (YeelightControlView.this.u.getCurrentMode()) {
                            case COLORFLOW:
                            case SUNSHINE:
                            case NIGHTLIGHT:
                            case COLOR:
                                float unused = YeelightControlView.this.S = motionEvent.getX();
                                float unused2 = YeelightControlView.this.T = motionEvent.getY();
                                YeelightControlView.this.u.drawCircle(motionEvent.getX(), motionEvent.getY());
                                YeelightControlView.this.u.startDrawCircle();
                                Timer unused3 = YeelightControlView.this.aw = new Timer();
                                SendCommandTimerTask unused4 = YeelightControlView.this.ax = new SendCommandTimerTask();
                                YeelightControlView.this.aw.schedule(YeelightControlView.this.ax, 0, 1000);
                                break;
                        }
                    case 1:
                        break;
                }
            }
            if (YeelightControlView.this.aw != null) {
                YeelightControlView.this.aw.cancel();
                Timer unused5 = YeelightControlView.this.aw = null;
            }
            if (YeelightControlView.this.ax != null) {
                YeelightControlView.this.av.postDelayed(new SendCommandTimerTask(), 800);
                YeelightControlView.this.ax.cancel();
                SendCommandTimerTask unused6 = YeelightControlView.this.ax = null;
            }
            if (YeelightControlView.this.Q == TouchDirection.NAN) {
                YeelightControlView.this.u.dismissCircle();
                return true;
            }
            if (YeelightControlView.this.aq) {
                YeelightControlView.this.getContext().getSharedPreferences("yeelight", 0).edit().putInt(YeelightControlView.this.p.a(), 1).commit();
                boolean unused7 = YeelightControlView.this.aq = false;
                YeelightControlView.this.n.setVisibility(8);
            }
            switch (YeelightControlView.this.u.getCurrentMode()) {
                case COLORFLOW:
                    if (YeelightControlView.this.au != null) {
                        YeelightControlView.this.au.a(YeelightControlView.this.E, YeelightControlView.this.al, YeelightControlView.this.ah);
                    }
                    YeelightControlView.this.a(YeelightControlView.this.al, YeelightControlView.this.ah);
                    YeelightControlView.this.u.dismissCircle();
                    break;
                case SUNSHINE:
                    if (YeelightControlView.this.Q == TouchDirection.HORIZONTAL) {
                        if (YeelightControlView.this.au != null && YeelightControlView.this.p.a(ProductModel.CapabilityType.CONTROL_CT_H)) {
                            YeelightControlView.this.au.b(YeelightControlView.this.aj);
                        }
                    } else if (YeelightControlView.this.au != null) {
                        YeelightControlView.this.au.a(YeelightControlView.this.ag);
                    }
                    YeelightControlView.this.u.dismissCircle();
                    break;
                case NIGHTLIGHT:
                    if (YeelightControlView.this.Q == TouchDirection.VERTICAL && YeelightControlView.this.au != null) {
                        YeelightControlView.this.au.a(YeelightControlView.this.ai);
                    }
                    if (YeelightControlView.this.Q == TouchDirection.HORIZONTAL && ((ProductModel.r.equals(YeelightControlView.this.p.a()) || ProductModel.s.equals(YeelightControlView.this.p.a())) && YeelightControlView.this.au != null)) {
                        YeelightControlView.this.au.c(YeelightControlView.this.ak);
                    }
                    YeelightControlView.this.u.dismissCircle();
                    break;
                case COLOR:
                    if (YeelightControlView.this.Q == TouchDirection.HORIZONTAL) {
                        if (YeelightControlView.this.au != null) {
                            YeelightControlView.this.au.c(YeelightControlView.this.ak);
                        }
                    } else if (YeelightControlView.this.au != null) {
                        YeelightControlView.this.au.a(YeelightControlView.this.ag);
                    }
                    YeelightControlView.this.u.dismissCircle();
                    break;
            }
            boolean unused8 = YeelightControlView.this.P = false;
            if (YeelightControlView.this.Q != TouchDirection.HORIZONTAL) {
                YeelightControlView.this.av.postDelayed(YeelightControlView.this.ay, 600);
            } else if (YeelightControlView.this.ad == 3 && YeelightControlView.this.p.a(ProductModel.CapabilityType.CONTROL_COLOR_H)) {
                YeelightControlView.this.av.postDelayed(YeelightControlView.this.ay, 600);
            } else if ((YeelightControlView.this.ad == 2 || YeelightControlView.this.ad == 5) && YeelightControlView.this.p.a(ProductModel.CapabilityType.CONTROL_CT_H)) {
                YeelightControlView.this.av.postDelayed(YeelightControlView.this.ay, 600);
            } else if (YeelightControlView.this.ad == 4 && YeelightControlView.this.p.a(ProductModel.CapabilityType.CONTROL_FLOW_H)) {
                YeelightControlView.this.av.postDelayed(YeelightControlView.this.ay, 600);
            } else if (YeelightControlView.this.ad != 6 || !ProductModel.r.equals(YeelightControlView.this.p.a())) {
                TouchDirection unused9 = YeelightControlView.this.Q = TouchDirection.NAN;
            } else {
                YeelightControlView.this.av.postDelayed(YeelightControlView.this.ay, 600);
            }
            YeelightControlView.this.j();
            YeelightControlView.this.k();
            YeelightControlView.this.R.onTouchEvent(motionEvent);
            return true;
        }
    }

    private class MyOnGestureListener extends GestureDetector.SimpleOnGestureListener {
        private MyOnGestureListener() {
        }

        /* JADX WARNING: Code restructure failed: missing block: B:27:0x00e7, code lost:
            if (com.xiaomi.smarthome.newui.card.profile.YeelightControlView.access$1600(r2.f20588a).a(com.xiaomi.smarthome.newui.card.ProductModel.CapabilityType.CONTROL_FLOW_H) == false) goto L_0x00ee;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:28:0x00e9, code lost:
            com.xiaomi.smarthome.newui.card.profile.YeelightControlView.access$3200(r2.f20588a, r3);
         */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public boolean onScroll(android.view.MotionEvent r3, android.view.MotionEvent r4, float r5, float r6) {
            /*
                r2 = this;
                if (r3 == 0) goto L_0x028c
                if (r4 != 0) goto L_0x0006
                goto L_0x028c
            L_0x0006:
                int[] r3 = com.xiaomi.smarthome.newui.card.profile.YeelightControlView.AnonymousClass6.f20587a
                com.xiaomi.smarthome.newui.card.profile.YeelightControlView r5 = com.xiaomi.smarthome.newui.card.profile.YeelightControlView.this
                com.xiaomi.smarthome.newui.card.profile.TouchView r5 = r5.u
                com.xiaomi.smarthome.newui.card.profile.TouchView$ControlMode r5 = r5.getCurrentMode()
                int r5 = r5.ordinal()
                r3 = r3[r5]
                r5 = 1
                switch(r3) {
                    case 1: goto L_0x028b;
                    case 2: goto L_0x001e;
                    case 3: goto L_0x001e;
                    case 4: goto L_0x001e;
                    case 5: goto L_0x001e;
                    default: goto L_0x001c;
                }
            L_0x001c:
                goto L_0x028b
            L_0x001e:
                com.xiaomi.smarthome.newui.card.profile.YeelightControlView r3 = com.xiaomi.smarthome.newui.card.profile.YeelightControlView.this
                boolean r3 = r3.P
                if (r3 != 0) goto L_0x00f3
                com.xiaomi.smarthome.newui.card.profile.YeelightControlView r3 = com.xiaomi.smarthome.newui.card.profile.YeelightControlView.this
                com.xiaomi.smarthome.newui.card.profile.YeelightControlView$TouchDirection r3 = r3.Q
                float r6 = r4.getX()
                com.xiaomi.smarthome.newui.card.profile.YeelightControlView r0 = com.xiaomi.smarthome.newui.card.profile.YeelightControlView.this
                float r0 = r0.S
                float r6 = r6 - r0
                float r6 = java.lang.Math.abs(r6)
                float r0 = r4.getY()
                com.xiaomi.smarthome.newui.card.profile.YeelightControlView r1 = com.xiaomi.smarthome.newui.card.profile.YeelightControlView.this
                float r1 = r1.T
                float r0 = r0 - r1
                float r0 = java.lang.Math.abs(r0)
                int r6 = (r6 > r0 ? 1 : (r6 == r0 ? 0 : -1))
                if (r6 <= 0) goto L_0x0056
                com.xiaomi.smarthome.newui.card.profile.YeelightControlView r6 = com.xiaomi.smarthome.newui.card.profile.YeelightControlView.this
                com.xiaomi.smarthome.newui.card.profile.YeelightControlView$TouchDirection r0 = com.xiaomi.smarthome.newui.card.profile.YeelightControlView.TouchDirection.HORIZONTAL
                com.xiaomi.smarthome.newui.card.profile.YeelightControlView.TouchDirection unused = r6.Q = r0
                goto L_0x005d
            L_0x0056:
                com.xiaomi.smarthome.newui.card.profile.YeelightControlView r6 = com.xiaomi.smarthome.newui.card.profile.YeelightControlView.this
                com.xiaomi.smarthome.newui.card.profile.YeelightControlView$TouchDirection r0 = com.xiaomi.smarthome.newui.card.profile.YeelightControlView.TouchDirection.VERTICAL
                com.xiaomi.smarthome.newui.card.profile.YeelightControlView.TouchDirection unused = r6.Q = r0
            L_0x005d:
                com.xiaomi.smarthome.newui.card.profile.YeelightControlView r6 = com.xiaomi.smarthome.newui.card.profile.YeelightControlView.this
                com.xiaomi.smarthome.newui.card.profile.YeelightControlView$TouchDirection r6 = r6.Q
                com.xiaomi.smarthome.newui.card.profile.YeelightControlView$TouchDirection r0 = com.xiaomi.smarthome.newui.card.profile.YeelightControlView.TouchDirection.HORIZONTAL
                if (r6 == r0) goto L_0x006e
                com.xiaomi.smarthome.newui.card.profile.YeelightControlView r6 = com.xiaomi.smarthome.newui.card.profile.YeelightControlView.this
                r6.a((com.xiaomi.smarthome.newui.card.profile.YeelightControlView.TouchDirection) r3)
                goto L_0x00ee
            L_0x006e:
                com.xiaomi.smarthome.newui.card.profile.YeelightControlView r6 = com.xiaomi.smarthome.newui.card.profile.YeelightControlView.this
                int r6 = r6.ad
                switch(r6) {
                    case 2: goto L_0x00c8;
                    case 3: goto L_0x00b4;
                    case 4: goto L_0x00db;
                    case 5: goto L_0x00c8;
                    case 6: goto L_0x0079;
                    default: goto L_0x0077;
                }
            L_0x0077:
                goto L_0x00ee
            L_0x0079:
                java.lang.String r6 = "yeelink.light.bslamp1"
                com.xiaomi.smarthome.newui.card.profile.YeelightControlView r0 = com.xiaomi.smarthome.newui.card.profile.YeelightControlView.this
                com.xiaomi.smarthome.newui.card.ProductModel r0 = r0.p
                java.lang.String r0 = r0.a()
                boolean r6 = r6.equals(r0)
                if (r6 != 0) goto L_0x009d
                java.lang.String r6 = "yeelink.light.bslamp2"
                com.xiaomi.smarthome.newui.card.profile.YeelightControlView r0 = com.xiaomi.smarthome.newui.card.profile.YeelightControlView.this
                com.xiaomi.smarthome.newui.card.ProductModel r0 = r0.p
                java.lang.String r0 = r0.a()
                boolean r6 = r6.equals(r0)
                if (r6 == 0) goto L_0x00ee
            L_0x009d:
                com.xiaomi.smarthome.newui.card.profile.YeelightControlView r6 = com.xiaomi.smarthome.newui.card.profile.YeelightControlView.this
                r6.a((com.xiaomi.smarthome.newui.card.profile.YeelightControlView.TouchDirection) r3)
                com.xiaomi.smarthome.newui.card.profile.YeelightControlView r3 = com.xiaomi.smarthome.newui.card.profile.YeelightControlView.this
                r6 = 3
                int unused = r3.ad = r6
                com.xiaomi.smarthome.newui.card.profile.YeelightControlView r3 = com.xiaomi.smarthome.newui.card.profile.YeelightControlView.this
                com.xiaomi.smarthome.newui.card.profile.TouchView r3 = r3.u
                com.xiaomi.smarthome.newui.card.profile.TouchView$ControlMode r6 = com.xiaomi.smarthome.newui.card.profile.TouchView.ControlMode.COLOR
                r3.setCurrentMode(r6)
                goto L_0x00ee
            L_0x00b4:
                com.xiaomi.smarthome.newui.card.profile.YeelightControlView r6 = com.xiaomi.smarthome.newui.card.profile.YeelightControlView.this
                com.xiaomi.smarthome.newui.card.ProductModel r6 = r6.p
                com.xiaomi.smarthome.newui.card.ProductModel$CapabilityType r0 = com.xiaomi.smarthome.newui.card.ProductModel.CapabilityType.CONTROL_COLOR_H
                boolean r6 = r6.a((com.xiaomi.smarthome.newui.card.ProductModel.CapabilityType) r0)
                if (r6 == 0) goto L_0x00ee
                com.xiaomi.smarthome.newui.card.profile.YeelightControlView r6 = com.xiaomi.smarthome.newui.card.profile.YeelightControlView.this
                r6.a((com.xiaomi.smarthome.newui.card.profile.YeelightControlView.TouchDirection) r3)
                goto L_0x00ee
            L_0x00c8:
                com.xiaomi.smarthome.newui.card.profile.YeelightControlView r6 = com.xiaomi.smarthome.newui.card.profile.YeelightControlView.this
                com.xiaomi.smarthome.newui.card.ProductModel r6 = r6.p
                com.xiaomi.smarthome.newui.card.ProductModel$CapabilityType r0 = com.xiaomi.smarthome.newui.card.ProductModel.CapabilityType.CONTROL_CT_H
                boolean r6 = r6.a((com.xiaomi.smarthome.newui.card.ProductModel.CapabilityType) r0)
                if (r6 == 0) goto L_0x00db
                com.xiaomi.smarthome.newui.card.profile.YeelightControlView r6 = com.xiaomi.smarthome.newui.card.profile.YeelightControlView.this
                r6.a((com.xiaomi.smarthome.newui.card.profile.YeelightControlView.TouchDirection) r3)
            L_0x00db:
                com.xiaomi.smarthome.newui.card.profile.YeelightControlView r6 = com.xiaomi.smarthome.newui.card.profile.YeelightControlView.this
                com.xiaomi.smarthome.newui.card.ProductModel r6 = r6.p
                com.xiaomi.smarthome.newui.card.ProductModel$CapabilityType r0 = com.xiaomi.smarthome.newui.card.ProductModel.CapabilityType.CONTROL_FLOW_H
                boolean r6 = r6.a((com.xiaomi.smarthome.newui.card.ProductModel.CapabilityType) r0)
                if (r6 == 0) goto L_0x00ee
                com.xiaomi.smarthome.newui.card.profile.YeelightControlView r6 = com.xiaomi.smarthome.newui.card.profile.YeelightControlView.this
                r6.a((com.xiaomi.smarthome.newui.card.profile.YeelightControlView.TouchDirection) r3)
            L_0x00ee:
                com.xiaomi.smarthome.newui.card.profile.YeelightControlView r3 = com.xiaomi.smarthome.newui.card.profile.YeelightControlView.this
                boolean unused = r3.P = r5
            L_0x00f3:
                int[] r3 = com.xiaomi.smarthome.newui.card.profile.YeelightControlView.AnonymousClass6.b
                com.xiaomi.smarthome.newui.card.profile.YeelightControlView r6 = com.xiaomi.smarthome.newui.card.profile.YeelightControlView.this
                com.xiaomi.smarthome.newui.card.profile.YeelightControlView$TouchDirection r6 = r6.Q
                int r6 = r6.ordinal()
                r3 = r3[r6]
                switch(r3) {
                    case 1: goto L_0x018e;
                    case 2: goto L_0x0106;
                    default: goto L_0x0104;
                }
            L_0x0104:
                goto L_0x0279
            L_0x0106:
                com.xiaomi.smarthome.newui.card.profile.YeelightControlView r3 = com.xiaomi.smarthome.newui.card.profile.YeelightControlView.this
                com.xiaomi.smarthome.newui.card.profile.TouchView r3 = r3.u
                com.xiaomi.smarthome.newui.card.profile.TouchView$ControlMode r3 = r3.getCurrentMode()
                com.xiaomi.smarthome.newui.card.profile.TouchView$ControlMode r6 = com.xiaomi.smarthome.newui.card.profile.TouchView.ControlMode.COLOR
                if (r3 != r6) goto L_0x0125
                com.xiaomi.smarthome.newui.card.profile.YeelightControlView r3 = com.xiaomi.smarthome.newui.card.profile.YeelightControlView.this
                float r6 = r4.getY()
                com.xiaomi.smarthome.newui.card.profile.YeelightControlView r0 = com.xiaomi.smarthome.newui.card.profile.YeelightControlView.this
                float r0 = r0.T
                float r6 = r6 - r0
                r3.a((float) r6)
                goto L_0x0171
            L_0x0125:
                com.xiaomi.smarthome.newui.card.profile.YeelightControlView r3 = com.xiaomi.smarthome.newui.card.profile.YeelightControlView.this
                com.xiaomi.smarthome.newui.card.profile.TouchView r3 = r3.u
                com.xiaomi.smarthome.newui.card.profile.TouchView$ControlMode r3 = r3.getCurrentMode()
                com.xiaomi.smarthome.newui.card.profile.TouchView$ControlMode r6 = com.xiaomi.smarthome.newui.card.profile.TouchView.ControlMode.SUNSHINE
                if (r3 == r6) goto L_0x0161
                com.xiaomi.smarthome.newui.card.profile.YeelightControlView r3 = com.xiaomi.smarthome.newui.card.profile.YeelightControlView.this
                com.xiaomi.smarthome.newui.card.profile.TouchView r3 = r3.u
                com.xiaomi.smarthome.newui.card.profile.TouchView$ControlMode r3 = r3.getCurrentMode()
                com.xiaomi.smarthome.newui.card.profile.TouchView$ControlMode r6 = com.xiaomi.smarthome.newui.card.profile.TouchView.ControlMode.NIGHTLIGHT
                if (r3 != r6) goto L_0x0142
                goto L_0x0161
            L_0x0142:
                com.xiaomi.smarthome.newui.card.profile.YeelightControlView r3 = com.xiaomi.smarthome.newui.card.profile.YeelightControlView.this
                com.xiaomi.smarthome.newui.card.profile.TouchView r3 = r3.u
                com.xiaomi.smarthome.newui.card.profile.TouchView$ControlMode r3 = r3.getCurrentMode()
                com.xiaomi.smarthome.newui.card.profile.TouchView$ControlMode r6 = com.xiaomi.smarthome.newui.card.profile.TouchView.ControlMode.COLORFLOW
                if (r3 != r6) goto L_0x0171
                com.xiaomi.smarthome.newui.card.profile.YeelightControlView r3 = com.xiaomi.smarthome.newui.card.profile.YeelightControlView.this
                float r6 = r4.getY()
                com.xiaomi.smarthome.newui.card.profile.YeelightControlView r0 = com.xiaomi.smarthome.newui.card.profile.YeelightControlView.this
                float r0 = r0.T
                float r6 = r6 - r0
                r3.a((float) r6)
                goto L_0x0171
            L_0x0161:
                com.xiaomi.smarthome.newui.card.profile.YeelightControlView r3 = com.xiaomi.smarthome.newui.card.profile.YeelightControlView.this
                float r6 = r4.getY()
                com.xiaomi.smarthome.newui.card.profile.YeelightControlView r0 = com.xiaomi.smarthome.newui.card.profile.YeelightControlView.this
                float r0 = r0.T
                float r6 = r6 - r0
                r3.a((float) r6)
            L_0x0171:
                com.xiaomi.smarthome.newui.card.profile.YeelightControlView r3 = com.xiaomi.smarthome.newui.card.profile.YeelightControlView.this
                com.xiaomi.smarthome.newui.card.profile.TouchView r3 = r3.u
                float r6 = r4.getX()
                float r0 = r4.getY()
                r3.drawCircle(r6, r0)
                com.xiaomi.smarthome.newui.card.profile.YeelightControlView r3 = com.xiaomi.smarthome.newui.card.profile.YeelightControlView.this
                r3.c((android.view.MotionEvent) r4)
                com.xiaomi.smarthome.newui.card.profile.YeelightControlView r3 = com.xiaomi.smarthome.newui.card.profile.YeelightControlView.this
                r3.d((android.view.MotionEvent) r4)
                goto L_0x0279
            L_0x018e:
                com.xiaomi.smarthome.newui.card.profile.YeelightControlView r3 = com.xiaomi.smarthome.newui.card.profile.YeelightControlView.this
                com.xiaomi.smarthome.newui.card.profile.TouchView r3 = r3.u
                com.xiaomi.smarthome.newui.card.profile.TouchView$ControlMode r3 = r3.getCurrentMode()
                com.xiaomi.smarthome.newui.card.profile.TouchView$ControlMode r6 = com.xiaomi.smarthome.newui.card.profile.TouchView.ControlMode.COLOR
                if (r3 != r6) goto L_0x01c7
                com.xiaomi.smarthome.newui.card.profile.YeelightControlView r3 = com.xiaomi.smarthome.newui.card.profile.YeelightControlView.this
                com.xiaomi.smarthome.newui.card.ProductModel r3 = r3.p
                com.xiaomi.smarthome.newui.card.ProductModel$CapabilityType r6 = com.xiaomi.smarthome.newui.card.ProductModel.CapabilityType.CONTROL_COLOR_H
                boolean r3 = r3.a((com.xiaomi.smarthome.newui.card.ProductModel.CapabilityType) r6)
                if (r3 == 0) goto L_0x01c7
                com.xiaomi.smarthome.newui.card.profile.YeelightControlView r3 = com.xiaomi.smarthome.newui.card.profile.YeelightControlView.this
                android.widget.ImageView r3 = r3.x
                int r6 = com.xiaomi.smarthome.miotcard.R.drawable.color_picker
                r3.setImageResource(r6)
                com.xiaomi.smarthome.newui.card.profile.YeelightControlView r3 = com.xiaomi.smarthome.newui.card.profile.YeelightControlView.this
                float r6 = r4.getX()
                com.xiaomi.smarthome.newui.card.profile.YeelightControlView r0 = com.xiaomi.smarthome.newui.card.profile.YeelightControlView.this
                float r0 = r0.S
                float r6 = r6 - r0
                r3.b((float) r6)
                goto L_0x0268
            L_0x01c7:
                com.xiaomi.smarthome.newui.card.profile.YeelightControlView r3 = com.xiaomi.smarthome.newui.card.profile.YeelightControlView.this
                com.xiaomi.smarthome.newui.card.profile.TouchView r3 = r3.u
                com.xiaomi.smarthome.newui.card.profile.TouchView$ControlMode r3 = r3.getCurrentMode()
                com.xiaomi.smarthome.newui.card.profile.TouchView$ControlMode r6 = com.xiaomi.smarthome.newui.card.profile.TouchView.ControlMode.SUNSHINE
                if (r3 != r6) goto L_0x020b
                com.xiaomi.smarthome.newui.card.profile.YeelightControlView r3 = com.xiaomi.smarthome.newui.card.profile.YeelightControlView.this
                com.xiaomi.smarthome.newui.card.ProductModel r3 = r3.p
                com.xiaomi.smarthome.newui.card.ProductModel$CapabilityType r6 = com.xiaomi.smarthome.newui.card.ProductModel.CapabilityType.CONTROL_CT_H
                boolean r3 = r3.a((com.xiaomi.smarthome.newui.card.ProductModel.CapabilityType) r6)
                if (r3 == 0) goto L_0x020b
                com.xiaomi.smarthome.newui.card.profile.YeelightControlView r3 = com.xiaomi.smarthome.newui.card.profile.YeelightControlView.this
                android.widget.ImageView r3 = r3.x
                int r6 = com.xiaomi.smarthome.miotcard.R.drawable.sunshine_picker
                r3.setImageResource(r6)
                com.xiaomi.smarthome.newui.card.profile.YeelightControlView r3 = com.xiaomi.smarthome.newui.card.profile.YeelightControlView.this
                com.xiaomi.smarthome.newui.card.profile.YeelightControlView r6 = com.xiaomi.smarthome.newui.card.profile.YeelightControlView.this
                float r0 = r4.getX()
                com.xiaomi.smarthome.newui.card.profile.YeelightControlView r1 = com.xiaomi.smarthome.newui.card.profile.YeelightControlView.this
                float r1 = r1.S
                float r0 = r0 - r1
                com.xiaomi.smarthome.newui.card.profile.YeelightControlView r1 = com.xiaomi.smarthome.newui.card.profile.YeelightControlView.this
                boolean r1 = r1.ac
                boolean r6 = r6.a((float) r0, (boolean) r1)
                boolean unused = r3.ac = r6
                goto L_0x0268
            L_0x020b:
                com.xiaomi.smarthome.newui.card.profile.YeelightControlView r3 = com.xiaomi.smarthome.newui.card.profile.YeelightControlView.this
                com.xiaomi.smarthome.newui.card.profile.TouchView r3 = r3.u
                com.xiaomi.smarthome.newui.card.profile.TouchView$ControlMode r3 = r3.getCurrentMode()
                com.xiaomi.smarthome.newui.card.profile.TouchView$ControlMode r6 = com.xiaomi.smarthome.newui.card.profile.TouchView.ControlMode.COLORFLOW
                if (r3 != r6) goto L_0x0238
                com.xiaomi.smarthome.newui.card.profile.YeelightControlView r3 = com.xiaomi.smarthome.newui.card.profile.YeelightControlView.this
                com.xiaomi.smarthome.newui.card.ProductModel r3 = r3.p
                com.xiaomi.smarthome.newui.card.ProductModel$CapabilityType r6 = com.xiaomi.smarthome.newui.card.ProductModel.CapabilityType.CONTROL_FLOW_H
                boolean r3 = r3.a((com.xiaomi.smarthome.newui.card.ProductModel.CapabilityType) r6)
                if (r3 == 0) goto L_0x0238
                com.xiaomi.smarthome.newui.card.profile.YeelightControlView r3 = com.xiaomi.smarthome.newui.card.profile.YeelightControlView.this
                float r6 = r4.getX()
                com.xiaomi.smarthome.newui.card.profile.YeelightControlView r0 = com.xiaomi.smarthome.newui.card.profile.YeelightControlView.this
                float r0 = r0.S
                float r6 = r6 - r0
                r3.d((float) r6)
                goto L_0x0268
            L_0x0238:
                com.xiaomi.smarthome.newui.card.profile.YeelightControlView r3 = com.xiaomi.smarthome.newui.card.profile.YeelightControlView.this
                com.xiaomi.smarthome.newui.card.profile.TouchView r3 = r3.u
                com.xiaomi.smarthome.newui.card.profile.TouchView$ControlMode r3 = r3.getCurrentMode()
                com.xiaomi.smarthome.newui.card.profile.TouchView$ControlMode r6 = com.xiaomi.smarthome.newui.card.profile.TouchView.ControlMode.NIGHTLIGHT
                if (r3 != r6) goto L_0x0268
                java.lang.String r3 = "yeelink.light.bslamp1"
                com.xiaomi.smarthome.newui.card.profile.YeelightControlView r6 = com.xiaomi.smarthome.newui.card.profile.YeelightControlView.this
                com.xiaomi.smarthome.newui.card.ProductModel r6 = r6.p
                java.lang.String r6 = r6.a()
                boolean r3 = r3.equals(r6)
                if (r3 == 0) goto L_0x0268
                com.xiaomi.smarthome.newui.card.profile.YeelightControlView r3 = com.xiaomi.smarthome.newui.card.profile.YeelightControlView.this
                float r6 = r4.getX()
                com.xiaomi.smarthome.newui.card.profile.YeelightControlView r0 = com.xiaomi.smarthome.newui.card.profile.YeelightControlView.this
                float r0 = r0.S
                float r6 = r6 - r0
                r3.b((float) r6)
            L_0x0268:
                com.xiaomi.smarthome.newui.card.profile.YeelightControlView r3 = com.xiaomi.smarthome.newui.card.profile.YeelightControlView.this
                com.xiaomi.smarthome.newui.card.profile.TouchView r3 = r3.u
                float r6 = r4.getX()
                float r0 = r4.getY()
                r3.drawCircle(r6, r0)
            L_0x0279:
                com.xiaomi.smarthome.newui.card.profile.YeelightControlView r3 = com.xiaomi.smarthome.newui.card.profile.YeelightControlView.this
                float r6 = r4.getX()
                float unused = r3.S = r6
                com.xiaomi.smarthome.newui.card.profile.YeelightControlView r3 = com.xiaomi.smarthome.newui.card.profile.YeelightControlView.this
                float r4 = r4.getY()
                float unused = r3.T = r4
            L_0x028b:
                return r5
            L_0x028c:
                boolean r3 = super.onScroll(r3, r4, r5, r6)
                return r3
            */
            throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.smarthome.newui.card.profile.YeelightControlView.MyOnGestureListener.onScroll(android.view.MotionEvent, android.view.MotionEvent, float, float):boolean");
        }
    }

    public boolean dispatchTouchEvent(MotionEvent motionEvent) {
        if (motionEvent == null || motionEvent.getPointerCount() <= 1 || motionEvent.getActionIndex() == 0) {
            return super.dispatchTouchEvent(motionEvent);
        }
        return true;
    }

    private int e(float f2) {
        return (int) ((f2 * getContext().getResources().getDisplayMetrics().density) + 0.5f);
    }

    private void n() {
        this.u.recycleBitmap();
        if (this.M != null && !this.M.isRecycled()) {
            this.r.setBackgroundDrawable((Drawable) null);
            this.M.recycle();
            this.M = null;
        }
    }

    private Drawable getOffMask() {
        try {
            if (this.M == null || this.M.isRecycled()) {
                BitmapFactory.Options options = new BitmapFactory.Options();
                options.inPreferredConfig = Bitmap.Config.ARGB_8888;
                options.inPurgeable = true;
                options.inInputShareable = true;
                this.M = BitmapFactory.decodeStream(getResources().openRawResource(R.drawable.switch_off_mask), (Rect) null, options);
            }
            return new BitmapDrawable(this.M);
        } catch (Exception unused) {
            return null;
        }
    }

    public void setSharedPrefs(Context context, String str) {
        this.am = context.getSharedPreferences(str, 0);
        this.ao = this.am.getBoolean("ignore_confrim", false);
        this.an = this.am.getInt("ignore_show_count", 0);
    }

    public void setKidMode(boolean z2, int i2) {
        if (z2 && !this.ap) {
            this.ab[1] = 0.7f;
            b(0.7f, false);
        }
        this.ap = z2;
    }

    public void setIsGroup(boolean z2) {
        this.ar = z2;
    }

    private class SendCommandTimerTask extends TimerTask {
        private SendCommandTimerTask() {
        }

        /* JADX WARNING: Code restructure failed: missing block: B:44:0x0110, code lost:
            return;
         */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public synchronized void run() {
            /*
                r2 = this;
                monitor-enter(r2)
                com.xiaomi.smarthome.newui.card.profile.YeelightControlView r0 = com.xiaomi.smarthome.newui.card.profile.YeelightControlView.this     // Catch:{ all -> 0x0111 }
                boolean r0 = r0.isShown()     // Catch:{ all -> 0x0111 }
                if (r0 != 0) goto L_0x000e
                r2.cancel()     // Catch:{ all -> 0x0111 }
                monitor-exit(r2)
                return
            L_0x000e:
                int[] r0 = com.xiaomi.smarthome.newui.card.profile.YeelightControlView.AnonymousClass6.f20587a     // Catch:{ all -> 0x0111 }
                com.xiaomi.smarthome.newui.card.profile.YeelightControlView r1 = com.xiaomi.smarthome.newui.card.profile.YeelightControlView.this     // Catch:{ all -> 0x0111 }
                com.xiaomi.smarthome.newui.card.profile.TouchView r1 = r1.u     // Catch:{ all -> 0x0111 }
                com.xiaomi.smarthome.newui.card.profile.TouchView$ControlMode r1 = r1.getCurrentMode()     // Catch:{ all -> 0x0111 }
                int r1 = r1.ordinal()     // Catch:{ all -> 0x0111 }
                r0 = r0[r1]     // Catch:{ all -> 0x0111 }
                switch(r0) {
                    case 1: goto L_0x010f;
                    case 2: goto L_0x010f;
                    case 3: goto L_0x00c8;
                    case 4: goto L_0x0061;
                    case 5: goto L_0x0025;
                    default: goto L_0x0023;
                }     // Catch:{ all -> 0x0111 }
            L_0x0023:
                goto L_0x010f
            L_0x0025:
                com.xiaomi.smarthome.newui.card.profile.YeelightControlView r0 = com.xiaomi.smarthome.newui.card.profile.YeelightControlView.this     // Catch:{ all -> 0x0111 }
                com.xiaomi.smarthome.newui.card.profile.YeelightControlView$TouchDirection r0 = r0.Q     // Catch:{ all -> 0x0111 }
                com.xiaomi.smarthome.newui.card.profile.YeelightControlView$TouchDirection r1 = com.xiaomi.smarthome.newui.card.profile.YeelightControlView.TouchDirection.HORIZONTAL     // Catch:{ all -> 0x0111 }
                if (r0 != r1) goto L_0x0048
                com.xiaomi.smarthome.newui.card.profile.YeelightControlView r0 = com.xiaomi.smarthome.newui.card.profile.YeelightControlView.this     // Catch:{ all -> 0x0111 }
                com.xiaomi.smarthome.newui.card.profile.YeelightControlView$ActionListener r0 = r0.au     // Catch:{ all -> 0x0111 }
                if (r0 == 0) goto L_0x010f
                com.xiaomi.smarthome.newui.card.profile.YeelightControlView r0 = com.xiaomi.smarthome.newui.card.profile.YeelightControlView.this     // Catch:{ all -> 0x0111 }
                com.xiaomi.smarthome.newui.card.profile.YeelightControlView$ActionListener r0 = r0.au     // Catch:{ all -> 0x0111 }
                com.xiaomi.smarthome.newui.card.profile.YeelightControlView r1 = com.xiaomi.smarthome.newui.card.profile.YeelightControlView.this     // Catch:{ all -> 0x0111 }
                int r1 = r1.ak     // Catch:{ all -> 0x0111 }
                r0.c(r1)     // Catch:{ all -> 0x0111 }
                goto L_0x010f
            L_0x0048:
                com.xiaomi.smarthome.newui.card.profile.YeelightControlView r0 = com.xiaomi.smarthome.newui.card.profile.YeelightControlView.this     // Catch:{ all -> 0x0111 }
                com.xiaomi.smarthome.newui.card.profile.YeelightControlView$ActionListener r0 = r0.au     // Catch:{ all -> 0x0111 }
                if (r0 == 0) goto L_0x010f
                com.xiaomi.smarthome.newui.card.profile.YeelightControlView r0 = com.xiaomi.smarthome.newui.card.profile.YeelightControlView.this     // Catch:{ all -> 0x0111 }
                com.xiaomi.smarthome.newui.card.profile.YeelightControlView$ActionListener r0 = r0.au     // Catch:{ all -> 0x0111 }
                com.xiaomi.smarthome.newui.card.profile.YeelightControlView r1 = com.xiaomi.smarthome.newui.card.profile.YeelightControlView.this     // Catch:{ all -> 0x0111 }
                int r1 = r1.ag     // Catch:{ all -> 0x0111 }
                r0.a((int) r1)     // Catch:{ all -> 0x0111 }
                goto L_0x010f
            L_0x0061:
                com.xiaomi.smarthome.newui.card.profile.YeelightControlView r0 = com.xiaomi.smarthome.newui.card.profile.YeelightControlView.this     // Catch:{ all -> 0x0111 }
                com.xiaomi.smarthome.newui.card.profile.YeelightControlView$TouchDirection r0 = r0.Q     // Catch:{ all -> 0x0111 }
                com.xiaomi.smarthome.newui.card.profile.YeelightControlView$TouchDirection r1 = com.xiaomi.smarthome.newui.card.profile.YeelightControlView.TouchDirection.VERTICAL     // Catch:{ all -> 0x0111 }
                if (r0 != r1) goto L_0x0082
                com.xiaomi.smarthome.newui.card.profile.YeelightControlView r0 = com.xiaomi.smarthome.newui.card.profile.YeelightControlView.this     // Catch:{ all -> 0x0111 }
                com.xiaomi.smarthome.newui.card.profile.YeelightControlView$ActionListener r0 = r0.au     // Catch:{ all -> 0x0111 }
                if (r0 == 0) goto L_0x0082
                com.xiaomi.smarthome.newui.card.profile.YeelightControlView r0 = com.xiaomi.smarthome.newui.card.profile.YeelightControlView.this     // Catch:{ all -> 0x0111 }
                com.xiaomi.smarthome.newui.card.profile.YeelightControlView$ActionListener r0 = r0.au     // Catch:{ all -> 0x0111 }
                com.xiaomi.smarthome.newui.card.profile.YeelightControlView r1 = com.xiaomi.smarthome.newui.card.profile.YeelightControlView.this     // Catch:{ all -> 0x0111 }
                int r1 = r1.ai     // Catch:{ all -> 0x0111 }
                r0.a((int) r1)     // Catch:{ all -> 0x0111 }
            L_0x0082:
                com.xiaomi.smarthome.newui.card.profile.YeelightControlView r0 = com.xiaomi.smarthome.newui.card.profile.YeelightControlView.this     // Catch:{ all -> 0x0111 }
                com.xiaomi.smarthome.newui.card.profile.YeelightControlView$TouchDirection r0 = r0.Q     // Catch:{ all -> 0x0111 }
                com.xiaomi.smarthome.newui.card.profile.YeelightControlView$TouchDirection r1 = com.xiaomi.smarthome.newui.card.profile.YeelightControlView.TouchDirection.HORIZONTAL     // Catch:{ all -> 0x0111 }
                if (r0 != r1) goto L_0x010f
                java.lang.String r0 = "yeelink.light.bslamp1"
                com.xiaomi.smarthome.newui.card.profile.YeelightControlView r1 = com.xiaomi.smarthome.newui.card.profile.YeelightControlView.this     // Catch:{ all -> 0x0111 }
                com.xiaomi.smarthome.newui.card.ProductModel r1 = r1.p     // Catch:{ all -> 0x0111 }
                java.lang.String r1 = r1.a()     // Catch:{ all -> 0x0111 }
                boolean r0 = r0.equals(r1)     // Catch:{ all -> 0x0111 }
                if (r0 != 0) goto L_0x00b0
                java.lang.String r0 = "yeelink.light.bslamp2"
                com.xiaomi.smarthome.newui.card.profile.YeelightControlView r1 = com.xiaomi.smarthome.newui.card.profile.YeelightControlView.this     // Catch:{ all -> 0x0111 }
                com.xiaomi.smarthome.newui.card.ProductModel r1 = r1.p     // Catch:{ all -> 0x0111 }
                java.lang.String r1 = r1.a()     // Catch:{ all -> 0x0111 }
                boolean r0 = r0.equals(r1)     // Catch:{ all -> 0x0111 }
                if (r0 == 0) goto L_0x010f
            L_0x00b0:
                com.xiaomi.smarthome.newui.card.profile.YeelightControlView r0 = com.xiaomi.smarthome.newui.card.profile.YeelightControlView.this     // Catch:{ all -> 0x0111 }
                com.xiaomi.smarthome.newui.card.profile.YeelightControlView$ActionListener r0 = r0.au     // Catch:{ all -> 0x0111 }
                if (r0 == 0) goto L_0x010f
                com.xiaomi.smarthome.newui.card.profile.YeelightControlView r0 = com.xiaomi.smarthome.newui.card.profile.YeelightControlView.this     // Catch:{ all -> 0x0111 }
                com.xiaomi.smarthome.newui.card.profile.YeelightControlView$ActionListener r0 = r0.au     // Catch:{ all -> 0x0111 }
                com.xiaomi.smarthome.newui.card.profile.YeelightControlView r1 = com.xiaomi.smarthome.newui.card.profile.YeelightControlView.this     // Catch:{ all -> 0x0111 }
                int r1 = r1.ak     // Catch:{ all -> 0x0111 }
                r0.c(r1)     // Catch:{ all -> 0x0111 }
                goto L_0x010f
            L_0x00c8:
                com.xiaomi.smarthome.newui.card.profile.YeelightControlView r0 = com.xiaomi.smarthome.newui.card.profile.YeelightControlView.this     // Catch:{ all -> 0x0111 }
                com.xiaomi.smarthome.newui.card.profile.YeelightControlView$TouchDirection r0 = r0.Q     // Catch:{ all -> 0x0111 }
                com.xiaomi.smarthome.newui.card.profile.YeelightControlView$TouchDirection r1 = com.xiaomi.smarthome.newui.card.profile.YeelightControlView.TouchDirection.HORIZONTAL     // Catch:{ all -> 0x0111 }
                if (r0 != r1) goto L_0x00f8
                com.xiaomi.smarthome.newui.card.profile.YeelightControlView r0 = com.xiaomi.smarthome.newui.card.profile.YeelightControlView.this     // Catch:{ all -> 0x0111 }
                com.xiaomi.smarthome.newui.card.profile.YeelightControlView$ActionListener r0 = r0.au     // Catch:{ all -> 0x0111 }
                if (r0 == 0) goto L_0x010f
                com.xiaomi.smarthome.newui.card.profile.YeelightControlView r0 = com.xiaomi.smarthome.newui.card.profile.YeelightControlView.this     // Catch:{ all -> 0x0111 }
                com.xiaomi.smarthome.newui.card.ProductModel r0 = r0.p     // Catch:{ all -> 0x0111 }
                com.xiaomi.smarthome.newui.card.ProductModel$CapabilityType r1 = com.xiaomi.smarthome.newui.card.ProductModel.CapabilityType.CONTROL_CT_H     // Catch:{ all -> 0x0111 }
                boolean r0 = r0.a((com.xiaomi.smarthome.newui.card.ProductModel.CapabilityType) r1)     // Catch:{ all -> 0x0111 }
                if (r0 == 0) goto L_0x010f
                com.xiaomi.smarthome.newui.card.profile.YeelightControlView r0 = com.xiaomi.smarthome.newui.card.profile.YeelightControlView.this     // Catch:{ all -> 0x0111 }
                com.xiaomi.smarthome.newui.card.profile.YeelightControlView$ActionListener r0 = r0.au     // Catch:{ all -> 0x0111 }
                com.xiaomi.smarthome.newui.card.profile.YeelightControlView r1 = com.xiaomi.smarthome.newui.card.profile.YeelightControlView.this     // Catch:{ all -> 0x0111 }
                int r1 = r1.aj     // Catch:{ all -> 0x0111 }
                r0.b(r1)     // Catch:{ all -> 0x0111 }
                goto L_0x010f
            L_0x00f8:
                com.xiaomi.smarthome.newui.card.profile.YeelightControlView r0 = com.xiaomi.smarthome.newui.card.profile.YeelightControlView.this     // Catch:{ all -> 0x0111 }
                com.xiaomi.smarthome.newui.card.profile.YeelightControlView$ActionListener r0 = r0.au     // Catch:{ all -> 0x0111 }
                if (r0 == 0) goto L_0x010f
                com.xiaomi.smarthome.newui.card.profile.YeelightControlView r0 = com.xiaomi.smarthome.newui.card.profile.YeelightControlView.this     // Catch:{ all -> 0x0111 }
                com.xiaomi.smarthome.newui.card.profile.YeelightControlView$ActionListener r0 = r0.au     // Catch:{ all -> 0x0111 }
                com.xiaomi.smarthome.newui.card.profile.YeelightControlView r1 = com.xiaomi.smarthome.newui.card.profile.YeelightControlView.this     // Catch:{ all -> 0x0111 }
                int r1 = r1.ag     // Catch:{ all -> 0x0111 }
                r0.a((int) r1)     // Catch:{ all -> 0x0111 }
            L_0x010f:
                monitor-exit(r2)
                return
            L_0x0111:
                r0 = move-exception
                monitor-exit(r2)
                throw r0
            */
            throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.smarthome.newui.card.profile.YeelightControlView.SendCommandTimerTask.run():void");
        }
    }

    public void setActivity(Activity activity) {
        this.az = activity;
    }

    public Activity getActivity() {
        return this.az;
    }

    private int getBright() {
        int i2 = this.ad;
        if (i2 == 4) {
            return this.ah;
        }
        if (i2 != 6) {
            return this.ag;
        }
        return this.ai;
    }

    public void setGuideImage(int i2) {
        if (i2 == -1) {
            this.n.setImageDrawable((Drawable) null);
        } else {
            this.n.setImageResource(i2);
        }
    }

    public ProductModel getProductModel() {
        return this.p;
    }

    public void setBgUri(Uri uri) {
        this.as = uri;
    }
}
