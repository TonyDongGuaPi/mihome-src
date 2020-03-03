package com.miui.tsmclient.model.mitsm;

import com.miui.tsmclient.model.mitsm.TSMSessionManager;
import com.miui.tsmclient.net.TSMAuthContants;
import com.miui.tsmclient.seitsm.TsmRpcModels;

public enum PullDataOperationType {
    ISSUE(TSMSessionManager.BusinessType.INSTALL, TsmRpcModels.SeOperationType.INSTALL, TSMAuthContants.URL_PULL_BUS_CARD_PERSO_DATA, "pullPersoData"),
    TRANSFER_IN(TSMSessionManager.BusinessType.TRANSFER_IN, TsmRpcModels.SeOperationType.SHIFT_IN, TSMAuthContants.URL_PULL_BUS_CARD_TRANSFER_IN_DATA, "pullBusCardTransfer"),
    TRANSFER_OUT(TSMSessionManager.BusinessType.TRANSFER_OUT, TsmRpcModels.SeOperationType.SHIFT_OUT, TSMAuthContants.URL_PULL_BUS_CARD_TRANSFER_OUT_DATA, "pullBusCardTransfer"),
    RECHARGE(TSMSessionManager.BusinessType.RECHARGE, TsmRpcModels.SeOperationType.TOPUP, TSMAuthContants.URL_PULL_BUS_CARD_TOPUP_DATA, "pullBusCardTopUp"),
    OUT_ISSUE(TSMSessionManager.BusinessType.OUT_INSTALL, TsmRpcModels.SeOperationType.OUT_INSTALL, TSMAuthContants.URL_PULL_BUS_CARD_PERSO_DATA, "pullBusCardOutInstall"),
    OUT_RECHARGE(TSMSessionManager.BusinessType.OUT_RECHARGE, TsmRpcModels.SeOperationType.OUT_TOPUP, TSMAuthContants.URL_PULL_BUS_CARD_TOPUP_DATA, "pullBusCardOutTopUp"),
    OUT_RETURN(TSMSessionManager.BusinessType.OUT_RETURN, TsmRpcModels.SeOperationType.OUT_RETURN, TSMAuthContants.URL_PULL_BUS_CARD_RETURN_DATA, "pullBusCardOutReturn");
    
    private TSMSessionManager.BusinessType mBusinessType;
    private String mDescription;
    private String mPath;
    private TsmRpcModels.SeOperationType mSeOperationType;

    private PullDataOperationType(TSMSessionManager.BusinessType businessType, TsmRpcModels.SeOperationType seOperationType, String str, String str2) {
        this.mBusinessType = businessType;
        this.mSeOperationType = seOperationType;
        this.mPath = str;
        this.mDescription = str2;
    }

    public TSMSessionManager.BusinessType getBusinessType() {
        return this.mBusinessType;
    }

    public TsmRpcModels.SeOperationType getSeOperationType() {
        return this.mSeOperationType;
    }

    public String getDescription() {
        return this.mDescription;
    }

    public String getPath() {
        return this.mPath;
    }
}
