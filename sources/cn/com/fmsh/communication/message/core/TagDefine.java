package cn.com.fmsh.communication.message.core;

import cn.com.fmsh.communication.message.enumerate.ETagType;
import java.util.ArrayList;
import java.util.List;

class TagDefine {
    private String desc;
    private byte id;
    private int length;
    private List<TagItemDefine> tagItems = new ArrayList();
    private ETagType type;

    public void setId(byte b) {
        this.id = b;
    }

    public void setLength(int i) {
        this.length = i;
    }

    public void setType(ETagType eTagType) {
        this.type = eTagType;
    }

    public void setDesc(String str) {
        this.desc = str;
    }

    public void addTagItem(TagItemDefine tagItemDefine) {
        if (tagItemDefine != null) {
            this.tagItems.add(tagItemDefine);
        }
    }

    public byte getId() {
        return this.id;
    }

    public int getLength() {
        return this.length;
    }

    public ETagType getType() {
        return this.type;
    }

    public String getDesc() {
        return this.desc;
    }

    public TagItemDefine[] getTagItemDefines() {
        return (TagItemDefine[]) this.tagItems.toArray(new TagItemDefine[0]);
    }
}
