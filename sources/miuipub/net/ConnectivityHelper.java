package miuipub.net;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import com.miuipub.internal.util.PackageConstants;
import miuipub.util.SoftReferenceSingleton;

public class ConnectivityHelper {

    /* renamed from: a  reason: collision with root package name */
    private static final SoftReferenceSingleton<ConnectivityHelper> f2971a = new SoftReferenceSingleton<ConnectivityHelper>() {
        /* access modifiers changed from: protected */
        /* renamed from: a */
        public ConnectivityHelper createInstance() {
            return new ConnectivityHelper();
        }
    };
    private static final String b = "ConnectivityHelper";
    private Context c;
    private ConnectivityManager d;
    private WifiManager e;
    private String f;

    public static ConnectivityHelper a() {
        return f2971a.get();
    }

    private ConnectivityHelper() {
        this.c = PackageConstants.a();
        this.d = (ConnectivityManager) this.c.getSystemService("connectivity");
    }

    public ConnectivityManager b() {
        return this.d;
    }

    public boolean c() {
        NetworkInfo activeNetworkInfo = this.d.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

    public boolean d() {
        NetworkInfo activeNetworkInfo = this.d.getActiveNetworkInfo();
        if (activeNetworkInfo == null || !activeNetworkInfo.isConnected() || activeNetworkInfo.getType() != 1) {
            return false;
        }
        return true;
    }

    public boolean e() {
        NetworkInfo activeNetworkInfo = this.d.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected() && !this.d.isActiveNetworkMetered();
    }

    public String f() {
        if (this.f != null) {
            return this.f;
        }
        if (this.f == null) {
            if (this.e == null) {
                this.e = (WifiManager) this.c.getSystemService("wifi");
            }
            WifiInfo connectionInfo = this.e.getConnectionInfo();
            if (connectionInfo != null) {
                this.f = connectionInfo.getMacAddress();
            }
        }
        return this.f;
    }
}
