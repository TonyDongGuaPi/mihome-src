package cn.com.fmsh.tsm.business.bean;

import cn.com.fmsh.communication.message.ITag;
import cn.com.fmsh.communication.message.exception.FMCommunicationMessageException;
import cn.com.fmsh.util.log.FMLog;
import cn.com.fmsh.util.log.LogFactory;

public class PreDepositInfo {
    private int blance;
    private int status;
    private int total;
    private int type;

    public int getTotal() {
        return this.total;
    }

    public void setTotal(int i) {
        this.total = i;
    }

    public int getBlance() {
        return this.blance;
    }

    public void setBlance(int i) {
        this.blance = i;
    }

    public int getStatus() {
        return this.status;
    }

    public void setStatus(int i) {
        this.status = i;
    }

    public int getType() {
        return this.type;
    }

    public void setType(int i) {
        this.type = i;
    }

    public static PreDepositInfo fromTag(ITag iTag) throws FMCommunicationMessageException {
        FMLog log = LogFactory.getInstance().getLog();
        if (iTag == null) {
            if (log != null) {
                log.warn(PreDepositInfo.class.getName(), "Tag生成额度信息时，Tag对象为空");
            }
            return null;
        }
        ITag[] itemTags = iTag.getItemTags();
        if (itemTags == null || itemTags.length < 1) {
            if (log != null) {
                log.warn(BusinessOrder.class.getName(), "Tag生成额度信息时，Tag子项为空");
            }
            return null;
        }
        PreDepositInfo preDepositInfo = new PreDepositInfo();
        for (ITag iTag2 : itemTags) {
            byte id = iTag2.getId();
            if (id == -116) {
                preDepositInfo.setStatus(iTag2.getIntVal());
            } else if (id != -107) {
                switch (id) {
                    case 90:
                        preDepositInfo.setTotal(iTag2.getIntVal());
                        break;
                    case 91:
                        preDepositInfo.setBlance(iTag2.getIntVal());
                        break;
                }
            } else {
                preDepositInfo.setType(iTag2.getIntVal());
            }
        }
        return preDepositInfo;
    }
}
