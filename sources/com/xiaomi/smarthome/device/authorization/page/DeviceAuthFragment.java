package com.xiaomi.smarthome.device.authorization.page;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.facebook.drawee.view.SimpleDraweeView;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.auth.bindaccount.ThirdAccountBindManager;
import com.xiaomi.smarthome.device.Device;
import com.xiaomi.smarthome.device.DeviceFactory;
import com.xiaomi.smarthome.device.SmartHomeDeviceManager;
import com.xiaomi.smarthome.device.api.DeviceApi;
import com.xiaomi.smarthome.device.authorization.BaseAuthData;
import com.xiaomi.smarthome.device.authorization.DeviceAuthData;
import com.xiaomi.smarthome.device.authorization.HomeRoomAuthData;
import com.xiaomi.smarthome.device.authorization.page.DeviceAuthFragment;
import com.xiaomi.smarthome.frame.AsyncCallback;
import com.xiaomi.smarthome.frame.Error;
import com.xiaomi.smarthome.framework.page.BaseFragment;
import com.xiaomi.smarthome.homeroom.HomeManager;
import com.xiaomi.smarthome.homeroom.MultiHomeDeviceManager;
import com.xiaomi.smarthome.homeroom.model.Home;
import com.xiaomi.smarthome.homeroom.model.Room;
import com.xiaomi.smarthome.library.common.util.ToastUtil;
import com.xiaomi.smarthome.library.common.widget.SwitchButton;
import com.xiaomi.smarthome.newui.HomeListDialogHelper;
import com.xiaomi.smarthome.newui.adapter.HeaderAndFooterWrapper;
import java.util.ArrayList;
import java.util.List;

public class DeviceAuthFragment extends BaseFragment {

    /* renamed from: a  reason: collision with root package name */
    public static String f15018a = "device";
    public static String b = "homeroom";
    View c;
    HeaderAndFooterWrapper d;
    HeaderAndFooterWrapper e;
    @BindView(2131428503)
    View emptyView;
    DeviceAuthData f;
    HomeRoomAuthData g;
    List<BaseAuthData.VoiceContrlData> h = new ArrayList();
    SwitchButton i;
    public String j;
    public boolean k = false;
    /* access modifiers changed from: private */
    public SparseBooleanArray l = new SparseBooleanArray();
    /* access modifiers changed from: private */
    public String m;
    @BindView(2131427688)
    View mAppBarLayout;
    @BindView(2131431951)
    View mAuthDeviceView;
    @BindView(2131431952)
    View mAuthHomeRoomView;
    @BindView(2131427740)
    TextView mAutoSelectTv;
    @BindView(2131428835)
    ImageView mDevIndicator;
    @BindView(2131428804)
    RecyclerView mRecyclerView;
    @BindView(2131431980)
    View mRlHomeSelect;
    @BindView(2131429643)
    ImageView mRoomIndicator;
    @BindView(2131432000)
    View mSceneAuth;
    @BindView(2131428020)
    SwitchButton mSelectAllBtn;
    @BindView(2131433284)
    TextView mTvDevSelect;
    @BindView(2131433347)
    TextView mTvHomeName;
    @BindView(2131433468)
    TextView mTvRoomSelect;
    @BindView(2131430829)
    View menuLayout;
    /* access modifiers changed from: private */
    public boolean n = false;
    private View o;
    private View p;
    private View q;
    private SwitchButton r;
    private View s;
    private SwitchButton t;
    private View u;

    class HomeRoomAdapter extends RecyclerView.Adapter<ViewHolder> {
        private List<Room> b = new ArrayList();

        public class ViewHolder_ViewBinding implements Unbinder {

            /* renamed from: a  reason: collision with root package name */
            private ViewHolder f15032a;

            @UiThread
            public ViewHolder_ViewBinding(ViewHolder viewHolder, View view) {
                this.f15032a = viewHolder;
                viewHolder.title = (TextView) Utils.findRequiredViewAsType(view, R.id.title, "field 'title'", TextView.class);
                viewHolder.checkBox = (CheckBox) Utils.findRequiredViewAsType(view, R.id.ratio_btn, "field 'checkBox'", CheckBox.class);
            }

            @CallSuper
            public void unbind() {
                ViewHolder viewHolder = this.f15032a;
                if (viewHolder != null) {
                    this.f15032a = null;
                    viewHolder.title = null;
                    viewHolder.checkBox = null;
                    return;
                }
                throw new IllegalStateException("Bindings already cleared.");
            }
        }

        public HomeRoomAdapter(Home home) {
            if (home != null && home.d() != null) {
                this.b.addAll(home.d());
                Room p = HomeManager.a().p(DeviceAuthFragment.this.m);
                if (p != null && this.b.contains(p)) {
                    this.b.remove(p);
                    this.b.add(0, p);
                }
            }
        }

        /* renamed from: a */
        public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
            return new ViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.home_room_auth_slave_list, viewGroup, false));
        }

        /* renamed from: a */
        public void onBindViewHolder(ViewHolder viewHolder, int i) {
            Room room = this.b.get(i);
            if (room != null) {
                viewHolder.itemView.setBackgroundResource(i == this.b.size() - 1 ? R.drawable.common_white_list_padding_no_left_margin : R.drawable.common_white_list_padding);
                StringBuilder sb = new StringBuilder(room.e());
                if (room.h().contains(DeviceAuthFragment.this.g.b())) {
                    sb.append(" ");
                    sb.append(DeviceAuthFragment.this.getString(R.string.device_located_room));
                }
                viewHolder.title.setText(sb);
                float f = 1.0f;
                viewHolder.title.setAlpha(DeviceAuthFragment.this.g.c() ? 0.3f : 1.0f);
                CheckBox checkBox = viewHolder.checkBox;
                if (DeviceAuthFragment.this.g.c()) {
                    f = 0.3f;
                }
                checkBox.setAlpha(f);
                viewHolder.checkBox.setOnCheckedChangeListener((CompoundButton.OnCheckedChangeListener) null);
                if (DeviceAuthFragment.this.g.c()) {
                    viewHolder.checkBox.setChecked(true);
                } else if (DeviceAuthFragment.this.g.g().contains(room.d())) {
                    viewHolder.checkBox.setChecked(true);
                } else {
                    viewHolder.checkBox.setChecked(false);
                }
                if (DeviceAuthFragment.this.g.c()) {
                    viewHolder.checkBox.setEnabled(false);
                    return;
                }
                viewHolder.checkBox.setEnabled(true);
                viewHolder.checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener(room) {
                    private final /* synthetic */ Room f$1;

                    {
                        this.f$1 = r2;
                    }

                    public final void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                        DeviceAuthFragment.HomeRoomAdapter.this.a(this.f$1, compoundButton, z);
                    }
                });
            }
        }

        /* access modifiers changed from: private */
        public /* synthetic */ void a(Room room, CompoundButton compoundButton, boolean z) {
            a(room, z);
        }

        public int getItemCount() {
            if (DeviceAuthFragment.this.g == null) {
                return 0;
            }
            return this.b.size();
        }

        private void a(Room room, boolean z) {
            if (z && !DeviceAuthFragment.this.g.g().contains(room.d())) {
                DeviceAuthFragment.this.g.g().add(room.d());
            } else if (!z) {
                DeviceAuthFragment.this.g.g().remove(room.d());
            }
            notifyDataSetChanged();
            ((DeviceAuthSlaveListActivity) DeviceAuthFragment.this.getActivity()).setDeviceAuthChanged(true);
        }

        public class ViewHolder extends RecyclerView.ViewHolder {
            @BindView(2131431751)
            CheckBox checkBox;
            @BindView(2131432910)
            TextView title;

            public ViewHolder(View view) {
                super(view);
                ButterKnife.bind((Object) this, view);
            }
        }
    }

    class DeviceAuthAdapter extends RecyclerView.Adapter<ViewHolder> {

        public class ViewHolder_ViewBinding implements Unbinder {

            /* renamed from: a  reason: collision with root package name */
            private ViewHolder f15029a;

            @UiThread
            public ViewHolder_ViewBinding(ViewHolder viewHolder, View view) {
                this.f15029a = viewHolder;
                viewHolder.title = (TextView) Utils.findRequiredViewAsType(view, R.id.title, "field 'title'", TextView.class);
                viewHolder.checkBox = (CheckBox) Utils.findRequiredViewAsType(view, R.id.ratio_btn, "field 'checkBox'", CheckBox.class);
                viewHolder.homeRoomName = (TextView) Utils.findRequiredViewAsType(view, R.id.home_room_name, "field 'homeRoomName'", TextView.class);
                viewHolder.deviceImg = (SimpleDraweeView) Utils.findRequiredViewAsType(view, R.id.device_icon, "field 'deviceImg'", SimpleDraweeView.class);
            }

            @CallSuper
            public void unbind() {
                ViewHolder viewHolder = this.f15029a;
                if (viewHolder != null) {
                    this.f15029a = null;
                    viewHolder.title = null;
                    viewHolder.checkBox = null;
                    viewHolder.homeRoomName = null;
                    viewHolder.deviceImg = null;
                    return;
                }
                throw new IllegalStateException("Bindings already cleared.");
            }
        }

        DeviceAuthAdapter() {
        }

        /* renamed from: a */
        public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
            return new ViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.device_auth_slave_list, viewGroup, false));
        }

        /* renamed from: a */
        public void onBindViewHolder(ViewHolder viewHolder, int i) {
            if (DeviceAuthFragment.this.f != null) {
                viewHolder.title.setText(DeviceAuthFragment.this.h.get(i).c);
                float f = 1.0f;
                viewHolder.title.setAlpha(DeviceAuthFragment.this.f.f.get() ? 0.3f : 1.0f);
                viewHolder.checkBox.setAlpha(DeviceAuthFragment.this.f.f.get() ? 0.3f : 1.0f);
                viewHolder.homeRoomName.setAlpha(DeviceAuthFragment.this.f.f.get() ? 0.3f : 1.0f);
                SimpleDraweeView simpleDraweeView = viewHolder.deviceImg;
                if (DeviceAuthFragment.this.f.f.get()) {
                    f = 0.3f;
                }
                simpleDraweeView.setAlpha(f);
                viewHolder.itemView.setBackgroundResource(i == DeviceAuthFragment.this.h.size() - 1 ? R.drawable.common_white_list_padding_no_left_margin : R.drawable.common_white_list_padding);
                if (!TextUtils.isEmpty(DeviceAuthFragment.this.h.get(i).e)) {
                    ThirdAccountBindManager.a(viewHolder.deviceImg, DeviceAuthFragment.this.h.get(i).e);
                } else {
                    DeviceFactory.b(DeviceAuthFragment.this.h.get(i).d, viewHolder.deviceImg);
                }
                viewHolder.checkBox.setOnCheckedChangeListener((CompoundButton.OnCheckedChangeListener) null);
                if (TextUtils.equals("1", DeviceAuthFragment.this.h.get(i).b)) {
                    viewHolder.checkBox.setChecked(true);
                } else {
                    viewHolder.checkBox.setChecked(false);
                }
                if (TextUtils.isEmpty(DeviceAuthFragment.this.h.get(i).f) && TextUtils.isEmpty(DeviceAuthFragment.this.h.get(i).g)) {
                    viewHolder.homeRoomName.setText("");
                } else if (!TextUtils.isEmpty(DeviceAuthFragment.this.h.get(i).f) && TextUtils.isEmpty(DeviceAuthFragment.this.h.get(i).g)) {
                    viewHolder.homeRoomName.setText(DeviceAuthFragment.this.h.get(i).f);
                } else if (TextUtils.isEmpty(DeviceAuthFragment.this.h.get(i).f) && !TextUtils.isEmpty(DeviceAuthFragment.this.h.get(i).g)) {
                    viewHolder.homeRoomName.setText(DeviceAuthFragment.this.h.get(i).g);
                } else if (!TextUtils.isEmpty(DeviceAuthFragment.this.h.get(i).f) && !TextUtils.isEmpty(DeviceAuthFragment.this.h.get(i).g)) {
                    TextView textView = viewHolder.homeRoomName;
                    textView.setText(DeviceAuthFragment.this.h.get(i).f + "-" + DeviceAuthFragment.this.h.get(i).g);
                }
                if (DeviceAuthFragment.this.f.f.get()) {
                    viewHolder.checkBox.setEnabled(false);
                    return;
                }
                viewHolder.checkBox.setEnabled(true);
                viewHolder.checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener(i) {
                    private final /* synthetic */ int f$1;

                    {
                        this.f$1 = r2;
                    }

                    public final void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                        DeviceAuthFragment.DeviceAuthAdapter.this.a(this.f$1, compoundButton, z);
                    }
                });
            }
        }

        /* access modifiers changed from: private */
        public /* synthetic */ void a(int i, CompoundButton compoundButton, boolean z) {
            a(i);
        }

        public int getItemCount() {
            return DeviceAuthFragment.this.h.size();
        }

        private void a(int i) {
            if (TextUtils.equals("0", DeviceAuthFragment.this.h.get(i).b)) {
                DeviceAuthFragment.this.h.get(i).b = "1";
            } else {
                DeviceAuthFragment.this.h.get(i).b = "0";
            }
            if (DeviceAuthFragment.this.l.get(i)) {
                DeviceAuthFragment.this.l.put(i, false);
            } else {
                DeviceAuthFragment.this.l.put(i, true);
            }
            notifyItemChanged(i);
            ((DeviceAuthSlaveListActivity) DeviceAuthFragment.this.getActivity()).setDeviceAuthChanged(true);
        }

        public class ViewHolder extends RecyclerView.ViewHolder {
            @BindView(2131431751)
            CheckBox checkBox;
            @BindView(2131428788)
            SimpleDraweeView deviceImg;
            @BindView(2131429641)
            TextView homeRoomName;
            @BindView(2131432910)
            TextView title;

            public ViewHolder(View view) {
                super(view);
                ButterKnife.bind((Object) this, view);
            }
        }
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.m = ((DeviceAuthSlaveListActivity) getActivity()).mDid;
        ((DeviceAuthSlaveListActivity) getActivity()).showProgressDialog(getResources().getString(R.string.loading_share_info));
        this.k = true;
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        if (this.c == null) {
            this.c = layoutInflater.inflate(R.layout.fragment_auth_device, (ViewGroup) null);
            ButterKnife.bind((Object) this, this.c);
        }
        return this.c;
    }

    public void onViewCreated(View view, Bundle bundle) {
        super.onViewCreated(view, bundle);
        c();
        if (this.k) {
            d();
            this.k = false;
        }
    }

    private void c() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this.mContext);
        linearLayoutManager.setSmoothScrollbarEnabled(true);
        this.mRecyclerView.setLayoutManager(linearLayoutManager);
        this.mRecyclerView.setHasFixedSize(true);
        this.mAuthDeviceView.setOnClickListener(new View.OnClickListener() {
            public final void onClick(View view) {
                DeviceAuthFragment.this.c(view);
            }
        });
        this.mAuthHomeRoomView.setOnClickListener(new View.OnClickListener() {
            public final void onClick(View view) {
                DeviceAuthFragment.this.b(view);
            }
        });
        this.mSceneAuth.setOnClickListener(new View.OnClickListener() {
            public final void onClick(View view) {
                DeviceAuthFragment.this.a(view);
            }
        });
        Device b2 = SmartHomeDeviceManager.a().b(this.m);
        if (b2 != null) {
            ((DeviceAuthSlaveListActivity) getActivity()).mTitleTextView.setText(b2.getName());
        } else {
            ((DeviceAuthSlaveListActivity) getActivity()).mTitleTextView.setText(R.string.auth_manager);
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void c(View view) {
        if (!TextUtils.equals(this.j, f15018a)) {
            this.j = f15018a;
            i();
            ((DeviceAuthSlaveListActivity) getActivity()).setDeviceAuthChanged(true);
            ToastUtil.a((int) R.string.auth_mode_change);
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void b(View view) {
        if (!TextUtils.equals(this.j, b)) {
            this.j = b;
            i();
            ((DeviceAuthSlaveListActivity) getActivity()).setDeviceAuthChanged(true);
            ToastUtil.a((int) R.string.auth_mode_change);
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void a(View view) {
        ((DeviceAuthSlaveListActivity) getActivity()).goSceneAuthFragment();
    }

    /* access modifiers changed from: private */
    public void d() {
        if (!this.n) {
            this.n = true;
            ThirdAccountBindManager.a().a((AsyncCallback<Void, Error>) new AsyncCallback<Void, Error>() {
                /* renamed from: a */
                public void onSuccess(Void voidR) {
                    if (DeviceAuthFragment.this.isValid()) {
                        DeviceAuthFragment.this.e();
                    }
                }

                public void onFailure(Error error) {
                    if (DeviceAuthFragment.this.isValid()) {
                        DeviceAuthFragment.this.e();
                    }
                }
            });
        }
    }

    /* access modifiers changed from: private */
    public void e() {
        ArrayList arrayList = new ArrayList();
        if (!TextUtils.isEmpty(this.m)) {
            arrayList.add(this.m);
        }
        DeviceApi.getInstance().getAllDeviceAuthData(getActivity(), arrayList, new AsyncCallback<BaseAuthData, Error>() {
            /* renamed from: a */
            public void onSuccess(BaseAuthData baseAuthData) {
                if (baseAuthData != null) {
                    DeviceAuthFragment.this.j = baseAuthData.b;
                    if (TextUtils.equals(DeviceAuthFragment.this.j, DeviceAuthFragment.f15018a) && (baseAuthData instanceof DeviceAuthData)) {
                        DeviceAuthFragment.this.f = (DeviceAuthData) baseAuthData;
                        DeviceAuthFragment.this.h = DeviceAuthFragment.this.f.d;
                        DeviceAuthFragment.this.i();
                    } else if (!TextUtils.equals(DeviceAuthFragment.this.j, DeviceAuthFragment.b) || !(baseAuthData instanceof HomeRoomAuthData)) {
                        DeviceAuthFragment.this.k();
                    } else {
                        DeviceAuthFragment.this.g = (HomeRoomAuthData) baseAuthData;
                        DeviceAuthFragment.this.f = DeviceAuthData.a(DeviceAuthFragment.this.g.b(), DeviceAuthFragment.this.g.c);
                        DeviceAuthFragment.this.h = DeviceAuthFragment.this.g.d;
                        DeviceAuthFragment.this.i();
                    }
                } else {
                    DeviceAuthFragment.this.k();
                }
                ((DeviceAuthSlaveListActivity) DeviceAuthFragment.this.getActivity()).setDeviceDataReady(true, true);
                boolean unused = DeviceAuthFragment.this.n = false;
            }

            public void onFailure(Error error) {
                DeviceAuthFragment.this.k();
                ((DeviceAuthSlaveListActivity) DeviceAuthFragment.this.getActivity()).setDeviceDataReady(true, false);
                boolean unused = DeviceAuthFragment.this.n = false;
            }
        });
    }

    /* access modifiers changed from: private */
    public void f() {
        if (this.f != null) {
            int i2 = 0;
            if (this.f.f.compareAndSet(false, true)) {
                for (int i3 = 0; i3 < this.h.size(); i3++) {
                    this.h.get(i3).b = "1";
                    this.l.put(i3, true);
                }
            } else {
                this.f.f.set(false);
            }
            this.d.notifyDataSetChanged();
            View view = this.u;
            if (this.f.f.get()) {
                i2 = 8;
            }
            view.setVisibility(i2);
            ((DeviceAuthSlaveListActivity) getActivity()).setDeviceAuthChanged(true);
        }
    }

    /* access modifiers changed from: private */
    public void g() {
        if (this.g != null) {
            if (!this.g.c()) {
                List<String> g2 = this.g.g();
                List<Room> a2 = HomeManager.a().a(this.g.f());
                if (a2 != null) {
                    g2.clear();
                    for (Room d2 : a2) {
                        g2.add(d2.d());
                    }
                }
                this.g.a(true);
            } else {
                this.g.a(false);
            }
            this.e.notifyDataSetChanged();
            ((DeviceAuthSlaveListActivity) getActivity()).setDeviceAuthChanged(true);
        }
    }

    private void h() {
        this.mSelectAllBtn.setOnCheckedChangeListener((CompoundButton.OnCheckedChangeListener) null);
        if (TextUtils.equals(this.j, f15018a) && this.f != null) {
            this.mSelectAllBtn.setChecked(this.f.f.get());
        } else if (TextUtils.equals(this.j, b) && this.g != null) {
            this.mSelectAllBtn.setChecked(this.g.c());
        }
        this.mSelectAllBtn.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                if (TextUtils.equals(DeviceAuthFragment.this.j, DeviceAuthFragment.f15018a)) {
                    DeviceAuthFragment.this.f();
                } else if (TextUtils.equals(DeviceAuthFragment.this.j, DeviceAuthFragment.b)) {
                    DeviceAuthFragment.this.g();
                }
            }
        });
    }

    /* access modifiers changed from: private */
    public void i() {
        if (TextUtils.equals(this.j, f15018a)) {
            this.mDevIndicator.setVisibility(0);
            this.mRoomIndicator.setVisibility(4);
            this.mTvDevSelect.setTextColor(getResources().getColor(R.color.class_text_33));
            this.mTvRoomSelect.setTextColor(getResources().getColor(R.color.black));
            this.mRlHomeSelect.setVisibility(8);
            this.mAutoSelectTv.setText(R.string.voice_control_device_auth_device);
            a();
        } else if (TextUtils.equals(this.j, b)) {
            this.mDevIndicator.setVisibility(4);
            this.mRoomIndicator.setVisibility(0);
            this.mTvRoomSelect.setTextColor(getResources().getColor(R.color.class_text_33));
            this.mTvDevSelect.setTextColor(getResources().getColor(R.color.black));
            this.mRlHomeSelect.setVisibility(0);
            this.mAutoSelectTv.setText(R.string.voice_control_device_auth_room);
            if (this.g == null && HomeManager.a().q(this.m) != null) {
                this.g = HomeRoomAuthData.a(this.m, HomeManager.a().q(this.m).j());
            }
            b();
        }
    }

    public void a() {
        this.l.clear();
        if (this.h.isEmpty()) {
            k();
            return;
        }
        j();
        if (this.o == null) {
            this.o = LayoutInflater.from(this.mContext).inflate(R.layout.fragment_auth_device_footer, this.mRecyclerView, false);
            this.u = this.o.findViewById(R.id.rl_new_device_auto_auth);
            this.i = (SwitchButton) this.o.findViewById(R.id.btn_auto_auth);
        }
        if (this.d == null) {
            this.d = new HeaderAndFooterWrapper(new DeviceAuthAdapter());
            this.d.b(this.o);
        }
        this.u.setVisibility(this.f.f.get() ? 8 : 0);
        this.i.setOnCheckedChangeListener((CompoundButton.OnCheckedChangeListener) null);
        this.i.setChecked(this.f.g);
        this.i.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public final void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                DeviceAuthFragment.this.a(compoundButton, z);
            }
        });
        this.mRecyclerView.setAdapter(this.d);
        this.d.notifyDataSetChanged();
        this.menuLayout.setVisibility(0);
        h();
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void a(CompoundButton compoundButton, boolean z) {
        this.f.g = z;
        ((DeviceAuthSlaveListActivity) getActivity()).setDeviceAuthChanged(true);
    }

    public void b() {
        if (this.g == null) {
            k();
            return;
        }
        Home j2 = HomeManager.a().j(this.g.f());
        if (j2 == null) {
            k();
            return;
        }
        j();
        int i2 = 0;
        if (this.p == null) {
            this.p = LayoutInflater.from(this.mContext).inflate(R.layout.fragment_auth_home_room_footer, this.mRecyclerView, false);
            this.s = this.p.findViewById(R.id.other_platform_device);
            this.t = (SwitchButton) this.p.findViewById(R.id.btn_other_platform_device);
            this.q = this.p.findViewById(R.id.shared_device);
            this.r = (SwitchButton) this.p.findViewById(R.id.btn_shared_device);
        }
        if (!MultiHomeDeviceManager.a().e().isEmpty()) {
            this.q.setVisibility(0);
        }
        if (!ThirdAccountBindManager.a().d().isEmpty()) {
            this.s.setVisibility(0);
        }
        this.mTvHomeName.setText(j2.k());
        this.r.setOnCheckedChangeListener((CompoundButton.OnCheckedChangeListener) null);
        this.t.setOnCheckedChangeListener((CompoundButton.OnCheckedChangeListener) null);
        this.r.setChecked(this.g.d());
        this.t.setChecked(this.g.e());
        this.mRlHomeSelect.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                HomeListDialogHelper.a((Context) DeviceAuthFragment.this.getActivity(), DeviceAuthFragment.this.m, DeviceAuthFragment.this.g.f(), (HomeListDialogHelper.ItemClickListener) new HomeListDialogHelper.ItemClickListener() {
                    public void a(Object obj) {
                        if (obj instanceof String) {
                            String str = (String) obj;
                            if (!TextUtils.equals(DeviceAuthFragment.this.g.f(), str)) {
                                DeviceAuthFragment.this.g = HomeRoomAuthData.a(DeviceAuthFragment.this.m, str);
                                DeviceAuthFragment.this.i();
                                ((DeviceAuthSlaveListActivity) DeviceAuthFragment.this.getActivity()).setDeviceAuthChanged(true);
                            }
                        }
                    }
                });
            }
        });
        this.r.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                DeviceAuthFragment.this.g.b(z);
                ((DeviceAuthSlaveListActivity) DeviceAuthFragment.this.getActivity()).setDeviceAuthChanged(true);
            }
        });
        this.t.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                DeviceAuthFragment.this.g.c(z);
                ((DeviceAuthSlaveListActivity) DeviceAuthFragment.this.getActivity()).setDeviceAuthChanged(true);
            }
        });
        this.e = new HeaderAndFooterWrapper(new HomeRoomAdapter(j2));
        this.e.b(this.p);
        this.mRecyclerView.setAdapter(this.e);
        this.e.notifyDataSetChanged();
        View view = this.menuLayout;
        if (this.e.getItemCount() <= 0) {
            i2 = 8;
        }
        view.setVisibility(i2);
        h();
    }

    private void j() {
        this.emptyView.setVisibility(8);
        this.mAppBarLayout.setVisibility(0);
        this.mRecyclerView.setVisibility(0);
    }

    /* access modifiers changed from: private */
    public void k() {
        this.emptyView.setVisibility(0);
        this.mAppBarLayout.setVisibility(8);
        this.mRecyclerView.setVisibility(8);
        this.emptyView.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if (!DeviceAuthFragment.this.n) {
                    DeviceAuthFragment.this.d();
                }
            }
        });
        this.emptyView.setBackgroundResource(R.color.transparent);
        ((ImageView) this.emptyView.findViewById(R.id.empty_icon)).setImageResource(R.drawable.device_empty);
        ((TextView) this.emptyView.findViewById(R.id.common_white_empty_text)).setText(R.string.no_data_tips);
    }
}
