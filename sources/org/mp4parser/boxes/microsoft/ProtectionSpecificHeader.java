package org.mp4parser.boxes.microsoft;

import com.taobao.weex.el.parse.Operators;
import java.nio.ByteBuffer;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import org.mp4parser.boxes.microsoft.contentprotection.GenericHeader;
import org.mp4parser.tools.Hex;

public abstract class ProtectionSpecificHeader {

    /* renamed from: a  reason: collision with root package name */
    protected static Map<UUID, Class<? extends ProtectionSpecificHeader>> f3938a = new HashMap();

    public abstract UUID a();

    public abstract void a(ByteBuffer byteBuffer);

    public abstract ByteBuffer b();

    public static ProtectionSpecificHeader a(UUID uuid, ByteBuffer byteBuffer) {
        ProtectionSpecificHeader protectionSpecificHeader;
        Class cls = f3938a.get(uuid);
        if (cls != null) {
            try {
                protectionSpecificHeader = (ProtectionSpecificHeader) cls.newInstance();
            } catch (InstantiationException e) {
                throw new RuntimeException(e);
            } catch (IllegalAccessException e2) {
                throw new RuntimeException(e2);
            }
        } else {
            protectionSpecificHeader = null;
        }
        if (protectionSpecificHeader == null) {
            protectionSpecificHeader = new GenericHeader();
        }
        protectionSpecificHeader.a(byteBuffer);
        return protectionSpecificHeader;
    }

    public boolean equals(Object obj) {
        throw new RuntimeException("somebody called equals on me but that's not supposed to happen.");
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("ProtectionSpecificHeader");
        sb.append("{data=");
        ByteBuffer duplicate = b().duplicate();
        duplicate.rewind();
        byte[] bArr = new byte[duplicate.limit()];
        duplicate.get(bArr);
        sb.append(Hex.a(bArr));
        sb.append(Operators.BLOCK_END);
        return sb.toString();
    }
}
