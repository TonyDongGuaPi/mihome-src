package com.xiaomi.jr.feature.data;

import android.content.Context;
import com.google.android.gms.ads.identifier.AdvertisingIdClient;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import java.io.IOException;

class GoogleAds {
    GoogleAds() {
    }

    public static String a(Context context) {
        try {
            AdvertisingIdClient.Info advertisingIdInfo = AdvertisingIdClient.getAdvertisingIdInfo(context);
            if (advertisingIdInfo != null) {
                return advertisingIdInfo.getId();
            }
            return "";
        } catch (IOException e) {
            e.printStackTrace();
            return "";
        } catch (GooglePlayServicesNotAvailableException e2) {
            e2.printStackTrace();
            return "";
        } catch (GooglePlayServicesRepairableException e3) {
            e3.printStackTrace();
            return "";
        }
    }
}
