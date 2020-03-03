package com.xiaomi.phonenum;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ResolveInfo;
import com.xiaomi.phonenum.phone.PhoneInfoManager;
import com.xiaomi.phonenum.phone.PhoneUtil;
import com.xiaomi.phonenum.utils.RomUtil;
import java.util.List;

public class PhoneNumKeeperFactory {
    public PhoneNumKeeper a(Context context, String str) {
        Context applicationContext = context.getApplicationContext();
        PhoneUtil a2 = PhoneInfoManager.a(context);
        PhoneNumKeeper phoneNumKeeper = new PhoneNumKeeper(a2);
        PhoneNumStore phoneNumStore = new PhoneNumStore(applicationContext, str, a2);
        if (!a(applicationContext) || !RomUtil.a(applicationContext, "com.xiaomi.permission.CLOUD_MANAGER")) {
            phoneNumKeeper.a((PhoneNumGetter) new PhoneNumStoreAdapter(phoneNumStore));
        } else {
            phoneNumKeeper.a((PhoneNumGetter) new MiuiPhoneNumServiceAdapter(new MiuiPhoneNumServiceProxy(applicationContext), phoneNumStore));
        }
        return phoneNumKeeper;
    }

    private boolean a(Context context) {
        Intent intent = new Intent(Constant.c);
        intent.setPackage(Constant.d);
        List<ResolveInfo> queryIntentServices = context.getPackageManager().queryIntentServices(intent, 0);
        if (queryIntentServices == null || queryIntentServices.size() <= 0) {
            return false;
        }
        return true;
    }
}
