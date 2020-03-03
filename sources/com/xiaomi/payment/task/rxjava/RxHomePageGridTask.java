package com.xiaomi.payment.task.rxjava;

import android.content.Context;
import com.mibi.common.data.Client;
import com.mibi.common.data.CommonConstants;
import com.mibi.common.data.Connection;
import com.mibi.common.data.ConnectionFactory;
import com.mibi.common.data.Session;
import com.mibi.common.data.SortedParameter;
import com.mibi.common.data.Utils;
import com.mibi.common.exception.PaymentException;
import com.mibi.common.exception.ResultException;
import com.mibi.common.rxjava.RxBasePaymentTask;
import com.xiaomi.payment.data.EntryData;
import com.xiaomi.payment.data.MibiConstants;
import com.xiaomi.payment.data.UnevenGridData;
import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class RxHomePageGridTask extends RxBasePaymentTask<Result> {

    public static class Result {

        /* renamed from: a  reason: collision with root package name */
        public UnevenGridData f12421a;
        public ArrayList<DiscountsItemData> b = new ArrayList<>();
        public ArrayList<GuideItemType> c = new ArrayList<>();

        public static class DiscountsItemData {

            /* renamed from: a  reason: collision with root package name */
            public String f12422a = "";
            public EntryData b;
        }

        public static class GuideItemType {

            /* renamed from: a  reason: collision with root package name */
            public String f12423a = "";
            public String b = "";
            public String c = "";
            public EntryData d;
        }
    }

    public RxHomePageGridTask(Context context, Session session) {
        super(context, session, Result.class);
        a(true);
    }

    /* access modifiers changed from: protected */
    public Connection b(SortedParameter sortedParameter) {
        Connection a2 = ConnectionFactory.a(MibiConstants.a(MibiConstants.bA), this.f7587a);
        SortedParameter d = a2.d();
        d.a("os", (Object) Client.p());
        d.a("package", (Object) Client.F().e());
        d.a("platform", (Object) Client.q());
        d.a("miuiVersion", (Object) Client.m());
        d.a("miuiUiVersion", (Object) Client.w());
        d.a("miuiUiVersionCode", (Object) Integer.valueOf(Client.x()));
        d.a(CommonConstants.aX, (Object) Boolean.valueOf(Utils.b()));
        a2.a(true);
        return a2;
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public void c(JSONObject jSONObject, Result result) throws PaymentException {
        JSONObject optJSONObject = jSONObject.optJSONObject(MibiConstants.ea);
        if (optJSONObject != null) {
            b(optJSONObject, result);
        }
        JSONObject optJSONObject2 = jSONObject.optJSONObject(MibiConstants.eb);
        if (optJSONObject2 != null) {
            c(optJSONObject2, result);
        }
        try {
            JSONObject jSONObject2 = jSONObject.getJSONObject(MibiConstants.ec);
            result.f12421a = new UnevenGridData();
            result.f12421a.a(jSONObject2);
        } catch (JSONException e) {
            throw new ResultException((Throwable) e);
        }
    }

    private void b(JSONObject jSONObject, Result result) throws PaymentException {
        try {
            JSONArray jSONArray = jSONObject.getJSONArray("data");
            for (int i = 0; i < jSONArray.length(); i++) {
                JSONObject jSONObject2 = jSONArray.getJSONObject(i);
                String string = jSONObject2.getString("image");
                Result.DiscountsItemData discountsItemData = new Result.DiscountsItemData();
                JSONObject jSONObject3 = jSONObject2.getJSONObject("entry");
                discountsItemData.b = new EntryData();
                discountsItemData.b.parseEntryData(jSONObject3);
                discountsItemData.f12422a = string;
                result.b.add(discountsItemData);
            }
        } catch (JSONException e) {
            throw new ResultException((Throwable) e);
        }
    }

    private void c(JSONObject jSONObject, Result result) throws PaymentException {
        try {
            JSONArray jSONArray = jSONObject.getJSONArray(MibiConstants.ej);
            for (int i = 0; i < jSONArray.length(); i++) {
                JSONObject jSONObject2 = jSONArray.getJSONObject(i);
                String string = jSONObject2.getString("image");
                String string2 = jSONObject2.getString("title");
                String string3 = jSONObject2.getString(MibiConstants.ee);
                Result.GuideItemType guideItemType = new Result.GuideItemType();
                JSONObject jSONObject3 = jSONObject2.getJSONObject("entry");
                guideItemType.d = new EntryData();
                guideItemType.d.parseEntryData(jSONObject3);
                guideItemType.f12423a = string;
                guideItemType.b = string2;
                guideItemType.c = string3;
                result.c.add(guideItemType);
            }
        } catch (JSONException e) {
            throw new ResultException((Throwable) e);
        }
    }
}
