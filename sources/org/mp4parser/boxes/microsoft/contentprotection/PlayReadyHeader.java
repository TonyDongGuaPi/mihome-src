package org.mp4parser.boxes.microsoft.contentprotection;

import com.taobao.weex.el.parse.Operators;
import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;
import org.mp4parser.boxes.microsoft.ProtectionSpecificHeader;
import org.mp4parser.tools.IsoTypeReader;
import org.mp4parser.tools.IsoTypeWriter;

public class PlayReadyHeader extends ProtectionSpecificHeader {
    public static UUID b = UUID.fromString("9A04F079-9840-4286-AB92-E65BE0885F95");
    private long c;
    private List<PlayReadyRecord> d;

    static {
        ProtectionSpecificHeader.f3938a.put(b, PlayReadyHeader.class);
    }

    public UUID a() {
        return b;
    }

    public void a(ByteBuffer byteBuffer) {
        this.c = IsoTypeReader.a(byteBuffer);
        this.d = PlayReadyRecord.a(byteBuffer, IsoTypeReader.e(byteBuffer));
    }

    public ByteBuffer b() {
        int i = 6;
        for (PlayReadyRecord a2 : this.d) {
            i = i + 4 + a2.a().rewind().limit();
        }
        ByteBuffer allocate = ByteBuffer.allocate(i);
        IsoTypeWriter.c(allocate, (long) i);
        IsoTypeWriter.c(allocate, this.d.size());
        for (PlayReadyRecord next : this.d) {
            IsoTypeWriter.c(allocate, next.f3946a);
            IsoTypeWriter.c(allocate, next.a().limit());
            allocate.put(next.a());
        }
        return allocate;
    }

    public List<PlayReadyRecord> c() {
        return Collections.unmodifiableList(this.d);
    }

    public void a(List<PlayReadyRecord> list) {
        this.d = list;
    }

    public String toString() {
        return "PlayReadyHeader" + "{length=" + this.c + ", recordCount=" + this.d.size() + ", records=" + this.d + Operators.BLOCK_END;
    }

    public static abstract class PlayReadyRecord {

        /* renamed from: a  reason: collision with root package name */
        int f3946a;

        public abstract ByteBuffer a();

        public abstract void a(ByteBuffer byteBuffer);

        public PlayReadyRecord(int i) {
            this.f3946a = i;
        }

        public static List<PlayReadyRecord> a(ByteBuffer byteBuffer, int i) {
            PlayReadyRecord playReadyRecord;
            ArrayList arrayList = new ArrayList(i);
            for (int i2 = 0; i2 < i; i2++) {
                int e = IsoTypeReader.e(byteBuffer);
                int e2 = IsoTypeReader.e(byteBuffer);
                switch (e) {
                    case 1:
                        playReadyRecord = new RMHeader();
                        break;
                    case 2:
                        playReadyRecord = new DefaulPlayReadyRecord(2);
                        break;
                    case 3:
                        playReadyRecord = new EmeddedLicenseStore();
                        break;
                    default:
                        playReadyRecord = new DefaulPlayReadyRecord(e);
                        break;
                }
                playReadyRecord.a((ByteBuffer) byteBuffer.slice().limit(e2));
                byteBuffer.position(byteBuffer.position() + e2);
                arrayList.add(playReadyRecord);
            }
            return arrayList;
        }

        public String toString() {
            return "PlayReadyRecord" + "{type=" + this.f3946a + ", length=" + a().limit() + Operators.BLOCK_END;
        }

        public static class RMHeader extends PlayReadyRecord {
            String b;

            public RMHeader() {
                super(1);
            }

            public void a(ByteBuffer byteBuffer) {
                try {
                    byte[] bArr = new byte[byteBuffer.slice().limit()];
                    byteBuffer.get(bArr);
                    this.b = new String(bArr, "UTF-16LE");
                } catch (UnsupportedEncodingException e) {
                    throw new RuntimeException(e);
                }
            }

            public ByteBuffer a() {
                try {
                    return ByteBuffer.wrap(this.b.getBytes("UTF-16LE"));
                } catch (UnsupportedEncodingException e) {
                    throw new RuntimeException(e);
                }
            }

            public String b() {
                return this.b;
            }

            public void a(String str) {
                this.b = str;
            }

            public String toString() {
                return "RMHeader" + "{length=" + a().limit() + ", header='" + this.b + Operators.SINGLE_QUOTE + Operators.BLOCK_END;
            }
        }

        public static class EmeddedLicenseStore extends PlayReadyRecord {
            ByteBuffer b;

            public EmeddedLicenseStore() {
                super(3);
            }

            public void a(ByteBuffer byteBuffer) {
                this.b = byteBuffer.duplicate();
            }

            public ByteBuffer a() {
                return this.b;
            }

            public String toString() {
                return "EmeddedLicenseStore" + "{length=" + a().limit() + Operators.BLOCK_END;
            }
        }

        public static class DefaulPlayReadyRecord extends PlayReadyRecord {
            ByteBuffer b;

            public DefaulPlayReadyRecord(int i) {
                super(i);
            }

            public void a(ByteBuffer byteBuffer) {
                this.b = byteBuffer.duplicate();
            }

            public ByteBuffer a() {
                return this.b;
            }
        }
    }
}
