package com.xiaomi.smarthome.core.server.internal.bluetooth.recognizer.beacon;

import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;

public class MiotBleAdvPacket implements Parcelable {
    public static final Parcelable.Creator<MiotBleAdvPacket> CREATOR = new Parcelable.Creator<MiotBleAdvPacket>() {
        /* renamed from: a */
        public MiotBleAdvPacket createFromParcel(Parcel parcel) {
            return new MiotBleAdvPacket(parcel);
        }

        /* renamed from: a */
        public MiotBleAdvPacket[] newArray(int i) {
            return new MiotBleAdvPacket[i];
        }
    };

    /* renamed from: a  reason: collision with root package name */
    public FrameControl f14276a;
    public int b;
    public int c;
    public String d;
    public Capability e;
    public String f;
    public IoCapability g;
    public Event h;
    public int[] i;
    public int[] j;
    public Mesh k;

    public int describeContents() {
        return 0;
    }

    protected MiotBleAdvPacket() {
    }

    protected MiotBleAdvPacket(Parcel parcel) {
        try {
            this.f14276a = (FrameControl) parcel.readParcelable(FrameControl.class.getClassLoader());
            this.b = parcel.readInt();
            this.c = parcel.readInt();
            if (this.f14276a != null) {
                if (this.f14276a.b) {
                    this.d = parcel.readString();
                }
                if (this.f14276a.c) {
                    this.e = (Capability) parcel.readParcelable(Capability.class.getClassLoader());
                }
                if (a()) {
                    this.f = parcel.readString();
                }
                if (this.f14276a.c && this.e != null && this.e.e) {
                    this.g = (IoCapability) parcel.readParcelable(IoCapability.class.getClassLoader());
                }
                if (this.f14276a.d) {
                    this.h = (Event) parcel.readParcelable(Event.class.getClassLoader());
                }
                if (this.f14276a.f14279a) {
                    this.i = new int[3];
                    parcel.readIntArray(this.i);
                    if (this.f14276a.l >= 4) {
                        this.j = new int[4];
                    } else {
                        this.j = new int[1];
                    }
                    parcel.readIntArray(this.j);
                }
                if (this.f14276a.e) {
                    this.k = (Mesh) parcel.readParcelable(Mesh.class.getClassLoader());
                }
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    public void writeToParcel(Parcel parcel, int i2) {
        try {
            parcel.writeParcelable(this.f14276a, 0);
            parcel.writeInt(this.b);
            parcel.writeInt(this.c);
            if (this.f14276a != null) {
                if (this.f14276a.b) {
                    parcel.writeString(this.d);
                }
                if (this.f14276a.c) {
                    parcel.writeParcelable(this.e, 0);
                }
                if (a()) {
                    parcel.writeString(this.f);
                }
                if (this.f14276a.c && this.e != null && this.e.e) {
                    parcel.writeParcelable(this.g, 0);
                }
                if (this.f14276a.d) {
                    parcel.writeParcelable(this.h, 0);
                }
                if (this.f14276a.f14279a) {
                    parcel.writeIntArray(this.i);
                    parcel.writeIntArray(this.j);
                }
                if (this.f14276a.e) {
                    parcel.writeParcelable(this.k, 0);
                }
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    public static class FrameControl implements Parcelable {
        public static final Parcelable.Creator<FrameControl> CREATOR = new Parcelable.Creator<FrameControl>() {
            /* renamed from: a */
            public FrameControl createFromParcel(Parcel parcel) {
                return new FrameControl(parcel);
            }

            /* renamed from: a */
            public FrameControl[] newArray(int i) {
                return new FrameControl[i];
            }
        };
        public static final int h = 0;
        public static final int i = 1;
        public static final int j = 2;

        /* renamed from: a  reason: collision with root package name */
        public boolean f14279a;
        public boolean b;
        public boolean c;
        public boolean d;
        public boolean e;
        public boolean f;
        public boolean g;
        public int k;
        public int l;

        public int describeContents() {
            return 0;
        }

        FrameControl() {
        }

        protected FrameControl(Parcel parcel) {
            boolean z = false;
            this.f14279a = parcel.readByte() != 0;
            this.b = parcel.readByte() != 0;
            this.c = parcel.readByte() != 0;
            this.d = parcel.readByte() != 0;
            this.e = parcel.readByte() != 0;
            this.f = parcel.readByte() != 0;
            this.g = parcel.readByte() != 0 ? true : z;
            this.k = parcel.readInt();
            this.l = parcel.readInt();
        }

        public void writeToParcel(Parcel parcel, int i2) {
            parcel.writeInt(this.f14279a ? 1 : 0);
            parcel.writeInt(this.b ? 1 : 0);
            parcel.writeInt(this.c ? 1 : 0);
            parcel.writeInt(this.d ? 1 : 0);
            parcel.writeInt(this.e ? 1 : 0);
            parcel.writeInt(this.f ? 1 : 0);
            parcel.writeInt(this.g ? 1 : 0);
            parcel.writeInt(this.k);
            parcel.writeInt(this.l);
        }

        public String toString() {
            StringBuilder sb = new StringBuilder("");
            sb.append("encrypted = " + this.f14279a);
            sb.append("\n");
            sb.append("withMac = " + this.b);
            sb.append("\n");
            sb.append("withCapability = " + this.c);
            sb.append("\n");
            sb.append("withEvent = " + this.d);
            sb.append("\n");
            sb.append("withMesh = " + this.e);
            sb.append("\n");
            sb.append("registered = " + this.f);
            sb.append("\n");
            sb.append("bindingCfm = " + this.g);
            sb.append("\n");
            sb.append("authMode = " + this.k);
            sb.append("\n");
            sb.append("version = " + this.l);
            return sb.toString();
        }
    }

    public static class Capability implements Parcelable {
        public static final Parcelable.Creator<Capability> CREATOR = new Parcelable.Creator<Capability>() {
            /* renamed from: a */
            public Capability createFromParcel(Parcel parcel) {
                return new Capability(parcel);
            }

            /* renamed from: a */
            public Capability[] newArray(int i) {
                return new Capability[i];
            }
        };

        /* renamed from: a  reason: collision with root package name */
        public boolean f14277a;
        public boolean b;
        public boolean c;
        public int d;
        public boolean e;

        public int describeContents() {
            return 0;
        }

        Capability() {
        }

        protected Capability(Parcel parcel) {
            boolean z = false;
            this.f14277a = parcel.readByte() != 0;
            this.b = parcel.readByte() != 0;
            this.c = parcel.readByte() != 0;
            this.d = parcel.readInt();
            this.e = parcel.readByte() != 0 ? true : z;
        }

        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append("connectable = " + this.f14277a);
            sb.append("\n");
            sb.append("centralable = " + this.b);
            sb.append("\n");
            sb.append("encryptable = " + this.c);
            sb.append("\n");
            sb.append("bindable = " + this.d);
            sb.append("\n");
            sb.append("ioCapabilityable = " + this.e);
            sb.append("\n");
            return sb.toString();
        }

        public void writeToParcel(Parcel parcel, int i) {
            parcel.writeInt(this.f14277a ? 1 : 0);
            parcel.writeInt(this.b ? 1 : 0);
            parcel.writeInt(this.c ? 1 : 0);
            parcel.writeInt(this.d);
            parcel.writeInt(this.e ? 1 : 0);
        }
    }

    public static class IoCapability implements Parcelable {
        public static final Parcelable.Creator<IoCapability> CREATOR = new Parcelable.Creator<IoCapability>() {
            /* renamed from: a */
            public IoCapability createFromParcel(Parcel parcel) {
                return new IoCapability(parcel);
            }

            /* renamed from: a */
            public IoCapability[] newArray(int i) {
                return new IoCapability[i];
            }
        };

        /* renamed from: a  reason: collision with root package name */
        public static final int f14280a = 0;
        public static final int b = 1;
        public static final int c = 2;
        public static final int d = 4;
        public static final int e = 8;
        public static final int f = 0;
        public static final int g = 1;
        public static final int h = 2;
        public static final int i = 4;
        public static final int j = 8;
        public int k;
        public int l;

        public int describeContents() {
            return 0;
        }

        IoCapability() {
        }

        protected IoCapability(Parcel parcel) {
            this.k = parcel.readInt();
            this.l = parcel.readInt();
        }

        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append("inputCapability = " + this.k);
            sb.append("\n");
            sb.append("outputCapability = " + this.l);
            sb.append("\n");
            return sb.toString();
        }

        public void writeToParcel(Parcel parcel, int i2) {
            parcel.writeInt(this.k);
            parcel.writeInt(this.l);
        }
    }

    public static class Event implements Parcelable {
        public static final Parcelable.Creator<Event> CREATOR = new Parcelable.Creator<Event>() {
            /* renamed from: a */
            public Event createFromParcel(Parcel parcel) {
                return new Event(parcel);
            }

            /* renamed from: a */
            public Event[] newArray(int i) {
                return new Event[i];
            }
        };

        /* renamed from: a  reason: collision with root package name */
        public int f14278a;
        public int b;

        public int describeContents() {
            return 0;
        }

        Event() {
        }

        protected Event(Parcel parcel) {
            this.f14278a = parcel.readInt();
            this.b = parcel.readInt();
        }

        public String toString() {
            StringBuilder sb = new StringBuilder("");
            sb.append(String.format("eventId = 0x%x", new Object[]{Integer.valueOf(this.f14278a)}));
            sb.append("\n");
            sb.append("eventLength = " + this.b);
            return sb.toString();
        }

        public void writeToParcel(Parcel parcel, int i) {
            parcel.writeInt(this.f14278a);
            parcel.writeInt(this.b);
        }
    }

    public static class Mesh implements Parcelable {
        public static final Parcelable.Creator<Mesh> CREATOR = new Parcelable.Creator<Mesh>() {
            /* renamed from: a */
            public Mesh createFromParcel(Parcel parcel) {
                return new Mesh(parcel);
            }

            /* renamed from: a */
            public Mesh[] newArray(int i) {
                return new Mesh[i];
            }
        };

        /* renamed from: a  reason: collision with root package name */
        public static final int f14281a = 0;
        public static final int b = 3;
        public int c;
        public int d;
        public int e;

        public int describeContents() {
            return 0;
        }

        Mesh() {
        }

        protected Mesh(Parcel parcel) {
            this.c = parcel.readInt();
            this.d = parcel.readInt();
            this.e = parcel.readInt();
        }

        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append("pbType = " + this.c);
            sb.append("\n");
            sb.append("state = " + this.d);
            sb.append("\n");
            sb.append("version = " + this.e);
            sb.append("\n");
            return sb.toString();
        }

        public void writeToParcel(Parcel parcel, int i) {
            parcel.writeInt(this.c);
            parcel.writeInt(this.d);
            parcel.writeInt(this.e);
        }
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("productId = 0x%2x", new Object[]{Integer.valueOf(this.b)}));
        sb.append("\n");
        sb.append(String.format("frameCounter = %d", new Object[]{Integer.valueOf(this.c)}));
        sb.append("\n");
        sb.append(String.format("mac = %s", new Object[]{this.d}));
        sb.append("\n");
        if (this.f14276a != null) {
            sb.append("\n");
            sb.append("FrameControl: ");
            sb.append("\n");
            sb.append(this.f14276a.toString());
            sb.append("\n");
        }
        if (this.e != null) {
            sb.append("\n");
            sb.append("Capability: ");
            sb.append("\n");
            sb.append(this.e.toString());
            sb.append("\n");
        }
        if (this.h != null) {
            sb.append("\n");
            sb.append("Event: ");
            sb.append("\n");
            sb.append(this.h.toString());
            sb.append("\n");
        }
        if (this.g != null) {
            sb.append("\n");
            sb.append("IoCapability: ");
            sb.append("\n");
            sb.append(this.g.toString());
            sb.append("\n");
        }
        if (this.k != null) {
            sb.append("\n");
            sb.append("Mesh: ");
            sb.append("\n");
            sb.append(this.k.toString());
            sb.append("\n");
        }
        if (!TextUtils.isEmpty(this.f)) {
            sb.append("\n");
            sb.append(String.format("comboKey: %s", new Object[]{this.f}));
            sb.append("\n");
        }
        return sb.toString();
    }

    public boolean a() {
        return this.e != null && this.e.d == 3 && this.f14276a != null && this.f14276a.l >= 3;
    }

    public boolean b() {
        return this.f14276a != null && this.f14276a.g;
    }
}
