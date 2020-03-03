package com.jstun.core.attribute;

import com.jstun.core.attribute.MessageAttributeInterface;
import com.jstun.core.util.Utility;
import com.jstun.core.util.UtilityException;
import com.ximalaya.ting.android.xmpayordersdk.IXmPayOrderListener;

public class ErrorCode extends MessageAttribute {

    /* renamed from: a  reason: collision with root package name */
    int f6200a;
    String b;

    public ErrorCode() {
        super(MessageAttributeInterface.MessageAttributeType.ErrorCode);
    }

    public void a(int i) throws MessageAttributeException {
        if (i == 420) {
            this.b = "Unkown Attribute";
        } else if (i == 500) {
            this.b = "Server Error";
        } else if (i != 600) {
            switch (i) {
                case 400:
                    this.b = "Bad Request";
                    break;
                case 401:
                    this.b = "Unauthorized";
                    break;
                default:
                    switch (i) {
                        case IXmPayOrderListener.y:
                            this.b = "Stale Credentials";
                            break;
                        case IXmPayOrderListener.z:
                            this.b = "Integrity Check Failure";
                            break;
                        case IXmPayOrderListener.A:
                            this.b = "Missing Username";
                            break;
                        case IXmPayOrderListener.B:
                            this.b = "Use TLS";
                            break;
                        default:
                            throw new MessageAttributeException("Response Code is not valid");
                    }
            }
        } else {
            this.b = "Global Failure";
        }
        this.f6200a = i;
    }

    public int a() {
        return this.f6200a;
    }

    public String b() {
        return this.b;
    }

    public byte[] e() throws UtilityException {
        int length = this.b.length();
        int i = length % 4;
        if (i != 0) {
            length += 4 - i;
        }
        int i2 = length + 4;
        byte[] bArr = new byte[i2];
        System.arraycopy(Utility.b(b(this.c)), 0, bArr, 0, 2);
        System.arraycopy(Utility.b(i2 - 4), 0, bArr, 2, 2);
        double d = (double) this.f6200a;
        Double.isNaN(d);
        bArr[6] = Utility.a((int) Math.floor(d / 100.0d));
        bArr[7] = Utility.a(this.f6200a % 100);
        byte[] bytes = this.b.getBytes();
        System.arraycopy(bytes, 0, bArr, 8, bytes.length);
        return bArr;
    }

    public static ErrorCode a(byte[] bArr) throws MessageAttributeParsingException {
        try {
            if (bArr.length >= 4) {
                int a2 = Utility.a(bArr[3]);
                if (a2 < 1 || a2 > 6) {
                    throw new MessageAttributeParsingException("Class parsing error");
                }
                int a3 = Utility.a(bArr[4]);
                if (a3 < 0 || a3 > 99) {
                    throw new MessageAttributeParsingException("Number parsing error");
                }
                int i = (a2 * 100) + a3;
                ErrorCode errorCode = new ErrorCode();
                errorCode.a(i);
                return errorCode;
            }
            throw new MessageAttributeParsingException("Data array too short");
        } catch (UtilityException unused) {
            throw new MessageAttributeParsingException("Parsing error");
        } catch (MessageAttributeException unused2) {
            throw new MessageAttributeParsingException("Parsing error");
        }
    }
}
