package javax.jmdns.impl;

import com.huawei.hms.support.api.push.HmsPushConst;
import com.taobao.weex.el.parse.Operators;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import javax.jmdns.impl.constants.DNSConstants;

public final class DNSOutgoing extends DNSMessage {

    /* renamed from: a  reason: collision with root package name */
    public static boolean f2633a = true;
    private static final int o = 12;
    Map<String, Integer> i;
    private int j;
    private final MessageOutputStream k;
    private final MessageOutputStream l;
    private final MessageOutputStream m;
    private final MessageOutputStream n;

    public static class MessageOutputStream extends ByteArrayOutputStream {

        /* renamed from: a  reason: collision with root package name */
        private final DNSOutgoing f2634a;
        private final int b;

        MessageOutputStream(int i, DNSOutgoing dNSOutgoing) {
            this(i, dNSOutgoing, 0);
        }

        MessageOutputStream(int i, DNSOutgoing dNSOutgoing, int i2) {
            super(i);
            this.f2634a = dNSOutgoing;
            this.b = i2;
        }

        /* access modifiers changed from: package-private */
        public void a(int i) {
            write(i & 255);
        }

        /* access modifiers changed from: package-private */
        public void a(String str, int i, int i2) {
            for (int i3 = 0; i3 < i2; i3++) {
                a((int) str.charAt(i + i3));
            }
        }

        /* access modifiers changed from: package-private */
        public void a(byte[] bArr) {
            if (bArr != null) {
                a(bArr, 0, bArr.length);
            }
        }

        /* access modifiers changed from: package-private */
        public void a(byte[] bArr, int i, int i2) {
            for (int i3 = 0; i3 < i2; i3++) {
                a((int) bArr[i + i3]);
            }
        }

        /* access modifiers changed from: package-private */
        public void b(int i) {
            a(i >> 8);
            a(i);
        }

        /* access modifiers changed from: package-private */
        public void c(int i) {
            b(i >> 16);
            b(i);
        }

        /* access modifiers changed from: package-private */
        public void b(String str, int i, int i2) {
            int i3 = 0;
            for (int i4 = 0; i4 < i2; i4++) {
                char charAt = str.charAt(i + i4);
                i3 = (charAt < 1 || charAt > 127) ? charAt > 2047 ? i3 + 3 : i3 + 2 : i3 + 1;
            }
            a(i3);
            for (int i5 = 0; i5 < i2; i5++) {
                char charAt2 = str.charAt(i + i5);
                if (charAt2 >= 1 && charAt2 <= 127) {
                    a((int) charAt2);
                } else if (charAt2 > 2047) {
                    a(((charAt2 >> 12) & 15) | 224);
                    a(((charAt2 >> 6) & 63) | 128);
                    a(((charAt2 >> 0) & 63) | 128);
                } else {
                    a(((charAt2 >> 6) & 31) | 192);
                    a(((charAt2 >> 0) & 63) | 128);
                }
            }
        }

        /* access modifiers changed from: package-private */
        public void a(String str) {
            a(str, true);
        }

        /* access modifiers changed from: package-private */
        public void a(String str, boolean z) {
            while (true) {
                int indexOf = str.indexOf(46);
                if (indexOf < 0) {
                    indexOf = str.length();
                }
                if (indexOf <= 0) {
                    a(0);
                    return;
                }
                String substring = str.substring(0, indexOf);
                if (!z || !DNSOutgoing.f2633a) {
                    b(substring, 0, substring.length());
                } else {
                    Integer num = this.f2634a.i.get(str);
                    if (num != null) {
                        int intValue = num.intValue();
                        a((intValue >> 8) | 192);
                        a(intValue & 255);
                        return;
                    }
                    this.f2634a.i.put(str, Integer.valueOf(size() + this.b));
                    b(substring, 0, substring.length());
                }
                str = str.substring(indexOf);
                if (str.startsWith(".")) {
                    str = str.substring(1);
                }
            }
        }

        /* access modifiers changed from: package-private */
        public void a(DNSQuestion dNSQuestion) {
            a(dNSQuestion.b());
            b(dNSQuestion.e().indexValue());
            b(dNSQuestion.f().indexValue());
        }

        /* access modifiers changed from: package-private */
        public void a(DNSRecord dNSRecord, long j) {
            a(dNSRecord.b());
            b(dNSRecord.e().indexValue());
            b(dNSRecord.f().indexValue() | ((!dNSRecord.g() || !this.f2634a.f()) ? 0 : 32768));
            c(j == 0 ? dNSRecord.r() : dNSRecord.c(j));
            MessageOutputStream messageOutputStream = new MessageOutputStream(512, this.f2634a, this.b + size() + 2);
            dNSRecord.a(messageOutputStream);
            byte[] byteArray = messageOutputStream.toByteArray();
            b(byteArray.length);
            write(byteArray, 0, byteArray.length);
        }
    }

    public DNSOutgoing(int i2) {
        this(i2, true, DNSConstants.f);
    }

    public DNSOutgoing(int i2, boolean z) {
        this(i2, z, DNSConstants.f);
    }

    public DNSOutgoing(int i2, boolean z, int i3) {
        super(i2, 0, z);
        this.i = new HashMap();
        this.j = i3 > 0 ? i3 : DNSConstants.f;
        this.k = new MessageOutputStream(i3, this);
        this.l = new MessageOutputStream(i3, this);
        this.m = new MessageOutputStream(i3, this);
        this.n = new MessageOutputStream(i3, this);
    }

    public int a() {
        return ((((this.j - 12) - this.k.size()) - this.l.size()) - this.m.size()) - this.n.size();
    }

    public void a(DNSQuestion dNSQuestion) throws IOException {
        MessageOutputStream messageOutputStream = new MessageOutputStream(512, this);
        messageOutputStream.a(dNSQuestion);
        byte[] byteArray = messageOutputStream.toByteArray();
        if (byteArray.length < a()) {
            this.e.add(dNSQuestion);
            this.k.write(byteArray, 0, byteArray.length);
            return;
        }
        throw new IOException("message full");
    }

    public void a(DNSIncoming dNSIncoming, DNSRecord dNSRecord) throws IOException {
        if (dNSIncoming == null || !dNSRecord.a(dNSIncoming)) {
            a(dNSRecord, 0);
        }
    }

    public void a(DNSRecord dNSRecord, long j2) throws IOException {
        if (dNSRecord == null) {
            return;
        }
        if (j2 == 0 || !dNSRecord.b(j2)) {
            MessageOutputStream messageOutputStream = new MessageOutputStream(512, this);
            messageOutputStream.a(dNSRecord, j2);
            byte[] byteArray = messageOutputStream.toByteArray();
            if (byteArray.length < a()) {
                this.f.add(dNSRecord);
                this.l.write(byteArray, 0, byteArray.length);
                return;
            }
            throw new IOException("message full");
        }
    }

    public void a(DNSRecord dNSRecord) throws IOException {
        MessageOutputStream messageOutputStream = new MessageOutputStream(512, this);
        messageOutputStream.a(dNSRecord, 0);
        byte[] byteArray = messageOutputStream.toByteArray();
        if (byteArray.length < a()) {
            this.g.add(dNSRecord);
            this.m.write(byteArray, 0, byteArray.length);
            return;
        }
        throw new IOException("message full");
    }

    public void b(DNSIncoming dNSIncoming, DNSRecord dNSRecord) throws IOException {
        MessageOutputStream messageOutputStream = new MessageOutputStream(512, this);
        messageOutputStream.a(dNSRecord, 0);
        byte[] byteArray = messageOutputStream.toByteArray();
        if (byteArray.length < a()) {
            this.h.add(dNSRecord);
            this.n.write(byteArray, 0, byteArray.length);
            return;
        }
        throw new IOException("message full");
    }

    public byte[] b() {
        long currentTimeMillis = System.currentTimeMillis();
        this.i.clear();
        MessageOutputStream messageOutputStream = new MessageOutputStream(this.j, this);
        messageOutputStream.b(this.d ? 0 : d());
        messageOutputStream.b(e());
        messageOutputStream.b(h());
        messageOutputStream.b(k());
        messageOutputStream.b(m());
        messageOutputStream.b(o());
        for (DNSQuestion a2 : this.e) {
            messageOutputStream.a(a2);
        }
        for (DNSRecord a3 : this.f) {
            messageOutputStream.a(a3, currentTimeMillis);
        }
        for (DNSRecord a4 : this.g) {
            messageOutputStream.a(a4, currentTimeMillis);
        }
        for (DNSRecord a5 : this.h) {
            messageOutputStream.a(a5, currentTimeMillis);
        }
        return messageOutputStream.toByteArray();
    }

    public boolean q() {
        return (e() & 32768) == 0;
    }

    /* access modifiers changed from: package-private */
    public String a(boolean z) {
        StringBuilder sb = new StringBuilder();
        sb.append(t());
        if (z) {
            sb.append(a(b()));
        }
        return sb.toString();
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(q() ? "dns[query:" : "dns[response:");
        sb.append(" id=0x");
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
        sb.append("\nnames=");
        sb.append(this.i);
        sb.append(Operators.ARRAY_END_STR);
        return sb.toString();
    }

    public int c() {
        return this.j;
    }
}
