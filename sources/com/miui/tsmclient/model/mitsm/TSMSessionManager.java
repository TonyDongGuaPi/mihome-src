package com.miui.tsmclient.model.mitsm;

import android.content.Context;
import com.miui.tsmclient.entity.CardInfo;
import com.miui.tsmclient.seitsm.Exception.SeiTSMApiException;
import com.miui.tsmclient.seitsm.SeiTsmAuthManager;
import com.miui.tsmclient.seitsm.TsmRpcModels;
import com.taobao.weex.annotation.JSMethod;
import java.util.HashMap;
import java.util.Map;

public class TSMSessionManager {
    private static TSMSessionManager sInstance = new TSMSessionManager();
    private Map<String, TsmRpcModels.TsmSessionInfo> mBusiness2SessionMap = new HashMap();
    private SeiTsmAuthManager mSeiTsmAuthManager = new SeiTsmAuthManager();

    public enum BusinessType {
        INSTALL,
        DELETE,
        RECHARGE,
        LOCK,
        QUERY,
        PREPARE_PAY,
        PERSO,
        SAVEKEY,
        TRANSFER_OUT,
        TRANSFER_IN,
        OUT_INSTALL,
        OUT_RECHARGE,
        OUT_RETURN
    }

    private TSMSessionManager() {
    }

    public static TSMSessionManager getInstance() {
        return sInstance;
    }

    public TsmRpcModels.TsmSessionInfo getSession(Context context, CardInfo cardInfo) throws SeiTSMApiException {
        return this.mSeiTsmAuthManager.createSession(context, cardInfo);
    }

    public TsmRpcModels.TsmSessionInfo getSession(Context context, CardInfo cardInfo, BusinessType businessType, boolean z) throws SeiTSMApiException {
        TsmRpcModels.TsmSessionInfo tsmSessionInfo;
        String realBusinessId = getRealBusinessId(cardInfo, businessType);
        synchronized (this.mBusiness2SessionMap) {
            if (!this.mBusiness2SessionMap.containsKey(realBusinessId) || z) {
                this.mBusiness2SessionMap.put(realBusinessId, this.mSeiTsmAuthManager.createSession(context, cardInfo));
            }
            tsmSessionInfo = this.mBusiness2SessionMap.get(realBusinessId);
        }
        return tsmSessionInfo;
    }

    public TsmRpcModels.TsmSessionInfo removeSession(CardInfo cardInfo, BusinessType businessType) {
        TsmRpcModels.TsmSessionInfo remove;
        synchronized (this.mBusiness2SessionMap) {
            remove = this.mBusiness2SessionMap.remove(getRealBusinessId(cardInfo, businessType));
        }
        return remove;
    }

    private String getRealBusinessId(CardInfo cardInfo, BusinessType businessType) {
        if (cardInfo == null || businessType == null) {
            return null;
        }
        return cardInfo.mCardType + JSMethod.NOT_SET + businessType.toString();
    }
}
