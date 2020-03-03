package com.xiaomi.smarthome.core.server.internal.upnp;

import android.os.Bundle;
import android.os.RemoteException;
import com.xiaomi.smarthome.core.client.IClientCallback;
import com.xiaomi.smarthome.core.entity.Error;
import com.xiaomi.smarthome.core.entity.net.KeyValuePair;
import com.xiaomi.smarthome.core.entity.upnp.UPnPRequest;
import com.xiaomi.smarthome.core.server.CoreAsyncTask;
import java.util.Iterator;
import org.cybergarage.upnp.Action;
import org.cybergarage.upnp.Argument;
import org.cybergarage.upnp.ArgumentList;
import org.cybergarage.upnp.Device;
import org.cybergarage.upnp.Service;
import org.cybergarage.upnp.UPnPStatus;
import org.json.JSONException;
import org.json.JSONObject;

public enum UPnPService {
    INSTANCE;

    public String getRootNodeValue(String str, String str2) {
        return UPnPDeviceManager.a().a(str, str2);
    }

    public void callUPnPApi(final UPnPRequest uPnPRequest, final IClientCallback iClientCallback) {
        new CoreAsyncTask() {
            public void onCancel() {
            }

            public void run() {
                Bundle bundle = new Bundle();
                if (uPnPRequest != null) {
                    Device a2 = UPnPDeviceManager.a().a(uPnPRequest.f14000a);
                    if (a2 != null) {
                        Service service = a2.getService(uPnPRequest.b);
                        if (service != null) {
                            Action action = service.getAction(uPnPRequest.c);
                            if (action != null) {
                                if (uPnPRequest.d != null && !uPnPRequest.d.isEmpty()) {
                                    ArgumentList argumentList = action.getArgumentList();
                                    Iterator<KeyValuePair> it = uPnPRequest.d.iterator();
                                    while (it.hasNext()) {
                                        KeyValuePair next = it.next();
                                        Argument argument = argumentList.getArgument(next.a());
                                        if (argument != null) {
                                            argument.setValue(next.b());
                                        }
                                    }
                                }
                                if (action.postControlAction()) {
                                    ArgumentList outputArgumentList = action.getOutputArgumentList();
                                    if (iClientCallback != null) {
                                        JSONObject jSONObject = new JSONObject();
                                        Iterator it2 = outputArgumentList.iterator();
                                        while (it2.hasNext()) {
                                            try {
                                                Argument argument2 = (Argument) it2.next();
                                                if (argument2 != null) {
                                                    String name = argument2.getName();
                                                    String value = argument2.getValue();
                                                    if (name != null) {
                                                        if (value == null) {
                                                            value = "";
                                                        }
                                                        jSONObject.put(name, value);
                                                    }
                                                }
                                            } catch (JSONException unused) {
                                            }
                                        }
                                        bundle.putString("result", jSONObject.toString());
                                        try {
                                            iClientCallback.onSuccess(bundle);
                                        } catch (RemoteException unused2) {
                                        }
                                    }
                                } else {
                                    UPnPStatus controlStatus = action.getControlStatus();
                                    if (iClientCallback != null) {
                                        bundle.putParcelable("error", new Error(controlStatus.getCode(), (String) null));
                                        iClientCallback.onFailure(bundle);
                                    }
                                }
                            } else if (iClientCallback != null) {
                                try {
                                    bundle.putParcelable("error", new Error(-1002, (String) null));
                                    iClientCallback.onFailure(bundle);
                                } catch (RemoteException unused3) {
                                }
                            }
                        } else if (iClientCallback != null) {
                            try {
                                bundle.putParcelable("error", new Error(-1001, (String) null));
                                iClientCallback.onFailure(bundle);
                            } catch (RemoteException unused4) {
                            }
                        }
                    } else if (iClientCallback != null) {
                        try {
                            bundle.putParcelable("error", new Error(-1000, (String) null));
                            iClientCallback.onFailure(bundle);
                        } catch (RemoteException unused5) {
                        }
                    }
                } else if (iClientCallback != null) {
                    try {
                        bundle.putParcelable("error", new Error(-100, (String) null));
                        iClientCallback.onFailure(bundle);
                    } catch (RemoteException unused6) {
                    }
                }
            }
        }.execute();
    }
}
