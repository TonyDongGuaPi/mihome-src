package com.xiaomi.accountsdk.hasheddeviceidlib;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Looper;
import android.text.TextUtils;
import com.xiaomi.accountsdk.account.exception.IllegalDeviceException;
import com.xiaomi.accountsdk.hasheddeviceidlib.PlainDeviceIdUtil;
import com.xiaomi.accountsdk.utils.AccountLog;
import java.util.UUID;

public class HashedDeviceIdUtil {
    private static final String PSEUDO_DEVICE_ID_PREFIX = "android_pseudo_";
    private static final String SP_KEY_HASHED_DEVICE_ID = "hashedDeviceId";
    private static final String SP_NAME_DEVICE_ID = "deviceId";
    private static final String TAG = "HashedDeviceIdUtil";
    private final Context context;
    private final PlainDeviceIdUtil.IPlainDeviceIdFetcher plainDeviceIdFetcher;

    public enum DeviceIdPolicy {
        RUNTIME_DEVICE_ID_ONLY,
        CACHED_THEN_RUNTIME_THEN_PSEUDO
    }

    public HashedDeviceIdUtil(Context context2) {
        this(context2, PlainDeviceIdUtil.getFetcherInstance());
    }

    public HashedDeviceIdUtil(Context context2, PlainDeviceIdUtil.IPlainDeviceIdFetcher iPlainDeviceIdFetcher) {
        Context context3;
        if (iPlainDeviceIdFetcher != null) {
            if (context2 == null) {
                context3 = null;
            } else {
                context3 = context2.getApplicationContext();
            }
            this.context = context3;
            this.plainDeviceIdFetcher = iPlainDeviceIdFetcher;
            return;
        }
        throw new IllegalArgumentException("plainDeviceIdFetcher == null");
    }

    public static class GlobalConfig {
        public static DeviceIdPolicy DEFAULT_DEVICE_ID_POLICY = DeviceIdPolicy.RUNTIME_DEVICE_ID_ONLY;
        private static final GlobalConfig sInstance = new GlobalConfig();
        /* access modifiers changed from: private */
        public DeviceIdPolicy policy = DEFAULT_DEVICE_ID_POLICY;
        private IUnifiedDeviceIdFetcher unifiedDeviceIdFetcher;

        private GlobalConfig() {
        }

        public static GlobalConfig getInstance() {
            return sInstance;
        }

        public void setPolicy(DeviceIdPolicy deviceIdPolicy) {
            this.policy = deviceIdPolicy;
        }

        public void setUnifiedDeviceIdFetcher(IUnifiedDeviceIdFetcher iUnifiedDeviceIdFetcher) {
            this.unifiedDeviceIdFetcher = iUnifiedDeviceIdFetcher;
        }

        public IUnifiedDeviceIdFetcher getUnifiedDeviceIdFetcher() {
            return this.unifiedDeviceIdFetcher;
        }
    }

    public boolean hasHistoricalHashedDeviceId() {
        return legal(loadHistoricalHashedDeviceId());
    }

    /* access modifiers changed from: package-private */
    public DeviceIdPolicy policy() {
        return GlobalConfig.getInstance().policy;
    }

    public String getHashedDeviceIdThrow() throws IllegalDeviceException {
        String hashedDeviceIdNoThrow = getHashedDeviceIdNoThrow();
        if (hashedDeviceIdNoThrow != null) {
            return hashedDeviceIdNoThrow;
        }
        throw new IllegalDeviceException("null device id");
    }

    @Deprecated
    public synchronized String getHashedDeviceIdNoThrow() {
        return getHashedDeviceId(true);
    }

    public synchronized String getHashedDeviceId(boolean z) {
        IUnifiedDeviceIdFetcher unifiedDeviceIdFetcher;
        DeviceIdPolicy policy = policy();
        if (policy == DeviceIdPolicy.RUNTIME_DEVICE_ID_ONLY) {
            return getRuntimeDeviceIdHashed();
        } else if (policy == DeviceIdPolicy.CACHED_THEN_RUNTIME_THEN_PSEUDO) {
            String loadHistoricalHashedDeviceId = loadHistoricalHashedDeviceId();
            if (legal(loadHistoricalHashedDeviceId)) {
                return loadHistoricalHashedDeviceId;
            }
            String runtimeDeviceIdHashed = getRuntimeDeviceIdHashed();
            if (runtimeDeviceIdHashed != null) {
                saveHistoricalHashedDeviceId(runtimeDeviceIdHashed);
                return runtimeDeviceIdHashed;
            }
            if (z) {
                if (!isMainThread() && (unifiedDeviceIdFetcher = GlobalConfig.getInstance().getUnifiedDeviceIdFetcher()) != null) {
                    String hashedDeviceId = unifiedDeviceIdFetcher.getHashedDeviceId(this.context);
                    if (!TextUtils.isEmpty(hashedDeviceId)) {
                        saveHistoricalHashedDeviceId(hashedDeviceId);
                        return hashedDeviceId;
                    }
                }
            }
            String createPseudoDeviceId = createPseudoDeviceId();
            saveHistoricalHashedDeviceId(createPseudoDeviceId);
            return createPseudoDeviceId;
        } else {
            throw new IllegalStateException("unknown policy " + policy);
        }
    }

    private static boolean isMainThread() {
        return Looper.myLooper() == Looper.getMainLooper();
    }

    /* access modifiers changed from: package-private */
    public String getRuntimeDeviceIdHashed() {
        try {
            String userEnvironmentPlainDeviceId = getUserEnvironmentPlainDeviceId();
            if (legal(userEnvironmentPlainDeviceId)) {
                return DeviceIdHasher.hashDeviceInfo(userEnvironmentPlainDeviceId);
            }
            return null;
        } catch (SecurityException e) {
            AccountLog.w(TAG, "can't get deviceid.", e);
            return null;
        }
    }

    /* access modifiers changed from: package-private */
    public String getUserEnvironmentPlainDeviceId() {
        return this.plainDeviceIdFetcher.getPlainDeviceId(this.context);
    }

    /* access modifiers changed from: package-private */
    public boolean legal(String str) {
        return !TextUtils.isEmpty(str);
    }

    /* access modifiers changed from: package-private */
    public String createPseudoDeviceId() {
        return String.format("%s%s", new Object[]{PSEUDO_DEVICE_ID_PREFIX, UUID.randomUUID().toString()});
    }

    public String loadHistoricalHashedDeviceId() {
        SharedPreferences sp = getSP();
        if (sp == null) {
            return null;
        }
        return sp.getString(SP_KEY_HASHED_DEVICE_ID, (String) null);
    }

    public void saveHistoricalHashedDeviceId(String str) {
        SharedPreferences sp = getSP();
        if (sp != null) {
            sp.edit().putString(SP_KEY_HASHED_DEVICE_ID, str).commit();
        }
    }

    /* access modifiers changed from: package-private */
    public SharedPreferences getSP() {
        if (this.context == null) {
            return null;
        }
        return this.context.getSharedPreferences("deviceId", 0);
    }
}
