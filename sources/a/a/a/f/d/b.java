package a.a.a.f.d;

import a.a.a.e.c.c;
import a.a.a.e.c.d;
import a.a.a.e.c.e;
import a.a.a.e.c.f;
import a.a.a.e.c.g;
import a.a.a.e.c.h;
import a.a.a.e.c.j;
import a.a.a.e.c.k;
import a.a.a.e.c.l;
import a.a.a.e.c.m;
import a.a.a.e.c.n;
import a.a.a.e.c.o;
import a.a.a.e.c.p;
import android.os.Parcel;
import android.os.Parcelable;
import in.cashify.otex.ExchangeManager;
import java.io.InvalidObjectException;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class b extends a.a.a.f.a implements Parcelable {
    public static final Parcelable.Creator<b> CREATOR = new a();

    /* renamed from: a  reason: collision with root package name */
    public List<g> f406a;

    public class a implements Parcelable.Creator<b> {
        /* renamed from: a */
        public b createFromParcel(Parcel parcel) {
            return new b(parcel);
        }

        /* renamed from: a */
        public b[] newArray(int i) {
            return new b[i];
        }
    }

    /* renamed from: a.a.a.f.d.b$b  reason: collision with other inner class name */
    public static /* synthetic */ class C0012b {

        /* renamed from: a  reason: collision with root package name */
        public static final /* synthetic */ int[] f407a = new int[ExchangeManager.h.values().length];

        /* JADX WARNING: Can't wrap try/catch for region: R(74:0|1|2|3|4|5|6|7|8|9|10|11|12|13|14|15|16|17|18|19|20|21|22|23|24|25|26|27|28|29|30|31|32|33|34|35|36|37|38|39|40|41|42|43|44|45|46|47|48|49|50|51|52|53|54|55|56|57|58|59|60|61|62|63|64|65|66|67|68|69|70|71|72|(3:73|74|76)) */
        /* JADX WARNING: Can't wrap try/catch for region: R(76:0|1|2|3|4|5|6|7|8|9|10|11|12|13|14|15|16|17|18|19|20|21|22|23|24|25|26|27|28|29|30|31|32|33|34|35|36|37|38|39|40|41|42|43|44|45|46|47|48|49|50|51|52|53|54|55|56|57|58|59|60|61|62|63|64|65|66|67|68|69|70|71|72|73|74|76) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:11:0x0040 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:13:0x004b */
        /* JADX WARNING: Missing exception handler attribute for start block: B:15:0x0056 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:17:0x0062 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:19:0x006e */
        /* JADX WARNING: Missing exception handler attribute for start block: B:21:0x007a */
        /* JADX WARNING: Missing exception handler attribute for start block: B:23:0x0086 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:25:0x0092 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:27:0x009e */
        /* JADX WARNING: Missing exception handler attribute for start block: B:29:0x00aa */
        /* JADX WARNING: Missing exception handler attribute for start block: B:31:0x00b6 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:33:0x00c2 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:35:0x00ce */
        /* JADX WARNING: Missing exception handler attribute for start block: B:37:0x00da */
        /* JADX WARNING: Missing exception handler attribute for start block: B:39:0x00e6 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0014 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:41:0x00f2 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:43:0x00fe */
        /* JADX WARNING: Missing exception handler attribute for start block: B:45:0x010a */
        /* JADX WARNING: Missing exception handler attribute for start block: B:47:0x0116 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:49:0x0122 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:51:0x012e */
        /* JADX WARNING: Missing exception handler attribute for start block: B:53:0x013a */
        /* JADX WARNING: Missing exception handler attribute for start block: B:55:0x0146 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:57:0x0152 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:59:0x015e */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x001f */
        /* JADX WARNING: Missing exception handler attribute for start block: B:61:0x016a */
        /* JADX WARNING: Missing exception handler attribute for start block: B:63:0x0176 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:65:0x0182 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:67:0x018e */
        /* JADX WARNING: Missing exception handler attribute for start block: B:69:0x019a */
        /* JADX WARNING: Missing exception handler attribute for start block: B:71:0x01a6 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:73:0x01b2 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:7:0x002a */
        /* JADX WARNING: Missing exception handler attribute for start block: B:9:0x0035 */
        static {
            /*
                in.cashify.otex.ExchangeManager$h[] r0 = in.cashify.otex.ExchangeManager.h.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                f407a = r0
                int[] r0 = f407a     // Catch:{ NoSuchFieldError -> 0x0014 }
                in.cashify.otex.ExchangeManager$h r1 = in.cashify.otex.ExchangeManager.h.WELCOME_PAGE     // Catch:{ NoSuchFieldError -> 0x0014 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0014 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0014 }
            L_0x0014:
                int[] r0 = f407a     // Catch:{ NoSuchFieldError -> 0x001f }
                in.cashify.otex.ExchangeManager$h r1 = in.cashify.otex.ExchangeManager.h.QUOTE_PAGE     // Catch:{ NoSuchFieldError -> 0x001f }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001f }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001f }
            L_0x001f:
                int[] r0 = f407a     // Catch:{ NoSuchFieldError -> 0x002a }
                in.cashify.otex.ExchangeManager$h r1 = in.cashify.otex.ExchangeManager.h.MOBILE_AGE     // Catch:{ NoSuchFieldError -> 0x002a }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x002a }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x002a }
            L_0x002a:
                int[] r0 = f407a     // Catch:{ NoSuchFieldError -> 0x0035 }
                in.cashify.otex.ExchangeManager$h r1 = in.cashify.otex.ExchangeManager.h.WIFI     // Catch:{ NoSuchFieldError -> 0x0035 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0035 }
                r2 = 4
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0035 }
            L_0x0035:
                int[] r0 = f407a     // Catch:{ NoSuchFieldError -> 0x0040 }
                in.cashify.otex.ExchangeManager$h r1 = in.cashify.otex.ExchangeManager.h.BLUETOOTH     // Catch:{ NoSuchFieldError -> 0x0040 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0040 }
                r2 = 5
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0040 }
            L_0x0040:
                int[] r0 = f407a     // Catch:{ NoSuchFieldError -> 0x004b }
                in.cashify.otex.ExchangeManager$h r1 = in.cashify.otex.ExchangeManager.h.MICROPHONE     // Catch:{ NoSuchFieldError -> 0x004b }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x004b }
                r2 = 6
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x004b }
            L_0x004b:
                int[] r0 = f407a     // Catch:{ NoSuchFieldError -> 0x0056 }
                in.cashify.otex.ExchangeManager$h r1 = in.cashify.otex.ExchangeManager.h.FRONT_CAMERA     // Catch:{ NoSuchFieldError -> 0x0056 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0056 }
                r2 = 7
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0056 }
            L_0x0056:
                int[] r0 = f407a     // Catch:{ NoSuchFieldError -> 0x0062 }
                in.cashify.otex.ExchangeManager$h r1 = in.cashify.otex.ExchangeManager.h.BACK_CAMERA     // Catch:{ NoSuchFieldError -> 0x0062 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0062 }
                r2 = 8
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0062 }
            L_0x0062:
                int[] r0 = f407a     // Catch:{ NoSuchFieldError -> 0x006e }
                in.cashify.otex.ExchangeManager$h r1 = in.cashify.otex.ExchangeManager.h.BATTERY     // Catch:{ NoSuchFieldError -> 0x006e }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x006e }
                r2 = 9
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x006e }
            L_0x006e:
                int[] r0 = f407a     // Catch:{ NoSuchFieldError -> 0x007a }
                in.cashify.otex.ExchangeManager$h r1 = in.cashify.otex.ExchangeManager.h.SPEAKER     // Catch:{ NoSuchFieldError -> 0x007a }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x007a }
                r2 = 10
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x007a }
            L_0x007a:
                int[] r0 = f407a     // Catch:{ NoSuchFieldError -> 0x0086 }
                in.cashify.otex.ExchangeManager$h r1 = in.cashify.otex.ExchangeManager.h.VOLUME     // Catch:{ NoSuchFieldError -> 0x0086 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0086 }
                r2 = 11
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0086 }
            L_0x0086:
                int[] r0 = f407a     // Catch:{ NoSuchFieldError -> 0x0092 }
                in.cashify.otex.ExchangeManager$h r1 = in.cashify.otex.ExchangeManager.h.CHARGING     // Catch:{ NoSuchFieldError -> 0x0092 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0092 }
                r2 = 12
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0092 }
            L_0x0092:
                int[] r0 = f407a     // Catch:{ NoSuchFieldError -> 0x009e }
                in.cashify.otex.ExchangeManager$h r1 = in.cashify.otex.ExchangeManager.h.AUDIO_JACK     // Catch:{ NoSuchFieldError -> 0x009e }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x009e }
                r2 = 13
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x009e }
            L_0x009e:
                int[] r0 = f407a     // Catch:{ NoSuchFieldError -> 0x00aa }
                in.cashify.otex.ExchangeManager$h r1 = in.cashify.otex.ExchangeManager.h.MANUAL_WEB_PAGE     // Catch:{ NoSuchFieldError -> 0x00aa }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x00aa }
                r2 = 14
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x00aa }
            L_0x00aa:
                int[] r0 = f407a     // Catch:{ NoSuchFieldError -> 0x00b6 }
                in.cashify.otex.ExchangeManager$h r1 = in.cashify.otex.ExchangeManager.h.PROXIMITY_SENSOR     // Catch:{ NoSuchFieldError -> 0x00b6 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x00b6 }
                r2 = 15
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x00b6 }
            L_0x00b6:
                int[] r0 = f407a     // Catch:{ NoSuchFieldError -> 0x00c2 }
                in.cashify.otex.ExchangeManager$h r1 = in.cashify.otex.ExchangeManager.h.TELEPHONY_TEST     // Catch:{ NoSuchFieldError -> 0x00c2 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x00c2 }
                r2 = 16
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x00c2 }
            L_0x00c2:
                int[] r0 = f407a     // Catch:{ NoSuchFieldError -> 0x00ce }
                in.cashify.otex.ExchangeManager$h r1 = in.cashify.otex.ExchangeManager.h.VIBRATION     // Catch:{ NoSuchFieldError -> 0x00ce }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x00ce }
                r2 = 17
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x00ce }
            L_0x00ce:
                int[] r0 = f407a     // Catch:{ NoSuchFieldError -> 0x00da }
                in.cashify.otex.ExchangeManager$h r1 = in.cashify.otex.ExchangeManager.h.MANUAL_SINGLE_CHOICE     // Catch:{ NoSuchFieldError -> 0x00da }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x00da }
                r2 = 18
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x00da }
            L_0x00da:
                int[] r0 = f407a     // Catch:{ NoSuchFieldError -> 0x00e6 }
                in.cashify.otex.ExchangeManager$h r1 = in.cashify.otex.ExchangeManager.h.MANUAL_MULTI_CHOICE     // Catch:{ NoSuchFieldError -> 0x00e6 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x00e6 }
                r2 = 19
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x00e6 }
            L_0x00e6:
                int[] r0 = f407a     // Catch:{ NoSuchFieldError -> 0x00f2 }
                in.cashify.otex.ExchangeManager$h r1 = in.cashify.otex.ExchangeManager.h.MANUAL_DROPDOWN     // Catch:{ NoSuchFieldError -> 0x00f2 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x00f2 }
                r2 = 20
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x00f2 }
            L_0x00f2:
                int[] r0 = f407a     // Catch:{ NoSuchFieldError -> 0x00fe }
                in.cashify.otex.ExchangeManager$h r1 = in.cashify.otex.ExchangeManager.h.GPS     // Catch:{ NoSuchFieldError -> 0x00fe }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x00fe }
                r2 = 21
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x00fe }
            L_0x00fe:
                int[] r0 = f407a     // Catch:{ NoSuchFieldError -> 0x010a }
                in.cashify.otex.ExchangeManager$h r1 = in.cashify.otex.ExchangeManager.h.PROMPT_PAGE     // Catch:{ NoSuchFieldError -> 0x010a }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x010a }
                r2 = 22
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x010a }
            L_0x010a:
                int[] r0 = f407a     // Catch:{ NoSuchFieldError -> 0x0116 }
                in.cashify.otex.ExchangeManager$h r1 = in.cashify.otex.ExchangeManager.h.ZERO_QUOTE_PAGE     // Catch:{ NoSuchFieldError -> 0x0116 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0116 }
                r2 = 23
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0116 }
            L_0x0116:
                int[] r0 = f407a     // Catch:{ NoSuchFieldError -> 0x0122 }
                in.cashify.otex.ExchangeManager$h r1 = in.cashify.otex.ExchangeManager.h.TOUCH_CALIBRATION     // Catch:{ NoSuchFieldError -> 0x0122 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0122 }
                r2 = 24
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0122 }
            L_0x0122:
                int[] r0 = f407a     // Catch:{ NoSuchFieldError -> 0x012e }
                in.cashify.otex.ExchangeManager$h r1 = in.cashify.otex.ExchangeManager.h.DEAD_PIXEL     // Catch:{ NoSuchFieldError -> 0x012e }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x012e }
                r2 = 25
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x012e }
            L_0x012e:
                int[] r0 = f407a     // Catch:{ NoSuchFieldError -> 0x013a }
                in.cashify.otex.ExchangeManager$h r1 = in.cashify.otex.ExchangeManager.h.SENSOR_ACCELEROMETER     // Catch:{ NoSuchFieldError -> 0x013a }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x013a }
                r2 = 26
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x013a }
            L_0x013a:
                int[] r0 = f407a     // Catch:{ NoSuchFieldError -> 0x0146 }
                in.cashify.otex.ExchangeManager$h r1 = in.cashify.otex.ExchangeManager.h.SENSOR_COMPASS     // Catch:{ NoSuchFieldError -> 0x0146 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0146 }
                r2 = 27
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0146 }
            L_0x0146:
                int[] r0 = f407a     // Catch:{ NoSuchFieldError -> 0x0152 }
                in.cashify.otex.ExchangeManager$h r1 = in.cashify.otex.ExchangeManager.h.SENSOR_GYROSCOPE     // Catch:{ NoSuchFieldError -> 0x0152 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0152 }
                r2 = 28
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0152 }
            L_0x0152:
                int[] r0 = f407a     // Catch:{ NoSuchFieldError -> 0x015e }
                in.cashify.otex.ExchangeManager$h r1 = in.cashify.otex.ExchangeManager.h.BUTTON_BACK     // Catch:{ NoSuchFieldError -> 0x015e }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x015e }
                r2 = 29
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x015e }
            L_0x015e:
                int[] r0 = f407a     // Catch:{ NoSuchFieldError -> 0x016a }
                in.cashify.otex.ExchangeManager$h r1 = in.cashify.otex.ExchangeManager.h.BUTTON_HOME     // Catch:{ NoSuchFieldError -> 0x016a }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x016a }
                r2 = 30
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x016a }
            L_0x016a:
                int[] r0 = f407a     // Catch:{ NoSuchFieldError -> 0x0176 }
                in.cashify.otex.ExchangeManager$h r1 = in.cashify.otex.ExchangeManager.h.BUTTON_RECENT_APPS     // Catch:{ NoSuchFieldError -> 0x0176 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0176 }
                r2 = 31
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0176 }
            L_0x0176:
                int[] r0 = f407a     // Catch:{ NoSuchFieldError -> 0x0182 }
                in.cashify.otex.ExchangeManager$h r1 = in.cashify.otex.ExchangeManager.h.SIM_CARD     // Catch:{ NoSuchFieldError -> 0x0182 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0182 }
                r2 = 32
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0182 }
            L_0x0182:
                int[] r0 = f407a     // Catch:{ NoSuchFieldError -> 0x018e }
                in.cashify.otex.ExchangeManager$h r1 = in.cashify.otex.ExchangeManager.h.SPEAKER_RECEIVER     // Catch:{ NoSuchFieldError -> 0x018e }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x018e }
                r2 = 33
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x018e }
            L_0x018e:
                int[] r0 = f407a     // Catch:{ NoSuchFieldError -> 0x019a }
                in.cashify.otex.ExchangeManager$h r1 = in.cashify.otex.ExchangeManager.h.POWER_BUTTON     // Catch:{ NoSuchFieldError -> 0x019a }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x019a }
                r2 = 34
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x019a }
            L_0x019a:
                int[] r0 = f407a     // Catch:{ NoSuchFieldError -> 0x01a6 }
                in.cashify.otex.ExchangeManager$h r1 = in.cashify.otex.ExchangeManager.h.REMARK     // Catch:{ NoSuchFieldError -> 0x01a6 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x01a6 }
                r2 = 35
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x01a6 }
            L_0x01a6:
                int[] r0 = f407a     // Catch:{ NoSuchFieldError -> 0x01b2 }
                in.cashify.otex.ExchangeManager$h r1 = in.cashify.otex.ExchangeManager.h.FINGERPRINT     // Catch:{ NoSuchFieldError -> 0x01b2 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x01b2 }
                r2 = 36
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x01b2 }
            L_0x01b2:
                int[] r0 = f407a     // Catch:{ NoSuchFieldError -> 0x01be }
                in.cashify.otex.ExchangeManager$h r1 = in.cashify.otex.ExchangeManager.h.NONE     // Catch:{ NoSuchFieldError -> 0x01be }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x01be }
                r2 = 37
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x01be }
            L_0x01be:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: a.a.a.f.d.b.C0012b.<clinit>():void");
        }
    }

    public b(Parcel parcel) {
        this.f406a = parcel.createTypedArrayList(g.CREATOR);
    }

    public b(String str) {
        JSONArray jSONArray = new JSONObject(str).getJSONArray("tl");
        if (jSONArray == null || jSONArray.length() <= 0) {
            throw new InvalidObjectException("Invalid response");
        }
        a(jSONArray);
    }

    public List<g> a() {
        return this.f406a;
    }

    public final void a(JSONArray jSONArray) {
        g gVar;
        try {
            this.f406a = new ArrayList(jSONArray.length());
            for (int i = 0; i < jSONArray.length(); i++) {
                JSONObject jSONObject = (JSONObject) jSONArray.get(i);
                switch (C0012b.f407a[ExchangeManager.h.a(jSONObject.optString("tid")).ordinal()]) {
                    case 1:
                    case 2:
                    case 3:
                    case 14:
                    case 16:
                    case 18:
                    case 19:
                    case 20:
                    case 22:
                    case 23:
                    case 26:
                    case 27:
                    case 28:
                    case 29:
                    case 30:
                    case 31:
                    case 32:
                    case 33:
                    case 34:
                    case 35:
                        break;
                    case 4:
                        gVar = new p(jSONObject);
                        break;
                    case 5:
                        gVar = new c(jSONObject);
                        break;
                    case 6:
                        gVar = new j(jSONObject);
                        break;
                    case 7:
                        gVar = new d(jSONObject);
                        break;
                    case 8:
                        gVar = new d(jSONObject);
                        break;
                    case 9:
                        gVar = new a.a.a.e.c.b(jSONObject);
                        break;
                    case 10:
                        gVar = new l(jSONObject);
                        break;
                    case 11:
                        gVar = new o(jSONObject);
                        break;
                    case 12:
                        gVar = new e(jSONObject);
                        break;
                    case 13:
                        gVar = new a.a.a.e.c.a(jSONObject);
                        break;
                    case 15:
                        gVar = new k(jSONObject);
                        break;
                    case 17:
                        gVar = new n(jSONObject);
                        break;
                    case 21:
                        gVar = new h(jSONObject);
                        break;
                    case 24:
                        gVar = new m(jSONObject);
                        break;
                    case 25:
                        gVar = new f(jSONObject);
                        break;
                }
                gVar = null;
                if (gVar != null && gVar.e()) {
                    this.f406a.add(gVar);
                }
            }
        } catch (JSONException e) {
            this.f406a = null;
            throw e;
        }
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeTypedList(this.f406a);
    }
}
