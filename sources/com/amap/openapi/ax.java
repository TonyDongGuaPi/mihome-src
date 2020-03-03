package com.amap.openapi;

import android.content.ContentResolver;
import android.content.Context;
import android.os.Build;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.telephony.CellIdentityCdma;
import android.telephony.CellIdentityGsm;
import android.telephony.CellIdentityLte;
import android.telephony.CellIdentityWcdma;
import android.telephony.CellInfo;
import android.telephony.CellInfoCdma;
import android.telephony.CellInfoGsm;
import android.telephony.CellInfoLte;
import android.telephony.CellInfoWcdma;
import android.telephony.CellLocation;
import android.telephony.NeighboringCellInfo;
import android.telephony.SignalStrength;
import android.telephony.TelephonyManager;
import android.telephony.cdma.CdmaCellLocation;
import android.telephony.gsm.GsmCellLocation;
import com.xiaomi.smarthome.auth.AuthCode;
import java.util.List;

public class ax {
    private static byte a(TelephonyManager telephonyManager) {
        try {
            return (byte) telephonyManager.getNetworkType();
        } catch (Throwable unused) {
            return 0;
        }
    }

    private static int a(int i) {
        return (i * 2) + AuthCode.n;
    }

    public static CellInfo a(List<CellInfo> list) {
        if (Build.VERSION.SDK_INT < 17) {
            return null;
        }
        if (list != null) {
            for (CellInfo next : list) {
                if (((next instanceof CellInfoLte) || (next instanceof CellInfoCdma) || (next instanceof CellInfoGsm)) && next.isRegistered()) {
                    return next;
                }
                if (Build.VERSION.SDK_INT >= 18 && (next instanceof CellInfoWcdma) && next.isRegistered()) {
                    return next;
                }
            }
        }
        return null;
    }

    public static void a(@NonNull Context context, @NonNull q qVar, @Nullable CellLocation cellLocation, @Nullable SignalStrength signalStrength, @Nullable List<CellInfo> list) {
        List list2;
        TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService("phone");
        qVar.a(a(telephonyManager), b(telephonyManager));
        if (cellLocation != null) {
            if (cellLocation instanceof GsmCellLocation) {
                try {
                    list2 = telephonyManager.getNeighboringCellInfo();
                } catch (Throwable unused) {
                }
                a(qVar, cellLocation, signalStrength, list2);
            }
            list2 = null;
            a(qVar, cellLocation, signalStrength, list2);
        }
        if (list != null) {
            a(qVar, list);
        }
    }

    private static void a(@NonNull q qVar, @NonNull CellLocation cellLocation, @Nullable SignalStrength signalStrength, @Nullable List<NeighboringCellInfo> list) {
        if (cellLocation instanceof GsmCellLocation) {
            GsmCellLocation gsmCellLocation = (GsmCellLocation) cellLocation;
            T wVar = new w();
            wVar.c = gsmCellLocation.getLac();
            wVar.d = gsmCellLocation.getCid();
            if (Build.VERSION.SDK_INT >= 9) {
                wVar.i = gsmCellLocation.getPsc();
            }
            if (signalStrength != null) {
                int gsmSignalStrength = signalStrength.getGsmSignalStrength();
                wVar.e = gsmSignalStrength == 99 ? Integer.MAX_VALUE : a(gsmSignalStrength);
            }
            r rVar = new r();
            rVar.f4741a = 1;
            rVar.f = wVar;
            rVar.b = 1;
            rVar.c = 0;
            qVar.c.add(rVar);
            if (list != null) {
                for (NeighboringCellInfo next : list) {
                    T wVar2 = new w();
                    wVar2.c = next.getLac();
                    wVar2.d = next.getCid();
                    wVar2.e = next.getRssi();
                    wVar2.i = next.getPsc();
                    r rVar2 = new r();
                    rVar2.f4741a = 1;
                    rVar2.f = wVar2;
                    rVar2.b = 0;
                    rVar2.c = 0;
                    qVar.c.add(rVar2);
                }
            }
        } else if (cellLocation instanceof CdmaCellLocation) {
            CdmaCellLocation cdmaCellLocation = (CdmaCellLocation) cellLocation;
            T pVar = new p();
            pVar.d = cdmaCellLocation.getBaseStationLatitude();
            pVar.e = cdmaCellLocation.getBaseStationLongitude();
            pVar.f4739a = cdmaCellLocation.getSystemId();
            pVar.b = cdmaCellLocation.getNetworkId();
            pVar.c = cdmaCellLocation.getBaseStationId();
            if (signalStrength != null) {
                pVar.f = signalStrength.getCdmaDbm();
            }
            r rVar3 = new r();
            rVar3.f4741a = 2;
            rVar3.f = pVar;
            rVar3.b = 1;
            rVar3.c = 0;
            qVar.c.add(rVar3);
        }
    }

    private static void a(@NonNull q qVar, @NonNull List<CellInfo> list) {
        T zVar;
        r rVar;
        byte b;
        if (Build.VERSION.SDK_INT >= 17) {
            for (CellInfo next : list) {
                if (next instanceof CellInfoCdma) {
                    CellInfoCdma cellInfoCdma = (CellInfoCdma) next;
                    CellIdentityCdma cellIdentity = cellInfoCdma.getCellIdentity();
                    T pVar = new p();
                    pVar.d = cellIdentity.getLatitude();
                    pVar.e = cellIdentity.getLongitude();
                    pVar.f4739a = cellIdentity.getSystemId();
                    pVar.b = cellIdentity.getNetworkId();
                    pVar.c = cellIdentity.getBasestationId();
                    pVar.f = cellInfoCdma.getCellSignalStrength().getCdmaDbm();
                    pVar.g = cellInfoCdma.getCellSignalStrength().getAsuLevel();
                    rVar = new r();
                    rVar.f4741a = 2;
                    rVar.f = pVar;
                } else {
                    if (next instanceof CellInfoGsm) {
                        CellInfoGsm cellInfoGsm = (CellInfoGsm) next;
                        CellIdentityGsm cellIdentity2 = cellInfoGsm.getCellIdentity();
                        zVar = new w();
                        zVar.f4747a = cellIdentity2.getMcc();
                        zVar.b = cellIdentity2.getMnc();
                        zVar.c = cellIdentity2.getLac();
                        zVar.d = cellIdentity2.getCid();
                        zVar.e = cellInfoGsm.getCellSignalStrength().getDbm();
                        zVar.h = cellInfoGsm.getCellSignalStrength().getAsuLevel();
                        if (Build.VERSION.SDK_INT >= 24) {
                            zVar.f = cellIdentity2.getArfcn();
                            zVar.g = cellIdentity2.getBsic();
                        }
                        rVar = new r();
                        rVar.f4741a = 1;
                    } else {
                        if (next instanceof CellInfoLte) {
                            CellInfoLte cellInfoLte = (CellInfoLte) next;
                            CellIdentityLte cellIdentity3 = cellInfoLte.getCellIdentity();
                            zVar = new x();
                            zVar.f4748a = cellIdentity3.getMcc();
                            zVar.b = cellIdentity3.getMnc();
                            zVar.c = cellIdentity3.getTac();
                            zVar.d = cellIdentity3.getCi();
                            zVar.e = cellIdentity3.getPci();
                            zVar.f = cellInfoLte.getCellSignalStrength().getDbm();
                            zVar.h = cellInfoLte.getCellSignalStrength().getAsuLevel();
                            if (Build.VERSION.SDK_INT >= 24) {
                                zVar.g = cellIdentity3.getEarfcn();
                            }
                            rVar = new r();
                            b = 3;
                        } else if (Build.VERSION.SDK_INT >= 18 && (next instanceof CellInfoWcdma)) {
                            CellInfoWcdma cellInfoWcdma = (CellInfoWcdma) next;
                            CellIdentityWcdma cellIdentity4 = cellInfoWcdma.getCellIdentity();
                            zVar = new z();
                            zVar.f4750a = cellIdentity4.getMcc();
                            zVar.b = cellIdentity4.getMnc();
                            zVar.c = cellIdentity4.getLac();
                            zVar.d = cellIdentity4.getCid();
                            zVar.e = cellIdentity4.getPsc();
                            zVar.f = cellInfoWcdma.getCellSignalStrength().getDbm();
                            zVar.h = cellInfoWcdma.getCellSignalStrength().getAsuLevel();
                            if (Build.VERSION.SDK_INT >= 24) {
                                zVar.g = cellIdentity4.getUarfcn();
                            }
                            rVar = new r();
                            b = 4;
                        }
                        rVar.f4741a = b;
                    }
                    rVar.f = zVar;
                }
                rVar.b = next.isRegistered() ? (byte) 1 : 0;
                rVar.c = 1;
                qVar.c.add(rVar);
            }
        }
    }

    public static boolean a(Context context) {
        try {
            ContentResolver contentResolver = context.getContentResolver();
            return (Build.VERSION.SDK_INT >= 17 ? Settings.Global.getInt(contentResolver, "airplane_mode_on", 0) : Settings.System.getInt(contentResolver, "airplane_mode_on", 0)) != 0;
        } catch (Throwable unused) {
            return false;
        }
    }

    public static boolean a(CellInfo cellInfo, CellInfo cellInfo2) {
        if (cellInfo == cellInfo2) {
            return true;
        }
        if (!(cellInfo == null || cellInfo2 == null || Build.VERSION.SDK_INT < 17)) {
            if ((cellInfo instanceof CellInfoGsm) && (cellInfo2 instanceof CellInfoGsm)) {
                CellIdentityGsm cellIdentity = ((CellInfoGsm) cellInfo).getCellIdentity();
                CellIdentityGsm cellIdentity2 = ((CellInfoGsm) cellInfo2).getCellIdentity();
                return cellIdentity.getCid() == cellIdentity2.getCid() && cellIdentity.getLac() == cellIdentity2.getLac();
            } else if ((cellInfo instanceof CellInfoCdma) && (cellInfo2 instanceof CellInfoCdma)) {
                CellIdentityCdma cellIdentity3 = ((CellInfoCdma) cellInfo).getCellIdentity();
                CellIdentityCdma cellIdentity4 = ((CellInfoCdma) cellInfo2).getCellIdentity();
                return cellIdentity3.getBasestationId() == cellIdentity4.getBasestationId() && cellIdentity3.getNetworkId() == cellIdentity4.getNetworkId() && cellIdentity3.getSystemId() == cellIdentity4.getSystemId();
            } else if ((cellInfo instanceof CellInfoLte) && (cellInfo2 instanceof CellInfoLte)) {
                CellIdentityLte cellIdentity5 = ((CellInfoLte) cellInfo).getCellIdentity();
                CellIdentityLte cellIdentity6 = ((CellInfoLte) cellInfo2).getCellIdentity();
                return cellIdentity5.getCi() == cellIdentity6.getCi() && cellIdentity5.getTac() == cellIdentity6.getTac();
            } else if (Build.VERSION.SDK_INT >= 18 && (cellInfo instanceof CellInfoWcdma) && (cellInfo2 instanceof CellInfoWcdma)) {
                CellIdentityWcdma cellIdentity7 = ((CellInfoWcdma) cellInfo).getCellIdentity();
                CellIdentityWcdma cellIdentity8 = ((CellInfoWcdma) cellInfo2).getCellIdentity();
                return cellIdentity7.getCid() == cellIdentity8.getCid() && cellIdentity7.getLac() == cellIdentity8.getLac();
            }
        }
    }

    public static boolean a(CellLocation cellLocation, CellLocation cellLocation2) {
        if (cellLocation == cellLocation2) {
            return true;
        }
        if (!(cellLocation == null || cellLocation2 == null)) {
            if ((cellLocation instanceof GsmCellLocation) && (cellLocation2 instanceof GsmCellLocation)) {
                GsmCellLocation gsmCellLocation = (GsmCellLocation) cellLocation;
                GsmCellLocation gsmCellLocation2 = (GsmCellLocation) cellLocation2;
                return gsmCellLocation.getCid() == gsmCellLocation2.getCid() && gsmCellLocation.getLac() == gsmCellLocation2.getLac();
            } else if ((cellLocation instanceof CdmaCellLocation) && (cellLocation2 instanceof CdmaCellLocation)) {
                CdmaCellLocation cdmaCellLocation = (CdmaCellLocation) cellLocation;
                CdmaCellLocation cdmaCellLocation2 = (CdmaCellLocation) cellLocation2;
                return cdmaCellLocation.getBaseStationId() == cdmaCellLocation2.getBaseStationId() && cdmaCellLocation.getNetworkId() == cdmaCellLocation2.getNetworkId() && cdmaCellLocation.getSystemId() == cdmaCellLocation2.getSystemId();
            }
        }
    }

    private static String b(TelephonyManager telephonyManager) {
        String str;
        try {
            str = telephonyManager.getNetworkOperator();
        } catch (Throwable unused) {
            str = null;
        }
        return str == null ? "" : str;
    }
}
