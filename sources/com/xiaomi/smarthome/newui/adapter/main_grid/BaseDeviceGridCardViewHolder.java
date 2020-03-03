package com.xiaomi.smarthome.newui.adapter.main_grid;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.text.TextUtils;
import android.util.Log;
import android.util.Pair;
import android.view.MotionEvent;
import android.view.View;
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
import com.xiaomi.smarthome.device.utils.IRDeviceUtil;
import com.xiaomi.smarthome.frame.core.CoreApi;
import com.xiaomi.smarthome.framework.page.BaseActivity;
import com.xiaomi.smarthome.homeroom.HomeManager;
import com.xiaomi.smarthome.library.common.util.SharePrefsManager;
import com.xiaomi.smarthome.library.common.util.ToastUtil;
import com.xiaomi.smarthome.miio.device.PhoneIRDevice;
import com.xiaomi.smarthome.newui.DeviceMainPage;
import com.xiaomi.smarthome.newui.adapter.DviceEditInterface;
import com.xiaomi.smarthome.newui.adapter.main_grid.BaseDeviceGridCardViewHolder;
import com.xiaomi.smarthome.newui.adapter.main_grid.DeviceCateHelper;
import com.xiaomi.smarthome.newui.card.Card;
import com.xiaomi.smarthome.newui.card.profile.ProfileCard;
import com.xiaomi.smarthome.newui.dragsort.ItemTouchHelperViewHolder;
import com.xiaomi.smarthome.newui.dragsort.SimpleItemDelegateTouchHelperCallback;
import com.xiaomi.smarthome.newui.utils.StringLengthUtils;
import com.xiaomi.smarthome.newui.widget.GridItemAnimView;
import com.xiaomi.smarthome.newui.widget.micards.CardStatusObserver;
import com.xiaomi.smarthome.smartconfig.DeviceFinder;
import com.xiaomi.smarthome.stat.STAT;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class BaseDeviceGridCardViewHolder extends RecyclerView.ViewHolder implements ItemTouchHelperViewHolder {
    private static final String b = "BaseDeviceGridCardViewHolder";
    private static int l = 8;
    private static final String[] m = {"小米", "米家", "米兔", "小蚁", "青米", "飞利浦", "Yeelight", "智米", "90分", "美的", "奥克斯", "金兴", "iHealth", "小吉", "云米", "Aqara", "素士", "创米", "夏洛克"};

    /* renamed from: a  reason: collision with root package name */
    public View f20403a;
    /* access modifiers changed from: private */
    public Context c;
    @BindView(2131428355)
    public CheckBox checkBox;
    /* access modifiers changed from: private */
    public DviceEditInterface d;
    @BindView(2131433285)
    public TextView descDeviceState;
    @BindView(2131433286)
    public View descSplit;
    @BindView(2131433283)
    public TextView deviceNameTv;
    /* access modifiers changed from: private */
    public SimpleItemDelegateTouchHelperCallback e;
    /* access modifiers changed from: private */
    public ItemTouchHelper f;
    /* access modifiers changed from: private */
    public DeviceMainGridSwitchHelper g;
    @BindView(2131429500)
    public GridItemAnimView gridItemAnimView;
    private View.OnTouchListener h;
    private DeviceCateHelper.CateType i;
    @BindView(2131429981)
    public ImageView isNew;
    @BindView(2131429685)
    public SimpleDraweeView iv;
    /* access modifiers changed from: private */
    public Device j;
    /* access modifiers changed from: private */
    public boolean k = false;
    @BindView(2131431268)
    public ImageView offlineTipIv;
    @BindView(2131433467)
    public TextView roomNameTv;
    @BindView(2131432436)
    public ImageView switchIv;
    @BindView(2131432437)
    public FrameLayout switchIvContainer;

    public BaseDeviceGridCardViewHolder(@NonNull View view, Context context, DviceEditInterface dviceEditInterface, SimpleItemDelegateTouchHelperCallback simpleItemDelegateTouchHelperCallback, ItemTouchHelper itemTouchHelper) {
        super(view);
        this.f20403a = view;
        this.c = context;
        this.d = dviceEditInterface;
        this.e = simpleItemDelegateTouchHelperCallback;
        this.f = itemTouchHelper;
        this.g = new DeviceMainGridSwitchHelper(context, this, this.d);
        this.h = new View.OnTouchListener() {
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if (motionEvent.getAction() != 0 || BaseDeviceGridCardViewHolder.this.e == null) {
                    return false;
                }
                BaseDeviceGridCardViewHolder.this.e.a((int) motionEvent.getX(), (int) motionEvent.getY());
                return false;
            }
        };
        ButterKnife.bind((Object) this, view);
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
        this.gridItemAnimView.setEnabled(false);
        this.gridItemAnimView.setVisibility(8);
        this.offlineTipIv.setVisibility(8);
        this.checkBox.setVisibility(8);
        this.isNew.setVisibility(8);
        this.switchIvContainer.setVisibility(8);
        this.f20403a.setBackgroundResource(R.drawable.main_grid_card_bg_normal_v2);
    }

    public void a(Device device, int i2) {
        this.j = device;
        if (b(device)) {
            b(device, i2);
            return;
        }
        this.i = DeviceCateHelper.a().a(device);
        c(device, i2);
    }

    private static boolean b(Device device) {
        return (device instanceof PhoneIRDevice) || (device != null && IRDeviceUtil.a(device.did));
    }

    public void a() {
        this.itemView.setSelected(true);
    }

    public void b() {
        this.itemView.setSelected(false);
    }

    private void b(final Device device, final int i2) {
        c();
        this.deviceNameTv.setVisibility(0);
        this.deviceNameTv.setText(R.string.phone_ir_device);
        this.roomNameTv.setVisibility(0);
        this.roomNameTv.setText(R.string.phone);
        int b2 = IRDeviceUtil.b(this.c, (IRDeviceUtil.IRDeviceListener) new IRDeviceUtil.IRDeviceListener() {
            public void onQueryComplete(List<IRRemoteInfo> list) {
                if (BaseDeviceGridCardViewHolder.this.c != null) {
                    if (!(BaseDeviceGridCardViewHolder.this.c instanceof BaseActivity) || !((BaseActivity) BaseDeviceGridCardViewHolder.this.c).isValid()) {
                        if (BaseDeviceGridCardViewHolder.this.c instanceof Activity) {
                            if (((Activity) BaseDeviceGridCardViewHolder.this.c).isFinishing()) {
                                return;
                            }
                            if (Build.VERSION.SDK_INT >= 17 && ((Activity) BaseDeviceGridCardViewHolder.this.c).isDestroyed()) {
                                return;
                            }
                        }
                        final int c = IRDeviceUtil.c(BaseDeviceGridCardViewHolder.this.c);
                        SHApplication.getGlobalHandler().post(new Runnable() {
                            public void run() {
                                BaseDeviceGridCardViewHolder.this.descSplit.setVisibility(0);
                                BaseDeviceGridCardViewHolder.this.descDeviceState.setVisibility(0);
                                String quantityString = BaseDeviceGridCardViewHolder.this.c.getResources().getQuantityString(R.plurals.ir_device_count_2, c, new Object[]{Integer.valueOf(c)});
                                BaseDeviceGridCardViewHolder.this.descDeviceState.setText(String.format("%s%d", new Object[]{quantityString, Integer.valueOf(c)}));
                                BaseDeviceGridCardViewHolder.this.e();
                            }
                        });
                        return;
                    }
                    final int c2 = IRDeviceUtil.c(BaseDeviceGridCardViewHolder.this.c);
                    SHApplication.getGlobalHandler().post(new Runnable() {
                        public void run() {
                            BaseDeviceGridCardViewHolder.this.descSplit.setVisibility(0);
                            BaseDeviceGridCardViewHolder.this.descDeviceState.setVisibility(0);
                            String quantityString = BaseDeviceGridCardViewHolder.this.c.getResources().getQuantityString(R.plurals.ir_device_count_2, c2, new Object[]{Integer.valueOf(c2)});
                            BaseDeviceGridCardViewHolder.this.descDeviceState.setText(String.format("%s%d", new Object[]{quantityString, Integer.valueOf(c2)}));
                            BaseDeviceGridCardViewHolder.this.e();
                        }
                    });
                }
            }
        });
        String quantityString = this.c.getResources().getQuantityString(R.plurals.ir_device_count_2, b2, new Object[]{Integer.valueOf(b2)});
        this.descSplit.setVisibility(0);
        this.descDeviceState.setVisibility(0);
        this.descDeviceState.setText(String.format("%s%d", new Object[]{quantityString, Integer.valueOf(b2)}));
        e();
        if (this.d.b() && this.h != null) {
            this.f20403a.setOnTouchListener(this.h);
        }
        final int q = this.d.q();
        final Device device2 = device;
        final int i3 = i2;
        final Device device3 = device;
        this.f20403a.setOnLongClickListener(new View.OnLongClickListener() {
            public boolean onLongClick(View view) {
                if (!BaseDeviceGridCardViewHolder.this.d.b() && BaseDeviceGridCardViewHolder.this.d.c() != DviceEditInterface.HostPage.TRANSFER_GUIDE_PAGE) {
                    ToastUtil.a((int) R.string.share_family_none_editable);
                    return true;
                } else if (CardStatusObserver.getCardStatus() != CardStatusObserver.CardStatus.HIDING) {
                    return true;
                } else {
                    if (BaseDeviceGridCardViewHolder.this.d.t() != null && !BaseDeviceGridCardViewHolder.this.d.L_()) {
                        BaseDeviceGridCardViewHolder.this.d.t().enterEditMode(BaseDeviceGridCardViewHolder.this.d);
                    }
                    if (!(BaseDeviceGridCardViewHolder.this.f == null || BaseDeviceGridCardViewHolder.this.e == null || BaseDeviceGridCardViewHolder.this.e.b() || !BaseDeviceGridCardViewHolder.this.d.L_() || q == 2)) {
                        BaseDeviceGridCardViewHolder.this.f.startDrag(BaseDeviceGridCardViewHolder.this);
                    }
                    if (device2 == null) {
                        return false;
                    }
                    STAT.d.a(device2.model, BaseDeviceGridCardViewHolder.this.d.q(), i3, BaseDeviceGridCardViewHolder.this.d.n(), device3.did);
                    return false;
                }
            }
        });
        this.f20403a.setOnClickListener(new View.OnClickListener(device) {

            /* renamed from: a  reason: collision with root package name */
            final /* synthetic */ Device f20410a;

            {
                this.f20410a = r2;
            }

            /* JADX WARNING: Code restructure failed: missing block: B:6:0x0039, code lost:
                r1 = com.xiaomi.smarthome.frame.core.CoreApi.a().d(r4.f20410a.model);
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
                    com.xiaomi.smarthome.newui.adapter.main_grid.BaseDeviceGridCardViewHolder r1 = com.xiaomi.smarthome.newui.adapter.main_grid.BaseDeviceGridCardViewHolder.this
                    com.xiaomi.smarthome.newui.adapter.DviceEditInterface r1 = r1.d
                    boolean r1 = r1.L_()
                    if (r1 != 0) goto L_0x006d
                    com.xiaomi.smarthome.device.Device r1 = r4.f20410a
                    r2 = 1
                    if (r1 == 0) goto L_0x0056
                    com.xiaomi.smarthome.device.Device r1 = r4.f20410a
                    java.lang.String r1 = r1.model
                    boolean r1 = android.text.TextUtils.isEmpty(r1)
                    if (r1 != 0) goto L_0x0056
                    com.xiaomi.smarthome.frame.core.CoreApi r1 = com.xiaomi.smarthome.frame.core.CoreApi.a()
                    com.xiaomi.smarthome.device.Device r3 = r4.f20410a
                    java.lang.String r3 = r3.model
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
                    com.xiaomi.smarthome.newui.adapter.main_grid.BaseDeviceGridCardViewHolder r3 = com.xiaomi.smarthome.newui.adapter.main_grid.BaseDeviceGridCardViewHolder.this
                    boolean r3 = r3.g()
                    if (r3 == 0) goto L_0x0062
                    if (r1 == 0) goto L_0x0062
                    goto L_0x0063
                L_0x0062:
                    r2 = 0
                L_0x0063:
                    com.xiaomi.smarthome.newui.adapter.main_grid.BaseDeviceGridCardViewHolder r1 = com.xiaomi.smarthome.newui.adapter.main_grid.BaseDeviceGridCardViewHolder.this
                    com.xiaomi.smarthome.newui.adapter.main_grid.BaseDeviceGridCardViewHolder$4$1 r3 = new com.xiaomi.smarthome.newui.adapter.main_grid.BaseDeviceGridCardViewHolder$4$1
                    r3.<init>(r2, r0)
                    r1.a(r5, r2, r3)
                L_0x006d:
                    return
                */
                throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.smarthome.newui.adapter.main_grid.BaseDeviceGridCardViewHolder.AnonymousClass4.onClick(android.view.View):void");
            }
        });
    }

    private boolean d() {
        int q = this.d.q();
        return q == 0 || q == 3;
    }

    private void c(final Device device, final int i2) {
        c();
        String a2 = a(this.deviceNameTv, a((String) device.getName()));
        this.deviceNameTv.setVisibility(0);
        this.deviceNameTv.setText(a2);
        this.deviceNameTv.setAlpha(device.isOnlineAdvance() ? 1.0f : 0.2f);
        DeviceFactory.b(device.model, this.iv);
        ArrayList<Pair> c2 = MainPageOpManager.a().c(device);
        if (b(this.j.did)) {
            StringBuilder sb = new StringBuilder();
            sb.append("renderNormalDevice:");
            sb.append(c2 == null ? null : Integer.valueOf(c2.size()));
            Log.d("hzd11", sb.toString());
        }
        if (c2 == null || c2.size() <= 0) {
            a(device);
        } else {
            a(c2);
        }
        final int q = this.d.q();
        int i3 = 8;
        if (d()) {
            this.roomNameTv.setVisibility(0);
            this.roomNameTv.setText(HomeManager.a().r(device.did));
        } else {
            this.roomNameTv.setVisibility(8);
        }
        if (this.d.L_()) {
            this.checkBox.setVisibility(0);
            this.switchIvContainer.setVisibility(8);
            this.offlineTipIv.setVisibility(8);
        } else {
            this.checkBox.setVisibility(8);
        }
        final Set<String> l2 = this.d.l();
        if (l2 != null) {
            this.checkBox.setChecked(l2.contains(device.did));
        }
        if (this.h != null) {
            this.f20403a.setOnTouchListener(this.h);
        }
        AnonymousClass5 r0 = new View.OnLongClickListener() {
            public boolean onLongClick(View view) {
                if (!BaseDeviceGridCardViewHolder.this.d.b() && BaseDeviceGridCardViewHolder.this.d.c() != DviceEditInterface.HostPage.TRANSFER_GUIDE_PAGE) {
                    ToastUtil.a((int) R.string.share_family_none_editable);
                    return true;
                } else if (CardStatusObserver.getCardStatus() != CardStatusObserver.CardStatus.HIDING) {
                    return true;
                } else {
                    if (BaseDeviceGridCardViewHolder.this.d.t() != null && !BaseDeviceGridCardViewHolder.this.d.L_()) {
                        BaseDeviceGridCardViewHolder.this.d.t().enterEditMode(BaseDeviceGridCardViewHolder.this.d);
                        BaseDeviceGridCardViewHolder.this.d.a(i2, true);
                    }
                    if (!(BaseDeviceGridCardViewHolder.this.f == null || BaseDeviceGridCardViewHolder.this.e == null || BaseDeviceGridCardViewHolder.this.e.b() || !BaseDeviceGridCardViewHolder.this.d.L_() || q == 2)) {
                        BaseDeviceGridCardViewHolder.this.f.startDrag(BaseDeviceGridCardViewHolder.this);
                        if (TextUtils.equals(DeviceFinder.a().i(), device.did)) {
                            DeviceFinder.a().j();
                        }
                        if (device.isNew) {
                            if (device instanceof BleDevice) {
                                ((BleDevice) device).b(false);
                            } else {
                                device.isNew = false;
                            }
                        }
                    }
                    STAT.d.a(device.model, BaseDeviceGridCardViewHolder.this.d.q(), i2, BaseDeviceGridCardViewHolder.this.d.n(), device.did);
                    return false;
                }
            }
        };
        if (this.d != null) {
            this.f20403a.setOnLongClickListener(r0);
        }
        final Device device2 = device;
        final int i4 = i2;
        this.f20403a.setOnClickListener(new View.OnClickListener() {
            /* JADX WARNING: Code restructure failed: missing block: B:8:0x0079, code lost:
                r1 = com.xiaomi.smarthome.frame.core.CoreApi.a().d(r6.model);
             */
            /* Code decompiled incorrectly, please refer to instructions dump. */
            public void onClick(android.view.View r7) {
                /*
                    r6 = this;
                    java.lang.String r0 = "PluginStartTime"
                    java.lang.StringBuilder r1 = new java.lang.StringBuilder
                    r1.<init>()
                    java.lang.String r2 = "onClickHomeDevice  "
                    r1.append(r2)
                    long r2 = java.lang.System.currentTimeMillis()
                    r1.append(r2)
                    java.lang.String r1 = r1.toString()
                    com.xiaomi.smarthome.framework.log.LogUtil.c(r0, r1)
                    android.graphics.RectF r0 = com.xiaomi.smarthome.library.common.util.ViewUtils.b((android.view.View) r7)
                    com.xiaomi.smarthome.newui.adapter.main_grid.BaseDeviceGridCardViewHolder r1 = com.xiaomi.smarthome.newui.adapter.main_grid.BaseDeviceGridCardViewHolder.this
                    com.xiaomi.smarthome.newui.adapter.DviceEditInterface r1 = r1.d
                    boolean r1 = r1.L_()
                    r2 = 1
                    if (r1 == 0) goto L_0x006f
                    com.xiaomi.smarthome.newui.adapter.main_grid.BaseDeviceGridCardViewHolder r7 = com.xiaomi.smarthome.newui.adapter.main_grid.BaseDeviceGridCardViewHolder.this
                    android.widget.CheckBox r7 = r7.checkBox
                    com.xiaomi.smarthome.newui.adapter.main_grid.BaseDeviceGridCardViewHolder r0 = com.xiaomi.smarthome.newui.adapter.main_grid.BaseDeviceGridCardViewHolder.this
                    android.widget.CheckBox r0 = r0.checkBox
                    boolean r0 = r0.isChecked()
                    r0 = r0 ^ r2
                    r7.setChecked(r0)
                    java.util.Set r7 = r5
                    com.xiaomi.smarthome.device.Device r0 = r6
                    java.lang.String r0 = r0.did
                    boolean r7 = r7.contains(r0)
                    if (r7 != 0) goto L_0x005b
                    com.xiaomi.smarthome.newui.adapter.main_grid.BaseDeviceGridCardViewHolder r7 = com.xiaomi.smarthome.newui.adapter.main_grid.BaseDeviceGridCardViewHolder.this
                    com.xiaomi.smarthome.newui.adapter.DviceEditInterface r7 = r7.d
                    int r0 = r7
                    com.xiaomi.smarthome.newui.adapter.main_grid.BaseDeviceGridCardViewHolder r1 = com.xiaomi.smarthome.newui.adapter.main_grid.BaseDeviceGridCardViewHolder.this
                    android.widget.CheckBox r1 = r1.checkBox
                    boolean r1 = r1.isChecked()
                    r7.a(r0, r1)
                    goto L_0x006e
                L_0x005b:
                    com.xiaomi.smarthome.newui.adapter.main_grid.BaseDeviceGridCardViewHolder r7 = com.xiaomi.smarthome.newui.adapter.main_grid.BaseDeviceGridCardViewHolder.this
                    com.xiaomi.smarthome.newui.adapter.DviceEditInterface r7 = r7.d
                    int r0 = r7
                    com.xiaomi.smarthome.newui.adapter.main_grid.BaseDeviceGridCardViewHolder r1 = com.xiaomi.smarthome.newui.adapter.main_grid.BaseDeviceGridCardViewHolder.this
                    android.widget.CheckBox r1 = r1.checkBox
                    boolean r1 = r1.isChecked()
                    r7.a(r0, r1)
                L_0x006e:
                    return
                L_0x006f:
                    com.xiaomi.smarthome.device.Device r1 = r6
                    java.lang.String r1 = r1.model
                    boolean r1 = android.text.TextUtils.isEmpty(r1)
                    if (r1 != 0) goto L_0x0096
                    com.xiaomi.smarthome.frame.core.CoreApi r1 = com.xiaomi.smarthome.frame.core.CoreApi.a()
                    com.xiaomi.smarthome.device.Device r3 = r6
                    java.lang.String r3 = r3.model
                    com.xiaomi.smarthome.core.entity.plugin.PluginRecord r1 = r1.d((java.lang.String) r3)
                    if (r1 == 0) goto L_0x0096
                    com.xiaomi.smarthome.core.entity.plugin.PluginPackageInfo r3 = r1.h()
                    if (r3 == 0) goto L_0x0096
                    com.xiaomi.smarthome.core.entity.plugin.PluginPackageInfo r1 = r1.h()
                    boolean r1 = r1.d()
                    goto L_0x0097
                L_0x0096:
                    r1 = 1
                L_0x0097:
                    com.xiaomi.smarthome.newui.adapter.main_grid.BaseDeviceGridCardViewHolder r3 = com.xiaomi.smarthome.newui.adapter.main_grid.BaseDeviceGridCardViewHolder.this
                    boolean r3 = r3.g()
                    r4 = 0
                    if (r3 == 0) goto L_0x00a3
                    if (r1 == 0) goto L_0x00a3
                    goto L_0x00a4
                L_0x00a3:
                    r2 = 0
                L_0x00a4:
                    com.xiaomi.smarthome.newui.adapter.main_grid.BaseDeviceGridCardViewHolder r1 = com.xiaomi.smarthome.newui.adapter.main_grid.BaseDeviceGridCardViewHolder.this
                    com.xiaomi.smarthome.newui.adapter.main_grid.BaseDeviceGridCardViewHolder$6$1 r5 = new com.xiaomi.smarthome.newui.adapter.main_grid.BaseDeviceGridCardViewHolder$6$1
                    r5.<init>(r3, r2, r0)
                    r1.a(r7, r2, r5)
                    com.xiaomi.smarthome.device.Device r7 = r6
                    boolean r7 = r7.isNew
                    if (r7 == 0) goto L_0x00c6
                    com.xiaomi.smarthome.device.Device r7 = r6
                    boolean r7 = r7 instanceof com.xiaomi.smarthome.device.BleDevice
                    if (r7 == 0) goto L_0x00c2
                    com.xiaomi.smarthome.device.Device r7 = r6
                    com.xiaomi.smarthome.device.BleDevice r7 = (com.xiaomi.smarthome.device.BleDevice) r7
                    r7.b((boolean) r4)
                    goto L_0x00c6
                L_0x00c2:
                    com.xiaomi.smarthome.device.Device r7 = r6
                    r7.isNew = r4
                L_0x00c6:
                    return
                */
                throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.smarthome.newui.adapter.main_grid.BaseDeviceGridCardViewHolder.AnonymousClass6.onClick(android.view.View):void");
            }
        });
        ImageView imageView = this.isNew;
        if (device.isNew) {
            i3 = 0;
        }
        imageView.setVisibility(i3);
    }

    private boolean b(String str) {
        return TextUtils.equals(str, "63158284");
    }

    /* JADX WARNING: Removed duplicated region for block: B:13:0x003e  */
    /* JADX WARNING: Removed duplicated region for block: B:24:0x00b8  */
    /* JADX WARNING: Removed duplicated region for block: B:31:0x00e5  */
    /* JADX WARNING: Removed duplicated region for block: B:40:0x0129  */
    /* JADX WARNING: Removed duplicated region for block: B:41:0x012b  */
    /* JADX WARNING: Removed duplicated region for block: B:44:0x0141  */
    /* JADX WARNING: Removed duplicated region for block: B:53:0x0173  */
    /* JADX WARNING: Removed duplicated region for block: B:54:0x018a  */
    /* JADX WARNING: Removed duplicated region for block: B:58:0x019e  */
    /* JADX WARNING: Removed duplicated region for block: B:72:0x01eb  */
    /* JADX WARNING: Removed duplicated region for block: B:76:0x0205  */
    /* JADX WARNING: Removed duplicated region for block: B:79:0x021b  */
    /* JADX WARNING: Removed duplicated region for block: B:96:? A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void a(java.util.ArrayList<android.util.Pair> r12) {
        /*
            r11 = this;
            com.xiaomi.smarthome.MainPageOpManager r0 = com.xiaomi.smarthome.MainPageOpManager.a()
            com.xiaomi.smarthome.device.Device r1 = r11.j
            com.xiaomi.smarthome.newui.card.Card r0 = r0.a(r1)
            java.util.List r0 = r0.a()
            r1 = 0
            r2 = 1
            r3 = 0
            if (r0 == 0) goto L_0x002b
            int r4 = r0.size()
            if (r4 != r2) goto L_0x002b
            java.lang.Object r0 = r0.get(r3)
            com.xiaomi.smarthome.newui.card.Card$CardType r0 = (com.xiaomi.smarthome.newui.card.Card.CardType) r0
            int r4 = r0.b
            if (r4 == r2) goto L_0x0028
            int r4 = r0.b
            r5 = 2
            if (r4 != r5) goto L_0x002c
        L_0x0028:
            r4 = r0
            r0 = 1
            goto L_0x002e
        L_0x002b:
            r0 = r1
        L_0x002c:
            r4 = r0
            r0 = 0
        L_0x002e:
            com.xiaomi.smarthome.device.Device r5 = r11.j
            boolean r5 = r5.isOnlineAdvance()
            com.xiaomi.smarthome.device.Device r6 = r11.j
            java.lang.String r6 = r6.did
            boolean r6 = r11.b((java.lang.String) r6)
            if (r6 == 0) goto L_0x0086
            java.lang.String r6 = "hzd11"
            java.lang.StringBuilder r7 = new java.lang.StringBuilder
            r7.<init>()
            java.lang.String r8 = "renderDeviceGridOp isOnline="
            r7.append(r8)
            r7.append(r5)
            java.lang.String r8 = ",isOperationType="
            r7.append(r8)
            r7.append(r0)
            java.lang.String r8 = ",renderData.get(0)="
            r7.append(r8)
            java.lang.Object r8 = r12.get(r3)
            r7.append(r8)
            java.lang.String r8 = ",pair.first="
            r7.append(r8)
            java.lang.Object r8 = r12.get(r3)
            android.util.Pair r8 = (android.util.Pair) r8
            java.lang.Object r8 = r8.first
            r7.append(r8)
            java.lang.String r8 = ",mDevice.isSharedReadOnly()"
            r7.append(r8)
            com.xiaomi.smarthome.device.Device r8 = r11.j
            boolean r8 = r8.isSharedReadOnly()
            r7.append(r8)
            java.lang.String r7 = r7.toString()
            android.util.Log.d(r6, r7)
        L_0x0086:
            if (r5 == 0) goto L_0x016b
            if (r0 == 0) goto L_0x016b
            java.lang.Object r0 = r12.get(r3)
            android.util.Pair r0 = (android.util.Pair) r0
            if (r0 == 0) goto L_0x016b
            java.lang.Object r5 = r0.first
            boolean r5 = r5 instanceof com.xiaomi.smarthome.newui.card.CardItem.State
            if (r5 == 0) goto L_0x016b
            com.xiaomi.smarthome.device.Device r5 = r11.j
            boolean r5 = r5.isSharedReadOnly()
            if (r5 != 0) goto L_0x016b
            long r5 = android.os.SystemClock.elapsedRealtime()
            com.xiaomi.smarthome.newui.adapter.main_grid.DeviceMainGridSwitchHelper r7 = r11.g
            com.xiaomi.smarthome.device.Device r8 = r11.j
            java.lang.String r8 = r8.did
            com.xiaomi.smarthome.newui.adapter.main_grid.DeviceMainGridSwitchHelper$DeviceSwitchStatus r7 = r7.a((java.lang.String) r8)
            com.xiaomi.smarthome.device.Device r8 = r11.j
            java.lang.String r8 = r8.did
            boolean r8 = r11.b((java.lang.String) r8)
            if (r8 == 0) goto L_0x00dc
            java.lang.String r8 = "hzd11"
            java.lang.StringBuilder r9 = new java.lang.StringBuilder
            r9.<init>()
            java.lang.String r10 = "updateOperationTypeViewByStatus1 pair="
            r9.append(r10)
            r9.append(r0)
            java.lang.String r10 = ",first"
            r9.append(r10)
            if (r0 != 0) goto L_0x00d0
            r10 = r1
            goto L_0x00d2
        L_0x00d0:
            java.lang.Object r10 = r0.first
        L_0x00d2:
            r9.append(r10)
            java.lang.String r9 = r9.toString()
            android.util.Log.d(r8, r9)
        L_0x00dc:
            long r7 = r7.f20427a
            long r5 = r5 - r7
            r7 = 300(0x12c, double:1.48E-321)
            int r9 = (r5 > r7 ? 1 : (r5 == r7 ? 0 : -1))
            if (r9 <= 0) goto L_0x0117
            com.xiaomi.smarthome.device.Device r5 = r11.j
            java.lang.String r5 = r5.did
            boolean r5 = r11.b((java.lang.String) r5)
            if (r5 == 0) goto L_0x0112
            java.lang.String r5 = "hzd11"
            java.lang.StringBuilder r6 = new java.lang.StringBuilder
            r6.<init>()
            java.lang.String r7 = "updateOperationTypeViewByStatus2 pair="
            r6.append(r7)
            r6.append(r0)
            java.lang.String r7 = ",first"
            r6.append(r7)
            if (r0 != 0) goto L_0x0106
            goto L_0x0108
        L_0x0106:
            java.lang.Object r1 = r0.first
        L_0x0108:
            r6.append(r1)
            java.lang.String r1 = r6.toString()
            android.util.Log.d(r5, r1)
        L_0x0112:
            com.xiaomi.smarthome.newui.adapter.main_grid.DeviceMainGridSwitchHelper r1 = r11.g
            r1.a((android.util.Pair<com.xiaomi.smarthome.newui.card.CardItem.State, java.lang.String>) r0)
        L_0x0117:
            android.widget.FrameLayout r1 = r11.switchIvContainer
            r1.setVisibility(r3)
            com.xiaomi.smarthome.newui.widget.GridItemAnimView r1 = r11.gridItemAnimView
            r1.setVisibility(r3)
            java.util.concurrent.atomic.AtomicBoolean r1 = new java.util.concurrent.atomic.AtomicBoolean
            java.lang.Object r0 = r0.first
            com.xiaomi.smarthome.newui.card.CardItem$State r5 = com.xiaomi.smarthome.newui.card.CardItem.State.SELECTED
            if (r0 != r5) goto L_0x012b
            r0 = 1
            goto L_0x012c
        L_0x012b:
            r0 = 0
        L_0x012c:
            r1.<init>(r0)
            android.widget.FrameLayout r0 = r11.switchIvContainer
            com.xiaomi.smarthome.newui.adapter.main_grid.BaseDeviceGridCardViewHolder$7 r5 = new com.xiaomi.smarthome.newui.adapter.main_grid.BaseDeviceGridCardViewHolder$7
            r5.<init>()
            r0.setOnTouchListener(r5)
            android.widget.FrameLayout r0 = r11.switchIvContainer
            int r0 = r0.getVisibility()
            if (r0 != 0) goto L_0x0161
            com.facebook.drawee.view.SimpleDraweeView r0 = r11.iv
            r0.setImportantForAccessibility(r3)
            com.facebook.drawee.view.SimpleDraweeView r0 = r11.iv
            boolean r5 = r1.get()
            if (r5 == 0) goto L_0x0158
            android.content.Context r5 = r11.c
            r6 = 2131495679(0x7f0c0aff, float:1.8614901E38)
        L_0x0153:
            java.lang.String r5 = r5.getString(r6)
            goto L_0x015e
        L_0x0158:
            android.content.Context r5 = r11.c
            r6 = 2131495678(0x7f0c0afe, float:1.86149E38)
            goto L_0x0153
        L_0x015e:
            r0.setContentDescription(r5)
        L_0x0161:
            android.widget.FrameLayout r0 = r11.switchIvContainer
            com.xiaomi.smarthome.newui.adapter.main_grid.BaseDeviceGridCardViewHolder$8 r5 = new com.xiaomi.smarthome.newui.adapter.main_grid.BaseDeviceGridCardViewHolder$8
            r5.<init>(r1, r3, r4)
            r0.setOnClickListener(r5)
        L_0x016b:
            boolean r0 = r11.d()
            r1 = 8
            if (r0 == 0) goto L_0x018a
            android.widget.TextView r0 = r11.roomNameTv
            r0.setVisibility(r3)
            com.xiaomi.smarthome.homeroom.HomeManager r0 = com.xiaomi.smarthome.homeroom.HomeManager.a()
            com.xiaomi.smarthome.device.Device r4 = r11.j
            java.lang.String r4 = r4.did
            java.lang.String r0 = r0.r(r4)
            android.widget.TextView r4 = r11.roomNameTv
            r4.setText(r0)
            goto L_0x018f
        L_0x018a:
            android.widget.TextView r0 = r11.roomNameTv
            r0.setVisibility(r1)
        L_0x018f:
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            java.util.Iterator r12 = r12.iterator()
        L_0x0198:
            boolean r4 = r12.hasNext()
            if (r4 == 0) goto L_0x01dd
            java.lang.Object r4 = r12.next()
            android.util.Pair r4 = (android.util.Pair) r4
            if (r4 == 0) goto L_0x0198
            java.lang.Object r5 = r4.first
            boolean r5 = r5 instanceof java.lang.String
            if (r5 == 0) goto L_0x0198
            java.lang.Object r5 = r4.second
            if (r5 == 0) goto L_0x0198
            java.lang.Object r5 = r4.second
            java.lang.String r5 = java.lang.String.valueOf(r5)
            java.lang.String r5 = r5.trim()
            com.xiaomi.smarthome.newui.adapter.main_grid.DeviceCateHelper$CateType r6 = r11.i
            if (r6 != 0) goto L_0x01d4
            boolean r6 = android.text.TextUtils.isEmpty(r5)
            if (r6 != 0) goto L_0x01d4
            java.lang.Object r4 = r4.first
            java.lang.String r4 = (java.lang.String) r4
            java.lang.String r4 = r4.trim()
            r0.append(r4)
            java.lang.String r4 = " "
            r0.append(r4)
        L_0x01d4:
            r0.append(r5)
            java.lang.String r4 = " "
            r0.append(r4)
            goto L_0x0198
        L_0x01dd:
            java.lang.String r12 = r0.toString()
            java.lang.String r12 = r12.trim()
            boolean r0 = android.text.TextUtils.isEmpty(r12)
            if (r0 != 0) goto L_0x0205
            r11.e()
            android.view.View r0 = r11.descSplit
            boolean r4 = r11.d()
            if (r4 == 0) goto L_0x01f7
            r1 = 0
        L_0x01f7:
            r0.setVisibility(r1)
            android.widget.TextView r0 = r11.descDeviceState
            r0.setVisibility(r3)
            android.widget.TextView r0 = r11.descDeviceState
            r0.setText(r12)
            goto L_0x0210
        L_0x0205:
            android.view.View r12 = r11.descSplit
            r0 = 4
            r12.setVisibility(r0)
            android.widget.TextView r12 = r11.descDeviceState
            r12.setVisibility(r1)
        L_0x0210:
            com.xiaomi.smarthome.device.Device r12 = r11.j
            r11.a((com.xiaomi.smarthome.device.Device) r12)
            com.xiaomi.smarthome.device.Device r12 = r11.j
            boolean r12 = r12 instanceof com.xiaomi.smarthome.device.BleDevice
            if (r12 == 0) goto L_0x0257
            com.xiaomi.smarthome.MainPageOpManager r12 = com.xiaomi.smarthome.MainPageOpManager.a()
            com.xiaomi.smarthome.device.Device r0 = r11.j
            java.util.ArrayList r12 = r12.b(r0)
            if (r12 != 0) goto L_0x022c
            java.util.ArrayList r12 = new java.util.ArrayList
            r12.<init>()
        L_0x022c:
            java.util.Iterator r12 = r12.iterator()
            r0 = 0
        L_0x0231:
            boolean r1 = r12.hasNext()
            if (r1 == 0) goto L_0x0248
            java.lang.Object r1 = r12.next()
            java.lang.Long r1 = (java.lang.Long) r1
            long r4 = r1.longValue()
            boolean r1 = com.xiaomi.smarthome.newui.card.CardItemUtils.a((long) r4)
            r1 = r1 ^ r2
            r0 = r0 | r1
            goto L_0x0231
        L_0x0248:
            if (r0 != 0) goto L_0x0257
            android.widget.ImageView r12 = r11.offlineTipIv
            r0 = 2130838193(0x7f0202b1, float:1.7281361E38)
            r12.setImageResource(r0)
            android.widget.ImageView r12 = r11.offlineTipIv
            r12.setVisibility(r3)
        L_0x0257:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.smarthome.newui.adapter.main_grid.BaseDeviceGridCardViewHolder.a(java.util.ArrayList):void");
    }

    public void a(Device device) {
        Card<? extends Card.CardType> a2 = MainPageOpManager.a().a(device);
        if (!device.isOnlineAdvance() && !(device instanceof BleDevice) && (!(a2 instanceof ProfileCard) || !((ProfileCard) a2).g)) {
            this.switchIvContainer.setVisibility(8);
            this.gridItemAnimView.setVisibility(8);
            this.checkBox.setVisibility(8);
            this.f20403a.setBackgroundResource(R.drawable.main_grid_card_bg_offline_v2);
            this.iv.setAlpha(0.3f);
            this.deviceNameTv.setAlpha(0.2f);
            this.roomNameTv.setAlpha(0.3f);
            this.descDeviceState.setAlpha(0.3f);
            this.descDeviceState.setVisibility(8);
            this.descSplit.setVisibility(8);
            this.descSplit.setAlpha(0.3f);
            return;
        }
        this.offlineTipIv.setVisibility(8);
        this.f20403a.setBackgroundResource(R.drawable.main_grid_card_bg_normal_v2);
        this.iv.setAlpha(1.0f);
        this.roomNameTv.setAlpha(1.0f);
        this.deviceNameTv.setAlpha(1.0f);
        this.descDeviceState.setAlpha(1.0f);
        this.descSplit.setAlpha(1.0f);
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
        if (!this.k) {
            this.k = true;
            ValueAnimator ofFloat = ValueAnimator.ofFloat(new float[]{1.0f, 0.9f, 1.0f});
            ofFloat.setDuration(360);
            ofFloat.addUpdateListener(new ValueAnimator.AnimatorUpdateListener(view) {
                private final /* synthetic */ View f$0;

                {
                    this.f$0 = r1;
                }

                public final void onAnimationUpdate(ValueAnimator valueAnimator) {
                    BaseDeviceGridCardViewHolder.a(this.f$0, valueAnimator);
                }
            });
            ofFloat.addListener(new AnimatorListenerAdapter() {
                public void onAnimationEnd(Animator animator) {
                    if (z) {
                        runnable.run();
                    }
                    view.postDelayed(new Runnable() {
                        public final void run() {
                            BaseDeviceGridCardViewHolder.AnonymousClass10.this.a();
                        }
                    }, 100);
                }

                /* access modifiers changed from: private */
                public /* synthetic */ void a() {
                    boolean unused = BaseDeviceGridCardViewHolder.this.k = false;
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
        if (TextUtils.isEmpty(str) || StringLengthUtils.a(str) <= l) {
            return str;
        }
        for (String str2 : m) {
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
