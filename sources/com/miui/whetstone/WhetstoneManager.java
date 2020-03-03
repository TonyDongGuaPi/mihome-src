package com.miui.whetstone;

import android.graphics.Bitmap;
import android.os.IBinder;
import android.os.RemoteException;
import android.os.ServiceManager;
import android.view.InputEvent;
import com.miui.whetstone.IWhetstone;
import com.miui.whetstone.strategy.WhetstonePackageInfo;

public abstract class WhetstoneManager {
    public static final String SERVICE_NAME = "miui.whetstone";
    /* access modifiers changed from: private */
    public static IWhetstone ws = getService();

    static class WhetstoneManagerDeath implements IBinder.DeathRecipient {
        private IWhetstone mToken;

        WhetstoneManagerDeath(IWhetstone iWhetstone) {
            this.mToken = iWhetstone;
        }

        public void binderDied() {
            IWhetstone unused = WhetstoneManager.ws = null;
            if (this.mToken != null) {
                this.mToken.asBinder().unlinkToDeath(this, 0);
            }
        }
    }

    private static IWhetstone getService() {
        if (ws == null) {
            ws = IWhetstone.Stub.asInterface(ServiceManager.getService(SERVICE_NAME));
            try {
                if (ws != null) {
                    ws.asBinder().linkToDeath(new WhetstoneManagerDeath(ws), 0);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return ws;
    }

    private static void checkService() {
        getService();
    }

    public static boolean isBitmapCacheNeeded() {
        checkService();
        if (ws != null) {
            try {
                return ws.isNeeded(1);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    public static boolean isOPENGLDisableNeeded() {
        checkService();
        if (ws != null) {
            try {
                return ws.isNeeded(2);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    public static void setWhetstoneLeve(int i) {
        checkService();
        if (ws != null) {
            try {
                ws.setWhetstoneLevel(i);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static int getWhetstoneLeve() {
        checkService();
        if (ws != null) {
            try {
                return ws.getWhetstoneLevel();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return 0;
    }

    public static WhetstonePackageInfo getWhetstonePackage(IBinder iBinder) {
        checkService();
        if (ws != null) {
            try {
                return ws.getWhetstonePackage(iBinder);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public static IBinder getApplicationThread(int i) {
        checkService();
        if (ws != null) {
            try {
                return ws.getApplicationThread(i);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public static void wifiSmartConfigAsync(String str, String str2, String str3, String str4, WhetstoneResultBinder whetstoneResultBinder) {
        checkService();
        if (ws != null) {
            try {
                ws.wifiSmartConfigStartAsync(str, str2, str3, str4, (String) null, whetstoneResultBinder);
            } catch (RemoteException e) {
                e.printStackTrace();
                whetstoneResultBinder.onResult(new WhetstoneResult(-1));
            }
        }
    }

    public static void wifiSmartConfigMacAsync(String str, String str2, String str3, String str4, String str5, WhetstoneResultBinder whetstoneResultBinder) {
        checkService();
        if (ws != null) {
            try {
                ws.wifiSmartConfigStartAsync(str, str2, str3, str4, str5, whetstoneResultBinder);
            } catch (RemoteException e) {
                whetstoneResultBinder.onResult(new WhetstoneResult(-1));
                e.printStackTrace();
            }
        }
    }

    public static void wifiSmartConfigStop() {
        checkService();
        if (ws != null) {
            try {
                ws.wifiSmartConfigStop();
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    }

    public static boolean wifiSmartConfigSupport() {
        checkService();
        if (ws != null) {
            try {
                return ws.wifiSmartConfigSupport();
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    public static Bitmap screenShot(int i, int i2) {
        checkService();
        if (ws != null) {
            try {
                return ws.screenShot(i, i2);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public static boolean injectInputEvent(InputEvent inputEvent, int i) {
        checkService();
        if (ws != null) {
            try {
                return ws.injectInputEvent(inputEvent, i);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    public static boolean putSetting(String str, String str2, String str3) {
        checkService();
        if (ws != null) {
            try {
                return ws.putSetting(str, str2, str3);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
        return false;
    }
}
