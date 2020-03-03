package com.xiaomi.ai;

import com.xiaomi.ai.SpeechEngine;

public class SpeechError {
    public static final int A = -11;
    public static final int B = -12;
    public static final int C = -13;
    public static final int D = -14;
    public static final int E = -15;
    public static final int F = -16;
    public static final int G = -17;
    public static final int H = -18;
    public static final int I = -19;
    public static final int J = -20;
    public static final int K = -21;
    public static final int L = -22;
    public static final int M = -23;
    public static final int N = 101;
    public static final int O = 102;
    public static final int P = 111;
    public static final int Q = 121;
    public static final int R = 131;

    /* renamed from: a  reason: collision with root package name */
    public static final int f9905a = 1;
    public static final int b = 2;
    public static final int c = 1;
    public static final int d = 2;
    public static final int e = 3;
    public static final int f = 4;
    public static final int g = 5;
    public static final int h = 6;
    public static final int i = 7;
    public static final int j = 8;
    public static final int k = 10;
    public static final int l = 11;
    public static final int m = 12;
    public static final int n = 13;
    public static final int o = 14;
    public static final int p = 15;
    public static final int q = -1;
    public static final int r = -2;
    public static final int s = -3;
    public static final int t = -4;
    public static final int u = -5;
    public static final int v = -6;
    public static final int w = -7;
    public static final int x = -8;
    public static final int y = -9;
    public static final int z = -10;
    SpeechEngine.ProcessStage S;
    int T;
    int U;
    String V;
    String W;

    public SpeechError(SpeechEngine.ProcessStage processStage) {
        this.S = processStage;
    }

    public int a() {
        return this.T;
    }

    public int b() {
        return this.U;
    }

    public String c() {
        return this.V;
    }

    public String toString() {
        return "stage = " + this.S + " errType = " + this.T + " errCode = " + this.U + " errMsg = " + this.V + " requestId =" + this.W + "  ";
    }
}
