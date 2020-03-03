package com.xiaomi.smarthome.framework.update;

import com.xiaomi.smarthome.library.bluetooth.OTAErrorCode;

public class OTAException extends Exception {
    private int mCode;

    static String code2String(int i) {
        if (i == -599) {
            return "switch firmware unknown error";
        }
        if (i == -499) {
            return "packet response unknown error";
        }
        switch (i) {
            case OTAErrorCode.C /*-506*/:
                return "switch firmware invalid sign";
            case OTAErrorCode.B /*-505*/:
                return "switch firmware device busy";
            case OTAErrorCode.A /*-504*/:
                return "switch firmware memory is full";
            case OTAErrorCode.z /*-503*/:
                return "switch firmware invalid params";
            case OTAErrorCode.x /*-502*/:
                return "switch firmware auth fail";
            case OTAErrorCode.y /*-501*/:
                return "switch firmware invalid command";
            default:
                switch (i) {
                    case OTAErrorCode.v /*-413*/:
                        return "packet response busy";
                    case OTAErrorCode.u /*-412*/:
                        return "packet response timeout";
                    default:
                        switch (i) {
                            case OTAErrorCode.t /*-403*/:
                                return "read device fragment size fail";
                            case OTAErrorCode.s /*-402*/:
                                return "read fragment last index fail";
                            case OTAErrorCode.r /*-401*/:
                                return "read protocol version fail";
                            default:
                                switch (i) {
                                    case OTAErrorCode.q /*-303*/:
                                        return "download url is empty";
                                    case OTAErrorCode.p /*-302*/:
                                        return "download file md5 not equal";
                                    case OTAErrorCode.o /*-301*/:
                                        return "download file fail";
                                    default:
                                        switch (i) {
                                            case OTAErrorCode.n /*-201*/:
                                                return "read remote version info fail";
                                            case OTAErrorCode.m /*-200*/:
                                                return "read firmware version fail";
                                            default:
                                                switch (i) {
                                                    case -108:
                                                        return "open data fragment notify fail";
                                                    case -107:
                                                        return "open control point notify fail";
                                                    default:
                                                        switch (i) {
                                                            case -105:
                                                                return "ble disconnect";
                                                            case -104:
                                                                return "download file not exist";
                                                            case -103:
                                                                return "another update ongoing";
                                                            case -102:
                                                                return "mac is empty";
                                                            case -101:
                                                                return "ota ongoing,but ble disconnect";
                                                            case -100:
                                                                return "connect device fail";
                                                            default:
                                                                switch (i) {
                                                                    case 0:
                                                                        return "ota success";
                                                                    case 1:
                                                                        return "it's latest version";
                                                                    case 2:
                                                                        return "user cancel upgrade";
                                                                    case 3:
                                                                        return "protocol version is low, need to update app";
                                                                    default:
                                                                        return "unknown error!";
                                                                }
                                                        }
                                                }
                                        }
                                }
                        }
                }
        }
    }

    public OTAException(int i) {
        super(code2String(i));
        this.mCode = i;
    }

    public OTAException(int i, String str) {
        super(str);
        this.mCode = i;
    }

    public int getCode() {
        return this.mCode;
    }
}
