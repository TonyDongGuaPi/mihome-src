package com.tencent.mm.sdk.a.a;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import com.tencent.mm.sdk.b.b;
import com.tencent.mm.sdk.b.h;

public final class a {

    /* renamed from: com.tencent.mm.sdk.a.a.a$a  reason: collision with other inner class name */
    public static class C0070a {
        public String Y;
        public Bundle Z;
        public String aa;
        public String ab;
    }

    public static boolean a(Context context, C0070a aVar) {
        String str;
        String str2;
        if (context == null) {
            str = "MicroMsg.SDK.MMessage";
            str2 = "send fail, invalid argument";
        } else if (h.h(aVar.ab)) {
            str = "MicroMsg.SDK.MMessage";
            str2 = "send fail, action is null";
        } else {
            String str3 = null;
            if (!h.h(aVar.aa)) {
                str3 = aVar.aa + ".permission.MM_MESSAGE";
            }
            Intent intent = new Intent(aVar.ab);
            if (aVar.Z != null) {
                intent.putExtras(aVar.Z);
            }
            String packageName = context.getPackageName();
            intent.putExtra("_mmessage_sdkVersion", 587268097);
            intent.putExtra("_mmessage_appPackage", packageName);
            intent.putExtra("_mmessage_content", aVar.Y);
            intent.putExtra("_mmessage_checksum", b.a(aVar.Y, 587268097, packageName));
            context.sendBroadcast(intent, str3);
            b.e("MicroMsg.SDK.MMessage", "send mm message, intent=" + intent + ", perm=" + str3);
            return true;
        }
        b.b(str, str2);
        return false;
    }
}
