package com.xiaomi.smarthome.mibrain.anothernamesetting;

import android.app.Activity;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import com.facebook.drawee.view.SimpleDraweeView;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.device.Device;
import com.xiaomi.smarthome.device.DeviceFactory;
import com.xiaomi.smarthome.device.SmartHomeDeviceManager;
import com.xiaomi.smarthome.frame.core.CoreApi;
import com.xiaomi.smarthome.framework.page.BaseActivity;
import com.xiaomi.smarthome.homeroom.HomeManager;
import com.xiaomi.smarthome.homeroom.model.Home;
import com.xiaomi.smarthome.library.common.dialog.XQProgressDialog;
import com.xiaomi.smarthome.library.common.widget.sectionedrecyclerviewadapter.Section;
import com.xiaomi.smarthome.library.common.widget.sectionedrecyclerviewadapter.SectionParameters;
import com.xiaomi.smarthome.library.common.widget.sectionedrecyclerviewadapter.SectionedRecyclerViewAdapter;
import com.xiaomi.smarthome.mibrain.anothernamesetting.AnotherNameDevListActivity;
import com.xiaomi.smarthome.mibrain.anothernamesetting.AnotherNameDevListViewModel;
import java.util.List;

public class AnotherNameDevListActivity extends BaseActivity {
    public static final String INTENT_KEY_SHOW_GUIDE = "show_guide";

    /* renamed from: a  reason: collision with root package name */
    private SectionedRecyclerViewAdapter f10629a;
    private XQProgressDialog b;
    private Unbinder c;
    @BindView(2131428992)
    TextView mEmpty;
    @BindView(2131429517)
    LinearLayout mGuide;
    @BindView(2131429518)
    ImageView mGuideCancel;
    @BindView(2131429742)
    TextView mIntroduction;
    @BindView(2131431796)
    RecyclerView mRecyclerView;
    @BindView(2131430969)
    ImageView mReturn;
    @BindView(2131430975)
    TextView mTitle;
    @BindView(2131430982)
    ImageView mTitleRightIcon;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_another_name_dev_list);
        this.c = ButterKnife.bind((Activity) this);
        a();
        c();
        Intent intent = getIntent();
        if (intent != null && intent.getBooleanExtra("show_guide", false)) {
            g();
        }
    }

    /* access modifiers changed from: protected */
    public void onDestroy() {
        super.onDestroy();
        f();
        if (this.c != null) {
            this.c.unbind();
        }
    }

    private void a() {
        this.mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        this.f10629a = new SectionedRecyclerViewAdapter();
        this.mRecyclerView.setAdapter(this.f10629a);
        this.mGuideCancel.setOnClickListener(new View.OnClickListener() {
            public final void onClick(View view) {
                AnotherNameDevListActivity.this.c(view);
            }
        });
        b();
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void c(View view) {
        this.mGuide.setVisibility(8);
    }

    private void b() {
        this.mTitle.setText(R.string.voice_another_name_setting);
        this.mReturn.setOnClickListener(new View.OnClickListener() {
            public final void onClick(View view) {
                AnotherNameDevListActivity.this.b(view);
            }
        });
        this.mTitleRightIcon.setImageResource(R.drawable.title_right_info_drawable);
        this.mTitleRightIcon.setVisibility(0);
        this.mTitleRightIcon.setOnClickListener(new View.OnClickListener() {
            public final void onClick(View view) {
                AnotherNameDevListActivity.this.a(view);
            }
        });
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void b(View view) {
        finish();
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void a(View view) {
        g();
    }

    private void c() {
        ((AnotherNameDevListViewModel) ViewModelProviders.a((FragmentActivity) this).a(AnotherNameDevListViewModel.class)).a().observe(this, new Observer() {
            public final void onChanged(Object obj) {
                AnotherNameDevListActivity.this.b((List) obj);
            }
        });
        d();
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void b(List list) {
        a((List<AnotherNameDevListViewModel.GroupBean>) list);
        f();
    }

    private void a(List<AnotherNameDevListViewModel.GroupBean> list) {
        if (list != null) {
            this.f10629a.a();
            for (AnotherNameDevListViewModel.GroupBean next : list) {
                Home j = HomeManager.a().j(next.b);
                if (j != null) {
                    this.f10629a.a((Section) new HomeSection(j, next.c));
                }
            }
            if (this.f10629a.getItemCount() == 0) {
                this.mEmpty.setVisibility(0);
                this.mRecyclerView.setVisibility(8);
                return;
            }
            this.mEmpty.setVisibility(8);
            this.mRecyclerView.setVisibility(0);
            this.f10629a.notifyDataSetChanged();
        }
    }

    private void d() {
        e();
        ((AnotherNameDevListViewModel) ViewModelProviders.a((FragmentActivity) this).a(AnotherNameDevListViewModel.class)).b();
    }

    private void e() {
        f();
        this.b = XQProgressDialog.a(this, (CharSequence) null, getString(R.string.loading));
    }

    private void f() {
        if (this.b != null && this.b.isShowing()) {
            this.b.dismiss();
        }
    }

    private void g() {
        if (isValid()) {
            this.mGuide.setVisibility(0);
        }
    }

    public void onBackPressed() {
        if (this.mGuide.getVisibility() == 0) {
            this.mGuide.setVisibility(8);
        } else {
            super.onBackPressed();
        }
    }

    private class HomeSection extends Section {
        private Home b;
        private List<String> i;

        public HomeSection(Home home, List<String> list) {
            super(new SectionParameters.Builder(R.layout.another_name_item_section).a((int) R.layout.another_name_item_section_header).a());
            this.i = list;
            this.b = home;
        }

        public int a() {
            return this.i.size();
        }

        public RecyclerView.ViewHolder a(View view) {
            return new HeaderViewHolder(view);
        }

        public RecyclerView.ViewHolder b(View view) {
            return new ChildViewHolder(view);
        }

        public void a(RecyclerView.ViewHolder viewHolder, int i2) {
            if (i2 >= 0) {
                String str = this.i.get(i2);
                ChildViewHolder childViewHolder = (ChildViewHolder) viewHolder;
                childViewHolder.b.setText(HomeManager.a().r(str));
                Device b2 = SmartHomeDeviceManager.a().b(str);
                if (b2 == null) {
                    childViewHolder.f10631a.setText(R.string.tag_no_device);
                    return;
                }
                childViewHolder.f10631a.setText(b2.getName());
                DeviceFactory.b(b2.model, childViewHolder.c);
                childViewHolder.itemView.setOnClickListener(new View.OnClickListener(b2, str) {
                    private final /* synthetic */ Device f$1;
                    private final /* synthetic */ String f$2;

                    {
                        this.f$1 = r2;
                        this.f$2 = r3;
                    }

                    public final void onClick(View view) {
                        AnotherNameDevListActivity.HomeSection.this.a(this.f$1, this.f$2, view);
                    }
                });
                if (i2 == this.i.size() - 1) {
                    childViewHolder.d.setBackgroundResource(R.drawable.bottom_radius_rectangle_bg);
                    childViewHolder.e.setVisibility(8);
                    return;
                }
                childViewHolder.d.setBackgroundResource(R.color.white);
                childViewHolder.e.setVisibility(0);
            }
        }

        /* access modifiers changed from: private */
        public /* synthetic */ void a(Device device, String str, View view) {
            int i2 = 0;
            try {
                String P = CoreApi.a().d(device.model).c().P();
                if (!TextUtils.isEmpty(P)) {
                    i2 = Integer.parseInt(P);
                }
            } catch (Exception e) {
                Log.e("InitDeviceRoomActivity", "goNext", e);
                CoreApi.a().a(0, device.model, Log.getStackTraceString(e));
            }
            if (i2 <= 1) {
                Intent intent = new Intent(AnotherNameDevListActivity.this, AnotherNameEditActivity.class);
                intent.putExtra(AnotherNameEditActivity.KEY_ALIAS_DID, str);
                AnotherNameDevListActivity.this.startActivity(intent);
                return;
            }
            AnotherNameMultiKeyActivity.startActivity(AnotherNameDevListActivity.this, device.did, device.mac);
        }

        public void a(RecyclerView.ViewHolder viewHolder) {
            ((HeaderViewHolder) viewHolder).f10632a.setText(this.b.k());
        }

        private class HeaderViewHolder extends RecyclerView.ViewHolder {

            /* renamed from: a  reason: collision with root package name */
            TextView f10632a;

            public HeaderViewHolder(View view) {
                super(view);
                this.f10632a = (TextView) view.findViewById(R.id.name_tv);
            }
        }

        private class ChildViewHolder extends RecyclerView.ViewHolder {

            /* renamed from: a  reason: collision with root package name */
            TextView f10631a;
            TextView b;
            SimpleDraweeView c;
            View d;
            View e;

            public ChildViewHolder(View view) {
                super(view);
                this.d = view;
                this.f10631a = (TextView) view.findViewById(R.id.device_name_tv);
                this.b = (TextView) view.findViewById(R.id.room_name_tv);
                this.c = (SimpleDraweeView) view.findViewById(R.id.icon);
                this.e = view.findViewById(R.id.divider);
            }
        }
    }
}
