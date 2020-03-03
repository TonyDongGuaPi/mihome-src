package cn.com.fmsh.tsm.business.bean;

import cn.com.fmsh.communication.message.ITag;
import cn.com.fmsh.communication.message.exception.FMCommunicationMessageException;
import cn.com.fmsh.util.log.FMLog;
import cn.com.fmsh.util.log.LogFactory;

public class PromotionMessage {
    private String code;
    private String description;
    private String title;

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String str) {
        this.title = str;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String str) {
        this.description = str;
    }

    public String getCode() {
        return this.code;
    }

    public void setCode(String str) {
        this.code = str;
    }

    public static PromotionMessage fromTag(ITag iTag) throws FMCommunicationMessageException {
        FMLog log = LogFactory.getInstance().getLog();
        if (iTag == null) {
            if (log != null) {
                log.warn(BusinessOrder.class.getName(), "转换Tag对象用户促销信息时，Tag对象为空");
            }
            return null;
        }
        ITag[] itemTags = iTag.getItemTags();
        if (itemTags == null || itemTags.length < 1) {
            if (log != null) {
                log.warn(BusinessOrder.class.getName(), " 转换Tag对象到用户促销信息时，Tag子对象为空");
            }
            return null;
        }
        PromotionMessage promotionMessage = new PromotionMessage();
        for (ITag iTag2 : itemTags) {
            byte id = iTag2.getId();
            if (id == -56) {
                promotionMessage.setCode(iTag2.getStringVal());
            } else if (id == 50) {
                promotionMessage.setTitle(iTag2.getStringVal());
            } else if (id == 52) {
                promotionMessage.setDescription(iTag2.getStringVal());
            }
        }
        return promotionMessage;
    }
}
