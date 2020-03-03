package com.xiaomi.smarthome.homeroom;

import android.app.Dialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v4.view.ViewPager;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.TextView;
import com.facebook.drawee.view.SimpleDraweeView;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.device.Device;
import com.xiaomi.smarthome.device.SmartHomeDeviceHelper;
import com.xiaomi.smarthome.device.SmartHomeDeviceManager;
import com.xiaomi.smarthome.frame.Error;
import com.xiaomi.smarthome.framework.api.AsyncResponseCallback;
import com.xiaomi.smarthome.framework.page.BaseActivity;
import com.xiaomi.smarthome.framework.statistic.StatHelper;
import com.xiaomi.smarthome.homeroom.HomeManager;
import com.xiaomi.smarthome.homeroom.model.Room;
import com.xiaomi.smarthome.library.common.dialog.MLAlertDialog;
import com.xiaomi.smarthome.library.common.util.StringUtil;
import com.xiaomi.smarthome.library.common.util.ToastUtil;
import com.xiaomi.smarthome.library.common.widget.ViewPagerSystem;
import com.xiaomi.smarthome.miio.TitleBarUtil;
import com.xiaomi.smarthome.miio.page.devicetag.DeviceTagEditorFragment;
import com.xiaomi.smarthome.newui.NameEditDialogHelper;
import com.xiaomi.smarthome.stat.STAT;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import miuipub.view.EditActionMode;

public class HomeRoomEditorActivityV2 extends BaseActivity implements TextWatcher, View.OnClickListener, HomeEditActivityBridge {
    public static final String ACTION_EDITOR_CHANGED = "editor_changed_action";
    public static final String HOME_ID_PARAM = "home_id_param";
    public static final String ROOM_ICON = "room_icon";
    public static final String ROOM_ID_PARAM = "room_id_param";
    public static final String ROOM_IMPORT = "room_import";
    public static final String ROOM_NAME = "room_name";
    /* access modifiers changed from: private */

    /* renamed from: a  reason: collision with root package name */
    public Room f18081a;
    private View b;
    private View c;
    /* access modifiers changed from: private */
    public TextView d;
    private SimpleDraweeView e;
    private TabLayout f;
    private ViewPagerSystem g;
    private Map<String, Boolean> h = new HashMap();
    /* access modifiers changed from: private */
    public List<Fragment> i = new ArrayList();
    /* access modifiers changed from: private */
    public int[] j = {R.string.home_name_short, R.string.router_name};
    private MyPagerAdapter k;
    private String l;
    NameEditDialogHelper.NameEditListener listener = new NameEditDialogHelper.NameEditListener() {
        public void a(String str) {
            if (!TextUtils.isEmpty(str)) {
                HomeRoomEditorActivityV2.this.d.setText(str);
            }
        }

        public String b(String str) {
            if (HomeManager.a().a(HomeRoomEditorActivityV2.this.f18081a, str)) {
                return HomeRoomEditorActivityV2.this.getString(R.string.room_name_duplicate);
            }
            return null;
        }
    };
    private TextView m;
    /* access modifiers changed from: private */
    public boolean n;
    private String o;
    private Dialog p;
    private BroadcastReceiver q = new BroadcastReceiver() {
        public void onReceive(Context context, Intent intent) {
            if (TextUtils.equals("editor_changed_action", intent.getAction())) {
                HomeRoomEditorActivityV2.this.d();
            }
        }
    };
    private Set<String> r = new HashSet();

    public void afterTextChanged(Editable editable) {
    }

    public void beforeTextChanged(CharSequence charSequence, int i2, int i3, int i4) {
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_home_room_editor_v2);
        String stringExtra = getIntent().getStringExtra("room_id_param");
        this.o = getIntent().getStringExtra("home_id_param");
        if (TextUtils.isEmpty(this.o)) {
            this.o = HomeManager.a().l();
        }
        if (TextUtils.isEmpty(stringExtra)) {
            this.f18081a = new Room();
            this.f18081a.a((List<String>) new ArrayList());
            this.f18081a.f(this.o);
            this.l = getIntent().getStringExtra("room_icon");
        } else {
            this.f18081a = HomeManager.a().i(stringExtra);
            if (this.f18081a == null) {
                this.f18081a = new Room();
                this.f18081a.a((List<String>) new ArrayList());
                this.f18081a.f(this.o);
            }
            this.l = this.f18081a.a();
        }
        this.n = getIntent().getBooleanExtra(ROOM_IMPORT, false);
        if (HomeManager.a().f() == null || HomeManager.a().f().size() <= 1) {
            this.j = new int[]{R.string.home_name_short};
        }
        List<String> h2 = this.f18081a.h();
        if (h2 != null) {
            for (int i2 = 0; i2 < h2.size(); i2++) {
                this.h.put(h2.get(i2), true);
                this.r.add(h2.get(i2));
            }
        } else {
            this.f18081a.a((List<String>) new ArrayList());
        }
        a();
    }

    private void a() {
        String stringExtra = getIntent().getStringExtra("room_name");
        TitleBarUtil.a(TitleBarUtil.a(), findViewById(R.id.title_bar_choose_device));
        this.b = findViewById(EditActionMode.b);
        this.c = findViewById(EditActionMode.f3057a);
        this.c.setBackgroundResource(R.drawable.title_right_tick_drawable);
        this.c.setEnabled(false);
        TextView textView = (TextView) findViewById(R.id.module_a_4_return_more_title);
        this.d = (TextView) findViewById(R.id.input_tag);
        this.d.setOnClickListener(this);
        this.e = (SimpleDraweeView) findViewById(R.id.icon);
        this.e.setImageURI(Uri.parse("file://" + HomeManager.a().f(this.l)));
        this.e.setOnClickListener(this);
        if (TextUtils.isEmpty(this.f18081a.e())) {
            textView.setText(R.string.tag_add_title);
            if (!TextUtils.isEmpty(stringExtra)) {
                this.d.setText(stringExtra);
                this.c.setEnabled(true);
            }
        } else {
            if (this.n) {
                textView.setText(R.string.import_room);
                this.c.setEnabled(true);
            } else {
                textView.setText(R.string.tag_editor_title);
            }
            this.d.setText(this.f18081a.e());
        }
        this.b.setOnClickListener(this);
        this.c.setOnClickListener(this);
        this.d.addTextChangedListener(this);
        this.m = (TextView) findViewById(R.id.count_tv);
        b();
        f();
        this.m.setText(String.format(getResources().getQuantityString(R.plurals.tag_selected_title, this.h.size(), new Object[]{Integer.valueOf(this.h.size())}), new Object[0]));
    }

    private void b() {
        this.i = new ArrayList();
        for (int i2 = 0; i2 < this.j.length; i2++) {
            Object obj = null;
            if (i2 == 0) {
                obj = new AllHomeDeviceListFragment();
            }
            if (i2 == 1) {
                obj = new RouterDeviceListByHomeFragment();
            }
            this.i.add(obj);
        }
    }

    private void c() {
        this.f = (TabLayout) findViewById(R.id.tab_layout);
        this.f.setSelectedTabIndicatorColor(getResources().getColor(R.color.class_text_17));
        this.f.setSelectedTabIndicatorHeight(0);
        this.f.setTabTextColors(getResources().getColor(R.color.class_D), getResources().getColor(R.color.class_text_17));
        this.f.setupWithViewPager(this.g);
        this.f.getTabAt(0).setText(getResources().getText(this.j[0]));
        this.f.setVisibility(8);
        if (this.j.length > 1) {
            this.f.getTabAt(1).setText(getResources().getText(this.j[1]));
            this.f.setVisibility(0);
        }
    }

    /* access modifiers changed from: private */
    public void d() {
        this.c.setEnabled(!TextUtils.isEmpty(this.d.getText().toString()));
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        super.onResume();
        IntentFilter intentFilter = new IntentFilter("editor_changed_action");
        intentFilter.addAction(DeviceTagEditorFragment.f19812a);
        LocalBroadcastManager.getInstance(this).registerReceiver(this.q, intentFilter);
        STAT.c.m(0);
    }

    /* access modifiers changed from: protected */
    public void onPause() {
        super.onPause();
        LocalBroadcastManager.getInstance(this).unregisterReceiver(this.q);
        STAT.c.m(this.mEnterTime);
    }

    /* access modifiers changed from: protected */
    public void onDestroy() {
        super.onDestroy();
        if (this.p != null && this.p.isShowing()) {
            this.p.dismiss();
        }
    }

    public void onClick(View view) {
        if (view == this.b) {
            onBackPressed();
        } else if (view == this.c) {
            try {
                STAT.d.a(!TextUtils.equals(this.d.getText().toString().trim(), this.f18081a.e()), !this.r.equals(this.h.keySet()));
            } catch (Exception e2) {
                e2.printStackTrace();
            }
            e();
        } else if (view == this.e) {
            Intent intent = new Intent(getContext(), HomeRoomEditorIconActivity.class);
            intent.putExtra("room_name", this.d.getText().toString());
            intent.putExtra("room_icon", this.l);
            startActivityForResult(intent, 0);
            STAT.d.T();
        } else if (view == this.d) {
            if (this.p != null && this.p.isShowing()) {
                this.p.dismiss();
            }
            this.p = NameEditDialogHelper.a(this, this.d.getText().toString(), getString(R.string.room_name_update), getString(R.string.input_tag_name_hint), this.listener);
            STAT.d.Y(this.d.getText().toString());
        }
    }

    /* access modifiers changed from: protected */
    public void onActivityResult(int i2, int i3, Intent intent) {
        super.onActivityResult(i2, i3, intent);
        if (i3 == -1) {
            if (!TextUtils.isEmpty(this.d.getText().toString())) {
                this.c.setEnabled(true);
            }
            this.l = intent.getStringExtra("room_icon");
            this.e.setImageURI(Uri.parse("file://" + HomeManager.a().f(this.l)));
        }
    }

    /* access modifiers changed from: private */
    public void e() {
        List<Room> e2;
        if (!TextUtils.isEmpty(this.f18081a.d()) || (e2 = HomeManager.a().e()) == null || e2.size() < 50) {
            String trim = this.d.getText().toString().trim();
            if (!HomeManager.A(trim)) {
                ToastUtil.a((int) R.string.room_name_too_long);
            } else if (StringUtil.t(trim)) {
                if (this.p != null && this.p.isShowing()) {
                    this.p.dismiss();
                }
                this.p = new MLAlertDialog.Builder(this).a((CharSequence) String.format(getString(R.string.tag_save_data_title), new Object[]{trim})).b((int) R.string.tag_save_data_description).c((int) R.string.tag_roger, (DialogInterface.OnClickListener) null).d();
            } else {
                final ArrayList arrayList = new ArrayList(this.h.keySet());
                if (HomeManager.a().a(this.f18081a, trim)) {
                    this.c.setEnabled(false);
                    NameEditDialogHelper.a(this, getString(R.string.name_duplicate));
                    return;
                }
                if (TextUtils.isEmpty(this.f18081a.d()) || this.n) {
                    HomeManager.a().a(TextUtils.isEmpty(this.o) ? this.f18081a.f() : this.o, trim, arrayList, this.l, new HomeManager.IHomeOperationCallback() {
                        public void a() {
                            if (HomeRoomEditorActivityV2.this.n) {
                                HomeRoomEditorActivityV2.this.f18081a.h().removeAll(arrayList);
                                if (HomeRoomEditorActivityV2.this.f18081a.h().size() == 0) {
                                    HomeManager.a().a(HomeRoomEditorActivityV2.this.f18081a, (HomeManager.IHomeOperationCallback) null);
                                }
                            }
                            HomeRoomEditorActivityV2.this.finish();
                        }

                        public void a(int i, Error error) {
                            if (error == null || error.a() != -35) {
                                ToastUtil.a((int) R.string.add_failed);
                            } else {
                                ToastUtil.a((int) R.string.name_repeat);
                            }
                        }
                    });
                    a(this.h.keySet());
                } else {
                    final String e3 = this.f18081a.e();
                    final String a2 = this.f18081a.a();
                    this.f18081a.e(trim);
                    this.f18081a.a(this.l);
                    ArrayList arrayList2 = new ArrayList();
                    for (String next : this.r) {
                        if (!arrayList.contains(next)) {
                            arrayList2.add(next);
                        }
                    }
                    HomeManager.a().a(this.f18081a, (List<String>) arrayList, (List<String>) arrayList2, (HomeManager.IHomeOperationCallback) new HomeManager.IHomeOperationCallback() {
                        public void a() {
                            HomeRoomEditorActivityV2.this.finish();
                        }

                        public void a(int i, Error error) {
                            if (error == null || error.a() != -35) {
                                ToastUtil.a((int) R.string.add_failed);
                            } else {
                                ToastUtil.a((int) R.string.name_repeat);
                            }
                            HomeRoomEditorActivityV2.this.f18081a.e(e3);
                            HomeRoomEditorActivityV2.this.f18081a.a(a2);
                        }
                    });
                    a(this.h.keySet());
                    StatHelper.h(trim);
                    if (getIntent().getBooleanExtra("result", false)) {
                        Intent intent = new Intent();
                        intent.putExtra("old_name", this.f18081a.e());
                        intent.putExtra("new_name", trim);
                        setResult(-1, intent);
                    }
                }
                SmartHomeDeviceHelper.a().b().j();
                SmartHomeDeviceHelper.a().b().k();
            }
        } else {
            ToastUtil.a((int) R.string.exceed_room_max_count);
        }
    }

    private void a(Set<String> set) {
        if (set != null && !set.isEmpty()) {
            HashSet hashSet = new HashSet();
            for (String l2 : set) {
                Device l3 = SmartHomeDeviceManager.a().l(l2);
                if (l3 != null && !l3.isShowMainList()) {
                    hashSet.add(l3.did);
                }
            }
            if (hashSet.size() > 0) {
                SmartHomeDeviceManager.a().a(true, (Set<String>) hashSet, (Context) this, (AsyncResponseCallback<Void>) null);
            }
        }
    }

    private void f() {
        this.g = (ViewPagerSystem) findViewById(R.id.view_pager);
        this.k = new MyPagerAdapter(getSupportFragmentManager());
        this.g.setAdapter(this.k);
        this.g.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            public void onPageScrollStateChanged(int i) {
            }

            public void onPageScrolled(int i, float f, int i2) {
            }

            public void onPageSelected(int i) {
                for (Fragment fragment : HomeRoomEditorActivityV2.this.i) {
                    if (fragment instanceof AllHomeDeviceListFragment) {
                        ((AllHomeDeviceListFragment) fragment).a();
                    } else if (fragment instanceof RouterDeviceListByHomeFragment) {
                        ((RouterDeviceListByHomeFragment) fragment).a();
                    }
                }
            }
        });
        this.g.setPagingEnabled(false);
        c();
    }

    public void onTextChanged(CharSequence charSequence, int i2, int i3, int i4) {
        this.c.setEnabled(!TextUtils.isEmpty(charSequence));
    }

    public Map<String, Boolean> getDeviceSelectedStatus() {
        return this.h;
    }

    public String getEditText() {
        CharSequence text = this.d.getText();
        if (text == null) {
            return null;
        }
        return text.toString();
    }

    public void updateSelectedCountTip(String str) {
        this.m.setText(str);
    }

    public Room getEditRoom() {
        return this.f18081a;
    }

    public class MyPagerAdapter extends FragmentPagerAdapter {
        public MyPagerAdapter(FragmentManager fragmentManager) {
            super(fragmentManager);
        }

        public int getCount() {
            return HomeRoomEditorActivityV2.this.i.size();
        }

        public CharSequence getPageTitle(int i) {
            return HomeRoomEditorActivityV2.this.getResources().getString(HomeRoomEditorActivityV2.this.j[i]);
        }

        public Fragment getItem(int i) {
            return (Fragment) HomeRoomEditorActivityV2.this.i.get(i);
        }
    }

    public void onBackPressed() {
        if (this.c.isEnabled()) {
            if (this.p != null && this.p.isShowing()) {
                this.p.dismiss();
            }
            this.p = new MLAlertDialog.Builder(this).a((int) R.string.tag_dump_data_prompt).b((int) R.string.tag_dump_data_prompt_description).a((int) R.string.save, (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialogInterface, int i) {
                    HomeRoomEditorActivityV2.this.e();
                }
            }).b((int) R.string.cancel, (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialogInterface, int i) {
                    HomeRoomEditorActivityV2.this.finish();
                }
            }).d();
            return;
        }
        try {
            super.onBackPressed();
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }
}
