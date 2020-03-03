package com.xiaomi.smarthome.miio.page.msgcentersetting;

import android.app.Activity;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.facebook.drawee.view.SimpleDraweeView;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.application.SHApplication;
import com.xiaomi.smarthome.device.DeviceFactory;
import com.xiaomi.smarthome.family.api.RemoteFamilyApi;
import com.xiaomi.smarthome.frame.AsyncCallback;
import com.xiaomi.smarthome.frame.Error;
import com.xiaomi.smarthome.framework.page.BaseActivity;
import com.xiaomi.smarthome.homeroom.HomeManager;
import com.xiaomi.smarthome.homeroom.model.Home;
import com.xiaomi.smarthome.homeroom.model.Room;
import com.xiaomi.smarthome.library.common.dialog.XQProgressDialog;
import com.xiaomi.smarthome.library.common.util.ToastUtil;
import com.xiaomi.smarthome.library.common.widget.SwitchButton;
import com.xiaomi.smarthome.miio.page.msgcentersetting.model.DeviceMsgSettingChild;
import com.xiaomi.smarthome.newui.HomeListDialogHelper;
import java.util.ArrayList;
import java.util.List;

public class DevicePushSettingActivity extends BaseActivity {
    public static final int CODE_DEVICE_PUSH = 300;
    public static final String EXTRA_HOME_DEVICE = "home_device";
    public static final String EXTRA_KEY = "device_push";
    public static final String EXTRA_SHARE_DEVICE = "share_device";

    /* renamed from: a  reason: collision with root package name */
    private static final String f19843a = "DevicePushSettingActivity";
    private XQProgressDialog b;
    /* access modifiers changed from: private */
    public MsgSettingAdapter c;
    /* access modifiers changed from: private */
    public String d;
    /* access modifiers changed from: private */
    public Home e;
    /* access modifiers changed from: private */
    public List<DeviceMsgSettingChild> f = new ArrayList();
    /* access modifiers changed from: private */
    public int g = 3;
    /* access modifiers changed from: private */
    public DevicePushViewModel h;
    @BindView(2131430797)
    View mMaskView;
    @BindView(2131429586)
    ImageView mMenuIcon;
    @BindView(2131432956)
    View mTitleGroup;
    @BindView(2131430969)
    ImageView moduleA3ReturnBtn;
    @BindView(2131430975)
    TextView moduleA3ReturnTitle;
    @BindView(2131432305)
    RecyclerView settingList;
    @BindView(2131432919)
    View titleBar;

    /* access modifiers changed from: private */
    public static /* synthetic */ void a(DialogInterface dialogInterface) {
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_device_push_setting);
        ButterKnife.bind((Activity) this);
        this.d = getIntent().getStringExtra(EXTRA_KEY);
        if (TextUtils.isEmpty(this.d)) {
            finish();
            return;
        }
        this.e = HomeManager.a().m();
        if (this.e == null) {
            finish();
            return;
        }
        this.h = (DevicePushViewModel) ViewModelProviders.a((FragmentActivity) this).a(DevicePushViewModel.class);
        this.h.a().observe(this, new Observer() {
            public final void onChanged(Object obj) {
                DevicePushSettingActivity.this.a((List) obj);
            }
        });
        a();
        c();
        this.h.a(this.e.j(), TextUtils.equals(this.d, EXTRA_SHARE_DEVICE));
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void a(List list) {
        d();
        if (this.h.b().getValue() != null) {
            this.g = this.h.b().getValue().intValue();
        }
        if ((list == null || list.size() == 0) && TextUtils.equals(this.d, EXTRA_SHARE_DEVICE)) {
            findViewById(R.id.empty_view).setVisibility(0);
        }
        this.f = list;
        this.c.notifyDataSetChanged();
    }

    @OnClick({2131430969})
    public void onClick(View view) {
        finish();
    }

    public void onBackPressed() {
        this.moduleA3ReturnBtn.performClick();
    }

    private void a() {
        this.settingList.setLayoutManager(new LinearLayoutManager(this));
        this.c = new MsgSettingAdapter();
        this.settingList.setAdapter(this.c);
        int i = 8;
        if (TextUtils.equals(this.d, EXTRA_HOME_DEVICE)) {
            ImageView imageView = this.mMenuIcon;
            if (HomeManager.a().f().size() > 1) {
                i = 0;
            }
            imageView.setVisibility(i);
            this.moduleA3ReturnTitle.setText(this.e.k());
            this.mTitleGroup.setOnClickListener(new View.OnClickListener() {
                public final void onClick(View view) {
                    DevicePushSettingActivity.this.b(view);
                }
            });
        } else {
            this.mMenuIcon.setVisibility(8);
            this.moduleA3ReturnTitle.setText(R.string.shared_device_room_name);
        }
        this.titleBar.setBackgroundColor(Color.parseColor("#ffffff"));
        this.mMenuIcon.setOnClickListener(new View.OnClickListener() {
            public final void onClick(View view) {
                DevicePushSettingActivity.this.a(view);
            }
        });
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void b(View view) {
        if (this.mMenuIcon.getVisibility() == 0) {
            this.mMenuIcon.performClick();
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void a(View view) {
        b();
    }

    private void b() {
        a(true);
        HomeListDialogHelper.a((Context) this, this.titleBar, false, this.e.j(), false, (HomeListDialogHelper.HomeListDialogV2Listener) new HomeListDialogHelper.HomeListDialogV2Listener() {
            public void a() {
                DevicePushSettingActivity.this.a(false);
            }

            public void a(Home home) {
                if (home != null && !TextUtils.equals(home.j(), DevicePushSettingActivity.this.e.j())) {
                    Home unused = DevicePushSettingActivity.this.e = home;
                    DevicePushSettingActivity.this.moduleA3ReturnTitle.setText(DevicePushSettingActivity.this.e.k());
                    DevicePushSettingActivity.this.c();
                    DevicePushSettingActivity.this.h.a(DevicePushSettingActivity.this.e.j(), TextUtils.equals(DevicePushSettingActivity.this.d, DevicePushSettingActivity.EXTRA_SHARE_DEVICE));
                }
            }
        });
    }

    /* access modifiers changed from: private */
    public void a(boolean z) {
        if (isValid() && this.mMenuIcon != null && this.mMaskView != null) {
            this.mMenuIcon.animate().rotation(z ? -180.0f : 0.0f).setDuration(200).setInterpolator(new AccelerateDecelerateInterpolator());
            this.mMaskView.setVisibility(z ? 0 : 8);
            this.mMaskView.setAnimation(AnimationUtils.loadAnimation(SHApplication.getAppContext(), z ? R.anim.dd_mask_in : R.anim.dd_mask_out));
        }
    }

    /* access modifiers changed from: private */
    public void a(final DeviceMsgSettingChild deviceMsgSettingChild) {
        boolean equals = TextUtils.equals(this.d, EXTRA_SHARE_DEVICE);
        ArrayList arrayList = new ArrayList();
        if (deviceMsgSettingChild != null) {
            arrayList.add(deviceMsgSettingChild);
        }
        RemoteFamilyApi.a().a(equals ? null : this.e, this.g, (List<DeviceMsgSettingChild>) arrayList, (AsyncCallback<Void, Error>) new AsyncCallback<Void, Error>() {
            /* renamed from: a */
            public void onSuccess(Void voidR) {
                if (DevicePushSettingActivity.this.isValid()) {
                    DevicePushSettingActivity.this.c.notifyDataSetChanged();
                }
            }

            public void onFailure(Error error) {
                if (DevicePushSettingActivity.this.isValid()) {
                    ToastUtil.a((int) R.string.home_set_failed);
                    if (DevicePushSettingActivity.this.f != null && deviceMsgSettingChild != null) {
                        if (DevicePushSettingActivity.this.f.contains(deviceMsgSettingChild)) {
                            deviceMsgSettingChild.j = Integer.valueOf(deviceMsgSettingChild.j.intValue() == 0 ? 3 : 0);
                        }
                        DevicePushSettingActivity.this.c.notifyDataSetChanged();
                    }
                }
            }
        });
    }

    /* access modifiers changed from: private */
    public void c() {
        if (this.b == null) {
            this.b = new XQProgressDialog(this);
            this.b.setCancelable(false);
            this.b.setMessage(getResources().getString(R.string.loading_share_info));
            this.b.setCancelable(true);
            this.b.setOnDismissListener($$Lambda$DevicePushSettingActivity$FbwKTfwSaK8kM0YmZ4mS71qoDAU.INSTANCE);
        }
        if (!this.b.isShowing()) {
            this.b.show();
        }
    }

    private void d() {
        if (this.b != null && this.b.isShowing()) {
            this.b.dismiss();
        }
    }

    private class MsgSettingAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
        private static final int b = 0;
        private static final int c = 2;

        public long getItemId(int i) {
            return (long) i;
        }

        private MsgSettingAdapter() {
        }

        @NonNull
        public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
            LayoutInflater from = LayoutInflater.from(DevicePushSettingActivity.this);
            if (i == 0) {
                return new GroupViewHolder(from.inflate(R.layout.item_msg_setting_system, (ViewGroup) null));
            }
            if (i != 2) {
                return null;
            }
            return new DeviceChildViewHolder(from.inflate(R.layout.item_msg_setting_device, (ViewGroup) null));
        }

        public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
            if (viewHolder instanceof GroupViewHolder) {
                ((GroupViewHolder) viewHolder).a(i);
            } else if (viewHolder instanceof DeviceChildViewHolder) {
                DeviceChildViewHolder deviceChildViewHolder = (DeviceChildViewHolder) viewHolder;
                if (TextUtils.equals(DevicePushSettingActivity.this.d, DevicePushSettingActivity.EXTRA_HOME_DEVICE)) {
                    i--;
                }
                deviceChildViewHolder.a(i);
            }
        }

        public int getItemCount() {
            if (DevicePushSettingActivity.this.f == null) {
                return 0;
            }
            if (TextUtils.equals(DevicePushSettingActivity.this.d, DevicePushSettingActivity.EXTRA_SHARE_DEVICE)) {
                if (DevicePushSettingActivity.this.f == null) {
                    return 0;
                }
                return DevicePushSettingActivity.this.f.size();
            } else if (!TextUtils.equals(DevicePushSettingActivity.this.d, DevicePushSettingActivity.EXTRA_HOME_DEVICE)) {
                return 0;
            } else {
                if (DevicePushSettingActivity.this.f == null) {
                    return 1;
                }
                return 1 + DevicePushSettingActivity.this.f.size();
            }
        }

        public int getItemViewType(int i) {
            int i2;
            if (DevicePushSettingActivity.this.f == null) {
                return -1;
            }
            if (TextUtils.equals(DevicePushSettingActivity.this.d, DevicePushSettingActivity.EXTRA_HOME_DEVICE)) {
                if (i == 0) {
                    return 0;
                }
                if (DevicePushSettingActivity.this.f == null || i - 1 < 0 || i2 >= DevicePushSettingActivity.this.f.size()) {
                    return -1;
                }
                return 2;
            } else if (TextUtils.equals(DevicePushSettingActivity.this.d, DevicePushSettingActivity.EXTRA_SHARE_DEVICE)) {
                return 2;
            }
            return -1;
        }
    }

    private class GroupViewHolder extends RecyclerView.ViewHolder {

        /* renamed from: a  reason: collision with root package name */
        public TextView f19848a;
        public SwitchButton b;
        private View.OnClickListener d = new View.OnClickListener() {
            public void onClick(View view) {
                GroupViewHolder.this.b.setChecked(!GroupViewHolder.this.b.isChecked());
                int unused = DevicePushSettingActivity.this.g = GroupViewHolder.this.b.isChecked() ? 3 : 0;
                DevicePushSettingActivity.this.a((DeviceMsgSettingChild) null);
            }
        };

        public GroupViewHolder(View view) {
            super(view);
            this.f19848a = (TextView) view.findViewById(R.id.device_push_item_title);
            this.b = (SwitchButton) view.findViewById(R.id.device_push_item_btn);
            this.b.setOnTouchEnable(false);
        }

        public void a(int i) {
            this.f19848a.setText(R.string.message_device_setting_title_v2);
            this.b.setChecked(DevicePushSettingActivity.this.g >= 3);
            this.itemView.setOnClickListener(this.d);
            this.b.setOnClickListener(this.d);
        }
    }

    private class DeviceChildViewHolder extends RecyclerView.ViewHolder {

        /* renamed from: a  reason: collision with root package name */
        public TextView f19846a;
        public TextView b;
        public SwitchButton c;
        public SimpleDraweeView d;
        private View.OnClickListener f = new View.OnClickListener() {
            /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r4v12, resolved type: java.lang.Object} */
            /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v5, resolved type: com.xiaomi.smarthome.miio.page.msgcentersetting.model.DeviceMsgSettingChild} */
            /* JADX WARNING: Multi-variable type inference failed */
            /* Code decompiled incorrectly, please refer to instructions dump. */
            public void onClick(android.view.View r4) {
                /*
                    r3 = this;
                    java.lang.Object r4 = r4.getTag()     // Catch:{ Exception -> 0x000b }
                    java.lang.Integer r4 = (java.lang.Integer) r4     // Catch:{ Exception -> 0x000b }
                    int r4 = r4.intValue()     // Catch:{ Exception -> 0x000b }
                    goto L_0x0010
                L_0x000b:
                    r4 = move-exception
                    r4.printStackTrace()
                    r4 = -1
                L_0x0010:
                    r0 = 0
                    if (r4 < 0) goto L_0x0030
                    com.xiaomi.smarthome.miio.page.msgcentersetting.DevicePushSettingActivity$DeviceChildViewHolder r1 = com.xiaomi.smarthome.miio.page.msgcentersetting.DevicePushSettingActivity.DeviceChildViewHolder.this
                    com.xiaomi.smarthome.miio.page.msgcentersetting.DevicePushSettingActivity r1 = com.xiaomi.smarthome.miio.page.msgcentersetting.DevicePushSettingActivity.this
                    java.util.List r1 = r1.f
                    int r1 = r1.size()
                    if (r4 >= r1) goto L_0x0030
                    com.xiaomi.smarthome.miio.page.msgcentersetting.DevicePushSettingActivity$DeviceChildViewHolder r0 = com.xiaomi.smarthome.miio.page.msgcentersetting.DevicePushSettingActivity.DeviceChildViewHolder.this
                    com.xiaomi.smarthome.miio.page.msgcentersetting.DevicePushSettingActivity r0 = com.xiaomi.smarthome.miio.page.msgcentersetting.DevicePushSettingActivity.this
                    java.util.List r0 = r0.f
                    java.lang.Object r4 = r0.get(r4)
                    r0 = r4
                    com.xiaomi.smarthome.miio.page.msgcentersetting.model.DeviceMsgSettingChild r0 = (com.xiaomi.smarthome.miio.page.msgcentersetting.model.DeviceMsgSettingChild) r0
                L_0x0030:
                    if (r0 != 0) goto L_0x0033
                    return
                L_0x0033:
                    java.lang.String r4 = r0.f
                    boolean r4 = android.text.TextUtils.isEmpty(r4)
                    if (r4 == 0) goto L_0x003c
                    return
                L_0x003c:
                    java.lang.Integer r4 = r0.j
                    int r4 = r4.intValue()
                    r1 = 0
                    r2 = 3
                    if (r4 < r2) goto L_0x004d
                    java.lang.Integer r4 = java.lang.Integer.valueOf(r1)
                    r0.j = r4
                    goto L_0x0054
                L_0x004d:
                    java.lang.Integer r4 = java.lang.Integer.valueOf(r2)
                    r0.j = r4
                    r1 = 1
                L_0x0054:
                    com.xiaomi.smarthome.miio.page.msgcentersetting.DevicePushSettingActivity$DeviceChildViewHolder r4 = com.xiaomi.smarthome.miio.page.msgcentersetting.DevicePushSettingActivity.DeviceChildViewHolder.this
                    com.xiaomi.smarthome.library.common.widget.SwitchButton r4 = r4.c
                    r4.setChecked(r1)
                    com.xiaomi.smarthome.miio.page.msgcentersetting.DevicePushSettingActivity$DeviceChildViewHolder r4 = com.xiaomi.smarthome.miio.page.msgcentersetting.DevicePushSettingActivity.DeviceChildViewHolder.this
                    com.xiaomi.smarthome.miio.page.msgcentersetting.DevicePushSettingActivity r4 = com.xiaomi.smarthome.miio.page.msgcentersetting.DevicePushSettingActivity.this
                    r4.a((com.xiaomi.smarthome.miio.page.msgcentersetting.model.DeviceMsgSettingChild) r0)
                    return
                */
                throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.smarthome.miio.page.msgcentersetting.DevicePushSettingActivity.DeviceChildViewHolder.AnonymousClass1.onClick(android.view.View):void");
            }
        };

        public DeviceChildViewHolder(View view) {
            super(view);
            this.f19846a = (TextView) view.findViewById(R.id.device_name);
            this.b = (TextView) view.findViewById(R.id.room_name);
            this.c = (SwitchButton) view.findViewById(R.id.device_push_item_btn);
            this.d = (SimpleDraweeView) view.findViewById(R.id.device_icon);
            this.c.setOnTouchEnable(false);
        }

        public void a(int i) {
            DeviceMsgSettingChild deviceMsgSettingChild = (DeviceMsgSettingChild) DevicePushSettingActivity.this.f.get(i);
            if (deviceMsgSettingChild != null) {
                this.f19846a.setText(deviceMsgSettingChild.g);
                if (deviceMsgSettingChild.i != null) {
                    DeviceFactory.b(deviceMsgSettingChild.i, this.d);
                } else {
                    this.d.setImageResource(R.drawable.device_list_phone_no);
                }
                int i2 = 0;
                this.c.setChecked(deviceMsgSettingChild.j.intValue() >= 3);
                if (TextUtils.equals(DevicePushSettingActivity.this.d, DevicePushSettingActivity.EXTRA_SHARE_DEVICE)) {
                    this.b.setText(R.string.shared_device_room_name);
                } else {
                    StringBuilder sb = new StringBuilder();
                    sb.append(DevicePushSettingActivity.this.e.k());
                    Room i3 = HomeManager.a().i(deviceMsgSettingChild.h);
                    sb.append(" | ");
                    sb.append(i3 == null ? DevicePushSettingActivity.this.getString(R.string.default_room) : i3.e());
                    this.b.setText(sb.toString().trim());
                    View view = this.itemView;
                    if (DevicePushSettingActivity.this.g < 3) {
                        i2 = 8;
                    }
                    view.setVisibility(i2);
                }
                this.itemView.setOnClickListener(this.f);
                this.itemView.setTag(Integer.valueOf(i));
            }
        }
    }
}
