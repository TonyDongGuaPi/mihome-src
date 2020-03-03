package com.xiaomi.smarthome.scene;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.device.Device;
import com.xiaomi.smarthome.framework.page.BaseActivity;
import com.xiaomi.smarthome.scene.SceneItemChooseFragment;
import java.util.ArrayList;
import java.util.List;

public abstract class CommonChooseActivity extends BaseActivity {
    public static final String EXTRA_DID = "extra_did";
    protected String defaultDeviceId;
    protected List<Device> mDevices = new ArrayList();
    protected SceneItemChooseFragment mFragment;
    protected TextView titleView;

    /* access modifiers changed from: protected */
    public abstract List<SceneItemChooseFragment.ItemData> getItemList();

    /* access modifiers changed from: protected */
    public abstract int getLayoutFile();

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.defaultDeviceId = getIntent().getStringExtra("extra_did");
        setContentView(getLayoutFile());
        a();
        initFragment();
    }

    private void a() {
        this.titleView = (TextView) findViewById(R.id.module_a_3_return_title);
        View findViewById = findViewById(R.id.module_a_3_return_btn);
        if (findViewById != null) {
            findViewById.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    CommonChooseActivity.this.b();
                    CommonChooseActivity.this.finish();
                }
            });
        }
    }

    public void initFragment() {
        this.mFragment = (SceneItemChooseFragment) getSupportFragmentManager().findFragmentById(R.id.device_choose_fragment);
        if (this.mFragment != null) {
            SceneItemChooseFragment.ItemData itemData = null;
            List<SceneItemChooseFragment.ItemData> itemList = getItemList();
            for (SceneItemChooseFragment.ItemData next : itemList) {
                if (this.defaultDeviceId != null && !this.defaultDeviceId.isEmpty() && this.defaultDeviceId.equalsIgnoreCase(next.f21310a)) {
                    itemData = next;
                }
            }
            if (itemData == null && !itemList.isEmpty()) {
                itemData = itemList.get(0);
            }
            this.mFragment.a(itemList, itemData);
        }
    }

    public void onBackPressed() {
        b();
        finish();
    }

    /* access modifiers changed from: private */
    public void b() {
        if (this.mFragment == null || this.mFragment.a() == null) {
            setResult(0);
            return;
        }
        Intent intent = new Intent();
        ArrayList arrayList = new ArrayList();
        arrayList.add(this.mFragment.a());
        intent.putParcelableArrayListExtra(GoLeaveHomeGroupConditionActivity.ITEM_CHOOSE_RESULT, arrayList);
        setResult(-1, intent);
    }
}
