package com.ximalaya.ting.android.opensdk.auth.utils;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.os.Looper;
import com.ximalaya.ting.android.opensdk.auth.model.XmlyAuth2AccessToken;
import com.ximalaya.ting.android.opensdk.constants.DTransferConstants;
import com.ximalaya.ting.android.opensdk.datatrasfer.CommonRequest;
import com.ximalaya.ting.android.opensdk.datatrasfer.IDataCallBack;
import com.ximalaya.ting.android.opensdk.httputil.BaseBuilder;
import com.ximalaya.ting.android.opensdk.httputil.BaseCall;
import com.ximalaya.ting.android.opensdk.httputil.IHttpCallBack;
import com.ximalaya.ting.android.opensdk.httputil.XimalayaException;
import java.util.Map;
import okhttp3.Response;

public class QrcodeLoginUtil {

    public interface IGenerateCallBack {
        void a(int i, String str);

        void a(Bitmap bitmap, String str);
    }

    public static void a(Map<String, String> map, final IGenerateCallBack iGenerateCallBack) {
        StringBuilder sb = new StringBuilder();
        sb.append(System.currentTimeMillis());
        map.put("timestamp", sb.toString());
        StringBuilder sb2 = new StringBuilder();
        sb2.append(System.currentTimeMillis());
        map.put("nonce", sb2.toString());
        try {
            map.put("client_id", CommonRequest.a().f());
        } catch (XimalayaException e) {
            e.printStackTrace();
        }
        try {
            BaseCall.a().a(BaseBuilder.a(CommonRequest.d(DTransferConstants.cR), CommonRequest.a(map), CommonRequest.a().e()).build(), (IHttpCallBack) new IHttpCallBack() {
                public final void a(final Response response) {
                    final Bitmap decodeStream = BitmapFactory.decodeStream(response.body().byteStream());
                    new Handler(Looper.getMainLooper()).post(new Runnable() {
                        public void run() {
                            if (iGenerateCallBack != null) {
                                iGenerateCallBack.a(decodeStream, response.header("qrcode_id"));
                            }
                        }
                    });
                }

                public final void a(final int i, final String str) {
                    new Handler(Looper.getMainLooper()).post(new Runnable() {
                        public void run() {
                            if (iGenerateCallBack != null) {
                                iGenerateCallBack.a(i, str);
                            }
                        }
                    });
                }
            });
        } catch (XimalayaException e2) {
            if (iGenerateCallBack != null) {
                iGenerateCallBack.a(e2.getErrorCode(), e2.getMessage());
            }
        }
    }

    public static void a(Map<String, String> map, IDataCallBack<XmlyAuth2AccessToken> iDataCallBack) {
        StringBuilder sb = new StringBuilder();
        sb.append(System.currentTimeMillis());
        map.put("timestamp", sb.toString());
        StringBuilder sb2 = new StringBuilder();
        sb2.append(System.currentTimeMillis());
        map.put("nonce", sb2.toString());
        map.put("response_type", "code");
        try {
            map.put("client_id", CommonRequest.a().f());
        } catch (XimalayaException e) {
            e.printStackTrace();
        }
        CommonRequest.b(DTransferConstants.cS, map, iDataCallBack, new CommonRequest.IRequestCallBack<XmlyAuth2AccessToken>() {
            /* renamed from: a */
            public final XmlyAuth2AccessToken b(String str) throws Exception {
                return XmlyAuth2AccessToken.a(str);
            }
        });
    }
}
