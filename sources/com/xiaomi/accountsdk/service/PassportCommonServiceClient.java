package com.xiaomi.accountsdk.service;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.content.pm.ServiceInfo;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import com.xiaomi.accountsdk.account.AccountIntent;
import com.xiaomi.accountsdk.futureservice.ClientFuture;
import com.xiaomi.accountsdk.futureservice.ServerServiceConnector;
import com.xiaomi.accountsdk.futureservice.SimpleClientFuture;
import com.xiaomi.accountsdk.service.DeviceInfoResult;
import com.xiaomi.accountsdk.utils.AccountLog;
import com.xiaomi.passport.IPassportCommonService;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class PassportCommonServiceClient {
    private static final String FEATURE_GET_DEVICE_INFO_VERSION = "feature_get_device_info_version";
    private static final String TAG = "PassportCommonServiceClient";

    private PassportCommonServiceClient() {
    }

    private static boolean isDeviceInfoSupported(Context context) {
        return checkServiceFeatureSupportedByVersion(context, FEATURE_GET_DEVICE_INFO_VERSION, 1);
    }

    public static DeviceInfoResult getDeviceInfoRpc(Context context, final String str, final int i, int i2) {
        if (!isDeviceInfoSupported(context)) {
            return new DeviceInfoResult.Builder((Bundle) null).errorCode(DeviceInfoResult.ErrorCode.ERROR_NOT_SUPPORTED).errorMessage("GetDeviceInfo feature is not yet supported by this version of XiaomiAccount, please update a newer version.").build();
        }
        SimpleClientFuture simpleClientFuture = new SimpleClientFuture();
        new ConnectorBase<DeviceInfoResult>(context, simpleClientFuture) {
            /* access modifiers changed from: protected */
            public DeviceInfoResult callServiceWork() throws RemoteException {
                return ((IPassportCommonService) getIService()).getDeviceInfo(str, i);
            }
        }.bind();
        try {
            return (DeviceInfoResult) simpleClientFuture.get((long) i2, TimeUnit.MILLISECONDS);
        } catch (InterruptedException | ExecutionException | TimeoutException e) {
            AccountLog.e(TAG, "getDeviceInfoRpc", e);
            return new DeviceInfoResult.Builder((Bundle) null).errorCode(DeviceInfoResult.ErrorCode.ERROR_EXECUTION_EXCEPTION).errorMessage(e.getMessage()).build();
        }
    }

    private static boolean checkServiceFeatureSupportedByVersion(Context context, String str, int i) {
        ResolveInfo resolveInfo;
        Intent intent = new Intent(AccountIntent.ACTION_COMMON_SERVICE);
        intent.setPackage(AccountIntent.PACKAGE_XIAOMI_ACCOUNT);
        List<ResolveInfo> queryIntentServices = context.getPackageManager().queryIntentServices(intent, 0);
        if (!(queryIntentServices == null || queryIntentServices.isEmpty() || (resolveInfo = queryIntentServices.get(0)) == null || resolveInfo.serviceInfo == null)) {
            try {
                ServiceInfo serviceInfo = context.getPackageManager().getServiceInfo(new ComponentName(resolveInfo.serviceInfo.packageName, resolveInfo.serviceInfo.name), 128);
                if (!(serviceInfo == null || serviceInfo.metaData == null)) {
                    Object obj = serviceInfo.metaData.get(str);
                    if (!(obj instanceof Integer) || ((Integer) obj).intValue() < i) {
                        return false;
                    }
                    return true;
                }
            } catch (PackageManager.NameNotFoundException e) {
                AccountLog.w(TAG, "component not found", e);
            }
        }
        return false;
    }

    private static abstract class ConnectorBase<T> extends ServerServiceConnector<IPassportCommonService, T, T> {
        protected ConnectorBase(Context context, ClientFuture<T, T> clientFuture) {
            super(context, AccountIntent.ACTION_COMMON_SERVICE, AccountIntent.PACKAGE_XIAOMI_ACCOUNT, clientFuture);
        }

        /* access modifiers changed from: protected */
        public final IPassportCommonService binderToServiceType(IBinder iBinder) {
            return IPassportCommonService.Stub.asInterface(iBinder);
        }
    }
}
