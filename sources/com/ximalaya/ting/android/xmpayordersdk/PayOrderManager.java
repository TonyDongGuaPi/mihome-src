package com.ximalaya.ting.android.xmpayordersdk;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import com.google.android.exoplayer2.C;
import com.ximalaya.ting.android.opensdk.constants.DTransferConstants;
import com.ximalaya.ting.android.opensdk.datatrasfer.AccessTokenManager;
import com.ximalaya.ting.android.opensdk.datatrasfer.CommonRequest;
import com.ximalaya.ting.android.opensdk.datatrasfer.IDataCallBack;
import com.ximalaya.ting.android.opensdk.datatrasfer.XimalayaResponse;
import com.ximalaya.ting.android.opensdk.model.pay.PayInfo;
import com.ximalaya.ting.android.opensdk.model.pay.PayOderStatue;
import com.ximalaya.ting.android.opensdk.util.SharedPreferencesUtil;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import org.json.JSONException;
import org.json.JSONObject;

public class PayOrderManager {

    /* renamed from: a  reason: collision with root package name */
    public static String f2380a = "h5";
    public static Activity b;
    protected static a c;

    public interface a {

        /* renamed from: a  reason: collision with root package name */
        public static final String f2388a = "onCreate";
        public static final String b = "onStart";
        public static final String c = "onResume";
        public static final String d = "onPause";
        public static final String e = "onStop";
        public static final String f = "onDestory";
        public static final String g = "onRestart";

        void a();
    }

    public static void a(String str, final Context context, final IXmPayOrderListener iXmPayOrderListener) {
        HashMap hashMap = new HashMap();
        hashMap.put("xima_order_no", str);
        hashMap.put("invoke_page_type", f2380a);
        CommonRequest.a(DTransferConstants.dr, hashMap, new IDataCallBack<XimalayaResponse>() {
            private static void a() {
            }

            public final /* bridge */ /* synthetic */ void a(Object obj) {
            }

            public final void a(int i, String str) {
                if (iXmPayOrderListener != null) {
                    iXmPayOrderListener.a(PayFinishModel.a(i, str));
                }
            }
        }, new CommonRequest.IRequestCallBack<XimalayaResponse>() {
            private XimalayaResponse a(String str) throws Exception {
                PayOrderManager.b(str, iXmPayOrderListener, context);
                return null;
            }

            public final /* synthetic */ Object b(String str) throws Exception {
                PayOrderManager.b(str, iXmPayOrderListener, context);
                return null;
            }
        });
    }

    public static void a(final long j, @NonNull Context context, IXmPayOrderListener iXmPayOrderListener) {
        b(j, new ArrayList<Long>() {
            {
                add(Long.valueOf(j));
            }
        }, context, iXmPayOrderListener);
    }

    public static void a(long j, List<Long> list, @NonNull Context context, IXmPayOrderListener iXmPayOrderListener) {
        b(j, list, context, iXmPayOrderListener);
    }

    private static void b(long j, final List<Long> list, @NonNull final Context context, final IXmPayOrderListener iXmPayOrderListener) {
        if (context != null && iXmPayOrderListener != null) {
            HashMap hashMap = new HashMap();
            hashMap.put(DTransferConstants.C, String.valueOf(j));
            CommonRequest.aS(hashMap, new IDataCallBack<PayInfo>() {
                public final /* synthetic */ void a(Object obj) {
                    PayInfo payInfo = (PayInfo) obj;
                    if (payInfo != null && payInfo.b() != null && payInfo.b().get(0) != null && payInfo.b().get(0).d() > 0.0f) {
                        HashMap hashMap = new HashMap();
                        hashMap.put("invoke_page_type", PayOrderManager.f2380a);
                        StringBuilder sb = new StringBuilder();
                        sb.append(payInfo.a());
                        hashMap.put("price_type", sb.toString());
                        hashMap.put("pay_content", PayOrderManager.a((Collection) list));
                        PayInfo.PriceTypeDetailBean priceTypeDetailBean = payInfo.b().get(0);
                        StringBuilder sb2 = new StringBuilder();
                        sb2.append(priceTypeDetailBean.d() <= 0.0f ? priceTypeDetailBean.b() : priceTypeDetailBean.d());
                        hashMap.put("price", sb2.toString());
                        CommonRequest.b(DTransferConstants.dq, hashMap, new IDataCallBack<XimalayaResponse>() {
                            private static void a() {
                            }

                            public final /* bridge */ /* synthetic */ void a(Object obj) {
                            }

                            public final void a(int i, String str) {
                                if (iXmPayOrderListener != null) {
                                    iXmPayOrderListener.a(PayFinishModel.a(i, str));
                                }
                            }
                        }, new CommonRequest.IRequestCallBack<XimalayaResponse>() {
                            private XimalayaResponse a(String str) throws Exception {
                                PayOrderManager.b(str, iXmPayOrderListener, context);
                                return null;
                            }

                            public final /* synthetic */ Object b(String str) throws Exception {
                                PayOrderManager.b(str, iXmPayOrderListener, context);
                                return null;
                            }
                        });
                    } else if (iXmPayOrderListener != null) {
                        iXmPayOrderListener.a(PayFinishModel.b(1015));
                    }
                }

                private void a(PayInfo payInfo) {
                    if (payInfo != null && payInfo.b() != null && payInfo.b().get(0) != null && payInfo.b().get(0).d() > 0.0f) {
                        HashMap hashMap = new HashMap();
                        hashMap.put("invoke_page_type", PayOrderManager.f2380a);
                        StringBuilder sb = new StringBuilder();
                        sb.append(payInfo.a());
                        hashMap.put("price_type", sb.toString());
                        hashMap.put("pay_content", PayOrderManager.a((Collection) list));
                        PayInfo.PriceTypeDetailBean priceTypeDetailBean = payInfo.b().get(0);
                        StringBuilder sb2 = new StringBuilder();
                        sb2.append(priceTypeDetailBean.d() <= 0.0f ? priceTypeDetailBean.b() : priceTypeDetailBean.d());
                        hashMap.put("price", sb2.toString());
                        CommonRequest.b(DTransferConstants.dq, hashMap, new IDataCallBack<XimalayaResponse>() {
                            private static void a() {
                            }

                            public final /* bridge */ /* synthetic */ void a(Object obj) {
                            }

                            public final void a(int i, String str) {
                                if (iXmPayOrderListener != null) {
                                    iXmPayOrderListener.a(PayFinishModel.a(i, str));
                                }
                            }
                        }, new CommonRequest.IRequestCallBack<XimalayaResponse>() {
                            private XimalayaResponse a(String str) throws Exception {
                                PayOrderManager.b(str, iXmPayOrderListener, context);
                                return null;
                            }

                            public final /* synthetic */ Object b(String str) throws Exception {
                                PayOrderManager.b(str, iXmPayOrderListener, context);
                                return null;
                            }
                        });
                    } else if (iXmPayOrderListener != null) {
                        iXmPayOrderListener.a(PayFinishModel.b(1015));
                    }
                }

                public final void a(int i, String str) {
                    if (iXmPayOrderListener != null) {
                        iXmPayOrderListener.a(PayFinishModel.a(i, str));
                    }
                }
            });
        }
    }

    /* access modifiers changed from: private */
    public static void b(String str, IXmPayOrderListener iXmPayOrderListener, @NonNull Context context) {
        String str2;
        try {
            JSONObject jSONObject = new JSONObject(str);
            String str3 = null;
            if (jSONObject.has("order_settlement_url")) {
                str2 = jSONObject.optString("order_settlement_url");
            } else if (!jSONObject.has("error_no")) {
                str2 = null;
            } else if (iXmPayOrderListener != null) {
                iXmPayOrderListener.a(PayFinishModel.a(jSONObject.optInt("error_no"), jSONObject.optString("error_desc")));
                return;
            } else {
                return;
            }
            if (jSONObject.has("order_no")) {
                str3 = jSONObject.optString("order_no");
            }
            if (TextUtils.isEmpty(str2)) {
                if (iXmPayOrderListener != null) {
                    iXmPayOrderListener.a(PayFinishModel.b(102));
                }
            } else if (TextUtils.isEmpty(AccessTokenManager.a().e())) {
                if (iXmPayOrderListener != null) {
                    iXmPayOrderListener.a(PayFinishModel.b(4));
                }
                if (CommonRequest.a().b() != null) {
                    CommonRequest.a().b().c();
                }
            } else {
                Intent intent = new Intent(context, XmPayOrderActivity.class);
                intent.putExtra(XmPayOrderActivity.ORDER_URL, str2);
                String a2 = a();
                intent.putExtra(XmPayOrderActivity.ORDER_NUM, a2);
                intent.putExtra(XmPayOrderActivity.PAY_ORDER_NO, str3);
                XmPayOrderCallBackManager.a().a(a2, iXmPayOrderListener);
                intent.setFlags(C.ENCODING_PCM_MU_LAW);
                context.startActivity(intent);
            }
        } catch (JSONException e) {
            e.printStackTrace();
            if (iXmPayOrderListener != null) {
                iXmPayOrderListener.a(PayFinishModel.b(102));
            }
        }
    }

    public static String a() {
        return String.valueOf(System.currentTimeMillis());
    }

    public static String a(Collection collection) {
        String str = "";
        if (collection == null || collection.size() <= 0) {
            return str;
        }
        Iterator it = collection.iterator();
        int i = 0;
        while (it.hasNext()) {
            if (i == collection.size() - 1) {
                return str + it.next();
            }
            str = str + it.next() + ",";
            i++;
        }
        return str;
    }

    public static void a(a aVar) {
        c = aVar;
    }

    public static void b() {
        if (b != null && !b.isFinishing()) {
            b.finish();
        }
    }

    public static boolean c() {
        return b != null;
    }

    public static void a(final Context context) {
        ArrayList<String> k = SharedPreferencesUtil.a(context).k(DTransferConstants.aU);
        if (k != null) {
            for (int i = 0; i < k.size(); i++) {
                final String str = k.get(i);
                if (!TextUtils.isEmpty(str)) {
                    HashMap hashMap = new HashMap();
                    hashMap.put("xima_order_no", str);
                    CommonRequest.aZ(hashMap, new IDataCallBack<PayOderStatue>() {
                        public final void a(int i, String str) {
                        }

                        private void a() {
                            PayOrderManager.a(context, str);
                        }

                        public final /* synthetic */ void a(Object obj) {
                            PayOrderManager.a(context, str);
                        }
                    });
                }
            }
        }
    }

    @Nullable
    public static List<String> b(Context context) {
        return SharedPreferencesUtil.a(context).k(DTransferConstants.aU);
    }

    public static void a(Context context, String str) {
        ArrayList<String> k = SharedPreferencesUtil.a(context).k(DTransferConstants.aU);
        if (k != null && k.remove(str)) {
            SharedPreferencesUtil.a(context).a(DTransferConstants.aU, k);
        }
    }
}
