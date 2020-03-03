package com.xiaomi.smarthome.miui10;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.res.Resources;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.content.LocalBroadcastManager;
import android.text.TextUtils;
import android.util.Log;
import android.util.Pair;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.ImageView;
import android.widget.Toast;
import com.mijia.camera.CameraNativePluginManager;
import com.xiaomi.smarthome.application.CommonApplication;
import com.xiaomi.smarthome.core.entity.plugin.PluginError;
import com.xiaomi.smarthome.core.entity.plugin.PluginRecord;
import com.xiaomi.smarthome.core.server.internal.util.LogUtil;
import com.xiaomi.smarthome.device.Device;
import com.xiaomi.smarthome.device.DeviceUtils;
import com.xiaomi.smarthome.device.MiTVDevice;
import com.xiaomi.smarthome.device.SmartHomeDeviceManager;
import com.xiaomi.smarthome.devicesubscribe.DevicePropSubscriber;
import com.xiaomi.smarthome.frame.AsyncCallback;
import com.xiaomi.smarthome.frame.Error;
import com.xiaomi.smarthome.frame.core.CoreApi;
import com.xiaomi.smarthome.frame.plugin.PluginApi;
import com.xiaomi.smarthome.frame.plugin.RunningProcess;
import com.xiaomi.smarthome.framework.page.ActivityHandler;
import com.xiaomi.smarthome.framework.page.CommonActivity;
import com.xiaomi.smarthome.globalsetting.GlobalSetting;
import com.xiaomi.smarthome.miio.TitleBarUtil;
import com.xiaomi.smarthome.miotcard.R;
import com.xiaomi.smarthome.miui10.MIUI10CardActivity;
import com.xiaomi.smarthome.newui.card.BaseCardRender;
import com.xiaomi.smarthome.newui.card.CameraCardItem;
import com.xiaomi.smarthome.newui.card.Card;
import com.xiaomi.smarthome.newui.card.CardAbstractManager;
import com.xiaomi.smarthome.newui.card.CardItem;
import com.xiaomi.smarthome.newui.card.CardRenderFactory;
import com.xiaomi.smarthome.newui.card.CardRender_1_item;
import com.xiaomi.smarthome.newui.card.ControlCardInfoManager;
import com.xiaomi.smarthome.newui.card.MiotSpecCardManager;
import com.xiaomi.smarthome.newui.card.OnStateChangedListener;
import com.xiaomi.smarthome.newui.card.other.OtherCard;
import com.xiaomi.smarthome.newui.card.other.OtherCardType;
import com.xiaomi.smarthome.newui.card.profile.ProfileCard;
import com.xiaomi.smarthome.newui.card.spec.SpecCard;
import com.xiaomi.smarthome.newui.widget.DeviceDownloadItemViewWrapper;
import com.xiaomi.smarthome.newui.widget.DeviceInstaller;
import com.xiaomi.smarthome.newui.widget.ProgressItemView;
import com.xiaomi.smarthome.newui.widget.micards.DownloadProgressProcessor;
import com.xiaomi.smarthome.stat.STAT;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class MIUI10CardActivity extends CommonActivity implements ActivityHandler.HandleMessage, DeviceInstaller, DownloadProgressProcessor {
    public static final String ACTION_OPENCARD = "miui.action_open_card";
    public static final String ACTION_REFRESH_FAILED = "miui.action_refresh_failed";
    public static final String ACTION_REFRESH_SUCCESS = "miui.action_refresh_success";
    public static final int RESULT_CODE_HIDE_CARD = 20;
    public static String sRoomName = "";

    /* renamed from: a  reason: collision with root package name */
    private ViewGroup f20130a;
    /* access modifiers changed from: private */
    public Device b;
    /* access modifiers changed from: private */
    public ProgressItemView c;
    /* access modifiers changed from: private */
    public View d;
    private ImageView e;
    /* access modifiers changed from: private */
    public DeviceDownloadItemViewWrapper f;
    /* access modifiers changed from: private */
    public boolean g = false;
    /* access modifiers changed from: private */
    public long h = -1;
    /* access modifiers changed from: private */
    public boolean i = false;
    /* access modifiers changed from: private */
    public long j = -1;
    /* access modifiers changed from: private */
    public boolean k = false;
    /* access modifiers changed from: private */
    public RectF l = null;
    /* access modifiers changed from: private */
    public BaseCardRender<? extends Card, ? extends Card.CardType, ?> m;
    Card<? extends Card.CardType> mCard;
    PluginApi.SendMessageHandle mSendMessageHandle;
    /* access modifiers changed from: private */
    public boolean n = true;
    /* access modifiers changed from: private */
    public boolean o = false;
    private boolean p = false;
    PointHandler pointHandler;
    /* access modifiers changed from: private */
    public String q;
    /* access modifiers changed from: private */
    public ActivityControl r = new ActivityControl(this);
    private BroadcastReceiver s = new BroadcastReceiver() {
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if (action != null) {
                if (action.equals("miui.action_refresh_success")) {
                    MIUI10CardActivity.this.t.post(new Runnable() {
                        public final void run() {
                            MIUI10CardActivity.AnonymousClass1.this.c();
                        }
                    });
                } else if (action.equals("miui.action_refresh_failed")) {
                    MIUI10CardActivity.this.t.post(new Runnable() {
                        public final void run() {
                            MIUI10CardActivity.AnonymousClass1.this.b();
                        }
                    });
                } else {
                    MIUI10CardActivity.this.t.post(new Runnable() {
                        public final void run() {
                            MIUI10CardActivity.AnonymousClass1.this.a();
                        }
                    });
                }
            }
        }

        /* access modifiers changed from: private */
        public /* synthetic */ void c() {
            boolean unused = MIUI10CardActivity.this.n = true;
            ControlCardInfoManager.f().a(true, (AsyncCallback<Object, Error>) new AsyncCallback<Object, Error>() {
                public void onFailure(Error error) {
                }

                public void onSuccess(Object obj) {
                    if (MIUI10CardActivity.this.m != null) {
                        MIUI10CardActivity.this.m.a((String) null, (Object) null);
                    }
                }
            }, "updatePropValue");
            MiotSpecCardManager.f().a(true, (AsyncCallback<Object, Error>) new AsyncCallback<Object, Error>() {
                public void onFailure(Error error) {
                }

                public void onSuccess(Object obj) {
                    if (MIUI10CardActivity.this.m != null) {
                        MIUI10CardActivity.this.m.a((String) null, (Object) null);
                    }
                }
            });
            if (MIUI10CardActivity.this.m != null) {
                MIUI10CardActivity.this.m.a(BaseCardRender.DataInitState.DONE);
                MIUI10CardActivity.this.m.a();
            }
        }

        /* access modifiers changed from: private */
        public /* synthetic */ void b() {
            boolean unused = MIUI10CardActivity.this.n = false;
            if (MIUI10CardActivity.this.m != null) {
                MIUI10CardActivity.this.m.a(BaseCardRender.DataInitState.NOT);
                MIUI10CardActivity.this.m.a();
            }
        }

        /* access modifiers changed from: private */
        public /* synthetic */ void a() {
            if (MIUI10CardActivity.this.m != null) {
                MIUI10CardActivity.this.m.a();
            }
        }
    };
    /* access modifiers changed from: private */
    public ActivityHandler t;
    private OnStateChangedListener u = new OnStateChangedListener() {
        public void onStateChanged(String str, String str2, Object obj) {
            if (MIUI10CardActivity.this.m != null && str.equals(MIUI10CardActivity.this.b.did)) {
                MIUI10CardActivity.this.m.a(str2, obj);
            }
        }
    };
    private DevicePropSubscriber v;

    public void handleMessage(Message message) {
    }

    public boolean shouldInstallNow(Device device) {
        return true;
    }

    /* access modifiers changed from: protected */
    @SuppressLint({"NewApi"})
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.t = new ActivityHandler(this);
        TitleBarUtil.a((Activity) this);
        Intent intent = getIntent();
        String stringExtra = intent.getStringExtra("from");
        if (!TextUtils.isEmpty(stringExtra) && stringExtra.equals("miui")) {
            this.o = true;
            getWindow().addFlags(4);
        }
        if (this.o) {
            STAT.d.b(true);
        }
        setContentView(R.layout.miui10_card_activity);
        this.f20130a = (ViewGroup) findViewById(R.id.fl_root);
        this.e = (ImageView) findViewById(R.id.card_mask);
        String stringExtra2 = intent.getStringExtra("model");
        String stringExtra3 = intent.getStringExtra("did");
        sRoomName = intent.getStringExtra("room_name");
        this.b = SmartHomeDeviceManager.a().b(stringExtra3);
        if (this.b == null) {
            this.b = new Device();
            this.b.model = stringExtra2;
            this.b.did = stringExtra3;
            this.b.name = intent.getStringExtra("device_name");
            this.b.isOnline = true;
            this.n = false;
        }
        this.q = this.b.model;
        if (!TextUtils.isEmpty(stringExtra) && stringExtra.equals("miui")) {
            LocalBroadcastManager.getInstance(this).sendBroadcast(new Intent(ACTION_OPENCARD).putExtra("param_did", stringExtra3));
            try {
                getWindow().setNavigationBarColor(0);
            } catch (Exception unused) {
            }
        }
        this.f20130a.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                MIUI10CardActivity.this.onBackPressed();
            }
        });
        a(ControlCardInfoManager.f().j());
        if (this.mCard == null && !TextUtils.isEmpty(this.b.model)) {
            this.mCard = ControlCardInfoManager.f().e(this.b.model);
            StringBuilder sb = new StringBuilder();
            sb.append("getProfileCardInfo model:");
            sb.append(this.b.model);
            sb.append(" did:");
            sb.append(this.b.did);
            sb.append(" card:");
            sb.append(this.mCard == null ? "null" : this.mCard.c);
            LogUtil.c("mijia-card", sb.toString());
        }
        if (this.mCard == null) {
            if (DeviceUtils.a().equals(this.b.did)) {
                OtherCard otherCard = new OtherCard();
                otherCard.b = 8;
                otherCard.c.add(OtherCardType.i());
                this.mCard = otherCard;
                LogUtil.c("mijia-card", "OtherCard createIRType model:" + this.b.model + " did:" + this.b.did + " card:" + this.mCard.c);
            } else if (this.b instanceof MiTVDevice) {
                OtherCard otherCard2 = new OtherCard();
                otherCard2.b = 9;
                otherCard2.c.add(OtherCardType.j());
                this.mCard = otherCard2;
                LogUtil.c("mijia-card", "OtherCard createTVType model:" + this.b.model + " did:" + this.b.did + " card:" + this.mCard.c);
            } else {
                a(ControlCardInfoManager.f().f(intent.getStringExtra("device_card")));
            }
        }
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("miui.action_refresh_success");
        intentFilter.addAction("miui.action_refresh_failed");
        ControlCardInfoManager.f().a(this.u);
        MiotSpecCardManager.f().a(this.u);
        LocalBroadcastManager.getInstance(getContext()).registerReceiver(this.s, intentFilter);
        if (this.mCard == null || this.mCard.b == -1) {
            this.mCard = MiotSpecCardManager.f().d(this.b);
            b();
            if (this.mCard == null) {
                LogUtil.c("mijia-card", "no card model:" + this.q + " did:" + this.b.did);
                return;
            }
            LogUtil.c("mijia-card", "show card model:" + this.q + " specLayoutType:" + this.mCard.b + " did:" + this.b.did);
            return;
        }
        b();
        LogUtil.c("mijia-card", "show card model:" + this.q + " jsonlayoutType:" + this.mCard.b + " did:" + this.b.did);
    }

    /* access modifiers changed from: protected */
    public void onDestroy() {
        super.onDestroy();
        ControlCardInfoManager.f().b(this.u);
        MiotSpecCardManager.f().b(this.u);
        LocalBroadcastManager.getInstance(getContext()).unregisterReceiver(this.s);
        if (this.m != null) {
            this.m.i();
        }
        if (this.v != null) {
            this.v.a();
            this.v = null;
        }
        sRoomName = "";
    }

    /* access modifiers changed from: private */
    public boolean a() {
        if (this.i) {
            return true;
        }
        long currentTimeMillis = System.currentTimeMillis();
        if (this.j <= 0 || currentTimeMillis - this.j >= 1000) {
            return this.i;
        }
        return true;
    }

    /* access modifiers changed from: private */
    public void a(View view, RectF rectF, RectF rectF2) {
        this.e = (ImageView) view.findViewById(R.id.card_mask);
        float centerX = rectF.centerX() - rectF2.centerX();
        float centerY = rectF.centerY() - rectF2.centerY();
        ObjectAnimator ofFloat = ObjectAnimator.ofFloat(view, "translationX", new float[]{centerX, 0.0f});
        ObjectAnimator ofFloat2 = ObjectAnimator.ofFloat(view, "translationY", new float[]{centerY, 0.0f});
        float width = rectF.width() / Math.max(rectF2.width(), 1.0f);
        float height = rectF.height() / Math.max(rectF2.height(), 1.0f);
        ObjectAnimator ofFloat3 = ObjectAnimator.ofFloat(view, "scaleX", new float[]{width, 1.0f});
        ObjectAnimator ofFloat4 = ObjectAnimator.ofFloat(view, "scaleY", new float[]{height, 1.0f});
        ObjectAnimator ofFloat5 = ObjectAnimator.ofFloat(this.e, "alpha", new float[]{1.0f, 0.0f});
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.playTogether(new Animator[]{ofFloat, ofFloat2, ofFloat3, ofFloat4, ofFloat5});
        animatorSet.setDuration(200);
        animatorSet.start();
    }

    private void b(View view, RectF rectF, RectF rectF2) {
        ObjectAnimator ofFloat = ObjectAnimator.ofFloat(view, "translationX", new float[]{0.0f, rectF.centerX() - rectF2.centerX()});
        ObjectAnimator ofFloat2 = ObjectAnimator.ofFloat(view, "translationY", new float[]{0.0f, rectF.centerY() - rectF2.centerY()});
        float width = rectF.width() / rectF2.width();
        float height = rectF.height() / rectF2.height();
        ObjectAnimator ofFloat3 = ObjectAnimator.ofFloat(view, "scaleX", new float[]{1.0f, width});
        ObjectAnimator ofFloat4 = ObjectAnimator.ofFloat(view, "scaleY", new float[]{1.0f, height});
        ObjectAnimator ofFloat5 = ObjectAnimator.ofFloat(this.e, "alpha", new float[]{0.0f, 1.0f});
        ObjectAnimator ofFloat6 = ObjectAnimator.ofFloat(view, "alpha", new float[]{1.0f, 0.0f});
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.playTogether(new Animator[]{ofFloat, ofFloat2, ofFloat3, ofFloat4, ofFloat6, ofFloat5});
        animatorSet.setDuration(200);
        animatorSet.setInterpolator(new AccelerateDecelerateInterpolator());
        animatorSet.start();
    }

    public void onBackPressed() {
        if (!this.p) {
            this.p = true;
            View view = null;
            if (this.m != null) {
                view = this.m.g();
            }
            if (view != null) {
                int[] iArr = new int[2];
                view.getLocationOnScreen(iArr);
                RectF rectF = new RectF((float) iArr[0], (float) iArr[1], (float) (iArr[0] + view.getWidth()), (float) (iArr[1] + view.getHeight()));
                if (this.l != null) {
                    b(view, this.l, rectF);
                }
                this.t.postDelayed(new Runnable() {
                    public void run() {
                        MIUI10CardActivity.this.setResult(20);
                        MIUI10CardActivity.this.finish();
                        MIUI10CardActivity.this.overridePendingTransition(0, 0);
                    }
                }, 200);
                return;
            }
            setResult(20);
            finish();
            overridePendingTransition(0, 0);
        }
    }

    public void finish() {
        super.finish();
        if (this.mSendMessageHandle != null) {
            this.mSendMessageHandle.cancel();
        }
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        super.onResume();
        Log.e("MIUI10CardActivity", "show model - " + this.q);
        STAT.e.a(this.o);
        this.r.b();
    }

    /* access modifiers changed from: protected */
    public void onActivityResult(int i2, int i3, Intent intent) {
        super.onActivityResult(i2, i3, intent);
        if (this.m instanceof CardRender_1_item) {
            ((CardRender_1_item) this.m).a(i3 == -1);
        }
    }

    /* access modifiers changed from: protected */
    public void onPause() {
        super.onPause();
        if (this.m != null) {
            this.m.h();
        }
        c();
        this.r.c();
    }

    private void b() {
        this.m = CardRenderFactory.a(this.mCard, this.f20130a, this, this.b);
        if (this.mCard instanceof ProfileCard) {
            ArrayList arrayList = new ArrayList();
            if (!TextUtils.isEmpty(this.b.did)) {
                arrayList.add(this.b.did);
            }
            ControlCardInfoManager.f().a((List<String>) arrayList, (AsyncCallback<Object, Error>) new AsyncCallback<Object, Error>() {
                public void onSuccess(Object obj) {
                    MIUI10CardActivity.this.m.a((String) null, (Object) null);
                    MIUI10CardActivity.this.e();
                    MIUI10CardActivity.this.a("profile", (Error) null);
                }

                public void onFailure(Error error) {
                    MIUI10CardActivity.this.a("profile", error);
                }
            }, "updatePropValue");
        } else if (this.mCard instanceof SpecCard) {
            ArrayList arrayList2 = new ArrayList();
            Iterator<? extends CardItem<C, E, T>> it = this.m.f20457a.iterator();
            while (it.hasNext()) {
                arrayList2.add((Pair[]) ((CardItem) it.next()).v);
            }
            MiotSpecCardManager.f().a(this.b.did, arrayList2, new AsyncCallback<Object, Error>() {
                public void onSuccess(Object obj) {
                    MIUI10CardActivity.this.m.a((String) null, (Object) null);
                    MIUI10CardActivity.this.d();
                    MIUI10CardActivity.this.a("spec", (Error) null);
                }

                public void onFailure(Error error) {
                    MIUI10CardActivity.this.m.a((String) null, (Object) null);
                    MIUI10CardActivity.this.a("spec", error);
                }
            });
        }
        if (!this.n) {
            this.m.a(BaseCardRender.DataInitState.DOING);
        } else {
            this.m.a(BaseCardRender.DataInitState.DONE);
        }
        this.m.b();
        View g2 = this.m.g();
        if (g2 != null) {
            g2.getViewTreeObserver().addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() {
                public boolean onPreDraw() {
                    if (!MIUI10CardActivity.this.k) {
                        boolean unused = MIUI10CardActivity.this.k = true;
                        View g = MIUI10CardActivity.this.m.g();
                        RectF rectF = new RectF((float) g.getLeft(), (float) g.getTop(), (float) (g.getLeft() + g.getWidth()), (float) (g.getTop() + g.getHeight()));
                        RectF unused2 = MIUI10CardActivity.this.l = (RectF) MIUI10CardActivity.this.getIntent().getParcelableExtra("view_position");
                        if (MIUI10CardActivity.this.l != null) {
                            MIUI10CardActivity.this.a(MIUI10CardActivity.this.m.g(), MIUI10CardActivity.this.l, rectF);
                        }
                    }
                    return true;
                }
            });
            g2.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                }
            });
        }
        this.c = this.m.c();
        this.d = this.m.d();
        if (this.c != null) {
            this.f = new DeviceDownloadItemViewWrapper(this, this.c, this.b);
            this.m.a(this.f);
            PluginRecord d2 = CoreApi.a().d(this.b.model);
            boolean z = d2 != null && d2.k();
            this.d.findViewById(R.id.three_point_loading).setVisibility(8);
            if (z) {
                this.f.setSuccess();
            }
            this.d.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    LogUtil.c("mijia-card", "click more action mEnable:" + MIUI10CardActivity.this.n);
                    STAT.d.a(MIUI10CardActivity.this.o);
                    if (MIUI10CardActivity.this.n) {
                        long currentTimeMillis = System.currentTimeMillis() - MIUI10CardActivity.this.h;
                        if (MIUI10CardActivity.this.g || MIUI10CardActivity.this.a() || (MIUI10CardActivity.this.h > 0 && currentTimeMillis < 1000)) {
                            LogUtil.b("mijia-card", "click more action isDownloading:" + MIUI10CardActivity.this.g);
                            return;
                        }
                        long unused = MIUI10CardActivity.this.h = System.currentTimeMillis();
                        PluginRecord d = CoreApi.a().d(MIUI10CardActivity.this.b.model);
                        if (d == null) {
                            LogUtil.b("mijia-card", "click more action record null");
                            return;
                        }
                        if (!TextUtils.isEmpty(MIUI10CardActivity.sRoomName)) {
                            STAT.d.d(MIUI10CardActivity.this.q, MIUI10CardActivity.sRoomName, SmartHomeDeviceManager.e(MIUI10CardActivity.this.b));
                        }
                        if (d.k() || CameraNativePluginManager.a().a(d.o())) {
                            if (MIUI10CardActivity.this.mCard != null) {
                                int size = MIUI10CardActivity.this.mCard.c.size();
                                for (int i = 0; i < size; i++) {
                                    if (MIUI10CardActivity.this.m.f20457a.get(i) instanceof CameraCardItem) {
                                        ((CameraCardItem) MIUI10CardActivity.this.m.f20457a.get(i)).d();
                                    }
                                }
                            }
                            Intent intent = new Intent();
                            MIUI10CardActivity.this.b.setLaunchParams(intent);
                            MIUI10CardActivity.this.a(MIUI10CardActivity.this.d);
                            MIUI10CardActivity.this.d.findViewById(R.id.three_point_loading).setVisibility(0);
                            MIUI10CardActivity.this.d.findViewById(R.id.bottom_text).setVisibility(8);
                            PluginApi.getInstance().sendMessage(MIUI10CardActivity.this, d, 1, intent, MIUI10CardActivity.this.b.newDeviceStat(), (RunningProcess) null, false, new PluginApi.SendMessageCallback() {
                                public void onSendSuccess(Bundle bundle) {
                                    super.onSendSuccess(bundle);
                                    MIUI10CardActivity.this.r.a();
                                    MIUI10CardActivity.this.d.post(new Runnable() {
                                        public void run() {
                                            MIUI10CardActivity.this.c();
                                            if (MIUI10CardActivity.this.d != null && MIUI10CardActivity.this.isValid()) {
                                                MIUI10CardActivity.this.d.findViewById(R.id.three_point_loading).setVisibility(8);
                                                MIUI10CardActivity.this.d.findViewById(R.id.bottom_text).setVisibility(0);
                                            }
                                        }
                                    });
                                }

                                public void onSendCancel() {
                                    super.onSendCancel();
                                    MIUI10CardActivity.this.d.post(new Runnable() {
                                        public void run() {
                                            MIUI10CardActivity.this.c();
                                            if (MIUI10CardActivity.this.d != null && MIUI10CardActivity.this.isValid()) {
                                                MIUI10CardActivity.this.d.findViewById(R.id.three_point_loading).setVisibility(8);
                                                MIUI10CardActivity.this.d.findViewById(R.id.bottom_text).setVisibility(0);
                                            }
                                        }
                                    });
                                }

                                public void onSendFailure(Error error) {
                                    super.onSendFailure(error);
                                    MIUI10CardActivity.this.d.post(new Runnable() {
                                        public void run() {
                                            MIUI10CardActivity.this.c();
                                            if (MIUI10CardActivity.this.d != null && MIUI10CardActivity.this.isValid()) {
                                                MIUI10CardActivity.this.d.findViewById(R.id.three_point_loading).setVisibility(8);
                                                MIUI10CardActivity.this.d.findViewById(R.id.bottom_text).setVisibility(0);
                                            }
                                        }
                                    });
                                }
                            });
                            return;
                        }
                        MIUI10CardActivity.this.f.a((DownloadProgressProcessor) MIUI10CardActivity.this);
                        MIUI10CardActivity.this.f.a((DeviceInstaller) MIUI10CardActivity.this);
                        MIUI10CardActivity.this.f.a((DeviceDownloadItemViewWrapper.ProgressCallback) new DeviceDownloadItemViewWrapper.ProgressCallback() {
                            public void a() {
                            }

                            public void a(float f) {
                            }

                            public void b() {
                            }

                            public void c() {
                                boolean unused = MIUI10CardActivity.this.g = false;
                            }

                            public void d() {
                                boolean unused = MIUI10CardActivity.this.g = false;
                            }

                            public void e() {
                                boolean unused = MIUI10CardActivity.this.g = false;
                            }
                        });
                        MIUI10CardActivity.this.mSendMessageHandle = MIUI10CardActivity.this.f.a();
                        boolean unused2 = MIUI10CardActivity.this.g = true;
                    }
                }
            });
        }
    }

    /* access modifiers changed from: private */
    public void a(String str, Error error) {
        if (GlobalSetting.j) {
            String pushId = CommonApplication.getApplication().getPushId();
            Context context = getContext();
            StringBuilder sb = new StringBuilder();
            sb.append(str);
            Object obj = error;
            if (error == null) {
                obj = "";
            }
            sb.append(obj);
            sb.append("\nmodel:");
            sb.append(this.b.model);
            sb.append("\ndid:");
            sb.append(this.b.did);
            sb.append(TextUtils.isEmpty(pushId) ? "\n订阅失败，请重启 app" : "");
            Toast.makeText(context, sb.toString(), 1).show();
        }
    }

    private void a(Map<String, ProfileCard> map) {
        if (map != null && map.size() != 0) {
            if (!TextUtils.isEmpty(this.b.model)) {
                this.mCard = map.get(this.b.model);
            } else {
                this.mCard = (Card) map.entrySet().iterator().next().getValue();
            }
            LogUtil.c("mijia-card", "load card from /sdcard/mijia_card_config.txt model:" + this.b.model);
        }
    }

    public void install(Device device, final DeviceInstaller.InstallCallback installCallback) {
        PluginRecord d2 = CoreApi.a().d(device.model);
        device.setLaunchParams(new Intent());
        PluginApi.getInstance().installPlugin(this, d2, new PluginApi.SendMessageCallback() {
            public void onInstallStart(PluginRecord pluginRecord) {
                super.onInstallStart(pluginRecord);
                boolean unused = MIUI10CardActivity.this.i = true;
                if (installCallback != null) {
                    installCallback.a();
                }
            }

            public void onInstallSuccess(PluginRecord pluginRecord) {
                long unused = MIUI10CardActivity.this.j = System.currentTimeMillis();
                super.onInstallSuccess(pluginRecord);
                boolean unused2 = MIUI10CardActivity.this.i = false;
                if (installCallback != null) {
                    installCallback.b();
                }
            }

            public void onInstallFailure(PluginError pluginError) {
                super.onInstallFailure(pluginError);
                long unused = MIUI10CardActivity.this.j = System.currentTimeMillis();
                boolean unused2 = MIUI10CardActivity.this.i = false;
                if (installCallback != null) {
                    installCallback.c();
                }
                MIUI10CardActivity.this.c.setSuccess();
            }

            public void onSendSuccess(Bundle bundle) {
                super.onSendSuccess(bundle);
                MIUI10CardActivity.this.r.a();
                long unused = MIUI10CardActivity.this.j = System.currentTimeMillis();
                boolean unused2 = MIUI10CardActivity.this.i = false;
                CommonApplication.getGlobalHandler().postDelayed(new Runnable() {
                    public void run() {
                        MIUI10CardActivity.this.c.setSuccess();
                    }
                }, 800);
            }

            public void onSendFailure(Error error) {
                super.onSendFailure(error);
                long unused = MIUI10CardActivity.this.j = System.currentTimeMillis();
                boolean unused2 = MIUI10CardActivity.this.i = false;
                MIUI10CardActivity.this.c.setSuccess();
            }

            public void onSendCancel() {
                super.onSendCancel();
                long unused = MIUI10CardActivity.this.j = System.currentTimeMillis();
                boolean unused2 = MIUI10CardActivity.this.i = false;
                MIUI10CardActivity.this.c.setSuccess();
            }
        });
    }

    public float progress(float f2, Device device) {
        PluginRecord d2;
        if (f2 <= 90.0f || !shouldInstallNow(device) || (d2 = CoreApi.a().d(device.model)) == null || d2.l()) {
            return f2;
        }
        return 90.0f;
    }

    static class PointHandler extends Handler {

        /* renamed from: a  reason: collision with root package name */
        private int f20149a = 0;
        private Drawable[] b;
        private ImageView[] c;
        private boolean d = false;

        PointHandler() {
        }

        /* access modifiers changed from: package-private */
        public void a(Resources resources, View view) {
            this.b = new Drawable[]{resources.getDrawable(R.drawable.loading_first_point), resources.getDrawable(R.drawable.loading_second_point), resources.getDrawable(R.drawable.loading_third_point)};
            this.c = new ImageView[]{(ImageView) view.findViewById(R.id.first), (ImageView) view.findViewById(R.id.second), (ImageView) view.findViewById(R.id.third)};
        }

        /* access modifiers changed from: package-private */
        public void a() {
            this.d = false;
            this.f20149a = 0;
            sendEmptyMessage(0);
        }

        /* access modifiers changed from: package-private */
        public void b() {
            this.d = true;
        }

        public void handleMessage(Message message) {
            if (!this.d) {
                for (int i = 0; i < 3; i++) {
                    this.c[i].setImageDrawable(this.b[(this.f20149a + i) % 3]);
                }
                sendEmptyMessageDelayed(0, 500);
            }
        }
    }

    /* access modifiers changed from: private */
    public void a(View view) {
        if (this.pointHandler == null) {
            this.pointHandler = new PointHandler();
            this.pointHandler.a(getResources(), view);
        }
        this.pointHandler.a();
    }

    /* access modifiers changed from: private */
    public void c() {
        if (this.pointHandler != null) {
            this.pointHandler.b();
        }
    }

    /* access modifiers changed from: private */
    public void d() {
        if (this.v == null) {
            this.v = new DevicePropSubscriber();
        }
        ArrayList arrayList = new ArrayList();
        arrayList.add(this.b);
        this.v.a((List<Device>) arrayList, (DevicePropSubscriber.DeviceSubscriberInterface) new CardAbstractManager.CardDeviceSubscriberInterface(MiotSpecCardManager.f()));
    }

    /* access modifiers changed from: private */
    public void e() {
        if (this.v == null) {
            this.v = new DevicePropSubscriber();
        }
        ArrayList arrayList = new ArrayList();
        arrayList.add(this.b);
        this.v.a((List<Device>) arrayList, (DevicePropSubscriber.DeviceSubscriberInterface) new CardAbstractManager.CardDeviceSubscriberInterface(ControlCardInfoManager.f()));
    }
}
