package com.xiaomi.smarthome.homeroom.transferactivities;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.ProgressBar;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.SmartHomeMainActivity;
import com.xiaomi.smarthome.application.SHApplication;
import com.xiaomi.smarthome.framework.page.BaseActivity;
import com.xiaomi.smarthome.homeroom.HomeManager;
import com.xiaomi.smarthome.miio.TitleBarUtil;
import java.util.ArrayList;
import java.util.List;

public class HomeRoomAddNewUserDeviceActivity extends BaseActivity {
    public static final String EXTRA_ROOM_LIST = "extra_device_model";
    public static final int RESULT_ADD_NEW_USER_DEVICE_ACTIVITY = 1;

    /* renamed from: a  reason: collision with root package name */
    private HomeRoomAddNewUserDeviceAdapter f18330a;
    private ArrayList<String> b;
    /* access modifiers changed from: private */
    public ProgressBar c;
    HomeManager.ITransferListener mITransferListener = new HomeManager.ITransferListener() {
        public void a() {
            HomeRoomAddNewUserDeviceActivity.this.c.setVisibility(8);
            Intent intent = new Intent();
            intent.setClass(HomeRoomAddNewUserDeviceActivity.this, SmartHomeMainActivity.class);
            intent.addFlags(335544320);
            HomeRoomAddNewUserDeviceActivity.this.startActivity(intent);
            HomeRoomAddNewUserDeviceActivity.this.overridePendingTransition(0, 0);
            HomeRoomAddNewUserDeviceActivity.this.finish();
        }

        public void b() {
            HomeRoomAddNewUserDeviceActivity.this.c.setVisibility(8);
            Intent intent = new Intent();
            intent.setClass(HomeRoomAddNewUserDeviceActivity.this, SmartHomeMainActivity.class);
            intent.putExtra(DeviceTagTransferActivity.START_TRANSFER_ACTIVITY, false);
            intent.addFlags(335544320);
            HomeRoomAddNewUserDeviceActivity.this.startActivity(intent);
            HomeRoomAddNewUserDeviceActivity.this.overridePendingTransition(0, 0);
            HomeRoomAddNewUserDeviceActivity.this.finish();
        }
    };

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_device_tag_add_new_user_device);
        this.b = getIntent().getStringArrayListExtra("extra_device_model");
        if (this.b == null) {
            this.b = new ArrayList<>();
        }
        TitleBarUtil.b((Activity) this);
        findViewById(R.id.confirm).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                HomeRoomAddNewUserDeviceActivity.this.a();
            }
        });
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        this.c = (ProgressBar) findViewById(R.id.progress_bar);
        recyclerView.setLayoutManager(new GridLayoutManager((Context) this, 4, 1, false));
        this.f18330a = new HomeRoomAddNewUserDeviceAdapter(this);
        this.f18330a.a((List<String>) this.b);
        recyclerView.setAdapter(this.f18330a);
        HomeManager.a().a(this.mITransferListener);
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        super.onResume();
        this.f18330a.a();
    }

    /* access modifiers changed from: protected */
    public void onDestroy() {
        super.onDestroy();
        HomeManager.a().b(this.mITransferListener);
        this.mITransferListener = null;
    }

    /* access modifiers changed from: protected */
    public void onActivityResult(int i, int i2, Intent intent) {
        int indexOf;
        super.onActivityResult(i, i2, intent);
        if (i == 1 && intent != null) {
            String stringExtra = intent.getStringExtra("old_name");
            String stringExtra2 = intent.getStringExtra("new_name");
            if (!TextUtils.equals(stringExtra, stringExtra2) && (indexOf = this.b.indexOf(stringExtra)) >= 0 && indexOf < this.b.size()) {
                this.b.set(indexOf, stringExtra2);
                this.f18330a.a((List<String>) this.b);
            }
        }
    }

    /* access modifiers changed from: private */
    public void a() {
        this.c.setVisibility(0);
        HomeManager.a().a(this.b);
        SHApplication.getAppContext().getSharedPreferences(HomeManager.f1548a, 0).edit().putBoolean(HomeManager.G, false).commit();
    }
}
