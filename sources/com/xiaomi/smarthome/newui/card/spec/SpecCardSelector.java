package com.xiaomi.smarthome.newui.card.spec;

import android.util.Pair;
import com.xiaomi.smarthome.core.server.internal.util.LogUtil;
import com.xiaomi.smarthome.device.api.spec.instance.Spec;
import com.xiaomi.smarthome.device.api.spec.instance.SpecDevice;
import com.xiaomi.smarthome.device.api.spec.instance.SpecService;
import com.xiaomi.smarthome.newui.card.profile.SpecCardType;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class SpecCardSelector {
    private static final Pair<SpecCard, Pair<SpecService, ? extends Spec.SpecItem>[][]> e = new Pair<>((Object) null, (Object) null);

    /* renamed from: a  reason: collision with root package name */
    public SpecCard f20593a;
    public ArrayList<SpecCard> b;
    public Map<Set<String>, SpecCard> c;
    public ArrayList<SpecCard> d;
    private Map<SpecDevice, Pair<SpecCard, Pair<SpecService, ? extends Spec.SpecItem>[][]>> f = new HashMap();

    public SpecCard a(SpecDevice specDevice) {
        Pair<SpecCard, Pair<SpecService, ? extends Spec.SpecItem>[][]> c2 = c(specDevice);
        if (c2 == null || c2.first == null) {
            return null;
        }
        return (SpecCard) c2.first;
    }

    public Pair<SpecService, ? extends Spec.SpecItem>[][] b(SpecDevice specDevice) {
        Pair<SpecCard, Pair<SpecService, ? extends Spec.SpecItem>[][]> c2 = c(specDevice);
        if (c2 == null || c2.first == null) {
            return (Pair[][]) null;
        }
        return (Pair[][]) c2.second;
    }

    public Pair<SpecCard, Pair<SpecService, ? extends Spec.SpecItem>[][]> c(SpecDevice specDevice) {
        if (specDevice == null) {
            LogUtil.b("mijia-card", "MiotSpecCardManager.getGridCard specDevice is null");
            return null;
        }
        Pair<SpecCard, Pair<SpecService, ? extends Spec.SpecItem>[][]> pair = this.f.get(specDevice);
        if (pair != null) {
            return pair;
        }
        if (this.d == null) {
            LogUtil.b("mijia-card", "MiotSpecCardManager.getGridCard list is null urn:" + specDevice.getType());
        } else {
            LogUtil.c("mijia-card", "MiotSpecCardManager.getGridCard prematch  urn:" + specDevice.getType());
            Iterator<SpecCard> it = this.d.iterator();
            while (it.hasNext()) {
                SpecCard next = it.next();
                Pair[][] a2 = a(next, specDevice);
                if (a2 != null) {
                    LogUtil.a("mijia-card", "MiotSpecCardManager.getGridCard matched  urn:" + specDevice.getType());
                    Pair<SpecCard, Pair<SpecService, ? extends Spec.SpecItem>[][]> pair2 = new Pair<>(next, a2);
                    this.f.put(specDevice, pair2);
                    return pair2;
                }
            }
            LogUtil.b("mijia-card", "MiotSpecCardManager.getGridCard not match  urn:" + specDevice.getType());
        }
        this.f.put(specDevice, e);
        return e;
    }

    public SpecCard d(SpecDevice specDevice) {
        Pair<SpecCard, Pair<SpecService, ? extends Spec.SpecItem>[][]> f2 = f(specDevice);
        if (f2 == null || f2.first == null) {
            return null;
        }
        return (SpecCard) f2.first;
    }

    public Pair<SpecService, ? extends Spec.SpecItem>[][] e(SpecDevice specDevice) {
        Pair<SpecCard, Pair<SpecService, ? extends Spec.SpecItem>[][]> f2 = f(specDevice);
        if (f2 == null || f2.first == null) {
            return (Pair[][]) null;
        }
        return (Pair[][]) f2.second;
    }

    public Pair<SpecCard, Pair<SpecService, ? extends Spec.SpecItem>[][]> f(SpecDevice specDevice) {
        if (specDevice == null) {
            LogUtil.b("mijia-card", "MiotSpecCardManager.getActivityCard specInstance is null");
            return null;
        }
        String type = specDevice.getType();
        Map<Set<String>, SpecCard> map = this.c;
        if (map != null) {
            for (Map.Entry next : map.entrySet()) {
                SpecCard specCard = (SpecCard) next.getValue();
                if (((Set) next.getKey()).contains(type)) {
                    Pair[][] a2 = a(specCard, specDevice);
                    if (a2 == null) {
                        LogUtil.a("mijia-card", "MiotSpecCardManager.chooseSpecCardInstance not match. card instance_type:2 layout_type:" + specCard.b + " layout:" + specCard.c);
                    } else {
                        LogUtil.a("mijia-card", "MiotSpecCardManager.chooseSpecCardInstance find. card instance_type:2 layout_type:" + specCard.b + " layout:" + specCard.c);
                        return new Pair<>(specCard, a2);
                    }
                }
            }
        }
        if (this.b != null) {
            Iterator<SpecCard> it = this.b.iterator();
            while (it.hasNext()) {
                SpecCard next2 = it.next();
                LogUtil.c("mijia-card", "MiotSpecCardManager.getActivityCard prematch  instance_type:1 targetUrn:" + type);
                Pair[][] a3 = a(next2, specDevice);
                if (a3 != null) {
                    LogUtil.c("mijia-card", "MiotSpecCardManager.getActivityCard matched  instance_type:1 targetUrn:" + type);
                    return new Pair<>(next2, a3);
                }
            }
            LogUtil.c("mijia-card", "MiotSpecCardManager.getActivityCard not match  instance_type:1 targetUrn:" + type);
        } else {
            LogUtil.c("mijia-card", "MiotSpecCardManager.getActivityCard not config instance_type:1");
        }
        if (this.f20593a != null) {
            Pair[][] a4 = a(this.f20593a, specDevice);
            if (a4 == null) {
                LogUtil.c("mijia-card", "MiotSpecCardManager.getActivityCard matched  instance_type:0 targetUrn:" + type);
                return null;
            }
            LogUtil.c("mijia-card", "MiotSpecCardManager.getActivityCard not match  instance_type:0 targetUrn:" + type);
            return new Pair<>(this.f20593a, a4);
        }
        LogUtil.c("mijia-card", "MiotSpecCardManager.getActivityCard not config instance_type:0 targetUrn:" + type);
        return null;
    }

    private Pair<SpecService, ? extends Spec.SpecItem>[][] a(SpecCard specCard, SpecDevice specDevice) {
        String type = specDevice.getType();
        List list = specCard.c;
        if (specCard.e == null || Arrays.binarySearch(specCard.e, type) < 0) {
            Pair<SpecService, ? extends Spec.SpecItem>[][] a2 = a(specDevice, (List<SpecCardType>) list);
            if (a2 == null) {
                LogUtil.c("mijia-card", "MiotSpecCardManager.matchSpecCard not match spec instance. layout_type:" + specCard.b + " layout:" + list + " targetUrn:" + type);
                return (Pair[][]) null;
            } else if (a2.length == list.size()) {
                LogUtil.c("mijia-card", "MiotSpecCardManager.matchSpecCard find. layout_type:" + specCard.b + " layout:" + list);
                return a2;
            } else {
                LogUtil.c("mijia-card", "MiotSpecCardManager.matchSpecCard not match layout size. layout_type:" + specCard.b + " layout:" + list + " targetUrn:" + type);
                return (Pair[][]) null;
            }
        } else {
            LogUtil.c("mijia-card", "MiotSpecCardManager.matchSpecCard not match. disable type contain urn:" + type + " layout_type:" + list + " targetUrn:" + type);
            return (Pair[][]) null;
        }
    }

    public Pair<SpecService, ? extends Spec.SpecItem>[][] a(SpecDevice specDevice, List<SpecCardType> list) {
        Map<String, ArrayList<Pair<SpecService, ? extends Spec.SpecItem>>> a2 = SpecUtils.a(specDevice);
        Pair<SpecService, ? extends Spec.SpecItem>[][] pairArr = new Pair[list.size()][];
        HashMap hashMap = new HashMap();
        for (int i = 0; i < list.size(); i++) {
            SpecCardType specCardType = list.get(i);
            ArrayList arrayList = (ArrayList) hashMap.get(specCardType.c);
            if (arrayList == null) {
                arrayList = new ArrayList();
                hashMap.put(specCardType.c, arrayList);
            }
            arrayList.add(Integer.valueOf(i));
        }
        for (Map.Entry entry : hashMap.entrySet()) {
            String[] split = ((String) entry.getKey()).split(",");
            ArrayList arrayList2 = (ArrayList) entry.getValue();
            int size = arrayList2.size();
            int i2 = 0;
            while (true) {
                if (i2 < split.length) {
                    String str = split[i2];
                    ArrayList arrayList3 = a2.get(str);
                    if (arrayList3 == null) {
                        LogUtil.b("mijia-card", "MiotSpecCardManager.getProperties notmatch " + str + " layouts:" + list);
                        return (Pair[][]) null;
                    } else if (size == arrayList3.size()) {
                        for (int i3 = 0; i3 < size; i3++) {
                            if (pairArr[((Integer) arrayList2.get(i3)).intValue()] == null) {
                                pairArr[((Integer) arrayList2.get(i3)).intValue()] = new Pair[split.length];
                            }
                            pairArr[((Integer) arrayList2.get(i3)).intValue()][i2] = (Pair) arrayList3.get(i3);
                        }
                        i2++;
                    } else {
                        LogUtil.b("mijia-card", "MiotSpecCardManager.getProperties size different " + str + " layouts:" + list);
                        return (Pair[][]) null;
                    }
                }
            }
        }
        return pairArr;
    }
}
