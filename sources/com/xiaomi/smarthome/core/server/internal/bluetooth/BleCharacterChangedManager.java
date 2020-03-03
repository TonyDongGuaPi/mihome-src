package com.xiaomi.smarthome.core.server.internal.bluetooth;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.RemoteException;
import android.text.TextUtils;
import com.xiaomi.smarthome.core.client.IClientApi;
import com.xiaomi.smarthome.core.server.CoreManager;
import com.xiaomi.smarthome.core.server.bluetooth.IBleResponse;
import com.xiaomi.smarthome.library.bluetooth.utils.BluetoothUtils;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public class BleCharacterChangedManager {

    /* renamed from: a  reason: collision with root package name */
    private static CharacterChangedReceiver f14098a;
    /* access modifiers changed from: private */
    public static Map<String, List<CharacterRecord>> b = new ConcurrentHashMap();

    public static synchronized void a(String str, UUID uuid, UUID uuid2, IBleResponse iBleResponse) {
        synchronized (BleCharacterChangedManager.class) {
            if (!TextUtils.isEmpty(str) && uuid != null) {
                if (uuid2 != null) {
                    List list = b.get(str);
                    CharacterRecord characterRecord = new CharacterRecord(uuid, uuid2);
                    if (list == null) {
                        ArrayList arrayList = new ArrayList();
                        arrayList.add(characterRecord);
                        b.put(str, arrayList);
                    } else if (!a(list, characterRecord)) {
                        list.add(characterRecord);
                    }
                    if (b.size() > 0) {
                        b();
                    }
                    if (iBleResponse != null) {
                        try {
                            iBleResponse.onResponse(0, (Bundle) null);
                        } catch (RemoteException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
            if (iBleResponse != null) {
                try {
                    iBleResponse.onResponse(-1, (Bundle) null);
                } catch (RemoteException e2) {
                    e2.printStackTrace();
                }
            }
        }
        return;
    }

    public static synchronized void a(String str, UUID uuid, UUID uuid2) {
        synchronized (BleCharacterChangedManager.class) {
            List list = b.get(str);
            if (list != null) {
                Iterator it = list.iterator();
                while (it.hasNext()) {
                    CharacterRecord characterRecord = (CharacterRecord) it.next();
                    if (characterRecord.f14099a.equals(uuid) && characterRecord.b.equals(uuid2)) {
                        it.remove();
                    }
                }
                if (list.size() == 0) {
                    b.remove(str);
                }
            }
            if (b.size() == 0) {
                c();
            }
        }
    }

    private static boolean a(List<CharacterRecord> list, CharacterRecord characterRecord) {
        if (list == null || list.size() == 0 || characterRecord == null) {
            return false;
        }
        for (CharacterRecord next : list) {
            if (characterRecord.f14099a.equals(next.f14099a) && characterRecord.b.equals(next.b)) {
                return true;
            }
        }
        return false;
    }

    private static synchronized void b() {
        synchronized (BleCharacterChangedManager.class) {
            if (f14098a == null) {
                f14098a = new CharacterChangedReceiver();
                BluetoothUtils.a((BroadcastReceiver) f14098a, new IntentFilter("com.xiaomi.smarthome.bluetooth.character_changed"));
            }
        }
    }

    private static synchronized void c() {
        synchronized (BleCharacterChangedManager.class) {
            if (f14098a != null) {
                BluetoothUtils.a((BroadcastReceiver) f14098a);
                f14098a = null;
            }
        }
    }

    private static class CharacterChangedReceiver extends BroadcastReceiver {
        private CharacterChangedReceiver() {
        }

        public void onReceive(Context context, Intent intent) {
            List<CharacterRecord> list;
            if ("com.xiaomi.smarthome.bluetooth.character_changed".equals(intent.getAction())) {
                String stringExtra = intent.getStringExtra("key_device_address");
                UUID uuid = (UUID) intent.getSerializableExtra("key_service_uuid");
                UUID uuid2 = (UUID) intent.getSerializableExtra("key_character_uuid");
                byte[] byteArrayExtra = intent.getByteArrayExtra("key_character_value");
                if (stringExtra != null && uuid != null && uuid2 != null && byteArrayExtra != null && (list = (List) BleCharacterChangedManager.b.get(stringExtra)) != null && list.size() > 0) {
                    for (CharacterRecord characterRecord : list) {
                        if (uuid.equals(characterRecord.f14099a) && uuid2.equals(characterRecord.b)) {
                            IClientApi a2 = CoreManager.a().a("com.xiaomi.smarthome");
                            if (a2 != null) {
                                Bundle bundle = new Bundle();
                                bundle.putString("key_device_address", stringExtra);
                                bundle.putSerializable("key_service_uuid", uuid);
                                bundle.putSerializable("key_character_uuid", uuid2);
                                bundle.putByteArray("key_character_value", byteArrayExtra);
                                try {
                                    a2.onBleCharacterChanged(bundle);
                                    return;
                                } catch (RemoteException e) {
                                    e.printStackTrace();
                                    return;
                                }
                            } else {
                                return;
                            }
                        }
                    }
                }
            }
        }
    }

    private static class CharacterRecord {

        /* renamed from: a  reason: collision with root package name */
        protected UUID f14099a;
        protected UUID b;

        public CharacterRecord(UUID uuid, UUID uuid2) {
            this.f14099a = uuid;
            this.b = uuid2;
        }
    }
}
