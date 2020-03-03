package com.xiaomi.zxing.aztec.encoder;

import com.xiaomi.zxing.common.BitArray;
import java.util.LinkedList;

final class State {

    /* renamed from: a  reason: collision with root package name */
    static final State f1643a = new State(Token.f1644a, 0, 0, 0);
    private final int b;
    private final Token c;
    private final int d;
    private final int e;

    private State(Token token, int i, int i2, int i3) {
        this.c = token;
        this.b = i;
        this.d = i2;
        this.e = i3;
    }

    /* access modifiers changed from: package-private */
    public int a() {
        return this.b;
    }

    /* access modifiers changed from: package-private */
    public Token b() {
        return this.c;
    }

    /* access modifiers changed from: package-private */
    public int c() {
        return this.d;
    }

    /* access modifiers changed from: package-private */
    public int d() {
        return this.e;
    }

    /* access modifiers changed from: package-private */
    public State a(int i, int i2) {
        int i3 = this.e;
        Token token = this.c;
        if (i != this.b) {
            int i4 = HighLevelEncoder.g[this.b][i];
            int i5 = 65535 & i4;
            int i6 = i4 >> 16;
            token = token.a(i5, i6);
            i3 += i6;
        }
        int i7 = i == 2 ? 4 : 5;
        return new State(token.a(i2, i7), i, 0, i3 + i7);
    }

    /* access modifiers changed from: package-private */
    public State b(int i, int i2) {
        Token token = this.c;
        int i3 = this.b == 2 ? 4 : 5;
        return new State(token.a(HighLevelEncoder.h[this.b][i], i3).a(i2, 5), this.b, 0, this.e + i3 + 5);
    }

    /* access modifiers changed from: package-private */
    public State a(int i) {
        Token token = this.c;
        int i2 = this.b;
        int i3 = this.e;
        if (this.b == 4 || this.b == 2) {
            int i4 = HighLevelEncoder.g[i2][0];
            int i5 = 65535 & i4;
            int i6 = i4 >> 16;
            token = token.a(i5, i6);
            i3 += i6;
            i2 = 0;
        }
        State state = new State(token, i2, this.d + 1, i3 + ((this.d == 0 || this.d == 31) ? 18 : this.d == 62 ? 9 : 8));
        return state.d == 2078 ? state.b(i + 1) : state;
    }

    /* access modifiers changed from: package-private */
    public State b(int i) {
        if (this.d == 0) {
            return this;
        }
        return new State(this.c.b(i - this.d, this.d), this.b, 0, this.e);
    }

    /* access modifiers changed from: package-private */
    public boolean a(State state) {
        int i = this.e + (HighLevelEncoder.g[this.b][state.b] >> 16);
        if (state.d > 0 && (this.d == 0 || this.d > state.d)) {
            i += 10;
        }
        return i <= state.e;
    }

    /* access modifiers changed from: package-private */
    public BitArray a(byte[] bArr) {
        LinkedList<Token> linkedList = new LinkedList<>();
        for (Token token = b(bArr.length).c; token != null; token = token.a()) {
            linkedList.addFirst(token);
        }
        BitArray bitArray = new BitArray();
        for (Token a2 : linkedList) {
            a2.a(bitArray, bArr);
        }
        return bitArray;
    }

    public String toString() {
        return String.format("%s bits=%d bytes=%d", new Object[]{HighLevelEncoder.f1641a[this.b], Integer.valueOf(this.e), Integer.valueOf(this.d)});
    }
}
