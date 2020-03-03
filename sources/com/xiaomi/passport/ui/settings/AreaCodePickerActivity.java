package com.xiaomi.passport.ui.settings;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import com.xiaomi.accountsdk.utils.ParcelableAttackGuardian;
import com.xiaomi.passport.ui.internal.util.FriendlyFragmentUtils;

public class AreaCodePickerActivity extends AppCompatActivity {
    public void onCreate(Bundle bundle) {
        setRequestedOrientation(1);
        super.onCreate(bundle);
        if (!new ParcelableAttackGuardian().safeCheck(this)) {
            finish();
            return;
        }
        AreaCodePickerFragment areaCodePickerFragment = new AreaCodePickerFragment();
        areaCodePickerFragment.setArguments(getIntent().getExtras());
        FriendlyFragmentUtils.addFragment(getFragmentManager(), 16908290, areaCodePickerFragment);
    }
}
