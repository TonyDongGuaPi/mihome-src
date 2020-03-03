package com.facebook.internal;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.pm.PackageManager;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import android.support.annotation.Nullable;
import android.support.annotation.RestrictTo;
import com.facebook.FacebookSdk;
import com.xiaomi.accountsdk.service.DeviceInfoResult;
import java.lang.reflect.Method;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.atomic.AtomicBoolean;

public class AttributionIdentifiers {
    private static final String ANDROID_ID_COLUMN_NAME = "androidid";
    private static final String ATTRIBUTION_ID_COLUMN_NAME = "aid";
    private static final String ATTRIBUTION_ID_CONTENT_PROVIDER = "com.facebook.katana.provider.AttributionIdProvider";
    private static final String ATTRIBUTION_ID_CONTENT_PROVIDER_WAKIZASHI = "com.facebook.wakizashi.provider.AttributionIdProvider";
    private static final int CONNECTION_RESULT_SUCCESS = 0;
    private static final long IDENTIFIER_REFRESH_INTERVAL_MILLIS = 3600000;
    private static final String LIMIT_TRACKING_COLUMN_NAME = "limit_tracking";
    private static final String TAG = AttributionIdentifiers.class.getCanonicalName();
    private static AttributionIdentifiers recentlyFetchedIdentifiers;
    private String androidAdvertiserId;
    private String androidInstallerPackage;
    private String attributionId;
    private long fetchTime;
    private boolean limitTracking;

    private static AttributionIdentifiers getAndroidId(Context context) {
        AttributionIdentifiers androidIdViaReflection = getAndroidIdViaReflection(context);
        if (androidIdViaReflection != null) {
            return androidIdViaReflection;
        }
        AttributionIdentifiers androidIdViaService = getAndroidIdViaService(context);
        return androidIdViaService == null ? new AttributionIdentifiers() : androidIdViaService;
    }

    private static AttributionIdentifiers getAndroidIdViaReflection(Context context) {
        Method methodQuietly;
        Object invokeMethodQuietly;
        try {
            if (!isGooglePlayServicesAvailable(context) || (methodQuietly = Utility.getMethodQuietly("com.google.android.gms.ads.identifier.AdvertisingIdClient", "getAdvertisingIdInfo", (Class<?>[]) new Class[]{Context.class})) == null || (invokeMethodQuietly = Utility.invokeMethodQuietly((Object) null, methodQuietly, context)) == null) {
                return null;
            }
            Method methodQuietly2 = Utility.getMethodQuietly(invokeMethodQuietly.getClass(), "getId", (Class<?>[]) new Class[0]);
            Method methodQuietly3 = Utility.getMethodQuietly(invokeMethodQuietly.getClass(), "isLimitAdTrackingEnabled", (Class<?>[]) new Class[0]);
            if (methodQuietly2 != null) {
                if (methodQuietly3 != null) {
                    AttributionIdentifiers attributionIdentifiers = new AttributionIdentifiers();
                    attributionIdentifiers.androidAdvertiserId = (String) Utility.invokeMethodQuietly(invokeMethodQuietly, methodQuietly2, new Object[0]);
                    attributionIdentifiers.limitTracking = ((Boolean) Utility.invokeMethodQuietly(invokeMethodQuietly, methodQuietly3, new Object[0])).booleanValue();
                    return attributionIdentifiers;
                }
            }
            return null;
        } catch (Exception e) {
            Utility.logd(DeviceInfoResult.BUNDLE_KEY_ANDROID_ID, e);
            return null;
        }
    }

    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public static boolean isTrackingLimited(Context context) {
        AttributionIdentifiers attributionIdentifiers = getAttributionIdentifiers(context);
        return attributionIdentifiers != null && attributionIdentifiers.isTrackingLimited();
    }

    private static boolean isGooglePlayServicesAvailable(Context context) {
        Method methodQuietly = Utility.getMethodQuietly("com.google.android.gms.common.GooglePlayServicesUtil", "isGooglePlayServicesAvailable", (Class<?>[]) new Class[]{Context.class});
        if (methodQuietly == null) {
            return false;
        }
        Object invokeMethodQuietly = Utility.invokeMethodQuietly((Object) null, methodQuietly, context);
        return (invokeMethodQuietly instanceof Integer) && ((Integer) invokeMethodQuietly).intValue() == 0;
    }

    private static AttributionIdentifiers getAndroidIdViaService(Context context) {
        GoogleAdServiceConnection googleAdServiceConnection = new GoogleAdServiceConnection();
        Intent intent = new Intent("com.google.android.gms.ads.identifier.service.START");
        intent.setPackage("com.google.android.gms");
        if (context.bindService(intent, googleAdServiceConnection, 1)) {
            try {
                GoogleAdInfo googleAdInfo = new GoogleAdInfo(googleAdServiceConnection.getBinder());
                AttributionIdentifiers attributionIdentifiers = new AttributionIdentifiers();
                attributionIdentifiers.androidAdvertiserId = googleAdInfo.getAdvertiserId();
                attributionIdentifiers.limitTracking = googleAdInfo.isTrackingLimited();
                return attributionIdentifiers;
            } catch (Exception e) {
                Utility.logd(DeviceInfoResult.BUNDLE_KEY_ANDROID_ID, e);
            } finally {
                context.unbindService(googleAdServiceConnection);
            }
        }
        return null;
    }

    /* JADX WARNING: Removed duplicated region for block: B:23:0x0077 A[Catch:{ Exception -> 0x00ed, all -> 0x00eb }] */
    /* JADX WARNING: Removed duplicated region for block: B:25:0x007b A[Catch:{ Exception -> 0x00ed, all -> 0x00eb }] */
    /* JADX WARNING: Removed duplicated region for block: B:27:0x0080 A[Catch:{ Exception -> 0x00ed, all -> 0x00eb }] */
    /* JADX WARNING: Removed duplicated region for block: B:61:0x010b  */
    /* JADX WARNING: Removed duplicated region for block: B:66:0x0113  */
    @android.support.annotation.Nullable
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static com.facebook.internal.AttributionIdentifiers getAttributionIdentifiers(android.content.Context r10) {
        /*
            com.facebook.internal.AttributionIdentifiers r0 = getAndroidId(r10)
            r1 = 0
            android.os.Looper r2 = android.os.Looper.myLooper()     // Catch:{ Exception -> 0x00ed, all -> 0x00eb }
            android.os.Looper r3 = android.os.Looper.getMainLooper()     // Catch:{ Exception -> 0x00ed, all -> 0x00eb }
            if (r2 == r3) goto L_0x00e3
            com.facebook.internal.AttributionIdentifiers r2 = recentlyFetchedIdentifiers     // Catch:{ Exception -> 0x00ed, all -> 0x00eb }
            if (r2 == 0) goto L_0x0027
            long r2 = java.lang.System.currentTimeMillis()     // Catch:{ Exception -> 0x00ed, all -> 0x00eb }
            com.facebook.internal.AttributionIdentifiers r4 = recentlyFetchedIdentifiers     // Catch:{ Exception -> 0x00ed, all -> 0x00eb }
            long r4 = r4.fetchTime     // Catch:{ Exception -> 0x00ed, all -> 0x00eb }
            r6 = 0
            long r2 = r2 - r4
            r4 = 3600000(0x36ee80, double:1.7786363E-317)
            int r6 = (r2 > r4 ? 1 : (r2 == r4 ? 0 : -1))
            if (r6 >= 0) goto L_0x0027
            com.facebook.internal.AttributionIdentifiers r10 = recentlyFetchedIdentifiers     // Catch:{ Exception -> 0x00ed, all -> 0x00eb }
            return r10
        L_0x0027:
            r2 = 3
            java.lang.String[] r5 = new java.lang.String[r2]     // Catch:{ Exception -> 0x00ed, all -> 0x00eb }
            java.lang.String r2 = "aid"
            r3 = 0
            r5[r3] = r2     // Catch:{ Exception -> 0x00ed, all -> 0x00eb }
            r2 = 1
            java.lang.String r4 = "androidid"
            r5[r2] = r4     // Catch:{ Exception -> 0x00ed, all -> 0x00eb }
            r2 = 2
            java.lang.String r4 = "limit_tracking"
            r5[r2] = r4     // Catch:{ Exception -> 0x00ed, all -> 0x00eb }
            android.content.pm.PackageManager r2 = r10.getPackageManager()     // Catch:{ Exception -> 0x00ed, all -> 0x00eb }
            java.lang.String r4 = "com.facebook.katana.provider.AttributionIdProvider"
            android.content.pm.ProviderInfo r2 = r2.resolveContentProvider(r4, r3)     // Catch:{ Exception -> 0x00ed, all -> 0x00eb }
            android.content.pm.PackageManager r4 = r10.getPackageManager()     // Catch:{ Exception -> 0x00ed, all -> 0x00eb }
            java.lang.String r6 = "com.facebook.wakizashi.provider.AttributionIdProvider"
            android.content.pm.ProviderInfo r3 = r4.resolveContentProvider(r6, r3)     // Catch:{ Exception -> 0x00ed, all -> 0x00eb }
            if (r2 == 0) goto L_0x005f
            java.lang.String r2 = r2.packageName     // Catch:{ Exception -> 0x00ed, all -> 0x00eb }
            boolean r2 = com.facebook.internal.FacebookSignatureValidator.validateSignature(r10, r2)     // Catch:{ Exception -> 0x00ed, all -> 0x00eb }
            if (r2 == 0) goto L_0x005f
            java.lang.String r2 = "content://com.facebook.katana.provider.AttributionIdProvider"
            android.net.Uri r2 = android.net.Uri.parse(r2)     // Catch:{ Exception -> 0x00ed, all -> 0x00eb }
        L_0x005d:
            r4 = r2
            goto L_0x0071
        L_0x005f:
            if (r3 == 0) goto L_0x0070
            java.lang.String r2 = r3.packageName     // Catch:{ Exception -> 0x00ed, all -> 0x00eb }
            boolean r2 = com.facebook.internal.FacebookSignatureValidator.validateSignature(r10, r2)     // Catch:{ Exception -> 0x00ed, all -> 0x00eb }
            if (r2 == 0) goto L_0x0070
            java.lang.String r2 = "content://com.facebook.wakizashi.provider.AttributionIdProvider"
            android.net.Uri r2 = android.net.Uri.parse(r2)     // Catch:{ Exception -> 0x00ed, all -> 0x00eb }
            goto L_0x005d
        L_0x0070:
            r4 = r1
        L_0x0071:
            java.lang.String r2 = getInstallerPackageName(r10)     // Catch:{ Exception -> 0x00ed, all -> 0x00eb }
            if (r2 == 0) goto L_0x0079
            r0.androidInstallerPackage = r2     // Catch:{ Exception -> 0x00ed, all -> 0x00eb }
        L_0x0079:
            if (r4 != 0) goto L_0x0080
            com.facebook.internal.AttributionIdentifiers r10 = cacheAndReturnIdentifiers(r0)     // Catch:{ Exception -> 0x00ed, all -> 0x00eb }
            return r10
        L_0x0080:
            android.content.ContentResolver r3 = r10.getContentResolver()     // Catch:{ Exception -> 0x00ed, all -> 0x00eb }
            r6 = 0
            r7 = 0
            r8 = 0
            android.database.Cursor r10 = r3.query(r4, r5, r6, r7, r8)     // Catch:{ Exception -> 0x00ed, all -> 0x00eb }
            if (r10 == 0) goto L_0x00d9
            boolean r2 = r10.moveToFirst()     // Catch:{ Exception -> 0x00d4, all -> 0x00d0 }
            if (r2 != 0) goto L_0x0094
            goto L_0x00d9
        L_0x0094:
            java.lang.String r2 = "aid"
            int r2 = r10.getColumnIndex(r2)     // Catch:{ Exception -> 0x00d4, all -> 0x00d0 }
            java.lang.String r3 = "androidid"
            int r3 = r10.getColumnIndex(r3)     // Catch:{ Exception -> 0x00d4, all -> 0x00d0 }
            java.lang.String r4 = "limit_tracking"
            int r4 = r10.getColumnIndex(r4)     // Catch:{ Exception -> 0x00d4, all -> 0x00d0 }
            java.lang.String r2 = r10.getString(r2)     // Catch:{ Exception -> 0x00d4, all -> 0x00d0 }
            r0.attributionId = r2     // Catch:{ Exception -> 0x00d4, all -> 0x00d0 }
            if (r3 <= 0) goto L_0x00c6
            if (r4 <= 0) goto L_0x00c6
            java.lang.String r2 = r0.getAndroidAdvertiserId()     // Catch:{ Exception -> 0x00d4, all -> 0x00d0 }
            if (r2 != 0) goto L_0x00c6
            java.lang.String r2 = r10.getString(r3)     // Catch:{ Exception -> 0x00d4, all -> 0x00d0 }
            r0.androidAdvertiserId = r2     // Catch:{ Exception -> 0x00d4, all -> 0x00d0 }
            java.lang.String r2 = r10.getString(r4)     // Catch:{ Exception -> 0x00d4, all -> 0x00d0 }
            boolean r2 = java.lang.Boolean.parseBoolean(r2)     // Catch:{ Exception -> 0x00d4, all -> 0x00d0 }
            r0.limitTracking = r2     // Catch:{ Exception -> 0x00d4, all -> 0x00d0 }
        L_0x00c6:
            if (r10 == 0) goto L_0x00cb
            r10.close()
        L_0x00cb:
            com.facebook.internal.AttributionIdentifiers r10 = cacheAndReturnIdentifiers(r0)
            return r10
        L_0x00d0:
            r0 = move-exception
            r1 = r10
            r10 = r0
            goto L_0x0111
        L_0x00d4:
            r0 = move-exception
            r9 = r0
            r0 = r10
            r10 = r9
            goto L_0x00ef
        L_0x00d9:
            com.facebook.internal.AttributionIdentifiers r0 = cacheAndReturnIdentifiers(r0)     // Catch:{ Exception -> 0x00d4, all -> 0x00d0 }
            if (r10 == 0) goto L_0x00e2
            r10.close()
        L_0x00e2:
            return r0
        L_0x00e3:
            com.facebook.FacebookException r10 = new com.facebook.FacebookException     // Catch:{ Exception -> 0x00ed, all -> 0x00eb }
            java.lang.String r0 = "getAttributionIdentifiers cannot be called on the main thread."
            r10.<init>((java.lang.String) r0)     // Catch:{ Exception -> 0x00ed, all -> 0x00eb }
            throw r10     // Catch:{ Exception -> 0x00ed, all -> 0x00eb }
        L_0x00eb:
            r10 = move-exception
            goto L_0x0111
        L_0x00ed:
            r10 = move-exception
            r0 = r1
        L_0x00ef:
            java.lang.String r2 = TAG     // Catch:{ all -> 0x010f }
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch:{ all -> 0x010f }
            r3.<init>()     // Catch:{ all -> 0x010f }
            java.lang.String r4 = "Caught unexpected exception in getAttributionId(): "
            r3.append(r4)     // Catch:{ all -> 0x010f }
            java.lang.String r10 = r10.toString()     // Catch:{ all -> 0x010f }
            r3.append(r10)     // Catch:{ all -> 0x010f }
            java.lang.String r10 = r3.toString()     // Catch:{ all -> 0x010f }
            com.facebook.internal.Utility.logd((java.lang.String) r2, (java.lang.String) r10)     // Catch:{ all -> 0x010f }
            if (r0 == 0) goto L_0x010e
            r0.close()
        L_0x010e:
            return r1
        L_0x010f:
            r10 = move-exception
            r1 = r0
        L_0x0111:
            if (r1 == 0) goto L_0x0116
            r1.close()
        L_0x0116:
            throw r10
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.internal.AttributionIdentifiers.getAttributionIdentifiers(android.content.Context):com.facebook.internal.AttributionIdentifiers");
    }

    public static AttributionIdentifiers getCachedIdentifiers() {
        return recentlyFetchedIdentifiers;
    }

    private static AttributionIdentifiers cacheAndReturnIdentifiers(AttributionIdentifiers attributionIdentifiers) {
        attributionIdentifiers.fetchTime = System.currentTimeMillis();
        recentlyFetchedIdentifiers = attributionIdentifiers;
        return attributionIdentifiers;
    }

    public String getAttributionId() {
        return this.attributionId;
    }

    public String getAndroidAdvertiserId() {
        if (!FacebookSdk.isInitialized() || !FacebookSdk.getAdvertiserIDCollectionEnabled()) {
            return null;
        }
        return this.androidAdvertiserId;
    }

    public String getAndroidInstallerPackage() {
        return this.androidInstallerPackage;
    }

    public boolean isTrackingLimited() {
        return this.limitTracking;
    }

    @Nullable
    private static String getInstallerPackageName(Context context) {
        PackageManager packageManager = context.getPackageManager();
        if (packageManager != null) {
            return packageManager.getInstallerPackageName(context.getPackageName());
        }
        return null;
    }

    private static final class GoogleAdServiceConnection implements ServiceConnection {
        private AtomicBoolean consumed;
        private final BlockingQueue<IBinder> queue;

        public void onServiceDisconnected(ComponentName componentName) {
        }

        private GoogleAdServiceConnection() {
            this.consumed = new AtomicBoolean(false);
            this.queue = new LinkedBlockingDeque();
        }

        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            if (iBinder != null) {
                try {
                    this.queue.put(iBinder);
                } catch (InterruptedException unused) {
                }
            }
        }

        public IBinder getBinder() throws InterruptedException {
            if (!this.consumed.compareAndSet(true, true)) {
                return this.queue.take();
            }
            throw new IllegalStateException("Binder already consumed");
        }
    }

    private static final class GoogleAdInfo implements IInterface {
        private static final int FIRST_TRANSACTION_CODE = 1;
        private static final int SECOND_TRANSACTION_CODE = 2;
        private IBinder binder;

        GoogleAdInfo(IBinder iBinder) {
            this.binder = iBinder;
        }

        public IBinder asBinder() {
            return this.binder;
        }

        public String getAdvertiserId() throws RemoteException {
            Parcel obtain = Parcel.obtain();
            Parcel obtain2 = Parcel.obtain();
            try {
                obtain.writeInterfaceToken("com.google.android.gms.ads.identifier.internal.IAdvertisingIdService");
                this.binder.transact(1, obtain, obtain2, 0);
                obtain2.readException();
                return obtain2.readString();
            } finally {
                obtain2.recycle();
                obtain.recycle();
            }
        }

        public boolean isTrackingLimited() throws RemoteException {
            Parcel obtain = Parcel.obtain();
            Parcel obtain2 = Parcel.obtain();
            try {
                obtain.writeInterfaceToken("com.google.android.gms.ads.identifier.internal.IAdvertisingIdService");
                boolean z = true;
                obtain.writeInt(1);
                this.binder.transact(2, obtain, obtain2, 0);
                obtain2.readException();
                if (obtain2.readInt() == 0) {
                    z = false;
                }
                return z;
            } finally {
                obtain2.recycle();
                obtain.recycle();
            }
        }
    }
}
