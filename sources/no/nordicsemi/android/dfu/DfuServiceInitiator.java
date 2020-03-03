package no.nordicsemi.android.dfu;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.ParcelUuid;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import com.coloros.mcssdk.PushManager;
import java.security.InvalidParameterException;
import java.util.UUID;

public class DfuServiceInitiator {
    public static final int DEFAULT_PRN_VALUE = 12;
    public static final int SCOPE_APPLICATION = 3542;
    public static final int SCOPE_SYSTEM_COMPONENTS = 7578;
    private Parcelable[] buttonlessDfuWithBondSharingUuids;
    private Parcelable[] buttonlessDfuWithoutBondSharingUuids;
    private int currentMtu = 23;
    private final String deviceAddress;
    private String deviceName;
    private boolean disableNotification = false;
    private boolean disableResume = false;
    private boolean enableUnsafeExperimentalButtonlessDfu = false;
    private Parcelable[] experimentalButtonlessDfuUuids;
    private String filePath;
    private int fileResId;
    private int fileType = -1;
    private Uri fileUri;
    private boolean forceDfu = false;
    private String initFilePath;
    private int initFileResId;
    private Uri initFileUri;
    private boolean keepBond;
    private Parcelable[] legacyDfuUuids;
    private String mimeType;
    private int mtu = 517;
    private int numberOfPackets = 12;
    private Boolean packetReceiptNotificationsEnabled;
    private boolean restoreBond;
    private Parcelable[] secureDfuUuids;
    private boolean startAsForegroundService = true;

    public DfuServiceInitiator(@NonNull String str) {
        this.deviceAddress = str;
    }

    public DfuServiceInitiator setDeviceName(@Nullable String str) {
        this.deviceName = str;
        return this;
    }

    public DfuServiceInitiator setDisableNotification(boolean z) {
        this.disableNotification = z;
        return this;
    }

    public DfuServiceInitiator setForeground(boolean z) {
        this.startAsForegroundService = z;
        return this;
    }

    public DfuServiceInitiator setKeepBond(boolean z) {
        this.keepBond = z;
        return this;
    }

    public DfuServiceInitiator setRestoreBond(boolean z) {
        this.restoreBond = z;
        return this;
    }

    public DfuServiceInitiator setPacketsReceiptNotificationsEnabled(boolean z) {
        this.packetReceiptNotificationsEnabled = Boolean.valueOf(z);
        return this;
    }

    public DfuServiceInitiator setPacketsReceiptNotificationsValue(int i) {
        if (i <= 0) {
            i = 12;
        }
        this.numberOfPackets = i;
        return this;
    }

    public DfuServiceInitiator setForceDfu(boolean z) {
        this.forceDfu = z;
        return this;
    }

    public DfuServiceInitiator disableResume() {
        this.disableResume = true;
        return this;
    }

    public DfuServiceInitiator setMtu(int i) {
        this.mtu = i;
        return this;
    }

    public DfuServiceInitiator setCurrentMtu(int i) {
        this.currentMtu = i;
        return this;
    }

    public DfuServiceInitiator disableMtuRequest() {
        this.mtu = 0;
        return this;
    }

    public DfuServiceInitiator setScope(int i) {
        if (DfuBaseService.MIME_TYPE_ZIP.equals(this.mimeType)) {
            if (i == 3542) {
                this.fileType = 4;
            } else if (i == 7578) {
                this.fileType = 3;
            } else {
                throw new UnsupportedOperationException("Unknown scope");
            }
            return this;
        }
        throw new UnsupportedOperationException("Scope can be set only for a ZIP file");
    }

    public DfuServiceInitiator setUnsafeExperimentalButtonlessServiceInSecureDfuEnabled(boolean z) {
        this.enableUnsafeExperimentalButtonlessDfu = z;
        return this;
    }

    public DfuServiceInitiator setCustomUuidsForLegacyDfu(@Nullable UUID uuid, @Nullable UUID uuid2, @Nullable UUID uuid3, @Nullable UUID uuid4) {
        ParcelUuid[] parcelUuidArr = new ParcelUuid[4];
        ParcelUuid parcelUuid = null;
        parcelUuidArr[0] = uuid != null ? new ParcelUuid(uuid) : null;
        parcelUuidArr[1] = uuid2 != null ? new ParcelUuid(uuid2) : null;
        parcelUuidArr[2] = uuid3 != null ? new ParcelUuid(uuid3) : null;
        if (uuid4 != null) {
            parcelUuid = new ParcelUuid(uuid4);
        }
        parcelUuidArr[3] = parcelUuid;
        this.legacyDfuUuids = parcelUuidArr;
        return this;
    }

    public DfuServiceInitiator setCustomUuidsForSecureDfu(@Nullable UUID uuid, @Nullable UUID uuid2, @Nullable UUID uuid3) {
        ParcelUuid[] parcelUuidArr = new ParcelUuid[3];
        ParcelUuid parcelUuid = null;
        parcelUuidArr[0] = uuid != null ? new ParcelUuid(uuid) : null;
        parcelUuidArr[1] = uuid2 != null ? new ParcelUuid(uuid2) : null;
        if (uuid3 != null) {
            parcelUuid = new ParcelUuid(uuid3);
        }
        parcelUuidArr[2] = parcelUuid;
        this.secureDfuUuids = parcelUuidArr;
        return this;
    }

    public DfuServiceInitiator setCustomUuidsForExperimentalButtonlessDfu(@Nullable UUID uuid, @Nullable UUID uuid2) {
        ParcelUuid[] parcelUuidArr = new ParcelUuid[2];
        ParcelUuid parcelUuid = null;
        parcelUuidArr[0] = uuid != null ? new ParcelUuid(uuid) : null;
        if (uuid2 != null) {
            parcelUuid = new ParcelUuid(uuid2);
        }
        parcelUuidArr[1] = parcelUuid;
        this.experimentalButtonlessDfuUuids = parcelUuidArr;
        return this;
    }

    public DfuServiceInitiator setCustomUuidsForButtonlessDfuWithBondSharing(@Nullable UUID uuid, @Nullable UUID uuid2) {
        ParcelUuid[] parcelUuidArr = new ParcelUuid[2];
        ParcelUuid parcelUuid = null;
        parcelUuidArr[0] = uuid != null ? new ParcelUuid(uuid) : null;
        if (uuid2 != null) {
            parcelUuid = new ParcelUuid(uuid2);
        }
        parcelUuidArr[1] = parcelUuid;
        this.buttonlessDfuWithBondSharingUuids = parcelUuidArr;
        return this;
    }

    public DfuServiceInitiator setCustomUuidsForButtonlessDfuWithoutBondSharing(@Nullable UUID uuid, @Nullable UUID uuid2) {
        ParcelUuid[] parcelUuidArr = new ParcelUuid[2];
        ParcelUuid parcelUuid = null;
        parcelUuidArr[0] = uuid != null ? new ParcelUuid(uuid) : null;
        if (uuid2 != null) {
            parcelUuid = new ParcelUuid(uuid2);
        }
        parcelUuidArr[1] = parcelUuid;
        this.buttonlessDfuWithoutBondSharingUuids = parcelUuidArr;
        return this;
    }

    public DfuServiceInitiator setZip(@NonNull Uri uri) {
        return init(uri, (String) null, 0, 0, DfuBaseService.MIME_TYPE_ZIP);
    }

    public DfuServiceInitiator setZip(@NonNull String str) {
        return init((Uri) null, str, 0, 0, DfuBaseService.MIME_TYPE_ZIP);
    }

    public DfuServiceInitiator setZip(int i) {
        return init((Uri) null, (String) null, i, 0, DfuBaseService.MIME_TYPE_ZIP);
    }

    public DfuServiceInitiator setZip(@Nullable Uri uri, @Nullable String str) {
        return init(uri, str, 0, 0, DfuBaseService.MIME_TYPE_ZIP);
    }

    @Deprecated
    public DfuServiceInitiator setBinOrHex(int i, @NonNull Uri uri) {
        if (i != 0) {
            return init(uri, (String) null, 0, i, "application/octet-stream");
        }
        throw new UnsupportedOperationException("You must specify the file type");
    }

    @Deprecated
    public DfuServiceInitiator setBinOrHex(int i, @NonNull String str) {
        if (i != 0) {
            return init((Uri) null, str, 0, i, "application/octet-stream");
        }
        throw new UnsupportedOperationException("You must specify the file type");
    }

    @Deprecated
    public DfuServiceInitiator setBinOrHex(int i, @Nullable Uri uri, @Nullable String str) {
        if (i != 0) {
            return init(uri, str, 0, i, "application/octet-stream");
        }
        throw new UnsupportedOperationException("You must specify the file type");
    }

    @Deprecated
    public DfuServiceInitiator setBinOrHex(int i, int i2) {
        if (i != 0) {
            return init((Uri) null, (String) null, i2, i, "application/octet-stream");
        }
        throw new UnsupportedOperationException("You must specify the file type");
    }

    @Deprecated
    public DfuServiceInitiator setInitFile(@NonNull Uri uri) {
        return init(uri, (String) null, 0);
    }

    @Deprecated
    public DfuServiceInitiator setInitFile(@Nullable String str) {
        return init((Uri) null, str, 0);
    }

    @Deprecated
    public DfuServiceInitiator setInitFile(int i) {
        return init((Uri) null, (String) null, i);
    }

    @Deprecated
    public DfuServiceInitiator setInitFile(@Nullable Uri uri, @Nullable String str) {
        return init(uri, str, 0);
    }

    public DfuServiceController start(@NonNull Context context, @NonNull Class<? extends DfuBaseService> cls) {
        if (this.fileType != -1) {
            Intent intent = new Intent(context, cls);
            intent.putExtra(DfuBaseService.EXTRA_DEVICE_ADDRESS, this.deviceAddress);
            intent.putExtra(DfuBaseService.EXTRA_DEVICE_NAME, this.deviceName);
            intent.putExtra(DfuBaseService.EXTRA_DISABLE_NOTIFICATION, this.disableNotification);
            intent.putExtra(DfuBaseService.EXTRA_FOREGROUND_SERVICE, this.startAsForegroundService);
            intent.putExtra(DfuBaseService.EXTRA_FILE_MIME_TYPE, this.mimeType);
            intent.putExtra(DfuBaseService.EXTRA_FILE_TYPE, this.fileType);
            intent.putExtra(DfuBaseService.EXTRA_FILE_URI, this.fileUri);
            intent.putExtra(DfuBaseService.EXTRA_FILE_PATH, this.filePath);
            intent.putExtra(DfuBaseService.EXTRA_FILE_RES_ID, this.fileResId);
            intent.putExtra(DfuBaseService.EXTRA_INIT_FILE_URI, this.initFileUri);
            intent.putExtra(DfuBaseService.EXTRA_INIT_FILE_PATH, this.initFilePath);
            intent.putExtra(DfuBaseService.EXTRA_INIT_FILE_RES_ID, this.initFileResId);
            intent.putExtra(DfuBaseService.EXTRA_KEEP_BOND, this.keepBond);
            intent.putExtra(DfuBaseService.EXTRA_RESTORE_BOND, this.restoreBond);
            intent.putExtra(DfuBaseService.EXTRA_FORCE_DFU, this.forceDfu);
            intent.putExtra(DfuBaseService.EXTRA_DISABLE_RESUME, this.disableResume);
            if (this.mtu > 0) {
                intent.putExtra(DfuBaseService.EXTRA_MTU, this.mtu);
            }
            intent.putExtra(DfuBaseService.EXTRA_CURRENT_MTU, this.currentMtu);
            intent.putExtra(DfuBaseService.EXTRA_UNSAFE_EXPERIMENTAL_BUTTONLESS_DFU, this.enableUnsafeExperimentalButtonlessDfu);
            if (this.packetReceiptNotificationsEnabled != null) {
                intent.putExtra(DfuBaseService.EXTRA_PACKET_RECEIPT_NOTIFICATIONS_ENABLED, this.packetReceiptNotificationsEnabled);
                intent.putExtra(DfuBaseService.EXTRA_PACKET_RECEIPT_NOTIFICATIONS_VALUE, this.numberOfPackets);
            }
            if (this.legacyDfuUuids != null) {
                intent.putExtra(DfuBaseService.EXTRA_CUSTOM_UUIDS_FOR_LEGACY_DFU, this.legacyDfuUuids);
            }
            if (this.secureDfuUuids != null) {
                intent.putExtra(DfuBaseService.EXTRA_CUSTOM_UUIDS_FOR_SECURE_DFU, this.secureDfuUuids);
            }
            if (this.experimentalButtonlessDfuUuids != null) {
                intent.putExtra(DfuBaseService.EXTRA_CUSTOM_UUIDS_FOR_EXPERIMENTAL_BUTTONLESS_DFU, this.experimentalButtonlessDfuUuids);
            }
            if (this.buttonlessDfuWithoutBondSharingUuids != null) {
                intent.putExtra(DfuBaseService.EXTRA_CUSTOM_UUIDS_FOR_BUTTONLESS_DFU_WITHOUT_BOND_SHARING, this.buttonlessDfuWithoutBondSharingUuids);
            }
            if (this.buttonlessDfuWithBondSharingUuids != null) {
                intent.putExtra(DfuBaseService.EXTRA_CUSTOM_UUIDS_FOR_BUTTONLESS_DFU_WITH_BOND_SHARING, this.buttonlessDfuWithBondSharingUuids);
            }
            if (Build.VERSION.SDK_INT < 26 || !this.startAsForegroundService) {
                context.startService(intent);
            } else {
                context.startForegroundService(intent);
            }
            return new DfuServiceController(context);
        }
        throw new UnsupportedOperationException("You must specify the firmware file before starting the service");
    }

    private DfuServiceInitiator init(@Nullable Uri uri, @Nullable String str, int i) {
        if (!DfuBaseService.MIME_TYPE_ZIP.equals(this.mimeType)) {
            this.initFileUri = uri;
            this.initFilePath = str;
            this.initFileResId = i;
            return this;
        }
        throw new InvalidParameterException("Init file must be located inside the ZIP");
    }

    private DfuServiceInitiator init(@Nullable Uri uri, @Nullable String str, int i, int i2, @NonNull String str2) {
        this.fileUri = uri;
        this.filePath = str;
        this.fileResId = i;
        this.fileType = i2;
        this.mimeType = str2;
        if (DfuBaseService.MIME_TYPE_ZIP.equals(str2)) {
            this.initFileUri = null;
            this.initFilePath = null;
            this.initFileResId = 0;
        }
        return this;
    }

    @RequiresApi(api = 26)
    public static void createDfuNotificationChannel(@NonNull Context context) {
        NotificationChannel notificationChannel = new NotificationChannel(DfuBaseService.NOTIFICATION_CHANNEL_DFU, context.getString(R.string.dfu_channel_name), 2);
        notificationChannel.setDescription(context.getString(R.string.dfu_channel_description));
        notificationChannel.setShowBadge(false);
        notificationChannel.setLockscreenVisibility(1);
        NotificationManager notificationManager = (NotificationManager) context.getSystemService(PushManager.MESSAGE_TYPE_NOTI);
        if (notificationManager != null) {
            notificationManager.createNotificationChannel(notificationChannel);
        }
    }
}
