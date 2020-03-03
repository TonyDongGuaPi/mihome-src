package com.mipay.account;

import com.mibi.common.account.AccountRegistry;
import com.mibi.common.account.IAccountProvider;
import com.mibi.common.account.ILoginProvider;
import com.mipay.core.runtime.BundleActivator;
import com.mipay.core.runtime.BundleContext;

public class Activator extends BundleActivator {
    public boolean start(BundleContext bundleContext) {
        super.start(bundleContext);
        AccountRegistry.a((IAccountProvider) new AccountProviderImpl(getAppContext()));
        AccountRegistry.a((ILoginProvider) new LoginProviderImpl(getAppContext()));
        return true;
    }
}
