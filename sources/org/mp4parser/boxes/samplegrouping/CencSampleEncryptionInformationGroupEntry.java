package org.mp4parser.boxes.samplegrouping;

import com.taobao.weex.el.parse.Operators;
import java.nio.ByteBuffer;
import java.util.UUID;
import org.mp4parser.tools.IsoTypeReader;
import org.mp4parser.tools.IsoTypeWriter;
import org.mp4parser.tools.UUIDConverter;

public class CencSampleEncryptionInformationGroupEntry extends GroupEntry {

    /* renamed from: a  reason: collision with root package name */
    public static final String f3956a = "seig";
    private boolean b;
    private byte c;
    private UUID d;

    public String a() {
        return f3956a;
    }

    public void a(ByteBuffer byteBuffer) {
        boolean z = true;
        if (IsoTypeReader.c(byteBuffer) != 1) {
            z = false;
        }
        this.b = z;
        this.c = (byte) IsoTypeReader.f(byteBuffer);
        byte[] bArr = new byte[16];
        byteBuffer.get(bArr);
        this.d = UUIDConverter.a(bArr);
    }

    public ByteBuffer b() {
        ByteBuffer allocate = ByteBuffer.allocate(20);
        IsoTypeWriter.a(allocate, this.b ? 1 : 0);
        if (this.b) {
            IsoTypeWriter.d(allocate, (int) this.c);
            allocate.put(UUIDConverter.a(this.d));
        } else {
            allocate.put(new byte[17]);
        }
        allocate.rewind();
        return allocate;
    }

    public boolean c() {
        return this.b;
    }

    public void a(boolean z) {
        this.b = z;
    }

    public byte d() {
        return this.c;
    }

    public void a(int i) {
        this.c = (byte) i;
    }

    public UUID e() {
        return this.d;
    }

    public void a(UUID uuid) {
        this.d = uuid;
    }

    public String toString() {
        return "CencSampleEncryptionInformationGroupEntry{isEncrypted=" + this.b + ", ivSize=" + this.c + ", kid=" + this.d + Operators.BLOCK_END;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        CencSampleEncryptionInformationGroupEntry cencSampleEncryptionInformationGroupEntry = (CencSampleEncryptionInformationGroupEntry) obj;
        if (this.b == cencSampleEncryptionInformationGroupEntry.b && this.c == cencSampleEncryptionInformationGroupEntry.c) {
            return this.d == null ? cencSampleEncryptionInformationGroupEntry.d == null : this.d.equals(cencSampleEncryptionInformationGroupEntry.d);
        }
        return false;
    }

    public int hashCode() {
        return ((((this.b ? 7 : 19) * 31) + this.c) * 31) + (this.d != null ? this.d.hashCode() : 0);
    }
}
