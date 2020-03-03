package com.xiaomi.smarthome.fastvideo;

import android.content.Context;
import com.xiaomi.smarthome.fastvideo.Param;

public class CorrectinglensDistortionFilter extends GlslFilter {

    /* renamed from: a  reason: collision with root package name */
    Param.VarFloatParam f15880a = new Param.VarFloatParamLens("correctionRadius", 1.1f, 0.8f, 2.5f);
    Param.VarFloatParam b;
    Param.FloatParam c;
    Param.FloatParam d;
    private final String o = "precision mediump float;\nvarying vec2 textureCoordinate;\nuniform sampler2D inputImageTexture;\nuniform float correctionRadius;\nuniform float zoom;\n uniform float osdx;\n uniform float osdy;\n void main()\n{\nif(textureCoordinate.x<osdx && textureCoordinate.y<osdy){\ngl_FragColor = texture2D(inputImageTexture, textureCoordinate);\nreturn;\n}\nhighp vec2 newCoor = textureCoordinate-vec2(0.5,0.5);\nhighp float dis = length(newCoor);\nhighp float r = dis / correctionRadius;\nhighp float theta = zoom*1.0;\n if(r>0.0){\ntheta = atan(r) / r;\n}\nhighp vec2 newTextureCoor = vec2(0.5,0.5)+newCoor*theta;\ngl_FragColor = texture2D(inputImageTexture, newTextureCoor);\n}";

    public String a() {
        return "precision mediump float;\nvarying vec2 textureCoordinate;\nuniform sampler2D inputImageTexture;\nuniform float correctionRadius;\nuniform float zoom;\n uniform float osdx;\n uniform float osdy;\n void main()\n{\nif(textureCoordinate.x<osdx && textureCoordinate.y<osdy){\ngl_FragColor = texture2D(inputImageTexture, textureCoordinate);\nreturn;\n}\nhighp vec2 newCoor = textureCoordinate-vec2(0.5,0.5);\nhighp float dis = length(newCoor);\nhighp float r = dis / correctionRadius;\nhighp float theta = zoom*1.0;\n if(r>0.0){\ntheta = atan(r) / r;\n}\nhighp vec2 newTextureCoor = vec2(0.5,0.5)+newCoor*theta;\ngl_FragColor = texture2D(inputImageTexture, newTextureCoor);\n}";
    }

    public CorrectinglensDistortionFilter(Context context) {
        super(context);
        a((Param) this.f15880a);
        this.b = new Param.VarFloatParam("zoom", 1.0f, 0.5f, 2.0f);
        a((Param) this.b);
        this.c = new Param.FloatParam("osdx", 0.0f);
        a((Param) this.c);
        this.d = new Param.FloatParam("osdy", 0.0f);
        a((Param) this.d);
    }

    public void a(float f) {
        this.f15880a.a(f);
    }

    public void b(float f) {
        this.b.a(f);
    }

    public void a(float f, float f2) {
        this.c.a(f);
        this.d.a(f2);
    }

    /* access modifiers changed from: protected */
    public void b() {
        super.b();
    }

    /* access modifiers changed from: protected */
    public void c() {
        super.c();
    }
}
