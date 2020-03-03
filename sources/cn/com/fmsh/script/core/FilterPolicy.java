package cn.com.fmsh.script.core;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class FilterPolicy {
    private byte cla;
    private List<byte[]> dataList = new ArrayList();
    private byte ins;

    public byte getCla() {
        return this.cla;
    }

    public void setCla(byte b) {
        this.cla = b;
    }

    public byte getIns() {
        return this.ins;
    }

    public void setIns(byte b) {
        this.ins = b;
    }

    public byte[][] getFilterDatas() {
        return (byte[][]) this.dataList.toArray((byte[][]) Array.newInstance(byte.class, new int[]{0, 0}));
    }

    public void addFilterData(byte[] bArr) {
        this.dataList.add(bArr);
    }
}
