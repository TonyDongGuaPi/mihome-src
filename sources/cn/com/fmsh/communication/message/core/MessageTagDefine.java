package cn.com.fmsh.communication.message.core;

public class MessageTagDefine {
    private int exist;
    private int multiple;
    private int order;
    private byte tag;

    public byte getTag() {
        return this.tag;
    }

    public void setTag(byte b) {
        this.tag = b;
    }

    public int getOrder() {
        return this.order;
    }

    public void setOrder(int i) {
        this.order = i;
    }

    public int getMultiple() {
        return this.multiple;
    }

    public void setMultiple(int i) {
        this.multiple = i;
    }

    public int getExist() {
        return this.exist;
    }

    public void setExist(int i) {
        this.exist = i;
    }
}
