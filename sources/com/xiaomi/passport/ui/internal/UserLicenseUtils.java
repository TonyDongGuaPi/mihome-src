package com.xiaomi.passport.ui.internal;

import android.content.Context;
import android.content.res.Resources;
import android.text.TextUtils;
import java.util.Arrays;
import java.util.HashMap;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.StringCompanionObject;
import org.jetbrains.annotations.NotNull;

@Metadata(bv = {1, 0, 2}, d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0006\b\u0016\u0018\u0000 \u00112\u00020\u0001:\u0001\u0011B\u0005¢\u0006\u0002\u0010\u0002J\u0010\u0010\n\u001a\u00020\u00052\u0006\u0010\u000b\u001a\u00020\fH\u0002J*\u0010\r\u001a\u001e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00060\u0004j\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u0006`\u00072\u0006\u0010\u000b\u001a\u00020\fJ\u0018\u0010\u000e\u001a\u00020\u00052\u0006\u0010\u000f\u001a\u00020\u00052\u0006\u0010\u000b\u001a\u00020\fH\u0002J\u0010\u0010\u0010\u001a\u00020\u00052\u0006\u0010\u000b\u001a\u00020\fH\u0002R.\u0010\u0003\u001a\"\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u0006\u0018\u00010\u0004j\u0010\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u0006\u0018\u0001`\u0007X\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\b\u001a\u0004\u0018\u00010\u0005X\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\t\u001a\u0004\u0018\u00010\u0005X\u000e¢\u0006\u0002\n\u0000¨\u0006\u0012"}, d2 = {"Lcom/xiaomi/passport/ui/internal/UserLicenseUtils;", "", "()V", "mURLLicenses", "Ljava/util/HashMap;", "", "Lcom/xiaomi/passport/ui/internal/URLLicense;", "Lkotlin/collections/HashMap;", "privacyPolicyUrl", "userAgreementUrl", "getPrivacyPolicyClicked", "context", "Landroid/content/Context;", "getURLLicenses", "getUrl", "urlFormat", "getUserAgreementClicked", "Companion", "passportui_release"}, k = 1, mv = {1, 1, 10})
public class UserLicenseUtils {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    private static final String URL_PRIVACY_POLICY = "https://www.miui.com/res/doc/privacy.html?lang=%s";
    private static final String URL_USER_AGREEMENT = "https://www.miui.com/res/doc/eula.html?lang=%s";
    /* access modifiers changed from: private */
    @NotNull
    public static final HashMap<String, URLLicense> globalLicenses = new HashMap<>();
    private HashMap<String, URLLicense> mURLLicenses;
    private String privacyPolicyUrl = PassportUI.INSTANCE.getPrivacyPolicyUrl();
    private String userAgreementUrl = PassportUI.INSTANCE.getUserAgreementUrl();

    @Metadata(bv = {1, 0, 2}, d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0003\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0016\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u00042\u0006\u0010\u000f\u001a\u00020\u0004R\u000e\u0010\u0003\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R-\u0010\u0006\u001a\u001e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\b0\u0007j\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\b`\t¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b¨\u0006\u0010"}, d2 = {"Lcom/xiaomi/passport/ui/internal/UserLicenseUtils$Companion;", "", "()V", "URL_PRIVACY_POLICY", "", "URL_USER_AGREEMENT", "globalLicenses", "Ljava/util/HashMap;", "Lcom/xiaomi/passport/ui/internal/URLLicense;", "Lkotlin/collections/HashMap;", "getGlobalLicenses", "()Ljava/util/HashMap;", "addLicense", "", "readableText", "url", "passportui_release"}, k = 1, mv = {1, 1, 10})
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        @NotNull
        public final HashMap<String, URLLicense> getGlobalLicenses() {
            return UserLicenseUtils.globalLicenses;
        }

        public final void addLicense(@NotNull String str, @NotNull String str2) {
            Intrinsics.f(str, "readableText");
            Intrinsics.f(str2, "url");
            URLLicense uRLLicense = new URLLicense(str, str2, (String) null, 4, (DefaultConstructorMarker) null);
            getGlobalLicenses().put(uRLLicense.getKey(), uRLLicense);
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:15:0x0020, code lost:
        return r11;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:34:0x00ad, code lost:
        return r11;
     */
    @org.jetbrains.annotations.NotNull
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.util.HashMap<java.lang.String, com.xiaomi.passport.ui.internal.URLLicense> getURLLicenses(@org.jetbrains.annotations.NotNull android.content.Context r11) {
        /*
            r10 = this;
            java.lang.String r0 = "context"
            kotlin.jvm.internal.Intrinsics.f(r11, r0)
            java.util.HashMap<java.lang.String, com.xiaomi.passport.ui.internal.URLLicense> r0 = r10.mURLLicenses
            if (r0 == 0) goto L_0x0011
            java.util.HashMap<java.lang.String, com.xiaomi.passport.ui.internal.URLLicense> r11 = r10.mURLLicenses
            if (r11 != 0) goto L_0x0010
            kotlin.jvm.internal.Intrinsics.a()
        L_0x0010:
            return r11
        L_0x0011:
            java.lang.Class<com.xiaomi.passport.ui.internal.UserLicenseUtils> r0 = com.xiaomi.passport.ui.internal.UserLicenseUtils.class
            monitor-enter(r0)
            java.util.HashMap<java.lang.String, com.xiaomi.passport.ui.internal.URLLicense> r1 = r10.mURLLicenses     // Catch:{ all -> 0x00ae }
            if (r1 == 0) goto L_0x0021
            java.util.HashMap<java.lang.String, com.xiaomi.passport.ui.internal.URLLicense> r11 = r10.mURLLicenses     // Catch:{ all -> 0x00ae }
            if (r11 != 0) goto L_0x001f
            kotlin.jvm.internal.Intrinsics.a()     // Catch:{ all -> 0x00ae }
        L_0x001f:
            monitor-exit(r0)
            return r11
        L_0x0021:
            java.util.HashMap r1 = new java.util.HashMap     // Catch:{ all -> 0x00ae }
            r1.<init>()     // Catch:{ all -> 0x00ae }
            r10.mURLLicenses = r1     // Catch:{ all -> 0x00ae }
            java.lang.String r4 = r10.getUserAgreementClicked(r11)     // Catch:{ all -> 0x00ae }
            int r1 = com.xiaomi.passport.ui.R.string.passport_user_agreement     // Catch:{ all -> 0x00ae }
            java.lang.String r3 = r11.getString(r1)     // Catch:{ all -> 0x00ae }
            com.xiaomi.passport.ui.internal.URLLicense r1 = new com.xiaomi.passport.ui.internal.URLLicense     // Catch:{ all -> 0x00ae }
            java.lang.String r2 = "agreementText"
            kotlin.jvm.internal.Intrinsics.b(r3, r2)     // Catch:{ all -> 0x00ae }
            r5 = 0
            r6 = 4
            r7 = 0
            r2 = r1
            r2.<init>(r3, r4, r5, r6, r7)     // Catch:{ all -> 0x00ae }
            java.util.HashMap<java.lang.String, com.xiaomi.passport.ui.internal.URLLicense> r2 = r10.mURLLicenses     // Catch:{ all -> 0x00ae }
            if (r2 != 0) goto L_0x0047
            kotlin.jvm.internal.Intrinsics.a()     // Catch:{ all -> 0x00ae }
        L_0x0047:
            java.util.Map r2 = (java.util.Map) r2     // Catch:{ all -> 0x00ae }
            java.lang.String r3 = r1.getKey()     // Catch:{ all -> 0x00ae }
            r2.put(r3, r1)     // Catch:{ all -> 0x00ae }
            java.lang.String r6 = r10.getPrivacyPolicyClicked(r11)     // Catch:{ all -> 0x00ae }
            int r1 = com.xiaomi.passport.ui.R.string.passport_privacy_policy     // Catch:{ all -> 0x00ae }
            java.lang.String r5 = r11.getString(r1)     // Catch:{ all -> 0x00ae }
            com.xiaomi.passport.ui.internal.URLLicense r11 = new com.xiaomi.passport.ui.internal.URLLicense     // Catch:{ all -> 0x00ae }
            java.lang.String r1 = "privacyText"
            kotlin.jvm.internal.Intrinsics.b(r5, r1)     // Catch:{ all -> 0x00ae }
            r7 = 0
            r8 = 4
            r9 = 0
            r4 = r11
            r4.<init>(r5, r6, r7, r8, r9)     // Catch:{ all -> 0x00ae }
            java.util.HashMap<java.lang.String, com.xiaomi.passport.ui.internal.URLLicense> r1 = r10.mURLLicenses     // Catch:{ all -> 0x00ae }
            if (r1 != 0) goto L_0x006f
            kotlin.jvm.internal.Intrinsics.a()     // Catch:{ all -> 0x00ae }
        L_0x006f:
            java.util.Map r1 = (java.util.Map) r1     // Catch:{ all -> 0x00ae }
            java.lang.String r2 = r11.getKey()     // Catch:{ all -> 0x00ae }
            r1.put(r2, r11)     // Catch:{ all -> 0x00ae }
            java.util.HashMap<java.lang.String, com.xiaomi.passport.ui.internal.URLLicense> r11 = globalLicenses     // Catch:{ all -> 0x00ae }
            java.util.Map r11 = (java.util.Map) r11     // Catch:{ all -> 0x00ae }
            java.util.Set r11 = r11.entrySet()     // Catch:{ all -> 0x00ae }
            java.util.Iterator r11 = r11.iterator()     // Catch:{ all -> 0x00ae }
        L_0x0084:
            boolean r1 = r11.hasNext()     // Catch:{ all -> 0x00ae }
            if (r1 == 0) goto L_0x00a5
            java.lang.Object r1 = r11.next()     // Catch:{ all -> 0x00ae }
            java.util.Map$Entry r1 = (java.util.Map.Entry) r1     // Catch:{ all -> 0x00ae }
            java.util.HashMap<java.lang.String, com.xiaomi.passport.ui.internal.URLLicense> r2 = r10.mURLLicenses     // Catch:{ all -> 0x00ae }
            if (r2 != 0) goto L_0x0097
            kotlin.jvm.internal.Intrinsics.a()     // Catch:{ all -> 0x00ae }
        L_0x0097:
            java.util.Map r2 = (java.util.Map) r2     // Catch:{ all -> 0x00ae }
            java.lang.Object r3 = r1.getKey()     // Catch:{ all -> 0x00ae }
            java.lang.Object r1 = r1.getValue()     // Catch:{ all -> 0x00ae }
            r2.put(r3, r1)     // Catch:{ all -> 0x00ae }
            goto L_0x0084
        L_0x00a5:
            java.util.HashMap<java.lang.String, com.xiaomi.passport.ui.internal.URLLicense> r11 = r10.mURLLicenses     // Catch:{ all -> 0x00ae }
            if (r11 != 0) goto L_0x00ac
            kotlin.jvm.internal.Intrinsics.a()     // Catch:{ all -> 0x00ae }
        L_0x00ac:
            monitor-exit(r0)
            return r11
        L_0x00ae:
            r11 = move-exception
            monitor-exit(r0)
            throw r11
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.passport.ui.internal.UserLicenseUtils.getURLLicenses(android.content.Context):java.util.HashMap");
    }

    private final String getUserAgreementClicked(Context context) {
        if (TextUtils.isEmpty(this.userAgreementUrl)) {
            this.userAgreementUrl = getUrl(URL_USER_AGREEMENT, context);
        }
        String str = this.userAgreementUrl;
        if (str == null) {
            Intrinsics.a();
        }
        return str;
    }

    private final String getPrivacyPolicyClicked(Context context) {
        if (TextUtils.isEmpty(this.privacyPolicyUrl)) {
            this.privacyPolicyUrl = getUrl(URL_PRIVACY_POLICY, context);
        }
        String str = this.privacyPolicyUrl;
        if (str == null) {
            Intrinsics.a();
        }
        return str;
    }

    private final String getUrl(String str, Context context) {
        Resources resources = context.getResources();
        Intrinsics.b(resources, "context.resources");
        String locale = resources.getConfiguration().locale.toString();
        StringCompanionObject stringCompanionObject = StringCompanionObject.f2835a;
        Object[] objArr = {locale};
        String format = String.format(str, Arrays.copyOf(objArr, objArr.length));
        Intrinsics.b(format, "java.lang.String.format(format, *args)");
        return format;
    }
}
