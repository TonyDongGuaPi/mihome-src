package com.tutk;

import com.debug.SDKLog;
import com.tutk.IOTC.AVAPIs;
import com.tutk.IOTC.IOTCAPIs;
import com.tutk.IOTC.RDTAPIs;

public class P2PUtils {
    private static volatile int REF_COUNT = 0;
    public static final String TAG = "P2P";
    public static volatile boolean sInit = false;

    public static synchronized void initial() {
        synchronized (P2PUtils.class) {
            if (REF_COUNT == 0) {
                sInit = true;
                SDKLog.d(TAG, "initial");
                IOTCAPIs.IOTC_Setup_Session_Alive_Timeout(16);
                int currentTimeMillis = (int) ((System.currentTimeMillis() % 10000) + 10000);
                int IOTC_Initialize2 = IOTCAPIs.IOTC_Initialize2(currentTimeMillis);
                SDKLog.b(TAG, "IOTC_Initialize2 port:" + currentTimeMillis + " ret:" + IOTC_Initialize2);
                if (IOTC_Initialize2 == 0 || IOTC_Initialize2 == -3) {
                    int avInitialize = AVAPIs.avInitialize(128);
                    AVAPIs.avClientSetMaxBufSize(5000);
                    SDKLog.b(TAG, "avInitialize ret:" + avInitialize);
                    if (avInitialize < 0) {
                        SDKLog.d(TAG, "avInitialize :" + getError(avInitialize));
                        return;
                    }
                    int RDT_Initialize = RDTAPIs.RDT_Initialize();
                    SDKLog.b(TAG, "RDT_Initialize ret:" + RDT_Initialize);
                    if (RDT_Initialize < 0) {
                        SDKLog.d(TAG, "RDT_Initialize :" + getError(RDT_Initialize));
                        return;
                    }
                } else {
                    SDKLog.d(TAG, "IOTC_Initialize2 :" + getError(IOTC_Initialize2));
                    return;
                }
            }
            REF_COUNT++;
        }
    }

    public static synchronized void unInitial() {
        synchronized (P2PUtils.class) {
            REF_COUNT--;
            if (REF_COUNT <= 0) {
                sInit = false;
                SDKLog.b(TAG, "RDT_Initialize ret:" + RDTAPIs.RDT_DeInitialize());
                SDKLog.b(TAG, "avDeInitialize ret:" + AVAPIs.avDeInitialize());
                SDKLog.b(TAG, "IOTC_DeInitialize ret:" + IOTCAPIs.IOTC_DeInitialize());
                REF_COUNT = 0;
            }
        }
    }

    public static String getError(int i) {
        SDKLog.d(TAG, "getError:" + i);
        if (i == -26) {
            return "Channel isn't on.Please open it by IOTC_Session_Channel_ON() or IOTC_Session_Get_Free_Channel()";
        }
        if (i == -10) {
            return "This UID is unlicense,Check your UID.";
        }
        switch (i) {
            case -43:
                return "Server doesn't support UDP relay mode,So client can't use UDP relay to connect to a device.";
            case -42:
                return "Client can't connect to a device via Lan, P2P, and Relay mode";
            case -41:
                return "Network is unreachable,Please check your network,Retry...";
            case -40:
                return "This UID's license doesn't support TCP.";
            default:
                switch (i) {
                    case -33:
                        return "Device can't connect to server by TCP.";
                    case -32:
                        return "Device can't connect to Master.";
                    case -31:
                        return "All channels are occupied,Please release some channel";
                    default:
                        switch (i) {
                            case -24:
                                return "Device doesn't listen or the sessions of device reach to maximum.Please release the session and check the device wheather it listen or not.";
                            case -23:
                                return "We can't receive an acknowledgement character within a TIMEOUT";
                            case -22:
                                return "Session is closed by remote so we can't access.Please close it or establish session again.";
                            default:
                                switch (i) {
                                    case -19:
                                        return "Device didn't register on server, so we can't find device.Please check the device again.";
                                    case -18:
                                        return "The amount of session reach to the maximum.It cannot be connected unless the session is released.";
                                    default:
                                        switch (i) {
                                            case -14:
                                            case -13:
                                                return "This SID is invalid.Please check it again.";
                                            case -12:
                                                return "Please initialize the IOTCAPI first.";
                                            default:
                                                switch (i) {
                                                    case -5:
                                                        return "Can't create thread.";
                                                    case -4:
                                                        return "Can't create mutex.";
                                                    case -3:
                                                        return "Already initialized";
                                                    case -2:
                                                        return "Can't resolve hostname.";
                                                    case -1:
                                                        return "Master doesn't respond,Please check the network wheather it could connect to the Internet.";
                                                    default:
                                                        return "";
                                                }
                                        }
                                }
                        }
                }
        }
    }
}
