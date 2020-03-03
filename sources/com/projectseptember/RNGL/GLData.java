package com.projectseptember.RNGL;

import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.ReadableMap;
import java.util.ArrayList;
import java.util.List;

public class GLData {

    /* renamed from: a  reason: collision with root package name */
    final Integer f8564a;
    final ReadableMap b;
    final Double c;
    final Double d;
    final Double e;
    final Integer f;
    final List<GLData> g;
    final List<GLData> h;

    public GLData(Integer num, ReadableMap readableMap, Double d2, Double d3, Double d4, Integer num2, List<GLData> list, List<GLData> list2) {
        this.f8564a = num;
        this.b = readableMap;
        this.c = d2;
        this.d = d3;
        this.e = d4;
        this.f = num2;
        this.g = list;
        this.h = list2;
    }

    public static List<GLData> a(ReadableArray readableArray) {
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < readableArray.size(); i++) {
            arrayList.add(a(readableArray.getMap(i)));
        }
        return arrayList;
    }

    public static GLData a(ReadableMap readableMap) {
        return new GLData(Integer.valueOf(readableMap.getInt("shader")), readableMap.getMap("uniforms"), Double.valueOf(readableMap.getDouble("width")), Double.valueOf(readableMap.getDouble("height")), Double.valueOf(readableMap.getDouble("pixelRatio")), Integer.valueOf(readableMap.getInt("fboId")), a(readableMap.getArray("contextChildren")), a(readableMap.getArray("children")));
    }
}
