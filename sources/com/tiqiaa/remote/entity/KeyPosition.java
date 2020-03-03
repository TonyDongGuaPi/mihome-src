package com.tiqiaa.remote.entity;

import com.imi.fastjson.annotation.JSONField;
import com.tiqiaa.common.IJsonable;

public class KeyPosition implements IJsonable, Cloneable {
    @JSONField(name = "column")
    protected int column;
    @JSONField(name = "icol")
    protected int icol;
    @JSONField(name = "id")
    protected long id;
    @JSONField(name = "irow")
    protected int irow;
    @JSONField(name = "key_id")
    private long key_id;
    @JSONField(name = "key_size")
    protected int key_size;
    @JSONField(name = "orientation")
    protected int orientation;
    @JSONField(name = "row")
    protected int row;
    @JSONField(name = "screen_num")
    protected int screen_num;

    public int getRow() {
        return this.row;
    }

    public void setRow(int i) {
        this.row = i;
    }

    public int getIrow() {
        return this.irow;
    }

    public void setIrow(int i) {
        this.irow = i;
    }

    public int getIcol() {
        return this.icol;
    }

    public void setIcol(int i) {
        this.icol = i;
    }

    public int getScreen_num() {
        return this.screen_num;
    }

    public void setScreen_num(int i) {
        this.screen_num = i;
    }

    public int getOrientation() {
        return this.orientation;
    }

    public void setOrientation(int i) {
        this.orientation = i;
    }

    public int getKey_size() {
        return this.key_size;
    }

    public void setKey_size(int i) {
        this.key_size = i;
    }

    public int getColumn() {
        return this.column;
    }

    public void setColumn(int i) {
        this.column = i;
    }

    public long getId() {
        return this.id;
    }

    public void setId(long j) {
        this.id = j;
    }

    public long getKey_id() {
        return this.key_id;
    }

    public void setKey_id(long j) {
        this.key_id = j;
    }

    public KeyPosition clone() {
        KeyPosition keyPosition = new KeyPosition();
        keyPosition.setColumn(this.column);
        keyPosition.setIcol(this.icol);
        keyPosition.setId(this.id);
        keyPosition.setIrow(this.irow);
        keyPosition.setKey_id(this.key_id);
        keyPosition.setKey_size(this.key_size);
        keyPosition.setOrientation(this.orientation);
        keyPosition.setRow(this.row);
        keyPosition.setScreen_num(this.screen_num);
        return keyPosition;
    }
}
