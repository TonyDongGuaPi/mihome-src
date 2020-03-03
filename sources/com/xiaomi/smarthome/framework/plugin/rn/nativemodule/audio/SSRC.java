package com.xiaomi.smarthome.framework.plugin.rn.nativemodule.audio;

import com.xiaomi.ai.AsrRequest;
import com.xiaomi.smarthome.framework.plugin.rn.nativemodule.MIOTAudioModule;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.Random;

public class SSRC {

    /* renamed from: a  reason: collision with root package name */
    static final /* synthetic */ boolean f17441a = (!SSRC.class.desiredAssertionStatus());
    private static final String d = "1.30";
    private static final int h = 65536;
    private static final int[] i = {0, MIOTAudioModule.SAMPLING_RATE, 44100, 37800, AsrRequest.d, 22050, MIOTAudioModule.SAMPLING_RATE, 44100};
    private static final int[] j = {1, 16, 20, 16, 16, 15, 16, 15};
    private static final int[] k = {8, 18, 27, 8, 8, 8, 10, 9};
    private static final double[][] l = {new double[]{-1.0d}, new double[]{-2.87207293510437d, 5.041323184967041d, -6.244299411773682d, 5.848398685455322d, -3.706754207611084d, 1.0495119094848633d, 1.1830236911773682d, -2.1126792430877686d, 1.9094531536102295d, -0.9991308450698853d, 0.17090806365013123d, 0.32615602016448975d, -0.39127644896507263d, 0.2687646150588989d, -0.0976761057972908d, 0.023473845794796944d}, new double[]{-2.6773197650909424d, 4.830892562866211d, -6.570110321044922d, 7.4572014808654785d, -6.726327419281006d, 4.848165035247803d, -2.0412089824676514d, -0.7006359100341797d, 2.95375657081604d, -4.080038547515869d, 4.184521675109863d, -3.331181287765503d, 2.117992639541626d, -0.879302978515625d, 0.031759146600961685d, 0.4238278865814209d, -0.4788210391998291d, 0.35490813851356506d, -0.1749683916568756d, 0.06090816855430603d}, new double[]{-1.6335992813110352d, 2.261549234390259d, -2.407702922821045d, 2.634171724319458d, -2.144036293029785d, 1.8153258562088013d, -1.0816224813461304d, 0.703026533126831d, -0.15991993248462677d, -0.04154951870441437d, 0.2941657602787018d, -0.25183168053627014d, 0.27766478061676025d, -0.15785403549671173d, 0.10165894031524658d, -0.016833892092108727d}, new double[]{-0.8290129899978638d, 0.9892265796661377d, -0.5982571244239807d, 1.0028809309005737d, -0.5993821620941162d, 0.7950245141983032d, -0.42723315954208374d, 0.5449252724647522d, -0.3079260587692261d, 0.3687179982662201d, -0.187920480966568d, 0.2261127084493637d, -0.10573341697454453d, 0.11435490846633911d, -0.0388006791472435d, 0.040842197835445404d}, new double[]{-0.06522997468709946d, 0.5498126149177551d, 0.4027854800224304d, 0.3178376853466034d, 0.2820179760456085d, 0.16985194385051727d, 0.15433363616466522d, 0.12507140636444092d, 0.08903945237398148d, 0.06441012024879456d, 0.04714600369334221d, 0.03280523791909218d, 0.028495194390416145d, 0.011695005930960178d, 0.011831838637590408d}, new double[]{-2.3925774097442627d, 3.4350297451019287d, -3.185370922088623d, 1.8117271661758423d, 0.2012477070093155d, -1.4759907722473145d, 1.7210904359817505d, -0.9774670004844666d, 0.13790138065814972d, 0.38185903429985046d, -0.27421241998672485d, -0.06658421456813812d, 0.35223302245140076d, -0.37672343850135803d, 0.23964276909828186d, -0.06867482513189316d}, new double[]{-2.0833916664123535d, 3.0418450832366943d, -3.204789876937866d, 2.757192611694336d, -1.4978630542755127d, 0.34275946021080017d, 0.7173374891281128d, -1.073705792427063d, 1.0225815773010254d, -0.5664999485015869d, 0.20968692004680634d, 0.06537853181362152d, -0.10322438180446625d, 0.06744202226400375d, 0.00495197344571352d}};
    private static final int x = 97;
    private static final double[] y = {0.7d, 0.9d, 0.18d};
    private ByteOrder b;
    private SplitRadixFft c;
    private double e;
    private double f;
    private int g;
    private double[][] m;
    private int n;
    private int o;
    private int p;
    private int q;
    private double[] r;
    private int s;
    private boolean t;
    private int u;
    private long v;
    private long w;

    private int a(double d2) {
        return (int) (d2 >= 0.0d ? d2 + 0.5d : d2 - 0.5d);
    }

    private void a(int i2) {
    }

    public int a(int i2, int i3, int i4, int i5, int i6, int i7, double d2) {
        int i8 = i3;
        int i9 = i6;
        int[] iArr = new int[97];
        int i10 = 1;
        while (true) {
            if (i10 >= 6) {
                int i11 = i2;
                break;
            } else if (i2 == i[i10]) {
                break;
            } else {
                i10++;
            }
        }
        if ((i9 == 3 || i9 == 4) && i10 == 6) {
            System.err.printf("Warning: ATH based noise shaping for destination frequency %dHz is not available, using triangular dither\n", new Object[]{Integer.valueOf(i2)});
        }
        if (i9 == 2 || i10 == 6) {
            i10 = 0;
        }
        if (i9 == 4 && (i10 == 1 || i10 == 2)) {
            i10 += 5;
        }
        this.n = i10;
        this.m = new double[i8][];
        this.o = j[this.n];
        for (int i12 = 0; i12 < i8; i12++) {
            this.m[i12] = new double[this.o];
        }
        this.p = i4;
        this.q = i5;
        int i13 = 65536;
        this.r = new double[65536];
        Random random = new Random(System.currentTimeMillis());
        for (int i14 = 0; i14 < 97; i14++) {
            iArr[i14] = random.nextInt();
        }
        switch (i7) {
            case 0:
                for (int i15 = 0; i15 < 65536; i15++) {
                    int nextInt = random.nextInt() % 97;
                    int i16 = iArr[nextInt];
                    iArr[nextInt] = random.nextInt();
                    double[] dArr = this.r;
                    double d3 = (double) i16;
                    Double.isNaN(d3);
                    dArr[i15] = ((d3 / 2.147483647E9d) - 0.5d) * d2;
                }
                break;
            case 1:
                for (int i17 = 0; i17 < 65536; i17++) {
                    int nextInt2 = random.nextInt() % 97;
                    int i18 = iArr[nextInt2];
                    iArr[nextInt2] = random.nextInt();
                    int nextInt3 = random.nextInt() % 97;
                    int i19 = iArr[nextInt3];
                    iArr[nextInt3] = random.nextInt();
                    double[] dArr2 = this.r;
                    double d4 = (double) i18;
                    Double.isNaN(d4);
                    double d5 = (double) i19;
                    Double.isNaN(d5);
                    dArr2[i17] = d2 * ((d4 / 2.147483647E9d) - (d5 / 2.147483647E9d));
                }
                break;
            case 2:
                int i20 = 0;
                boolean z = false;
                double d6 = 0.0d;
                double d7 = 0.0d;
                while (i20 < i13) {
                    if (!z) {
                        int nextInt4 = random.nextInt() % 97;
                        double d8 = (double) iArr[nextInt4];
                        Double.isNaN(d8);
                        double d9 = d8 / 2.147483647E9d;
                        iArr[nextInt4] = random.nextInt();
                        if (d9 == 1.0d) {
                            d9 = 0.0d;
                        }
                        double sqrt = Math.sqrt(Math.log(1.0d - d9) * -2.0d);
                        int nextInt5 = random.nextInt() % 97;
                        double d10 = (double) iArr[nextInt5];
                        Double.isNaN(d10);
                        iArr[nextInt5] = random.nextInt();
                        double d11 = (d10 / 2.147483647E9d) * 6.283185307179586d;
                        this.r[i20] = d2 * sqrt * Math.cos(d11);
                        d7 = d11;
                        d6 = sqrt;
                        z = true;
                    } else {
                        this.r[i20] = d2 * d6 * Math.sin(d7);
                        z = false;
                    }
                    i20++;
                    i13 = 65536;
                    int i21 = i6;
                }
                break;
        }
        this.s = 0;
        int i22 = i6;
        if (i22 == 0 || i22 == 1) {
            return 1;
        }
        return k[this.n];
    }

    public int a(double d2, double[] dArr, int i2, int i3) {
        double d3;
        if (i2 == 1) {
            double[] dArr2 = this.r;
            int i4 = this.s;
            this.s = i4 + 1;
            double d4 = d2 + dArr2[i4 & 65535];
            if (d4 < ((double) this.p)) {
                double d5 = (double) this.p;
                Double.isNaN(d5);
                double d6 = d4 / d5;
                if (dArr[0] >= d6) {
                    d6 = dArr[0];
                }
                dArr[0] = d6;
                d4 = (double) this.p;
            }
            if (d4 > ((double) this.q)) {
                double d7 = (double) this.q;
                Double.isNaN(d7);
                double d8 = d4 / d7;
                if (dArr[0] >= d8) {
                    d8 = dArr[0];
                }
                dArr[0] = d8;
                d4 = (double) this.q;
            }
            return a(d4);
        }
        double d9 = 0.0d;
        for (int i5 = 0; i5 < this.o; i5++) {
            d9 += l[this.n][i5] * this.m[i3][i5];
        }
        double d10 = d2 + d9;
        double[] dArr3 = this.r;
        int i6 = this.s;
        this.s = i6 + 1;
        double d11 = dArr3[65535 & i6] + d10;
        for (int i7 = this.o - 2; i7 >= 0; i7--) {
            this.m[i3][i7 + 1] = this.m[i3][i7];
        }
        if (d11 < ((double) this.p)) {
            double d12 = (double) this.p;
            Double.isNaN(d12);
            double d13 = d11 / d12;
            if (dArr[0] >= d13) {
                d13 = dArr[0];
            }
            dArr[0] = d13;
            d3 = (double) this.p;
            double[] dArr4 = this.m[i3];
            Double.isNaN(d3);
            dArr4[0] = d3 - d10;
            if (this.m[i3][0] > 1.0d) {
                this.m[i3][0] = 1.0d;
            }
            if (this.m[i3][0] < -1.0d) {
                this.m[i3][0] = -1.0d;
            }
        } else if (d11 > ((double) this.q)) {
            double d14 = (double) this.q;
            Double.isNaN(d14);
            double d15 = d11 / d14;
            if (dArr[0] >= d15) {
                d15 = dArr[0];
            }
            dArr[0] = d15;
            d3 = (double) this.q;
            double[] dArr5 = this.m[i3];
            Double.isNaN(d3);
            dArr5[0] = d3 - d10;
            if (this.m[i3][0] > 1.0d) {
                this.m[i3][0] = 1.0d;
            }
            if (this.m[i3][0] < -1.0d) {
                this.m[i3][0] = -1.0d;
            }
        } else {
            d3 = (double) a(d11);
            double[] dArr6 = this.m[i3];
            Double.isNaN(d3);
            dArr6[0] = d3 - d10;
        }
        return (int) d3;
    }

    private double b(double d2) {
        if (d2 <= 21.0d) {
            return 0.0d;
        }
        if (d2 > 50.0d) {
            return (d2 - 8.7d) * 0.1102d;
        }
        double d3 = d2 - 21.0d;
        return (Math.pow(d3, 0.4d) * 0.5842d) + (d3 * 0.07886d);
    }

    private double a(double d2, int i2, double d3, double d4) {
        double d5 = 4.0d * d2 * d2;
        double d6 = (double) i2;
        Double.isNaN(d6);
        double d7 = d6 - 1.0d;
        return I0Bessel.a(d3 * Math.sqrt(1.0d - (d5 / (d7 * d7)))) / d4;
    }

    private double c(double d2) {
        if (d2 == 0.0d) {
            return 1.0d;
        }
        return Math.sin(d2) / d2;
    }

    private double a(int i2, double d2, double d3) {
        double d4 = 1.0d / d3;
        double d5 = 6.283185307179586d * d2;
        double d6 = (double) i2;
        Double.isNaN(d6);
        return d2 * 2.0d * d4 * c(d6 * d5 * d4);
    }

    private void a() {
        System.err.printf("http://shibatch.sourceforge.net/\n\n", new Object[0]);
        System.err.printf("usage: ssrc [<options>] <source wav file> <destination wav file>\n", new Object[0]);
        System.err.printf("options : --rate <sampling rate>     output sample rate\n", new Object[0]);
        System.err.printf("          --att <attenuation(dB)>    attenuate signal\n", new Object[0]);
        System.err.printf("          --bits <number of bits>    output quantization bit length\n", new Object[0]);
        System.err.printf("          --tmpfile <file name>      specify temporal file\n", new Object[0]);
        System.err.printf("          --twopass                  two pass processing to avoid clipping\n", new Object[0]);
        System.err.printf("          --normalize                normalize the wave file\n", new Object[0]);
        System.err.printf("          --quiet                    nothing displayed except error\n", new Object[0]);
        System.err.printf("          --dither [<type>]          dithering\n", new Object[0]);
        System.err.printf("                                       0 : no dither\n", new Object[0]);
        System.err.printf("                                       1 : no noise shaping\n", new Object[0]);
        System.err.printf("                                       2 : triangular spectral shape\n", new Object[0]);
        System.err.printf("                                       3 : ATH based noise shaping\n", new Object[0]);
        System.err.printf("                                       4 : less dither amplitude than type 3\n", new Object[0]);
        System.err.printf("          --pdf <type> [<amp>]       select p.d.f. of noise\n", new Object[0]);
        System.err.printf("                                       0 : rectangular\n", new Object[0]);
        System.err.printf("                                       1 : triangular\n", new Object[0]);
        System.err.printf("                                       2 : Gaussian\n", new Object[0]);
        System.err.printf("          --profile <type>           specify profile\n", new Object[0]);
        System.err.printf("                                       standard : the default quality\n", new Object[0]);
        System.err.printf("                                       fast     : fast, not so bad quality\n", new Object[0]);
    }

    private void b(int i2) {
        throw new IllegalStateException("unknown error " + i2);
    }

    private void b() {
        this.v = System.currentTimeMillis();
        this.w = 0;
        this.u = -1;
    }

    private void d(double d2) {
        int i2;
        if (!this.t) {
            long currentTimeMillis = System.currentTimeMillis() - this.v;
            if (d2 == 0.0d) {
                i2 = 0;
            } else {
                double d3 = (double) currentTimeMillis;
                Double.isNaN(d3);
                i2 = (int) ((d3 * (1.0d - d2)) / d2);
            }
            int i3 = (int) (d2 * 100.0d);
            if (!(i3 == this.u && currentTimeMillis == this.w)) {
                System.err.printf(" %3d%% processed", new Object[]{Integer.valueOf(i3)});
                this.u = i3;
            }
            if (currentTimeMillis != this.w) {
                System.err.printf(", ETA =%4dmsec", new Object[]{Integer.valueOf(i2)});
                this.w = currentTimeMillis;
            }
            System.err.printf("\r", new Object[0]);
            System.err.flush();
        }
    }

    private int a(int i2, int i3) {
        while (true) {
            int i4 = i3;
            int i5 = i2;
            i2 = i4;
            if (i2 == 0) {
                return i5;
            }
            i3 = i5 % i2;
        }
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r25v0, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r14v7, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r10v1, resolved type: int[]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v3, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r22v0, resolved type: double[][]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r14v8, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r10v2, resolved type: int[]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r7v5, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r6v10, resolved type: int[]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r26v2, resolved type: double[]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v5, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r13v1, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v6, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v7, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v14, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v3, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r28v0, resolved type: double[][]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v5, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r40v0, resolved type: double[][]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r58v0, resolved type: double[]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r60v0, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r58v1, resolved type: double[]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r41v2, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r13v2, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r6v15, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v14, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r69v0, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v11, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v12, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r69v1, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r29v5, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r13v4, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v13, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r52v0, resolved type: double[]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r12v5, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r78v0, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r60v2, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r7v11, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r58v2, resolved type: double[]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r13v5, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r78v1, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r78v2, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r78v3, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v47, resolved type: double} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r78v4, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r76v5, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r78v5, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r52v1, resolved type: double[]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r12v8, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v65, resolved type: double} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v70, resolved type: double} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v25, resolved type: double} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v71, resolved type: double} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v73, resolved type: double} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v75, resolved type: double} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v26, resolved type: double} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v76, resolved type: double} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v78, resolved type: double} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v79, resolved type: double} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r52v2, resolved type: double[]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r12v11, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v86, resolved type: double} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v87, resolved type: double} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v91, resolved type: double} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v29, resolved type: double} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v92, resolved type: double} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v94, resolved type: double} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v96, resolved type: double} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v30, resolved type: double} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v97, resolved type: double} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v99, resolved type: double} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v100, resolved type: double} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v33, resolved type: double} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r58v3, resolved type: double[]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r75v3, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r52v3, resolved type: double[]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r12v17, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v115, resolved type: double} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v38, resolved type: double} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v116, resolved type: double} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v118, resolved type: double} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v120, resolved type: double} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v39, resolved type: double} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v121, resolved type: double} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v123, resolved type: double} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v124, resolved type: double} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r75v7, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r52v7, resolved type: double[]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r12v21, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r75v8, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r52v8, resolved type: double[]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r12v22, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r37v4, resolved type: double} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v40, resolved type: double} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r37v7, resolved type: double} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v41, resolved type: double} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r37v8, resolved type: double} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v139, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v42, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r69v3, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v45, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v47, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v49, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v51, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v52, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r11v23, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r14v46, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r11v24, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v53, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v54, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v55, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v56, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v57, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r41v13, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r26v3, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r32v4, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r4v39, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r4v41, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r7v35, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r25v3, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r25v4, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r25v5, resolved type: int} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Removed duplicated region for block: B:250:0x0996  */
    /* JADX WARNING: Removed duplicated region for block: B:254:0x09a1  */
    /* JADX WARNING: Removed duplicated region for block: B:257:0x09b9  */
    /* JADX WARNING: Removed duplicated region for block: B:258:0x09cc  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public double a(java.io.InputStream r82, java.io.OutputStream r83, int r84, int r85, int r86, int r87, int r88, double r89, int r91, boolean r92, int r93) throws java.io.IOException {
        /*
            r81 = this;
            r8 = r81
            r9 = r83
            r10 = r84
            r13 = r87
            r14 = r88
            r6 = 1
            double[] r7 = new double[r6]
            r17 = 0
            r4 = 0
            r7[r4] = r17
            int r5 = r8.g
            double r0 = r8.e
            int r2 = r8.a((int) r13, (int) r14)
            int r19 = r13 / r2
            int r3 = r19 * r14
            int r2 = r3 / r14
            r20 = 5
            r21 = 4
            r23 = 3
            r24 = 2
            if (r2 != r6) goto L_0x002d
            r25 = 1
            goto L_0x003a
        L_0x002d:
            int r25 = r2 % 2
            if (r25 != 0) goto L_0x0034
            r25 = 2
            goto L_0x003a
        L_0x0034:
            int r25 = r2 % 3
            if (r25 != 0) goto L_0x0a0a
            r25 = 3
        L_0x003a:
            int r2 = r14 * r25
            int r26 = r2 / 2
            int r4 = r13 / 2
            int r6 = r26 - r4
            r29 = r5
            int r5 = r6 * 2
            double r14 = (double) r5
            r30 = 4611686018427387904(0x4000000000000000, double:2.0)
            java.lang.Double.isNaN(r14)
            double r14 = r14 / r30
            double r4 = (double) r4
            r32 = r7
            double r6 = (double) r6
            java.lang.Double.isNaN(r6)
            double r6 = r6 / r30
            java.lang.Double.isNaN(r4)
            double r33 = r4 + r6
            r35 = 4626604192193052672(0x4035000000000000, double:21.0)
            int r6 = (r0 > r35 ? 1 : (r0 == r35 ? 0 : -1))
            r37 = 4606481658697998559(0x3fed82a9930be0df, double:0.9222)
            if (r6 > 0) goto L_0x006a
            r6 = r37
            goto L_0x0078
        L_0x006a:
            r6 = 4620636922686786765(0x401fcccccccccccd, double:7.95)
            double r6 = r0 - r6
            r39 = 4624273579385888440(0x402cb851eb851eb8, double:14.36)
            double r6 = r6 / r39
        L_0x0078:
            double r11 = (double) r3
            java.lang.Double.isNaN(r11)
            double r14 = r11 / r14
            double r14 = r14 * r6
            r6 = 4607182418800017408(0x3ff0000000000000, double:1.0)
            double r14 = r14 + r6
            int r14 = (int) r14
            int r15 = r14 % 2
            if (r15 != 0) goto L_0x008a
            int r14 = r14 + 1
        L_0x008a:
            double r15 = r8.b((double) r0)
            double r39 = com.xiaomi.smarthome.framework.plugin.rn.nativemodule.audio.I0Bessel.a(r15)
            int r1 = r3 / r13
            int r0 = r14 / r1
            r26 = 1
            int r0 = r0 + 1
            int r9 = r1 * r25
            int[] r10 = new int[r9]
            r6 = 0
        L_0x009f:
            if (r6 >= r9) goto L_0x00b4
            int r7 = r3 / r2
            int r7 = r7 * r6
            int r7 = r7 % r1
            int r7 = r1 - r7
            r10[r6] = r7
            r7 = r10[r6]
            if (r7 != r1) goto L_0x00b1
            r7 = 0
            r10[r6] = r7
        L_0x00b1:
            int r6 = r6 + 1
            goto L_0x009f
        L_0x00b4:
            int[] r6 = new int[r9]
            r7 = 0
        L_0x00b7:
            if (r7 >= r9) goto L_0x00d5
            r43 = r4
            r4 = r10[r7]
            int r5 = r3 / r2
            if (r4 >= r5) goto L_0x00c4
            r4 = r84
            goto L_0x00c5
        L_0x00c4:
            r4 = 0
        L_0x00c5:
            r6[r7] = r4
            r4 = r10[r7]
            if (r4 != r1) goto L_0x00cf
            r4 = 0
            r10[r7] = r4
            goto L_0x00d0
        L_0x00cf:
            r4 = 0
        L_0x00d0:
            int r7 = r7 + 1
            r4 = r43
            goto L_0x00b7
        L_0x00d5:
            r43 = r4
            r4 = 0
            int[] r5 = new int[]{r1, r0}
            java.lang.Class<double> r7 = double.class
            java.lang.Object r5 = java.lang.reflect.Array.newInstance(r7, r5)
            r22 = r5
            double[][] r22 = (double[][]) r22
            int r7 = r14 / 2
            int r5 = -r7
        L_0x00e9:
            if (r5 > r7) goto L_0x014d
            int r28 = r5 + r7
            int r45 = r28 % r1
            r45 = r22[r45]
            int r28 = r28 / r1
            r47 = r1
            r46 = r2
            double r1 = (double) r5
            r48 = r10
            r10 = r0
            r0 = r81
            r49 = r9
            r9 = r46
            r46 = r47
            r47 = r3
            r3 = r14
            r50 = r14
            r27 = r29
            r14 = 0
            r29 = r5
            r4 = r15
            r41 = r7
            r26 = r32
            r42 = 1
            r32 = r6
            r6 = r39
            double r6 = r0.a((double) r1, (int) r3, (double) r4, (double) r6)
            r1 = r29
            r2 = r33
            r4 = r11
            double r0 = r0.a(r1, r2, r4)
            double r6 = r6 * r0
            java.lang.Double.isNaN(r11)
            double r6 = r6 * r11
            double r0 = (double) r13
            java.lang.Double.isNaN(r0)
            double r6 = r6 / r0
            r45[r28] = r6
            int r5 = r29 + 1
            r2 = r9
            r0 = r10
            r29 = r27
            r6 = r32
            r7 = r41
            r1 = r46
            r3 = r47
            r10 = r48
            r9 = r49
            r14 = r50
            r4 = 0
            r32 = r26
            r26 = 1
            goto L_0x00e9
        L_0x014d:
            r46 = r1
            r47 = r3
            r41 = r7
            r49 = r9
            r48 = r10
            r27 = r29
            r26 = r32
            r14 = 0
            r42 = 1
            r10 = r0
            r9 = r2
            r32 = r6
            double r0 = r8.e
            int r2 = (r0 > r35 ? 1 : (r0 == r35 ? 0 : -1))
            if (r2 > 0) goto L_0x0169
            goto L_0x0177
        L_0x0169:
            r2 = 4620636922686786765(0x401fcccccccccccd, double:7.95)
            double r2 = r0 - r2
            r4 = 4624273579385888440(0x402cb851eb851eb8, double:14.36)
            double r37 = r2 / r4
        L_0x0177:
            r2 = 1
        L_0x0178:
            int r5 = r27 * r2
            int r3 = r5 % 2
            if (r3 != 0) goto L_0x0180
            int r5 = r5 + -1
        L_0x0180:
            r11 = r5
            double r6 = (double) r9
            java.lang.Double.isNaN(r6)
            double r3 = r6 * r37
            int r5 = r11 + -1
            double r14 = (double) r5
            java.lang.Double.isNaN(r14)
            double r3 = r3 / r14
            double r14 = r8.f
            int r5 = (r3 > r14 ? 1 : (r3 == r14 ? 0 : -1))
            if (r5 >= 0) goto L_0x09f6
            double r14 = r8.b((double) r0)
            double r27 = com.xiaomi.smarthome.framework.plugin.rn.nativemodule.audio.I0Bessel.a(r14)
            r0 = 1
        L_0x019d:
            if (r0 >= r11) goto L_0x01a2
            int r0 = r0 * 2
            goto L_0x019d
        L_0x01a2:
            int r12 = r0 * 2
            double[] r4 = new double[r12]
            int r5 = r11 / 2
            int r0 = -r5
            r3 = r0
        L_0x01aa:
            if (r3 > r5) goto L_0x01df
            int r16 = r3 + r5
            double r1 = (double) r3
            r0 = r81
            r29 = r3
            r3 = r11
            r39 = r4
            r33 = r5
            r4 = r14
            r54 = r6
            r6 = r27
            double r6 = r0.a((double) r1, (int) r3, (double) r4, (double) r6)
            r1 = r29
            r2 = r43
            r4 = r54
            double r0 = r0.a(r1, r2, r4)
            double r6 = r6 * r0
            double r0 = (double) r12
            java.lang.Double.isNaN(r0)
            double r6 = r6 / r0
            double r6 = r6 * r30
            r39[r16] = r6
            int r3 = r29 + 1
            r5 = r33
            r4 = r39
            r6 = r54
            goto L_0x01aa
        L_0x01df:
            r29 = r3
            r39 = r4
            r54 = r6
            double r0 = (double) r12
            double r0 = java.lang.Math.sqrt(r0)
            double r0 = r0 + r30
            int r0 = (int) r0
            int[] r6 = new int[r0]
            r0 = 0
            r6[r0] = r0
            int r7 = r12 / 2
            double[] r14 = new double[r7]
            com.xiaomi.smarthome.framework.plugin.rn.nativemodule.audio.SplitRadixFft r0 = r8.c
            r35 = 1
            r33 = r0
            r34 = r12
            r36 = r39
            r37 = r6
            r38 = r14
            r33.b((int) r34, (int) r35, (double[]) r36, (int[]) r37, (double[]) r38)
            r81.b()
            int r0 = r7 / r25
            int r0 = r0 + 1
            r16 = r48
            r15 = r84
            int[] r1 = new int[]{r15, r0}
            java.lang.Class<double> r2 = double.class
            java.lang.Object r1 = java.lang.reflect.Array.newInstance(r2, r1)
            r28 = r1
            double[][] r28 = (double[][]) r28
            int[] r1 = new int[]{r15, r12}
            java.lang.Class<double> r2 = double.class
            java.lang.Object r1 = java.lang.reflect.Array.newInstance(r2, r1)
            r40 = r1
            double[][] r40 = (double[][]) r40
            int r1 = r7 + r10
            int r1 = r1 + 2
            int r1 = r1 * r15
            int r2 = r1 * r85
            java.nio.ByteBuffer r2 = java.nio.ByteBuffer.allocate(r2)
            int r0 = r0 * r15
            int r3 = r0 * r86
            java.nio.ByteBuffer r5 = java.nio.ByteBuffer.allocate(r3)
            double[] r4 = new double[r1]
            double[] r3 = new double[r0]
            int r0 = r41 / r46
            int r0 = r0 + 1
            r56 = r0
            double r0 = (double) r11
            java.lang.Double.isNaN(r0)
            double r0 = r0 / r30
            r11 = r88
            int r9 = r9 / r11
            r57 = r2
            r58 = r3
            double r2 = (double) r9
            java.lang.Double.isNaN(r2)
            double r0 = r0 / r2
            int r0 = (int) r0
            r2 = r91
            r60 = r0
            r62 = r5
            r61 = r6
            r0 = r56
            r3 = r57
            r1 = 0
            r9 = 0
            r27 = 0
            r33 = 0
            r41 = 0
            r43 = 0
            r44 = 1
            r59 = 0
        L_0x0279:
            double r5 = (double) r7
            r63 = r12
            double r11 = (double) r13
            java.lang.Double.isNaN(r5)
            java.lang.Double.isNaN(r11)
            double r5 = r5 * r11
            java.lang.Double.isNaN(r54)
            double r5 = r5 / r54
            double r5 = java.lang.Math.ceil(r5)
            r64 = r11
            r11 = 4607182418800017408(0x3ff0000000000000, double:1.0)
            double r5 = r5 + r11
            double r11 = (double) r10
            java.lang.Double.isNaN(r11)
            double r5 = r5 + r11
            double r11 = (double) r0
            java.lang.Double.isNaN(r11)
            double r5 = r5 - r11
            int r5 = (int) r5
            int r6 = r5 + r1
            if (r6 <= r2) goto L_0x02a5
            int r6 = r2 - r1
            goto L_0x02a6
        L_0x02a5:
            r6 = r5
        L_0x02a6:
            r11 = 0
            r3.position(r11)
            int r11 = r85 * r15
            int r6 = r6 * r11
            r3.limit(r6)
            int r6 = r3.limit()
            byte[] r6 = new byte[r6]
            r12 = r82
            int r34 = r12.read(r6)
            if (r34 >= 0) goto L_0x02c3
            r66 = r2
            r2 = 0
            goto L_0x02c7
        L_0x02c3:
            r66 = r2
            r2 = r34
        L_0x02c7:
            int r12 = r3.limit()
            if (r2 >= r12) goto L_0x02d3
            int r12 = r2 / r85
            int r12 = r12 * r15
            int r12 = r12 + r1
            goto L_0x02d5
        L_0x02d3:
            r12 = r66
        L_0x02d5:
            r3.limit(r2)
            java.nio.ByteBuffer r6 = java.nio.ByteBuffer.wrap(r6)
            r6.position(r2)
            r6.flip()
            int r2 = r2 / r11
            switch(r85) {
                case 1: goto L_0x0398;
                case 2: goto L_0x0365;
                case 3: goto L_0x0323;
                case 4: goto L_0x02f0;
                default: goto L_0x02e6;
            }
        L_0x02e6:
            r69 = r7
            r67 = r10
            r68 = r14
            r3 = r29
            goto L_0x03be
        L_0x02f0:
            r3 = 0
        L_0x02f1:
            int r11 = r2 * r15
            if (r3 >= r11) goto L_0x031b
            java.nio.ByteOrder r11 = r8.b
            java.nio.ByteBuffer r11 = r6.order(r11)
            java.nio.IntBuffer r11 = r11.asIntBuffer()
            int r11 = r11.get(r3)
            int r29 = r15 * r0
            int r29 = r29 + r3
            r34 = 4467570830353629184(0x3e00000000200000, double:4.656612875245797E-10)
            r67 = r10
            double r10 = (double) r11
            java.lang.Double.isNaN(r10)
            double r10 = r10 * r34
            r4[r29] = r10
            int r3 = r3 + 1
            r10 = r67
            goto L_0x02f1
        L_0x031b:
            r67 = r10
            r69 = r7
            r68 = r14
            goto L_0x03be
        L_0x0323:
            r67 = r10
            r3 = 0
        L_0x0326:
            int r10 = r2 * r15
            if (r3 >= r10) goto L_0x0362
            int r10 = r15 * r0
            int r10 = r10 + r3
            r34 = 4503599627907366976(0x3e80000020000040, double:1.1920930376163766E-7)
            int r11 = r3 * 3
            byte r29 = r6.get(r11)
            r36 = 0
            int r29 = r29 << 0
            r68 = r14
            int r14 = r11 + 1
            byte r14 = r6.get(r14)
            r36 = 8
            int r14 = r14 << 8
            r14 = r29 | r14
            int r11 = r11 + 2
            byte r11 = r6.get(r11)
            int r11 = r11 << 16
            r11 = r11 | r14
            double r13 = (double) r11
            java.lang.Double.isNaN(r13)
            double r13 = r13 * r34
            r4[r10] = r13
            int r3 = r3 + 1
            r14 = r68
            r13 = r87
            goto L_0x0326
        L_0x0362:
            r68 = r14
            goto L_0x0395
        L_0x0365:
            r67 = r10
            r68 = r14
            r3 = 0
        L_0x036a:
            int r10 = r2 * r15
            if (r3 >= r10) goto L_0x0395
            java.nio.ByteOrder r10 = r8.b
            java.nio.ByteBuffer r10 = r6.order(r10)
            java.nio.ShortBuffer r10 = r10.asShortBuffer()
            short r10 = r10.get(r3)
            int r11 = r15 * r0
            int r11 = r11 + r3
            r13 = 4539628561832607872(0x3f00002000400080, double:3.051850947599719E-5)
            r69 = r7
            double r7 = (double) r10
            java.lang.Double.isNaN(r7)
            double r7 = r7 * r13
            r4[r11] = r7
            int r3 = r3 + 1
            r7 = r69
            r8 = r81
            goto L_0x036a
        L_0x0395:
            r69 = r7
            goto L_0x03be
        L_0x0398:
            r69 = r7
            r67 = r10
            r68 = r14
            r3 = 0
        L_0x039f:
            int r7 = r2 * r15
            if (r3 >= r7) goto L_0x03be
            int r7 = r15 * r0
            int r7 = r7 + r3
            r10 = 4575692682822812680(0x3f80204081020408, double:0.007874015748031496)
            byte r8 = r6.get(r3)
            double r13 = (double) r8
            r34 = 4638707616191610880(0x4060000000000000, double:128.0)
            java.lang.Double.isNaN(r13)
            double r13 = r13 - r34
            double r13 = r13 * r10
            r4[r7] = r13
            int r3 = r3 + 1
            goto L_0x039f
        L_0x03be:
            int r7 = r15 * r5
            if (r3 >= r7) goto L_0x03ca
            int r7 = r15 * r0
            int r7 = r7 + r3
            r4[r7] = r17
            int r3 = r3 + 1
            goto L_0x03be
        L_0x03ca:
            int r7 = r0 + r5
            int r8 = r1 + r2
            if (r8 < r12) goto L_0x03d2
            r10 = 1
            goto L_0x03d3
        L_0x03d2:
            r10 = 0
        L_0x03d3:
            int r0 = r9 + -1
            r11 = r87
            int r0 = r0 * r11
            int r0 = r0 + r47
            int r0 = r0 / r47
            int r0 = r0 * r15
            r14 = r27
            r13 = r33
            r29 = r41
            r1 = 0
        L_0x03e6:
            r5 = 7
            if (r1 >= r15) goto L_0x061b
            int r2 = r0 + r1
            r3 = r67
            if (r3 == r5) goto L_0x04d6
            r13 = 9
            if (r3 == r13) goto L_0x0432
            r5 = r2
            r14 = r27
            r13 = r69
            r2 = 0
        L_0x03f9:
            if (r2 >= r13) goto L_0x042b
            r29 = r16[r14]
            r70 = r0
            r35 = r5
            r33 = r17
            r0 = 0
        L_0x0404:
            if (r0 >= r3) goto L_0x0415
            r36 = r22[r29]
            r37 = r36[r0]
            r52 = r4[r35]
            double r37 = r37 * r52
            double r33 = r33 + r37
            int r35 = r35 + r15
            int r0 = r0 + 1
            goto L_0x0404
        L_0x0415:
            r0 = r40[r1]
            r0[r2] = r33
            r0 = r32[r14]
            int r5 = r5 + r0
            int r0 = r14 + 1
            r14 = r49
            if (r0 != r14) goto L_0x0423
            r0 = 0
        L_0x0423:
            int r2 = r2 + 1
            r49 = r14
            r14 = r0
            r0 = r70
            goto L_0x03f9
        L_0x042b:
            r70 = r0
            r0 = r49
        L_0x042f:
            r5 = 6
            goto L_0x0558
        L_0x0432:
            r70 = r0
            r0 = r49
            r13 = r69
            r29 = r2
            r14 = r27
            r2 = 0
        L_0x043d:
            if (r2 >= r13) goto L_0x042f
            r33 = r16[r14]
            r34 = r40[r1]
            r35 = r22[r33]
            r36 = 0
            r37 = r35[r36]
            int r35 = r15 * 0
            int r35 = r29 + r35
            r35 = r4[r35]
            double r37 = r37 * r35
            r35 = r22[r33]
            r49 = r35[r42]
            int r35 = r15 * 1
            int r35 = r29 + r35
            r35 = r4[r35]
            double r49 = r49 * r35
            double r37 = r37 + r49
            r35 = r22[r33]
            r49 = r35[r24]
            int r35 = r15 * 2
            int r35 = r29 + r35
            r35 = r4[r35]
            double r49 = r49 * r35
            double r37 = r37 + r49
            r35 = r22[r33]
            r49 = r35[r23]
            int r35 = r15 * 3
            int r35 = r29 + r35
            r35 = r4[r35]
            double r49 = r49 * r35
            double r37 = r37 + r49
            r35 = r22[r33]
            r49 = r35[r21]
            int r35 = r15 * 4
            int r35 = r29 + r35
            r35 = r4[r35]
            double r49 = r49 * r35
            double r37 = r37 + r49
            r35 = r22[r33]
            r49 = r35[r20]
            int r35 = r15 * 5
            int r35 = r29 + r35
            r35 = r4[r35]
            double r49 = r49 * r35
            double r37 = r37 + r49
            r35 = r22[r33]
            r5 = 6
            r49 = r35[r5]
            int r35 = r15 * 6
            int r35 = r29 + r35
            r35 = r4[r35]
            double r49 = r49 * r35
            double r37 = r37 + r49
            r35 = r22[r33]
            r36 = 7
            r49 = r35[r36]
            int r35 = r15 * 7
            int r35 = r29 + r35
            r51 = r4[r35]
            double r49 = r49 * r51
            double r37 = r37 + r49
            r33 = r22[r33]
            r35 = 8
            r49 = r33[r35]
            int r33 = r15 * 8
            int r33 = r29 + r33
            r51 = r4[r33]
            double r49 = r49 * r51
            double r37 = r37 + r49
            r34[r2] = r37
            r33 = r32[r14]
            int r29 = r29 + r33
            int r14 = r14 + 1
            if (r14 != r0) goto L_0x04d1
            r14 = 0
        L_0x04d1:
            int r2 = r2 + 1
            r5 = 7
            goto L_0x043d
        L_0x04d6:
            r70 = r0
            r0 = r49
            r13 = r69
            r5 = 6
            r29 = r2
            r14 = r27
            r2 = 0
        L_0x04e2:
            if (r2 >= r13) goto L_0x0558
            r33 = r16[r14]
            r34 = r40[r1]
            r35 = r22[r33]
            r36 = 0
            r37 = r35[r36]
            int r35 = r15 * 0
            int r35 = r29 + r35
            r35 = r4[r35]
            double r37 = r37 * r35
            r35 = r22[r33]
            r49 = r35[r42]
            int r35 = r15 * 1
            int r35 = r29 + r35
            r35 = r4[r35]
            double r49 = r49 * r35
            double r37 = r37 + r49
            r35 = r22[r33]
            r49 = r35[r24]
            int r35 = r15 * 2
            int r35 = r29 + r35
            r35 = r4[r35]
            double r49 = r49 * r35
            double r37 = r37 + r49
            r35 = r22[r33]
            r49 = r35[r23]
            int r35 = r15 * 3
            int r35 = r29 + r35
            r35 = r4[r35]
            double r49 = r49 * r35
            double r37 = r37 + r49
            r35 = r22[r33]
            r49 = r35[r21]
            int r35 = r15 * 4
            int r35 = r29 + r35
            r35 = r4[r35]
            double r49 = r49 * r35
            double r37 = r37 + r49
            r35 = r22[r33]
            r49 = r35[r20]
            int r35 = r15 * 5
            int r35 = r29 + r35
            r35 = r4[r35]
            double r49 = r49 * r35
            double r37 = r37 + r49
            r33 = r22[r33]
            r35 = r33[r5]
            int r33 = r15 * 6
            int r33 = r29 + r33
            r49 = r4[r33]
            double r35 = r35 * r49
            double r37 = r37 + r35
            r34[r2] = r37
            r33 = r32[r14]
            int r29 = r29 + r33
            int r14 = r14 + 1
            if (r14 != r0) goto L_0x0555
            r14 = 0
        L_0x0555:
            int r2 = r2 + 1
            goto L_0x04e2
        L_0x0558:
            r5 = r13
            r2 = r63
        L_0x055b:
            if (r5 >= r2) goto L_0x0564
            r29 = r40[r1]
            r29[r5] = r17
            int r5 = r5 + 1
            goto L_0x055b
        L_0x0564:
            r71 = r0
            r5 = r81
            com.xiaomi.smarthome.framework.plugin.rn.nativemodule.audio.SplitRadixFft r0 = r5.c
            r35 = 1
            r36 = r40[r1]
            r33 = r0
            r34 = r2
            r37 = r61
            r38 = r68
            r33.b((int) r34, (int) r35, (double[]) r36, (int[]) r37, (double[]) r38)
            r0 = r40[r1]
            r29 = 0
            r33 = r39[r29]
            r35 = r40[r1]
            r36 = r35[r29]
            double r33 = r33 * r36
            r0[r29] = r33
            r0 = r40[r1]
            r33 = r39[r42]
            r29 = r40[r1]
            r35 = r29[r42]
            double r33 = r33 * r35
            r0[r42] = r33
            r0 = 1
        L_0x0594:
            if (r0 >= r13) goto L_0x05c9
            int r29 = r0 * 2
            r33 = r39[r29]
            r35 = r40[r1]
            r36 = r35[r29]
            double r33 = r33 * r36
            int r35 = r29 + 1
            r36 = r39[r35]
            r38 = r40[r1]
            r49 = r38[r35]
            double r36 = r36 * r49
            double r33 = r33 - r36
            r36 = r39[r35]
            r38 = r40[r1]
            r49 = r38[r29]
            double r36 = r36 * r49
            r49 = r39[r29]
            r38 = r40[r1]
            r51 = r38[r35]
            double r49 = r49 * r51
            double r36 = r36 + r49
            r38 = r40[r1]
            r38[r29] = r33
            r29 = r40[r1]
            r29[r35] = r36
            int r0 = r0 + 1
            goto L_0x0594
        L_0x05c9:
            com.xiaomi.smarthome.framework.plugin.rn.nativemodule.audio.SplitRadixFft r0 = r5.c
            r35 = -1
            r36 = r40[r1]
            r33 = r0
            r34 = r2
            r37 = r61
            r38 = r68
            r33.b((int) r34, (int) r35, (double[]) r36, (int[]) r37, (double[]) r38)
            r0 = r41
            r29 = 0
        L_0x05de:
            if (r0 >= r13) goto L_0x05f5
            r33 = r28[r1]
            r34 = r33[r29]
            r33 = r40[r1]
            r36 = r33[r0]
            double r34 = r34 + r36
            int r33 = r29 * r15
            int r33 = r1 + r33
            r58[r33] = r34
            int r0 = r0 + r25
            int r29 = r29 + 1
            goto L_0x05de
        L_0x05f5:
            int r33 = r0 - r13
            r34 = 0
        L_0x05f9:
            if (r0 >= r2) goto L_0x0608
            r35 = r28[r1]
            r36 = r40[r1]
            r37 = r36[r0]
            r35[r34] = r37
            int r0 = r0 + r25
            int r34 = r34 + 1
            goto L_0x05f9
        L_0x0608:
            int r1 = r1 + 1
            r63 = r2
            r67 = r3
            r69 = r13
            r13 = r29
            r29 = r33
            r49 = r71
            r3 = r0
            r0 = r70
            goto L_0x03e6
        L_0x061b:
            r71 = r49
            r2 = r63
            r33 = r67
            r34 = r69
            r5 = r81
            r36 = 7
            int r0 = r34 * r19
            int r0 = r0 / r25
            int r9 = r9 + r0
            r62.clear()
            if (r92 == 0) goto L_0x0676
            r0 = 0
        L_0x0632:
            int r1 = r13 * r15
            if (r0 >= r1) goto L_0x0663
            r37 = r58[r0]
            int r1 = (r37 > r17 ? 1 : (r37 == r17 ? 0 : -1))
            if (r1 <= 0) goto L_0x0642
            r37 = r58[r0]
            r72 = r2
        L_0x0640:
            r1 = 0
            goto L_0x064a
        L_0x0642:
            r72 = r2
            r1 = r58[r0]
            double r1 = -r1
            r37 = r1
            goto L_0x0640
        L_0x064a:
            r2 = r26[r1]
            int r27 = (r2 > r37 ? 1 : (r2 == r37 ? 0 : -1))
            if (r27 >= 0) goto L_0x0651
            goto L_0x0653
        L_0x0651:
            r37 = r26[r1]
        L_0x0653:
            r26[r1] = r37
            java.nio.DoubleBuffer r1 = r62.asDoubleBuffer()
            r2 = r58[r0]
            r1.put(r0, r2)
            int r0 = r0 + 1
            r2 = r72
            goto L_0x0632
        L_0x0663:
            r51 = r2
            r11 = r5
            r73 = r6
            r75 = r12
            r74 = r14
            r52 = r58
            r14 = r62
            r41 = r71
            r12 = r0
            r6 = r4
            goto L_0x0879
        L_0x0676:
            r72 = r2
            switch(r86) {
                case 1: goto L_0x07f8;
                case 2: goto L_0x0768;
                case 3: goto L_0x068e;
                default: goto L_0x067b;
            }
        L_0x067b:
            r11 = r5
            r73 = r6
            r75 = r12
            r74 = r14
            r52 = r58
            r14 = r62
            r41 = r71
            r51 = r72
            r6 = r4
            r12 = r3
            goto L_0x0879
        L_0x068e:
            r0 = 4710765209155796992(0x415fffffc0000000, double:8388607.0)
            double r37 = r89 * r0
            r3 = 0
            r27 = 0
        L_0x0698:
            int r0 = r13 * r15
            if (r3 >= r0) goto L_0x0752
            if (r93 == 0) goto L_0x06c2
            r0 = r58[r3]
            double r1 = r0 * r37
            r41 = r71
            r0 = r81
            r51 = r72
            r53 = r3
            r52 = r58
            r3 = r26
            r73 = r6
            r6 = r4
            r4 = r93
            r11 = r5
            r75 = r12
            r74 = r14
            r14 = r62
            r12 = 7
            r5 = r27
            int r0 = r0.a((double) r1, (double[]) r3, (int) r4, (int) r5)
            goto L_0x0715
        L_0x06c2:
            r53 = r3
            r11 = r5
            r73 = r6
            r75 = r12
            r74 = r14
            r52 = r58
            r14 = r62
            r41 = r71
            r51 = r72
            r12 = 7
            r6 = r4
            r0 = r52[r53]
            double r0 = r0 * r37
            int r0 = r11.a((double) r0)
            r1 = -8388608(0xffffffffff800000, float:-Infinity)
            if (r0 >= r1) goto L_0x06f7
            double r0 = (double) r0
            r2 = -4512606826625236992(0xc160000000000000, double:-8388608.0)
            java.lang.Double.isNaN(r0)
            double r0 = r0 / r2
            r2 = 0
            r3 = r26[r2]
            int r5 = (r3 > r0 ? 1 : (r3 == r0 ? 0 : -1))
            if (r5 >= 0) goto L_0x06f0
            goto L_0x06f2
        L_0x06f0:
            r0 = r26[r2]
        L_0x06f2:
            r26[r2] = r0
            r0 = -8388608(0xffffffffff800000, float:-Infinity)
            goto L_0x06f8
        L_0x06f7:
            r2 = 0
        L_0x06f8:
            r1 = 8388607(0x7fffff, float:1.1754942E-38)
            if (r1 >= r0) goto L_0x0715
            double r0 = (double) r0
            r3 = 4710765209155796992(0x415fffffc0000000, double:8388607.0)
            java.lang.Double.isNaN(r0)
            double r0 = r0 / r3
            r3 = r26[r2]
            int r5 = (r3 > r0 ? 1 : (r3 == r0 ? 0 : -1))
            if (r5 >= 0) goto L_0x070e
            goto L_0x0710
        L_0x070e:
            r0 = r26[r2]
        L_0x0710:
            r26[r2] = r0
            r0 = 8388607(0x7fffff, float:1.1754942E-38)
        L_0x0715:
            int r3 = r53 * 3
            r1 = r0 & 255(0xff, float:3.57E-43)
            byte r1 = (byte) r1
            r14.put(r3, r1)
            r1 = 8
            int r0 = r0 >> r1
            int r2 = r3 + 1
            r4 = r0 & 255(0xff, float:3.57E-43)
            byte r4 = (byte) r4
            r14.put(r2, r4)
            int r0 = r0 >> r1
            int r3 = r3 + 2
            r0 = r0 & 255(0xff, float:3.57E-43)
            byte r0 = (byte) r0
            r14.put(r3, r0)
            int r4 = r27 + 1
            if (r4 != r15) goto L_0x0738
            r27 = 0
            goto L_0x073a
        L_0x0738:
            r27 = r4
        L_0x073a:
            int r3 = r53 + 1
            r4 = r6
            r5 = r11
            r62 = r14
            r71 = r41
            r72 = r51
            r58 = r52
            r6 = r73
            r14 = r74
            r12 = r75
            r11 = r87
            r36 = 7
            goto L_0x0698
        L_0x0752:
            r53 = r3
            r11 = r5
            r73 = r6
            r75 = r12
            r74 = r14
            r52 = r58
            r14 = r62
            r41 = r71
            r51 = r72
            r6 = r4
            r12 = r53
            goto L_0x0879
        L_0x0768:
            r11 = r5
            r73 = r6
            r75 = r12
            r74 = r14
            r52 = r58
            r14 = r62
            r41 = r71
            r51 = r72
            r12 = 7
            r6 = r4
            r0 = 4674736138332667904(0x40dfffc000000000, double:32767.0)
            double r36 = r89 * r0
            r5 = 0
            r27 = 0
        L_0x0783:
            int r0 = r13 * r15
            if (r5 >= r0) goto L_0x07f5
            if (r93 == 0) goto L_0x079b
            r0 = r52[r5]
            double r1 = r0 * r36
            r0 = r81
            r3 = r26
            r4 = r93
            r12 = r5
            r5 = r27
            int r0 = r0.a((double) r1, (double[]) r3, (int) r4, (int) r5)
            goto L_0x07da
        L_0x079b:
            r12 = r5
            r0 = r52[r12]
            double r0 = r0 * r36
            int r0 = r11.a((double) r0)
            r1 = -32768(0xffffffffffff8000, float:NaN)
            if (r0 >= r1) goto L_0x07be
            double r0 = (double) r0
            r2 = -4548635623644200960(0xc0e0000000000000, double:-32768.0)
            java.lang.Double.isNaN(r0)
            double r0 = r0 / r2
            r2 = 0
            r3 = r26[r2]
            int r5 = (r3 > r0 ? 1 : (r3 == r0 ? 0 : -1))
            if (r5 >= 0) goto L_0x07b7
            goto L_0x07b9
        L_0x07b7:
            r0 = r26[r2]
        L_0x07b9:
            r26[r2] = r0
            r0 = -32768(0xffffffffffff8000, float:NaN)
            goto L_0x07bf
        L_0x07be:
            r2 = 0
        L_0x07bf:
            r1 = 32767(0x7fff, float:4.5916E-41)
            if (r1 >= r0) goto L_0x07da
            double r0 = (double) r0
            r3 = 4674736138332667904(0x40dfffc000000000, double:32767.0)
            java.lang.Double.isNaN(r0)
            double r0 = r0 / r3
            r3 = r26[r2]
            int r5 = (r3 > r0 ? 1 : (r3 == r0 ? 0 : -1))
            if (r5 >= 0) goto L_0x07d4
            goto L_0x07d6
        L_0x07d4:
            r0 = r26[r2]
        L_0x07d6:
            r26[r2] = r0
            r0 = 32767(0x7fff, float:4.5916E-41)
        L_0x07da:
            java.nio.ByteOrder r1 = r11.b
            java.nio.ByteBuffer r1 = r14.order(r1)
            java.nio.ShortBuffer r1 = r1.asShortBuffer()
            short r0 = (short) r0
            r1.put(r12, r0)
            int r4 = r27 + 1
            if (r4 != r15) goto L_0x07ef
            r27 = 0
            goto L_0x07f1
        L_0x07ef:
            r27 = r4
        L_0x07f1:
            int r5 = r12 + 1
            r12 = 7
            goto L_0x0783
        L_0x07f5:
            r12 = r5
            goto L_0x0879
        L_0x07f8:
            r11 = r5
            r73 = r6
            r75 = r12
            r74 = r14
            r52 = r58
            r14 = r62
            r41 = r71
            r51 = r72
            r6 = r4
            r0 = 4638637247447433216(0x405fc00000000000, double:127.0)
            double r36 = r89 * r0
            r12 = 0
            r27 = 0
        L_0x0812:
            int r0 = r13 * r15
            if (r12 >= r0) goto L_0x0879
            if (r93 == 0) goto L_0x0829
            r0 = r52[r12]
            double r1 = r0 * r36
            r0 = r81
            r3 = r26
            r4 = r93
            r5 = r27
            int r0 = r0.a((double) r1, (double[]) r3, (int) r4, (int) r5)
            goto L_0x0867
        L_0x0829:
            r0 = r52[r12]
            double r0 = r0 * r36
            int r0 = r11.a((double) r0)
            r1 = -128(0xffffffffffffff80, float:NaN)
            if (r0 >= r1) goto L_0x084b
            double r0 = (double) r0
            r2 = -4584664420663164928(0xc060000000000000, double:-128.0)
            java.lang.Double.isNaN(r0)
            double r0 = r0 / r2
            r2 = 0
            r3 = r26[r2]
            int r5 = (r3 > r0 ? 1 : (r3 == r0 ? 0 : -1))
            if (r5 >= 0) goto L_0x0844
            goto L_0x0846
        L_0x0844:
            r0 = r26[r2]
        L_0x0846:
            r26[r2] = r0
            r0 = -128(0xffffffffffffff80, float:NaN)
            goto L_0x084c
        L_0x084b:
            r2 = 0
        L_0x084c:
            r1 = 127(0x7f, float:1.78E-43)
            if (r1 >= r0) goto L_0x0867
            double r0 = (double) r0
            r3 = 4638637247447433216(0x405fc00000000000, double:127.0)
            java.lang.Double.isNaN(r0)
            double r0 = r0 / r3
            r3 = r26[r2]
            int r5 = (r3 > r0 ? 1 : (r3 == r0 ? 0 : -1))
            if (r5 >= 0) goto L_0x0861
            goto L_0x0863
        L_0x0861:
            r0 = r26[r2]
        L_0x0863:
            r26[r2] = r0
            r0 = 127(0x7f, float:1.78E-43)
        L_0x0867:
            int r0 = r0 + 128
            byte r0 = (byte) r0
            r14.put(r12, r0)
            int r4 = r27 + 1
            if (r4 != r15) goto L_0x0874
            r27 = 0
            goto L_0x0876
        L_0x0874:
            r27 = r4
        L_0x0876:
            int r12 = r12 + 1
            goto L_0x0812
        L_0x0879:
            if (r44 != 0) goto L_0x08fe
            if (r10 == 0) goto L_0x08db
            double r0 = (double) r8
            r77 = r6
            r76 = r7
            r4 = r64
            r3 = r88
            double r6 = (double) r3
            java.lang.Double.isNaN(r0)
            java.lang.Double.isNaN(r6)
            double r0 = r0 * r6
            java.lang.Double.isNaN(r4)
            double r0 = r0 / r4
            double r4 = r0 + r30
            r2 = r59
            int r6 = r2 + r13
            r78 = r9
            double r9 = (double) r6
            int r7 = (r4 > r9 ? 1 : (r4 == r9 ? 0 : -1))
            if (r7 <= 0) goto L_0x08b5
            r4 = 0
            r14.position(r4)
            int r0 = r86 * r15
            int r0 = r0 * r13
            r14.limit(r0)
            r36 = r41
            r7 = r83
            r11.a((java.io.OutputStream) r7, (java.nio.ByteBuffer) r14)
            r59 = r6
            goto L_0x08f9
        L_0x08b5:
            r4 = 0
            r7 = r83
            r14.position(r4)
            int r3 = r86 * r15
            double r3 = (double) r3
            double r0 = java.lang.Math.floor(r0)
            double r0 = r0 + r30
            double r5 = (double) r2
            java.lang.Double.isNaN(r5)
            double r0 = r0 - r5
            java.lang.Double.isNaN(r3)
            double r3 = r3 * r0
            int r0 = (int) r3
            if (r0 <= 0) goto L_0x08d7
            r14.limit(r0)
            r11.a((java.io.OutputStream) r7, (java.nio.ByteBuffer) r14)
        L_0x08d7:
            r5 = 4607182418800017408(0x3ff0000000000000, double:1.0)
            goto L_0x096d
        L_0x08db:
            r77 = r6
            r76 = r7
            r78 = r9
            r36 = r41
            r2 = r59
            r0 = 0
            r3 = r88
            r7 = r83
            r14.position(r0)
            int r0 = r86 * r15
            int r0 = r0 * r13
            r14.limit(r0)
            r11.a((java.io.OutputStream) r7, (java.nio.ByteBuffer) r14)
            int r59 = r2 + r13
        L_0x08f9:
            r4 = r7
        L_0x08fa:
            r5 = 4607182418800017408(0x3ff0000000000000, double:1.0)
            goto L_0x098e
        L_0x08fe:
            r77 = r6
            r76 = r7
            r78 = r9
            r36 = r41
            r2 = r59
            r0 = r60
            r4 = r64
            r3 = r88
            r7 = r83
            if (r13 >= r0) goto L_0x0917
            int r60 = r0 - r13
            r59 = r2
            goto L_0x08f9
        L_0x0917:
            if (r10 == 0) goto L_0x0974
            double r9 = (double) r8
            double r6 = (double) r3
            java.lang.Double.isNaN(r9)
            java.lang.Double.isNaN(r6)
            double r9 = r9 * r6
            java.lang.Double.isNaN(r4)
            double r9 = r9 / r4
            double r4 = r9 + r30
            int r59 = r2 + r13
            int r1 = r59 - r0
            double r6 = (double) r1
            int r1 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1))
            if (r1 <= 0) goto L_0x094a
            int r1 = r86 * r15
            int r4 = r1 * r0
            r14.position(r4)
            int r1 = r1 * r13
            r14.limit(r1)
            r4 = r83
            r11.a((java.io.OutputStream) r4, (java.nio.ByteBuffer) r14)
            int r1 = r13 - r0
            int r59 = r2 + r1
            r60 = r0
            goto L_0x08fa
        L_0x094a:
            r4 = r83
            int r1 = r86 * r15
            int r0 = r0 * r1
            r14.position(r0)
            double r0 = (double) r1
            double r5 = java.lang.Math.floor(r9)
            double r5 = r5 + r30
            double r2 = (double) r2
            java.lang.Double.isNaN(r2)
            double r5 = r5 - r2
            java.lang.Double.isNaN(r0)
            double r0 = r0 * r5
            int r0 = (int) r0
            r14.limit(r0)
            r11.a((java.io.OutputStream) r4, (java.nio.ByteBuffer) r14)
            goto L_0x08d7
        L_0x096d:
            r11.d(r5)
            r0 = 0
            r0 = r26[r0]
            return r0
        L_0x0974:
            r4 = r7
            r5 = 4607182418800017408(0x3ff0000000000000, double:1.0)
            int r1 = r86 * r15
            int r7 = r1 * r0
            r14.position(r7)
            int r1 = r1 * r13
            r14.limit(r1)
            r11.a((java.io.OutputStream) r4, (java.nio.ByteBuffer) r14)
            int r1 = r13 - r0
            int r59 = r2 + r1
            r60 = r0
            r44 = 0
        L_0x098e:
            int r9 = r78 + -1
            int r9 = r9 / r46
            boolean r0 = f17441a
            if (r0 != 0) goto L_0x09a1
            r0 = r76
            if (r0 < r9) goto L_0x099b
            goto L_0x09a3
        L_0x099b:
            java.lang.AssertionError r0 = new java.lang.AssertionError
            r0.<init>()
            throw r0
        L_0x09a1:
            r0 = r76
        L_0x09a3:
            int r1 = r15 * r9
            int r0 = r0 - r9
            int r2 = r15 * r0
            r7 = r77
            r10 = 0
            java.lang.System.arraycopy(r7, r1, r7, r10, r2)
            int r9 = r9 * r46
            int r9 = r78 - r9
            int r1 = r43 + 1
            r2 = 7
            r10 = r43 & 7
            if (r10 != r2) goto L_0x09cc
            double r5 = (double) r8
            r79 = r0
            r80 = r1
            r2 = r75
            double r0 = (double) r2
            java.lang.Double.isNaN(r5)
            java.lang.Double.isNaN(r0)
            double r5 = r5 / r0
            r11.d(r5)
            goto L_0x09d2
        L_0x09cc:
            r79 = r0
            r80 = r1
            r2 = r75
        L_0x09d2:
            r4 = r7
            r1 = r8
            r8 = r11
            r62 = r14
            r41 = r29
            r10 = r33
            r7 = r34
            r49 = r36
            r58 = r52
            r14 = r68
            r27 = r74
            r0 = r79
            r43 = r80
            r11 = r3
            r29 = r12
            r33 = r13
            r12 = r51
            r3 = r73
            r13 = r87
            goto L_0x0279
        L_0x09f6:
            r11 = r8
            r33 = r10
            r16 = r48
            r36 = r49
            r3 = r88
            r4 = r83
            r15 = r84
            int r2 = r2 * 2
            r13 = r87
            r14 = 0
            goto L_0x0178
        L_0x0a0a:
            r11 = r8
            r3 = r14
            r42 = 1
            java.lang.IllegalArgumentException r0 = new java.lang.IllegalArgumentException
            r1 = 6
            java.lang.Object[] r1 = new java.lang.Object[r1]
            java.lang.Integer r4 = java.lang.Integer.valueOf(r87)
            r5 = 0
            r1[r5] = r4
            java.lang.Integer r4 = java.lang.Integer.valueOf(r88)
            r1[r42] = r4
            java.lang.Integer r4 = java.lang.Integer.valueOf(r87)
            r1[r24] = r4
            java.lang.Integer r4 = java.lang.Integer.valueOf(r87)
            r1[r23] = r4
            java.lang.Integer r3 = java.lang.Integer.valueOf(r88)
            r1[r21] = r3
            java.lang.Integer r2 = java.lang.Integer.valueOf(r2)
            r1[r20] = r2
            java.lang.String r2 = "Resampling from %dHz to %dHz is not supported.\n%d/gcd(%d,%d)=%d must be divided by 2 or 3.\n"
            java.lang.String r1 = java.lang.String.format(r2, r1)
            r0.<init>(r1)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.smarthome.framework.plugin.rn.nativemodule.audio.SSRC.a(java.io.InputStream, java.io.OutputStream, int, int, int, int, int, double, int, boolean, int):double");
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v1, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r23v1, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r23v2, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r14v1, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r13v1, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v2, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r32v1, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r37v2, resolved type: int[]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r33v1, resolved type: int[]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r32v5, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r14v2, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r10v2, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r9v4, resolved type: double[][]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r7v3, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r6v5, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v9, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v4, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r36v1, resolved type: double[][]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r62v0, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v5, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r52v0, resolved type: double[][]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r52v1, resolved type: double[][]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r72v0, resolved type: double[]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r73v0, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r4v6, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r73v1, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r67v1, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r32v6, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r14v3, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r6v6, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v5, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r4v7, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v12, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v7, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r78v0, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r81v0, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r12v8, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r6v8, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r82v0, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v10, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v11, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v16, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r82v1, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r81v1, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r67v2, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r14v4, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r9v18, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v12, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v17, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r44v1, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r15v2, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r13v4, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r11v3, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v20, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v23, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v21, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r4v8, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r32v7, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v8, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v30, resolved type: java.lang.Object[]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v19, resolved type: java.lang.Object[]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r8v4, resolved type: java.lang.Object[]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v20, resolved type: double[]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r96v2, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v34, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r96v3, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r97v5, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r96v4, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r96v5, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r44v2, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r11v4, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r26v1, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r26v2, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r13v7, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v11, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r15v5, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r26v3, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v12, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r26v4, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r26v5, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r15v7, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v53, resolved type: double} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v67, resolved type: double} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r15v8, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r44v3, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r11v5, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r24v2, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v40, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r13v10, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r24v4, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v41, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v74, resolved type: double} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v88, resolved type: double} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r44v4, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r13v15, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r11v6, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v46, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r24v7, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v23, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v47, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r44v5, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r11v8, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r13v18, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r24v9, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v25, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v98, resolved type: double} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v112, resolved type: double} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r44v9, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r11v13, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r15v10, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r13v22, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r44v10, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r13v23, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r11v14, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v27, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r15v11, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r44v11, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r11v15, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r13v24, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v49, resolved type: double} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v50, resolved type: double} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v51, resolved type: double} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v53, resolved type: double} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v53, resolved type: double} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v56, resolved type: double} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v57, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r82v2, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v58, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v63, resolved type: double[]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r9v20, resolved type: double[]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r23v3, resolved type: double} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v64, resolved type: double[]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v56, resolved type: double[]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r23v4, resolved type: double} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r11v17, resolved type: double[]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r81v2, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r21v13, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r14v19, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v58, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v55, resolved type: double[]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v118, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v72, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v59, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r4v31, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v74, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v60, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v64, resolved type: double[]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v61, resolved type: double[]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v79, resolved type: double[]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v69, resolved type: double[]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r21v23, resolved type: double} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r11v26, resolved type: double[]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r23v8, resolved type: double} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r82v5, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v91, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v92, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v75, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v76, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v93, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r82v6, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v94, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r82v7, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v95, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v96, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v70, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v71, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v97, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r82v8, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r4v38, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v98, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r82v9, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v99, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v100, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v83, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v84, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v72, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r4v40, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v73, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v101, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v102, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v103, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r82v10, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r21v30, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r21v31, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v104, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r82v12, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v105, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v82, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v83, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r10v20, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r6v24, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v107, resolved type: int[]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v84, resolved type: int[]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v129, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r29v0, resolved type: double[][]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v85, resolved type: int[]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v108, resolved type: int[]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r33v2, resolved type: int[]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r37v3, resolved type: int[]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r7v24, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r28v6, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r29v3, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r29v4, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r29v5, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v108, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v112, resolved type: int[]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r33v4, resolved type: int[]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r7v26, resolved type: int} */
    /* JADX WARNING: Code restructure failed: missing block: B:100:0x046c, code lost:
        if (r1 >= r14) goto L_0x049b;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:102:0x0470, code lost:
        if (f17441a != false) goto L_0x047d;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:103:0x0472, code lost:
        r3 = r82;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:104:0x0474, code lost:
        if (r2 >= r3) goto L_0x0477;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:106:0x047c, code lost:
        throw new java.lang.AssertionError();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:107:0x047d, code lost:
        r3 = r82;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:108:0x047f, code lost:
        r36[r0][r1] = r13[(r2 * r5) + r0];
        r4 = r1 + 1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:109:0x048a, code lost:
        r9 = r1 + r15;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:110:0x048c, code lost:
        if (r4 >= r9) goto L_0x0495;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:111:0x048e, code lost:
        r36[r0][r4] = 0.0d;
        r4 = r4 + 1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:112:0x0495, code lost:
        r2 = r2 + 1;
        r82 = r3;
        r1 = r9;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:113:0x049b, code lost:
        r3 = r82;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:114:0x049f, code lost:
        if (f17441a != false) goto L_0x04aa;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:115:0x04a1, code lost:
        if (r2 != r3) goto L_0x04a4;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:117:0x04a9, code lost:
        throw new java.lang.AssertionError();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:118:0x04aa, code lost:
        r1 = r14;
        r4 = r67;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:119:0x04ad, code lost:
        if (r1 >= r4) goto L_0x04b6;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:120:0x04af, code lost:
        r36[r0][r1] = 0.0d;
        r1 = r1 + 1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:121:0x04b6, code lost:
        r12 = r101;
        r12.c.b(r4, 1, r36[r0], r50, r38);
        r36[r0][0] = r49[0] * r36[r0][0];
        r36[r0][r48] = r49[r48] * r36[r0][r48];
        r1 = 1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:122:0x04e3, code lost:
        if (r1 >= r14) goto L_0x0518;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:123:0x04e5, code lost:
        r2 = r1 * 2;
        r9 = r2 + 1;
        r36[r0][r2] = (r49[r2] * r36[r0][r2]) - (r49[r9] * r36[r0][r9]);
        r36[r0][r9] = (r49[r9] * r36[r0][r2]) + (r49[r2] * r36[r0][r9]);
        r1 = r1 + 1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:124:0x0518, code lost:
        r12.c.b(r4, -1, r36[r0], r50, r38);
        r1 = 0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:125:0x052a, code lost:
        if (r1 >= r14) goto L_0x053d;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:126:0x052c, code lost:
        r2 = r52[r0];
        r9 = r73 + r1;
        r2[r9] = r2[r9] + r36[r0][r1];
        r1 = r1 + 1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:127:0x053d, code lost:
        r2 = r84 / r41;
        r9 = r83 / r2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:128:0x0543, code lost:
        if ((r83 % r2) == 0) goto L_0x0547;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:129:0x0545, code lost:
        r9 = r9 + 1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:130:0x0547, code lost:
        r61 = 0;
        r11 = (r52[0].length * r0) + r9;
        r21 = r1;
        r85 = r3;
        r1 = r76;
        r9 = 0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:131:0x0556, code lost:
        r86 = r4;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:132:0x0561, code lost:
        if ((r11 - (r52[r61].length * r0)) >= (r14 + 1)) goto L_0x05ee;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:133:0x0563, code lost:
        r3 = r33[r1];
        r4 = r37[r1] + r11;
        r1 = r1 + 1;
        r87 = r10;
        r10 = r64;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:134:0x056e, code lost:
        if (r1 != r10) goto L_0x0571;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:135:0x0570, code lost:
        r1 = 0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:137:0x0573, code lost:
        if (f17441a != false) goto L_0x0598;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:138:0x0575, code lost:
        r88 = r1;
        r89 = r10;
        r10 = r14;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:139:0x058f, code lost:
        if ((((r11 - (r52[0].length * r0)) * r2) - (r83 + ((r84 / r108) * r9))) != r3) goto L_0x0592;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:141:0x0597, code lost:
        throw new java.lang.AssertionError();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:142:0x0598, code lost:
        r88 = r1;
        r89 = r10;
        r10 = r14;
        r14 = r108;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:143:0x059f, code lost:
        r23 = r11;
        r21 = 0.0d;
        r11 = r81;
        r1 = 0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:144:0x05a6, code lost:
        if (r1 >= r11) goto L_0x05cd;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:145:0x05a8, code lost:
        r21 = r21 + (r63[r3][r1] * r52[r23 / r52[0].length][r23 % r52[0].length]);
        r23 = r23 + 1;
        r1 = r1 + 1;
        r2 = r2;
        r3 = r3;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:146:0x05cd, code lost:
        r72[(0 + (r9 * r5)) + r0] = r21;
        r9 = r9 + 1;
        r21 = r1;
        r14 = r10;
        r81 = r11;
        r10 = r87;
        r1 = r88;
        r64 = r89;
        r2 = r2;
        r61 = 0;
        r11 = r4;
        r4 = r86;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:147:0x05ee, code lost:
        r87 = r10;
        r10 = r14;
        r89 = r64;
        r11 = r81;
        r14 = r108;
        r0 = r0 + 1;
        r14 = r10;
        r82 = r85;
        r67 = r86;
        r10 = r87;
        r11 = r1;
        r1 = r21;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:148:0x0605, code lost:
        r87 = r10;
        r10 = r14;
        r89 = r64;
        r86 = r67;
        r53 = r81;
        r12 = r101;
        r21 = r83 + ((r84 / r108) * r9);
        r80.clear();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:149:0x061b, code lost:
        if (r112 == false) goto L_0x065b;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:150:0x061d, code lost:
        r0 = 0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:152:0x0620, code lost:
        if (r0 >= (r9 * r5)) goto L_0x0647;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:154:0x0626, code lost:
        if (r72[r0] <= 0.0d) goto L_0x062c;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:155:0x0628, code lost:
        r1 = r72[r0];
     */
    /* JADX WARNING: Code restructure failed: missing block: B:157:0x062c, code lost:
        r1 = -r72[r0];
     */
    /* JADX WARNING: Code restructure failed: missing block: B:159:0x0634, code lost:
        if (r16[0] >= r1) goto L_0x0637;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:160:0x0637, code lost:
        r1 = r16[0];
     */
    /* JADX WARNING: Code restructure failed: missing block: B:161:0x0639, code lost:
        r16[0] = r1;
        r80.asDoubleBuffer().put(r0, r72[r0]);
        r0 = r0 + 1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:162:0x0647, code lost:
        r93 = r11;
        r94 = r13;
        r95 = r15;
        r44 = r62;
        r45 = r70;
        r11 = r73;
        r14 = r80;
        r47 = r86;
        r13 = r0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:163:0x0658, code lost:
        r15 = r5;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:164:0x065b, code lost:
        switch(r106) {
            case 1: goto L_0x07de;
            case 2: goto L_0x0744;
            case 3: goto L_0x0672;
            default: goto L_0x065e;
        };
     */
    /* JADX WARNING: Code restructure failed: missing block: B:165:0x065e, code lost:
        r93 = r11;
        r94 = r13;
        r95 = r15;
        r44 = r62;
        r45 = r70;
        r11 = r73;
        r14 = r80;
        r47 = r86;
        r15 = r5;
        r13 = r1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:166:0x0672, code lost:
        r22 = r109 * 8388607.0d;
        r4 = 0;
        r24 = 0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:168:0x067e, code lost:
        if (r4 >= (r9 * r5)) goto L_0x072b;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:169:0x0680, code lost:
        if (r113 == 0) goto L_0x06a3;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:170:0x0682, code lost:
        r44 = r62;
        r93 = r11;
        r94 = r13;
        r45 = r70;
        r11 = r73;
        r92 = r4;
        r47 = r86;
        r0 = a(r72[r4] * r22, r16, r113, r24);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:171:0x06a3, code lost:
        r92 = r4;
        r93 = r11;
        r94 = r13;
        r44 = r62;
        r45 = r70;
        r11 = r73;
        r47 = r86;
        r0 = a(r72[r92] * r22);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:172:0x06bc, code lost:
        if (r0 >= -8388608) goto L_0x06d2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:173:0x06be, code lost:
        r0 = (double) r0;
        java.lang.Double.isNaN(r0);
        r0 = r0 / -8388608.0d;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:174:0x06c9, code lost:
        if (r16[0] >= r0) goto L_0x06cc;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:175:0x06cc, code lost:
        r0 = r16[0];
     */
    /* JADX WARNING: Code restructure failed: missing block: B:176:0x06ce, code lost:
        r16[0] = r0;
        r0 = -8388608;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:178:0x06d5, code lost:
        if (8388607 >= r0) goto L_0x06ef;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:179:0x06d7, code lost:
        r0 = (double) r0;
        java.lang.Double.isNaN(r0);
        r0 = r0 / 8388607.0d;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:180:0x06e5, code lost:
        if (r16[0] >= r0) goto L_0x06e8;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:181:0x06e8, code lost:
        r0 = r16[0];
     */
    /* JADX WARNING: Code restructure failed: missing block: B:182:0x06ea, code lost:
        r16[0] = r0;
        r0 = 8388607;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:183:0x06ef, code lost:
        r4 = r92 * 3;
        r5 = r80;
        r5.put(r4, (byte) (r0 & 255));
        r0 = r0 >> 8;
        r5.put(r4 + 1, (byte) (r0 & 255));
        r5.put(r4 + 2, (byte) ((r0 >> 8) & 255));
        r4 = r24 + 1;
        r3 = r104;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:184:0x0711, code lost:
        if (r4 != r3) goto L_0x0716;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:185:0x0713, code lost:
        r24 = 0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:186:0x0716, code lost:
        r24 = r4;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:187:0x0718, code lost:
        r4 = r92 + 1;
        r80 = r5;
        r73 = r11;
        r62 = r44;
        r70 = r45;
        r86 = r47;
        r11 = r93;
        r13 = r94;
        r5 = r3;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:188:0x072b, code lost:
        r3 = r5;
        r93 = r11;
        r94 = r13;
        r44 = r62;
        r45 = r70;
        r11 = r73;
        r47 = r86;
        r95 = r15;
        r14 = r80;
        r13 = r4;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:189:0x0741, code lost:
        r15 = r3;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:190:0x0744, code lost:
        r3 = r5;
        r93 = r11;
        r94 = r13;
        r44 = r62;
        r45 = r70;
        r11 = r73;
        r5 = r80;
        r47 = r86;
        r22 = r109 * 32767.0d;
        r4 = 0;
        r24 = 0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:192:0x0760, code lost:
        if (r4 >= (r9 * r3)) goto L_0x07d8;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:193:0x0762, code lost:
        if (r113 == 0) goto L_0x0777;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:194:0x0764, code lost:
        r13 = r4;
        r14 = r5;
        r0 = a(r72[r4] * r22, r16, r113, r24);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:195:0x0777, code lost:
        r13 = r4;
        r14 = r5;
        r0 = a(r72[r13] * r22);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:196:0x0783, code lost:
        if (r0 >= -32768) goto L_0x079b;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:197:0x0785, code lost:
        r0 = (double) r0;
        java.lang.Double.isNaN(r0);
        r0 = r0 / -32768.0d;
        r2 = 0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:198:0x0791, code lost:
        if (r16[0] >= r0) goto L_0x0794;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:199:0x0794, code lost:
        r0 = r16[0];
     */
    /* JADX WARNING: Code restructure failed: missing block: B:200:0x0796, code lost:
        r16[0] = r0;
        r0 = -32768;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:201:0x079b, code lost:
        r2 = 0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:203:0x079e, code lost:
        if (32767 >= r0) goto L_0x07b7;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:204:0x07a0, code lost:
        r0 = (double) r0;
        java.lang.Double.isNaN(r0);
        r0 = r0 / 32767.0d;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:205:0x07ae, code lost:
        if (r16[r2] >= r0) goto L_0x07b1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:206:0x07b1, code lost:
        r0 = r16[r2];
     */
    /* JADX WARNING: Code restructure failed: missing block: B:207:0x07b3, code lost:
        r16[r2] = r0;
        r0 = javax.jmdns.impl.constants.DNSRecordClass.CLASS_MASK;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:208:0x07b7, code lost:
        r14.order(r12.b).asShortBuffer().put(r13, (short) r0);
        r4 = r24 + 1;
        r5 = r104;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:209:0x07c9, code lost:
        if (r4 != r5) goto L_0x07ce;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:210:0x07cb, code lost:
        r24 = 0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:211:0x07ce, code lost:
        r24 = r4;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:212:0x07d0, code lost:
        r4 = r13 + 1;
        r3 = r5;
        r5 = r14;
        r14 = r108;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:213:0x07d8, code lost:
        r13 = r4;
        r14 = r5;
        r95 = r15;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:214:0x07de, code lost:
        r93 = r11;
        r94 = r13;
        r44 = r62;
        r45 = r70;
        r11 = r73;
        r14 = r80;
        r47 = r86;
        r24 = r109 * 127.0d;
        r13 = 0;
        r26 = 0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:216:0x07f8, code lost:
        if (r13 >= (r9 * r5)) goto L_0x0862;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:217:0x07fa, code lost:
        if (r113 == 0) goto L_0x0810;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:218:0x07fc, code lost:
        r95 = r15;
        r15 = r5;
        r0 = a(r72[r13] * r24, r16, r113, r26);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:219:0x0810, code lost:
        r95 = r15;
        r15 = r5;
        r0 = a(r72[r13] * r24);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:220:0x081d, code lost:
        if (r0 >= -128) goto L_0x0835;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:221:0x081f, code lost:
        r0 = (double) r0;
        java.lang.Double.isNaN(r0);
        r0 = r0 / -128.0d;
        r2 = 0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:222:0x082b, code lost:
        if (r16[0] >= r0) goto L_0x082e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:223:0x082e, code lost:
        r0 = r16[0];
     */
    /* JADX WARNING: Code restructure failed: missing block: B:224:0x0830, code lost:
        r16[0] = r0;
        r0 = -128;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:225:0x0835, code lost:
        r2 = 0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:227:0x0838, code lost:
        if (127 >= r0) goto L_0x084d;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:228:0x083a, code lost:
        r0 = (double) r0;
        java.lang.Double.isNaN(r0);
        r0 = r0 / 127.0d;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:229:0x0844, code lost:
        if (r16[r2] >= r0) goto L_0x0847;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:230:0x0847, code lost:
        r0 = r16[r2];
     */
    /* JADX WARNING: Code restructure failed: missing block: B:231:0x0849, code lost:
        r16[r2] = r0;
        r0 = 127;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:232:0x084d, code lost:
        r14.put(r13, (byte) (r0 + 128));
        r4 = r26 + 1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:233:0x0855, code lost:
        if (r4 != r15) goto L_0x085a;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:234:0x0857, code lost:
        r26 = 0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:235:0x085a, code lost:
        r26 = r4;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:236:0x085c, code lost:
        r13 = r13 + 1;
        r5 = r15;
        r15 = r95;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:237:0x0862, code lost:
        r95 = r15;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:238:0x0866, code lost:
        if (r19 != false) goto L_0x08d5;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:239:0x0868, code lost:
        if (r8 == false) goto L_0x08b8;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:240:0x086a, code lost:
        r0 = (double) r7;
        java.lang.Double.isNaN(r0);
        java.lang.Double.isNaN(r34);
        java.lang.Double.isNaN(r65);
        r0 = (r0 * r34) / r65;
        r4 = r79 + r9;
        r96 = r6;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:241:0x0881, code lost:
        if ((r0 + 2.0d) <= ((double) r4)) goto L_0x0893;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:242:0x0883, code lost:
        r14.position(0);
        r14.limit(r69 * r9);
        a(r103, r14);
        r1 = r4;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:243:0x0893, code lost:
        r6 = r103;
        r14.position(0);
        r2 = (double) r79;
        java.lang.Double.isNaN(r2);
        java.lang.Double.isNaN(r45);
        r0 = (int) (r45 * ((java.lang.Math.floor(r0) + 2.0d) - r2));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:244:0x08ac, code lost:
        if (r0 <= 0) goto L_0x08b4;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:245:0x08ae, code lost:
        r14.limit(r0);
        a(r6, r14);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:247:0x08b8, code lost:
        r96 = r6;
        r14.position(0);
        r14.limit(r69 * r9);
        a(r103, r14);
        r1 = r79 + r9;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:248:0x08cd, code lost:
        r97 = r7;
        r0 = r77;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:250:0x08d5, code lost:
        r96 = r6;
        r0 = r77;
        r2 = r79;
        r6 = r103;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:251:0x08dd, code lost:
        if (r9 >= r0) goto L_0x08e4;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:252:0x08df, code lost:
        r0 = r0 - r9;
        r1 = r2;
        r97 = r7;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:253:0x08e4, code lost:
        if (r8 == false) goto L_0x0940;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:254:0x08e6, code lost:
        r3 = (double) r7;
        java.lang.Double.isNaN(r3);
        java.lang.Double.isNaN(r34);
        java.lang.Double.isNaN(r65);
        r3 = (r3 * r34) / r65;
        r97 = r7;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:255:0x08fe, code lost:
        if ((r3 + 2.0d) <= ((double) ((r2 + r9) - r0))) goto L_0x0911;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:256:0x0900, code lost:
        r14.position(r69 * r0);
        r1 = r9 - r0;
        r14.limit(r69 * r1);
        a(r6, r14);
        r1 = r1 + r2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:257:0x0911, code lost:
        r14.position(r69 * r0);
        r1 = (double) r2;
        java.lang.Double.isNaN(r1);
        r3 = (java.lang.Math.floor(r3) + 2.0d) + r1;
        r1 = (double) r9;
        java.lang.Double.isNaN(r1);
        r0 = (double) r0;
        java.lang.Double.isNaN(r0);
        java.lang.Double.isNaN(r45);
        r14.limit((int) (((r3 + r1) - r0) * r45));
        a(r6, r14);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:260:0x0940, code lost:
        r97 = r7;
        r14.position(r69 * r0);
        r14.limit(r69 * r9);
        a(r6, r14);
        r1 = (r9 - r0) + r2;
        r19 = false;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:261:0x0956, code lost:
        r7 = r84 / r41;
        r2 = (r21 - 1) / r7;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:262:0x095b, code lost:
        if (r2 <= r10) goto L_0x095e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:263:0x095d, code lost:
        r2 = r10;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:264:0x095e, code lost:
        r5 = 0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:265:0x095f, code lost:
        if (r5 >= r15) goto L_0x0974;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:266:0x0961, code lost:
        java.lang.System.arraycopy(r52[r5], r2, r52[r5], 0, r44 - r2);
        r5 = r5 + 1;
        r0 = r0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:267:0x0974, code lost:
        r98 = r0;
        r4 = r21 - (r2 * r7);
        r0 = 0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:268:0x097b, code lost:
        if (r0 >= r15) goto L_0x0987;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:269:0x097d, code lost:
        java.lang.System.arraycopy(r36[r0], r10, r52[r0], r11, r10);
        r0 = r0 + 1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:270:0x0987, code lost:
        r0 = r75 + 1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:271:0x098c, code lost:
        if ((r75 & 7) != 7) goto L_0x09a3;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:272:0x098e, code lost:
        r2 = r97;
        r7 = (double) r2;
        r100 = r0;
        r99 = r1;
        r3 = r96;
        r0 = (double) r3;
        java.lang.Double.isNaN(r7);
        java.lang.Double.isNaN(r0);
        d(r7 / r0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:273:0x09a3, code lost:
        r100 = r0;
        r99 = r1;
        r3 = r96;
        r2 = r97;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:79:0x03f0, code lost:
        r83 = r4;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:84:0x0423, code lost:
        r84 = r7;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:90:0x044e, code lost:
        if (r1 >= (r5 * r9)) goto L_0x0455;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:91:0x0450, code lost:
        r13[r1] = 0.0d;
        r1 = r1 + 1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:92:0x0455, code lost:
        r7 = r0 + r12;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:93:0x045b, code lost:
        if (r102.available() < 0) goto L_0x0462;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:94:0x045d, code lost:
        if (r7 < r6) goto L_0x0460;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:95:0x0460, code lost:
        r8 = false;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:96:0x0462, code lost:
        r8 = true;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:97:0x0463, code lost:
        r9 = r74;
        r11 = r76;
        r0 = 0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:98:0x0468, code lost:
        if (r0 >= r5) goto L_0x0605;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:99:0x046a, code lost:
        r1 = 0;
        r2 = 0;
     */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public double b(java.io.InputStream r102, java.io.OutputStream r103, int r104, int r105, int r106, int r107, int r108, double r109, int r111, boolean r112, int r113) throws java.io.IOException {
        /*
            r101 = this;
            r8 = r101
            r9 = r103
            r10 = r104
            r13 = r107
            r14 = r108
            r6 = 1
            double[] r7 = new double[r6]
            r17 = 0
            r4 = 0
            r7[r4] = r17
            int r0 = r8.g
            double r1 = r8.e
            int r19 = r8.a((int) r13, (int) r14)
            int r3 = r14 / r19
            r5 = 3
            r20 = 2
            if (r3 != r6) goto L_0x0023
            r5 = 1
            goto L_0x002d
        L_0x0023:
            int r21 = r3 % 2
            if (r21 != 0) goto L_0x0029
            r5 = 2
            goto L_0x002d
        L_0x0029:
            int r21 = r3 % 3
            if (r21 != 0) goto L_0x09ec
        L_0x002d:
            int r3 = r13 * r5
            r21 = 4626604192193052672(0x4035000000000000, double:21.0)
            int r23 = (r1 > r21 ? 1 : (r1 == r21 ? 0 : -1))
            r24 = 4624273579385888440(0x402cb851eb851eb8, double:14.36)
            r26 = 4620636922686786765(0x401fcccccccccccd, double:7.95)
            r28 = 4606481658697998559(0x3fed82a9930be0df, double:0.9222)
            if (r23 > 0) goto L_0x0047
            r30 = r28
            goto L_0x004d
        L_0x0047:
            r23 = 0
            double r30 = r1 - r26
            double r30 = r30 / r24
        L_0x004d:
            r23 = 1
        L_0x004f:
            int r32 = r0 * r23
            int r33 = r32 % 2
            if (r33 != 0) goto L_0x0057
            int r32 = r32 + -1
        L_0x0057:
            r9 = r32
            double r11 = (double) r3
            java.lang.Double.isNaN(r11)
            double r32 = r11 * r30
            int r4 = r9 + -1
            r36 = r7
            double r6 = (double) r4
            java.lang.Double.isNaN(r6)
            double r32 = r32 / r6
            double r6 = (double) r14
            java.lang.Double.isNaN(r6)
            double r37 = r6 - r32
            r39 = 4611686018427387904(0x4000000000000000, double:2.0)
            double r37 = r37 / r39
            r41 = r3
            double r3 = r8.f
            int r42 = (r32 > r3 ? 1 : (r32 == r3 ? 0 : -1))
            if (r42 >= 0) goto L_0x09d3
            double r30 = r8.b((double) r1)
            double r32 = com.xiaomi.smarthome.framework.plugin.rn.nativemodule.audio.I0Bessel.a(r30)
            r0 = 1
        L_0x0084:
            if (r0 >= r9) goto L_0x0089
            int r0 = r0 * 2
            goto L_0x0084
        L_0x0089:
            int r4 = r0 * 2
            double[] r3 = new double[r4]
            int r1 = r9 / 2
            int r0 = -r1
            r2 = r0
        L_0x0091:
            if (r2 > r1) goto L_0x00e1
            int r23 = r2 + r1
            r49 = r3
            r48 = r4
            double r3 = (double) r2
            r0 = r101
            r42 = r1
            r50 = r2
            r1 = r3
            r3 = r9
            r15 = r5
            r51 = r9
            r9 = r48
            r10 = 0
            r4 = r30
            r34 = r6
            r16 = r36
            r6 = r32
            double r6 = r0.a((double) r1, (int) r3, (double) r4, (double) r6)
            r1 = r50
            r2 = r37
            r4 = r11
            double r0 = r0.a(r1, r2, r4)
            double r6 = r6 * r0
            java.lang.Double.isNaN(r11)
            double r6 = r6 * r11
            double r0 = (double) r13
            java.lang.Double.isNaN(r0)
            double r6 = r6 / r0
            double r0 = (double) r9
            java.lang.Double.isNaN(r0)
            double r6 = r6 / r0
            double r6 = r6 * r39
            r49[r23] = r6
            int r2 = r50 + 1
            r4 = r9
            r5 = r15
            r6 = r34
            r1 = r42
            r3 = r49
            r9 = r51
            r10 = r104
            goto L_0x0091
        L_0x00e1:
            r50 = r2
            r49 = r3
            r15 = r5
            r34 = r6
            r51 = r9
            r16 = r36
            r10 = 0
            r9 = r4
            double r0 = (double) r9
            double r0 = java.lang.Math.sqrt(r0)
            double r0 = r0 + r39
            int r0 = (int) r0
            int[] r6 = new int[r0]
            r6[r10] = r10
            int r7 = r9 / 2
            double[] r4 = new double[r7]
            com.xiaomi.smarthome.framework.plugin.rn.nativemodule.audio.SplitRadixFft r0 = r8.c
            r44 = 1
            r42 = r0
            r43 = r9
            r45 = r49
            r46 = r6
            r47 = r4
            r42.b((int) r43, (int) r44, (double[]) r45, (int[]) r46, (double[]) r47)
            r5 = 1
            if (r15 != r5) goto L_0x014a
            int r0 = r13 / r19
            int r0 = r0 * r14
            int[] r3 = new int[r5]
            r3[r10] = r10
            int[] r1 = new int[r5]
            int r2 = r13 / r14
            r1[r10] = r2
            int[] r2 = new int[]{r5, r5}
            java.lang.Class<double> r5 = double.class
            java.lang.Object r2 = java.lang.reflect.Array.newInstance(r5, r2)
            double[][] r2 = (double[][]) r2
            r5 = r2[r10]
            r21 = 4607182418800017408(0x3ff0000000000000, double:1.0)
            r5[r10] = r21
            r37 = r1
            r33 = r3
            r38 = r4
            r14 = r7
            r56 = r9
            r32 = r50
            r10 = 1
            r27 = 0
            r48 = 1
            r7 = r0
            r9 = r2
            r50 = r6
            r0 = 1
            r6 = 1
            goto L_0x0279
        L_0x014a:
            double r0 = r8.e
            int r2 = r13 / r19
            int r5 = r2 * r14
            int r3 = r41 / 2
            int r2 = r13 / 2
            int r3 = r3 - r2
            int r10 = r3 * 2
            r54 = r6
            r55 = r7
            double r6 = (double) r10
            java.lang.Double.isNaN(r6)
            double r6 = r6 / r39
            r56 = r9
            double r9 = (double) r2
            double r2 = (double) r3
            java.lang.Double.isNaN(r2)
            double r2 = r2 / r39
            java.lang.Double.isNaN(r9)
            double r9 = r9 + r2
            int r2 = (r0 > r21 ? 1 : (r0 == r21 ? 0 : -1))
            if (r2 > 0) goto L_0x0173
            goto L_0x0178
        L_0x0173:
            r2 = 0
            double r2 = r0 - r26
            double r28 = r2 / r24
        L_0x0178:
            double r2 = (double) r5
            java.lang.Double.isNaN(r2)
            double r6 = r2 / r6
            double r6 = r6 * r28
            r21 = 4607182418800017408(0x3ff0000000000000, double:1.0)
            double r6 = r6 + r21
            int r6 = (int) r6
            int r7 = r6 % 2
            if (r7 != 0) goto L_0x018b
            int r6 = r6 + 1
        L_0x018b:
            r19 = r6
            double r23 = r8.b((double) r0)
            double r25 = com.xiaomi.smarthome.framework.plugin.rn.nativemodule.audio.I0Bessel.a(r23)
            int r6 = r5 / r41
            int r0 = r19 / r6
            r1 = 1
            int r7 = r0 + 1
            int[] r1 = new int[r6]
            r0 = 0
        L_0x019f:
            if (r0 >= r6) goto L_0x01bd
            int r27 = r5 / r14
            int r27 = r27 * r0
            int r27 = r27 % r6
            int r27 = r6 - r27
            r1[r0] = r27
            r57 = r2
            r2 = r1[r0]
            if (r2 != r6) goto L_0x01b6
            r27 = 0
            r1[r0] = r27
            goto L_0x01b8
        L_0x01b6:
            r27 = 0
        L_0x01b8:
            int r0 = r0 + 1
            r2 = r57
            goto L_0x019f
        L_0x01bd:
            r57 = r2
            r27 = 0
            int[] r3 = new int[r6]
            r0 = 0
        L_0x01c4:
            if (r0 >= r6) goto L_0x01e8
            int r2 = r5 / r14
            r28 = r1[r0]
            int r2 = r2 - r28
            int r2 = r2 / r6
            r28 = 1
            int r2 = r2 + 1
            r3[r0] = r2
            int r2 = r0 + 1
            if (r2 != r6) goto L_0x01da
            r29 = 0
            goto L_0x01dc
        L_0x01da:
            r29 = r2
        L_0x01dc:
            r29 = r1[r29]
            if (r29 != 0) goto L_0x01e6
            r29 = r3[r0]
            int r29 = r29 + -1
            r3[r0] = r29
        L_0x01e6:
            r0 = r2
            goto L_0x01c4
        L_0x01e8:
            r28 = 1
            int[] r0 = new int[]{r6, r7}
            java.lang.Class<double> r2 = double.class
            java.lang.Object r0 = java.lang.reflect.Array.newInstance(r2, r0)
            r29 = r0
            double[][] r29 = (double[][]) r29
            int r2 = r19 / 2
            int r0 = -r2
        L_0x01fb:
            if (r0 > r2) goto L_0x025b
            int r30 = r0 + r2
            int r31 = r30 % r6
            r31 = r29[r31]
            int r30 = r30 / r6
            r59 = r1
            r60 = r2
            double r1 = (double) r0
            r32 = r0
            r0 = r101
            r21 = r57
            r33 = r59
            r36 = r60
            r37 = r3
            r3 = r19
            r38 = r4
            r28 = r5
            r48 = 1
            r4 = r23
            r42 = r6
            r43 = r7
            r50 = r54
            r14 = r55
            r6 = r25
            double r6 = r0.a((double) r1, (int) r3, (double) r4, (double) r6)
            r1 = r32
            r2 = r9
            r4 = r21
            double r0 = r0.a(r1, r2, r4)
            double r6 = r6 * r0
            java.lang.Double.isNaN(r21)
            double r6 = r6 * r21
            java.lang.Double.isNaN(r11)
            double r6 = r6 / r11
            r31[r30] = r6
            int r0 = r32 + 1
            r5 = r28
            r1 = r33
            r2 = r36
            r3 = r37
            r4 = r38
            r6 = r42
            r7 = r43
            r14 = r108
            r21 = 4607182418800017408(0x3ff0000000000000, double:1.0)
            r28 = 1
            goto L_0x01fb
        L_0x025b:
            r32 = r0
            r33 = r1
            r37 = r3
            r38 = r4
            r28 = r5
            r42 = r6
            r43 = r7
            r50 = r54
            r14 = r55
            r48 = 1
            r0 = r19
            r7 = r28
            r9 = r29
            r10 = r42
            r6 = r43
        L_0x0279:
            r101.b()
            r4 = r56
            r3 = 0
            r5 = r104
            int[] r1 = new int[]{r5, r4}
            java.lang.Class<double> r2 = double.class
            java.lang.Object r1 = java.lang.reflect.Array.newInstance(r2, r1)
            r36 = r1
            double[][] r36 = (double[][]) r36
            int r1 = r6 + 1
            int r2 = r1 + r14
            int[] r3 = new int[]{r5, r2}
            r62 = r2
            java.lang.Class<double> r2 = double.class
            java.lang.Object r2 = java.lang.reflect.Array.newInstance(r2, r3)
            r52 = r2
            double[][] r52 = (double[][]) r52
            int r2 = r14 / r15
            int r2 = r2 + r15
            int r2 = r2 + 1
            int r2 = r2 * r5
            r21 = r11
            int r3 = r2 * r105
            java.nio.ByteBuffer r3 = java.nio.ByteBuffer.allocate(r3)
            r63 = r9
            r64 = r10
            double r9 = (double) r14
            java.lang.Double.isNaN(r9)
            java.lang.Double.isNaN(r34)
            double r9 = r9 * r34
            double r12 = (double) r13
            java.lang.Double.isNaN(r12)
            double r9 = r9 / r12
            r65 = r12
            r12 = 4607182418800017408(0x3ff0000000000000, double:1.0)
            double r9 = r9 + r12
            int r12 = r106 * r5
            r68 = r3
            r67 = r4
            double r3 = (double) r12
            java.lang.Double.isNaN(r3)
            r69 = r12
            double r12 = r9 * r3
            int r12 = (int) r12
            java.nio.ByteBuffer r12 = java.nio.ByteBuffer.allocate(r12)
            double[] r13 = new double[r2]
            r70 = r3
            double r2 = (double) r5
            java.lang.Double.isNaN(r2)
            double r2 = r2 * r9
            int r2 = (int) r2
            double[] r9 = new double[r2]
            r2 = r51
            double r2 = (double) r2
            java.lang.Double.isNaN(r2)
            double r2 = r2 / r39
            java.lang.Double.isNaN(r21)
            java.lang.Double.isNaN(r34)
            double r21 = r21 / r34
            double r2 = r2 / r21
            r72 = r9
            double r9 = (double) r0
            java.lang.Double.isNaN(r9)
            double r9 = r9 / r39
            r73 = r1
            double r0 = (double) r7
            java.lang.Double.isNaN(r0)
            java.lang.Double.isNaN(r34)
            double r0 = r0 / r34
            double r9 = r9 / r0
            double r2 = r2 + r9
            int r0 = (int) r2
            r1 = r111
            r9 = r0
            r2 = r68
            r0 = 0
            r3 = 0
            r4 = 0
            r10 = 0
            r19 = 1
            r74 = 0
            r75 = 0
        L_0x0320:
            int r21 = r14 + 0
            int r21 = r21 + -1
            int r21 = r21 / r15
            r76 = r3
            int r3 = r21 + 1
            r77 = r9
            int r9 = r3 + r0
            if (r9 <= r1) goto L_0x0335
            int r9 = r1 - r0
            r78 = r1
            goto L_0x0338
        L_0x0335:
            r78 = r1
            r9 = r3
        L_0x0338:
            r1 = 0
            r2.position(r1)
            int r1 = r105 * r5
            r79 = r10
            int r10 = r1 * r9
            r2.limit(r10)
            int r10 = r2.limit()
            byte[] r10 = new byte[r10]
            r80 = r12
            r12 = r102
            int r21 = r12.read(r10)
            if (r21 >= 0) goto L_0x0359
            r81 = r6
            r12 = 0
            goto L_0x035d
        L_0x0359:
            r81 = r6
            r12 = r21
        L_0x035d:
            int r6 = r2.limit()
            if (r12 >= r6) goto L_0x0369
            int r6 = r12 / r105
            int r6 = r6 * r5
            int r6 = r6 + r0
            goto L_0x036b
        L_0x0369:
            r6 = r78
        L_0x036b:
            r2.limit(r12)
            java.nio.ByteBuffer r10 = java.nio.ByteBuffer.wrap(r10)
            r10.position(r12)
            r10.flip()
            int r12 = r12 / r1
            switch(r105) {
                case 1: goto L_0x0426;
                case 2: goto L_0x03f3;
                case 3: goto L_0x03b0;
                case 4: goto L_0x0386;
                default: goto L_0x037c;
            }
        L_0x037c:
            r82 = r3
            r83 = r4
            r84 = r7
            r1 = r32
            goto L_0x044c
        L_0x0386:
            r1 = 0
        L_0x0387:
            int r2 = r12 * r5
            if (r1 >= r2) goto L_0x03ad
            java.nio.ByteOrder r2 = r8.b
            java.nio.ByteBuffer r2 = r10.order(r2)
            int r2 = r2.getInt(r1)
            int r21 = r5 * 0
            int r21 = r21 + r1
            r22 = 4467570830353629184(0x3e00000000200000, double:4.656612875245797E-10)
            r82 = r3
            double r2 = (double) r2
            java.lang.Double.isNaN(r2)
            double r2 = r2 * r22
            r13[r21] = r2
            int r1 = r1 + 1
            r3 = r82
            goto L_0x0387
        L_0x03ad:
            r82 = r3
            goto L_0x03f0
        L_0x03b0:
            r82 = r3
            r1 = 0
        L_0x03b3:
            int r2 = r12 * r5
            if (r1 >= r2) goto L_0x03f0
            int r2 = r5 * 0
            int r2 = r2 + r1
            r21 = 4503599627907366976(0x3e80000020000040, double:1.1920930376163766E-7)
            int r3 = r1 * 3
            byte r11 = r10.get(r3)
            r11 = r11 & 255(0xff, float:3.57E-43)
            r23 = 0
            int r11 = r11 << 0
            r83 = r4
            int r4 = r3 + 1
            byte r4 = r10.get(r4)
            r4 = r4 & 255(0xff, float:3.57E-43)
            int r4 = r4 << 8
            r4 = r4 | r11
            int r3 = r3 + 2
            byte r3 = r10.get(r3)
            r3 = r3 & 255(0xff, float:3.57E-43)
            int r3 = r3 << 16
            r3 = r3 | r4
            double r3 = (double) r3
            java.lang.Double.isNaN(r3)
            double r3 = r3 * r21
            r13[r2] = r3
            int r1 = r1 + 1
            r4 = r83
            goto L_0x03b3
        L_0x03f0:
            r83 = r4
            goto L_0x0423
        L_0x03f3:
            r82 = r3
            r83 = r4
            r1 = 0
        L_0x03f8:
            int r2 = r12 * r5
            if (r1 >= r2) goto L_0x0423
            java.nio.ByteOrder r2 = r8.b
            java.nio.ByteBuffer r2 = r10.order(r2)
            java.nio.ShortBuffer r2 = r2.asShortBuffer()
            short r2 = r2.get(r1)
            int r3 = r5 * 0
            int r3 = r3 + r1
            r21 = 4539628561832607872(0x3f00002000400080, double:3.051850947599719E-5)
            r84 = r7
            double r7 = (double) r2
            java.lang.Double.isNaN(r7)
            double r7 = r7 * r21
            r13[r3] = r7
            int r1 = r1 + 1
            r7 = r84
            r8 = r101
            goto L_0x03f8
        L_0x0423:
            r84 = r7
            goto L_0x044c
        L_0x0426:
            r82 = r3
            r83 = r4
            r84 = r7
            r1 = 0
        L_0x042d:
            int r2 = r12 * r5
            if (r1 >= r2) goto L_0x044c
            int r2 = r5 * 0
            int r2 = r2 + r1
            r3 = 4575692682822812680(0x3f80204081020408, double:0.007874015748031496)
            byte r7 = r10.get(r1)
            r7 = r7 & 255(0xff, float:3.57E-43)
            int r7 = r7 + -128
            double r7 = (double) r7
            java.lang.Double.isNaN(r7)
            double r7 = r7 * r3
            r13[r2] = r7
            int r1 = r1 + 1
            goto L_0x042d
        L_0x044c:
            int r2 = r5 * r9
            if (r1 >= r2) goto L_0x0455
            r13[r1] = r17
            int r1 = r1 + 1
            goto L_0x044c
        L_0x0455:
            int r7 = r0 + r12
            int r0 = r102.available()
            if (r0 < 0) goto L_0x0462
            if (r7 < r6) goto L_0x0460
            goto L_0x0462
        L_0x0460:
            r8 = 0
            goto L_0x0463
        L_0x0462:
            r8 = 1
        L_0x0463:
            r9 = r74
            r11 = r76
            r0 = 0
        L_0x0468:
            if (r0 >= r5) goto L_0x0605
            r1 = 0
            r2 = 0
        L_0x046c:
            if (r1 >= r14) goto L_0x049b
            boolean r3 = f17441a
            if (r3 != 0) goto L_0x047d
            r3 = r82
            if (r2 >= r3) goto L_0x0477
            goto L_0x047f
        L_0x0477:
            java.lang.AssertionError r0 = new java.lang.AssertionError
            r0.<init>()
            throw r0
        L_0x047d:
            r3 = r82
        L_0x047f:
            r4 = r36[r0]
            int r9 = r2 * r5
            int r9 = r9 + r0
            r11 = r13[r9]
            r4[r1] = r11
            int r4 = r1 + 1
        L_0x048a:
            int r9 = r1 + r15
            if (r4 >= r9) goto L_0x0495
            r9 = r36[r0]
            r9[r4] = r17
            int r4 = r4 + 1
            goto L_0x048a
        L_0x0495:
            int r2 = r2 + 1
            r82 = r3
            r1 = r9
            goto L_0x046c
        L_0x049b:
            r3 = r82
            boolean r1 = f17441a
            if (r1 != 0) goto L_0x04aa
            if (r2 != r3) goto L_0x04a4
            goto L_0x04aa
        L_0x04a4:
            java.lang.AssertionError r0 = new java.lang.AssertionError
            r0.<init>()
            throw r0
        L_0x04aa:
            r1 = r14
            r4 = r67
        L_0x04ad:
            if (r1 >= r4) goto L_0x04b6
            r2 = r36[r0]
            r2[r1] = r17
            int r1 = r1 + 1
            goto L_0x04ad
        L_0x04b6:
            r12 = r101
            com.xiaomi.smarthome.framework.plugin.rn.nativemodule.audio.SplitRadixFft r1 = r12.c
            r44 = 1
            r45 = r36[r0]
            r42 = r1
            r43 = r4
            r46 = r50
            r47 = r38
            r42.b((int) r43, (int) r44, (double[]) r45, (int[]) r46, (double[]) r47)
            r1 = r36[r0]
            r2 = 0
            r21 = r49[r2]
            r9 = r36[r0]
            r23 = r9[r2]
            double r21 = r21 * r23
            r1[r2] = r21
            r1 = r36[r0]
            r21 = r49[r48]
            r2 = r36[r0]
            r23 = r2[r48]
            double r21 = r21 * r23
            r1[r48] = r21
            r1 = 1
        L_0x04e3:
            if (r1 >= r14) goto L_0x0518
            int r2 = r1 * 2
            r21 = r49[r2]
            r9 = r36[r0]
            r23 = r9[r2]
            double r21 = r21 * r23
            int r9 = r2 + 1
            r23 = r49[r9]
            r11 = r36[r0]
            r25 = r11[r9]
            double r23 = r23 * r25
            double r21 = r21 - r23
            r23 = r49[r9]
            r11 = r36[r0]
            r25 = r11[r2]
            double r23 = r23 * r25
            r25 = r49[r2]
            r11 = r36[r0]
            r27 = r11[r9]
            double r25 = r25 * r27
            double r23 = r23 + r25
            r11 = r36[r0]
            r11[r2] = r21
            r2 = r36[r0]
            r2[r9] = r23
            int r1 = r1 + 1
            goto L_0x04e3
        L_0x0518:
            com.xiaomi.smarthome.framework.plugin.rn.nativemodule.audio.SplitRadixFft r1 = r12.c
            r44 = -1
            r45 = r36[r0]
            r42 = r1
            r43 = r4
            r46 = r50
            r47 = r38
            r42.b((int) r43, (int) r44, (double[]) r45, (int[]) r46, (double[]) r47)
            r1 = 0
        L_0x052a:
            if (r1 >= r14) goto L_0x053d
            r2 = r52[r0]
            int r9 = r73 + r1
            r21 = r2[r9]
            r11 = r36[r0]
            r23 = r11[r1]
            double r21 = r21 + r23
            r2[r9] = r21
            int r1 = r1 + 1
            goto L_0x052a
        L_0x053d:
            int r2 = r84 / r41
            int r9 = r83 / r2
            int r11 = r83 % r2
            if (r11 == 0) goto L_0x0547
            int r9 = r9 + 1
        L_0x0547:
            r61 = 0
            r11 = r52[r61]
            int r11 = r11.length
            int r11 = r11 * r0
            int r11 = r11 + r9
            r21 = r1
            r85 = r3
            r1 = r76
            r9 = 0
        L_0x0556:
            r3 = r52[r61]
            int r3 = r3.length
            int r3 = r3 * r0
            int r3 = r11 - r3
            r86 = r4
            int r4 = r14 + 1
            if (r3 >= r4) goto L_0x05ee
            r3 = r33[r1]
            r4 = r37[r1]
            int r4 = r4 + r11
            int r1 = r1 + 1
            r87 = r10
            r10 = r64
            if (r1 != r10) goto L_0x0571
            r1 = 0
        L_0x0571:
            boolean r21 = f17441a
            if (r21 != 0) goto L_0x0598
            r88 = r1
            r21 = 0
            r1 = r52[r21]
            int r1 = r1.length
            int r1 = r1 * r0
            int r1 = r11 - r1
            int r1 = r1 * r2
            r89 = r10
            r10 = r14
            r14 = r108
            int r21 = r84 / r14
            int r21 = r21 * r9
            int r21 = r83 + r21
            int r1 = r1 - r21
            if (r1 != r3) goto L_0x0592
            goto L_0x059f
        L_0x0592:
            java.lang.AssertionError r0 = new java.lang.AssertionError
            r0.<init>()
            throw r0
        L_0x0598:
            r88 = r1
            r89 = r10
            r10 = r14
            r14 = r108
        L_0x059f:
            r23 = r11
            r21 = r17
            r11 = r81
            r1 = 0
        L_0x05a6:
            if (r1 >= r11) goto L_0x05cd
            r24 = r63[r3]
            r25 = r24[r1]
            r90 = r2
            r24 = 0
            r2 = r52[r24]
            int r2 = r2.length
            int r2 = r23 / r2
            r2 = r52[r2]
            r91 = r3
            r3 = r52[r24]
            int r3 = r3.length
            int r3 = r23 % r3
            r27 = r2[r3]
            double r25 = r25 * r27
            double r21 = r21 + r25
            int r23 = r23 + 1
            int r1 = r1 + 1
            r2 = r90
            r3 = r91
            goto L_0x05a6
        L_0x05cd:
            r90 = r2
            r24 = 0
            int r2 = r9 * r5
            int r2 = r24 + r2
            int r2 = r2 + r0
            r72[r2] = r21
            int r9 = r9 + 1
            r21 = r1
            r14 = r10
            r81 = r11
            r10 = r87
            r1 = r88
            r64 = r89
            r2 = r90
            r61 = 0
            r11 = r4
            r4 = r86
            goto L_0x0556
        L_0x05ee:
            r87 = r10
            r10 = r14
            r89 = r64
            r11 = r81
            r14 = r108
            int r0 = r0 + 1
            r14 = r10
            r82 = r85
            r67 = r86
            r10 = r87
            r11 = r1
            r1 = r21
            goto L_0x0468
        L_0x0605:
            r87 = r10
            r10 = r14
            r89 = r64
            r86 = r67
            r53 = r81
            r12 = r101
            r14 = r108
            int r0 = r84 / r14
            int r0 = r0 * r9
            int r21 = r83 + r0
            r80.clear()
            if (r112 == 0) goto L_0x065b
            r0 = 0
        L_0x061e:
            int r1 = r9 * r5
            if (r0 >= r1) goto L_0x0647
            r1 = r72[r0]
            int r3 = (r1 > r17 ? 1 : (r1 == r17 ? 0 : -1))
            if (r3 <= 0) goto L_0x062c
            r1 = r72[r0]
        L_0x062a:
            r3 = 0
            goto L_0x0630
        L_0x062c:
            r1 = r72[r0]
            double r1 = -r1
            goto L_0x062a
        L_0x0630:
            r22 = r16[r3]
            int r4 = (r22 > r1 ? 1 : (r22 == r1 ? 0 : -1))
            if (r4 >= 0) goto L_0x0637
            goto L_0x0639
        L_0x0637:
            r1 = r16[r3]
        L_0x0639:
            r16[r3] = r1
            java.nio.DoubleBuffer r1 = r80.asDoubleBuffer()
            r3 = r72[r0]
            r1.put(r0, r3)
            int r0 = r0 + 1
            goto L_0x061e
        L_0x0647:
            r93 = r11
            r94 = r13
            r95 = r15
            r44 = r62
            r45 = r70
            r11 = r73
            r14 = r80
            r47 = r86
            r13 = r0
        L_0x0658:
            r15 = r5
            goto L_0x0866
        L_0x065b:
            switch(r106) {
                case 1: goto L_0x07de;
                case 2: goto L_0x0744;
                case 3: goto L_0x0672;
                default: goto L_0x065e;
            }
        L_0x065e:
            r93 = r11
            r94 = r13
            r95 = r15
            r44 = r62
            r45 = r70
            r11 = r73
            r14 = r80
            r47 = r86
            r15 = r5
            r13 = r1
            goto L_0x0866
        L_0x0672:
            r0 = 4710765209155796992(0x415fffffc0000000, double:8388607.0)
            double r22 = r109 * r0
            r4 = 0
            r24 = 0
        L_0x067c:
            int r0 = r9 * r5
            if (r4 >= r0) goto L_0x072b
            if (r113 == 0) goto L_0x06a3
            r0 = r72[r4]
            double r1 = r0 * r22
            r0 = r101
            r44 = r62
            r3 = r73
            r93 = r11
            r94 = r13
            r45 = r70
            r13 = 0
            r11 = r3
            r3 = r16
            r92 = r4
            r47 = r86
            r4 = r113
            r5 = r24
            int r0 = r0.a((double) r1, (double[]) r3, (int) r4, (int) r5)
            goto L_0x06ef
        L_0x06a3:
            r92 = r4
            r93 = r11
            r94 = r13
            r44 = r62
            r45 = r70
            r11 = r73
            r47 = r86
            r13 = 0
            r0 = r72[r92]
            double r0 = r0 * r22
            int r0 = r12.a((double) r0)
            r1 = -8388608(0xffffffffff800000, float:-Infinity)
            if (r0 >= r1) goto L_0x06d2
            double r0 = (double) r0
            r2 = -4512606826625236992(0xc160000000000000, double:-8388608.0)
            java.lang.Double.isNaN(r0)
            double r0 = r0 / r2
            r2 = r16[r13]
            int r4 = (r2 > r0 ? 1 : (r2 == r0 ? 0 : -1))
            if (r4 >= 0) goto L_0x06cc
            goto L_0x06ce
        L_0x06cc:
            r0 = r16[r13]
        L_0x06ce:
            r16[r13] = r0
            r0 = -8388608(0xffffffffff800000, float:-Infinity)
        L_0x06d2:
            r1 = 8388607(0x7fffff, float:1.1754942E-38)
            if (r1 >= r0) goto L_0x06ef
            double r0 = (double) r0
            r2 = 4710765209155796992(0x415fffffc0000000, double:8388607.0)
            java.lang.Double.isNaN(r0)
            double r0 = r0 / r2
            r2 = r16[r13]
            int r4 = (r2 > r0 ? 1 : (r2 == r0 ? 0 : -1))
            if (r4 >= 0) goto L_0x06e8
            goto L_0x06ea
        L_0x06e8:
            r0 = r16[r13]
        L_0x06ea:
            r16[r13] = r0
            r0 = 8388607(0x7fffff, float:1.1754942E-38)
        L_0x06ef:
            int r4 = r92 * 3
            r1 = r0 & 255(0xff, float:3.57E-43)
            byte r1 = (byte) r1
            r5 = r80
            r5.put(r4, r1)
            int r0 = r0 >> 8
            int r1 = r4 + 1
            r2 = r0 & 255(0xff, float:3.57E-43)
            byte r2 = (byte) r2
            r5.put(r1, r2)
            int r0 = r0 >> 8
            int r4 = r4 + 2
            r0 = r0 & 255(0xff, float:3.57E-43)
            byte r0 = (byte) r0
            r5.put(r4, r0)
            int r4 = r24 + 1
            r3 = r104
            if (r4 != r3) goto L_0x0716
            r24 = 0
            goto L_0x0718
        L_0x0716:
            r24 = r4
        L_0x0718:
            int r4 = r92 + 1
            r80 = r5
            r73 = r11
            r62 = r44
            r70 = r45
            r86 = r47
            r11 = r93
            r13 = r94
            r5 = r3
            goto L_0x067c
        L_0x072b:
            r92 = r4
            r3 = r5
            r93 = r11
            r94 = r13
            r44 = r62
            r45 = r70
            r11 = r73
            r47 = r86
            r13 = 0
            r95 = r15
            r14 = r80
            r13 = r92
        L_0x0741:
            r15 = r3
            goto L_0x0866
        L_0x0744:
            r3 = r5
            r93 = r11
            r94 = r13
            r44 = r62
            r45 = r70
            r11 = r73
            r5 = r80
            r47 = r86
            r13 = 0
            r0 = 4674736138332667904(0x40dfffc000000000, double:32767.0)
            double r22 = r109 * r0
            r4 = 0
            r24 = 0
        L_0x075e:
            int r0 = r9 * r3
            if (r4 >= r0) goto L_0x07d8
            if (r113 == 0) goto L_0x0777
            r0 = r72[r4]
            double r1 = r0 * r22
            r0 = r101
            r3 = r16
            r13 = r4
            r4 = r113
            r14 = r5
            r5 = r24
            int r0 = r0.a((double) r1, (double[]) r3, (int) r4, (int) r5)
            goto L_0x07b7
        L_0x0777:
            r13 = r4
            r14 = r5
            r0 = r72[r13]
            double r0 = r0 * r22
            int r0 = r12.a((double) r0)
            r1 = -32768(0xffffffffffff8000, float:NaN)
            if (r0 >= r1) goto L_0x079b
            double r0 = (double) r0
            r2 = -4548635623644200960(0xc0e0000000000000, double:-32768.0)
            java.lang.Double.isNaN(r0)
            double r0 = r0 / r2
            r2 = 0
            r3 = r16[r2]
            int r5 = (r3 > r0 ? 1 : (r3 == r0 ? 0 : -1))
            if (r5 >= 0) goto L_0x0794
            goto L_0x0796
        L_0x0794:
            r0 = r16[r2]
        L_0x0796:
            r16[r2] = r0
            r0 = -32768(0xffffffffffff8000, float:NaN)
            goto L_0x079c
        L_0x079b:
            r2 = 0
        L_0x079c:
            r1 = 32767(0x7fff, float:4.5916E-41)
            if (r1 >= r0) goto L_0x07b7
            double r0 = (double) r0
            r3 = 4674736138332667904(0x40dfffc000000000, double:32767.0)
            java.lang.Double.isNaN(r0)
            double r0 = r0 / r3
            r3 = r16[r2]
            int r5 = (r3 > r0 ? 1 : (r3 == r0 ? 0 : -1))
            if (r5 >= 0) goto L_0x07b1
            goto L_0x07b3
        L_0x07b1:
            r0 = r16[r2]
        L_0x07b3:
            r16[r2] = r0
            r0 = 32767(0x7fff, float:4.5916E-41)
        L_0x07b7:
            java.nio.ByteOrder r1 = r12.b
            java.nio.ByteBuffer r1 = r14.order(r1)
            java.nio.ShortBuffer r1 = r1.asShortBuffer()
            short r0 = (short) r0
            r1.put(r13, r0)
            int r4 = r24 + 1
            r5 = r104
            if (r4 != r5) goto L_0x07ce
            r24 = 0
            goto L_0x07d0
        L_0x07ce:
            r24 = r4
        L_0x07d0:
            int r4 = r13 + 1
            r3 = r5
            r5 = r14
            r13 = 0
            r14 = r108
            goto L_0x075e
        L_0x07d8:
            r13 = r4
            r14 = r5
            r95 = r15
            goto L_0x0741
        L_0x07de:
            r93 = r11
            r94 = r13
            r44 = r62
            r45 = r70
            r11 = r73
            r14 = r80
            r47 = r86
            r22 = 4638637247447433216(0x405fc00000000000, double:127.0)
            double r24 = r109 * r22
            r13 = 0
            r26 = 0
        L_0x07f6:
            int r0 = r9 * r5
            if (r13 >= r0) goto L_0x0862
            if (r113 == 0) goto L_0x0810
            r0 = r72[r13]
            double r1 = r0 * r24
            r0 = r101
            r3 = r16
            r4 = r113
            r95 = r15
            r15 = r5
            r5 = r26
            int r0 = r0.a((double) r1, (double[]) r3, (int) r4, (int) r5)
            goto L_0x084d
        L_0x0810:
            r95 = r15
            r15 = r5
            r0 = r72[r13]
            double r0 = r0 * r24
            int r0 = r12.a((double) r0)
            r1 = -128(0xffffffffffffff80, float:NaN)
            if (r0 >= r1) goto L_0x0835
            double r0 = (double) r0
            r2 = -4584664420663164928(0xc060000000000000, double:-128.0)
            java.lang.Double.isNaN(r0)
            double r0 = r0 / r2
            r2 = 0
            r3 = r16[r2]
            int r5 = (r3 > r0 ? 1 : (r3 == r0 ? 0 : -1))
            if (r5 >= 0) goto L_0x082e
            goto L_0x0830
        L_0x082e:
            r0 = r16[r2]
        L_0x0830:
            r16[r2] = r0
            r0 = -128(0xffffffffffffff80, float:NaN)
            goto L_0x0836
        L_0x0835:
            r2 = 0
        L_0x0836:
            r1 = 127(0x7f, float:1.78E-43)
            if (r1 >= r0) goto L_0x084d
            double r0 = (double) r0
            java.lang.Double.isNaN(r0)
            double r0 = r0 / r22
            r3 = r16[r2]
            int r5 = (r3 > r0 ? 1 : (r3 == r0 ? 0 : -1))
            if (r5 >= 0) goto L_0x0847
            goto L_0x0849
        L_0x0847:
            r0 = r16[r2]
        L_0x0849:
            r16[r2] = r0
            r0 = 127(0x7f, float:1.78E-43)
        L_0x084d:
            int r0 = r0 + 128
            byte r0 = (byte) r0
            r14.put(r13, r0)
            int r4 = r26 + 1
            if (r4 != r15) goto L_0x085a
            r26 = 0
            goto L_0x085c
        L_0x085a:
            r26 = r4
        L_0x085c:
            int r13 = r13 + 1
            r5 = r15
            r15 = r95
            goto L_0x07f6
        L_0x0862:
            r95 = r15
            goto L_0x0658
        L_0x0866:
            if (r19 != 0) goto L_0x08d5
            if (r8 == 0) goto L_0x08b8
            double r0 = (double) r7
            java.lang.Double.isNaN(r0)
            java.lang.Double.isNaN(r34)
            double r0 = r0 * r34
            java.lang.Double.isNaN(r65)
            double r0 = r0 / r65
            double r2 = r0 + r39
            int r4 = r79 + r9
            r96 = r6
            double r5 = (double) r4
            int r8 = (r2 > r5 ? 1 : (r2 == r5 ? 0 : -1))
            if (r8 <= 0) goto L_0x0893
            r2 = 0
            r14.position(r2)
            int r0 = r69 * r9
            r14.limit(r0)
            r6 = r103
            r12.a((java.io.OutputStream) r6, (java.nio.ByteBuffer) r14)
            r1 = r4
            goto L_0x08cd
        L_0x0893:
            r2 = 0
            r6 = r103
            r14.position(r2)
            double r0 = java.lang.Math.floor(r0)
            double r0 = r0 + r39
            r2 = r79
            double r2 = (double) r2
            java.lang.Double.isNaN(r2)
            double r0 = r0 - r2
            java.lang.Double.isNaN(r45)
            double r3 = r45 * r0
            int r0 = (int) r3
            if (r0 <= 0) goto L_0x08b4
            r14.limit(r0)
            r12.a((java.io.OutputStream) r6, (java.nio.ByteBuffer) r14)
        L_0x08b4:
            r3 = 4607182418800017408(0x3ff0000000000000, double:1.0)
            goto L_0x0939
        L_0x08b8:
            r96 = r6
            r2 = r79
            r0 = 0
            r6 = r103
            r14.position(r0)
            int r0 = r69 * r9
            r14.limit(r0)
            r12.a((java.io.OutputStream) r6, (java.nio.ByteBuffer) r14)
            int r0 = r2 + r9
            r1 = r0
        L_0x08cd:
            r97 = r7
            r0 = r77
        L_0x08d1:
            r3 = 4607182418800017408(0x3ff0000000000000, double:1.0)
            goto L_0x0956
        L_0x08d5:
            r96 = r6
            r0 = r77
            r2 = r79
            r6 = r103
            if (r9 >= r0) goto L_0x08e4
            int r0 = r0 - r9
            r1 = r2
            r97 = r7
            goto L_0x08d1
        L_0x08e4:
            if (r8 == 0) goto L_0x0940
            double r3 = (double) r7
            java.lang.Double.isNaN(r3)
            java.lang.Double.isNaN(r34)
            double r3 = r3 * r34
            java.lang.Double.isNaN(r65)
            double r3 = r3 / r65
            double r22 = r3 + r39
            int r1 = r2 + r9
            int r1 = r1 - r0
            r97 = r7
            double r7 = (double) r1
            int r1 = (r22 > r7 ? 1 : (r22 == r7 ? 0 : -1))
            if (r1 <= 0) goto L_0x0911
            int r1 = r69 * r0
            r14.position(r1)
            int r1 = r9 - r0
            int r3 = r69 * r1
            r14.limit(r3)
            r12.a((java.io.OutputStream) r6, (java.nio.ByteBuffer) r14)
            int r1 = r1 + r2
            goto L_0x08d1
        L_0x0911:
            int r1 = r69 * r0
            r14.position(r1)
            double r3 = java.lang.Math.floor(r3)
            double r3 = r3 + r39
            double r1 = (double) r2
            java.lang.Double.isNaN(r1)
            double r3 = r3 + r1
            double r1 = (double) r9
            java.lang.Double.isNaN(r1)
            double r3 = r3 + r1
            double r0 = (double) r0
            java.lang.Double.isNaN(r0)
            double r3 = r3 - r0
            java.lang.Double.isNaN(r45)
            double r3 = r3 * r45
            int r0 = (int) r3
            r14.limit(r0)
            r12.a((java.io.OutputStream) r6, (java.nio.ByteBuffer) r14)
            goto L_0x08b4
        L_0x0939:
            r12.d(r3)
            r0 = 0
            r0 = r16[r0]
            return r0
        L_0x0940:
            r97 = r7
            r3 = 4607182418800017408(0x3ff0000000000000, double:1.0)
            int r1 = r69 * r0
            r14.position(r1)
            int r1 = r69 * r9
            r14.limit(r1)
            r12.a((java.io.OutputStream) r6, (java.nio.ByteBuffer) r14)
            int r1 = r9 - r0
            int r1 = r1 + r2
            r19 = 0
        L_0x0956:
            int r2 = r21 + -1
            int r7 = r84 / r41
            int r2 = r2 / r7
            if (r2 <= r10) goto L_0x095e
            r2 = r10
        L_0x095e:
            r5 = 0
        L_0x095f:
            if (r5 >= r15) goto L_0x0974
            r8 = r52[r5]
            r3 = r52[r5]
            int r4 = r44 - r2
            r98 = r0
            r0 = 0
            java.lang.System.arraycopy(r8, r2, r3, r0, r4)
            int r5 = r5 + 1
            r0 = r98
            r3 = 4607182418800017408(0x3ff0000000000000, double:1.0)
            goto L_0x095f
        L_0x0974:
            r98 = r0
            int r2 = r2 * r7
            int r4 = r21 - r2
            r0 = 0
        L_0x097b:
            if (r0 >= r15) goto L_0x0987
            r2 = r36[r0]
            r3 = r52[r0]
            java.lang.System.arraycopy(r2, r10, r3, r11, r10)
            int r0 = r0 + 1
            goto L_0x097b
        L_0x0987:
            int r0 = r75 + 1
            r2 = r75 & 7
            r3 = 7
            if (r2 != r3) goto L_0x09a3
            r2 = r97
            double r7 = (double) r2
            r100 = r0
            r99 = r1
            r3 = r96
            double r0 = (double) r3
            java.lang.Double.isNaN(r7)
            java.lang.Double.isNaN(r0)
            double r7 = r7 / r0
            r12.d(r7)
            goto L_0x09ab
        L_0x09a3:
            r100 = r0
            r99 = r1
            r3 = r96
            r2 = r97
        L_0x09ab:
            r0 = r2
            r1 = r3
            r74 = r9
            r73 = r11
            r8 = r12
            r32 = r13
            r12 = r14
            r5 = r15
            r62 = r44
            r70 = r45
            r67 = r47
            r6 = r53
            r7 = r84
            r2 = r87
            r64 = r89
            r3 = r93
            r13 = r94
            r15 = r95
            r9 = r98
            r75 = r100
            r14 = r10
            r10 = r99
            goto L_0x0320
        L_0x09d3:
            r95 = r5
            r12 = r8
            r15 = r10
            r16 = r36
            r6 = r103
            r48 = 1
            int r23 = r23 * 2
            r13 = r107
            r9 = r6
            r7 = r16
            r3 = r41
            r4 = 0
            r6 = 1
            r14 = r108
            goto L_0x004f
        L_0x09ec:
            r12 = r8
            r48 = 1
            java.lang.IllegalArgumentException r0 = new java.lang.IllegalArgumentException
            r1 = 6
            java.lang.Object[] r1 = new java.lang.Object[r1]
            java.lang.Integer r2 = java.lang.Integer.valueOf(r107)
            r4 = 0
            r1[r4] = r2
            java.lang.Integer r2 = java.lang.Integer.valueOf(r108)
            r1[r48] = r2
            java.lang.Integer r2 = java.lang.Integer.valueOf(r108)
            r1[r20] = r2
            java.lang.Integer r2 = java.lang.Integer.valueOf(r107)
            r1[r5] = r2
            r2 = 4
            java.lang.Integer r4 = java.lang.Integer.valueOf(r108)
            r1[r2] = r4
            r2 = 5
            java.lang.Integer r3 = java.lang.Integer.valueOf(r3)
            r1[r2] = r3
            java.lang.String r2 = "Resampling from %dHz to %dHz is not supported.\n%d/gcd(%d,%d)=%d must be divided by 2 or 3."
            java.lang.String r1 = java.lang.String.format(r2, r1)
            r0.<init>(r1)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.smarthome.framework.plugin.rn.nativemodule.audio.SSRC.b(java.io.InputStream, java.io.OutputStream, int, int, int, int, int, double, int, boolean, int):double");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:14:0x012f, code lost:
        r10 = r0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:16:0x0134, code lost:
        if (r26.available() != 0) goto L_0x013a;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:17:0x0136, code lost:
        r13 = r25;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:18:0x013a, code lost:
        r5 = r5 * r31;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:19:0x013c, code lost:
        if (r34 != false) goto L_0x0224;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:20:0x013e, code lost:
        switch(r30) {
            case 1: goto L_0x01e7;
            case 2: goto L_0x01a5;
            case 3: goto L_0x014e;
            default: goto L_0x0141;
        };
     */
    /* JADX WARNING: Code restructure failed: missing block: B:21:0x0141, code lost:
        r6 = r2;
        r24 = r4;
        r20 = r22;
        r13 = r25;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:22:0x014e, code lost:
        r5 = r5 * 8388607.0d;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:23:0x0155, code lost:
        if (r35 == 0) goto L_0x0171;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:24:0x0157, code lost:
        r20 = r22;
        r24 = r4;
        r13 = 0;
        r0 = a(r5, r21, r35, r15);
        r3 = r2;
        r4 = r25;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:25:0x0171, code lost:
        r3 = r2;
        r24 = r4;
        r20 = r22;
        r4 = r25;
        r13 = 0;
        r0 = r4.a(r5);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:26:0x017f, code lost:
        r10.position(r13);
        r10.limit(3);
        r10.put(r13, (byte) (r0 & 255));
        r0 = r0 >> 8;
        r10.put(1, (byte) (r0 & 255));
        r10.put(2, (byte) ((r0 >> 8) & 255));
        r10.flip();
        r4.a(r8, r10);
        r6 = r3;
        r13 = r4;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:27:0x01a5, code lost:
        r3 = r2;
        r24 = r4;
        r20 = r22;
        r4 = r25;
        r1 = r5 * 32767.0d;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:28:0x01b6, code lost:
        if (r35 == 0) goto L_0x01c8;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:29:0x01b8, code lost:
        r6 = r3;
        r13 = r4;
        r0 = a(r1, r21, r35, r15);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:31:0x01c8, code lost:
        r6 = r3;
        r13 = r4;
        r0 = r13.a(r1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:32:0x01cf, code lost:
        r10.position(0);
        r10.limit(2);
        r10.asShortBuffer().put(0, (short) r0);
        r10.flip();
        r13.a(r8, r10);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:34:0x01e7, code lost:
        r24 = r4;
        r20 = r22;
        r13 = r25;
        r4 = r2;
        r1 = r5 * 127.0d;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:35:0x01f7, code lost:
        if (r35 == 0) goto L_0x0207;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:36:0x01f9, code lost:
        r6 = r4;
        r0 = a(r1, r21, r35, r15);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:38:0x0207, code lost:
        r6 = r4;
        r0 = r13.a(r1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:39:0x020d, code lost:
        r10.position(0);
        r10.limit(1);
        r10.put(0, (byte) (r0 + 128));
        r10.flip();
        r13.a(r8, r10);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:40:0x0220, code lost:
        r0 = r6;
        r2 = r24;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:41:0x0224, code lost:
        r0 = r2;
        r24 = r4;
        r20 = r22;
        r13 = r25;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:42:0x0233, code lost:
        if (r5 <= 0.0d) goto L_0x0237;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:43:0x0235, code lost:
        r2 = r5;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:44:0x0237, code lost:
        r2 = -r5;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:46:0x023c, code lost:
        if (r21[0] >= r2) goto L_0x023f;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:47:0x023f, code lost:
        r2 = r21[0];
     */
    /* JADX WARNING: Code restructure failed: missing block: B:48:0x0241, code lost:
        r21[0] = r2;
        r2 = r24;
        r2.position(0);
        r2.putDouble(r5);
        r2.flip();
        r13.a(r8, r2);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:49:0x0251, code lost:
        r5 = r15 + 1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:50:0x0253, code lost:
        if (r5 != r9) goto L_0x0257;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:51:0x0255, code lost:
        r15 = 0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:52:0x0257, code lost:
        r15 = r5;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:53:0x0258, code lost:
        r1 = r20 + 1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:54:0x025e, code lost:
        if ((262143 & r1) != 0) goto L_0x026c;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:55:0x0260, code lost:
        r3 = (double) r1;
        r5 = (double) r0;
        java.lang.Double.isNaN(r3);
        java.lang.Double.isNaN(r5);
        r13.d(r3 / r5);
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public double a(java.io.InputStream r26, java.io.OutputStream r27, int r28, int r29, int r30, double r31, int r33, boolean r34, int r35) throws java.io.IOException {
        /*
            r25 = this;
            r6 = r25
            r7 = r26
            r8 = r27
            r9 = r28
            r10 = 1
            double[] r11 = new double[r10]
            r12 = 0
            r5 = 0
            r11[r5] = r12
            r25.b()
            r14 = 8
            if (r34 == 0) goto L_0x001c
            java.nio.ByteBuffer r0 = java.nio.ByteBuffer.allocate(r14)
            goto L_0x001d
        L_0x001c:
            r0 = 0
        L_0x001d:
            r4 = r0
            r3 = 4
            java.nio.ByteBuffer r0 = java.nio.ByteBuffer.allocate(r3)
            r1 = 0
            r15 = 0
        L_0x0025:
            int r2 = r33 * r9
            if (r1 >= r2) goto L_0x0278
            r12 = 3
            r13 = 2
            switch(r29) {
                case 1: goto L_0x00fc;
                case 2: goto L_0x00bf;
                case 3: goto L_0x0074;
                case 4: goto L_0x0037;
                default: goto L_0x002e;
            }
        L_0x002e:
            r22 = r1
            r21 = r11
            r10 = r0
            r5 = 0
            goto L_0x0130
        L_0x0037:
            r0.position(r5)
            r0.limit(r3)
            int r0 = r0.limit()
            byte[] r0 = new byte[r0]
            r7.read(r0)
            java.nio.ByteBuffer r0 = java.nio.ByteBuffer.wrap(r0)
            int r3 = r0.limit()
            r0.position(r3)
            r0.flip()
            java.nio.ByteOrder r3 = r6.b
            java.nio.ByteBuffer r3 = r0.order(r3)
            java.nio.IntBuffer r3 = r3.asIntBuffer()
            int r3 = r3.get(r5)
            r19 = 4467570830353629184(0x3e00000000200000, double:4.656612875245797E-10)
            r21 = r11
            double r10 = (double) r3
            java.lang.Double.isNaN(r10)
            double r10 = r10 * r19
            r22 = r1
            r5 = r10
            goto L_0x012f
        L_0x0074:
            r21 = r11
            r0.position(r5)
            r0.limit(r12)
            int r0 = r0.limit()
            byte[] r0 = new byte[r0]
            r7.read(r0)
            java.nio.ByteBuffer r0 = java.nio.ByteBuffer.wrap(r0)
            int r3 = r0.limit()
            r0.position(r3)
            r0.flip()
            r10 = 4503599627907366976(0x3e80000020000040, double:1.1920930376163766E-7)
            byte r3 = r0.get(r5)
            r3 = r3 & 255(0xff, float:3.57E-43)
            int r3 = r3 << r5
            r12 = 1
            byte r5 = r0.get(r12)
            r5 = r5 & 255(0xff, float:3.57E-43)
            int r5 = r5 << r14
            r3 = r3 | r5
            byte r5 = r0.get(r13)
            r5 = r5 & 255(0xff, float:3.57E-43)
            int r5 = r5 << 16
            r3 = r3 | r5
            r23 = r0
            r22 = r1
            double r0 = (double) r3
            java.lang.Double.isNaN(r0)
            double r0 = r0 * r10
            r5 = r0
            r10 = r23
            goto L_0x0130
        L_0x00bf:
            r22 = r1
            r21 = r11
            r1 = 0
            r0.position(r1)
            r0.limit(r13)
            int r0 = r0.limit()
            byte[] r0 = new byte[r0]
            r7.read(r0)
            java.nio.ByteBuffer r0 = java.nio.ByteBuffer.wrap(r0)
            int r1 = r0.limit()
            r0.position(r1)
            r0.flip()
            java.nio.ByteOrder r1 = r6.b
            java.nio.ByteBuffer r1 = r0.order(r1)
            java.nio.ShortBuffer r1 = r1.asShortBuffer()
            r5 = 0
            short r1 = r1.get(r5)
            r10 = 4539628561832607872(0x3f00002000400080, double:3.051850947599719E-5)
            double r5 = (double) r1
            java.lang.Double.isNaN(r5)
            double r5 = r5 * r10
            goto L_0x012f
        L_0x00fc:
            r22 = r1
            r21 = r11
            r0.position(r5)
            r1 = 1
            r0.limit(r1)
            int r0 = r0.limit()
            byte[] r0 = new byte[r0]
            r7.read(r0)
            java.nio.ByteBuffer r0 = java.nio.ByteBuffer.wrap(r0)
            int r1 = r0.limit()
            r0.position(r1)
            r0.flip()
            r10 = 4575692682822812680(0x3f80204081020408, double:0.007874015748031496)
            byte r1 = r0.get(r5)
            int r1 = r1 + -128
            double r5 = (double) r1
            java.lang.Double.isNaN(r5)
            double r5 = r5 * r10
        L_0x012f:
            r10 = r0
        L_0x0130:
            int r0 = r26.available()
            if (r0 != 0) goto L_0x013a
            r13 = r25
            goto L_0x027b
        L_0x013a:
            double r5 = r5 * r31
            if (r34 != 0) goto L_0x0224
            switch(r30) {
                case 1: goto L_0x01e7;
                case 2: goto L_0x01a5;
                case 3: goto L_0x014e;
                default: goto L_0x0141;
            }
        L_0x0141:
            r6 = r2
            r24 = r4
            r20 = r22
            r1 = 0
            r2 = 1
            r13 = r25
            r18 = 4
            goto L_0x0220
        L_0x014e:
            r0 = 4710765209155796992(0x415fffffc0000000, double:8388607.0)
            double r5 = r5 * r0
            if (r35 == 0) goto L_0x0171
            r0 = r25
            r3 = r2
            r20 = r22
            r1 = r5
            r6 = r3
            r18 = 4
            r3 = r21
            r5 = r4
            r4 = r35
            r24 = r5
            r13 = 0
            r5 = r15
            int r0 = r0.a((double) r1, (double[]) r3, (int) r4, (int) r5)
            r3 = r6
            r4 = r25
            goto L_0x017f
        L_0x0171:
            r3 = r2
            r24 = r4
            r20 = r22
            r4 = r25
            r13 = 0
            r18 = 4
            int r0 = r4.a((double) r5)
        L_0x017f:
            r10.position(r13)
            r1 = 3
            r10.limit(r1)
            r1 = r0 & 255(0xff, float:3.57E-43)
            byte r1 = (byte) r1
            r10.put(r13, r1)
            int r0 = r0 >> r14
            r1 = r0 & 255(0xff, float:3.57E-43)
            byte r1 = (byte) r1
            r2 = 1
            r10.put(r2, r1)
            int r0 = r0 >> r14
            r0 = r0 & 255(0xff, float:3.57E-43)
            byte r0 = (byte) r0
            r1 = 2
            r10.put(r1, r0)
            r10.flip()
            r4.a((java.io.OutputStream) r8, (java.nio.ByteBuffer) r10)
            r6 = r3
            r13 = r4
            goto L_0x01e4
        L_0x01a5:
            r3 = r2
            r24 = r4
            r20 = r22
            r4 = r25
            r13 = 0
            r18 = 4
            r0 = 4674736138332667904(0x40dfffc000000000, double:32767.0)
            double r1 = r5 * r0
            if (r35 == 0) goto L_0x01c8
            r0 = r25
            r6 = r3
            r3 = r21
            r5 = r4
            r4 = r35
            r13 = r5
            r5 = r15
            int r0 = r0.a((double) r1, (double[]) r3, (int) r4, (int) r5)
        L_0x01c6:
            r1 = 0
            goto L_0x01cf
        L_0x01c8:
            r6 = r3
            r13 = r4
            int r0 = r13.a((double) r1)
            goto L_0x01c6
        L_0x01cf:
            r10.position(r1)
            r2 = 2
            r10.limit(r2)
            java.nio.ShortBuffer r2 = r10.asShortBuffer()
            short r0 = (short) r0
            r2.put(r1, r0)
            r10.flip()
            r13.a((java.io.OutputStream) r8, (java.nio.ByteBuffer) r10)
        L_0x01e4:
            r1 = 0
            r2 = 1
            goto L_0x0220
        L_0x01e7:
            r24 = r4
            r20 = r22
            r13 = r25
            r18 = 4
            r4 = r2
            r0 = 4638637247447433216(0x405fc00000000000, double:127.0)
            double r1 = r5 * r0
            if (r35 == 0) goto L_0x0207
            r0 = r25
            r3 = r21
            r6 = r4
            r4 = r35
            r5 = r15
            int r0 = r0.a((double) r1, (double[]) r3, (int) r4, (int) r5)
        L_0x0205:
            r1 = 0
            goto L_0x020d
        L_0x0207:
            r6 = r4
            int r0 = r13.a((double) r1)
            goto L_0x0205
        L_0x020d:
            r10.position(r1)
            r2 = 1
            r10.limit(r2)
            int r0 = r0 + 128
            byte r0 = (byte) r0
            r10.put(r1, r0)
            r10.flip()
            r13.a((java.io.OutputStream) r8, (java.nio.ByteBuffer) r10)
        L_0x0220:
            r0 = r6
            r2 = r24
            goto L_0x0251
        L_0x0224:
            r0 = r2
            r24 = r4
            r20 = r22
            r1 = 0
            r2 = 1
            r3 = 0
            r13 = r25
            r18 = 4
            int r16 = (r5 > r3 ? 1 : (r5 == r3 ? 0 : -1))
            if (r16 <= 0) goto L_0x0237
            r2 = r5
            goto L_0x0238
        L_0x0237:
            double r2 = -r5
        L_0x0238:
            r16 = r21[r1]
            int r4 = (r16 > r2 ? 1 : (r16 == r2 ? 0 : -1))
            if (r4 >= 0) goto L_0x023f
            goto L_0x0241
        L_0x023f:
            r2 = r21[r1]
        L_0x0241:
            r21[r1] = r2
            r2 = r24
            r2.position(r1)
            r2.putDouble(r5)
            r2.flip()
            r13.a((java.io.OutputStream) r8, (java.nio.ByteBuffer) r2)
        L_0x0251:
            int r5 = r15 + 1
            if (r5 != r9) goto L_0x0257
            r15 = 0
            goto L_0x0258
        L_0x0257:
            r15 = r5
        L_0x0258:
            int r1 = r20 + 1
            r3 = 262143(0x3ffff, float:3.6734E-40)
            r3 = r3 & r1
            if (r3 != 0) goto L_0x026c
            double r3 = (double) r1
            double r5 = (double) r0
            java.lang.Double.isNaN(r3)
            java.lang.Double.isNaN(r5)
            double r3 = r3 / r5
            r13.d(r3)
        L_0x026c:
            r4 = r2
            r0 = r10
            r6 = r13
            r11 = r21
            r3 = 4
            r5 = 0
            r10 = 1
            r12 = 0
            goto L_0x0025
        L_0x0278:
            r13 = r6
            r21 = r11
        L_0x027b:
            r0 = 4607182418800017408(0x3ff0000000000000, double:1.0)
            r13.d(r0)
            r0 = 0
            r0 = r21[r0]
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.smarthome.framework.plugin.rn.nativemodule.audio.SSRC.a(java.io.InputStream, java.io.OutputStream, int, int, int, double, int, boolean, int):double");
    }

    public static void a(String[] strArr) throws Exception {
        new SSRC(strArr);
    }

    public SSRC() {
        this.b = ByteOrder.LITTLE_ENDIAN;
        this.c = new SplitRadixFft();
        this.e = 150.0d;
        this.f = 200.0d;
        this.g = 1;
        this.t = false;
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v1, resolved type: short} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v2, resolved type: short} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r14v5, resolved type: short} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r10v4, resolved type: short} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r38v0, resolved type: short} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r26v2, resolved type: short} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r15v26, resolved type: short} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v3, resolved type: short} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v4, resolved type: short} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r6v12, resolved type: short} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r7v4, resolved type: short} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v5, resolved type: short} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r6v14, resolved type: short} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r7v5, resolved type: short} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r6v24, resolved type: short} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r7v10, resolved type: short} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r6v26, resolved type: short} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r7v12, resolved type: short} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r6v29, resolved type: short} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r7v13, resolved type: short} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r6v30, resolved type: short} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r7v14, resolved type: short} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r15v41, resolved type: short} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r26v3, resolved type: short} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r7v15, resolved type: short} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v74, resolved type: short} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r8v19, resolved type: short} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r26v4, resolved type: short} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r15v42, resolved type: short} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r38v1, resolved type: short} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r38v2, resolved type: short} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r10v20, resolved type: short} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r10v21, resolved type: short} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r14v14, resolved type: short} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v91, resolved type: short} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v92, resolved type: short} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r14v15, resolved type: short} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r14v16, resolved type: short} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v93, resolved type: short} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v35, resolved type: short} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v57, resolved type: short} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v64, resolved type: short} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v36, resolved type: short} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v67, resolved type: short} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v37, resolved type: short} */
    /* JADX WARNING: Code restructure failed: missing block: B:269:0x07e0, code lost:
        r16 = r4;
     */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    SSRC(java.lang.String[] r49) throws java.io.IOException {
        /*
            r48 = this;
            r13 = r48
            r0 = r49
            r48.<init>()
            java.nio.ByteOrder r1 = java.nio.ByteOrder.LITTLE_ENDIAN
            r13.b = r1
            com.xiaomi.smarthome.framework.plugin.rn.nativemodule.audio.SplitRadixFft r1 = new com.xiaomi.smarthome.framework.plugin.rn.nativemodule.audio.SplitRadixFft
            r1.<init>()
            r13.c = r1
            r1 = 4639481672377565184(0x4062c00000000000, double:150.0)
            r13.e = r1
            r1 = 4641240890982006784(0x4069000000000000, double:200.0)
            r13.f = r1
            r14 = 1
            r13.g = r14
            r15 = 0
            r13.t = r15
            double[] r12 = new double[r14]
            r16 = 0
            r12[r15] = r16
            r2 = 0
            r3 = 4595653203753948938(0x3fc70a3d70a3d70a, double:0.18)
            r9 = r2
            r7 = r3
            r10 = r16
            r2 = 0
            r3 = -1
            r4 = 0
            r5 = -1
            r6 = 0
            r18 = 0
            r19 = 0
        L_0x003c:
            int r1 = r0.length
            r14 = 2
            if (r2 >= r1) goto L_0x01d6
            r1 = r0[r2]
            char r1 = r1.charAt(r15)
            r15 = 45
            if (r1 == r15) goto L_0x004c
            goto L_0x01d6
        L_0x004c:
            r1 = r0[r2]
            java.lang.String r15 = "--rate"
            boolean r1 = r1.equals(r15)
            if (r1 == 0) goto L_0x0062
            int r2 = r2 + 1
            r1 = r0[r2]
            int r1 = java.lang.Integer.parseInt(r1)
            r5 = r1
        L_0x005f:
            r1 = 1
            goto L_0x019f
        L_0x0062:
            r1 = r0[r2]
            java.lang.String r15 = "--att"
            boolean r1 = r1.equals(r15)
            if (r1 == 0) goto L_0x0076
            int r2 = r2 + 1
            r1 = r0[r2]
            float r1 = java.lang.Float.parseFloat(r1)
            double r10 = (double) r1
            goto L_0x005f
        L_0x0076:
            r1 = r0[r2]
            java.lang.String r15 = "--bits"
            boolean r1 = r1.equals(r15)
            if (r1 == 0) goto L_0x00a1
            int r2 = r2 + 1
            r1 = r0[r2]
            int r1 = java.lang.Integer.parseInt(r1)
            r3 = 8
            if (r1 == r3) goto L_0x009d
            r3 = 16
            if (r1 == r3) goto L_0x009d
            r3 = 24
            if (r1 != r3) goto L_0x0095
            goto L_0x009d
        L_0x0095:
            java.lang.IllegalArgumentException r0 = new java.lang.IllegalArgumentException
            java.lang.String r1 = "Error: Only 8bit, 16bit and 24bit PCM are supported."
            r0.<init>(r1)
            throw r0
        L_0x009d:
            int r1 = r1 / 8
            r3 = r1
            goto L_0x005f
        L_0x00a1:
            r1 = r0[r2]
            java.lang.String r15 = "--twopass"
            boolean r1 = r1.equals(r15)
            if (r1 == 0) goto L_0x00b0
            r1 = 1
            r18 = 1
            goto L_0x019f
        L_0x00b0:
            r1 = r0[r2]
            java.lang.String r15 = "--normalize"
            boolean r1 = r1.equals(r15)
            if (r1 == 0) goto L_0x00c1
            r1 = 1
            r18 = 1
            r19 = 1
            goto L_0x019f
        L_0x00c1:
            r1 = r0[r2]
            java.lang.String r15 = "--dither"
            boolean r1 = r1.equals(r15)
            if (r1 == 0) goto L_0x00f7
            int r1 = r2 + 1
            r4 = r0[r1]     // Catch:{ NumberFormatException -> 0x00f3 }
            int r4 = java.lang.Integer.parseInt(r4)     // Catch:{ NumberFormatException -> 0x00f3 }
            if (r4 < 0) goto L_0x00da
            r14 = 4
            if (r4 > r14) goto L_0x00da
        L_0x00d8:
            r2 = r1
            goto L_0x005f
        L_0x00da:
            java.lang.IllegalArgumentException r4 = new java.lang.IllegalArgumentException     // Catch:{ NumberFormatException -> 0x00f3 }
            java.lang.StringBuilder r14 = new java.lang.StringBuilder     // Catch:{ NumberFormatException -> 0x00f3 }
            r14.<init>()     // Catch:{ NumberFormatException -> 0x00f3 }
            java.lang.String r15 = "unrecognized dither type : "
            r14.append(r15)     // Catch:{ NumberFormatException -> 0x00f3 }
            r1 = r0[r1]     // Catch:{ NumberFormatException -> 0x00f3 }
            r14.append(r1)     // Catch:{ NumberFormatException -> 0x00f3 }
            java.lang.String r1 = r14.toString()     // Catch:{ NumberFormatException -> 0x00f3 }
            r4.<init>(r1)     // Catch:{ NumberFormatException -> 0x00f3 }
            throw r4     // Catch:{ NumberFormatException -> 0x00f3 }
        L_0x00f3:
            r1 = 1
            r4 = -1
            goto L_0x019f
        L_0x00f7:
            r1 = r0[r2]
            java.lang.String r15 = "--pdf"
            boolean r1 = r1.equals(r15)
            if (r1 == 0) goto L_0x014e
            int r2 = r2 + 1
            r1 = r0[r2]     // Catch:{ NumberFormatException -> 0x0135 }
            int r6 = java.lang.Integer.parseInt(r1)     // Catch:{ NumberFormatException -> 0x0135 }
            if (r6 < 0) goto L_0x011c
            if (r6 > r14) goto L_0x011c
            int r1 = r2 + 1
            r7 = r0[r1]     // Catch:{ NumberFormatException -> 0x0116 }
            double r7 = java.lang.Double.parseDouble(r7)     // Catch:{ NumberFormatException -> 0x0116 }
            goto L_0x00d8
        L_0x0116:
            double[] r1 = y
            r7 = r1[r6]
            goto L_0x005f
        L_0x011c:
            java.lang.IllegalArgumentException r1 = new java.lang.IllegalArgumentException     // Catch:{ NumberFormatException -> 0x0135 }
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch:{ NumberFormatException -> 0x0135 }
            r3.<init>()     // Catch:{ NumberFormatException -> 0x0135 }
            java.lang.String r4 = "unrecognized p.d.f. type : "
            r3.append(r4)     // Catch:{ NumberFormatException -> 0x0135 }
            r4 = r0[r2]     // Catch:{ NumberFormatException -> 0x0135 }
            r3.append(r4)     // Catch:{ NumberFormatException -> 0x0135 }
            java.lang.String r3 = r3.toString()     // Catch:{ NumberFormatException -> 0x0135 }
            r1.<init>(r3)     // Catch:{ NumberFormatException -> 0x0135 }
            throw r1     // Catch:{ NumberFormatException -> 0x0135 }
        L_0x0135:
            java.lang.IllegalArgumentException r1 = new java.lang.IllegalArgumentException
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>()
            java.lang.String r4 = "unrecognized p.d.f. type : "
            r3.append(r4)
            r0 = r0[r2]
            r3.append(r0)
            java.lang.String r0 = r3.toString()
            r1.<init>(r0)
            throw r1
        L_0x014e:
            r1 = r0[r2]
            java.lang.String r14 = "--quiet"
            boolean r1 = r1.equals(r14)
            if (r1 == 0) goto L_0x015c
            r1 = 1
            r13.t = r1
            goto L_0x019f
        L_0x015c:
            r1 = r0[r2]
            java.lang.String r14 = "--tmpfile"
            boolean r1 = r1.equals(r14)
            if (r1 == 0) goto L_0x016d
            int r2 = r2 + 1
            r1 = r0[r2]
            r9 = r1
            goto L_0x005f
        L_0x016d:
            r1 = r0[r2]
            java.lang.String r14 = "--profile"
            boolean r1 = r1.equals(r14)
            if (r1 == 0) goto L_0x01bd
            int r2 = r2 + 1
            r1 = r0[r2]
            java.lang.String r14 = "fast"
            boolean r1 = r1.equals(r14)
            if (r1 == 0) goto L_0x0193
            r14 = 4636455816377925632(0x4058000000000000, double:96.0)
            r13.e = r14
            r14 = 4665518107723300864(0x40bf400000000000, double:8000.0)
            r13.f = r14
            r1 = 1024(0x400, float:1.435E-42)
            r13.g = r1
            goto L_0x019d
        L_0x0193:
            r1 = r0[r2]
            java.lang.String r14 = "standard"
            boolean r1 = r1.equals(r14)
            if (r1 == 0) goto L_0x01a4
        L_0x019d:
            goto L_0x005f
        L_0x019f:
            int r2 = r2 + r1
            r14 = 1
            r15 = 0
            goto L_0x003c
        L_0x01a4:
            java.lang.IllegalArgumentException r1 = new java.lang.IllegalArgumentException
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>()
            java.lang.String r4 = "unrecognized profile : "
            r3.append(r4)
            r0 = r0[r2]
            r3.append(r0)
            java.lang.String r0 = r3.toString()
            r1.<init>(r0)
            throw r1
        L_0x01bd:
            java.lang.IllegalArgumentException r1 = new java.lang.IllegalArgumentException
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>()
            java.lang.String r4 = "unrecognized option : "
            r3.append(r4)
            r0 = r0[r2]
            r3.append(r0)
            java.lang.String r0 = r3.toString()
            r1.<init>(r0)
            throw r1
        L_0x01d6:
            boolean r1 = r13.t
            if (r1 != 0) goto L_0x01e7
            java.io.PrintStream r1 = java.lang.System.err
            java.lang.String r15 = "Shibatch sampling rate converter version 1.30(high precision/nio)\n\n"
            r24 = r12
            r14 = 0
            java.lang.Object[] r12 = new java.lang.Object[r14]
            r1.printf(r15, r12)
            goto L_0x01e9
        L_0x01e7:
            r24 = r12
        L_0x01e9:
            int r1 = r0.length
            int r1 = r1 - r2
            r12 = 2
            if (r1 != r12) goto L_0x0a8e
            r1 = r0[r2]
            r12 = 1
            int r2 = r2 + r12
            r14 = r0[r2]
            java.io.FileInputStream r12 = new java.io.FileInputStream     // Catch:{ IOException -> 0x0a86 }
            r12.<init>(r1)     // Catch:{ IOException -> 0x0a86 }
            r0 = 256(0x100, float:3.59E-43)
            java.nio.ByteBuffer r0 = java.nio.ByteBuffer.allocate(r0)
            java.nio.ByteOrder r1 = java.nio.ByteOrder.LITTLE_ENDIAN
            java.nio.ByteBuffer r0 = r0.order(r1)
            r1 = 36
            r0.limit(r1)
            java.nio.channels.FileChannel r1 = r12.getChannel()
            r1.read(r0)
            r0.flip()
            java.io.PrintStream r1 = java.lang.System.err
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            java.lang.String r15 = "p: "
            r2.append(r15)
            int r15 = r0.position()
            r2.append(r15)
            java.lang.String r15 = ", l: "
            r2.append(r15)
            int r15 = r0.limit()
            r2.append(r15)
            java.lang.String r2 = r2.toString()
            r1.println(r2)
            byte r1 = r0.get()
            r2 = 82
            if (r1 == r2) goto L_0x0247
            r1 = 1
            r13.b((int) r1)
            goto L_0x0248
        L_0x0247:
            r1 = 1
        L_0x0248:
            byte r2 = r0.get()
            r15 = 73
            if (r2 == r15) goto L_0x0253
            r13.b((int) r1)
        L_0x0253:
            byte r2 = r0.get()
            r15 = 70
            if (r2 == r15) goto L_0x025e
            r13.b((int) r1)
        L_0x025e:
            byte r2 = r0.get()
            r15 = 70
            if (r2 == r15) goto L_0x0269
            r13.b((int) r1)
        L_0x0269:
            r0.getInt()
            byte r1 = r0.get()
            r2 = 87
            if (r1 == r2) goto L_0x0279
            r1 = 2
            r13.b((int) r1)
            goto L_0x027a
        L_0x0279:
            r1 = 2
        L_0x027a:
            byte r2 = r0.get()
            r15 = 65
            if (r2 == r15) goto L_0x0285
            r13.b((int) r1)
        L_0x0285:
            byte r2 = r0.get()
            r15 = 86
            if (r2 == r15) goto L_0x0290
            r13.b((int) r1)
        L_0x0290:
            byte r2 = r0.get()
            r15 = 69
            if (r2 == r15) goto L_0x029b
            r13.b((int) r1)
        L_0x029b:
            byte r2 = r0.get()
            r15 = 102(0x66, float:1.43E-43)
            if (r2 == r15) goto L_0x02a6
            r13.b((int) r1)
        L_0x02a6:
            byte r2 = r0.get()
            r15 = 109(0x6d, float:1.53E-43)
            if (r2 == r15) goto L_0x02b1
            r13.b((int) r1)
        L_0x02b1:
            byte r2 = r0.get()
            r15 = 116(0x74, float:1.63E-43)
            if (r2 == r15) goto L_0x02bc
            r13.b((int) r1)
        L_0x02bc:
            byte r2 = r0.get()
            r15 = 32
            if (r2 == r15) goto L_0x02c7
            r13.b((int) r1)
        L_0x02c7:
            int r1 = r0.getInt()
            short r2 = r0.getShort()
            r15 = 1
            if (r2 != r15) goto L_0x0a7e
            short r15 = r0.getShort()
            int r2 = r0.getInt()
            int r25 = r0.getInt()
            int r26 = r25 % r2
            int r26 = r26 * r15
            if (r26 == 0) goto L_0x02eb
            r27 = r9
            r9 = 4
            r13.b((int) r9)
            goto L_0x02ed
        L_0x02eb:
            r27 = r9
        L_0x02ed:
            r0.getShort()
            r0.getShort()
            int r9 = r2 * r15
            int r9 = r25 / r9
            r28 = r14
            r14 = 16
            if (r1 <= r14) goto L_0x0328
            r1 = 0
            r0.position(r1)
            r1 = 2
            r0.limit(r1)
            byte[] r1 = r13.a((java.nio.ByteBuffer) r0)
            r12.read(r1)
            r0.flip()
            short r1 = r0.getShort()
            java.nio.channels.FileChannel r14 = r12.getChannel()
            java.nio.channels.FileChannel r25 = r12.getChannel()
            long r25 = r25.position()
            r29 = r7
            long r7 = (long) r1
            long r7 = r25 + r7
            r14.position(r7)
            goto L_0x032a
        L_0x0328:
            r29 = r7
        L_0x032a:
            r1 = 0
        L_0x032b:
            r0.position(r1)
            r1 = 8
            r0.limit(r1)
            java.nio.channels.FileChannel r1 = r12.getChannel()
            r1.read(r0)
            r0.flip()
            byte r1 = r0.get()
            byte r7 = r0.get()
            byte r8 = r0.get()
            byte r14 = r0.get()
            r31 = r6
            int r6 = r0.getInt()
            r32 = r0
            java.io.PrintStream r0 = java.lang.System.err
            r33 = r6
            java.lang.String r6 = "chunk: %c%c%c%c\n"
            r35 = r10
            r34 = r15
            r15 = 4
            java.lang.Object[] r10 = new java.lang.Object[r15]
            java.lang.Integer r11 = java.lang.Integer.valueOf(r1)
            r15 = 0
            r10[r15] = r11
            java.lang.Integer r11 = java.lang.Integer.valueOf(r7)
            r15 = 1
            r10[r15] = r11
            java.lang.Integer r11 = java.lang.Integer.valueOf(r8)
            r15 = 2
            r10[r15] = r11
            java.lang.Integer r11 = java.lang.Integer.valueOf(r14)
            r15 = 3
            r10[r15] = r11
            r0.printf(r6, r10)
            r0 = 100
            if (r1 != r0) goto L_0x0392
            r0 = 97
            if (r7 != r0) goto L_0x0392
            r0 = 116(0x74, float:1.63E-43)
            if (r8 != r0) goto L_0x0392
            r0 = 97
            if (r14 != r0) goto L_0x0392
            goto L_0x03a6
        L_0x0392:
            java.nio.channels.FileChannel r0 = r12.getChannel()
            long r0 = r0.position()
            java.nio.channels.FileChannel r6 = r12.getChannel()
            long r6 = r6.size()
            int r8 = (r0 > r6 ? 1 : (r0 == r6 ? 0 : -1))
            if (r8 != 0) goto L_0x0a43
        L_0x03a6:
            java.nio.channels.FileChannel r0 = r12.getChannel()
            long r0 = r0.position()
            java.nio.channels.FileChannel r6 = r12.getChannel()
            long r6 = r6.size()
            int r8 = (r0 > r6 ? 1 : (r0 == r6 ? 0 : -1))
            if (r8 == 0) goto L_0x0a3a
            r0 = 1
            if (r9 == r0) goto L_0x03ce
            r0 = 2
            if (r9 == r0) goto L_0x03ce
            if (r9 == r15) goto L_0x03ce
            r0 = 4
            if (r9 != r0) goto L_0x03c6
            goto L_0x03ce
        L_0x03c6:
            java.lang.IllegalStateException r0 = new java.lang.IllegalStateException
            java.lang.String r1 = "Error : Only 8bit, 16bit, 24bit and 32bit PCM are supported."
            r0.<init>(r1)
            throw r0
        L_0x03ce:
            r0 = -1
            if (r3 != r0) goto L_0x03df
            r1 = 1
            if (r9 == r1) goto L_0x03d7
            r1 = r9
        L_0x03d5:
            r3 = 4
            goto L_0x03d9
        L_0x03d7:
            r1 = 2
            goto L_0x03d5
        L_0x03d9:
            if (r1 != r3) goto L_0x03dd
            r14 = 3
            goto L_0x03e0
        L_0x03dd:
            r14 = r1
            goto L_0x03e0
        L_0x03df:
            r14 = r3
        L_0x03e0:
            if (r5 != r0) goto L_0x03e4
            r10 = r2
            goto L_0x03e5
        L_0x03e4:
            r10 = r5
        L_0x03e5:
            if (r4 != r0) goto L_0x03f5
            if (r14 >= r9) goto L_0x03f2
            r0 = 1
            if (r14 != r0) goto L_0x03ef
            r20 = 4
            goto L_0x03f7
        L_0x03ef:
            r20 = 3
            goto L_0x03f7
        L_0x03f2:
            r20 = 1
            goto L_0x03f7
        L_0x03f5:
            r20 = r4
        L_0x03f7:
            boolean r0 = r13.t
            if (r0 != 0) goto L_0x04d7
            r0 = 5
            java.lang.String[] r0 = new java.lang.String[r0]
            java.lang.String r1 = "none"
            r3 = 0
            r0[r3] = r1
            java.lang.String r1 = "no noise shaping"
            r4 = 1
            r0[r4] = r1
            java.lang.String r1 = "triangular spectral shape"
            r5 = 2
            r0[r5] = r1
            java.lang.String r1 = "ATH based noise shaping"
            r0[r15] = r1
            java.lang.String r1 = "ATH based noise shaping(less amplitude)"
            r6 = 4
            r0[r6] = r1
            java.lang.String[] r1 = new java.lang.String[r15]
            java.lang.String r6 = "rectangular"
            r1[r3] = r6
            java.lang.String r6 = "triangular"
            r1[r4] = r6
            java.lang.String r6 = "gaussian"
            r1[r5] = r6
            java.io.PrintStream r6 = java.lang.System.err
            java.lang.String r7 = "frequency : %d -> %d\n"
            java.lang.Object[] r8 = new java.lang.Object[r5]
            java.lang.Integer r5 = java.lang.Integer.valueOf(r2)
            r8[r3] = r5
            java.lang.Integer r5 = java.lang.Integer.valueOf(r10)
            r8[r4] = r5
            r6.printf(r7, r8)
            java.io.PrintStream r5 = java.lang.System.err
            java.lang.String r6 = "attenuation : %gdB\n"
            java.lang.Object[] r7 = new java.lang.Object[r4]
            java.lang.Double r4 = java.lang.Double.valueOf(r35)
            r7[r3] = r4
            r5.printf(r6, r7)
            java.io.PrintStream r4 = java.lang.System.err
            java.lang.String r5 = "bits per sample : %d -> %d\n"
            r6 = 2
            java.lang.Object[] r7 = new java.lang.Object[r6]
            int r6 = r9 * 8
            java.lang.Integer r6 = java.lang.Integer.valueOf(r6)
            r7[r3] = r6
            int r6 = r14 * 8
            java.lang.Integer r6 = java.lang.Integer.valueOf(r6)
            r8 = 1
            r7[r8] = r6
            r4.printf(r5, r7)
            java.io.PrintStream r4 = java.lang.System.err
            java.lang.String r5 = "nchannels : %d\n"
            java.lang.Object[] r6 = new java.lang.Object[r8]
            java.lang.Integer r7 = java.lang.Integer.valueOf(r34)
            r6[r3] = r7
            r4.printf(r5, r6)
            java.io.PrintStream r4 = java.lang.System.err
            java.lang.String r5 = "length : %d bytes, %g secs\n"
            r6 = 2
            java.lang.Object[] r7 = new java.lang.Object[r6]
            java.lang.Integer r6 = java.lang.Integer.valueOf(r33)
            r7[r3] = r6
            r37 = r12
            r6 = r33
            double r11 = (double) r6
            r38 = r14
            double r13 = (double) r9
            java.lang.Double.isNaN(r11)
            java.lang.Double.isNaN(r13)
            double r11 = r11 / r13
            r39 = r9
            r13 = r34
            double r8 = (double) r13
            java.lang.Double.isNaN(r8)
            double r11 = r11 / r8
            double r8 = (double) r2
            java.lang.Double.isNaN(r8)
            double r11 = r11 / r8
            java.lang.Double r3 = java.lang.Double.valueOf(r11)
            r8 = 1
            r7[r8] = r3
            r4.printf(r5, r7)
            if (r20 != 0) goto L_0x04b3
            java.io.PrintStream r0 = java.lang.System.err
            java.lang.String r1 = "dither type : none\n"
            r3 = 0
            java.lang.Object[] r4 = new java.lang.Object[r3]
            r0.printf(r1, r4)
            goto L_0x04cd
        L_0x04b3:
            r3 = 0
            java.io.PrintStream r4 = java.lang.System.err
            java.lang.String r5 = "dither type : %s, %s p.d.f, amp = %g\n"
            java.lang.Object[] r7 = new java.lang.Object[r15]
            r0 = r0[r20]
            r7[r3] = r0
            r0 = r1[r31]
            r1 = 1
            r7[r1] = r0
            java.lang.Double r0 = java.lang.Double.valueOf(r29)
            r1 = 2
            r7[r1] = r0
            r4.printf(r5, r7)
        L_0x04cd:
            java.io.PrintStream r0 = java.lang.System.err
            java.lang.String r1 = "\n"
            java.lang.Object[] r4 = new java.lang.Object[r3]
            r0.printf(r1, r4)
            goto L_0x04e1
        L_0x04d7:
            r39 = r9
            r37 = r12
            r38 = r14
            r6 = r33
            r13 = r34
        L_0x04e1:
            java.io.File r0 = new java.io.File     // Catch:{ IOException -> 0x0a30 }
            r14 = r28
            r0.<init>(r14)     // Catch:{ IOException -> 0x0a30 }
            java.io.FileOutputStream r12 = new java.io.FileOutputStream     // Catch:{ IOException -> 0x0a30 }
            r12.<init>(r0)     // Catch:{ IOException -> 0x0a30 }
            r0 = 44
            java.nio.ByteBuffer r0 = java.nio.ByteBuffer.allocate(r0)
            java.nio.ByteOrder r1 = java.nio.ByteOrder.LITTLE_ENDIAN
            java.nio.ByteBuffer r0 = r0.order(r1)
            java.lang.String r1 = "RIFF"
            byte[] r1 = r1.getBytes()
            r0.put(r1)
            r1 = 0
            r0.putInt(r1)
            java.lang.String r1 = "WAVEfmt "
            byte[] r1 = r1.getBytes()
            r0.put(r1)
            r1 = 16
            r0.putInt(r1)
            r1 = 1
            r0.putShort(r1)
            short r1 = (short) r13
            r0.putShort(r1)
            r0.putInt(r10)
            int r1 = r10 * r13
            int r1 = r1 * r38
            r0.putInt(r1)
            int r1 = r38 * r13
            short r1 = (short) r1
            r0.putShort(r1)
            int r1 = r38 * 8
            short r1 = (short) r1
            r0.putShort(r1)
            java.lang.String r1 = "data"
            byte[] r1 = r1.getBytes()
            r0.put(r1)
            r1 = 0
            r0.putInt(r1)
            r0.flip()
            r11 = r48
            r11.a((java.io.OutputStream) r12, (java.nio.ByteBuffer) r0)
            r22 = 8388607(0x7fffff, float:1.1754942E-38)
            r25 = 127(0x7f, float:1.78E-43)
            if (r20 == 0) goto L_0x058d
            r7 = r38
            r0 = 1
            if (r7 != r0) goto L_0x0558
            r0 = -128(0xffffffffffffff80, float:NaN)
            r1 = 127(0x7f, float:1.78E-43)
            goto L_0x055a
        L_0x0558:
            r0 = 0
            r1 = 0
        L_0x055a:
            r3 = 2
            if (r7 != r3) goto L_0x0561
            r0 = -32768(0xffffffffffff8000, float:NaN)
            r1 = 32767(0x7fff, float:4.5916E-41)
        L_0x0561:
            if (r7 != r15) goto L_0x0568
            r0 = -8388608(0xffffffffff800000, float:-Infinity)
            r1 = 8388607(0x7fffff, float:1.1754942E-38)
        L_0x0568:
            r3 = 4
            if (r7 != r3) goto L_0x0576
            r0 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = 2147483647(0x7fffffff, float:NaN)
            r3 = -2147483648(0xffffffff80000000, float:-0.0)
            r4 = 2147483647(0x7fffffff, float:NaN)
            goto L_0x0578
        L_0x0576:
            r3 = r0
            r4 = r1
        L_0x0578:
            r0 = r48
            r1 = r10
            r8 = r2
            r2 = r13
            r5 = r20
            r9 = r6
            r6 = r31
            r26 = r7
            r15 = r8
            r7 = r29
            int r0 = r0.a(r1, r2, r3, r4, r5, r6, r7)
            r8 = r0
            goto L_0x0592
        L_0x058d:
            r15 = r2
            r9 = r6
            r26 = r38
            r8 = 0
        L_0x0592:
            r6 = 4621819117588971520(0x4024000000000000, double:10.0)
            r28 = 4626322717216342016(0x4034000000000000, double:20.0)
            r4 = 4607182418800017408(0x3ff0000000000000, double:1.0)
            if (r18 == 0) goto L_0x092e
            boolean r0 = r11.t
            if (r0 != 0) goto L_0x05a8
            java.io.PrintStream r0 = java.lang.System.err
            java.lang.String r1 = "Pass 1\n"
            r2 = 0
            java.lang.Object[] r3 = new java.lang.Object[r2]
            r0.printf(r1, r3)
        L_0x05a8:
            if (r27 == 0) goto L_0x05b6
            java.io.File r0 = new java.io.File     // Catch:{ IOException -> 0x05b3 }
            r2 = r27
            r0.<init>(r2)     // Catch:{ IOException -> 0x05b3 }
        L_0x05b1:
            r3 = r0
            goto L_0x05bf
        L_0x05b3:
            r8 = r11
            goto L_0x0926
        L_0x05b6:
            java.lang.String r0 = "ssrc_"
            java.lang.String r1 = ".tmp"
            java.io.File r0 = java.io.File.createTempFile(r0, r1)     // Catch:{ IOException -> 0x05b3 }
            goto L_0x05b1
        L_0x05bf:
            java.io.FileOutputStream r2 = new java.io.FileOutputStream     // Catch:{ IOException -> 0x05b3 }
            r2.<init>(r3)     // Catch:{ IOException -> 0x05b3 }
            if (r19 == 0) goto L_0x0647
            if (r15 >= r10) goto L_0x05fa
            r27 = 8
            r30 = 4607182418800017408(0x3ff0000000000000, double:1.0)
            int r0 = r9 / r39
            int r32 = r0 / r13
            r0 = r48
            r1 = r37
            r33 = r2
            r9 = r3
            r3 = r13
            r4 = r39
            r5 = r27
            r6 = r15
            r7 = r10
            r10 = r8
            r15 = r9
            r8 = r30
            r42 = r10
            r40 = r35
            r10 = r32
            r11 = r18
            r43 = r14
            r14 = r12
            r12 = r20
            double r0 = r0.a(r1, r2, r3, r4, r5, r6, r7, r8, r10, r11, r12)
            r2 = 0
            r24[r2] = r0
        L_0x05f6:
            r11 = r40
            goto L_0x06e5
        L_0x05fa:
            r33 = r2
            r42 = r8
            r43 = r14
            r40 = r35
            r14 = r12
            r12 = r3
            if (r15 <= r10) goto L_0x0629
            r5 = 8
            r30 = 4607182418800017408(0x3ff0000000000000, double:1.0)
            int r6 = r9 / r39
            int r11 = r6 / r13
            r0 = r48
            r1 = r37
            r2 = r33
            r3 = r13
            r4 = r39
            r6 = r15
            r7 = r10
            r8 = r30
            r10 = r11
            r11 = r18
            r15 = r12
            r12 = r20
            double r0 = r0.b(r1, r2, r3, r4, r5, r6, r7, r8, r10, r11, r12)
            r2 = 0
            r24[r2] = r0
            goto L_0x05f6
        L_0x0629:
            r15 = r12
            r5 = 8
            r6 = 4607182418800017408(0x3ff0000000000000, double:1.0)
            int r0 = r9 / r39
            int r8 = r0 / r13
            r0 = r48
            r1 = r37
            r2 = r33
            r3 = r13
            r4 = r39
            r9 = r18
            r10 = r20
            double r0 = r0.a(r1, r2, r3, r4, r5, r6, r8, r9, r10)
            r2 = 0
            r24[r2] = r0
            goto L_0x05f6
        L_0x0647:
            r33 = r2
            r42 = r8
            r43 = r14
            r40 = r35
            r14 = r12
            r12 = r3
            if (r15 >= r10) goto L_0x0685
            r5 = 8
            r7 = r40
            double r0 = -r7
            double r0 = r0 / r28
            r3 = 4621819117588971520(0x4024000000000000, double:10.0)
            double r30 = java.lang.Math.pow(r3, r0)
            int r6 = r9 / r39
            int r11 = r6 / r13
            r0 = r48
            r1 = r37
            r2 = r33
            r3 = r13
            r4 = r39
            r6 = r15
            r8 = r7
            r7 = r10
            r44 = r8
            r8 = r30
            r10 = r11
            r11 = r18
            r15 = r12
            r12 = r20
            double r0 = r0.a(r1, r2, r3, r4, r5, r6, r7, r8, r10, r11, r12)
            r2 = 0
            r24[r2] = r0
            r11 = r44
            goto L_0x06e5
        L_0x0685:
            r44 = r40
            if (r15 <= r10) goto L_0x06ba
            r5 = 8
            r7 = r44
            double r0 = -r7
            double r0 = r0 / r28
            r3 = 4621819117588971520(0x4024000000000000, double:10.0)
            double r30 = java.lang.Math.pow(r3, r0)
            int r6 = r9 / r39
            int r11 = r6 / r13
            r0 = r48
            r1 = r37
            r2 = r33
            r3 = r13
            r4 = r39
            r6 = r15
            r8 = r7
            r7 = r10
            r46 = r8
            r8 = r30
            r10 = r11
            r11 = r18
            r15 = r12
            r12 = r20
            double r0 = r0.b(r1, r2, r3, r4, r5, r6, r7, r8, r10, r11, r12)
            r2 = 0
            r24[r2] = r0
            r11 = r46
            goto L_0x06e5
        L_0x06ba:
            r15 = r12
            r46 = r44
            r5 = 8
            r11 = r46
            double r0 = -r11
            double r0 = r0 / r28
            r6 = 4621819117588971520(0x4024000000000000, double:10.0)
            double r30 = java.lang.Math.pow(r6, r0)
            int r0 = r9 / r39
            int r8 = r0 / r13
            r0 = r48
            r1 = r37
            r2 = r33
            r3 = r13
            r4 = r39
            r9 = r6
            r6 = r30
            r9 = r18
            r10 = r20
            double r0 = r0.a(r1, r2, r3, r4, r5, r6, r8, r9, r10)
            r2 = 0
            r24[r2] = r0
        L_0x06e5:
            r33.close()
            r8 = r48
            boolean r0 = r8.t
            if (r0 != 0) goto L_0x0706
            java.io.PrintStream r0 = java.lang.System.err
            java.lang.String r1 = "\npeak : %gdB\n"
            r3 = 1
            java.lang.Object[] r4 = new java.lang.Object[r3]
            r5 = r24[r2]
            double r5 = java.lang.Math.log10(r5)
            double r5 = r5 * r28
            java.lang.Double r3 = java.lang.Double.valueOf(r5)
            r4[r2] = r3
            r0.printf(r1, r4)
        L_0x0706:
            if (r19 != 0) goto L_0x072b
            r0 = r24[r2]
            double r3 = -r11
            double r3 = r3 / r28
            r5 = 4621819117588971520(0x4024000000000000, double:10.0)
            double r3 = java.lang.Math.pow(r5, r3)
            int r7 = (r0 > r3 ? 1 : (r0 == r3 ? 0 : -1))
            if (r7 >= 0) goto L_0x071c
            r9 = 4607182418800017408(0x3ff0000000000000, double:1.0)
            r24[r2] = r9
            goto L_0x073b
        L_0x071c:
            r9 = 4607182418800017408(0x3ff0000000000000, double:1.0)
            r0 = r24[r2]
            double r3 = r11 / r28
            double r3 = java.lang.Math.pow(r5, r3)
            double r0 = r0 * r3
            r24[r2] = r0
            goto L_0x073b
        L_0x072b:
            r5 = 4621819117588971520(0x4024000000000000, double:10.0)
            r9 = 4607182418800017408(0x3ff0000000000000, double:1.0)
            r0 = r24[r2]
            double r3 = r11 / r28
            double r3 = java.lang.Math.pow(r5, r3)
            double r0 = r0 * r3
            r24[r2] = r0
        L_0x073b:
            boolean r0 = r8.t
            if (r0 != 0) goto L_0x0748
            java.io.PrintStream r0 = java.lang.System.err
            java.lang.String r1 = "\nPass 2\n"
            java.lang.Object[] r3 = new java.lang.Object[r2]
            r0.printf(r1, r3)
        L_0x0748:
            if (r20 == 0) goto L_0x07e3
            switch(r26) {
                case 1: goto L_0x07b3;
                case 2: goto L_0x077f;
                case 3: goto L_0x074f;
                default: goto L_0x074d;
            }
        L_0x074d:
            goto L_0x080a
        L_0x074f:
            if (r19 != 0) goto L_0x0770
            r0 = r24[r2]
            r3 = r42
            int r4 = r22 - r3
            double r4 = (double) r4
            r6 = 4710765209155796992(0x415fffffc0000000, double:8388607.0)
            java.lang.Double.isNaN(r4)
            double r4 = r4 / r6
            int r6 = (r0 > r4 ? 1 : (r0 == r4 ? 0 : -1))
            if (r6 < 0) goto L_0x0766
            goto L_0x0772
        L_0x0766:
            r0 = r24[r2]
            double r4 = r9 / r0
            r0 = 4710765209155796992(0x415fffffc0000000, double:8388607.0)
            goto L_0x077c
        L_0x0770:
            r3 = r42
        L_0x0772:
            r0 = r24[r2]
            double r4 = r9 / r0
            int r0 = r22 - r3
            double r0 = (double) r0
            java.lang.Double.isNaN(r0)
        L_0x077c:
            double r4 = r4 * r0
            goto L_0x07e0
        L_0x077f:
            r3 = r42
            if (r19 != 0) goto L_0x07a4
            r0 = r24[r2]
            r4 = 32767(0x7fff, float:4.5916E-41)
            int r5 = 32767 - r3
            double r5 = (double) r5
            r11 = 4674736138332667904(0x40dfffc000000000, double:32767.0)
            java.lang.Double.isNaN(r5)
            double r5 = r5 / r11
            int r7 = (r0 > r5 ? 1 : (r0 == r5 ? 0 : -1))
            if (r7 < 0) goto L_0x0798
            goto L_0x07a6
        L_0x0798:
            r0 = r24[r2]
            double r4 = r9 / r0
            r0 = 4674736138332667904(0x40dfffc000000000, double:32767.0)
            double r4 = r4 * r0
            goto L_0x07e0
        L_0x07a4:
            r4 = 32767(0x7fff, float:4.5916E-41)
        L_0x07a6:
            r0 = r24[r2]
            double r0 = r9 / r0
            int r3 = 32767 - r3
            double r3 = (double) r3
            java.lang.Double.isNaN(r3)
            double r4 = r0 * r3
            goto L_0x07e0
        L_0x07b3:
            r3 = r42
            if (r19 != 0) goto L_0x07d4
            r0 = r24[r2]
            int r4 = 127 - r3
            double r4 = (double) r4
            r6 = 4638637247447433216(0x405fc00000000000, double:127.0)
            java.lang.Double.isNaN(r4)
            double r4 = r4 / r6
            int r6 = (r0 > r4 ? 1 : (r0 == r4 ? 0 : -1))
            if (r6 < 0) goto L_0x07ca
            goto L_0x07d4
        L_0x07ca:
            r0 = r24[r2]
            double r4 = r9 / r0
            r0 = 4638637247447433216(0x405fc00000000000, double:127.0)
            goto L_0x07de
        L_0x07d4:
            r0 = r24[r2]
            double r4 = r9 / r0
            int r0 = 127 - r3
            double r0 = (double) r0
            java.lang.Double.isNaN(r0)
        L_0x07de:
            double r4 = r4 * r0
        L_0x07e0:
            r16 = r4
            goto L_0x080a
        L_0x07e3:
            switch(r26) {
                case 1: goto L_0x07ff;
                case 2: goto L_0x07f3;
                case 3: goto L_0x07e7;
                default: goto L_0x07e6;
            }
        L_0x07e6:
            goto L_0x080a
        L_0x07e7:
            r0 = r24[r2]
            double r4 = r9 / r0
            r0 = 4710765209155796992(0x415fffffc0000000, double:8388607.0)
            double r16 = r4 * r0
            goto L_0x080a
        L_0x07f3:
            r0 = r24[r2]
            double r4 = r9 / r0
            r0 = 4674736138332667904(0x40dfffc000000000, double:32767.0)
            double r16 = r4 * r0
            goto L_0x080a
        L_0x07ff:
            r0 = r24[r2]
            double r4 = r9 / r0
            r0 = 4638637247447433216(0x405fc00000000000, double:127.0)
            double r16 = r4 * r0
        L_0x080a:
            r8.s = r2
            r48.b()
            long r0 = r15.length()
            r2 = 8
            long r0 = r0 / r2
            int r6 = (int) r0
            java.io.FileInputStream r0 = new java.io.FileInputStream
            r0.<init>(r15)
            java.nio.channels.FileChannel r7 = r0.getChannel()
            r0 = 8
            java.nio.ByteBuffer r11 = java.nio.ByteBuffer.allocate(r0)
            r0 = 0
            r12 = 0
        L_0x0828:
            if (r0 >= r6) goto L_0x08f5
            r11.clear()
            r7.read(r11)
            r11.flip()
            double r1 = r11.getDouble()
            double r1 = r1 * r16
            int r5 = r0 + 1
            switch(r26) {
                case 1: goto L_0x08ad;
                case 2: goto L_0x087f;
                case 3: goto L_0x0846;
                default: goto L_0x083e;
            }
        L_0x083e:
            r9 = r5
            r10 = 3
            r22 = 8
        L_0x0842:
            r23 = 2
            goto L_0x08d7
        L_0x0846:
            if (r20 == 0) goto L_0x0856
            r0 = r48
            r3 = r24
            r4 = r20
            r9 = r5
            r5 = r12
            int r0 = r0.a((double) r1, (double[]) r3, (int) r4, (int) r5)
        L_0x0854:
            r10 = 3
            goto L_0x085c
        L_0x0856:
            r9 = r5
            int r0 = r8.a((double) r1)
            goto L_0x0854
        L_0x085c:
            java.nio.ByteBuffer r1 = java.nio.ByteBuffer.allocate(r10)
            r2 = r0 & 255(0xff, float:3.57E-43)
            byte r2 = (byte) r2
            r1.put(r2)
            r22 = 8
            int r0 = r0 >> 8
            r2 = r0 & 255(0xff, float:3.57E-43)
            byte r2 = (byte) r2
            r1.put(r2)
            int r0 = r0 >> 8
            r0 = r0 & 255(0xff, float:3.57E-43)
            byte r0 = (byte) r0
            r1.put(r0)
            r1.flip()
            r8.a((java.io.OutputStream) r14, (java.nio.ByteBuffer) r1)
            goto L_0x0842
        L_0x087f:
            r9 = r5
            r10 = 3
            r22 = 8
            if (r20 == 0) goto L_0x0893
            r0 = r48
            r3 = r24
            r4 = r20
            r5 = r12
            int r0 = r0.a((double) r1, (double[]) r3, (int) r4, (int) r5)
        L_0x0890:
            r23 = 2
            goto L_0x0898
        L_0x0893:
            int r0 = r8.a((double) r1)
            goto L_0x0890
        L_0x0898:
            java.nio.ByteBuffer r1 = java.nio.ByteBuffer.allocate(r23)
            java.nio.ByteOrder r2 = java.nio.ByteOrder.LITTLE_ENDIAN
            java.nio.ByteBuffer r1 = r1.order(r2)
            short r0 = (short) r0
            r1.putShort(r0)
            r1.flip()
            r8.a((java.io.OutputStream) r14, (java.nio.ByteBuffer) r1)
            goto L_0x08d7
        L_0x08ad:
            r9 = r5
            r10 = 3
            r22 = 8
            r23 = 2
            if (r20 == 0) goto L_0x08c2
            r0 = r48
            r3 = r24
            r4 = r20
            r5 = r12
            int r0 = r0.a((double) r1, (double[]) r3, (int) r4, (int) r5)
        L_0x08c0:
            r1 = 1
            goto L_0x08c7
        L_0x08c2:
            int r0 = r8.a((double) r1)
            goto L_0x08c0
        L_0x08c7:
            java.nio.ByteBuffer r2 = java.nio.ByteBuffer.allocate(r1)
            int r0 = r0 + 128
            byte r0 = (byte) r0
            r2.put(r0)
            r2.flip()
            r8.a((java.io.OutputStream) r14, (java.nio.ByteBuffer) r2)
        L_0x08d7:
            int r0 = r12 + 1
            if (r0 != r13) goto L_0x08dd
            r12 = 0
            goto L_0x08de
        L_0x08dd:
            r12 = r0
        L_0x08de:
            r0 = 262143(0x3ffff, float:3.6734E-40)
            r0 = r0 & r9
            if (r0 != 0) goto L_0x08f0
            double r0 = (double) r9
            double r2 = (double) r6
            java.lang.Double.isNaN(r0)
            java.lang.Double.isNaN(r2)
            double r0 = r0 / r2
            r8.d(r0)
        L_0x08f0:
            r0 = r9
            r9 = 4607182418800017408(0x3ff0000000000000, double:1.0)
            goto L_0x0828
        L_0x08f5:
            r0 = r9
            r8.d(r0)
            boolean r2 = r8.t
            if (r2 != 0) goto L_0x0908
            java.io.PrintStream r2 = java.lang.System.err
            java.lang.String r3 = "\n"
            r4 = 0
            java.lang.Object[] r5 = new java.lang.Object[r4]
            r2.printf(r3, r5)
            goto L_0x0909
        L_0x0908:
            r4 = 0
        L_0x0909:
            r7.close()
            if (r15 == 0) goto L_0x0920
            boolean r2 = r15.delete()
            if (r2 != 0) goto L_0x0920
            java.io.PrintStream r2 = java.lang.System.err
            java.lang.String r3 = "Failed to remove %s\n"
            r5 = 1
            java.lang.Object[] r6 = new java.lang.Object[r5]
            r6[r4] = r15
            r2.printf(r3, r6)
        L_0x0920:
            r16 = r0
            r15 = r8
            r2 = 0
            goto L_0x09b7
        L_0x0926:
            java.lang.IllegalStateException r0 = new java.lang.IllegalStateException
            java.lang.String r1 = "cannot open temporary file."
            r0.<init>(r1)
            throw r0
        L_0x092e:
            r16 = r4
            r5 = r6
            r8 = r11
            r43 = r14
            r14 = r12
            r11 = r35
            if (r15 >= r10) goto L_0x0960
            double r0 = -r11
            double r0 = r0 / r28
            double r11 = java.lang.Math.pow(r5, r0)
            int r6 = r9 / r39
            int r19 = r6 / r13
            r0 = r48
            r1 = r37
            r2 = r14
            r3 = r13
            r4 = r39
            r5 = r26
            r6 = r15
            r7 = r10
            r15 = r8
            r8 = r11
            r10 = r19
            r11 = r18
            r12 = r20
            double r0 = r0.a(r1, r2, r3, r4, r5, r6, r7, r8, r10, r11, r12)
            r2 = 0
            r24[r2] = r0
            goto L_0x09aa
        L_0x0960:
            if (r15 <= r10) goto L_0x0989
            double r0 = -r11
            double r0 = r0 / r28
            double r11 = java.lang.Math.pow(r5, r0)
            int r6 = r9 / r39
            int r19 = r6 / r13
            r0 = r48
            r1 = r37
            r2 = r14
            r3 = r13
            r4 = r39
            r5 = r26
            r6 = r15
            r7 = r10
            r15 = r8
            r8 = r11
            r10 = r19
            r11 = r18
            r12 = r20
            double r0 = r0.b(r1, r2, r3, r4, r5, r6, r7, r8, r10, r11, r12)
            r2 = 0
            r24[r2] = r0
            goto L_0x09aa
        L_0x0989:
            r15 = r8
            double r0 = -r11
            double r0 = r0 / r28
            double r6 = java.lang.Math.pow(r5, r0)
            int r0 = r9 / r39
            int r8 = r0 / r13
            r0 = r48
            r1 = r37
            r2 = r14
            r3 = r13
            r4 = r39
            r5 = r26
            r9 = r18
            r10 = r20
            double r0 = r0.a(r1, r2, r3, r4, r5, r6, r8, r9, r10)
            r2 = 0
            r24[r2] = r0
        L_0x09aa:
            boolean r0 = r15.t
            if (r0 != 0) goto L_0x09b7
            java.io.PrintStream r0 = java.lang.System.err
            java.lang.String r1 = "\n"
            java.lang.Object[] r3 = new java.lang.Object[r2]
            r0.printf(r1, r3)
        L_0x09b7:
            if (r20 == 0) goto L_0x09bc
            r15.a((int) r13)
        L_0x09bc:
            if (r18 != 0) goto L_0x09e0
            r0 = r24[r2]
            int r3 = (r0 > r16 ? 1 : (r0 == r16 ? 0 : -1))
            if (r3 <= 0) goto L_0x09e0
            boolean r0 = r15.t
            if (r0 != 0) goto L_0x09e0
            java.io.PrintStream r0 = java.lang.System.err
            java.lang.String r1 = "clipping detected : %gdB\n"
            r6 = 1
            java.lang.Object[] r3 = new java.lang.Object[r6]
            r4 = r24[r2]
            double r4 = java.lang.Math.log10(r4)
            double r4 = r4 * r28
            java.lang.Double r4 = java.lang.Double.valueOf(r4)
            r3[r2] = r4
            r0.printf(r1, r3)
        L_0x09e0:
            r14.close()
            java.io.File r0 = new java.io.File
            r7 = r43
            r0.<init>(r7)
            long r1 = r0.length()
            int r1 = (int) r1
            java.io.RandomAccessFile r2 = new java.io.RandomAccessFile
            java.lang.String r3 = "rw"
            r2.<init>(r0, r3)
            java.nio.channels.FileChannel r0 = r2.getChannel()
            r8 = 4
            java.nio.ByteBuffer r2 = java.nio.ByteBuffer.allocate(r8)
            java.nio.ByteOrder r3 = java.nio.ByteOrder.LITTLE_ENDIAN
            java.nio.ByteBuffer r2 = r2.order(r3)
            int r3 = r1 + -8
            r10 = 0
            r2.position(r10)
            r2.limit(r8)
            r2.putInt(r3)
            r2.flip()
            r3 = 4
            r0.write(r2, r3)
            int r1 = r1 + -44
            r2.position(r10)
            r2.limit(r8)
            r2.putInt(r1)
            r2.flip()
            r3 = 40
            r0.write(r2, r3)
            r0.close()
            return
        L_0x0a30:
            r15 = r48
            java.lang.IllegalArgumentException r0 = new java.lang.IllegalArgumentException
            java.lang.String r1 = "cannot open output file."
            r0.<init>(r1)
            throw r0
        L_0x0a3a:
            r15 = r13
            java.lang.IllegalStateException r0 = new java.lang.IllegalStateException
            java.lang.String r1 = "Couldn't find data chank"
            r0.<init>(r1)
            throw r0
        L_0x0a43:
            r15 = r2
            r39 = r9
            r37 = r12
            r2 = r27
            r7 = r28
            r9 = r33
            r13 = r34
            r11 = r35
            r0 = -1
            r1 = 16
            r6 = 1
            r8 = 4
            r10 = 0
            r22 = 8
            r23 = 2
            java.nio.channels.FileChannel r14 = r37.getChannel()
            java.nio.channels.FileChannel r20 = r37.getChannel()
            long r20 = r20.position()
            long r0 = (long) r9
            long r0 = r20 + r0
            r14.position(r0)
            r10 = r11
            r2 = r15
            r6 = r31
            r0 = r32
            r12 = r37
            r9 = r39
            r1 = 0
            r15 = r13
            r13 = r48
            goto L_0x032b
        L_0x0a7e:
            java.lang.IllegalStateException r0 = new java.lang.IllegalStateException
            java.lang.String r1 = "Error: Only PCM is supported."
            r0.<init>(r1)
            throw r0
        L_0x0a86:
            java.lang.IllegalArgumentException r0 = new java.lang.IllegalArgumentException
            java.lang.String r1 = "cannot open input file."
            r0.<init>(r1)
            throw r0
        L_0x0a8e:
            r48.a()
            java.lang.IllegalStateException r0 = new java.lang.IllegalStateException
            java.lang.String r1 = "too few arguments"
            r0.<init>(r1)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.smarthome.framework.plugin.rn.nativemodule.audio.SSRC.<init>(java.lang.String[]):void");
    }

    /* JADX WARNING: Removed duplicated region for block: B:23:0x0071  */
    /* JADX WARNING: Removed duplicated region for block: B:24:0x0073  */
    /* JADX WARNING: Removed duplicated region for block: B:26:0x0076  */
    /* JADX WARNING: Removed duplicated region for block: B:31:0x0083  */
    /* JADX WARNING: Removed duplicated region for block: B:34:0x0089  */
    /* JADX WARNING: Removed duplicated region for block: B:40:0x015b  */
    /* JADX WARNING: Removed duplicated region for block: B:54:0x019e  */
    /* JADX WARNING: Removed duplicated region for block: B:57:0x01ab  */
    /* JADX WARNING: Removed duplicated region for block: B:58:0x01d8  */
    /* JADX WARNING: Removed duplicated region for block: B:65:0x022d  */
    /* JADX WARNING: Removed duplicated region for block: B:67:0x0238  */
    /* JADX WARNING: Removed duplicated region for block: B:72:0x0247  */
    /* JADX WARNING: Removed duplicated region for block: B:77:? A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public SSRC(java.io.InputStream r24, java.io.OutputStream r25, int r26, int r27, int r28, int r29, int r30, int r31, double r32, int r34, boolean r35) throws java.io.IOException {
        /*
            r23 = this;
            r13 = r23
            r9 = r26
            r10 = r28
            r14 = r30
            r11 = r31
            r7 = r32
            r0 = r34
            r23.<init>()
            java.nio.ByteOrder r1 = java.nio.ByteOrder.LITTLE_ENDIAN
            r13.b = r1
            com.xiaomi.smarthome.framework.plugin.rn.nativemodule.audio.SplitRadixFft r1 = new com.xiaomi.smarthome.framework.plugin.rn.nativemodule.audio.SplitRadixFft
            r1.<init>()
            r13.c = r1
            r1 = 4639481672377565184(0x4062c00000000000, double:150.0)
            r13.e = r1
            r1 = 4641240890982006784(0x4069000000000000, double:200.0)
            r13.f = r1
            r15 = 1
            r13.g = r15
            r12 = 0
            r13.t = r12
            double[] r6 = new double[r15]
            r1 = 0
            r6[r12] = r1
            if (r0 < 0) goto L_0x0260
            r1 = 4
            if (r0 > r1) goto L_0x0260
            r2 = r35
            r13.t = r2
            boolean r2 = r13.t
            if (r2 != 0) goto L_0x0049
            java.io.PrintStream r2 = java.lang.System.err
            java.lang.String r3 = "Shibatch sampling rate converter version 1.30(high precision/nio)\n\n"
            java.lang.Object[] r4 = new java.lang.Object[r12]
            r2.printf(r3, r4)
        L_0x0049:
            r2 = 3
            r3 = 2
            if (r10 == r15) goto L_0x005c
            if (r10 == r3) goto L_0x005c
            if (r10 == r2) goto L_0x005c
            if (r10 != r1) goto L_0x0054
            goto L_0x005c
        L_0x0054:
            java.lang.IllegalStateException r0 = new java.lang.IllegalStateException
            java.lang.String r1 = "Error : Only 8bit, 16bit, 24bit and 32bit PCM are supported."
            r0.<init>(r1)
            throw r0
        L_0x005c:
            r4 = -1
            r5 = r29
            if (r5 != r4) goto L_0x006c
            if (r10 == r15) goto L_0x0065
            r5 = r10
            goto L_0x0066
        L_0x0065:
            r5 = 2
        L_0x0066:
            if (r5 != r1) goto L_0x006c
            r5 = r27
            r7 = 3
            goto L_0x006f
        L_0x006c:
            r7 = r5
            r5 = r27
        L_0x006f:
            if (r5 != r4) goto L_0x0073
            r8 = r9
            goto L_0x0074
        L_0x0073:
            r8 = r5
        L_0x0074:
            if (r0 != r4) goto L_0x0083
            if (r7 >= r10) goto L_0x0080
            if (r7 != r15) goto L_0x007d
            r16 = 4
            goto L_0x0085
        L_0x007d:
            r16 = 3
            goto L_0x0085
        L_0x0080:
            r16 = 1
            goto L_0x0085
        L_0x0083:
            r16 = r0
        L_0x0085:
            boolean r0 = r13.t
            if (r0 != 0) goto L_0x0159
            r0 = 5
            java.lang.String[] r0 = new java.lang.String[r0]
            java.lang.String r4 = "none"
            r0[r12] = r4
            java.lang.String r4 = "no noise shaping"
            r0[r15] = r4
            java.lang.String r4 = "triangular spectral shape"
            r0[r3] = r4
            java.lang.String r4 = "ATH based noise shaping"
            r0[r2] = r4
            java.lang.String r4 = "ATH based noise shaping(less amplitude)"
            r0[r1] = r4
            java.lang.String[] r4 = new java.lang.String[r2]
            java.lang.String r5 = "rectangular"
            r4[r12] = r5
            java.lang.String r5 = "triangular"
            r4[r15] = r5
            java.lang.String r5 = "gaussian"
            r4[r3] = r5
            java.io.PrintStream r5 = java.lang.System.err
            java.lang.String r1 = "frequency : %d -> %d\n"
            java.lang.Object[] r2 = new java.lang.Object[r3]
            java.lang.Integer r19 = java.lang.Integer.valueOf(r26)
            r2[r12] = r19
            java.lang.Integer r19 = java.lang.Integer.valueOf(r8)
            r2[r15] = r19
            r5.printf(r1, r2)
            java.io.PrintStream r1 = java.lang.System.err
            java.lang.String r2 = "attenuation : %gdB\n"
            java.lang.Object[] r5 = new java.lang.Object[r15]
            java.lang.Double r19 = java.lang.Double.valueOf(r32)
            r5[r12] = r19
            r1.printf(r2, r5)
            java.io.PrintStream r1 = java.lang.System.err
            java.lang.String r2 = "bits per sample : %d -> %d\n"
            java.lang.Object[] r5 = new java.lang.Object[r3]
            int r19 = r10 * 8
            java.lang.Integer r19 = java.lang.Integer.valueOf(r19)
            r5[r12] = r19
            int r19 = r7 * 8
            java.lang.Integer r19 = java.lang.Integer.valueOf(r19)
            r5[r15] = r19
            r1.printf(r2, r5)
            java.io.PrintStream r1 = java.lang.System.err
            java.lang.String r2 = "nchannels : %d\n"
            java.lang.Object[] r5 = new java.lang.Object[r15]
            java.lang.Integer r19 = java.lang.Integer.valueOf(r30)
            r5[r12] = r19
            r1.printf(r2, r5)
            java.io.PrintStream r1 = java.lang.System.err
            java.lang.String r2 = "length : %d bytes, %g secs\n"
            java.lang.Object[] r5 = new java.lang.Object[r3]
            java.lang.Integer r19 = java.lang.Integer.valueOf(r31)
            r5[r12] = r19
            r21 = r4
            double r3 = (double) r11
            double r12 = (double) r10
            java.lang.Double.isNaN(r3)
            java.lang.Double.isNaN(r12)
            double r3 = r3 / r12
            double r12 = (double) r14
            java.lang.Double.isNaN(r12)
            double r3 = r3 / r12
            double r12 = (double) r9
            java.lang.Double.isNaN(r12)
            double r3 = r3 / r12
            java.lang.Double r3 = java.lang.Double.valueOf(r3)
            r5[r15] = r3
            r1.printf(r2, r5)
            if (r16 != 0) goto L_0x0131
            java.io.PrintStream r0 = java.lang.System.err
            java.lang.String r1 = "dither type : none\n"
            r12 = 0
            java.lang.Object[] r2 = new java.lang.Object[r12]
            r0.printf(r1, r2)
            goto L_0x0150
        L_0x0131:
            r12 = 0
            java.io.PrintStream r1 = java.lang.System.err
            java.lang.String r2 = "dither type : %s, %s p.d.f, amp = %g\n"
            r3 = 3
            java.lang.Object[] r4 = new java.lang.Object[r3]
            r0 = r0[r16]
            r4[r12] = r0
            r0 = r21[r12]
            r4[r15] = r0
            r21 = 4595653203753948938(0x3fc70a3d70a3d70a, double:0.18)
            java.lang.Double r0 = java.lang.Double.valueOf(r21)
            r3 = 2
            r4[r3] = r0
            r1.printf(r2, r4)
        L_0x0150:
            java.io.PrintStream r0 = java.lang.System.err
            java.lang.String r1 = "\n"
            java.lang.Object[] r2 = new java.lang.Object[r12]
            r0.printf(r1, r2)
        L_0x0159:
            if (r16 == 0) goto L_0x019e
            if (r7 != r15) goto L_0x0162
            r0 = -128(0xffffffffffffff80, float:NaN)
            r1 = 127(0x7f, float:1.78E-43)
            goto L_0x0164
        L_0x0162:
            r0 = 0
            r1 = 0
        L_0x0164:
            r2 = 2
            if (r7 != r2) goto L_0x016b
            r0 = -32768(0xffffffffffff8000, float:NaN)
            r1 = 32767(0x7fff, float:4.5916E-41)
        L_0x016b:
            r2 = 3
            if (r7 != r2) goto L_0x0173
            r0 = -8388608(0xffffffffff800000, float:-Infinity)
            r1 = 8388607(0x7fffff, float:1.1754942E-38)
        L_0x0173:
            r2 = 4
            if (r7 != r2) goto L_0x0181
            r0 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = 2147483647(0x7fffffff, float:NaN)
            r3 = -2147483648(0xffffffff80000000, float:-0.0)
            r4 = 2147483647(0x7fffffff, float:NaN)
            goto L_0x0183
        L_0x0181:
            r3 = r0
            r4 = r1
        L_0x0183:
            r13 = 0
            r17 = 4595653203753948938(0x3fc70a3d70a3d70a, double:0.18)
            r0 = r23
            r1 = r8
            r2 = r30
            r5 = r16
            r19 = r6
            r6 = r13
            r20 = r7
            r15 = r8
            r12 = r32
            r7 = r17
            r0.a(r1, r2, r3, r4, r5, r6, r7)
            goto L_0x01a5
        L_0x019e:
            r19 = r6
            r20 = r7
            r15 = r8
            r12 = r32
        L_0x01a5:
            r0 = 4621819117588971520(0x4024000000000000, double:10.0)
            r17 = 4626322717216342016(0x4034000000000000, double:20.0)
            if (r9 >= r15) goto L_0x01d8
            double r2 = -r12
            double r2 = r2 / r17
            double r12 = java.lang.Math.pow(r0, r2)
            int r0 = r11 / r10
            int r11 = r0 / r14
            r21 = 0
            r0 = r23
            r1 = r24
            r2 = r25
            r3 = r30
            r4 = r28
            r5 = r20
            r6 = r26
            r7 = r15
            r8 = r12
            r10 = r11
            r11 = r21
            r13 = 0
            r12 = r16
            double r0 = r0.a(r1, r2, r3, r4, r5, r6, r7, r8, r10, r11, r12)
            r19[r13] = r0
            r1 = r23
            r15 = 0
            goto L_0x0229
        L_0x01d8:
            r8 = 0
            if (r9 <= r15) goto L_0x0207
            double r2 = -r12
            double r2 = r2 / r17
            double r12 = java.lang.Math.pow(r0, r2)
            int r0 = r11 / r10
            int r11 = r0 / r14
            r21 = 0
            r0 = r23
            r1 = r24
            r2 = r25
            r3 = r30
            r4 = r28
            r5 = r20
            r6 = r26
            r7 = r15
            r15 = 0
            r8 = r12
            r10 = r11
            r11 = r21
            r12 = r16
            double r0 = r0.b(r1, r2, r3, r4, r5, r6, r7, r8, r10, r11, r12)
            r19[r15] = r0
        L_0x0204:
            r1 = r23
            goto L_0x0229
        L_0x0207:
            r15 = 0
            double r2 = -r12
            double r2 = r2 / r17
            double r6 = java.lang.Math.pow(r0, r2)
            int r0 = r11 / r10
            int r8 = r0 / r14
            r9 = 0
            r0 = r23
            r1 = r24
            r2 = r25
            r3 = r30
            r4 = r28
            r5 = r20
            r10 = r16
            double r0 = r0.a(r1, r2, r3, r4, r5, r6, r8, r9, r10)
            r19[r15] = r0
            goto L_0x0204
        L_0x0229:
            boolean r0 = r1.t
            if (r0 != 0) goto L_0x0236
            java.io.PrintStream r0 = java.lang.System.err
            java.lang.String r2 = "\n"
            java.lang.Object[] r3 = new java.lang.Object[r15]
            r0.printf(r2, r3)
        L_0x0236:
            if (r16 == 0) goto L_0x023b
            r1.a((int) r14)
        L_0x023b:
            r2 = r19[r15]
            r4 = 4607182418800017408(0x3ff0000000000000, double:1.0)
            int r0 = (r2 > r4 ? 1 : (r2 == r4 ? 0 : -1))
            if (r0 <= 0) goto L_0x025f
            boolean r0 = r1.t
            if (r0 != 0) goto L_0x025f
            java.io.PrintStream r0 = java.lang.System.err
            java.lang.String r2 = "clipping detected : %gdB\n"
            r3 = 1
            java.lang.Object[] r3 = new java.lang.Object[r3]
            r4 = r19[r15]
            double r4 = java.lang.Math.log10(r4)
            double r4 = r4 * r17
            java.lang.Double r4 = java.lang.Double.valueOf(r4)
            r3[r15] = r4
            r0.printf(r2, r3)
        L_0x025f:
            return
        L_0x0260:
            r1 = r13
            java.lang.IllegalArgumentException r2 = new java.lang.IllegalArgumentException
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>()
            java.lang.String r4 = "unrecognized dither type : "
            r3.append(r4)
            r3.append(r0)
            java.lang.String r0 = r3.toString()
            r2.<init>(r0)
            throw r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.smarthome.framework.plugin.rn.nativemodule.audio.SSRC.<init>(java.io.InputStream, java.io.OutputStream, int, int, int, int, int, int, double, int, boolean):void");
    }

    /* access modifiers changed from: protected */
    public byte[] a(ByteBuffer byteBuffer) {
        byte[] bArr = new byte[(byteBuffer.limit() - byteBuffer.position())];
        byteBuffer.get(bArr, 0, bArr.length);
        return bArr;
    }

    /* access modifiers changed from: protected */
    public void a(OutputStream outputStream, ByteBuffer byteBuffer) {
        try {
            outputStream.write(a(byteBuffer));
        } catch (IOException unused) {
        }
    }
}
