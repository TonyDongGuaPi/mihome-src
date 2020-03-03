package com.xiaomi.smarthome.newui.card;

import com.alipay.sdk.util.i;
import com.xiaomi.smarthome.newui.card.profile.YelightColorUtils;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ProductModel {

    /* renamed from: a  reason: collision with root package name */
    public static final String f20525a = "yeelink.light.virtual";
    public static final String b = "yeelink.light.mono1";
    public static final String c = "yeelink.light.mono2";
    public static final String d = "yeelink.light.color1";
    public static final String e = "yeelink.light.color3";
    public static final String f = "yeelink.light.lamp1";
    public static final String g = "yeelink.light.lamp3";
    public static final String h = "yeelink.light.strip1";
    public static final String i = "yeelink.light.strip2";
    public static final String j = "yeelink.light.ceiling1";
    public static final String k = "yeelink.light.ceiling2";
    public static final String l = "yeelink.light.ceiling3";
    public static final String m = "yeelink.light.ceiling4";
    public static final String n = "yeelink.light.ceiling5";
    public static final String o = "yeelink.light.ceiling6";
    public static final String p = "yeelink.light.ceiling7";
    public static final String q = "yeelink.light.ceiling8";
    public static final String r = "yeelink.light.bslamp1";
    public static final String s = "yeelink.light.bslamp2";
    public static final String t = "yeelink.light.ceiling4.ambi";
    public static final String u = "yeelink.light.ct2";
    public static final String v = "yeelink.wifispeaker.v1";
    public List<Capability> A;
    protected String w;
    protected boolean x;
    protected int y;
    protected int z;

    public enum CapabilityType {
        BTN_POWER,
        BTN_COLOR,
        BTN_CT,
        BTN_FLOW,
        BTN_SCENE,
        BTN_NL,
        BTN_CAMERA,
        CONTROL_COLOR_V,
        CONTROL_COLOR_H,
        CONTROL_CT_V,
        CONTROL_CT_H,
        CONTROL_FLOW_V,
        CONTROL_FLOW_H,
        SCENE_BRIGHT,
        SCENE_COLOR,
        SCENE_CT,
        SCENE_FLOW,
        SCENE_NL,
        BTN_GUARD_READ,
        BTN_GUARD_WORK,
        CONTROL_CT_GUARD_READ,
        CONTROL_CT_GUARD_WORK,
        BTN_DELAY
    }

    public ProductModel() {
        this.w = "";
        this.x = false;
        this.y = YelightColorUtils.g;
        this.z = YelightColorUtils.f;
        this.A = new ArrayList();
        this.w = "";
    }

    public String a() {
        return this.w;
    }

    public ProductModel(String str) {
        this.w = "";
        this.x = false;
        this.y = YelightColorUtils.g;
        this.z = YelightColorUtils.f;
        this.A = new ArrayList();
        String[] split = str.split(i.b);
        if (split != null) {
            for (String valueOf : split) {
                this.A.add(new Capability(CapabilityType.valueOf(valueOf)));
            }
        }
        if (a((ProductModel) new Mono(b))) {
            this.w = b;
        } else if (a((ProductModel) new Lamp(f))) {
            this.w = f;
        } else if (a((ProductModel) new Color(d))) {
            this.w = d;
        } else if (a((ProductModel) new Color3(e))) {
            this.w = e;
        } else if (a((ProductModel) new Ceiling(j))) {
            this.w = j;
        } else if (a((ProductModel) new Ceiling(k))) {
            this.w = k;
        }
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Capability type : this.A) {
            sb.append(type.getType().toString());
            sb.append(i.b);
        }
        return sb.toString();
    }

    public ProductModel(List<Capability> list) {
        this.w = "";
        this.x = false;
        this.y = YelightColorUtils.g;
        this.z = YelightColorUtils.f;
        this.A = new ArrayList();
        if (list != null) {
            this.A = list;
        }
    }

    public boolean a(CapabilityType capabilityType) {
        for (Capability next : this.A) {
            if (next.getType() == capabilityType && next.isEnabled()) {
                return true;
            }
        }
        return false;
    }

    public boolean b() {
        return this.x;
    }

    public static class Capability implements Serializable, Comparable<Capability> {
        private static final long serialVersionUID = 650754658372631811L;
        public boolean mEnabled = true;
        public CapabilityType mType;

        public Capability(CapabilityType capabilityType) {
            this.mType = capabilityType;
        }

        public CapabilityType getType() {
            return this.mType;
        }

        public void enalbeCapability(boolean z) {
            this.mEnabled = z;
        }

        public boolean isEnabled() {
            return this.mEnabled;
        }

        public boolean equals(Object obj) {
            if (obj == null) {
                return false;
            }
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof Capability) || ((Capability) obj).mType != this.mType) {
                return false;
            }
            return true;
        }

        public String toString() {
            return this.mType.name();
        }

        public int compareTo(Capability capability) {
            return getType().compareTo(capability.getType());
        }
    }

    public static class MinModel extends ProductModel {
        public MinModel() {
            this.A.add(new Capability(CapabilityType.BTN_POWER));
            this.A.add(new Capability(CapabilityType.BTN_CT));
            this.A.add(new Capability(CapabilityType.BTN_SCENE));
            this.A.add(new Capability(CapabilityType.CONTROL_CT_V));
            this.A.add(new Capability(CapabilityType.SCENE_BRIGHT));
            this.w = "";
        }
    }

    public static class MaxModel extends ProductModel {
        public MaxModel() {
            for (CapabilityType capability : CapabilityType.values()) {
                CapabilityType capabilityType = CapabilityType.BTN_NL;
                this.A.add(new Capability(capability));
            }
            this.w = "";
        }
    }

    public static class Mono extends ProductModel {
        public Mono(String str) {
            this.A.add(new Capability(CapabilityType.BTN_POWER));
            this.A.add(new Capability(CapabilityType.BTN_SCENE));
            this.A.add(new Capability(CapabilityType.CONTROL_CT_V));
            this.A.add(new Capability(CapabilityType.SCENE_BRIGHT));
            this.y = YelightColorUtils.g;
            this.w = str;
        }
    }

    public static class CT2 extends ProductModel {
        public CT2(String str) {
            this.A.add(new Capability(CapabilityType.BTN_POWER));
            this.A.add(new Capability(CapabilityType.BTN_SCENE));
            this.A.add(new Capability(CapabilityType.CONTROL_CT_V));
            this.A.add(new Capability(CapabilityType.CONTROL_CT_H));
            this.A.add(new Capability(CapabilityType.SCENE_BRIGHT));
            this.y = 2700;
            this.w = str;
        }
    }

    public static class Ceiling extends ProductModel {
        public Ceiling(String str) {
            this.A.add(new Capability(CapabilityType.BTN_POWER));
            this.A.add(new Capability(CapabilityType.BTN_CT));
            this.A.add(new Capability(CapabilityType.BTN_NL));
            this.A.add(new Capability(CapabilityType.BTN_SCENE));
            this.A.add(new Capability(CapabilityType.CONTROL_CT_V));
            this.A.add(new Capability(CapabilityType.CONTROL_CT_H));
            this.A.add(new Capability(CapabilityType.SCENE_BRIGHT));
            this.A.add(new Capability(CapabilityType.SCENE_CT));
            this.A.add(new Capability(CapabilityType.SCENE_NL));
            this.y = 2700;
            this.w = str;
        }
    }

    public static class Ceiling2 extends ProductModel {
        public Ceiling2(String str) {
            this.A.add(new Capability(CapabilityType.BTN_POWER));
            this.A.add(new Capability(CapabilityType.BTN_CT));
            this.A.add(new Capability(CapabilityType.BTN_NL));
            this.A.add(new Capability(CapabilityType.BTN_SCENE));
            this.A.add(new Capability(CapabilityType.CONTROL_CT_V));
            this.A.add(new Capability(CapabilityType.SCENE_BRIGHT));
            this.A.add(new Capability(CapabilityType.SCENE_CT));
            this.A.add(new Capability(CapabilityType.SCENE_NL));
            this.y = 2700;
            this.w = str;
        }
    }

    public static class GroupMono extends ProductModel {
        public GroupMono(String str) {
            this.A.add(new Capability(CapabilityType.BTN_POWER));
            this.A.add(new Capability(CapabilityType.BTN_CT));
            this.A.add(new Capability(CapabilityType.BTN_SCENE));
            this.A.add(new Capability(CapabilityType.CONTROL_CT_V));
            this.A.add(new Capability(CapabilityType.SCENE_BRIGHT));
            this.A.add(new Capability(CapabilityType.SCENE_CT));
            this.y = 2700;
            this.w = str;
        }
    }

    public static class GroupCeiling extends ProductModel {
        public GroupCeiling(String str) {
            this.A.add(new Capability(CapabilityType.BTN_POWER));
            this.A.add(new Capability(CapabilityType.BTN_CT));
            this.A.add(new Capability(CapabilityType.BTN_SCENE));
            this.A.add(new Capability(CapabilityType.CONTROL_CT_V));
            this.A.add(new Capability(CapabilityType.CONTROL_CT_H));
            this.A.add(new Capability(CapabilityType.SCENE_BRIGHT));
            this.A.add(new Capability(CapabilityType.SCENE_CT));
            this.A.add(new Capability(CapabilityType.SCENE_NL));
            this.w = str;
        }
    }

    public static class Color extends ProductModel {
        public Color(String str) {
            this.A.add(new Capability(CapabilityType.BTN_POWER));
            this.A.add(new Capability(CapabilityType.BTN_COLOR));
            this.A.add(new Capability(CapabilityType.BTN_CT));
            this.A.add(new Capability(CapabilityType.BTN_FLOW));
            this.A.add(new Capability(CapabilityType.BTN_SCENE));
            this.A.add(new Capability(CapabilityType.BTN_CAMERA));
            this.A.add(new Capability(CapabilityType.CONTROL_COLOR_V));
            this.A.add(new Capability(CapabilityType.CONTROL_COLOR_H));
            this.A.add(new Capability(CapabilityType.CONTROL_CT_V));
            this.A.add(new Capability(CapabilityType.CONTROL_CT_H));
            this.A.add(new Capability(CapabilityType.CONTROL_FLOW_V));
            this.A.add(new Capability(CapabilityType.CONTROL_FLOW_H));
            this.A.add(new Capability(CapabilityType.SCENE_BRIGHT));
            this.A.add(new Capability(CapabilityType.SCENE_COLOR));
            this.A.add(new Capability(CapabilityType.SCENE_CT));
            this.A.add(new Capability(CapabilityType.SCENE_FLOW));
            this.w = str;
        }
    }

    public static class Color3 extends Color {
        public Color3(String str) {
            super(str);
            this.A.add(new Capability(CapabilityType.BTN_DELAY));
        }
    }

    public static class Ambilight extends ProductModel {
        public Ambilight(String str) {
            this.A.add(new Capability(CapabilityType.BTN_POWER));
            this.A.add(new Capability(CapabilityType.BTN_COLOR));
            this.A.add(new Capability(CapabilityType.BTN_CT));
            this.A.add(new Capability(CapabilityType.BTN_SCENE));
            this.A.add(new Capability(CapabilityType.CONTROL_COLOR_V));
            this.A.add(new Capability(CapabilityType.CONTROL_COLOR_H));
            this.A.add(new Capability(CapabilityType.CONTROL_CT_V));
            this.A.add(new Capability(CapabilityType.CONTROL_CT_H));
            this.A.add(new Capability(CapabilityType.SCENE_BRIGHT));
            this.A.add(new Capability(CapabilityType.SCENE_COLOR));
            this.A.add(new Capability(CapabilityType.SCENE_CT));
            this.A.add(new Capability(CapabilityType.SCENE_FLOW));
            this.w = str;
        }
    }

    public static class Strip extends ProductModel {
        public Strip(String str) {
            this.A.add(new Capability(CapabilityType.BTN_POWER));
            this.A.add(new Capability(CapabilityType.BTN_COLOR));
            this.A.add(new Capability(CapabilityType.BTN_CT));
            this.A.add(new Capability(CapabilityType.BTN_FLOW));
            this.A.add(new Capability(CapabilityType.BTN_SCENE));
            this.A.add(new Capability(CapabilityType.BTN_CAMERA));
            this.A.add(new Capability(CapabilityType.CONTROL_COLOR_V));
            this.A.add(new Capability(CapabilityType.CONTROL_COLOR_H));
            this.A.add(new Capability(CapabilityType.CONTROL_CT_V));
            this.A.add(new Capability(CapabilityType.CONTROL_CT_H));
            this.A.add(new Capability(CapabilityType.CONTROL_FLOW_V));
            this.A.add(new Capability(CapabilityType.CONTROL_FLOW_H));
            this.A.add(new Capability(CapabilityType.SCENE_BRIGHT));
            this.A.add(new Capability(CapabilityType.SCENE_COLOR));
            this.A.add(new Capability(CapabilityType.SCENE_CT));
            this.A.add(new Capability(CapabilityType.SCENE_FLOW));
            this.w = str;
        }
    }

    public static class Strip2 extends ProductModel {
        public Strip2(String str) {
            this.A.add(new Capability(CapabilityType.BTN_POWER));
            this.A.add(new Capability(CapabilityType.BTN_COLOR));
            this.A.add(new Capability(CapabilityType.BTN_FLOW));
            this.A.add(new Capability(CapabilityType.BTN_SCENE));
            this.A.add(new Capability(CapabilityType.BTN_CAMERA));
            this.A.add(new Capability(CapabilityType.BTN_DELAY));
            this.A.add(new Capability(CapabilityType.CONTROL_COLOR_V));
            this.A.add(new Capability(CapabilityType.CONTROL_COLOR_H));
            this.A.add(new Capability(CapabilityType.CONTROL_FLOW_V));
            this.A.add(new Capability(CapabilityType.CONTROL_FLOW_H));
            this.A.add(new Capability(CapabilityType.SCENE_BRIGHT));
            this.A.add(new Capability(CapabilityType.SCENE_COLOR));
            this.A.add(new Capability(CapabilityType.SCENE_FLOW));
            this.w = str;
        }
    }

    public static class Lamp extends ProductModel {
        public Lamp(String str) {
            this.A.add(new Capability(CapabilityType.BTN_POWER));
            this.A.add(new Capability(CapabilityType.BTN_CT));
            this.A.add(new Capability(CapabilityType.BTN_SCENE));
            this.A.add(new Capability(CapabilityType.CONTROL_CT_V));
            this.A.add(new Capability(CapabilityType.CONTROL_CT_H));
            this.A.add(new Capability(CapabilityType.SCENE_BRIGHT));
            this.A.add(new Capability(CapabilityType.SCENE_CT));
            this.w = str;
            this.x = true;
        }
    }

    public boolean a(ProductModel productModel) {
        if (this.A.size() != productModel.A.size()) {
            return false;
        }
        Collections.sort(this.A);
        Collections.sort(productModel.A);
        for (int i2 = 0; i2 < this.A.size(); i2++) {
            if (!this.A.get(i2).equals(productModel.A.get(i2))) {
                return false;
            }
        }
        return true;
    }

    public int c() {
        return this.y;
    }

    public int d() {
        return this.z;
    }
}
