package com.ximalaya.ting.android.player;

import com.taobao.weex.el.parse.Operators;
import java.nio.ByteBuffer;

public class BufferItem {
    private static final String d = "dl_mp3";

    /* renamed from: a  reason: collision with root package name */
    public ByteBuffer f2269a;
    public boolean b;
    public int c;
    private int e = -1;
    private boolean f;
    private int g;

    public BufferItem() {
        Logger.a(d, (Object) "======================BufferItem Constructor()");
        this.f = false;
        this.g = 0;
        this.b = false;
    }

    public void a(ByteBuffer byteBuffer) {
        if (byteBuffer.hasArray()) {
            this.f2269a = ByteBuffer.wrap(byteBuffer.array());
            this.g = byteBuffer.array().length;
            Logger.a(d, (Object) "======================BufferItem setBuffer0(" + this.g + Operators.BRACKET_END_STR);
        }
    }

    public void a(byte[] bArr) {
        if (bArr != null) {
            this.f2269a = ByteBuffer.wrap(bArr);
            this.g = bArr.length;
            Logger.a(d, (Object) "======================BufferItem setBuffer1(" + this.g + Operators.BRACKET_END_STR);
        }
    }

    public ByteBuffer a() {
        if (this.f2269a.hasArray()) {
            return ByteBuffer.wrap(this.f2269a.array());
        }
        return ByteBuffer.allocate(0);
    }

    public boolean b() {
        return this.f;
    }

    public int c() {
        return this.g;
    }

    public void d() {
        this.f = true;
    }

    public int e() {
        return this.e;
    }

    public void a(int i) {
        this.e = i;
    }
}
