package com.xiaomi.smarthome.framework.permission;

import android.app.Activity;
import kotlin.Metadata;
import kotlin.jvm.internal.MutablePropertyReference0;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KDeclarationContainer;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 3}, k = 3, mv = {1, 1, 13})
final class SerialPermissionRequest$onRequestPermissionsResult$1 extends MutablePropertyReference0 {
    SerialPermissionRequest$onRequestPermissionsResult$1(SerialPermissionRequest serialPermissionRequest) {
        super(serialPermissionRequest);
    }

    public String getName() {
        return "activity";
    }

    public KDeclarationContainer getOwner() {
        return Reflection.b(SerialPermissionRequest.class);
    }

    public String getSignature() {
        return "getActivity()Landroid/app/Activity;";
    }

    @Nullable
    public Object get() {
        return SerialPermissionRequest.a((SerialPermissionRequest) this.receiver);
    }

    public void set(@Nullable Object obj) {
        ((SerialPermissionRequest) this.receiver).d = (Activity) obj;
    }
}
