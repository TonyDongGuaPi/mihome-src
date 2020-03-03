package com.mibi.common.hybrid;

import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.util.Log;
import com.mibi.common.data.CommonConstants;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import miuipub.hybrid.HybridResourceResponse;
import miuipub.hybrid.HybridView;
import miuipub.hybrid.HybridViewClient;

class MipayHybridViewClient extends HybridViewClient {

    /* renamed from: a  reason: collision with root package name */
    private static final String f7582a = "MibiHybrid";
    private static final String b = "https://static.pay.xiaomi.com/scriptlib/";
    private static final List<String> c = new ArrayList();
    private MipayHybridLoginHelper d = new MipayHybridLoginHelper();
    private final String e;
    private final String f;

    static {
        c.add("react@15/react.min.js");
        c.add("react-dom@15/react-dom.min.js");
        c.add("react-router-dom@4/react-router-dom.min.js");
    }

    MipayHybridViewClient(String str, String str2) {
        this.e = str;
        this.f = str2;
    }

    public void a(HybridView hybridView, String str, String str2, String str3) {
        if (CommonConstants.b) {
            Log.d("MibiHybrid", "onReceivedLoginRequest");
        }
        this.d.a(hybridView, this.f, this.e, str3);
    }

    public void b(HybridView hybridView, String str) {
        super.b(hybridView, str);
        if (CommonConstants.b) {
            Log.d("MibiHybrid", "onPageFinished" + str);
        }
    }

    public void a(HybridView hybridView, String str, Bitmap bitmap) {
        super.a(hybridView, str, bitmap);
        if (CommonConstants.b) {
            Log.d("MibiHybrid", "onPageStarted" + str);
        }
    }

    public HybridResourceResponse c(HybridView hybridView, String str) {
        if (str.startsWith(b)) {
            String substring = str.substring(b.length());
            if (c.contains(substring)) {
                try {
                    AssetManager assets = hybridView.getContext().getAssets();
                    return new HybridResourceResponse((String) null, (String) null, assets.open("hybrid/" + substring));
                } catch (IOException unused) {
                    return null;
                }
            }
        }
        return super.c(hybridView, str);
    }
}
