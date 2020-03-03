package javax.jmdns.impl;

import com.huawei.hms.support.api.push.HmsPushConst;
import com.taobao.weex.el.parse.Operators;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.jmdns.impl.constants.DNSConstants;
import javax.jmdns.impl.constants.DNSRecordClass;
import javax.jmdns.impl.constants.DNSRecordType;

public final class DNSIncoming extends DNSMessage {

    /* renamed from: a  reason: collision with root package name */
    public static boolean f2629a = true;
    private static Logger i = Logger.getLogger(DNSIncoming.class.getName());
    private static final char[] n = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};
    private final DatagramPacket j;
    private final long k;
    private final MessageInputStream l;
    private int m;

    public static class MessageInputStream extends ByteArrayInputStream {
        private static Logger b = Logger.getLogger(MessageInputStream.class.getName());

        /* renamed from: a  reason: collision with root package name */
        final Map<Integer, String> f2631a;

        public MessageInputStream(byte[] bArr, int i) {
            this(bArr, 0, i);
        }

        public MessageInputStream(byte[] bArr, int i, int i2) {
            super(bArr, i, i2);
            this.f2631a = new HashMap();
        }

        public int a() {
            return read();
        }

        public int b() {
            return (read() << 8) | read();
        }

        public int c() {
            return (b() << 16) | b();
        }

        public byte[] a(int i) {
            byte[] bArr = new byte[i];
            read(bArr, 0, i);
            return bArr;
        }

        public String b(int i) {
            StringBuilder sb = new StringBuilder(i);
            int i2 = 0;
            while (i2 < i) {
                int read = read();
                int i3 = read >> 4;
                switch (i3) {
                    case 0:
                    case 1:
                    case 2:
                    case 3:
                    case 4:
                    case 5:
                    case 6:
                    case 7:
                        break;
                    default:
                        switch (i3) {
                            case 12:
                            case 13:
                                read = ((read & 31) << 6) | (read() & 63);
                                i2++;
                                break;
                            case 14:
                                read = ((read & 15) << 12) | ((read() & 63) << 6) | (read() & 63);
                                i2 = i2 + 1 + 1;
                                break;
                            default:
                                read = ((read & 63) << 4) | (read() & 15);
                                i2++;
                                break;
                        }
                }
                sb.append((char) read);
                i2++;
            }
            return sb.toString();
        }

        /* access modifiers changed from: protected */
        public synchronized int d() {
            return this.pos < this.count ? this.buf[this.pos] & 255 : -1;
        }

        /* JADX WARNING: Removed duplicated region for block: B:24:0x0101 A[LOOP:3: B:22:0x00fb->B:24:0x0101, LOOP_END] */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public java.lang.String e() {
            /*
                r7 = this;
                java.util.HashMap r0 = new java.util.HashMap
                r0.<init>()
                java.lang.StringBuilder r1 = new java.lang.StringBuilder
                r1.<init>()
                r2 = 0
            L_0x000b:
                if (r2 != 0) goto L_0x00f3
                int r3 = r7.read()
                if (r3 != 0) goto L_0x0015
                goto L_0x00f3
            L_0x0015:
                int[] r4 = javax.jmdns.impl.DNSIncoming.AnonymousClass1.f2630a
                javax.jmdns.impl.constants.DNSLabel r5 = javax.jmdns.impl.constants.DNSLabel.labelForByte(r3)
                int r5 = r5.ordinal()
                r4 = r4[r5]
                r5 = 1
                switch(r4) {
                    case 1: goto L_0x00b2;
                    case 2: goto L_0x004f;
                    case 3: goto L_0x0047;
                    default: goto L_0x0025;
                }
            L_0x0025:
                java.util.logging.Logger r4 = b
                java.lang.StringBuilder r5 = new java.lang.StringBuilder
                r5.<init>()
                java.lang.String r6 = "unsupported dns label type: '"
                r5.append(r6)
                r3 = r3 & 192(0xc0, float:2.69E-43)
                java.lang.String r3 = java.lang.Integer.toHexString(r3)
                r5.append(r3)
                java.lang.String r3 = "'"
                r5.append(r3)
                java.lang.String r3 = r5.toString()
                r4.severe(r3)
                goto L_0x000b
            L_0x0047:
                java.util.logging.Logger r3 = b
                java.lang.String r4 = "Extended label are not currently supported."
                r3.severe(r4)
                goto L_0x000b
            L_0x004f:
                int r2 = javax.jmdns.impl.constants.DNSLabel.labelValue(r3)
                int r2 = r2 << 8
                int r3 = r7.read()
                r2 = r2 | r3
                java.util.Map<java.lang.Integer, java.lang.String> r3 = r7.f2631a
                java.lang.Integer r4 = java.lang.Integer.valueOf(r2)
                java.lang.Object r3 = r3.get(r4)
                java.lang.String r3 = (java.lang.String) r3
                if (r3 != 0) goto L_0x0094
                java.util.logging.Logger r3 = b
                java.lang.StringBuilder r4 = new java.lang.StringBuilder
                r4.<init>()
                java.lang.String r6 = "bad domain name: possible circular name detected. Bad offset: 0x"
                r4.append(r6)
                java.lang.String r2 = java.lang.Integer.toHexString(r2)
                r4.append(r2)
                java.lang.String r2 = " at 0x"
                r4.append(r2)
                int r2 = r7.pos
                int r2 = r2 + -2
                java.lang.String r2 = java.lang.Integer.toHexString(r2)
                r4.append(r2)
                java.lang.String r2 = r4.toString()
                r3.severe(r2)
                java.lang.String r3 = ""
            L_0x0094:
                r1.append(r3)
                java.util.Collection r2 = r0.values()
                java.util.Iterator r2 = r2.iterator()
            L_0x009f:
                boolean r4 = r2.hasNext()
                if (r4 == 0) goto L_0x00af
                java.lang.Object r4 = r2.next()
                java.lang.StringBuilder r4 = (java.lang.StringBuilder) r4
                r4.append(r3)
                goto L_0x009f
            L_0x00af:
                r2 = 1
                goto L_0x000b
            L_0x00b2:
                int r4 = r7.pos
                int r4 = r4 - r5
                java.lang.StringBuilder r5 = new java.lang.StringBuilder
                r5.<init>()
                java.lang.String r3 = r7.b(r3)
                r5.append(r3)
                java.lang.String r3 = "."
                r5.append(r3)
                java.lang.String r3 = r5.toString()
                r1.append(r3)
                java.util.Collection r5 = r0.values()
                java.util.Iterator r5 = r5.iterator()
            L_0x00d5:
                boolean r6 = r5.hasNext()
                if (r6 == 0) goto L_0x00e5
                java.lang.Object r6 = r5.next()
                java.lang.StringBuilder r6 = (java.lang.StringBuilder) r6
                r6.append(r3)
                goto L_0x00d5
            L_0x00e5:
                java.lang.Integer r4 = java.lang.Integer.valueOf(r4)
                java.lang.StringBuilder r5 = new java.lang.StringBuilder
                r5.<init>(r3)
                r0.put(r4, r5)
                goto L_0x000b
            L_0x00f3:
                java.util.Set r2 = r0.keySet()
                java.util.Iterator r2 = r2.iterator()
            L_0x00fb:
                boolean r3 = r2.hasNext()
                if (r3 == 0) goto L_0x0117
                java.lang.Object r3 = r2.next()
                java.lang.Integer r3 = (java.lang.Integer) r3
                java.util.Map<java.lang.Integer, java.lang.String> r4 = r7.f2631a
                java.lang.Object r5 = r0.get(r3)
                java.lang.StringBuilder r5 = (java.lang.StringBuilder) r5
                java.lang.String r5 = r5.toString()
                r4.put(r3, r5)
                goto L_0x00fb
            L_0x0117:
                java.lang.String r0 = r1.toString()
                return r0
            */
            throw new UnsupportedOperationException("Method not decompiled: javax.jmdns.impl.DNSIncoming.MessageInputStream.e():java.lang.String");
        }

        public String f() {
            return b(read());
        }
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public DNSIncoming(DatagramPacket datagramPacket) throws IOException {
        super(0, 0, datagramPacket.getPort() == DNSConstants.c);
        this.j = datagramPacket;
        InetAddress address = datagramPacket.getAddress();
        this.l = new MessageInputStream(datagramPacket.getData(), datagramPacket.getLength());
        this.k = System.currentTimeMillis();
        this.m = DNSConstants.f;
        try {
            a(this.l.b());
            b(this.l.b());
            int b = this.l.b();
            int b2 = this.l.b();
            int b3 = this.l.b();
            int b4 = this.l.b();
            if (b > 0) {
                for (int i2 = 0; i2 < b; i2++) {
                    this.e.add(u());
                }
            }
            if (b2 > 0) {
                for (int i3 = 0; i3 < b2; i3++) {
                    DNSRecord a2 = a(address);
                    if (a2 != null) {
                        this.f.add(a2);
                    }
                }
            }
            if (b3 > 0) {
                for (int i4 = 0; i4 < b3; i4++) {
                    DNSRecord a3 = a(address);
                    if (a3 != null) {
                        this.g.add(a3);
                    }
                }
            }
            if (b4 > 0) {
                for (int i5 = 0; i5 < b4; i5++) {
                    DNSRecord a4 = a(address);
                    if (a4 != null) {
                        this.h.add(a4);
                    }
                }
            }
        } catch (Exception e) {
            Logger logger = i;
            Level level = Level.WARNING;
            logger.log(level, "DNSIncoming() dump " + a(true) + "\n exception ", e);
            IOException iOException = new IOException("DNSIncoming corrupted message");
            iOException.initCause(e);
            throw iOException;
        }
    }

    private DNSIncoming(int i2, int i3, boolean z, DatagramPacket datagramPacket, long j2) {
        super(i2, i3, z);
        this.j = datagramPacket;
        this.l = new MessageInputStream(datagramPacket.getData(), datagramPacket.getLength());
        this.k = j2;
    }

    /* renamed from: a */
    public DNSIncoming clone() {
        DNSIncoming dNSIncoming = new DNSIncoming(e(), d(), f(), this.j, this.k);
        dNSIncoming.m = this.m;
        dNSIncoming.e.addAll(this.e);
        dNSIncoming.f.addAll(this.f);
        dNSIncoming.g.addAll(this.g);
        dNSIncoming.h.addAll(this.h);
        return dNSIncoming;
    }

    private DNSQuestion u() {
        String e = this.l.e();
        DNSRecordType typeForIndex = DNSRecordType.typeForIndex(this.l.b());
        if (typeForIndex == DNSRecordType.TYPE_IGNORE) {
            Logger logger = i;
            Level level = Level.SEVERE;
            logger.log(level, "Could not find record type: " + a(true));
        }
        int b = this.l.b();
        DNSRecordClass classForIndex = DNSRecordClass.classForIndex(b);
        return DNSQuestion.a(e, typeForIndex, classForIndex, classForIndex.isUnique(b));
    }

    /* JADX WARNING: type inference failed for: r1v4, types: [javax.jmdns.impl.DNSRecord] */
    /* JADX WARNING: type inference failed for: r1v5 */
    /* JADX WARNING: type inference failed for: r1v11 */
    /* JADX WARNING: type inference failed for: r1v15 */
    /* JADX WARNING: type inference failed for: r1v19 */
    /* JADX WARNING: type inference failed for: r2v52, types: [javax.jmdns.impl.DNSRecord$IPv4Address] */
    /* JADX WARNING: type inference failed for: r2v53, types: [javax.jmdns.impl.DNSRecord$IPv6Address] */
    /* JADX WARNING: type inference failed for: r2v54, types: [javax.jmdns.impl.DNSRecord$Text] */
    /* JADX WARNING: Can't fix incorrect switch cases order */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Removed duplicated region for block: B:118:0x00d0 A[SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:71:0x0236  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private javax.jmdns.impl.DNSRecord a(java.net.InetAddress r21) {
        /*
            r20 = this;
            r0 = r20
            javax.jmdns.impl.DNSIncoming$MessageInputStream r1 = r0.l
            java.lang.String r3 = r1.e()
            javax.jmdns.impl.DNSIncoming$MessageInputStream r1 = r0.l
            int r1 = r1.b()
            javax.jmdns.impl.constants.DNSRecordType r1 = javax.jmdns.impl.constants.DNSRecordType.typeForIndex(r1)
            javax.jmdns.impl.constants.DNSRecordType r2 = javax.jmdns.impl.constants.DNSRecordType.TYPE_IGNORE
            r4 = 1
            if (r1 != r2) goto L_0x003b
            java.util.logging.Logger r2 = i
            java.util.logging.Level r5 = java.util.logging.Level.SEVERE
            java.lang.StringBuilder r6 = new java.lang.StringBuilder
            r6.<init>()
            java.lang.String r7 = "Could not find record type. domain: "
            r6.append(r7)
            r6.append(r3)
            java.lang.String r7 = "\n"
            r6.append(r7)
            java.lang.String r7 = r0.a((boolean) r4)
            r6.append(r7)
            java.lang.String r6 = r6.toString()
            r2.log(r5, r6)
        L_0x003b:
            javax.jmdns.impl.DNSIncoming$MessageInputStream r2 = r0.l
            int r2 = r2.b()
            javax.jmdns.impl.constants.DNSRecordType r5 = javax.jmdns.impl.constants.DNSRecordType.TYPE_OPT
            if (r1 != r5) goto L_0x0048
            javax.jmdns.impl.constants.DNSRecordClass r5 = javax.jmdns.impl.constants.DNSRecordClass.CLASS_UNKNOWN
            goto L_0x004c
        L_0x0048:
            javax.jmdns.impl.constants.DNSRecordClass r5 = javax.jmdns.impl.constants.DNSRecordClass.classForIndex(r2)
        L_0x004c:
            javax.jmdns.impl.constants.DNSRecordClass r6 = javax.jmdns.impl.constants.DNSRecordClass.CLASS_UNKNOWN
            if (r5 != r6) goto L_0x0080
            javax.jmdns.impl.constants.DNSRecordType r6 = javax.jmdns.impl.constants.DNSRecordType.TYPE_OPT
            if (r1 == r6) goto L_0x0080
            java.util.logging.Logger r6 = i
            java.util.logging.Level r7 = java.util.logging.Level.SEVERE
            java.lang.StringBuilder r8 = new java.lang.StringBuilder
            r8.<init>()
            java.lang.String r9 = "Could not find record class. domain: "
            r8.append(r9)
            r8.append(r3)
            java.lang.String r9 = " type: "
            r8.append(r9)
            r8.append(r1)
            java.lang.String r9 = "\n"
            r8.append(r9)
            java.lang.String r9 = r0.a((boolean) r4)
            r8.append(r9)
            java.lang.String r8 = r8.toString()
            r6.log(r7, r8)
        L_0x0080:
            boolean r6 = r5.isUnique(r2)
            javax.jmdns.impl.DNSIncoming$MessageInputStream r7 = r0.l
            int r7 = r7.c()
            javax.jmdns.impl.DNSIncoming$MessageInputStream r8 = r0.l
            int r8 = r8.b()
            int[] r9 = javax.jmdns.impl.DNSIncoming.AnonymousClass1.c
            int r10 = r1.ordinal()
            r9 = r9[r10]
            r11 = 0
            switch(r9) {
                case 1: goto L_0x03a1;
                case 2: goto L_0x0390;
                case 3: goto L_0x0357;
                case 4: goto L_0x0357;
                case 5: goto L_0x0345;
                case 6: goto L_0x0313;
                case 7: goto L_0x02d4;
                case 8: goto L_0x00be;
                default: goto L_0x009c;
            }
        L_0x009c:
            java.util.logging.Logger r2 = i
            java.util.logging.Level r3 = java.util.logging.Level.FINER
            boolean r2 = r2.isLoggable(r3)
            if (r2 == 0) goto L_0x03b2
            java.util.logging.Logger r2 = i
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>()
            java.lang.String r4 = "DNSIncoming() unknown type:"
            r3.append(r4)
            r3.append(r1)
            java.lang.String r1 = r3.toString()
            r2.finer(r1)
            goto L_0x03b2
        L_0x00be:
            int r1 = r20.e()
            javax.jmdns.impl.constants.DNSResultCode r1 = javax.jmdns.impl.constants.DNSResultCode.resultCodeForFlags(r1, r7)
            r3 = 16711680(0xff0000, float:2.3418052E-38)
            r3 = r3 & r7
            r5 = 16
            int r3 = r3 >> r5
            if (r3 != 0) goto L_0x02b2
            r0.m = r2
        L_0x00d0:
            javax.jmdns.impl.DNSIncoming$MessageInputStream r1 = r0.l
            int r1 = r1.available()
            if (r1 <= 0) goto L_0x03b8
            javax.jmdns.impl.DNSIncoming$MessageInputStream r1 = r0.l
            int r1 = r1.available()
            r2 = 2
            if (r1 < r2) goto L_0x02a7
            javax.jmdns.impl.DNSIncoming$MessageInputStream r1 = r0.l
            int r1 = r1.b()
            javax.jmdns.impl.constants.DNSOptionCode r3 = javax.jmdns.impl.constants.DNSOptionCode.resultCodeForFlags(r1)
            javax.jmdns.impl.DNSIncoming$MessageInputStream r6 = r0.l
            int r6 = r6.available()
            if (r6 < r2) goto L_0x029c
            javax.jmdns.impl.DNSIncoming$MessageInputStream r6 = r0.l
            int r6 = r6.b()
            byte[] r7 = new byte[r11]
            javax.jmdns.impl.DNSIncoming$MessageInputStream r8 = r0.l
            int r8 = r8.available()
            if (r8 < r6) goto L_0x0109
            javax.jmdns.impl.DNSIncoming$MessageInputStream r7 = r0.l
            byte[] r7 = r7.a(r6)
        L_0x0109:
            int[] r6 = javax.jmdns.impl.DNSIncoming.AnonymousClass1.b
            int r8 = r3.ordinal()
            r6 = r6[r8]
            switch(r6) {
                case 1: goto L_0x016a;
                case 2: goto L_0x013a;
                case 3: goto L_0x013a;
                case 4: goto L_0x013a;
                case 5: goto L_0x0115;
                default: goto L_0x0114;
            }
        L_0x0114:
            goto L_0x00d0
        L_0x0115:
            java.util.logging.Logger r2 = i
            java.util.logging.Level r3 = java.util.logging.Level.WARNING
            java.lang.StringBuilder r6 = new java.lang.StringBuilder
            r6.<init>()
            java.lang.String r8 = "There was an OPT answer. Not currently handled. Option code: "
            r6.append(r8)
            r6.append(r1)
            java.lang.String r1 = " data: "
            r6.append(r1)
            java.lang.String r1 = r0.b(r7)
            r6.append(r1)
            java.lang.String r1 = r6.toString()
            r2.log(r3, r1)
            goto L_0x00d0
        L_0x013a:
            java.util.logging.Logger r1 = i
            java.util.logging.Level r2 = java.util.logging.Level.FINE
            boolean r1 = r1.isLoggable(r2)
            if (r1 == 0) goto L_0x00d0
            java.util.logging.Logger r1 = i
            java.util.logging.Level r2 = java.util.logging.Level.FINE
            java.lang.StringBuilder r6 = new java.lang.StringBuilder
            r6.<init>()
            java.lang.String r8 = "There was an OPT answer. Option code: "
            r6.append(r8)
            r6.append(r3)
            java.lang.String r3 = " data: "
            r6.append(r3)
            java.lang.String r3 = r0.b(r7)
            r6.append(r3)
            java.lang.String r3 = r6.toString()
            r1.log(r2, r3)
            goto L_0x00d0
        L_0x016a:
            byte r1 = r7[r11]     // Catch:{ Exception -> 0x020d }
            byte r3 = r7[r4]     // Catch:{ Exception -> 0x020e }
            r6 = 6
            byte[] r8 = new byte[r6]     // Catch:{ Exception -> 0x020f }
            byte r9 = r7[r2]     // Catch:{ Exception -> 0x020f }
            r8[r11] = r9     // Catch:{ Exception -> 0x020f }
            r9 = 3
            byte r12 = r7[r9]     // Catch:{ Exception -> 0x020f }
            r8[r4] = r12     // Catch:{ Exception -> 0x020f }
            r12 = 4
            byte r13 = r7[r12]     // Catch:{ Exception -> 0x020f }
            r8[r2] = r13     // Catch:{ Exception -> 0x020f }
            r13 = 5
            byte r14 = r7[r13]     // Catch:{ Exception -> 0x020f }
            r8[r9] = r14     // Catch:{ Exception -> 0x020f }
            byte r14 = r7[r6]     // Catch:{ Exception -> 0x020f }
            r8[r12] = r14     // Catch:{ Exception -> 0x020f }
            r14 = 7
            byte r15 = r7[r14]     // Catch:{ Exception -> 0x020f }
            r8[r13] = r15     // Catch:{ Exception -> 0x020f }
            int r15 = r7.length     // Catch:{ Exception -> 0x020a }
            r10 = 8
            if (r15 <= r10) goto L_0x01b7
            byte[] r15 = new byte[r6]     // Catch:{ Exception -> 0x020a }
            byte r16 = r7[r10]     // Catch:{ Exception -> 0x020a }
            r15[r11] = r16     // Catch:{ Exception -> 0x020a }
            r16 = 9
            byte r16 = r7[r16]     // Catch:{ Exception -> 0x020a }
            r15[r4] = r16     // Catch:{ Exception -> 0x020a }
            r16 = 10
            byte r16 = r7[r16]     // Catch:{ Exception -> 0x020a }
            r15[r2] = r16     // Catch:{ Exception -> 0x020a }
            r16 = 11
            byte r16 = r7[r16]     // Catch:{ Exception -> 0x020a }
            r15[r9] = r16     // Catch:{ Exception -> 0x020a }
            r16 = 12
            byte r16 = r7[r16]     // Catch:{ Exception -> 0x020a }
            r15[r12] = r16     // Catch:{ Exception -> 0x020a }
            r16 = 13
            byte r16 = r7[r16]     // Catch:{ Exception -> 0x020a }
            r15[r13] = r16     // Catch:{ Exception -> 0x020a }
            goto L_0x01b8
        L_0x01b7:
            r15 = r8
        L_0x01b8:
            int r14 = r7.length     // Catch:{ Exception -> 0x020b }
            r16 = 17
            r17 = 15
            r18 = 14
            r6 = 18
            if (r14 != r6) goto L_0x01d6
            byte[] r14 = new byte[r12]     // Catch:{ Exception -> 0x020b }
            byte r19 = r7[r18]     // Catch:{ Exception -> 0x020b }
            r14[r11] = r19     // Catch:{ Exception -> 0x020b }
            byte r19 = r7[r17]     // Catch:{ Exception -> 0x020b }
            r14[r4] = r19     // Catch:{ Exception -> 0x020b }
            byte r19 = r7[r5]     // Catch:{ Exception -> 0x020b }
            r14[r2] = r19     // Catch:{ Exception -> 0x020b }
            byte r19 = r7[r16]     // Catch:{ Exception -> 0x020b }
            r14[r9] = r19     // Catch:{ Exception -> 0x020b }
            goto L_0x01d7
        L_0x01d6:
            r14 = 0
        L_0x01d7:
            int r13 = r7.length     // Catch:{ Exception -> 0x0212 }
            r12 = 22
            if (r13 != r12) goto L_0x022c
            byte[] r10 = new byte[r10]     // Catch:{ Exception -> 0x0212 }
            byte r12 = r7[r18]     // Catch:{ Exception -> 0x0212 }
            r10[r11] = r12     // Catch:{ Exception -> 0x0212 }
            byte r12 = r7[r17]     // Catch:{ Exception -> 0x0212 }
            r10[r4] = r12     // Catch:{ Exception -> 0x0212 }
            byte r12 = r7[r5]     // Catch:{ Exception -> 0x0212 }
            r10[r2] = r12     // Catch:{ Exception -> 0x0212 }
            byte r2 = r7[r16]     // Catch:{ Exception -> 0x0212 }
            r10[r9] = r2     // Catch:{ Exception -> 0x0212 }
            byte r2 = r7[r6]     // Catch:{ Exception -> 0x0212 }
            r6 = 4
            r10[r6] = r2     // Catch:{ Exception -> 0x0212 }
            r2 = 19
            byte r2 = r7[r2]     // Catch:{ Exception -> 0x0212 }
            r6 = 5
            r10[r6] = r2     // Catch:{ Exception -> 0x0212 }
            r2 = 20
            byte r2 = r7[r2]     // Catch:{ Exception -> 0x0212 }
            r6 = 6
            r10[r6] = r2     // Catch:{ Exception -> 0x0212 }
            r2 = 21
            byte r2 = r7[r2]     // Catch:{ Exception -> 0x0212 }
            r6 = 7
            r10[r6] = r2     // Catch:{ Exception -> 0x0212 }
            r14 = r10
            goto L_0x022c
        L_0x020a:
            r15 = r8
        L_0x020b:
            r14 = 0
            goto L_0x0212
        L_0x020d:
            r1 = 0
        L_0x020e:
            r3 = 0
        L_0x020f:
            r8 = 0
            r14 = 0
            r15 = 0
        L_0x0212:
            java.util.logging.Logger r2 = i
            java.lang.StringBuilder r6 = new java.lang.StringBuilder
            r6.<init>()
            java.lang.String r9 = "Malformed OPT answer. Option code: Owner data: "
            r6.append(r9)
            java.lang.String r7 = r0.b(r7)
            r6.append(r7)
            java.lang.String r6 = r6.toString()
            r2.warning(r6)
        L_0x022c:
            java.util.logging.Logger r2 = i
            java.util.logging.Level r6 = java.util.logging.Level.FINE
            boolean r2 = r2.isLoggable(r6)
            if (r2 == 0) goto L_0x00d0
            java.util.logging.Logger r2 = i
            java.lang.StringBuilder r6 = new java.lang.StringBuilder
            r6.<init>()
            java.lang.String r7 = "Unhandled Owner OPT version: "
            r6.append(r7)
            r6.append(r1)
            java.lang.String r1 = " sequence: "
            r6.append(r1)
            r6.append(r3)
            java.lang.String r1 = " MAC address: "
            r6.append(r1)
            java.lang.String r1 = r0.b(r8)
            r6.append(r1)
            if (r15 == r8) goto L_0x0271
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            java.lang.String r3 = " wakeup MAC address: "
            r1.append(r3)
            java.lang.String r3 = r0.b(r15)
            r1.append(r3)
            java.lang.String r1 = r1.toString()
            goto L_0x0273
        L_0x0271:
            java.lang.String r1 = ""
        L_0x0273:
            r6.append(r1)
            if (r14 == 0) goto L_0x028e
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            java.lang.String r3 = " password: "
            r1.append(r3)
            java.lang.String r3 = r0.b(r14)
            r1.append(r3)
            java.lang.String r1 = r1.toString()
            goto L_0x0290
        L_0x028e:
            java.lang.String r1 = ""
        L_0x0290:
            r6.append(r1)
            java.lang.String r1 = r6.toString()
            r2.fine(r1)
            goto L_0x00d0
        L_0x029c:
            java.util.logging.Logger r1 = i
            java.util.logging.Level r2 = java.util.logging.Level.WARNING
            java.lang.String r3 = "There was a problem reading the OPT record. Ignoring."
            r1.log(r2, r3)
            goto L_0x03b8
        L_0x02a7:
            java.util.logging.Logger r1 = i
            java.util.logging.Level r2 = java.util.logging.Level.WARNING
            java.lang.String r3 = "There was a problem reading the OPT record. Ignoring."
            r1.log(r2, r3)
            goto L_0x03b8
        L_0x02b2:
            java.util.logging.Logger r2 = i
            java.util.logging.Level r4 = java.util.logging.Level.WARNING
            java.lang.StringBuilder r5 = new java.lang.StringBuilder
            r5.<init>()
            java.lang.String r6 = "There was an OPT answer. Wrong version number: "
            r5.append(r6)
            r5.append(r3)
            java.lang.String r3 = " result code: "
            r5.append(r3)
            r5.append(r1)
            java.lang.String r1 = r5.toString()
            r2.log(r4, r1)
            goto L_0x03b8
        L_0x02d4:
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            javax.jmdns.impl.DNSIncoming$MessageInputStream r2 = r0.l
            java.lang.String r2 = r2.b(r8)
            r1.append(r2)
            java.lang.String r2 = " "
            int r2 = r1.indexOf(r2)
            if (r2 <= 0) goto L_0x02ef
            java.lang.String r8 = r1.substring(r11, r2)
            goto L_0x02f3
        L_0x02ef:
            java.lang.String r8 = r1.toString()
        L_0x02f3:
            java.lang.String r8 = r8.trim()
            if (r2 <= 0) goto L_0x02ff
            int r2 = r2 + r4
            java.lang.String r1 = r1.substring(r2)
            goto L_0x0301
        L_0x02ff:
            java.lang.String r1 = ""
        L_0x0301:
            java.lang.String r1 = r1.trim()
            javax.jmdns.impl.DNSRecord$HostInformation r9 = new javax.jmdns.impl.DNSRecord$HostInformation
            r2 = r9
            r4 = r5
            r5 = r6
            r6 = r7
            r7 = r8
            r8 = r1
            r2.<init>(r3, r4, r5, r6, r7, r8)
            r1 = r9
            goto L_0x03b9
        L_0x0313:
            javax.jmdns.impl.DNSIncoming$MessageInputStream r1 = r0.l
            int r1 = r1.b()
            javax.jmdns.impl.DNSIncoming$MessageInputStream r2 = r0.l
            int r8 = r2.b()
            javax.jmdns.impl.DNSIncoming$MessageInputStream r2 = r0.l
            int r9 = r2.b()
            boolean r2 = f2629a
            if (r2 == 0) goto L_0x0331
            javax.jmdns.impl.DNSIncoming$MessageInputStream r2 = r0.l
            java.lang.String r2 = r2.e()
        L_0x032f:
            r10 = r2
            goto L_0x0338
        L_0x0331:
            javax.jmdns.impl.DNSIncoming$MessageInputStream r2 = r0.l
            java.lang.String r2 = r2.f()
            goto L_0x032f
        L_0x0338:
            javax.jmdns.impl.DNSRecord$Service r11 = new javax.jmdns.impl.DNSRecord$Service
            r2 = r11
            r4 = r5
            r5 = r6
            r6 = r7
            r7 = r1
            r2.<init>(r3, r4, r5, r6, r7, r8, r9, r10)
            r1 = r11
            goto L_0x03b9
        L_0x0345:
            javax.jmdns.impl.DNSRecord$Text r1 = new javax.jmdns.impl.DNSRecord$Text
            javax.jmdns.impl.DNSIncoming$MessageInputStream r2 = r0.l
            byte[] r8 = r2.a(r8)
            r2 = r1
            r4 = r5
            r5 = r6
            r6 = r7
            r7 = r8
            r2.<init>(r3, r4, r5, r6, r7)
            goto L_0x03b9
        L_0x0357:
            javax.jmdns.impl.DNSIncoming$MessageInputStream r1 = r0.l
            java.lang.String r1 = r1.e()
            int r2 = r1.length()
            if (r2 <= 0) goto L_0x036f
            javax.jmdns.impl.DNSRecord$Pointer r8 = new javax.jmdns.impl.DNSRecord$Pointer
            r2 = r8
            r4 = r5
            r5 = r6
            r6 = r7
            r7 = r1
            r2.<init>(r3, r4, r5, r6, r7)
            r1 = r8
            goto L_0x03b9
        L_0x036f:
            java.util.logging.Logger r1 = i
            java.util.logging.Level r2 = java.util.logging.Level.WARNING
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            r4.<init>()
            java.lang.String r6 = "PTR record of class: "
            r4.append(r6)
            r4.append(r5)
            java.lang.String r5 = ", there was a problem reading the service name of the answer for domain:"
            r4.append(r5)
            r4.append(r3)
            java.lang.String r3 = r4.toString()
            r1.log(r2, r3)
            goto L_0x03b8
        L_0x0390:
            javax.jmdns.impl.DNSRecord$IPv6Address r1 = new javax.jmdns.impl.DNSRecord$IPv6Address
            javax.jmdns.impl.DNSIncoming$MessageInputStream r2 = r0.l
            byte[] r8 = r2.a(r8)
            r2 = r1
            r4 = r5
            r5 = r6
            r6 = r7
            r7 = r8
            r2.<init>((java.lang.String) r3, (javax.jmdns.impl.constants.DNSRecordClass) r4, (boolean) r5, (int) r6, (byte[]) r7)
            goto L_0x03b9
        L_0x03a1:
            javax.jmdns.impl.DNSRecord$IPv4Address r1 = new javax.jmdns.impl.DNSRecord$IPv4Address
            javax.jmdns.impl.DNSIncoming$MessageInputStream r2 = r0.l
            byte[] r8 = r2.a(r8)
            r2 = r1
            r4 = r5
            r5 = r6
            r6 = r7
            r7 = r8
            r2.<init>((java.lang.String) r3, (javax.jmdns.impl.constants.DNSRecordClass) r4, (boolean) r5, (int) r6, (byte[]) r7)
            goto L_0x03b9
        L_0x03b2:
            javax.jmdns.impl.DNSIncoming$MessageInputStream r1 = r0.l
            long r2 = (long) r8
            r1.skip(r2)
        L_0x03b8:
            r1 = 0
        L_0x03b9:
            if (r1 == 0) goto L_0x03c0
            r2 = r21
            r1.a((java.net.InetAddress) r2)
        L_0x03c0:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: javax.jmdns.impl.DNSIncoming.a(java.net.InetAddress):javax.jmdns.impl.DNSRecord");
    }

    /* access modifiers changed from: package-private */
    public String a(boolean z) {
        StringBuilder sb = new StringBuilder();
        sb.append(t());
        if (z) {
            byte[] bArr = new byte[this.j.getLength()];
            System.arraycopy(this.j.getData(), 0, bArr, 0, bArr.length);
            sb.append(a(bArr));
        }
        return sb.toString();
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(q() ? "dns[query," : "dns[response,");
        if (this.j.getAddress() != null) {
            sb.append(this.j.getAddress().getHostAddress());
        }
        sb.append(Operators.CONDITION_IF_MIDDLE);
        sb.append(this.j.getPort());
        sb.append(", length=");
        sb.append(this.j.getLength());
        sb.append(", id=0x");
        sb.append(Integer.toHexString(d()));
        if (e() != 0) {
            sb.append(", flags=0x");
            sb.append(Integer.toHexString(e()));
            if ((e() & 32768) != 0) {
                sb.append(":r");
            }
            if ((e() & 1024) != 0) {
                sb.append(":aa");
            }
            if ((e() & 512) != 0) {
                sb.append(":tc");
            }
        }
        if (h() > 0) {
            sb.append(", questions=");
            sb.append(h());
        }
        if (k() > 0) {
            sb.append(", answers=");
            sb.append(k());
        }
        if (m() > 0) {
            sb.append(", authorities=");
            sb.append(m());
        }
        if (o() > 0) {
            sb.append(", additionals=");
            sb.append(o());
        }
        if (h() > 0) {
            sb.append("\nquestions:");
            for (DNSQuestion append : this.e) {
                sb.append(HmsPushConst.NEW_LINE);
                sb.append(append);
            }
        }
        if (k() > 0) {
            sb.append("\nanswers:");
            for (DNSRecord append2 : this.f) {
                sb.append(HmsPushConst.NEW_LINE);
                sb.append(append2);
            }
        }
        if (m() > 0) {
            sb.append("\nauthorities:");
            for (DNSRecord append3 : this.g) {
                sb.append(HmsPushConst.NEW_LINE);
                sb.append(append3);
            }
        }
        if (o() > 0) {
            sb.append("\nadditionals:");
            for (DNSRecord append4 : this.h) {
                sb.append(HmsPushConst.NEW_LINE);
                sb.append(append4);
            }
        }
        sb.append(Operators.ARRAY_END_STR);
        return sb.toString();
    }

    /* access modifiers changed from: package-private */
    public void a(DNSIncoming dNSIncoming) {
        if (!q() || !p() || !dNSIncoming.q()) {
            throw new IllegalArgumentException();
        }
        this.e.addAll(dNSIncoming.g());
        this.f.addAll(dNSIncoming.j());
        this.g.addAll(dNSIncoming.l());
        this.h.addAll(dNSIncoming.n());
    }

    public int b() {
        return (int) (System.currentTimeMillis() - this.k);
    }

    public int c() {
        return this.m;
    }

    private String b(byte[] bArr) {
        StringBuilder sb = new StringBuilder(bArr.length * 2);
        for (byte b : bArr) {
            byte b2 = b & 255;
            sb.append(n[b2 / 16]);
            sb.append(n[b2 % 16]);
        }
        return sb.toString();
    }
}
