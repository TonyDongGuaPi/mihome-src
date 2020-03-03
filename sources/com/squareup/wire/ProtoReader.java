package com.squareup.wire;

import java.io.EOFException;
import java.io.IOException;
import java.net.ProtocolException;
import okio.BufferedSource;
import okio.ByteString;

public final class ProtoReader {
    private static final int FIELD_ENCODING_MASK = 7;
    private static final int RECURSION_LIMIT = 65;
    private static final int STATE_END_GROUP = 4;
    private static final int STATE_FIXED32 = 5;
    private static final int STATE_FIXED64 = 1;
    private static final int STATE_LENGTH_DELIMITED = 2;
    private static final int STATE_PACKED_TAG = 7;
    private static final int STATE_START_GROUP = 3;
    private static final int STATE_TAG = 6;
    private static final int STATE_VARINT = 0;
    static final int TAG_FIELD_ENCODING_BITS = 3;
    private long limit = Long.MAX_VALUE;
    private FieldEncoding nextFieldEncoding;
    private long pos = 0;
    private long pushedLimit = -1;
    private int recursionDepth;
    private final BufferedSource source;
    private int state = 2;
    private int tag = -1;

    public ProtoReader(BufferedSource bufferedSource) {
        this.source = bufferedSource;
    }

    public long beginMessage() throws IOException {
        if (this.state == 2) {
            int i = this.recursionDepth + 1;
            this.recursionDepth = i;
            if (i <= 65) {
                long j = this.pushedLimit;
                this.pushedLimit = -1;
                this.state = 6;
                return j;
            }
            throw new IOException("Wire recursion limit exceeded");
        }
        throw new IllegalStateException("Unexpected call to beginMessage()");
    }

    public void endMessage(long j) throws IOException {
        if (this.state == 6) {
            int i = this.recursionDepth - 1;
            this.recursionDepth = i;
            if (i < 0 || this.pushedLimit != -1) {
                throw new IllegalStateException("No corresponding call to beginMessage()");
            } else if (this.pos == this.limit || this.recursionDepth == 0) {
                this.limit = j;
            } else {
                throw new IOException("Expected to end at " + this.limit + " but was " + this.pos);
            }
        } else {
            throw new IllegalStateException("Unexpected call to endMessage()");
        }
    }

    public int nextTag() throws IOException {
        if (this.state == 7) {
            this.state = 2;
            return this.tag;
        } else if (this.state == 6) {
            while (this.pos < this.limit && !this.source.exhausted()) {
                int internalReadVarint32 = internalReadVarint32();
                if (internalReadVarint32 != 0) {
                    this.tag = internalReadVarint32 >> 3;
                    int i = internalReadVarint32 & 7;
                    switch (i) {
                        case 0:
                            this.nextFieldEncoding = FieldEncoding.VARINT;
                            this.state = 0;
                            return this.tag;
                        case 1:
                            this.nextFieldEncoding = FieldEncoding.FIXED64;
                            this.state = 1;
                            return this.tag;
                        case 2:
                            this.nextFieldEncoding = FieldEncoding.LENGTH_DELIMITED;
                            this.state = 2;
                            int internalReadVarint322 = internalReadVarint32();
                            if (internalReadVarint322 < 0) {
                                throw new ProtocolException("Negative length: " + internalReadVarint322);
                            } else if (this.pushedLimit == -1) {
                                this.pushedLimit = this.limit;
                                this.limit = this.pos + ((long) internalReadVarint322);
                                if (this.limit <= this.pushedLimit) {
                                    return this.tag;
                                }
                                throw new EOFException();
                            } else {
                                throw new IllegalStateException();
                            }
                        case 3:
                            skipGroup(this.tag);
                        case 4:
                            throw new ProtocolException("Unexpected end group");
                        case 5:
                            this.nextFieldEncoding = FieldEncoding.FIXED32;
                            this.state = 5;
                            return this.tag;
                        default:
                            throw new ProtocolException("Unexpected field encoding: " + i);
                    }
                } else {
                    throw new ProtocolException("Unexpected tag 0");
                }
            }
            return -1;
        } else {
            throw new IllegalStateException("Unexpected call to nextTag()");
        }
    }

    public FieldEncoding peekFieldEncoding() throws IOException {
        return this.nextFieldEncoding;
    }

    public void skip() throws IOException {
        int i = this.state;
        if (i != 5) {
            switch (i) {
                case 0:
                    readVarint64();
                    return;
                case 1:
                    readFixed64();
                    return;
                case 2:
                    this.source.skip(beforeLengthDelimitedScalar());
                    return;
                default:
                    throw new IllegalStateException("Unexpected call to skip()");
            }
        } else {
            readFixed32();
        }
    }

    private void skipGroup(int i) throws IOException {
        while (this.pos < this.limit && !this.source.exhausted()) {
            int internalReadVarint32 = internalReadVarint32();
            if (internalReadVarint32 != 0) {
                int i2 = internalReadVarint32 >> 3;
                int i3 = internalReadVarint32 & 7;
                switch (i3) {
                    case 0:
                        this.state = 0;
                        readVarint64();
                        break;
                    case 1:
                        this.state = 1;
                        readFixed64();
                        break;
                    case 2:
                        long internalReadVarint322 = (long) internalReadVarint32();
                        this.pos += internalReadVarint322;
                        this.source.skip(internalReadVarint322);
                        break;
                    case 3:
                        skipGroup(i2);
                        break;
                    case 4:
                        if (i2 != i) {
                            throw new ProtocolException("Unexpected end group");
                        }
                        return;
                    case 5:
                        this.state = 5;
                        readFixed32();
                        break;
                    default:
                        throw new ProtocolException("Unexpected field encoding: " + i3);
                }
            } else {
                throw new ProtocolException("Unexpected tag 0");
            }
        }
        throw new EOFException();
    }

    public ByteString readBytes() throws IOException {
        return this.source.readByteString(beforeLengthDelimitedScalar());
    }

    public String readString() throws IOException {
        return this.source.readUtf8(beforeLengthDelimitedScalar());
    }

    public int readVarint32() throws IOException {
        if (this.state == 0 || this.state == 2) {
            int internalReadVarint32 = internalReadVarint32();
            afterPackableScalar(0);
            return internalReadVarint32;
        }
        throw new ProtocolException("Expected VARINT or LENGTH_DELIMITED but was " + this.state);
    }

    private int internalReadVarint32() throws IOException {
        this.pos++;
        byte readByte = this.source.readByte();
        if (readByte >= 0) {
            return readByte;
        }
        byte b = readByte & Byte.MAX_VALUE;
        this.pos++;
        byte readByte2 = this.source.readByte();
        if (readByte2 >= 0) {
            return b | (readByte2 << 7);
        }
        byte b2 = b | ((readByte2 & Byte.MAX_VALUE) << 7);
        this.pos++;
        byte readByte3 = this.source.readByte();
        if (readByte3 >= 0) {
            return b2 | (readByte3 << 14);
        }
        byte b3 = b2 | ((readByte3 & Byte.MAX_VALUE) << 14);
        this.pos++;
        byte readByte4 = this.source.readByte();
        if (readByte4 >= 0) {
            return b3 | (readByte4 << 21);
        }
        byte b4 = b3 | ((readByte4 & Byte.MAX_VALUE) << 21);
        this.pos++;
        byte readByte5 = this.source.readByte();
        byte b5 = b4 | (readByte5 << 28);
        if (readByte5 >= 0) {
            return b5;
        }
        for (int i = 0; i < 5; i++) {
            this.pos++;
            if (this.source.readByte() >= 0) {
                return b5;
            }
        }
        throw new ProtocolException("Malformed VARINT");
    }

    public long readVarint64() throws IOException {
        if (this.state == 0 || this.state == 2) {
            long j = 0;
            for (int i = 0; i < 64; i += 7) {
                this.pos++;
                byte readByte = this.source.readByte();
                j |= ((long) (readByte & Byte.MAX_VALUE)) << i;
                if ((readByte & 128) == 0) {
                    afterPackableScalar(0);
                    return j;
                }
            }
            throw new ProtocolException("WireInput encountered a malformed varint");
        }
        throw new ProtocolException("Expected VARINT or LENGTH_DELIMITED but was " + this.state);
    }

    public int readFixed32() throws IOException {
        if (this.state == 5 || this.state == 2) {
            this.source.require(4);
            this.pos += 4;
            int readIntLe = this.source.readIntLe();
            afterPackableScalar(5);
            return readIntLe;
        }
        throw new ProtocolException("Expected FIXED32 or LENGTH_DELIMITED but was " + this.state);
    }

    public long readFixed64() throws IOException {
        if (this.state == 1 || this.state == 2) {
            this.source.require(8);
            this.pos += 8;
            long readLongLe = this.source.readLongLe();
            afterPackableScalar(1);
            return readLongLe;
        }
        throw new ProtocolException("Expected FIXED64 or LENGTH_DELIMITED but was " + this.state);
    }

    private void afterPackableScalar(int i) throws IOException {
        if (this.state == i) {
            this.state = 6;
        } else if (this.pos > this.limit) {
            throw new IOException("Expected to end at " + this.limit + " but was " + this.pos);
        } else if (this.pos == this.limit) {
            this.limit = this.pushedLimit;
            this.pushedLimit = -1;
            this.state = 6;
        } else {
            this.state = 7;
        }
    }

    private long beforeLengthDelimitedScalar() throws IOException {
        if (this.state == 2) {
            long j = this.limit - this.pos;
            this.source.require(j);
            this.state = 6;
            this.pos = this.limit;
            this.limit = this.pushedLimit;
            this.pushedLimit = -1;
            return j;
        }
        throw new ProtocolException("Expected LENGTH_DELIMITED but was " + this.state);
    }
}
