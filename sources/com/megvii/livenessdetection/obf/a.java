package com.megvii.livenessdetection.obf;

import android.support.media.ExifInterface;
import com.megvii.livenessdetection.Detector;
import com.xiaomi.smarthome.device.utils.DeviceTagInterface;
import miuipub.reflect.Field;

public final class a {

    /* renamed from: a  reason: collision with root package name */
    private StringBuilder f6684a = new StringBuilder();
    private long b = -1;

    public final synchronized void a(Detector.DetectionType detectionType) {
        if (detectionType != null) {
            b();
            StringBuilder sb = this.f6684a;
            String str = "";
            switch (detectionType) {
                case NONE:
                    str = "N";
                    break;
                case DONE:
                    str = "O";
                    break;
                case BLINK:
                    str = ExifInterface.LONGITUDE_EAST;
                    break;
                case MOUTH:
                    str = "M";
                    break;
                case POS_YAW:
                    str = "Y";
                    break;
                case POS_YAW_LEFT:
                    str = "L";
                    break;
                case POS_YAW_RIGHT:
                    str = "R";
                    break;
                case POS_PITCH:
                    str = "P";
                    break;
                case POS_PITCH_UP:
                    str = "U";
                    break;
                case POS_PITCH_DOWN:
                    str = Field.h;
                    break;
                case AIMLESS:
                    str = "A";
                    break;
            }
            sb.append(str);
            this.f6684a.append("\n");
        }
    }

    public final synchronized void a(Detector.DetectionFailedType detectionFailedType) {
        if (detectionFailedType != null) {
            b();
            StringBuilder sb = this.f6684a;
            String str = "";
            switch (detectionFailedType) {
                case NOTVIDEO:
                    str = "n";
                    break;
                case ACTIONBLEND:
                    str = "b";
                    break;
                case TIMEOUT:
                    str = "t";
                    break;
                case MASK:
                    str = "m";
                    break;
                case TOOMANYFACELOST:
                    str = DeviceTagInterface.q;
                    break;
                case FACELOSTNOTCONTINUOUS:
                    str = "l";
                    break;
                case FACENOTCONTINUOUS:
                    str = "c";
                    break;
            }
            sb.append(str);
            this.f6684a.append("\n");
        }
    }

    private void b() {
        if (this.b == -1) {
            this.b = System.currentTimeMillis();
            this.f6684a.append(System.currentTimeMillis() / 1000);
            this.f6684a.append("\n");
        }
        this.f6684a.append(System.currentTimeMillis() - this.b);
        this.f6684a.append(":");
    }

    public final synchronized void a() {
        this.f6684a = new StringBuilder();
        this.b = -1;
    }

    public final synchronized String toString() {
        return this.f6684a.toString();
    }
}
