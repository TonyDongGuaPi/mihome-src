package com.xiaomi.accountsdk.request;

import android.os.SystemClock;
import com.xiaomi.accountsdk.request.RequestWithIPStatHelper;
import com.xiaomi.accountsdk.request.SimpleRequest;
import com.xiaomi.accountsdk.utils.AccountLog;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashSet;
import java.util.List;

public class RequestWithIP extends PassportRequest {
    private static final String TAG = "RequestWithIP";
    private final IPStrategy ipStrategy;
    private final PassportSimpleRequest originalRequest;
    private final RequestWithIPStatHelper statHelper;

    static class IpRequestResult {
        SimpleRequest.StringContent stringContent = null;
        long timeSpent = 0;

        IpRequestResult() {
        }
    }

    public RequestWithIP(PassportSimpleRequest passportSimpleRequest, IPStrategy iPStrategy, RequestWithIPStatHelper requestWithIPStatHelper) {
        if (passportSimpleRequest == null || iPStrategy == null) {
            throw new IllegalArgumentException();
        }
        this.originalRequest = passportSimpleRequest;
        this.ipStrategy = iPStrategy;
        this.statHelper = new RequestWithIPStatHelper.Composition(new IPDiagnosisStatHelper(), requestWithIPStatHelper);
    }

    public SimpleRequest.StringContent execute() throws IOException, PassportRequestException {
        SimpleRequest.StringContent executeByIp;
        this.statHelper.start();
        try {
            if (!this.originalRequest.isHTTPS() && (executeByIp = executeByIp()) != null) {
                return executeByIp;
            }
            SimpleRequest.StringContent executeByHostName = executeByHostName();
            this.statHelper.finish();
            return executeByHostName;
        } finally {
            this.statHelper.finish();
        }
    }

    private SimpleRequest.StringContent executeByIp() throws MalformedURLException, PassportRequestException {
        boolean z;
        List<String> backupIpList;
        String host = new URL(this.originalRequest.arguments.url).getHost();
        IpRequestResult ipRequestResult = new IpRequestResult();
        this.statHelper.onIpRequestStarted();
        HashSet hashSet = new HashSet();
        try {
            String cachedIp = this.ipStrategy.getCachedIp(host);
            this.statHelper.onCachedIpStarted(cachedIp);
            int i = 0;
            if (executeWithIp(this.originalRequest, host, cachedIp, ipRequestResult)) {
                this.ipStrategy.updateCachedIpIfNeeded(host, cachedIp, ipRequestResult.timeSpent);
                this.statHelper.onCachedIpSucceed();
                z = true;
            } else {
                this.statHelper.onCachedIpFailed();
                z = false;
            }
            hashSet.add(cachedIp);
            if (!z) {
                this.statHelper.onDnsResolvingStarted();
                String dnsIp0 = this.ipStrategy.getDnsIp0(host, cachedIp);
                this.statHelper.onDnsResolvingFinished(dnsIp0);
                this.statHelper.onDnsip0Started(dnsIp0);
                if (hashSet.contains(dnsIp0) || !executeWithIp(this.originalRequest, host, dnsIp0, ipRequestResult)) {
                    this.statHelper.onDnsIp0Failed();
                } else {
                    this.ipStrategy.updateCachedIpAndTimeStamp(host, dnsIp0);
                    this.statHelper.onDnsIp0Succeed();
                    z = true;
                }
                hashSet.add(dnsIp0);
            }
            if (!z && (backupIpList = this.ipStrategy.getBackupIpList(host)) != null) {
                while (true) {
                    if (i >= backupIpList.size()) {
                        break;
                    }
                    String str = backupIpList.get(i);
                    if (hashSet.contains(str)) {
                        this.statHelper.onBackupIpFailed(i);
                    } else {
                        hashSet.add(str);
                        PassportSimpleRequest copy = this.originalRequest.copy();
                        copy.arguments.setTimeoutMillis(10000);
                        this.statHelper.onBackupIpStarted(i, str);
                        if (executeWithIp(copy, host, str, ipRequestResult)) {
                            this.ipStrategy.updateCachedIpAndTimeStamp(host, str);
                            this.statHelper.onBackupIpSucceeded(i);
                            z = true;
                            break;
                        }
                        this.statHelper.onBackupIpFailed(i);
                    }
                    i++;
                }
            }
            if (z) {
                this.statHelper.onIpRequest200Succeed();
            } else {
                this.statHelper.onIpRequestFailed();
            }
            if (!z) {
                return null;
            }
            this.ipStrategy.updateBackupIpListIfNeeded(host);
            return ipRequestResult.stringContent;
        } catch (PassportRequestException e) {
            this.statHelper.onIpRequestIOSucceed();
            this.ipStrategy.updateBackupIpListIfNeeded(host);
            throw e;
        }
    }

    private SimpleRequest.StringContent executeByHostName() throws IOException, PassportRequestException {
        Throwable th;
        this.statHelper.onHostNameRequestStarted();
        boolean z = true;
        try {
            SimpleRequest.StringContent execute = this.originalRequest.execute();
            this.statHelper.onHostNameRequestFinished(true);
            return execute;
        } catch (IOException e) {
            throw e;
        } catch (Throwable th2) {
            th = th2;
            z = false;
        }
        this.statHelper.onHostNameRequestFinished(z);
        throw th;
    }

    static boolean executeWithIp(PassportSimpleRequest passportSimpleRequest, String str, String str2, IpRequestResult ipRequestResult) throws PassportRequestException {
        if (passportSimpleRequest == null || str == null || str2 == null || ipRequestResult == null) {
            return false;
        }
        long elapsedRealtime = SystemClock.elapsedRealtime();
        try {
            PassportSimpleRequest copy = passportSimpleRequest.copy();
            PassportRequestArguments passportRequestArguments = copy.arguments;
            passportRequestArguments.headers.put("host", str);
            passportRequestArguments.url = passportRequestArguments.url.replaceFirst(str, str2);
            ipRequestResult.stringContent = copy.execute();
            boolean z = ipRequestResult.stringContent != null;
            String valueOf = String.valueOf(z);
            ipRequestResult.timeSpent = SystemClock.elapsedRealtime() - elapsedRealtime;
            AccountLog.d(TAG, String.format("visit with ip, host=%s, ip=%s, time=%s, result=%s", new Object[]{str, str2, Long.valueOf(ipRequestResult.timeSpent), valueOf}));
            return z;
        } catch (IOException e) {
            String format = String.format("IOError,%s", new Object[]{e.getMessage()});
            ipRequestResult.timeSpent = SystemClock.elapsedRealtime() - elapsedRealtime;
            AccountLog.d(TAG, String.format("visit with ip, host=%s, ip=%s, time=%s, result=%s", new Object[]{str, str2, Long.valueOf(ipRequestResult.timeSpent), format}));
            return false;
        } catch (Throwable th) {
            ipRequestResult.timeSpent = SystemClock.elapsedRealtime() - elapsedRealtime;
            AccountLog.d(TAG, String.format("visit with ip, host=%s, ip=%s, time=%s, result=%s", new Object[]{str, str2, Long.valueOf(ipRequestResult.timeSpent), null}));
            throw th;
        }
    }
}
