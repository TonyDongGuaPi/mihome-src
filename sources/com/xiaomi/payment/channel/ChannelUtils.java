package com.xiaomi.payment.channel;

import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Toast;
import com.mibi.common.base.StepFragment;
import com.mibi.common.data.Utils;
import com.mibi.common.ui.PadDialogActivity;
import com.mibi.common.ui.PhoneCommonActivity;
import com.xiaomi.payment.data.MibiCodeConstants;
import com.xiaomi.payment.data.MibiConstants;
import com.xiaomi.payment.ui.fragment.query.RechargeQueryFragment;

public class ChannelUtils {
    static void a(StepFragment stepFragment, Bundle bundle, boolean z) {
        if (z) {
            b(stepFragment, bundle);
        } else {
            a(stepFragment, bundle);
        }
    }

    static void a(StepFragment stepFragment, Bundle bundle) {
        stepFragment.a(RechargeQueryFragment.class, bundle, 0, (String) null, Utils.b() ? PadDialogActivity.class : PhoneCommonActivity.class);
    }

    static void b(StepFragment stepFragment, Bundle bundle) {
        stepFragment.b(1103, bundle);
        stepFragment.a(MibiCodeConstants.b, false);
    }

    static void a(StepFragment stepFragment, int i, String str) {
        if (!TextUtils.isEmpty(str)) {
            Toast.makeText(stepFragment.getActivity(), str, 0).show();
        }
        Bundle bundle = new Bundle();
        bundle.putInt(MibiConstants.da, i);
        bundle.putString(MibiConstants.db, str);
        stepFragment.b(1005, bundle);
    }
}
