package cn.com.fmsh.tsm.business.card;

import cn.com.fmsh.script.ApduHandler;
import cn.com.fmsh.script.constants.ScriptToolsConst;
import cn.com.fmsh.script.exception.FMScriptHandleException;
import cn.com.fmsh.tsm.business.card.base.CardManager;
import cn.com.fmsh.tsm.business.card.core.LntAppManager;
import cn.com.fmsh.tsm.business.card.core.ShTourAppManager;
import cn.com.fmsh.tsm.business.card.core.StpcManager;
import cn.com.fmsh.tsm.business.constants.Constants;
import cn.com.fmsh.tsm.business.enums.EnumCardAppType;
import cn.com.fmsh.tsm.business.exception.BusinessException;
import cn.com.fmsh.util.FM_Bytes;
import cn.com.fmsh.util.Util4Java;
import cn.com.fmsh.util.log.FMLog;
import cn.com.fmsh.util.log.LogFactory;
import java.util.ArrayList;
import java.util.List;

public class CardManagerFactory {
    private static CardManagerFactory cardManagerFactory;
    private ApduHandler apduHandler;
    private FMLog fmLog = LogFactory.getInstance().getLog();
    private final String logTag = CardManagerFactory.class.getName();

    private CardManagerFactory() {
    }

    public static CardManagerFactory instance() {
        if (cardManagerFactory == null) {
            cardManagerFactory = new CardManagerFactory();
        }
        return cardManagerFactory;
    }

    public CardManager getCardManager(EnumCardAppType enumCardAppType) {
        if (enumCardAppType == null) {
            return new StpcManager();
        }
        if (enumCardAppType == EnumCardAppType.CARD_APP_TYPE_SH) {
            return new StpcManager();
        }
        if (enumCardAppType == EnumCardAppType.CARD_APP_TYPE_SH_TOUR || enumCardAppType == EnumCardAppType.CARD_APP_TYPE_SH_RENT) {
            return new ShTourAppManager();
        }
        return new LntAppManager();
    }

    public void setApduHandler(ApduHandler apduHandler2) {
        this.apduHandler = apduHandler2;
    }

    public List<EnumCardAppType> getCardAppTypes() throws BusinessException {
        EnumCardAppType cardAppType;
        if (this.fmLog == null) {
            this.fmLog = LogFactory.getInstance().getLog();
        }
        if (this.fmLog != null) {
            this.fmLog.debug(this.logTag, "获取卡类型列表...");
        }
        if (this.apduHandler == null) {
            if (this.fmLog != null) {
                this.fmLog.error(this.logTag, "获取卡类型列表时，APDU处理器为空");
            }
            throw new BusinessException("获取卡类型列表时，请先切换卡的访问方式(OMA/NFC)", BusinessException.ErrorMessage.local_business_apdu_handler_null);
        }
        ArrayList arrayList = new ArrayList();
        LntAppManager lntAppManager = new LntAppManager();
        if (this.apduHandler.open(lntAppManager.getAid())) {
            lntAppManager.setApduHandler(this.apduHandler);
            byte[] appNo = lntAppManager.getAppNo();
            if (appNo != null && appNo.length > 0) {
                arrayList.add(EnumCardAppType.CARD_APP_TYPE_LNT);
            }
        }
        if (this.apduHandler.open(new StpcManager().getAid()) && (cardAppType = getCardAppType()) != null) {
            arrayList.add(cardAppType);
        }
        return arrayList;
    }

    public EnumCardAppType getCardAppType() throws BusinessException {
        byte[] bArr = new byte[7];
        bArr[1] = ScriptToolsConst.TagName.CommandMultiple;
        bArr[4] = 2;
        bArr[5] = Constants.TagName.CARD_APP_ACTIVATION_STATUS;
        bArr[6] = 1;
        if (this.fmLog == null) {
            this.fmLog = LogFactory.getInstance().getLog();
        }
        try {
            byte[] transceive = this.apduHandler.transceive(bArr);
            if (!FM_Bytes.isEnd9000(transceive)) {
                if (this.fmLog != null) {
                    FMLog fMLog = this.fmLog;
                    String str = this.logTag;
                    fMLog.warn(str, "获取卡的类型时，APDU指令执行异常:" + FM_Bytes.bytesToHexString(transceive));
                }
                throw new BusinessException("获取卡的类型时，读取15文件失败", BusinessException.ErrorMessage.local_business_execute_fail);
            } else if (transceive[transceive.length - 4] < 70 || transceive[transceive.length - 4] > 79) {
                return EnumCardAppType.CARD_APP_TYPE_SH;
            } else {
                return EnumCardAppType.CARD_APP_TYPE_SH_TOUR;
            }
        } catch (FMScriptHandleException e) {
            if (this.fmLog != null) {
                FMLog fMLog2 = this.fmLog;
                String str2 = this.logTag;
                fMLog2.warn(str2, "Apdu指令执行出现异常" + Util4Java.getExceptionInfo(e));
            }
            if (this.apduHandler != null) {
                this.apduHandler.close();
            }
            throw new BusinessException("Apdu指令执行出现异常", BusinessException.ErrorMessage.local_business_execute_fail);
        }
    }
}
