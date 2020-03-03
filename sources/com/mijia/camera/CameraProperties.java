package com.mijia.camera;

import com.mijia.model.property.CameraPropertyBase;
import java.util.ArrayList;
import java.util.List;

public class CameraProperties extends CameraPropertyBase {

    /* renamed from: a  reason: collision with root package name */
    public static final String f7888a = "mini_level";
    public static final String b = "improve_program";
    public static final String[] c = {"light", CameraPropertyBase.h, CameraPropertyBase.i, "watermark", CameraPropertyBase.k, CameraPropertyBase.l, CameraPropertyBase.m, CameraPropertyBase.n, f7888a, CameraPropertyBase.o, CameraPropertyBase.p};
    public static final String[] d = {"light", CameraPropertyBase.h, CameraPropertyBase.i, "watermark", CameraPropertyBase.k, CameraPropertyBase.l, CameraPropertyBase.n, CameraPropertyBase.u, CameraPropertyBase.p};

    public CameraProperties(MijiaCameraDevice mijiaCameraDevice) {
        super(mijiaCameraDevice);
    }

    public List<String> a() {
        ArrayList arrayList = new ArrayList();
        for (String str : c) {
            arrayList.add("prop." + str);
        }
        return arrayList;
    }

    public List<String> b() {
        ArrayList arrayList = new ArrayList();
        for (String str : d) {
            arrayList.add("prop." + str);
        }
        return arrayList;
    }
}
