package com.xiaomi.smarthome.device.bluetooth.search;

import android.os.RemoteException;
import android.util.Log;
import com.xiaomi.smarthome.bluetooth.XmBluetoothDevice;
import com.xiaomi.smarthome.bluetooth.XmBluetoothSearchManager;
import com.xiaomi.smarthome.core.entity.bluetooth.SearchRequest;
import com.xiaomi.smarthome.core.entity.bluetooth.SearchResult;
import com.xiaomi.smarthome.core.server.bluetooth.SearchResponse;
import com.xiaomi.smarthome.device.bluetooth.BleObjectTranslator;
import com.xiaomi.smarthome.device.bluetooth.utils.BluetoothHelper;
import com.xiaomi.smarthome.frame.plugin.host.PluginBluetoothSearchManagerHostApi;

@Deprecated
public class BluetoothSearchManager extends PluginBluetoothSearchManagerHostApi {
    public void startScanBluetooth(final XmBluetoothSearchManager.BluetoothHandler bluetoothHandler) {
        if (bluetoothHandler != null) {
            startScanBluetooth(new XmBluetoothSearchManager.XmBluetoothSearchRequest(bluetoothHandler), new XmBluetoothSearchManager.XmBluetoothSearchResponse() {
                public void onSearchStarted() {
                    try {
                        bluetoothHandler.onSearchStarted();
                    } catch (Throwable unused) {
                    }
                }

                public void onDeviceFounded(XmBluetoothDevice xmBluetoothDevice) {
                    try {
                        bluetoothHandler.onDeviceFounded(xmBluetoothDevice);
                    } catch (Throwable unused) {
                    }
                }

                public void onSearchStopped() {
                    try {
                        bluetoothHandler.onSearchStopped();
                    } catch (Throwable unused) {
                    }
                }

                public void onSearchCanceled() {
                    try {
                        bluetoothHandler.onSearchStopped();
                    } catch (Throwable unused) {
                    }
                }
            });
            return;
        }
        throw new NullPointerException("handler null");
    }

    public void stopScanBluetooth(XmBluetoothSearchManager.BluetoothHandler bluetoothHandler) {
        Log.i("stopScan", "BSM stop");
        BluetoothHelper.b();
    }

    public void startScanBluetoothImmediately(XmBluetoothSearchManager.BluetoothHandler bluetoothHandler) {
        startScanBluetooth(bluetoothHandler);
    }

    private SearchRequest a(XmBluetoothSearchManager.XmBluetoothSearchRequest xmBluetoothSearchRequest) {
        SearchRequest.Builder builder = new SearchRequest.Builder();
        if (xmBluetoothSearchRequest.taskType == 1) {
            builder.a(xmBluetoothSearchRequest.taskDuration);
        } else if (xmBluetoothSearchRequest.taskType == 2) {
            builder.b(xmBluetoothSearchRequest.taskDuration);
        } else {
            throw new IllegalArgumentException("invalid request type");
        }
        return builder.a();
    }

    public void startScanBluetooth(XmBluetoothSearchManager.XmBluetoothSearchRequest xmBluetoothSearchRequest, final XmBluetoothSearchManager.XmBluetoothSearchResponse xmBluetoothSearchResponse) {
        if (xmBluetoothSearchRequest == null || xmBluetoothSearchResponse == null) {
            throw new NullPointerException("request or response null");
        }
        BluetoothHelper.a(a(xmBluetoothSearchRequest), (SearchResponse) new SearchResponse.Stub() {
            public void onSearchStarted() throws RemoteException {
                xmBluetoothSearchResponse.onSearchStarted();
            }

            public void onDeviceFounded(SearchResult searchResult) throws RemoteException {
                xmBluetoothSearchResponse.onDeviceFounded(BleObjectTranslator.a(searchResult));
            }

            public void onSearchStopped() throws RemoteException {
                xmBluetoothSearchResponse.onSearchStopped();
            }

            public void onSearchCanceled() throws RemoteException {
                xmBluetoothSearchResponse.onSearchCanceled();
            }
        });
    }

    public void stopScanBluetooth() {
        Log.i("stopScan", "BSM stop");
        BluetoothHelper.b();
    }
}
