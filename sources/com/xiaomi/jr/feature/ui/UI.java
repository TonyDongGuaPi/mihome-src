package com.xiaomi.jr.feature.ui;

import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Toast;
import com.google.gson.annotations.SerializedName;
import com.xiaomi.jr.hybrid.HybridFeature;
import com.xiaomi.jr.hybrid.HybridUtils;
import com.xiaomi.jr.hybrid.Request;
import com.xiaomi.jr.hybrid.Response;
import com.xiaomi.jr.hybrid.annotation.Action;
import com.xiaomi.jr.hybrid.annotation.Feature;
import com.xiaomi.jr.ui.menu.ActionBarWebContextMenu;

@Feature("UI")
public class UI extends HybridFeature {

    private static class ShowToastParam {
        @SerializedName("message")

        /* renamed from: a  reason: collision with root package name */
        String f10798a;
        @SerializedName("longDuration")
        boolean b;

        private ShowToastParam() {
        }
    }

    private static class SetTwoLineTitleParam {
        @SerializedName("title")

        /* renamed from: a  reason: collision with root package name */
        String f10797a;
        @SerializedName("subtitle")
        String b;

        private SetTwoLineTitleParam() {
        }
    }

    @Action(paramClazz = String.class)
    public Response setMenu(Request<String> request) {
        if (((Boolean) HybridUtils.a((Request) request, 2)).booleanValue()) {
            return new Response.RuntimeErrorResponse((Request) request, "setMenu on home page is forbidden!");
        }
        String a2 = ActionBarWebContextMenu.a(HybridUtils.b(request), request.c(), (ActionBarWebContextMenu.MenuEventListener) new ActionBarWebContextMenu.MenuEventListener() {
            public final void onMenuEvent(String str) {
                UI.lambda$setMenu$0(Request.this, str);
            }
        });
        return a2 != null ? new Response.RuntimeErrorResponse((Request) request, a2) : Response.j;
    }

    static /* synthetic */ void lambda$setMenu$0(Request request, String str) {
        if (!TextUtils.isEmpty(str)) {
            HybridUtils.a(request, new Response(str));
        }
    }

    @Action(paramClazz = String.class)
    public Response setTitle(Request<String> request) {
        performSetTitle(request, request.c(), (String) null);
        return Response.j;
    }

    @Action(paramClazz = SetTwoLineTitleParam.class)
    public Response setTwoLineTitle(Request<SetTwoLineTitleParam> request) {
        performSetTitle(request, request.c().f10797a, request.c().b);
        return Response.j;
    }

    private void performSetTitle(Request request, String str, String str2) {
        Bundle bundle = new Bundle();
        bundle.putString("title", str);
        bundle.putString("subtitle", str2);
        HybridUtils.a(request, 15, bundle);
    }

    @Action(paramClazz = ShowToastParam.class)
    public Response showToast(Request<ShowToastParam> request) {
        Toast.makeText(HybridUtils.a((Request) request), request.c().f10798a, request.c().b ? 1 : 0).show();
        return Response.j;
    }

    @Action(paramClazz = String.class)
    public Response showSafeKeyboard(Request<String> request) {
        HybridUtils.a(request, 20, request.c());
        return Response.j;
    }

    @Action
    public Response hideSafeKeyboard(Request request) {
        HybridUtils.a(request, 21, (Object) null);
        return Response.j;
    }

    @Action(paramClazz = Boolean.class)
    public Response disableGoBack(Request<Boolean> request) {
        HybridUtils.a(request, 1, request.c());
        return Response.j;
    }

    @Action(paramClazz = Boolean.class)
    public Response requestInterceptBackKeyEvent(Request<Boolean> request) {
        HybridUtils.a(request, 6, request.c());
        return Response.j;
    }

    @Action(paramClazz = Boolean.class)
    public Response requestInterceptBackAndHomeKeyEvent(Request<Boolean> request) {
        HybridUtils.a(request, 16, request.c());
        return Response.j;
    }

    @Action(paramClazz = Boolean.class)
    public Response requestInterceptTouchEvent(Request<Boolean> request) {
        HybridUtils.a(request, 7, request.c());
        return Response.j;
    }

    @Action(paramClazz = Boolean.class)
    public Response startShowLoading(Request<Boolean> request) {
        HybridUtils.a(request, 8, request.c());
        return Response.j;
    }

    @Action
    public Response stopShowLoading(Request request) {
        HybridUtils.a(request, 9, request.c());
        return Response.j;
    }
}
