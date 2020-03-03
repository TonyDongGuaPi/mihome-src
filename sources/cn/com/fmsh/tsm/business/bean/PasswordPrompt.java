package cn.com.fmsh.tsm.business.bean;

import cn.com.fmsh.communication.message.ITag;
import cn.com.fmsh.communication.message.exception.FMCommunicationMessageException;
import cn.com.fmsh.util.log.FMLog;
import cn.com.fmsh.util.log.LogFactory;

public class PasswordPrompt {
    private int count;
    private String email;

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String str) {
        this.email = str;
    }

    public int getCount() {
        return this.count;
    }

    public void setCount(int i) {
        this.count = i;
    }

    public static PasswordPrompt fromTag(ITag iTag) throws FMCommunicationMessageException {
        FMLog log = LogFactory.getInstance().getLog();
        if (iTag == null) {
            if (log != null) {
                log.warn(BusinessOrder.class.getName(), " 转换Tag对象到密码提示信息对象时，Tag对象为空");
            }
            return null;
        }
        ITag[] itemTags = iTag.getItemTags();
        if (itemTags == null || itemTags.length < 1) {
            if (log != null) {
                log.warn(BusinessOrder.class.getName(), " 转换Tag对象到密码提示信息对象时，Tag对象为空");
            }
            return null;
        }
        PasswordPrompt passwordPrompt = new PasswordPrompt();
        for (ITag iTag2 : itemTags) {
            byte id = iTag2.getId();
            if (id == 4) {
                passwordPrompt.setEmail(iTag2.getStringVal());
            } else if (id == 36) {
                passwordPrompt.setCount(iTag2.getIntVal());
            }
        }
        return passwordPrompt;
    }
}
