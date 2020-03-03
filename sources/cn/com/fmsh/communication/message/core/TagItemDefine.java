package cn.com.fmsh.communication.message.core;

class TagItemDefine {
    private String desc;
    private int exist;
    private int multiple;
    private int order;
    private byte tag;

    TagItemDefine() {
    }

    public void setTag(byte b) {
        this.tag = b;
    }

    public void setMultiple(int i) {
        this.multiple = i;
    }

    public void setExist(int i) {
        this.exist = i;
    }

    public void setOrder(int i) {
        this.order = i;
    }

    public void setDesc(String str) {
        this.desc = str;
    }

    public byte getTag() {
        return this.tag;
    }

    public int getMultiple() {
        return this.multiple;
    }

    public int getExist() {
        return this.exist;
    }

    public int getOrder() {
        return this.order;
    }

    public String getDesc() {
        return this.desc;
    }
}
