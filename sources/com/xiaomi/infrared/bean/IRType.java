package com.xiaomi.infrared.bean;

import com.xiaomi.smarthome.R;

public enum IRType {
    STB(1),
    TV(2),
    BOX(3),
    DVD(4),
    AC(5),
    NO_STATE_AC(5),
    PRO(6),
    PA(7),
    FAN(8),
    SLR(9),
    LIGHT(10),
    AIR_CLEANER(11),
    WATER_HEATER(12),
    Unknown(0);
    
    private int value;

    private IRType(int i) {
        this.value = 0;
        this.value = i;
    }

    /* JADX WARNING: Can't fix incorrect switch cases order */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static com.xiaomi.infrared.bean.IRType valueOfModel(java.lang.String r1) {
        /*
            int r0 = r1.hashCode()
            switch(r0) {
                case -2132619104: goto L_0x0095;
                case -1705284246: goto L_0x008a;
                case -1538046743: goto L_0x007f;
                case -672704689: goto L_0x0075;
                case -651195253: goto L_0x006a;
                case -1574995: goto L_0x0060;
                case 1225669421: goto L_0x0055;
                case 1494818283: goto L_0x004a;
                case 1510596542: goto L_0x0040;
                case 1510596543: goto L_0x0036;
                case 1641499374: goto L_0x002a;
                case 1645729317: goto L_0x001f;
                case 1957550456: goto L_0x0014;
                case 2143373335: goto L_0x0009;
                default: goto L_0x0007;
            }
        L_0x0007:
            goto L_0x009f
        L_0x0009:
            java.lang.String r0 = "miir.wifispeaker.ir01"
            boolean r1 = r1.equals(r0)
            if (r1 == 0) goto L_0x009f
            r1 = 7
            goto L_0x00a0
        L_0x0014:
            java.lang.String r0 = "miir.tvbox.ir01"
            boolean r1 = r1.equals(r0)
            if (r1 == 0) goto L_0x009f
            r1 = 2
            goto L_0x00a0
        L_0x001f:
            java.lang.String r0 = "miir.projector.ir01"
            boolean r1 = r1.equals(r0)
            if (r1 == 0) goto L_0x009f
            r1 = 6
            goto L_0x00a0
        L_0x002a:
            java.lang.String r0 = "miir.fan.ir01"
            boolean r1 = r1.equals(r0)
            if (r1 == 0) goto L_0x009f
            r1 = 8
            goto L_0x00a0
        L_0x0036:
            java.lang.String r0 = "miir.aircondition.ir02"
            boolean r1 = r1.equals(r0)
            if (r1 == 0) goto L_0x009f
            r1 = 5
            goto L_0x00a0
        L_0x0040:
            java.lang.String r0 = "miir.aircondition.ir01"
            boolean r1 = r1.equals(r0)
            if (r1 == 0) goto L_0x009f
            r1 = 4
            goto L_0x00a0
        L_0x004a:
            java.lang.String r0 = "miir.airpurifier.ir01"
            boolean r1 = r1.equals(r0)
            if (r1 == 0) goto L_0x009f
            r1 = 11
            goto L_0x00a0
        L_0x0055:
            java.lang.String r0 = "miir.waterheater.ir01"
            boolean r1 = r1.equals(r0)
            if (r1 == 0) goto L_0x009f
            r1 = 12
            goto L_0x00a0
        L_0x0060:
            java.lang.String r0 = "miir.tv.ir01"
            boolean r1 = r1.equals(r0)
            if (r1 == 0) goto L_0x009f
            r1 = 1
            goto L_0x00a0
        L_0x006a:
            java.lang.String r0 = "miir.light.ir01"
            boolean r1 = r1.equals(r0)
            if (r1 == 0) goto L_0x009f
            r1 = 10
            goto L_0x00a0
        L_0x0075:
            java.lang.String r0 = "miir.dvd.ir01"
            boolean r1 = r1.equals(r0)
            if (r1 == 0) goto L_0x009f
            r1 = 3
            goto L_0x00a0
        L_0x007f:
            java.lang.String r0 = "miir.remote.ir01"
            boolean r1 = r1.equals(r0)
            if (r1 == 0) goto L_0x009f
            r1 = 13
            goto L_0x00a0
        L_0x008a:
            java.lang.String r0 = "miir.camera.ir01"
            boolean r1 = r1.equals(r0)
            if (r1 == 0) goto L_0x009f
            r1 = 9
            goto L_0x00a0
        L_0x0095:
            java.lang.String r0 = "miir.stb.ir01"
            boolean r1 = r1.equals(r0)
            if (r1 == 0) goto L_0x009f
            r1 = 0
            goto L_0x00a0
        L_0x009f:
            r1 = -1
        L_0x00a0:
            switch(r1) {
                case 0: goto L_0x00cd;
                case 1: goto L_0x00ca;
                case 2: goto L_0x00c7;
                case 3: goto L_0x00c4;
                case 4: goto L_0x00c1;
                case 5: goto L_0x00be;
                case 6: goto L_0x00bb;
                case 7: goto L_0x00b8;
                case 8: goto L_0x00b5;
                case 9: goto L_0x00b2;
                case 10: goto L_0x00af;
                case 11: goto L_0x00ac;
                case 12: goto L_0x00a9;
                case 13: goto L_0x00a6;
                default: goto L_0x00a3;
            }
        L_0x00a3:
            com.xiaomi.infrared.bean.IRType r1 = Unknown
            return r1
        L_0x00a6:
            com.xiaomi.infrared.bean.IRType r1 = Unknown
            return r1
        L_0x00a9:
            com.xiaomi.infrared.bean.IRType r1 = WATER_HEATER
            return r1
        L_0x00ac:
            com.xiaomi.infrared.bean.IRType r1 = AIR_CLEANER
            return r1
        L_0x00af:
            com.xiaomi.infrared.bean.IRType r1 = LIGHT
            return r1
        L_0x00b2:
            com.xiaomi.infrared.bean.IRType r1 = SLR
            return r1
        L_0x00b5:
            com.xiaomi.infrared.bean.IRType r1 = FAN
            return r1
        L_0x00b8:
            com.xiaomi.infrared.bean.IRType r1 = PA
            return r1
        L_0x00bb:
            com.xiaomi.infrared.bean.IRType r1 = PRO
            return r1
        L_0x00be:
            com.xiaomi.infrared.bean.IRType r1 = AC
            return r1
        L_0x00c1:
            com.xiaomi.infrared.bean.IRType r1 = NO_STATE_AC
            return r1
        L_0x00c4:
            com.xiaomi.infrared.bean.IRType r1 = DVD
            return r1
        L_0x00c7:
            com.xiaomi.infrared.bean.IRType r1 = BOX
            return r1
        L_0x00ca:
            com.xiaomi.infrared.bean.IRType r1 = TV
            return r1
        L_0x00cd:
            com.xiaomi.infrared.bean.IRType r1 = STB
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.infrared.bean.IRType.valueOfModel(java.lang.String):com.xiaomi.infrared.bean.IRType");
    }

    public static IRType valueOf(int i) {
        switch (i) {
            case 1:
                return STB;
            case 2:
                return TV;
            case 3:
                return BOX;
            case 4:
                return DVD;
            case 5:
                return AC;
            case 6:
                return PRO;
            case 7:
                return PA;
            case 8:
                return FAN;
            case 9:
                return SLR;
            case 10:
                return LIGHT;
            case 11:
                return AIR_CLEANER;
            case 12:
                return WATER_HEATER;
            default:
                return Unknown;
        }
    }

    public static int getInfraredControllerLogo(IRType iRType) {
        switch (iRType) {
            case STB:
                return R.drawable.ir_main_device_list_settopbox;
            case TV:
                return R.drawable.ir_main_device_list_mitv;
            case BOX:
                return R.drawable.ir_main_device_list_mibox;
            case DVD:
                return R.drawable.ir_main_device_list_dvd;
            case NO_STATE_AC:
            case AC:
                return R.drawable.ir_main_device_list_air;
            case PRO:
                return R.drawable.ir_main_device_list_projector;
            case PA:
                return R.drawable.ir_main_device_list_amplifier;
            case FAN:
                return R.drawable.ir_main_device_list_fan;
            case SLR:
                return R.drawable.ir_main_device_list_camera;
            case LIGHT:
                return R.drawable.ir_device_list_lamp;
            case AIR_CLEANER:
                return R.drawable.ir_main_device_list_purifier;
            case WATER_HEATER:
                return R.drawable.ir_man_device_list_water_heater;
            case Unknown:
                return R.drawable.ir_main_device_list_others;
            default:
                return 0;
        }
    }

    public int value() {
        return this.value;
    }
}
