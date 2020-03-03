package com.xiaomi.smarthome.framework.permission;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Handler;
import android.os.Looper;
import android.support.v4.app.ActivityCompat;
import com.xiaomi.smarthome.library.common.dialog.MLAlertDialog;
import io.reactivex.Completable;
import io.reactivex.subjects.CompletableSubject;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000V\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0011\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0015\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u0000 \u001f2\u00020\u0001:\u0002\u001f B\u0005¢\u0006\u0002\u0010\u0002J)\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u00112\f\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00140\u00132\u0006\u0010\u0015\u001a\u00020\u0016¢\u0006\u0002\u0010\u0017J\b\u0010\u0018\u001a\u00020\u0019H\u0002J\u001a\u0010\u001a\u001a\u00020\u00192\b\u0010\u001b\u001a\u0004\u0018\u00010\r2\u0006\u0010\u0003\u001a\u00020\u0004H\u0002J\u001c\u0010\u001c\u001a\u00020\u001d2\u0006\u0010\u0003\u001a\u00020\u00042\f\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\r0\u001eR\u000e\u0010\u0003\u001a\u00020\u0004X.¢\u0006\u0002\n\u0000R\u001c\u0010\u0005\u001a\u0004\u0018\u00010\u0006X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0007\u0010\b\"\u0004\b\t\u0010\nR\u0014\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\r0\fX.¢\u0006\u0002\n\u0000¨\u0006!"}, d2 = {"Lcom/xiaomi/smarthome/framework/permission/SerialPermissionRequest;", "", "()V", "activity", "Landroid/app/Activity;", "cancelListener", "Landroid/content/DialogInterface$OnClickListener;", "getCancelListener", "()Landroid/content/DialogInterface$OnClickListener;", "setCancelListener", "(Landroid/content/DialogInterface$OnClickListener;)V", "permissionBeans", "", "Lcom/xiaomi/smarthome/framework/permission/PermissionBean;", "onRequestPermissionsResult", "", "requestCode", "", "permissions", "", "", "grantResults", "", "(I[Ljava/lang/String;[I)Z", "permissionRequestFinishListener", "Lio/reactivex/Completable;", "requestDNDPermission", "permission", "requestPermission", "", "Ljava/util/ArrayList;", "Companion", "FocusAwareDialog", "app_GooglePlayRelease"}, k = 1, mv = {1, 1, 13})
public final class SerialPermissionRequest {

    /* renamed from: a  reason: collision with root package name */
    public static final int f17119a = 6000;
    public static final Companion b = new Companion((DefaultConstructorMarker) null);
    private static final String f = "SerialPermissionRequest";
    @Nullable
    private DialogInterface.OnClickListener c;
    /* access modifiers changed from: private */
    public Activity d;
    private List<PermissionBean> e;

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006XT¢\u0006\u0002\n\u0000¨\u0006\u0007"}, d2 = {"Lcom/xiaomi/smarthome/framework/permission/SerialPermissionRequest$Companion;", "", "()V", "REQUEST_CODE", "", "TAG", "", "app_GooglePlayRelease"}, k = 1, mv = {1, 1, 13})
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    @NotNull
    public static final /* synthetic */ Activity a(SerialPermissionRequest serialPermissionRequest) {
        Activity activity = serialPermissionRequest.d;
        if (activity == null) {
            Intrinsics.c("activity");
        }
        return activity;
    }

    @Nullable
    public final DialogInterface.OnClickListener a() {
        return this.c;
    }

    public final void a(@Nullable DialogInterface.OnClickListener onClickListener) {
        this.c = onClickListener;
    }

    public final void a(@NotNull Activity activity, @NotNull ArrayList<PermissionBean> arrayList) {
        Intrinsics.f(activity, "activity");
        Intrinsics.f(arrayList, "permissions");
        if (!arrayList.isEmpty()) {
            this.d = activity;
            this.e = arrayList;
            Iterable<PermissionBean> iterable = arrayList;
            Collection arrayList2 = new ArrayList(CollectionsKt.a(iterable, 10));
            for (PermissionBean c2 : iterable) {
                arrayList2.add(c2.c());
            }
            Collection arrayList3 = new ArrayList();
            for (String[] c3 : (List) arrayList2) {
                CollectionsKt.a(arrayList3, ArraysKt.c((T[]) c3));
            }
            Object[] array = ((List) arrayList3).toArray(new String[0]);
            if (array != null) {
                ActivityCompat.requestPermissions(activity, (String[]) array, 6000);
                return;
            }
            throw new TypeCastException("null cannot be cast to non-null type kotlin.Array<T>");
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:21:0x004e  */
    /* JADX WARNING: Removed duplicated region for block: B:61:0x0051 A[SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final boolean a(int r9, @org.jetbrains.annotations.NotNull java.lang.String[] r10, @org.jetbrains.annotations.NotNull int[] r11) {
        /*
            r8 = this;
            java.lang.String r0 = "permissions"
            kotlin.jvm.internal.Intrinsics.f(r10, r0)
            java.lang.String r0 = "grantResults"
            kotlin.jvm.internal.Intrinsics.f(r11, r0)
            r0 = 0
            r1 = 6000(0x1770, float:8.408E-42)
            if (r9 == r1) goto L_0x0010
            return r0
        L_0x0010:
            r9 = r8
            com.xiaomi.smarthome.framework.permission.SerialPermissionRequest r9 = (com.xiaomi.smarthome.framework.permission.SerialPermissionRequest) r9
            android.app.Activity r9 = r9.d
            r1 = 1
            if (r9 == 0) goto L_0x001a
            r9 = 1
            goto L_0x001b
        L_0x001a:
            r9 = 0
        L_0x001b:
            if (r9 != 0) goto L_0x001e
            return r0
        L_0x001e:
            java.lang.String r9 = "SerialPermissionRequest"
            java.lang.String r2 = "onRequestPermissionsResult"
            com.xiaomi.smarthome.shop.utils.LogUtil.a(r9, r2)
            java.util.ArrayList r9 = new java.util.ArrayList
            r9.<init>()
            java.util.Collection r9 = (java.util.Collection) r9
            int r2 = r10.length
            r3 = 0
            r4 = 0
        L_0x002f:
            if (r3 >= r2) goto L_0x0055
            r5 = r10[r3]
            int r6 = r4 + 1
            r4 = r11[r4]
            r7 = -1
            if (r4 != r7) goto L_0x004b
            android.app.Activity r4 = r8.d
            if (r4 != 0) goto L_0x0043
            java.lang.String r7 = "activity"
            kotlin.jvm.internal.Intrinsics.c(r7)
        L_0x0043:
            boolean r4 = android.support.v4.app.ActivityCompat.shouldShowRequestPermissionRationale(r4, r5)
            if (r4 != 0) goto L_0x004b
            r4 = 1
            goto L_0x004c
        L_0x004b:
            r4 = 0
        L_0x004c:
            if (r4 == 0) goto L_0x0051
            r9.add(r5)
        L_0x0051:
            int r3 = r3 + 1
            r4 = r6
            goto L_0x002f
        L_0x0055:
            java.util.List r9 = (java.util.List) r9
            java.lang.Iterable r9 = (java.lang.Iterable) r9
            java.util.ArrayList r10 = new java.util.ArrayList
            r11 = 10
            int r11 = kotlin.collections.CollectionsKt.a(r9, (int) r11)
            r10.<init>(r11)
            java.util.Collection r10 = (java.util.Collection) r10
            java.util.Iterator r9 = r9.iterator()
        L_0x006a:
            boolean r11 = r9.hasNext()
            if (r11 == 0) goto L_0x00a4
            java.lang.Object r11 = r9.next()
            java.lang.String r11 = (java.lang.String) r11
            java.util.List<com.xiaomi.smarthome.framework.permission.PermissionBean> r2 = r8.e
            if (r2 != 0) goto L_0x007f
            java.lang.String r3 = "permissionBeans"
            kotlin.jvm.internal.Intrinsics.c(r3)
        L_0x007f:
            java.lang.Iterable r2 = (java.lang.Iterable) r2
            java.util.Iterator r2 = r2.iterator()
        L_0x0085:
            boolean r3 = r2.hasNext()
            if (r3 == 0) goto L_0x009d
            java.lang.Object r3 = r2.next()
            r4 = r3
            com.xiaomi.smarthome.framework.permission.PermissionBean r4 = (com.xiaomi.smarthome.framework.permission.PermissionBean) r4
            java.lang.String[] r4 = r4.c()
            boolean r4 = kotlin.collections.ArraysKt.b((T[]) r4, r11)
            if (r4 == 0) goto L_0x0085
            goto L_0x009e
        L_0x009d:
            r3 = 0
        L_0x009e:
            com.xiaomi.smarthome.framework.permission.PermissionBean r3 = (com.xiaomi.smarthome.framework.permission.PermissionBean) r3
            r10.add(r3)
            goto L_0x006a
        L_0x00a4:
            java.util.List r10 = (java.util.List) r10
            java.lang.Iterable r10 = (java.lang.Iterable) r10
            java.util.ArrayList r9 = new java.util.ArrayList
            r9.<init>()
            java.util.Collection r9 = (java.util.Collection) r9
            java.util.Iterator r10 = r10.iterator()
        L_0x00b3:
            boolean r11 = r10.hasNext()
            if (r11 == 0) goto L_0x00cb
            java.lang.Object r11 = r10.next()
            r2 = r11
            com.xiaomi.smarthome.framework.permission.PermissionBean r2 = (com.xiaomi.smarthome.framework.permission.PermissionBean) r2
            if (r2 == 0) goto L_0x00c4
            r2 = 1
            goto L_0x00c5
        L_0x00c4:
            r2 = 0
        L_0x00c5:
            if (r2 == 0) goto L_0x00b3
            r9.add(r11)
            goto L_0x00b3
        L_0x00cb:
            java.util.List r9 = (java.util.List) r9
            java.lang.Iterable r9 = (java.lang.Iterable) r9
            java.util.Set r9 = kotlin.collections.CollectionsKt.t(r9)
            java.lang.Iterable r9 = (java.lang.Iterable) r9
            java.util.List r9 = kotlin.collections.CollectionsKt.r(r9)
            r10 = r9
            java.util.Collection r10 = (java.util.Collection) r10
            boolean r10 = r10.isEmpty()
            r10 = r10 ^ r1
            if (r10 == 0) goto L_0x0130
            java.lang.Object r10 = r9.get(r0)
            com.xiaomi.smarthome.framework.permission.PermissionBean r10 = (com.xiaomi.smarthome.framework.permission.PermissionBean) r10
            android.app.Activity r11 = r8.d
            if (r11 != 0) goto L_0x00f2
            java.lang.String r2 = "activity"
            kotlin.jvm.internal.Intrinsics.c(r2)
        L_0x00f2:
            io.reactivex.Completable r10 = r8.a((com.xiaomi.smarthome.framework.permission.PermissionBean) r10, (android.app.Activity) r11)
            int r11 = r9.size()
        L_0x00fa:
            if (r1 >= r11) goto L_0x011d
            java.lang.Object r2 = r9.get(r1)
            com.xiaomi.smarthome.framework.permission.PermissionBean r2 = (com.xiaomi.smarthome.framework.permission.PermissionBean) r2
            android.app.Activity r3 = r8.d
            if (r3 != 0) goto L_0x010b
            java.lang.String r4 = "activity"
            kotlin.jvm.internal.Intrinsics.c(r4)
        L_0x010b:
            io.reactivex.Completable r2 = r8.a((com.xiaomi.smarthome.framework.permission.PermissionBean) r2, (android.app.Activity) r3)
            io.reactivex.CompletableSource r2 = (io.reactivex.CompletableSource) r2
            io.reactivex.Completable r10 = r10.andThen((io.reactivex.CompletableSource) r2)
            java.lang.String r2 = "next.andThen(requestDNDP…ission[index], activity))"
            kotlin.jvm.internal.Intrinsics.b(r10, r2)
            int r1 = r1 + 1
            goto L_0x00fa
        L_0x011d:
            io.reactivex.Completable r9 = r8.b()
            io.reactivex.CompletableSource r9 = (io.reactivex.CompletableSource) r9
            io.reactivex.Completable r9 = r10.andThen((io.reactivex.CompletableSource) r9)
            java.lang.String r10 = "next.andThen(permissionRequestFinishListener())"
            kotlin.jvm.internal.Intrinsics.b(r9, r10)
            r9.subscribe()
            return r0
        L_0x0130:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.smarthome.framework.permission.SerialPermissionRequest.a(int, java.lang.String[], int[]):boolean");
    }

    private final Completable b() {
        CompletableSubject create = CompletableSubject.create();
        Intrinsics.b(create, "CompletableSubject.create()");
        create.doOnSubscribe(new SerialPermissionRequest$permissionRequestFinishListener$1(create));
        return create;
    }

    private final Completable a(PermissionBean permissionBean, Activity activity) {
        if (permissionBean == null || permissionBean.b()) {
            Completable complete = Completable.complete();
            Intrinsics.b(complete, "Completable.complete()");
            return complete;
        }
        CompletableSubject create = CompletableSubject.create();
        Intrinsics.b(create, "CompletableSubject.create()");
        Completable doOnSubscribe = create.doOnSubscribe(new SerialPermissionRequest$requestDNDPermission$1(this, permissionBean, create, activity));
        Intrinsics.b(doOnSubscribe, "permissionSubject.doOnSu…)\n            }\n        }");
        return doOnSubscribe;
    }

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\u0010\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nH\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0004¢\u0006\u0002\n\u0000¨\u0006\u000b"}, d2 = {"Lcom/xiaomi/smarthome/framework/permission/SerialPermissionRequest$FocusAwareDialog;", "Lcom/xiaomi/smarthome/library/common/dialog/MLAlertDialog;", "permission", "Lcom/xiaomi/smarthome/framework/permission/PermissionBean;", "context", "Landroid/content/Context;", "(Lcom/xiaomi/smarthome/framework/permission/PermissionBean;Landroid/content/Context;)V", "onWindowFocusChanged", "", "hasFocus", "", "app_GooglePlayRelease"}, k = 1, mv = {1, 1, 13})
    public static final class FocusAwareDialog extends MLAlertDialog {

        /* renamed from: a  reason: collision with root package name */
        private final PermissionBean f17124a;

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public FocusAwareDialog(@NotNull PermissionBean permissionBean, @NotNull Context context) {
            super(context);
            Intrinsics.f(permissionBean, "permission");
            Intrinsics.f(context, "context");
            this.f17124a = permissionBean;
        }

        public void onWindowFocusChanged(boolean z) {
            super.onWindowFocusChanged(z);
            if (z && this.f17124a.b()) {
                new Handler(Looper.getMainLooper()).post(new SerialPermissionRequest$FocusAwareDialog$onWindowFocusChanged$1(this));
            }
        }
    }
}
