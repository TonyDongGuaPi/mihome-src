package com.xiaomi.passport.servicetoken;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.RemoteException;
import com.xiaomi.accountsdk.futureservice.ClientFuture;
import com.xiaomi.passport.servicetoken.IServiceTokenUIResponse;
import com.xiaomi.passport.servicetoken.ServiceTokenResult;

final class ServiceTokenUIErrorHandler {
    private ServiceTokenUIErrorHandler() {
    }

    public static ServiceTokenResult blockingHandleIntentError(Context context, ServiceTokenResult serviceTokenResult) {
        if (context instanceof Activity) {
            Activity activity = (Activity) context;
            return (activity.isFinishing() || serviceTokenResult == null || serviceTokenResult.errorCode != ServiceTokenResult.ErrorCode.ERROR_USER_INTERACTION_NEEDED || serviceTokenResult.intent == null || !new ServiceTokenUtilMiui().doesXiaomiAccountAppSupportServiceTokenUIResponse(activity)) ? serviceTokenResult : handleWithServiceTokenUIResponse(serviceTokenResult, activity);
        }
        return serviceTokenResult;
    }

    private static ServiceTokenResult handleWithServiceTokenUIResponse(final ServiceTokenResult serviceTokenResult, Activity activity) {
        final ServiceTokenFuture serviceTokenFuture = new ServiceTokenFuture((ClientFuture.ClientCallback<ServiceTokenResult>) null);
        serviceTokenResult.intent.putExtra("accountAuthenticatorResponse", new ServiceTokenUIResponse((IServiceTokenUIResponse) new IServiceTokenUIResponse.Stub() {
            public void onResult(Bundle bundle) throws RemoteException {
                serviceTokenFuture.setServerData(AMAuthTokenConverter.fromAMBundle(bundle, serviceTokenResult.sid));
            }

            public void onRequestContinued() throws RemoteException {
                serviceTokenFuture.setServerData(serviceTokenResult);
            }

            public void onError(int i, String str) throws RemoteException {
                if (i == 4) {
                    serviceTokenFuture.setServerData(new ServiceTokenResult.Builder(serviceTokenResult.sid).errorCode(ServiceTokenResult.ErrorCode.ERROR_CANCELLED).build());
                } else {
                    serviceTokenFuture.setServerData(serviceTokenResult);
                }
            }
        }));
        activity.startActivity(serviceTokenResult.intent);
        return serviceTokenFuture.get();
    }
}
