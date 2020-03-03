package com.amap.api.services.a;

import android.content.Context;
import com.amap.api.services.core.AMapException;
import com.amap.api.services.routepoisearch.RoutePOIItem;
import com.amap.api.services.routepoisearch.RoutePOISearchQuery;
import com.amap.api.services.routepoisearch.RoutePOISearchResult;
import com.unionpay.tsmservice.request.GetTransElementsRequestParams;
import com.xiaomi.smarthome.plugin.DeviceConstant;
import java.util.ArrayList;
import org.json.JSONException;
import org.json.JSONObject;

public class ap extends k<RoutePOISearchQuery, RoutePOISearchResult> {
    public ap(Context context, RoutePOISearchQuery routePOISearchQuery) {
        super(context, routePOISearchQuery);
    }

    /* access modifiers changed from: protected */
    public String g() {
        String str;
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("key=");
        stringBuffer.append(bp.f(this.d));
        stringBuffer.append("&range=");
        stringBuffer.append("" + ((RoutePOISearchQuery) this.f4426a).getRange());
        String str2 = "";
        try {
            switch (((RoutePOISearchQuery) this.f4426a).getSearchType()) {
                case TypeGasStation:
                    str = GetTransElementsRequestParams.TRANS_TYPE_DOWNLOAD_APPLY;
                    break;
                case TypeMaintenanceStation:
                    str = DeviceConstant.IPC009_FULL_ENCRYPTION_SUPPORT_VERSION_POSTFIX;
                    break;
                case TypeATM:
                    str = "1603";
                    break;
                case TypeToilet:
                    str = "2003";
                    break;
                case TypeFillingStation:
                    str = "0103";
                    break;
                case TypeServiceArea:
                    str = "180301";
                    break;
            }
            str2 = str;
        } catch (Exception unused) {
        }
        if (((RoutePOISearchQuery) this.f4426a).getPolylines() == null || ((RoutePOISearchQuery) this.f4426a).getPolylines().size() <= 0) {
            stringBuffer.append("&origin=");
            stringBuffer.append(s.a(((RoutePOISearchQuery) this.f4426a).getFrom()));
            stringBuffer.append("&destination=");
            stringBuffer.append(s.a(((RoutePOISearchQuery) this.f4426a).getTo()));
            stringBuffer.append("&strategy=");
            stringBuffer.append("" + ((RoutePOISearchQuery) this.f4426a).getMode());
        } else {
            stringBuffer.append("&polyline=");
            stringBuffer.append(s.a(((RoutePOISearchQuery) this.f4426a).getPolylines()));
        }
        stringBuffer.append("&types=");
        stringBuffer.append(str2);
        return stringBuffer.toString();
    }

    /* access modifiers changed from: protected */
    /* renamed from: e */
    public RoutePOISearchResult a(String str) throws AMapException {
        ArrayList<RoutePOIItem> arrayList;
        ArrayList<RoutePOIItem> arrayList2 = new ArrayList<>();
        try {
            arrayList = z.w(new JSONObject(str));
        } catch (JSONException e) {
            e.printStackTrace();
            arrayList = arrayList2;
        }
        return new RoutePOISearchResult(arrayList, (RoutePOISearchQuery) this.f4426a);
    }

    public String i() {
        return r.a() + "/place/route?";
    }
}
