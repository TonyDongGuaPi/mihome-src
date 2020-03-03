package com.xiaomi.smarthome.newui.mainpage;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.SystemClock;
import android.support.annotation.NonNull;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.text.TextUtils;
import android.util.Log;
import android.util.Pair;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewPropertyAnimator;
import android.view.ViewStub;
import android.widget.CheckBox;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.facebook.drawee.view.SimpleDraweeView;
import com.xiaomi.smarthome.MainPageOpManager;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.SmartHomeConfig;
import com.xiaomi.smarthome.application.SHApplication;
import com.xiaomi.smarthome.device.BleDevice;
import com.xiaomi.smarthome.device.Device;
import com.xiaomi.smarthome.device.DeviceFactory;
import com.xiaomi.smarthome.device.IRRemoteInfo;
import com.xiaomi.smarthome.device.SmartHomeDeviceManager;
import com.xiaomi.smarthome.device.utils.IRDeviceUtil;
import com.xiaomi.smarthome.frame.core.CoreApi;
import com.xiaomi.smarthome.framework.page.BaseActivity;
import com.xiaomi.smarthome.globalsetting.GlobalSetting;
import com.xiaomi.smarthome.library.common.network.NetworkUtils;
import com.xiaomi.smarthome.library.common.util.DisplayUtils;
import com.xiaomi.smarthome.library.common.util.SharePrefsManager;
import com.xiaomi.smarthome.library.common.util.ToastUtil;
import com.xiaomi.smarthome.newui.DeviceMainPage;
import com.xiaomi.smarthome.newui.adapter.DviceEditInterface;
import com.xiaomi.smarthome.newui.card.Card;
import com.xiaomi.smarthome.newui.card.CardItem;
import com.xiaomi.smarthome.newui.card.profile.ProfileCard;
import com.xiaomi.smarthome.newui.dragsort.ItemTouchHelperViewHolder;
import com.xiaomi.smarthome.newui.dragsort.SimpleItemDelegateTouchHelperCallback;
import com.xiaomi.smarthome.newui.mainpage.DeviceGridCardViewHolder;
import com.xiaomi.smarthome.newui.mainpage.devicepage.model.MainPageDeviceModel;
import com.xiaomi.smarthome.newui.utils.StringLengthUtils;
import com.xiaomi.smarthome.newui.widget.GridItemAnimView;
import com.xiaomi.smarthome.newui.widget.micards.CardStatusObserver;
import com.xiaomi.smarthome.smartconfig.DeviceFinder;
import com.xiaomi.smarthome.stat.STAT;
import java.util.List;
import java.util.Set;
import java.util.concurrent.atomic.AtomicBoolean;

public class DeviceGridCardViewHolder extends RecyclerView.ViewHolder implements ItemTouchHelperViewHolder {
    private static int r = 8;
    private static final String[] s = {"小米", "米家", "米兔", "小蚁", "青米", "飞利浦", "Yeelight", "智米", "90分", "美的", "奥克斯", "金兴", "iHealth", "小吉", "云米", "Aqara", "素士", "创米", "夏洛克"};

    /* renamed from: a  reason: collision with root package name */
    public View f20654a;
    public ViewStub b;
    public CheckBox c;
    public ViewStub d;
    @BindView(2131433285)
    public TextView descDeviceState;
    @BindView(2131433286)
    public View descSplit;
    @BindView(2131433283)
    public TextView deviceNameTv;
    public ImageView e;
    public ViewStub f;
    public ImageView g;
    public ViewStub h;
    public GridItemAnimView i;
    @BindView(2131429685)
    public SimpleDraweeView iv;
    /* access modifiers changed from: private */
    public Context j;
    /* access modifiers changed from: private */
    public DviceEditInterface k;
    /* access modifiers changed from: private */
    public SimpleItemDelegateTouchHelperCallback l;
    /* access modifiers changed from: private */
    public ItemTouchHelper m;
    /* access modifiers changed from: private */
    public DeviceMainGridSwitchHelper n;
    private View.OnTouchListener o;
    private MainPageDeviceModel p;
    /* access modifiers changed from: private */
    public boolean q = false;
    @BindView(2131433467)
    public TextView roomNameTv;
    @BindView(2131432436)
    public ImageView switchIv;
    @BindView(2131432437)
    public FrameLayout switchIvContainer;

    public DeviceGridCardViewHolder(@NonNull View view, Context context, DviceEditInterface dviceEditInterface, SimpleItemDelegateTouchHelperCallback simpleItemDelegateTouchHelperCallback, ItemTouchHelper itemTouchHelper) {
        super(view);
        this.f20654a = view;
        this.j = context;
        this.k = dviceEditInterface;
        this.l = simpleItemDelegateTouchHelperCallback;
        this.m = itemTouchHelper;
        this.n = new DeviceMainGridSwitchHelper(context, this, this.k);
        this.o = new View.OnTouchListener() {
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if (motionEvent.getAction() != 0 || DeviceGridCardViewHolder.this.l == null) {
                    return false;
                }
                DeviceGridCardViewHolder.this.l.a((int) motionEvent.getX(), (int) motionEvent.getY());
                return false;
            }
        };
        ButterKnife.bind((Object) this, view);
        this.b = (ViewStub) view.findViewById(R.id.ckb_edit_selected_vs);
        this.d = (ViewStub) view.findViewById(R.id.isnew_vs);
        this.f = (ViewStub) view.findViewById(R.id.offline_tip_iv_vs);
        this.h = (ViewStub) view.findViewById(R.id.grid_anim_view_vs);
        this.iv.setImportantForAccessibility(2);
    }

    private void c() {
        this.iv.setImageResource(R.drawable.device_icon_ir_nor);
        this.iv.setAlpha(1.0f);
        this.deviceNameTv.setAlpha(0.9f);
        this.switchIv.setClickable(false);
        this.roomNameTv.setAlpha(1.0f);
        this.roomNameTv.setVisibility(8);
        f();
        this.descSplit.setAlpha(1.0f);
        this.descSplit.setVisibility(8);
        this.descDeviceState.setAlpha(1.0f);
        this.descDeviceState.setText("");
        this.descDeviceState.setVisibility(8);
        if (this.i != null) {
            this.i.setEnabled(false);
            this.i.setVisibility(8);
        }
        if (this.g != null) {
            this.g.setVisibility(8);
        }
        if (this.c != null) {
            this.c.setVisibility(8);
        }
        if (this.e != null) {
            this.e.setVisibility(8);
        }
        this.switchIvContainer.setVisibility(8);
        this.f20654a.setBackgroundResource(R.drawable.main_grid_card_bg_normal_v2);
    }

    public void a(MainPageDeviceModel mainPageDeviceModel, int i2) {
        this.p = mainPageDeviceModel;
        if (mainPageDeviceModel.b()) {
            b(mainPageDeviceModel, i2);
        } else {
            c(mainPageDeviceModel, i2);
        }
    }

    public void a() {
        this.itemView.setSelected(true);
    }

    public void b() {
        this.itemView.setSelected(false);
    }

    private void b(final MainPageDeviceModel mainPageDeviceModel, final int i2) {
        c();
        this.deviceNameTv.setVisibility(0);
        this.deviceNameTv.setText(R.string.phone_ir_device);
        this.roomNameTv.setVisibility(0);
        this.roomNameTv.setText(R.string.phone);
        int b2 = IRDeviceUtil.b(this.j, (IRDeviceUtil.IRDeviceListener) new IRDeviceUtil.IRDeviceListener() {
            public void onQueryComplete(List<IRRemoteInfo> list) {
                if (DeviceGridCardViewHolder.this.j != null) {
                    if (!(DeviceGridCardViewHolder.this.j instanceof BaseActivity) || !((BaseActivity) DeviceGridCardViewHolder.this.j).isValid()) {
                        if (DeviceGridCardViewHolder.this.j instanceof Activity) {
                            if (((Activity) DeviceGridCardViewHolder.this.j).isFinishing()) {
                                return;
                            }
                            if (Build.VERSION.SDK_INT >= 17 && ((Activity) DeviceGridCardViewHolder.this.j).isDestroyed()) {
                                return;
                            }
                        }
                        final int c = IRDeviceUtil.c(DeviceGridCardViewHolder.this.j);
                        SHApplication.getGlobalHandler().post(new Runnable() {
                            public void run() {
                                DeviceGridCardViewHolder.this.descSplit.setVisibility(0);
                                DeviceGridCardViewHolder.this.descDeviceState.setVisibility(0);
                                String quantityString = DeviceGridCardViewHolder.this.j.getResources().getQuantityString(R.plurals.ir_device_count_2, c, new Object[]{Integer.valueOf(c)});
                                DeviceGridCardViewHolder.this.descDeviceState.setText(String.format("%s%d", new Object[]{quantityString, Integer.valueOf(c)}));
                                DeviceGridCardViewHolder.this.e();
                            }
                        });
                        return;
                    }
                    final int c2 = IRDeviceUtil.c(DeviceGridCardViewHolder.this.j);
                    SHApplication.getGlobalHandler().post(new Runnable() {
                        public void run() {
                            DeviceGridCardViewHolder.this.descSplit.setVisibility(0);
                            DeviceGridCardViewHolder.this.descDeviceState.setVisibility(0);
                            String quantityString = DeviceGridCardViewHolder.this.j.getResources().getQuantityString(R.plurals.ir_device_count_2, c2, new Object[]{Integer.valueOf(c2)});
                            DeviceGridCardViewHolder.this.descDeviceState.setText(String.format("%s%d", new Object[]{quantityString, Integer.valueOf(c2)}));
                            DeviceGridCardViewHolder.this.e();
                        }
                    });
                }
            }
        });
        String quantityString = this.j.getResources().getQuantityString(R.plurals.ir_device_count_2, b2, new Object[]{Integer.valueOf(b2)});
        this.descSplit.setVisibility(0);
        this.descDeviceState.setVisibility(0);
        this.descDeviceState.setText(String.format("%s%d", new Object[]{quantityString, Integer.valueOf(b2)}));
        e();
        if (this.k.b() && this.o != null) {
            this.f20654a.setOnTouchListener(this.o);
        }
        final int q2 = this.k.q();
        this.f20654a.setOnLongClickListener(new View.OnLongClickListener() {
            public boolean onLongClick(View view) {
                if (!DeviceGridCardViewHolder.this.k.b() && DeviceGridCardViewHolder.this.k.c() != DviceEditInterface.HostPage.TRANSFER_GUIDE_PAGE) {
                    ToastUtil.a((int) R.string.share_family_none_editable);
                    return true;
                } else if (CardStatusObserver.getCardStatus() != CardStatusObserver.CardStatus.HIDING) {
                    return true;
                } else {
                    if (DeviceGridCardViewHolder.this.k.t() != null && !DeviceGridCardViewHolder.this.k.L_()) {
                        DeviceGridCardViewHolder.this.k.t().enterEditMode(DeviceGridCardViewHolder.this.k);
                    }
                    if (!(DeviceGridCardViewHolder.this.m == null || DeviceGridCardViewHolder.this.l == null || DeviceGridCardViewHolder.this.l.b() || !DeviceGridCardViewHolder.this.k.L_() || q2 == 2)) {
                        DeviceGridCardViewHolder.this.m.startDrag(DeviceGridCardViewHolder.this);
                    }
                    STAT.d.a(mainPageDeviceModel.c(), DeviceGridCardViewHolder.this.k.q(), i2, DeviceGridCardViewHolder.this.k.n(), mainPageDeviceModel.d());
                    return false;
                }
            }
        });
        this.f20654a.setOnClickListener(new View.OnClickListener() {
            /* JADX WARNING: Code restructure failed: missing block: B:4:0x0037, code lost:
                r1 = com.xiaomi.smarthome.frame.core.CoreApi.a().d(r8.c());
             */
            /* Code decompiled incorrectly, please refer to instructions dump. */
            public void onClick(android.view.View r5) {
                /*
                    r4 = this;
                    java.lang.String r0 = "PluginStartTime"
                    java.lang.StringBuilder r1 = new java.lang.StringBuilder
                    r1.<init>()
                    java.lang.String r2 = "onClickHomeDevice  "
                    r1.append(r2)
                    long r2 = java.lang.System.currentTimeMillis()
                    r1.append(r2)
                    java.lang.String r1 = r1.toString()
                    com.xiaomi.smarthome.framework.log.LogUtil.c(r0, r1)
                    android.graphics.RectF r0 = com.xiaomi.smarthome.library.common.util.ViewUtils.b((android.view.View) r5)
                    com.xiaomi.smarthome.newui.mainpage.DeviceGridCardViewHolder r1 = com.xiaomi.smarthome.newui.mainpage.DeviceGridCardViewHolder.this
                    com.xiaomi.smarthome.newui.adapter.DviceEditInterface r1 = r1.k
                    boolean r1 = r1.L_()
                    if (r1 != 0) goto L_0x006d
                    com.xiaomi.smarthome.newui.mainpage.devicepage.model.MainPageDeviceModel r1 = r8
                    java.lang.String r1 = r1.c()
                    boolean r1 = android.text.TextUtils.isEmpty(r1)
                    r2 = 1
                    if (r1 != 0) goto L_0x0056
                    com.xiaomi.smarthome.frame.core.CoreApi r1 = com.xiaomi.smarthome.frame.core.CoreApi.a()
                    com.xiaomi.smarthome.newui.mainpage.devicepage.model.MainPageDeviceModel r3 = r8
                    java.lang.String r3 = r3.c()
                    com.xiaomi.smarthome.core.entity.plugin.PluginRecord r1 = r1.d((java.lang.String) r3)
                    if (r1 == 0) goto L_0x0056
                    com.xiaomi.smarthome.core.entity.plugin.PluginPackageInfo r3 = r1.h()
                    if (r3 == 0) goto L_0x0056
                    com.xiaomi.smarthome.core.entity.plugin.PluginPackageInfo r1 = r1.h()
                    boolean r1 = r1.d()
                    goto L_0x0057
                L_0x0056:
                    r1 = 1
                L_0x0057:
                    com.xiaomi.smarthome.newui.mainpage.DeviceGridCardViewHolder r3 = com.xiaomi.smarthome.newui.mainpage.DeviceGridCardViewHolder.this
                    boolean r3 = r3.g()
                    if (r3 == 0) goto L_0x0062
                    if (r1 == 0) goto L_0x0062
                    goto L_0x0063
                L_0x0062:
                    r2 = 0
                L_0x0063:
                    com.xiaomi.smarthome.newui.mainpage.DeviceGridCardViewHolder r1 = com.xiaomi.smarthome.newui.mainpage.DeviceGridCardViewHolder.this
                    com.xiaomi.smarthome.newui.mainpage.DeviceGridCardViewHolder$4$1 r3 = new com.xiaomi.smarthome.newui.mainpage.DeviceGridCardViewHolder$4$1
                    r3.<init>(r2, r0)
                    r1.a(r5, r2, r3)
                L_0x006d:
                    return
                */
                throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.smarthome.newui.mainpage.DeviceGridCardViewHolder.AnonymousClass4.onClick(android.view.View):void");
            }
        });
    }

    private boolean d() {
        int q2 = this.k.q();
        return q2 == 0 || q2 == 3;
    }

    private void c(final MainPageDeviceModel mainPageDeviceModel, final int i2) {
        boolean z;
        Context context;
        int i3;
        c();
        String a2 = a(this.deviceNameTv, a(mainPageDeviceModel.e()));
        this.deviceNameTv.setVisibility(0);
        this.deviceNameTv.setText(a2);
        this.deviceNameTv.setAlpha(mainPageDeviceModel.f() ? 1.0f : 0.2f);
        if (TextUtils.isEmpty(mainPageDeviceModel.a())) {
            DeviceFactory.b(mainPageDeviceModel.c(), this.iv);
        } else {
            DeviceFactory.a(mainPageDeviceModel.a(), this.iv);
        }
        Pair<CardItem.State, String> h2 = mainPageDeviceModel.h();
        if (h2 != null) {
            if (SystemClock.elapsedRealtime() - this.n.a(mainPageDeviceModel.d()).f20679a > 300) {
                this.n.a(h2);
            }
            this.switchIvContainer.setVisibility(0);
            if (this.i == null) {
                this.i = (GridItemAnimView) this.h.inflate();
            }
            this.i.setVisibility(0);
            final AtomicBoolean atomicBoolean = new AtomicBoolean(h2.first == CardItem.State.SELECTED);
            this.switchIvContainer.setOnTouchListener(new View.OnTouchListener() {
                public boolean onTouch(View view, MotionEvent motionEvent) {
                    int action = motionEvent.getAction();
                    if (action != 3) {
                        switch (action) {
                            case 0:
                                ViewPropertyAnimator animate = DeviceGridCardViewHolder.this.switchIv.animate();
                                animate.alpha(0.4f);
                                animate.setDuration(100);
                                return false;
                            case 1:
                                DeviceGridCardViewHolder.this.switchIvContainer.setOnTouchListener((View.OnTouchListener) null);
                                ViewPropertyAnimator animate2 = DeviceGridCardViewHolder.this.switchIv.animate();
                                animate2.alpha(1.0f);
                                animate2.setDuration(100);
                                animate2.setListener(new AnimatorListenerAdapter() {
                                    public void onAnimationEnd(Animator animator) {
                                        super.onAnimationEnd(animator);
                                        DeviceGridCardViewHolder.this.switchIvContainer.setOnTouchListener(this);
                                    }
                                });
                                return false;
                            default:
                                return false;
                        }
                    } else {
                        ViewPropertyAnimator animate3 = DeviceGridCardViewHolder.this.switchIv.animate();
                        animate3.alpha(1.0f);
                        animate3.setDuration(100);
                        return false;
                    }
                }
            });
            if (this.switchIvContainer.getVisibility() == 0) {
                this.iv.setImportantForAccessibility(0);
                SimpleDraweeView simpleDraweeView = this.iv;
                if (atomicBoolean.get()) {
                    context = this.j;
                    i3 = R.string.intelligent_plug_on;
                } else {
                    context = this.j;
                    i3 = R.string.intelligent_plug_off;
                }
                simpleDraweeView.setContentDescription(context.getString(i3));
            }
            Card<? extends Card.CardType> a3 = MainPageOpManager.a().a(SmartHomeDeviceManager.a().b(mainPageDeviceModel.d()));
            if (a3 != null) {
                final Card.CardType cardType = (Card.CardType) a3.a().get(0);
                this.switchIvContainer.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View view) {
                        if (!NetworkUtils.c()) {
                            ToastUtil.a(DeviceGridCardViewHolder.this.j, (int) R.string.popup_select_loc_no_network);
                            return;
                        }
                        if (GlobalSetting.q || GlobalSetting.u) {
                            Log.d("SwitchAnim", "start playClickEffect isCurrentOnForUI=" + atomicBoolean.get() + ",device=" + mainPageDeviceModel.e());
                        }
                        DeviceGridCardViewHolder.this.n.a(SmartHomeDeviceManager.a().b(mainPageDeviceModel.d()), cardType, atomicBoolean, (View.OnClickListener) this);
                    }
                });
            }
            z = true;
        } else {
            if (!TextUtils.isEmpty(mainPageDeviceModel.m())) {
                this.switchIv.setImageBitmap(BitmapFactory.decodeFile(mainPageDeviceModel.m()));
                this.switchIvContainer.setVisibility(0);
            } else {
                this.switchIvContainer.setVisibility(8);
            }
            z = false;
        }
        StringBuilder sb = new StringBuilder();
        if (!TextUtils.isEmpty(mainPageDeviceModel.i()) && !TextUtils.isEmpty(mainPageDeviceModel.j())) {
            sb.append(mainPageDeviceModel.i() + " " + mainPageDeviceModel.j());
        }
        if (!TextUtils.isEmpty(mainPageDeviceModel.k()) && !TextUtils.isEmpty(mainPageDeviceModel.l())) {
            sb.append(" " + mainPageDeviceModel.k() + " " + mainPageDeviceModel.l());
        }
        String trim = sb.toString().trim();
        if (!TextUtils.isEmpty(trim)) {
            e();
            this.descSplit.setVisibility(0);
            this.descDeviceState.setVisibility(0);
            this.descDeviceState.setText(trim);
            z = true;
        } else {
            this.descSplit.setVisibility(4);
            this.descDeviceState.setVisibility(8);
        }
        if (!z && (SmartHomeDeviceManager.a().b(mainPageDeviceModel.d()) instanceof BleDevice)) {
            if (this.g == null) {
                this.g = (ImageView) this.f.inflate();
            }
            this.g.setImageResource(R.drawable.ble_to_be_connected_icon);
            this.g.setVisibility(0);
        }
        final int q2 = this.k.q();
        String g2 = mainPageDeviceModel.g();
        if (d()) {
            this.roomNameTv.setVisibility(0);
            this.roomNameTv.setText(g2);
        } else {
            this.roomNameTv.setVisibility(8);
        }
        Device b2 = SmartHomeDeviceManager.a().b(mainPageDeviceModel.d());
        a(mainPageDeviceModel);
        final Set<String> l2 = this.k.l();
        if (this.k.L_()) {
            if (this.c == null) {
                this.c = (CheckBox) this.b.inflate();
            }
            this.c.setMaxWidth(DisplayUtils.a(20.0f));
            this.c.setMaxHeight(DisplayUtils.a(20.0f));
            ViewGroup.LayoutParams layoutParams = this.c.getLayoutParams();
            layoutParams.width = DisplayUtils.a(20.0f);
            layoutParams.height = DisplayUtils.a(20.0f);
            this.c.setLayoutParams(layoutParams);
            this.c.setVisibility(0);
            this.switchIvContainer.setVisibility(8);
            if (this.g != null) {
                this.g.setVisibility(8);
            }
            if (l2 != null) {
                this.c.setChecked(l2.contains(mainPageDeviceModel.d()));
            }
        } else if (this.c != null) {
            this.c.setVisibility(8);
        }
        if (this.o != null) {
            this.f20654a.setOnTouchListener(this.o);
        }
        this.f20654a.setOnLongClickListener(new View.OnLongClickListener() {
            public boolean onLongClick(View view) {
                if (!DeviceGridCardViewHolder.this.k.b() && DeviceGridCardViewHolder.this.k.c() != DviceEditInterface.HostPage.TRANSFER_GUIDE_PAGE) {
                    ToastUtil.a((int) R.string.share_family_none_editable);
                    return true;
                } else if (CardStatusObserver.getCardStatus() != CardStatusObserver.CardStatus.HIDING) {
                    return true;
                } else {
                    if (DeviceGridCardViewHolder.this.k.t() != null && !DeviceGridCardViewHolder.this.k.L_()) {
                        DeviceGridCardViewHolder.this.k.t().enterEditMode(DeviceGridCardViewHolder.this.k);
                        DeviceGridCardViewHolder.this.k.a(i2, true);
                    }
                    if (!(DeviceGridCardViewHolder.this.m == null || DeviceGridCardViewHolder.this.l == null || DeviceGridCardViewHolder.this.l.b() || !DeviceGridCardViewHolder.this.k.L_() || q2 == 2)) {
                        DeviceGridCardViewHolder.this.m.startDrag(DeviceGridCardViewHolder.this);
                        if (TextUtils.equals(DeviceFinder.a().i(), mainPageDeviceModel.d())) {
                            DeviceFinder.a().j();
                        }
                        Device b2 = SmartHomeDeviceManager.a().b(mainPageDeviceModel.d());
                        if (b2 != null && b2.isNew) {
                            if (b2 instanceof BleDevice) {
                                ((BleDevice) b2).b(false);
                            } else {
                                b2.isNew = false;
                            }
                        }
                    }
                    STAT.d.a(mainPageDeviceModel.c(), DeviceGridCardViewHolder.this.k.q(), i2, DeviceGridCardViewHolder.this.k.n(), mainPageDeviceModel.d());
                    return false;
                }
            }
        });
        final MainPageDeviceModel mainPageDeviceModel2 = mainPageDeviceModel;
        final int i4 = i2;
        this.f20654a.setOnClickListener(new View.OnClickListener() {
            /* JADX WARNING: Code restructure failed: missing block: B:11:0x0091, code lost:
                r0 = com.xiaomi.smarthome.frame.core.CoreApi.a().d(r7.c());
             */
            /* Code decompiled incorrectly, please refer to instructions dump. */
            public void onClick(android.view.View r11) {
                /*
                    r10 = this;
                    java.lang.String r0 = "PluginStartTime"
                    java.lang.StringBuilder r1 = new java.lang.StringBuilder
                    r1.<init>()
                    java.lang.String r2 = "onClickHomeDevice  "
                    r1.append(r2)
                    long r2 = java.lang.System.currentTimeMillis()
                    r1.append(r2)
                    java.lang.String r1 = r1.toString()
                    com.xiaomi.smarthome.framework.log.LogUtil.c(r0, r1)
                    android.graphics.RectF r7 = com.xiaomi.smarthome.library.common.util.ViewUtils.b((android.view.View) r11)
                    com.xiaomi.smarthome.newui.mainpage.DeviceGridCardViewHolder r0 = com.xiaomi.smarthome.newui.mainpage.DeviceGridCardViewHolder.this
                    com.xiaomi.smarthome.newui.adapter.DviceEditInterface r0 = r0.k
                    boolean r0 = r0.L_()
                    r1 = 1
                    if (r0 == 0) goto L_0x0085
                    com.xiaomi.smarthome.newui.mainpage.DeviceGridCardViewHolder r11 = com.xiaomi.smarthome.newui.mainpage.DeviceGridCardViewHolder.this
                    android.widget.CheckBox r11 = r11.c
                    if (r11 != 0) goto L_0x003f
                    com.xiaomi.smarthome.newui.mainpage.DeviceGridCardViewHolder r11 = com.xiaomi.smarthome.newui.mainpage.DeviceGridCardViewHolder.this
                    com.xiaomi.smarthome.newui.mainpage.DeviceGridCardViewHolder r0 = com.xiaomi.smarthome.newui.mainpage.DeviceGridCardViewHolder.this
                    android.view.ViewStub r0 = r0.b
                    android.view.View r0 = r0.inflate()
                    android.widget.CheckBox r0 = (android.widget.CheckBox) r0
                    r11.c = r0
                L_0x003f:
                    com.xiaomi.smarthome.newui.mainpage.DeviceGridCardViewHolder r11 = com.xiaomi.smarthome.newui.mainpage.DeviceGridCardViewHolder.this
                    android.widget.CheckBox r11 = r11.c
                    com.xiaomi.smarthome.newui.mainpage.DeviceGridCardViewHolder r0 = com.xiaomi.smarthome.newui.mainpage.DeviceGridCardViewHolder.this
                    android.widget.CheckBox r0 = r0.c
                    boolean r0 = r0.isChecked()
                    r0 = r0 ^ r1
                    r11.setChecked(r0)
                    java.util.Set r11 = r6
                    com.xiaomi.smarthome.newui.mainpage.devicepage.model.MainPageDeviceModel r0 = r7
                    java.lang.String r0 = r0.d()
                    boolean r11 = r11.contains(r0)
                    if (r11 != 0) goto L_0x0071
                    com.xiaomi.smarthome.newui.mainpage.DeviceGridCardViewHolder r11 = com.xiaomi.smarthome.newui.mainpage.DeviceGridCardViewHolder.this
                    com.xiaomi.smarthome.newui.adapter.DviceEditInterface r11 = r11.k
                    int r0 = r8
                    com.xiaomi.smarthome.newui.mainpage.DeviceGridCardViewHolder r1 = com.xiaomi.smarthome.newui.mainpage.DeviceGridCardViewHolder.this
                    android.widget.CheckBox r1 = r1.c
                    boolean r1 = r1.isChecked()
                    r11.a(r0, r1)
                    goto L_0x0084
                L_0x0071:
                    com.xiaomi.smarthome.newui.mainpage.DeviceGridCardViewHolder r11 = com.xiaomi.smarthome.newui.mainpage.DeviceGridCardViewHolder.this
                    com.xiaomi.smarthome.newui.adapter.DviceEditInterface r11 = r11.k
                    int r0 = r8
                    com.xiaomi.smarthome.newui.mainpage.DeviceGridCardViewHolder r1 = com.xiaomi.smarthome.newui.mainpage.DeviceGridCardViewHolder.this
                    android.widget.CheckBox r1 = r1.c
                    boolean r1 = r1.isChecked()
                    r11.a(r0, r1)
                L_0x0084:
                    return
                L_0x0085:
                    com.xiaomi.smarthome.newui.mainpage.devicepage.model.MainPageDeviceModel r0 = r7
                    java.lang.String r0 = r0.c()
                    boolean r0 = android.text.TextUtils.isEmpty(r0)
                    if (r0 != 0) goto L_0x00b0
                    com.xiaomi.smarthome.frame.core.CoreApi r0 = com.xiaomi.smarthome.frame.core.CoreApi.a()
                    com.xiaomi.smarthome.newui.mainpage.devicepage.model.MainPageDeviceModel r2 = r7
                    java.lang.String r2 = r2.c()
                    com.xiaomi.smarthome.core.entity.plugin.PluginRecord r0 = r0.d((java.lang.String) r2)
                    if (r0 == 0) goto L_0x00b0
                    com.xiaomi.smarthome.core.entity.plugin.PluginPackageInfo r2 = r0.h()
                    if (r2 == 0) goto L_0x00b0
                    com.xiaomi.smarthome.core.entity.plugin.PluginPackageInfo r0 = r0.h()
                    boolean r0 = r0.d()
                    goto L_0x00b1
                L_0x00b0:
                    r0 = 1
                L_0x00b1:
                    com.xiaomi.smarthome.newui.mainpage.DeviceGridCardViewHolder r2 = com.xiaomi.smarthome.newui.mainpage.DeviceGridCardViewHolder.this
                    boolean r4 = r2.g()
                    r8 = 0
                    if (r4 == 0) goto L_0x00bd
                    if (r0 == 0) goto L_0x00bd
                    goto L_0x00be
                L_0x00bd:
                    r1 = 0
                L_0x00be:
                    com.xiaomi.smarthome.newui.mainpage.DeviceGridCardViewHolder r0 = com.xiaomi.smarthome.newui.mainpage.DeviceGridCardViewHolder.this
                    com.xiaomi.smarthome.newui.mainpage.DeviceGridCardViewHolder$8$1 r9 = new com.xiaomi.smarthome.newui.mainpage.DeviceGridCardViewHolder$8$1
                    r2 = r9
                    r3 = r10
                    r5 = r1
                    r6 = r11
                    r2.<init>(r4, r5, r6, r7)
                    r0.a(r11, r1, r9)
                    com.xiaomi.smarthome.device.SmartHomeDeviceManager r11 = com.xiaomi.smarthome.device.SmartHomeDeviceManager.a()
                    com.xiaomi.smarthome.newui.mainpage.devicepage.model.MainPageDeviceModel r0 = r7
                    java.lang.String r0 = r0.d()
                    com.xiaomi.smarthome.device.Device r11 = r11.b((java.lang.String) r0)
                    if (r11 == 0) goto L_0x00ec
                    boolean r0 = r11.isNew
                    if (r0 == 0) goto L_0x00ec
                    boolean r0 = r11 instanceof com.xiaomi.smarthome.device.BleDevice
                    if (r0 == 0) goto L_0x00ea
                    com.xiaomi.smarthome.device.BleDevice r11 = (com.xiaomi.smarthome.device.BleDevice) r11
                    r11.b((boolean) r8)
                    goto L_0x00ec
                L_0x00ea:
                    r11.isNew = r8
                L_0x00ec:
                    return
                */
                throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.smarthome.newui.mainpage.DeviceGridCardViewHolder.AnonymousClass8.onClick(android.view.View):void");
            }
        });
        a(mainPageDeviceModel);
        if (b2 != null && b2.isNew) {
            if (this.e == null) {
                this.e = (ImageView) this.d.inflate();
            }
            this.e.setVisibility(0);
        } else if (this.e != null) {
            this.e.setVisibility(8);
        }
    }

    public void a(MainPageDeviceModel mainPageDeviceModel) {
        boolean z;
        if (mainPageDeviceModel != null) {
            Device b2 = SmartHomeDeviceManager.a().b(mainPageDeviceModel.d());
            if (b2 == null) {
                z = !mainPageDeviceModel.f();
            } else {
                Card<? extends Card.CardType> a2 = MainPageOpManager.a().a(b2);
                z = !b2.isOnlineAdvance() && !(b2 instanceof BleDevice) && (!(a2 instanceof ProfileCard) || !((ProfileCard) a2).g);
            }
            if (z) {
                if (this.switchIvContainer != null) {
                    this.switchIvContainer.setVisibility(8);
                }
                if (this.i != null) {
                    this.i.setVisibility(8);
                }
                this.f20654a.setBackgroundResource(R.drawable.main_grid_card_bg_offline_v2);
                this.iv.setAlpha(0.3f);
                this.deviceNameTv.setAlpha(0.2f);
                this.roomNameTv.setAlpha(0.3f);
                this.descDeviceState.setAlpha(0.3f);
                this.descDeviceState.setVisibility(8);
                this.descSplit.setVisibility(8);
                this.descSplit.setAlpha(0.3f);
                return;
            }
            if (this.g != null) {
                this.g.setVisibility(8);
            }
            this.f20654a.setBackgroundResource(R.drawable.main_grid_card_bg_normal_v2);
            this.iv.setAlpha(1.0f);
            this.roomNameTv.setAlpha(1.0f);
            this.deviceNameTv.setAlpha(1.0f);
            this.descDeviceState.setAlpha(1.0f);
            this.descSplit.setAlpha(1.0f);
        }
    }

    public static boolean a(final Context context, Device device) {
        if (!CoreApi.a().q() || device.isBinded()) {
            return false;
        }
        device.bindDevice(context, new Device.IBindDeviceCallback() {
            public void a() {
                LocalBroadcastManager.getInstance(context).sendBroadcast(new Intent("com.smarthome.refresh_list_view"));
            }

            public void b() {
                LocalBroadcastManager.getInstance(context).sendBroadcast(new Intent(DeviceMainPage.f));
            }

            public void c() {
                LocalBroadcastManager.getInstance(context).sendBroadcast(new Intent("com.smarthome.refresh_list_view"));
            }

            public void a(int i) {
                ToastUtil.a(i == -1 ? R.string.button_bind_failed_has_binded : R.string.button_bind_failed);
            }

            public void d() {
                ToastUtil.a((int) R.string.button_bind_failed_info);
            }
        });
        return true;
    }

    public void a(final View view, final boolean z, final Runnable runnable) {
        if (!this.q) {
            this.q = true;
            ValueAnimator ofFloat = ValueAnimator.ofFloat(new float[]{1.0f, 0.9f, 1.0f});
            ofFloat.setDuration(360);
            ofFloat.addUpdateListener(new ValueAnimator.AnimatorUpdateListener(view) {
                private final /* synthetic */ View f$0;

                {
                    this.f$0 = r1;
                }

                public final void onAnimationUpdate(ValueAnimator valueAnimator) {
                    DeviceGridCardViewHolder.a(this.f$0, valueAnimator);
                }
            });
            ofFloat.addListener(new AnimatorListenerAdapter() {
                public void onAnimationEnd(Animator animator) {
                    if (z) {
                        runnable.run();
                    }
                    view.postDelayed(new Runnable() {
                        public final void run() {
                            DeviceGridCardViewHolder.AnonymousClass10.this.a();
                        }
                    }, 100);
                }

                /* access modifiers changed from: private */
                public /* synthetic */ void a() {
                    boolean unused = DeviceGridCardViewHolder.this.q = false;
                }

                public void onAnimationStart(Animator animator) {
                    if (!z) {
                        runnable.run();
                    }
                }
            });
            ofFloat.start();
        }
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ void a(View view, ValueAnimator valueAnimator) {
        Float f2 = (Float) valueAnimator.getAnimatedValue();
        view.setScaleX(f2.floatValue());
        view.setScaleY(f2.floatValue());
    }

    /* access modifiers changed from: private */
    public void e() {
        a(0);
    }

    private void f() {
        a(1000);
    }

    private void a(int i2) {
        LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) this.roomNameTv.getLayoutParams();
        float f2 = (float) i2;
        if (f2 != layoutParams.weight) {
            layoutParams.weight = f2;
            this.roomNameTv.requestLayout();
        }
    }

    public String a(TextView textView, String str) {
        return TextUtils.isEmpty(str) ? "" : str;
    }

    public String a(String str) {
        if (TextUtils.isEmpty(str) || StringLengthUtils.a(str) <= r) {
            return str;
        }
        for (String str2 : s) {
            if (str.startsWith(str2) && str.length() > str2.length()) {
                return str.substring(str2.length()).trim();
            }
        }
        return str;
    }

    /* access modifiers changed from: private */
    public boolean g() {
        return SharePrefsManager.b(SHApplication.getAppContext(), SmartHomeConfig.i, SmartHomeConfig.r, false);
    }
}
