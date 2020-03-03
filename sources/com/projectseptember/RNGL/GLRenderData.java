package com.projectseptember.RNGL;

import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import java.util.List;
import java.util.Map;

public class GLRenderData {

    /* renamed from: a  reason: collision with root package name */
    final GLShader f8571a;
    final Map<String, Integer> b;
    final Map<String, Float> c;
    final Map<String, IntBuffer> d;
    final Map<String, FloatBuffer> e;
    final Map<String, GLTexture> f;
    final Integer g;
    final Integer h;
    final Integer i;
    final List<GLRenderData> j;
    final List<GLRenderData> k;

    public GLRenderData(GLShader gLShader, Map<String, Integer> map, Map<String, Float> map2, Map<String, IntBuffer> map3, Map<String, FloatBuffer> map4, Map<String, GLTexture> map5, Integer num, Integer num2, Integer num3, List<GLRenderData> list, List<GLRenderData> list2) {
        this.f8571a = gLShader;
        this.b = map;
        this.c = map2;
        this.d = map3;
        this.e = map4;
        this.f = map5;
        this.g = num;
        this.h = num2;
        this.i = num3;
        this.j = list;
        this.k = list2;
    }
}
