package cn.com.fmsh.communication.message.core;

import java.util.ArrayList;
import java.util.List;

class MessageDefine {
    private String desc;
    private int messageCode;
    List<MessageTagDefine> messageTagDefines = new ArrayList();
    private String retCode;

    public void setMessageCode(int i) {
        this.messageCode = i;
    }

    public void setDesc(String str) {
        this.desc = str;
    }

    public void addMessageData(MessageTagDefine messageTagDefine) {
        this.messageTagDefines.add(messageTagDefine);
    }

    public int getMessageCode() {
        return this.messageCode;
    }

    public String getDesc() {
        return this.desc;
    }

    public MessageTagDefine[] getMessageTagDefines() {
        return (MessageTagDefine[]) this.messageTagDefines.toArray(new MessageTagDefine[0]);
    }

    public String getRetCode() {
        return this.retCode;
    }

    public void setRetCode(String str) {
        this.retCode = str;
    }
}
