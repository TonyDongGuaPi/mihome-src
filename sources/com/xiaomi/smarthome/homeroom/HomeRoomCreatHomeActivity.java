package com.xiaomi.smarthome.homeroom;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.location.Address;
import android.location.Location;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.TextView;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.SmartHomeMainActivity;
import com.xiaomi.smarthome.application.SHApplication;
import com.xiaomi.smarthome.frame.AsyncCallback;
import com.xiaomi.smarthome.frame.Error;
import com.xiaomi.smarthome.frame.core.CoreApi;
import com.xiaomi.smarthome.framework.page.BaseActivity;
import com.xiaomi.smarthome.homeroom.HomeManager;
import com.xiaomi.smarthome.homeroom.model.Home;
import com.xiaomi.smarthome.library.common.dialog.MLAlertDialog;
import com.xiaomi.smarthome.library.common.util.ToastUtil;
import com.xiaomi.smarthome.miio.areainfo.LocationData;
import com.xiaomi.smarthome.miio.areainfo.MainlandAreaInfo;
import com.xiaomi.smarthome.miio.areainfo.ShowProvinceHelper;
import com.xiaomi.smarthome.newui.HomeEditorActivity;
import com.xiaomi.smarthome.newui.MultiHomeManagerActivity;
import com.xiaomi.smarthome.newui.NameEditDialogHelper;

public class HomeRoomCreatHomeActivity extends BaseActivity implements TextWatcher, View.OnClickListener {

    /* renamed from: a  reason: collision with root package name */
    private static final String f18049a = "HomeRoomCreatHomeActivity";
    private View b;
    private View c;
    private TextView d;
    /* access modifiers changed from: private */
    public TextView e;
    private View f;
    private Home.Builder g;
    private View h;
    NameEditDialogHelper.NameEditListener listener = new NameEditDialogHelper.NameEditListener() {
        public void a(String str) {
            if (!TextUtils.isEmpty(str)) {
                HomeRoomCreatHomeActivity.this.e.setText(str);
            }
        }

        public String b(String str) {
            if (HomeManager.a().a((Home) null, str)) {
                return HomeRoomCreatHomeActivity.this.getString(R.string.home_name_duplicate);
            }
            return null;
        }
    };

    public void afterTextChanged(Editable editable) {
    }

    public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_home_room_creat_home);
        a();
        b();
    }

    private void a() {
        this.g = new Home.Builder();
    }

    private void b() {
        ((TextView) findViewById(R.id.module_a_3_return_title)).setText(R.string.home_add_title);
        this.d = (TextView) findViewById(R.id.home_loc_tv);
        this.d.addTextChangedListener(this);
        this.e = (TextView) findViewById(R.id.name_tv);
        this.e.addTextChangedListener(this);
        this.h = findViewById(R.id.home_name_container);
        this.h.setOnClickListener(this);
        this.b = findViewById(R.id.module_a_3_return_btn);
        this.b.setOnClickListener(this);
        this.c = findViewById(R.id.confirm);
        this.c.setEnabled(false);
        this.c.setOnClickListener(this);
        this.f = findViewById(R.id.home_loc_container);
        this.f.setOnClickListener(this);
        if (CoreApi.a().D()) {
            this.f.setVisibility(8);
        }
    }

    private void c() {
        if (TextUtils.isEmpty(this.e.getText()) || (TextUtils.isEmpty(this.d.getText()) && this.f.getVisibility() == 0)) {
            ToastUtil.a((int) R.string.add_failed);
            return;
        }
        final Home a2 = this.g.g(this.e.getText().toString()).a(Long.parseLong(CoreApi.a().s())).a();
        HomeManager.a().a(a2, (HomeManager.IHomeOperationCallback) new HomeManager.IHomeOperationCallback() {
            public void a() {
                MLAlertDialog.Builder builder = new MLAlertDialog.Builder(HomeRoomCreatHomeActivity.this);
                builder.a((int) R.string.setting_now, (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialogInterface, int i) {
                        HomeManager.a().a(a2.j(), (AsyncCallback) null);
                        dialogInterface.dismiss();
                        HomeRoomCreatHomeActivity.this.finish();
                        MultiHomeManagerActivity.finishActivity();
                        HomeEditorActivity.finishActivity();
                        HomeRoomManageListActivity.startActivity(HomeRoomCreatHomeActivity.this.getContext(), a2.j());
                        HomeRoomCreatHomeActivity.this.mHandler.postDelayed(new Runnable() {
                            public void run() {
                                HomeRoomRecommendActivity.startActivity(HomeRoomCreatHomeActivity.this.getContext(), a2.j());
                            }
                        }, 100);
                    }
                });
                builder.b((int) R.string.setting_after, (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialogInterface, int i) {
                        if (a2 != null) {
                            HomeManager.a().a(a2.j(), (AsyncCallback) null);
                            dialogInterface.dismiss();
                            HomeRoomCreatHomeActivity.this.finish();
                            HomeRoomCreatHomeActivity.this.mHandler.postDelayed(new Runnable() {
                                public void run() {
                                    Intent intent = new Intent();
                                    intent.setClass(HomeRoomCreatHomeActivity.this, SmartHomeMainActivity.class);
                                    intent.addFlags(335544320);
                                    HomeRoomCreatHomeActivity.this.startActivity(intent);
                                    HomeRoomCreatHomeActivity.this.overridePendingTransition(0, 0);
                                }
                            }, 100);
                            SharedPreferences.Editor edit = SHApplication.getAppContext().getSharedPreferences(HomeManager.f1548a, 0).edit();
                            edit.putBoolean(HomeManager.H + a2.j(), true).apply();
                        }
                    }
                });
                builder.a(false);
                builder.a((CharSequence) String.format(SHApplication.getAppContext().getString(R.string.title_add_home_successful), new Object[]{a2.k()}));
                builder.b((int) R.string.message_add_home_successful);
                builder.d();
            }

            public void a(int i, Error error) {
                ToastUtil.a((int) R.string.add_failed);
            }
        });
    }

    public void onBackPressed() {
        if (!TextUtils.isEmpty(this.d.getText()) || !TextUtils.isEmpty(this.e.getText())) {
            new MLAlertDialog.Builder(this).b((int) R.string.home_dump_data_prompt_description).a((int) R.string.exit, (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialogInterface, int i) {
                    HomeRoomCreatHomeActivity.this.finish();
                }
            }).b((int) R.string.keep_on_edit, (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialogInterface, int i) {
                    dialogInterface.dismiss();
                }
            }).d();
        } else {
            super.onBackPressed();
        }
    }

    public void onClick(View view) {
        if (view == this.b) {
            onBackPressed();
        } else if (view == this.c) {
            c();
        } else if (view == this.f) {
            ShowProvinceHelper.a((Activity) this, (ShowProvinceHelper.IUpdateLocationCallback) new ShowProvinceHelper.IUpdateLocationCallback() {
                public void a(Context context, String str, String str2, String str3, String str4) {
                    HomeRoomCreatHomeActivity.this.a(str4, "0", "0");
                }

                public void a(Context context, Address address, Location location, boolean z, boolean z2) {
                    LocationData a2 = ShowProvinceHelper.a(context, address);
                    if (a2 != null) {
                        HomeRoomCreatHomeActivity.this.a(a2.f, String.valueOf(location.getLatitude()), String.valueOf(location.getLongitude()));
                    }
                }
            });
        } else if (view == this.h) {
            NameEditDialogHelper.a(this, this.e.getText().toString(), getString(R.string.home_name_update), getString(R.string.input_home_hint), this.listener);
        }
    }

    /* access modifiers changed from: protected */
    public void onDestroy() {
        super.onDestroy();
    }

    /* access modifiers changed from: private */
    public void a(String str, String str2, String str3) {
        String str4 = "error";
        if (!TextUtils.isEmpty(str) && !TextUtils.equals(str, "0")) {
            this.g.i(str2);
            this.g.j(str3);
            this.g.h(str);
            str4 = MainlandAreaInfo.a(SHApplication.getAppContext(), str);
        }
        this.d.setText(str4);
    }

    public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        boolean z = true;
        if (CoreApi.a().D()) {
            this.c.setEnabled(true ^ TextUtils.isEmpty(this.e.getText()));
            return;
        }
        View view = this.c;
        if (TextUtils.isEmpty(this.d.getText()) || TextUtils.isEmpty(this.e.getText())) {
            z = false;
        }
        view.setEnabled(z);
    }
}
