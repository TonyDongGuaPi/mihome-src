package com.ximalaya.ting.android.opensdk.model.xdcs;

import java.util.ArrayList;
import java.util.List;

public class XdcsRecord {

    /* renamed from: a  reason: collision with root package name */
    public List<XdcsEvent> f2140a = new ArrayList();
    public long b;
    public String c;
    public String d;

    public static XdcsRecord a(List<XdcsEvent> list) {
        XdcsRecord xdcsRecord = new XdcsRecord();
        if (list != null) {
            xdcsRecord.f2140a = new ArrayList(list);
        }
        xdcsRecord.b = System.currentTimeMillis();
        return xdcsRecord;
    }
}
