package org.mp4parser.boxes.iso14496.part1.objectdescriptors;

import com.taobao.weex.el.parse.Operators;
import com.tiqiaa.icontrol.util.TiqiaaService;
import com.xiaomi.ai.AsrRequest;
import com.xiaomi.chatbot.speechsdk.record.RecordDevice;
import com.xiaomi.smarthome.framework.plugin.rn.nativemodule.MIOTAudioModule;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import org.mp4parser.tools.Hex;
import org.mp4parser.tools.IsoTypeWriter;

@Descriptor(objectTypeIndication = 64, tags = {5})
public class AudioSpecificConfig extends BaseDescriptor {

    /* renamed from: a  reason: collision with root package name */
    public static Map<Integer, Integer> f3818a = new HashMap();
    public static Map<Integer, String> b = new HashMap();
    public int A;
    public int B;
    public int C;
    public boolean D;
    public boolean E;
    public boolean F;
    public int G;
    public boolean H;
    public int I;
    public int J;
    public int K;
    public int L;
    public int M;
    public int N;
    public int O;
    public int P;
    public int Q;
    public int R;
    public int S;
    public int T;
    public int U;
    public int V;
    public boolean W;
    byte[] X;
    boolean Y = false;
    public ELDSpecificConfig c;
    public int d;
    public int e;
    public int f;
    public int g;
    public int h;
    public int i;
    public int j;
    public boolean k;
    public boolean l;
    public int m = -1;
    public int n;
    public int o;
    public int p;
    public int q;
    public int r;
    public int s;
    public int t = -1;
    public int u = -1;
    public int v = -1;
    public int w;
    public int x;
    public int y;
    public int z;

    static {
        f3818a.put(0, 96000);
        f3818a.put(1, 88200);
        f3818a.put(2, 64000);
        f3818a.put(3, Integer.valueOf(MIOTAudioModule.SAMPLING_RATE));
        f3818a.put(4, 44100);
        f3818a.put(5, Integer.valueOf(AsrRequest.d));
        f3818a.put(6, 24000);
        f3818a.put(7, 22050);
        f3818a.put(8, Integer.valueOf(RecordDevice.PCM_FREQUENCE_16K));
        f3818a.put(9, Integer.valueOf(TiqiaaService.BaseCallBack.ERROR_CODE_NO_NET));
        f3818a.put(10, 11025);
        f3818a.put(11, 8000);
        b.put(1, "AAC main");
        b.put(2, "AAC LC");
        b.put(3, "AAC SSR");
        b.put(4, "AAC LTP");
        b.put(5, "SBR");
        b.put(6, "AAC Scalable");
        b.put(7, "TwinVQ");
        b.put(8, "CELP");
        b.put(9, "HVXC");
        b.put(10, "(reserved)");
        b.put(11, "(reserved)");
        b.put(12, "TTSI");
        b.put(13, "Main synthetic");
        b.put(14, "Wavetable synthesis");
        b.put(15, "General MIDI");
        b.put(16, "Algorithmic Synthesis and Audio FX");
        b.put(17, "ER AAC LC");
        b.put(18, "(reserved)");
        b.put(19, "ER AAC LTP");
        b.put(20, "ER AAC Scalable");
        b.put(21, "ER TwinVQ");
        b.put(22, "ER BSAC");
        b.put(23, "ER AAC LD");
        b.put(24, "ER CELP");
        b.put(25, "ER HVXC");
        b.put(26, "ER HILN");
        b.put(27, "ER Parametric");
        b.put(28, "SSC");
        b.put(29, "PS");
        b.put(30, "MPEG Surround");
        b.put(31, "(escape)");
        b.put(32, "Layer-1");
        b.put(33, "Layer-2");
        b.put(34, "Layer-3");
        b.put(35, "DST");
        b.put(36, "ALS");
        b.put(37, "SLS");
        b.put(38, "SLS non-core");
        b.put(39, "ER AAC ELD");
        b.put(40, "SMR Simple");
        b.put(41, "SMR Main");
    }

    public AudioSpecificConfig() {
        this.Z = 5;
    }

    public void a(ByteBuffer byteBuffer) throws IOException {
        this.Y = true;
        ByteBuffer slice = byteBuffer.slice();
        slice.limit(this.aa);
        byteBuffer.position(byteBuffer.position() + this.aa);
        this.X = new byte[this.aa];
        slice.get(this.X);
        slice.rewind();
        BitReaderBuffer bitReaderBuffer = new BitReaderBuffer(slice);
        int a2 = a(bitReaderBuffer);
        this.d = a2;
        this.e = a2;
        this.f = bitReaderBuffer.a(4);
        if (this.f == 15) {
            this.g = bitReaderBuffer.a(24);
        }
        this.h = bitReaderBuffer.a(4);
        if (this.d == 5 || this.d == 29) {
            this.i = 5;
            this.k = true;
            if (this.d == 29) {
                this.l = true;
            }
            this.m = bitReaderBuffer.a(4);
            if (this.m == 15) {
                this.n = bitReaderBuffer.a(24);
            }
            this.d = a(bitReaderBuffer);
            if (this.d == 22) {
                this.o = bitReaderBuffer.a(4);
            }
        } else {
            this.i = 0;
        }
        switch (this.d) {
            case 1:
            case 2:
            case 3:
            case 4:
            case 6:
            case 7:
            case 17:
            case 19:
            case 20:
            case 21:
            case 22:
            case 23:
                a(this.f, this.h, this.d, bitReaderBuffer);
                break;
            case 8:
                throw new UnsupportedOperationException("can't parse CelpSpecificConfig yet");
            case 9:
                throw new UnsupportedOperationException("can't parse HvxcSpecificConfig yet");
            case 12:
                throw new UnsupportedOperationException("can't parse TTSSpecificConfig yet");
            case 13:
            case 14:
            case 15:
            case 16:
                throw new UnsupportedOperationException("can't parse StructuredAudioSpecificConfig yet");
            case 24:
                throw new UnsupportedOperationException("can't parse ErrorResilientCelpSpecificConfig yet");
            case 25:
                throw new UnsupportedOperationException("can't parse ErrorResilientHvxcSpecificConfig yet");
            case 26:
            case 27:
                b(this.f, this.h, this.d, bitReaderBuffer);
                break;
            case 28:
                throw new UnsupportedOperationException("can't parse SSCSpecificConfig yet");
            case 30:
                this.p = bitReaderBuffer.a(1);
                throw new UnsupportedOperationException("can't parse SpatialSpecificConfig yet");
            case 32:
            case 33:
            case 34:
                throw new UnsupportedOperationException("can't parse MPEG_1_2_SpecificConfig yet");
            case 35:
                throw new UnsupportedOperationException("can't parse DSTSpecificConfig yet");
            case 36:
                this.q = bitReaderBuffer.a(5);
                throw new UnsupportedOperationException("can't parse ALSSpecificConfig yet");
            case 37:
            case 38:
                throw new UnsupportedOperationException("can't parse SLSSpecificConfig yet");
            case 39:
                this.c = new ELDSpecificConfig(this.h, bitReaderBuffer);
                break;
            case 40:
            case 41:
                throw new UnsupportedOperationException("can't parse SymbolicMusicSpecificConfig yet");
        }
        int i2 = this.d;
        if (!(i2 == 17 || i2 == 39)) {
            switch (i2) {
                case 19:
                case 20:
                case 21:
                case 22:
                case 23:
                case 24:
                case 25:
                case 26:
                case 27:
                    break;
            }
        }
        this.r = bitReaderBuffer.a(2);
        if (this.r == 2 || this.r == 3) {
            throw new UnsupportedOperationException("can't parse ErrorProtectionSpecificConfig yet");
        }
        if (this.r == 3) {
            this.s = bitReaderBuffer.a(1);
            if (this.s == 0) {
                throw new RuntimeException("not implemented");
            }
        }
        if (this.i != 5 && bitReaderBuffer.d() >= 16) {
            int a3 = bitReaderBuffer.a(11);
            this.t = a3;
            this.v = a3;
            if (this.t == 695) {
                this.i = a(bitReaderBuffer);
                if (this.i == 5) {
                    this.k = bitReaderBuffer.a();
                    if (this.k) {
                        this.m = bitReaderBuffer.a(4);
                        if (this.m == 15) {
                            this.n = bitReaderBuffer.a(24);
                        }
                        if (bitReaderBuffer.d() >= 12) {
                            int a4 = bitReaderBuffer.a(11);
                            this.t = a4;
                            this.u = a4;
                            if (this.t == 1352) {
                                this.l = bitReaderBuffer.a();
                            }
                        }
                    }
                }
                if (this.i == 22) {
                    this.k = bitReaderBuffer.a();
                    if (this.k) {
                        this.m = bitReaderBuffer.a(4);
                        if (this.m == 15) {
                            this.n = bitReaderBuffer.a(24);
                        }
                    }
                    this.o = bitReaderBuffer.a(4);
                }
            }
        }
    }

    private int m() {
        int i2 = (this.x == 1 ? 16 : 2) + 1;
        if (this.h != 0) {
            if (this.d == 6 || this.d == 20) {
                i2 += 3;
            }
            if (this.z == 1) {
                if (this.d == 22) {
                    i2 = i2 + 5 + 11;
                }
                if (this.d == 17 || this.d == 19 || this.d == 20 || this.d == 23) {
                    i2 = i2 + 1 + 1 + 1;
                }
                i2++;
                if (this.G == 1) {
                    throw new RuntimeException("Not implemented");
                }
            }
            return i2;
        }
        throw new UnsupportedOperationException("can't parse program_config_element yet");
    }

    /* access modifiers changed from: protected */
    public int a() {
        int i2;
        int i3 = (this.e > 30 ? 11 : 5) + 4;
        if (this.f == 15) {
            i3 += 24;
        }
        int i4 = i3 + 4;
        if (this.d == 5 || this.d == 29) {
            i4 += 4;
            if (this.m == 15) {
                i4 += 24;
            }
        }
        if (this.d == 22) {
            i4 += 4;
        }
        if (this.H) {
            i4 += m();
        }
        if (this.v >= 0) {
            i2 += 11;
            if (this.v == 695) {
                int i5 = i2 + 5;
                if (this.i > 30) {
                    i5 += 6;
                }
                if (this.i == 5) {
                    i2++;
                    if (this.k) {
                        i2 += 4;
                        if (this.m == 15) {
                            i2 += 24;
                        }
                        if (this.u >= 0) {
                            i2 += 11;
                            if (this.u == 1352) {
                                i2++;
                            }
                        }
                    }
                }
                if (this.i == 22) {
                    int i6 = i2 + 1;
                    if (this.k) {
                        i6 += 4;
                        if (this.m == 15) {
                            i6 += 24;
                        }
                    }
                    i2 = i6 + 4;
                }
            }
        }
        double d2 = (double) i2;
        Double.isNaN(d2);
        return (int) Math.ceil(d2 / 8.0d);
    }

    public ByteBuffer b() {
        ByteBuffer allocate = ByteBuffer.allocate(l());
        IsoTypeWriter.d(allocate, this.Z);
        a(allocate, a());
        allocate.put(c());
        return (ByteBuffer) allocate.rewind();
    }

    /* access modifiers changed from: protected */
    public ByteBuffer c() {
        ByteBuffer wrap = ByteBuffer.wrap(new byte[a()]);
        BitWriterBuffer bitWriterBuffer = new BitWriterBuffer(wrap);
        a(this.e, bitWriterBuffer);
        bitWriterBuffer.a(this.f, 4);
        if (this.f == 15) {
            bitWriterBuffer.a(this.g, 24);
        }
        bitWriterBuffer.a(this.h, 4);
        if (this.d == 5 || this.d == 29) {
            this.i = 5;
            this.k = true;
            if (this.d == 29) {
                this.l = true;
            }
            bitWriterBuffer.a(this.m, 4);
            if (this.m == 15) {
                bitWriterBuffer.a(this.n, 24);
            }
            a(this.d, bitWriterBuffer);
            if (this.d == 22) {
                bitWriterBuffer.a(this.o, 4);
            }
        }
        switch (this.d) {
            case 1:
            case 2:
            case 3:
            case 4:
            case 6:
            case 7:
            case 17:
            case 19:
            case 20:
            case 21:
            case 22:
            case 23:
                a(bitWriterBuffer);
                break;
            case 8:
                throw new UnsupportedOperationException("can't write CelpSpecificConfig yet");
            case 9:
                throw new UnsupportedOperationException("can't write HvxcSpecificConfig yet");
            case 12:
                throw new UnsupportedOperationException("can't write TTSSpecificConfig yet");
            case 13:
            case 14:
            case 15:
            case 16:
                throw new UnsupportedOperationException("can't write StructuredAudioSpecificConfig yet");
            case 24:
                throw new UnsupportedOperationException("can't write ErrorResilientCelpSpecificConfig yet");
            case 25:
                throw new UnsupportedOperationException("can't write ErrorResilientHvxcSpecificConfig yet");
            case 26:
            case 27:
                throw new UnsupportedOperationException("can't write parseParametricSpecificConfig yet");
            case 28:
                throw new UnsupportedOperationException("can't write SSCSpecificConfig yet");
            case 30:
                bitWriterBuffer.a(this.p, 1);
                throw new UnsupportedOperationException("can't write SpatialSpecificConfig yet");
            case 32:
            case 33:
            case 34:
                throw new UnsupportedOperationException("can't write MPEG_1_2_SpecificConfig yet");
            case 35:
                throw new UnsupportedOperationException("can't write DSTSpecificConfig yet");
            case 36:
                bitWriterBuffer.a(this.q, 5);
                throw new UnsupportedOperationException("can't write ALSSpecificConfig yet");
            case 37:
            case 38:
                throw new UnsupportedOperationException("can't write SLSSpecificConfig yet");
            case 39:
                throw new UnsupportedOperationException("can't write ELDSpecificConfig yet");
            case 40:
            case 41:
                throw new UnsupportedOperationException("can't parse SymbolicMusicSpecificConfig yet");
        }
        int i2 = this.d;
        if (!(i2 == 17 || i2 == 39)) {
            switch (i2) {
                case 19:
                case 20:
                case 21:
                case 22:
                case 23:
                case 24:
                case 25:
                case 26:
                case 27:
                    break;
            }
        }
        bitWriterBuffer.a(this.r, 2);
        if (this.r == 2 || this.r == 3) {
            throw new UnsupportedOperationException("can't parse ErrorProtectionSpecificConfig yet");
        }
        if (this.r == 3) {
            bitWriterBuffer.a(this.s, 1);
            if (this.s == 0) {
                throw new RuntimeException("not implemented");
            }
        }
        if (this.v >= 0) {
            bitWriterBuffer.a(this.v, 11);
            if (this.v == 695) {
                a(this.i, bitWriterBuffer);
                if (this.i == 5) {
                    bitWriterBuffer.a(this.k);
                    if (this.k) {
                        bitWriterBuffer.a(this.m, 4);
                        if (this.m == 15) {
                            bitWriterBuffer.a(this.n, 24);
                        }
                        if (this.u >= 0) {
                            bitWriterBuffer.a(this.u, 11);
                            if (this.t == 1352) {
                                bitWriterBuffer.a(this.l);
                            }
                        }
                    }
                }
                if (this.i == 22) {
                    bitWriterBuffer.a(this.k);
                    if (this.k) {
                        bitWriterBuffer.a(this.m, 4);
                        if (this.m == 15) {
                            bitWriterBuffer.a(this.n, 24);
                        }
                    }
                    bitWriterBuffer.a(this.o, 4);
                }
            }
        }
        return (ByteBuffer) wrap.rewind();
    }

    private void a(int i2, BitWriterBuffer bitWriterBuffer) {
        if (i2 >= 32) {
            bitWriterBuffer.a(31, 5);
            bitWriterBuffer.a(i2 - 32, 6);
            return;
        }
        bitWriterBuffer.a(i2, 5);
    }

    private int a(BitReaderBuffer bitReaderBuffer) throws IOException {
        int a2 = bitReaderBuffer.a(5);
        return a2 == 31 ? bitReaderBuffer.a(6) + 32 : a2;
    }

    private void a(int i2, int i3, int i4, BitReaderBuffer bitReaderBuffer) throws IOException {
        this.w = bitReaderBuffer.a(1);
        this.x = bitReaderBuffer.a(1);
        if (this.x == 1) {
            this.y = bitReaderBuffer.a(14);
        }
        this.z = bitReaderBuffer.a(1);
        if (i3 != 0) {
            if (i4 == 6 || i4 == 20) {
                this.A = bitReaderBuffer.a(3);
            }
            if (this.z == 1) {
                if (i4 == 22) {
                    this.B = bitReaderBuffer.a(5);
                    this.C = bitReaderBuffer.a(11);
                }
                if (i4 == 17 || i4 == 19 || i4 == 20 || i4 == 23) {
                    this.D = bitReaderBuffer.a();
                    this.E = bitReaderBuffer.a();
                    this.F = bitReaderBuffer.a();
                }
                this.G = bitReaderBuffer.a(1);
                if (this.G == 1) {
                    throw new RuntimeException("not yet implemented");
                }
            }
            this.H = true;
            return;
        }
        throw new UnsupportedOperationException("can't parse program_config_element yet");
    }

    private void a(BitWriterBuffer bitWriterBuffer) {
        bitWriterBuffer.a(this.w, 1);
        bitWriterBuffer.a(this.x, 1);
        if (this.x == 1) {
            bitWriterBuffer.a(this.y, 14);
        }
        bitWriterBuffer.a(this.z, 1);
        if (this.h != 0) {
            if (this.d == 6 || this.d == 20) {
                bitWriterBuffer.a(this.A, 3);
            }
            if (this.z == 1) {
                if (this.d == 22) {
                    bitWriterBuffer.a(this.B, 5);
                    bitWriterBuffer.a(this.C, 11);
                }
                if (this.d == 17 || this.d == 19 || this.d == 20 || this.d == 23) {
                    bitWriterBuffer.a(this.D);
                    bitWriterBuffer.a(this.E);
                    bitWriterBuffer.a(this.F);
                }
                bitWriterBuffer.a(this.G, 1);
                if (this.G == 1) {
                    throw new RuntimeException("not yet implemented");
                }
                return;
            }
            return;
        }
        throw new UnsupportedOperationException("can't parse program_config_element yet");
    }

    private void b(int i2, int i3, int i4, BitReaderBuffer bitReaderBuffer) throws IOException {
        this.I = bitReaderBuffer.a(1);
        if (this.I == 1) {
            c(i2, i3, i4, bitReaderBuffer);
        } else {
            f(i2, i3, i4, bitReaderBuffer);
        }
    }

    private void c(int i2, int i3, int i4, BitReaderBuffer bitReaderBuffer) throws IOException {
        this.J = bitReaderBuffer.a(2);
        if (this.J != 1) {
            d(i2, i3, i4, bitReaderBuffer);
        }
        if (this.J != 0) {
            e(i2, i3, i4, bitReaderBuffer);
        }
        this.K = bitReaderBuffer.a(1);
        this.W = true;
    }

    private void d(int i2, int i3, int i4, BitReaderBuffer bitReaderBuffer) throws IOException {
        this.L = bitReaderBuffer.a(1);
        this.M = bitReaderBuffer.a(2);
        this.N = bitReaderBuffer.a(1);
        if (this.N == 1) {
            this.O = bitReaderBuffer.a(1);
        }
    }

    private void e(int i2, int i3, int i4, BitReaderBuffer bitReaderBuffer) throws IOException {
        this.P = bitReaderBuffer.a(1);
        this.Q = bitReaderBuffer.a(8);
        this.R = bitReaderBuffer.a(4);
        this.S = bitReaderBuffer.a(12);
        this.T = bitReaderBuffer.a(2);
    }

    private void f(int i2, int i3, int i4, BitReaderBuffer bitReaderBuffer) throws IOException {
        this.U = bitReaderBuffer.a(1);
        if (this.U == 1) {
            this.V = bitReaderBuffer.a(2);
        }
    }

    public byte[] d() {
        return c().array();
    }

    public int e() {
        return this.d;
    }

    public void a(int i2) {
        this.d = i2;
    }

    public void b(int i2) {
        this.e = i2;
    }

    public int f() {
        return this.i;
    }

    public void c(int i2) {
        this.f = i2;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("AudioSpecificConfig");
        sb.append("{configBytes=");
        sb.append(Hex.a(this.X));
        sb.append(", audioObjectType=");
        sb.append(this.d);
        sb.append(" (");
        sb.append(b.get(Integer.valueOf(this.d)));
        sb.append(Operators.BRACKET_END_STR);
        sb.append(", samplingFrequencyIndex=");
        sb.append(this.f);
        sb.append(" (");
        sb.append(f3818a.get(Integer.valueOf(this.f)));
        sb.append(Operators.BRACKET_END_STR);
        sb.append(", samplingFrequency=");
        sb.append(this.g);
        sb.append(", channelConfiguration=");
        sb.append(this.h);
        if (this.i > 0) {
            sb.append(", extensionAudioObjectType=");
            sb.append(this.i);
            sb.append(" (");
            sb.append(b.get(Integer.valueOf(this.i)));
            sb.append(Operators.BRACKET_END_STR);
            sb.append(", sbrPresentFlag=");
            sb.append(this.k);
            sb.append(", psPresentFlag=");
            sb.append(this.l);
            sb.append(", extensionSamplingFrequencyIndex=");
            sb.append(this.m);
            sb.append(" (");
            sb.append(f3818a.get(Integer.valueOf(this.m)));
            sb.append(Operators.BRACKET_END_STR);
            sb.append(", extensionSamplingFrequency=");
            sb.append(this.n);
            sb.append(", extensionChannelConfiguration=");
            sb.append(this.o);
        }
        sb.append(", syncExtensionType=");
        sb.append(this.t);
        if (this.H) {
            sb.append(", frameLengthFlag=");
            sb.append(this.w);
            sb.append(", dependsOnCoreCoder=");
            sb.append(this.x);
            sb.append(", coreCoderDelay=");
            sb.append(this.y);
            sb.append(", extensionFlag=");
            sb.append(this.z);
            sb.append(", layerNr=");
            sb.append(this.A);
            sb.append(", numOfSubFrame=");
            sb.append(this.B);
            sb.append(", layer_length=");
            sb.append(this.C);
            sb.append(", aacSectionDataResilienceFlag=");
            sb.append(this.D);
            sb.append(", aacScalefactorDataResilienceFlag=");
            sb.append(this.E);
            sb.append(", aacSpectralDataResilienceFlag=");
            sb.append(this.F);
            sb.append(", extensionFlag3=");
            sb.append(this.G);
        }
        if (this.W) {
            sb.append(", isBaseLayer=");
            sb.append(this.I);
            sb.append(", paraMode=");
            sb.append(this.J);
            sb.append(", paraExtensionFlag=");
            sb.append(this.K);
            sb.append(", hvxcVarMode=");
            sb.append(this.L);
            sb.append(", hvxcRateMode=");
            sb.append(this.M);
            sb.append(", erHvxcExtensionFlag=");
            sb.append(this.N);
            sb.append(", var_ScalableFlag=");
            sb.append(this.O);
            sb.append(", hilnQuantMode=");
            sb.append(this.P);
            sb.append(", hilnMaxNumLine=");
            sb.append(this.Q);
            sb.append(", hilnSampleRateCode=");
            sb.append(this.R);
            sb.append(", hilnFrameLength=");
            sb.append(this.S);
            sb.append(", hilnContMode=");
            sb.append(this.T);
            sb.append(", hilnEnhaLayer=");
            sb.append(this.U);
            sb.append(", hilnEnhaQuantMode=");
            sb.append(this.V);
        }
        sb.append(Operators.BLOCK_END);
        return sb.toString();
    }

    public int g() {
        return this.f == 15 ? this.g : f3818a.get(Integer.valueOf(this.f)).intValue();
    }

    public void d(int i2) {
        this.g = i2;
    }

    public int h() {
        return this.m == 15 ? this.n : f3818a.get(Integer.valueOf(this.m)).intValue();
    }

    public int i() {
        return this.h;
    }

    public void e(int i2) {
        this.h = i2;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        AudioSpecificConfig audioSpecificConfig = (AudioSpecificConfig) obj;
        return this.E == audioSpecificConfig.E && this.D == audioSpecificConfig.D && this.F == audioSpecificConfig.F && this.d == audioSpecificConfig.d && this.h == audioSpecificConfig.h && this.y == audioSpecificConfig.y && this.x == audioSpecificConfig.x && this.s == audioSpecificConfig.s && this.r == audioSpecificConfig.r && this.N == audioSpecificConfig.N && this.i == audioSpecificConfig.i && this.o == audioSpecificConfig.o && this.z == audioSpecificConfig.z && this.G == audioSpecificConfig.G && this.n == audioSpecificConfig.n && this.m == audioSpecificConfig.m && this.q == audioSpecificConfig.q && this.w == audioSpecificConfig.w && this.H == audioSpecificConfig.H && this.T == audioSpecificConfig.T && this.U == audioSpecificConfig.U && this.V == audioSpecificConfig.V && this.S == audioSpecificConfig.S && this.Q == audioSpecificConfig.Q && this.P == audioSpecificConfig.P && this.R == audioSpecificConfig.R && this.M == audioSpecificConfig.M && this.L == audioSpecificConfig.L && this.I == audioSpecificConfig.I && this.A == audioSpecificConfig.A && this.C == audioSpecificConfig.C && this.B == audioSpecificConfig.B && this.K == audioSpecificConfig.K && this.J == audioSpecificConfig.J && this.W == audioSpecificConfig.W && this.l == audioSpecificConfig.l && this.p == audioSpecificConfig.p && this.g == audioSpecificConfig.g && this.f == audioSpecificConfig.f && this.k == audioSpecificConfig.k && this.t == audioSpecificConfig.t && this.O == audioSpecificConfig.O && Arrays.equals(this.X, audioSpecificConfig.X);
    }

    public int hashCode() {
        return ((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((this.X != null ? Arrays.hashCode(this.X) : 0) * 31) + this.d) * 31) + this.f) * 31) + this.g) * 31) + this.h) * 31) + this.i) * 31) + (this.k ? 1 : 0)) * 31) + (this.l ? 1 : 0)) * 31) + this.m) * 31) + this.n) * 31) + this.o) * 31) + this.p) * 31) + this.q) * 31) + this.r) * 31) + this.s) * 31) + this.t) * 31) + this.w) * 31) + this.x) * 31) + this.y) * 31) + this.z) * 31) + this.A) * 31) + this.B) * 31) + this.C) * 31) + (this.D ? 1 : 0)) * 31) + (this.E ? 1 : 0)) * 31) + (this.F ? 1 : 0)) * 31) + this.G) * 31) + (this.H ? 1 : 0)) * 31) + this.I) * 31) + this.J) * 31) + this.K) * 31) + this.L) * 31) + this.M) * 31) + this.N) * 31) + this.O) * 31) + this.P) * 31) + this.Q) * 31) + this.R) * 31) + this.S) * 31) + this.T) * 31) + this.U) * 31) + this.V) * 31) + (this.W ? 1 : 0);
    }

    public class ELDSpecificConfig {
        private static final int i = 0;

        /* renamed from: a  reason: collision with root package name */
        public boolean f3819a;
        public boolean b;
        public boolean c;
        public boolean d;
        public boolean e;
        public boolean f;
        public boolean g;

        public ELDSpecificConfig(int i2, BitReaderBuffer bitReaderBuffer) {
            int i3;
            this.f3819a = bitReaderBuffer.a();
            this.b = bitReaderBuffer.a();
            this.c = bitReaderBuffer.a();
            this.d = bitReaderBuffer.a();
            this.e = bitReaderBuffer.a();
            if (this.e) {
                this.f = bitReaderBuffer.a();
                this.g = bitReaderBuffer.a();
                a(i2, bitReaderBuffer);
            }
            while (bitReaderBuffer.a(4) != 0) {
                int a2 = bitReaderBuffer.a(4);
                if (a2 == 15) {
                    i3 = bitReaderBuffer.a(8);
                    a2 += i3;
                } else {
                    i3 = 0;
                }
                if (i3 == 255) {
                    a2 += bitReaderBuffer.a(16);
                }
                for (int i4 = 0; i4 < a2; i4++) {
                    bitReaderBuffer.a(8);
                }
            }
        }

        public void a(int i2, BitReaderBuffer bitReaderBuffer) {
            int i3;
            switch (i2) {
                case 1:
                case 2:
                    i3 = 1;
                    break;
                case 3:
                    i3 = 2;
                    break;
                case 4:
                case 5:
                case 6:
                    i3 = 3;
                    break;
                case 7:
                    i3 = 4;
                    break;
                default:
                    i3 = 0;
                    break;
            }
            for (int i4 = 0; i4 < i3; i4++) {
                new sbr_header(bitReaderBuffer);
            }
        }
    }

    public class sbr_header {

        /* renamed from: a  reason: collision with root package name */
        public boolean f3820a;
        public int b;
        public int c;
        public int d;
        public int e;
        public boolean f;
        public boolean g;
        public int h;
        public boolean i;
        public int j;
        public int k;
        public int l;
        public boolean m;
        public boolean n;

        public sbr_header(BitReaderBuffer bitReaderBuffer) {
            this.f3820a = bitReaderBuffer.a();
            this.b = bitReaderBuffer.a(4);
            this.c = bitReaderBuffer.a(4);
            this.d = bitReaderBuffer.a(3);
            this.e = bitReaderBuffer.a(2);
            this.f = bitReaderBuffer.a();
            this.g = bitReaderBuffer.a();
            if (this.f) {
                this.h = bitReaderBuffer.a(2);
                this.i = bitReaderBuffer.a();
                this.j = bitReaderBuffer.a(2);
            }
            if (this.g) {
                this.k = bitReaderBuffer.a(2);
                this.l = bitReaderBuffer.a(2);
                this.m = bitReaderBuffer.a();
            }
            this.n = bitReaderBuffer.a();
        }
    }
}
