package com.alibaba.android.bindingx.core;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import com.alibaba.android.bindingx.core.internal.BindingXConstants;
import com.alibaba.android.bindingx.core.internal.BindingXOrientationHandler;
import com.alibaba.android.bindingx.core.internal.BindingXTimingHandler;
import com.alibaba.android.bindingx.core.internal.BindingXTouchHandler;
import com.alibaba.android.bindingx.core.internal.ExpressionPair;
import com.alibaba.android.bindingx.core.internal.Utils;
import com.taobao.weex.el.parse.Operators;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import org.json.JSONObject;

public class BindingXCore {

    /* renamed from: a  reason: collision with root package name */
    private Map<String, Map<String, IEventHandler>> f736a;
    private final Map<String, ObjectCreator<IEventHandler, Context, PlatformManager>> b = new HashMap(8);
    private final PlatformManager c;

    public interface JavaScriptCallback {
        void a(Object obj);
    }

    public interface ObjectCreator<Type, ParamA, ParamB> {
        Type a(@NonNull ParamA parama, @NonNull ParamB paramb, Object... objArr);
    }

    public BindingXCore(@NonNull PlatformManager platformManager) {
        this.c = platformManager;
        a("pan", (ObjectCreator<IEventHandler, Context, PlatformManager>) new ObjectCreator<IEventHandler, Context, PlatformManager>() {
            public IEventHandler a(@NonNull Context context, @NonNull PlatformManager platformManager, Object... objArr) {
                return new BindingXTouchHandler(context, platformManager, objArr);
            }
        });
        a("orientation", (ObjectCreator<IEventHandler, Context, PlatformManager>) new ObjectCreator<IEventHandler, Context, PlatformManager>() {
            public IEventHandler a(@NonNull Context context, @NonNull PlatformManager platformManager, Object... objArr) {
                return new BindingXOrientationHandler(context, platformManager, objArr);
            }
        });
        a(BindingXEventType.d, (ObjectCreator<IEventHandler, Context, PlatformManager>) new ObjectCreator<IEventHandler, Context, PlatformManager>() {
            public IEventHandler a(@NonNull Context context, @NonNull PlatformManager platformManager, Object... objArr) {
                return new BindingXTimingHandler(context, platformManager, objArr);
            }
        });
    }

    public String a(@Nullable Context context, @Nullable String str, @NonNull Map<String, Object> map, @NonNull JavaScriptCallback javaScriptCallback) {
        Map<String, Object> map2;
        Map<String, Object> map3 = map;
        String a2 = Utils.a(map, "eventType");
        String a3 = Utils.a(map, BindingXConstants.p);
        LogProxy.a(map);
        Object obj = map.get("options");
        if (obj != null && (obj instanceof Map)) {
            try {
                map2 = Utils.a(new JSONObject((Map) obj));
            } catch (Exception e) {
                LogProxy.e("parse external config failed.\n", e);
            }
            ExpressionPair b2 = Utils.b(map, BindingXConstants.q);
            return a(Utils.a(map, "anchor"), a3, a2, map2, b2, Utils.a(map), Utils.b(map), javaScriptCallback, context, str);
        }
        map2 = null;
        ExpressionPair b22 = Utils.b(map, BindingXConstants.q);
        return a(Utils.a(map, "anchor"), a3, a2, map2, b22, Utils.a(map), Utils.b(map), javaScriptCallback, context, str);
    }

    public void a(@Nullable Map<String, Object> map) {
        if (map != null) {
            a(Utils.a(map, "token"), Utils.a(map, "eventType"));
        }
    }

    public void a(@Nullable String str, @Nullable String str2) {
        LogProxy.c("disable binding [" + str + "," + str2 + Operators.ARRAY_END_STR);
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2)) {
            LogProxy.c("disable binding failed(0x1) [" + str + "," + str2 + Operators.ARRAY_END_STR);
        } else if (this.f736a == null || this.f736a.isEmpty()) {
            LogProxy.c("disable binding failed(0x2) [" + str + "," + str2 + Operators.ARRAY_END_STR);
        } else {
            Map map = this.f736a.get(str);
            if (map == null || map.isEmpty()) {
                LogProxy.c("disable binding failed(0x3) [" + str + "," + str2 + Operators.ARRAY_END_STR);
                return;
            }
            IEventHandler iEventHandler = (IEventHandler) map.get(str2);
            if (iEventHandler == null) {
                LogProxy.c("disable binding failed(0x4) [" + str + "," + str2 + Operators.ARRAY_END_STR);
            } else if (iEventHandler.c(str, str2)) {
                this.f736a.remove(str);
                LogProxy.c("disable binding success[" + str + "," + str2 + Operators.ARRAY_END_STR);
            } else {
                LogProxy.c("disabled failed(0x4) [" + str + "," + str2 + Operators.ARRAY_END_STR);
            }
        }
    }

    public void a() {
        if (this.f736a != null) {
            try {
                for (Map next : this.f736a.values()) {
                    if (next != null && !next.isEmpty()) {
                        for (IEventHandler iEventHandler : next.values()) {
                            if (iEventHandler != null) {
                                iEventHandler.a();
                            }
                        }
                    }
                }
                this.f736a.clear();
                this.f736a = null;
            } catch (Exception e) {
                LogProxy.e("release failed", e);
            }
        }
    }

    public String a(@Nullable Context context, @Nullable String str, @Nullable String str2, @Nullable String str3, @Nullable String str4) {
        IEventHandler iEventHandler;
        if (TextUtils.isEmpty(str4)) {
            LogProxy.e("[doPrepare] failed. can not found eventType");
            return null;
        } else if (context == null) {
            LogProxy.e("[doPrepare] failed. context or wxInstance is null");
            return null;
        } else {
            if (TextUtils.isEmpty(str2)) {
                str2 = d();
            }
            if (this.f736a == null) {
                this.f736a = new HashMap();
            }
            Map map = this.f736a.get(str2);
            if (map == null || (iEventHandler = (IEventHandler) map.get(str4)) == null) {
                if (map == null) {
                    map = new HashMap(4);
                    this.f736a.put(str2, map);
                }
                IEventHandler a2 = a(context, str, str4);
                if (a2 != null) {
                    a2.a(str3);
                    a2.b(str2);
                    if (a2.a(str2, str4)) {
                        a2.b(str2, str4);
                        map.put(str4, a2);
                        LogProxy.c("enableBinding success.[token:" + str2 + ",type:" + str4 + Operators.ARRAY_END_STR);
                    } else {
                        LogProxy.e("expression enabled failed. [token:" + str2 + ",type:" + str4 + Operators.ARRAY_END_STR);
                        return null;
                    }
                } else {
                    LogProxy.e("unknown eventType: " + str4);
                    return null;
                }
            } else {
                LogProxy.c("you have already enabled binding,[token:" + str2 + ",type:" + str4 + Operators.ARRAY_END_STR);
                iEventHandler.b(str2, str4);
                LogProxy.c("enableBinding success.[token:" + str2 + ",type:" + str4 + Operators.ARRAY_END_STR);
            }
            return str2;
        }
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v18, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v20, resolved type: com.alibaba.android.bindingx.core.IEventHandler} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.lang.String a(@android.support.annotation.Nullable java.lang.String r13, @android.support.annotation.Nullable java.lang.String r14, @android.support.annotation.Nullable java.lang.String r15, @android.support.annotation.Nullable java.util.Map<java.lang.String, java.lang.Object> r16, @android.support.annotation.Nullable com.alibaba.android.bindingx.core.internal.ExpressionPair r17, @android.support.annotation.Nullable java.util.List<java.util.Map<java.lang.String, java.lang.Object>> r18, @android.support.annotation.Nullable java.util.Map<java.lang.String, com.alibaba.android.bindingx.core.internal.ExpressionPair> r19, @android.support.annotation.Nullable com.alibaba.android.bindingx.core.BindingXCore.JavaScriptCallback r20, @android.support.annotation.Nullable android.content.Context r21, @android.support.annotation.Nullable java.lang.String r22) {
        /*
            r12 = this;
            r6 = r12
            r7 = r13
            r8 = r15
            r9 = r18
            boolean r0 = android.text.TextUtils.isEmpty(r15)
            r1 = 0
            if (r0 != 0) goto L_0x00d0
            if (r9 != 0) goto L_0x0010
            goto L_0x00d0
        L_0x0010:
            java.util.Map<java.lang.String, java.util.Map<java.lang.String, com.alibaba.android.bindingx.core.IEventHandler>> r0 = r6.f736a
            if (r0 == 0) goto L_0x002b
            boolean r0 = android.text.TextUtils.isEmpty(r13)
            if (r0 != 0) goto L_0x002b
            java.util.Map<java.lang.String, java.util.Map<java.lang.String, com.alibaba.android.bindingx.core.IEventHandler>> r0 = r6.f736a
            java.lang.Object r0 = r0.get(r13)
            java.util.Map r0 = (java.util.Map) r0
            if (r0 == 0) goto L_0x002b
            java.lang.Object r0 = r0.get(r15)
            r1 = r0
            com.alibaba.android.bindingx.core.IEventHandler r1 = (com.alibaba.android.bindingx.core.IEventHandler) r1
        L_0x002b:
            r10 = r1
            if (r10 != 0) goto L_0x007a
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            java.lang.String r1 = "binding not enabled,try auto enable it.[sourceRef:"
            r0.append(r1)
            r0.append(r13)
            java.lang.String r1 = ",eventType:"
            r0.append(r1)
            r0.append(r15)
            java.lang.String r1 = "]"
            r0.append(r1)
            java.lang.String r0 = r0.toString()
            com.alibaba.android.bindingx.core.LogProxy.c(r0)
            r0 = r12
            r1 = r21
            r2 = r22
            r3 = r13
            r4 = r14
            r5 = r15
            java.lang.String r0 = r0.a(r1, r2, r3, r4, r5)
            boolean r1 = android.text.TextUtils.isEmpty(r0)
            if (r1 != 0) goto L_0x0078
            java.util.Map<java.lang.String, java.util.Map<java.lang.String, com.alibaba.android.bindingx.core.IEventHandler>> r1 = r6.f736a
            if (r1 == 0) goto L_0x0078
            java.util.Map<java.lang.String, java.util.Map<java.lang.String, com.alibaba.android.bindingx.core.IEventHandler>> r1 = r6.f736a
            java.lang.Object r1 = r1.get(r0)
            java.util.Map r1 = (java.util.Map) r1
            if (r1 == 0) goto L_0x0078
            java.lang.Object r1 = r1.get(r15)
            com.alibaba.android.bindingx.core.IEventHandler r1 = (com.alibaba.android.bindingx.core.IEventHandler) r1
            r11 = r0
            r10 = r1
            goto L_0x007b
        L_0x0078:
            r11 = r0
            goto L_0x007b
        L_0x007a:
            r11 = r7
        L_0x007b:
            if (r10 == 0) goto L_0x00b3
            r0 = r10
            r1 = r15
            r2 = r16
            r3 = r17
            r4 = r18
            r5 = r20
            r0.a(r1, r2, r3, r4, r5)
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            java.lang.String r1 = "createBinding success.[exitExp:"
            r0.append(r1)
            r1 = r17
            r0.append(r1)
            java.lang.String r1 = ",args:"
            r0.append(r1)
            r0.append(r9)
            java.lang.String r1 = "]"
            r0.append(r1)
            java.lang.String r0 = r0.toString()
            com.alibaba.android.bindingx.core.LogProxy.c(r0)
            r0 = r19
            r10.a(r0)
            goto L_0x00cf
        L_0x00b3:
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            java.lang.String r1 = "internal error.binding failed for ref:"
            r0.append(r1)
            r0.append(r13)
            java.lang.String r1 = ",type:"
            r0.append(r1)
            r0.append(r15)
            java.lang.String r0 = r0.toString()
            com.alibaba.android.bindingx.core.LogProxy.e(r0)
        L_0x00cf:
            return r11
        L_0x00d0:
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            java.lang.String r2 = "doBind failed,illegal argument.["
            r0.append(r2)
            r0.append(r15)
            java.lang.String r2 = ","
            r0.append(r2)
            r0.append(r9)
            java.lang.String r2 = "]"
            r0.append(r2)
            java.lang.String r0 = r0.toString()
            com.alibaba.android.bindingx.core.LogProxy.e(r0)
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alibaba.android.bindingx.core.BindingXCore.a(java.lang.String, java.lang.String, java.lang.String, java.util.Map, com.alibaba.android.bindingx.core.internal.ExpressionPair, java.util.List, java.util.Map, com.alibaba.android.bindingx.core.BindingXCore$JavaScriptCallback, android.content.Context, java.lang.String):java.lang.String");
    }

    public void b() {
        if (this.f736a != null) {
            try {
                for (Map<String, IEventHandler> values : this.f736a.values()) {
                    for (IEventHandler b2 : values.values()) {
                        try {
                            b2.b();
                        } catch (Exception e) {
                            LogProxy.e("execute activity pause failed.", e);
                        }
                    }
                }
            } catch (Exception e2) {
                LogProxy.e("activity pause failed", e2);
            }
        }
    }

    public void c() {
        if (this.f736a != null) {
            try {
                for (Map<String, IEventHandler> values : this.f736a.values()) {
                    for (IEventHandler c2 : values.values()) {
                        try {
                            c2.c();
                        } catch (Exception e) {
                            LogProxy.e("execute activity pause failed.", e);
                        }
                    }
                }
            } catch (Exception e2) {
                LogProxy.e("activity pause failed", e2);
            }
        }
    }

    public void a(String str, ObjectCreator<IEventHandler, Context, PlatformManager> objectCreator) {
        if (!TextUtils.isEmpty(str) && objectCreator != null) {
            this.b.put(str, objectCreator);
        }
    }

    private String d() {
        return UUID.randomUUID().toString();
    }

    @Nullable
    private IEventHandler a(@NonNull Context context, @Nullable String str, @NonNull String str2) {
        ObjectCreator objectCreator;
        if (this.b.isEmpty() || this.c == null || (objectCreator = this.b.get(str2)) == null) {
            return null;
        }
        return (IEventHandler) objectCreator.a(context, this.c, str);
    }
}
