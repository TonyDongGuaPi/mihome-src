package com.mibi.sdk;

import android.app.Activity;
import android.os.Bundle;

public interface IMibi {
    void a();

    void a(Activity activity, long j, IMibiAccountProvider iMibiAccountProvider, Bundle bundle);

    void a(Activity activity, IMibiAccountProvider iMibiAccountProvider);

    void a(Activity activity, String str, IMibiAccountProvider iMibiAccountProvider, Bundle bundle);

    void b(Activity activity, IMibiAccountProvider iMibiAccountProvider);

    void b(Activity activity, String str, IMibiAccountProvider iMibiAccountProvider, Bundle bundle);
}
