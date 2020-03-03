package in.cashify.otex;

import android.support.annotation.NonNull;
import com.xiaomi.smarthome.download.Downloads;

public class ExchangeError {

    /* renamed from: a  reason: collision with root package name */
    public final String f2590a;
    public final Kind b;

    public enum Kind {
        INVALID_RESPONSE(402, "Invalid server response"),
        ROOTED_DEVICE_NOT_SUPPORTED(403, "Unsupported rooted device"),
        EMULATOR_NOT_SUPPORTED(405, "Unsupported Emulator"),
        DEVICE_NOT_SUPPORTED(406, "Unsupported Device"),
        DEVICE_NOT_ELIGIBLE(407, "Ineligible Device"),
        EXCHANGE_NOT_VALID(408, "Invalid Exchange!"),
        SERVER_ERROR(410, "server error"),
        INVALID_PIN_CODE(411, "Invalid postal code"),
        DUPLICATE_DEVICE(416, "Unsupported duplicate device"),
        PERMISSION_NOT_GRANTED(417, "READ_PHONE_STATE permission not granted to start diagnose"),
        UNKNOWN_ERROR(Downloads.STATUS_DEVICE_NOT_FOUND_ERROR, "Unknown Error");
        

        /* renamed from: a  reason: collision with root package name */
        public final int f2591a;
        public String b;

        /* access modifiers changed from: public */
        Kind(int i, String str) {
            this.f2591a = i;
            this.b = str;
        }

        public static Kind valueOfInt(int i) {
            for (Kind kind : values()) {
                if (kind.getCode() == i) {
                    return kind;
                }
            }
            return UNKNOWN_ERROR;
        }

        public int getCode() {
            return this.f2591a;
        }

        public String getDetail() {
            return this.b;
        }

        public void setDetail(String str) {
            this.b = str;
        }
    }

    public ExchangeError(String str, int i) {
        this.f2590a = str;
        this.b = Kind.valueOfInt(i);
        this.b.setDetail(str);
    }

    public ExchangeError(String str, @NonNull Kind kind) {
        this.f2590a = str;
        this.b = kind;
    }

    public String a() {
        return this.f2590a;
    }

    public Kind b() {
        return this.b;
    }
}
