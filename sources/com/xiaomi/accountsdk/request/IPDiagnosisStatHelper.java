package com.xiaomi.accountsdk.request;

import android.text.TextUtils;
import com.xiaomi.accountsdk.account.PassportCAConstants;
import com.xiaomi.accountsdk.account.URLConst;
import com.xiaomi.accountsdk.account.data.BaseConstants;
import java.util.ArrayList;

class IPDiagnosisStatHelper extends RequestWithIPStatHelper {
    private ArrayList<String> usedIps = new ArrayList<>();
    private ArrayList<String> usedNets = new ArrayList<>();

    public void onBackupIpSucceeded(int i) {
    }

    public void onCachedIpSucceed() {
    }

    IPDiagnosisStatHelper() {
    }

    public void onIpRequestStarted() {
        statCountEvent(BaseConstants.STAT_CATEGORY_IP, BaseConstants.STAT_KEY_IP_REQUEST_STARTED);
    }

    public void onIpRequest200Succeed() {
        statCountEvent(BaseConstants.STAT_CATEGORY_IP, BaseConstants.STAT_KEY_IP_REQUEST_SUCCEED);
        statAllIps(true);
    }

    public void onIpRequestIOSucceed() {
        statCountEvent(BaseConstants.STAT_CATEGORY_IP, BaseConstants.STAT_KEY_IP_REQUEST_IO_SUCCEEDED);
        statAllIps(true);
    }

    public void onIpRequestFailed() {
        statCountEvent(BaseConstants.STAT_CATEGORY_IP, BaseConstants.STAT_KEY_IP_REQUEST_FAILED);
        statAllIps(false);
    }

    public void onCachedIpStarted(String str) {
        this.usedIps.add(str);
        this.usedNets.add(getNetworkName());
    }

    public void onCachedIpFailed() {
        statCountEvent(BaseConstants.STAT_CATEGORY_IP, BaseConstants.STAT_KEY_CACHED_IP_FAILED);
    }

    public void onDnsip0Started(String str) {
        this.usedIps.add(str);
        this.usedNets.add(getNetworkName());
    }

    public void onDnsIp0Succeed() {
        statDummyUrl(String.format(URLConst.HyperTextTransferProtocol + "dummyurl/cachedIpDiagonose?_ver=%s&cacheipnet=%s&cachedip=%s&dnsipnet=%s&dnsip=%s&finalnet=%s", new Object[]{PassportCAConstants.IMPL_VERSION, this.usedNets.get(0), this.usedIps.get(0), this.usedNets.get(1), this.usedIps.get(1), getNetworkName()}));
    }

    public void onDnsIp0Failed() {
        statCountEvent(BaseConstants.STAT_CATEGORY_IP, BaseConstants.STAT_KEY_DNS_IP_0_FAILED);
    }

    public void onBackupIpStarted(int i, String str) {
        this.usedIps.add(str);
        this.usedNets.add(getNetworkName());
    }

    public void onBackupIpFailed(int i) {
        statCountEvent(BaseConstants.STAT_CATEGORY_IP, BaseConstants.STAT_KEY_BACKUP_IP_FAILED + i);
    }

    /* access modifiers changed from: package-private */
    public void statAllIps(boolean z) {
        statDummyUrl(String.format(URLConst.HyperTextTransferProtocol + "dummyurl/IpDiagonose?_ver=%s&_ips=%s&_nets=%s&_ipResult=%s", new Object[]{PassportCAConstants.IMPL_VERSION, TextUtils.join(",", this.usedIps), TextUtils.join(",", this.usedNets), Boolean.valueOf(z)}));
    }
}
