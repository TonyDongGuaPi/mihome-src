package com.xiaomi.smarthome.wificonfig;

import android.net.wifi.ScanResult;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.IBinder;
import android.text.TextUtils;
import com.miui.whetstone.WhetstoneResult;
import com.miui.whetstone.WhetstoneResultBinder;
import com.xiaomi.miio.MiioLocalAPI;
import com.xiaomi.miio.MiioLocalResponse;
import com.xiaomi.miio.MiioLocalResult;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.device.DeviceFactory;
import com.xiaomi.smarthome.device.KuailianManager;
import com.xiaomi.smarthome.frame.core.CoreApi;
import com.xiaomi.smarthome.framework.log.MyLog;
import com.xiaomi.smarthome.framework.wifiaccount.WifiAccountManager;
import com.xiaomi.smarthome.library.common.network.WifiUtil;
import com.xiaomi.smarthome.miio.WifiAccount;
import com.xiaomi.smarthome.wificonfig.WifiSettingUtils;
import java.lang.reflect.InvocationTargetException;

public class WifiSettingNormal extends BaseWifiSetting {

    /* renamed from: a  reason: collision with root package name */
    private String f22949a;
    private long b;

    public void startConnection() {
        ScanResult scanResult;
        int i;
        long j;
        WifiManager wifiManager = (WifiManager) getSystemService("wifi");
        if (wifiManager.isWifiEnabled()) {
            this.mStartTime = System.currentTimeMillis();
            this.f22949a = WifiUtil.b(this);
            WifiInfo connectionInfo = wifiManager.getConnectionInfo();
            if (connectionInfo != null) {
                if (this.mScanResult != null) {
                    for (WifiSettingUtils.KuailianScanResult kuailianScanResult : this.mScanResult) {
                        if (WifiSettingUtils.a(connectionInfo.getSSID(), kuailianScanResult.f22964a.SSID) && !TextUtils.isEmpty(kuailianScanResult.f22964a.BSSID)) {
                            this.f22949a = kuailianScanResult.f22964a.BSSID;
                        }
                    }
                }
                WifiAccount.WifiItem a2 = WifiAccountManager.a().a(this.f22949a);
                if (a2 != null) {
                    KuailianManager.a().a(this, true, this.mKuailianScanResult, (String) null);
                    if (this.mRouterInfo == null) {
                        scanResult = this.mChooseUnconnWifi ? ((WifiSettingUtils.KuailianScanResult) this.mUnconnectResult.get(this.mPosition)).f22964a : ((WifiSettingUtils.KuailianScanResult) this.mScanResult.get(this.mPosition)).f22964a;
                    } else {
                        scanResult = null;
                    }
                    if (this.mKuailianScanResult == null) {
                        try {
                            j = Long.valueOf(CoreApi.a().s()).longValue();
                        } catch (Exception unused) {
                            j = 0;
                        }
                        if (j > 0) {
                            if (this.mIsMiui && !this.mIsShowPasswd && !this.mChooseNopasswdWifi && this.mRouterInfo == null) {
                                try {
                                    this.mWhetstoneClass.getDeclaredMethod("wifiSmartConfigAsyncWithUid", new Class[]{String.class, String.class, String.class, String.class, Integer.TYPE, IBinder.class}).invoke((Object) null, new Object[]{scanResult.SSID, null, scanResult.BSSID, scanResult.capabilities, Integer.valueOf((int) j), new WhetstoneResultBinder() {
                                        public void onResult(WhetstoneResult whetstoneResult) {
                                            super.onResult(whetstoneResult);
                                        }
                                    }});
                                } catch (IllegalAccessException | NoSuchMethodException | InvocationTargetException unused2) {
                                }
                            } else if (a2 != null) {
                                MiioLocalAPI.a(a2.c, a2.d == null ? "" : a2.d, (String) null, a2.f, j, (MiioLocalResponse) new MiioLocalResponse() {
                                    public void a(MiioLocalResult miioLocalResult) {
                                    }
                                });
                            }
                        } else if (!this.mIsMiui || this.mIsShowPasswd || this.mChooseNopasswdWifi || this.mRouterInfo != null) {
                            MiioLocalAPI.a(a2.c, a2.d == null ? "" : a2.d, (String) null, a2.f, (MiioLocalResponse) new MiioLocalResponse() {
                                public void a(MiioLocalResult miioLocalResult) {
                                }
                            });
                        } else {
                            this.mWhetstoneClass.getDeclaredMethod("wifiSmartConfigAsync", new Class[]{String.class, String.class, String.class, String.class, Integer.TYPE, WhetstoneResultBinder.class}).invoke((Object) null, new Object[]{scanResult.SSID, null, scanResult.BSSID, scanResult.capabilities, new WhetstoneResultBinder() {
                                public void onResult(WhetstoneResult whetstoneResult) {
                                    super.onResult(whetstoneResult);
                                }
                            }});
                        }
                    } else {
                        String h = DeviceFactory.h(this.mKuailianScanResult);
                        try {
                            i = Integer.valueOf(CoreApi.a().s()).intValue();
                        } catch (Exception unused3) {
                            i = 0;
                        }
                        if (i > 0) {
                            if (!this.mIsMiui || this.mIsShowPasswd || this.mChooseNopasswdWifi || this.mRouterInfo != null) {
                                MiioLocalAPI.a(a2.c, a2.d == null ? "" : a2.d, (String) null, a2.f, h, (long) i, (MiioLocalResponse) new MiioLocalResponse() {
                                    public void a(MiioLocalResult miioLocalResult) {
                                    }
                                });
                            } else {
                                this.mWhetstoneClass.getDeclaredMethod("wifiSmartConfigMacAsyncWithUid", new Class[]{String.class, String.class, String.class, String.class, String.class, Integer.TYPE, IBinder.class}).invoke((Object) null, new Object[]{scanResult.SSID, null, scanResult.BSSID, scanResult.capabilities, h, Integer.valueOf(i), new WhetstoneResultBinder() {
                                    public void onResult(WhetstoneResult whetstoneResult) {
                                        super.onResult(whetstoneResult);
                                    }
                                }});
                            }
                        } else if (!this.mIsMiui || this.mIsShowPasswd || this.mChooseNopasswdWifi || this.mRouterInfo != null) {
                            MiioLocalAPI.a(a2.c, a2.d == null ? "" : a2.d, (String) null, a2.f, h, (MiioLocalResponse) new MiioLocalResponse() {
                                public void a(MiioLocalResult miioLocalResult) {
                                }
                            });
                        } else {
                            this.mWhetstoneClass.getDeclaredMethod("wifiSmartConfigMacAsync", new Class[]{String.class, String.class, String.class, String.class, String.class, WhetstoneResultBinder.class}).invoke((Object) null, new Object[]{scanResult.SSID, null, scanResult.BSSID, scanResult.capabilities, new WhetstoneResultBinder() {
                                public void onResult(WhetstoneResult whetstoneResult) {
                                    super.onResult(whetstoneResult);
                                }
                            }});
                        }
                    }
                    this.mStartTime = System.currentTimeMillis();
                    WifiDeviceFinder.m.clear();
                }
            }
        }
    }

    public String getConnStatusText() {
        long currentTimeMillis = System.currentTimeMillis() - this.mStartTime;
        if (currentTimeMillis < 15000) {
            return getString(R.string.kuailian_sub_main_title_3_1);
        }
        if (currentTimeMillis < 30000) {
            return getString(R.string.kuailian_sub_main_title_3_2);
        }
        if (currentTimeMillis < 45000) {
            return getString(R.string.kuailian_sub_main_title_3_3);
        }
        return getString(R.string.kuailian_sub_main_title_3_4);
    }

    public void startWriteLog(boolean z) {
        MyLog.f("kuailian_start - normal");
    }
}
