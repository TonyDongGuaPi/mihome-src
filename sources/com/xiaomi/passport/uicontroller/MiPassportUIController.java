package com.xiaomi.passport.uicontroller;

import android.content.Context;
import android.os.IBinder;
import android.os.RemoteException;
import com.xiaomi.accountsdk.account.data.AccountInfo;
import com.xiaomi.accountsdk.account.data.MiLoginResult;
import com.xiaomi.accountsdk.account.data.NotificationAuthResult;
import com.xiaomi.accountsdk.account.data.NotificationLoginEndParams;
import com.xiaomi.accountsdk.account.data.PasswordLoginParams;
import com.xiaomi.accountsdk.account.data.Step2LoginParams;
import com.xiaomi.accountsdk.futureservice.ClientFuture;
import com.xiaomi.accountsdk.futureservice.ServerServiceConnector;
import com.xiaomi.passport.uicontroller.IMiPassportUIControllerService;
import com.xiaomi.passport.uicontroller.MiPassportLoginFuture;

public class MiPassportUIController {
    private static final String DEFAULT_ACTION_LOGIN_SERVICE = "com.xiaomi.account.action.UI_CONTROLLER_SERVICE";
    private static final String MIUI_LOGIN_SERVICE_PACKAGE_NAME = "com.xiaomi.account";
    private static final String TAG = "MiPassportUIController";
    private static volatile MiPassportUIControllerFactory factory = MiPassportUIControllerFactory.DEFAULT_IMPL;
    private static volatile MiPassportUIController sThis;
    /* access modifiers changed from: private */
    public final String loginServiceActionName;
    /* access modifiers changed from: private */
    public final String loginServicePackageName;
    /* access modifiers changed from: private */
    public final Context mContext;

    public MiPassportUIController(Context context, String str, String str2) {
        this.mContext = context.getApplicationContext();
        this.loginServiceActionName = str;
        this.loginServicePackageName = str2;
    }

    public static void resetMiPassportUIControllerFactoryForTest() {
        factory = MiPassportUIControllerFactory.DEFAULT_IMPL;
    }

    public static void setMiPassportUIControllerFactoryForTest(MiPassportUIControllerFactory miPassportUIControllerFactory) {
        factory = miPassportUIControllerFactory;
    }

    public static MiPassportUIController get(Context context) {
        return factory.newMUiController(context, DEFAULT_ACTION_LOGIN_SERVICE, context.getPackageName());
    }

    public static MiPassportUIController getForMiuiSystemAccountService(Context context) {
        return factory.newMUiController(context, DEFAULT_ACTION_LOGIN_SERVICE, "com.xiaomi.account");
    }

    public MiPassportLoginFuture.PasswordLoginFuture loginByPassword(final PasswordLoginParams passwordLoginParams, MiPassportLoginFuture.PasswordLoginUICallback passwordLoginUICallback) {
        MiPassportLoginFuture.PasswordLoginFuture passwordLoginFuture = new MiPassportLoginFuture.PasswordLoginFuture(passwordLoginUICallback);
        new UIConnector<MiLoginResult, AccountInfo>(passwordLoginFuture) {
            public MiLoginResult doModelLayerWork() throws RemoteException {
                return ((IMiPassportUIControllerService) getIService()).loginByPassword(passwordLoginParams);
            }
        }.bind();
        return passwordLoginFuture;
    }

    public MiPassportLoginFuture.NotificationLoginFuture onNotificationLoginEnd(final NotificationLoginEndParams notificationLoginEndParams, MiPassportLoginFuture.NotificationLoginUICallback notificationLoginUICallback) {
        MiPassportLoginFuture.NotificationLoginFuture notificationLoginFuture = new MiPassportLoginFuture.NotificationLoginFuture(notificationLoginUICallback);
        new UIConnector<MiLoginResult, AccountInfo>(notificationLoginFuture) {
            public MiLoginResult doModelLayerWork() throws RemoteException {
                return ((IMiPassportUIControllerService) getIService()).onNotificationLoginEnd(notificationLoginEndParams);
            }
        }.bind();
        return notificationLoginFuture;
    }

    public MiPassportLoginFuture.Step2LoginFuture loginByStep2(final Step2LoginParams step2LoginParams, MiPassportLoginFuture.Step2LoginUICallback step2LoginUICallback) {
        MiPassportLoginFuture.Step2LoginFuture step2LoginFuture = new MiPassportLoginFuture.Step2LoginFuture(step2LoginUICallback);
        new UIConnector<MiLoginResult, AccountInfo>(step2LoginFuture) {
            public MiLoginResult doModelLayerWork() throws RemoteException {
                return ((IMiPassportUIControllerService) getIService()).loginByStep2(step2LoginParams);
            }
        }.bind();
        return step2LoginFuture;
    }

    @Deprecated
    public void addOrUpdateAccountManager(AccountInfo accountInfo) {
        addOrUpdateAccountManager(accountInfo, (MiPassportLoginFuture.AddOrUpdateUICallback) null);
    }

    public MiPassportLoginFuture.AddOrUpdateAccountFuture addOrUpdateAccountManager(final AccountInfo accountInfo, MiPassportLoginFuture.AddOrUpdateUICallback addOrUpdateUICallback) {
        MiPassportLoginFuture.AddOrUpdateAccountFuture addOrUpdateAccountFuture = new MiPassportLoginFuture.AddOrUpdateAccountFuture(addOrUpdateUICallback);
        new UIConnector<Void, Void>(addOrUpdateAccountFuture) {
            public Void doModelLayerWork() throws RemoteException {
                ((IMiPassportUIControllerService) getIService()).addOrUpdateAccountManager(accountInfo);
                return null;
            }
        }.bind();
        return addOrUpdateAccountFuture;
    }

    public MiPassportLoginFuture.NotificationAuthFuture onNotificationAuthEnd(final String str, MiPassportLoginFuture.NotificationAuthUICallback notificationAuthUICallback) {
        MiPassportLoginFuture.NotificationAuthFuture notificationAuthFuture = new MiPassportLoginFuture.NotificationAuthFuture(notificationAuthUICallback);
        new UIConnector<NotificationAuthResult, NotificationAuthResult>(notificationAuthFuture) {
            public NotificationAuthResult doModelLayerWork() throws RemoteException {
                return ((IMiPassportUIControllerService) getIService()).onNotificationAuthEnd(str);
            }
        }.bind();
        return notificationAuthFuture;
    }

    private abstract class UIConnector<ModelDataType, UIDataType> extends ServerServiceConnector<IMiPassportUIControllerService, ModelDataType, UIDataType> {
        /* access modifiers changed from: protected */
        public abstract ModelDataType doModelLayerWork() throws RemoteException;

        protected UIConnector(ClientFuture<ModelDataType, UIDataType> clientFuture) {
            super(MiPassportUIController.this.mContext, MiPassportUIController.this.loginServiceActionName, MiPassportUIController.this.loginServicePackageName, clientFuture);
        }

        /* access modifiers changed from: protected */
        public IMiPassportUIControllerService binderToServiceType(IBinder iBinder) {
            return IMiPassportUIControllerService.Stub.asInterface(iBinder);
        }

        /* access modifiers changed from: protected */
        public ModelDataType callServiceWork() throws RemoteException {
            return doModelLayerWork();
        }
    }
}
