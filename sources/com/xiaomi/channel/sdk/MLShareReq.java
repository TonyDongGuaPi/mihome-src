package com.xiaomi.channel.sdk;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import java.io.File;

public class MLShareReq implements IShareReq {

    /* renamed from: a  reason: collision with root package name */
    private int f10071a;
    private MLShareMessage b;
    private final int c;
    private MLExtraInfo d;
    private int e;

    @Deprecated
    public MLShareReq(int i, MLShareMessage mLShareMessage, int i2) {
        this(mLShareMessage, i2);
    }

    public MLShareReq(MLShareMessage mLShareMessage, int i) {
        this.f10071a = 0;
        this.e = 0;
        this.b = mLShareMessage;
        this.c = i;
    }

    public void a(MLExtraInfo mLExtraInfo) {
        this.d = mLExtraInfo;
    }

    public MLExtraInfo b() {
        return this.d;
    }

    public Bundle a() {
        boolean z;
        boolean z2;
        Bundle bundle = new Bundle();
        bundle.putInt(ShareConstants.n, this.c);
        if (this.b != null) {
            boolean z3 = true;
            if (!TextUtils.isEmpty(this.b.c)) {
                bundle.putString("share_title", this.b.c);
                z = true;
            } else {
                z = false;
            }
            if (!TextUtils.isEmpty(this.b.b)) {
                bundle.putString(ShareConstants.f, this.b.b);
                z = true;
            }
            if (!TextUtils.isEmpty(this.b.f10070a)) {
                bundle.putString("share_url", this.b.f10070a);
                z2 = true;
            } else {
                z2 = false;
            }
            if (!TextUtils.isEmpty(this.b.g)) {
                bundle.putString(ShareConstants.p, this.b.g);
            }
            if (!TextUtils.isEmpty(this.b.e)) {
                bundle.putString(ShareConstants.l, this.b.e);
            } else {
                if (this.b.d != null) {
                    Bitmap a2 = this.b.d.a();
                    if (a2 != null) {
                        File a3 = ShareUtils.a();
                        if (a3 != null) {
                            if (a3.exists()) {
                                a3.delete();
                            }
                            String absolutePath = a3.getAbsolutePath();
                            if (ShareUtils.a(a2, absolutePath)) {
                                Log.d("MLShare", "MLShare shareFilePath:" + absolutePath);
                                bundle.putString(ShareConstants.j, absolutePath);
                            } else {
                                bundle.putParcelable(ShareConstants.i, a2);
                            }
                        } else {
                            bundle.putParcelable(ShareConstants.i, a2);
                        }
                    }
                } else if (!TextUtils.isEmpty(this.b.f)) {
                    bundle.putString(ShareConstants.j, this.b.f);
                }
                z3 = false;
            }
            if (z && !z3 && !z2) {
                this.f10071a = ShareConstants.E;
            } else if (z3 && !z && !z2) {
                this.f10071a = ShareConstants.F;
            } else if ((!z3 || !z) && ((!z3 || !z2) && (!z || !z2))) {
                return null;
            } else {
                this.f10071a = ShareConstants.I;
            }
            if (!TextUtils.isEmpty(this.b.h)) {
                bundle.putString(ShareConstants.q, this.b.f);
            } else if (!TextUtils.isEmpty(this.b.i)) {
                bundle.putString(ShareConstants.r, this.b.e);
            }
            bundle.putInt("share_type", this.f10071a);
            if (this.e > 0) {
                bundle.putInt(ShareConstants.o, this.e);
            }
        }
        return bundle;
    }

    public void a(int i) {
        this.e = i;
    }

    public void a(Bundle bundle) {
        if (bundle != null) {
            this.f10071a = bundle.getInt("share_type");
            String string = bundle.getString("share_title");
            this.b = new MLShareMessage();
            this.b.c = string;
            if (bundle.containsKey(ShareConstants.f)) {
                this.b.b = bundle.getString(ShareConstants.f);
            }
            if (bundle.containsKey("share_url")) {
                this.b.f10070a = bundle.getString("share_url");
            }
            if (bundle.containsKey(ShareConstants.i)) {
                this.b.d = new MLImgObj((Bitmap) bundle.getParcelable(ShareConstants.i));
            }
        }
    }
}
