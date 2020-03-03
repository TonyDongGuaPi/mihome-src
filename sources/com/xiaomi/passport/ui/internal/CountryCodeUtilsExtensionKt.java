package com.xiaomi.passport.ui.internal;

import android.content.Context;
import android.text.TextUtils;
import com.xiaomi.passport.ui.internal.PhoneNumUtil;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 2}, d1 = {"\u0000\u0016\n\u0000\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a\u001a\u0010\u0005\u001a\u0004\u0018\u00010\u00022\u0006\u0010\u0006\u001a\u00020\u00072\b\u0010\b\u001a\u0004\u0018\u00010\u0001\"\u0015\u0010\u0000\u001a\u00020\u0001*\u00020\u00028F¢\u0006\u0006\u001a\u0004\b\u0003\u0010\u0004¨\u0006\t"}, d2 = {"countryCodeWithPrefix", "", "Lcom/xiaomi/passport/ui/internal/PhoneNumUtil$CountryPhoneNumData;", "getCountryCodeWithPrefix", "(Lcom/xiaomi/passport/ui/internal/PhoneNumUtil$CountryPhoneNumData;)Ljava/lang/String;", "smartGetCountryCodeData", "context", "Landroid/content/Context;", "icOrIso", "passportui_release"}, k = 2, mv = {1, 1, 10})
public final class CountryCodeUtilsExtensionKt {
    @Nullable
    public static final PhoneNumUtil.CountryPhoneNumData smartGetCountryCodeData(@NotNull Context context, @Nullable String str) {
        Intrinsics.f(context, "context");
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        List<PhoneNumUtil.CountryPhoneNumData> countryPhoneNumDataList = PhoneNumUtil.getCountryPhoneNumDataList(context);
        Intrinsics.b(countryPhoneNumDataList, "sortedData");
        Collection arrayList = new ArrayList();
        Iterator it = countryPhoneNumDataList.iterator();
        while (true) {
            boolean z = false;
            if (!it.hasNext()) {
                return (PhoneNumUtil.CountryPhoneNumData) CollectionsKt.c((List) arrayList, 0);
            }
            Object next = it.next();
            PhoneNumUtil.CountryPhoneNumData countryPhoneNumData = (PhoneNumUtil.CountryPhoneNumData) next;
            Intrinsics.b(countryPhoneNumData, "it");
            if (Intrinsics.a((Object) str, (Object) getCountryCodeWithPrefix(countryPhoneNumData)) || Intrinsics.a((Object) str, (Object) countryPhoneNumData.countryISO)) {
                z = true;
            }
            if (z) {
                arrayList.add(next);
            }
        }
    }

    @NotNull
    public static final String getCountryCodeWithPrefix(@NotNull PhoneNumUtil.CountryPhoneNumData countryPhoneNumData) {
        Intrinsics.f(countryPhoneNumData, "$receiver");
        return '+' + countryPhoneNumData.countryCode;
    }
}
