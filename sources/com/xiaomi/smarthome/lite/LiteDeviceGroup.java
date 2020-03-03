package com.xiaomi.smarthome.lite;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;
import com.xiaomi.smarthome.application.SHApplication;
import com.xiaomi.smarthome.device.Device;
import com.xiaomi.smarthome.device.DeviceFactory;
import java.util.ArrayList;
import java.util.Iterator;

public class LiteDeviceGroup extends LiteDeviceAbstract {

    /* renamed from: a  reason: collision with root package name */
    static final int f19356a = 300;
    static final int b = 10;
    public ArrayList<LiteDevice> c = new ArrayList<>();
    public String d;
    boolean e = true;
    Bitmap f = Bitmap.createBitmap(300, 300, Bitmap.Config.ARGB_8888);
    Canvas l;
    Paint m = new Paint();
    IUpdateListener n;

    public interface IUpdateListener {
        void a();
    }

    public int b() {
        return 1;
    }

    public LiteDeviceGroup() {
        this.m.setAntiAlias(true);
        this.l = new Canvas(this.f);
    }

    public void a(IUpdateListener iUpdateListener) {
        this.n = iUpdateListener;
    }

    public int hashCode() {
        Iterator<LiteDevice> it = this.c.iterator();
        int i = 0;
        while (it.hasNext()) {
            i += it.next().f19353a.did.hashCode();
        }
        return i;
    }

    public class BitmapLoad implements Target {

        /* renamed from: a  reason: collision with root package name */
        int f19357a;
        Device b;

        public BitmapLoad(int i, Device device) {
            this.f19357a = i;
            this.b = device;
        }

        public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom loadedFrom) {
            if (a() && bitmap != null) {
                LiteDeviceGroup.this.l.drawBitmap(bitmap, new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight()), b(), LiteDeviceGroup.this.m);
                if (LiteDeviceGroup.this.n != null) {
                    LiteDeviceGroup.this.n.a();
                }
            }
        }

        public void onBitmapFailed(Exception exc, Drawable drawable) {
            if (a()) {
            }
        }

        public void onPrepareLoad(Drawable drawable) {
            if (a()) {
                if (drawable != null) {
                    drawable.setBounds(b());
                    drawable.draw(LiteDeviceGroup.this.l);
                }
                if (LiteDeviceGroup.this.n != null) {
                    LiteDeviceGroup.this.n.a();
                }
            }
        }

        /* access modifiers changed from: package-private */
        public boolean a() {
            if (this.f19357a < 0 || this.f19357a >= LiteDeviceGroup.this.c.size()) {
                return false;
            }
            return LiteDeviceGroup.this.c.get(this.f19357a).f19353a.did.equals(this.b.did);
        }

        /* access modifiers changed from: package-private */
        public Rect b() {
            int i = (this.f19357a % 3) * 100;
            int i2 = (this.f19357a / 3) * 100;
            Rect rect = new Rect(i, i2, i + 100, i2 + 100);
            rect.inset(10, 10);
            return rect;
        }
    }

    public void a() {
        this.l.drawRGB(255, 255, 255);
        for (int i = 0; i < this.c.size(); i++) {
            Device device = this.c.get(i).f19353a;
            DeviceFactory.a(SHApplication.getAppContext(), device, (Target) new BitmapLoad(i, device), 0);
        }
        this.e = false;
    }

    public Bitmap c() {
        if (this.e) {
            a();
            this.e = false;
        }
        return this.f;
    }

    public void a(LiteDevice liteDevice) {
        if (a(liteDevice.f19353a.did) == null) {
            this.c.add(liteDevice);
            this.e = true;
        }
    }

    public LiteDevice a(String str) {
        Iterator<LiteDevice> it = this.c.iterator();
        while (it.hasNext()) {
            LiteDevice next = it.next();
            if (next.f19353a.did.equals(str)) {
                return next;
            }
        }
        return null;
    }

    public void b(LiteDevice liteDevice) {
        LiteDevice a2 = a(liteDevice.f19353a.did);
        if (a2 != null) {
            this.c.remove(a2);
            this.e = true;
        }
    }
}
