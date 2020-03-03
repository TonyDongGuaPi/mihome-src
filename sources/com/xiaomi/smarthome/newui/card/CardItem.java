package com.xiaomi.smarthome.newui.card;

import android.animation.ValueAnimator;
import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.os.Vibrator;
import android.support.annotation.NonNull;
import android.support.v4.view.ViewCompat;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import com.miui.tsmclient.util.StringUtils;
import com.xiaomi.smarthome.application.CommonApplication;
import com.xiaomi.smarthome.device.Device;
import com.xiaomi.smarthome.library.common.util.DisplayUtils;
import com.xiaomi.smarthome.miotcard.R;
import com.xiaomi.smarthome.newui.card.BaseCardRender;
import com.xiaomi.smarthome.newui.card.Card;
import com.xiaomi.smarthome.newui.card.Card.CardType;
import com.xiaomi.smarthome.newui.card.profile.PropItem;
import com.xiaomi.smarthome.newui.widget.DeviceDownloadItemViewWrapper;
import com.xiaomi.smarthome.newui.widget.cards.UpdateInterceptor;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import org.json.JSONObject;

public abstract class CardItem<C extends Card<E>, E extends Card.CardType<T>, T> implements ICardPropObserver, IDeviceStateObserver, UpdateInterceptor {
    public static final int b = -3837;
    public static final int c = 0;
    public static final int d = 1;
    public static final int e = 2;
    public static final int f = 3;
    public static final int g = 4;
    public static final int h = 5;
    public static final int i = 6;
    public static final int j = 7;
    public static final int k = 8;
    public static final int l = 9;
    public static final int m = 10;
    public static final int n = 11;
    public static final int o = 12;
    public static final int p = 13;
    public static final int q = 14;
    public static final int r = 15;
    public static final int s = 16;
    public static final int t = 17;
    public static final int u = 18;
    public double A;
    protected DeviceDownloadItemViewWrapper B;
    protected BaseCardRender<C, E, T> C;
    protected Vibrator D;
    protected Activity E;
    protected final int F = 200;
    public final E G;
    protected BaseCardRender.DataInitState H = BaseCardRender.DataInitState.NOT;
    private Animation I = new ScaleAnimation(1.0f, 0.9f, 1.0f, 0.9f, 1, 0.5f, 1, 0.5f);
    private boolean J = false;
    private Map<View, ValueAnimator> K = new HashMap();

    /* renamed from: a  reason: collision with root package name */
    private ICardPropChangeCtl f20473a;
    public final T[] v;
    public String w;
    public int x;
    public List<Operation> y;
    public String z;

    public void a(int i2) {
    }

    public void e() {
    }

    public void f() {
    }

    public void onUpdateViewBackgroud(@NonNull View view, int i2, int i3) {
    }

    public void onUpdateViewScale(@NonNull View view, int i2, float f2) {
    }

    public void onUpdateViewTransitionY(@NonNull View view, int i2, float f2) {
    }

    public void onUpdateViewZ(@NonNull View view, int i2, float f2) {
    }

    public CardItem(E e2, T[] tArr) {
        this.G = e2;
        this.v = tArr;
        if (e2 != null) {
            this.w = e2.c;
            this.x = e2.b;
            this.A = e2.f20466a;
            this.y = e2.d;
            this.z = e2.f;
        }
    }

    public void a(BaseCardRender.DataInitState dataInitState) {
        this.H = dataInitState;
    }

    public void a(DeviceDownloadItemViewWrapper deviceDownloadItemViewWrapper) {
        this.B = deviceDownloadItemViewWrapper;
    }

    /* JADX WARNING: Removed duplicated region for block: B:28:0x00c7  */
    /* JADX WARNING: Removed duplicated region for block: B:30:0x00e6  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.lang.String a(java.util.Map<java.lang.String, java.lang.String> r6) {
        /*
            if (r6 != 0) goto L_0x0005
            java.lang.String r6 = ""
            return r6
        L_0x0005:
            com.xiaomi.smarthome.frame.core.CoreApi r0 = com.xiaomi.smarthome.frame.core.CoreApi.a()
            java.util.Locale r0 = r0.I()
            if (r0 != 0) goto L_0x0013
            java.util.Locale r0 = java.util.Locale.getDefault()
        L_0x0013:
            r1 = 0
            java.util.Locale r2 = java.util.Locale.CHINA     // Catch:{ Exception -> 0x00c0 }
            boolean r2 = com.xiaomi.smarthome.newui.utils.LanguageUtil.a(r0, r2)     // Catch:{ Exception -> 0x00c0 }
            if (r2 == 0) goto L_0x0026
            java.lang.String r2 = "zh_cn"
            java.lang.Object r2 = r6.get(r2)     // Catch:{ Exception -> 0x00c0 }
            java.lang.String r2 = (java.lang.String) r2     // Catch:{ Exception -> 0x00c0 }
        L_0x0024:
            r1 = r2
            goto L_0x0053
        L_0x0026:
            java.util.Locale r2 = java.util.Locale.TRADITIONAL_CHINESE     // Catch:{ Exception -> 0x00c0 }
            boolean r2 = com.xiaomi.smarthome.newui.utils.LanguageUtil.a(r0, r2)     // Catch:{ Exception -> 0x00c0 }
            if (r2 == 0) goto L_0x0037
            java.lang.String r2 = "zh_tw"
            java.lang.Object r2 = r6.get(r2)     // Catch:{ Exception -> 0x00c0 }
            java.lang.String r2 = (java.lang.String) r2     // Catch:{ Exception -> 0x00c0 }
            goto L_0x0024
        L_0x0037:
            java.util.Locale r2 = com.xiaomi.smarthome.newui.utils.LanguageUtil.f20743a     // Catch:{ Exception -> 0x00c0 }
            boolean r2 = com.xiaomi.smarthome.newui.utils.LanguageUtil.a(r0, r2)     // Catch:{ Exception -> 0x00c0 }
            if (r2 == 0) goto L_0x0048
            java.lang.String r2 = "zh_hk"
            java.lang.Object r2 = r6.get(r2)     // Catch:{ Exception -> 0x00c0 }
            java.lang.String r2 = (java.lang.String) r2     // Catch:{ Exception -> 0x00c0 }
            goto L_0x0024
        L_0x0048:
            java.lang.String r2 = r0.toString()     // Catch:{ Exception -> 0x00c0 }
            java.lang.Object r2 = r6.get(r2)     // Catch:{ Exception -> 0x00c0 }
            java.lang.String r2 = (java.lang.String) r2     // Catch:{ Exception -> 0x00c0 }
            goto L_0x0024
        L_0x0053:
            if (r1 != 0) goto L_0x0076
            java.lang.String r2 = r0.getLanguage()     // Catch:{ Exception -> 0x00c0 }
            java.lang.String r3 = "mijia-card"
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x00c0 }
            r4.<init>()     // Catch:{ Exception -> 0x00c0 }
            java.lang.String r5 = "CardItem.getLanguageName loading getLanguage:"
            r4.append(r5)     // Catch:{ Exception -> 0x00c0 }
            r4.append(r2)     // Catch:{ Exception -> 0x00c0 }
            java.lang.String r4 = r4.toString()     // Catch:{ Exception -> 0x00c0 }
            com.xiaomi.smarthome.core.server.internal.util.LogUtil.c(r3, r4)     // Catch:{ Exception -> 0x00c0 }
            java.lang.Object r2 = r6.get(r2)     // Catch:{ Exception -> 0x00c0 }
            java.lang.String r2 = (java.lang.String) r2     // Catch:{ Exception -> 0x00c0 }
            r1 = r2
        L_0x0076:
            if (r1 != 0) goto L_0x0099
            java.lang.String r2 = r0.getISO3Language()     // Catch:{ Exception -> 0x00c0 }
            java.lang.String r3 = "mijia-card"
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x00c0 }
            r4.<init>()     // Catch:{ Exception -> 0x00c0 }
            java.lang.String r5 = "CardItem.getLanguageName loading iso3Language:"
            r4.append(r5)     // Catch:{ Exception -> 0x00c0 }
            r4.append(r2)     // Catch:{ Exception -> 0x00c0 }
            java.lang.String r4 = r4.toString()     // Catch:{ Exception -> 0x00c0 }
            com.xiaomi.smarthome.core.server.internal.util.LogUtil.c(r3, r4)     // Catch:{ Exception -> 0x00c0 }
            java.lang.Object r2 = r6.get(r2)     // Catch:{ Exception -> 0x00c0 }
            java.lang.String r2 = (java.lang.String) r2     // Catch:{ Exception -> 0x00c0 }
            r1 = r2
        L_0x0099:
            if (r1 != 0) goto L_0x00c4
            java.lang.String r0 = r0.getCountry()     // Catch:{ Exception -> 0x00c0 }
            java.lang.String r0 = com.xiaomi.smarthome.newui.card.CardItemUtils.a((java.lang.String) r0)     // Catch:{ Exception -> 0x00c0 }
            java.lang.String r2 = "mijia-card"
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x00c0 }
            r3.<init>()     // Catch:{ Exception -> 0x00c0 }
            java.lang.String r4 = "CardItem.getLanguageName loading country:"
            r3.append(r4)     // Catch:{ Exception -> 0x00c0 }
            r3.append(r0)     // Catch:{ Exception -> 0x00c0 }
            java.lang.String r3 = r3.toString()     // Catch:{ Exception -> 0x00c0 }
            com.xiaomi.smarthome.core.server.internal.util.LogUtil.c(r2, r3)     // Catch:{ Exception -> 0x00c0 }
            java.lang.Object r0 = r6.get(r0)     // Catch:{ Exception -> 0x00c0 }
            java.lang.String r0 = (java.lang.String) r0     // Catch:{ Exception -> 0x00c0 }
            goto L_0x00c5
        L_0x00c0:
            r0 = move-exception
            r0.printStackTrace()
        L_0x00c4:
            r0 = r1
        L_0x00c5:
            if (r0 != 0) goto L_0x00e6
            java.lang.String r0 = "en"
            java.lang.Object r6 = r6.get(r0)
            java.lang.String r6 = (java.lang.String) r6
            java.lang.String r0 = "mijia-card"
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            java.lang.String r2 = "CardItem.getLanguageName not match return default en:"
            r1.append(r2)
            r1.append(r6)
            java.lang.String r1 = r1.toString()
            com.xiaomi.smarthome.core.server.internal.util.LogUtil.c(r0, r1)
            return r6
        L_0x00e6:
            java.lang.String r6 = "mijia-card"
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            java.lang.String r2 = "CardItem.getLanguageName match result:"
            r1.append(r2)
            r1.append(r0)
            java.lang.String r1 = r1.toString()
            com.xiaomi.smarthome.core.server.internal.util.LogUtil.c(r6, r1)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.smarthome.newui.card.CardItem.a(java.util.Map):java.lang.String");
    }

    public static String a(String str) {
        try {
            if (TextUtils.isEmpty(str)) {
                return null;
            }
            if (str.equals("null")) {
                return null;
            }
            StringBuilder sb = new StringBuilder();
            for (int length = str.length(); length > 0; length -= 2) {
                sb.append(str.substring(length - 2, length));
            }
            String sb2 = sb.reverse().toString();
            long j2 = 0;
            for (int i2 = 0; i2 < sb2.length(); i2++) {
                char charAt = sb2.charAt(i2);
                if (charAt >= '0' && charAt <= '9') {
                    double d2 = (double) j2;
                    double d3 = (double) (charAt - '0');
                    double pow = Math.pow(16.0d, (double) i2);
                    Double.isNaN(d3);
                    Double.isNaN(d2);
                    j2 = (long) (d2 + (d3 * pow));
                } else if (charAt >= 'a' && charAt <= 'f') {
                    double d4 = (double) j2;
                    double d5 = (double) ((charAt - 'a') + 10);
                    double pow2 = Math.pow(16.0d, (double) i2);
                    Double.isNaN(d5);
                    Double.isNaN(d4);
                    j2 = (long) (d4 + (d5 * pow2));
                }
            }
            return String.valueOf(j2);
        } catch (Exception e2) {
            e2.printStackTrace();
            return "";
        }
    }

    /* access modifiers changed from: protected */
    public View a(ViewGroup viewGroup, int i2) {
        View inflate = LayoutInflater.from(viewGroup.getContext()).inflate(i2, viewGroup, false);
        inflate.setTag(b, this.G);
        viewGroup.addView(inflate);
        this.E = (Activity) viewGroup.getContext();
        return inflate;
    }

    /* access modifiers changed from: protected */
    public float a(Context context) {
        return context.getResources().getDimension(R.dimen.miui10_card_width);
    }

    /* access modifiers changed from: protected */
    public void a(View view, int i2, int i3) {
        if (i3 != i2 - 1) {
            View findViewById = view.findViewById(R.id.card_item_divider);
            ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) findViewById.getLayoutParams();
            marginLayoutParams.setMargins(DisplayUtils.a(view.getContext(), 13.0f), 0, DisplayUtils.a(view.getContext(), 13.0f), 0);
            findViewById.setLayoutParams(marginLayoutParams);
            findViewById.requestLayout();
        }
    }

    /* access modifiers changed from: protected */
    public boolean j() {
        return this.J;
    }

    /* access modifiers changed from: protected */
    public void a(boolean z2) {
        this.J = z2;
    }

    public int b(String str) {
        Resources resources = CommonApplication.getAppContext().getResources();
        if (TextUtils.isEmpty(str)) {
            return resources.getColor(R.color.com_facebook_blue);
        }
        int identifier = resources.getIdentifier(str, "color", CommonApplication.getAppContext().getPackageName());
        return identifier == 0 ? resources.getColor(R.color.com_facebook_blue) : identifier;
    }

    /* access modifiers changed from: protected */
    public void a(View view) {
        b(view);
        Device k2 = k();
        Log.i("mijia-card", "card click model:" + k2.model + " did:" + k2.did + " cardType:" + this.G);
    }

    public static String a(Object obj) {
        if (obj == null || TextUtils.isEmpty(String.valueOf(obj))) {
            return "";
        }
        try {
            Date date = new Date(Long.parseLong(obj.toString()) * 1000);
            Date date2 = new Date();
            if (date.getYear() == date2.getYear() && date.getMonth() == date2.getMonth() && date.getDay() == date2.getDay()) {
                return new SimpleDateFormat("HH:mm", Locale.getDefault()).format(date);
            }
            return new SimpleDateFormat(StringUtils.EXPECT_DATE_FORMAT, Locale.getDefault()).format(date);
        } catch (Exception e2) {
            e2.printStackTrace();
            return "";
        }
    }

    public static String b(Object obj) {
        if (obj == null || TextUtils.isEmpty(String.valueOf(obj))) {
            return "";
        }
        try {
            return new SimpleDateFormat("HH:mm", Locale.getDefault()).format(new Date(Long.parseLong(obj.toString()) * 1000));
        } catch (Exception e2) {
            e2.printStackTrace();
            return "";
        }
    }

    public void a(ICardPropChangeCtl iCardPropChangeCtl) {
        this.f20473a = iCardPropChangeCtl;
    }

    /* access modifiers changed from: protected */
    public void b(final View view) {
        ValueAnimator valueAnimator = this.K.get(view);
        if (valueAnimator != null) {
            valueAnimator.cancel();
            valueAnimator = null;
        }
        if (valueAnimator == null) {
            valueAnimator = ValueAnimator.ofFloat(new float[]{0.0f, 1.0f});
            valueAnimator.setDuration(200);
        }
        valueAnimator.removeAllListeners();
        valueAnimator.removeAllUpdateListeners();
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                float floatValue = ((Float) valueAnimator.getAnimatedValue()).floatValue();
                if (floatValue < 0.5f) {
                    float f = 1.0f - floatValue;
                    view.setScaleX(f);
                    view.setScaleY(f);
                    return;
                }
                view.setScaleX(floatValue);
                view.setScaleY(floatValue);
            }
        });
        valueAnimator.start();
    }

    public void onUpdateViewAlpha(@NonNull View view, int i2, float f2) {
        if (ViewCompat.getAlpha(view) != f2) {
            ViewCompat.setAlpha(view, f2);
        }
    }

    public void b(BaseCardRender.DataInitState dataInitState) {
        this.H = dataInitState;
    }

    public static boolean c(Object obj) {
        return obj == null || obj.equals(JSONObject.NULL);
    }

    public void i() {
        Map<View, ValueAnimator> map = this.K;
        for (ValueAnimator cancel : map.values()) {
            cancel.cancel();
        }
        map.clear();
        DeviceDownloadItemViewWrapper deviceDownloadItemViewWrapper = this.B;
        if (deviceDownloadItemViewWrapper != null) {
            deviceDownloadItemViewWrapper.a((DeviceDownloadItemViewWrapper.ProgressCallback) null);
        }
        this.E = null;
        this.B = null;
        this.f20473a = null;
        this.D = null;
    }

    public void a(ViewGroup viewGroup, int i2, int i3) {
        this.D = (Vibrator) viewGroup.getContext().getSystemService("vibrator");
        this.J = false;
    }

    public void a(BaseCardRender baseCardRender) {
        this.C = baseCardRender;
    }

    @NonNull
    public Device k() {
        return this.C.e;
    }

    @NonNull
    public C l() {
        return this.C.b;
    }

    public T m() {
        if (this.v == null || this.v.length <= 0) {
            return null;
        }
        return this.v[0];
    }

    public PropItem n() {
        return this.G.a();
    }

    public boolean a(Device device, Operation operation) {
        if (!device.isOnline || device.isSharedReadOnly() || this.H == BaseCardRender.DataInitState.NOT || this.H == BaseCardRender.DataInitState.DOING) {
            return false;
        }
        return this.G.a(device, operation);
    }

    public enum State {
        NOR("normal"),
        SELECTED("selected"),
        UNABLE("unable");
        
        String desc;

        private State(String str) {
            this.desc = str;
        }

        public String toString() {
            return this.desc;
        }
    }
}
