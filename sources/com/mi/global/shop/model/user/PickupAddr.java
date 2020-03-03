package com.mi.global.shop.model.user;

import com.squareup.wire.FieldEncoding;
import com.squareup.wire.Message;
import com.squareup.wire.ProtoAdapter;
import com.squareup.wire.ProtoReader;
import com.squareup.wire.ProtoWriter;
import com.squareup.wire.WireField;
import com.squareup.wire.internal.Internal;
import com.taobao.weex.el.parse.Operators;
import java.io.IOException;
import okio.ByteString;

public final class PickupAddr extends Message<PickupAddr, Builder> {
    public static final ProtoAdapter<PickupAddr> ADAPTER = new ProtoAdapter_PickupAddr();
    public static final String DEFAULT_CODE = "";
    public static final String DEFAULT_SAT = "";
    public static final String DEFAULT_SUNDAYTOHOLIDAY = "";
    public static final String DEFAULT_WORKDAY = "";
    private static final long serialVersionUID = 0;
    @WireField(adapter = "com.squareup.wire.ProtoAdapter#STRING", tag = 3)
    public final String Sat;
    @WireField(adapter = "com.squareup.wire.ProtoAdapter#STRING", tag = 4)
    public final String SundayToHoliday;
    @WireField(adapter = "com.squareup.wire.ProtoAdapter#STRING", tag = 1)
    public final String code;
    @WireField(adapter = "com.squareup.wire.ProtoAdapter#STRING", tag = 2)
    public final String workday;

    public PickupAddr(String str, String str2, String str3, String str4) {
        this(str, str2, str3, str4, ByteString.EMPTY);
    }

    public PickupAddr(String str, String str2, String str3, String str4, ByteString byteString) {
        super(ADAPTER, byteString);
        this.code = str;
        this.workday = str2;
        this.Sat = str3;
        this.SundayToHoliday = str4;
    }

    public Builder newBuilder() {
        Builder builder = new Builder();
        builder.code = this.code;
        builder.workday = this.workday;
        builder.Sat = this.Sat;
        builder.SundayToHoliday = this.SundayToHoliday;
        builder.addUnknownFields(unknownFields());
        return builder;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof PickupAddr)) {
            return false;
        }
        PickupAddr pickupAddr = (PickupAddr) obj;
        if (!Internal.equals(unknownFields(), pickupAddr.unknownFields()) || !Internal.equals(this.code, pickupAddr.code) || !Internal.equals(this.workday, pickupAddr.workday) || !Internal.equals(this.Sat, pickupAddr.Sat) || !Internal.equals(this.SundayToHoliday, pickupAddr.SundayToHoliday)) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        int i = this.hashCode;
        if (i != 0) {
            return i;
        }
        int i2 = 0;
        int hashCode = ((((((unknownFields().hashCode() * 37) + (this.code != null ? this.code.hashCode() : 0)) * 37) + (this.workday != null ? this.workday.hashCode() : 0)) * 37) + (this.Sat != null ? this.Sat.hashCode() : 0)) * 37;
        if (this.SundayToHoliday != null) {
            i2 = this.SundayToHoliday.hashCode();
        }
        int i3 = hashCode + i2;
        this.hashCode = i3;
        return i3;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        if (this.code != null) {
            sb.append(", code=");
            sb.append(this.code);
        }
        if (this.workday != null) {
            sb.append(", workday=");
            sb.append(this.workday);
        }
        if (this.Sat != null) {
            sb.append(", Sat=");
            sb.append(this.Sat);
        }
        if (this.SundayToHoliday != null) {
            sb.append(", SundayToHoliday=");
            sb.append(this.SundayToHoliday);
        }
        StringBuilder replace = sb.replace(0, 2, "PickupAddr{");
        replace.append(Operators.BLOCK_END);
        return replace.toString();
    }

    public static final class Builder extends Message.Builder<PickupAddr, Builder> {
        public String Sat;
        public String SundayToHoliday;
        public String code;
        public String workday;

        public Builder code(String str) {
            this.code = str;
            return this;
        }

        public Builder workday(String str) {
            this.workday = str;
            return this;
        }

        public Builder Sat(String str) {
            this.Sat = str;
            return this;
        }

        public Builder SundayToHoliday(String str) {
            this.SundayToHoliday = str;
            return this;
        }

        public PickupAddr build() {
            return new PickupAddr(this.code, this.workday, this.Sat, this.SundayToHoliday, buildUnknownFields());
        }
    }

    private static final class ProtoAdapter_PickupAddr extends ProtoAdapter<PickupAddr> {
        ProtoAdapter_PickupAddr() {
            super(FieldEncoding.LENGTH_DELIMITED, PickupAddr.class);
        }

        public int encodedSize(PickupAddr pickupAddr) {
            int i = 0;
            int encodedSizeWithTag = (pickupAddr.code != null ? ProtoAdapter.STRING.encodedSizeWithTag(1, pickupAddr.code) : 0) + (pickupAddr.workday != null ? ProtoAdapter.STRING.encodedSizeWithTag(2, pickupAddr.workday) : 0) + (pickupAddr.Sat != null ? ProtoAdapter.STRING.encodedSizeWithTag(3, pickupAddr.Sat) : 0);
            if (pickupAddr.SundayToHoliday != null) {
                i = ProtoAdapter.STRING.encodedSizeWithTag(4, pickupAddr.SundayToHoliday);
            }
            return encodedSizeWithTag + i + pickupAddr.unknownFields().size();
        }

        public void encode(ProtoWriter protoWriter, PickupAddr pickupAddr) throws IOException {
            if (pickupAddr.code != null) {
                ProtoAdapter.STRING.encodeWithTag(protoWriter, 1, pickupAddr.code);
            }
            if (pickupAddr.workday != null) {
                ProtoAdapter.STRING.encodeWithTag(protoWriter, 2, pickupAddr.workday);
            }
            if (pickupAddr.Sat != null) {
                ProtoAdapter.STRING.encodeWithTag(protoWriter, 3, pickupAddr.Sat);
            }
            if (pickupAddr.SundayToHoliday != null) {
                ProtoAdapter.STRING.encodeWithTag(protoWriter, 4, pickupAddr.SundayToHoliday);
            }
            protoWriter.writeBytes(pickupAddr.unknownFields());
        }

        public PickupAddr decode(ProtoReader protoReader) throws IOException {
            Builder builder = new Builder();
            long beginMessage = protoReader.beginMessage();
            while (true) {
                int nextTag = protoReader.nextTag();
                if (nextTag != -1) {
                    switch (nextTag) {
                        case 1:
                            builder.code(ProtoAdapter.STRING.decode(protoReader));
                            break;
                        case 2:
                            builder.workday(ProtoAdapter.STRING.decode(protoReader));
                            break;
                        case 3:
                            builder.Sat(ProtoAdapter.STRING.decode(protoReader));
                            break;
                        case 4:
                            builder.SundayToHoliday(ProtoAdapter.STRING.decode(protoReader));
                            break;
                        default:
                            FieldEncoding peekFieldEncoding = protoReader.peekFieldEncoding();
                            builder.addUnknownField(nextTag, peekFieldEncoding, peekFieldEncoding.rawProtoAdapter().decode(protoReader));
                            break;
                    }
                } else {
                    protoReader.endMessage(beginMessage);
                    return builder.build();
                }
            }
        }

        public PickupAddr redact(PickupAddr pickupAddr) {
            Builder newBuilder = pickupAddr.newBuilder();
            newBuilder.clearUnknownFields();
            return newBuilder.build();
        }
    }
}
