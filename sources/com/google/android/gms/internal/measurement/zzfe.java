package com.google.android.gms.internal.measurement;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import bolts.MeasurementEvent;
import com.alibaba.android.bindingx.core.internal.BindingXConstants;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.util.Clock;
import com.google.android.gms.measurement.AppMeasurement;
import com.taobao.weex.el.parse.Operators;
import com.xiaomi.accountsdk.service.DeviceInfoResult;
import com.xiaomi.stat.d;
import java.util.concurrent.atomic.AtomicReference;
import tv.danmaku.ijk.media.player.IjkMediaPlayer;

public final class zzfe extends zzhh {
    private static final AtomicReference<String[]> zzaij = new AtomicReference<>();
    private static final AtomicReference<String[]> zzaik = new AtomicReference<>();
    private static final AtomicReference<String[]> zzail = new AtomicReference<>();

    zzfe(zzgl zzgl) {
        super(zzgl);
    }

    @Nullable
    private static String zza(String str, String[] strArr, String[] strArr2, AtomicReference<String[]> atomicReference) {
        String str2;
        Preconditions.checkNotNull(strArr);
        Preconditions.checkNotNull(strArr2);
        Preconditions.checkNotNull(atomicReference);
        Preconditions.checkArgument(strArr.length == strArr2.length);
        for (int i = 0; i < strArr.length; i++) {
            if (zzka.zzs(str, strArr[i])) {
                synchronized (atomicReference) {
                    String[] strArr3 = atomicReference.get();
                    if (strArr3 == null) {
                        strArr3 = new String[strArr2.length];
                        atomicReference.set(strArr3);
                    }
                    if (strArr3[i] == null) {
                        strArr3[i] = strArr2[i] + Operators.BRACKET_START_STR + strArr[i] + Operators.BRACKET_END_STR;
                    }
                    str2 = strArr3[i];
                }
                return str2;
            }
        }
        return str;
    }

    private static void zza(StringBuilder sb, int i) {
        for (int i2 = 0; i2 < i; i2++) {
            sb.append("  ");
        }
    }

    private final void zza(StringBuilder sb, int i, zzkf zzkf) {
        if (zzkf != null) {
            zza(sb, i);
            sb.append("filter {\n");
            zza(sb, i, "complement", (Object) zzkf.zzarx);
            zza(sb, i, "param_name", (Object) zzbk(zzkf.zzary));
            int i2 = i + 1;
            zzki zzki = zzkf.zzarv;
            if (zzki != null) {
                zza(sb, i2);
                sb.append("string_filter");
                sb.append(" {\n");
                if (zzki.zzash != null) {
                    String str = "UNKNOWN_MATCH_TYPE";
                    switch (zzki.zzash.intValue()) {
                        case 1:
                            str = "REGEXP";
                            break;
                        case 2:
                            str = "BEGINS_WITH";
                            break;
                        case 3:
                            str = "ENDS_WITH";
                            break;
                        case 4:
                            str = "PARTIAL";
                            break;
                        case 5:
                            str = "EXACT";
                            break;
                        case 6:
                            str = "IN_LIST";
                            break;
                    }
                    zza(sb, i2, "match_type", (Object) str);
                }
                zza(sb, i2, BindingXConstants.k, (Object) zzki.zzasi);
                zza(sb, i2, "case_sensitive", (Object) zzki.zzasj);
                if (zzki.zzask.length > 0) {
                    zza(sb, i2 + 1);
                    sb.append("expression_list {\n");
                    for (String append : zzki.zzask) {
                        zza(sb, i2 + 2);
                        sb.append(append);
                        sb.append("\n");
                    }
                    sb.append("}\n");
                }
                zza(sb, i2);
                sb.append("}\n");
            }
            zza(sb, i2, "number_filter", zzkf.zzarw);
            zza(sb, i);
            sb.append("}\n");
        }
    }

    private final void zza(StringBuilder sb, int i, String str, zzkg zzkg) {
        if (zzkg != null) {
            zza(sb, i);
            sb.append(str);
            sb.append(" {\n");
            if (zzkg.zzarz != null) {
                String str2 = "UNKNOWN_COMPARISON_TYPE";
                switch (zzkg.zzarz.intValue()) {
                    case 1:
                        str2 = "LESS_THAN";
                        break;
                    case 2:
                        str2 = "GREATER_THAN";
                        break;
                    case 3:
                        str2 = "EQUAL";
                        break;
                    case 4:
                        str2 = "BETWEEN";
                        break;
                }
                zza(sb, i, "comparison_type", (Object) str2);
            }
            zza(sb, i, "match_as_float", (Object) zzkg.zzasa);
            zza(sb, i, "comparison_value", (Object) zzkg.zzasb);
            zza(sb, i, "min_comparison_value", (Object) zzkg.zzasc);
            zza(sb, i, "max_comparison_value", (Object) zzkg.zzasd);
            zza(sb, i);
            sb.append("}\n");
        }
    }

    private static void zza(StringBuilder sb, int i, String str, zzkr zzkr) {
        if (zzkr != null) {
            zza(sb, 3);
            sb.append(str);
            sb.append(" {\n");
            int i2 = 0;
            if (zzkr.zzaul != null) {
                zza(sb, 4);
                sb.append("results: ");
                long[] jArr = zzkr.zzaul;
                int length = jArr.length;
                int i3 = 0;
                int i4 = 0;
                while (i3 < length) {
                    Long valueOf = Long.valueOf(jArr[i3]);
                    int i5 = i4 + 1;
                    if (i4 != 0) {
                        sb.append(", ");
                    }
                    sb.append(valueOf);
                    i3++;
                    i4 = i5;
                }
                sb.append(10);
            }
            if (zzkr.zzauk != null) {
                zza(sb, 4);
                sb.append("status: ");
                long[] jArr2 = zzkr.zzauk;
                int length2 = jArr2.length;
                int i6 = 0;
                while (i2 < length2) {
                    Long valueOf2 = Long.valueOf(jArr2[i2]);
                    int i7 = i6 + 1;
                    if (i6 != 0) {
                        sb.append(", ");
                    }
                    sb.append(valueOf2);
                    i2++;
                    i6 = i7;
                }
                sb.append(10);
            }
            zza(sb, 3);
            sb.append("}\n");
        }
    }

    private static void zza(StringBuilder sb, int i, String str, Object obj) {
        if (obj != null) {
            zza(sb, i + 1);
            sb.append(str);
            sb.append(": ");
            sb.append(obj);
            sb.append(10);
        }
    }

    @Nullable
    private final String zzb(zzer zzer) {
        if (zzer == null) {
            return null;
        }
        return !zzil() ? zzer.toString() : zzb(zzer.zzif());
    }

    private final boolean zzil() {
        return this.zzacw.zzge().isLoggable(3);
    }

    public final /* bridge */ /* synthetic */ Context getContext() {
        return super.getContext();
    }

    /* access modifiers changed from: protected */
    @Nullable
    public final String zza(zzep zzep) {
        if (zzep == null) {
            return null;
        }
        if (!zzil()) {
            return zzep.toString();
        }
        return "Event{appId='" + zzep.zzti + "', name='" + zzbj(zzep.name) + "', params=" + zzb(zzep.zzafq) + "}";
    }

    /* access modifiers changed from: protected */
    public final String zza(zzke zzke) {
        if (zzke == null) {
            return "null";
        }
        StringBuilder sb = new StringBuilder();
        sb.append("\nevent_filter {\n");
        zza(sb, 0, "filter_id", (Object) zzke.zzarp);
        zza(sb, 0, MeasurementEvent.b, (Object) zzbj(zzke.zzarq));
        zza(sb, 1, "event_count_filter", zzke.zzart);
        sb.append("  filters {\n");
        for (zzkf zza : zzke.zzarr) {
            zza(sb, 2, zza);
        }
        zza(sb, 1);
        sb.append("}\n}\n");
        return sb.toString();
    }

    /* access modifiers changed from: protected */
    public final String zza(zzkh zzkh) {
        if (zzkh == null) {
            return "null";
        }
        StringBuilder sb = new StringBuilder();
        sb.append("\nproperty_filter {\n");
        zza(sb, 0, "filter_id", (Object) zzkh.zzarp);
        zza(sb, 0, "property_name", (Object) zzbl(zzkh.zzasf));
        zza(sb, 1, zzkh.zzasg);
        sb.append("}\n");
        return sb.toString();
    }

    /* access modifiers changed from: protected */
    public final String zza(zzkp zzkp) {
        zzkp zzkp2 = zzkp;
        StringBuilder sb = new StringBuilder();
        sb.append("\nbatch {\n");
        if (zzkp2.zzatf != null) {
            for (zzkq zzkq : zzkp2.zzatf) {
                if (!(zzkq == null || zzkq == null)) {
                    zza(sb, 1);
                    sb.append("bundle {\n");
                    zza(sb, 1, "protocol_version", (Object) zzkq.zzath);
                    zza(sb, 1, "platform", (Object) zzkq.zzatp);
                    zza(sb, 1, "gmp_version", (Object) zzkq.zzatt);
                    zza(sb, 1, "uploading_gmp_version", (Object) zzkq.zzatu);
                    zza(sb, 1, d.al, (Object) zzkq.zzauf);
                    zza(sb, 1, "gmp_app_id", (Object) zzkq.zzadm);
                    zza(sb, 1, "app_id", (Object) zzkq.zzti);
                    zza(sb, 1, "app_version", (Object) zzkq.zzth);
                    zza(sb, 1, "app_version_major", (Object) zzkq.zzaub);
                    zza(sb, 1, "firebase_instance_id", (Object) zzkq.zzado);
                    zza(sb, 1, "dev_cert_hash", (Object) zzkq.zzatx);
                    zza(sb, 1, "app_store", (Object) zzkq.zzadt);
                    zza(sb, 1, "upload_timestamp_millis", (Object) zzkq.zzatk);
                    zza(sb, 1, "start_timestamp_millis", (Object) zzkq.zzatl);
                    zza(sb, 1, "end_timestamp_millis", (Object) zzkq.zzatm);
                    zza(sb, 1, "previous_bundle_start_timestamp_millis", (Object) zzkq.zzatn);
                    zza(sb, 1, "previous_bundle_end_timestamp_millis", (Object) zzkq.zzato);
                    zza(sb, 1, "app_instance_id", (Object) zzkq.zzadl);
                    zza(sb, 1, "resettable_device_id", (Object) zzkq.zzatv);
                    zza(sb, 1, "device_id", (Object) zzkq.zzaue);
                    zza(sb, 1, "limited_ad_tracking", (Object) zzkq.zzatw);
                    zza(sb, 1, "os_version", (Object) zzkq.zzatq);
                    zza(sb, 1, "device_model", (Object) zzkq.zzatr);
                    zza(sb, 1, "user_default_language", (Object) zzkq.zzafn);
                    zza(sb, 1, "time_zone_offset_minutes", (Object) zzkq.zzats);
                    zza(sb, 1, "bundle_sequential_index", (Object) zzkq.zzaty);
                    zza(sb, 1, "service_upload", (Object) zzkq.zzatz);
                    zza(sb, 1, "health_monitor", (Object) zzkq.zzaek);
                    if (!(zzkq.zzaug == null || zzkq.zzaug.longValue() == 0)) {
                        zza(sb, 1, DeviceInfoResult.BUNDLE_KEY_ANDROID_ID, (Object) zzkq.zzaug);
                    }
                    if (zzkq.zzauj != null) {
                        zza(sb, 1, IjkMediaPlayer.OnNativeInvokeListener.ARG_RETRY_COUNTER, (Object) zzkq.zzauj);
                    }
                    zzks[] zzksArr = zzkq.zzatj;
                    if (zzksArr != null) {
                        for (zzks zzks : zzksArr) {
                            if (zzks != null) {
                                zza(sb, 2);
                                sb.append("user_property {\n");
                                zza(sb, 2, "set_timestamp_millis", (Object) zzks.zzaun);
                                zza(sb, 2, "name", (Object) zzbl(zzks.name));
                                zza(sb, 2, "string_value", (Object) zzks.zzajf);
                                zza(sb, 2, "int_value", (Object) zzks.zzate);
                                zza(sb, 2, "double_value", (Object) zzks.zzarc);
                                zza(sb, 2);
                                sb.append("}\n");
                            }
                        }
                    }
                    zzkm[] zzkmArr = zzkq.zzaua;
                    if (zzkmArr != null) {
                        for (zzkm zzkm : zzkmArr) {
                            if (zzkm != null) {
                                zza(sb, 2);
                                sb.append("audience_membership {\n");
                                zza(sb, 2, "audience_id", (Object) zzkm.zzarl);
                                zza(sb, 2, "new_audience", (Object) zzkm.zzasy);
                                zza(sb, 2, "current_data", zzkm.zzasw);
                                zza(sb, 2, "previous_data", zzkm.zzasx);
                                zza(sb, 2);
                                sb.append("}\n");
                            }
                        }
                    }
                    zzkn[] zzknArr = zzkq.zzati;
                    if (zzknArr != null) {
                        for (zzkn zzkn : zzknArr) {
                            if (zzkn != null) {
                                zza(sb, 2);
                                sb.append("event {\n");
                                zza(sb, 2, "name", (Object) zzbj(zzkn.name));
                                zza(sb, 2, "timestamp_millis", (Object) zzkn.zzatb);
                                zza(sb, 2, "previous_timestamp_millis", (Object) zzkn.zzatc);
                                zza(sb, 2, "count", (Object) zzkn.count);
                                zzko[] zzkoArr = zzkn.zzata;
                                if (zzkoArr != null) {
                                    for (zzko zzko : zzkoArr) {
                                        if (zzko != null) {
                                            zza(sb, 3);
                                            sb.append("param {\n");
                                            zza(sb, 3, "name", (Object) zzbk(zzko.name));
                                            zza(sb, 3, "string_value", (Object) zzko.zzajf);
                                            zza(sb, 3, "int_value", (Object) zzko.zzate);
                                            zza(sb, 3, "double_value", (Object) zzko.zzarc);
                                            zza(sb, 3);
                                            sb.append("}\n");
                                        }
                                    }
                                }
                                zza(sb, 2);
                                sb.append("}\n");
                            }
                        }
                    }
                    zza(sb, 1);
                    sb.append("}\n");
                }
            }
        }
        sb.append("}\n");
        return sb.toString();
    }

    public final /* bridge */ /* synthetic */ void zzab() {
        super.zzab();
    }

    /* access modifiers changed from: protected */
    @Nullable
    public final String zzb(Bundle bundle) {
        if (bundle == null) {
            return null;
        }
        if (!zzil()) {
            return bundle.toString();
        }
        StringBuilder sb = new StringBuilder();
        for (String str : bundle.keySet()) {
            sb.append(sb.length() != 0 ? ", " : "Bundle[{");
            sb.append(zzbk(str));
            sb.append("=");
            sb.append(bundle.get(str));
        }
        sb.append("}]");
        return sb.toString();
    }

    /* access modifiers changed from: protected */
    @Nullable
    public final String zzb(zzeu zzeu) {
        if (zzeu == null) {
            return null;
        }
        if (!zzil()) {
            return zzeu.toString();
        }
        return "origin=" + zzeu.origin + ",name=" + zzbj(zzeu.name) + ",params=" + zzb(zzeu.zzafq);
    }

    /* access modifiers changed from: protected */
    @Nullable
    public final String zzbj(String str) {
        if (str == null) {
            return null;
        }
        return !zzil() ? str : zza(str, AppMeasurement.Event.zzacy, AppMeasurement.Event.zzacx, zzaij);
    }

    /* access modifiers changed from: protected */
    @Nullable
    public final String zzbk(String str) {
        if (str == null) {
            return null;
        }
        return !zzil() ? str : zza(str, AppMeasurement.Param.zzada, AppMeasurement.Param.zzacz, zzaik);
    }

    /* access modifiers changed from: protected */
    @Nullable
    public final String zzbl(String str) {
        if (str == null) {
            return null;
        }
        if (!zzil()) {
            return str;
        }
        if (!str.startsWith("_exp_")) {
            return zza(str, AppMeasurement.UserProperty.zzadc, AppMeasurement.UserProperty.zzadb, zzail);
        }
        return "experiment_id" + Operators.BRACKET_START_STR + str + Operators.BRACKET_END_STR;
    }

    public final /* bridge */ /* synthetic */ Clock zzbt() {
        return super.zzbt();
    }

    public final /* bridge */ /* synthetic */ void zzfr() {
        super.zzfr();
    }

    public final /* bridge */ /* synthetic */ void zzfs() {
        super.zzfs();
    }

    public final /* bridge */ /* synthetic */ zzdu zzft() {
        return super.zzft();
    }

    public final /* bridge */ /* synthetic */ zzhk zzfu() {
        return super.zzfu();
    }

    public final /* bridge */ /* synthetic */ zzfb zzfv() {
        return super.zzfv();
    }

    public final /* bridge */ /* synthetic */ zzeo zzfw() {
        return super.zzfw();
    }

    public final /* bridge */ /* synthetic */ zzii zzfx() {
        return super.zzfx();
    }

    public final /* bridge */ /* synthetic */ zzif zzfy() {
        return super.zzfy();
    }

    public final /* bridge */ /* synthetic */ zzfc zzfz() {
        return super.zzfz();
    }

    public final /* bridge */ /* synthetic */ zzfe zzga() {
        return super.zzga();
    }

    public final /* bridge */ /* synthetic */ zzka zzgb() {
        return super.zzgb();
    }

    public final /* bridge */ /* synthetic */ zzjh zzgc() {
        return super.zzgc();
    }

    public final /* bridge */ /* synthetic */ zzgg zzgd() {
        return super.zzgd();
    }

    public final /* bridge */ /* synthetic */ zzfg zzge() {
        return super.zzge();
    }

    public final /* bridge */ /* synthetic */ zzfr zzgf() {
        return super.zzgf();
    }

    public final /* bridge */ /* synthetic */ zzef zzgg() {
        return super.zzgg();
    }

    /* access modifiers changed from: protected */
    public final boolean zzhf() {
        return false;
    }
}
