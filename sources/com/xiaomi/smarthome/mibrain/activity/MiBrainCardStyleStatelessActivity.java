package com.xiaomi.smarthome.mibrain.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewPropertyAnimator;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.device.Device;
import com.xiaomi.smarthome.device.SmartHomeDeviceHelper;
import com.xiaomi.smarthome.device.SmartHomeDeviceManager;
import com.xiaomi.smarthome.framework.page.BaseActivity;
import com.xiaomi.smarthome.mibrain.model.aitips.AiDevice;
import com.xiaomi.smarthome.mibrain.model.aitips.AiTipsDevice;
import com.xiaomi.smarthome.mibrain.model.aitips.Tips;
import com.xiaomi.smarthome.shop.utils.DisplayUtils;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.Typography;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 3}, d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u0000 \u001d2\u00020\u0001:\u0001\u001dB\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\u0012\u001a\u00020\u0013H\u0002J\b\u0010\u0014\u001a\u00020\u0013H\u0002J\u0010\u0010\u0015\u001a\u00020\u00062\u0006\u0010\u0016\u001a\u00020\u0006H\u0002J\b\u0010\u0017\u001a\u00020\u0013H\u0016J\u0012\u0010\u0018\u001a\u00020\u00132\b\u0010\u0019\u001a\u0004\u0018\u00010\u001aH\u0014J\b\u0010\u001b\u001a\u00020\u0013H\u0002J\b\u0010\u001c\u001a\u00020\u0013H\u0002R\u0010\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X.¢\u0006\u0002\n\u0000R\u0010\u0010\u0007\u001a\u0004\u0018\u00010\u0004X\u000e¢\u0006\u0002\n\u0000R\u001a\u0010\b\u001a\u00020\u0006X.¢\u0006\u000e\n\u0000\u001a\u0004\b\t\u0010\n\"\u0004\b\u000b\u0010\fR\u000e\u0010\r\u001a\u00020\u0006X.¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u0006X.¢\u0006\u0002\n\u0000R\"\u0010\u000f\u001a\u0016\u0012\u0004\u0012\u00020\u0006\u0018\u00010\u0010j\n\u0012\u0004\u0012\u00020\u0006\u0018\u0001`\u0011X\u000e¢\u0006\u0002\n\u0000¨\u0006\u001e"}, d2 = {"Lcom/xiaomi/smarthome/mibrain/activity/MiBrainCardStyleStatelessActivity;", "Lcom/xiaomi/smarthome/framework/page/BaseActivity;", "()V", "mCtrlDevice", "Lcom/xiaomi/smarthome/device/Device;", "mCtrlDid", "", "mDevice", "mDid", "getMDid", "()Ljava/lang/String;", "setMDid", "(Ljava/lang/String;)V", "mMethod", "mQueryPrefix", "mTips", "Ljava/util/ArrayList;", "Lkotlin/collections/ArrayList;", "animHide", "", "animShow", "getDeviceCateString", "model", "onBackPressed", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "setupSpeakView", "setupTipsView", "Companion", "app_GooglePlayRelease"}, k = 1, mv = {1, 1, 13})
public final class MiBrainCardStyleStatelessActivity extends BaseActivity {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    @NotNull
    public static final String TAG = "MiBrainCardStyleStatelessActivity";
    private static final String g = "arg_method";
    private static final String h = "arg_ctrl_did";
    private static final String i = "arg_did";
    private static final String j = "arg_tips";
    private static final String k = "arg_query";

    /* renamed from: a  reason: collision with root package name */
    private String f10615a;
    /* access modifiers changed from: private */
    public Device b;
    /* access modifiers changed from: private */
    public Device c;
    /* access modifiers changed from: private */
    public String d;
    /* access modifiers changed from: private */
    public String e;
    /* access modifiers changed from: private */
    public ArrayList<String> f;
    private HashMap l;
    @NotNull
    public String mDid;

    public void _$_clearFindViewByIdCache() {
        if (this.l != null) {
            this.l.clear();
        }
    }

    public View _$_findCachedViewById(int i2) {
        if (this.l == null) {
            this.l = new HashMap();
        }
        View view = (View) this.l.get(Integer.valueOf(i2));
        if (view != null) {
            return view;
        }
        View findViewById = findViewById(i2);
        this.l.put(Integer.valueOf(i2), findViewById);
        return findViewById;
    }

    @NotNull
    public static final /* synthetic */ String access$getMMethod$p(MiBrainCardStyleStatelessActivity miBrainCardStyleStatelessActivity) {
        String str = miBrainCardStyleStatelessActivity.d;
        if (str == null) {
            Intrinsics.c("mMethod");
        }
        return str;
    }

    @NotNull
    public static final /* synthetic */ String access$getMQueryPrefix$p(MiBrainCardStyleStatelessActivity miBrainCardStyleStatelessActivity) {
        String str = miBrainCardStyleStatelessActivity.e;
        if (str == null) {
            Intrinsics.c("mQueryPrefix");
        }
        return str;
    }

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u001e\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u0004R\u000e\u0010\u0003\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000¨\u0006\u0011"}, d2 = {"Lcom/xiaomi/smarthome/mibrain/activity/MiBrainCardStyleStatelessActivity$Companion;", "", "()V", "ARG_CTRL_DID", "", "ARG_DID", "ARG_METHOD", "ARG_QUERY", "ARG_TIPS", "TAG", "start", "", "context", "Landroid/content/Context;", "aiDevice", "Lcom/xiaomi/smarthome/mibrain/model/aitips/AiDevice;", "queryPrefix", "app_GooglePlayRelease"}, k = 1, mv = {1, 1, 13})
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final void a(@NotNull Context context, @NotNull AiDevice aiDevice, @NotNull String str) {
            Intrinsics.f(context, "context");
            Intrinsics.f(aiDevice, "aiDevice");
            Intrinsics.f(str, "queryPrefix");
            Intent intent = new Intent(context, MiBrainCardStyleStatelessActivity.class);
            intent.putExtra(MiBrainCardStyleStatelessActivity.h, aiDevice.a());
            intent.putExtra(MiBrainCardStyleStatelessActivity.g, aiDevice.b());
            AiTipsDevice c = aiDevice.c();
            Intrinsics.b(c, "aiDevice.device");
            intent.putExtra("arg_did", c.g());
            intent.putExtra(MiBrainCardStyleStatelessActivity.k, str);
            AiTipsDevice c2 = aiDevice.c();
            Intrinsics.b(c2, "aiDevice.device");
            List<Tips> h = c2.h();
            if (h == null || h.isEmpty()) {
                Toast.makeText(context, R.string.failed, 0).show();
                return;
            }
            Tips tips = h.get(0);
            Intrinsics.b(tips, "tips[0]");
            ArrayList arrayList = new ArrayList(tips.b());
            if (h.size() > 1) {
                Tips tips2 = h.get(1);
                Intrinsics.b(tips2, "tips[1]");
                List<String> b = tips2.b();
                if (b != null) {
                    arrayList.addAll(b);
                }
            }
            intent.putStringArrayListExtra(MiBrainCardStyleStatelessActivity.j, arrayList.size() > 3 ? new ArrayList(arrayList.subList(0, 3)) : arrayList);
            context.startActivity(intent);
        }
    }

    @NotNull
    public final String getMDid() {
        String str = this.mDid;
        if (str == null) {
            Intrinsics.c("mDid");
        }
        return str;
    }

    public final void setMDid(@NotNull String str) {
        Intrinsics.f(str, "<set-?>");
        this.mDid = str;
    }

    /* access modifiers changed from: protected */
    public void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
        overridePendingTransition(0, 0);
        setContentView(R.layout.activity_mi_brain_card_style_stateless);
        findViewById(R.id.close_btn).setOnClickListener(new MiBrainCardStyleStatelessActivity$onCreate$1(this));
        String stringExtra = getIntent().getStringExtra(h);
        Intrinsics.b(stringExtra, "intent.getStringExtra(ARG_CTRL_DID)");
        this.f10615a = stringExtra;
        String stringExtra2 = getIntent().getStringExtra("arg_did");
        Intrinsics.b(stringExtra2, "intent.getStringExtra(ARG_DID)");
        this.mDid = stringExtra2;
        String stringExtra3 = getIntent().getStringExtra(g);
        Intrinsics.b(stringExtra3, "intent.getStringExtra(ARG_METHOD)");
        this.d = stringExtra3;
        String stringExtra4 = getIntent().getStringExtra(k);
        Intrinsics.b(stringExtra4, "intent.getStringExtra(ARG_QUERY)");
        this.e = stringExtra4;
        this.f = getIntent().getStringArrayListExtra(j);
        SmartHomeDeviceManager a2 = SmartHomeDeviceManager.a();
        String str = this.f10615a;
        if (str == null) {
            Intrinsics.c("mCtrlDid");
        }
        this.b = a2.b(str);
        SmartHomeDeviceManager a3 = SmartHomeDeviceManager.a();
        String str2 = this.mDid;
        if (str2 == null) {
            Intrinsics.c("mDid");
        }
        this.c = a3.b(str2);
        if (!(this.b == null || this.c == null)) {
            String str3 = this.d;
            if (str3 == null) {
                Intrinsics.c("mMethod");
            }
            if (!TextUtils.isEmpty(str3) && this.f != null) {
                ArrayList<String> arrayList = this.f;
                if (arrayList == null) {
                    Intrinsics.a();
                }
                if (!arrayList.isEmpty()) {
                    TextView textView = (TextView) findViewById(R.id.manual_desc);
                    Device device = this.b;
                    if (device == null) {
                        Intrinsics.a();
                    }
                    if (!device.isOnline) {
                        textView.setText(R.string.mi_brain_xiaoai_offline_tips);
                    }
                    b();
                    a();
                    d();
                    return;
                }
            }
        }
        finish();
    }

    /* access modifiers changed from: private */
    public final String a(String str) {
        if (TextUtils.isEmpty(str)) {
            return "";
        }
        SmartHomeDeviceHelper a2 = SmartHomeDeviceHelper.a();
        Intrinsics.b(a2, "SmartHomeDeviceHelper.getInstance()");
        String str2 = a2.b().c(str).f15435a;
        Intrinsics.b(str2, "tagManager.getCategoryForModel(model).id");
        return str2;
    }

    private final void a() {
        ImageView imageView = (ImageView) findViewById(R.id.speak_decor_bg);
        Device device = this.b;
        if (device == null) {
            Intrinsics.a();
        }
        if (!device.isOnline) {
            Intrinsics.b(imageView, "speakDecor");
            imageView.setEnabled(false);
            imageView.setAlpha(0.5f);
            return;
        }
        imageView.setOnClickListener(new MiBrainCardStyleStatelessActivity$setupSpeakView$1(this, imageView));
    }

    private final void b() {
        String str;
        TextView textView = (TextView) findViewById(R.id.tips);
        ArrayList<String> arrayList = this.f;
        if (arrayList != null) {
            Iterable<String> iterable = arrayList;
            Collection arrayList2 = new ArrayList(CollectionsKt.a(iterable, 10));
            for (String str2 : iterable) {
                arrayList2.add(Typography.y + str2 + "”\n");
            }
            Iterator it = ((List) arrayList2).iterator();
            if (it.hasNext()) {
                Object next = it.next();
                while (it.hasNext()) {
                    next = ((String) next) + ((String) it.next());
                }
                str = (String) next;
            } else {
                throw new UnsupportedOperationException("Empty collection can't be reduced.");
            }
        } else {
            str = null;
        }
        Intrinsics.b(textView, "tipsView");
        textView.setText(str);
    }

    /* access modifiers changed from: private */
    public final void c() {
        ViewPropertyAnimator translationY = findViewById(R.id.card).animate().translationY((float) DisplayUtils.a((Activity) this, 600.0f));
        ViewPropertyAnimator withEndAction = findViewById(R.id.mask).animate().alpha(0.0f).withEndAction(new MiBrainCardStyleStatelessActivity$animHide$maskAnim$1(this));
        translationY.start();
        withEndAction.start();
    }

    private final void d() {
        findViewById(R.id.card).animate().translationY(0.0f).start();
    }

    public void onBackPressed() {
        c();
    }
}
