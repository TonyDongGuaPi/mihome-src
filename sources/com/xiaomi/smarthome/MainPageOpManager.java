package com.xiaomi.smarthome;

import android.text.TextUtils;
import android.util.Log;
import android.util.Pair;
import com.xiaomi.smarthome.core.server.internal.plugin.util.FileUtils;
import com.xiaomi.smarthome.device.Device;
import com.xiaomi.smarthome.device.DeviceUtils;
import com.xiaomi.smarthome.device.api.spec.instance.Spec;
import com.xiaomi.smarthome.device.api.spec.instance.SpecAction;
import com.xiaomi.smarthome.device.api.spec.instance.SpecProperty;
import com.xiaomi.smarthome.device.api.spec.instance.SpecService;
import com.xiaomi.smarthome.frame.AsyncCallback;
import com.xiaomi.smarthome.frame.Error;
import com.xiaomi.smarthome.framework.log.LogUtil;
import com.xiaomi.smarthome.framework.log.LogUtilGrey;
import com.xiaomi.smarthome.library.common.util.CalendarUtils;
import com.xiaomi.smarthome.newui.card.Card;
import com.xiaomi.smarthome.newui.card.CardItem;
import com.xiaomi.smarthome.newui.card.ControlCardInfoManager;
import com.xiaomi.smarthome.newui.card.MiotSpecCardManager;
import com.xiaomi.smarthome.newui.card.Operation;
import com.xiaomi.smarthome.newui.card.profile.ProfileCard;
import com.xiaomi.smarthome.newui.card.profile.ProfileCardType;
import com.xiaomi.smarthome.newui.card.profile.PropItem;
import com.xiaomi.smarthome.newui.card.profile.SpecCardType;
import com.xiaomi.smarthome.newui.card.spec.SpecUtils;
import com.xiaomi.youpin.login.other.log.LoginLogUtilsGrey;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONObject;

public class MainPageOpManager {

    /* renamed from: a  reason: collision with root package name */
    public static final String f13390a = "%.0f";
    private static MainPageOpManager b;

    private MainPageOpManager() {
    }

    public static MainPageOpManager a() {
        if (b == null) {
            synchronized (MainPageOpManager.class) {
                if (b == null) {
                    b = new MainPageOpManager();
                }
            }
        }
        return b;
    }

    public void a(Device device, int i, Card.CardType cardType, AsyncCallback<Void, Error> asyncCallback) {
        if ((device == null || cardType == null) && asyncCallback != null) {
            asyncCallback.onFailure(new Error(-1, "device is null"));
        }
        if (cardType instanceof ProfileCardType) {
            a(device, (ProfileCardType) cardType, asyncCallback);
        } else if (cardType instanceof SpecCardType) {
            a(device, i, (SpecCardType) cardType, asyncCallback);
        }
    }

    private void a(Device device, ProfileCardType profileCardType, final AsyncCallback<Void, Error> asyncCallback) {
        if (ControlCardInfoManager.f().e(device.model) == null) {
            if (asyncCallback != null) {
                asyncCallback.onFailure(new Error(-1, "card config is null"));
            }
        } else if (profileCardType != null && profileCardType.d != null && profileCardType.d.size() >= 2) {
            Operation a2 = Operation.a((List<Operation>) profileCardType.d, profileCardType.b(device, profileCardType.c));
            if (a2 != null) {
                a2.a(profileCardType, a2.a((List<Operation>) profileCardType.d, profileCardType.a()), device, new AsyncCallback<JSONObject, Error>() {
                    /* renamed from: a */
                    public void onSuccess(JSONObject jSONObject) {
                        if (asyncCallback != null) {
                            asyncCallback.onSuccess(null);
                        }
                    }

                    public void onFailure(Error error) {
                        if (asyncCallback != null) {
                            asyncCallback.onFailure(error);
                        }
                    }
                });
            } else if (asyncCallback != null) {
                asyncCallback.onFailure(new Error(-1, "get operation null"));
            }
        } else if (asyncCallback != null) {
            asyncCallback.onFailure(new Error(-1, "wrong config"));
        }
    }

    private void a(Device device, int i, SpecCardType specCardType, final AsyncCallback<Void, Error> asyncCallback) {
        Pair pair;
        if (specCardType != null) {
            MiotSpecCardManager f = MiotSpecCardManager.f();
            Pair[] a2 = f.a(device, i);
            if (a2 == null || a2.length == 0 || (pair = a2[0]) == null || !(pair.second instanceof SpecProperty)) {
                LogUtilGrey.a("mijia-card", "toggleSpec not get prop:" + device);
                return;
            }
            Pair<SpecService, SpecAction> a3 = SpecUtils.a(MiotSpecCardManager.f().a(device), Operation.a((List<Operation>) specCardType.d, (Object) SpecUtils.a((SpecProperty) pair.second, specCardType.b(device, (Pair<SpecService, ? extends Spec.SpecItem>) pair))));
            if (a3 != null) {
                f.a(device.did, a3, a2, new AsyncCallback<JSONObject, Error>() {
                    /* renamed from: a */
                    public void onSuccess(JSONObject jSONObject) {
                        if (asyncCallback != null) {
                            asyncCallback.onSuccess(null);
                        }
                    }

                    public void onFailure(Error error) {
                        if (asyncCallback != null) {
                            asyncCallback.onFailure(null);
                        }
                    }
                });
                return;
            }
            f.a(device.did, (SpecService) pair.first, (SpecProperty) pair.second, specCardType.c(device, (Pair<SpecService, ? extends Spec.SpecItem>) pair), new AsyncCallback<JSONObject, Error>() {
                /* renamed from: a */
                public void onSuccess(JSONObject jSONObject) {
                    if (asyncCallback != null) {
                        asyncCallback.onSuccess(null);
                    }
                }

                public void onFailure(Error error) {
                    if (asyncCallback != null) {
                        asyncCallback.onFailure(null);
                    }
                }
            });
        } else if (asyncCallback != null) {
            asyncCallback.onFailure(new Error(-1, "wrong config"));
        }
    }

    public Card<? extends Card.CardType> a(Device device) {
        if (device == null) {
            return null;
        }
        ProfileCard e = ControlCardInfoManager.f().e(device.model);
        return e == null ? MiotSpecCardManager.f().c(device) : e;
    }

    public ArrayList<Long> b(Device device) {
        Card<? extends Card.CardType> a2 = a().a(device);
        if (a2 == null) {
            return null;
        }
        List<? extends Card.CardType> a3 = a2.a();
        ArrayList<Long> arrayList = new ArrayList<>();
        for (int i = 0; i < a3.size(); i++) {
            Card.CardType cardType = (Card.CardType) a3.get(i);
            arrayList.add(Long.valueOf(cardType.g(device, a(cardType, i, device))));
        }
        return arrayList;
    }

    public ArrayList<Pair> c(Device device) {
        Card<? extends Card.CardType> a2 = a().a(device);
        if (a2 == null) {
            LogUtil.c("mijia-card", "getDeviceRenderData card is null device:" + device);
            return null;
        }
        List<? extends Card.CardType> a3 = a2.a();
        if (a3 == null || a3.isEmpty()) {
            LogUtil.c("mijia-card", "getDeviceRenderData gridCardItems is empty device:" + device);
            return null;
        } else if (a2.b == -1) {
            LogUtil.c("mijia-card", "getDeviceRenderData layoutType is not support device:" + device);
            return null;
        } else if (a3.size() == 1) {
            ArrayList<Pair> arrayList = new ArrayList<>();
            Card.CardType cardType = (Card.CardType) a3.get(0);
            Object a4 = a(cardType, 0, device);
            if (a4 == null) {
                Log.e("mijia-card", "getDeviceRenderData configGrid but no support device:" + device + " item" + cardType);
                return null;
            }
            if (cardType.b == 1 || cardType.b == 2) {
                Pair<CardItem.State, String> a5 = a(device, cardType, 0, false);
                if (a5 != null) {
                    arrayList.add(a5);
                }
            } else if (cardType.b == 7) {
                arrayList.add(new Pair(cardType.a(device, a4), c(device, cardType, a4)));
            } else if (cardType.b == 8) {
                Object b2 = cardType.b(device, a4);
                arrayList.add(new Pair(cardType.a(device, a4), b2 == null ? "" : String.valueOf(cardType.a(device, a4, b2))));
            } else if (cardType.b == 10) {
                arrayList.add(new Pair(cardType.a(device, a4), b(device, cardType, a4)));
            } else if (cardType.b == 18) {
                arrayList.add(new Pair(a(device, cardType, a4), cardType.a(device, a4, cardType.b(device, a4))));
            }
            LogUtil.c("mijia-card", "getDeviceRenderData match 1 item device:" + device + " pair:" + arrayList);
            return arrayList;
        } else if (a3.size() == 2) {
            Card.CardType cardType2 = (Card.CardType) a3.get(0);
            Card.CardType cardType3 = (Card.CardType) a3.get(1);
            if (cardType2.b != cardType3.b) {
                return null;
            }
            ArrayList<Pair> arrayList2 = new ArrayList<>();
            Object a6 = a(cardType2, 0, device);
            Object a7 = a(cardType3, 1, device);
            if (a6 == null || a7 == null) {
                Log.e("mijia-card", "getDeviceRenderData configGrid but no support device:" + device + " item1" + cardType3 + " item1" + cardType3);
                return null;
            }
            if (cardType2.b == 7) {
                arrayList2.add(new Pair(cardType2.a(device, a6), c(device, cardType2, a6)));
                arrayList2.add(new Pair(cardType3.a(device, a7), c(device, cardType3, a7)));
            } else if (cardType2.b == 8) {
                Object b3 = cardType2.b(device, a6);
                Object b4 = cardType3.b(device, a7);
                arrayList2.add(new Pair(cardType2.a(device, a6), b3 == null ? "" : String.valueOf(cardType2.a(device, a6, b3))));
                arrayList2.add(new Pair(cardType3.a(device, a7), b3 == null ? "" : String.valueOf(cardType3.a(device, a7, b4))));
            } else if (cardType2.b == 10) {
                arrayList2.add(new Pair(cardType2.a(device, a6), b(device, cardType2, a6)));
                arrayList2.add(new Pair(cardType3.a(device, a7), b(device, cardType3, a7)));
            }
            LogUtil.c("mijia-card", "getDeviceRenderData match 2 item device:" + device + " pair:" + arrayList2);
            return arrayList2;
        } else {
            LogUtil.c("mijia-card", "getDeviceRenderData match none item device:" + device);
            return null;
        }
    }

    private Object a(Card.CardType cardType, int i, Device device) {
        if (cardType instanceof ProfileCardType) {
            return ((ProfileCardType) cardType).c;
        }
        Pair[] a2 = MiotSpecCardManager.f().a(device, i);
        if (a2 != null && a2.length != 0) {
            return a2[0];
        }
        Log.e("mijia-card", "getDeviceRenderData configGrid gridCardProperty is null device:" + device + " item" + cardType);
        return null;
    }

    private String a(Device device, Card.CardType cardType, Object obj) {
        try {
            Pair<Object, Long> d = cardType.d(device, obj);
            if (d == null) {
                return "";
            }
            return CalendarUtils.a(((Long) d.second).longValue() * 1000);
        } catch (Exception e) {
            LogUtil.b("mijia-card", Log.getStackTraceString(e));
            return "";
        }
    }

    private String b(Device device, Card.CardType<Object> cardType, Object obj) {
        try {
            Pair<Object, Long> d = cardType.d(device, obj);
            if (d == null) {
                return "";
            }
            return CalendarUtils.a(((Long) d.second).longValue() * 1000);
        } catch (Exception e) {
            LogUtil.b("mijia-card", Log.getStackTraceString(e));
            return "";
        }
    }

    private String c(Device device, Card.CardType<Object> cardType, Object obj) {
        String a2 = cardType.a(obj, cardType.b(device, obj), cardType.e(device, obj));
        if (TextUtils.isEmpty(a2)) {
            return "";
        }
        String f = cardType.f(device, obj);
        StringBuilder sb = new StringBuilder();
        sb.append(a2);
        if (f == null) {
            f = "";
        }
        sb.append(f);
        return sb.toString();
    }

    public Pair<CardItem.State, String> a(Device device, int i, Card.CardType cardType, boolean z) {
        return a(device, cardType, i, z);
    }

    private Pair<CardItem.State, String> a(Device device, Card.CardType cardType, int i, boolean z) {
        if (cardType == null || device == null) {
            return null;
        }
        if (cardType instanceof ProfileCardType) {
            return a(device, (ProfileCardType) cardType, z);
        }
        return a(device, (SpecCardType) cardType, (Pair<SpecService, ? extends Spec.SpecItem>[]) MiotSpecCardManager.f().a(device, i), z);
    }

    private Pair<CardItem.State, String> a(Device device, ProfileCardType profileCardType, boolean z) {
        CardItem.State state;
        Operation operation;
        String str;
        if (profileCardType == null || device == null) {
            StringBuilder sb = new StringBuilder();
            sb.append("card_items null model:");
            sb.append(device == null ? "null" : device.model);
            LogUtil.b("mijia-card", sb.toString());
            return null;
        }
        PropItem a2 = profileCardType.a();
        if (a2 == null) {
            LogUtil.b("mijia-card", "propItem null model:" + device.model);
            return null;
        }
        Object d = profileCardType.b(device, profileCardType.c);
        List list = profileCardType.d;
        if (list == null || list.size() < 2) {
            LogUtil.b("mijia-card", "card_items.prop_key:" + profileCardType.c + " card_items.operations only one model:" + device.model);
            return null;
        }
        boolean z2 = true;
        if (profileCardType.b == 1) {
            if (a2.j == null) {
                LogUtil.b("mijia-card", "model:" + device.model + " prop:" + a2.f20557a + " switchStatus is not config");
                return null;
            }
            CardItem.State state2 = null;
            for (Object valueOf : a2.j) {
                if (String.valueOf(valueOf).equals(String.valueOf(d))) {
                    state2 = z ? CardItem.State.NOR : CardItem.State.SELECTED;
                }
            }
            if (state2 == null) {
                state = z ? CardItem.State.SELECTED : CardItem.State.NOR;
                operation = null;
            } else {
                operation = null;
                state = state2;
            }
        } else if (profileCardType.b != 2) {
            state = null;
            operation = null;
        } else if (((Operation) list.get(0)).a(String.valueOf(d))) {
            state = z ? CardItem.State.NOR : CardItem.State.SELECTED;
            operation = (Operation) list.get(0);
        } else {
            state = z ? CardItem.State.SELECTED : CardItem.State.NOR;
            operation = (Operation) list.get(1);
        }
        if (operation != null && !profileCardType.a(device, operation)) {
            z2 = false;
        }
        if (e(device) && !z2) {
            return new Pair<>(CardItem.State.NOR, (Object) null);
        }
        if (!z2) {
            state = CardItem.State.UNABLE;
        }
        if (state == CardItem.State.SELECTED) {
            StringBuilder sb2 = new StringBuilder();
            sb2.append(ControlCardInfoManager.f().d());
            sb2.append(ControlCardInfoManager.f().a("grid_" + ((Operation) list.get(0)).i + ".png"));
            str = sb2.toString();
        } else {
            StringBuilder sb3 = new StringBuilder();
            sb3.append(ControlCardInfoManager.f().d());
            sb3.append(ControlCardInfoManager.f().a("grid_" + ((Operation) list.get(0)).h + ".png"));
            str = sb3.toString();
        }
        if (!FileUtils.f(str)) {
            str = null;
        }
        return new Pair<>(state, str);
    }

    private boolean e(Device device) {
        if (device == null) {
            return false;
        }
        if (device.model.contains("rockrobo.vacuum") || device.model.contains("roborock.sweeper") || device.model.contains("roborock.vacuum")) {
            return true;
        }
        return false;
    }

    private Pair<CardItem.State, String> a(Device device, SpecCardType specCardType, Pair<SpecService, ? extends Spec.SpecItem>[] pairArr, boolean z) {
        Pair<SpecService, ? extends Spec.SpecItem> pair;
        CardItem.State state;
        String str;
        Device device2 = device;
        SpecCardType specCardType2 = specCardType;
        Pair<SpecService, ? extends Spec.SpecItem>[] pairArr2 = pairArr;
        if (device2 == null || specCardType2 == null || pairArr2 == null || pairArr2.length == 0 || (pair = pairArr2[0]) == null || !(pair.second instanceof SpecProperty) || TextUtils.isEmpty(DeviceUtils.e(device))) {
            return null;
        }
        Object e = specCardType2.b(device, pair);
        if (e == null) {
            LoginLogUtilsGrey.a("getSpecButtonDrawable", device2.did + " getPropValue == null");
            return null;
        }
        Operation a2 = Operation.a((List<Operation>) specCardType2.d, (Object) SpecUtils.a((SpecProperty) pair.second, e));
        Pair<SpecService, SpecAction> a3 = SpecUtils.a(MiotSpecCardManager.f().a(device), a2);
        if (!specCardType2.a(device, a2)) {
            state = CardItem.State.UNABLE;
        } else if (a3 == null) {
            state = SpecUtils.a(e) ? z ? CardItem.State.NOR : CardItem.State.SELECTED : z ? CardItem.State.SELECTED : CardItem.State.NOR;
        } else if (specCardType2.d.indexOf(a2) == 0) {
            state = z ? CardItem.State.NOR : CardItem.State.SELECTED;
        } else {
            state = z ? CardItem.State.SELECTED : CardItem.State.NOR;
        }
        CardItem.State state2 = state;
        if (a2 == null || a3 == null) {
            str = null;
        } else {
            str = SpecUtils.a("carditemspec_grid_", device, (SpecService) a3.first, (Spec.SpecItem) a3.second, String.valueOf(a2.b), (Object) null, CardItem.State.SELECTED);
            if (str == null) {
                str = SpecUtils.a("carditemspec_grid_", device, (SpecService) a3.first, (Spec.SpecItem) a3.second, String.valueOf(a2.b), (Object) null, CardItem.State.NOR);
            }
        }
        if (str == null) {
            str = SpecUtils.a("carditemspec_grid_", device, (SpecService) pair.first, (Spec.SpecItem) pair.second, (String) null, e, state2);
        }
        if (str == null) {
            return null;
        }
        return new Pair<>(state2, str);
    }

    public boolean d(Device device) {
        if (device == null) {
            return false;
        }
        ProfileCard e = ControlCardInfoManager.f().e(device.model);
        if (e != null) {
            return a(e.a(), e.b());
        }
        return a(MiotSpecCardManager.f().c(device).a(), MiotSpecCardManager.f().d(device).b());
    }

    private boolean a(List<? extends Card.CardType> list, List<? extends Card.CardType> list2) {
        int size;
        if (list2 == null || list2.size() == 0) {
            if (list == null || list.size() == 0) {
                return true;
            }
            return false;
        } else if (list == null || (size = list2.size()) != list.size()) {
            return false;
        } else {
            for (int i = 0; i < size; i++) {
                if (!TextUtils.equals(((Card.CardType) list2.get(i)).c, ((Card.CardType) list.get(i)).c)) {
                    return false;
                }
            }
            return true;
        }
    }
}
