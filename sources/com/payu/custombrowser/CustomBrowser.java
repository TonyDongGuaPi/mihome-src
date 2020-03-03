package com.payu.custombrowser;

import android.app.Activity;
import android.content.Intent;
import android.support.annotation.NonNull;
import com.alipay.sdk.sys.a;
import com.payu.custombrowser.bean.CustomBrowserConfig;
import com.payu.custombrowser.bean.CustomBrowserData;
import com.payu.custombrowser.util.CBConstant;

public class CustomBrowser {
    public void addCustomBrowser(Activity activity, @NonNull CustomBrowserConfig customBrowserConfig, @NonNull PayUCustomBrowserCallback payUCustomBrowserCallback) {
        CustomBrowserData.SINGLETON.setPayuCustomBrowserCallback(payUCustomBrowserCallback);
        if (customBrowserConfig.getPayuPostData() != null && customBrowserConfig.getEnableSurePay() > 0 && (customBrowserConfig.getPostURL().contentEquals("https://secure.payu.in/_payment") || customBrowserConfig.getPostURL().contentEquals("https://mobiletest.payu.in/_payment") || customBrowserConfig.getPostURL().contentEquals(CBConstant.MOBILE_TEST_PAYMENT_URL_SEAMLESS) || customBrowserConfig.getPostURL().contentEquals(CBConstant.PRODUCTION_PAYMENT_URL_SEAMLESS))) {
            if (customBrowserConfig.getPayuPostData().trim().endsWith(a.b)) {
                customBrowserConfig.setPayuPostData(customBrowserConfig.getPayuPostData().substring(0, customBrowserConfig.getPayuPostData().length() - 1));
            }
            customBrowserConfig.setPayuPostData(customBrowserConfig.getPayuPostData() + "&snooze=" + customBrowserConfig.getEnableSurePay());
        }
        Intent intent = new Intent(activity, CBActivity.class);
        intent.putExtra(CBConstant.CB_CONFIG, customBrowserConfig);
        activity.startActivity(intent);
    }
}
