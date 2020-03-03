package com.xiaomi.smarthome.mibrain.anothernamesetting;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.multikey.PowerMultikeyActivity;
import java.util.ArrayList;
import java.util.List;

public class AnotherNameMultiKeyActivity extends PowerMultikeyActivity {

    /* renamed from: a  reason: collision with root package name */
    private LinearLayout f10651a;

    public static void startActivity(Activity activity, String str, String str2) {
        Intent intent = new Intent(activity, AnotherNameMultiKeyActivity.class);
        intent.putExtra("from", 1);
        intent.putExtra("device_id", str);
        intent.putExtra("device_mac", str2);
        activity.startActivity(intent);
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        if (this.mAdapter != null) {
            this.mAdapter.a((PowerMultikeyActivity.ItemClickListener) new PowerMultikeyActivity.ItemClickListener() {
                public final void onItemClick(List list, int i) {
                    AnotherNameMultiKeyActivity.this.a(list, i);
                }
            });
        }
        this.mTitleTv.setText(R.string.voice_another_name_setting);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void a(List list, int i) {
        ArrayList arrayList = (ArrayList) list;
        requestUpdate(arrayList);
        Intent intent = new Intent(this, AnotherNameEditActivity.class);
        intent.putExtra(AnotherNameEditActivity.KEY_ALIAS_DID, this.mDevice.did);
        intent.putExtra(AnotherNameEditActivity.KEY_MULTI_BTN, true);
        intent.putExtra(AnotherNameEditActivity.KEY_MULTI_BTN_POSITION, i);
        Bundle bundle = new Bundle();
        bundle.putParcelableArrayList(AnotherNameEditActivity.KEY_MULTI_BTN_BEAN_LIST, arrayList);
        intent.putExtras(bundle);
        startActivity(intent);
    }

    public void onBackPressed() {
        if (this.f10651a == null || this.f10651a.getVisibility() != 0) {
            super.onBackPressed();
        } else {
            this.f10651a.setVisibility(8);
        }
    }

    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.module_a_3_return_btn) {
            onBackPressed();
        } else if (id == R.id.module_a_3_right_iv_setting_btn) {
            a();
        }
    }

    private void a() {
        if (this.f10651a == null) {
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-1, -1);
            this.f10651a = (LinearLayout) View.inflate(this, R.layout.another_name_guide, (ViewGroup) null);
            this.f10651a.setLayoutParams(layoutParams);
            ((ViewGroup) findViewById(16908290)).addView(this.f10651a);
            this.f10651a.findViewById(R.id.guide_cancel).setOnClickListener(new View.OnClickListener() {
                public final void onClick(View view) {
                    AnotherNameMultiKeyActivity.this.a(view);
                }
            });
        }
        this.f10651a.setVisibility(0);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void a(View view) {
        this.f10651a.setVisibility(8);
    }
}
