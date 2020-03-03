package com.xiaomi.mobilestats.data;

import cn.com.fmsh.tsm.business.constants.Constants;
import com.drew.metadata.exif.makernotes.OlympusRawInfoMakernoteDirectory;
import com.google.android.exoplayer2.C;
import com.google.protobuf.AbstractMessageLite;
import com.google.protobuf.ByteString;
import com.google.protobuf.CodedInputStream;
import com.google.protobuf.CodedOutputStream;
import com.google.protobuf.Descriptors;
import com.google.protobuf.ExtensionRegistry;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.GeneratedMessage;
import com.google.protobuf.Internal;
import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.Message;
import com.google.protobuf.MessageLite;
import com.google.protobuf.MessageOrBuilder;
import com.google.protobuf.Parser;
import com.google.protobuf.ProtocolMessageEnum;
import com.google.protobuf.RepeatedFieldBuilder;
import com.google.protobuf.SingleFieldBuilder;
import com.google.protobuf.UnknownFieldSet;
import com.tencent.smtt.export.external.TbsCoreSettings;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.cybergarage.upnp.control.ControlResponse;
import org.jacoco.agent.rt.internal_8ff85ea.asm.Opcodes;

public final class ProtoMsg {
    /* access modifiers changed from: private */
    public static final Descriptors.Descriptor O = getDescriptor().getMessageTypes().get(0);
    /* access modifiers changed from: private */
    public static GeneratedMessage.FieldAccessorTable P = new GeneratedMessage.FieldAccessorTable(O, new String[0]);
    /* access modifiers changed from: private */
    public static final Descriptors.Descriptor Q = O.getNestedTypes().get(0);
    /* access modifiers changed from: private */
    public static GeneratedMessage.FieldAccessorTable R = new GeneratedMessage.FieldAccessorTable(Q, new String[]{Constants.XMLNode.KEY, "Value"});
    /* access modifiers changed from: private */
    public static final Descriptors.Descriptor S = O.getNestedTypes().get(1);
    /* access modifiers changed from: private */
    public static GeneratedMessage.FieldAccessorTable T = new GeneratedMessage.FieldAccessorTable(S, new String[]{"Type", "SessionId", "UserId", "DeviceId", "StartMills", "EndMills", "Duration", "PageId", "PageMap", "Platform", TbsCoreSettings.TBS_SETTINGS_APP_KEY, "AppChannel", "PackageName", "LogVersionApp", "VersionCode", "VersionName", "SdkVerson", "OsVersion", "DeviceName", "MccMnc", "CellId"});
    /* access modifiers changed from: private */
    public static final Descriptors.Descriptor U = O.getNestedTypes().get(2);
    /* access modifiers changed from: private */
    public static GeneratedMessage.FieldAccessorTable V = new GeneratedMessage.FieldAccessorTable(U, new String[]{"Page"});
    /* access modifiers changed from: private */
    public static final Descriptors.Descriptor W = O.getNestedTypes().get(3);
    /* access modifiers changed from: private */
    public static GeneratedMessage.FieldAccessorTable X = new GeneratedMessage.FieldAccessorTable(W, new String[]{"Type", "SessionId", "UserId", "DeviceId", "Time", "EventId", "Label", "EventMap", "Platform", TbsCoreSettings.TBS_SETTINGS_APP_KEY, "AppChannel", "PackageName", "LogVersionApp"});
    /* access modifiers changed from: private */
    public static final Descriptors.Descriptor Y = O.getNestedTypes().get(4);
    /* access modifiers changed from: private */
    public static GeneratedMessage.FieldAccessorTable Z = new GeneratedMessage.FieldAccessorTable(Y, new String[]{"Event"});
    /* access modifiers changed from: private */
    public static final Descriptors.Descriptor aa = O.getNestedTypes().get(5);
    /* access modifiers changed from: private */
    public static GeneratedMessage.FieldAccessorTable ab = new GeneratedMessage.FieldAccessorTable(aa, new String[]{"Type", TbsCoreSettings.TBS_SETTINGS_APP_KEY, "AppChannel", "SessionId", "DeviceId", "UserId", "VersionCode", "VersionName", "SdkVerson", "OsVersion", "Platform", "Language", "Resolution", "DeviceName", "MacAddress", "HasBluetooth", "HasWifi", "HasGravity", "MccMnc", "CellId", "Lac", "HasGps", "Latitude", "Longitude", "PhoneType", "Time", "ClientMap", "Network", "NetworkDetail", "MiuiVersion", Constants.XMLNode.KEY, "PackageName", "Imei", "LogVersionApp", "SimOperator", "ChannelInfo"});
    /* access modifiers changed from: private */
    public static final Descriptors.Descriptor ac = O.getNestedTypes().get(6);
    /* access modifiers changed from: private */
    public static GeneratedMessage.FieldAccessorTable ad = new GeneratedMessage.FieldAccessorTable(ac, new String[]{ControlResponse.FAULT_CODE});
    /* access modifiers changed from: private */
    public static final Descriptors.Descriptor ae = O.getNestedTypes().get(7);
    /* access modifiers changed from: private */
    public static GeneratedMessage.FieldAccessorTable af = new GeneratedMessage.FieldAccessorTable(ae, new String[]{"Type", "Strategy", "Interval", "IsWifi", Constants.XMLNode.KEY});
    /* access modifiers changed from: private */
    public static final Descriptors.Descriptor ag = O.getNestedTypes().get(8);
    /* access modifiers changed from: private */
    public static GeneratedMessage.FieldAccessorTable ah = new GeneratedMessage.FieldAccessorTable(ag, new String[]{"Type", TbsCoreSettings.TBS_SETTINGS_APP_KEY, "AppChannel", "SessionId", "Activity", "Time", "DeviceId", "Exception", "ExceptionDetail", "NetworkType", "Network", "Url", "ErrorMap", "VersionCode", "VersionName", "UserId", "DeviceName", "Platform", "PackageName", "LogVersionApp"});
    /* access modifiers changed from: private */
    public static final Descriptors.Descriptor ai = O.getNestedTypes().get(9);
    /* access modifiers changed from: private */
    public static GeneratedMessage.FieldAccessorTable aj = new GeneratedMessage.FieldAccessorTable(ai, new String[]{"Error"});
    /* access modifiers changed from: private */
    public static final Descriptors.Descriptor ak = O.getNestedTypes().get(10);
    /* access modifiers changed from: private */
    public static GeneratedMessage.FieldAccessorTable al = new GeneratedMessage.FieldAccessorTable(ak, new String[]{"Type", TbsCoreSettings.TBS_SETTINGS_APP_KEY, "AppChannel", "SessionId", "Time", "DeviceId", "Throwable", "CrashMap", "VersionCode", "VersionName", "UserId", "DeviceName", "Platform", "MemoryClass", "LargeMemoryClass", "CrashNum", "CrashMd5", "PackageName", "LogVersionApp"});
    /* access modifiers changed from: private */
    public static final Descriptors.Descriptor am = O.getNestedTypes().get(11);
    /* access modifiers changed from: private */
    public static GeneratedMessage.FieldAccessorTable an = new GeneratedMessage.FieldAccessorTable(am, new String[]{"Crash"});
    /* access modifiers changed from: private */
    public static final Descriptors.Descriptor ao = O.getNestedTypes().get(12);
    /* access modifiers changed from: private */
    public static GeneratedMessage.FieldAccessorTable ap = new GeneratedMessage.FieldAccessorTable(ao, new String[]{"Type", "SessionId", "UserId", "DeviceId", "Time", "ViewId", "Label", "ViewMap", "Platform", TbsCoreSettings.TBS_SETTINGS_APP_KEY, "AppChannel", "PackageName", "LogVersionApp"});
    /* access modifiers changed from: private */
    public static final Descriptors.Descriptor aq = O.getNestedTypes().get(13);
    /* access modifiers changed from: private */
    public static GeneratedMessage.FieldAccessorTable ar = new GeneratedMessage.FieldAccessorTable(aq, new String[]{"View"});
    /* access modifiers changed from: private */
    public static final Descriptors.Descriptor as = O.getNestedTypes().get(14);
    /* access modifiers changed from: private */
    public static GeneratedMessage.FieldAccessorTable at = new GeneratedMessage.FieldAccessorTable(as, new String[]{"Code", "Msg", "Map"});
    /* access modifiers changed from: private */
    public static Descriptors.FileDescriptor au;

    public final class StatsMsg extends GeneratedMessage implements StatsMsgOrBuilder {
        public static Parser PARSER = new b();
        private static final StatsMsg av = new StatsMsg(true);
        private final UnknownFieldSet aw;
        private byte ax;
        private int ay;

        public final class Builder extends GeneratedMessage.Builder implements StatsMsgOrBuilder {
            private Builder() {
                R();
            }

            private Builder(GeneratedMessage.BuilderParent builderParent) {
                super(builderParent);
                R();
            }

            /* synthetic */ Builder(GeneratedMessage.BuilderParent builderParent, a aVar) {
                this(builderParent);
            }

            private void R() {
                boolean unused = StatsMsg.alwaysUseFieldBuilders;
            }

            /* access modifiers changed from: private */
            public static Builder S() {
                return new Builder();
            }

            public static final Descriptors.Descriptor getDescriptor() {
                return ProtoMsg.O;
            }

            public StatsMsg build() {
                StatsMsg buildPartial = buildPartial();
                if (buildPartial.isInitialized()) {
                    return buildPartial;
                }
                throw newUninitializedMessageException(buildPartial);
            }

            public StatsMsg buildPartial() {
                StatsMsg statsMsg = new StatsMsg((GeneratedMessage.Builder) this, (a) null);
                onBuilt();
                return statsMsg;
            }

            public Builder clear() {
                super.clear();
                return this;
            }

            public Builder clone() {
                return S().mergeFrom(buildPartial());
            }

            public StatsMsg getDefaultInstanceForType() {
                return StatsMsg.getDefaultInstance();
            }

            public Descriptors.Descriptor getDescriptorForType() {
                return ProtoMsg.O;
            }

            /* access modifiers changed from: protected */
            public GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
                return ProtoMsg.P.ensureFieldAccessorsInitialized(StatsMsg.class, Builder.class);
            }

            public final boolean isInitialized() {
                return true;
            }

            public Builder mergeFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) {
                StatsMsg statsMsg;
                StatsMsg statsMsg2 = null;
                try {
                    StatsMsg statsMsg3 = (StatsMsg) StatsMsg.PARSER.parsePartialFrom(codedInputStream, extensionRegistryLite);
                    if (statsMsg3 != null) {
                        mergeFrom(statsMsg3);
                    }
                    return this;
                } catch (InvalidProtocolBufferException e) {
                    statsMsg = (StatsMsg) e.getUnfinishedMessage();
                    throw e;
                } catch (Throwable th) {
                    th = th;
                    statsMsg2 = statsMsg;
                }
                if (statsMsg2 != null) {
                    mergeFrom(statsMsg2);
                }
                throw th;
            }

            public Builder mergeFrom(Message message) {
                if (message instanceof StatsMsg) {
                    return mergeFrom((StatsMsg) message);
                }
                super.mergeFrom(message);
                return this;
            }

            public Builder mergeFrom(StatsMsg statsMsg) {
                if (statsMsg == StatsMsg.getDefaultInstance()) {
                    return this;
                }
                mergeUnknownFields(statsMsg.getUnknownFields());
                return this;
            }
        }

        public final class Client extends GeneratedMessage implements ClientOrBuilder {
            public static final int APP_CHANNEL_FIELD_NUMBER = 3;
            public static final int APP_KEY_FIELD_NUMBER = 2;
            public static final int CELL_ID_FIELD_NUMBER = 20;
            public static final int CHANNEL_INFO_FIELD_NUMBER = 36;
            public static final int CLIENT_MAP_FIELD_NUMBER = 27;
            public static final int DEVICE_ID_FIELD_NUMBER = 5;
            public static final int DEVICE_NAME_FIELD_NUMBER = 14;
            public static final int HAS_BLUETOOTH_FIELD_NUMBER = 16;
            public static final int HAS_GPS_FIELD_NUMBER = 22;
            public static final int HAS_GRAVITY_FIELD_NUMBER = 18;
            public static final int HAS_WIFI_FIELD_NUMBER = 17;
            public static final int IMEI_FIELD_NUMBER = 33;
            public static final int KEY_FIELD_NUMBER = 31;
            public static final int LAC_FIELD_NUMBER = 21;
            public static final int LANGUAGE_FIELD_NUMBER = 12;
            public static final int LATITUDE_FIELD_NUMBER = 23;
            public static final int LOG_VERSION_APP_FIELD_NUMBER = 34;
            public static final int LONGITUDE_FIELD_NUMBER = 24;
            public static final int MAC_ADDRESS_FIELD_NUMBER = 15;
            public static final int MCC_MNC_FIELD_NUMBER = 19;
            public static final int MIUI_VERSION_FIELD_NUMBER = 30;
            public static final int NETWORK_DETAIL_FIELD_NUMBER = 29;
            public static final int NETWORK_FIELD_NUMBER = 28;
            public static final int OS_VERSION_FIELD_NUMBER = 10;
            public static final int PACKAGE_NAME_FIELD_NUMBER = 32;
            public static Parser PARSER = new c();
            public static final int PHONE_TYPE_FIELD_NUMBER = 25;
            public static final int PLATFORM_FIELD_NUMBER = 11;
            public static final int RESOLUTION_FIELD_NUMBER = 13;
            public static final int SDK_VERSON_FIELD_NUMBER = 9;
            public static final int SESSION_ID_FIELD_NUMBER = 4;
            public static final int SIM_OPERATOR_FIELD_NUMBER = 35;
            public static final int TIME_FIELD_NUMBER = 26;
            public static final int TYPE_FIELD_NUMBER = 1;
            public static final int USER_ID_FIELD_NUMBER = 6;
            public static final int VERSION_CODE_FIELD_NUMBER = 7;
            public static final int VERSION_NAME_FIELD_NUMBER = 8;
            private static final Client az = new Client(true);
            /* access modifiers changed from: private */
            public int aA;
            /* access modifiers changed from: private */
            public int aB;
            /* access modifiers changed from: private */
            public Object aC;
            /* access modifiers changed from: private */
            public Object aD;
            /* access modifiers changed from: private */
            public Object aE;
            /* access modifiers changed from: private */
            public Object aF;
            /* access modifiers changed from: private */
            public Object aG;
            /* access modifiers changed from: private */
            public Object aH;
            /* access modifiers changed from: private */
            public int aI;
            /* access modifiers changed from: private */
            public Object aJ;
            /* access modifiers changed from: private */
            public Object aK;
            /* access modifiers changed from: private */
            public Object aL;
            /* access modifiers changed from: private */
            public Object aM;
            /* access modifiers changed from: private */
            public Object aN;
            /* access modifiers changed from: private */
            public Object aO;
            /* access modifiers changed from: private */
            public Object aP;
            /* access modifiers changed from: private */
            public Object aQ;
            /* access modifiers changed from: private */
            public boolean aR;
            /* access modifiers changed from: private */
            public boolean aS;
            /* access modifiers changed from: private */
            public boolean aT;
            /* access modifiers changed from: private */
            public Object aU;
            /* access modifiers changed from: private */
            public Object aV;
            /* access modifiers changed from: private */
            public Object aW;
            /* access modifiers changed from: private */
            public boolean aX;
            /* access modifiers changed from: private */
            public Object aY;
            /* access modifiers changed from: private */
            public Object aZ;
            private final UnknownFieldSet aw;
            private byte ax;
            private int ay;
            /* access modifiers changed from: private */
            public Object ba;
            /* access modifiers changed from: private */
            public long bb;
            /* access modifiers changed from: private */
            public List bc;
            /* access modifiers changed from: private */
            public NetworkType bd;
            /* access modifiers changed from: private */
            public Object be;
            /* access modifiers changed from: private */
            public Object bf;
            /* access modifiers changed from: private */
            public Object bg;
            /* access modifiers changed from: private */
            public Object bh;
            /* access modifiers changed from: private */
            public Object bi;
            /* access modifiers changed from: private */
            public Object bj;
            /* access modifiers changed from: private */
            public Object bk;
            /* access modifiers changed from: private */
            public Object bl;

            public final class Builder extends GeneratedMessage.Builder implements ClientOrBuilder {
                private int aA;
                private int aB;
                private Object aC;
                private Object aD;
                private Object aE;
                private Object aF;
                private Object aG;
                private Object aH;
                private int aI;
                private Object aJ;
                private Object aK;
                private Object aL;
                private Object aM;
                private Object aN;
                private Object aO;
                private Object aP;
                private Object aQ;
                private boolean aR;
                private boolean aS;
                private boolean aT;
                private Object aU;
                private Object aV;
                private Object aW;
                private boolean aX;
                private Object aY;
                private Object aZ;
                private Object ba;
                private long bb;
                private List bc;
                private NetworkType bd;
                private Object be;
                private Object bf;
                private Object bg;
                private Object bh;
                private Object bi;
                private Object bj;
                private Object bk;
                private Object bl;
                private RepeatedFieldBuilder bm;

                private Builder() {
                    this.aC = "";
                    this.aD = "";
                    this.aE = "";
                    this.aF = "";
                    this.aG = "";
                    this.aH = "";
                    this.aJ = "";
                    this.aK = "";
                    this.aL = "";
                    this.aM = "";
                    this.aN = "";
                    this.aO = "";
                    this.aP = "";
                    this.aQ = "";
                    this.aU = "";
                    this.aV = "";
                    this.aW = "";
                    this.aY = "";
                    this.aZ = "";
                    this.ba = "";
                    this.bc = Collections.emptyList();
                    this.bd = NetworkType.NETWORK_UNKNOWN;
                    this.be = "";
                    this.bf = "";
                    this.bg = "";
                    this.bh = "";
                    this.bi = "";
                    this.bj = "";
                    this.bk = "";
                    this.bl = "";
                    R();
                }

                private Builder(GeneratedMessage.BuilderParent builderParent) {
                    super(builderParent);
                    this.aC = "";
                    this.aD = "";
                    this.aE = "";
                    this.aF = "";
                    this.aG = "";
                    this.aH = "";
                    this.aJ = "";
                    this.aK = "";
                    this.aL = "";
                    this.aM = "";
                    this.aN = "";
                    this.aO = "";
                    this.aP = "";
                    this.aQ = "";
                    this.aU = "";
                    this.aV = "";
                    this.aW = "";
                    this.aY = "";
                    this.aZ = "";
                    this.ba = "";
                    this.bc = Collections.emptyList();
                    this.bd = NetworkType.NETWORK_UNKNOWN;
                    this.be = "";
                    this.bf = "";
                    this.bg = "";
                    this.bh = "";
                    this.bi = "";
                    this.bj = "";
                    this.bk = "";
                    this.bl = "";
                    R();
                }

                /* synthetic */ Builder(GeneratedMessage.BuilderParent builderParent, a aVar) {
                    this(builderParent);
                }

                private void R() {
                    if (Client.alwaysUseFieldBuilders) {
                        Y();
                    }
                }

                /* access modifiers changed from: private */
                public static Builder W() {
                    return new Builder();
                }

                private void X() {
                    if ((this.aA & com.xiaomi.mishopsdk.util.Constants.CALLIGRAPHY_TAG_PRICE) != 67108864) {
                        this.bc = new ArrayList(this.bc);
                        this.aA |= com.xiaomi.mishopsdk.util.Constants.CALLIGRAPHY_TAG_PRICE;
                    }
                }

                private RepeatedFieldBuilder Y() {
                    if (this.bm == null) {
                        this.bm = new RepeatedFieldBuilder(this.bc, (this.aA & com.xiaomi.mishopsdk.util.Constants.CALLIGRAPHY_TAG_PRICE) == 67108864, getParentForChildren(), isClean());
                        this.bc = null;
                    }
                    return this.bm;
                }

                public static final Descriptors.Descriptor getDescriptor() {
                    return ProtoMsg.aa;
                }

                public Builder addAllClientMap(Iterable iterable) {
                    if (this.bm == null) {
                        X();
                        AbstractMessageLite.Builder.addAll(iterable, this.bc);
                        onChanged();
                    } else {
                        this.bm.addAllMessages(iterable);
                    }
                    return this;
                }

                public Builder addClientMap(int i, ProtoMap.Builder builder) {
                    if (this.bm == null) {
                        X();
                        this.bc.add(i, builder.build());
                        onChanged();
                    } else {
                        this.bm.addMessage(i, builder.build());
                    }
                    return this;
                }

                public Builder addClientMap(int i, ProtoMap protoMap) {
                    if (this.bm != null) {
                        this.bm.addMessage(i, protoMap);
                    } else if (protoMap != null) {
                        X();
                        this.bc.add(i, protoMap);
                        onChanged();
                    } else {
                        throw new NullPointerException();
                    }
                    return this;
                }

                public Builder addClientMap(ProtoMap.Builder builder) {
                    if (this.bm == null) {
                        X();
                        this.bc.add(builder.build());
                        onChanged();
                    } else {
                        this.bm.addMessage(builder.build());
                    }
                    return this;
                }

                public Builder addClientMap(ProtoMap protoMap) {
                    if (this.bm != null) {
                        this.bm.addMessage(protoMap);
                    } else if (protoMap != null) {
                        X();
                        this.bc.add(protoMap);
                        onChanged();
                    } else {
                        throw new NullPointerException();
                    }
                    return this;
                }

                public ProtoMap.Builder addClientMapBuilder() {
                    return (ProtoMap.Builder) Y().addBuilder(ProtoMap.getDefaultInstance());
                }

                public ProtoMap.Builder addClientMapBuilder(int i) {
                    return (ProtoMap.Builder) Y().addBuilder(i, ProtoMap.getDefaultInstance());
                }

                public Client build() {
                    Client buildPartial = buildPartial();
                    if (buildPartial.isInitialized()) {
                        return buildPartial;
                    }
                    throw newUninitializedMessageException(buildPartial);
                }

                public Client buildPartial() {
                    List list;
                    Client client = new Client((GeneratedMessage.Builder) this, (a) null);
                    int i = this.aA;
                    int i2 = this.aB;
                    int i3 = 1;
                    int i4 = (i & 1) == 1 ? 1 : 0;
                    Object unused = client.aC = this.aC;
                    if ((i & 2) == 2) {
                        i4 |= 2;
                    }
                    Object unused2 = client.aD = this.aD;
                    if ((i & 4) == 4) {
                        i4 |= 4;
                    }
                    Object unused3 = client.aE = this.aE;
                    if ((i & 8) == 8) {
                        i4 |= 8;
                    }
                    Object unused4 = client.aF = this.aF;
                    if ((i & 16) == 16) {
                        i4 |= 16;
                    }
                    Object unused5 = client.aG = this.aG;
                    if ((i & 32) == 32) {
                        i4 |= 32;
                    }
                    Object unused6 = client.aH = this.aH;
                    if ((i & 64) == 64) {
                        i4 |= 64;
                    }
                    int unused7 = client.aI = this.aI;
                    if ((i & 128) == 128) {
                        i4 |= 128;
                    }
                    Object unused8 = client.aJ = this.aJ;
                    if ((i & 256) == 256) {
                        i4 |= 256;
                    }
                    Object unused9 = client.aK = this.aK;
                    if ((i & 512) == 512) {
                        i4 |= 512;
                    }
                    Object unused10 = client.aL = this.aL;
                    if ((i & 1024) == 1024) {
                        i4 |= 1024;
                    }
                    Object unused11 = client.aM = this.aM;
                    if ((i & 2048) == 2048) {
                        i4 |= 2048;
                    }
                    Object unused12 = client.aN = this.aN;
                    if ((i & 4096) == 4096) {
                        i4 |= 4096;
                    }
                    Object unused13 = client.aO = this.aO;
                    if ((i & 8192) == 8192) {
                        i4 |= 8192;
                    }
                    Object unused14 = client.aP = this.aP;
                    if ((i & 16384) == 16384) {
                        i4 |= 16384;
                    }
                    Object unused15 = client.aQ = this.aQ;
                    if ((32768 & i) == 32768) {
                        i4 |= 32768;
                    }
                    boolean unused16 = client.aR = this.aR;
                    if ((65536 & i) == 65536) {
                        i4 |= 65536;
                    }
                    boolean unused17 = client.aS = this.aS;
                    if ((131072 & i) == 131072) {
                        i4 |= 131072;
                    }
                    boolean unused18 = client.aT = this.aT;
                    if ((262144 & i) == 262144) {
                        i4 |= 262144;
                    }
                    Object unused19 = client.aU = this.aU;
                    if ((524288 & i) == 524288) {
                        i4 |= 524288;
                    }
                    Object unused20 = client.aV = this.aV;
                    if ((1048576 & i) == 1048576) {
                        i4 |= 1048576;
                    }
                    Object unused21 = client.aW = this.aW;
                    if ((2097152 & i) == 2097152) {
                        i4 |= 2097152;
                    }
                    boolean unused22 = client.aX = this.aX;
                    if ((4194304 & i) == 4194304) {
                        i4 |= 4194304;
                    }
                    Object unused23 = client.aY = this.aY;
                    if ((8388608 & i) == 8388608) {
                        i4 |= 8388608;
                    }
                    Object unused24 = client.aZ = this.aZ;
                    if ((16777216 & i) == 16777216) {
                        i4 |= 16777216;
                    }
                    Object unused25 = client.ba = this.ba;
                    if ((33554432 & i) == 33554432) {
                        i4 |= 33554432;
                    }
                    long unused26 = client.bb = this.bb;
                    if (this.bm == null) {
                        if ((this.aA & com.xiaomi.mishopsdk.util.Constants.CALLIGRAPHY_TAG_PRICE) == 67108864) {
                            this.bc = Collections.unmodifiableList(this.bc);
                            this.aA &= -67108865;
                        }
                        list = this.bc;
                    } else {
                        list = this.bm.build();
                    }
                    List unused27 = client.bc = list;
                    if ((134217728 & i) == 134217728) {
                        i4 |= com.xiaomi.mishopsdk.util.Constants.CALLIGRAPHY_TAG_PRICE;
                    }
                    NetworkType unused28 = client.bd = this.bd;
                    if ((268435456 & i) == 268435456) {
                        i4 |= 134217728;
                    }
                    Object unused29 = client.be = this.be;
                    if ((536870912 & i) == 536870912) {
                        i4 |= C.ENCODING_PCM_MU_LAW;
                    }
                    Object unused30 = client.bf = this.bf;
                    if ((1073741824 & i) == 1073741824) {
                        i4 |= 536870912;
                    }
                    Object unused31 = client.bg = this.bg;
                    if ((i & Integer.MIN_VALUE) == Integer.MIN_VALUE) {
                        i4 |= 1073741824;
                    }
                    Object unused32 = client.bh = this.bh;
                    if ((i2 & 1) == 1) {
                        i4 |= Integer.MIN_VALUE;
                    }
                    Object unused33 = client.bi = this.bi;
                    if ((i2 & 2) != 2) {
                        i3 = 0;
                    }
                    Object unused34 = client.bj = this.bj;
                    if ((i2 & 4) == 4) {
                        i3 |= 2;
                    }
                    Object unused35 = client.bk = this.bk;
                    if ((i2 & 8) == 8) {
                        i3 |= 4;
                    }
                    Object unused36 = client.bl = this.bl;
                    int unused37 = client.aA = i4;
                    int unused38 = client.aB = i3;
                    onBuilt();
                    return client;
                }

                public Builder clear() {
                    super.clear();
                    this.aC = "";
                    this.aA &= -2;
                    this.aD = "";
                    this.aA &= -3;
                    this.aE = "";
                    this.aA &= -5;
                    this.aF = "";
                    this.aA &= -9;
                    this.aG = "";
                    this.aA &= -17;
                    this.aH = "";
                    this.aA &= -33;
                    this.aI = 0;
                    this.aA &= -65;
                    this.aJ = "";
                    this.aA &= -129;
                    this.aK = "";
                    this.aA &= -257;
                    this.aL = "";
                    this.aA &= -513;
                    this.aM = "";
                    this.aA &= -1025;
                    this.aN = "";
                    this.aA &= -2049;
                    this.aO = "";
                    this.aA &= -4097;
                    this.aP = "";
                    this.aA &= -8193;
                    this.aQ = "";
                    this.aA &= -16385;
                    this.aR = false;
                    this.aA &= -32769;
                    this.aS = false;
                    this.aA &= -65537;
                    this.aT = false;
                    this.aA &= -131073;
                    this.aU = "";
                    this.aA &= -262145;
                    this.aV = "";
                    this.aA &= -524289;
                    this.aW = "";
                    this.aA &= -1048577;
                    this.aX = false;
                    this.aA &= -2097153;
                    this.aY = "";
                    this.aA &= -4194305;
                    this.aZ = "";
                    this.aA &= -8388609;
                    this.ba = "";
                    this.aA &= -16777217;
                    this.bb = 0;
                    this.aA &= -33554433;
                    if (this.bm == null) {
                        this.bc = Collections.emptyList();
                        this.aA &= -67108865;
                    } else {
                        this.bm.clear();
                    }
                    this.bd = NetworkType.NETWORK_UNKNOWN;
                    this.aA &= -134217729;
                    this.be = "";
                    this.aA &= -268435457;
                    this.bf = "";
                    this.aA &= -536870913;
                    this.bg = "";
                    this.aA &= -1073741825;
                    this.bh = "";
                    this.aA &= Integer.MAX_VALUE;
                    this.bi = "";
                    this.aB &= -2;
                    this.bj = "";
                    this.aB &= -3;
                    this.bk = "";
                    this.aB &= -5;
                    this.bl = "";
                    this.aB &= -9;
                    return this;
                }

                public Builder clearAppChannel() {
                    this.aA &= -5;
                    this.aE = Client.getDefaultInstance().getAppChannel();
                    onChanged();
                    return this;
                }

                public Builder clearAppKey() {
                    this.aA &= -3;
                    this.aD = Client.getDefaultInstance().getAppKey();
                    onChanged();
                    return this;
                }

                public Builder clearCellId() {
                    this.aA &= -524289;
                    this.aV = Client.getDefaultInstance().getCellId();
                    onChanged();
                    return this;
                }

                public Builder clearChannelInfo() {
                    this.aB &= -9;
                    this.bl = Client.getDefaultInstance().getChannelInfo();
                    onChanged();
                    return this;
                }

                public Builder clearClientMap() {
                    if (this.bm == null) {
                        this.bc = Collections.emptyList();
                        this.aA &= -67108865;
                        onChanged();
                    } else {
                        this.bm.clear();
                    }
                    return this;
                }

                public Builder clearDeviceId() {
                    this.aA &= -17;
                    this.aG = Client.getDefaultInstance().getDeviceId();
                    onChanged();
                    return this;
                }

                public Builder clearDeviceName() {
                    this.aA &= -8193;
                    this.aP = Client.getDefaultInstance().getDeviceName();
                    onChanged();
                    return this;
                }

                public Builder clearHasBluetooth() {
                    this.aA &= -32769;
                    this.aR = false;
                    onChanged();
                    return this;
                }

                public Builder clearHasGps() {
                    this.aA &= -2097153;
                    this.aX = false;
                    onChanged();
                    return this;
                }

                public Builder clearHasGravity() {
                    this.aA &= -131073;
                    this.aT = false;
                    onChanged();
                    return this;
                }

                public Builder clearHasWifi() {
                    this.aA &= -65537;
                    this.aS = false;
                    onChanged();
                    return this;
                }

                public Builder clearImei() {
                    this.aB &= -2;
                    this.bi = Client.getDefaultInstance().getImei();
                    onChanged();
                    return this;
                }

                public Builder clearKey() {
                    this.aA &= -1073741825;
                    this.bg = Client.getDefaultInstance().getKey();
                    onChanged();
                    return this;
                }

                public Builder clearLac() {
                    this.aA &= -1048577;
                    this.aW = Client.getDefaultInstance().getLac();
                    onChanged();
                    return this;
                }

                public Builder clearLanguage() {
                    this.aA &= -2049;
                    this.aN = Client.getDefaultInstance().getLanguage();
                    onChanged();
                    return this;
                }

                public Builder clearLatitude() {
                    this.aA &= -4194305;
                    this.aY = Client.getDefaultInstance().getLatitude();
                    onChanged();
                    return this;
                }

                public Builder clearLogVersionApp() {
                    this.aB &= -3;
                    this.bj = Client.getDefaultInstance().getLogVersionApp();
                    onChanged();
                    return this;
                }

                public Builder clearLongitude() {
                    this.aA &= -8388609;
                    this.aZ = Client.getDefaultInstance().getLongitude();
                    onChanged();
                    return this;
                }

                public Builder clearMacAddress() {
                    this.aA &= -16385;
                    this.aQ = Client.getDefaultInstance().getMacAddress();
                    onChanged();
                    return this;
                }

                public Builder clearMccMnc() {
                    this.aA &= -262145;
                    this.aU = Client.getDefaultInstance().getMccMnc();
                    onChanged();
                    return this;
                }

                public Builder clearMiuiVersion() {
                    this.aA &= -536870913;
                    this.bf = Client.getDefaultInstance().getMiuiVersion();
                    onChanged();
                    return this;
                }

                public Builder clearNetwork() {
                    this.aA &= -134217729;
                    this.bd = NetworkType.NETWORK_UNKNOWN;
                    onChanged();
                    return this;
                }

                public Builder clearNetworkDetail() {
                    this.aA &= -268435457;
                    this.be = Client.getDefaultInstance().getNetworkDetail();
                    onChanged();
                    return this;
                }

                public Builder clearOsVersion() {
                    this.aA &= -513;
                    this.aL = Client.getDefaultInstance().getOsVersion();
                    onChanged();
                    return this;
                }

                public Builder clearPackageName() {
                    this.aA &= Integer.MAX_VALUE;
                    this.bh = Client.getDefaultInstance().getPackageName();
                    onChanged();
                    return this;
                }

                public Builder clearPhoneType() {
                    this.aA &= -16777217;
                    this.ba = Client.getDefaultInstance().getPhoneType();
                    onChanged();
                    return this;
                }

                public Builder clearPlatform() {
                    this.aA &= -1025;
                    this.aM = Client.getDefaultInstance().getPlatform();
                    onChanged();
                    return this;
                }

                public Builder clearResolution() {
                    this.aA &= -4097;
                    this.aO = Client.getDefaultInstance().getResolution();
                    onChanged();
                    return this;
                }

                public Builder clearSdkVerson() {
                    this.aA &= -257;
                    this.aK = Client.getDefaultInstance().getSdkVerson();
                    onChanged();
                    return this;
                }

                public Builder clearSessionId() {
                    this.aA &= -9;
                    this.aF = Client.getDefaultInstance().getSessionId();
                    onChanged();
                    return this;
                }

                public Builder clearSimOperator() {
                    this.aB &= -5;
                    this.bk = Client.getDefaultInstance().getSimOperator();
                    onChanged();
                    return this;
                }

                public Builder clearTime() {
                    this.aA &= -33554433;
                    this.bb = 0;
                    onChanged();
                    return this;
                }

                public Builder clearType() {
                    this.aA &= -2;
                    this.aC = Client.getDefaultInstance().getType();
                    onChanged();
                    return this;
                }

                public Builder clearUserId() {
                    this.aA &= -33;
                    this.aH = Client.getDefaultInstance().getUserId();
                    onChanged();
                    return this;
                }

                public Builder clearVersionCode() {
                    this.aA &= -65;
                    this.aI = 0;
                    onChanged();
                    return this;
                }

                public Builder clearVersionName() {
                    this.aA &= -129;
                    this.aJ = Client.getDefaultInstance().getVersionName();
                    onChanged();
                    return this;
                }

                public Builder clone() {
                    return W().mergeFrom(buildPartial());
                }

                public String getAppChannel() {
                    Object obj = this.aE;
                    if (obj instanceof String) {
                        return (String) obj;
                    }
                    ByteString byteString = (ByteString) obj;
                    String stringUtf8 = byteString.toStringUtf8();
                    if (byteString.isValidUtf8()) {
                        this.aE = stringUtf8;
                    }
                    return stringUtf8;
                }

                public ByteString getAppChannelBytes() {
                    Object obj = this.aE;
                    if (!(obj instanceof String)) {
                        return (ByteString) obj;
                    }
                    ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                    this.aE = copyFromUtf8;
                    return copyFromUtf8;
                }

                public String getAppKey() {
                    Object obj = this.aD;
                    if (obj instanceof String) {
                        return (String) obj;
                    }
                    ByteString byteString = (ByteString) obj;
                    String stringUtf8 = byteString.toStringUtf8();
                    if (byteString.isValidUtf8()) {
                        this.aD = stringUtf8;
                    }
                    return stringUtf8;
                }

                public ByteString getAppKeyBytes() {
                    Object obj = this.aD;
                    if (!(obj instanceof String)) {
                        return (ByteString) obj;
                    }
                    ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                    this.aD = copyFromUtf8;
                    return copyFromUtf8;
                }

                public String getCellId() {
                    Object obj = this.aV;
                    if (obj instanceof String) {
                        return (String) obj;
                    }
                    ByteString byteString = (ByteString) obj;
                    String stringUtf8 = byteString.toStringUtf8();
                    if (byteString.isValidUtf8()) {
                        this.aV = stringUtf8;
                    }
                    return stringUtf8;
                }

                public ByteString getCellIdBytes() {
                    Object obj = this.aV;
                    if (!(obj instanceof String)) {
                        return (ByteString) obj;
                    }
                    ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                    this.aV = copyFromUtf8;
                    return copyFromUtf8;
                }

                public String getChannelInfo() {
                    Object obj = this.bl;
                    if (obj instanceof String) {
                        return (String) obj;
                    }
                    ByteString byteString = (ByteString) obj;
                    String stringUtf8 = byteString.toStringUtf8();
                    if (byteString.isValidUtf8()) {
                        this.bl = stringUtf8;
                    }
                    return stringUtf8;
                }

                public ByteString getChannelInfoBytes() {
                    Object obj = this.bl;
                    if (!(obj instanceof String)) {
                        return (ByteString) obj;
                    }
                    ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                    this.bl = copyFromUtf8;
                    return copyFromUtf8;
                }

                public ProtoMap getClientMap(int i) {
                    return (ProtoMap) (this.bm == null ? this.bc.get(i) : this.bm.getMessage(i));
                }

                public ProtoMap.Builder getClientMapBuilder(int i) {
                    return (ProtoMap.Builder) Y().getBuilder(i);
                }

                public List getClientMapBuilderList() {
                    return Y().getBuilderList();
                }

                public int getClientMapCount() {
                    return this.bm == null ? this.bc.size() : this.bm.getCount();
                }

                public List getClientMapList() {
                    return this.bm == null ? Collections.unmodifiableList(this.bc) : this.bm.getMessageList();
                }

                public ProtoMapOrBuilder getClientMapOrBuilder(int i) {
                    return (ProtoMapOrBuilder) (this.bm == null ? this.bc.get(i) : this.bm.getMessageOrBuilder(i));
                }

                public List getClientMapOrBuilderList() {
                    return this.bm != null ? this.bm.getMessageOrBuilderList() : Collections.unmodifiableList(this.bc);
                }

                public Client getDefaultInstanceForType() {
                    return Client.getDefaultInstance();
                }

                public Descriptors.Descriptor getDescriptorForType() {
                    return ProtoMsg.aa;
                }

                public String getDeviceId() {
                    Object obj = this.aG;
                    if (obj instanceof String) {
                        return (String) obj;
                    }
                    ByteString byteString = (ByteString) obj;
                    String stringUtf8 = byteString.toStringUtf8();
                    if (byteString.isValidUtf8()) {
                        this.aG = stringUtf8;
                    }
                    return stringUtf8;
                }

                public ByteString getDeviceIdBytes() {
                    Object obj = this.aG;
                    if (!(obj instanceof String)) {
                        return (ByteString) obj;
                    }
                    ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                    this.aG = copyFromUtf8;
                    return copyFromUtf8;
                }

                public String getDeviceName() {
                    Object obj = this.aP;
                    if (obj instanceof String) {
                        return (String) obj;
                    }
                    ByteString byteString = (ByteString) obj;
                    String stringUtf8 = byteString.toStringUtf8();
                    if (byteString.isValidUtf8()) {
                        this.aP = stringUtf8;
                    }
                    return stringUtf8;
                }

                public ByteString getDeviceNameBytes() {
                    Object obj = this.aP;
                    if (!(obj instanceof String)) {
                        return (ByteString) obj;
                    }
                    ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                    this.aP = copyFromUtf8;
                    return copyFromUtf8;
                }

                public boolean getHasBluetooth() {
                    return this.aR;
                }

                public boolean getHasGps() {
                    return this.aX;
                }

                public boolean getHasGravity() {
                    return this.aT;
                }

                public boolean getHasWifi() {
                    return this.aS;
                }

                public String getImei() {
                    Object obj = this.bi;
                    if (obj instanceof String) {
                        return (String) obj;
                    }
                    ByteString byteString = (ByteString) obj;
                    String stringUtf8 = byteString.toStringUtf8();
                    if (byteString.isValidUtf8()) {
                        this.bi = stringUtf8;
                    }
                    return stringUtf8;
                }

                public ByteString getImeiBytes() {
                    Object obj = this.bi;
                    if (!(obj instanceof String)) {
                        return (ByteString) obj;
                    }
                    ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                    this.bi = copyFromUtf8;
                    return copyFromUtf8;
                }

                public String getKey() {
                    Object obj = this.bg;
                    if (obj instanceof String) {
                        return (String) obj;
                    }
                    ByteString byteString = (ByteString) obj;
                    String stringUtf8 = byteString.toStringUtf8();
                    if (byteString.isValidUtf8()) {
                        this.bg = stringUtf8;
                    }
                    return stringUtf8;
                }

                public ByteString getKeyBytes() {
                    Object obj = this.bg;
                    if (!(obj instanceof String)) {
                        return (ByteString) obj;
                    }
                    ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                    this.bg = copyFromUtf8;
                    return copyFromUtf8;
                }

                public String getLac() {
                    Object obj = this.aW;
                    if (obj instanceof String) {
                        return (String) obj;
                    }
                    ByteString byteString = (ByteString) obj;
                    String stringUtf8 = byteString.toStringUtf8();
                    if (byteString.isValidUtf8()) {
                        this.aW = stringUtf8;
                    }
                    return stringUtf8;
                }

                public ByteString getLacBytes() {
                    Object obj = this.aW;
                    if (!(obj instanceof String)) {
                        return (ByteString) obj;
                    }
                    ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                    this.aW = copyFromUtf8;
                    return copyFromUtf8;
                }

                public String getLanguage() {
                    Object obj = this.aN;
                    if (obj instanceof String) {
                        return (String) obj;
                    }
                    ByteString byteString = (ByteString) obj;
                    String stringUtf8 = byteString.toStringUtf8();
                    if (byteString.isValidUtf8()) {
                        this.aN = stringUtf8;
                    }
                    return stringUtf8;
                }

                public ByteString getLanguageBytes() {
                    Object obj = this.aN;
                    if (!(obj instanceof String)) {
                        return (ByteString) obj;
                    }
                    ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                    this.aN = copyFromUtf8;
                    return copyFromUtf8;
                }

                public String getLatitude() {
                    Object obj = this.aY;
                    if (obj instanceof String) {
                        return (String) obj;
                    }
                    ByteString byteString = (ByteString) obj;
                    String stringUtf8 = byteString.toStringUtf8();
                    if (byteString.isValidUtf8()) {
                        this.aY = stringUtf8;
                    }
                    return stringUtf8;
                }

                public ByteString getLatitudeBytes() {
                    Object obj = this.aY;
                    if (!(obj instanceof String)) {
                        return (ByteString) obj;
                    }
                    ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                    this.aY = copyFromUtf8;
                    return copyFromUtf8;
                }

                public String getLogVersionApp() {
                    Object obj = this.bj;
                    if (obj instanceof String) {
                        return (String) obj;
                    }
                    ByteString byteString = (ByteString) obj;
                    String stringUtf8 = byteString.toStringUtf8();
                    if (byteString.isValidUtf8()) {
                        this.bj = stringUtf8;
                    }
                    return stringUtf8;
                }

                public ByteString getLogVersionAppBytes() {
                    Object obj = this.bj;
                    if (!(obj instanceof String)) {
                        return (ByteString) obj;
                    }
                    ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                    this.bj = copyFromUtf8;
                    return copyFromUtf8;
                }

                public String getLongitude() {
                    Object obj = this.aZ;
                    if (obj instanceof String) {
                        return (String) obj;
                    }
                    ByteString byteString = (ByteString) obj;
                    String stringUtf8 = byteString.toStringUtf8();
                    if (byteString.isValidUtf8()) {
                        this.aZ = stringUtf8;
                    }
                    return stringUtf8;
                }

                public ByteString getLongitudeBytes() {
                    Object obj = this.aZ;
                    if (!(obj instanceof String)) {
                        return (ByteString) obj;
                    }
                    ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                    this.aZ = copyFromUtf8;
                    return copyFromUtf8;
                }

                public String getMacAddress() {
                    Object obj = this.aQ;
                    if (obj instanceof String) {
                        return (String) obj;
                    }
                    ByteString byteString = (ByteString) obj;
                    String stringUtf8 = byteString.toStringUtf8();
                    if (byteString.isValidUtf8()) {
                        this.aQ = stringUtf8;
                    }
                    return stringUtf8;
                }

                public ByteString getMacAddressBytes() {
                    Object obj = this.aQ;
                    if (!(obj instanceof String)) {
                        return (ByteString) obj;
                    }
                    ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                    this.aQ = copyFromUtf8;
                    return copyFromUtf8;
                }

                public String getMccMnc() {
                    Object obj = this.aU;
                    if (obj instanceof String) {
                        return (String) obj;
                    }
                    ByteString byteString = (ByteString) obj;
                    String stringUtf8 = byteString.toStringUtf8();
                    if (byteString.isValidUtf8()) {
                        this.aU = stringUtf8;
                    }
                    return stringUtf8;
                }

                public ByteString getMccMncBytes() {
                    Object obj = this.aU;
                    if (!(obj instanceof String)) {
                        return (ByteString) obj;
                    }
                    ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                    this.aU = copyFromUtf8;
                    return copyFromUtf8;
                }

                public String getMiuiVersion() {
                    Object obj = this.bf;
                    if (obj instanceof String) {
                        return (String) obj;
                    }
                    ByteString byteString = (ByteString) obj;
                    String stringUtf8 = byteString.toStringUtf8();
                    if (byteString.isValidUtf8()) {
                        this.bf = stringUtf8;
                    }
                    return stringUtf8;
                }

                public ByteString getMiuiVersionBytes() {
                    Object obj = this.bf;
                    if (!(obj instanceof String)) {
                        return (ByteString) obj;
                    }
                    ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                    this.bf = copyFromUtf8;
                    return copyFromUtf8;
                }

                public NetworkType getNetwork() {
                    return this.bd;
                }

                public String getNetworkDetail() {
                    Object obj = this.be;
                    if (obj instanceof String) {
                        return (String) obj;
                    }
                    ByteString byteString = (ByteString) obj;
                    String stringUtf8 = byteString.toStringUtf8();
                    if (byteString.isValidUtf8()) {
                        this.be = stringUtf8;
                    }
                    return stringUtf8;
                }

                public ByteString getNetworkDetailBytes() {
                    Object obj = this.be;
                    if (!(obj instanceof String)) {
                        return (ByteString) obj;
                    }
                    ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                    this.be = copyFromUtf8;
                    return copyFromUtf8;
                }

                public String getOsVersion() {
                    Object obj = this.aL;
                    if (obj instanceof String) {
                        return (String) obj;
                    }
                    ByteString byteString = (ByteString) obj;
                    String stringUtf8 = byteString.toStringUtf8();
                    if (byteString.isValidUtf8()) {
                        this.aL = stringUtf8;
                    }
                    return stringUtf8;
                }

                public ByteString getOsVersionBytes() {
                    Object obj = this.aL;
                    if (!(obj instanceof String)) {
                        return (ByteString) obj;
                    }
                    ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                    this.aL = copyFromUtf8;
                    return copyFromUtf8;
                }

                public String getPackageName() {
                    Object obj = this.bh;
                    if (obj instanceof String) {
                        return (String) obj;
                    }
                    ByteString byteString = (ByteString) obj;
                    String stringUtf8 = byteString.toStringUtf8();
                    if (byteString.isValidUtf8()) {
                        this.bh = stringUtf8;
                    }
                    return stringUtf8;
                }

                public ByteString getPackageNameBytes() {
                    Object obj = this.bh;
                    if (!(obj instanceof String)) {
                        return (ByteString) obj;
                    }
                    ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                    this.bh = copyFromUtf8;
                    return copyFromUtf8;
                }

                public String getPhoneType() {
                    Object obj = this.ba;
                    if (obj instanceof String) {
                        return (String) obj;
                    }
                    ByteString byteString = (ByteString) obj;
                    String stringUtf8 = byteString.toStringUtf8();
                    if (byteString.isValidUtf8()) {
                        this.ba = stringUtf8;
                    }
                    return stringUtf8;
                }

                public ByteString getPhoneTypeBytes() {
                    Object obj = this.ba;
                    if (!(obj instanceof String)) {
                        return (ByteString) obj;
                    }
                    ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                    this.ba = copyFromUtf8;
                    return copyFromUtf8;
                }

                public String getPlatform() {
                    Object obj = this.aM;
                    if (obj instanceof String) {
                        return (String) obj;
                    }
                    ByteString byteString = (ByteString) obj;
                    String stringUtf8 = byteString.toStringUtf8();
                    if (byteString.isValidUtf8()) {
                        this.aM = stringUtf8;
                    }
                    return stringUtf8;
                }

                public ByteString getPlatformBytes() {
                    Object obj = this.aM;
                    if (!(obj instanceof String)) {
                        return (ByteString) obj;
                    }
                    ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                    this.aM = copyFromUtf8;
                    return copyFromUtf8;
                }

                public String getResolution() {
                    Object obj = this.aO;
                    if (obj instanceof String) {
                        return (String) obj;
                    }
                    ByteString byteString = (ByteString) obj;
                    String stringUtf8 = byteString.toStringUtf8();
                    if (byteString.isValidUtf8()) {
                        this.aO = stringUtf8;
                    }
                    return stringUtf8;
                }

                public ByteString getResolutionBytes() {
                    Object obj = this.aO;
                    if (!(obj instanceof String)) {
                        return (ByteString) obj;
                    }
                    ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                    this.aO = copyFromUtf8;
                    return copyFromUtf8;
                }

                public String getSdkVerson() {
                    Object obj = this.aK;
                    if (obj instanceof String) {
                        return (String) obj;
                    }
                    ByteString byteString = (ByteString) obj;
                    String stringUtf8 = byteString.toStringUtf8();
                    if (byteString.isValidUtf8()) {
                        this.aK = stringUtf8;
                    }
                    return stringUtf8;
                }

                public ByteString getSdkVersonBytes() {
                    Object obj = this.aK;
                    if (!(obj instanceof String)) {
                        return (ByteString) obj;
                    }
                    ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                    this.aK = copyFromUtf8;
                    return copyFromUtf8;
                }

                public String getSessionId() {
                    Object obj = this.aF;
                    if (obj instanceof String) {
                        return (String) obj;
                    }
                    ByteString byteString = (ByteString) obj;
                    String stringUtf8 = byteString.toStringUtf8();
                    if (byteString.isValidUtf8()) {
                        this.aF = stringUtf8;
                    }
                    return stringUtf8;
                }

                public ByteString getSessionIdBytes() {
                    Object obj = this.aF;
                    if (!(obj instanceof String)) {
                        return (ByteString) obj;
                    }
                    ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                    this.aF = copyFromUtf8;
                    return copyFromUtf8;
                }

                public String getSimOperator() {
                    Object obj = this.bk;
                    if (obj instanceof String) {
                        return (String) obj;
                    }
                    ByteString byteString = (ByteString) obj;
                    String stringUtf8 = byteString.toStringUtf8();
                    if (byteString.isValidUtf8()) {
                        this.bk = stringUtf8;
                    }
                    return stringUtf8;
                }

                public ByteString getSimOperatorBytes() {
                    Object obj = this.bk;
                    if (!(obj instanceof String)) {
                        return (ByteString) obj;
                    }
                    ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                    this.bk = copyFromUtf8;
                    return copyFromUtf8;
                }

                public long getTime() {
                    return this.bb;
                }

                public String getType() {
                    Object obj = this.aC;
                    if (obj instanceof String) {
                        return (String) obj;
                    }
                    ByteString byteString = (ByteString) obj;
                    String stringUtf8 = byteString.toStringUtf8();
                    if (byteString.isValidUtf8()) {
                        this.aC = stringUtf8;
                    }
                    return stringUtf8;
                }

                public ByteString getTypeBytes() {
                    Object obj = this.aC;
                    if (!(obj instanceof String)) {
                        return (ByteString) obj;
                    }
                    ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                    this.aC = copyFromUtf8;
                    return copyFromUtf8;
                }

                public String getUserId() {
                    Object obj = this.aH;
                    if (obj instanceof String) {
                        return (String) obj;
                    }
                    ByteString byteString = (ByteString) obj;
                    String stringUtf8 = byteString.toStringUtf8();
                    if (byteString.isValidUtf8()) {
                        this.aH = stringUtf8;
                    }
                    return stringUtf8;
                }

                public ByteString getUserIdBytes() {
                    Object obj = this.aH;
                    if (!(obj instanceof String)) {
                        return (ByteString) obj;
                    }
                    ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                    this.aH = copyFromUtf8;
                    return copyFromUtf8;
                }

                public int getVersionCode() {
                    return this.aI;
                }

                public String getVersionName() {
                    Object obj = this.aJ;
                    if (obj instanceof String) {
                        return (String) obj;
                    }
                    ByteString byteString = (ByteString) obj;
                    String stringUtf8 = byteString.toStringUtf8();
                    if (byteString.isValidUtf8()) {
                        this.aJ = stringUtf8;
                    }
                    return stringUtf8;
                }

                public ByteString getVersionNameBytes() {
                    Object obj = this.aJ;
                    if (!(obj instanceof String)) {
                        return (ByteString) obj;
                    }
                    ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                    this.aJ = copyFromUtf8;
                    return copyFromUtf8;
                }

                public boolean hasAppChannel() {
                    return (this.aA & 4) == 4;
                }

                public boolean hasAppKey() {
                    return (this.aA & 2) == 2;
                }

                public boolean hasCellId() {
                    return (this.aA & 524288) == 524288;
                }

                public boolean hasChannelInfo() {
                    return (this.aB & 8) == 8;
                }

                public boolean hasDeviceId() {
                    return (this.aA & 16) == 16;
                }

                public boolean hasDeviceName() {
                    return (this.aA & 8192) == 8192;
                }

                public boolean hasHasBluetooth() {
                    return (this.aA & 32768) == 32768;
                }

                public boolean hasHasGps() {
                    return (this.aA & 2097152) == 2097152;
                }

                public boolean hasHasGravity() {
                    return (this.aA & 131072) == 131072;
                }

                public boolean hasHasWifi() {
                    return (this.aA & 65536) == 65536;
                }

                public boolean hasImei() {
                    return (this.aB & 1) == 1;
                }

                public boolean hasKey() {
                    return (this.aA & 1073741824) == 1073741824;
                }

                public boolean hasLac() {
                    return (this.aA & 1048576) == 1048576;
                }

                public boolean hasLanguage() {
                    return (this.aA & 2048) == 2048;
                }

                public boolean hasLatitude() {
                    return (this.aA & 4194304) == 4194304;
                }

                public boolean hasLogVersionApp() {
                    return (this.aB & 2) == 2;
                }

                public boolean hasLongitude() {
                    return (this.aA & 8388608) == 8388608;
                }

                public boolean hasMacAddress() {
                    return (this.aA & 16384) == 16384;
                }

                public boolean hasMccMnc() {
                    return (this.aA & 262144) == 262144;
                }

                public boolean hasMiuiVersion() {
                    return (this.aA & 536870912) == 536870912;
                }

                public boolean hasNetwork() {
                    return (this.aA & 134217728) == 134217728;
                }

                public boolean hasNetworkDetail() {
                    return (this.aA & C.ENCODING_PCM_MU_LAW) == 268435456;
                }

                public boolean hasOsVersion() {
                    return (this.aA & 512) == 512;
                }

                public boolean hasPackageName() {
                    return (this.aA & Integer.MIN_VALUE) == Integer.MIN_VALUE;
                }

                public boolean hasPhoneType() {
                    return (this.aA & 16777216) == 16777216;
                }

                public boolean hasPlatform() {
                    return (this.aA & 1024) == 1024;
                }

                public boolean hasResolution() {
                    return (this.aA & 4096) == 4096;
                }

                public boolean hasSdkVerson() {
                    return (this.aA & 256) == 256;
                }

                public boolean hasSessionId() {
                    return (this.aA & 8) == 8;
                }

                public boolean hasSimOperator() {
                    return (this.aB & 4) == 4;
                }

                public boolean hasTime() {
                    return (this.aA & 33554432) == 33554432;
                }

                public boolean hasType() {
                    return (this.aA & 1) == 1;
                }

                public boolean hasUserId() {
                    return (this.aA & 32) == 32;
                }

                public boolean hasVersionCode() {
                    return (this.aA & 64) == 64;
                }

                public boolean hasVersionName() {
                    return (this.aA & 128) == 128;
                }

                /* access modifiers changed from: protected */
                public GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
                    return ProtoMsg.ab.ensureFieldAccessorsInitialized(Client.class, Builder.class);
                }

                public final boolean isInitialized() {
                    if (!hasType()) {
                        return false;
                    }
                    for (int i = 0; i < getClientMapCount(); i++) {
                        if (!getClientMap(i).isInitialized()) {
                            return false;
                        }
                    }
                    return true;
                }

                public Builder mergeFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) {
                    Client client;
                    Client client2 = null;
                    try {
                        Client client3 = (Client) Client.PARSER.parsePartialFrom(codedInputStream, extensionRegistryLite);
                        if (client3 != null) {
                            mergeFrom(client3);
                        }
                        return this;
                    } catch (InvalidProtocolBufferException e) {
                        client = (Client) e.getUnfinishedMessage();
                        throw e;
                    } catch (Throwable th) {
                        th = th;
                        client2 = client;
                    }
                    if (client2 != null) {
                        mergeFrom(client2);
                    }
                    throw th;
                }

                public Builder mergeFrom(Message message) {
                    if (message instanceof Client) {
                        return mergeFrom((Client) message);
                    }
                    super.mergeFrom(message);
                    return this;
                }

                public Builder mergeFrom(Client client) {
                    if (client == Client.getDefaultInstance()) {
                        return this;
                    }
                    if (client.hasType()) {
                        this.aA |= 1;
                        this.aC = client.aC;
                        onChanged();
                    }
                    if (client.hasAppKey()) {
                        this.aA |= 2;
                        this.aD = client.aD;
                        onChanged();
                    }
                    if (client.hasAppChannel()) {
                        this.aA |= 4;
                        this.aE = client.aE;
                        onChanged();
                    }
                    if (client.hasSessionId()) {
                        this.aA |= 8;
                        this.aF = client.aF;
                        onChanged();
                    }
                    if (client.hasDeviceId()) {
                        this.aA |= 16;
                        this.aG = client.aG;
                        onChanged();
                    }
                    if (client.hasUserId()) {
                        this.aA |= 32;
                        this.aH = client.aH;
                        onChanged();
                    }
                    if (client.hasVersionCode()) {
                        setVersionCode(client.getVersionCode());
                    }
                    if (client.hasVersionName()) {
                        this.aA |= 128;
                        this.aJ = client.aJ;
                        onChanged();
                    }
                    if (client.hasSdkVerson()) {
                        this.aA |= 256;
                        this.aK = client.aK;
                        onChanged();
                    }
                    if (client.hasOsVersion()) {
                        this.aA |= 512;
                        this.aL = client.aL;
                        onChanged();
                    }
                    if (client.hasPlatform()) {
                        this.aA |= 1024;
                        this.aM = client.aM;
                        onChanged();
                    }
                    if (client.hasLanguage()) {
                        this.aA |= 2048;
                        this.aN = client.aN;
                        onChanged();
                    }
                    if (client.hasResolution()) {
                        this.aA |= 4096;
                        this.aO = client.aO;
                        onChanged();
                    }
                    if (client.hasDeviceName()) {
                        this.aA |= 8192;
                        this.aP = client.aP;
                        onChanged();
                    }
                    if (client.hasMacAddress()) {
                        this.aA |= 16384;
                        this.aQ = client.aQ;
                        onChanged();
                    }
                    if (client.hasHasBluetooth()) {
                        setHasBluetooth(client.getHasBluetooth());
                    }
                    if (client.hasHasWifi()) {
                        setHasWifi(client.getHasWifi());
                    }
                    if (client.hasHasGravity()) {
                        setHasGravity(client.getHasGravity());
                    }
                    if (client.hasMccMnc()) {
                        this.aA |= 262144;
                        this.aU = client.aU;
                        onChanged();
                    }
                    if (client.hasCellId()) {
                        this.aA |= 524288;
                        this.aV = client.aV;
                        onChanged();
                    }
                    if (client.hasLac()) {
                        this.aA |= 1048576;
                        this.aW = client.aW;
                        onChanged();
                    }
                    if (client.hasHasGps()) {
                        setHasGps(client.getHasGps());
                    }
                    if (client.hasLatitude()) {
                        this.aA |= 4194304;
                        this.aY = client.aY;
                        onChanged();
                    }
                    if (client.hasLongitude()) {
                        this.aA |= 8388608;
                        this.aZ = client.aZ;
                        onChanged();
                    }
                    if (client.hasPhoneType()) {
                        this.aA |= 16777216;
                        this.ba = client.ba;
                        onChanged();
                    }
                    if (client.hasTime()) {
                        setTime(client.getTime());
                    }
                    if (this.bm == null) {
                        if (!client.bc.isEmpty()) {
                            if (this.bc.isEmpty()) {
                                this.bc = client.bc;
                                this.aA &= -67108865;
                            } else {
                                X();
                                this.bc.addAll(client.bc);
                            }
                            onChanged();
                        }
                    } else if (!client.bc.isEmpty()) {
                        if (this.bm.isEmpty()) {
                            this.bm.dispose();
                            RepeatedFieldBuilder repeatedFieldBuilder = null;
                            this.bm = null;
                            this.bc = client.bc;
                            this.aA = -67108865 & this.aA;
                            if (Client.alwaysUseFieldBuilders) {
                                repeatedFieldBuilder = Y();
                            }
                            this.bm = repeatedFieldBuilder;
                        } else {
                            this.bm.addAllMessages(client.bc);
                        }
                    }
                    if (client.hasNetwork()) {
                        setNetwork(client.getNetwork());
                    }
                    if (client.hasNetworkDetail()) {
                        this.aA |= C.ENCODING_PCM_MU_LAW;
                        this.be = client.be;
                        onChanged();
                    }
                    if (client.hasMiuiVersion()) {
                        this.aA |= 536870912;
                        this.bf = client.bf;
                        onChanged();
                    }
                    if (client.hasKey()) {
                        this.aA |= 1073741824;
                        this.bg = client.bg;
                        onChanged();
                    }
                    if (client.hasPackageName()) {
                        this.aA |= Integer.MIN_VALUE;
                        this.bh = client.bh;
                        onChanged();
                    }
                    if (client.hasImei()) {
                        this.aB |= 1;
                        this.bi = client.bi;
                        onChanged();
                    }
                    if (client.hasLogVersionApp()) {
                        this.aB |= 2;
                        this.bj = client.bj;
                        onChanged();
                    }
                    if (client.hasSimOperator()) {
                        this.aB |= 4;
                        this.bk = client.bk;
                        onChanged();
                    }
                    if (client.hasChannelInfo()) {
                        this.aB |= 8;
                        this.bl = client.bl;
                        onChanged();
                    }
                    mergeUnknownFields(client.getUnknownFields());
                    return this;
                }

                public Builder removeClientMap(int i) {
                    if (this.bm == null) {
                        X();
                        this.bc.remove(i);
                        onChanged();
                    } else {
                        this.bm.remove(i);
                    }
                    return this;
                }

                public Builder setAppChannel(String str) {
                    if (str != null) {
                        this.aA |= 4;
                        this.aE = str;
                        onChanged();
                        return this;
                    }
                    throw new NullPointerException();
                }

                public Builder setAppChannelBytes(ByteString byteString) {
                    if (byteString != null) {
                        this.aA |= 4;
                        this.aE = byteString;
                        onChanged();
                        return this;
                    }
                    throw new NullPointerException();
                }

                public Builder setAppKey(String str) {
                    if (str != null) {
                        this.aA |= 2;
                        this.aD = str;
                        onChanged();
                        return this;
                    }
                    throw new NullPointerException();
                }

                public Builder setAppKeyBytes(ByteString byteString) {
                    if (byteString != null) {
                        this.aA |= 2;
                        this.aD = byteString;
                        onChanged();
                        return this;
                    }
                    throw new NullPointerException();
                }

                public Builder setCellId(String str) {
                    if (str != null) {
                        this.aA |= 524288;
                        this.aV = str;
                        onChanged();
                        return this;
                    }
                    throw new NullPointerException();
                }

                public Builder setCellIdBytes(ByteString byteString) {
                    if (byteString != null) {
                        this.aA |= 524288;
                        this.aV = byteString;
                        onChanged();
                        return this;
                    }
                    throw new NullPointerException();
                }

                public Builder setChannelInfo(String str) {
                    if (str != null) {
                        this.aB |= 8;
                        this.bl = str;
                        onChanged();
                        return this;
                    }
                    throw new NullPointerException();
                }

                public Builder setChannelInfoBytes(ByteString byteString) {
                    if (byteString != null) {
                        this.aB |= 8;
                        this.bl = byteString;
                        onChanged();
                        return this;
                    }
                    throw new NullPointerException();
                }

                public Builder setClientMap(int i, ProtoMap.Builder builder) {
                    if (this.bm == null) {
                        X();
                        this.bc.set(i, builder.build());
                        onChanged();
                    } else {
                        this.bm.setMessage(i, builder.build());
                    }
                    return this;
                }

                public Builder setClientMap(int i, ProtoMap protoMap) {
                    if (this.bm != null) {
                        this.bm.setMessage(i, protoMap);
                    } else if (protoMap != null) {
                        X();
                        this.bc.set(i, protoMap);
                        onChanged();
                    } else {
                        throw new NullPointerException();
                    }
                    return this;
                }

                public Builder setDeviceId(String str) {
                    if (str != null) {
                        this.aA |= 16;
                        this.aG = str;
                        onChanged();
                        return this;
                    }
                    throw new NullPointerException();
                }

                public Builder setDeviceIdBytes(ByteString byteString) {
                    if (byteString != null) {
                        this.aA |= 16;
                        this.aG = byteString;
                        onChanged();
                        return this;
                    }
                    throw new NullPointerException();
                }

                public Builder setDeviceName(String str) {
                    if (str != null) {
                        this.aA |= 8192;
                        this.aP = str;
                        onChanged();
                        return this;
                    }
                    throw new NullPointerException();
                }

                public Builder setDeviceNameBytes(ByteString byteString) {
                    if (byteString != null) {
                        this.aA |= 8192;
                        this.aP = byteString;
                        onChanged();
                        return this;
                    }
                    throw new NullPointerException();
                }

                public Builder setHasBluetooth(boolean z) {
                    this.aA |= 32768;
                    this.aR = z;
                    onChanged();
                    return this;
                }

                public Builder setHasGps(boolean z) {
                    this.aA |= 2097152;
                    this.aX = z;
                    onChanged();
                    return this;
                }

                public Builder setHasGravity(boolean z) {
                    this.aA |= 131072;
                    this.aT = z;
                    onChanged();
                    return this;
                }

                public Builder setHasWifi(boolean z) {
                    this.aA |= 65536;
                    this.aS = z;
                    onChanged();
                    return this;
                }

                public Builder setImei(String str) {
                    if (str != null) {
                        this.aB |= 1;
                        this.bi = str;
                        onChanged();
                        return this;
                    }
                    throw new NullPointerException();
                }

                public Builder setImeiBytes(ByteString byteString) {
                    if (byteString != null) {
                        this.aB |= 1;
                        this.bi = byteString;
                        onChanged();
                        return this;
                    }
                    throw new NullPointerException();
                }

                public Builder setKey(String str) {
                    if (str != null) {
                        this.aA |= 1073741824;
                        this.bg = str;
                        onChanged();
                        return this;
                    }
                    throw new NullPointerException();
                }

                public Builder setKeyBytes(ByteString byteString) {
                    if (byteString != null) {
                        this.aA |= 1073741824;
                        this.bg = byteString;
                        onChanged();
                        return this;
                    }
                    throw new NullPointerException();
                }

                public Builder setLac(String str) {
                    if (str != null) {
                        this.aA |= 1048576;
                        this.aW = str;
                        onChanged();
                        return this;
                    }
                    throw new NullPointerException();
                }

                public Builder setLacBytes(ByteString byteString) {
                    if (byteString != null) {
                        this.aA |= 1048576;
                        this.aW = byteString;
                        onChanged();
                        return this;
                    }
                    throw new NullPointerException();
                }

                public Builder setLanguage(String str) {
                    if (str != null) {
                        this.aA |= 2048;
                        this.aN = str;
                        onChanged();
                        return this;
                    }
                    throw new NullPointerException();
                }

                public Builder setLanguageBytes(ByteString byteString) {
                    if (byteString != null) {
                        this.aA |= 2048;
                        this.aN = byteString;
                        onChanged();
                        return this;
                    }
                    throw new NullPointerException();
                }

                public Builder setLatitude(String str) {
                    if (str != null) {
                        this.aA |= 4194304;
                        this.aY = str;
                        onChanged();
                        return this;
                    }
                    throw new NullPointerException();
                }

                public Builder setLatitudeBytes(ByteString byteString) {
                    if (byteString != null) {
                        this.aA |= 4194304;
                        this.aY = byteString;
                        onChanged();
                        return this;
                    }
                    throw new NullPointerException();
                }

                public Builder setLogVersionApp(String str) {
                    if (str != null) {
                        this.aB |= 2;
                        this.bj = str;
                        onChanged();
                        return this;
                    }
                    throw new NullPointerException();
                }

                public Builder setLogVersionAppBytes(ByteString byteString) {
                    if (byteString != null) {
                        this.aB |= 2;
                        this.bj = byteString;
                        onChanged();
                        return this;
                    }
                    throw new NullPointerException();
                }

                public Builder setLongitude(String str) {
                    if (str != null) {
                        this.aA |= 8388608;
                        this.aZ = str;
                        onChanged();
                        return this;
                    }
                    throw new NullPointerException();
                }

                public Builder setLongitudeBytes(ByteString byteString) {
                    if (byteString != null) {
                        this.aA |= 8388608;
                        this.aZ = byteString;
                        onChanged();
                        return this;
                    }
                    throw new NullPointerException();
                }

                public Builder setMacAddress(String str) {
                    if (str != null) {
                        this.aA |= 16384;
                        this.aQ = str;
                        onChanged();
                        return this;
                    }
                    throw new NullPointerException();
                }

                public Builder setMacAddressBytes(ByteString byteString) {
                    if (byteString != null) {
                        this.aA |= 16384;
                        this.aQ = byteString;
                        onChanged();
                        return this;
                    }
                    throw new NullPointerException();
                }

                public Builder setMccMnc(String str) {
                    if (str != null) {
                        this.aA |= 262144;
                        this.aU = str;
                        onChanged();
                        return this;
                    }
                    throw new NullPointerException();
                }

                public Builder setMccMncBytes(ByteString byteString) {
                    if (byteString != null) {
                        this.aA |= 262144;
                        this.aU = byteString;
                        onChanged();
                        return this;
                    }
                    throw new NullPointerException();
                }

                public Builder setMiuiVersion(String str) {
                    if (str != null) {
                        this.aA |= 536870912;
                        this.bf = str;
                        onChanged();
                        return this;
                    }
                    throw new NullPointerException();
                }

                public Builder setMiuiVersionBytes(ByteString byteString) {
                    if (byteString != null) {
                        this.aA |= 536870912;
                        this.bf = byteString;
                        onChanged();
                        return this;
                    }
                    throw new NullPointerException();
                }

                public Builder setNetwork(NetworkType networkType) {
                    if (networkType != null) {
                        this.aA |= 134217728;
                        this.bd = networkType;
                        onChanged();
                        return this;
                    }
                    throw new NullPointerException();
                }

                public Builder setNetworkDetail(String str) {
                    if (str != null) {
                        this.aA |= C.ENCODING_PCM_MU_LAW;
                        this.be = str;
                        onChanged();
                        return this;
                    }
                    throw new NullPointerException();
                }

                public Builder setNetworkDetailBytes(ByteString byteString) {
                    if (byteString != null) {
                        this.aA |= C.ENCODING_PCM_MU_LAW;
                        this.be = byteString;
                        onChanged();
                        return this;
                    }
                    throw new NullPointerException();
                }

                public Builder setOsVersion(String str) {
                    if (str != null) {
                        this.aA |= 512;
                        this.aL = str;
                        onChanged();
                        return this;
                    }
                    throw new NullPointerException();
                }

                public Builder setOsVersionBytes(ByteString byteString) {
                    if (byteString != null) {
                        this.aA |= 512;
                        this.aL = byteString;
                        onChanged();
                        return this;
                    }
                    throw new NullPointerException();
                }

                public Builder setPackageName(String str) {
                    if (str != null) {
                        this.aA |= Integer.MIN_VALUE;
                        this.bh = str;
                        onChanged();
                        return this;
                    }
                    throw new NullPointerException();
                }

                public Builder setPackageNameBytes(ByteString byteString) {
                    if (byteString != null) {
                        this.aA |= Integer.MIN_VALUE;
                        this.bh = byteString;
                        onChanged();
                        return this;
                    }
                    throw new NullPointerException();
                }

                public Builder setPhoneType(String str) {
                    if (str != null) {
                        this.aA |= 16777216;
                        this.ba = str;
                        onChanged();
                        return this;
                    }
                    throw new NullPointerException();
                }

                public Builder setPhoneTypeBytes(ByteString byteString) {
                    if (byteString != null) {
                        this.aA |= 16777216;
                        this.ba = byteString;
                        onChanged();
                        return this;
                    }
                    throw new NullPointerException();
                }

                public Builder setPlatform(String str) {
                    if (str != null) {
                        this.aA |= 1024;
                        this.aM = str;
                        onChanged();
                        return this;
                    }
                    throw new NullPointerException();
                }

                public Builder setPlatformBytes(ByteString byteString) {
                    if (byteString != null) {
                        this.aA |= 1024;
                        this.aM = byteString;
                        onChanged();
                        return this;
                    }
                    throw new NullPointerException();
                }

                public Builder setResolution(String str) {
                    if (str != null) {
                        this.aA |= 4096;
                        this.aO = str;
                        onChanged();
                        return this;
                    }
                    throw new NullPointerException();
                }

                public Builder setResolutionBytes(ByteString byteString) {
                    if (byteString != null) {
                        this.aA |= 4096;
                        this.aO = byteString;
                        onChanged();
                        return this;
                    }
                    throw new NullPointerException();
                }

                public Builder setSdkVerson(String str) {
                    if (str != null) {
                        this.aA |= 256;
                        this.aK = str;
                        onChanged();
                        return this;
                    }
                    throw new NullPointerException();
                }

                public Builder setSdkVersonBytes(ByteString byteString) {
                    if (byteString != null) {
                        this.aA |= 256;
                        this.aK = byteString;
                        onChanged();
                        return this;
                    }
                    throw new NullPointerException();
                }

                public Builder setSessionId(String str) {
                    if (str != null) {
                        this.aA |= 8;
                        this.aF = str;
                        onChanged();
                        return this;
                    }
                    throw new NullPointerException();
                }

                public Builder setSessionIdBytes(ByteString byteString) {
                    if (byteString != null) {
                        this.aA |= 8;
                        this.aF = byteString;
                        onChanged();
                        return this;
                    }
                    throw new NullPointerException();
                }

                public Builder setSimOperator(String str) {
                    if (str != null) {
                        this.aB |= 4;
                        this.bk = str;
                        onChanged();
                        return this;
                    }
                    throw new NullPointerException();
                }

                public Builder setSimOperatorBytes(ByteString byteString) {
                    if (byteString != null) {
                        this.aB |= 4;
                        this.bk = byteString;
                        onChanged();
                        return this;
                    }
                    throw new NullPointerException();
                }

                public Builder setTime(long j) {
                    this.aA |= 33554432;
                    this.bb = j;
                    onChanged();
                    return this;
                }

                public Builder setType(String str) {
                    if (str != null) {
                        this.aA |= 1;
                        this.aC = str;
                        onChanged();
                        return this;
                    }
                    throw new NullPointerException();
                }

                public Builder setTypeBytes(ByteString byteString) {
                    if (byteString != null) {
                        this.aA |= 1;
                        this.aC = byteString;
                        onChanged();
                        return this;
                    }
                    throw new NullPointerException();
                }

                public Builder setUserId(String str) {
                    if (str != null) {
                        this.aA |= 32;
                        this.aH = str;
                        onChanged();
                        return this;
                    }
                    throw new NullPointerException();
                }

                public Builder setUserIdBytes(ByteString byteString) {
                    if (byteString != null) {
                        this.aA |= 32;
                        this.aH = byteString;
                        onChanged();
                        return this;
                    }
                    throw new NullPointerException();
                }

                public Builder setVersionCode(int i) {
                    this.aA |= 64;
                    this.aI = i;
                    onChanged();
                    return this;
                }

                public Builder setVersionName(String str) {
                    if (str != null) {
                        this.aA |= 128;
                        this.aJ = str;
                        onChanged();
                        return this;
                    }
                    throw new NullPointerException();
                }

                public Builder setVersionNameBytes(ByteString byteString) {
                    if (byteString != null) {
                        this.aA |= 128;
                        this.aJ = byteString;
                        onChanged();
                        return this;
                    }
                    throw new NullPointerException();
                }
            }

            static {
                az.P();
            }

            private Client(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) {
                this.ax = -1;
                this.ay = -1;
                P();
                UnknownFieldSet.Builder newBuilder = UnknownFieldSet.newBuilder();
                boolean z = false;
                boolean z2 = false;
                while (!z) {
                    try {
                        int readTag = codedInputStream.readTag();
                        switch (readTag) {
                            case 0:
                                z = true;
                                break;
                            case 10:
                                ByteString readBytes = codedInputStream.readBytes();
                                this.aA = 1 | this.aA;
                                this.aC = readBytes;
                                break;
                            case 18:
                                ByteString readBytes2 = codedInputStream.readBytes();
                                this.aA |= 2;
                                this.aD = readBytes2;
                                break;
                            case 26:
                                ByteString readBytes3 = codedInputStream.readBytes();
                                this.aA |= 4;
                                this.aE = readBytes3;
                                break;
                            case 34:
                                ByteString readBytes4 = codedInputStream.readBytes();
                                this.aA |= 8;
                                this.aF = readBytes4;
                                break;
                            case 42:
                                ByteString readBytes5 = codedInputStream.readBytes();
                                this.aA |= 16;
                                this.aG = readBytes5;
                                break;
                            case 50:
                                ByteString readBytes6 = codedInputStream.readBytes();
                                this.aA |= 32;
                                this.aH = readBytes6;
                                break;
                            case 56:
                                this.aA |= 64;
                                this.aI = codedInputStream.readInt32();
                                break;
                            case 66:
                                ByteString readBytes7 = codedInputStream.readBytes();
                                this.aA |= 128;
                                this.aJ = readBytes7;
                                break;
                            case 74:
                                ByteString readBytes8 = codedInputStream.readBytes();
                                this.aA |= 256;
                                this.aK = readBytes8;
                                break;
                            case 82:
                                ByteString readBytes9 = codedInputStream.readBytes();
                                this.aA |= 512;
                                this.aL = readBytes9;
                                break;
                            case 90:
                                ByteString readBytes10 = codedInputStream.readBytes();
                                this.aA |= 1024;
                                this.aM = readBytes10;
                                break;
                            case 98:
                                ByteString readBytes11 = codedInputStream.readBytes();
                                this.aA |= 2048;
                                this.aN = readBytes11;
                                break;
                            case 106:
                                ByteString readBytes12 = codedInputStream.readBytes();
                                this.aA |= 4096;
                                this.aO = readBytes12;
                                break;
                            case 114:
                                ByteString readBytes13 = codedInputStream.readBytes();
                                this.aA |= 8192;
                                this.aP = readBytes13;
                                break;
                            case 122:
                                ByteString readBytes14 = codedInputStream.readBytes();
                                this.aA |= 16384;
                                this.aQ = readBytes14;
                                break;
                            case 128:
                                this.aA |= 32768;
                                this.aR = codedInputStream.readBool();
                                break;
                            case 136:
                                this.aA |= 65536;
                                this.aS = codedInputStream.readBool();
                                break;
                            case 144:
                                this.aA |= 131072;
                                this.aT = codedInputStream.readBool();
                                break;
                            case 154:
                                ByteString readBytes15 = codedInputStream.readBytes();
                                this.aA |= 262144;
                                this.aU = readBytes15;
                                break;
                            case 162:
                                ByteString readBytes16 = codedInputStream.readBytes();
                                this.aA |= 524288;
                                this.aV = readBytes16;
                                break;
                            case 170:
                                ByteString readBytes17 = codedInputStream.readBytes();
                                this.aA |= 1048576;
                                this.aW = readBytes17;
                                break;
                            case 176:
                                this.aA |= 2097152;
                                this.aX = codedInputStream.readBool();
                                break;
                            case Opcodes.cW:
                                ByteString readBytes18 = codedInputStream.readBytes();
                                this.aA |= 4194304;
                                this.aY = readBytes18;
                                break;
                            case 194:
                                ByteString readBytes19 = codedInputStream.readBytes();
                                this.aA |= 8388608;
                                this.aZ = readBytes19;
                                break;
                            case 202:
                                ByteString readBytes20 = codedInputStream.readBytes();
                                this.aA |= 16777216;
                                this.ba = readBytes20;
                                break;
                            case 208:
                                this.aA |= 33554432;
                                this.bb = codedInputStream.readInt64();
                                break;
                            case 218:
                                if (!(z2 & true)) {
                                    this.bc = new ArrayList();
                                    z2 |= true;
                                }
                                this.bc.add(codedInputStream.readMessage(ProtoMap.PARSER, extensionRegistryLite));
                                break;
                            case 224:
                                int readEnum = codedInputStream.readEnum();
                                NetworkType valueOf = NetworkType.valueOf(readEnum);
                                if (valueOf != null) {
                                    this.aA |= com.xiaomi.mishopsdk.util.Constants.CALLIGRAPHY_TAG_PRICE;
                                    this.bd = valueOf;
                                    break;
                                } else {
                                    newBuilder.mergeVarintField(28, readEnum);
                                    break;
                                }
                            case 234:
                                ByteString readBytes21 = codedInputStream.readBytes();
                                this.aA |= 134217728;
                                this.be = readBytes21;
                                break;
                            case 242:
                                ByteString readBytes22 = codedInputStream.readBytes();
                                this.aA |= C.ENCODING_PCM_MU_LAW;
                                this.bf = readBytes22;
                                break;
                            case 250:
                                ByteString readBytes23 = codedInputStream.readBytes();
                                this.aA |= 536870912;
                                this.bg = readBytes23;
                                break;
                            case 258:
                                ByteString readBytes24 = codedInputStream.readBytes();
                                this.aA |= 1073741824;
                                this.bh = readBytes24;
                                break;
                            case 266:
                                ByteString readBytes25 = codedInputStream.readBytes();
                                this.aA |= Integer.MIN_VALUE;
                                this.bi = readBytes25;
                                break;
                            case 274:
                                ByteString readBytes26 = codedInputStream.readBytes();
                                this.aB = 1 | this.aB;
                                this.bj = readBytes26;
                                break;
                            case 282:
                                ByteString readBytes27 = codedInputStream.readBytes();
                                this.aB |= 2;
                                this.bk = readBytes27;
                                break;
                            case OlympusRawInfoMakernoteDirectory.j:
                                ByteString readBytes28 = codedInputStream.readBytes();
                                this.aB |= 4;
                                this.bl = readBytes28;
                                break;
                            default:
                                if (parseUnknownField(codedInputStream, newBuilder, extensionRegistryLite, readTag)) {
                                    break;
                                }
                                z = true;
                                break;
                        }
                    } catch (InvalidProtocolBufferException e) {
                        throw e.setUnfinishedMessage(this);
                    } catch (IOException e2) {
                        throw new InvalidProtocolBufferException(e2.getMessage()).setUnfinishedMessage(this);
                    } catch (Throwable th) {
                        if (z2 & true) {
                            this.bc = Collections.unmodifiableList(this.bc);
                        }
                        this.aw = newBuilder.build();
                        makeExtensionsImmutable();
                        throw th;
                    }
                }
                if (z2 & true) {
                    this.bc = Collections.unmodifiableList(this.bc);
                }
                this.aw = newBuilder.build();
                makeExtensionsImmutable();
            }

            /* synthetic */ Client(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite, a aVar) {
                this(codedInputStream, extensionRegistryLite);
            }

            private Client(GeneratedMessage.Builder builder) {
                super(builder);
                this.ax = -1;
                this.ay = -1;
                this.aw = builder.getUnknownFields();
            }

            /* synthetic */ Client(GeneratedMessage.Builder builder, a aVar) {
                this(builder);
            }

            private Client(boolean z) {
                this.ax = -1;
                this.ay = -1;
                this.aw = UnknownFieldSet.getDefaultInstance();
            }

            private void P() {
                this.aC = "";
                this.aD = "";
                this.aE = "";
                this.aF = "";
                this.aG = "";
                this.aH = "";
                this.aI = 0;
                this.aJ = "";
                this.aK = "";
                this.aL = "";
                this.aM = "";
                this.aN = "";
                this.aO = "";
                this.aP = "";
                this.aQ = "";
                this.aR = false;
                this.aS = false;
                this.aT = false;
                this.aU = "";
                this.aV = "";
                this.aW = "";
                this.aX = false;
                this.aY = "";
                this.aZ = "";
                this.ba = "";
                this.bb = 0;
                this.bc = Collections.emptyList();
                this.bd = NetworkType.NETWORK_UNKNOWN;
                this.be = "";
                this.bf = "";
                this.bg = "";
                this.bh = "";
                this.bi = "";
                this.bj = "";
                this.bk = "";
                this.bl = "";
            }

            public static Client getDefaultInstance() {
                return az;
            }

            public static final Descriptors.Descriptor getDescriptor() {
                return ProtoMsg.aa;
            }

            public static Builder newBuilder() {
                return Builder.W();
            }

            public static Builder newBuilder(Client client) {
                return newBuilder().mergeFrom(client);
            }

            public static Client parseDelimitedFrom(InputStream inputStream) {
                return (Client) PARSER.parseDelimitedFrom(inputStream);
            }

            public static Client parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) {
                return (Client) PARSER.parseDelimitedFrom(inputStream, extensionRegistryLite);
            }

            public static Client parseFrom(ByteString byteString) {
                return (Client) PARSER.parseFrom(byteString);
            }

            public static Client parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) {
                return (Client) PARSER.parseFrom(byteString, extensionRegistryLite);
            }

            public static Client parseFrom(CodedInputStream codedInputStream) {
                return (Client) PARSER.parseFrom(codedInputStream);
            }

            public static Client parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) {
                return (Client) PARSER.parseFrom(codedInputStream, extensionRegistryLite);
            }

            public static Client parseFrom(InputStream inputStream) {
                return (Client) PARSER.parseFrom(inputStream);
            }

            public static Client parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) {
                return (Client) PARSER.parseFrom(inputStream, extensionRegistryLite);
            }

            public static Client parseFrom(byte[] bArr) {
                return (Client) PARSER.parseFrom(bArr);
            }

            public static Client parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) {
                return (Client) PARSER.parseFrom(bArr, extensionRegistryLite);
            }

            public String getAppChannel() {
                Object obj = this.aE;
                if (obj instanceof String) {
                    return (String) obj;
                }
                ByteString byteString = (ByteString) obj;
                String stringUtf8 = byteString.toStringUtf8();
                if (byteString.isValidUtf8()) {
                    this.aE = stringUtf8;
                }
                return stringUtf8;
            }

            public ByteString getAppChannelBytes() {
                Object obj = this.aE;
                if (!(obj instanceof String)) {
                    return (ByteString) obj;
                }
                ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                this.aE = copyFromUtf8;
                return copyFromUtf8;
            }

            public String getAppKey() {
                Object obj = this.aD;
                if (obj instanceof String) {
                    return (String) obj;
                }
                ByteString byteString = (ByteString) obj;
                String stringUtf8 = byteString.toStringUtf8();
                if (byteString.isValidUtf8()) {
                    this.aD = stringUtf8;
                }
                return stringUtf8;
            }

            public ByteString getAppKeyBytes() {
                Object obj = this.aD;
                if (!(obj instanceof String)) {
                    return (ByteString) obj;
                }
                ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                this.aD = copyFromUtf8;
                return copyFromUtf8;
            }

            public String getCellId() {
                Object obj = this.aV;
                if (obj instanceof String) {
                    return (String) obj;
                }
                ByteString byteString = (ByteString) obj;
                String stringUtf8 = byteString.toStringUtf8();
                if (byteString.isValidUtf8()) {
                    this.aV = stringUtf8;
                }
                return stringUtf8;
            }

            public ByteString getCellIdBytes() {
                Object obj = this.aV;
                if (!(obj instanceof String)) {
                    return (ByteString) obj;
                }
                ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                this.aV = copyFromUtf8;
                return copyFromUtf8;
            }

            public String getChannelInfo() {
                Object obj = this.bl;
                if (obj instanceof String) {
                    return (String) obj;
                }
                ByteString byteString = (ByteString) obj;
                String stringUtf8 = byteString.toStringUtf8();
                if (byteString.isValidUtf8()) {
                    this.bl = stringUtf8;
                }
                return stringUtf8;
            }

            public ByteString getChannelInfoBytes() {
                Object obj = this.bl;
                if (!(obj instanceof String)) {
                    return (ByteString) obj;
                }
                ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                this.bl = copyFromUtf8;
                return copyFromUtf8;
            }

            public ProtoMap getClientMap(int i) {
                return (ProtoMap) this.bc.get(i);
            }

            public int getClientMapCount() {
                return this.bc.size();
            }

            public List getClientMapList() {
                return this.bc;
            }

            public ProtoMapOrBuilder getClientMapOrBuilder(int i) {
                return (ProtoMapOrBuilder) this.bc.get(i);
            }

            public List getClientMapOrBuilderList() {
                return this.bc;
            }

            public Client getDefaultInstanceForType() {
                return az;
            }

            public String getDeviceId() {
                Object obj = this.aG;
                if (obj instanceof String) {
                    return (String) obj;
                }
                ByteString byteString = (ByteString) obj;
                String stringUtf8 = byteString.toStringUtf8();
                if (byteString.isValidUtf8()) {
                    this.aG = stringUtf8;
                }
                return stringUtf8;
            }

            public ByteString getDeviceIdBytes() {
                Object obj = this.aG;
                if (!(obj instanceof String)) {
                    return (ByteString) obj;
                }
                ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                this.aG = copyFromUtf8;
                return copyFromUtf8;
            }

            public String getDeviceName() {
                Object obj = this.aP;
                if (obj instanceof String) {
                    return (String) obj;
                }
                ByteString byteString = (ByteString) obj;
                String stringUtf8 = byteString.toStringUtf8();
                if (byteString.isValidUtf8()) {
                    this.aP = stringUtf8;
                }
                return stringUtf8;
            }

            public ByteString getDeviceNameBytes() {
                Object obj = this.aP;
                if (!(obj instanceof String)) {
                    return (ByteString) obj;
                }
                ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                this.aP = copyFromUtf8;
                return copyFromUtf8;
            }

            public boolean getHasBluetooth() {
                return this.aR;
            }

            public boolean getHasGps() {
                return this.aX;
            }

            public boolean getHasGravity() {
                return this.aT;
            }

            public boolean getHasWifi() {
                return this.aS;
            }

            public String getImei() {
                Object obj = this.bi;
                if (obj instanceof String) {
                    return (String) obj;
                }
                ByteString byteString = (ByteString) obj;
                String stringUtf8 = byteString.toStringUtf8();
                if (byteString.isValidUtf8()) {
                    this.bi = stringUtf8;
                }
                return stringUtf8;
            }

            public ByteString getImeiBytes() {
                Object obj = this.bi;
                if (!(obj instanceof String)) {
                    return (ByteString) obj;
                }
                ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                this.bi = copyFromUtf8;
                return copyFromUtf8;
            }

            public String getKey() {
                Object obj = this.bg;
                if (obj instanceof String) {
                    return (String) obj;
                }
                ByteString byteString = (ByteString) obj;
                String stringUtf8 = byteString.toStringUtf8();
                if (byteString.isValidUtf8()) {
                    this.bg = stringUtf8;
                }
                return stringUtf8;
            }

            public ByteString getKeyBytes() {
                Object obj = this.bg;
                if (!(obj instanceof String)) {
                    return (ByteString) obj;
                }
                ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                this.bg = copyFromUtf8;
                return copyFromUtf8;
            }

            public String getLac() {
                Object obj = this.aW;
                if (obj instanceof String) {
                    return (String) obj;
                }
                ByteString byteString = (ByteString) obj;
                String stringUtf8 = byteString.toStringUtf8();
                if (byteString.isValidUtf8()) {
                    this.aW = stringUtf8;
                }
                return stringUtf8;
            }

            public ByteString getLacBytes() {
                Object obj = this.aW;
                if (!(obj instanceof String)) {
                    return (ByteString) obj;
                }
                ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                this.aW = copyFromUtf8;
                return copyFromUtf8;
            }

            public String getLanguage() {
                Object obj = this.aN;
                if (obj instanceof String) {
                    return (String) obj;
                }
                ByteString byteString = (ByteString) obj;
                String stringUtf8 = byteString.toStringUtf8();
                if (byteString.isValidUtf8()) {
                    this.aN = stringUtf8;
                }
                return stringUtf8;
            }

            public ByteString getLanguageBytes() {
                Object obj = this.aN;
                if (!(obj instanceof String)) {
                    return (ByteString) obj;
                }
                ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                this.aN = copyFromUtf8;
                return copyFromUtf8;
            }

            public String getLatitude() {
                Object obj = this.aY;
                if (obj instanceof String) {
                    return (String) obj;
                }
                ByteString byteString = (ByteString) obj;
                String stringUtf8 = byteString.toStringUtf8();
                if (byteString.isValidUtf8()) {
                    this.aY = stringUtf8;
                }
                return stringUtf8;
            }

            public ByteString getLatitudeBytes() {
                Object obj = this.aY;
                if (!(obj instanceof String)) {
                    return (ByteString) obj;
                }
                ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                this.aY = copyFromUtf8;
                return copyFromUtf8;
            }

            public String getLogVersionApp() {
                Object obj = this.bj;
                if (obj instanceof String) {
                    return (String) obj;
                }
                ByteString byteString = (ByteString) obj;
                String stringUtf8 = byteString.toStringUtf8();
                if (byteString.isValidUtf8()) {
                    this.bj = stringUtf8;
                }
                return stringUtf8;
            }

            public ByteString getLogVersionAppBytes() {
                Object obj = this.bj;
                if (!(obj instanceof String)) {
                    return (ByteString) obj;
                }
                ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                this.bj = copyFromUtf8;
                return copyFromUtf8;
            }

            public String getLongitude() {
                Object obj = this.aZ;
                if (obj instanceof String) {
                    return (String) obj;
                }
                ByteString byteString = (ByteString) obj;
                String stringUtf8 = byteString.toStringUtf8();
                if (byteString.isValidUtf8()) {
                    this.aZ = stringUtf8;
                }
                return stringUtf8;
            }

            public ByteString getLongitudeBytes() {
                Object obj = this.aZ;
                if (!(obj instanceof String)) {
                    return (ByteString) obj;
                }
                ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                this.aZ = copyFromUtf8;
                return copyFromUtf8;
            }

            public String getMacAddress() {
                Object obj = this.aQ;
                if (obj instanceof String) {
                    return (String) obj;
                }
                ByteString byteString = (ByteString) obj;
                String stringUtf8 = byteString.toStringUtf8();
                if (byteString.isValidUtf8()) {
                    this.aQ = stringUtf8;
                }
                return stringUtf8;
            }

            public ByteString getMacAddressBytes() {
                Object obj = this.aQ;
                if (!(obj instanceof String)) {
                    return (ByteString) obj;
                }
                ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                this.aQ = copyFromUtf8;
                return copyFromUtf8;
            }

            public String getMccMnc() {
                Object obj = this.aU;
                if (obj instanceof String) {
                    return (String) obj;
                }
                ByteString byteString = (ByteString) obj;
                String stringUtf8 = byteString.toStringUtf8();
                if (byteString.isValidUtf8()) {
                    this.aU = stringUtf8;
                }
                return stringUtf8;
            }

            public ByteString getMccMncBytes() {
                Object obj = this.aU;
                if (!(obj instanceof String)) {
                    return (ByteString) obj;
                }
                ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                this.aU = copyFromUtf8;
                return copyFromUtf8;
            }

            public String getMiuiVersion() {
                Object obj = this.bf;
                if (obj instanceof String) {
                    return (String) obj;
                }
                ByteString byteString = (ByteString) obj;
                String stringUtf8 = byteString.toStringUtf8();
                if (byteString.isValidUtf8()) {
                    this.bf = stringUtf8;
                }
                return stringUtf8;
            }

            public ByteString getMiuiVersionBytes() {
                Object obj = this.bf;
                if (!(obj instanceof String)) {
                    return (ByteString) obj;
                }
                ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                this.bf = copyFromUtf8;
                return copyFromUtf8;
            }

            public NetworkType getNetwork() {
                return this.bd;
            }

            public String getNetworkDetail() {
                Object obj = this.be;
                if (obj instanceof String) {
                    return (String) obj;
                }
                ByteString byteString = (ByteString) obj;
                String stringUtf8 = byteString.toStringUtf8();
                if (byteString.isValidUtf8()) {
                    this.be = stringUtf8;
                }
                return stringUtf8;
            }

            public ByteString getNetworkDetailBytes() {
                Object obj = this.be;
                if (!(obj instanceof String)) {
                    return (ByteString) obj;
                }
                ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                this.be = copyFromUtf8;
                return copyFromUtf8;
            }

            public String getOsVersion() {
                Object obj = this.aL;
                if (obj instanceof String) {
                    return (String) obj;
                }
                ByteString byteString = (ByteString) obj;
                String stringUtf8 = byteString.toStringUtf8();
                if (byteString.isValidUtf8()) {
                    this.aL = stringUtf8;
                }
                return stringUtf8;
            }

            public ByteString getOsVersionBytes() {
                Object obj = this.aL;
                if (!(obj instanceof String)) {
                    return (ByteString) obj;
                }
                ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                this.aL = copyFromUtf8;
                return copyFromUtf8;
            }

            public String getPackageName() {
                Object obj = this.bh;
                if (obj instanceof String) {
                    return (String) obj;
                }
                ByteString byteString = (ByteString) obj;
                String stringUtf8 = byteString.toStringUtf8();
                if (byteString.isValidUtf8()) {
                    this.bh = stringUtf8;
                }
                return stringUtf8;
            }

            public ByteString getPackageNameBytes() {
                Object obj = this.bh;
                if (!(obj instanceof String)) {
                    return (ByteString) obj;
                }
                ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                this.bh = copyFromUtf8;
                return copyFromUtf8;
            }

            public Parser getParserForType() {
                return PARSER;
            }

            public String getPhoneType() {
                Object obj = this.ba;
                if (obj instanceof String) {
                    return (String) obj;
                }
                ByteString byteString = (ByteString) obj;
                String stringUtf8 = byteString.toStringUtf8();
                if (byteString.isValidUtf8()) {
                    this.ba = stringUtf8;
                }
                return stringUtf8;
            }

            public ByteString getPhoneTypeBytes() {
                Object obj = this.ba;
                if (!(obj instanceof String)) {
                    return (ByteString) obj;
                }
                ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                this.ba = copyFromUtf8;
                return copyFromUtf8;
            }

            public String getPlatform() {
                Object obj = this.aM;
                if (obj instanceof String) {
                    return (String) obj;
                }
                ByteString byteString = (ByteString) obj;
                String stringUtf8 = byteString.toStringUtf8();
                if (byteString.isValidUtf8()) {
                    this.aM = stringUtf8;
                }
                return stringUtf8;
            }

            public ByteString getPlatformBytes() {
                Object obj = this.aM;
                if (!(obj instanceof String)) {
                    return (ByteString) obj;
                }
                ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                this.aM = copyFromUtf8;
                return copyFromUtf8;
            }

            public String getResolution() {
                Object obj = this.aO;
                if (obj instanceof String) {
                    return (String) obj;
                }
                ByteString byteString = (ByteString) obj;
                String stringUtf8 = byteString.toStringUtf8();
                if (byteString.isValidUtf8()) {
                    this.aO = stringUtf8;
                }
                return stringUtf8;
            }

            public ByteString getResolutionBytes() {
                Object obj = this.aO;
                if (!(obj instanceof String)) {
                    return (ByteString) obj;
                }
                ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                this.aO = copyFromUtf8;
                return copyFromUtf8;
            }

            public String getSdkVerson() {
                Object obj = this.aK;
                if (obj instanceof String) {
                    return (String) obj;
                }
                ByteString byteString = (ByteString) obj;
                String stringUtf8 = byteString.toStringUtf8();
                if (byteString.isValidUtf8()) {
                    this.aK = stringUtf8;
                }
                return stringUtf8;
            }

            public ByteString getSdkVersonBytes() {
                Object obj = this.aK;
                if (!(obj instanceof String)) {
                    return (ByteString) obj;
                }
                ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                this.aK = copyFromUtf8;
                return copyFromUtf8;
            }

            public int getSerializedSize() {
                int i = this.ay;
                if (i != -1) {
                    return i;
                }
                int computeBytesSize = (this.aA & 1) == 1 ? CodedOutputStream.computeBytesSize(1, getTypeBytes()) + 0 : 0;
                if ((this.aA & 2) == 2) {
                    computeBytesSize += CodedOutputStream.computeBytesSize(2, getAppKeyBytes());
                }
                if ((this.aA & 4) == 4) {
                    computeBytesSize += CodedOutputStream.computeBytesSize(3, getAppChannelBytes());
                }
                if ((this.aA & 8) == 8) {
                    computeBytesSize += CodedOutputStream.computeBytesSize(4, getSessionIdBytes());
                }
                if ((this.aA & 16) == 16) {
                    computeBytesSize += CodedOutputStream.computeBytesSize(5, getDeviceIdBytes());
                }
                if ((this.aA & 32) == 32) {
                    computeBytesSize += CodedOutputStream.computeBytesSize(6, getUserIdBytes());
                }
                if ((this.aA & 64) == 64) {
                    computeBytesSize += CodedOutputStream.computeInt32Size(7, this.aI);
                }
                if ((this.aA & 128) == 128) {
                    computeBytesSize += CodedOutputStream.computeBytesSize(8, getVersionNameBytes());
                }
                if ((this.aA & 256) == 256) {
                    computeBytesSize += CodedOutputStream.computeBytesSize(9, getSdkVersonBytes());
                }
                if ((this.aA & 512) == 512) {
                    computeBytesSize += CodedOutputStream.computeBytesSize(10, getOsVersionBytes());
                }
                if ((this.aA & 1024) == 1024) {
                    computeBytesSize += CodedOutputStream.computeBytesSize(11, getPlatformBytes());
                }
                if ((this.aA & 2048) == 2048) {
                    computeBytesSize += CodedOutputStream.computeBytesSize(12, getLanguageBytes());
                }
                if ((this.aA & 4096) == 4096) {
                    computeBytesSize += CodedOutputStream.computeBytesSize(13, getResolutionBytes());
                }
                if ((this.aA & 8192) == 8192) {
                    computeBytesSize += CodedOutputStream.computeBytesSize(14, getDeviceNameBytes());
                }
                if ((this.aA & 16384) == 16384) {
                    computeBytesSize += CodedOutputStream.computeBytesSize(15, getMacAddressBytes());
                }
                if ((this.aA & 32768) == 32768) {
                    computeBytesSize += CodedOutputStream.computeBoolSize(16, this.aR);
                }
                if ((this.aA & 65536) == 65536) {
                    computeBytesSize += CodedOutputStream.computeBoolSize(17, this.aS);
                }
                if ((this.aA & 131072) == 131072) {
                    computeBytesSize += CodedOutputStream.computeBoolSize(18, this.aT);
                }
                if ((this.aA & 262144) == 262144) {
                    computeBytesSize += CodedOutputStream.computeBytesSize(19, getMccMncBytes());
                }
                if ((this.aA & 524288) == 524288) {
                    computeBytesSize += CodedOutputStream.computeBytesSize(20, getCellIdBytes());
                }
                if ((this.aA & 1048576) == 1048576) {
                    computeBytesSize += CodedOutputStream.computeBytesSize(21, getLacBytes());
                }
                if ((this.aA & 2097152) == 2097152) {
                    computeBytesSize += CodedOutputStream.computeBoolSize(22, this.aX);
                }
                if ((this.aA & 4194304) == 4194304) {
                    computeBytesSize += CodedOutputStream.computeBytesSize(23, getLatitudeBytes());
                }
                if ((this.aA & 8388608) == 8388608) {
                    computeBytesSize += CodedOutputStream.computeBytesSize(24, getLongitudeBytes());
                }
                if ((this.aA & 16777216) == 16777216) {
                    computeBytesSize += CodedOutputStream.computeBytesSize(25, getPhoneTypeBytes());
                }
                if ((this.aA & 33554432) == 33554432) {
                    computeBytesSize += CodedOutputStream.computeInt64Size(26, this.bb);
                }
                for (int i2 = 0; i2 < this.bc.size(); i2++) {
                    computeBytesSize += CodedOutputStream.computeMessageSize(27, (MessageLite) this.bc.get(i2));
                }
                if ((this.aA & com.xiaomi.mishopsdk.util.Constants.CALLIGRAPHY_TAG_PRICE) == 67108864) {
                    computeBytesSize += CodedOutputStream.computeEnumSize(28, this.bd.getNumber());
                }
                if ((this.aA & 134217728) == 134217728) {
                    computeBytesSize += CodedOutputStream.computeBytesSize(29, getNetworkDetailBytes());
                }
                if ((this.aA & C.ENCODING_PCM_MU_LAW) == 268435456) {
                    computeBytesSize += CodedOutputStream.computeBytesSize(30, getMiuiVersionBytes());
                }
                if ((this.aA & 536870912) == 536870912) {
                    computeBytesSize += CodedOutputStream.computeBytesSize(31, getKeyBytes());
                }
                if ((this.aA & 1073741824) == 1073741824) {
                    computeBytesSize += CodedOutputStream.computeBytesSize(32, getPackageNameBytes());
                }
                if ((this.aA & Integer.MIN_VALUE) == Integer.MIN_VALUE) {
                    computeBytesSize += CodedOutputStream.computeBytesSize(33, getImeiBytes());
                }
                if ((this.aB & 1) == 1) {
                    computeBytesSize += CodedOutputStream.computeBytesSize(34, getLogVersionAppBytes());
                }
                if ((this.aB & 2) == 2) {
                    computeBytesSize += CodedOutputStream.computeBytesSize(35, getSimOperatorBytes());
                }
                if ((this.aB & 4) == 4) {
                    computeBytesSize += CodedOutputStream.computeBytesSize(36, getChannelInfoBytes());
                }
                int serializedSize = computeBytesSize + getUnknownFields().getSerializedSize();
                this.ay = serializedSize;
                return serializedSize;
            }

            public String getSessionId() {
                Object obj = this.aF;
                if (obj instanceof String) {
                    return (String) obj;
                }
                ByteString byteString = (ByteString) obj;
                String stringUtf8 = byteString.toStringUtf8();
                if (byteString.isValidUtf8()) {
                    this.aF = stringUtf8;
                }
                return stringUtf8;
            }

            public ByteString getSessionIdBytes() {
                Object obj = this.aF;
                if (!(obj instanceof String)) {
                    return (ByteString) obj;
                }
                ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                this.aF = copyFromUtf8;
                return copyFromUtf8;
            }

            public String getSimOperator() {
                Object obj = this.bk;
                if (obj instanceof String) {
                    return (String) obj;
                }
                ByteString byteString = (ByteString) obj;
                String stringUtf8 = byteString.toStringUtf8();
                if (byteString.isValidUtf8()) {
                    this.bk = stringUtf8;
                }
                return stringUtf8;
            }

            public ByteString getSimOperatorBytes() {
                Object obj = this.bk;
                if (!(obj instanceof String)) {
                    return (ByteString) obj;
                }
                ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                this.bk = copyFromUtf8;
                return copyFromUtf8;
            }

            public long getTime() {
                return this.bb;
            }

            public String getType() {
                Object obj = this.aC;
                if (obj instanceof String) {
                    return (String) obj;
                }
                ByteString byteString = (ByteString) obj;
                String stringUtf8 = byteString.toStringUtf8();
                if (byteString.isValidUtf8()) {
                    this.aC = stringUtf8;
                }
                return stringUtf8;
            }

            public ByteString getTypeBytes() {
                Object obj = this.aC;
                if (!(obj instanceof String)) {
                    return (ByteString) obj;
                }
                ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                this.aC = copyFromUtf8;
                return copyFromUtf8;
            }

            public final UnknownFieldSet getUnknownFields() {
                return this.aw;
            }

            public String getUserId() {
                Object obj = this.aH;
                if (obj instanceof String) {
                    return (String) obj;
                }
                ByteString byteString = (ByteString) obj;
                String stringUtf8 = byteString.toStringUtf8();
                if (byteString.isValidUtf8()) {
                    this.aH = stringUtf8;
                }
                return stringUtf8;
            }

            public ByteString getUserIdBytes() {
                Object obj = this.aH;
                if (!(obj instanceof String)) {
                    return (ByteString) obj;
                }
                ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                this.aH = copyFromUtf8;
                return copyFromUtf8;
            }

            public int getVersionCode() {
                return this.aI;
            }

            public String getVersionName() {
                Object obj = this.aJ;
                if (obj instanceof String) {
                    return (String) obj;
                }
                ByteString byteString = (ByteString) obj;
                String stringUtf8 = byteString.toStringUtf8();
                if (byteString.isValidUtf8()) {
                    this.aJ = stringUtf8;
                }
                return stringUtf8;
            }

            public ByteString getVersionNameBytes() {
                Object obj = this.aJ;
                if (!(obj instanceof String)) {
                    return (ByteString) obj;
                }
                ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                this.aJ = copyFromUtf8;
                return copyFromUtf8;
            }

            public boolean hasAppChannel() {
                return (this.aA & 4) == 4;
            }

            public boolean hasAppKey() {
                return (this.aA & 2) == 2;
            }

            public boolean hasCellId() {
                return (this.aA & 524288) == 524288;
            }

            public boolean hasChannelInfo() {
                return (this.aB & 4) == 4;
            }

            public boolean hasDeviceId() {
                return (this.aA & 16) == 16;
            }

            public boolean hasDeviceName() {
                return (this.aA & 8192) == 8192;
            }

            public boolean hasHasBluetooth() {
                return (this.aA & 32768) == 32768;
            }

            public boolean hasHasGps() {
                return (this.aA & 2097152) == 2097152;
            }

            public boolean hasHasGravity() {
                return (this.aA & 131072) == 131072;
            }

            public boolean hasHasWifi() {
                return (this.aA & 65536) == 65536;
            }

            public boolean hasImei() {
                return (this.aA & Integer.MIN_VALUE) == Integer.MIN_VALUE;
            }

            public boolean hasKey() {
                return (this.aA & 536870912) == 536870912;
            }

            public boolean hasLac() {
                return (this.aA & 1048576) == 1048576;
            }

            public boolean hasLanguage() {
                return (this.aA & 2048) == 2048;
            }

            public boolean hasLatitude() {
                return (this.aA & 4194304) == 4194304;
            }

            public boolean hasLogVersionApp() {
                return (this.aB & 1) == 1;
            }

            public boolean hasLongitude() {
                return (this.aA & 8388608) == 8388608;
            }

            public boolean hasMacAddress() {
                return (this.aA & 16384) == 16384;
            }

            public boolean hasMccMnc() {
                return (this.aA & 262144) == 262144;
            }

            public boolean hasMiuiVersion() {
                return (this.aA & C.ENCODING_PCM_MU_LAW) == 268435456;
            }

            public boolean hasNetwork() {
                return (this.aA & com.xiaomi.mishopsdk.util.Constants.CALLIGRAPHY_TAG_PRICE) == 67108864;
            }

            public boolean hasNetworkDetail() {
                return (this.aA & 134217728) == 134217728;
            }

            public boolean hasOsVersion() {
                return (this.aA & 512) == 512;
            }

            public boolean hasPackageName() {
                return (this.aA & 1073741824) == 1073741824;
            }

            public boolean hasPhoneType() {
                return (this.aA & 16777216) == 16777216;
            }

            public boolean hasPlatform() {
                return (this.aA & 1024) == 1024;
            }

            public boolean hasResolution() {
                return (this.aA & 4096) == 4096;
            }

            public boolean hasSdkVerson() {
                return (this.aA & 256) == 256;
            }

            public boolean hasSessionId() {
                return (this.aA & 8) == 8;
            }

            public boolean hasSimOperator() {
                return (this.aB & 2) == 2;
            }

            public boolean hasTime() {
                return (this.aA & 33554432) == 33554432;
            }

            public boolean hasType() {
                return (this.aA & 1) == 1;
            }

            public boolean hasUserId() {
                return (this.aA & 32) == 32;
            }

            public boolean hasVersionCode() {
                return (this.aA & 64) == 64;
            }

            public boolean hasVersionName() {
                return (this.aA & 128) == 128;
            }

            /* access modifiers changed from: protected */
            public GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
                return ProtoMsg.ab.ensureFieldAccessorsInitialized(Client.class, Builder.class);
            }

            public final boolean isInitialized() {
                byte b = this.ax;
                if (b == 1) {
                    return true;
                }
                if (b == 0) {
                    return false;
                }
                if (!hasType()) {
                    this.ax = 0;
                    return false;
                }
                for (int i = 0; i < getClientMapCount(); i++) {
                    if (!getClientMap(i).isInitialized()) {
                        this.ax = 0;
                        return false;
                    }
                }
                this.ax = 1;
                return true;
            }

            public Builder newBuilderForType() {
                return newBuilder();
            }

            /* access modifiers changed from: protected */
            public Builder newBuilderForType(GeneratedMessage.BuilderParent builderParent) {
                return new Builder(builderParent, (a) null);
            }

            public Builder toBuilder() {
                return newBuilder(this);
            }

            /* access modifiers changed from: protected */
            public Object writeReplace() {
                return super.writeReplace();
            }

            public void writeTo(CodedOutputStream codedOutputStream) {
                getSerializedSize();
                if ((this.aA & 1) == 1) {
                    codedOutputStream.writeBytes(1, getTypeBytes());
                }
                if ((this.aA & 2) == 2) {
                    codedOutputStream.writeBytes(2, getAppKeyBytes());
                }
                if ((this.aA & 4) == 4) {
                    codedOutputStream.writeBytes(3, getAppChannelBytes());
                }
                if ((this.aA & 8) == 8) {
                    codedOutputStream.writeBytes(4, getSessionIdBytes());
                }
                if ((this.aA & 16) == 16) {
                    codedOutputStream.writeBytes(5, getDeviceIdBytes());
                }
                if ((this.aA & 32) == 32) {
                    codedOutputStream.writeBytes(6, getUserIdBytes());
                }
                if ((this.aA & 64) == 64) {
                    codedOutputStream.writeInt32(7, this.aI);
                }
                if ((this.aA & 128) == 128) {
                    codedOutputStream.writeBytes(8, getVersionNameBytes());
                }
                if ((this.aA & 256) == 256) {
                    codedOutputStream.writeBytes(9, getSdkVersonBytes());
                }
                if ((this.aA & 512) == 512) {
                    codedOutputStream.writeBytes(10, getOsVersionBytes());
                }
                if ((this.aA & 1024) == 1024) {
                    codedOutputStream.writeBytes(11, getPlatformBytes());
                }
                if ((this.aA & 2048) == 2048) {
                    codedOutputStream.writeBytes(12, getLanguageBytes());
                }
                if ((this.aA & 4096) == 4096) {
                    codedOutputStream.writeBytes(13, getResolutionBytes());
                }
                if ((this.aA & 8192) == 8192) {
                    codedOutputStream.writeBytes(14, getDeviceNameBytes());
                }
                if ((this.aA & 16384) == 16384) {
                    codedOutputStream.writeBytes(15, getMacAddressBytes());
                }
                if ((this.aA & 32768) == 32768) {
                    codedOutputStream.writeBool(16, this.aR);
                }
                if ((this.aA & 65536) == 65536) {
                    codedOutputStream.writeBool(17, this.aS);
                }
                if ((this.aA & 131072) == 131072) {
                    codedOutputStream.writeBool(18, this.aT);
                }
                if ((this.aA & 262144) == 262144) {
                    codedOutputStream.writeBytes(19, getMccMncBytes());
                }
                if ((this.aA & 524288) == 524288) {
                    codedOutputStream.writeBytes(20, getCellIdBytes());
                }
                if ((this.aA & 1048576) == 1048576) {
                    codedOutputStream.writeBytes(21, getLacBytes());
                }
                if ((this.aA & 2097152) == 2097152) {
                    codedOutputStream.writeBool(22, this.aX);
                }
                if ((this.aA & 4194304) == 4194304) {
                    codedOutputStream.writeBytes(23, getLatitudeBytes());
                }
                if ((this.aA & 8388608) == 8388608) {
                    codedOutputStream.writeBytes(24, getLongitudeBytes());
                }
                if ((this.aA & 16777216) == 16777216) {
                    codedOutputStream.writeBytes(25, getPhoneTypeBytes());
                }
                if ((this.aA & 33554432) == 33554432) {
                    codedOutputStream.writeInt64(26, this.bb);
                }
                for (int i = 0; i < this.bc.size(); i++) {
                    codedOutputStream.writeMessage(27, (MessageLite) this.bc.get(i));
                }
                if ((this.aA & com.xiaomi.mishopsdk.util.Constants.CALLIGRAPHY_TAG_PRICE) == 67108864) {
                    codedOutputStream.writeEnum(28, this.bd.getNumber());
                }
                if ((this.aA & 134217728) == 134217728) {
                    codedOutputStream.writeBytes(29, getNetworkDetailBytes());
                }
                if ((this.aA & C.ENCODING_PCM_MU_LAW) == 268435456) {
                    codedOutputStream.writeBytes(30, getMiuiVersionBytes());
                }
                if ((this.aA & 536870912) == 536870912) {
                    codedOutputStream.writeBytes(31, getKeyBytes());
                }
                if ((this.aA & 1073741824) == 1073741824) {
                    codedOutputStream.writeBytes(32, getPackageNameBytes());
                }
                if ((this.aA & Integer.MIN_VALUE) == Integer.MIN_VALUE) {
                    codedOutputStream.writeBytes(33, getImeiBytes());
                }
                if ((this.aB & 1) == 1) {
                    codedOutputStream.writeBytes(34, getLogVersionAppBytes());
                }
                if ((this.aB & 2) == 2) {
                    codedOutputStream.writeBytes(35, getSimOperatorBytes());
                }
                if ((this.aB & 4) == 4) {
                    codedOutputStream.writeBytes(36, getChannelInfoBytes());
                }
                getUnknownFields().writeTo(codedOutputStream);
            }
        }

        public final class ClientMsg extends GeneratedMessage implements ClientMsgOrBuilder {
            public static final int CLIENT_FIELD_NUMBER = 1;
            public static Parser PARSER = new d();
            private static final ClientMsg bn = new ClientMsg(true);
            private final UnknownFieldSet aw;
            private byte ax;
            private int ay;
            /* access modifiers changed from: private */
            public List bo;

            public final class Builder extends GeneratedMessage.Builder implements ClientMsgOrBuilder {
                private int aA;
                private List bo;
                private RepeatedFieldBuilder bp;

                private Builder() {
                    this.bo = Collections.emptyList();
                    R();
                }

                private Builder(GeneratedMessage.BuilderParent builderParent) {
                    super(builderParent);
                    this.bo = Collections.emptyList();
                    R();
                }

                /* synthetic */ Builder(GeneratedMessage.BuilderParent builderParent, a aVar) {
                    this(builderParent);
                }

                private void R() {
                    if (ClientMsg.alwaysUseFieldBuilders) {
                        ae();
                    }
                }

                /* access modifiers changed from: private */
                public static Builder ac() {
                    return new Builder();
                }

                private void ad() {
                    if ((this.aA & 1) != 1) {
                        this.bo = new ArrayList(this.bo);
                        this.aA |= 1;
                    }
                }

                private RepeatedFieldBuilder ae() {
                    if (this.bp == null) {
                        List list = this.bo;
                        boolean z = true;
                        if ((this.aA & 1) != 1) {
                            z = false;
                        }
                        this.bp = new RepeatedFieldBuilder(list, z, getParentForChildren(), isClean());
                        this.bo = null;
                    }
                    return this.bp;
                }

                public static final Descriptors.Descriptor getDescriptor() {
                    return ProtoMsg.ac;
                }

                public Builder addAllClient(Iterable iterable) {
                    if (this.bp == null) {
                        ad();
                        AbstractMessageLite.Builder.addAll(iterable, this.bo);
                        onChanged();
                    } else {
                        this.bp.addAllMessages(iterable);
                    }
                    return this;
                }

                public Builder addClient(int i, Client.Builder builder) {
                    if (this.bp == null) {
                        ad();
                        this.bo.add(i, builder.build());
                        onChanged();
                    } else {
                        this.bp.addMessage(i, builder.build());
                    }
                    return this;
                }

                public Builder addClient(int i, Client client) {
                    if (this.bp != null) {
                        this.bp.addMessage(i, client);
                    } else if (client != null) {
                        ad();
                        this.bo.add(i, client);
                        onChanged();
                    } else {
                        throw new NullPointerException();
                    }
                    return this;
                }

                public Builder addClient(Client.Builder builder) {
                    if (this.bp == null) {
                        ad();
                        this.bo.add(builder.build());
                        onChanged();
                    } else {
                        this.bp.addMessage(builder.build());
                    }
                    return this;
                }

                public Builder addClient(Client client) {
                    if (this.bp != null) {
                        this.bp.addMessage(client);
                    } else if (client != null) {
                        ad();
                        this.bo.add(client);
                        onChanged();
                    } else {
                        throw new NullPointerException();
                    }
                    return this;
                }

                public Client.Builder addClientBuilder() {
                    return (Client.Builder) ae().addBuilder(Client.getDefaultInstance());
                }

                public Client.Builder addClientBuilder(int i) {
                    return (Client.Builder) ae().addBuilder(i, Client.getDefaultInstance());
                }

                public ClientMsg build() {
                    ClientMsg buildPartial = buildPartial();
                    if (buildPartial.isInitialized()) {
                        return buildPartial;
                    }
                    throw newUninitializedMessageException(buildPartial);
                }

                public ClientMsg buildPartial() {
                    List list;
                    ClientMsg clientMsg = new ClientMsg((GeneratedMessage.Builder) this, (a) null);
                    int i = this.aA;
                    if (this.bp == null) {
                        if ((this.aA & 1) == 1) {
                            this.bo = Collections.unmodifiableList(this.bo);
                            this.aA &= -2;
                        }
                        list = this.bo;
                    } else {
                        list = this.bp.build();
                    }
                    List unused = clientMsg.bo = list;
                    onBuilt();
                    return clientMsg;
                }

                public Builder clear() {
                    super.clear();
                    if (this.bp == null) {
                        this.bo = Collections.emptyList();
                        this.aA &= -2;
                    } else {
                        this.bp.clear();
                    }
                    return this;
                }

                public Builder clearClient() {
                    if (this.bp == null) {
                        this.bo = Collections.emptyList();
                        this.aA &= -2;
                        onChanged();
                    } else {
                        this.bp.clear();
                    }
                    return this;
                }

                public Builder clone() {
                    return ac().mergeFrom(buildPartial());
                }

                public Client getClient(int i) {
                    return (Client) (this.bp == null ? this.bo.get(i) : this.bp.getMessage(i));
                }

                public Client.Builder getClientBuilder(int i) {
                    return (Client.Builder) ae().getBuilder(i);
                }

                public List getClientBuilderList() {
                    return ae().getBuilderList();
                }

                public int getClientCount() {
                    return this.bp == null ? this.bo.size() : this.bp.getCount();
                }

                public List getClientList() {
                    return this.bp == null ? Collections.unmodifiableList(this.bo) : this.bp.getMessageList();
                }

                public ClientOrBuilder getClientOrBuilder(int i) {
                    return (ClientOrBuilder) (this.bp == null ? this.bo.get(i) : this.bp.getMessageOrBuilder(i));
                }

                public List getClientOrBuilderList() {
                    return this.bp != null ? this.bp.getMessageOrBuilderList() : Collections.unmodifiableList(this.bo);
                }

                public ClientMsg getDefaultInstanceForType() {
                    return ClientMsg.getDefaultInstance();
                }

                public Descriptors.Descriptor getDescriptorForType() {
                    return ProtoMsg.ac;
                }

                /* access modifiers changed from: protected */
                public GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
                    return ProtoMsg.ad.ensureFieldAccessorsInitialized(ClientMsg.class, Builder.class);
                }

                public final boolean isInitialized() {
                    for (int i = 0; i < getClientCount(); i++) {
                        if (!getClient(i).isInitialized()) {
                            return false;
                        }
                    }
                    return true;
                }

                public Builder mergeFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) {
                    ClientMsg clientMsg;
                    ClientMsg clientMsg2 = null;
                    try {
                        ClientMsg clientMsg3 = (ClientMsg) ClientMsg.PARSER.parsePartialFrom(codedInputStream, extensionRegistryLite);
                        if (clientMsg3 != null) {
                            mergeFrom(clientMsg3);
                        }
                        return this;
                    } catch (InvalidProtocolBufferException e) {
                        clientMsg = (ClientMsg) e.getUnfinishedMessage();
                        throw e;
                    } catch (Throwable th) {
                        th = th;
                        clientMsg2 = clientMsg;
                    }
                    if (clientMsg2 != null) {
                        mergeFrom(clientMsg2);
                    }
                    throw th;
                }

                public Builder mergeFrom(Message message) {
                    if (message instanceof ClientMsg) {
                        return mergeFrom((ClientMsg) message);
                    }
                    super.mergeFrom(message);
                    return this;
                }

                public Builder mergeFrom(ClientMsg clientMsg) {
                    if (clientMsg == ClientMsg.getDefaultInstance()) {
                        return this;
                    }
                    if (this.bp == null) {
                        if (!clientMsg.bo.isEmpty()) {
                            if (this.bo.isEmpty()) {
                                this.bo = clientMsg.bo;
                                this.aA &= -2;
                            } else {
                                ad();
                                this.bo.addAll(clientMsg.bo);
                            }
                            onChanged();
                        }
                    } else if (!clientMsg.bo.isEmpty()) {
                        if (this.bp.isEmpty()) {
                            this.bp.dispose();
                            RepeatedFieldBuilder repeatedFieldBuilder = null;
                            this.bp = null;
                            this.bo = clientMsg.bo;
                            this.aA &= -2;
                            if (ClientMsg.alwaysUseFieldBuilders) {
                                repeatedFieldBuilder = ae();
                            }
                            this.bp = repeatedFieldBuilder;
                        } else {
                            this.bp.addAllMessages(clientMsg.bo);
                        }
                    }
                    mergeUnknownFields(clientMsg.getUnknownFields());
                    return this;
                }

                public Builder removeClient(int i) {
                    if (this.bp == null) {
                        ad();
                        this.bo.remove(i);
                        onChanged();
                    } else {
                        this.bp.remove(i);
                    }
                    return this;
                }

                public Builder setClient(int i, Client.Builder builder) {
                    if (this.bp == null) {
                        ad();
                        this.bo.set(i, builder.build());
                        onChanged();
                    } else {
                        this.bp.setMessage(i, builder.build());
                    }
                    return this;
                }

                public Builder setClient(int i, Client client) {
                    if (this.bp != null) {
                        this.bp.setMessage(i, client);
                    } else if (client != null) {
                        ad();
                        this.bo.set(i, client);
                        onChanged();
                    } else {
                        throw new NullPointerException();
                    }
                    return this;
                }
            }

            static {
                bn.P();
            }

            private ClientMsg(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) {
                this.ax = -1;
                this.ay = -1;
                P();
                UnknownFieldSet.Builder newBuilder = UnknownFieldSet.newBuilder();
                boolean z = false;
                boolean z2 = false;
                while (!z) {
                    try {
                        int readTag = codedInputStream.readTag();
                        if (readTag != 0) {
                            if (readTag == 10) {
                                if (!z2 || !true) {
                                    this.bo = new ArrayList();
                                    z2 |= true;
                                }
                                this.bo.add(codedInputStream.readMessage(Client.PARSER, extensionRegistryLite));
                            } else if (!parseUnknownField(codedInputStream, newBuilder, extensionRegistryLite, readTag)) {
                            }
                        }
                        z = true;
                    } catch (InvalidProtocolBufferException e) {
                        throw e.setUnfinishedMessage(this);
                    } catch (IOException e2) {
                        throw new InvalidProtocolBufferException(e2.getMessage()).setUnfinishedMessage(this);
                    } catch (Throwable th) {
                        if (z2 && true) {
                            this.bo = Collections.unmodifiableList(this.bo);
                        }
                        this.aw = newBuilder.build();
                        makeExtensionsImmutable();
                        throw th;
                    }
                }
                if (z2 && true) {
                    this.bo = Collections.unmodifiableList(this.bo);
                }
                this.aw = newBuilder.build();
                makeExtensionsImmutable();
            }

            /* synthetic */ ClientMsg(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite, a aVar) {
                this(codedInputStream, extensionRegistryLite);
            }

            private ClientMsg(GeneratedMessage.Builder builder) {
                super(builder);
                this.ax = -1;
                this.ay = -1;
                this.aw = builder.getUnknownFields();
            }

            /* synthetic */ ClientMsg(GeneratedMessage.Builder builder, a aVar) {
                this(builder);
            }

            private ClientMsg(boolean z) {
                this.ax = -1;
                this.ay = -1;
                this.aw = UnknownFieldSet.getDefaultInstance();
            }

            private void P() {
                this.bo = Collections.emptyList();
            }

            public static ClientMsg getDefaultInstance() {
                return bn;
            }

            public static final Descriptors.Descriptor getDescriptor() {
                return ProtoMsg.ac;
            }

            public static Builder newBuilder() {
                return Builder.ac();
            }

            public static Builder newBuilder(ClientMsg clientMsg) {
                return newBuilder().mergeFrom(clientMsg);
            }

            public static ClientMsg parseDelimitedFrom(InputStream inputStream) {
                return (ClientMsg) PARSER.parseDelimitedFrom(inputStream);
            }

            public static ClientMsg parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) {
                return (ClientMsg) PARSER.parseDelimitedFrom(inputStream, extensionRegistryLite);
            }

            public static ClientMsg parseFrom(ByteString byteString) {
                return (ClientMsg) PARSER.parseFrom(byteString);
            }

            public static ClientMsg parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) {
                return (ClientMsg) PARSER.parseFrom(byteString, extensionRegistryLite);
            }

            public static ClientMsg parseFrom(CodedInputStream codedInputStream) {
                return (ClientMsg) PARSER.parseFrom(codedInputStream);
            }

            public static ClientMsg parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) {
                return (ClientMsg) PARSER.parseFrom(codedInputStream, extensionRegistryLite);
            }

            public static ClientMsg parseFrom(InputStream inputStream) {
                return (ClientMsg) PARSER.parseFrom(inputStream);
            }

            public static ClientMsg parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) {
                return (ClientMsg) PARSER.parseFrom(inputStream, extensionRegistryLite);
            }

            public static ClientMsg parseFrom(byte[] bArr) {
                return (ClientMsg) PARSER.parseFrom(bArr);
            }

            public static ClientMsg parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) {
                return (ClientMsg) PARSER.parseFrom(bArr, extensionRegistryLite);
            }

            public Client getClient(int i) {
                return (Client) this.bo.get(i);
            }

            public int getClientCount() {
                return this.bo.size();
            }

            public List getClientList() {
                return this.bo;
            }

            public ClientOrBuilder getClientOrBuilder(int i) {
                return (ClientOrBuilder) this.bo.get(i);
            }

            public List getClientOrBuilderList() {
                return this.bo;
            }

            public ClientMsg getDefaultInstanceForType() {
                return bn;
            }

            public Parser getParserForType() {
                return PARSER;
            }

            public int getSerializedSize() {
                int i = this.ay;
                if (i != -1) {
                    return i;
                }
                int i2 = 0;
                for (int i3 = 0; i3 < this.bo.size(); i3++) {
                    i2 += CodedOutputStream.computeMessageSize(1, (MessageLite) this.bo.get(i3));
                }
                int serializedSize = i2 + getUnknownFields().getSerializedSize();
                this.ay = serializedSize;
                return serializedSize;
            }

            public final UnknownFieldSet getUnknownFields() {
                return this.aw;
            }

            /* access modifiers changed from: protected */
            public GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
                return ProtoMsg.ad.ensureFieldAccessorsInitialized(ClientMsg.class, Builder.class);
            }

            public final boolean isInitialized() {
                byte b = this.ax;
                if (b == 1) {
                    return true;
                }
                if (b == 0) {
                    return false;
                }
                for (int i = 0; i < getClientCount(); i++) {
                    if (!getClient(i).isInitialized()) {
                        this.ax = 0;
                        return false;
                    }
                }
                this.ax = 1;
                return true;
            }

            public Builder newBuilderForType() {
                return newBuilder();
            }

            /* access modifiers changed from: protected */
            public Builder newBuilderForType(GeneratedMessage.BuilderParent builderParent) {
                return new Builder(builderParent, (a) null);
            }

            public Builder toBuilder() {
                return newBuilder(this);
            }

            /* access modifiers changed from: protected */
            public Object writeReplace() {
                return super.writeReplace();
            }

            public void writeTo(CodedOutputStream codedOutputStream) {
                getSerializedSize();
                for (int i = 0; i < this.bo.size(); i++) {
                    codedOutputStream.writeMessage(1, (MessageLite) this.bo.get(i));
                }
                getUnknownFields().writeTo(codedOutputStream);
            }
        }

        public interface ClientMsgOrBuilder extends MessageOrBuilder {
            Client getClient(int i);

            int getClientCount();

            List getClientList();

            ClientOrBuilder getClientOrBuilder(int i);

            List getClientOrBuilderList();
        }

        public interface ClientOrBuilder extends MessageOrBuilder {
            String getAppChannel();

            ByteString getAppChannelBytes();

            String getAppKey();

            ByteString getAppKeyBytes();

            String getCellId();

            ByteString getCellIdBytes();

            String getChannelInfo();

            ByteString getChannelInfoBytes();

            ProtoMap getClientMap(int i);

            int getClientMapCount();

            List getClientMapList();

            ProtoMapOrBuilder getClientMapOrBuilder(int i);

            List getClientMapOrBuilderList();

            String getDeviceId();

            ByteString getDeviceIdBytes();

            String getDeviceName();

            ByteString getDeviceNameBytes();

            boolean getHasBluetooth();

            boolean getHasGps();

            boolean getHasGravity();

            boolean getHasWifi();

            String getImei();

            ByteString getImeiBytes();

            String getKey();

            ByteString getKeyBytes();

            String getLac();

            ByteString getLacBytes();

            String getLanguage();

            ByteString getLanguageBytes();

            String getLatitude();

            ByteString getLatitudeBytes();

            String getLogVersionApp();

            ByteString getLogVersionAppBytes();

            String getLongitude();

            ByteString getLongitudeBytes();

            String getMacAddress();

            ByteString getMacAddressBytes();

            String getMccMnc();

            ByteString getMccMncBytes();

            String getMiuiVersion();

            ByteString getMiuiVersionBytes();

            NetworkType getNetwork();

            String getNetworkDetail();

            ByteString getNetworkDetailBytes();

            String getOsVersion();

            ByteString getOsVersionBytes();

            String getPackageName();

            ByteString getPackageNameBytes();

            String getPhoneType();

            ByteString getPhoneTypeBytes();

            String getPlatform();

            ByteString getPlatformBytes();

            String getResolution();

            ByteString getResolutionBytes();

            String getSdkVerson();

            ByteString getSdkVersonBytes();

            String getSessionId();

            ByteString getSessionIdBytes();

            String getSimOperator();

            ByteString getSimOperatorBytes();

            long getTime();

            String getType();

            ByteString getTypeBytes();

            String getUserId();

            ByteString getUserIdBytes();

            int getVersionCode();

            String getVersionName();

            ByteString getVersionNameBytes();

            boolean hasAppChannel();

            boolean hasAppKey();

            boolean hasCellId();

            boolean hasChannelInfo();

            boolean hasDeviceId();

            boolean hasDeviceName();

            boolean hasHasBluetooth();

            boolean hasHasGps();

            boolean hasHasGravity();

            boolean hasHasWifi();

            boolean hasImei();

            boolean hasKey();

            boolean hasLac();

            boolean hasLanguage();

            boolean hasLatitude();

            boolean hasLogVersionApp();

            boolean hasLongitude();

            boolean hasMacAddress();

            boolean hasMccMnc();

            boolean hasMiuiVersion();

            boolean hasNetwork();

            boolean hasNetworkDetail();

            boolean hasOsVersion();

            boolean hasPackageName();

            boolean hasPhoneType();

            boolean hasPlatform();

            boolean hasResolution();

            boolean hasSdkVerson();

            boolean hasSessionId();

            boolean hasSimOperator();

            boolean hasTime();

            boolean hasType();

            boolean hasUserId();

            boolean hasVersionCode();

            boolean hasVersionName();
        }

        public final class ClientResponse extends GeneratedMessage implements ClientResponseOrBuilder {
            public static final int INTERVAL_FIELD_NUMBER = 3;
            public static final int IS_WIFI_FIELD_NUMBER = 4;
            public static final int KEY_FIELD_NUMBER = 5;
            public static Parser PARSER = new e();
            public static final int STRATEGY_FIELD_NUMBER = 2;
            public static final int TYPE_FIELD_NUMBER = 1;
            private static final ClientResponse bq = new ClientResponse(true);
            /* access modifiers changed from: private */
            public int aA;
            /* access modifiers changed from: private */
            public Object aC;
            private final UnknownFieldSet aw;
            private byte ax;
            private int ay;
            /* access modifiers changed from: private */
            public Object bg;
            /* access modifiers changed from: private */
            public ReportStrategy br;
            /* access modifiers changed from: private */
            public int bs;
            /* access modifiers changed from: private */
            public boolean bt;

            public final class Builder extends GeneratedMessage.Builder implements ClientResponseOrBuilder {
                private int aA;
                private Object aC;
                private Object bg;
                private ReportStrategy br;
                private int bs;
                private boolean bt;

                private Builder() {
                    this.aC = "";
                    this.br = ReportStrategy.REAL_TIME;
                    this.bg = "";
                    R();
                }

                private Builder(GeneratedMessage.BuilderParent builderParent) {
                    super(builderParent);
                    this.aC = "";
                    this.br = ReportStrategy.REAL_TIME;
                    this.bg = "";
                    R();
                }

                /* synthetic */ Builder(GeneratedMessage.BuilderParent builderParent, a aVar) {
                    this(builderParent);
                }

                private void R() {
                    boolean unused = ClientResponse.alwaysUseFieldBuilders;
                }

                /* access modifiers changed from: private */
                public static Builder ah() {
                    return new Builder();
                }

                public static final Descriptors.Descriptor getDescriptor() {
                    return ProtoMsg.ae;
                }

                public ClientResponse build() {
                    ClientResponse buildPartial = buildPartial();
                    if (buildPartial.isInitialized()) {
                        return buildPartial;
                    }
                    throw newUninitializedMessageException(buildPartial);
                }

                public ClientResponse buildPartial() {
                    ClientResponse clientResponse = new ClientResponse((GeneratedMessage.Builder) this, (a) null);
                    int i = this.aA;
                    int i2 = 1;
                    if ((i & 1) != 1) {
                        i2 = 0;
                    }
                    Object unused = clientResponse.aC = this.aC;
                    if ((i & 2) == 2) {
                        i2 |= 2;
                    }
                    ReportStrategy unused2 = clientResponse.br = this.br;
                    if ((i & 4) == 4) {
                        i2 |= 4;
                    }
                    int unused3 = clientResponse.bs = this.bs;
                    if ((i & 8) == 8) {
                        i2 |= 8;
                    }
                    boolean unused4 = clientResponse.bt = this.bt;
                    if ((i & 16) == 16) {
                        i2 |= 16;
                    }
                    Object unused5 = clientResponse.bg = this.bg;
                    int unused6 = clientResponse.aA = i2;
                    onBuilt();
                    return clientResponse;
                }

                public Builder clear() {
                    super.clear();
                    this.aC = "";
                    this.aA &= -2;
                    this.br = ReportStrategy.REAL_TIME;
                    this.aA &= -3;
                    this.bs = 0;
                    this.aA &= -5;
                    this.bt = false;
                    this.aA &= -9;
                    this.bg = "";
                    this.aA &= -17;
                    return this;
                }

                public Builder clearInterval() {
                    this.aA &= -5;
                    this.bs = 0;
                    onChanged();
                    return this;
                }

                public Builder clearIsWifi() {
                    this.aA &= -9;
                    this.bt = false;
                    onChanged();
                    return this;
                }

                public Builder clearKey() {
                    this.aA &= -17;
                    this.bg = ClientResponse.getDefaultInstance().getKey();
                    onChanged();
                    return this;
                }

                public Builder clearStrategy() {
                    this.aA &= -3;
                    this.br = ReportStrategy.REAL_TIME;
                    onChanged();
                    return this;
                }

                public Builder clearType() {
                    this.aA &= -2;
                    this.aC = ClientResponse.getDefaultInstance().getType();
                    onChanged();
                    return this;
                }

                public Builder clone() {
                    return ah().mergeFrom(buildPartial());
                }

                public ClientResponse getDefaultInstanceForType() {
                    return ClientResponse.getDefaultInstance();
                }

                public Descriptors.Descriptor getDescriptorForType() {
                    return ProtoMsg.ae;
                }

                public int getInterval() {
                    return this.bs;
                }

                public boolean getIsWifi() {
                    return this.bt;
                }

                public String getKey() {
                    Object obj = this.bg;
                    if (obj instanceof String) {
                        return (String) obj;
                    }
                    ByteString byteString = (ByteString) obj;
                    String stringUtf8 = byteString.toStringUtf8();
                    if (byteString.isValidUtf8()) {
                        this.bg = stringUtf8;
                    }
                    return stringUtf8;
                }

                public ByteString getKeyBytes() {
                    Object obj = this.bg;
                    if (!(obj instanceof String)) {
                        return (ByteString) obj;
                    }
                    ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                    this.bg = copyFromUtf8;
                    return copyFromUtf8;
                }

                public ReportStrategy getStrategy() {
                    return this.br;
                }

                public String getType() {
                    Object obj = this.aC;
                    if (obj instanceof String) {
                        return (String) obj;
                    }
                    ByteString byteString = (ByteString) obj;
                    String stringUtf8 = byteString.toStringUtf8();
                    if (byteString.isValidUtf8()) {
                        this.aC = stringUtf8;
                    }
                    return stringUtf8;
                }

                public ByteString getTypeBytes() {
                    Object obj = this.aC;
                    if (!(obj instanceof String)) {
                        return (ByteString) obj;
                    }
                    ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                    this.aC = copyFromUtf8;
                    return copyFromUtf8;
                }

                public boolean hasInterval() {
                    return (this.aA & 4) == 4;
                }

                public boolean hasIsWifi() {
                    return (this.aA & 8) == 8;
                }

                public boolean hasKey() {
                    return (this.aA & 16) == 16;
                }

                public boolean hasStrategy() {
                    return (this.aA & 2) == 2;
                }

                public boolean hasType() {
                    return (this.aA & 1) == 1;
                }

                /* access modifiers changed from: protected */
                public GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
                    return ProtoMsg.af.ensureFieldAccessorsInitialized(ClientResponse.class, Builder.class);
                }

                public final boolean isInitialized() {
                    return hasType() && hasStrategy();
                }

                public Builder mergeFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) {
                    ClientResponse clientResponse;
                    ClientResponse clientResponse2 = null;
                    try {
                        ClientResponse clientResponse3 = (ClientResponse) ClientResponse.PARSER.parsePartialFrom(codedInputStream, extensionRegistryLite);
                        if (clientResponse3 != null) {
                            mergeFrom(clientResponse3);
                        }
                        return this;
                    } catch (InvalidProtocolBufferException e) {
                        clientResponse = (ClientResponse) e.getUnfinishedMessage();
                        throw e;
                    } catch (Throwable th) {
                        th = th;
                        clientResponse2 = clientResponse;
                    }
                    if (clientResponse2 != null) {
                        mergeFrom(clientResponse2);
                    }
                    throw th;
                }

                public Builder mergeFrom(Message message) {
                    if (message instanceof ClientResponse) {
                        return mergeFrom((ClientResponse) message);
                    }
                    super.mergeFrom(message);
                    return this;
                }

                public Builder mergeFrom(ClientResponse clientResponse) {
                    if (clientResponse == ClientResponse.getDefaultInstance()) {
                        return this;
                    }
                    if (clientResponse.hasType()) {
                        this.aA |= 1;
                        this.aC = clientResponse.aC;
                        onChanged();
                    }
                    if (clientResponse.hasStrategy()) {
                        setStrategy(clientResponse.getStrategy());
                    }
                    if (clientResponse.hasInterval()) {
                        setInterval(clientResponse.getInterval());
                    }
                    if (clientResponse.hasIsWifi()) {
                        setIsWifi(clientResponse.getIsWifi());
                    }
                    if (clientResponse.hasKey()) {
                        this.aA |= 16;
                        this.bg = clientResponse.bg;
                        onChanged();
                    }
                    mergeUnknownFields(clientResponse.getUnknownFields());
                    return this;
                }

                public Builder setInterval(int i) {
                    this.aA |= 4;
                    this.bs = i;
                    onChanged();
                    return this;
                }

                public Builder setIsWifi(boolean z) {
                    this.aA |= 8;
                    this.bt = z;
                    onChanged();
                    return this;
                }

                public Builder setKey(String str) {
                    if (str != null) {
                        this.aA |= 16;
                        this.bg = str;
                        onChanged();
                        return this;
                    }
                    throw new NullPointerException();
                }

                public Builder setKeyBytes(ByteString byteString) {
                    if (byteString != null) {
                        this.aA |= 16;
                        this.bg = byteString;
                        onChanged();
                        return this;
                    }
                    throw new NullPointerException();
                }

                public Builder setStrategy(ReportStrategy reportStrategy) {
                    if (reportStrategy != null) {
                        this.aA |= 2;
                        this.br = reportStrategy;
                        onChanged();
                        return this;
                    }
                    throw new NullPointerException();
                }

                public Builder setType(String str) {
                    if (str != null) {
                        this.aA |= 1;
                        this.aC = str;
                        onChanged();
                        return this;
                    }
                    throw new NullPointerException();
                }

                public Builder setTypeBytes(ByteString byteString) {
                    if (byteString != null) {
                        this.aA |= 1;
                        this.aC = byteString;
                        onChanged();
                        return this;
                    }
                    throw new NullPointerException();
                }
            }

            static {
                bq.P();
            }

            private ClientResponse(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) {
                this.ax = -1;
                this.ay = -1;
                P();
                UnknownFieldSet.Builder newBuilder = UnknownFieldSet.newBuilder();
                boolean z = false;
                while (!z) {
                    try {
                        int readTag = codedInputStream.readTag();
                        if (readTag != 0) {
                            if (readTag == 10) {
                                ByteString readBytes = codedInputStream.readBytes();
                                this.aA = 1 | this.aA;
                                this.aC = readBytes;
                            } else if (readTag == 16) {
                                int readEnum = codedInputStream.readEnum();
                                ReportStrategy valueOf = ReportStrategy.valueOf(readEnum);
                                if (valueOf == null) {
                                    newBuilder.mergeVarintField(2, readEnum);
                                } else {
                                    this.aA |= 2;
                                    this.br = valueOf;
                                }
                            } else if (readTag == 24) {
                                this.aA |= 4;
                                this.bs = codedInputStream.readUInt32();
                            } else if (readTag == 32) {
                                this.aA |= 8;
                                this.bt = codedInputStream.readBool();
                            } else if (readTag == 42) {
                                ByteString readBytes2 = codedInputStream.readBytes();
                                this.aA |= 16;
                                this.bg = readBytes2;
                            } else if (!parseUnknownField(codedInputStream, newBuilder, extensionRegistryLite, readTag)) {
                            }
                        }
                        z = true;
                    } catch (InvalidProtocolBufferException e) {
                        throw e.setUnfinishedMessage(this);
                    } catch (IOException e2) {
                        throw new InvalidProtocolBufferException(e2.getMessage()).setUnfinishedMessage(this);
                    } catch (Throwable th) {
                        this.aw = newBuilder.build();
                        makeExtensionsImmutable();
                        throw th;
                    }
                }
                this.aw = newBuilder.build();
                makeExtensionsImmutable();
            }

            /* synthetic */ ClientResponse(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite, a aVar) {
                this(codedInputStream, extensionRegistryLite);
            }

            private ClientResponse(GeneratedMessage.Builder builder) {
                super(builder);
                this.ax = -1;
                this.ay = -1;
                this.aw = builder.getUnknownFields();
            }

            /* synthetic */ ClientResponse(GeneratedMessage.Builder builder, a aVar) {
                this(builder);
            }

            private ClientResponse(boolean z) {
                this.ax = -1;
                this.ay = -1;
                this.aw = UnknownFieldSet.getDefaultInstance();
            }

            private void P() {
                this.aC = "";
                this.br = ReportStrategy.REAL_TIME;
                this.bs = 0;
                this.bt = false;
                this.bg = "";
            }

            public static ClientResponse getDefaultInstance() {
                return bq;
            }

            public static final Descriptors.Descriptor getDescriptor() {
                return ProtoMsg.ae;
            }

            public static Builder newBuilder() {
                return Builder.ah();
            }

            public static Builder newBuilder(ClientResponse clientResponse) {
                return newBuilder().mergeFrom(clientResponse);
            }

            public static ClientResponse parseDelimitedFrom(InputStream inputStream) {
                return (ClientResponse) PARSER.parseDelimitedFrom(inputStream);
            }

            public static ClientResponse parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) {
                return (ClientResponse) PARSER.parseDelimitedFrom(inputStream, extensionRegistryLite);
            }

            public static ClientResponse parseFrom(ByteString byteString) {
                return (ClientResponse) PARSER.parseFrom(byteString);
            }

            public static ClientResponse parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) {
                return (ClientResponse) PARSER.parseFrom(byteString, extensionRegistryLite);
            }

            public static ClientResponse parseFrom(CodedInputStream codedInputStream) {
                return (ClientResponse) PARSER.parseFrom(codedInputStream);
            }

            public static ClientResponse parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) {
                return (ClientResponse) PARSER.parseFrom(codedInputStream, extensionRegistryLite);
            }

            public static ClientResponse parseFrom(InputStream inputStream) {
                return (ClientResponse) PARSER.parseFrom(inputStream);
            }

            public static ClientResponse parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) {
                return (ClientResponse) PARSER.parseFrom(inputStream, extensionRegistryLite);
            }

            public static ClientResponse parseFrom(byte[] bArr) {
                return (ClientResponse) PARSER.parseFrom(bArr);
            }

            public static ClientResponse parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) {
                return (ClientResponse) PARSER.parseFrom(bArr, extensionRegistryLite);
            }

            public ClientResponse getDefaultInstanceForType() {
                return bq;
            }

            public int getInterval() {
                return this.bs;
            }

            public boolean getIsWifi() {
                return this.bt;
            }

            public String getKey() {
                Object obj = this.bg;
                if (obj instanceof String) {
                    return (String) obj;
                }
                ByteString byteString = (ByteString) obj;
                String stringUtf8 = byteString.toStringUtf8();
                if (byteString.isValidUtf8()) {
                    this.bg = stringUtf8;
                }
                return stringUtf8;
            }

            public ByteString getKeyBytes() {
                Object obj = this.bg;
                if (!(obj instanceof String)) {
                    return (ByteString) obj;
                }
                ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                this.bg = copyFromUtf8;
                return copyFromUtf8;
            }

            public Parser getParserForType() {
                return PARSER;
            }

            public int getSerializedSize() {
                int i = this.ay;
                if (i != -1) {
                    return i;
                }
                int i2 = 0;
                if ((this.aA & 1) == 1) {
                    i2 = 0 + CodedOutputStream.computeBytesSize(1, getTypeBytes());
                }
                if ((this.aA & 2) == 2) {
                    i2 += CodedOutputStream.computeEnumSize(2, this.br.getNumber());
                }
                if ((this.aA & 4) == 4) {
                    i2 += CodedOutputStream.computeUInt32Size(3, this.bs);
                }
                if ((this.aA & 8) == 8) {
                    i2 += CodedOutputStream.computeBoolSize(4, this.bt);
                }
                if ((this.aA & 16) == 16) {
                    i2 += CodedOutputStream.computeBytesSize(5, getKeyBytes());
                }
                int serializedSize = i2 + getUnknownFields().getSerializedSize();
                this.ay = serializedSize;
                return serializedSize;
            }

            public ReportStrategy getStrategy() {
                return this.br;
            }

            public String getType() {
                Object obj = this.aC;
                if (obj instanceof String) {
                    return (String) obj;
                }
                ByteString byteString = (ByteString) obj;
                String stringUtf8 = byteString.toStringUtf8();
                if (byteString.isValidUtf8()) {
                    this.aC = stringUtf8;
                }
                return stringUtf8;
            }

            public ByteString getTypeBytes() {
                Object obj = this.aC;
                if (!(obj instanceof String)) {
                    return (ByteString) obj;
                }
                ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                this.aC = copyFromUtf8;
                return copyFromUtf8;
            }

            public final UnknownFieldSet getUnknownFields() {
                return this.aw;
            }

            public boolean hasInterval() {
                return (this.aA & 4) == 4;
            }

            public boolean hasIsWifi() {
                return (this.aA & 8) == 8;
            }

            public boolean hasKey() {
                return (this.aA & 16) == 16;
            }

            public boolean hasStrategy() {
                return (this.aA & 2) == 2;
            }

            public boolean hasType() {
                return (this.aA & 1) == 1;
            }

            /* access modifiers changed from: protected */
            public GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
                return ProtoMsg.af.ensureFieldAccessorsInitialized(ClientResponse.class, Builder.class);
            }

            public final boolean isInitialized() {
                byte b = this.ax;
                if (b == 1) {
                    return true;
                }
                if (b == 0) {
                    return false;
                }
                if (!hasType()) {
                    this.ax = 0;
                    return false;
                } else if (!hasStrategy()) {
                    this.ax = 0;
                    return false;
                } else {
                    this.ax = 1;
                    return true;
                }
            }

            public Builder newBuilderForType() {
                return newBuilder();
            }

            /* access modifiers changed from: protected */
            public Builder newBuilderForType(GeneratedMessage.BuilderParent builderParent) {
                return new Builder(builderParent, (a) null);
            }

            public Builder toBuilder() {
                return newBuilder(this);
            }

            /* access modifiers changed from: protected */
            public Object writeReplace() {
                return super.writeReplace();
            }

            public void writeTo(CodedOutputStream codedOutputStream) {
                getSerializedSize();
                if ((this.aA & 1) == 1) {
                    codedOutputStream.writeBytes(1, getTypeBytes());
                }
                if ((this.aA & 2) == 2) {
                    codedOutputStream.writeEnum(2, this.br.getNumber());
                }
                if ((this.aA & 4) == 4) {
                    codedOutputStream.writeUInt32(3, this.bs);
                }
                if ((this.aA & 8) == 8) {
                    codedOutputStream.writeBool(4, this.bt);
                }
                if ((this.aA & 16) == 16) {
                    codedOutputStream.writeBytes(5, getKeyBytes());
                }
                getUnknownFields().writeTo(codedOutputStream);
            }
        }

        public interface ClientResponseOrBuilder extends MessageOrBuilder {
            int getInterval();

            boolean getIsWifi();

            String getKey();

            ByteString getKeyBytes();

            ReportStrategy getStrategy();

            String getType();

            ByteString getTypeBytes();

            boolean hasInterval();

            boolean hasIsWifi();

            boolean hasKey();

            boolean hasStrategy();

            boolean hasType();
        }

        public final class Crash extends GeneratedMessage implements CrashOrBuilder {
            public static final int APP_CHANNEL_FIELD_NUMBER = 3;
            public static final int APP_KEY_FIELD_NUMBER = 2;
            public static final int CRASH_MAP_FIELD_NUMBER = 8;
            public static final int CRASH_MD5_FIELD_NUMBER = 17;
            public static final int CRASH_NUM_FIELD_NUMBER = 16;
            public static final int DEVICE_ID_FIELD_NUMBER = 6;
            public static final int DEVICE_NAME_FIELD_NUMBER = 12;
            public static final int LARGE_MEMORY_CLASS_FIELD_NUMBER = 15;
            public static final int LOG_VERSION_APP_FIELD_NUMBER = 19;
            public static final int MEMORY_CLASS_FIELD_NUMBER = 14;
            public static final int PACKAGE_NAME_FIELD_NUMBER = 18;
            public static Parser PARSER = new f();
            public static final int PLATFORM_FIELD_NUMBER = 13;
            public static final int SESSION_ID_FIELD_NUMBER = 4;
            public static final int THROWABLE_FIELD_NUMBER = 7;
            public static final int TIME_FIELD_NUMBER = 5;
            public static final int TYPE_FIELD_NUMBER = 1;
            public static final int USER_ID_FIELD_NUMBER = 11;
            public static final int VERSION_CODE_FIELD_NUMBER = 9;
            public static final int VERSION_NAME_FIELD_NUMBER = 10;
            private static final Crash bu = new Crash(true);
            /* access modifiers changed from: private */
            public int aA;
            /* access modifiers changed from: private */
            public Object aC;
            /* access modifiers changed from: private */
            public Object aD;
            /* access modifiers changed from: private */
            public Object aE;
            /* access modifiers changed from: private */
            public Object aF;
            /* access modifiers changed from: private */
            public Object aG;
            /* access modifiers changed from: private */
            public Object aH;
            /* access modifiers changed from: private */
            public int aI;
            /* access modifiers changed from: private */
            public Object aJ;
            /* access modifiers changed from: private */
            public Object aM;
            /* access modifiers changed from: private */
            public Object aP;
            private final UnknownFieldSet aw;
            private byte ax;
            private int ay;
            /* access modifiers changed from: private */
            public Object bA;
            /* access modifiers changed from: private */
            public long bb;
            /* access modifiers changed from: private */
            public Object bh;
            /* access modifiers changed from: private */
            public Object bj;
            /* access modifiers changed from: private */
            public Object bv;
            /* access modifiers changed from: private */
            public List bw;
            /* access modifiers changed from: private */
            public Object bx;
            /* access modifiers changed from: private */
            public Object by;
            /* access modifiers changed from: private */
            public int bz;

            public final class Builder extends GeneratedMessage.Builder implements CrashOrBuilder {
                private int aA;
                private Object aC;
                private Object aD;
                private Object aE;
                private Object aF;
                private Object aG;
                private Object aH;
                private int aI;
                private Object aJ;
                private Object aM;
                private Object aP;
                private Object bA;
                private RepeatedFieldBuilder bB;
                private long bb;
                private Object bh;
                private Object bj;
                private Object bv;
                private List bw;
                private Object bx;
                private Object by;
                private int bz;

                private Builder() {
                    this.aC = "";
                    this.aD = "";
                    this.aE = "";
                    this.aF = "";
                    this.aG = "";
                    this.bv = "";
                    this.bw = Collections.emptyList();
                    this.aJ = "";
                    this.aH = "";
                    this.aP = "";
                    this.aM = "";
                    this.bx = "";
                    this.by = "";
                    this.bA = "";
                    this.bh = "";
                    this.bj = "";
                    R();
                }

                private Builder(GeneratedMessage.BuilderParent builderParent) {
                    super(builderParent);
                    this.aC = "";
                    this.aD = "";
                    this.aE = "";
                    this.aF = "";
                    this.aG = "";
                    this.bv = "";
                    this.bw = Collections.emptyList();
                    this.aJ = "";
                    this.aH = "";
                    this.aP = "";
                    this.aM = "";
                    this.bx = "";
                    this.by = "";
                    this.bA = "";
                    this.bh = "";
                    this.bj = "";
                    R();
                }

                /* synthetic */ Builder(GeneratedMessage.BuilderParent builderParent, a aVar) {
                    this(builderParent);
                }

                private void R() {
                    if (Crash.alwaysUseFieldBuilders) {
                        an();
                    }
                }

                /* access modifiers changed from: private */
                public static Builder al() {
                    return new Builder();
                }

                private void am() {
                    if ((this.aA & 128) != 128) {
                        this.bw = new ArrayList(this.bw);
                        this.aA |= 128;
                    }
                }

                private RepeatedFieldBuilder an() {
                    if (this.bB == null) {
                        this.bB = new RepeatedFieldBuilder(this.bw, (this.aA & 128) == 128, getParentForChildren(), isClean());
                        this.bw = null;
                    }
                    return this.bB;
                }

                public static final Descriptors.Descriptor getDescriptor() {
                    return ProtoMsg.ak;
                }

                public Builder addAllCrashMap(Iterable iterable) {
                    if (this.bB == null) {
                        am();
                        AbstractMessageLite.Builder.addAll(iterable, this.bw);
                        onChanged();
                    } else {
                        this.bB.addAllMessages(iterable);
                    }
                    return this;
                }

                public Builder addCrashMap(int i, ProtoMap.Builder builder) {
                    if (this.bB == null) {
                        am();
                        this.bw.add(i, builder.build());
                        onChanged();
                    } else {
                        this.bB.addMessage(i, builder.build());
                    }
                    return this;
                }

                public Builder addCrashMap(int i, ProtoMap protoMap) {
                    if (this.bB != null) {
                        this.bB.addMessage(i, protoMap);
                    } else if (protoMap != null) {
                        am();
                        this.bw.add(i, protoMap);
                        onChanged();
                    } else {
                        throw new NullPointerException();
                    }
                    return this;
                }

                public Builder addCrashMap(ProtoMap.Builder builder) {
                    if (this.bB == null) {
                        am();
                        this.bw.add(builder.build());
                        onChanged();
                    } else {
                        this.bB.addMessage(builder.build());
                    }
                    return this;
                }

                public Builder addCrashMap(ProtoMap protoMap) {
                    if (this.bB != null) {
                        this.bB.addMessage(protoMap);
                    } else if (protoMap != null) {
                        am();
                        this.bw.add(protoMap);
                        onChanged();
                    } else {
                        throw new NullPointerException();
                    }
                    return this;
                }

                public ProtoMap.Builder addCrashMapBuilder() {
                    return (ProtoMap.Builder) an().addBuilder(ProtoMap.getDefaultInstance());
                }

                public ProtoMap.Builder addCrashMapBuilder(int i) {
                    return (ProtoMap.Builder) an().addBuilder(i, ProtoMap.getDefaultInstance());
                }

                public Crash build() {
                    Crash buildPartial = buildPartial();
                    if (buildPartial.isInitialized()) {
                        return buildPartial;
                    }
                    throw newUninitializedMessageException(buildPartial);
                }

                public Crash buildPartial() {
                    List list;
                    Crash crash = new Crash((GeneratedMessage.Builder) this, (a) null);
                    int i = this.aA;
                    int i2 = 1;
                    if ((i & 1) != 1) {
                        i2 = 0;
                    }
                    Object unused = crash.aC = this.aC;
                    if ((i & 2) == 2) {
                        i2 |= 2;
                    }
                    Object unused2 = crash.aD = this.aD;
                    if ((i & 4) == 4) {
                        i2 |= 4;
                    }
                    Object unused3 = crash.aE = this.aE;
                    if ((i & 8) == 8) {
                        i2 |= 8;
                    }
                    Object unused4 = crash.aF = this.aF;
                    if ((i & 16) == 16) {
                        i2 |= 16;
                    }
                    long unused5 = crash.bb = this.bb;
                    if ((i & 32) == 32) {
                        i2 |= 32;
                    }
                    Object unused6 = crash.aG = this.aG;
                    if ((i & 64) == 64) {
                        i2 |= 64;
                    }
                    Object unused7 = crash.bv = this.bv;
                    if (this.bB == null) {
                        if ((this.aA & 128) == 128) {
                            this.bw = Collections.unmodifiableList(this.bw);
                            this.aA &= -129;
                        }
                        list = this.bw;
                    } else {
                        list = this.bB.build();
                    }
                    List unused8 = crash.bw = list;
                    if ((i & 256) == 256) {
                        i2 |= 128;
                    }
                    int unused9 = crash.aI = this.aI;
                    if ((i & 512) == 512) {
                        i2 |= 256;
                    }
                    Object unused10 = crash.aJ = this.aJ;
                    if ((i & 1024) == 1024) {
                        i2 |= 512;
                    }
                    Object unused11 = crash.aH = this.aH;
                    if ((i & 2048) == 2048) {
                        i2 |= 1024;
                    }
                    Object unused12 = crash.aP = this.aP;
                    if ((i & 4096) == 4096) {
                        i2 |= 2048;
                    }
                    Object unused13 = crash.aM = this.aM;
                    if ((i & 8192) == 8192) {
                        i2 |= 4096;
                    }
                    Object unused14 = crash.bx = this.bx;
                    if ((i & 16384) == 16384) {
                        i2 |= 8192;
                    }
                    Object unused15 = crash.by = this.by;
                    if ((32768 & i) == 32768) {
                        i2 |= 16384;
                    }
                    int unused16 = crash.bz = this.bz;
                    if ((65536 & i) == 65536) {
                        i2 |= 32768;
                    }
                    Object unused17 = crash.bA = this.bA;
                    if ((131072 & i) == 131072) {
                        i2 |= 65536;
                    }
                    Object unused18 = crash.bh = this.bh;
                    if ((i & 262144) == 262144) {
                        i2 |= 131072;
                    }
                    Object unused19 = crash.bj = this.bj;
                    int unused20 = crash.aA = i2;
                    onBuilt();
                    return crash;
                }

                public Builder clear() {
                    super.clear();
                    this.aC = "";
                    this.aA &= -2;
                    this.aD = "";
                    this.aA &= -3;
                    this.aE = "";
                    this.aA &= -5;
                    this.aF = "";
                    this.aA &= -9;
                    this.bb = 0;
                    this.aA &= -17;
                    this.aG = "";
                    this.aA &= -33;
                    this.bv = "";
                    this.aA &= -65;
                    if (this.bB == null) {
                        this.bw = Collections.emptyList();
                        this.aA &= -129;
                    } else {
                        this.bB.clear();
                    }
                    this.aI = 0;
                    this.aA &= -257;
                    this.aJ = "";
                    this.aA &= -513;
                    this.aH = "";
                    this.aA &= -1025;
                    this.aP = "";
                    this.aA &= -2049;
                    this.aM = "";
                    this.aA &= -4097;
                    this.bx = "";
                    this.aA &= -8193;
                    this.by = "";
                    this.aA &= -16385;
                    this.bz = 0;
                    this.aA &= -32769;
                    this.bA = "";
                    this.aA &= -65537;
                    this.bh = "";
                    this.aA &= -131073;
                    this.bj = "";
                    this.aA &= -262145;
                    return this;
                }

                public Builder clearAppChannel() {
                    this.aA &= -5;
                    this.aE = Crash.getDefaultInstance().getAppChannel();
                    onChanged();
                    return this;
                }

                public Builder clearAppKey() {
                    this.aA &= -3;
                    this.aD = Crash.getDefaultInstance().getAppKey();
                    onChanged();
                    return this;
                }

                public Builder clearCrashMap() {
                    if (this.bB == null) {
                        this.bw = Collections.emptyList();
                        this.aA &= -129;
                        onChanged();
                    } else {
                        this.bB.clear();
                    }
                    return this;
                }

                public Builder clearCrashMd5() {
                    this.aA &= -65537;
                    this.bA = Crash.getDefaultInstance().getCrashMd5();
                    onChanged();
                    return this;
                }

                public Builder clearCrashNum() {
                    this.aA &= -32769;
                    this.bz = 0;
                    onChanged();
                    return this;
                }

                public Builder clearDeviceId() {
                    this.aA &= -33;
                    this.aG = Crash.getDefaultInstance().getDeviceId();
                    onChanged();
                    return this;
                }

                public Builder clearDeviceName() {
                    this.aA &= -2049;
                    this.aP = Crash.getDefaultInstance().getDeviceName();
                    onChanged();
                    return this;
                }

                public Builder clearLargeMemoryClass() {
                    this.aA &= -16385;
                    this.by = Crash.getDefaultInstance().getLargeMemoryClass();
                    onChanged();
                    return this;
                }

                public Builder clearLogVersionApp() {
                    this.aA &= -262145;
                    this.bj = Crash.getDefaultInstance().getLogVersionApp();
                    onChanged();
                    return this;
                }

                public Builder clearMemoryClass() {
                    this.aA &= -8193;
                    this.bx = Crash.getDefaultInstance().getMemoryClass();
                    onChanged();
                    return this;
                }

                public Builder clearPackageName() {
                    this.aA &= -131073;
                    this.bh = Crash.getDefaultInstance().getPackageName();
                    onChanged();
                    return this;
                }

                public Builder clearPlatform() {
                    this.aA &= -4097;
                    this.aM = Crash.getDefaultInstance().getPlatform();
                    onChanged();
                    return this;
                }

                public Builder clearSessionId() {
                    this.aA &= -9;
                    this.aF = Crash.getDefaultInstance().getSessionId();
                    onChanged();
                    return this;
                }

                public Builder clearThrowable() {
                    this.aA &= -65;
                    this.bv = Crash.getDefaultInstance().getThrowable();
                    onChanged();
                    return this;
                }

                public Builder clearTime() {
                    this.aA &= -17;
                    this.bb = 0;
                    onChanged();
                    return this;
                }

                public Builder clearType() {
                    this.aA &= -2;
                    this.aC = Crash.getDefaultInstance().getType();
                    onChanged();
                    return this;
                }

                public Builder clearUserId() {
                    this.aA &= -1025;
                    this.aH = Crash.getDefaultInstance().getUserId();
                    onChanged();
                    return this;
                }

                public Builder clearVersionCode() {
                    this.aA &= -257;
                    this.aI = 0;
                    onChanged();
                    return this;
                }

                public Builder clearVersionName() {
                    this.aA &= -513;
                    this.aJ = Crash.getDefaultInstance().getVersionName();
                    onChanged();
                    return this;
                }

                public Builder clone() {
                    return al().mergeFrom(buildPartial());
                }

                public String getAppChannel() {
                    Object obj = this.aE;
                    if (obj instanceof String) {
                        return (String) obj;
                    }
                    ByteString byteString = (ByteString) obj;
                    String stringUtf8 = byteString.toStringUtf8();
                    if (byteString.isValidUtf8()) {
                        this.aE = stringUtf8;
                    }
                    return stringUtf8;
                }

                public ByteString getAppChannelBytes() {
                    Object obj = this.aE;
                    if (!(obj instanceof String)) {
                        return (ByteString) obj;
                    }
                    ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                    this.aE = copyFromUtf8;
                    return copyFromUtf8;
                }

                public String getAppKey() {
                    Object obj = this.aD;
                    if (obj instanceof String) {
                        return (String) obj;
                    }
                    ByteString byteString = (ByteString) obj;
                    String stringUtf8 = byteString.toStringUtf8();
                    if (byteString.isValidUtf8()) {
                        this.aD = stringUtf8;
                    }
                    return stringUtf8;
                }

                public ByteString getAppKeyBytes() {
                    Object obj = this.aD;
                    if (!(obj instanceof String)) {
                        return (ByteString) obj;
                    }
                    ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                    this.aD = copyFromUtf8;
                    return copyFromUtf8;
                }

                public ProtoMap getCrashMap(int i) {
                    return (ProtoMap) (this.bB == null ? this.bw.get(i) : this.bB.getMessage(i));
                }

                public ProtoMap.Builder getCrashMapBuilder(int i) {
                    return (ProtoMap.Builder) an().getBuilder(i);
                }

                public List getCrashMapBuilderList() {
                    return an().getBuilderList();
                }

                public int getCrashMapCount() {
                    return this.bB == null ? this.bw.size() : this.bB.getCount();
                }

                public List getCrashMapList() {
                    return this.bB == null ? Collections.unmodifiableList(this.bw) : this.bB.getMessageList();
                }

                public ProtoMapOrBuilder getCrashMapOrBuilder(int i) {
                    return (ProtoMapOrBuilder) (this.bB == null ? this.bw.get(i) : this.bB.getMessageOrBuilder(i));
                }

                public List getCrashMapOrBuilderList() {
                    return this.bB != null ? this.bB.getMessageOrBuilderList() : Collections.unmodifiableList(this.bw);
                }

                public String getCrashMd5() {
                    Object obj = this.bA;
                    if (obj instanceof String) {
                        return (String) obj;
                    }
                    ByteString byteString = (ByteString) obj;
                    String stringUtf8 = byteString.toStringUtf8();
                    if (byteString.isValidUtf8()) {
                        this.bA = stringUtf8;
                    }
                    return stringUtf8;
                }

                public ByteString getCrashMd5Bytes() {
                    Object obj = this.bA;
                    if (!(obj instanceof String)) {
                        return (ByteString) obj;
                    }
                    ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                    this.bA = copyFromUtf8;
                    return copyFromUtf8;
                }

                public int getCrashNum() {
                    return this.bz;
                }

                public Crash getDefaultInstanceForType() {
                    return Crash.getDefaultInstance();
                }

                public Descriptors.Descriptor getDescriptorForType() {
                    return ProtoMsg.ak;
                }

                public String getDeviceId() {
                    Object obj = this.aG;
                    if (obj instanceof String) {
                        return (String) obj;
                    }
                    ByteString byteString = (ByteString) obj;
                    String stringUtf8 = byteString.toStringUtf8();
                    if (byteString.isValidUtf8()) {
                        this.aG = stringUtf8;
                    }
                    return stringUtf8;
                }

                public ByteString getDeviceIdBytes() {
                    Object obj = this.aG;
                    if (!(obj instanceof String)) {
                        return (ByteString) obj;
                    }
                    ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                    this.aG = copyFromUtf8;
                    return copyFromUtf8;
                }

                public String getDeviceName() {
                    Object obj = this.aP;
                    if (obj instanceof String) {
                        return (String) obj;
                    }
                    ByteString byteString = (ByteString) obj;
                    String stringUtf8 = byteString.toStringUtf8();
                    if (byteString.isValidUtf8()) {
                        this.aP = stringUtf8;
                    }
                    return stringUtf8;
                }

                public ByteString getDeviceNameBytes() {
                    Object obj = this.aP;
                    if (!(obj instanceof String)) {
                        return (ByteString) obj;
                    }
                    ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                    this.aP = copyFromUtf8;
                    return copyFromUtf8;
                }

                public String getLargeMemoryClass() {
                    Object obj = this.by;
                    if (obj instanceof String) {
                        return (String) obj;
                    }
                    ByteString byteString = (ByteString) obj;
                    String stringUtf8 = byteString.toStringUtf8();
                    if (byteString.isValidUtf8()) {
                        this.by = stringUtf8;
                    }
                    return stringUtf8;
                }

                public ByteString getLargeMemoryClassBytes() {
                    Object obj = this.by;
                    if (!(obj instanceof String)) {
                        return (ByteString) obj;
                    }
                    ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                    this.by = copyFromUtf8;
                    return copyFromUtf8;
                }

                public String getLogVersionApp() {
                    Object obj = this.bj;
                    if (obj instanceof String) {
                        return (String) obj;
                    }
                    ByteString byteString = (ByteString) obj;
                    String stringUtf8 = byteString.toStringUtf8();
                    if (byteString.isValidUtf8()) {
                        this.bj = stringUtf8;
                    }
                    return stringUtf8;
                }

                public ByteString getLogVersionAppBytes() {
                    Object obj = this.bj;
                    if (!(obj instanceof String)) {
                        return (ByteString) obj;
                    }
                    ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                    this.bj = copyFromUtf8;
                    return copyFromUtf8;
                }

                public String getMemoryClass() {
                    Object obj = this.bx;
                    if (obj instanceof String) {
                        return (String) obj;
                    }
                    ByteString byteString = (ByteString) obj;
                    String stringUtf8 = byteString.toStringUtf8();
                    if (byteString.isValidUtf8()) {
                        this.bx = stringUtf8;
                    }
                    return stringUtf8;
                }

                public ByteString getMemoryClassBytes() {
                    Object obj = this.bx;
                    if (!(obj instanceof String)) {
                        return (ByteString) obj;
                    }
                    ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                    this.bx = copyFromUtf8;
                    return copyFromUtf8;
                }

                public String getPackageName() {
                    Object obj = this.bh;
                    if (obj instanceof String) {
                        return (String) obj;
                    }
                    ByteString byteString = (ByteString) obj;
                    String stringUtf8 = byteString.toStringUtf8();
                    if (byteString.isValidUtf8()) {
                        this.bh = stringUtf8;
                    }
                    return stringUtf8;
                }

                public ByteString getPackageNameBytes() {
                    Object obj = this.bh;
                    if (!(obj instanceof String)) {
                        return (ByteString) obj;
                    }
                    ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                    this.bh = copyFromUtf8;
                    return copyFromUtf8;
                }

                public String getPlatform() {
                    Object obj = this.aM;
                    if (obj instanceof String) {
                        return (String) obj;
                    }
                    ByteString byteString = (ByteString) obj;
                    String stringUtf8 = byteString.toStringUtf8();
                    if (byteString.isValidUtf8()) {
                        this.aM = stringUtf8;
                    }
                    return stringUtf8;
                }

                public ByteString getPlatformBytes() {
                    Object obj = this.aM;
                    if (!(obj instanceof String)) {
                        return (ByteString) obj;
                    }
                    ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                    this.aM = copyFromUtf8;
                    return copyFromUtf8;
                }

                public String getSessionId() {
                    Object obj = this.aF;
                    if (obj instanceof String) {
                        return (String) obj;
                    }
                    ByteString byteString = (ByteString) obj;
                    String stringUtf8 = byteString.toStringUtf8();
                    if (byteString.isValidUtf8()) {
                        this.aF = stringUtf8;
                    }
                    return stringUtf8;
                }

                public ByteString getSessionIdBytes() {
                    Object obj = this.aF;
                    if (!(obj instanceof String)) {
                        return (ByteString) obj;
                    }
                    ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                    this.aF = copyFromUtf8;
                    return copyFromUtf8;
                }

                public String getThrowable() {
                    Object obj = this.bv;
                    if (obj instanceof String) {
                        return (String) obj;
                    }
                    ByteString byteString = (ByteString) obj;
                    String stringUtf8 = byteString.toStringUtf8();
                    if (byteString.isValidUtf8()) {
                        this.bv = stringUtf8;
                    }
                    return stringUtf8;
                }

                public ByteString getThrowableBytes() {
                    Object obj = this.bv;
                    if (!(obj instanceof String)) {
                        return (ByteString) obj;
                    }
                    ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                    this.bv = copyFromUtf8;
                    return copyFromUtf8;
                }

                public long getTime() {
                    return this.bb;
                }

                public String getType() {
                    Object obj = this.aC;
                    if (obj instanceof String) {
                        return (String) obj;
                    }
                    ByteString byteString = (ByteString) obj;
                    String stringUtf8 = byteString.toStringUtf8();
                    if (byteString.isValidUtf8()) {
                        this.aC = stringUtf8;
                    }
                    return stringUtf8;
                }

                public ByteString getTypeBytes() {
                    Object obj = this.aC;
                    if (!(obj instanceof String)) {
                        return (ByteString) obj;
                    }
                    ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                    this.aC = copyFromUtf8;
                    return copyFromUtf8;
                }

                public String getUserId() {
                    Object obj = this.aH;
                    if (obj instanceof String) {
                        return (String) obj;
                    }
                    ByteString byteString = (ByteString) obj;
                    String stringUtf8 = byteString.toStringUtf8();
                    if (byteString.isValidUtf8()) {
                        this.aH = stringUtf8;
                    }
                    return stringUtf8;
                }

                public ByteString getUserIdBytes() {
                    Object obj = this.aH;
                    if (!(obj instanceof String)) {
                        return (ByteString) obj;
                    }
                    ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                    this.aH = copyFromUtf8;
                    return copyFromUtf8;
                }

                public int getVersionCode() {
                    return this.aI;
                }

                public String getVersionName() {
                    Object obj = this.aJ;
                    if (obj instanceof String) {
                        return (String) obj;
                    }
                    ByteString byteString = (ByteString) obj;
                    String stringUtf8 = byteString.toStringUtf8();
                    if (byteString.isValidUtf8()) {
                        this.aJ = stringUtf8;
                    }
                    return stringUtf8;
                }

                public ByteString getVersionNameBytes() {
                    Object obj = this.aJ;
                    if (!(obj instanceof String)) {
                        return (ByteString) obj;
                    }
                    ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                    this.aJ = copyFromUtf8;
                    return copyFromUtf8;
                }

                public boolean hasAppChannel() {
                    return (this.aA & 4) == 4;
                }

                public boolean hasAppKey() {
                    return (this.aA & 2) == 2;
                }

                public boolean hasCrashMd5() {
                    return (this.aA & 65536) == 65536;
                }

                public boolean hasCrashNum() {
                    return (this.aA & 32768) == 32768;
                }

                public boolean hasDeviceId() {
                    return (this.aA & 32) == 32;
                }

                public boolean hasDeviceName() {
                    return (this.aA & 2048) == 2048;
                }

                public boolean hasLargeMemoryClass() {
                    return (this.aA & 16384) == 16384;
                }

                public boolean hasLogVersionApp() {
                    return (this.aA & 262144) == 262144;
                }

                public boolean hasMemoryClass() {
                    return (this.aA & 8192) == 8192;
                }

                public boolean hasPackageName() {
                    return (this.aA & 131072) == 131072;
                }

                public boolean hasPlatform() {
                    return (this.aA & 4096) == 4096;
                }

                public boolean hasSessionId() {
                    return (this.aA & 8) == 8;
                }

                public boolean hasThrowable() {
                    return (this.aA & 64) == 64;
                }

                public boolean hasTime() {
                    return (this.aA & 16) == 16;
                }

                public boolean hasType() {
                    return (this.aA & 1) == 1;
                }

                public boolean hasUserId() {
                    return (this.aA & 1024) == 1024;
                }

                public boolean hasVersionCode() {
                    return (this.aA & 256) == 256;
                }

                public boolean hasVersionName() {
                    return (this.aA & 512) == 512;
                }

                /* access modifiers changed from: protected */
                public GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
                    return ProtoMsg.al.ensureFieldAccessorsInitialized(Crash.class, Builder.class);
                }

                public final boolean isInitialized() {
                    if (!hasType()) {
                        return false;
                    }
                    for (int i = 0; i < getCrashMapCount(); i++) {
                        if (!getCrashMap(i).isInitialized()) {
                            return false;
                        }
                    }
                    return true;
                }

                public Builder mergeFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) {
                    Crash crash;
                    Crash crash2 = null;
                    try {
                        Crash crash3 = (Crash) Crash.PARSER.parsePartialFrom(codedInputStream, extensionRegistryLite);
                        if (crash3 != null) {
                            mergeFrom(crash3);
                        }
                        return this;
                    } catch (InvalidProtocolBufferException e) {
                        crash = (Crash) e.getUnfinishedMessage();
                        throw e;
                    } catch (Throwable th) {
                        th = th;
                        crash2 = crash;
                    }
                    if (crash2 != null) {
                        mergeFrom(crash2);
                    }
                    throw th;
                }

                public Builder mergeFrom(Message message) {
                    if (message instanceof Crash) {
                        return mergeFrom((Crash) message);
                    }
                    super.mergeFrom(message);
                    return this;
                }

                public Builder mergeFrom(Crash crash) {
                    if (crash == Crash.getDefaultInstance()) {
                        return this;
                    }
                    if (crash.hasType()) {
                        this.aA |= 1;
                        this.aC = crash.aC;
                        onChanged();
                    }
                    if (crash.hasAppKey()) {
                        this.aA |= 2;
                        this.aD = crash.aD;
                        onChanged();
                    }
                    if (crash.hasAppChannel()) {
                        this.aA |= 4;
                        this.aE = crash.aE;
                        onChanged();
                    }
                    if (crash.hasSessionId()) {
                        this.aA |= 8;
                        this.aF = crash.aF;
                        onChanged();
                    }
                    if (crash.hasTime()) {
                        setTime(crash.getTime());
                    }
                    if (crash.hasDeviceId()) {
                        this.aA |= 32;
                        this.aG = crash.aG;
                        onChanged();
                    }
                    if (crash.hasThrowable()) {
                        this.aA |= 64;
                        this.bv = crash.bv;
                        onChanged();
                    }
                    if (this.bB == null) {
                        if (!crash.bw.isEmpty()) {
                            if (this.bw.isEmpty()) {
                                this.bw = crash.bw;
                                this.aA &= -129;
                            } else {
                                am();
                                this.bw.addAll(crash.bw);
                            }
                            onChanged();
                        }
                    } else if (!crash.bw.isEmpty()) {
                        if (this.bB.isEmpty()) {
                            this.bB.dispose();
                            RepeatedFieldBuilder repeatedFieldBuilder = null;
                            this.bB = null;
                            this.bw = crash.bw;
                            this.aA &= -129;
                            if (Crash.alwaysUseFieldBuilders) {
                                repeatedFieldBuilder = an();
                            }
                            this.bB = repeatedFieldBuilder;
                        } else {
                            this.bB.addAllMessages(crash.bw);
                        }
                    }
                    if (crash.hasVersionCode()) {
                        setVersionCode(crash.getVersionCode());
                    }
                    if (crash.hasVersionName()) {
                        this.aA |= 512;
                        this.aJ = crash.aJ;
                        onChanged();
                    }
                    if (crash.hasUserId()) {
                        this.aA |= 1024;
                        this.aH = crash.aH;
                        onChanged();
                    }
                    if (crash.hasDeviceName()) {
                        this.aA |= 2048;
                        this.aP = crash.aP;
                        onChanged();
                    }
                    if (crash.hasPlatform()) {
                        this.aA |= 4096;
                        this.aM = crash.aM;
                        onChanged();
                    }
                    if (crash.hasMemoryClass()) {
                        this.aA |= 8192;
                        this.bx = crash.bx;
                        onChanged();
                    }
                    if (crash.hasLargeMemoryClass()) {
                        this.aA |= 16384;
                        this.by = crash.by;
                        onChanged();
                    }
                    if (crash.hasCrashNum()) {
                        setCrashNum(crash.getCrashNum());
                    }
                    if (crash.hasCrashMd5()) {
                        this.aA |= 65536;
                        this.bA = crash.bA;
                        onChanged();
                    }
                    if (crash.hasPackageName()) {
                        this.aA |= 131072;
                        this.bh = crash.bh;
                        onChanged();
                    }
                    if (crash.hasLogVersionApp()) {
                        this.aA |= 262144;
                        this.bj = crash.bj;
                        onChanged();
                    }
                    mergeUnknownFields(crash.getUnknownFields());
                    return this;
                }

                public Builder removeCrashMap(int i) {
                    if (this.bB == null) {
                        am();
                        this.bw.remove(i);
                        onChanged();
                    } else {
                        this.bB.remove(i);
                    }
                    return this;
                }

                public Builder setAppChannel(String str) {
                    if (str != null) {
                        this.aA |= 4;
                        this.aE = str;
                        onChanged();
                        return this;
                    }
                    throw new NullPointerException();
                }

                public Builder setAppChannelBytes(ByteString byteString) {
                    if (byteString != null) {
                        this.aA |= 4;
                        this.aE = byteString;
                        onChanged();
                        return this;
                    }
                    throw new NullPointerException();
                }

                public Builder setAppKey(String str) {
                    if (str != null) {
                        this.aA |= 2;
                        this.aD = str;
                        onChanged();
                        return this;
                    }
                    throw new NullPointerException();
                }

                public Builder setAppKeyBytes(ByteString byteString) {
                    if (byteString != null) {
                        this.aA |= 2;
                        this.aD = byteString;
                        onChanged();
                        return this;
                    }
                    throw new NullPointerException();
                }

                public Builder setCrashMap(int i, ProtoMap.Builder builder) {
                    if (this.bB == null) {
                        am();
                        this.bw.set(i, builder.build());
                        onChanged();
                    } else {
                        this.bB.setMessage(i, builder.build());
                    }
                    return this;
                }

                public Builder setCrashMap(int i, ProtoMap protoMap) {
                    if (this.bB != null) {
                        this.bB.setMessage(i, protoMap);
                    } else if (protoMap != null) {
                        am();
                        this.bw.set(i, protoMap);
                        onChanged();
                    } else {
                        throw new NullPointerException();
                    }
                    return this;
                }

                public Builder setCrashMd5(String str) {
                    if (str != null) {
                        this.aA |= 65536;
                        this.bA = str;
                        onChanged();
                        return this;
                    }
                    throw new NullPointerException();
                }

                public Builder setCrashMd5Bytes(ByteString byteString) {
                    if (byteString != null) {
                        this.aA |= 65536;
                        this.bA = byteString;
                        onChanged();
                        return this;
                    }
                    throw new NullPointerException();
                }

                public Builder setCrashNum(int i) {
                    this.aA |= 32768;
                    this.bz = i;
                    onChanged();
                    return this;
                }

                public Builder setDeviceId(String str) {
                    if (str != null) {
                        this.aA |= 32;
                        this.aG = str;
                        onChanged();
                        return this;
                    }
                    throw new NullPointerException();
                }

                public Builder setDeviceIdBytes(ByteString byteString) {
                    if (byteString != null) {
                        this.aA |= 32;
                        this.aG = byteString;
                        onChanged();
                        return this;
                    }
                    throw new NullPointerException();
                }

                public Builder setDeviceName(String str) {
                    if (str != null) {
                        this.aA |= 2048;
                        this.aP = str;
                        onChanged();
                        return this;
                    }
                    throw new NullPointerException();
                }

                public Builder setDeviceNameBytes(ByteString byteString) {
                    if (byteString != null) {
                        this.aA |= 2048;
                        this.aP = byteString;
                        onChanged();
                        return this;
                    }
                    throw new NullPointerException();
                }

                public Builder setLargeMemoryClass(String str) {
                    if (str != null) {
                        this.aA |= 16384;
                        this.by = str;
                        onChanged();
                        return this;
                    }
                    throw new NullPointerException();
                }

                public Builder setLargeMemoryClassBytes(ByteString byteString) {
                    if (byteString != null) {
                        this.aA |= 16384;
                        this.by = byteString;
                        onChanged();
                        return this;
                    }
                    throw new NullPointerException();
                }

                public Builder setLogVersionApp(String str) {
                    if (str != null) {
                        this.aA |= 262144;
                        this.bj = str;
                        onChanged();
                        return this;
                    }
                    throw new NullPointerException();
                }

                public Builder setLogVersionAppBytes(ByteString byteString) {
                    if (byteString != null) {
                        this.aA |= 262144;
                        this.bj = byteString;
                        onChanged();
                        return this;
                    }
                    throw new NullPointerException();
                }

                public Builder setMemoryClass(String str) {
                    if (str != null) {
                        this.aA |= 8192;
                        this.bx = str;
                        onChanged();
                        return this;
                    }
                    throw new NullPointerException();
                }

                public Builder setMemoryClassBytes(ByteString byteString) {
                    if (byteString != null) {
                        this.aA |= 8192;
                        this.bx = byteString;
                        onChanged();
                        return this;
                    }
                    throw new NullPointerException();
                }

                public Builder setPackageName(String str) {
                    if (str != null) {
                        this.aA |= 131072;
                        this.bh = str;
                        onChanged();
                        return this;
                    }
                    throw new NullPointerException();
                }

                public Builder setPackageNameBytes(ByteString byteString) {
                    if (byteString != null) {
                        this.aA |= 131072;
                        this.bh = byteString;
                        onChanged();
                        return this;
                    }
                    throw new NullPointerException();
                }

                public Builder setPlatform(String str) {
                    if (str != null) {
                        this.aA |= 4096;
                        this.aM = str;
                        onChanged();
                        return this;
                    }
                    throw new NullPointerException();
                }

                public Builder setPlatformBytes(ByteString byteString) {
                    if (byteString != null) {
                        this.aA |= 4096;
                        this.aM = byteString;
                        onChanged();
                        return this;
                    }
                    throw new NullPointerException();
                }

                public Builder setSessionId(String str) {
                    if (str != null) {
                        this.aA |= 8;
                        this.aF = str;
                        onChanged();
                        return this;
                    }
                    throw new NullPointerException();
                }

                public Builder setSessionIdBytes(ByteString byteString) {
                    if (byteString != null) {
                        this.aA |= 8;
                        this.aF = byteString;
                        onChanged();
                        return this;
                    }
                    throw new NullPointerException();
                }

                public Builder setThrowable(String str) {
                    if (str != null) {
                        this.aA |= 64;
                        this.bv = str;
                        onChanged();
                        return this;
                    }
                    throw new NullPointerException();
                }

                public Builder setThrowableBytes(ByteString byteString) {
                    if (byteString != null) {
                        this.aA |= 64;
                        this.bv = byteString;
                        onChanged();
                        return this;
                    }
                    throw new NullPointerException();
                }

                public Builder setTime(long j) {
                    this.aA |= 16;
                    this.bb = j;
                    onChanged();
                    return this;
                }

                public Builder setType(String str) {
                    if (str != null) {
                        this.aA |= 1;
                        this.aC = str;
                        onChanged();
                        return this;
                    }
                    throw new NullPointerException();
                }

                public Builder setTypeBytes(ByteString byteString) {
                    if (byteString != null) {
                        this.aA |= 1;
                        this.aC = byteString;
                        onChanged();
                        return this;
                    }
                    throw new NullPointerException();
                }

                public Builder setUserId(String str) {
                    if (str != null) {
                        this.aA |= 1024;
                        this.aH = str;
                        onChanged();
                        return this;
                    }
                    throw new NullPointerException();
                }

                public Builder setUserIdBytes(ByteString byteString) {
                    if (byteString != null) {
                        this.aA |= 1024;
                        this.aH = byteString;
                        onChanged();
                        return this;
                    }
                    throw new NullPointerException();
                }

                public Builder setVersionCode(int i) {
                    this.aA |= 256;
                    this.aI = i;
                    onChanged();
                    return this;
                }

                public Builder setVersionName(String str) {
                    if (str != null) {
                        this.aA |= 512;
                        this.aJ = str;
                        onChanged();
                        return this;
                    }
                    throw new NullPointerException();
                }

                public Builder setVersionNameBytes(ByteString byteString) {
                    if (byteString != null) {
                        this.aA |= 512;
                        this.aJ = byteString;
                        onChanged();
                        return this;
                    }
                    throw new NullPointerException();
                }
            }

            static {
                bu.P();
            }

            private Crash(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) {
                this.ax = -1;
                this.ay = -1;
                P();
                UnknownFieldSet.Builder newBuilder = UnknownFieldSet.newBuilder();
                boolean z = false;
                boolean z2 = false;
                while (!z) {
                    try {
                        int readTag = codedInputStream.readTag();
                        switch (readTag) {
                            case 0:
                                z = true;
                                break;
                            case 10:
                                ByteString readBytes = codedInputStream.readBytes();
                                this.aA = 1 | this.aA;
                                this.aC = readBytes;
                                break;
                            case 18:
                                ByteString readBytes2 = codedInputStream.readBytes();
                                this.aA |= 2;
                                this.aD = readBytes2;
                                break;
                            case 26:
                                ByteString readBytes3 = codedInputStream.readBytes();
                                this.aA |= 4;
                                this.aE = readBytes3;
                                break;
                            case 34:
                                ByteString readBytes4 = codedInputStream.readBytes();
                                this.aA |= 8;
                                this.aF = readBytes4;
                                break;
                            case 40:
                                this.aA |= 16;
                                this.bb = codedInputStream.readInt64();
                                break;
                            case 50:
                                ByteString readBytes5 = codedInputStream.readBytes();
                                this.aA |= 32;
                                this.aG = readBytes5;
                                break;
                            case 58:
                                ByteString readBytes6 = codedInputStream.readBytes();
                                this.aA |= 64;
                                this.bv = readBytes6;
                                break;
                            case 66:
                                if (!(z2 & true)) {
                                    this.bw = new ArrayList();
                                    z2 |= true;
                                }
                                this.bw.add(codedInputStream.readMessage(ProtoMap.PARSER, extensionRegistryLite));
                                break;
                            case 72:
                                this.aA |= 128;
                                this.aI = codedInputStream.readInt32();
                                break;
                            case 82:
                                ByteString readBytes7 = codedInputStream.readBytes();
                                this.aA |= 256;
                                this.aJ = readBytes7;
                                break;
                            case 90:
                                ByteString readBytes8 = codedInputStream.readBytes();
                                this.aA |= 512;
                                this.aH = readBytes8;
                                break;
                            case 98:
                                ByteString readBytes9 = codedInputStream.readBytes();
                                this.aA |= 1024;
                                this.aP = readBytes9;
                                break;
                            case 106:
                                ByteString readBytes10 = codedInputStream.readBytes();
                                this.aA |= 2048;
                                this.aM = readBytes10;
                                break;
                            case 114:
                                ByteString readBytes11 = codedInputStream.readBytes();
                                this.aA |= 4096;
                                this.bx = readBytes11;
                                break;
                            case 122:
                                ByteString readBytes12 = codedInputStream.readBytes();
                                this.aA |= 8192;
                                this.by = readBytes12;
                                break;
                            case 128:
                                this.aA |= 16384;
                                this.bz = codedInputStream.readInt32();
                                break;
                            case 138:
                                ByteString readBytes13 = codedInputStream.readBytes();
                                this.aA |= 32768;
                                this.bA = readBytes13;
                                break;
                            case 146:
                                ByteString readBytes14 = codedInputStream.readBytes();
                                this.aA |= 65536;
                                this.bh = readBytes14;
                                break;
                            case 154:
                                ByteString readBytes15 = codedInputStream.readBytes();
                                this.aA |= 131072;
                                this.bj = readBytes15;
                                break;
                            default:
                                if (parseUnknownField(codedInputStream, newBuilder, extensionRegistryLite, readTag)) {
                                    break;
                                }
                                z = true;
                                break;
                        }
                    } catch (InvalidProtocolBufferException e) {
                        throw e.setUnfinishedMessage(this);
                    } catch (IOException e2) {
                        throw new InvalidProtocolBufferException(e2.getMessage()).setUnfinishedMessage(this);
                    } catch (Throwable th) {
                        if (z2 & true) {
                            this.bw = Collections.unmodifiableList(this.bw);
                        }
                        this.aw = newBuilder.build();
                        makeExtensionsImmutable();
                        throw th;
                    }
                }
                if (z2 & true) {
                    this.bw = Collections.unmodifiableList(this.bw);
                }
                this.aw = newBuilder.build();
                makeExtensionsImmutable();
            }

            /* synthetic */ Crash(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite, a aVar) {
                this(codedInputStream, extensionRegistryLite);
            }

            private Crash(GeneratedMessage.Builder builder) {
                super(builder);
                this.ax = -1;
                this.ay = -1;
                this.aw = builder.getUnknownFields();
            }

            /* synthetic */ Crash(GeneratedMessage.Builder builder, a aVar) {
                this(builder);
            }

            private Crash(boolean z) {
                this.ax = -1;
                this.ay = -1;
                this.aw = UnknownFieldSet.getDefaultInstance();
            }

            private void P() {
                this.aC = "";
                this.aD = "";
                this.aE = "";
                this.aF = "";
                this.bb = 0;
                this.aG = "";
                this.bv = "";
                this.bw = Collections.emptyList();
                this.aI = 0;
                this.aJ = "";
                this.aH = "";
                this.aP = "";
                this.aM = "";
                this.bx = "";
                this.by = "";
                this.bz = 0;
                this.bA = "";
                this.bh = "";
                this.bj = "";
            }

            public static Crash getDefaultInstance() {
                return bu;
            }

            public static final Descriptors.Descriptor getDescriptor() {
                return ProtoMsg.ak;
            }

            public static Builder newBuilder() {
                return Builder.al();
            }

            public static Builder newBuilder(Crash crash) {
                return newBuilder().mergeFrom(crash);
            }

            public static Crash parseDelimitedFrom(InputStream inputStream) {
                return (Crash) PARSER.parseDelimitedFrom(inputStream);
            }

            public static Crash parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) {
                return (Crash) PARSER.parseDelimitedFrom(inputStream, extensionRegistryLite);
            }

            public static Crash parseFrom(ByteString byteString) {
                return (Crash) PARSER.parseFrom(byteString);
            }

            public static Crash parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) {
                return (Crash) PARSER.parseFrom(byteString, extensionRegistryLite);
            }

            public static Crash parseFrom(CodedInputStream codedInputStream) {
                return (Crash) PARSER.parseFrom(codedInputStream);
            }

            public static Crash parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) {
                return (Crash) PARSER.parseFrom(codedInputStream, extensionRegistryLite);
            }

            public static Crash parseFrom(InputStream inputStream) {
                return (Crash) PARSER.parseFrom(inputStream);
            }

            public static Crash parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) {
                return (Crash) PARSER.parseFrom(inputStream, extensionRegistryLite);
            }

            public static Crash parseFrom(byte[] bArr) {
                return (Crash) PARSER.parseFrom(bArr);
            }

            public static Crash parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) {
                return (Crash) PARSER.parseFrom(bArr, extensionRegistryLite);
            }

            public String getAppChannel() {
                Object obj = this.aE;
                if (obj instanceof String) {
                    return (String) obj;
                }
                ByteString byteString = (ByteString) obj;
                String stringUtf8 = byteString.toStringUtf8();
                if (byteString.isValidUtf8()) {
                    this.aE = stringUtf8;
                }
                return stringUtf8;
            }

            public ByteString getAppChannelBytes() {
                Object obj = this.aE;
                if (!(obj instanceof String)) {
                    return (ByteString) obj;
                }
                ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                this.aE = copyFromUtf8;
                return copyFromUtf8;
            }

            public String getAppKey() {
                Object obj = this.aD;
                if (obj instanceof String) {
                    return (String) obj;
                }
                ByteString byteString = (ByteString) obj;
                String stringUtf8 = byteString.toStringUtf8();
                if (byteString.isValidUtf8()) {
                    this.aD = stringUtf8;
                }
                return stringUtf8;
            }

            public ByteString getAppKeyBytes() {
                Object obj = this.aD;
                if (!(obj instanceof String)) {
                    return (ByteString) obj;
                }
                ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                this.aD = copyFromUtf8;
                return copyFromUtf8;
            }

            public ProtoMap getCrashMap(int i) {
                return (ProtoMap) this.bw.get(i);
            }

            public int getCrashMapCount() {
                return this.bw.size();
            }

            public List getCrashMapList() {
                return this.bw;
            }

            public ProtoMapOrBuilder getCrashMapOrBuilder(int i) {
                return (ProtoMapOrBuilder) this.bw.get(i);
            }

            public List getCrashMapOrBuilderList() {
                return this.bw;
            }

            public String getCrashMd5() {
                Object obj = this.bA;
                if (obj instanceof String) {
                    return (String) obj;
                }
                ByteString byteString = (ByteString) obj;
                String stringUtf8 = byteString.toStringUtf8();
                if (byteString.isValidUtf8()) {
                    this.bA = stringUtf8;
                }
                return stringUtf8;
            }

            public ByteString getCrashMd5Bytes() {
                Object obj = this.bA;
                if (!(obj instanceof String)) {
                    return (ByteString) obj;
                }
                ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                this.bA = copyFromUtf8;
                return copyFromUtf8;
            }

            public int getCrashNum() {
                return this.bz;
            }

            public Crash getDefaultInstanceForType() {
                return bu;
            }

            public String getDeviceId() {
                Object obj = this.aG;
                if (obj instanceof String) {
                    return (String) obj;
                }
                ByteString byteString = (ByteString) obj;
                String stringUtf8 = byteString.toStringUtf8();
                if (byteString.isValidUtf8()) {
                    this.aG = stringUtf8;
                }
                return stringUtf8;
            }

            public ByteString getDeviceIdBytes() {
                Object obj = this.aG;
                if (!(obj instanceof String)) {
                    return (ByteString) obj;
                }
                ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                this.aG = copyFromUtf8;
                return copyFromUtf8;
            }

            public String getDeviceName() {
                Object obj = this.aP;
                if (obj instanceof String) {
                    return (String) obj;
                }
                ByteString byteString = (ByteString) obj;
                String stringUtf8 = byteString.toStringUtf8();
                if (byteString.isValidUtf8()) {
                    this.aP = stringUtf8;
                }
                return stringUtf8;
            }

            public ByteString getDeviceNameBytes() {
                Object obj = this.aP;
                if (!(obj instanceof String)) {
                    return (ByteString) obj;
                }
                ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                this.aP = copyFromUtf8;
                return copyFromUtf8;
            }

            public String getLargeMemoryClass() {
                Object obj = this.by;
                if (obj instanceof String) {
                    return (String) obj;
                }
                ByteString byteString = (ByteString) obj;
                String stringUtf8 = byteString.toStringUtf8();
                if (byteString.isValidUtf8()) {
                    this.by = stringUtf8;
                }
                return stringUtf8;
            }

            public ByteString getLargeMemoryClassBytes() {
                Object obj = this.by;
                if (!(obj instanceof String)) {
                    return (ByteString) obj;
                }
                ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                this.by = copyFromUtf8;
                return copyFromUtf8;
            }

            public String getLogVersionApp() {
                Object obj = this.bj;
                if (obj instanceof String) {
                    return (String) obj;
                }
                ByteString byteString = (ByteString) obj;
                String stringUtf8 = byteString.toStringUtf8();
                if (byteString.isValidUtf8()) {
                    this.bj = stringUtf8;
                }
                return stringUtf8;
            }

            public ByteString getLogVersionAppBytes() {
                Object obj = this.bj;
                if (!(obj instanceof String)) {
                    return (ByteString) obj;
                }
                ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                this.bj = copyFromUtf8;
                return copyFromUtf8;
            }

            public String getMemoryClass() {
                Object obj = this.bx;
                if (obj instanceof String) {
                    return (String) obj;
                }
                ByteString byteString = (ByteString) obj;
                String stringUtf8 = byteString.toStringUtf8();
                if (byteString.isValidUtf8()) {
                    this.bx = stringUtf8;
                }
                return stringUtf8;
            }

            public ByteString getMemoryClassBytes() {
                Object obj = this.bx;
                if (!(obj instanceof String)) {
                    return (ByteString) obj;
                }
                ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                this.bx = copyFromUtf8;
                return copyFromUtf8;
            }

            public String getPackageName() {
                Object obj = this.bh;
                if (obj instanceof String) {
                    return (String) obj;
                }
                ByteString byteString = (ByteString) obj;
                String stringUtf8 = byteString.toStringUtf8();
                if (byteString.isValidUtf8()) {
                    this.bh = stringUtf8;
                }
                return stringUtf8;
            }

            public ByteString getPackageNameBytes() {
                Object obj = this.bh;
                if (!(obj instanceof String)) {
                    return (ByteString) obj;
                }
                ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                this.bh = copyFromUtf8;
                return copyFromUtf8;
            }

            public Parser getParserForType() {
                return PARSER;
            }

            public String getPlatform() {
                Object obj = this.aM;
                if (obj instanceof String) {
                    return (String) obj;
                }
                ByteString byteString = (ByteString) obj;
                String stringUtf8 = byteString.toStringUtf8();
                if (byteString.isValidUtf8()) {
                    this.aM = stringUtf8;
                }
                return stringUtf8;
            }

            public ByteString getPlatformBytes() {
                Object obj = this.aM;
                if (!(obj instanceof String)) {
                    return (ByteString) obj;
                }
                ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                this.aM = copyFromUtf8;
                return copyFromUtf8;
            }

            public int getSerializedSize() {
                int i = this.ay;
                if (i != -1) {
                    return i;
                }
                int computeBytesSize = (this.aA & 1) == 1 ? CodedOutputStream.computeBytesSize(1, getTypeBytes()) + 0 : 0;
                if ((this.aA & 2) == 2) {
                    computeBytesSize += CodedOutputStream.computeBytesSize(2, getAppKeyBytes());
                }
                if ((this.aA & 4) == 4) {
                    computeBytesSize += CodedOutputStream.computeBytesSize(3, getAppChannelBytes());
                }
                if ((this.aA & 8) == 8) {
                    computeBytesSize += CodedOutputStream.computeBytesSize(4, getSessionIdBytes());
                }
                if ((this.aA & 16) == 16) {
                    computeBytesSize += CodedOutputStream.computeInt64Size(5, this.bb);
                }
                if ((this.aA & 32) == 32) {
                    computeBytesSize += CodedOutputStream.computeBytesSize(6, getDeviceIdBytes());
                }
                if ((this.aA & 64) == 64) {
                    computeBytesSize += CodedOutputStream.computeBytesSize(7, getThrowableBytes());
                }
                for (int i2 = 0; i2 < this.bw.size(); i2++) {
                    computeBytesSize += CodedOutputStream.computeMessageSize(8, (MessageLite) this.bw.get(i2));
                }
                if ((this.aA & 128) == 128) {
                    computeBytesSize += CodedOutputStream.computeInt32Size(9, this.aI);
                }
                if ((this.aA & 256) == 256) {
                    computeBytesSize += CodedOutputStream.computeBytesSize(10, getVersionNameBytes());
                }
                if ((this.aA & 512) == 512) {
                    computeBytesSize += CodedOutputStream.computeBytesSize(11, getUserIdBytes());
                }
                if ((this.aA & 1024) == 1024) {
                    computeBytesSize += CodedOutputStream.computeBytesSize(12, getDeviceNameBytes());
                }
                if ((this.aA & 2048) == 2048) {
                    computeBytesSize += CodedOutputStream.computeBytesSize(13, getPlatformBytes());
                }
                if ((this.aA & 4096) == 4096) {
                    computeBytesSize += CodedOutputStream.computeBytesSize(14, getMemoryClassBytes());
                }
                if ((this.aA & 8192) == 8192) {
                    computeBytesSize += CodedOutputStream.computeBytesSize(15, getLargeMemoryClassBytes());
                }
                if ((this.aA & 16384) == 16384) {
                    computeBytesSize += CodedOutputStream.computeInt32Size(16, this.bz);
                }
                if ((this.aA & 32768) == 32768) {
                    computeBytesSize += CodedOutputStream.computeBytesSize(17, getCrashMd5Bytes());
                }
                if ((this.aA & 65536) == 65536) {
                    computeBytesSize += CodedOutputStream.computeBytesSize(18, getPackageNameBytes());
                }
                if ((this.aA & 131072) == 131072) {
                    computeBytesSize += CodedOutputStream.computeBytesSize(19, getLogVersionAppBytes());
                }
                int serializedSize = computeBytesSize + getUnknownFields().getSerializedSize();
                this.ay = serializedSize;
                return serializedSize;
            }

            public String getSessionId() {
                Object obj = this.aF;
                if (obj instanceof String) {
                    return (String) obj;
                }
                ByteString byteString = (ByteString) obj;
                String stringUtf8 = byteString.toStringUtf8();
                if (byteString.isValidUtf8()) {
                    this.aF = stringUtf8;
                }
                return stringUtf8;
            }

            public ByteString getSessionIdBytes() {
                Object obj = this.aF;
                if (!(obj instanceof String)) {
                    return (ByteString) obj;
                }
                ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                this.aF = copyFromUtf8;
                return copyFromUtf8;
            }

            public String getThrowable() {
                Object obj = this.bv;
                if (obj instanceof String) {
                    return (String) obj;
                }
                ByteString byteString = (ByteString) obj;
                String stringUtf8 = byteString.toStringUtf8();
                if (byteString.isValidUtf8()) {
                    this.bv = stringUtf8;
                }
                return stringUtf8;
            }

            public ByteString getThrowableBytes() {
                Object obj = this.bv;
                if (!(obj instanceof String)) {
                    return (ByteString) obj;
                }
                ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                this.bv = copyFromUtf8;
                return copyFromUtf8;
            }

            public long getTime() {
                return this.bb;
            }

            public String getType() {
                Object obj = this.aC;
                if (obj instanceof String) {
                    return (String) obj;
                }
                ByteString byteString = (ByteString) obj;
                String stringUtf8 = byteString.toStringUtf8();
                if (byteString.isValidUtf8()) {
                    this.aC = stringUtf8;
                }
                return stringUtf8;
            }

            public ByteString getTypeBytes() {
                Object obj = this.aC;
                if (!(obj instanceof String)) {
                    return (ByteString) obj;
                }
                ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                this.aC = copyFromUtf8;
                return copyFromUtf8;
            }

            public final UnknownFieldSet getUnknownFields() {
                return this.aw;
            }

            public String getUserId() {
                Object obj = this.aH;
                if (obj instanceof String) {
                    return (String) obj;
                }
                ByteString byteString = (ByteString) obj;
                String stringUtf8 = byteString.toStringUtf8();
                if (byteString.isValidUtf8()) {
                    this.aH = stringUtf8;
                }
                return stringUtf8;
            }

            public ByteString getUserIdBytes() {
                Object obj = this.aH;
                if (!(obj instanceof String)) {
                    return (ByteString) obj;
                }
                ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                this.aH = copyFromUtf8;
                return copyFromUtf8;
            }

            public int getVersionCode() {
                return this.aI;
            }

            public String getVersionName() {
                Object obj = this.aJ;
                if (obj instanceof String) {
                    return (String) obj;
                }
                ByteString byteString = (ByteString) obj;
                String stringUtf8 = byteString.toStringUtf8();
                if (byteString.isValidUtf8()) {
                    this.aJ = stringUtf8;
                }
                return stringUtf8;
            }

            public ByteString getVersionNameBytes() {
                Object obj = this.aJ;
                if (!(obj instanceof String)) {
                    return (ByteString) obj;
                }
                ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                this.aJ = copyFromUtf8;
                return copyFromUtf8;
            }

            public boolean hasAppChannel() {
                return (this.aA & 4) == 4;
            }

            public boolean hasAppKey() {
                return (this.aA & 2) == 2;
            }

            public boolean hasCrashMd5() {
                return (this.aA & 32768) == 32768;
            }

            public boolean hasCrashNum() {
                return (this.aA & 16384) == 16384;
            }

            public boolean hasDeviceId() {
                return (this.aA & 32) == 32;
            }

            public boolean hasDeviceName() {
                return (this.aA & 1024) == 1024;
            }

            public boolean hasLargeMemoryClass() {
                return (this.aA & 8192) == 8192;
            }

            public boolean hasLogVersionApp() {
                return (this.aA & 131072) == 131072;
            }

            public boolean hasMemoryClass() {
                return (this.aA & 4096) == 4096;
            }

            public boolean hasPackageName() {
                return (this.aA & 65536) == 65536;
            }

            public boolean hasPlatform() {
                return (this.aA & 2048) == 2048;
            }

            public boolean hasSessionId() {
                return (this.aA & 8) == 8;
            }

            public boolean hasThrowable() {
                return (this.aA & 64) == 64;
            }

            public boolean hasTime() {
                return (this.aA & 16) == 16;
            }

            public boolean hasType() {
                return (this.aA & 1) == 1;
            }

            public boolean hasUserId() {
                return (this.aA & 512) == 512;
            }

            public boolean hasVersionCode() {
                return (this.aA & 128) == 128;
            }

            public boolean hasVersionName() {
                return (this.aA & 256) == 256;
            }

            /* access modifiers changed from: protected */
            public GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
                return ProtoMsg.al.ensureFieldAccessorsInitialized(Crash.class, Builder.class);
            }

            public final boolean isInitialized() {
                byte b = this.ax;
                if (b == 1) {
                    return true;
                }
                if (b == 0) {
                    return false;
                }
                if (!hasType()) {
                    this.ax = 0;
                    return false;
                }
                for (int i = 0; i < getCrashMapCount(); i++) {
                    if (!getCrashMap(i).isInitialized()) {
                        this.ax = 0;
                        return false;
                    }
                }
                this.ax = 1;
                return true;
            }

            public Builder newBuilderForType() {
                return newBuilder();
            }

            /* access modifiers changed from: protected */
            public Builder newBuilderForType(GeneratedMessage.BuilderParent builderParent) {
                return new Builder(builderParent, (a) null);
            }

            public Builder toBuilder() {
                return newBuilder(this);
            }

            /* access modifiers changed from: protected */
            public Object writeReplace() {
                return super.writeReplace();
            }

            public void writeTo(CodedOutputStream codedOutputStream) {
                getSerializedSize();
                if ((this.aA & 1) == 1) {
                    codedOutputStream.writeBytes(1, getTypeBytes());
                }
                if ((this.aA & 2) == 2) {
                    codedOutputStream.writeBytes(2, getAppKeyBytes());
                }
                if ((this.aA & 4) == 4) {
                    codedOutputStream.writeBytes(3, getAppChannelBytes());
                }
                if ((this.aA & 8) == 8) {
                    codedOutputStream.writeBytes(4, getSessionIdBytes());
                }
                if ((this.aA & 16) == 16) {
                    codedOutputStream.writeInt64(5, this.bb);
                }
                if ((this.aA & 32) == 32) {
                    codedOutputStream.writeBytes(6, getDeviceIdBytes());
                }
                if ((this.aA & 64) == 64) {
                    codedOutputStream.writeBytes(7, getThrowableBytes());
                }
                for (int i = 0; i < this.bw.size(); i++) {
                    codedOutputStream.writeMessage(8, (MessageLite) this.bw.get(i));
                }
                if ((this.aA & 128) == 128) {
                    codedOutputStream.writeInt32(9, this.aI);
                }
                if ((this.aA & 256) == 256) {
                    codedOutputStream.writeBytes(10, getVersionNameBytes());
                }
                if ((this.aA & 512) == 512) {
                    codedOutputStream.writeBytes(11, getUserIdBytes());
                }
                if ((this.aA & 1024) == 1024) {
                    codedOutputStream.writeBytes(12, getDeviceNameBytes());
                }
                if ((this.aA & 2048) == 2048) {
                    codedOutputStream.writeBytes(13, getPlatformBytes());
                }
                if ((this.aA & 4096) == 4096) {
                    codedOutputStream.writeBytes(14, getMemoryClassBytes());
                }
                if ((this.aA & 8192) == 8192) {
                    codedOutputStream.writeBytes(15, getLargeMemoryClassBytes());
                }
                if ((this.aA & 16384) == 16384) {
                    codedOutputStream.writeInt32(16, this.bz);
                }
                if ((this.aA & 32768) == 32768) {
                    codedOutputStream.writeBytes(17, getCrashMd5Bytes());
                }
                if ((this.aA & 65536) == 65536) {
                    codedOutputStream.writeBytes(18, getPackageNameBytes());
                }
                if ((this.aA & 131072) == 131072) {
                    codedOutputStream.writeBytes(19, getLogVersionAppBytes());
                }
                getUnknownFields().writeTo(codedOutputStream);
            }
        }

        public final class CrashMsg extends GeneratedMessage implements CrashMsgOrBuilder {
            public static final int CRASH_FIELD_NUMBER = 1;
            public static Parser PARSER = new g();
            private static final CrashMsg bC = new CrashMsg(true);
            private final UnknownFieldSet aw;
            private byte ax;
            private int ay;
            /* access modifiers changed from: private */
            public List bD;

            public final class Builder extends GeneratedMessage.Builder implements CrashMsgOrBuilder {
                private int aA;
                private List bD;
                private RepeatedFieldBuilder bE;

                private Builder() {
                    this.bD = Collections.emptyList();
                    R();
                }

                private Builder(GeneratedMessage.BuilderParent builderParent) {
                    super(builderParent);
                    this.bD = Collections.emptyList();
                    R();
                }

                /* synthetic */ Builder(GeneratedMessage.BuilderParent builderParent, a aVar) {
                    this(builderParent);
                }

                private void R() {
                    if (CrashMsg.alwaysUseFieldBuilders) {
                        at();
                    }
                }

                /* access modifiers changed from: private */
                public static Builder ar() {
                    return new Builder();
                }

                private void as() {
                    if ((this.aA & 1) != 1) {
                        this.bD = new ArrayList(this.bD);
                        this.aA |= 1;
                    }
                }

                private RepeatedFieldBuilder at() {
                    if (this.bE == null) {
                        List list = this.bD;
                        boolean z = true;
                        if ((this.aA & 1) != 1) {
                            z = false;
                        }
                        this.bE = new RepeatedFieldBuilder(list, z, getParentForChildren(), isClean());
                        this.bD = null;
                    }
                    return this.bE;
                }

                public static final Descriptors.Descriptor getDescriptor() {
                    return ProtoMsg.am;
                }

                public Builder addAllCrash(Iterable iterable) {
                    if (this.bE == null) {
                        as();
                        AbstractMessageLite.Builder.addAll(iterable, this.bD);
                        onChanged();
                    } else {
                        this.bE.addAllMessages(iterable);
                    }
                    return this;
                }

                public Builder addCrash(int i, Crash.Builder builder) {
                    if (this.bE == null) {
                        as();
                        this.bD.add(i, builder.build());
                        onChanged();
                    } else {
                        this.bE.addMessage(i, builder.build());
                    }
                    return this;
                }

                public Builder addCrash(int i, Crash crash) {
                    if (this.bE != null) {
                        this.bE.addMessage(i, crash);
                    } else if (crash != null) {
                        as();
                        this.bD.add(i, crash);
                        onChanged();
                    } else {
                        throw new NullPointerException();
                    }
                    return this;
                }

                public Builder addCrash(Crash.Builder builder) {
                    if (this.bE == null) {
                        as();
                        this.bD.add(builder.build());
                        onChanged();
                    } else {
                        this.bE.addMessage(builder.build());
                    }
                    return this;
                }

                public Builder addCrash(Crash crash) {
                    if (this.bE != null) {
                        this.bE.addMessage(crash);
                    } else if (crash != null) {
                        as();
                        this.bD.add(crash);
                        onChanged();
                    } else {
                        throw new NullPointerException();
                    }
                    return this;
                }

                public Crash.Builder addCrashBuilder() {
                    return (Crash.Builder) at().addBuilder(Crash.getDefaultInstance());
                }

                public Crash.Builder addCrashBuilder(int i) {
                    return (Crash.Builder) at().addBuilder(i, Crash.getDefaultInstance());
                }

                public CrashMsg build() {
                    CrashMsg buildPartial = buildPartial();
                    if (buildPartial.isInitialized()) {
                        return buildPartial;
                    }
                    throw newUninitializedMessageException(buildPartial);
                }

                public CrashMsg buildPartial() {
                    List list;
                    CrashMsg crashMsg = new CrashMsg((GeneratedMessage.Builder) this, (a) null);
                    int i = this.aA;
                    if (this.bE == null) {
                        if ((this.aA & 1) == 1) {
                            this.bD = Collections.unmodifiableList(this.bD);
                            this.aA &= -2;
                        }
                        list = this.bD;
                    } else {
                        list = this.bE.build();
                    }
                    List unused = crashMsg.bD = list;
                    onBuilt();
                    return crashMsg;
                }

                public Builder clear() {
                    super.clear();
                    if (this.bE == null) {
                        this.bD = Collections.emptyList();
                        this.aA &= -2;
                    } else {
                        this.bE.clear();
                    }
                    return this;
                }

                public Builder clearCrash() {
                    if (this.bE == null) {
                        this.bD = Collections.emptyList();
                        this.aA &= -2;
                        onChanged();
                    } else {
                        this.bE.clear();
                    }
                    return this;
                }

                public Builder clone() {
                    return ar().mergeFrom(buildPartial());
                }

                public Crash getCrash(int i) {
                    return (Crash) (this.bE == null ? this.bD.get(i) : this.bE.getMessage(i));
                }

                public Crash.Builder getCrashBuilder(int i) {
                    return (Crash.Builder) at().getBuilder(i);
                }

                public List getCrashBuilderList() {
                    return at().getBuilderList();
                }

                public int getCrashCount() {
                    return this.bE == null ? this.bD.size() : this.bE.getCount();
                }

                public List getCrashList() {
                    return this.bE == null ? Collections.unmodifiableList(this.bD) : this.bE.getMessageList();
                }

                public CrashOrBuilder getCrashOrBuilder(int i) {
                    return (CrashOrBuilder) (this.bE == null ? this.bD.get(i) : this.bE.getMessageOrBuilder(i));
                }

                public List getCrashOrBuilderList() {
                    return this.bE != null ? this.bE.getMessageOrBuilderList() : Collections.unmodifiableList(this.bD);
                }

                public CrashMsg getDefaultInstanceForType() {
                    return CrashMsg.getDefaultInstance();
                }

                public Descriptors.Descriptor getDescriptorForType() {
                    return ProtoMsg.am;
                }

                /* access modifiers changed from: protected */
                public GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
                    return ProtoMsg.an.ensureFieldAccessorsInitialized(CrashMsg.class, Builder.class);
                }

                public final boolean isInitialized() {
                    for (int i = 0; i < getCrashCount(); i++) {
                        if (!getCrash(i).isInitialized()) {
                            return false;
                        }
                    }
                    return true;
                }

                public Builder mergeFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) {
                    CrashMsg crashMsg;
                    CrashMsg crashMsg2 = null;
                    try {
                        CrashMsg crashMsg3 = (CrashMsg) CrashMsg.PARSER.parsePartialFrom(codedInputStream, extensionRegistryLite);
                        if (crashMsg3 != null) {
                            mergeFrom(crashMsg3);
                        }
                        return this;
                    } catch (InvalidProtocolBufferException e) {
                        crashMsg = (CrashMsg) e.getUnfinishedMessage();
                        throw e;
                    } catch (Throwable th) {
                        th = th;
                        crashMsg2 = crashMsg;
                    }
                    if (crashMsg2 != null) {
                        mergeFrom(crashMsg2);
                    }
                    throw th;
                }

                public Builder mergeFrom(Message message) {
                    if (message instanceof CrashMsg) {
                        return mergeFrom((CrashMsg) message);
                    }
                    super.mergeFrom(message);
                    return this;
                }

                public Builder mergeFrom(CrashMsg crashMsg) {
                    if (crashMsg == CrashMsg.getDefaultInstance()) {
                        return this;
                    }
                    if (this.bE == null) {
                        if (!crashMsg.bD.isEmpty()) {
                            if (this.bD.isEmpty()) {
                                this.bD = crashMsg.bD;
                                this.aA &= -2;
                            } else {
                                as();
                                this.bD.addAll(crashMsg.bD);
                            }
                            onChanged();
                        }
                    } else if (!crashMsg.bD.isEmpty()) {
                        if (this.bE.isEmpty()) {
                            this.bE.dispose();
                            RepeatedFieldBuilder repeatedFieldBuilder = null;
                            this.bE = null;
                            this.bD = crashMsg.bD;
                            this.aA &= -2;
                            if (CrashMsg.alwaysUseFieldBuilders) {
                                repeatedFieldBuilder = at();
                            }
                            this.bE = repeatedFieldBuilder;
                        } else {
                            this.bE.addAllMessages(crashMsg.bD);
                        }
                    }
                    mergeUnknownFields(crashMsg.getUnknownFields());
                    return this;
                }

                public Builder removeCrash(int i) {
                    if (this.bE == null) {
                        as();
                        this.bD.remove(i);
                        onChanged();
                    } else {
                        this.bE.remove(i);
                    }
                    return this;
                }

                public Builder setCrash(int i, Crash.Builder builder) {
                    if (this.bE == null) {
                        as();
                        this.bD.set(i, builder.build());
                        onChanged();
                    } else {
                        this.bE.setMessage(i, builder.build());
                    }
                    return this;
                }

                public Builder setCrash(int i, Crash crash) {
                    if (this.bE != null) {
                        this.bE.setMessage(i, crash);
                    } else if (crash != null) {
                        as();
                        this.bD.set(i, crash);
                        onChanged();
                    } else {
                        throw new NullPointerException();
                    }
                    return this;
                }
            }

            static {
                bC.P();
            }

            private CrashMsg(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) {
                this.ax = -1;
                this.ay = -1;
                P();
                UnknownFieldSet.Builder newBuilder = UnknownFieldSet.newBuilder();
                boolean z = false;
                boolean z2 = false;
                while (!z) {
                    try {
                        int readTag = codedInputStream.readTag();
                        if (readTag != 0) {
                            if (readTag == 10) {
                                if (!z2 || !true) {
                                    this.bD = new ArrayList();
                                    z2 |= true;
                                }
                                this.bD.add(codedInputStream.readMessage(Crash.PARSER, extensionRegistryLite));
                            } else if (!parseUnknownField(codedInputStream, newBuilder, extensionRegistryLite, readTag)) {
                            }
                        }
                        z = true;
                    } catch (InvalidProtocolBufferException e) {
                        throw e.setUnfinishedMessage(this);
                    } catch (IOException e2) {
                        throw new InvalidProtocolBufferException(e2.getMessage()).setUnfinishedMessage(this);
                    } catch (Throwable th) {
                        if (z2 && true) {
                            this.bD = Collections.unmodifiableList(this.bD);
                        }
                        this.aw = newBuilder.build();
                        makeExtensionsImmutable();
                        throw th;
                    }
                }
                if (z2 && true) {
                    this.bD = Collections.unmodifiableList(this.bD);
                }
                this.aw = newBuilder.build();
                makeExtensionsImmutable();
            }

            /* synthetic */ CrashMsg(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite, a aVar) {
                this(codedInputStream, extensionRegistryLite);
            }

            private CrashMsg(GeneratedMessage.Builder builder) {
                super(builder);
                this.ax = -1;
                this.ay = -1;
                this.aw = builder.getUnknownFields();
            }

            /* synthetic */ CrashMsg(GeneratedMessage.Builder builder, a aVar) {
                this(builder);
            }

            private CrashMsg(boolean z) {
                this.ax = -1;
                this.ay = -1;
                this.aw = UnknownFieldSet.getDefaultInstance();
            }

            private void P() {
                this.bD = Collections.emptyList();
            }

            public static CrashMsg getDefaultInstance() {
                return bC;
            }

            public static final Descriptors.Descriptor getDescriptor() {
                return ProtoMsg.am;
            }

            public static Builder newBuilder() {
                return Builder.ar();
            }

            public static Builder newBuilder(CrashMsg crashMsg) {
                return newBuilder().mergeFrom(crashMsg);
            }

            public static CrashMsg parseDelimitedFrom(InputStream inputStream) {
                return (CrashMsg) PARSER.parseDelimitedFrom(inputStream);
            }

            public static CrashMsg parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) {
                return (CrashMsg) PARSER.parseDelimitedFrom(inputStream, extensionRegistryLite);
            }

            public static CrashMsg parseFrom(ByteString byteString) {
                return (CrashMsg) PARSER.parseFrom(byteString);
            }

            public static CrashMsg parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) {
                return (CrashMsg) PARSER.parseFrom(byteString, extensionRegistryLite);
            }

            public static CrashMsg parseFrom(CodedInputStream codedInputStream) {
                return (CrashMsg) PARSER.parseFrom(codedInputStream);
            }

            public static CrashMsg parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) {
                return (CrashMsg) PARSER.parseFrom(codedInputStream, extensionRegistryLite);
            }

            public static CrashMsg parseFrom(InputStream inputStream) {
                return (CrashMsg) PARSER.parseFrom(inputStream);
            }

            public static CrashMsg parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) {
                return (CrashMsg) PARSER.parseFrom(inputStream, extensionRegistryLite);
            }

            public static CrashMsg parseFrom(byte[] bArr) {
                return (CrashMsg) PARSER.parseFrom(bArr);
            }

            public static CrashMsg parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) {
                return (CrashMsg) PARSER.parseFrom(bArr, extensionRegistryLite);
            }

            public Crash getCrash(int i) {
                return (Crash) this.bD.get(i);
            }

            public int getCrashCount() {
                return this.bD.size();
            }

            public List getCrashList() {
                return this.bD;
            }

            public CrashOrBuilder getCrashOrBuilder(int i) {
                return (CrashOrBuilder) this.bD.get(i);
            }

            public List getCrashOrBuilderList() {
                return this.bD;
            }

            public CrashMsg getDefaultInstanceForType() {
                return bC;
            }

            public Parser getParserForType() {
                return PARSER;
            }

            public int getSerializedSize() {
                int i = this.ay;
                if (i != -1) {
                    return i;
                }
                int i2 = 0;
                for (int i3 = 0; i3 < this.bD.size(); i3++) {
                    i2 += CodedOutputStream.computeMessageSize(1, (MessageLite) this.bD.get(i3));
                }
                int serializedSize = i2 + getUnknownFields().getSerializedSize();
                this.ay = serializedSize;
                return serializedSize;
            }

            public final UnknownFieldSet getUnknownFields() {
                return this.aw;
            }

            /* access modifiers changed from: protected */
            public GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
                return ProtoMsg.an.ensureFieldAccessorsInitialized(CrashMsg.class, Builder.class);
            }

            public final boolean isInitialized() {
                byte b = this.ax;
                if (b == 1) {
                    return true;
                }
                if (b == 0) {
                    return false;
                }
                for (int i = 0; i < getCrashCount(); i++) {
                    if (!getCrash(i).isInitialized()) {
                        this.ax = 0;
                        return false;
                    }
                }
                this.ax = 1;
                return true;
            }

            public Builder newBuilderForType() {
                return newBuilder();
            }

            /* access modifiers changed from: protected */
            public Builder newBuilderForType(GeneratedMessage.BuilderParent builderParent) {
                return new Builder(builderParent, (a) null);
            }

            public Builder toBuilder() {
                return newBuilder(this);
            }

            /* access modifiers changed from: protected */
            public Object writeReplace() {
                return super.writeReplace();
            }

            public void writeTo(CodedOutputStream codedOutputStream) {
                getSerializedSize();
                for (int i = 0; i < this.bD.size(); i++) {
                    codedOutputStream.writeMessage(1, (MessageLite) this.bD.get(i));
                }
                getUnknownFields().writeTo(codedOutputStream);
            }
        }

        public interface CrashMsgOrBuilder extends MessageOrBuilder {
            Crash getCrash(int i);

            int getCrashCount();

            List getCrashList();

            CrashOrBuilder getCrashOrBuilder(int i);

            List getCrashOrBuilderList();
        }

        public interface CrashOrBuilder extends MessageOrBuilder {
            String getAppChannel();

            ByteString getAppChannelBytes();

            String getAppKey();

            ByteString getAppKeyBytes();

            ProtoMap getCrashMap(int i);

            int getCrashMapCount();

            List getCrashMapList();

            ProtoMapOrBuilder getCrashMapOrBuilder(int i);

            List getCrashMapOrBuilderList();

            String getCrashMd5();

            ByteString getCrashMd5Bytes();

            int getCrashNum();

            String getDeviceId();

            ByteString getDeviceIdBytes();

            String getDeviceName();

            ByteString getDeviceNameBytes();

            String getLargeMemoryClass();

            ByteString getLargeMemoryClassBytes();

            String getLogVersionApp();

            ByteString getLogVersionAppBytes();

            String getMemoryClass();

            ByteString getMemoryClassBytes();

            String getPackageName();

            ByteString getPackageNameBytes();

            String getPlatform();

            ByteString getPlatformBytes();

            String getSessionId();

            ByteString getSessionIdBytes();

            String getThrowable();

            ByteString getThrowableBytes();

            long getTime();

            String getType();

            ByteString getTypeBytes();

            String getUserId();

            ByteString getUserIdBytes();

            int getVersionCode();

            String getVersionName();

            ByteString getVersionNameBytes();

            boolean hasAppChannel();

            boolean hasAppKey();

            boolean hasCrashMd5();

            boolean hasCrashNum();

            boolean hasDeviceId();

            boolean hasDeviceName();

            boolean hasLargeMemoryClass();

            boolean hasLogVersionApp();

            boolean hasMemoryClass();

            boolean hasPackageName();

            boolean hasPlatform();

            boolean hasSessionId();

            boolean hasThrowable();

            boolean hasTime();

            boolean hasType();

            boolean hasUserId();

            boolean hasVersionCode();

            boolean hasVersionName();
        }

        public final class ErrorMsg extends GeneratedMessage implements ErrorMsgOrBuilder {
            public static final int ERROR_FIELD_NUMBER = 1;
            public static Parser PARSER = new h();
            private static final ErrorMsg bF = new ErrorMsg(true);
            private final UnknownFieldSet aw;
            private byte ax;
            private int ay;
            /* access modifiers changed from: private */
            public List bG;

            public final class Builder extends GeneratedMessage.Builder implements ErrorMsgOrBuilder {
                private int aA;
                private List bG;
                private RepeatedFieldBuilder bH;

                private Builder() {
                    this.bG = Collections.emptyList();
                    R();
                }

                private Builder(GeneratedMessage.BuilderParent builderParent) {
                    super(builderParent);
                    this.bG = Collections.emptyList();
                    R();
                }

                /* synthetic */ Builder(GeneratedMessage.BuilderParent builderParent, a aVar) {
                    this(builderParent);
                }

                private void R() {
                    if (ErrorMsg.alwaysUseFieldBuilders) {
                        az();
                    }
                }

                /* access modifiers changed from: private */
                public static Builder ax() {
                    return new Builder();
                }

                private void ay() {
                    if ((this.aA & 1) != 1) {
                        this.bG = new ArrayList(this.bG);
                        this.aA |= 1;
                    }
                }

                private RepeatedFieldBuilder az() {
                    if (this.bH == null) {
                        List list = this.bG;
                        boolean z = true;
                        if ((this.aA & 1) != 1) {
                            z = false;
                        }
                        this.bH = new RepeatedFieldBuilder(list, z, getParentForChildren(), isClean());
                        this.bG = null;
                    }
                    return this.bH;
                }

                public static final Descriptors.Descriptor getDescriptor() {
                    return ProtoMsg.ai;
                }

                public Builder addAllError(Iterable iterable) {
                    if (this.bH == null) {
                        ay();
                        AbstractMessageLite.Builder.addAll(iterable, this.bG);
                        onChanged();
                    } else {
                        this.bH.addAllMessages(iterable);
                    }
                    return this;
                }

                public Builder addError(int i, ProtoError.Builder builder) {
                    if (this.bH == null) {
                        ay();
                        this.bG.add(i, builder.build());
                        onChanged();
                    } else {
                        this.bH.addMessage(i, builder.build());
                    }
                    return this;
                }

                public Builder addError(int i, ProtoError protoError) {
                    if (this.bH != null) {
                        this.bH.addMessage(i, protoError);
                    } else if (protoError != null) {
                        ay();
                        this.bG.add(i, protoError);
                        onChanged();
                    } else {
                        throw new NullPointerException();
                    }
                    return this;
                }

                public Builder addError(ProtoError.Builder builder) {
                    if (this.bH == null) {
                        ay();
                        this.bG.add(builder.build());
                        onChanged();
                    } else {
                        this.bH.addMessage(builder.build());
                    }
                    return this;
                }

                public Builder addError(ProtoError protoError) {
                    if (this.bH != null) {
                        this.bH.addMessage(protoError);
                    } else if (protoError != null) {
                        ay();
                        this.bG.add(protoError);
                        onChanged();
                    } else {
                        throw new NullPointerException();
                    }
                    return this;
                }

                public ProtoError.Builder addErrorBuilder() {
                    return (ProtoError.Builder) az().addBuilder(ProtoError.getDefaultInstance());
                }

                public ProtoError.Builder addErrorBuilder(int i) {
                    return (ProtoError.Builder) az().addBuilder(i, ProtoError.getDefaultInstance());
                }

                public ErrorMsg build() {
                    ErrorMsg buildPartial = buildPartial();
                    if (buildPartial.isInitialized()) {
                        return buildPartial;
                    }
                    throw newUninitializedMessageException(buildPartial);
                }

                public ErrorMsg buildPartial() {
                    List list;
                    ErrorMsg errorMsg = new ErrorMsg((GeneratedMessage.Builder) this, (a) null);
                    int i = this.aA;
                    if (this.bH == null) {
                        if ((this.aA & 1) == 1) {
                            this.bG = Collections.unmodifiableList(this.bG);
                            this.aA &= -2;
                        }
                        list = this.bG;
                    } else {
                        list = this.bH.build();
                    }
                    List unused = errorMsg.bG = list;
                    onBuilt();
                    return errorMsg;
                }

                public Builder clear() {
                    super.clear();
                    if (this.bH == null) {
                        this.bG = Collections.emptyList();
                        this.aA &= -2;
                    } else {
                        this.bH.clear();
                    }
                    return this;
                }

                public Builder clearError() {
                    if (this.bH == null) {
                        this.bG = Collections.emptyList();
                        this.aA &= -2;
                        onChanged();
                    } else {
                        this.bH.clear();
                    }
                    return this;
                }

                public Builder clone() {
                    return ax().mergeFrom(buildPartial());
                }

                public ErrorMsg getDefaultInstanceForType() {
                    return ErrorMsg.getDefaultInstance();
                }

                public Descriptors.Descriptor getDescriptorForType() {
                    return ProtoMsg.ai;
                }

                public ProtoError getError(int i) {
                    return (ProtoError) (this.bH == null ? this.bG.get(i) : this.bH.getMessage(i));
                }

                public ProtoError.Builder getErrorBuilder(int i) {
                    return (ProtoError.Builder) az().getBuilder(i);
                }

                public List getErrorBuilderList() {
                    return az().getBuilderList();
                }

                public int getErrorCount() {
                    return this.bH == null ? this.bG.size() : this.bH.getCount();
                }

                public List getErrorList() {
                    return this.bH == null ? Collections.unmodifiableList(this.bG) : this.bH.getMessageList();
                }

                public ProtoErrorOrBuilder getErrorOrBuilder(int i) {
                    return (ProtoErrorOrBuilder) (this.bH == null ? this.bG.get(i) : this.bH.getMessageOrBuilder(i));
                }

                public List getErrorOrBuilderList() {
                    return this.bH != null ? this.bH.getMessageOrBuilderList() : Collections.unmodifiableList(this.bG);
                }

                /* access modifiers changed from: protected */
                public GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
                    return ProtoMsg.aj.ensureFieldAccessorsInitialized(ErrorMsg.class, Builder.class);
                }

                public final boolean isInitialized() {
                    for (int i = 0; i < getErrorCount(); i++) {
                        if (!getError(i).isInitialized()) {
                            return false;
                        }
                    }
                    return true;
                }

                public Builder mergeFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) {
                    ErrorMsg errorMsg;
                    ErrorMsg errorMsg2 = null;
                    try {
                        ErrorMsg errorMsg3 = (ErrorMsg) ErrorMsg.PARSER.parsePartialFrom(codedInputStream, extensionRegistryLite);
                        if (errorMsg3 != null) {
                            mergeFrom(errorMsg3);
                        }
                        return this;
                    } catch (InvalidProtocolBufferException e) {
                        errorMsg = (ErrorMsg) e.getUnfinishedMessage();
                        throw e;
                    } catch (Throwable th) {
                        th = th;
                        errorMsg2 = errorMsg;
                    }
                    if (errorMsg2 != null) {
                        mergeFrom(errorMsg2);
                    }
                    throw th;
                }

                public Builder mergeFrom(Message message) {
                    if (message instanceof ErrorMsg) {
                        return mergeFrom((ErrorMsg) message);
                    }
                    super.mergeFrom(message);
                    return this;
                }

                public Builder mergeFrom(ErrorMsg errorMsg) {
                    if (errorMsg == ErrorMsg.getDefaultInstance()) {
                        return this;
                    }
                    if (this.bH == null) {
                        if (!errorMsg.bG.isEmpty()) {
                            if (this.bG.isEmpty()) {
                                this.bG = errorMsg.bG;
                                this.aA &= -2;
                            } else {
                                ay();
                                this.bG.addAll(errorMsg.bG);
                            }
                            onChanged();
                        }
                    } else if (!errorMsg.bG.isEmpty()) {
                        if (this.bH.isEmpty()) {
                            this.bH.dispose();
                            RepeatedFieldBuilder repeatedFieldBuilder = null;
                            this.bH = null;
                            this.bG = errorMsg.bG;
                            this.aA &= -2;
                            if (ErrorMsg.alwaysUseFieldBuilders) {
                                repeatedFieldBuilder = az();
                            }
                            this.bH = repeatedFieldBuilder;
                        } else {
                            this.bH.addAllMessages(errorMsg.bG);
                        }
                    }
                    mergeUnknownFields(errorMsg.getUnknownFields());
                    return this;
                }

                public Builder removeError(int i) {
                    if (this.bH == null) {
                        ay();
                        this.bG.remove(i);
                        onChanged();
                    } else {
                        this.bH.remove(i);
                    }
                    return this;
                }

                public Builder setError(int i, ProtoError.Builder builder) {
                    if (this.bH == null) {
                        ay();
                        this.bG.set(i, builder.build());
                        onChanged();
                    } else {
                        this.bH.setMessage(i, builder.build());
                    }
                    return this;
                }

                public Builder setError(int i, ProtoError protoError) {
                    if (this.bH != null) {
                        this.bH.setMessage(i, protoError);
                    } else if (protoError != null) {
                        ay();
                        this.bG.set(i, protoError);
                        onChanged();
                    } else {
                        throw new NullPointerException();
                    }
                    return this;
                }
            }

            static {
                bF.P();
            }

            private ErrorMsg(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) {
                this.ax = -1;
                this.ay = -1;
                P();
                UnknownFieldSet.Builder newBuilder = UnknownFieldSet.newBuilder();
                boolean z = false;
                boolean z2 = false;
                while (!z) {
                    try {
                        int readTag = codedInputStream.readTag();
                        if (readTag != 0) {
                            if (readTag == 10) {
                                if (!z2 || !true) {
                                    this.bG = new ArrayList();
                                    z2 |= true;
                                }
                                this.bG.add(codedInputStream.readMessage(ProtoError.PARSER, extensionRegistryLite));
                            } else if (!parseUnknownField(codedInputStream, newBuilder, extensionRegistryLite, readTag)) {
                            }
                        }
                        z = true;
                    } catch (InvalidProtocolBufferException e) {
                        throw e.setUnfinishedMessage(this);
                    } catch (IOException e2) {
                        throw new InvalidProtocolBufferException(e2.getMessage()).setUnfinishedMessage(this);
                    } catch (Throwable th) {
                        if (z2 && true) {
                            this.bG = Collections.unmodifiableList(this.bG);
                        }
                        this.aw = newBuilder.build();
                        makeExtensionsImmutable();
                        throw th;
                    }
                }
                if (z2 && true) {
                    this.bG = Collections.unmodifiableList(this.bG);
                }
                this.aw = newBuilder.build();
                makeExtensionsImmutable();
            }

            /* synthetic */ ErrorMsg(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite, a aVar) {
                this(codedInputStream, extensionRegistryLite);
            }

            private ErrorMsg(GeneratedMessage.Builder builder) {
                super(builder);
                this.ax = -1;
                this.ay = -1;
                this.aw = builder.getUnknownFields();
            }

            /* synthetic */ ErrorMsg(GeneratedMessage.Builder builder, a aVar) {
                this(builder);
            }

            private ErrorMsg(boolean z) {
                this.ax = -1;
                this.ay = -1;
                this.aw = UnknownFieldSet.getDefaultInstance();
            }

            private void P() {
                this.bG = Collections.emptyList();
            }

            public static ErrorMsg getDefaultInstance() {
                return bF;
            }

            public static final Descriptors.Descriptor getDescriptor() {
                return ProtoMsg.ai;
            }

            public static Builder newBuilder() {
                return Builder.ax();
            }

            public static Builder newBuilder(ErrorMsg errorMsg) {
                return newBuilder().mergeFrom(errorMsg);
            }

            public static ErrorMsg parseDelimitedFrom(InputStream inputStream) {
                return (ErrorMsg) PARSER.parseDelimitedFrom(inputStream);
            }

            public static ErrorMsg parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) {
                return (ErrorMsg) PARSER.parseDelimitedFrom(inputStream, extensionRegistryLite);
            }

            public static ErrorMsg parseFrom(ByteString byteString) {
                return (ErrorMsg) PARSER.parseFrom(byteString);
            }

            public static ErrorMsg parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) {
                return (ErrorMsg) PARSER.parseFrom(byteString, extensionRegistryLite);
            }

            public static ErrorMsg parseFrom(CodedInputStream codedInputStream) {
                return (ErrorMsg) PARSER.parseFrom(codedInputStream);
            }

            public static ErrorMsg parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) {
                return (ErrorMsg) PARSER.parseFrom(codedInputStream, extensionRegistryLite);
            }

            public static ErrorMsg parseFrom(InputStream inputStream) {
                return (ErrorMsg) PARSER.parseFrom(inputStream);
            }

            public static ErrorMsg parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) {
                return (ErrorMsg) PARSER.parseFrom(inputStream, extensionRegistryLite);
            }

            public static ErrorMsg parseFrom(byte[] bArr) {
                return (ErrorMsg) PARSER.parseFrom(bArr);
            }

            public static ErrorMsg parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) {
                return (ErrorMsg) PARSER.parseFrom(bArr, extensionRegistryLite);
            }

            public ErrorMsg getDefaultInstanceForType() {
                return bF;
            }

            public ProtoError getError(int i) {
                return (ProtoError) this.bG.get(i);
            }

            public int getErrorCount() {
                return this.bG.size();
            }

            public List getErrorList() {
                return this.bG;
            }

            public ProtoErrorOrBuilder getErrorOrBuilder(int i) {
                return (ProtoErrorOrBuilder) this.bG.get(i);
            }

            public List getErrorOrBuilderList() {
                return this.bG;
            }

            public Parser getParserForType() {
                return PARSER;
            }

            public int getSerializedSize() {
                int i = this.ay;
                if (i != -1) {
                    return i;
                }
                int i2 = 0;
                for (int i3 = 0; i3 < this.bG.size(); i3++) {
                    i2 += CodedOutputStream.computeMessageSize(1, (MessageLite) this.bG.get(i3));
                }
                int serializedSize = i2 + getUnknownFields().getSerializedSize();
                this.ay = serializedSize;
                return serializedSize;
            }

            public final UnknownFieldSet getUnknownFields() {
                return this.aw;
            }

            /* access modifiers changed from: protected */
            public GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
                return ProtoMsg.aj.ensureFieldAccessorsInitialized(ErrorMsg.class, Builder.class);
            }

            public final boolean isInitialized() {
                byte b = this.ax;
                if (b == 1) {
                    return true;
                }
                if (b == 0) {
                    return false;
                }
                for (int i = 0; i < getErrorCount(); i++) {
                    if (!getError(i).isInitialized()) {
                        this.ax = 0;
                        return false;
                    }
                }
                this.ax = 1;
                return true;
            }

            public Builder newBuilderForType() {
                return newBuilder();
            }

            /* access modifiers changed from: protected */
            public Builder newBuilderForType(GeneratedMessage.BuilderParent builderParent) {
                return new Builder(builderParent, (a) null);
            }

            public Builder toBuilder() {
                return newBuilder(this);
            }

            /* access modifiers changed from: protected */
            public Object writeReplace() {
                return super.writeReplace();
            }

            public void writeTo(CodedOutputStream codedOutputStream) {
                getSerializedSize();
                for (int i = 0; i < this.bG.size(); i++) {
                    codedOutputStream.writeMessage(1, (MessageLite) this.bG.get(i));
                }
                getUnknownFields().writeTo(codedOutputStream);
            }
        }

        public interface ErrorMsgOrBuilder extends MessageOrBuilder {
            ProtoError getError(int i);

            int getErrorCount();

            List getErrorList();

            ProtoErrorOrBuilder getErrorOrBuilder(int i);

            List getErrorOrBuilderList();
        }

        public final class Event extends GeneratedMessage implements EventOrBuilder {
            public static final int APP_CHANNEL_FIELD_NUMBER = 11;
            public static final int APP_KEY_FIELD_NUMBER = 10;
            public static final int DEVICE_ID_FIELD_NUMBER = 4;
            public static final int EVENT_ID_FIELD_NUMBER = 6;
            public static final int EVENT_MAP_FIELD_NUMBER = 8;
            public static final int LABEL_FIELD_NUMBER = 7;
            public static final int LOG_VERSION_APP_FIELD_NUMBER = 13;
            public static final int PACKAGE_NAME_FIELD_NUMBER = 12;
            public static Parser PARSER = new i();
            public static final int PLATFORM_FIELD_NUMBER = 9;
            public static final int SESSION_ID_FIELD_NUMBER = 2;
            public static final int TIME_FIELD_NUMBER = 5;
            public static final int TYPE_FIELD_NUMBER = 1;
            public static final int USER_ID_FIELD_NUMBER = 3;
            private static final Event bI = new Event(true);
            /* access modifiers changed from: private */
            public int aA;
            /* access modifiers changed from: private */
            public Object aC;
            /* access modifiers changed from: private */
            public Object aD;
            /* access modifiers changed from: private */
            public Object aE;
            /* access modifiers changed from: private */
            public Object aF;
            /* access modifiers changed from: private */
            public Object aG;
            /* access modifiers changed from: private */
            public Object aH;
            /* access modifiers changed from: private */
            public Object aM;
            private final UnknownFieldSet aw;
            private byte ax;
            private int ay;
            /* access modifiers changed from: private */
            public Object bJ;
            /* access modifiers changed from: private */
            public Object bK;
            /* access modifiers changed from: private */
            public List bL;
            /* access modifiers changed from: private */
            public long bb;
            /* access modifiers changed from: private */
            public Object bh;
            /* access modifiers changed from: private */
            public Object bj;

            public final class Builder extends GeneratedMessage.Builder implements EventOrBuilder {
                private int aA;
                private Object aC;
                private Object aD;
                private Object aE;
                private Object aF;
                private Object aG;
                private Object aH;
                private Object aM;
                private Object bJ;
                private Object bK;
                private List bL;
                private RepeatedFieldBuilder bM;
                private long bb;
                private Object bh;
                private Object bj;

                private Builder() {
                    this.aC = "";
                    this.aF = "";
                    this.aH = "";
                    this.aG = "";
                    this.bJ = "";
                    this.bK = "";
                    this.bL = Collections.emptyList();
                    this.aM = "";
                    this.aD = "";
                    this.aE = "";
                    this.bh = "";
                    this.bj = "";
                    R();
                }

                private Builder(GeneratedMessage.BuilderParent builderParent) {
                    super(builderParent);
                    this.aC = "";
                    this.aF = "";
                    this.aH = "";
                    this.aG = "";
                    this.bJ = "";
                    this.bK = "";
                    this.bL = Collections.emptyList();
                    this.aM = "";
                    this.aD = "";
                    this.aE = "";
                    this.bh = "";
                    this.bj = "";
                    R();
                }

                /* synthetic */ Builder(GeneratedMessage.BuilderParent builderParent, a aVar) {
                    this(builderParent);
                }

                private void R() {
                    if (Event.alwaysUseFieldBuilders) {
                        aF();
                    }
                }

                /* access modifiers changed from: private */
                public static Builder aD() {
                    return new Builder();
                }

                private void aE() {
                    if ((this.aA & 128) != 128) {
                        this.bL = new ArrayList(this.bL);
                        this.aA |= 128;
                    }
                }

                private RepeatedFieldBuilder aF() {
                    if (this.bM == null) {
                        this.bM = new RepeatedFieldBuilder(this.bL, (this.aA & 128) == 128, getParentForChildren(), isClean());
                        this.bL = null;
                    }
                    return this.bM;
                }

                public static final Descriptors.Descriptor getDescriptor() {
                    return ProtoMsg.W;
                }

                public Builder addAllEventMap(Iterable iterable) {
                    if (this.bM == null) {
                        aE();
                        AbstractMessageLite.Builder.addAll(iterable, this.bL);
                        onChanged();
                    } else {
                        this.bM.addAllMessages(iterable);
                    }
                    return this;
                }

                public Builder addEventMap(int i, ProtoMap.Builder builder) {
                    if (this.bM == null) {
                        aE();
                        this.bL.add(i, builder.build());
                        onChanged();
                    } else {
                        this.bM.addMessage(i, builder.build());
                    }
                    return this;
                }

                public Builder addEventMap(int i, ProtoMap protoMap) {
                    if (this.bM != null) {
                        this.bM.addMessage(i, protoMap);
                    } else if (protoMap != null) {
                        aE();
                        this.bL.add(i, protoMap);
                        onChanged();
                    } else {
                        throw new NullPointerException();
                    }
                    return this;
                }

                public Builder addEventMap(ProtoMap.Builder builder) {
                    if (this.bM == null) {
                        aE();
                        this.bL.add(builder.build());
                        onChanged();
                    } else {
                        this.bM.addMessage(builder.build());
                    }
                    return this;
                }

                public Builder addEventMap(ProtoMap protoMap) {
                    if (this.bM != null) {
                        this.bM.addMessage(protoMap);
                    } else if (protoMap != null) {
                        aE();
                        this.bL.add(protoMap);
                        onChanged();
                    } else {
                        throw new NullPointerException();
                    }
                    return this;
                }

                public ProtoMap.Builder addEventMapBuilder() {
                    return (ProtoMap.Builder) aF().addBuilder(ProtoMap.getDefaultInstance());
                }

                public ProtoMap.Builder addEventMapBuilder(int i) {
                    return (ProtoMap.Builder) aF().addBuilder(i, ProtoMap.getDefaultInstance());
                }

                public Event build() {
                    Event buildPartial = buildPartial();
                    if (buildPartial.isInitialized()) {
                        return buildPartial;
                    }
                    throw newUninitializedMessageException(buildPartial);
                }

                public Event buildPartial() {
                    List list;
                    Event event = new Event((GeneratedMessage.Builder) this, (a) null);
                    int i = this.aA;
                    int i2 = 1;
                    if ((i & 1) != 1) {
                        i2 = 0;
                    }
                    Object unused = event.aC = this.aC;
                    if ((i & 2) == 2) {
                        i2 |= 2;
                    }
                    Object unused2 = event.aF = this.aF;
                    if ((i & 4) == 4) {
                        i2 |= 4;
                    }
                    Object unused3 = event.aH = this.aH;
                    if ((i & 8) == 8) {
                        i2 |= 8;
                    }
                    Object unused4 = event.aG = this.aG;
                    if ((i & 16) == 16) {
                        i2 |= 16;
                    }
                    long unused5 = event.bb = this.bb;
                    if ((i & 32) == 32) {
                        i2 |= 32;
                    }
                    Object unused6 = event.bJ = this.bJ;
                    if ((i & 64) == 64) {
                        i2 |= 64;
                    }
                    Object unused7 = event.bK = this.bK;
                    if (this.bM == null) {
                        if ((this.aA & 128) == 128) {
                            this.bL = Collections.unmodifiableList(this.bL);
                            this.aA &= -129;
                        }
                        list = this.bL;
                    } else {
                        list = this.bM.build();
                    }
                    List unused8 = event.bL = list;
                    if ((i & 256) == 256) {
                        i2 |= 128;
                    }
                    Object unused9 = event.aM = this.aM;
                    if ((i & 512) == 512) {
                        i2 |= 256;
                    }
                    Object unused10 = event.aD = this.aD;
                    if ((i & 1024) == 1024) {
                        i2 |= 512;
                    }
                    Object unused11 = event.aE = this.aE;
                    if ((i & 2048) == 2048) {
                        i2 |= 1024;
                    }
                    Object unused12 = event.bh = this.bh;
                    if ((i & 4096) == 4096) {
                        i2 |= 2048;
                    }
                    Object unused13 = event.bj = this.bj;
                    int unused14 = event.aA = i2;
                    onBuilt();
                    return event;
                }

                public Builder clear() {
                    super.clear();
                    this.aC = "";
                    this.aA &= -2;
                    this.aF = "";
                    this.aA &= -3;
                    this.aH = "";
                    this.aA &= -5;
                    this.aG = "";
                    this.aA &= -9;
                    this.bb = 0;
                    this.aA &= -17;
                    this.bJ = "";
                    this.aA &= -33;
                    this.bK = "";
                    this.aA &= -65;
                    if (this.bM == null) {
                        this.bL = Collections.emptyList();
                        this.aA &= -129;
                    } else {
                        this.bM.clear();
                    }
                    this.aM = "";
                    this.aA &= -257;
                    this.aD = "";
                    this.aA &= -513;
                    this.aE = "";
                    this.aA &= -1025;
                    this.bh = "";
                    this.aA &= -2049;
                    this.bj = "";
                    this.aA &= -4097;
                    return this;
                }

                public Builder clearAppChannel() {
                    this.aA &= -1025;
                    this.aE = Event.getDefaultInstance().getAppChannel();
                    onChanged();
                    return this;
                }

                public Builder clearAppKey() {
                    this.aA &= -513;
                    this.aD = Event.getDefaultInstance().getAppKey();
                    onChanged();
                    return this;
                }

                public Builder clearDeviceId() {
                    this.aA &= -9;
                    this.aG = Event.getDefaultInstance().getDeviceId();
                    onChanged();
                    return this;
                }

                public Builder clearEventId() {
                    this.aA &= -33;
                    this.bJ = Event.getDefaultInstance().getEventId();
                    onChanged();
                    return this;
                }

                public Builder clearEventMap() {
                    if (this.bM == null) {
                        this.bL = Collections.emptyList();
                        this.aA &= -129;
                        onChanged();
                    } else {
                        this.bM.clear();
                    }
                    return this;
                }

                public Builder clearLabel() {
                    this.aA &= -65;
                    this.bK = Event.getDefaultInstance().getLabel();
                    onChanged();
                    return this;
                }

                public Builder clearLogVersionApp() {
                    this.aA &= -4097;
                    this.bj = Event.getDefaultInstance().getLogVersionApp();
                    onChanged();
                    return this;
                }

                public Builder clearPackageName() {
                    this.aA &= -2049;
                    this.bh = Event.getDefaultInstance().getPackageName();
                    onChanged();
                    return this;
                }

                public Builder clearPlatform() {
                    this.aA &= -257;
                    this.aM = Event.getDefaultInstance().getPlatform();
                    onChanged();
                    return this;
                }

                public Builder clearSessionId() {
                    this.aA &= -3;
                    this.aF = Event.getDefaultInstance().getSessionId();
                    onChanged();
                    return this;
                }

                public Builder clearTime() {
                    this.aA &= -17;
                    this.bb = 0;
                    onChanged();
                    return this;
                }

                public Builder clearType() {
                    this.aA &= -2;
                    this.aC = Event.getDefaultInstance().getType();
                    onChanged();
                    return this;
                }

                public Builder clearUserId() {
                    this.aA &= -5;
                    this.aH = Event.getDefaultInstance().getUserId();
                    onChanged();
                    return this;
                }

                public Builder clone() {
                    return aD().mergeFrom(buildPartial());
                }

                public String getAppChannel() {
                    Object obj = this.aE;
                    if (obj instanceof String) {
                        return (String) obj;
                    }
                    ByteString byteString = (ByteString) obj;
                    String stringUtf8 = byteString.toStringUtf8();
                    if (byteString.isValidUtf8()) {
                        this.aE = stringUtf8;
                    }
                    return stringUtf8;
                }

                public ByteString getAppChannelBytes() {
                    Object obj = this.aE;
                    if (!(obj instanceof String)) {
                        return (ByteString) obj;
                    }
                    ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                    this.aE = copyFromUtf8;
                    return copyFromUtf8;
                }

                public String getAppKey() {
                    Object obj = this.aD;
                    if (obj instanceof String) {
                        return (String) obj;
                    }
                    ByteString byteString = (ByteString) obj;
                    String stringUtf8 = byteString.toStringUtf8();
                    if (byteString.isValidUtf8()) {
                        this.aD = stringUtf8;
                    }
                    return stringUtf8;
                }

                public ByteString getAppKeyBytes() {
                    Object obj = this.aD;
                    if (!(obj instanceof String)) {
                        return (ByteString) obj;
                    }
                    ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                    this.aD = copyFromUtf8;
                    return copyFromUtf8;
                }

                public Event getDefaultInstanceForType() {
                    return Event.getDefaultInstance();
                }

                public Descriptors.Descriptor getDescriptorForType() {
                    return ProtoMsg.W;
                }

                public String getDeviceId() {
                    Object obj = this.aG;
                    if (obj instanceof String) {
                        return (String) obj;
                    }
                    ByteString byteString = (ByteString) obj;
                    String stringUtf8 = byteString.toStringUtf8();
                    if (byteString.isValidUtf8()) {
                        this.aG = stringUtf8;
                    }
                    return stringUtf8;
                }

                public ByteString getDeviceIdBytes() {
                    Object obj = this.aG;
                    if (!(obj instanceof String)) {
                        return (ByteString) obj;
                    }
                    ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                    this.aG = copyFromUtf8;
                    return copyFromUtf8;
                }

                public String getEventId() {
                    Object obj = this.bJ;
                    if (obj instanceof String) {
                        return (String) obj;
                    }
                    ByteString byteString = (ByteString) obj;
                    String stringUtf8 = byteString.toStringUtf8();
                    if (byteString.isValidUtf8()) {
                        this.bJ = stringUtf8;
                    }
                    return stringUtf8;
                }

                public ByteString getEventIdBytes() {
                    Object obj = this.bJ;
                    if (!(obj instanceof String)) {
                        return (ByteString) obj;
                    }
                    ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                    this.bJ = copyFromUtf8;
                    return copyFromUtf8;
                }

                public ProtoMap getEventMap(int i) {
                    return (ProtoMap) (this.bM == null ? this.bL.get(i) : this.bM.getMessage(i));
                }

                public ProtoMap.Builder getEventMapBuilder(int i) {
                    return (ProtoMap.Builder) aF().getBuilder(i);
                }

                public List getEventMapBuilderList() {
                    return aF().getBuilderList();
                }

                public int getEventMapCount() {
                    return this.bM == null ? this.bL.size() : this.bM.getCount();
                }

                public List getEventMapList() {
                    return this.bM == null ? Collections.unmodifiableList(this.bL) : this.bM.getMessageList();
                }

                public ProtoMapOrBuilder getEventMapOrBuilder(int i) {
                    return (ProtoMapOrBuilder) (this.bM == null ? this.bL.get(i) : this.bM.getMessageOrBuilder(i));
                }

                public List getEventMapOrBuilderList() {
                    return this.bM != null ? this.bM.getMessageOrBuilderList() : Collections.unmodifiableList(this.bL);
                }

                public String getLabel() {
                    Object obj = this.bK;
                    if (obj instanceof String) {
                        return (String) obj;
                    }
                    ByteString byteString = (ByteString) obj;
                    String stringUtf8 = byteString.toStringUtf8();
                    if (byteString.isValidUtf8()) {
                        this.bK = stringUtf8;
                    }
                    return stringUtf8;
                }

                public ByteString getLabelBytes() {
                    Object obj = this.bK;
                    if (!(obj instanceof String)) {
                        return (ByteString) obj;
                    }
                    ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                    this.bK = copyFromUtf8;
                    return copyFromUtf8;
                }

                public String getLogVersionApp() {
                    Object obj = this.bj;
                    if (obj instanceof String) {
                        return (String) obj;
                    }
                    ByteString byteString = (ByteString) obj;
                    String stringUtf8 = byteString.toStringUtf8();
                    if (byteString.isValidUtf8()) {
                        this.bj = stringUtf8;
                    }
                    return stringUtf8;
                }

                public ByteString getLogVersionAppBytes() {
                    Object obj = this.bj;
                    if (!(obj instanceof String)) {
                        return (ByteString) obj;
                    }
                    ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                    this.bj = copyFromUtf8;
                    return copyFromUtf8;
                }

                public String getPackageName() {
                    Object obj = this.bh;
                    if (obj instanceof String) {
                        return (String) obj;
                    }
                    ByteString byteString = (ByteString) obj;
                    String stringUtf8 = byteString.toStringUtf8();
                    if (byteString.isValidUtf8()) {
                        this.bh = stringUtf8;
                    }
                    return stringUtf8;
                }

                public ByteString getPackageNameBytes() {
                    Object obj = this.bh;
                    if (!(obj instanceof String)) {
                        return (ByteString) obj;
                    }
                    ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                    this.bh = copyFromUtf8;
                    return copyFromUtf8;
                }

                public String getPlatform() {
                    Object obj = this.aM;
                    if (obj instanceof String) {
                        return (String) obj;
                    }
                    ByteString byteString = (ByteString) obj;
                    String stringUtf8 = byteString.toStringUtf8();
                    if (byteString.isValidUtf8()) {
                        this.aM = stringUtf8;
                    }
                    return stringUtf8;
                }

                public ByteString getPlatformBytes() {
                    Object obj = this.aM;
                    if (!(obj instanceof String)) {
                        return (ByteString) obj;
                    }
                    ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                    this.aM = copyFromUtf8;
                    return copyFromUtf8;
                }

                public String getSessionId() {
                    Object obj = this.aF;
                    if (obj instanceof String) {
                        return (String) obj;
                    }
                    ByteString byteString = (ByteString) obj;
                    String stringUtf8 = byteString.toStringUtf8();
                    if (byteString.isValidUtf8()) {
                        this.aF = stringUtf8;
                    }
                    return stringUtf8;
                }

                public ByteString getSessionIdBytes() {
                    Object obj = this.aF;
                    if (!(obj instanceof String)) {
                        return (ByteString) obj;
                    }
                    ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                    this.aF = copyFromUtf8;
                    return copyFromUtf8;
                }

                public long getTime() {
                    return this.bb;
                }

                public String getType() {
                    Object obj = this.aC;
                    if (obj instanceof String) {
                        return (String) obj;
                    }
                    ByteString byteString = (ByteString) obj;
                    String stringUtf8 = byteString.toStringUtf8();
                    if (byteString.isValidUtf8()) {
                        this.aC = stringUtf8;
                    }
                    return stringUtf8;
                }

                public ByteString getTypeBytes() {
                    Object obj = this.aC;
                    if (!(obj instanceof String)) {
                        return (ByteString) obj;
                    }
                    ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                    this.aC = copyFromUtf8;
                    return copyFromUtf8;
                }

                public String getUserId() {
                    Object obj = this.aH;
                    if (obj instanceof String) {
                        return (String) obj;
                    }
                    ByteString byteString = (ByteString) obj;
                    String stringUtf8 = byteString.toStringUtf8();
                    if (byteString.isValidUtf8()) {
                        this.aH = stringUtf8;
                    }
                    return stringUtf8;
                }

                public ByteString getUserIdBytes() {
                    Object obj = this.aH;
                    if (!(obj instanceof String)) {
                        return (ByteString) obj;
                    }
                    ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                    this.aH = copyFromUtf8;
                    return copyFromUtf8;
                }

                public boolean hasAppChannel() {
                    return (this.aA & 1024) == 1024;
                }

                public boolean hasAppKey() {
                    return (this.aA & 512) == 512;
                }

                public boolean hasDeviceId() {
                    return (this.aA & 8) == 8;
                }

                public boolean hasEventId() {
                    return (this.aA & 32) == 32;
                }

                public boolean hasLabel() {
                    return (this.aA & 64) == 64;
                }

                public boolean hasLogVersionApp() {
                    return (this.aA & 4096) == 4096;
                }

                public boolean hasPackageName() {
                    return (this.aA & 2048) == 2048;
                }

                public boolean hasPlatform() {
                    return (this.aA & 256) == 256;
                }

                public boolean hasSessionId() {
                    return (this.aA & 2) == 2;
                }

                public boolean hasTime() {
                    return (this.aA & 16) == 16;
                }

                public boolean hasType() {
                    return (this.aA & 1) == 1;
                }

                public boolean hasUserId() {
                    return (this.aA & 4) == 4;
                }

                /* access modifiers changed from: protected */
                public GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
                    return ProtoMsg.X.ensureFieldAccessorsInitialized(Event.class, Builder.class);
                }

                public final boolean isInitialized() {
                    if (!hasType()) {
                        return false;
                    }
                    for (int i = 0; i < getEventMapCount(); i++) {
                        if (!getEventMap(i).isInitialized()) {
                            return false;
                        }
                    }
                    return true;
                }

                public Builder mergeFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) {
                    Event event;
                    Event event2 = null;
                    try {
                        Event event3 = (Event) Event.PARSER.parsePartialFrom(codedInputStream, extensionRegistryLite);
                        if (event3 != null) {
                            mergeFrom(event3);
                        }
                        return this;
                    } catch (InvalidProtocolBufferException e) {
                        event = (Event) e.getUnfinishedMessage();
                        throw e;
                    } catch (Throwable th) {
                        th = th;
                        event2 = event;
                    }
                    if (event2 != null) {
                        mergeFrom(event2);
                    }
                    throw th;
                }

                public Builder mergeFrom(Message message) {
                    if (message instanceof Event) {
                        return mergeFrom((Event) message);
                    }
                    super.mergeFrom(message);
                    return this;
                }

                public Builder mergeFrom(Event event) {
                    if (event == Event.getDefaultInstance()) {
                        return this;
                    }
                    if (event.hasType()) {
                        this.aA |= 1;
                        this.aC = event.aC;
                        onChanged();
                    }
                    if (event.hasSessionId()) {
                        this.aA |= 2;
                        this.aF = event.aF;
                        onChanged();
                    }
                    if (event.hasUserId()) {
                        this.aA |= 4;
                        this.aH = event.aH;
                        onChanged();
                    }
                    if (event.hasDeviceId()) {
                        this.aA |= 8;
                        this.aG = event.aG;
                        onChanged();
                    }
                    if (event.hasTime()) {
                        setTime(event.getTime());
                    }
                    if (event.hasEventId()) {
                        this.aA |= 32;
                        this.bJ = event.bJ;
                        onChanged();
                    }
                    if (event.hasLabel()) {
                        this.aA |= 64;
                        this.bK = event.bK;
                        onChanged();
                    }
                    if (this.bM == null) {
                        if (!event.bL.isEmpty()) {
                            if (this.bL.isEmpty()) {
                                this.bL = event.bL;
                                this.aA &= -129;
                            } else {
                                aE();
                                this.bL.addAll(event.bL);
                            }
                            onChanged();
                        }
                    } else if (!event.bL.isEmpty()) {
                        if (this.bM.isEmpty()) {
                            this.bM.dispose();
                            RepeatedFieldBuilder repeatedFieldBuilder = null;
                            this.bM = null;
                            this.bL = event.bL;
                            this.aA &= -129;
                            if (Event.alwaysUseFieldBuilders) {
                                repeatedFieldBuilder = aF();
                            }
                            this.bM = repeatedFieldBuilder;
                        } else {
                            this.bM.addAllMessages(event.bL);
                        }
                    }
                    if (event.hasPlatform()) {
                        this.aA |= 256;
                        this.aM = event.aM;
                        onChanged();
                    }
                    if (event.hasAppKey()) {
                        this.aA |= 512;
                        this.aD = event.aD;
                        onChanged();
                    }
                    if (event.hasAppChannel()) {
                        this.aA |= 1024;
                        this.aE = event.aE;
                        onChanged();
                    }
                    if (event.hasPackageName()) {
                        this.aA |= 2048;
                        this.bh = event.bh;
                        onChanged();
                    }
                    if (event.hasLogVersionApp()) {
                        this.aA |= 4096;
                        this.bj = event.bj;
                        onChanged();
                    }
                    mergeUnknownFields(event.getUnknownFields());
                    return this;
                }

                public Builder removeEventMap(int i) {
                    if (this.bM == null) {
                        aE();
                        this.bL.remove(i);
                        onChanged();
                    } else {
                        this.bM.remove(i);
                    }
                    return this;
                }

                public Builder setAppChannel(String str) {
                    if (str != null) {
                        this.aA |= 1024;
                        this.aE = str;
                        onChanged();
                        return this;
                    }
                    throw new NullPointerException();
                }

                public Builder setAppChannelBytes(ByteString byteString) {
                    if (byteString != null) {
                        this.aA |= 1024;
                        this.aE = byteString;
                        onChanged();
                        return this;
                    }
                    throw new NullPointerException();
                }

                public Builder setAppKey(String str) {
                    if (str != null) {
                        this.aA |= 512;
                        this.aD = str;
                        onChanged();
                        return this;
                    }
                    throw new NullPointerException();
                }

                public Builder setAppKeyBytes(ByteString byteString) {
                    if (byteString != null) {
                        this.aA |= 512;
                        this.aD = byteString;
                        onChanged();
                        return this;
                    }
                    throw new NullPointerException();
                }

                public Builder setDeviceId(String str) {
                    if (str != null) {
                        this.aA |= 8;
                        this.aG = str;
                        onChanged();
                        return this;
                    }
                    throw new NullPointerException();
                }

                public Builder setDeviceIdBytes(ByteString byteString) {
                    if (byteString != null) {
                        this.aA |= 8;
                        this.aG = byteString;
                        onChanged();
                        return this;
                    }
                    throw new NullPointerException();
                }

                public Builder setEventId(String str) {
                    if (str != null) {
                        this.aA |= 32;
                        this.bJ = str;
                        onChanged();
                        return this;
                    }
                    throw new NullPointerException();
                }

                public Builder setEventIdBytes(ByteString byteString) {
                    if (byteString != null) {
                        this.aA |= 32;
                        this.bJ = byteString;
                        onChanged();
                        return this;
                    }
                    throw new NullPointerException();
                }

                public Builder setEventMap(int i, ProtoMap.Builder builder) {
                    if (this.bM == null) {
                        aE();
                        this.bL.set(i, builder.build());
                        onChanged();
                    } else {
                        this.bM.setMessage(i, builder.build());
                    }
                    return this;
                }

                public Builder setEventMap(int i, ProtoMap protoMap) {
                    if (this.bM != null) {
                        this.bM.setMessage(i, protoMap);
                    } else if (protoMap != null) {
                        aE();
                        this.bL.set(i, protoMap);
                        onChanged();
                    } else {
                        throw new NullPointerException();
                    }
                    return this;
                }

                public Builder setLabel(String str) {
                    if (str != null) {
                        this.aA |= 64;
                        this.bK = str;
                        onChanged();
                        return this;
                    }
                    throw new NullPointerException();
                }

                public Builder setLabelBytes(ByteString byteString) {
                    if (byteString != null) {
                        this.aA |= 64;
                        this.bK = byteString;
                        onChanged();
                        return this;
                    }
                    throw new NullPointerException();
                }

                public Builder setLogVersionApp(String str) {
                    if (str != null) {
                        this.aA |= 4096;
                        this.bj = str;
                        onChanged();
                        return this;
                    }
                    throw new NullPointerException();
                }

                public Builder setLogVersionAppBytes(ByteString byteString) {
                    if (byteString != null) {
                        this.aA |= 4096;
                        this.bj = byteString;
                        onChanged();
                        return this;
                    }
                    throw new NullPointerException();
                }

                public Builder setPackageName(String str) {
                    if (str != null) {
                        this.aA |= 2048;
                        this.bh = str;
                        onChanged();
                        return this;
                    }
                    throw new NullPointerException();
                }

                public Builder setPackageNameBytes(ByteString byteString) {
                    if (byteString != null) {
                        this.aA |= 2048;
                        this.bh = byteString;
                        onChanged();
                        return this;
                    }
                    throw new NullPointerException();
                }

                public Builder setPlatform(String str) {
                    if (str != null) {
                        this.aA |= 256;
                        this.aM = str;
                        onChanged();
                        return this;
                    }
                    throw new NullPointerException();
                }

                public Builder setPlatformBytes(ByteString byteString) {
                    if (byteString != null) {
                        this.aA |= 256;
                        this.aM = byteString;
                        onChanged();
                        return this;
                    }
                    throw new NullPointerException();
                }

                public Builder setSessionId(String str) {
                    if (str != null) {
                        this.aA |= 2;
                        this.aF = str;
                        onChanged();
                        return this;
                    }
                    throw new NullPointerException();
                }

                public Builder setSessionIdBytes(ByteString byteString) {
                    if (byteString != null) {
                        this.aA |= 2;
                        this.aF = byteString;
                        onChanged();
                        return this;
                    }
                    throw new NullPointerException();
                }

                public Builder setTime(long j) {
                    this.aA |= 16;
                    this.bb = j;
                    onChanged();
                    return this;
                }

                public Builder setType(String str) {
                    if (str != null) {
                        this.aA |= 1;
                        this.aC = str;
                        onChanged();
                        return this;
                    }
                    throw new NullPointerException();
                }

                public Builder setTypeBytes(ByteString byteString) {
                    if (byteString != null) {
                        this.aA |= 1;
                        this.aC = byteString;
                        onChanged();
                        return this;
                    }
                    throw new NullPointerException();
                }

                public Builder setUserId(String str) {
                    if (str != null) {
                        this.aA |= 4;
                        this.aH = str;
                        onChanged();
                        return this;
                    }
                    throw new NullPointerException();
                }

                public Builder setUserIdBytes(ByteString byteString) {
                    if (byteString != null) {
                        this.aA |= 4;
                        this.aH = byteString;
                        onChanged();
                        return this;
                    }
                    throw new NullPointerException();
                }
            }

            static {
                bI.P();
            }

            private Event(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) {
                this.ax = -1;
                this.ay = -1;
                P();
                UnknownFieldSet.Builder newBuilder = UnknownFieldSet.newBuilder();
                boolean z = false;
                boolean z2 = false;
                while (!z) {
                    try {
                        int readTag = codedInputStream.readTag();
                        switch (readTag) {
                            case 0:
                                z = true;
                                break;
                            case 10:
                                ByteString readBytes = codedInputStream.readBytes();
                                this.aA = 1 | this.aA;
                                this.aC = readBytes;
                                break;
                            case 18:
                                ByteString readBytes2 = codedInputStream.readBytes();
                                this.aA |= 2;
                                this.aF = readBytes2;
                                break;
                            case 26:
                                ByteString readBytes3 = codedInputStream.readBytes();
                                this.aA |= 4;
                                this.aH = readBytes3;
                                break;
                            case 34:
                                ByteString readBytes4 = codedInputStream.readBytes();
                                this.aA |= 8;
                                this.aG = readBytes4;
                                break;
                            case 40:
                                this.aA |= 16;
                                this.bb = codedInputStream.readInt64();
                                break;
                            case 50:
                                ByteString readBytes5 = codedInputStream.readBytes();
                                this.aA |= 32;
                                this.bJ = readBytes5;
                                break;
                            case 58:
                                ByteString readBytes6 = codedInputStream.readBytes();
                                this.aA |= 64;
                                this.bK = readBytes6;
                                break;
                            case 66:
                                if (!(z2 & true)) {
                                    this.bL = new ArrayList();
                                    z2 |= true;
                                }
                                this.bL.add(codedInputStream.readMessage(ProtoMap.PARSER, extensionRegistryLite));
                                break;
                            case 74:
                                ByteString readBytes7 = codedInputStream.readBytes();
                                this.aA |= 128;
                                this.aM = readBytes7;
                                break;
                            case 82:
                                ByteString readBytes8 = codedInputStream.readBytes();
                                this.aA |= 256;
                                this.aD = readBytes8;
                                break;
                            case 90:
                                ByteString readBytes9 = codedInputStream.readBytes();
                                this.aA |= 512;
                                this.aE = readBytes9;
                                break;
                            case 98:
                                ByteString readBytes10 = codedInputStream.readBytes();
                                this.aA |= 1024;
                                this.bh = readBytes10;
                                break;
                            case 106:
                                ByteString readBytes11 = codedInputStream.readBytes();
                                this.aA |= 2048;
                                this.bj = readBytes11;
                                break;
                            default:
                                if (parseUnknownField(codedInputStream, newBuilder, extensionRegistryLite, readTag)) {
                                    break;
                                }
                                z = true;
                                break;
                        }
                    } catch (InvalidProtocolBufferException e) {
                        throw e.setUnfinishedMessage(this);
                    } catch (IOException e2) {
                        throw new InvalidProtocolBufferException(e2.getMessage()).setUnfinishedMessage(this);
                    } catch (Throwable th) {
                        if (z2 & true) {
                            this.bL = Collections.unmodifiableList(this.bL);
                        }
                        this.aw = newBuilder.build();
                        makeExtensionsImmutable();
                        throw th;
                    }
                }
                if (z2 & true) {
                    this.bL = Collections.unmodifiableList(this.bL);
                }
                this.aw = newBuilder.build();
                makeExtensionsImmutable();
            }

            /* synthetic */ Event(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite, a aVar) {
                this(codedInputStream, extensionRegistryLite);
            }

            private Event(GeneratedMessage.Builder builder) {
                super(builder);
                this.ax = -1;
                this.ay = -1;
                this.aw = builder.getUnknownFields();
            }

            /* synthetic */ Event(GeneratedMessage.Builder builder, a aVar) {
                this(builder);
            }

            private Event(boolean z) {
                this.ax = -1;
                this.ay = -1;
                this.aw = UnknownFieldSet.getDefaultInstance();
            }

            private void P() {
                this.aC = "";
                this.aF = "";
                this.aH = "";
                this.aG = "";
                this.bb = 0;
                this.bJ = "";
                this.bK = "";
                this.bL = Collections.emptyList();
                this.aM = "";
                this.aD = "";
                this.aE = "";
                this.bh = "";
                this.bj = "";
            }

            public static Event getDefaultInstance() {
                return bI;
            }

            public static final Descriptors.Descriptor getDescriptor() {
                return ProtoMsg.W;
            }

            public static Builder newBuilder() {
                return Builder.aD();
            }

            public static Builder newBuilder(Event event) {
                return newBuilder().mergeFrom(event);
            }

            public static Event parseDelimitedFrom(InputStream inputStream) {
                return (Event) PARSER.parseDelimitedFrom(inputStream);
            }

            public static Event parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) {
                return (Event) PARSER.parseDelimitedFrom(inputStream, extensionRegistryLite);
            }

            public static Event parseFrom(ByteString byteString) {
                return (Event) PARSER.parseFrom(byteString);
            }

            public static Event parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) {
                return (Event) PARSER.parseFrom(byteString, extensionRegistryLite);
            }

            public static Event parseFrom(CodedInputStream codedInputStream) {
                return (Event) PARSER.parseFrom(codedInputStream);
            }

            public static Event parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) {
                return (Event) PARSER.parseFrom(codedInputStream, extensionRegistryLite);
            }

            public static Event parseFrom(InputStream inputStream) {
                return (Event) PARSER.parseFrom(inputStream);
            }

            public static Event parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) {
                return (Event) PARSER.parseFrom(inputStream, extensionRegistryLite);
            }

            public static Event parseFrom(byte[] bArr) {
                return (Event) PARSER.parseFrom(bArr);
            }

            public static Event parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) {
                return (Event) PARSER.parseFrom(bArr, extensionRegistryLite);
            }

            public String getAppChannel() {
                Object obj = this.aE;
                if (obj instanceof String) {
                    return (String) obj;
                }
                ByteString byteString = (ByteString) obj;
                String stringUtf8 = byteString.toStringUtf8();
                if (byteString.isValidUtf8()) {
                    this.aE = stringUtf8;
                }
                return stringUtf8;
            }

            public ByteString getAppChannelBytes() {
                Object obj = this.aE;
                if (!(obj instanceof String)) {
                    return (ByteString) obj;
                }
                ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                this.aE = copyFromUtf8;
                return copyFromUtf8;
            }

            public String getAppKey() {
                Object obj = this.aD;
                if (obj instanceof String) {
                    return (String) obj;
                }
                ByteString byteString = (ByteString) obj;
                String stringUtf8 = byteString.toStringUtf8();
                if (byteString.isValidUtf8()) {
                    this.aD = stringUtf8;
                }
                return stringUtf8;
            }

            public ByteString getAppKeyBytes() {
                Object obj = this.aD;
                if (!(obj instanceof String)) {
                    return (ByteString) obj;
                }
                ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                this.aD = copyFromUtf8;
                return copyFromUtf8;
            }

            public Event getDefaultInstanceForType() {
                return bI;
            }

            public String getDeviceId() {
                Object obj = this.aG;
                if (obj instanceof String) {
                    return (String) obj;
                }
                ByteString byteString = (ByteString) obj;
                String stringUtf8 = byteString.toStringUtf8();
                if (byteString.isValidUtf8()) {
                    this.aG = stringUtf8;
                }
                return stringUtf8;
            }

            public ByteString getDeviceIdBytes() {
                Object obj = this.aG;
                if (!(obj instanceof String)) {
                    return (ByteString) obj;
                }
                ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                this.aG = copyFromUtf8;
                return copyFromUtf8;
            }

            public String getEventId() {
                Object obj = this.bJ;
                if (obj instanceof String) {
                    return (String) obj;
                }
                ByteString byteString = (ByteString) obj;
                String stringUtf8 = byteString.toStringUtf8();
                if (byteString.isValidUtf8()) {
                    this.bJ = stringUtf8;
                }
                return stringUtf8;
            }

            public ByteString getEventIdBytes() {
                Object obj = this.bJ;
                if (!(obj instanceof String)) {
                    return (ByteString) obj;
                }
                ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                this.bJ = copyFromUtf8;
                return copyFromUtf8;
            }

            public ProtoMap getEventMap(int i) {
                return (ProtoMap) this.bL.get(i);
            }

            public int getEventMapCount() {
                return this.bL.size();
            }

            public List getEventMapList() {
                return this.bL;
            }

            public ProtoMapOrBuilder getEventMapOrBuilder(int i) {
                return (ProtoMapOrBuilder) this.bL.get(i);
            }

            public List getEventMapOrBuilderList() {
                return this.bL;
            }

            public String getLabel() {
                Object obj = this.bK;
                if (obj instanceof String) {
                    return (String) obj;
                }
                ByteString byteString = (ByteString) obj;
                String stringUtf8 = byteString.toStringUtf8();
                if (byteString.isValidUtf8()) {
                    this.bK = stringUtf8;
                }
                return stringUtf8;
            }

            public ByteString getLabelBytes() {
                Object obj = this.bK;
                if (!(obj instanceof String)) {
                    return (ByteString) obj;
                }
                ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                this.bK = copyFromUtf8;
                return copyFromUtf8;
            }

            public String getLogVersionApp() {
                Object obj = this.bj;
                if (obj instanceof String) {
                    return (String) obj;
                }
                ByteString byteString = (ByteString) obj;
                String stringUtf8 = byteString.toStringUtf8();
                if (byteString.isValidUtf8()) {
                    this.bj = stringUtf8;
                }
                return stringUtf8;
            }

            public ByteString getLogVersionAppBytes() {
                Object obj = this.bj;
                if (!(obj instanceof String)) {
                    return (ByteString) obj;
                }
                ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                this.bj = copyFromUtf8;
                return copyFromUtf8;
            }

            public String getPackageName() {
                Object obj = this.bh;
                if (obj instanceof String) {
                    return (String) obj;
                }
                ByteString byteString = (ByteString) obj;
                String stringUtf8 = byteString.toStringUtf8();
                if (byteString.isValidUtf8()) {
                    this.bh = stringUtf8;
                }
                return stringUtf8;
            }

            public ByteString getPackageNameBytes() {
                Object obj = this.bh;
                if (!(obj instanceof String)) {
                    return (ByteString) obj;
                }
                ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                this.bh = copyFromUtf8;
                return copyFromUtf8;
            }

            public Parser getParserForType() {
                return PARSER;
            }

            public String getPlatform() {
                Object obj = this.aM;
                if (obj instanceof String) {
                    return (String) obj;
                }
                ByteString byteString = (ByteString) obj;
                String stringUtf8 = byteString.toStringUtf8();
                if (byteString.isValidUtf8()) {
                    this.aM = stringUtf8;
                }
                return stringUtf8;
            }

            public ByteString getPlatformBytes() {
                Object obj = this.aM;
                if (!(obj instanceof String)) {
                    return (ByteString) obj;
                }
                ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                this.aM = copyFromUtf8;
                return copyFromUtf8;
            }

            public int getSerializedSize() {
                int i = this.ay;
                if (i != -1) {
                    return i;
                }
                int computeBytesSize = (this.aA & 1) == 1 ? CodedOutputStream.computeBytesSize(1, getTypeBytes()) + 0 : 0;
                if ((this.aA & 2) == 2) {
                    computeBytesSize += CodedOutputStream.computeBytesSize(2, getSessionIdBytes());
                }
                if ((this.aA & 4) == 4) {
                    computeBytesSize += CodedOutputStream.computeBytesSize(3, getUserIdBytes());
                }
                if ((this.aA & 8) == 8) {
                    computeBytesSize += CodedOutputStream.computeBytesSize(4, getDeviceIdBytes());
                }
                if ((this.aA & 16) == 16) {
                    computeBytesSize += CodedOutputStream.computeInt64Size(5, this.bb);
                }
                if ((this.aA & 32) == 32) {
                    computeBytesSize += CodedOutputStream.computeBytesSize(6, getEventIdBytes());
                }
                if ((this.aA & 64) == 64) {
                    computeBytesSize += CodedOutputStream.computeBytesSize(7, getLabelBytes());
                }
                for (int i2 = 0; i2 < this.bL.size(); i2++) {
                    computeBytesSize += CodedOutputStream.computeMessageSize(8, (MessageLite) this.bL.get(i2));
                }
                if ((this.aA & 128) == 128) {
                    computeBytesSize += CodedOutputStream.computeBytesSize(9, getPlatformBytes());
                }
                if ((this.aA & 256) == 256) {
                    computeBytesSize += CodedOutputStream.computeBytesSize(10, getAppKeyBytes());
                }
                if ((this.aA & 512) == 512) {
                    computeBytesSize += CodedOutputStream.computeBytesSize(11, getAppChannelBytes());
                }
                if ((this.aA & 1024) == 1024) {
                    computeBytesSize += CodedOutputStream.computeBytesSize(12, getPackageNameBytes());
                }
                if ((this.aA & 2048) == 2048) {
                    computeBytesSize += CodedOutputStream.computeBytesSize(13, getLogVersionAppBytes());
                }
                int serializedSize = computeBytesSize + getUnknownFields().getSerializedSize();
                this.ay = serializedSize;
                return serializedSize;
            }

            public String getSessionId() {
                Object obj = this.aF;
                if (obj instanceof String) {
                    return (String) obj;
                }
                ByteString byteString = (ByteString) obj;
                String stringUtf8 = byteString.toStringUtf8();
                if (byteString.isValidUtf8()) {
                    this.aF = stringUtf8;
                }
                return stringUtf8;
            }

            public ByteString getSessionIdBytes() {
                Object obj = this.aF;
                if (!(obj instanceof String)) {
                    return (ByteString) obj;
                }
                ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                this.aF = copyFromUtf8;
                return copyFromUtf8;
            }

            public long getTime() {
                return this.bb;
            }

            public String getType() {
                Object obj = this.aC;
                if (obj instanceof String) {
                    return (String) obj;
                }
                ByteString byteString = (ByteString) obj;
                String stringUtf8 = byteString.toStringUtf8();
                if (byteString.isValidUtf8()) {
                    this.aC = stringUtf8;
                }
                return stringUtf8;
            }

            public ByteString getTypeBytes() {
                Object obj = this.aC;
                if (!(obj instanceof String)) {
                    return (ByteString) obj;
                }
                ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                this.aC = copyFromUtf8;
                return copyFromUtf8;
            }

            public final UnknownFieldSet getUnknownFields() {
                return this.aw;
            }

            public String getUserId() {
                Object obj = this.aH;
                if (obj instanceof String) {
                    return (String) obj;
                }
                ByteString byteString = (ByteString) obj;
                String stringUtf8 = byteString.toStringUtf8();
                if (byteString.isValidUtf8()) {
                    this.aH = stringUtf8;
                }
                return stringUtf8;
            }

            public ByteString getUserIdBytes() {
                Object obj = this.aH;
                if (!(obj instanceof String)) {
                    return (ByteString) obj;
                }
                ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                this.aH = copyFromUtf8;
                return copyFromUtf8;
            }

            public boolean hasAppChannel() {
                return (this.aA & 512) == 512;
            }

            public boolean hasAppKey() {
                return (this.aA & 256) == 256;
            }

            public boolean hasDeviceId() {
                return (this.aA & 8) == 8;
            }

            public boolean hasEventId() {
                return (this.aA & 32) == 32;
            }

            public boolean hasLabel() {
                return (this.aA & 64) == 64;
            }

            public boolean hasLogVersionApp() {
                return (this.aA & 2048) == 2048;
            }

            public boolean hasPackageName() {
                return (this.aA & 1024) == 1024;
            }

            public boolean hasPlatform() {
                return (this.aA & 128) == 128;
            }

            public boolean hasSessionId() {
                return (this.aA & 2) == 2;
            }

            public boolean hasTime() {
                return (this.aA & 16) == 16;
            }

            public boolean hasType() {
                return (this.aA & 1) == 1;
            }

            public boolean hasUserId() {
                return (this.aA & 4) == 4;
            }

            /* access modifiers changed from: protected */
            public GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
                return ProtoMsg.X.ensureFieldAccessorsInitialized(Event.class, Builder.class);
            }

            public final boolean isInitialized() {
                byte b = this.ax;
                if (b == 1) {
                    return true;
                }
                if (b == 0) {
                    return false;
                }
                if (!hasType()) {
                    this.ax = 0;
                    return false;
                }
                for (int i = 0; i < getEventMapCount(); i++) {
                    if (!getEventMap(i).isInitialized()) {
                        this.ax = 0;
                        return false;
                    }
                }
                this.ax = 1;
                return true;
            }

            public Builder newBuilderForType() {
                return newBuilder();
            }

            /* access modifiers changed from: protected */
            public Builder newBuilderForType(GeneratedMessage.BuilderParent builderParent) {
                return new Builder(builderParent, (a) null);
            }

            public Builder toBuilder() {
                return newBuilder(this);
            }

            /* access modifiers changed from: protected */
            public Object writeReplace() {
                return super.writeReplace();
            }

            public void writeTo(CodedOutputStream codedOutputStream) {
                getSerializedSize();
                if ((this.aA & 1) == 1) {
                    codedOutputStream.writeBytes(1, getTypeBytes());
                }
                if ((this.aA & 2) == 2) {
                    codedOutputStream.writeBytes(2, getSessionIdBytes());
                }
                if ((this.aA & 4) == 4) {
                    codedOutputStream.writeBytes(3, getUserIdBytes());
                }
                if ((this.aA & 8) == 8) {
                    codedOutputStream.writeBytes(4, getDeviceIdBytes());
                }
                if ((this.aA & 16) == 16) {
                    codedOutputStream.writeInt64(5, this.bb);
                }
                if ((this.aA & 32) == 32) {
                    codedOutputStream.writeBytes(6, getEventIdBytes());
                }
                if ((this.aA & 64) == 64) {
                    codedOutputStream.writeBytes(7, getLabelBytes());
                }
                for (int i = 0; i < this.bL.size(); i++) {
                    codedOutputStream.writeMessage(8, (MessageLite) this.bL.get(i));
                }
                if ((this.aA & 128) == 128) {
                    codedOutputStream.writeBytes(9, getPlatformBytes());
                }
                if ((this.aA & 256) == 256) {
                    codedOutputStream.writeBytes(10, getAppKeyBytes());
                }
                if ((this.aA & 512) == 512) {
                    codedOutputStream.writeBytes(11, getAppChannelBytes());
                }
                if ((this.aA & 1024) == 1024) {
                    codedOutputStream.writeBytes(12, getPackageNameBytes());
                }
                if ((this.aA & 2048) == 2048) {
                    codedOutputStream.writeBytes(13, getLogVersionAppBytes());
                }
                getUnknownFields().writeTo(codedOutputStream);
            }
        }

        public final class EventMsg extends GeneratedMessage implements EventMsgOrBuilder {
            public static final int EVENT_FIELD_NUMBER = 1;
            public static Parser PARSER = new j();
            private static final EventMsg bN = new EventMsg(true);
            private final UnknownFieldSet aw;
            private byte ax;
            private int ay;
            /* access modifiers changed from: private */
            public List bO;

            public final class Builder extends GeneratedMessage.Builder implements EventMsgOrBuilder {
                private int aA;
                private List bO;
                private RepeatedFieldBuilder bP;

                private Builder() {
                    this.bO = Collections.emptyList();
                    R();
                }

                private Builder(GeneratedMessage.BuilderParent builderParent) {
                    super(builderParent);
                    this.bO = Collections.emptyList();
                    R();
                }

                /* synthetic */ Builder(GeneratedMessage.BuilderParent builderParent, a aVar) {
                    this(builderParent);
                }

                private void R() {
                    if (EventMsg.alwaysUseFieldBuilders) {
                        aL();
                    }
                }

                /* access modifiers changed from: private */
                public static Builder aJ() {
                    return new Builder();
                }

                private void aK() {
                    if ((this.aA & 1) != 1) {
                        this.bO = new ArrayList(this.bO);
                        this.aA |= 1;
                    }
                }

                private RepeatedFieldBuilder aL() {
                    if (this.bP == null) {
                        List list = this.bO;
                        boolean z = true;
                        if ((this.aA & 1) != 1) {
                            z = false;
                        }
                        this.bP = new RepeatedFieldBuilder(list, z, getParentForChildren(), isClean());
                        this.bO = null;
                    }
                    return this.bP;
                }

                public static final Descriptors.Descriptor getDescriptor() {
                    return ProtoMsg.Y;
                }

                public Builder addAllEvent(Iterable iterable) {
                    if (this.bP == null) {
                        aK();
                        AbstractMessageLite.Builder.addAll(iterable, this.bO);
                        onChanged();
                    } else {
                        this.bP.addAllMessages(iterable);
                    }
                    return this;
                }

                public Builder addEvent(int i, Event.Builder builder) {
                    if (this.bP == null) {
                        aK();
                        this.bO.add(i, builder.build());
                        onChanged();
                    } else {
                        this.bP.addMessage(i, builder.build());
                    }
                    return this;
                }

                public Builder addEvent(int i, Event event) {
                    if (this.bP != null) {
                        this.bP.addMessage(i, event);
                    } else if (event != null) {
                        aK();
                        this.bO.add(i, event);
                        onChanged();
                    } else {
                        throw new NullPointerException();
                    }
                    return this;
                }

                public Builder addEvent(Event.Builder builder) {
                    if (this.bP == null) {
                        aK();
                        this.bO.add(builder.build());
                        onChanged();
                    } else {
                        this.bP.addMessage(builder.build());
                    }
                    return this;
                }

                public Builder addEvent(Event event) {
                    if (this.bP != null) {
                        this.bP.addMessage(event);
                    } else if (event != null) {
                        aK();
                        this.bO.add(event);
                        onChanged();
                    } else {
                        throw new NullPointerException();
                    }
                    return this;
                }

                public Event.Builder addEventBuilder() {
                    return (Event.Builder) aL().addBuilder(Event.getDefaultInstance());
                }

                public Event.Builder addEventBuilder(int i) {
                    return (Event.Builder) aL().addBuilder(i, Event.getDefaultInstance());
                }

                public EventMsg build() {
                    EventMsg buildPartial = buildPartial();
                    if (buildPartial.isInitialized()) {
                        return buildPartial;
                    }
                    throw newUninitializedMessageException(buildPartial);
                }

                public EventMsg buildPartial() {
                    List list;
                    EventMsg eventMsg = new EventMsg((GeneratedMessage.Builder) this, (a) null);
                    int i = this.aA;
                    if (this.bP == null) {
                        if ((this.aA & 1) == 1) {
                            this.bO = Collections.unmodifiableList(this.bO);
                            this.aA &= -2;
                        }
                        list = this.bO;
                    } else {
                        list = this.bP.build();
                    }
                    List unused = eventMsg.bO = list;
                    onBuilt();
                    return eventMsg;
                }

                public Builder clear() {
                    super.clear();
                    if (this.bP == null) {
                        this.bO = Collections.emptyList();
                        this.aA &= -2;
                    } else {
                        this.bP.clear();
                    }
                    return this;
                }

                public Builder clearEvent() {
                    if (this.bP == null) {
                        this.bO = Collections.emptyList();
                        this.aA &= -2;
                        onChanged();
                    } else {
                        this.bP.clear();
                    }
                    return this;
                }

                public Builder clone() {
                    return aJ().mergeFrom(buildPartial());
                }

                public EventMsg getDefaultInstanceForType() {
                    return EventMsg.getDefaultInstance();
                }

                public Descriptors.Descriptor getDescriptorForType() {
                    return ProtoMsg.Y;
                }

                public Event getEvent(int i) {
                    return (Event) (this.bP == null ? this.bO.get(i) : this.bP.getMessage(i));
                }

                public Event.Builder getEventBuilder(int i) {
                    return (Event.Builder) aL().getBuilder(i);
                }

                public List getEventBuilderList() {
                    return aL().getBuilderList();
                }

                public int getEventCount() {
                    return this.bP == null ? this.bO.size() : this.bP.getCount();
                }

                public List getEventList() {
                    return this.bP == null ? Collections.unmodifiableList(this.bO) : this.bP.getMessageList();
                }

                public EventOrBuilder getEventOrBuilder(int i) {
                    return (EventOrBuilder) (this.bP == null ? this.bO.get(i) : this.bP.getMessageOrBuilder(i));
                }

                public List getEventOrBuilderList() {
                    return this.bP != null ? this.bP.getMessageOrBuilderList() : Collections.unmodifiableList(this.bO);
                }

                /* access modifiers changed from: protected */
                public GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
                    return ProtoMsg.Z.ensureFieldAccessorsInitialized(EventMsg.class, Builder.class);
                }

                public final boolean isInitialized() {
                    for (int i = 0; i < getEventCount(); i++) {
                        if (!getEvent(i).isInitialized()) {
                            return false;
                        }
                    }
                    return true;
                }

                public Builder mergeFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) {
                    EventMsg eventMsg;
                    EventMsg eventMsg2 = null;
                    try {
                        EventMsg eventMsg3 = (EventMsg) EventMsg.PARSER.parsePartialFrom(codedInputStream, extensionRegistryLite);
                        if (eventMsg3 != null) {
                            mergeFrom(eventMsg3);
                        }
                        return this;
                    } catch (InvalidProtocolBufferException e) {
                        eventMsg = (EventMsg) e.getUnfinishedMessage();
                        throw e;
                    } catch (Throwable th) {
                        th = th;
                        eventMsg2 = eventMsg;
                    }
                    if (eventMsg2 != null) {
                        mergeFrom(eventMsg2);
                    }
                    throw th;
                }

                public Builder mergeFrom(Message message) {
                    if (message instanceof EventMsg) {
                        return mergeFrom((EventMsg) message);
                    }
                    super.mergeFrom(message);
                    return this;
                }

                public Builder mergeFrom(EventMsg eventMsg) {
                    if (eventMsg == EventMsg.getDefaultInstance()) {
                        return this;
                    }
                    if (this.bP == null) {
                        if (!eventMsg.bO.isEmpty()) {
                            if (this.bO.isEmpty()) {
                                this.bO = eventMsg.bO;
                                this.aA &= -2;
                            } else {
                                aK();
                                this.bO.addAll(eventMsg.bO);
                            }
                            onChanged();
                        }
                    } else if (!eventMsg.bO.isEmpty()) {
                        if (this.bP.isEmpty()) {
                            this.bP.dispose();
                            RepeatedFieldBuilder repeatedFieldBuilder = null;
                            this.bP = null;
                            this.bO = eventMsg.bO;
                            this.aA &= -2;
                            if (EventMsg.alwaysUseFieldBuilders) {
                                repeatedFieldBuilder = aL();
                            }
                            this.bP = repeatedFieldBuilder;
                        } else {
                            this.bP.addAllMessages(eventMsg.bO);
                        }
                    }
                    mergeUnknownFields(eventMsg.getUnknownFields());
                    return this;
                }

                public Builder removeEvent(int i) {
                    if (this.bP == null) {
                        aK();
                        this.bO.remove(i);
                        onChanged();
                    } else {
                        this.bP.remove(i);
                    }
                    return this;
                }

                public Builder setEvent(int i, Event.Builder builder) {
                    if (this.bP == null) {
                        aK();
                        this.bO.set(i, builder.build());
                        onChanged();
                    } else {
                        this.bP.setMessage(i, builder.build());
                    }
                    return this;
                }

                public Builder setEvent(int i, Event event) {
                    if (this.bP != null) {
                        this.bP.setMessage(i, event);
                    } else if (event != null) {
                        aK();
                        this.bO.set(i, event);
                        onChanged();
                    } else {
                        throw new NullPointerException();
                    }
                    return this;
                }
            }

            static {
                bN.P();
            }

            private EventMsg(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) {
                this.ax = -1;
                this.ay = -1;
                P();
                UnknownFieldSet.Builder newBuilder = UnknownFieldSet.newBuilder();
                boolean z = false;
                boolean z2 = false;
                while (!z) {
                    try {
                        int readTag = codedInputStream.readTag();
                        if (readTag != 0) {
                            if (readTag == 10) {
                                if (!z2 || !true) {
                                    this.bO = new ArrayList();
                                    z2 |= true;
                                }
                                this.bO.add(codedInputStream.readMessage(Event.PARSER, extensionRegistryLite));
                            } else if (!parseUnknownField(codedInputStream, newBuilder, extensionRegistryLite, readTag)) {
                            }
                        }
                        z = true;
                    } catch (InvalidProtocolBufferException e) {
                        throw e.setUnfinishedMessage(this);
                    } catch (IOException e2) {
                        throw new InvalidProtocolBufferException(e2.getMessage()).setUnfinishedMessage(this);
                    } catch (Throwable th) {
                        if (z2 && true) {
                            this.bO = Collections.unmodifiableList(this.bO);
                        }
                        this.aw = newBuilder.build();
                        makeExtensionsImmutable();
                        throw th;
                    }
                }
                if (z2 && true) {
                    this.bO = Collections.unmodifiableList(this.bO);
                }
                this.aw = newBuilder.build();
                makeExtensionsImmutable();
            }

            /* synthetic */ EventMsg(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite, a aVar) {
                this(codedInputStream, extensionRegistryLite);
            }

            private EventMsg(GeneratedMessage.Builder builder) {
                super(builder);
                this.ax = -1;
                this.ay = -1;
                this.aw = builder.getUnknownFields();
            }

            /* synthetic */ EventMsg(GeneratedMessage.Builder builder, a aVar) {
                this(builder);
            }

            private EventMsg(boolean z) {
                this.ax = -1;
                this.ay = -1;
                this.aw = UnknownFieldSet.getDefaultInstance();
            }

            private void P() {
                this.bO = Collections.emptyList();
            }

            public static EventMsg getDefaultInstance() {
                return bN;
            }

            public static final Descriptors.Descriptor getDescriptor() {
                return ProtoMsg.Y;
            }

            public static Builder newBuilder() {
                return Builder.aJ();
            }

            public static Builder newBuilder(EventMsg eventMsg) {
                return newBuilder().mergeFrom(eventMsg);
            }

            public static EventMsg parseDelimitedFrom(InputStream inputStream) {
                return (EventMsg) PARSER.parseDelimitedFrom(inputStream);
            }

            public static EventMsg parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) {
                return (EventMsg) PARSER.parseDelimitedFrom(inputStream, extensionRegistryLite);
            }

            public static EventMsg parseFrom(ByteString byteString) {
                return (EventMsg) PARSER.parseFrom(byteString);
            }

            public static EventMsg parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) {
                return (EventMsg) PARSER.parseFrom(byteString, extensionRegistryLite);
            }

            public static EventMsg parseFrom(CodedInputStream codedInputStream) {
                return (EventMsg) PARSER.parseFrom(codedInputStream);
            }

            public static EventMsg parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) {
                return (EventMsg) PARSER.parseFrom(codedInputStream, extensionRegistryLite);
            }

            public static EventMsg parseFrom(InputStream inputStream) {
                return (EventMsg) PARSER.parseFrom(inputStream);
            }

            public static EventMsg parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) {
                return (EventMsg) PARSER.parseFrom(inputStream, extensionRegistryLite);
            }

            public static EventMsg parseFrom(byte[] bArr) {
                return (EventMsg) PARSER.parseFrom(bArr);
            }

            public static EventMsg parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) {
                return (EventMsg) PARSER.parseFrom(bArr, extensionRegistryLite);
            }

            public EventMsg getDefaultInstanceForType() {
                return bN;
            }

            public Event getEvent(int i) {
                return (Event) this.bO.get(i);
            }

            public int getEventCount() {
                return this.bO.size();
            }

            public List getEventList() {
                return this.bO;
            }

            public EventOrBuilder getEventOrBuilder(int i) {
                return (EventOrBuilder) this.bO.get(i);
            }

            public List getEventOrBuilderList() {
                return this.bO;
            }

            public Parser getParserForType() {
                return PARSER;
            }

            public int getSerializedSize() {
                int i = this.ay;
                if (i != -1) {
                    return i;
                }
                int i2 = 0;
                for (int i3 = 0; i3 < this.bO.size(); i3++) {
                    i2 += CodedOutputStream.computeMessageSize(1, (MessageLite) this.bO.get(i3));
                }
                int serializedSize = i2 + getUnknownFields().getSerializedSize();
                this.ay = serializedSize;
                return serializedSize;
            }

            public final UnknownFieldSet getUnknownFields() {
                return this.aw;
            }

            /* access modifiers changed from: protected */
            public GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
                return ProtoMsg.Z.ensureFieldAccessorsInitialized(EventMsg.class, Builder.class);
            }

            public final boolean isInitialized() {
                byte b = this.ax;
                if (b == 1) {
                    return true;
                }
                if (b == 0) {
                    return false;
                }
                for (int i = 0; i < getEventCount(); i++) {
                    if (!getEvent(i).isInitialized()) {
                        this.ax = 0;
                        return false;
                    }
                }
                this.ax = 1;
                return true;
            }

            public Builder newBuilderForType() {
                return newBuilder();
            }

            /* access modifiers changed from: protected */
            public Builder newBuilderForType(GeneratedMessage.BuilderParent builderParent) {
                return new Builder(builderParent, (a) null);
            }

            public Builder toBuilder() {
                return newBuilder(this);
            }

            /* access modifiers changed from: protected */
            public Object writeReplace() {
                return super.writeReplace();
            }

            public void writeTo(CodedOutputStream codedOutputStream) {
                getSerializedSize();
                for (int i = 0; i < this.bO.size(); i++) {
                    codedOutputStream.writeMessage(1, (MessageLite) this.bO.get(i));
                }
                getUnknownFields().writeTo(codedOutputStream);
            }
        }

        public interface EventMsgOrBuilder extends MessageOrBuilder {
            Event getEvent(int i);

            int getEventCount();

            List getEventList();

            EventOrBuilder getEventOrBuilder(int i);

            List getEventOrBuilderList();
        }

        public interface EventOrBuilder extends MessageOrBuilder {
            String getAppChannel();

            ByteString getAppChannelBytes();

            String getAppKey();

            ByteString getAppKeyBytes();

            String getDeviceId();

            ByteString getDeviceIdBytes();

            String getEventId();

            ByteString getEventIdBytes();

            ProtoMap getEventMap(int i);

            int getEventMapCount();

            List getEventMapList();

            ProtoMapOrBuilder getEventMapOrBuilder(int i);

            List getEventMapOrBuilderList();

            String getLabel();

            ByteString getLabelBytes();

            String getLogVersionApp();

            ByteString getLogVersionAppBytes();

            String getPackageName();

            ByteString getPackageNameBytes();

            String getPlatform();

            ByteString getPlatformBytes();

            String getSessionId();

            ByteString getSessionIdBytes();

            long getTime();

            String getType();

            ByteString getTypeBytes();

            String getUserId();

            ByteString getUserIdBytes();

            boolean hasAppChannel();

            boolean hasAppKey();

            boolean hasDeviceId();

            boolean hasEventId();

            boolean hasLabel();

            boolean hasLogVersionApp();

            boolean hasPackageName();

            boolean hasPlatform();

            boolean hasSessionId();

            boolean hasTime();

            boolean hasType();

            boolean hasUserId();
        }

        public enum NetworkType implements ProtocolMessageEnum {
            NETWORK_UNKNOWN(0, 0),
            NETWORK_WIFI(1, 1),
            NETWORK_2G(2, 2),
            NETWORK_3G(3, 3),
            NETWORK_4G(4, 4),
            NETWORK_BLUETOOTH(5, 5),
            NETWORK_OTHERS(6, 6);
            
            public static final int NETWORK_2G_VALUE = 2;
            public static final int NETWORK_3G_VALUE = 3;
            public static final int NETWORK_4G_VALUE = 4;
            public static final int NETWORK_BLUETOOTH_VALUE = 5;
            public static final int NETWORK_OTHERS_VALUE = 6;
            public static final int NETWORK_UNKNOWN_VALUE = 0;
            public static final int NETWORK_WIFI_VALUE = 1;
            private static Internal.EnumLiteMap bQ;
            private static final NetworkType[] bR = null;
            private final int index;
            private final int value;

            static {
                bQ = new k();
                bR = values();
            }

            private NetworkType(int i, int i2) {
                this.index = i;
                this.value = i2;
            }

            public static final Descriptors.EnumDescriptor getDescriptor() {
                return StatsMsg.getDescriptor().getEnumTypes().get(1);
            }

            public static Internal.EnumLiteMap internalGetValueMap() {
                return bQ;
            }

            public static NetworkType valueOf(int i) {
                switch (i) {
                    case 0:
                        return NETWORK_UNKNOWN;
                    case 1:
                        return NETWORK_WIFI;
                    case 2:
                        return NETWORK_2G;
                    case 3:
                        return NETWORK_3G;
                    case 4:
                        return NETWORK_4G;
                    case 5:
                        return NETWORK_BLUETOOTH;
                    case 6:
                        return NETWORK_OTHERS;
                    default:
                        return null;
                }
            }

            public static NetworkType valueOf(Descriptors.EnumValueDescriptor enumValueDescriptor) {
                if (enumValueDescriptor.getType() == getDescriptor()) {
                    return bR[enumValueDescriptor.getIndex()];
                }
                throw new IllegalArgumentException("EnumValueDescriptor is not for this type.");
            }

            public final Descriptors.EnumDescriptor getDescriptorForType() {
                return getDescriptor();
            }

            public final int getNumber() {
                return this.value;
            }

            public final Descriptors.EnumValueDescriptor getValueDescriptor() {
                return getDescriptor().getValues().get(this.index);
            }
        }

        public final class Page extends GeneratedMessage implements PageOrBuilder {
            public static final int APP_CHANNEL_FIELD_NUMBER = 12;
            public static final int APP_KEY_FIELD_NUMBER = 11;
            public static final int CELL_ID_FIELD_NUMBER = 21;
            public static final int DEVICE_ID_FIELD_NUMBER = 4;
            public static final int DEVICE_NAME_FIELD_NUMBER = 19;
            public static final int DURATION_FIELD_NUMBER = 7;
            public static final int END_MILLS_FIELD_NUMBER = 6;
            public static final int LOG_VERSION_APP_FIELD_NUMBER = 14;
            public static final int MCC_MNC_FIELD_NUMBER = 20;
            public static final int OS_VERSION_FIELD_NUMBER = 18;
            public static final int PACKAGE_NAME_FIELD_NUMBER = 13;
            public static final int PAGE_ID_FIELD_NUMBER = 8;
            public static final int PAGE_MAP_FIELD_NUMBER = 9;
            public static Parser PARSER = new l();
            public static final int PLATFORM_FIELD_NUMBER = 10;
            public static final int SDK_VERSON_FIELD_NUMBER = 17;
            public static final int SESSION_ID_FIELD_NUMBER = 2;
            public static final int START_MILLS_FIELD_NUMBER = 5;
            public static final int TYPE_FIELD_NUMBER = 1;
            public static final int USER_ID_FIELD_NUMBER = 3;
            public static final int VERSION_CODE_FIELD_NUMBER = 15;
            public static final int VERSION_NAME_FIELD_NUMBER = 16;
            private static final Page bT = new Page(true);
            /* access modifiers changed from: private */
            public int aA;
            /* access modifiers changed from: private */
            public Object aC;
            /* access modifiers changed from: private */
            public Object aD;
            /* access modifiers changed from: private */
            public Object aE;
            /* access modifiers changed from: private */
            public Object aF;
            /* access modifiers changed from: private */
            public Object aG;
            /* access modifiers changed from: private */
            public Object aH;
            /* access modifiers changed from: private */
            public int aI;
            /* access modifiers changed from: private */
            public Object aJ;
            /* access modifiers changed from: private */
            public Object aK;
            /* access modifiers changed from: private */
            public Object aL;
            /* access modifiers changed from: private */
            public Object aM;
            /* access modifiers changed from: private */
            public Object aP;
            /* access modifiers changed from: private */
            public Object aU;
            /* access modifiers changed from: private */
            public Object aV;
            private final UnknownFieldSet aw;
            private byte ax;
            private int ay;
            /* access modifiers changed from: private */
            public long bU;
            /* access modifiers changed from: private */
            public long bV;
            /* access modifiers changed from: private */
            public long bW;
            /* access modifiers changed from: private */
            public Object bX;
            /* access modifiers changed from: private */
            public List bY;
            /* access modifiers changed from: private */
            public Object bh;
            /* access modifiers changed from: private */
            public Object bj;

            public final class Builder extends GeneratedMessage.Builder implements PageOrBuilder {
                private int aA;
                private Object aC;
                private Object aD;
                private Object aE;
                private Object aF;
                private Object aG;
                private Object aH;
                private int aI;
                private Object aJ;
                private Object aK;
                private Object aL;
                private Object aM;
                private Object aP;
                private Object aU;
                private Object aV;
                private long bU;
                private long bV;
                private long bW;
                private Object bX;
                private List bY;
                private RepeatedFieldBuilder bZ;
                private Object bh;
                private Object bj;

                private Builder() {
                    this.aC = "";
                    this.aF = "";
                    this.aH = "";
                    this.aG = "";
                    this.bX = "";
                    this.bY = Collections.emptyList();
                    this.aM = "";
                    this.aD = "";
                    this.aE = "";
                    this.bh = "";
                    this.bj = "";
                    this.aJ = "";
                    this.aK = "";
                    this.aL = "";
                    this.aP = "";
                    this.aU = "";
                    this.aV = "";
                    R();
                }

                private Builder(GeneratedMessage.BuilderParent builderParent) {
                    super(builderParent);
                    this.aC = "";
                    this.aF = "";
                    this.aH = "";
                    this.aG = "";
                    this.bX = "";
                    this.bY = Collections.emptyList();
                    this.aM = "";
                    this.aD = "";
                    this.aE = "";
                    this.bh = "";
                    this.bj = "";
                    this.aJ = "";
                    this.aK = "";
                    this.aL = "";
                    this.aP = "";
                    this.aU = "";
                    this.aV = "";
                    R();
                }

                /* synthetic */ Builder(GeneratedMessage.BuilderParent builderParent, a aVar) {
                    this(builderParent);
                }

                private void R() {
                    if (Page.alwaysUseFieldBuilders) {
                        aR();
                    }
                }

                /* access modifiers changed from: private */
                public static Builder aP() {
                    return new Builder();
                }

                private void aQ() {
                    if ((this.aA & 256) != 256) {
                        this.bY = new ArrayList(this.bY);
                        this.aA |= 256;
                    }
                }

                private RepeatedFieldBuilder aR() {
                    if (this.bZ == null) {
                        this.bZ = new RepeatedFieldBuilder(this.bY, (this.aA & 256) == 256, getParentForChildren(), isClean());
                        this.bY = null;
                    }
                    return this.bZ;
                }

                public static final Descriptors.Descriptor getDescriptor() {
                    return ProtoMsg.S;
                }

                public Builder addAllPageMap(Iterable iterable) {
                    if (this.bZ == null) {
                        aQ();
                        AbstractMessageLite.Builder.addAll(iterable, this.bY);
                        onChanged();
                    } else {
                        this.bZ.addAllMessages(iterable);
                    }
                    return this;
                }

                public Builder addPageMap(int i, ProtoMap.Builder builder) {
                    if (this.bZ == null) {
                        aQ();
                        this.bY.add(i, builder.build());
                        onChanged();
                    } else {
                        this.bZ.addMessage(i, builder.build());
                    }
                    return this;
                }

                public Builder addPageMap(int i, ProtoMap protoMap) {
                    if (this.bZ != null) {
                        this.bZ.addMessage(i, protoMap);
                    } else if (protoMap != null) {
                        aQ();
                        this.bY.add(i, protoMap);
                        onChanged();
                    } else {
                        throw new NullPointerException();
                    }
                    return this;
                }

                public Builder addPageMap(ProtoMap.Builder builder) {
                    if (this.bZ == null) {
                        aQ();
                        this.bY.add(builder.build());
                        onChanged();
                    } else {
                        this.bZ.addMessage(builder.build());
                    }
                    return this;
                }

                public Builder addPageMap(ProtoMap protoMap) {
                    if (this.bZ != null) {
                        this.bZ.addMessage(protoMap);
                    } else if (protoMap != null) {
                        aQ();
                        this.bY.add(protoMap);
                        onChanged();
                    } else {
                        throw new NullPointerException();
                    }
                    return this;
                }

                public ProtoMap.Builder addPageMapBuilder() {
                    return (ProtoMap.Builder) aR().addBuilder(ProtoMap.getDefaultInstance());
                }

                public ProtoMap.Builder addPageMapBuilder(int i) {
                    return (ProtoMap.Builder) aR().addBuilder(i, ProtoMap.getDefaultInstance());
                }

                public Page build() {
                    Page buildPartial = buildPartial();
                    if (buildPartial.isInitialized()) {
                        return buildPartial;
                    }
                    throw newUninitializedMessageException(buildPartial);
                }

                public Page buildPartial() {
                    List list;
                    Page page = new Page((GeneratedMessage.Builder) this, (a) null);
                    int i = this.aA;
                    int i2 = 1;
                    if ((i & 1) != 1) {
                        i2 = 0;
                    }
                    Object unused = page.aC = this.aC;
                    if ((i & 2) == 2) {
                        i2 |= 2;
                    }
                    Object unused2 = page.aF = this.aF;
                    if ((i & 4) == 4) {
                        i2 |= 4;
                    }
                    Object unused3 = page.aH = this.aH;
                    if ((i & 8) == 8) {
                        i2 |= 8;
                    }
                    Object unused4 = page.aG = this.aG;
                    if ((i & 16) == 16) {
                        i2 |= 16;
                    }
                    long unused5 = page.bU = this.bU;
                    if ((i & 32) == 32) {
                        i2 |= 32;
                    }
                    long unused6 = page.bV = this.bV;
                    if ((i & 64) == 64) {
                        i2 |= 64;
                    }
                    long unused7 = page.bW = this.bW;
                    if ((i & 128) == 128) {
                        i2 |= 128;
                    }
                    Object unused8 = page.bX = this.bX;
                    if (this.bZ == null) {
                        if ((this.aA & 256) == 256) {
                            this.bY = Collections.unmodifiableList(this.bY);
                            this.aA &= -257;
                        }
                        list = this.bY;
                    } else {
                        list = this.bZ.build();
                    }
                    List unused9 = page.bY = list;
                    if ((i & 512) == 512) {
                        i2 |= 256;
                    }
                    Object unused10 = page.aM = this.aM;
                    if ((i & 1024) == 1024) {
                        i2 |= 512;
                    }
                    Object unused11 = page.aD = this.aD;
                    if ((i & 2048) == 2048) {
                        i2 |= 1024;
                    }
                    Object unused12 = page.aE = this.aE;
                    if ((i & 4096) == 4096) {
                        i2 |= 2048;
                    }
                    Object unused13 = page.bh = this.bh;
                    if ((i & 8192) == 8192) {
                        i2 |= 4096;
                    }
                    Object unused14 = page.bj = this.bj;
                    if ((i & 16384) == 16384) {
                        i2 |= 8192;
                    }
                    int unused15 = page.aI = this.aI;
                    if ((32768 & i) == 32768) {
                        i2 |= 16384;
                    }
                    Object unused16 = page.aJ = this.aJ;
                    if ((65536 & i) == 65536) {
                        i2 |= 32768;
                    }
                    Object unused17 = page.aK = this.aK;
                    if ((131072 & i) == 131072) {
                        i2 |= 65536;
                    }
                    Object unused18 = page.aL = this.aL;
                    if ((262144 & i) == 262144) {
                        i2 |= 131072;
                    }
                    Object unused19 = page.aP = this.aP;
                    if ((524288 & i) == 524288) {
                        i2 |= 262144;
                    }
                    Object unused20 = page.aU = this.aU;
                    if ((i & 1048576) == 1048576) {
                        i2 |= 524288;
                    }
                    Object unused21 = page.aV = this.aV;
                    int unused22 = page.aA = i2;
                    onBuilt();
                    return page;
                }

                public Builder clear() {
                    super.clear();
                    this.aC = "";
                    this.aA &= -2;
                    this.aF = "";
                    this.aA &= -3;
                    this.aH = "";
                    this.aA &= -5;
                    this.aG = "";
                    this.aA &= -9;
                    this.bU = 0;
                    this.aA &= -17;
                    this.bV = 0;
                    this.aA &= -33;
                    this.bW = 0;
                    this.aA &= -65;
                    this.bX = "";
                    this.aA &= -129;
                    if (this.bZ == null) {
                        this.bY = Collections.emptyList();
                        this.aA &= -257;
                    } else {
                        this.bZ.clear();
                    }
                    this.aM = "";
                    this.aA &= -513;
                    this.aD = "";
                    this.aA &= -1025;
                    this.aE = "";
                    this.aA &= -2049;
                    this.bh = "";
                    this.aA &= -4097;
                    this.bj = "";
                    this.aA &= -8193;
                    this.aI = 0;
                    this.aA &= -16385;
                    this.aJ = "";
                    this.aA &= -32769;
                    this.aK = "";
                    this.aA &= -65537;
                    this.aL = "";
                    this.aA &= -131073;
                    this.aP = "";
                    this.aA &= -262145;
                    this.aU = "";
                    this.aA &= -524289;
                    this.aV = "";
                    this.aA &= -1048577;
                    return this;
                }

                public Builder clearAppChannel() {
                    this.aA &= -2049;
                    this.aE = Page.getDefaultInstance().getAppChannel();
                    onChanged();
                    return this;
                }

                public Builder clearAppKey() {
                    this.aA &= -1025;
                    this.aD = Page.getDefaultInstance().getAppKey();
                    onChanged();
                    return this;
                }

                public Builder clearCellId() {
                    this.aA &= -1048577;
                    this.aV = Page.getDefaultInstance().getCellId();
                    onChanged();
                    return this;
                }

                public Builder clearDeviceId() {
                    this.aA &= -9;
                    this.aG = Page.getDefaultInstance().getDeviceId();
                    onChanged();
                    return this;
                }

                public Builder clearDeviceName() {
                    this.aA &= -262145;
                    this.aP = Page.getDefaultInstance().getDeviceName();
                    onChanged();
                    return this;
                }

                public Builder clearDuration() {
                    this.aA &= -65;
                    this.bW = 0;
                    onChanged();
                    return this;
                }

                public Builder clearEndMills() {
                    this.aA &= -33;
                    this.bV = 0;
                    onChanged();
                    return this;
                }

                public Builder clearLogVersionApp() {
                    this.aA &= -8193;
                    this.bj = Page.getDefaultInstance().getLogVersionApp();
                    onChanged();
                    return this;
                }

                public Builder clearMccMnc() {
                    this.aA &= -524289;
                    this.aU = Page.getDefaultInstance().getMccMnc();
                    onChanged();
                    return this;
                }

                public Builder clearOsVersion() {
                    this.aA &= -131073;
                    this.aL = Page.getDefaultInstance().getOsVersion();
                    onChanged();
                    return this;
                }

                public Builder clearPackageName() {
                    this.aA &= -4097;
                    this.bh = Page.getDefaultInstance().getPackageName();
                    onChanged();
                    return this;
                }

                public Builder clearPageId() {
                    this.aA &= -129;
                    this.bX = Page.getDefaultInstance().getPageId();
                    onChanged();
                    return this;
                }

                public Builder clearPageMap() {
                    if (this.bZ == null) {
                        this.bY = Collections.emptyList();
                        this.aA &= -257;
                        onChanged();
                    } else {
                        this.bZ.clear();
                    }
                    return this;
                }

                public Builder clearPlatform() {
                    this.aA &= -513;
                    this.aM = Page.getDefaultInstance().getPlatform();
                    onChanged();
                    return this;
                }

                public Builder clearSdkVerson() {
                    this.aA &= -65537;
                    this.aK = Page.getDefaultInstance().getSdkVerson();
                    onChanged();
                    return this;
                }

                public Builder clearSessionId() {
                    this.aA &= -3;
                    this.aF = Page.getDefaultInstance().getSessionId();
                    onChanged();
                    return this;
                }

                public Builder clearStartMills() {
                    this.aA &= -17;
                    this.bU = 0;
                    onChanged();
                    return this;
                }

                public Builder clearType() {
                    this.aA &= -2;
                    this.aC = Page.getDefaultInstance().getType();
                    onChanged();
                    return this;
                }

                public Builder clearUserId() {
                    this.aA &= -5;
                    this.aH = Page.getDefaultInstance().getUserId();
                    onChanged();
                    return this;
                }

                public Builder clearVersionCode() {
                    this.aA &= -16385;
                    this.aI = 0;
                    onChanged();
                    return this;
                }

                public Builder clearVersionName() {
                    this.aA &= -32769;
                    this.aJ = Page.getDefaultInstance().getVersionName();
                    onChanged();
                    return this;
                }

                public Builder clone() {
                    return aP().mergeFrom(buildPartial());
                }

                public String getAppChannel() {
                    Object obj = this.aE;
                    if (obj instanceof String) {
                        return (String) obj;
                    }
                    ByteString byteString = (ByteString) obj;
                    String stringUtf8 = byteString.toStringUtf8();
                    if (byteString.isValidUtf8()) {
                        this.aE = stringUtf8;
                    }
                    return stringUtf8;
                }

                public ByteString getAppChannelBytes() {
                    Object obj = this.aE;
                    if (!(obj instanceof String)) {
                        return (ByteString) obj;
                    }
                    ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                    this.aE = copyFromUtf8;
                    return copyFromUtf8;
                }

                public String getAppKey() {
                    Object obj = this.aD;
                    if (obj instanceof String) {
                        return (String) obj;
                    }
                    ByteString byteString = (ByteString) obj;
                    String stringUtf8 = byteString.toStringUtf8();
                    if (byteString.isValidUtf8()) {
                        this.aD = stringUtf8;
                    }
                    return stringUtf8;
                }

                public ByteString getAppKeyBytes() {
                    Object obj = this.aD;
                    if (!(obj instanceof String)) {
                        return (ByteString) obj;
                    }
                    ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                    this.aD = copyFromUtf8;
                    return copyFromUtf8;
                }

                public String getCellId() {
                    Object obj = this.aV;
                    if (obj instanceof String) {
                        return (String) obj;
                    }
                    ByteString byteString = (ByteString) obj;
                    String stringUtf8 = byteString.toStringUtf8();
                    if (byteString.isValidUtf8()) {
                        this.aV = stringUtf8;
                    }
                    return stringUtf8;
                }

                public ByteString getCellIdBytes() {
                    Object obj = this.aV;
                    if (!(obj instanceof String)) {
                        return (ByteString) obj;
                    }
                    ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                    this.aV = copyFromUtf8;
                    return copyFromUtf8;
                }

                public Page getDefaultInstanceForType() {
                    return Page.getDefaultInstance();
                }

                public Descriptors.Descriptor getDescriptorForType() {
                    return ProtoMsg.S;
                }

                public String getDeviceId() {
                    Object obj = this.aG;
                    if (obj instanceof String) {
                        return (String) obj;
                    }
                    ByteString byteString = (ByteString) obj;
                    String stringUtf8 = byteString.toStringUtf8();
                    if (byteString.isValidUtf8()) {
                        this.aG = stringUtf8;
                    }
                    return stringUtf8;
                }

                public ByteString getDeviceIdBytes() {
                    Object obj = this.aG;
                    if (!(obj instanceof String)) {
                        return (ByteString) obj;
                    }
                    ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                    this.aG = copyFromUtf8;
                    return copyFromUtf8;
                }

                public String getDeviceName() {
                    Object obj = this.aP;
                    if (obj instanceof String) {
                        return (String) obj;
                    }
                    ByteString byteString = (ByteString) obj;
                    String stringUtf8 = byteString.toStringUtf8();
                    if (byteString.isValidUtf8()) {
                        this.aP = stringUtf8;
                    }
                    return stringUtf8;
                }

                public ByteString getDeviceNameBytes() {
                    Object obj = this.aP;
                    if (!(obj instanceof String)) {
                        return (ByteString) obj;
                    }
                    ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                    this.aP = copyFromUtf8;
                    return copyFromUtf8;
                }

                public long getDuration() {
                    return this.bW;
                }

                public long getEndMills() {
                    return this.bV;
                }

                public String getLogVersionApp() {
                    Object obj = this.bj;
                    if (obj instanceof String) {
                        return (String) obj;
                    }
                    ByteString byteString = (ByteString) obj;
                    String stringUtf8 = byteString.toStringUtf8();
                    if (byteString.isValidUtf8()) {
                        this.bj = stringUtf8;
                    }
                    return stringUtf8;
                }

                public ByteString getLogVersionAppBytes() {
                    Object obj = this.bj;
                    if (!(obj instanceof String)) {
                        return (ByteString) obj;
                    }
                    ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                    this.bj = copyFromUtf8;
                    return copyFromUtf8;
                }

                public String getMccMnc() {
                    Object obj = this.aU;
                    if (obj instanceof String) {
                        return (String) obj;
                    }
                    ByteString byteString = (ByteString) obj;
                    String stringUtf8 = byteString.toStringUtf8();
                    if (byteString.isValidUtf8()) {
                        this.aU = stringUtf8;
                    }
                    return stringUtf8;
                }

                public ByteString getMccMncBytes() {
                    Object obj = this.aU;
                    if (!(obj instanceof String)) {
                        return (ByteString) obj;
                    }
                    ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                    this.aU = copyFromUtf8;
                    return copyFromUtf8;
                }

                public String getOsVersion() {
                    Object obj = this.aL;
                    if (obj instanceof String) {
                        return (String) obj;
                    }
                    ByteString byteString = (ByteString) obj;
                    String stringUtf8 = byteString.toStringUtf8();
                    if (byteString.isValidUtf8()) {
                        this.aL = stringUtf8;
                    }
                    return stringUtf8;
                }

                public ByteString getOsVersionBytes() {
                    Object obj = this.aL;
                    if (!(obj instanceof String)) {
                        return (ByteString) obj;
                    }
                    ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                    this.aL = copyFromUtf8;
                    return copyFromUtf8;
                }

                public String getPackageName() {
                    Object obj = this.bh;
                    if (obj instanceof String) {
                        return (String) obj;
                    }
                    ByteString byteString = (ByteString) obj;
                    String stringUtf8 = byteString.toStringUtf8();
                    if (byteString.isValidUtf8()) {
                        this.bh = stringUtf8;
                    }
                    return stringUtf8;
                }

                public ByteString getPackageNameBytes() {
                    Object obj = this.bh;
                    if (!(obj instanceof String)) {
                        return (ByteString) obj;
                    }
                    ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                    this.bh = copyFromUtf8;
                    return copyFromUtf8;
                }

                public String getPageId() {
                    Object obj = this.bX;
                    if (obj instanceof String) {
                        return (String) obj;
                    }
                    ByteString byteString = (ByteString) obj;
                    String stringUtf8 = byteString.toStringUtf8();
                    if (byteString.isValidUtf8()) {
                        this.bX = stringUtf8;
                    }
                    return stringUtf8;
                }

                public ByteString getPageIdBytes() {
                    Object obj = this.bX;
                    if (!(obj instanceof String)) {
                        return (ByteString) obj;
                    }
                    ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                    this.bX = copyFromUtf8;
                    return copyFromUtf8;
                }

                public ProtoMap getPageMap(int i) {
                    return (ProtoMap) (this.bZ == null ? this.bY.get(i) : this.bZ.getMessage(i));
                }

                public ProtoMap.Builder getPageMapBuilder(int i) {
                    return (ProtoMap.Builder) aR().getBuilder(i);
                }

                public List getPageMapBuilderList() {
                    return aR().getBuilderList();
                }

                public int getPageMapCount() {
                    return this.bZ == null ? this.bY.size() : this.bZ.getCount();
                }

                public List getPageMapList() {
                    return this.bZ == null ? Collections.unmodifiableList(this.bY) : this.bZ.getMessageList();
                }

                public ProtoMapOrBuilder getPageMapOrBuilder(int i) {
                    return (ProtoMapOrBuilder) (this.bZ == null ? this.bY.get(i) : this.bZ.getMessageOrBuilder(i));
                }

                public List getPageMapOrBuilderList() {
                    return this.bZ != null ? this.bZ.getMessageOrBuilderList() : Collections.unmodifiableList(this.bY);
                }

                public String getPlatform() {
                    Object obj = this.aM;
                    if (obj instanceof String) {
                        return (String) obj;
                    }
                    ByteString byteString = (ByteString) obj;
                    String stringUtf8 = byteString.toStringUtf8();
                    if (byteString.isValidUtf8()) {
                        this.aM = stringUtf8;
                    }
                    return stringUtf8;
                }

                public ByteString getPlatformBytes() {
                    Object obj = this.aM;
                    if (!(obj instanceof String)) {
                        return (ByteString) obj;
                    }
                    ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                    this.aM = copyFromUtf8;
                    return copyFromUtf8;
                }

                public String getSdkVerson() {
                    Object obj = this.aK;
                    if (obj instanceof String) {
                        return (String) obj;
                    }
                    ByteString byteString = (ByteString) obj;
                    String stringUtf8 = byteString.toStringUtf8();
                    if (byteString.isValidUtf8()) {
                        this.aK = stringUtf8;
                    }
                    return stringUtf8;
                }

                public ByteString getSdkVersonBytes() {
                    Object obj = this.aK;
                    if (!(obj instanceof String)) {
                        return (ByteString) obj;
                    }
                    ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                    this.aK = copyFromUtf8;
                    return copyFromUtf8;
                }

                public String getSessionId() {
                    Object obj = this.aF;
                    if (obj instanceof String) {
                        return (String) obj;
                    }
                    ByteString byteString = (ByteString) obj;
                    String stringUtf8 = byteString.toStringUtf8();
                    if (byteString.isValidUtf8()) {
                        this.aF = stringUtf8;
                    }
                    return stringUtf8;
                }

                public ByteString getSessionIdBytes() {
                    Object obj = this.aF;
                    if (!(obj instanceof String)) {
                        return (ByteString) obj;
                    }
                    ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                    this.aF = copyFromUtf8;
                    return copyFromUtf8;
                }

                public long getStartMills() {
                    return this.bU;
                }

                public String getType() {
                    Object obj = this.aC;
                    if (obj instanceof String) {
                        return (String) obj;
                    }
                    ByteString byteString = (ByteString) obj;
                    String stringUtf8 = byteString.toStringUtf8();
                    if (byteString.isValidUtf8()) {
                        this.aC = stringUtf8;
                    }
                    return stringUtf8;
                }

                public ByteString getTypeBytes() {
                    Object obj = this.aC;
                    if (!(obj instanceof String)) {
                        return (ByteString) obj;
                    }
                    ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                    this.aC = copyFromUtf8;
                    return copyFromUtf8;
                }

                public String getUserId() {
                    Object obj = this.aH;
                    if (obj instanceof String) {
                        return (String) obj;
                    }
                    ByteString byteString = (ByteString) obj;
                    String stringUtf8 = byteString.toStringUtf8();
                    if (byteString.isValidUtf8()) {
                        this.aH = stringUtf8;
                    }
                    return stringUtf8;
                }

                public ByteString getUserIdBytes() {
                    Object obj = this.aH;
                    if (!(obj instanceof String)) {
                        return (ByteString) obj;
                    }
                    ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                    this.aH = copyFromUtf8;
                    return copyFromUtf8;
                }

                public int getVersionCode() {
                    return this.aI;
                }

                public String getVersionName() {
                    Object obj = this.aJ;
                    if (obj instanceof String) {
                        return (String) obj;
                    }
                    ByteString byteString = (ByteString) obj;
                    String stringUtf8 = byteString.toStringUtf8();
                    if (byteString.isValidUtf8()) {
                        this.aJ = stringUtf8;
                    }
                    return stringUtf8;
                }

                public ByteString getVersionNameBytes() {
                    Object obj = this.aJ;
                    if (!(obj instanceof String)) {
                        return (ByteString) obj;
                    }
                    ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                    this.aJ = copyFromUtf8;
                    return copyFromUtf8;
                }

                public boolean hasAppChannel() {
                    return (this.aA & 2048) == 2048;
                }

                public boolean hasAppKey() {
                    return (this.aA & 1024) == 1024;
                }

                public boolean hasCellId() {
                    return (this.aA & 1048576) == 1048576;
                }

                public boolean hasDeviceId() {
                    return (this.aA & 8) == 8;
                }

                public boolean hasDeviceName() {
                    return (this.aA & 262144) == 262144;
                }

                public boolean hasDuration() {
                    return (this.aA & 64) == 64;
                }

                public boolean hasEndMills() {
                    return (this.aA & 32) == 32;
                }

                public boolean hasLogVersionApp() {
                    return (this.aA & 8192) == 8192;
                }

                public boolean hasMccMnc() {
                    return (this.aA & 524288) == 524288;
                }

                public boolean hasOsVersion() {
                    return (this.aA & 131072) == 131072;
                }

                public boolean hasPackageName() {
                    return (this.aA & 4096) == 4096;
                }

                public boolean hasPageId() {
                    return (this.aA & 128) == 128;
                }

                public boolean hasPlatform() {
                    return (this.aA & 512) == 512;
                }

                public boolean hasSdkVerson() {
                    return (this.aA & 65536) == 65536;
                }

                public boolean hasSessionId() {
                    return (this.aA & 2) == 2;
                }

                public boolean hasStartMills() {
                    return (this.aA & 16) == 16;
                }

                public boolean hasType() {
                    return (this.aA & 1) == 1;
                }

                public boolean hasUserId() {
                    return (this.aA & 4) == 4;
                }

                public boolean hasVersionCode() {
                    return (this.aA & 16384) == 16384;
                }

                public boolean hasVersionName() {
                    return (this.aA & 32768) == 32768;
                }

                /* access modifiers changed from: protected */
                public GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
                    return ProtoMsg.T.ensureFieldAccessorsInitialized(Page.class, Builder.class);
                }

                public final boolean isInitialized() {
                    if (!hasType()) {
                        return false;
                    }
                    for (int i = 0; i < getPageMapCount(); i++) {
                        if (!getPageMap(i).isInitialized()) {
                            return false;
                        }
                    }
                    return true;
                }

                public Builder mergeFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) {
                    Page page;
                    Page page2 = null;
                    try {
                        Page page3 = (Page) Page.PARSER.parsePartialFrom(codedInputStream, extensionRegistryLite);
                        if (page3 != null) {
                            mergeFrom(page3);
                        }
                        return this;
                    } catch (InvalidProtocolBufferException e) {
                        page = (Page) e.getUnfinishedMessage();
                        throw e;
                    } catch (Throwable th) {
                        th = th;
                        page2 = page;
                    }
                    if (page2 != null) {
                        mergeFrom(page2);
                    }
                    throw th;
                }

                public Builder mergeFrom(Message message) {
                    if (message instanceof Page) {
                        return mergeFrom((Page) message);
                    }
                    super.mergeFrom(message);
                    return this;
                }

                public Builder mergeFrom(Page page) {
                    if (page == Page.getDefaultInstance()) {
                        return this;
                    }
                    if (page.hasType()) {
                        this.aA |= 1;
                        this.aC = page.aC;
                        onChanged();
                    }
                    if (page.hasSessionId()) {
                        this.aA |= 2;
                        this.aF = page.aF;
                        onChanged();
                    }
                    if (page.hasUserId()) {
                        this.aA |= 4;
                        this.aH = page.aH;
                        onChanged();
                    }
                    if (page.hasDeviceId()) {
                        this.aA |= 8;
                        this.aG = page.aG;
                        onChanged();
                    }
                    if (page.hasStartMills()) {
                        setStartMills(page.getStartMills());
                    }
                    if (page.hasEndMills()) {
                        setEndMills(page.getEndMills());
                    }
                    if (page.hasDuration()) {
                        setDuration(page.getDuration());
                    }
                    if (page.hasPageId()) {
                        this.aA |= 128;
                        this.bX = page.bX;
                        onChanged();
                    }
                    if (this.bZ == null) {
                        if (!page.bY.isEmpty()) {
                            if (this.bY.isEmpty()) {
                                this.bY = page.bY;
                                this.aA &= -257;
                            } else {
                                aQ();
                                this.bY.addAll(page.bY);
                            }
                            onChanged();
                        }
                    } else if (!page.bY.isEmpty()) {
                        if (this.bZ.isEmpty()) {
                            this.bZ.dispose();
                            RepeatedFieldBuilder repeatedFieldBuilder = null;
                            this.bZ = null;
                            this.bY = page.bY;
                            this.aA &= -257;
                            if (Page.alwaysUseFieldBuilders) {
                                repeatedFieldBuilder = aR();
                            }
                            this.bZ = repeatedFieldBuilder;
                        } else {
                            this.bZ.addAllMessages(page.bY);
                        }
                    }
                    if (page.hasPlatform()) {
                        this.aA |= 512;
                        this.aM = page.aM;
                        onChanged();
                    }
                    if (page.hasAppKey()) {
                        this.aA |= 1024;
                        this.aD = page.aD;
                        onChanged();
                    }
                    if (page.hasAppChannel()) {
                        this.aA |= 2048;
                        this.aE = page.aE;
                        onChanged();
                    }
                    if (page.hasPackageName()) {
                        this.aA |= 4096;
                        this.bh = page.bh;
                        onChanged();
                    }
                    if (page.hasLogVersionApp()) {
                        this.aA |= 8192;
                        this.bj = page.bj;
                        onChanged();
                    }
                    if (page.hasVersionCode()) {
                        setVersionCode(page.getVersionCode());
                    }
                    if (page.hasVersionName()) {
                        this.aA |= 32768;
                        this.aJ = page.aJ;
                        onChanged();
                    }
                    if (page.hasSdkVerson()) {
                        this.aA |= 65536;
                        this.aK = page.aK;
                        onChanged();
                    }
                    if (page.hasOsVersion()) {
                        this.aA |= 131072;
                        this.aL = page.aL;
                        onChanged();
                    }
                    if (page.hasDeviceName()) {
                        this.aA |= 262144;
                        this.aP = page.aP;
                        onChanged();
                    }
                    if (page.hasMccMnc()) {
                        this.aA |= 524288;
                        this.aU = page.aU;
                        onChanged();
                    }
                    if (page.hasCellId()) {
                        this.aA |= 1048576;
                        this.aV = page.aV;
                        onChanged();
                    }
                    mergeUnknownFields(page.getUnknownFields());
                    return this;
                }

                public Builder removePageMap(int i) {
                    if (this.bZ == null) {
                        aQ();
                        this.bY.remove(i);
                        onChanged();
                    } else {
                        this.bZ.remove(i);
                    }
                    return this;
                }

                public Builder setAppChannel(String str) {
                    if (str != null) {
                        this.aA |= 2048;
                        this.aE = str;
                        onChanged();
                        return this;
                    }
                    throw new NullPointerException();
                }

                public Builder setAppChannelBytes(ByteString byteString) {
                    if (byteString != null) {
                        this.aA |= 2048;
                        this.aE = byteString;
                        onChanged();
                        return this;
                    }
                    throw new NullPointerException();
                }

                public Builder setAppKey(String str) {
                    if (str != null) {
                        this.aA |= 1024;
                        this.aD = str;
                        onChanged();
                        return this;
                    }
                    throw new NullPointerException();
                }

                public Builder setAppKeyBytes(ByteString byteString) {
                    if (byteString != null) {
                        this.aA |= 1024;
                        this.aD = byteString;
                        onChanged();
                        return this;
                    }
                    throw new NullPointerException();
                }

                public Builder setCellId(String str) {
                    if (str != null) {
                        this.aA |= 1048576;
                        this.aV = str;
                        onChanged();
                        return this;
                    }
                    throw new NullPointerException();
                }

                public Builder setCellIdBytes(ByteString byteString) {
                    if (byteString != null) {
                        this.aA |= 1048576;
                        this.aV = byteString;
                        onChanged();
                        return this;
                    }
                    throw new NullPointerException();
                }

                public Builder setDeviceId(String str) {
                    if (str != null) {
                        this.aA |= 8;
                        this.aG = str;
                        onChanged();
                        return this;
                    }
                    throw new NullPointerException();
                }

                public Builder setDeviceIdBytes(ByteString byteString) {
                    if (byteString != null) {
                        this.aA |= 8;
                        this.aG = byteString;
                        onChanged();
                        return this;
                    }
                    throw new NullPointerException();
                }

                public Builder setDeviceName(String str) {
                    if (str != null) {
                        this.aA |= 262144;
                        this.aP = str;
                        onChanged();
                        return this;
                    }
                    throw new NullPointerException();
                }

                public Builder setDeviceNameBytes(ByteString byteString) {
                    if (byteString != null) {
                        this.aA |= 262144;
                        this.aP = byteString;
                        onChanged();
                        return this;
                    }
                    throw new NullPointerException();
                }

                public Builder setDuration(long j) {
                    this.aA |= 64;
                    this.bW = j;
                    onChanged();
                    return this;
                }

                public Builder setEndMills(long j) {
                    this.aA |= 32;
                    this.bV = j;
                    onChanged();
                    return this;
                }

                public Builder setLogVersionApp(String str) {
                    if (str != null) {
                        this.aA |= 8192;
                        this.bj = str;
                        onChanged();
                        return this;
                    }
                    throw new NullPointerException();
                }

                public Builder setLogVersionAppBytes(ByteString byteString) {
                    if (byteString != null) {
                        this.aA |= 8192;
                        this.bj = byteString;
                        onChanged();
                        return this;
                    }
                    throw new NullPointerException();
                }

                public Builder setMccMnc(String str) {
                    if (str != null) {
                        this.aA |= 524288;
                        this.aU = str;
                        onChanged();
                        return this;
                    }
                    throw new NullPointerException();
                }

                public Builder setMccMncBytes(ByteString byteString) {
                    if (byteString != null) {
                        this.aA |= 524288;
                        this.aU = byteString;
                        onChanged();
                        return this;
                    }
                    throw new NullPointerException();
                }

                public Builder setOsVersion(String str) {
                    if (str != null) {
                        this.aA |= 131072;
                        this.aL = str;
                        onChanged();
                        return this;
                    }
                    throw new NullPointerException();
                }

                public Builder setOsVersionBytes(ByteString byteString) {
                    if (byteString != null) {
                        this.aA |= 131072;
                        this.aL = byteString;
                        onChanged();
                        return this;
                    }
                    throw new NullPointerException();
                }

                public Builder setPackageName(String str) {
                    if (str != null) {
                        this.aA |= 4096;
                        this.bh = str;
                        onChanged();
                        return this;
                    }
                    throw new NullPointerException();
                }

                public Builder setPackageNameBytes(ByteString byteString) {
                    if (byteString != null) {
                        this.aA |= 4096;
                        this.bh = byteString;
                        onChanged();
                        return this;
                    }
                    throw new NullPointerException();
                }

                public Builder setPageId(String str) {
                    if (str != null) {
                        this.aA |= 128;
                        this.bX = str;
                        onChanged();
                        return this;
                    }
                    throw new NullPointerException();
                }

                public Builder setPageIdBytes(ByteString byteString) {
                    if (byteString != null) {
                        this.aA |= 128;
                        this.bX = byteString;
                        onChanged();
                        return this;
                    }
                    throw new NullPointerException();
                }

                public Builder setPageMap(int i, ProtoMap.Builder builder) {
                    if (this.bZ == null) {
                        aQ();
                        this.bY.set(i, builder.build());
                        onChanged();
                    } else {
                        this.bZ.setMessage(i, builder.build());
                    }
                    return this;
                }

                public Builder setPageMap(int i, ProtoMap protoMap) {
                    if (this.bZ != null) {
                        this.bZ.setMessage(i, protoMap);
                    } else if (protoMap != null) {
                        aQ();
                        this.bY.set(i, protoMap);
                        onChanged();
                    } else {
                        throw new NullPointerException();
                    }
                    return this;
                }

                public Builder setPlatform(String str) {
                    if (str != null) {
                        this.aA |= 512;
                        this.aM = str;
                        onChanged();
                        return this;
                    }
                    throw new NullPointerException();
                }

                public Builder setPlatformBytes(ByteString byteString) {
                    if (byteString != null) {
                        this.aA |= 512;
                        this.aM = byteString;
                        onChanged();
                        return this;
                    }
                    throw new NullPointerException();
                }

                public Builder setSdkVerson(String str) {
                    if (str != null) {
                        this.aA |= 65536;
                        this.aK = str;
                        onChanged();
                        return this;
                    }
                    throw new NullPointerException();
                }

                public Builder setSdkVersonBytes(ByteString byteString) {
                    if (byteString != null) {
                        this.aA |= 65536;
                        this.aK = byteString;
                        onChanged();
                        return this;
                    }
                    throw new NullPointerException();
                }

                public Builder setSessionId(String str) {
                    if (str != null) {
                        this.aA |= 2;
                        this.aF = str;
                        onChanged();
                        return this;
                    }
                    throw new NullPointerException();
                }

                public Builder setSessionIdBytes(ByteString byteString) {
                    if (byteString != null) {
                        this.aA |= 2;
                        this.aF = byteString;
                        onChanged();
                        return this;
                    }
                    throw new NullPointerException();
                }

                public Builder setStartMills(long j) {
                    this.aA |= 16;
                    this.bU = j;
                    onChanged();
                    return this;
                }

                public Builder setType(String str) {
                    if (str != null) {
                        this.aA |= 1;
                        this.aC = str;
                        onChanged();
                        return this;
                    }
                    throw new NullPointerException();
                }

                public Builder setTypeBytes(ByteString byteString) {
                    if (byteString != null) {
                        this.aA |= 1;
                        this.aC = byteString;
                        onChanged();
                        return this;
                    }
                    throw new NullPointerException();
                }

                public Builder setUserId(String str) {
                    if (str != null) {
                        this.aA |= 4;
                        this.aH = str;
                        onChanged();
                        return this;
                    }
                    throw new NullPointerException();
                }

                public Builder setUserIdBytes(ByteString byteString) {
                    if (byteString != null) {
                        this.aA |= 4;
                        this.aH = byteString;
                        onChanged();
                        return this;
                    }
                    throw new NullPointerException();
                }

                public Builder setVersionCode(int i) {
                    this.aA |= 16384;
                    this.aI = i;
                    onChanged();
                    return this;
                }

                public Builder setVersionName(String str) {
                    if (str != null) {
                        this.aA |= 32768;
                        this.aJ = str;
                        onChanged();
                        return this;
                    }
                    throw new NullPointerException();
                }

                public Builder setVersionNameBytes(ByteString byteString) {
                    if (byteString != null) {
                        this.aA |= 32768;
                        this.aJ = byteString;
                        onChanged();
                        return this;
                    }
                    throw new NullPointerException();
                }
            }

            static {
                bT.P();
            }

            private Page(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) {
                this.ax = -1;
                this.ay = -1;
                P();
                UnknownFieldSet.Builder newBuilder = UnknownFieldSet.newBuilder();
                boolean z = false;
                boolean z2 = false;
                while (!z) {
                    try {
                        int readTag = codedInputStream.readTag();
                        switch (readTag) {
                            case 0:
                                z = true;
                                break;
                            case 10:
                                ByteString readBytes = codedInputStream.readBytes();
                                this.aA = 1 | this.aA;
                                this.aC = readBytes;
                                break;
                            case 18:
                                ByteString readBytes2 = codedInputStream.readBytes();
                                this.aA |= 2;
                                this.aF = readBytes2;
                                break;
                            case 26:
                                ByteString readBytes3 = codedInputStream.readBytes();
                                this.aA |= 4;
                                this.aH = readBytes3;
                                break;
                            case 34:
                                ByteString readBytes4 = codedInputStream.readBytes();
                                this.aA |= 8;
                                this.aG = readBytes4;
                                break;
                            case 40:
                                this.aA |= 16;
                                this.bU = codedInputStream.readInt64();
                                break;
                            case 48:
                                this.aA |= 32;
                                this.bV = codedInputStream.readInt64();
                                break;
                            case 56:
                                this.aA |= 64;
                                this.bW = codedInputStream.readInt64();
                                break;
                            case 66:
                                ByteString readBytes5 = codedInputStream.readBytes();
                                this.aA |= 128;
                                this.bX = readBytes5;
                                break;
                            case 74:
                                if (!(z2 & true)) {
                                    this.bY = new ArrayList();
                                    z2 |= true;
                                }
                                this.bY.add(codedInputStream.readMessage(ProtoMap.PARSER, extensionRegistryLite));
                                break;
                            case 82:
                                ByteString readBytes6 = codedInputStream.readBytes();
                                this.aA |= 256;
                                this.aM = readBytes6;
                                break;
                            case 90:
                                ByteString readBytes7 = codedInputStream.readBytes();
                                this.aA |= 512;
                                this.aD = readBytes7;
                                break;
                            case 98:
                                ByteString readBytes8 = codedInputStream.readBytes();
                                this.aA |= 1024;
                                this.aE = readBytes8;
                                break;
                            case 106:
                                ByteString readBytes9 = codedInputStream.readBytes();
                                this.aA |= 2048;
                                this.bh = readBytes9;
                                break;
                            case 114:
                                ByteString readBytes10 = codedInputStream.readBytes();
                                this.aA |= 4096;
                                this.bj = readBytes10;
                                break;
                            case 120:
                                this.aA |= 8192;
                                this.aI = codedInputStream.readInt32();
                                break;
                            case 130:
                                ByteString readBytes11 = codedInputStream.readBytes();
                                this.aA |= 16384;
                                this.aJ = readBytes11;
                                break;
                            case 138:
                                ByteString readBytes12 = codedInputStream.readBytes();
                                this.aA |= 32768;
                                this.aK = readBytes12;
                                break;
                            case 146:
                                ByteString readBytes13 = codedInputStream.readBytes();
                                this.aA |= 65536;
                                this.aL = readBytes13;
                                break;
                            case 154:
                                ByteString readBytes14 = codedInputStream.readBytes();
                                this.aA |= 131072;
                                this.aP = readBytes14;
                                break;
                            case 162:
                                ByteString readBytes15 = codedInputStream.readBytes();
                                this.aA |= 262144;
                                this.aU = readBytes15;
                                break;
                            case 170:
                                ByteString readBytes16 = codedInputStream.readBytes();
                                this.aA |= 524288;
                                this.aV = readBytes16;
                                break;
                            default:
                                if (parseUnknownField(codedInputStream, newBuilder, extensionRegistryLite, readTag)) {
                                    break;
                                }
                                z = true;
                                break;
                        }
                    } catch (InvalidProtocolBufferException e) {
                        throw e.setUnfinishedMessage(this);
                    } catch (IOException e2) {
                        throw new InvalidProtocolBufferException(e2.getMessage()).setUnfinishedMessage(this);
                    } catch (Throwable th) {
                        if (z2 & true) {
                            this.bY = Collections.unmodifiableList(this.bY);
                        }
                        this.aw = newBuilder.build();
                        makeExtensionsImmutable();
                        throw th;
                    }
                }
                if (z2 & true) {
                    this.bY = Collections.unmodifiableList(this.bY);
                }
                this.aw = newBuilder.build();
                makeExtensionsImmutable();
            }

            /* synthetic */ Page(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite, a aVar) {
                this(codedInputStream, extensionRegistryLite);
            }

            private Page(GeneratedMessage.Builder builder) {
                super(builder);
                this.ax = -1;
                this.ay = -1;
                this.aw = builder.getUnknownFields();
            }

            /* synthetic */ Page(GeneratedMessage.Builder builder, a aVar) {
                this(builder);
            }

            private Page(boolean z) {
                this.ax = -1;
                this.ay = -1;
                this.aw = UnknownFieldSet.getDefaultInstance();
            }

            private void P() {
                this.aC = "";
                this.aF = "";
                this.aH = "";
                this.aG = "";
                this.bU = 0;
                this.bV = 0;
                this.bW = 0;
                this.bX = "";
                this.bY = Collections.emptyList();
                this.aM = "";
                this.aD = "";
                this.aE = "";
                this.bh = "";
                this.bj = "";
                this.aI = 0;
                this.aJ = "";
                this.aK = "";
                this.aL = "";
                this.aP = "";
                this.aU = "";
                this.aV = "";
            }

            public static Page getDefaultInstance() {
                return bT;
            }

            public static final Descriptors.Descriptor getDescriptor() {
                return ProtoMsg.S;
            }

            public static Builder newBuilder() {
                return Builder.aP();
            }

            public static Builder newBuilder(Page page) {
                return newBuilder().mergeFrom(page);
            }

            public static Page parseDelimitedFrom(InputStream inputStream) {
                return (Page) PARSER.parseDelimitedFrom(inputStream);
            }

            public static Page parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) {
                return (Page) PARSER.parseDelimitedFrom(inputStream, extensionRegistryLite);
            }

            public static Page parseFrom(ByteString byteString) {
                return (Page) PARSER.parseFrom(byteString);
            }

            public static Page parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) {
                return (Page) PARSER.parseFrom(byteString, extensionRegistryLite);
            }

            public static Page parseFrom(CodedInputStream codedInputStream) {
                return (Page) PARSER.parseFrom(codedInputStream);
            }

            public static Page parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) {
                return (Page) PARSER.parseFrom(codedInputStream, extensionRegistryLite);
            }

            public static Page parseFrom(InputStream inputStream) {
                return (Page) PARSER.parseFrom(inputStream);
            }

            public static Page parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) {
                return (Page) PARSER.parseFrom(inputStream, extensionRegistryLite);
            }

            public static Page parseFrom(byte[] bArr) {
                return (Page) PARSER.parseFrom(bArr);
            }

            public static Page parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) {
                return (Page) PARSER.parseFrom(bArr, extensionRegistryLite);
            }

            public String getAppChannel() {
                Object obj = this.aE;
                if (obj instanceof String) {
                    return (String) obj;
                }
                ByteString byteString = (ByteString) obj;
                String stringUtf8 = byteString.toStringUtf8();
                if (byteString.isValidUtf8()) {
                    this.aE = stringUtf8;
                }
                return stringUtf8;
            }

            public ByteString getAppChannelBytes() {
                Object obj = this.aE;
                if (!(obj instanceof String)) {
                    return (ByteString) obj;
                }
                ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                this.aE = copyFromUtf8;
                return copyFromUtf8;
            }

            public String getAppKey() {
                Object obj = this.aD;
                if (obj instanceof String) {
                    return (String) obj;
                }
                ByteString byteString = (ByteString) obj;
                String stringUtf8 = byteString.toStringUtf8();
                if (byteString.isValidUtf8()) {
                    this.aD = stringUtf8;
                }
                return stringUtf8;
            }

            public ByteString getAppKeyBytes() {
                Object obj = this.aD;
                if (!(obj instanceof String)) {
                    return (ByteString) obj;
                }
                ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                this.aD = copyFromUtf8;
                return copyFromUtf8;
            }

            public String getCellId() {
                Object obj = this.aV;
                if (obj instanceof String) {
                    return (String) obj;
                }
                ByteString byteString = (ByteString) obj;
                String stringUtf8 = byteString.toStringUtf8();
                if (byteString.isValidUtf8()) {
                    this.aV = stringUtf8;
                }
                return stringUtf8;
            }

            public ByteString getCellIdBytes() {
                Object obj = this.aV;
                if (!(obj instanceof String)) {
                    return (ByteString) obj;
                }
                ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                this.aV = copyFromUtf8;
                return copyFromUtf8;
            }

            public Page getDefaultInstanceForType() {
                return bT;
            }

            public String getDeviceId() {
                Object obj = this.aG;
                if (obj instanceof String) {
                    return (String) obj;
                }
                ByteString byteString = (ByteString) obj;
                String stringUtf8 = byteString.toStringUtf8();
                if (byteString.isValidUtf8()) {
                    this.aG = stringUtf8;
                }
                return stringUtf8;
            }

            public ByteString getDeviceIdBytes() {
                Object obj = this.aG;
                if (!(obj instanceof String)) {
                    return (ByteString) obj;
                }
                ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                this.aG = copyFromUtf8;
                return copyFromUtf8;
            }

            public String getDeviceName() {
                Object obj = this.aP;
                if (obj instanceof String) {
                    return (String) obj;
                }
                ByteString byteString = (ByteString) obj;
                String stringUtf8 = byteString.toStringUtf8();
                if (byteString.isValidUtf8()) {
                    this.aP = stringUtf8;
                }
                return stringUtf8;
            }

            public ByteString getDeviceNameBytes() {
                Object obj = this.aP;
                if (!(obj instanceof String)) {
                    return (ByteString) obj;
                }
                ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                this.aP = copyFromUtf8;
                return copyFromUtf8;
            }

            public long getDuration() {
                return this.bW;
            }

            public long getEndMills() {
                return this.bV;
            }

            public String getLogVersionApp() {
                Object obj = this.bj;
                if (obj instanceof String) {
                    return (String) obj;
                }
                ByteString byteString = (ByteString) obj;
                String stringUtf8 = byteString.toStringUtf8();
                if (byteString.isValidUtf8()) {
                    this.bj = stringUtf8;
                }
                return stringUtf8;
            }

            public ByteString getLogVersionAppBytes() {
                Object obj = this.bj;
                if (!(obj instanceof String)) {
                    return (ByteString) obj;
                }
                ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                this.bj = copyFromUtf8;
                return copyFromUtf8;
            }

            public String getMccMnc() {
                Object obj = this.aU;
                if (obj instanceof String) {
                    return (String) obj;
                }
                ByteString byteString = (ByteString) obj;
                String stringUtf8 = byteString.toStringUtf8();
                if (byteString.isValidUtf8()) {
                    this.aU = stringUtf8;
                }
                return stringUtf8;
            }

            public ByteString getMccMncBytes() {
                Object obj = this.aU;
                if (!(obj instanceof String)) {
                    return (ByteString) obj;
                }
                ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                this.aU = copyFromUtf8;
                return copyFromUtf8;
            }

            public String getOsVersion() {
                Object obj = this.aL;
                if (obj instanceof String) {
                    return (String) obj;
                }
                ByteString byteString = (ByteString) obj;
                String stringUtf8 = byteString.toStringUtf8();
                if (byteString.isValidUtf8()) {
                    this.aL = stringUtf8;
                }
                return stringUtf8;
            }

            public ByteString getOsVersionBytes() {
                Object obj = this.aL;
                if (!(obj instanceof String)) {
                    return (ByteString) obj;
                }
                ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                this.aL = copyFromUtf8;
                return copyFromUtf8;
            }

            public String getPackageName() {
                Object obj = this.bh;
                if (obj instanceof String) {
                    return (String) obj;
                }
                ByteString byteString = (ByteString) obj;
                String stringUtf8 = byteString.toStringUtf8();
                if (byteString.isValidUtf8()) {
                    this.bh = stringUtf8;
                }
                return stringUtf8;
            }

            public ByteString getPackageNameBytes() {
                Object obj = this.bh;
                if (!(obj instanceof String)) {
                    return (ByteString) obj;
                }
                ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                this.bh = copyFromUtf8;
                return copyFromUtf8;
            }

            public String getPageId() {
                Object obj = this.bX;
                if (obj instanceof String) {
                    return (String) obj;
                }
                ByteString byteString = (ByteString) obj;
                String stringUtf8 = byteString.toStringUtf8();
                if (byteString.isValidUtf8()) {
                    this.bX = stringUtf8;
                }
                return stringUtf8;
            }

            public ByteString getPageIdBytes() {
                Object obj = this.bX;
                if (!(obj instanceof String)) {
                    return (ByteString) obj;
                }
                ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                this.bX = copyFromUtf8;
                return copyFromUtf8;
            }

            public ProtoMap getPageMap(int i) {
                return (ProtoMap) this.bY.get(i);
            }

            public int getPageMapCount() {
                return this.bY.size();
            }

            public List getPageMapList() {
                return this.bY;
            }

            public ProtoMapOrBuilder getPageMapOrBuilder(int i) {
                return (ProtoMapOrBuilder) this.bY.get(i);
            }

            public List getPageMapOrBuilderList() {
                return this.bY;
            }

            public Parser getParserForType() {
                return PARSER;
            }

            public String getPlatform() {
                Object obj = this.aM;
                if (obj instanceof String) {
                    return (String) obj;
                }
                ByteString byteString = (ByteString) obj;
                String stringUtf8 = byteString.toStringUtf8();
                if (byteString.isValidUtf8()) {
                    this.aM = stringUtf8;
                }
                return stringUtf8;
            }

            public ByteString getPlatformBytes() {
                Object obj = this.aM;
                if (!(obj instanceof String)) {
                    return (ByteString) obj;
                }
                ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                this.aM = copyFromUtf8;
                return copyFromUtf8;
            }

            public String getSdkVerson() {
                Object obj = this.aK;
                if (obj instanceof String) {
                    return (String) obj;
                }
                ByteString byteString = (ByteString) obj;
                String stringUtf8 = byteString.toStringUtf8();
                if (byteString.isValidUtf8()) {
                    this.aK = stringUtf8;
                }
                return stringUtf8;
            }

            public ByteString getSdkVersonBytes() {
                Object obj = this.aK;
                if (!(obj instanceof String)) {
                    return (ByteString) obj;
                }
                ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                this.aK = copyFromUtf8;
                return copyFromUtf8;
            }

            public int getSerializedSize() {
                int i = this.ay;
                if (i != -1) {
                    return i;
                }
                int computeBytesSize = (this.aA & 1) == 1 ? CodedOutputStream.computeBytesSize(1, getTypeBytes()) + 0 : 0;
                if ((this.aA & 2) == 2) {
                    computeBytesSize += CodedOutputStream.computeBytesSize(2, getSessionIdBytes());
                }
                if ((this.aA & 4) == 4) {
                    computeBytesSize += CodedOutputStream.computeBytesSize(3, getUserIdBytes());
                }
                if ((this.aA & 8) == 8) {
                    computeBytesSize += CodedOutputStream.computeBytesSize(4, getDeviceIdBytes());
                }
                if ((this.aA & 16) == 16) {
                    computeBytesSize += CodedOutputStream.computeInt64Size(5, this.bU);
                }
                if ((this.aA & 32) == 32) {
                    computeBytesSize += CodedOutputStream.computeInt64Size(6, this.bV);
                }
                if ((this.aA & 64) == 64) {
                    computeBytesSize += CodedOutputStream.computeInt64Size(7, this.bW);
                }
                if ((this.aA & 128) == 128) {
                    computeBytesSize += CodedOutputStream.computeBytesSize(8, getPageIdBytes());
                }
                for (int i2 = 0; i2 < this.bY.size(); i2++) {
                    computeBytesSize += CodedOutputStream.computeMessageSize(9, (MessageLite) this.bY.get(i2));
                }
                if ((this.aA & 256) == 256) {
                    computeBytesSize += CodedOutputStream.computeBytesSize(10, getPlatformBytes());
                }
                if ((this.aA & 512) == 512) {
                    computeBytesSize += CodedOutputStream.computeBytesSize(11, getAppKeyBytes());
                }
                if ((this.aA & 1024) == 1024) {
                    computeBytesSize += CodedOutputStream.computeBytesSize(12, getAppChannelBytes());
                }
                if ((this.aA & 2048) == 2048) {
                    computeBytesSize += CodedOutputStream.computeBytesSize(13, getPackageNameBytes());
                }
                if ((this.aA & 4096) == 4096) {
                    computeBytesSize += CodedOutputStream.computeBytesSize(14, getLogVersionAppBytes());
                }
                if ((this.aA & 8192) == 8192) {
                    computeBytesSize += CodedOutputStream.computeInt32Size(15, this.aI);
                }
                if ((this.aA & 16384) == 16384) {
                    computeBytesSize += CodedOutputStream.computeBytesSize(16, getVersionNameBytes());
                }
                if ((this.aA & 32768) == 32768) {
                    computeBytesSize += CodedOutputStream.computeBytesSize(17, getSdkVersonBytes());
                }
                if ((this.aA & 65536) == 65536) {
                    computeBytesSize += CodedOutputStream.computeBytesSize(18, getOsVersionBytes());
                }
                if ((this.aA & 131072) == 131072) {
                    computeBytesSize += CodedOutputStream.computeBytesSize(19, getDeviceNameBytes());
                }
                if ((this.aA & 262144) == 262144) {
                    computeBytesSize += CodedOutputStream.computeBytesSize(20, getMccMncBytes());
                }
                if ((this.aA & 524288) == 524288) {
                    computeBytesSize += CodedOutputStream.computeBytesSize(21, getCellIdBytes());
                }
                int serializedSize = computeBytesSize + getUnknownFields().getSerializedSize();
                this.ay = serializedSize;
                return serializedSize;
            }

            public String getSessionId() {
                Object obj = this.aF;
                if (obj instanceof String) {
                    return (String) obj;
                }
                ByteString byteString = (ByteString) obj;
                String stringUtf8 = byteString.toStringUtf8();
                if (byteString.isValidUtf8()) {
                    this.aF = stringUtf8;
                }
                return stringUtf8;
            }

            public ByteString getSessionIdBytes() {
                Object obj = this.aF;
                if (!(obj instanceof String)) {
                    return (ByteString) obj;
                }
                ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                this.aF = copyFromUtf8;
                return copyFromUtf8;
            }

            public long getStartMills() {
                return this.bU;
            }

            public String getType() {
                Object obj = this.aC;
                if (obj instanceof String) {
                    return (String) obj;
                }
                ByteString byteString = (ByteString) obj;
                String stringUtf8 = byteString.toStringUtf8();
                if (byteString.isValidUtf8()) {
                    this.aC = stringUtf8;
                }
                return stringUtf8;
            }

            public ByteString getTypeBytes() {
                Object obj = this.aC;
                if (!(obj instanceof String)) {
                    return (ByteString) obj;
                }
                ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                this.aC = copyFromUtf8;
                return copyFromUtf8;
            }

            public final UnknownFieldSet getUnknownFields() {
                return this.aw;
            }

            public String getUserId() {
                Object obj = this.aH;
                if (obj instanceof String) {
                    return (String) obj;
                }
                ByteString byteString = (ByteString) obj;
                String stringUtf8 = byteString.toStringUtf8();
                if (byteString.isValidUtf8()) {
                    this.aH = stringUtf8;
                }
                return stringUtf8;
            }

            public ByteString getUserIdBytes() {
                Object obj = this.aH;
                if (!(obj instanceof String)) {
                    return (ByteString) obj;
                }
                ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                this.aH = copyFromUtf8;
                return copyFromUtf8;
            }

            public int getVersionCode() {
                return this.aI;
            }

            public String getVersionName() {
                Object obj = this.aJ;
                if (obj instanceof String) {
                    return (String) obj;
                }
                ByteString byteString = (ByteString) obj;
                String stringUtf8 = byteString.toStringUtf8();
                if (byteString.isValidUtf8()) {
                    this.aJ = stringUtf8;
                }
                return stringUtf8;
            }

            public ByteString getVersionNameBytes() {
                Object obj = this.aJ;
                if (!(obj instanceof String)) {
                    return (ByteString) obj;
                }
                ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                this.aJ = copyFromUtf8;
                return copyFromUtf8;
            }

            public boolean hasAppChannel() {
                return (this.aA & 1024) == 1024;
            }

            public boolean hasAppKey() {
                return (this.aA & 512) == 512;
            }

            public boolean hasCellId() {
                return (this.aA & 524288) == 524288;
            }

            public boolean hasDeviceId() {
                return (this.aA & 8) == 8;
            }

            public boolean hasDeviceName() {
                return (this.aA & 131072) == 131072;
            }

            public boolean hasDuration() {
                return (this.aA & 64) == 64;
            }

            public boolean hasEndMills() {
                return (this.aA & 32) == 32;
            }

            public boolean hasLogVersionApp() {
                return (this.aA & 4096) == 4096;
            }

            public boolean hasMccMnc() {
                return (this.aA & 262144) == 262144;
            }

            public boolean hasOsVersion() {
                return (this.aA & 65536) == 65536;
            }

            public boolean hasPackageName() {
                return (this.aA & 2048) == 2048;
            }

            public boolean hasPageId() {
                return (this.aA & 128) == 128;
            }

            public boolean hasPlatform() {
                return (this.aA & 256) == 256;
            }

            public boolean hasSdkVerson() {
                return (this.aA & 32768) == 32768;
            }

            public boolean hasSessionId() {
                return (this.aA & 2) == 2;
            }

            public boolean hasStartMills() {
                return (this.aA & 16) == 16;
            }

            public boolean hasType() {
                return (this.aA & 1) == 1;
            }

            public boolean hasUserId() {
                return (this.aA & 4) == 4;
            }

            public boolean hasVersionCode() {
                return (this.aA & 8192) == 8192;
            }

            public boolean hasVersionName() {
                return (this.aA & 16384) == 16384;
            }

            /* access modifiers changed from: protected */
            public GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
                return ProtoMsg.T.ensureFieldAccessorsInitialized(Page.class, Builder.class);
            }

            public final boolean isInitialized() {
                byte b = this.ax;
                if (b == 1) {
                    return true;
                }
                if (b == 0) {
                    return false;
                }
                if (!hasType()) {
                    this.ax = 0;
                    return false;
                }
                for (int i = 0; i < getPageMapCount(); i++) {
                    if (!getPageMap(i).isInitialized()) {
                        this.ax = 0;
                        return false;
                    }
                }
                this.ax = 1;
                return true;
            }

            public Builder newBuilderForType() {
                return newBuilder();
            }

            /* access modifiers changed from: protected */
            public Builder newBuilderForType(GeneratedMessage.BuilderParent builderParent) {
                return new Builder(builderParent, (a) null);
            }

            public Builder toBuilder() {
                return newBuilder(this);
            }

            /* access modifiers changed from: protected */
            public Object writeReplace() {
                return super.writeReplace();
            }

            public void writeTo(CodedOutputStream codedOutputStream) {
                getSerializedSize();
                if ((this.aA & 1) == 1) {
                    codedOutputStream.writeBytes(1, getTypeBytes());
                }
                if ((this.aA & 2) == 2) {
                    codedOutputStream.writeBytes(2, getSessionIdBytes());
                }
                if ((this.aA & 4) == 4) {
                    codedOutputStream.writeBytes(3, getUserIdBytes());
                }
                if ((this.aA & 8) == 8) {
                    codedOutputStream.writeBytes(4, getDeviceIdBytes());
                }
                if ((this.aA & 16) == 16) {
                    codedOutputStream.writeInt64(5, this.bU);
                }
                if ((this.aA & 32) == 32) {
                    codedOutputStream.writeInt64(6, this.bV);
                }
                if ((this.aA & 64) == 64) {
                    codedOutputStream.writeInt64(7, this.bW);
                }
                if ((this.aA & 128) == 128) {
                    codedOutputStream.writeBytes(8, getPageIdBytes());
                }
                for (int i = 0; i < this.bY.size(); i++) {
                    codedOutputStream.writeMessage(9, (MessageLite) this.bY.get(i));
                }
                if ((this.aA & 256) == 256) {
                    codedOutputStream.writeBytes(10, getPlatformBytes());
                }
                if ((this.aA & 512) == 512) {
                    codedOutputStream.writeBytes(11, getAppKeyBytes());
                }
                if ((this.aA & 1024) == 1024) {
                    codedOutputStream.writeBytes(12, getAppChannelBytes());
                }
                if ((this.aA & 2048) == 2048) {
                    codedOutputStream.writeBytes(13, getPackageNameBytes());
                }
                if ((this.aA & 4096) == 4096) {
                    codedOutputStream.writeBytes(14, getLogVersionAppBytes());
                }
                if ((this.aA & 8192) == 8192) {
                    codedOutputStream.writeInt32(15, this.aI);
                }
                if ((this.aA & 16384) == 16384) {
                    codedOutputStream.writeBytes(16, getVersionNameBytes());
                }
                if ((this.aA & 32768) == 32768) {
                    codedOutputStream.writeBytes(17, getSdkVersonBytes());
                }
                if ((this.aA & 65536) == 65536) {
                    codedOutputStream.writeBytes(18, getOsVersionBytes());
                }
                if ((this.aA & 131072) == 131072) {
                    codedOutputStream.writeBytes(19, getDeviceNameBytes());
                }
                if ((this.aA & 262144) == 262144) {
                    codedOutputStream.writeBytes(20, getMccMncBytes());
                }
                if ((this.aA & 524288) == 524288) {
                    codedOutputStream.writeBytes(21, getCellIdBytes());
                }
                getUnknownFields().writeTo(codedOutputStream);
            }
        }

        public final class PageMsg extends GeneratedMessage implements PageMsgOrBuilder {
            public static final int PAGE_FIELD_NUMBER = 1;
            public static Parser PARSER = new m();
            private static final PageMsg ca = new PageMsg(true);
            private final UnknownFieldSet aw;
            private byte ax;
            private int ay;
            /* access modifiers changed from: private */
            public List cb;

            public final class Builder extends GeneratedMessage.Builder implements PageMsgOrBuilder {
                private int aA;
                private List cb;
                private RepeatedFieldBuilder cc;

                private Builder() {
                    this.cb = Collections.emptyList();
                    R();
                }

                private Builder(GeneratedMessage.BuilderParent builderParent) {
                    super(builderParent);
                    this.cb = Collections.emptyList();
                    R();
                }

                /* synthetic */ Builder(GeneratedMessage.BuilderParent builderParent, a aVar) {
                    this(builderParent);
                }

                private void R() {
                    if (PageMsg.alwaysUseFieldBuilders) {
                        aX();
                    }
                }

                /* access modifiers changed from: private */
                public static Builder aV() {
                    return new Builder();
                }

                private void aW() {
                    if ((this.aA & 1) != 1) {
                        this.cb = new ArrayList(this.cb);
                        this.aA |= 1;
                    }
                }

                private RepeatedFieldBuilder aX() {
                    if (this.cc == null) {
                        List list = this.cb;
                        boolean z = true;
                        if ((this.aA & 1) != 1) {
                            z = false;
                        }
                        this.cc = new RepeatedFieldBuilder(list, z, getParentForChildren(), isClean());
                        this.cb = null;
                    }
                    return this.cc;
                }

                public static final Descriptors.Descriptor getDescriptor() {
                    return ProtoMsg.U;
                }

                public Builder addAllPage(Iterable iterable) {
                    if (this.cc == null) {
                        aW();
                        AbstractMessageLite.Builder.addAll(iterable, this.cb);
                        onChanged();
                    } else {
                        this.cc.addAllMessages(iterable);
                    }
                    return this;
                }

                public Builder addPage(int i, Page.Builder builder) {
                    if (this.cc == null) {
                        aW();
                        this.cb.add(i, builder.build());
                        onChanged();
                    } else {
                        this.cc.addMessage(i, builder.build());
                    }
                    return this;
                }

                public Builder addPage(int i, Page page) {
                    if (this.cc != null) {
                        this.cc.addMessage(i, page);
                    } else if (page != null) {
                        aW();
                        this.cb.add(i, page);
                        onChanged();
                    } else {
                        throw new NullPointerException();
                    }
                    return this;
                }

                public Builder addPage(Page.Builder builder) {
                    if (this.cc == null) {
                        aW();
                        this.cb.add(builder.build());
                        onChanged();
                    } else {
                        this.cc.addMessage(builder.build());
                    }
                    return this;
                }

                public Builder addPage(Page page) {
                    if (this.cc != null) {
                        this.cc.addMessage(page);
                    } else if (page != null) {
                        aW();
                        this.cb.add(page);
                        onChanged();
                    } else {
                        throw new NullPointerException();
                    }
                    return this;
                }

                public Page.Builder addPageBuilder() {
                    return (Page.Builder) aX().addBuilder(Page.getDefaultInstance());
                }

                public Page.Builder addPageBuilder(int i) {
                    return (Page.Builder) aX().addBuilder(i, Page.getDefaultInstance());
                }

                public PageMsg build() {
                    PageMsg buildPartial = buildPartial();
                    if (buildPartial.isInitialized()) {
                        return buildPartial;
                    }
                    throw newUninitializedMessageException(buildPartial);
                }

                public PageMsg buildPartial() {
                    List list;
                    PageMsg pageMsg = new PageMsg((GeneratedMessage.Builder) this, (a) null);
                    int i = this.aA;
                    if (this.cc == null) {
                        if ((this.aA & 1) == 1) {
                            this.cb = Collections.unmodifiableList(this.cb);
                            this.aA &= -2;
                        }
                        list = this.cb;
                    } else {
                        list = this.cc.build();
                    }
                    List unused = pageMsg.cb = list;
                    onBuilt();
                    return pageMsg;
                }

                public Builder clear() {
                    super.clear();
                    if (this.cc == null) {
                        this.cb = Collections.emptyList();
                        this.aA &= -2;
                    } else {
                        this.cc.clear();
                    }
                    return this;
                }

                public Builder clearPage() {
                    if (this.cc == null) {
                        this.cb = Collections.emptyList();
                        this.aA &= -2;
                        onChanged();
                    } else {
                        this.cc.clear();
                    }
                    return this;
                }

                public Builder clone() {
                    return aV().mergeFrom(buildPartial());
                }

                public PageMsg getDefaultInstanceForType() {
                    return PageMsg.getDefaultInstance();
                }

                public Descriptors.Descriptor getDescriptorForType() {
                    return ProtoMsg.U;
                }

                public Page getPage(int i) {
                    return (Page) (this.cc == null ? this.cb.get(i) : this.cc.getMessage(i));
                }

                public Page.Builder getPageBuilder(int i) {
                    return (Page.Builder) aX().getBuilder(i);
                }

                public List getPageBuilderList() {
                    return aX().getBuilderList();
                }

                public int getPageCount() {
                    return this.cc == null ? this.cb.size() : this.cc.getCount();
                }

                public List getPageList() {
                    return this.cc == null ? Collections.unmodifiableList(this.cb) : this.cc.getMessageList();
                }

                public PageOrBuilder getPageOrBuilder(int i) {
                    return (PageOrBuilder) (this.cc == null ? this.cb.get(i) : this.cc.getMessageOrBuilder(i));
                }

                public List getPageOrBuilderList() {
                    return this.cc != null ? this.cc.getMessageOrBuilderList() : Collections.unmodifiableList(this.cb);
                }

                /* access modifiers changed from: protected */
                public GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
                    return ProtoMsg.V.ensureFieldAccessorsInitialized(PageMsg.class, Builder.class);
                }

                public final boolean isInitialized() {
                    for (int i = 0; i < getPageCount(); i++) {
                        if (!getPage(i).isInitialized()) {
                            return false;
                        }
                    }
                    return true;
                }

                public Builder mergeFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) {
                    PageMsg pageMsg;
                    PageMsg pageMsg2 = null;
                    try {
                        PageMsg pageMsg3 = (PageMsg) PageMsg.PARSER.parsePartialFrom(codedInputStream, extensionRegistryLite);
                        if (pageMsg3 != null) {
                            mergeFrom(pageMsg3);
                        }
                        return this;
                    } catch (InvalidProtocolBufferException e) {
                        pageMsg = (PageMsg) e.getUnfinishedMessage();
                        throw e;
                    } catch (Throwable th) {
                        th = th;
                        pageMsg2 = pageMsg;
                    }
                    if (pageMsg2 != null) {
                        mergeFrom(pageMsg2);
                    }
                    throw th;
                }

                public Builder mergeFrom(Message message) {
                    if (message instanceof PageMsg) {
                        return mergeFrom((PageMsg) message);
                    }
                    super.mergeFrom(message);
                    return this;
                }

                public Builder mergeFrom(PageMsg pageMsg) {
                    if (pageMsg == PageMsg.getDefaultInstance()) {
                        return this;
                    }
                    if (this.cc == null) {
                        if (!pageMsg.cb.isEmpty()) {
                            if (this.cb.isEmpty()) {
                                this.cb = pageMsg.cb;
                                this.aA &= -2;
                            } else {
                                aW();
                                this.cb.addAll(pageMsg.cb);
                            }
                            onChanged();
                        }
                    } else if (!pageMsg.cb.isEmpty()) {
                        if (this.cc.isEmpty()) {
                            this.cc.dispose();
                            RepeatedFieldBuilder repeatedFieldBuilder = null;
                            this.cc = null;
                            this.cb = pageMsg.cb;
                            this.aA &= -2;
                            if (PageMsg.alwaysUseFieldBuilders) {
                                repeatedFieldBuilder = aX();
                            }
                            this.cc = repeatedFieldBuilder;
                        } else {
                            this.cc.addAllMessages(pageMsg.cb);
                        }
                    }
                    mergeUnknownFields(pageMsg.getUnknownFields());
                    return this;
                }

                public Builder removePage(int i) {
                    if (this.cc == null) {
                        aW();
                        this.cb.remove(i);
                        onChanged();
                    } else {
                        this.cc.remove(i);
                    }
                    return this;
                }

                public Builder setPage(int i, Page.Builder builder) {
                    if (this.cc == null) {
                        aW();
                        this.cb.set(i, builder.build());
                        onChanged();
                    } else {
                        this.cc.setMessage(i, builder.build());
                    }
                    return this;
                }

                public Builder setPage(int i, Page page) {
                    if (this.cc != null) {
                        this.cc.setMessage(i, page);
                    } else if (page != null) {
                        aW();
                        this.cb.set(i, page);
                        onChanged();
                    } else {
                        throw new NullPointerException();
                    }
                    return this;
                }
            }

            static {
                ca.P();
            }

            private PageMsg(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) {
                this.ax = -1;
                this.ay = -1;
                P();
                UnknownFieldSet.Builder newBuilder = UnknownFieldSet.newBuilder();
                boolean z = false;
                boolean z2 = false;
                while (!z) {
                    try {
                        int readTag = codedInputStream.readTag();
                        if (readTag != 0) {
                            if (readTag == 10) {
                                if (!z2 || !true) {
                                    this.cb = new ArrayList();
                                    z2 |= true;
                                }
                                this.cb.add(codedInputStream.readMessage(Page.PARSER, extensionRegistryLite));
                            } else if (!parseUnknownField(codedInputStream, newBuilder, extensionRegistryLite, readTag)) {
                            }
                        }
                        z = true;
                    } catch (InvalidProtocolBufferException e) {
                        throw e.setUnfinishedMessage(this);
                    } catch (IOException e2) {
                        throw new InvalidProtocolBufferException(e2.getMessage()).setUnfinishedMessage(this);
                    } catch (Throwable th) {
                        if (z2 && true) {
                            this.cb = Collections.unmodifiableList(this.cb);
                        }
                        this.aw = newBuilder.build();
                        makeExtensionsImmutable();
                        throw th;
                    }
                }
                if (z2 && true) {
                    this.cb = Collections.unmodifiableList(this.cb);
                }
                this.aw = newBuilder.build();
                makeExtensionsImmutable();
            }

            /* synthetic */ PageMsg(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite, a aVar) {
                this(codedInputStream, extensionRegistryLite);
            }

            private PageMsg(GeneratedMessage.Builder builder) {
                super(builder);
                this.ax = -1;
                this.ay = -1;
                this.aw = builder.getUnknownFields();
            }

            /* synthetic */ PageMsg(GeneratedMessage.Builder builder, a aVar) {
                this(builder);
            }

            private PageMsg(boolean z) {
                this.ax = -1;
                this.ay = -1;
                this.aw = UnknownFieldSet.getDefaultInstance();
            }

            private void P() {
                this.cb = Collections.emptyList();
            }

            public static PageMsg getDefaultInstance() {
                return ca;
            }

            public static final Descriptors.Descriptor getDescriptor() {
                return ProtoMsg.U;
            }

            public static Builder newBuilder() {
                return Builder.aV();
            }

            public static Builder newBuilder(PageMsg pageMsg) {
                return newBuilder().mergeFrom(pageMsg);
            }

            public static PageMsg parseDelimitedFrom(InputStream inputStream) {
                return (PageMsg) PARSER.parseDelimitedFrom(inputStream);
            }

            public static PageMsg parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) {
                return (PageMsg) PARSER.parseDelimitedFrom(inputStream, extensionRegistryLite);
            }

            public static PageMsg parseFrom(ByteString byteString) {
                return (PageMsg) PARSER.parseFrom(byteString);
            }

            public static PageMsg parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) {
                return (PageMsg) PARSER.parseFrom(byteString, extensionRegistryLite);
            }

            public static PageMsg parseFrom(CodedInputStream codedInputStream) {
                return (PageMsg) PARSER.parseFrom(codedInputStream);
            }

            public static PageMsg parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) {
                return (PageMsg) PARSER.parseFrom(codedInputStream, extensionRegistryLite);
            }

            public static PageMsg parseFrom(InputStream inputStream) {
                return (PageMsg) PARSER.parseFrom(inputStream);
            }

            public static PageMsg parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) {
                return (PageMsg) PARSER.parseFrom(inputStream, extensionRegistryLite);
            }

            public static PageMsg parseFrom(byte[] bArr) {
                return (PageMsg) PARSER.parseFrom(bArr);
            }

            public static PageMsg parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) {
                return (PageMsg) PARSER.parseFrom(bArr, extensionRegistryLite);
            }

            public PageMsg getDefaultInstanceForType() {
                return ca;
            }

            public Page getPage(int i) {
                return (Page) this.cb.get(i);
            }

            public int getPageCount() {
                return this.cb.size();
            }

            public List getPageList() {
                return this.cb;
            }

            public PageOrBuilder getPageOrBuilder(int i) {
                return (PageOrBuilder) this.cb.get(i);
            }

            public List getPageOrBuilderList() {
                return this.cb;
            }

            public Parser getParserForType() {
                return PARSER;
            }

            public int getSerializedSize() {
                int i = this.ay;
                if (i != -1) {
                    return i;
                }
                int i2 = 0;
                for (int i3 = 0; i3 < this.cb.size(); i3++) {
                    i2 += CodedOutputStream.computeMessageSize(1, (MessageLite) this.cb.get(i3));
                }
                int serializedSize = i2 + getUnknownFields().getSerializedSize();
                this.ay = serializedSize;
                return serializedSize;
            }

            public final UnknownFieldSet getUnknownFields() {
                return this.aw;
            }

            /* access modifiers changed from: protected */
            public GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
                return ProtoMsg.V.ensureFieldAccessorsInitialized(PageMsg.class, Builder.class);
            }

            public final boolean isInitialized() {
                byte b = this.ax;
                if (b == 1) {
                    return true;
                }
                if (b == 0) {
                    return false;
                }
                for (int i = 0; i < getPageCount(); i++) {
                    if (!getPage(i).isInitialized()) {
                        this.ax = 0;
                        return false;
                    }
                }
                this.ax = 1;
                return true;
            }

            public Builder newBuilderForType() {
                return newBuilder();
            }

            /* access modifiers changed from: protected */
            public Builder newBuilderForType(GeneratedMessage.BuilderParent builderParent) {
                return new Builder(builderParent, (a) null);
            }

            public Builder toBuilder() {
                return newBuilder(this);
            }

            /* access modifiers changed from: protected */
            public Object writeReplace() {
                return super.writeReplace();
            }

            public void writeTo(CodedOutputStream codedOutputStream) {
                getSerializedSize();
                for (int i = 0; i < this.cb.size(); i++) {
                    codedOutputStream.writeMessage(1, (MessageLite) this.cb.get(i));
                }
                getUnknownFields().writeTo(codedOutputStream);
            }
        }

        public interface PageMsgOrBuilder extends MessageOrBuilder {
            Page getPage(int i);

            int getPageCount();

            List getPageList();

            PageOrBuilder getPageOrBuilder(int i);

            List getPageOrBuilderList();
        }

        public interface PageOrBuilder extends MessageOrBuilder {
            String getAppChannel();

            ByteString getAppChannelBytes();

            String getAppKey();

            ByteString getAppKeyBytes();

            String getCellId();

            ByteString getCellIdBytes();

            String getDeviceId();

            ByteString getDeviceIdBytes();

            String getDeviceName();

            ByteString getDeviceNameBytes();

            long getDuration();

            long getEndMills();

            String getLogVersionApp();

            ByteString getLogVersionAppBytes();

            String getMccMnc();

            ByteString getMccMncBytes();

            String getOsVersion();

            ByteString getOsVersionBytes();

            String getPackageName();

            ByteString getPackageNameBytes();

            String getPageId();

            ByteString getPageIdBytes();

            ProtoMap getPageMap(int i);

            int getPageMapCount();

            List getPageMapList();

            ProtoMapOrBuilder getPageMapOrBuilder(int i);

            List getPageMapOrBuilderList();

            String getPlatform();

            ByteString getPlatformBytes();

            String getSdkVerson();

            ByteString getSdkVersonBytes();

            String getSessionId();

            ByteString getSessionIdBytes();

            long getStartMills();

            String getType();

            ByteString getTypeBytes();

            String getUserId();

            ByteString getUserIdBytes();

            int getVersionCode();

            String getVersionName();

            ByteString getVersionNameBytes();

            boolean hasAppChannel();

            boolean hasAppKey();

            boolean hasCellId();

            boolean hasDeviceId();

            boolean hasDeviceName();

            boolean hasDuration();

            boolean hasEndMills();

            boolean hasLogVersionApp();

            boolean hasMccMnc();

            boolean hasOsVersion();

            boolean hasPackageName();

            boolean hasPageId();

            boolean hasPlatform();

            boolean hasSdkVerson();

            boolean hasSessionId();

            boolean hasStartMills();

            boolean hasType();

            boolean hasUserId();

            boolean hasVersionCode();

            boolean hasVersionName();
        }

        public final class ProtoError extends GeneratedMessage implements ProtoErrorOrBuilder {
            public static final int ACTIVITY_FIELD_NUMBER = 5;
            public static final int APP_CHANNEL_FIELD_NUMBER = 3;
            public static final int APP_KEY_FIELD_NUMBER = 2;
            public static final int DEVICE_ID_FIELD_NUMBER = 7;
            public static final int DEVICE_NAME_FIELD_NUMBER = 17;
            public static final int ERROR_MAP_FIELD_NUMBER = 13;
            public static final int EXCEPTION_DETAIL_FIELD_NUMBER = 9;
            public static final int EXCEPTION_FIELD_NUMBER = 8;
            public static final int LOG_VERSION_APP_FIELD_NUMBER = 20;
            public static final int NETWORK_FIELD_NUMBER = 11;
            public static final int NETWORK_TYPE_FIELD_NUMBER = 10;
            public static final int PACKAGE_NAME_FIELD_NUMBER = 19;
            public static Parser PARSER = new n();
            public static final int PLATFORM_FIELD_NUMBER = 18;
            public static final int SESSION_ID_FIELD_NUMBER = 4;
            public static final int TIME_FIELD_NUMBER = 6;
            public static final int TYPE_FIELD_NUMBER = 1;
            public static final int URL_FIELD_NUMBER = 12;
            public static final int USER_ID_FIELD_NUMBER = 16;
            public static final int VERSION_CODE_FIELD_NUMBER = 14;
            public static final int VERSION_NAME_FIELD_NUMBER = 15;
            private static final ProtoError cd = new ProtoError(true);
            /* access modifiers changed from: private */
            public int aA;
            /* access modifiers changed from: private */
            public Object aC;
            /* access modifiers changed from: private */
            public Object aD;
            /* access modifiers changed from: private */
            public Object aE;
            /* access modifiers changed from: private */
            public Object aF;
            /* access modifiers changed from: private */
            public Object aG;
            /* access modifiers changed from: private */
            public Object aH;
            /* access modifiers changed from: private */
            public int aI;
            /* access modifiers changed from: private */
            public Object aJ;
            /* access modifiers changed from: private */
            public Object aM;
            /* access modifiers changed from: private */
            public Object aP;
            private final UnknownFieldSet aw;
            private byte ax;
            private int ay;
            /* access modifiers changed from: private */
            public long bb;
            /* access modifiers changed from: private */
            public Object bh;
            /* access modifiers changed from: private */
            public Object bj;
            /* access modifiers changed from: private */
            public Object ce;
            /* access modifiers changed from: private */
            public Object cf;
            /* access modifiers changed from: private */
            public Object cg;
            /* access modifiers changed from: private */
            public Object ch;
            /* access modifiers changed from: private */
            public Object ci;
            /* access modifiers changed from: private */
            public Object cj;
            /* access modifiers changed from: private */
            public List ck;

            public final class Builder extends GeneratedMessage.Builder implements ProtoErrorOrBuilder {
                private int aA;
                private Object aC;
                private Object aD;
                private Object aE;
                private Object aF;
                private Object aG;
                private Object aH;
                private int aI;
                private Object aJ;
                private Object aM;
                private Object aP;
                private long bb;
                private Object bh;
                private Object bj;
                private Object ce;
                private Object cf;
                private Object cg;
                private Object ch;
                private Object ci;
                private Object cj;
                private List ck;
                private RepeatedFieldBuilder cl;

                private Builder() {
                    this.aC = "";
                    this.aD = "";
                    this.aE = "";
                    this.aF = "";
                    this.ce = "";
                    this.aG = "";
                    this.cf = "";
                    this.cg = "";
                    this.ch = "";
                    this.ci = "";
                    this.cj = "";
                    this.ck = Collections.emptyList();
                    this.aJ = "";
                    this.aH = "";
                    this.aP = "";
                    this.aM = "";
                    this.bh = "";
                    this.bj = "";
                    R();
                }

                private Builder(GeneratedMessage.BuilderParent builderParent) {
                    super(builderParent);
                    this.aC = "";
                    this.aD = "";
                    this.aE = "";
                    this.aF = "";
                    this.ce = "";
                    this.aG = "";
                    this.cf = "";
                    this.cg = "";
                    this.ch = "";
                    this.ci = "";
                    this.cj = "";
                    this.ck = Collections.emptyList();
                    this.aJ = "";
                    this.aH = "";
                    this.aP = "";
                    this.aM = "";
                    this.bh = "";
                    this.bj = "";
                    R();
                }

                /* synthetic */ Builder(GeneratedMessage.BuilderParent builderParent, a aVar) {
                    this(builderParent);
                }

                private void R() {
                    if (ProtoError.alwaysUseFieldBuilders) {
                        bd();
                    }
                }

                /* access modifiers changed from: private */
                public static Builder bb() {
                    return new Builder();
                }

                private void bc() {
                    if ((this.aA & 4096) != 4096) {
                        this.ck = new ArrayList(this.ck);
                        this.aA |= 4096;
                    }
                }

                private RepeatedFieldBuilder bd() {
                    if (this.cl == null) {
                        this.cl = new RepeatedFieldBuilder(this.ck, (this.aA & 4096) == 4096, getParentForChildren(), isClean());
                        this.ck = null;
                    }
                    return this.cl;
                }

                public static final Descriptors.Descriptor getDescriptor() {
                    return ProtoMsg.ag;
                }

                public Builder addAllErrorMap(Iterable iterable) {
                    if (this.cl == null) {
                        bc();
                        AbstractMessageLite.Builder.addAll(iterable, this.ck);
                        onChanged();
                    } else {
                        this.cl.addAllMessages(iterable);
                    }
                    return this;
                }

                public Builder addErrorMap(int i, ProtoMap.Builder builder) {
                    if (this.cl == null) {
                        bc();
                        this.ck.add(i, builder.build());
                        onChanged();
                    } else {
                        this.cl.addMessage(i, builder.build());
                    }
                    return this;
                }

                public Builder addErrorMap(int i, ProtoMap protoMap) {
                    if (this.cl != null) {
                        this.cl.addMessage(i, protoMap);
                    } else if (protoMap != null) {
                        bc();
                        this.ck.add(i, protoMap);
                        onChanged();
                    } else {
                        throw new NullPointerException();
                    }
                    return this;
                }

                public Builder addErrorMap(ProtoMap.Builder builder) {
                    if (this.cl == null) {
                        bc();
                        this.ck.add(builder.build());
                        onChanged();
                    } else {
                        this.cl.addMessage(builder.build());
                    }
                    return this;
                }

                public Builder addErrorMap(ProtoMap protoMap) {
                    if (this.cl != null) {
                        this.cl.addMessage(protoMap);
                    } else if (protoMap != null) {
                        bc();
                        this.ck.add(protoMap);
                        onChanged();
                    } else {
                        throw new NullPointerException();
                    }
                    return this;
                }

                public ProtoMap.Builder addErrorMapBuilder() {
                    return (ProtoMap.Builder) bd().addBuilder(ProtoMap.getDefaultInstance());
                }

                public ProtoMap.Builder addErrorMapBuilder(int i) {
                    return (ProtoMap.Builder) bd().addBuilder(i, ProtoMap.getDefaultInstance());
                }

                public ProtoError build() {
                    ProtoError buildPartial = buildPartial();
                    if (buildPartial.isInitialized()) {
                        return buildPartial;
                    }
                    throw newUninitializedMessageException(buildPartial);
                }

                public ProtoError buildPartial() {
                    List list;
                    ProtoError protoError = new ProtoError((GeneratedMessage.Builder) this, (a) null);
                    int i = this.aA;
                    int i2 = 1;
                    if ((i & 1) != 1) {
                        i2 = 0;
                    }
                    Object unused = protoError.aC = this.aC;
                    if ((i & 2) == 2) {
                        i2 |= 2;
                    }
                    Object unused2 = protoError.aD = this.aD;
                    if ((i & 4) == 4) {
                        i2 |= 4;
                    }
                    Object unused3 = protoError.aE = this.aE;
                    if ((i & 8) == 8) {
                        i2 |= 8;
                    }
                    Object unused4 = protoError.aF = this.aF;
                    if ((i & 16) == 16) {
                        i2 |= 16;
                    }
                    Object unused5 = protoError.ce = this.ce;
                    if ((i & 32) == 32) {
                        i2 |= 32;
                    }
                    long unused6 = protoError.bb = this.bb;
                    if ((i & 64) == 64) {
                        i2 |= 64;
                    }
                    Object unused7 = protoError.aG = this.aG;
                    if ((i & 128) == 128) {
                        i2 |= 128;
                    }
                    Object unused8 = protoError.cf = this.cf;
                    if ((i & 256) == 256) {
                        i2 |= 256;
                    }
                    Object unused9 = protoError.cg = this.cg;
                    if ((i & 512) == 512) {
                        i2 |= 512;
                    }
                    Object unused10 = protoError.ch = this.ch;
                    if ((i & 1024) == 1024) {
                        i2 |= 1024;
                    }
                    Object unused11 = protoError.ci = this.ci;
                    if ((i & 2048) == 2048) {
                        i2 |= 2048;
                    }
                    Object unused12 = protoError.cj = this.cj;
                    if (this.cl == null) {
                        if ((this.aA & 4096) == 4096) {
                            this.ck = Collections.unmodifiableList(this.ck);
                            this.aA &= -4097;
                        }
                        list = this.ck;
                    } else {
                        list = this.cl.build();
                    }
                    List unused13 = protoError.ck = list;
                    if ((i & 8192) == 8192) {
                        i2 |= 4096;
                    }
                    int unused14 = protoError.aI = this.aI;
                    if ((i & 16384) == 16384) {
                        i2 |= 8192;
                    }
                    Object unused15 = protoError.aJ = this.aJ;
                    if ((32768 & i) == 32768) {
                        i2 |= 16384;
                    }
                    Object unused16 = protoError.aH = this.aH;
                    if ((65536 & i) == 65536) {
                        i2 |= 32768;
                    }
                    Object unused17 = protoError.aP = this.aP;
                    if ((131072 & i) == 131072) {
                        i2 |= 65536;
                    }
                    Object unused18 = protoError.aM = this.aM;
                    if ((262144 & i) == 262144) {
                        i2 |= 131072;
                    }
                    Object unused19 = protoError.bh = this.bh;
                    if ((i & 524288) == 524288) {
                        i2 |= 262144;
                    }
                    Object unused20 = protoError.bj = this.bj;
                    int unused21 = protoError.aA = i2;
                    onBuilt();
                    return protoError;
                }

                public Builder clear() {
                    super.clear();
                    this.aC = "";
                    this.aA &= -2;
                    this.aD = "";
                    this.aA &= -3;
                    this.aE = "";
                    this.aA &= -5;
                    this.aF = "";
                    this.aA &= -9;
                    this.ce = "";
                    this.aA &= -17;
                    this.bb = 0;
                    this.aA &= -33;
                    this.aG = "";
                    this.aA &= -65;
                    this.cf = "";
                    this.aA &= -129;
                    this.cg = "";
                    this.aA &= -257;
                    this.ch = "";
                    this.aA &= -513;
                    this.ci = "";
                    this.aA &= -1025;
                    this.cj = "";
                    this.aA &= -2049;
                    if (this.cl == null) {
                        this.ck = Collections.emptyList();
                        this.aA &= -4097;
                    } else {
                        this.cl.clear();
                    }
                    this.aI = 0;
                    this.aA &= -8193;
                    this.aJ = "";
                    this.aA &= -16385;
                    this.aH = "";
                    this.aA &= -32769;
                    this.aP = "";
                    this.aA &= -65537;
                    this.aM = "";
                    this.aA &= -131073;
                    this.bh = "";
                    this.aA &= -262145;
                    this.bj = "";
                    this.aA &= -524289;
                    return this;
                }

                public Builder clearActivity() {
                    this.aA &= -17;
                    this.ce = ProtoError.getDefaultInstance().getActivity();
                    onChanged();
                    return this;
                }

                public Builder clearAppChannel() {
                    this.aA &= -5;
                    this.aE = ProtoError.getDefaultInstance().getAppChannel();
                    onChanged();
                    return this;
                }

                public Builder clearAppKey() {
                    this.aA &= -3;
                    this.aD = ProtoError.getDefaultInstance().getAppKey();
                    onChanged();
                    return this;
                }

                public Builder clearDeviceId() {
                    this.aA &= -65;
                    this.aG = ProtoError.getDefaultInstance().getDeviceId();
                    onChanged();
                    return this;
                }

                public Builder clearDeviceName() {
                    this.aA &= -65537;
                    this.aP = ProtoError.getDefaultInstance().getDeviceName();
                    onChanged();
                    return this;
                }

                public Builder clearErrorMap() {
                    if (this.cl == null) {
                        this.ck = Collections.emptyList();
                        this.aA &= -4097;
                        onChanged();
                    } else {
                        this.cl.clear();
                    }
                    return this;
                }

                public Builder clearException() {
                    this.aA &= -129;
                    this.cf = ProtoError.getDefaultInstance().getException();
                    onChanged();
                    return this;
                }

                public Builder clearExceptionDetail() {
                    this.aA &= -257;
                    this.cg = ProtoError.getDefaultInstance().getExceptionDetail();
                    onChanged();
                    return this;
                }

                public Builder clearLogVersionApp() {
                    this.aA &= -524289;
                    this.bj = ProtoError.getDefaultInstance().getLogVersionApp();
                    onChanged();
                    return this;
                }

                public Builder clearNetwork() {
                    this.aA &= -1025;
                    this.ci = ProtoError.getDefaultInstance().getNetwork();
                    onChanged();
                    return this;
                }

                public Builder clearNetworkType() {
                    this.aA &= -513;
                    this.ch = ProtoError.getDefaultInstance().getNetworkType();
                    onChanged();
                    return this;
                }

                public Builder clearPackageName() {
                    this.aA &= -262145;
                    this.bh = ProtoError.getDefaultInstance().getPackageName();
                    onChanged();
                    return this;
                }

                public Builder clearPlatform() {
                    this.aA &= -131073;
                    this.aM = ProtoError.getDefaultInstance().getPlatform();
                    onChanged();
                    return this;
                }

                public Builder clearSessionId() {
                    this.aA &= -9;
                    this.aF = ProtoError.getDefaultInstance().getSessionId();
                    onChanged();
                    return this;
                }

                public Builder clearTime() {
                    this.aA &= -33;
                    this.bb = 0;
                    onChanged();
                    return this;
                }

                public Builder clearType() {
                    this.aA &= -2;
                    this.aC = ProtoError.getDefaultInstance().getType();
                    onChanged();
                    return this;
                }

                public Builder clearUrl() {
                    this.aA &= -2049;
                    this.cj = ProtoError.getDefaultInstance().getUrl();
                    onChanged();
                    return this;
                }

                public Builder clearUserId() {
                    this.aA &= -32769;
                    this.aH = ProtoError.getDefaultInstance().getUserId();
                    onChanged();
                    return this;
                }

                public Builder clearVersionCode() {
                    this.aA &= -8193;
                    this.aI = 0;
                    onChanged();
                    return this;
                }

                public Builder clearVersionName() {
                    this.aA &= -16385;
                    this.aJ = ProtoError.getDefaultInstance().getVersionName();
                    onChanged();
                    return this;
                }

                public Builder clone() {
                    return bb().mergeFrom(buildPartial());
                }

                public String getActivity() {
                    Object obj = this.ce;
                    if (obj instanceof String) {
                        return (String) obj;
                    }
                    ByteString byteString = (ByteString) obj;
                    String stringUtf8 = byteString.toStringUtf8();
                    if (byteString.isValidUtf8()) {
                        this.ce = stringUtf8;
                    }
                    return stringUtf8;
                }

                public ByteString getActivityBytes() {
                    Object obj = this.ce;
                    if (!(obj instanceof String)) {
                        return (ByteString) obj;
                    }
                    ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                    this.ce = copyFromUtf8;
                    return copyFromUtf8;
                }

                public String getAppChannel() {
                    Object obj = this.aE;
                    if (obj instanceof String) {
                        return (String) obj;
                    }
                    ByteString byteString = (ByteString) obj;
                    String stringUtf8 = byteString.toStringUtf8();
                    if (byteString.isValidUtf8()) {
                        this.aE = stringUtf8;
                    }
                    return stringUtf8;
                }

                public ByteString getAppChannelBytes() {
                    Object obj = this.aE;
                    if (!(obj instanceof String)) {
                        return (ByteString) obj;
                    }
                    ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                    this.aE = copyFromUtf8;
                    return copyFromUtf8;
                }

                public String getAppKey() {
                    Object obj = this.aD;
                    if (obj instanceof String) {
                        return (String) obj;
                    }
                    ByteString byteString = (ByteString) obj;
                    String stringUtf8 = byteString.toStringUtf8();
                    if (byteString.isValidUtf8()) {
                        this.aD = stringUtf8;
                    }
                    return stringUtf8;
                }

                public ByteString getAppKeyBytes() {
                    Object obj = this.aD;
                    if (!(obj instanceof String)) {
                        return (ByteString) obj;
                    }
                    ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                    this.aD = copyFromUtf8;
                    return copyFromUtf8;
                }

                public ProtoError getDefaultInstanceForType() {
                    return ProtoError.getDefaultInstance();
                }

                public Descriptors.Descriptor getDescriptorForType() {
                    return ProtoMsg.ag;
                }

                public String getDeviceId() {
                    Object obj = this.aG;
                    if (obj instanceof String) {
                        return (String) obj;
                    }
                    ByteString byteString = (ByteString) obj;
                    String stringUtf8 = byteString.toStringUtf8();
                    if (byteString.isValidUtf8()) {
                        this.aG = stringUtf8;
                    }
                    return stringUtf8;
                }

                public ByteString getDeviceIdBytes() {
                    Object obj = this.aG;
                    if (!(obj instanceof String)) {
                        return (ByteString) obj;
                    }
                    ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                    this.aG = copyFromUtf8;
                    return copyFromUtf8;
                }

                public String getDeviceName() {
                    Object obj = this.aP;
                    if (obj instanceof String) {
                        return (String) obj;
                    }
                    ByteString byteString = (ByteString) obj;
                    String stringUtf8 = byteString.toStringUtf8();
                    if (byteString.isValidUtf8()) {
                        this.aP = stringUtf8;
                    }
                    return stringUtf8;
                }

                public ByteString getDeviceNameBytes() {
                    Object obj = this.aP;
                    if (!(obj instanceof String)) {
                        return (ByteString) obj;
                    }
                    ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                    this.aP = copyFromUtf8;
                    return copyFromUtf8;
                }

                public ProtoMap getErrorMap(int i) {
                    return (ProtoMap) (this.cl == null ? this.ck.get(i) : this.cl.getMessage(i));
                }

                public ProtoMap.Builder getErrorMapBuilder(int i) {
                    return (ProtoMap.Builder) bd().getBuilder(i);
                }

                public List getErrorMapBuilderList() {
                    return bd().getBuilderList();
                }

                public int getErrorMapCount() {
                    return this.cl == null ? this.ck.size() : this.cl.getCount();
                }

                public List getErrorMapList() {
                    return this.cl == null ? Collections.unmodifiableList(this.ck) : this.cl.getMessageList();
                }

                public ProtoMapOrBuilder getErrorMapOrBuilder(int i) {
                    return (ProtoMapOrBuilder) (this.cl == null ? this.ck.get(i) : this.cl.getMessageOrBuilder(i));
                }

                public List getErrorMapOrBuilderList() {
                    return this.cl != null ? this.cl.getMessageOrBuilderList() : Collections.unmodifiableList(this.ck);
                }

                public String getException() {
                    Object obj = this.cf;
                    if (obj instanceof String) {
                        return (String) obj;
                    }
                    ByteString byteString = (ByteString) obj;
                    String stringUtf8 = byteString.toStringUtf8();
                    if (byteString.isValidUtf8()) {
                        this.cf = stringUtf8;
                    }
                    return stringUtf8;
                }

                public ByteString getExceptionBytes() {
                    Object obj = this.cf;
                    if (!(obj instanceof String)) {
                        return (ByteString) obj;
                    }
                    ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                    this.cf = copyFromUtf8;
                    return copyFromUtf8;
                }

                public String getExceptionDetail() {
                    Object obj = this.cg;
                    if (obj instanceof String) {
                        return (String) obj;
                    }
                    ByteString byteString = (ByteString) obj;
                    String stringUtf8 = byteString.toStringUtf8();
                    if (byteString.isValidUtf8()) {
                        this.cg = stringUtf8;
                    }
                    return stringUtf8;
                }

                public ByteString getExceptionDetailBytes() {
                    Object obj = this.cg;
                    if (!(obj instanceof String)) {
                        return (ByteString) obj;
                    }
                    ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                    this.cg = copyFromUtf8;
                    return copyFromUtf8;
                }

                public String getLogVersionApp() {
                    Object obj = this.bj;
                    if (obj instanceof String) {
                        return (String) obj;
                    }
                    ByteString byteString = (ByteString) obj;
                    String stringUtf8 = byteString.toStringUtf8();
                    if (byteString.isValidUtf8()) {
                        this.bj = stringUtf8;
                    }
                    return stringUtf8;
                }

                public ByteString getLogVersionAppBytes() {
                    Object obj = this.bj;
                    if (!(obj instanceof String)) {
                        return (ByteString) obj;
                    }
                    ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                    this.bj = copyFromUtf8;
                    return copyFromUtf8;
                }

                public String getNetwork() {
                    Object obj = this.ci;
                    if (obj instanceof String) {
                        return (String) obj;
                    }
                    ByteString byteString = (ByteString) obj;
                    String stringUtf8 = byteString.toStringUtf8();
                    if (byteString.isValidUtf8()) {
                        this.ci = stringUtf8;
                    }
                    return stringUtf8;
                }

                public ByteString getNetworkBytes() {
                    Object obj = this.ci;
                    if (!(obj instanceof String)) {
                        return (ByteString) obj;
                    }
                    ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                    this.ci = copyFromUtf8;
                    return copyFromUtf8;
                }

                public String getNetworkType() {
                    Object obj = this.ch;
                    if (obj instanceof String) {
                        return (String) obj;
                    }
                    ByteString byteString = (ByteString) obj;
                    String stringUtf8 = byteString.toStringUtf8();
                    if (byteString.isValidUtf8()) {
                        this.ch = stringUtf8;
                    }
                    return stringUtf8;
                }

                public ByteString getNetworkTypeBytes() {
                    Object obj = this.ch;
                    if (!(obj instanceof String)) {
                        return (ByteString) obj;
                    }
                    ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                    this.ch = copyFromUtf8;
                    return copyFromUtf8;
                }

                public String getPackageName() {
                    Object obj = this.bh;
                    if (obj instanceof String) {
                        return (String) obj;
                    }
                    ByteString byteString = (ByteString) obj;
                    String stringUtf8 = byteString.toStringUtf8();
                    if (byteString.isValidUtf8()) {
                        this.bh = stringUtf8;
                    }
                    return stringUtf8;
                }

                public ByteString getPackageNameBytes() {
                    Object obj = this.bh;
                    if (!(obj instanceof String)) {
                        return (ByteString) obj;
                    }
                    ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                    this.bh = copyFromUtf8;
                    return copyFromUtf8;
                }

                public String getPlatform() {
                    Object obj = this.aM;
                    if (obj instanceof String) {
                        return (String) obj;
                    }
                    ByteString byteString = (ByteString) obj;
                    String stringUtf8 = byteString.toStringUtf8();
                    if (byteString.isValidUtf8()) {
                        this.aM = stringUtf8;
                    }
                    return stringUtf8;
                }

                public ByteString getPlatformBytes() {
                    Object obj = this.aM;
                    if (!(obj instanceof String)) {
                        return (ByteString) obj;
                    }
                    ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                    this.aM = copyFromUtf8;
                    return copyFromUtf8;
                }

                public String getSessionId() {
                    Object obj = this.aF;
                    if (obj instanceof String) {
                        return (String) obj;
                    }
                    ByteString byteString = (ByteString) obj;
                    String stringUtf8 = byteString.toStringUtf8();
                    if (byteString.isValidUtf8()) {
                        this.aF = stringUtf8;
                    }
                    return stringUtf8;
                }

                public ByteString getSessionIdBytes() {
                    Object obj = this.aF;
                    if (!(obj instanceof String)) {
                        return (ByteString) obj;
                    }
                    ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                    this.aF = copyFromUtf8;
                    return copyFromUtf8;
                }

                public long getTime() {
                    return this.bb;
                }

                public String getType() {
                    Object obj = this.aC;
                    if (obj instanceof String) {
                        return (String) obj;
                    }
                    ByteString byteString = (ByteString) obj;
                    String stringUtf8 = byteString.toStringUtf8();
                    if (byteString.isValidUtf8()) {
                        this.aC = stringUtf8;
                    }
                    return stringUtf8;
                }

                public ByteString getTypeBytes() {
                    Object obj = this.aC;
                    if (!(obj instanceof String)) {
                        return (ByteString) obj;
                    }
                    ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                    this.aC = copyFromUtf8;
                    return copyFromUtf8;
                }

                public String getUrl() {
                    Object obj = this.cj;
                    if (obj instanceof String) {
                        return (String) obj;
                    }
                    ByteString byteString = (ByteString) obj;
                    String stringUtf8 = byteString.toStringUtf8();
                    if (byteString.isValidUtf8()) {
                        this.cj = stringUtf8;
                    }
                    return stringUtf8;
                }

                public ByteString getUrlBytes() {
                    Object obj = this.cj;
                    if (!(obj instanceof String)) {
                        return (ByteString) obj;
                    }
                    ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                    this.cj = copyFromUtf8;
                    return copyFromUtf8;
                }

                public String getUserId() {
                    Object obj = this.aH;
                    if (obj instanceof String) {
                        return (String) obj;
                    }
                    ByteString byteString = (ByteString) obj;
                    String stringUtf8 = byteString.toStringUtf8();
                    if (byteString.isValidUtf8()) {
                        this.aH = stringUtf8;
                    }
                    return stringUtf8;
                }

                public ByteString getUserIdBytes() {
                    Object obj = this.aH;
                    if (!(obj instanceof String)) {
                        return (ByteString) obj;
                    }
                    ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                    this.aH = copyFromUtf8;
                    return copyFromUtf8;
                }

                public int getVersionCode() {
                    return this.aI;
                }

                public String getVersionName() {
                    Object obj = this.aJ;
                    if (obj instanceof String) {
                        return (String) obj;
                    }
                    ByteString byteString = (ByteString) obj;
                    String stringUtf8 = byteString.toStringUtf8();
                    if (byteString.isValidUtf8()) {
                        this.aJ = stringUtf8;
                    }
                    return stringUtf8;
                }

                public ByteString getVersionNameBytes() {
                    Object obj = this.aJ;
                    if (!(obj instanceof String)) {
                        return (ByteString) obj;
                    }
                    ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                    this.aJ = copyFromUtf8;
                    return copyFromUtf8;
                }

                public boolean hasActivity() {
                    return (this.aA & 16) == 16;
                }

                public boolean hasAppChannel() {
                    return (this.aA & 4) == 4;
                }

                public boolean hasAppKey() {
                    return (this.aA & 2) == 2;
                }

                public boolean hasDeviceId() {
                    return (this.aA & 64) == 64;
                }

                public boolean hasDeviceName() {
                    return (this.aA & 65536) == 65536;
                }

                public boolean hasException() {
                    return (this.aA & 128) == 128;
                }

                public boolean hasExceptionDetail() {
                    return (this.aA & 256) == 256;
                }

                public boolean hasLogVersionApp() {
                    return (this.aA & 524288) == 524288;
                }

                public boolean hasNetwork() {
                    return (this.aA & 1024) == 1024;
                }

                public boolean hasNetworkType() {
                    return (this.aA & 512) == 512;
                }

                public boolean hasPackageName() {
                    return (this.aA & 262144) == 262144;
                }

                public boolean hasPlatform() {
                    return (this.aA & 131072) == 131072;
                }

                public boolean hasSessionId() {
                    return (this.aA & 8) == 8;
                }

                public boolean hasTime() {
                    return (this.aA & 32) == 32;
                }

                public boolean hasType() {
                    return (this.aA & 1) == 1;
                }

                public boolean hasUrl() {
                    return (this.aA & 2048) == 2048;
                }

                public boolean hasUserId() {
                    return (this.aA & 32768) == 32768;
                }

                public boolean hasVersionCode() {
                    return (this.aA & 8192) == 8192;
                }

                public boolean hasVersionName() {
                    return (this.aA & 16384) == 16384;
                }

                /* access modifiers changed from: protected */
                public GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
                    return ProtoMsg.ah.ensureFieldAccessorsInitialized(ProtoError.class, Builder.class);
                }

                public final boolean isInitialized() {
                    if (!hasType()) {
                        return false;
                    }
                    for (int i = 0; i < getErrorMapCount(); i++) {
                        if (!getErrorMap(i).isInitialized()) {
                            return false;
                        }
                    }
                    return true;
                }

                public Builder mergeFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) {
                    ProtoError protoError;
                    ProtoError protoError2 = null;
                    try {
                        ProtoError protoError3 = (ProtoError) ProtoError.PARSER.parsePartialFrom(codedInputStream, extensionRegistryLite);
                        if (protoError3 != null) {
                            mergeFrom(protoError3);
                        }
                        return this;
                    } catch (InvalidProtocolBufferException e) {
                        protoError = (ProtoError) e.getUnfinishedMessage();
                        throw e;
                    } catch (Throwable th) {
                        th = th;
                        protoError2 = protoError;
                    }
                    if (protoError2 != null) {
                        mergeFrom(protoError2);
                    }
                    throw th;
                }

                public Builder mergeFrom(Message message) {
                    if (message instanceof ProtoError) {
                        return mergeFrom((ProtoError) message);
                    }
                    super.mergeFrom(message);
                    return this;
                }

                public Builder mergeFrom(ProtoError protoError) {
                    if (protoError == ProtoError.getDefaultInstance()) {
                        return this;
                    }
                    if (protoError.hasType()) {
                        this.aA |= 1;
                        this.aC = protoError.aC;
                        onChanged();
                    }
                    if (protoError.hasAppKey()) {
                        this.aA |= 2;
                        this.aD = protoError.aD;
                        onChanged();
                    }
                    if (protoError.hasAppChannel()) {
                        this.aA |= 4;
                        this.aE = protoError.aE;
                        onChanged();
                    }
                    if (protoError.hasSessionId()) {
                        this.aA |= 8;
                        this.aF = protoError.aF;
                        onChanged();
                    }
                    if (protoError.hasActivity()) {
                        this.aA |= 16;
                        this.ce = protoError.ce;
                        onChanged();
                    }
                    if (protoError.hasTime()) {
                        setTime(protoError.getTime());
                    }
                    if (protoError.hasDeviceId()) {
                        this.aA |= 64;
                        this.aG = protoError.aG;
                        onChanged();
                    }
                    if (protoError.hasException()) {
                        this.aA |= 128;
                        this.cf = protoError.cf;
                        onChanged();
                    }
                    if (protoError.hasExceptionDetail()) {
                        this.aA |= 256;
                        this.cg = protoError.cg;
                        onChanged();
                    }
                    if (protoError.hasNetworkType()) {
                        this.aA |= 512;
                        this.ch = protoError.ch;
                        onChanged();
                    }
                    if (protoError.hasNetwork()) {
                        this.aA |= 1024;
                        this.ci = protoError.ci;
                        onChanged();
                    }
                    if (protoError.hasUrl()) {
                        this.aA |= 2048;
                        this.cj = protoError.cj;
                        onChanged();
                    }
                    if (this.cl == null) {
                        if (!protoError.ck.isEmpty()) {
                            if (this.ck.isEmpty()) {
                                this.ck = protoError.ck;
                                this.aA &= -4097;
                            } else {
                                bc();
                                this.ck.addAll(protoError.ck);
                            }
                            onChanged();
                        }
                    } else if (!protoError.ck.isEmpty()) {
                        if (this.cl.isEmpty()) {
                            this.cl.dispose();
                            RepeatedFieldBuilder repeatedFieldBuilder = null;
                            this.cl = null;
                            this.ck = protoError.ck;
                            this.aA &= -4097;
                            if (ProtoError.alwaysUseFieldBuilders) {
                                repeatedFieldBuilder = bd();
                            }
                            this.cl = repeatedFieldBuilder;
                        } else {
                            this.cl.addAllMessages(protoError.ck);
                        }
                    }
                    if (protoError.hasVersionCode()) {
                        setVersionCode(protoError.getVersionCode());
                    }
                    if (protoError.hasVersionName()) {
                        this.aA |= 16384;
                        this.aJ = protoError.aJ;
                        onChanged();
                    }
                    if (protoError.hasUserId()) {
                        this.aA |= 32768;
                        this.aH = protoError.aH;
                        onChanged();
                    }
                    if (protoError.hasDeviceName()) {
                        this.aA |= 65536;
                        this.aP = protoError.aP;
                        onChanged();
                    }
                    if (protoError.hasPlatform()) {
                        this.aA |= 131072;
                        this.aM = protoError.aM;
                        onChanged();
                    }
                    if (protoError.hasPackageName()) {
                        this.aA |= 262144;
                        this.bh = protoError.bh;
                        onChanged();
                    }
                    if (protoError.hasLogVersionApp()) {
                        this.aA |= 524288;
                        this.bj = protoError.bj;
                        onChanged();
                    }
                    mergeUnknownFields(protoError.getUnknownFields());
                    return this;
                }

                public Builder removeErrorMap(int i) {
                    if (this.cl == null) {
                        bc();
                        this.ck.remove(i);
                        onChanged();
                    } else {
                        this.cl.remove(i);
                    }
                    return this;
                }

                public Builder setActivity(String str) {
                    if (str != null) {
                        this.aA |= 16;
                        this.ce = str;
                        onChanged();
                        return this;
                    }
                    throw new NullPointerException();
                }

                public Builder setActivityBytes(ByteString byteString) {
                    if (byteString != null) {
                        this.aA |= 16;
                        this.ce = byteString;
                        onChanged();
                        return this;
                    }
                    throw new NullPointerException();
                }

                public Builder setAppChannel(String str) {
                    if (str != null) {
                        this.aA |= 4;
                        this.aE = str;
                        onChanged();
                        return this;
                    }
                    throw new NullPointerException();
                }

                public Builder setAppChannelBytes(ByteString byteString) {
                    if (byteString != null) {
                        this.aA |= 4;
                        this.aE = byteString;
                        onChanged();
                        return this;
                    }
                    throw new NullPointerException();
                }

                public Builder setAppKey(String str) {
                    if (str != null) {
                        this.aA |= 2;
                        this.aD = str;
                        onChanged();
                        return this;
                    }
                    throw new NullPointerException();
                }

                public Builder setAppKeyBytes(ByteString byteString) {
                    if (byteString != null) {
                        this.aA |= 2;
                        this.aD = byteString;
                        onChanged();
                        return this;
                    }
                    throw new NullPointerException();
                }

                public Builder setDeviceId(String str) {
                    if (str != null) {
                        this.aA |= 64;
                        this.aG = str;
                        onChanged();
                        return this;
                    }
                    throw new NullPointerException();
                }

                public Builder setDeviceIdBytes(ByteString byteString) {
                    if (byteString != null) {
                        this.aA |= 64;
                        this.aG = byteString;
                        onChanged();
                        return this;
                    }
                    throw new NullPointerException();
                }

                public Builder setDeviceName(String str) {
                    if (str != null) {
                        this.aA |= 65536;
                        this.aP = str;
                        onChanged();
                        return this;
                    }
                    throw new NullPointerException();
                }

                public Builder setDeviceNameBytes(ByteString byteString) {
                    if (byteString != null) {
                        this.aA |= 65536;
                        this.aP = byteString;
                        onChanged();
                        return this;
                    }
                    throw new NullPointerException();
                }

                public Builder setErrorMap(int i, ProtoMap.Builder builder) {
                    if (this.cl == null) {
                        bc();
                        this.ck.set(i, builder.build());
                        onChanged();
                    } else {
                        this.cl.setMessage(i, builder.build());
                    }
                    return this;
                }

                public Builder setErrorMap(int i, ProtoMap protoMap) {
                    if (this.cl != null) {
                        this.cl.setMessage(i, protoMap);
                    } else if (protoMap != null) {
                        bc();
                        this.ck.set(i, protoMap);
                        onChanged();
                    } else {
                        throw new NullPointerException();
                    }
                    return this;
                }

                public Builder setException(String str) {
                    if (str != null) {
                        this.aA |= 128;
                        this.cf = str;
                        onChanged();
                        return this;
                    }
                    throw new NullPointerException();
                }

                public Builder setExceptionBytes(ByteString byteString) {
                    if (byteString != null) {
                        this.aA |= 128;
                        this.cf = byteString;
                        onChanged();
                        return this;
                    }
                    throw new NullPointerException();
                }

                public Builder setExceptionDetail(String str) {
                    if (str != null) {
                        this.aA |= 256;
                        this.cg = str;
                        onChanged();
                        return this;
                    }
                    throw new NullPointerException();
                }

                public Builder setExceptionDetailBytes(ByteString byteString) {
                    if (byteString != null) {
                        this.aA |= 256;
                        this.cg = byteString;
                        onChanged();
                        return this;
                    }
                    throw new NullPointerException();
                }

                public Builder setLogVersionApp(String str) {
                    if (str != null) {
                        this.aA |= 524288;
                        this.bj = str;
                        onChanged();
                        return this;
                    }
                    throw new NullPointerException();
                }

                public Builder setLogVersionAppBytes(ByteString byteString) {
                    if (byteString != null) {
                        this.aA |= 524288;
                        this.bj = byteString;
                        onChanged();
                        return this;
                    }
                    throw new NullPointerException();
                }

                public Builder setNetwork(String str) {
                    if (str != null) {
                        this.aA |= 1024;
                        this.ci = str;
                        onChanged();
                        return this;
                    }
                    throw new NullPointerException();
                }

                public Builder setNetworkBytes(ByteString byteString) {
                    if (byteString != null) {
                        this.aA |= 1024;
                        this.ci = byteString;
                        onChanged();
                        return this;
                    }
                    throw new NullPointerException();
                }

                public Builder setNetworkType(String str) {
                    if (str != null) {
                        this.aA |= 512;
                        this.ch = str;
                        onChanged();
                        return this;
                    }
                    throw new NullPointerException();
                }

                public Builder setNetworkTypeBytes(ByteString byteString) {
                    if (byteString != null) {
                        this.aA |= 512;
                        this.ch = byteString;
                        onChanged();
                        return this;
                    }
                    throw new NullPointerException();
                }

                public Builder setPackageName(String str) {
                    if (str != null) {
                        this.aA |= 262144;
                        this.bh = str;
                        onChanged();
                        return this;
                    }
                    throw new NullPointerException();
                }

                public Builder setPackageNameBytes(ByteString byteString) {
                    if (byteString != null) {
                        this.aA |= 262144;
                        this.bh = byteString;
                        onChanged();
                        return this;
                    }
                    throw new NullPointerException();
                }

                public Builder setPlatform(String str) {
                    if (str != null) {
                        this.aA |= 131072;
                        this.aM = str;
                        onChanged();
                        return this;
                    }
                    throw new NullPointerException();
                }

                public Builder setPlatformBytes(ByteString byteString) {
                    if (byteString != null) {
                        this.aA |= 131072;
                        this.aM = byteString;
                        onChanged();
                        return this;
                    }
                    throw new NullPointerException();
                }

                public Builder setSessionId(String str) {
                    if (str != null) {
                        this.aA |= 8;
                        this.aF = str;
                        onChanged();
                        return this;
                    }
                    throw new NullPointerException();
                }

                public Builder setSessionIdBytes(ByteString byteString) {
                    if (byteString != null) {
                        this.aA |= 8;
                        this.aF = byteString;
                        onChanged();
                        return this;
                    }
                    throw new NullPointerException();
                }

                public Builder setTime(long j) {
                    this.aA |= 32;
                    this.bb = j;
                    onChanged();
                    return this;
                }

                public Builder setType(String str) {
                    if (str != null) {
                        this.aA |= 1;
                        this.aC = str;
                        onChanged();
                        return this;
                    }
                    throw new NullPointerException();
                }

                public Builder setTypeBytes(ByteString byteString) {
                    if (byteString != null) {
                        this.aA |= 1;
                        this.aC = byteString;
                        onChanged();
                        return this;
                    }
                    throw new NullPointerException();
                }

                public Builder setUrl(String str) {
                    if (str != null) {
                        this.aA |= 2048;
                        this.cj = str;
                        onChanged();
                        return this;
                    }
                    throw new NullPointerException();
                }

                public Builder setUrlBytes(ByteString byteString) {
                    if (byteString != null) {
                        this.aA |= 2048;
                        this.cj = byteString;
                        onChanged();
                        return this;
                    }
                    throw new NullPointerException();
                }

                public Builder setUserId(String str) {
                    if (str != null) {
                        this.aA |= 32768;
                        this.aH = str;
                        onChanged();
                        return this;
                    }
                    throw new NullPointerException();
                }

                public Builder setUserIdBytes(ByteString byteString) {
                    if (byteString != null) {
                        this.aA |= 32768;
                        this.aH = byteString;
                        onChanged();
                        return this;
                    }
                    throw new NullPointerException();
                }

                public Builder setVersionCode(int i) {
                    this.aA |= 8192;
                    this.aI = i;
                    onChanged();
                    return this;
                }

                public Builder setVersionName(String str) {
                    if (str != null) {
                        this.aA |= 16384;
                        this.aJ = str;
                        onChanged();
                        return this;
                    }
                    throw new NullPointerException();
                }

                public Builder setVersionNameBytes(ByteString byteString) {
                    if (byteString != null) {
                        this.aA |= 16384;
                        this.aJ = byteString;
                        onChanged();
                        return this;
                    }
                    throw new NullPointerException();
                }
            }

            static {
                cd.P();
            }

            private ProtoError(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) {
                this.ax = -1;
                this.ay = -1;
                P();
                UnknownFieldSet.Builder newBuilder = UnknownFieldSet.newBuilder();
                boolean z = false;
                boolean z2 = false;
                while (!z) {
                    try {
                        int readTag = codedInputStream.readTag();
                        switch (readTag) {
                            case 0:
                                z = true;
                                break;
                            case 10:
                                ByteString readBytes = codedInputStream.readBytes();
                                this.aA = 1 | this.aA;
                                this.aC = readBytes;
                                break;
                            case 18:
                                ByteString readBytes2 = codedInputStream.readBytes();
                                this.aA |= 2;
                                this.aD = readBytes2;
                                break;
                            case 26:
                                ByteString readBytes3 = codedInputStream.readBytes();
                                this.aA |= 4;
                                this.aE = readBytes3;
                                break;
                            case 34:
                                ByteString readBytes4 = codedInputStream.readBytes();
                                this.aA |= 8;
                                this.aF = readBytes4;
                                break;
                            case 42:
                                ByteString readBytes5 = codedInputStream.readBytes();
                                this.aA |= 16;
                                this.ce = readBytes5;
                                break;
                            case 48:
                                this.aA |= 32;
                                this.bb = codedInputStream.readInt64();
                                break;
                            case 58:
                                ByteString readBytes6 = codedInputStream.readBytes();
                                this.aA |= 64;
                                this.aG = readBytes6;
                                break;
                            case 66:
                                ByteString readBytes7 = codedInputStream.readBytes();
                                this.aA |= 128;
                                this.cf = readBytes7;
                                break;
                            case 74:
                                ByteString readBytes8 = codedInputStream.readBytes();
                                this.aA |= 256;
                                this.cg = readBytes8;
                                break;
                            case 82:
                                ByteString readBytes9 = codedInputStream.readBytes();
                                this.aA |= 512;
                                this.ch = readBytes9;
                                break;
                            case 90:
                                ByteString readBytes10 = codedInputStream.readBytes();
                                this.aA |= 1024;
                                this.ci = readBytes10;
                                break;
                            case 98:
                                ByteString readBytes11 = codedInputStream.readBytes();
                                this.aA |= 2048;
                                this.cj = readBytes11;
                                break;
                            case 106:
                                if (!(z2 & true)) {
                                    this.ck = new ArrayList();
                                    z2 |= true;
                                }
                                this.ck.add(codedInputStream.readMessage(ProtoMap.PARSER, extensionRegistryLite));
                                break;
                            case 112:
                                this.aA |= 4096;
                                this.aI = codedInputStream.readInt32();
                                break;
                            case 122:
                                ByteString readBytes12 = codedInputStream.readBytes();
                                this.aA |= 8192;
                                this.aJ = readBytes12;
                                break;
                            case 130:
                                ByteString readBytes13 = codedInputStream.readBytes();
                                this.aA |= 16384;
                                this.aH = readBytes13;
                                break;
                            case 138:
                                ByteString readBytes14 = codedInputStream.readBytes();
                                this.aA |= 32768;
                                this.aP = readBytes14;
                                break;
                            case 146:
                                ByteString readBytes15 = codedInputStream.readBytes();
                                this.aA |= 65536;
                                this.aM = readBytes15;
                                break;
                            case 154:
                                ByteString readBytes16 = codedInputStream.readBytes();
                                this.aA |= 131072;
                                this.bh = readBytes16;
                                break;
                            case 162:
                                ByteString readBytes17 = codedInputStream.readBytes();
                                this.aA |= 262144;
                                this.bj = readBytes17;
                                break;
                            default:
                                if (parseUnknownField(codedInputStream, newBuilder, extensionRegistryLite, readTag)) {
                                    break;
                                }
                                z = true;
                                break;
                        }
                    } catch (InvalidProtocolBufferException e) {
                        throw e.setUnfinishedMessage(this);
                    } catch (IOException e2) {
                        throw new InvalidProtocolBufferException(e2.getMessage()).setUnfinishedMessage(this);
                    } catch (Throwable th) {
                        if (z2 & true) {
                            this.ck = Collections.unmodifiableList(this.ck);
                        }
                        this.aw = newBuilder.build();
                        makeExtensionsImmutable();
                        throw th;
                    }
                }
                if (z2 & true) {
                    this.ck = Collections.unmodifiableList(this.ck);
                }
                this.aw = newBuilder.build();
                makeExtensionsImmutable();
            }

            /* synthetic */ ProtoError(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite, a aVar) {
                this(codedInputStream, extensionRegistryLite);
            }

            private ProtoError(GeneratedMessage.Builder builder) {
                super(builder);
                this.ax = -1;
                this.ay = -1;
                this.aw = builder.getUnknownFields();
            }

            /* synthetic */ ProtoError(GeneratedMessage.Builder builder, a aVar) {
                this(builder);
            }

            private ProtoError(boolean z) {
                this.ax = -1;
                this.ay = -1;
                this.aw = UnknownFieldSet.getDefaultInstance();
            }

            private void P() {
                this.aC = "";
                this.aD = "";
                this.aE = "";
                this.aF = "";
                this.ce = "";
                this.bb = 0;
                this.aG = "";
                this.cf = "";
                this.cg = "";
                this.ch = "";
                this.ci = "";
                this.cj = "";
                this.ck = Collections.emptyList();
                this.aI = 0;
                this.aJ = "";
                this.aH = "";
                this.aP = "";
                this.aM = "";
                this.bh = "";
                this.bj = "";
            }

            public static ProtoError getDefaultInstance() {
                return cd;
            }

            public static final Descriptors.Descriptor getDescriptor() {
                return ProtoMsg.ag;
            }

            public static Builder newBuilder() {
                return Builder.bb();
            }

            public static Builder newBuilder(ProtoError protoError) {
                return newBuilder().mergeFrom(protoError);
            }

            public static ProtoError parseDelimitedFrom(InputStream inputStream) {
                return (ProtoError) PARSER.parseDelimitedFrom(inputStream);
            }

            public static ProtoError parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) {
                return (ProtoError) PARSER.parseDelimitedFrom(inputStream, extensionRegistryLite);
            }

            public static ProtoError parseFrom(ByteString byteString) {
                return (ProtoError) PARSER.parseFrom(byteString);
            }

            public static ProtoError parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) {
                return (ProtoError) PARSER.parseFrom(byteString, extensionRegistryLite);
            }

            public static ProtoError parseFrom(CodedInputStream codedInputStream) {
                return (ProtoError) PARSER.parseFrom(codedInputStream);
            }

            public static ProtoError parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) {
                return (ProtoError) PARSER.parseFrom(codedInputStream, extensionRegistryLite);
            }

            public static ProtoError parseFrom(InputStream inputStream) {
                return (ProtoError) PARSER.parseFrom(inputStream);
            }

            public static ProtoError parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) {
                return (ProtoError) PARSER.parseFrom(inputStream, extensionRegistryLite);
            }

            public static ProtoError parseFrom(byte[] bArr) {
                return (ProtoError) PARSER.parseFrom(bArr);
            }

            public static ProtoError parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) {
                return (ProtoError) PARSER.parseFrom(bArr, extensionRegistryLite);
            }

            public String getActivity() {
                Object obj = this.ce;
                if (obj instanceof String) {
                    return (String) obj;
                }
                ByteString byteString = (ByteString) obj;
                String stringUtf8 = byteString.toStringUtf8();
                if (byteString.isValidUtf8()) {
                    this.ce = stringUtf8;
                }
                return stringUtf8;
            }

            public ByteString getActivityBytes() {
                Object obj = this.ce;
                if (!(obj instanceof String)) {
                    return (ByteString) obj;
                }
                ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                this.ce = copyFromUtf8;
                return copyFromUtf8;
            }

            public String getAppChannel() {
                Object obj = this.aE;
                if (obj instanceof String) {
                    return (String) obj;
                }
                ByteString byteString = (ByteString) obj;
                String stringUtf8 = byteString.toStringUtf8();
                if (byteString.isValidUtf8()) {
                    this.aE = stringUtf8;
                }
                return stringUtf8;
            }

            public ByteString getAppChannelBytes() {
                Object obj = this.aE;
                if (!(obj instanceof String)) {
                    return (ByteString) obj;
                }
                ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                this.aE = copyFromUtf8;
                return copyFromUtf8;
            }

            public String getAppKey() {
                Object obj = this.aD;
                if (obj instanceof String) {
                    return (String) obj;
                }
                ByteString byteString = (ByteString) obj;
                String stringUtf8 = byteString.toStringUtf8();
                if (byteString.isValidUtf8()) {
                    this.aD = stringUtf8;
                }
                return stringUtf8;
            }

            public ByteString getAppKeyBytes() {
                Object obj = this.aD;
                if (!(obj instanceof String)) {
                    return (ByteString) obj;
                }
                ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                this.aD = copyFromUtf8;
                return copyFromUtf8;
            }

            public ProtoError getDefaultInstanceForType() {
                return cd;
            }

            public String getDeviceId() {
                Object obj = this.aG;
                if (obj instanceof String) {
                    return (String) obj;
                }
                ByteString byteString = (ByteString) obj;
                String stringUtf8 = byteString.toStringUtf8();
                if (byteString.isValidUtf8()) {
                    this.aG = stringUtf8;
                }
                return stringUtf8;
            }

            public ByteString getDeviceIdBytes() {
                Object obj = this.aG;
                if (!(obj instanceof String)) {
                    return (ByteString) obj;
                }
                ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                this.aG = copyFromUtf8;
                return copyFromUtf8;
            }

            public String getDeviceName() {
                Object obj = this.aP;
                if (obj instanceof String) {
                    return (String) obj;
                }
                ByteString byteString = (ByteString) obj;
                String stringUtf8 = byteString.toStringUtf8();
                if (byteString.isValidUtf8()) {
                    this.aP = stringUtf8;
                }
                return stringUtf8;
            }

            public ByteString getDeviceNameBytes() {
                Object obj = this.aP;
                if (!(obj instanceof String)) {
                    return (ByteString) obj;
                }
                ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                this.aP = copyFromUtf8;
                return copyFromUtf8;
            }

            public ProtoMap getErrorMap(int i) {
                return (ProtoMap) this.ck.get(i);
            }

            public int getErrorMapCount() {
                return this.ck.size();
            }

            public List getErrorMapList() {
                return this.ck;
            }

            public ProtoMapOrBuilder getErrorMapOrBuilder(int i) {
                return (ProtoMapOrBuilder) this.ck.get(i);
            }

            public List getErrorMapOrBuilderList() {
                return this.ck;
            }

            public String getException() {
                Object obj = this.cf;
                if (obj instanceof String) {
                    return (String) obj;
                }
                ByteString byteString = (ByteString) obj;
                String stringUtf8 = byteString.toStringUtf8();
                if (byteString.isValidUtf8()) {
                    this.cf = stringUtf8;
                }
                return stringUtf8;
            }

            public ByteString getExceptionBytes() {
                Object obj = this.cf;
                if (!(obj instanceof String)) {
                    return (ByteString) obj;
                }
                ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                this.cf = copyFromUtf8;
                return copyFromUtf8;
            }

            public String getExceptionDetail() {
                Object obj = this.cg;
                if (obj instanceof String) {
                    return (String) obj;
                }
                ByteString byteString = (ByteString) obj;
                String stringUtf8 = byteString.toStringUtf8();
                if (byteString.isValidUtf8()) {
                    this.cg = stringUtf8;
                }
                return stringUtf8;
            }

            public ByteString getExceptionDetailBytes() {
                Object obj = this.cg;
                if (!(obj instanceof String)) {
                    return (ByteString) obj;
                }
                ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                this.cg = copyFromUtf8;
                return copyFromUtf8;
            }

            public String getLogVersionApp() {
                Object obj = this.bj;
                if (obj instanceof String) {
                    return (String) obj;
                }
                ByteString byteString = (ByteString) obj;
                String stringUtf8 = byteString.toStringUtf8();
                if (byteString.isValidUtf8()) {
                    this.bj = stringUtf8;
                }
                return stringUtf8;
            }

            public ByteString getLogVersionAppBytes() {
                Object obj = this.bj;
                if (!(obj instanceof String)) {
                    return (ByteString) obj;
                }
                ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                this.bj = copyFromUtf8;
                return copyFromUtf8;
            }

            public String getNetwork() {
                Object obj = this.ci;
                if (obj instanceof String) {
                    return (String) obj;
                }
                ByteString byteString = (ByteString) obj;
                String stringUtf8 = byteString.toStringUtf8();
                if (byteString.isValidUtf8()) {
                    this.ci = stringUtf8;
                }
                return stringUtf8;
            }

            public ByteString getNetworkBytes() {
                Object obj = this.ci;
                if (!(obj instanceof String)) {
                    return (ByteString) obj;
                }
                ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                this.ci = copyFromUtf8;
                return copyFromUtf8;
            }

            public String getNetworkType() {
                Object obj = this.ch;
                if (obj instanceof String) {
                    return (String) obj;
                }
                ByteString byteString = (ByteString) obj;
                String stringUtf8 = byteString.toStringUtf8();
                if (byteString.isValidUtf8()) {
                    this.ch = stringUtf8;
                }
                return stringUtf8;
            }

            public ByteString getNetworkTypeBytes() {
                Object obj = this.ch;
                if (!(obj instanceof String)) {
                    return (ByteString) obj;
                }
                ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                this.ch = copyFromUtf8;
                return copyFromUtf8;
            }

            public String getPackageName() {
                Object obj = this.bh;
                if (obj instanceof String) {
                    return (String) obj;
                }
                ByteString byteString = (ByteString) obj;
                String stringUtf8 = byteString.toStringUtf8();
                if (byteString.isValidUtf8()) {
                    this.bh = stringUtf8;
                }
                return stringUtf8;
            }

            public ByteString getPackageNameBytes() {
                Object obj = this.bh;
                if (!(obj instanceof String)) {
                    return (ByteString) obj;
                }
                ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                this.bh = copyFromUtf8;
                return copyFromUtf8;
            }

            public Parser getParserForType() {
                return PARSER;
            }

            public String getPlatform() {
                Object obj = this.aM;
                if (obj instanceof String) {
                    return (String) obj;
                }
                ByteString byteString = (ByteString) obj;
                String stringUtf8 = byteString.toStringUtf8();
                if (byteString.isValidUtf8()) {
                    this.aM = stringUtf8;
                }
                return stringUtf8;
            }

            public ByteString getPlatformBytes() {
                Object obj = this.aM;
                if (!(obj instanceof String)) {
                    return (ByteString) obj;
                }
                ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                this.aM = copyFromUtf8;
                return copyFromUtf8;
            }

            public int getSerializedSize() {
                int i = this.ay;
                if (i != -1) {
                    return i;
                }
                int computeBytesSize = (this.aA & 1) == 1 ? CodedOutputStream.computeBytesSize(1, getTypeBytes()) + 0 : 0;
                if ((this.aA & 2) == 2) {
                    computeBytesSize += CodedOutputStream.computeBytesSize(2, getAppKeyBytes());
                }
                if ((this.aA & 4) == 4) {
                    computeBytesSize += CodedOutputStream.computeBytesSize(3, getAppChannelBytes());
                }
                if ((this.aA & 8) == 8) {
                    computeBytesSize += CodedOutputStream.computeBytesSize(4, getSessionIdBytes());
                }
                if ((this.aA & 16) == 16) {
                    computeBytesSize += CodedOutputStream.computeBytesSize(5, getActivityBytes());
                }
                if ((this.aA & 32) == 32) {
                    computeBytesSize += CodedOutputStream.computeInt64Size(6, this.bb);
                }
                if ((this.aA & 64) == 64) {
                    computeBytesSize += CodedOutputStream.computeBytesSize(7, getDeviceIdBytes());
                }
                if ((this.aA & 128) == 128) {
                    computeBytesSize += CodedOutputStream.computeBytesSize(8, getExceptionBytes());
                }
                if ((this.aA & 256) == 256) {
                    computeBytesSize += CodedOutputStream.computeBytesSize(9, getExceptionDetailBytes());
                }
                if ((this.aA & 512) == 512) {
                    computeBytesSize += CodedOutputStream.computeBytesSize(10, getNetworkTypeBytes());
                }
                if ((this.aA & 1024) == 1024) {
                    computeBytesSize += CodedOutputStream.computeBytesSize(11, getNetworkBytes());
                }
                if ((this.aA & 2048) == 2048) {
                    computeBytesSize += CodedOutputStream.computeBytesSize(12, getUrlBytes());
                }
                for (int i2 = 0; i2 < this.ck.size(); i2++) {
                    computeBytesSize += CodedOutputStream.computeMessageSize(13, (MessageLite) this.ck.get(i2));
                }
                if ((this.aA & 4096) == 4096) {
                    computeBytesSize += CodedOutputStream.computeInt32Size(14, this.aI);
                }
                if ((this.aA & 8192) == 8192) {
                    computeBytesSize += CodedOutputStream.computeBytesSize(15, getVersionNameBytes());
                }
                if ((this.aA & 16384) == 16384) {
                    computeBytesSize += CodedOutputStream.computeBytesSize(16, getUserIdBytes());
                }
                if ((this.aA & 32768) == 32768) {
                    computeBytesSize += CodedOutputStream.computeBytesSize(17, getDeviceNameBytes());
                }
                if ((this.aA & 65536) == 65536) {
                    computeBytesSize += CodedOutputStream.computeBytesSize(18, getPlatformBytes());
                }
                if ((this.aA & 131072) == 131072) {
                    computeBytesSize += CodedOutputStream.computeBytesSize(19, getPackageNameBytes());
                }
                if ((this.aA & 262144) == 262144) {
                    computeBytesSize += CodedOutputStream.computeBytesSize(20, getLogVersionAppBytes());
                }
                int serializedSize = computeBytesSize + getUnknownFields().getSerializedSize();
                this.ay = serializedSize;
                return serializedSize;
            }

            public String getSessionId() {
                Object obj = this.aF;
                if (obj instanceof String) {
                    return (String) obj;
                }
                ByteString byteString = (ByteString) obj;
                String stringUtf8 = byteString.toStringUtf8();
                if (byteString.isValidUtf8()) {
                    this.aF = stringUtf8;
                }
                return stringUtf8;
            }

            public ByteString getSessionIdBytes() {
                Object obj = this.aF;
                if (!(obj instanceof String)) {
                    return (ByteString) obj;
                }
                ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                this.aF = copyFromUtf8;
                return copyFromUtf8;
            }

            public long getTime() {
                return this.bb;
            }

            public String getType() {
                Object obj = this.aC;
                if (obj instanceof String) {
                    return (String) obj;
                }
                ByteString byteString = (ByteString) obj;
                String stringUtf8 = byteString.toStringUtf8();
                if (byteString.isValidUtf8()) {
                    this.aC = stringUtf8;
                }
                return stringUtf8;
            }

            public ByteString getTypeBytes() {
                Object obj = this.aC;
                if (!(obj instanceof String)) {
                    return (ByteString) obj;
                }
                ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                this.aC = copyFromUtf8;
                return copyFromUtf8;
            }

            public final UnknownFieldSet getUnknownFields() {
                return this.aw;
            }

            public String getUrl() {
                Object obj = this.cj;
                if (obj instanceof String) {
                    return (String) obj;
                }
                ByteString byteString = (ByteString) obj;
                String stringUtf8 = byteString.toStringUtf8();
                if (byteString.isValidUtf8()) {
                    this.cj = stringUtf8;
                }
                return stringUtf8;
            }

            public ByteString getUrlBytes() {
                Object obj = this.cj;
                if (!(obj instanceof String)) {
                    return (ByteString) obj;
                }
                ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                this.cj = copyFromUtf8;
                return copyFromUtf8;
            }

            public String getUserId() {
                Object obj = this.aH;
                if (obj instanceof String) {
                    return (String) obj;
                }
                ByteString byteString = (ByteString) obj;
                String stringUtf8 = byteString.toStringUtf8();
                if (byteString.isValidUtf8()) {
                    this.aH = stringUtf8;
                }
                return stringUtf8;
            }

            public ByteString getUserIdBytes() {
                Object obj = this.aH;
                if (!(obj instanceof String)) {
                    return (ByteString) obj;
                }
                ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                this.aH = copyFromUtf8;
                return copyFromUtf8;
            }

            public int getVersionCode() {
                return this.aI;
            }

            public String getVersionName() {
                Object obj = this.aJ;
                if (obj instanceof String) {
                    return (String) obj;
                }
                ByteString byteString = (ByteString) obj;
                String stringUtf8 = byteString.toStringUtf8();
                if (byteString.isValidUtf8()) {
                    this.aJ = stringUtf8;
                }
                return stringUtf8;
            }

            public ByteString getVersionNameBytes() {
                Object obj = this.aJ;
                if (!(obj instanceof String)) {
                    return (ByteString) obj;
                }
                ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                this.aJ = copyFromUtf8;
                return copyFromUtf8;
            }

            public boolean hasActivity() {
                return (this.aA & 16) == 16;
            }

            public boolean hasAppChannel() {
                return (this.aA & 4) == 4;
            }

            public boolean hasAppKey() {
                return (this.aA & 2) == 2;
            }

            public boolean hasDeviceId() {
                return (this.aA & 64) == 64;
            }

            public boolean hasDeviceName() {
                return (this.aA & 32768) == 32768;
            }

            public boolean hasException() {
                return (this.aA & 128) == 128;
            }

            public boolean hasExceptionDetail() {
                return (this.aA & 256) == 256;
            }

            public boolean hasLogVersionApp() {
                return (this.aA & 262144) == 262144;
            }

            public boolean hasNetwork() {
                return (this.aA & 1024) == 1024;
            }

            public boolean hasNetworkType() {
                return (this.aA & 512) == 512;
            }

            public boolean hasPackageName() {
                return (this.aA & 131072) == 131072;
            }

            public boolean hasPlatform() {
                return (this.aA & 65536) == 65536;
            }

            public boolean hasSessionId() {
                return (this.aA & 8) == 8;
            }

            public boolean hasTime() {
                return (this.aA & 32) == 32;
            }

            public boolean hasType() {
                return (this.aA & 1) == 1;
            }

            public boolean hasUrl() {
                return (this.aA & 2048) == 2048;
            }

            public boolean hasUserId() {
                return (this.aA & 16384) == 16384;
            }

            public boolean hasVersionCode() {
                return (this.aA & 4096) == 4096;
            }

            public boolean hasVersionName() {
                return (this.aA & 8192) == 8192;
            }

            /* access modifiers changed from: protected */
            public GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
                return ProtoMsg.ah.ensureFieldAccessorsInitialized(ProtoError.class, Builder.class);
            }

            public final boolean isInitialized() {
                byte b = this.ax;
                if (b == 1) {
                    return true;
                }
                if (b == 0) {
                    return false;
                }
                if (!hasType()) {
                    this.ax = 0;
                    return false;
                }
                for (int i = 0; i < getErrorMapCount(); i++) {
                    if (!getErrorMap(i).isInitialized()) {
                        this.ax = 0;
                        return false;
                    }
                }
                this.ax = 1;
                return true;
            }

            public Builder newBuilderForType() {
                return newBuilder();
            }

            /* access modifiers changed from: protected */
            public Builder newBuilderForType(GeneratedMessage.BuilderParent builderParent) {
                return new Builder(builderParent, (a) null);
            }

            public Builder toBuilder() {
                return newBuilder(this);
            }

            /* access modifiers changed from: protected */
            public Object writeReplace() {
                return super.writeReplace();
            }

            public void writeTo(CodedOutputStream codedOutputStream) {
                getSerializedSize();
                if ((this.aA & 1) == 1) {
                    codedOutputStream.writeBytes(1, getTypeBytes());
                }
                if ((this.aA & 2) == 2) {
                    codedOutputStream.writeBytes(2, getAppKeyBytes());
                }
                if ((this.aA & 4) == 4) {
                    codedOutputStream.writeBytes(3, getAppChannelBytes());
                }
                if ((this.aA & 8) == 8) {
                    codedOutputStream.writeBytes(4, getSessionIdBytes());
                }
                if ((this.aA & 16) == 16) {
                    codedOutputStream.writeBytes(5, getActivityBytes());
                }
                if ((this.aA & 32) == 32) {
                    codedOutputStream.writeInt64(6, this.bb);
                }
                if ((this.aA & 64) == 64) {
                    codedOutputStream.writeBytes(7, getDeviceIdBytes());
                }
                if ((this.aA & 128) == 128) {
                    codedOutputStream.writeBytes(8, getExceptionBytes());
                }
                if ((this.aA & 256) == 256) {
                    codedOutputStream.writeBytes(9, getExceptionDetailBytes());
                }
                if ((this.aA & 512) == 512) {
                    codedOutputStream.writeBytes(10, getNetworkTypeBytes());
                }
                if ((this.aA & 1024) == 1024) {
                    codedOutputStream.writeBytes(11, getNetworkBytes());
                }
                if ((this.aA & 2048) == 2048) {
                    codedOutputStream.writeBytes(12, getUrlBytes());
                }
                for (int i = 0; i < this.ck.size(); i++) {
                    codedOutputStream.writeMessage(13, (MessageLite) this.ck.get(i));
                }
                if ((this.aA & 4096) == 4096) {
                    codedOutputStream.writeInt32(14, this.aI);
                }
                if ((this.aA & 8192) == 8192) {
                    codedOutputStream.writeBytes(15, getVersionNameBytes());
                }
                if ((this.aA & 16384) == 16384) {
                    codedOutputStream.writeBytes(16, getUserIdBytes());
                }
                if ((this.aA & 32768) == 32768) {
                    codedOutputStream.writeBytes(17, getDeviceNameBytes());
                }
                if ((this.aA & 65536) == 65536) {
                    codedOutputStream.writeBytes(18, getPlatformBytes());
                }
                if ((this.aA & 131072) == 131072) {
                    codedOutputStream.writeBytes(19, getPackageNameBytes());
                }
                if ((this.aA & 262144) == 262144) {
                    codedOutputStream.writeBytes(20, getLogVersionAppBytes());
                }
                getUnknownFields().writeTo(codedOutputStream);
            }
        }

        public interface ProtoErrorOrBuilder extends MessageOrBuilder {
            String getActivity();

            ByteString getActivityBytes();

            String getAppChannel();

            ByteString getAppChannelBytes();

            String getAppKey();

            ByteString getAppKeyBytes();

            String getDeviceId();

            ByteString getDeviceIdBytes();

            String getDeviceName();

            ByteString getDeviceNameBytes();

            ProtoMap getErrorMap(int i);

            int getErrorMapCount();

            List getErrorMapList();

            ProtoMapOrBuilder getErrorMapOrBuilder(int i);

            List getErrorMapOrBuilderList();

            String getException();

            ByteString getExceptionBytes();

            String getExceptionDetail();

            ByteString getExceptionDetailBytes();

            String getLogVersionApp();

            ByteString getLogVersionAppBytes();

            String getNetwork();

            ByteString getNetworkBytes();

            String getNetworkType();

            ByteString getNetworkTypeBytes();

            String getPackageName();

            ByteString getPackageNameBytes();

            String getPlatform();

            ByteString getPlatformBytes();

            String getSessionId();

            ByteString getSessionIdBytes();

            long getTime();

            String getType();

            ByteString getTypeBytes();

            String getUrl();

            ByteString getUrlBytes();

            String getUserId();

            ByteString getUserIdBytes();

            int getVersionCode();

            String getVersionName();

            ByteString getVersionNameBytes();

            boolean hasActivity();

            boolean hasAppChannel();

            boolean hasAppKey();

            boolean hasDeviceId();

            boolean hasDeviceName();

            boolean hasException();

            boolean hasExceptionDetail();

            boolean hasLogVersionApp();

            boolean hasNetwork();

            boolean hasNetworkType();

            boolean hasPackageName();

            boolean hasPlatform();

            boolean hasSessionId();

            boolean hasTime();

            boolean hasType();

            boolean hasUrl();

            boolean hasUserId();

            boolean hasVersionCode();

            boolean hasVersionName();
        }

        public final class ProtoMap extends GeneratedMessage implements ProtoMapOrBuilder {
            public static final int KEY_FIELD_NUMBER = 1;
            public static Parser PARSER = new o();
            public static final int VALUE_FIELD_NUMBER = 2;
            private static final ProtoMap cm = new ProtoMap(true);
            /* access modifiers changed from: private */
            public int aA;
            private final UnknownFieldSet aw;
            private byte ax;
            private int ay;
            /* access modifiers changed from: private */
            public Object bg;
            /* access modifiers changed from: private */

            /* renamed from: cn  reason: collision with root package name */
            public Object f12076cn;

            public final class Builder extends GeneratedMessage.Builder implements ProtoMapOrBuilder {
                private int aA;
                private Object bg;

                /* renamed from: cn  reason: collision with root package name */
                private Object f12077cn;

                private Builder() {
                    this.bg = "";
                    this.f12077cn = "";
                    R();
                }

                private Builder(GeneratedMessage.BuilderParent builderParent) {
                    super(builderParent);
                    this.bg = "";
                    this.f12077cn = "";
                    R();
                }

                /* synthetic */ Builder(GeneratedMessage.BuilderParent builderParent, a aVar) {
                    this(builderParent);
                }

                private void R() {
                    boolean unused = ProtoMap.alwaysUseFieldBuilders;
                }

                /* access modifiers changed from: private */
                public static Builder bg() {
                    return new Builder();
                }

                public static final Descriptors.Descriptor getDescriptor() {
                    return ProtoMsg.Q;
                }

                public ProtoMap build() {
                    ProtoMap buildPartial = buildPartial();
                    if (buildPartial.isInitialized()) {
                        return buildPartial;
                    }
                    throw newUninitializedMessageException(buildPartial);
                }

                public ProtoMap buildPartial() {
                    ProtoMap protoMap = new ProtoMap((GeneratedMessage.Builder) this, (a) null);
                    int i = this.aA;
                    int i2 = 1;
                    if ((i & 1) != 1) {
                        i2 = 0;
                    }
                    Object unused = protoMap.bg = this.bg;
                    if ((i & 2) == 2) {
                        i2 |= 2;
                    }
                    Object unused2 = protoMap.f12076cn = this.f12077cn;
                    int unused3 = protoMap.aA = i2;
                    onBuilt();
                    return protoMap;
                }

                public Builder clear() {
                    super.clear();
                    this.bg = "";
                    this.aA &= -2;
                    this.f12077cn = "";
                    this.aA &= -3;
                    return this;
                }

                public Builder clearKey() {
                    this.aA &= -2;
                    this.bg = ProtoMap.getDefaultInstance().getKey();
                    onChanged();
                    return this;
                }

                public Builder clearValue() {
                    this.aA &= -3;
                    this.f12077cn = ProtoMap.getDefaultInstance().getValue();
                    onChanged();
                    return this;
                }

                public Builder clone() {
                    return bg().mergeFrom(buildPartial());
                }

                public ProtoMap getDefaultInstanceForType() {
                    return ProtoMap.getDefaultInstance();
                }

                public Descriptors.Descriptor getDescriptorForType() {
                    return ProtoMsg.Q;
                }

                public String getKey() {
                    Object obj = this.bg;
                    if (obj instanceof String) {
                        return (String) obj;
                    }
                    ByteString byteString = (ByteString) obj;
                    String stringUtf8 = byteString.toStringUtf8();
                    if (byteString.isValidUtf8()) {
                        this.bg = stringUtf8;
                    }
                    return stringUtf8;
                }

                public ByteString getKeyBytes() {
                    Object obj = this.bg;
                    if (!(obj instanceof String)) {
                        return (ByteString) obj;
                    }
                    ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                    this.bg = copyFromUtf8;
                    return copyFromUtf8;
                }

                public String getValue() {
                    Object obj = this.f12077cn;
                    if (obj instanceof String) {
                        return (String) obj;
                    }
                    ByteString byteString = (ByteString) obj;
                    String stringUtf8 = byteString.toStringUtf8();
                    if (byteString.isValidUtf8()) {
                        this.f12077cn = stringUtf8;
                    }
                    return stringUtf8;
                }

                public ByteString getValueBytes() {
                    Object obj = this.f12077cn;
                    if (!(obj instanceof String)) {
                        return (ByteString) obj;
                    }
                    ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                    this.f12077cn = copyFromUtf8;
                    return copyFromUtf8;
                }

                public boolean hasKey() {
                    return (this.aA & 1) == 1;
                }

                public boolean hasValue() {
                    return (this.aA & 2) == 2;
                }

                /* access modifiers changed from: protected */
                public GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
                    return ProtoMsg.R.ensureFieldAccessorsInitialized(ProtoMap.class, Builder.class);
                }

                public final boolean isInitialized() {
                    return hasKey() && hasValue();
                }

                public Builder mergeFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) {
                    ProtoMap protoMap;
                    ProtoMap protoMap2 = null;
                    try {
                        ProtoMap protoMap3 = (ProtoMap) ProtoMap.PARSER.parsePartialFrom(codedInputStream, extensionRegistryLite);
                        if (protoMap3 != null) {
                            mergeFrom(protoMap3);
                        }
                        return this;
                    } catch (InvalidProtocolBufferException e) {
                        protoMap = (ProtoMap) e.getUnfinishedMessage();
                        throw e;
                    } catch (Throwable th) {
                        th = th;
                        protoMap2 = protoMap;
                    }
                    if (protoMap2 != null) {
                        mergeFrom(protoMap2);
                    }
                    throw th;
                }

                public Builder mergeFrom(Message message) {
                    if (message instanceof ProtoMap) {
                        return mergeFrom((ProtoMap) message);
                    }
                    super.mergeFrom(message);
                    return this;
                }

                public Builder mergeFrom(ProtoMap protoMap) {
                    if (protoMap == ProtoMap.getDefaultInstance()) {
                        return this;
                    }
                    if (protoMap.hasKey()) {
                        this.aA |= 1;
                        this.bg = protoMap.bg;
                        onChanged();
                    }
                    if (protoMap.hasValue()) {
                        this.aA |= 2;
                        this.f12077cn = protoMap.f12076cn;
                        onChanged();
                    }
                    mergeUnknownFields(protoMap.getUnknownFields());
                    return this;
                }

                public Builder setKey(String str) {
                    if (str != null) {
                        this.aA |= 1;
                        this.bg = str;
                        onChanged();
                        return this;
                    }
                    throw new NullPointerException();
                }

                public Builder setKeyBytes(ByteString byteString) {
                    if (byteString != null) {
                        this.aA |= 1;
                        this.bg = byteString;
                        onChanged();
                        return this;
                    }
                    throw new NullPointerException();
                }

                public Builder setValue(String str) {
                    if (str != null) {
                        this.aA |= 2;
                        this.f12077cn = str;
                        onChanged();
                        return this;
                    }
                    throw new NullPointerException();
                }

                public Builder setValueBytes(ByteString byteString) {
                    if (byteString != null) {
                        this.aA |= 2;
                        this.f12077cn = byteString;
                        onChanged();
                        return this;
                    }
                    throw new NullPointerException();
                }
            }

            static {
                cm.P();
            }

            private ProtoMap(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) {
                this.ax = -1;
                this.ay = -1;
                P();
                UnknownFieldSet.Builder newBuilder = UnknownFieldSet.newBuilder();
                boolean z = false;
                while (!z) {
                    try {
                        int readTag = codedInputStream.readTag();
                        if (readTag != 0) {
                            if (readTag == 10) {
                                ByteString readBytes = codedInputStream.readBytes();
                                this.aA = 1 | this.aA;
                                this.bg = readBytes;
                            } else if (readTag == 18) {
                                ByteString readBytes2 = codedInputStream.readBytes();
                                this.aA |= 2;
                                this.f12076cn = readBytes2;
                            } else if (!parseUnknownField(codedInputStream, newBuilder, extensionRegistryLite, readTag)) {
                            }
                        }
                        z = true;
                    } catch (InvalidProtocolBufferException e) {
                        throw e.setUnfinishedMessage(this);
                    } catch (IOException e2) {
                        throw new InvalidProtocolBufferException(e2.getMessage()).setUnfinishedMessage(this);
                    } catch (Throwable th) {
                        this.aw = newBuilder.build();
                        makeExtensionsImmutable();
                        throw th;
                    }
                }
                this.aw = newBuilder.build();
                makeExtensionsImmutable();
            }

            /* synthetic */ ProtoMap(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite, a aVar) {
                this(codedInputStream, extensionRegistryLite);
            }

            private ProtoMap(GeneratedMessage.Builder builder) {
                super(builder);
                this.ax = -1;
                this.ay = -1;
                this.aw = builder.getUnknownFields();
            }

            /* synthetic */ ProtoMap(GeneratedMessage.Builder builder, a aVar) {
                this(builder);
            }

            private ProtoMap(boolean z) {
                this.ax = -1;
                this.ay = -1;
                this.aw = UnknownFieldSet.getDefaultInstance();
            }

            private void P() {
                this.bg = "";
                this.f12076cn = "";
            }

            public static ProtoMap getDefaultInstance() {
                return cm;
            }

            public static final Descriptors.Descriptor getDescriptor() {
                return ProtoMsg.Q;
            }

            public static Builder newBuilder() {
                return Builder.bg();
            }

            public static Builder newBuilder(ProtoMap protoMap) {
                return newBuilder().mergeFrom(protoMap);
            }

            public static ProtoMap parseDelimitedFrom(InputStream inputStream) {
                return (ProtoMap) PARSER.parseDelimitedFrom(inputStream);
            }

            public static ProtoMap parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) {
                return (ProtoMap) PARSER.parseDelimitedFrom(inputStream, extensionRegistryLite);
            }

            public static ProtoMap parseFrom(ByteString byteString) {
                return (ProtoMap) PARSER.parseFrom(byteString);
            }

            public static ProtoMap parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) {
                return (ProtoMap) PARSER.parseFrom(byteString, extensionRegistryLite);
            }

            public static ProtoMap parseFrom(CodedInputStream codedInputStream) {
                return (ProtoMap) PARSER.parseFrom(codedInputStream);
            }

            public static ProtoMap parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) {
                return (ProtoMap) PARSER.parseFrom(codedInputStream, extensionRegistryLite);
            }

            public static ProtoMap parseFrom(InputStream inputStream) {
                return (ProtoMap) PARSER.parseFrom(inputStream);
            }

            public static ProtoMap parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) {
                return (ProtoMap) PARSER.parseFrom(inputStream, extensionRegistryLite);
            }

            public static ProtoMap parseFrom(byte[] bArr) {
                return (ProtoMap) PARSER.parseFrom(bArr);
            }

            public static ProtoMap parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) {
                return (ProtoMap) PARSER.parseFrom(bArr, extensionRegistryLite);
            }

            public ProtoMap getDefaultInstanceForType() {
                return cm;
            }

            public String getKey() {
                Object obj = this.bg;
                if (obj instanceof String) {
                    return (String) obj;
                }
                ByteString byteString = (ByteString) obj;
                String stringUtf8 = byteString.toStringUtf8();
                if (byteString.isValidUtf8()) {
                    this.bg = stringUtf8;
                }
                return stringUtf8;
            }

            public ByteString getKeyBytes() {
                Object obj = this.bg;
                if (!(obj instanceof String)) {
                    return (ByteString) obj;
                }
                ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                this.bg = copyFromUtf8;
                return copyFromUtf8;
            }

            public Parser getParserForType() {
                return PARSER;
            }

            public int getSerializedSize() {
                int i = this.ay;
                if (i != -1) {
                    return i;
                }
                int i2 = 0;
                if ((this.aA & 1) == 1) {
                    i2 = 0 + CodedOutputStream.computeBytesSize(1, getKeyBytes());
                }
                if ((this.aA & 2) == 2) {
                    i2 += CodedOutputStream.computeBytesSize(2, getValueBytes());
                }
                int serializedSize = i2 + getUnknownFields().getSerializedSize();
                this.ay = serializedSize;
                return serializedSize;
            }

            public final UnknownFieldSet getUnknownFields() {
                return this.aw;
            }

            public String getValue() {
                Object obj = this.f12076cn;
                if (obj instanceof String) {
                    return (String) obj;
                }
                ByteString byteString = (ByteString) obj;
                String stringUtf8 = byteString.toStringUtf8();
                if (byteString.isValidUtf8()) {
                    this.f12076cn = stringUtf8;
                }
                return stringUtf8;
            }

            public ByteString getValueBytes() {
                Object obj = this.f12076cn;
                if (!(obj instanceof String)) {
                    return (ByteString) obj;
                }
                ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                this.f12076cn = copyFromUtf8;
                return copyFromUtf8;
            }

            public boolean hasKey() {
                return (this.aA & 1) == 1;
            }

            public boolean hasValue() {
                return (this.aA & 2) == 2;
            }

            /* access modifiers changed from: protected */
            public GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
                return ProtoMsg.R.ensureFieldAccessorsInitialized(ProtoMap.class, Builder.class);
            }

            public final boolean isInitialized() {
                byte b = this.ax;
                if (b == 1) {
                    return true;
                }
                if (b == 0) {
                    return false;
                }
                if (!hasKey()) {
                    this.ax = 0;
                    return false;
                } else if (!hasValue()) {
                    this.ax = 0;
                    return false;
                } else {
                    this.ax = 1;
                    return true;
                }
            }

            public Builder newBuilderForType() {
                return newBuilder();
            }

            /* access modifiers changed from: protected */
            public Builder newBuilderForType(GeneratedMessage.BuilderParent builderParent) {
                return new Builder(builderParent, (a) null);
            }

            public Builder toBuilder() {
                return newBuilder(this);
            }

            /* access modifiers changed from: protected */
            public Object writeReplace() {
                return super.writeReplace();
            }

            public void writeTo(CodedOutputStream codedOutputStream) {
                getSerializedSize();
                if ((this.aA & 1) == 1) {
                    codedOutputStream.writeBytes(1, getKeyBytes());
                }
                if ((this.aA & 2) == 2) {
                    codedOutputStream.writeBytes(2, getValueBytes());
                }
                getUnknownFields().writeTo(codedOutputStream);
            }
        }

        public interface ProtoMapOrBuilder extends MessageOrBuilder {
            String getKey();

            ByteString getKeyBytes();

            String getValue();

            ByteString getValueBytes();

            boolean hasKey();

            boolean hasValue();
        }

        public enum ReportStrategy implements ProtocolMessageEnum {
            REAL_TIME(0, 0),
            APP_START(1, 1),
            ONCE_PER_DAY(2, 2),
            SET_TIME_INTERVAL(3, 3);
            
            public static final int APP_START_VALUE = 1;
            public static final int ONCE_PER_DAY_VALUE = 2;
            public static final int REAL_TIME_VALUE = 0;
            public static final int SET_TIME_INTERVAL_VALUE = 3;
            private static Internal.EnumLiteMap bQ;
            private static final ReportStrategy[] co = null;
            private final int index;
            private final int value;

            static {
                bQ = new p();
                co = values();
            }

            private ReportStrategy(int i, int i2) {
                this.index = i;
                this.value = i2;
            }

            public static final Descriptors.EnumDescriptor getDescriptor() {
                return StatsMsg.getDescriptor().getEnumTypes().get(0);
            }

            public static Internal.EnumLiteMap internalGetValueMap() {
                return bQ;
            }

            public static ReportStrategy valueOf(int i) {
                switch (i) {
                    case 0:
                        return REAL_TIME;
                    case 1:
                        return APP_START;
                    case 2:
                        return ONCE_PER_DAY;
                    case 3:
                        return SET_TIME_INTERVAL;
                    default:
                        return null;
                }
            }

            public static ReportStrategy valueOf(Descriptors.EnumValueDescriptor enumValueDescriptor) {
                if (enumValueDescriptor.getType() == getDescriptor()) {
                    return co[enumValueDescriptor.getIndex()];
                }
                throw new IllegalArgumentException("EnumValueDescriptor is not for this type.");
            }

            public final Descriptors.EnumDescriptor getDescriptorForType() {
                return getDescriptor();
            }

            public final int getNumber() {
                return this.value;
            }

            public final Descriptors.EnumValueDescriptor getValueDescriptor() {
                return getDescriptor().getValues().get(this.index);
            }
        }

        public final class Response extends GeneratedMessage implements ResponseOrBuilder {
            public static final int CODE_FIELD_NUMBER = 1;
            public static final int MAP_FIELD_NUMBER = 3;
            public static final int MSG_FIELD_NUMBER = 2;
            public static Parser PARSER = new q();
            private static final Response cq = new Response(true);
            /* access modifiers changed from: private */
            public int aA;
            private final UnknownFieldSet aw;
            private byte ax;
            private int ay;
            /* access modifiers changed from: private */
            public int cr;
            /* access modifiers changed from: private */
            public Object cs;
            /* access modifiers changed from: private */
            public ProtoMap ct;

            public final class Builder extends GeneratedMessage.Builder implements ResponseOrBuilder {
                private int aA;
                private int cr;
                private Object cs;
                private ProtoMap ct;
                private SingleFieldBuilder cu;

                private Builder() {
                    this.cs = "";
                    this.ct = ProtoMap.getDefaultInstance();
                    R();
                }

                private Builder(GeneratedMessage.BuilderParent builderParent) {
                    super(builderParent);
                    this.cs = "";
                    this.ct = ProtoMap.getDefaultInstance();
                    R();
                }

                /* synthetic */ Builder(GeneratedMessage.BuilderParent builderParent, a aVar) {
                    this(builderParent);
                }

                private void R() {
                    if (Response.alwaysUseFieldBuilders) {
                        bk();
                    }
                }

                /* access modifiers changed from: private */
                public static Builder bj() {
                    return new Builder();
                }

                private SingleFieldBuilder bk() {
                    if (this.cu == null) {
                        this.cu = new SingleFieldBuilder(getMap(), getParentForChildren(), isClean());
                        this.ct = null;
                    }
                    return this.cu;
                }

                public static final Descriptors.Descriptor getDescriptor() {
                    return ProtoMsg.as;
                }

                public Response build() {
                    Response buildPartial = buildPartial();
                    if (buildPartial.isInitialized()) {
                        return buildPartial;
                    }
                    throw newUninitializedMessageException(buildPartial);
                }

                public Response buildPartial() {
                    Response response = new Response((GeneratedMessage.Builder) this, (a) null);
                    int i = this.aA;
                    int i2 = 1;
                    if ((i & 1) != 1) {
                        i2 = 0;
                    }
                    int unused = response.cr = this.cr;
                    if ((i & 2) == 2) {
                        i2 |= 2;
                    }
                    Object unused2 = response.cs = this.cs;
                    if ((i & 4) == 4) {
                        i2 |= 4;
                    }
                    ProtoMap unused3 = response.ct = this.cu == null ? this.ct : (ProtoMap) this.cu.build();
                    int unused4 = response.aA = i2;
                    onBuilt();
                    return response;
                }

                public Builder clear() {
                    super.clear();
                    this.cr = 0;
                    this.aA &= -2;
                    this.cs = "";
                    this.aA &= -3;
                    if (this.cu == null) {
                        this.ct = ProtoMap.getDefaultInstance();
                    } else {
                        this.cu.clear();
                    }
                    this.aA &= -5;
                    return this;
                }

                public Builder clearCode() {
                    this.aA &= -2;
                    this.cr = 0;
                    onChanged();
                    return this;
                }

                public Builder clearMap() {
                    if (this.cu == null) {
                        this.ct = ProtoMap.getDefaultInstance();
                        onChanged();
                    } else {
                        this.cu.clear();
                    }
                    this.aA &= -5;
                    return this;
                }

                public Builder clearMsg() {
                    this.aA &= -3;
                    this.cs = Response.getDefaultInstance().getMsg();
                    onChanged();
                    return this;
                }

                public Builder clone() {
                    return bj().mergeFrom(buildPartial());
                }

                public int getCode() {
                    return this.cr;
                }

                public Response getDefaultInstanceForType() {
                    return Response.getDefaultInstance();
                }

                public Descriptors.Descriptor getDescriptorForType() {
                    return ProtoMsg.as;
                }

                public ProtoMap getMap() {
                    return this.cu == null ? this.ct : (ProtoMap) this.cu.getMessage();
                }

                public ProtoMap.Builder getMapBuilder() {
                    this.aA |= 4;
                    onChanged();
                    return (ProtoMap.Builder) bk().getBuilder();
                }

                public ProtoMapOrBuilder getMapOrBuilder() {
                    return this.cu != null ? (ProtoMapOrBuilder) this.cu.getMessageOrBuilder() : this.ct;
                }

                public String getMsg() {
                    Object obj = this.cs;
                    if (obj instanceof String) {
                        return (String) obj;
                    }
                    ByteString byteString = (ByteString) obj;
                    String stringUtf8 = byteString.toStringUtf8();
                    if (byteString.isValidUtf8()) {
                        this.cs = stringUtf8;
                    }
                    return stringUtf8;
                }

                public ByteString getMsgBytes() {
                    Object obj = this.cs;
                    if (!(obj instanceof String)) {
                        return (ByteString) obj;
                    }
                    ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                    this.cs = copyFromUtf8;
                    return copyFromUtf8;
                }

                public boolean hasCode() {
                    return (this.aA & 1) == 1;
                }

                public boolean hasMap() {
                    return (this.aA & 4) == 4;
                }

                public boolean hasMsg() {
                    return (this.aA & 2) == 2;
                }

                /* access modifiers changed from: protected */
                public GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
                    return ProtoMsg.at.ensureFieldAccessorsInitialized(Response.class, Builder.class);
                }

                public final boolean isInitialized() {
                    if (hasCode() && hasMsg()) {
                        return !hasMap() || getMap().isInitialized();
                    }
                    return false;
                }

                public Builder mergeFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) {
                    Response response;
                    Response response2 = null;
                    try {
                        Response response3 = (Response) Response.PARSER.parsePartialFrom(codedInputStream, extensionRegistryLite);
                        if (response3 != null) {
                            mergeFrom(response3);
                        }
                        return this;
                    } catch (InvalidProtocolBufferException e) {
                        response = (Response) e.getUnfinishedMessage();
                        throw e;
                    } catch (Throwable th) {
                        th = th;
                        response2 = response;
                    }
                    if (response2 != null) {
                        mergeFrom(response2);
                    }
                    throw th;
                }

                public Builder mergeFrom(Message message) {
                    if (message instanceof Response) {
                        return mergeFrom((Response) message);
                    }
                    super.mergeFrom(message);
                    return this;
                }

                public Builder mergeFrom(Response response) {
                    if (response == Response.getDefaultInstance()) {
                        return this;
                    }
                    if (response.hasCode()) {
                        setCode(response.getCode());
                    }
                    if (response.hasMsg()) {
                        this.aA |= 2;
                        this.cs = response.cs;
                        onChanged();
                    }
                    if (response.hasMap()) {
                        mergeMap(response.getMap());
                    }
                    mergeUnknownFields(response.getUnknownFields());
                    return this;
                }

                public Builder mergeMap(ProtoMap protoMap) {
                    if (this.cu == null) {
                        if ((this.aA & 4) == 4 && this.ct != ProtoMap.getDefaultInstance()) {
                            protoMap = ProtoMap.newBuilder(this.ct).mergeFrom(protoMap).buildPartial();
                        }
                        this.ct = protoMap;
                        onChanged();
                    } else {
                        this.cu.mergeFrom(protoMap);
                    }
                    this.aA |= 4;
                    return this;
                }

                public Builder setCode(int i) {
                    this.aA |= 1;
                    this.cr = i;
                    onChanged();
                    return this;
                }

                public Builder setMap(ProtoMap.Builder builder) {
                    if (this.cu == null) {
                        this.ct = builder.build();
                        onChanged();
                    } else {
                        this.cu.setMessage(builder.build());
                    }
                    this.aA |= 4;
                    return this;
                }

                public Builder setMap(ProtoMap protoMap) {
                    if (this.cu != null) {
                        this.cu.setMessage(protoMap);
                    } else if (protoMap != null) {
                        this.ct = protoMap;
                        onChanged();
                    } else {
                        throw new NullPointerException();
                    }
                    this.aA |= 4;
                    return this;
                }

                public Builder setMsg(String str) {
                    if (str != null) {
                        this.aA |= 2;
                        this.cs = str;
                        onChanged();
                        return this;
                    }
                    throw new NullPointerException();
                }

                public Builder setMsgBytes(ByteString byteString) {
                    if (byteString != null) {
                        this.aA |= 2;
                        this.cs = byteString;
                        onChanged();
                        return this;
                    }
                    throw new NullPointerException();
                }
            }

            static {
                cq.P();
            }

            private Response(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) {
                this.ax = -1;
                this.ay = -1;
                P();
                UnknownFieldSet.Builder newBuilder = UnknownFieldSet.newBuilder();
                boolean z = false;
                while (!z) {
                    try {
                        int readTag = codedInputStream.readTag();
                        if (readTag != 0) {
                            if (readTag == 8) {
                                this.aA |= 1;
                                this.cr = codedInputStream.readInt32();
                            } else if (readTag == 18) {
                                ByteString readBytes = codedInputStream.readBytes();
                                this.aA |= 2;
                                this.cs = readBytes;
                            } else if (readTag == 26) {
                                ProtoMap.Builder builder = (this.aA & 4) == 4 ? this.ct.toBuilder() : null;
                                this.ct = (ProtoMap) codedInputStream.readMessage(ProtoMap.PARSER, extensionRegistryLite);
                                if (builder != null) {
                                    builder.mergeFrom(this.ct);
                                    this.ct = builder.buildPartial();
                                }
                                this.aA |= 4;
                            } else if (!parseUnknownField(codedInputStream, newBuilder, extensionRegistryLite, readTag)) {
                            }
                        }
                        z = true;
                    } catch (InvalidProtocolBufferException e) {
                        throw e.setUnfinishedMessage(this);
                    } catch (IOException e2) {
                        throw new InvalidProtocolBufferException(e2.getMessage()).setUnfinishedMessage(this);
                    } catch (Throwable th) {
                        this.aw = newBuilder.build();
                        makeExtensionsImmutable();
                        throw th;
                    }
                }
                this.aw = newBuilder.build();
                makeExtensionsImmutable();
            }

            /* synthetic */ Response(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite, a aVar) {
                this(codedInputStream, extensionRegistryLite);
            }

            private Response(GeneratedMessage.Builder builder) {
                super(builder);
                this.ax = -1;
                this.ay = -1;
                this.aw = builder.getUnknownFields();
            }

            /* synthetic */ Response(GeneratedMessage.Builder builder, a aVar) {
                this(builder);
            }

            private Response(boolean z) {
                this.ax = -1;
                this.ay = -1;
                this.aw = UnknownFieldSet.getDefaultInstance();
            }

            private void P() {
                this.cr = 0;
                this.cs = "";
                this.ct = ProtoMap.getDefaultInstance();
            }

            public static Response getDefaultInstance() {
                return cq;
            }

            public static final Descriptors.Descriptor getDescriptor() {
                return ProtoMsg.as;
            }

            public static Builder newBuilder() {
                return Builder.bj();
            }

            public static Builder newBuilder(Response response) {
                return newBuilder().mergeFrom(response);
            }

            public static Response parseDelimitedFrom(InputStream inputStream) {
                return (Response) PARSER.parseDelimitedFrom(inputStream);
            }

            public static Response parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) {
                return (Response) PARSER.parseDelimitedFrom(inputStream, extensionRegistryLite);
            }

            public static Response parseFrom(ByteString byteString) {
                return (Response) PARSER.parseFrom(byteString);
            }

            public static Response parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) {
                return (Response) PARSER.parseFrom(byteString, extensionRegistryLite);
            }

            public static Response parseFrom(CodedInputStream codedInputStream) {
                return (Response) PARSER.parseFrom(codedInputStream);
            }

            public static Response parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) {
                return (Response) PARSER.parseFrom(codedInputStream, extensionRegistryLite);
            }

            public static Response parseFrom(InputStream inputStream) {
                return (Response) PARSER.parseFrom(inputStream);
            }

            public static Response parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) {
                return (Response) PARSER.parseFrom(inputStream, extensionRegistryLite);
            }

            public static Response parseFrom(byte[] bArr) {
                return (Response) PARSER.parseFrom(bArr);
            }

            public static Response parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) {
                return (Response) PARSER.parseFrom(bArr, extensionRegistryLite);
            }

            public int getCode() {
                return this.cr;
            }

            public Response getDefaultInstanceForType() {
                return cq;
            }

            public ProtoMap getMap() {
                return this.ct;
            }

            public ProtoMapOrBuilder getMapOrBuilder() {
                return this.ct;
            }

            public String getMsg() {
                Object obj = this.cs;
                if (obj instanceof String) {
                    return (String) obj;
                }
                ByteString byteString = (ByteString) obj;
                String stringUtf8 = byteString.toStringUtf8();
                if (byteString.isValidUtf8()) {
                    this.cs = stringUtf8;
                }
                return stringUtf8;
            }

            public ByteString getMsgBytes() {
                Object obj = this.cs;
                if (!(obj instanceof String)) {
                    return (ByteString) obj;
                }
                ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                this.cs = copyFromUtf8;
                return copyFromUtf8;
            }

            public Parser getParserForType() {
                return PARSER;
            }

            public int getSerializedSize() {
                int i = this.ay;
                if (i != -1) {
                    return i;
                }
                int i2 = 0;
                if ((this.aA & 1) == 1) {
                    i2 = 0 + CodedOutputStream.computeInt32Size(1, this.cr);
                }
                if ((this.aA & 2) == 2) {
                    i2 += CodedOutputStream.computeBytesSize(2, getMsgBytes());
                }
                if ((this.aA & 4) == 4) {
                    i2 += CodedOutputStream.computeMessageSize(3, this.ct);
                }
                int serializedSize = i2 + getUnknownFields().getSerializedSize();
                this.ay = serializedSize;
                return serializedSize;
            }

            public final UnknownFieldSet getUnknownFields() {
                return this.aw;
            }

            public boolean hasCode() {
                return (this.aA & 1) == 1;
            }

            public boolean hasMap() {
                return (this.aA & 4) == 4;
            }

            public boolean hasMsg() {
                return (this.aA & 2) == 2;
            }

            /* access modifiers changed from: protected */
            public GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
                return ProtoMsg.at.ensureFieldAccessorsInitialized(Response.class, Builder.class);
            }

            public final boolean isInitialized() {
                byte b = this.ax;
                if (b == 1) {
                    return true;
                }
                if (b == 0) {
                    return false;
                }
                if (!hasCode()) {
                    this.ax = 0;
                    return false;
                } else if (!hasMsg()) {
                    this.ax = 0;
                    return false;
                } else if (!hasMap() || getMap().isInitialized()) {
                    this.ax = 1;
                    return true;
                } else {
                    this.ax = 0;
                    return false;
                }
            }

            public Builder newBuilderForType() {
                return newBuilder();
            }

            /* access modifiers changed from: protected */
            public Builder newBuilderForType(GeneratedMessage.BuilderParent builderParent) {
                return new Builder(builderParent, (a) null);
            }

            public Builder toBuilder() {
                return newBuilder(this);
            }

            /* access modifiers changed from: protected */
            public Object writeReplace() {
                return super.writeReplace();
            }

            public void writeTo(CodedOutputStream codedOutputStream) {
                getSerializedSize();
                if ((this.aA & 1) == 1) {
                    codedOutputStream.writeInt32(1, this.cr);
                }
                if ((this.aA & 2) == 2) {
                    codedOutputStream.writeBytes(2, getMsgBytes());
                }
                if ((this.aA & 4) == 4) {
                    codedOutputStream.writeMessage(3, this.ct);
                }
                getUnknownFields().writeTo(codedOutputStream);
            }
        }

        public interface ResponseOrBuilder extends MessageOrBuilder {
            int getCode();

            ProtoMap getMap();

            ProtoMapOrBuilder getMapOrBuilder();

            String getMsg();

            ByteString getMsgBytes();

            boolean hasCode();

            boolean hasMap();

            boolean hasMsg();
        }

        public final class View extends GeneratedMessage implements ViewOrBuilder {
            public static final int APP_CHANNEL_FIELD_NUMBER = 11;
            public static final int APP_KEY_FIELD_NUMBER = 10;
            public static final int DEVICE_ID_FIELD_NUMBER = 4;
            public static final int LABEL_FIELD_NUMBER = 7;
            public static final int LOG_VERSION_APP_FIELD_NUMBER = 13;
            public static final int PACKAGE_NAME_FIELD_NUMBER = 12;
            public static Parser PARSER = new r();
            public static final int PLATFORM_FIELD_NUMBER = 9;
            public static final int SESSION_ID_FIELD_NUMBER = 2;
            public static final int TIME_FIELD_NUMBER = 5;
            public static final int TYPE_FIELD_NUMBER = 1;
            public static final int USER_ID_FIELD_NUMBER = 3;
            public static final int VIEW_ID_FIELD_NUMBER = 6;
            public static final int VIEW_MAP_FIELD_NUMBER = 8;
            private static final View cv = new View(true);
            /* access modifiers changed from: private */
            public int aA;
            /* access modifiers changed from: private */
            public Object aC;
            /* access modifiers changed from: private */
            public Object aD;
            /* access modifiers changed from: private */
            public Object aE;
            /* access modifiers changed from: private */
            public Object aF;
            /* access modifiers changed from: private */
            public Object aG;
            /* access modifiers changed from: private */
            public Object aH;
            /* access modifiers changed from: private */
            public Object aM;
            private final UnknownFieldSet aw;
            private byte ax;
            private int ay;
            /* access modifiers changed from: private */
            public Object bK;
            /* access modifiers changed from: private */
            public long bb;
            /* access modifiers changed from: private */
            public Object bh;
            /* access modifiers changed from: private */
            public Object bj;
            /* access modifiers changed from: private */
            public Object cw;
            /* access modifiers changed from: private */
            public List cx;

            public final class Builder extends GeneratedMessage.Builder implements ViewOrBuilder {
                private int aA;
                private Object aC;
                private Object aD;
                private Object aE;
                private Object aF;
                private Object aG;
                private Object aH;
                private Object aM;
                private Object bK;
                private long bb;
                private Object bh;
                private Object bj;
                private Object cw;
                private List cx;
                private RepeatedFieldBuilder cy;

                private Builder() {
                    this.aC = "";
                    this.aF = "";
                    this.aH = "";
                    this.aG = "";
                    this.cw = "";
                    this.bK = "";
                    this.cx = Collections.emptyList();
                    this.aM = "";
                    this.aD = "";
                    this.aE = "";
                    this.bh = "";
                    this.bj = "";
                    R();
                }

                private Builder(GeneratedMessage.BuilderParent builderParent) {
                    super(builderParent);
                    this.aC = "";
                    this.aF = "";
                    this.aH = "";
                    this.aG = "";
                    this.cw = "";
                    this.bK = "";
                    this.cx = Collections.emptyList();
                    this.aM = "";
                    this.aD = "";
                    this.aE = "";
                    this.bh = "";
                    this.bj = "";
                    R();
                }

                /* synthetic */ Builder(GeneratedMessage.BuilderParent builderParent, a aVar) {
                    this(builderParent);
                }

                private void R() {
                    if (View.alwaysUseFieldBuilders) {
                        bq();
                    }
                }

                /* access modifiers changed from: private */
                public static Builder bo() {
                    return new Builder();
                }

                private void bp() {
                    if ((this.aA & 128) != 128) {
                        this.cx = new ArrayList(this.cx);
                        this.aA |= 128;
                    }
                }

                private RepeatedFieldBuilder bq() {
                    if (this.cy == null) {
                        this.cy = new RepeatedFieldBuilder(this.cx, (this.aA & 128) == 128, getParentForChildren(), isClean());
                        this.cx = null;
                    }
                    return this.cy;
                }

                public static final Descriptors.Descriptor getDescriptor() {
                    return ProtoMsg.ao;
                }

                public Builder addAllViewMap(Iterable iterable) {
                    if (this.cy == null) {
                        bp();
                        AbstractMessageLite.Builder.addAll(iterable, this.cx);
                        onChanged();
                    } else {
                        this.cy.addAllMessages(iterable);
                    }
                    return this;
                }

                public Builder addViewMap(int i, ProtoMap.Builder builder) {
                    if (this.cy == null) {
                        bp();
                        this.cx.add(i, builder.build());
                        onChanged();
                    } else {
                        this.cy.addMessage(i, builder.build());
                    }
                    return this;
                }

                public Builder addViewMap(int i, ProtoMap protoMap) {
                    if (this.cy != null) {
                        this.cy.addMessage(i, protoMap);
                    } else if (protoMap != null) {
                        bp();
                        this.cx.add(i, protoMap);
                        onChanged();
                    } else {
                        throw new NullPointerException();
                    }
                    return this;
                }

                public Builder addViewMap(ProtoMap.Builder builder) {
                    if (this.cy == null) {
                        bp();
                        this.cx.add(builder.build());
                        onChanged();
                    } else {
                        this.cy.addMessage(builder.build());
                    }
                    return this;
                }

                public Builder addViewMap(ProtoMap protoMap) {
                    if (this.cy != null) {
                        this.cy.addMessage(protoMap);
                    } else if (protoMap != null) {
                        bp();
                        this.cx.add(protoMap);
                        onChanged();
                    } else {
                        throw new NullPointerException();
                    }
                    return this;
                }

                public ProtoMap.Builder addViewMapBuilder() {
                    return (ProtoMap.Builder) bq().addBuilder(ProtoMap.getDefaultInstance());
                }

                public ProtoMap.Builder addViewMapBuilder(int i) {
                    return (ProtoMap.Builder) bq().addBuilder(i, ProtoMap.getDefaultInstance());
                }

                public View build() {
                    View buildPartial = buildPartial();
                    if (buildPartial.isInitialized()) {
                        return buildPartial;
                    }
                    throw newUninitializedMessageException(buildPartial);
                }

                public View buildPartial() {
                    List list;
                    View view = new View((GeneratedMessage.Builder) this, (a) null);
                    int i = this.aA;
                    int i2 = 1;
                    if ((i & 1) != 1) {
                        i2 = 0;
                    }
                    Object unused = view.aC = this.aC;
                    if ((i & 2) == 2) {
                        i2 |= 2;
                    }
                    Object unused2 = view.aF = this.aF;
                    if ((i & 4) == 4) {
                        i2 |= 4;
                    }
                    Object unused3 = view.aH = this.aH;
                    if ((i & 8) == 8) {
                        i2 |= 8;
                    }
                    Object unused4 = view.aG = this.aG;
                    if ((i & 16) == 16) {
                        i2 |= 16;
                    }
                    long unused5 = view.bb = this.bb;
                    if ((i & 32) == 32) {
                        i2 |= 32;
                    }
                    Object unused6 = view.cw = this.cw;
                    if ((i & 64) == 64) {
                        i2 |= 64;
                    }
                    Object unused7 = view.bK = this.bK;
                    if (this.cy == null) {
                        if ((this.aA & 128) == 128) {
                            this.cx = Collections.unmodifiableList(this.cx);
                            this.aA &= -129;
                        }
                        list = this.cx;
                    } else {
                        list = this.cy.build();
                    }
                    List unused8 = view.cx = list;
                    if ((i & 256) == 256) {
                        i2 |= 128;
                    }
                    Object unused9 = view.aM = this.aM;
                    if ((i & 512) == 512) {
                        i2 |= 256;
                    }
                    Object unused10 = view.aD = this.aD;
                    if ((i & 1024) == 1024) {
                        i2 |= 512;
                    }
                    Object unused11 = view.aE = this.aE;
                    if ((i & 2048) == 2048) {
                        i2 |= 1024;
                    }
                    Object unused12 = view.bh = this.bh;
                    if ((i & 4096) == 4096) {
                        i2 |= 2048;
                    }
                    Object unused13 = view.bj = this.bj;
                    int unused14 = view.aA = i2;
                    onBuilt();
                    return view;
                }

                public Builder clear() {
                    super.clear();
                    this.aC = "";
                    this.aA &= -2;
                    this.aF = "";
                    this.aA &= -3;
                    this.aH = "";
                    this.aA &= -5;
                    this.aG = "";
                    this.aA &= -9;
                    this.bb = 0;
                    this.aA &= -17;
                    this.cw = "";
                    this.aA &= -33;
                    this.bK = "";
                    this.aA &= -65;
                    if (this.cy == null) {
                        this.cx = Collections.emptyList();
                        this.aA &= -129;
                    } else {
                        this.cy.clear();
                    }
                    this.aM = "";
                    this.aA &= -257;
                    this.aD = "";
                    this.aA &= -513;
                    this.aE = "";
                    this.aA &= -1025;
                    this.bh = "";
                    this.aA &= -2049;
                    this.bj = "";
                    this.aA &= -4097;
                    return this;
                }

                public Builder clearAppChannel() {
                    this.aA &= -1025;
                    this.aE = View.getDefaultInstance().getAppChannel();
                    onChanged();
                    return this;
                }

                public Builder clearAppKey() {
                    this.aA &= -513;
                    this.aD = View.getDefaultInstance().getAppKey();
                    onChanged();
                    return this;
                }

                public Builder clearDeviceId() {
                    this.aA &= -9;
                    this.aG = View.getDefaultInstance().getDeviceId();
                    onChanged();
                    return this;
                }

                public Builder clearLabel() {
                    this.aA &= -65;
                    this.bK = View.getDefaultInstance().getLabel();
                    onChanged();
                    return this;
                }

                public Builder clearLogVersionApp() {
                    this.aA &= -4097;
                    this.bj = View.getDefaultInstance().getLogVersionApp();
                    onChanged();
                    return this;
                }

                public Builder clearPackageName() {
                    this.aA &= -2049;
                    this.bh = View.getDefaultInstance().getPackageName();
                    onChanged();
                    return this;
                }

                public Builder clearPlatform() {
                    this.aA &= -257;
                    this.aM = View.getDefaultInstance().getPlatform();
                    onChanged();
                    return this;
                }

                public Builder clearSessionId() {
                    this.aA &= -3;
                    this.aF = View.getDefaultInstance().getSessionId();
                    onChanged();
                    return this;
                }

                public Builder clearTime() {
                    this.aA &= -17;
                    this.bb = 0;
                    onChanged();
                    return this;
                }

                public Builder clearType() {
                    this.aA &= -2;
                    this.aC = View.getDefaultInstance().getType();
                    onChanged();
                    return this;
                }

                public Builder clearUserId() {
                    this.aA &= -5;
                    this.aH = View.getDefaultInstance().getUserId();
                    onChanged();
                    return this;
                }

                public Builder clearViewId() {
                    this.aA &= -33;
                    this.cw = View.getDefaultInstance().getViewId();
                    onChanged();
                    return this;
                }

                public Builder clearViewMap() {
                    if (this.cy == null) {
                        this.cx = Collections.emptyList();
                        this.aA &= -129;
                        onChanged();
                    } else {
                        this.cy.clear();
                    }
                    return this;
                }

                public Builder clone() {
                    return bo().mergeFrom(buildPartial());
                }

                public String getAppChannel() {
                    Object obj = this.aE;
                    if (obj instanceof String) {
                        return (String) obj;
                    }
                    ByteString byteString = (ByteString) obj;
                    String stringUtf8 = byteString.toStringUtf8();
                    if (byteString.isValidUtf8()) {
                        this.aE = stringUtf8;
                    }
                    return stringUtf8;
                }

                public ByteString getAppChannelBytes() {
                    Object obj = this.aE;
                    if (!(obj instanceof String)) {
                        return (ByteString) obj;
                    }
                    ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                    this.aE = copyFromUtf8;
                    return copyFromUtf8;
                }

                public String getAppKey() {
                    Object obj = this.aD;
                    if (obj instanceof String) {
                        return (String) obj;
                    }
                    ByteString byteString = (ByteString) obj;
                    String stringUtf8 = byteString.toStringUtf8();
                    if (byteString.isValidUtf8()) {
                        this.aD = stringUtf8;
                    }
                    return stringUtf8;
                }

                public ByteString getAppKeyBytes() {
                    Object obj = this.aD;
                    if (!(obj instanceof String)) {
                        return (ByteString) obj;
                    }
                    ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                    this.aD = copyFromUtf8;
                    return copyFromUtf8;
                }

                public View getDefaultInstanceForType() {
                    return View.getDefaultInstance();
                }

                public Descriptors.Descriptor getDescriptorForType() {
                    return ProtoMsg.ao;
                }

                public String getDeviceId() {
                    Object obj = this.aG;
                    if (obj instanceof String) {
                        return (String) obj;
                    }
                    ByteString byteString = (ByteString) obj;
                    String stringUtf8 = byteString.toStringUtf8();
                    if (byteString.isValidUtf8()) {
                        this.aG = stringUtf8;
                    }
                    return stringUtf8;
                }

                public ByteString getDeviceIdBytes() {
                    Object obj = this.aG;
                    if (!(obj instanceof String)) {
                        return (ByteString) obj;
                    }
                    ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                    this.aG = copyFromUtf8;
                    return copyFromUtf8;
                }

                public String getLabel() {
                    Object obj = this.bK;
                    if (obj instanceof String) {
                        return (String) obj;
                    }
                    ByteString byteString = (ByteString) obj;
                    String stringUtf8 = byteString.toStringUtf8();
                    if (byteString.isValidUtf8()) {
                        this.bK = stringUtf8;
                    }
                    return stringUtf8;
                }

                public ByteString getLabelBytes() {
                    Object obj = this.bK;
                    if (!(obj instanceof String)) {
                        return (ByteString) obj;
                    }
                    ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                    this.bK = copyFromUtf8;
                    return copyFromUtf8;
                }

                public String getLogVersionApp() {
                    Object obj = this.bj;
                    if (obj instanceof String) {
                        return (String) obj;
                    }
                    ByteString byteString = (ByteString) obj;
                    String stringUtf8 = byteString.toStringUtf8();
                    if (byteString.isValidUtf8()) {
                        this.bj = stringUtf8;
                    }
                    return stringUtf8;
                }

                public ByteString getLogVersionAppBytes() {
                    Object obj = this.bj;
                    if (!(obj instanceof String)) {
                        return (ByteString) obj;
                    }
                    ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                    this.bj = copyFromUtf8;
                    return copyFromUtf8;
                }

                public String getPackageName() {
                    Object obj = this.bh;
                    if (obj instanceof String) {
                        return (String) obj;
                    }
                    ByteString byteString = (ByteString) obj;
                    String stringUtf8 = byteString.toStringUtf8();
                    if (byteString.isValidUtf8()) {
                        this.bh = stringUtf8;
                    }
                    return stringUtf8;
                }

                public ByteString getPackageNameBytes() {
                    Object obj = this.bh;
                    if (!(obj instanceof String)) {
                        return (ByteString) obj;
                    }
                    ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                    this.bh = copyFromUtf8;
                    return copyFromUtf8;
                }

                public String getPlatform() {
                    Object obj = this.aM;
                    if (obj instanceof String) {
                        return (String) obj;
                    }
                    ByteString byteString = (ByteString) obj;
                    String stringUtf8 = byteString.toStringUtf8();
                    if (byteString.isValidUtf8()) {
                        this.aM = stringUtf8;
                    }
                    return stringUtf8;
                }

                public ByteString getPlatformBytes() {
                    Object obj = this.aM;
                    if (!(obj instanceof String)) {
                        return (ByteString) obj;
                    }
                    ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                    this.aM = copyFromUtf8;
                    return copyFromUtf8;
                }

                public String getSessionId() {
                    Object obj = this.aF;
                    if (obj instanceof String) {
                        return (String) obj;
                    }
                    ByteString byteString = (ByteString) obj;
                    String stringUtf8 = byteString.toStringUtf8();
                    if (byteString.isValidUtf8()) {
                        this.aF = stringUtf8;
                    }
                    return stringUtf8;
                }

                public ByteString getSessionIdBytes() {
                    Object obj = this.aF;
                    if (!(obj instanceof String)) {
                        return (ByteString) obj;
                    }
                    ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                    this.aF = copyFromUtf8;
                    return copyFromUtf8;
                }

                public long getTime() {
                    return this.bb;
                }

                public String getType() {
                    Object obj = this.aC;
                    if (obj instanceof String) {
                        return (String) obj;
                    }
                    ByteString byteString = (ByteString) obj;
                    String stringUtf8 = byteString.toStringUtf8();
                    if (byteString.isValidUtf8()) {
                        this.aC = stringUtf8;
                    }
                    return stringUtf8;
                }

                public ByteString getTypeBytes() {
                    Object obj = this.aC;
                    if (!(obj instanceof String)) {
                        return (ByteString) obj;
                    }
                    ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                    this.aC = copyFromUtf8;
                    return copyFromUtf8;
                }

                public String getUserId() {
                    Object obj = this.aH;
                    if (obj instanceof String) {
                        return (String) obj;
                    }
                    ByteString byteString = (ByteString) obj;
                    String stringUtf8 = byteString.toStringUtf8();
                    if (byteString.isValidUtf8()) {
                        this.aH = stringUtf8;
                    }
                    return stringUtf8;
                }

                public ByteString getUserIdBytes() {
                    Object obj = this.aH;
                    if (!(obj instanceof String)) {
                        return (ByteString) obj;
                    }
                    ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                    this.aH = copyFromUtf8;
                    return copyFromUtf8;
                }

                public String getViewId() {
                    Object obj = this.cw;
                    if (obj instanceof String) {
                        return (String) obj;
                    }
                    ByteString byteString = (ByteString) obj;
                    String stringUtf8 = byteString.toStringUtf8();
                    if (byteString.isValidUtf8()) {
                        this.cw = stringUtf8;
                    }
                    return stringUtf8;
                }

                public ByteString getViewIdBytes() {
                    Object obj = this.cw;
                    if (!(obj instanceof String)) {
                        return (ByteString) obj;
                    }
                    ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                    this.cw = copyFromUtf8;
                    return copyFromUtf8;
                }

                public ProtoMap getViewMap(int i) {
                    return (ProtoMap) (this.cy == null ? this.cx.get(i) : this.cy.getMessage(i));
                }

                public ProtoMap.Builder getViewMapBuilder(int i) {
                    return (ProtoMap.Builder) bq().getBuilder(i);
                }

                public List getViewMapBuilderList() {
                    return bq().getBuilderList();
                }

                public int getViewMapCount() {
                    return this.cy == null ? this.cx.size() : this.cy.getCount();
                }

                public List getViewMapList() {
                    return this.cy == null ? Collections.unmodifiableList(this.cx) : this.cy.getMessageList();
                }

                public ProtoMapOrBuilder getViewMapOrBuilder(int i) {
                    return (ProtoMapOrBuilder) (this.cy == null ? this.cx.get(i) : this.cy.getMessageOrBuilder(i));
                }

                public List getViewMapOrBuilderList() {
                    return this.cy != null ? this.cy.getMessageOrBuilderList() : Collections.unmodifiableList(this.cx);
                }

                public boolean hasAppChannel() {
                    return (this.aA & 1024) == 1024;
                }

                public boolean hasAppKey() {
                    return (this.aA & 512) == 512;
                }

                public boolean hasDeviceId() {
                    return (this.aA & 8) == 8;
                }

                public boolean hasLabel() {
                    return (this.aA & 64) == 64;
                }

                public boolean hasLogVersionApp() {
                    return (this.aA & 4096) == 4096;
                }

                public boolean hasPackageName() {
                    return (this.aA & 2048) == 2048;
                }

                public boolean hasPlatform() {
                    return (this.aA & 256) == 256;
                }

                public boolean hasSessionId() {
                    return (this.aA & 2) == 2;
                }

                public boolean hasTime() {
                    return (this.aA & 16) == 16;
                }

                public boolean hasType() {
                    return (this.aA & 1) == 1;
                }

                public boolean hasUserId() {
                    return (this.aA & 4) == 4;
                }

                public boolean hasViewId() {
                    return (this.aA & 32) == 32;
                }

                /* access modifiers changed from: protected */
                public GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
                    return ProtoMsg.ap.ensureFieldAccessorsInitialized(View.class, Builder.class);
                }

                public final boolean isInitialized() {
                    if (!hasType()) {
                        return false;
                    }
                    for (int i = 0; i < getViewMapCount(); i++) {
                        if (!getViewMap(i).isInitialized()) {
                            return false;
                        }
                    }
                    return true;
                }

                public Builder mergeFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) {
                    View view;
                    View view2 = null;
                    try {
                        View view3 = (View) View.PARSER.parsePartialFrom(codedInputStream, extensionRegistryLite);
                        if (view3 != null) {
                            mergeFrom(view3);
                        }
                        return this;
                    } catch (InvalidProtocolBufferException e) {
                        view = (View) e.getUnfinishedMessage();
                        throw e;
                    } catch (Throwable th) {
                        th = th;
                        view2 = view;
                    }
                    if (view2 != null) {
                        mergeFrom(view2);
                    }
                    throw th;
                }

                public Builder mergeFrom(Message message) {
                    if (message instanceof View) {
                        return mergeFrom((View) message);
                    }
                    super.mergeFrom(message);
                    return this;
                }

                public Builder mergeFrom(View view) {
                    if (view == View.getDefaultInstance()) {
                        return this;
                    }
                    if (view.hasType()) {
                        this.aA |= 1;
                        this.aC = view.aC;
                        onChanged();
                    }
                    if (view.hasSessionId()) {
                        this.aA |= 2;
                        this.aF = view.aF;
                        onChanged();
                    }
                    if (view.hasUserId()) {
                        this.aA |= 4;
                        this.aH = view.aH;
                        onChanged();
                    }
                    if (view.hasDeviceId()) {
                        this.aA |= 8;
                        this.aG = view.aG;
                        onChanged();
                    }
                    if (view.hasTime()) {
                        setTime(view.getTime());
                    }
                    if (view.hasViewId()) {
                        this.aA |= 32;
                        this.cw = view.cw;
                        onChanged();
                    }
                    if (view.hasLabel()) {
                        this.aA |= 64;
                        this.bK = view.bK;
                        onChanged();
                    }
                    if (this.cy == null) {
                        if (!view.cx.isEmpty()) {
                            if (this.cx.isEmpty()) {
                                this.cx = view.cx;
                                this.aA &= -129;
                            } else {
                                bp();
                                this.cx.addAll(view.cx);
                            }
                            onChanged();
                        }
                    } else if (!view.cx.isEmpty()) {
                        if (this.cy.isEmpty()) {
                            this.cy.dispose();
                            RepeatedFieldBuilder repeatedFieldBuilder = null;
                            this.cy = null;
                            this.cx = view.cx;
                            this.aA &= -129;
                            if (View.alwaysUseFieldBuilders) {
                                repeatedFieldBuilder = bq();
                            }
                            this.cy = repeatedFieldBuilder;
                        } else {
                            this.cy.addAllMessages(view.cx);
                        }
                    }
                    if (view.hasPlatform()) {
                        this.aA |= 256;
                        this.aM = view.aM;
                        onChanged();
                    }
                    if (view.hasAppKey()) {
                        this.aA |= 512;
                        this.aD = view.aD;
                        onChanged();
                    }
                    if (view.hasAppChannel()) {
                        this.aA |= 1024;
                        this.aE = view.aE;
                        onChanged();
                    }
                    if (view.hasPackageName()) {
                        this.aA |= 2048;
                        this.bh = view.bh;
                        onChanged();
                    }
                    if (view.hasLogVersionApp()) {
                        this.aA |= 4096;
                        this.bj = view.bj;
                        onChanged();
                    }
                    mergeUnknownFields(view.getUnknownFields());
                    return this;
                }

                public Builder removeViewMap(int i) {
                    if (this.cy == null) {
                        bp();
                        this.cx.remove(i);
                        onChanged();
                    } else {
                        this.cy.remove(i);
                    }
                    return this;
                }

                public Builder setAppChannel(String str) {
                    if (str != null) {
                        this.aA |= 1024;
                        this.aE = str;
                        onChanged();
                        return this;
                    }
                    throw new NullPointerException();
                }

                public Builder setAppChannelBytes(ByteString byteString) {
                    if (byteString != null) {
                        this.aA |= 1024;
                        this.aE = byteString;
                        onChanged();
                        return this;
                    }
                    throw new NullPointerException();
                }

                public Builder setAppKey(String str) {
                    if (str != null) {
                        this.aA |= 512;
                        this.aD = str;
                        onChanged();
                        return this;
                    }
                    throw new NullPointerException();
                }

                public Builder setAppKeyBytes(ByteString byteString) {
                    if (byteString != null) {
                        this.aA |= 512;
                        this.aD = byteString;
                        onChanged();
                        return this;
                    }
                    throw new NullPointerException();
                }

                public Builder setDeviceId(String str) {
                    if (str != null) {
                        this.aA |= 8;
                        this.aG = str;
                        onChanged();
                        return this;
                    }
                    throw new NullPointerException();
                }

                public Builder setDeviceIdBytes(ByteString byteString) {
                    if (byteString != null) {
                        this.aA |= 8;
                        this.aG = byteString;
                        onChanged();
                        return this;
                    }
                    throw new NullPointerException();
                }

                public Builder setLabel(String str) {
                    if (str != null) {
                        this.aA |= 64;
                        this.bK = str;
                        onChanged();
                        return this;
                    }
                    throw new NullPointerException();
                }

                public Builder setLabelBytes(ByteString byteString) {
                    if (byteString != null) {
                        this.aA |= 64;
                        this.bK = byteString;
                        onChanged();
                        return this;
                    }
                    throw new NullPointerException();
                }

                public Builder setLogVersionApp(String str) {
                    if (str != null) {
                        this.aA |= 4096;
                        this.bj = str;
                        onChanged();
                        return this;
                    }
                    throw new NullPointerException();
                }

                public Builder setLogVersionAppBytes(ByteString byteString) {
                    if (byteString != null) {
                        this.aA |= 4096;
                        this.bj = byteString;
                        onChanged();
                        return this;
                    }
                    throw new NullPointerException();
                }

                public Builder setPackageName(String str) {
                    if (str != null) {
                        this.aA |= 2048;
                        this.bh = str;
                        onChanged();
                        return this;
                    }
                    throw new NullPointerException();
                }

                public Builder setPackageNameBytes(ByteString byteString) {
                    if (byteString != null) {
                        this.aA |= 2048;
                        this.bh = byteString;
                        onChanged();
                        return this;
                    }
                    throw new NullPointerException();
                }

                public Builder setPlatform(String str) {
                    if (str != null) {
                        this.aA |= 256;
                        this.aM = str;
                        onChanged();
                        return this;
                    }
                    throw new NullPointerException();
                }

                public Builder setPlatformBytes(ByteString byteString) {
                    if (byteString != null) {
                        this.aA |= 256;
                        this.aM = byteString;
                        onChanged();
                        return this;
                    }
                    throw new NullPointerException();
                }

                public Builder setSessionId(String str) {
                    if (str != null) {
                        this.aA |= 2;
                        this.aF = str;
                        onChanged();
                        return this;
                    }
                    throw new NullPointerException();
                }

                public Builder setSessionIdBytes(ByteString byteString) {
                    if (byteString != null) {
                        this.aA |= 2;
                        this.aF = byteString;
                        onChanged();
                        return this;
                    }
                    throw new NullPointerException();
                }

                public Builder setTime(long j) {
                    this.aA |= 16;
                    this.bb = j;
                    onChanged();
                    return this;
                }

                public Builder setType(String str) {
                    if (str != null) {
                        this.aA |= 1;
                        this.aC = str;
                        onChanged();
                        return this;
                    }
                    throw new NullPointerException();
                }

                public Builder setTypeBytes(ByteString byteString) {
                    if (byteString != null) {
                        this.aA |= 1;
                        this.aC = byteString;
                        onChanged();
                        return this;
                    }
                    throw new NullPointerException();
                }

                public Builder setUserId(String str) {
                    if (str != null) {
                        this.aA |= 4;
                        this.aH = str;
                        onChanged();
                        return this;
                    }
                    throw new NullPointerException();
                }

                public Builder setUserIdBytes(ByteString byteString) {
                    if (byteString != null) {
                        this.aA |= 4;
                        this.aH = byteString;
                        onChanged();
                        return this;
                    }
                    throw new NullPointerException();
                }

                public Builder setViewId(String str) {
                    if (str != null) {
                        this.aA |= 32;
                        this.cw = str;
                        onChanged();
                        return this;
                    }
                    throw new NullPointerException();
                }

                public Builder setViewIdBytes(ByteString byteString) {
                    if (byteString != null) {
                        this.aA |= 32;
                        this.cw = byteString;
                        onChanged();
                        return this;
                    }
                    throw new NullPointerException();
                }

                public Builder setViewMap(int i, ProtoMap.Builder builder) {
                    if (this.cy == null) {
                        bp();
                        this.cx.set(i, builder.build());
                        onChanged();
                    } else {
                        this.cy.setMessage(i, builder.build());
                    }
                    return this;
                }

                public Builder setViewMap(int i, ProtoMap protoMap) {
                    if (this.cy != null) {
                        this.cy.setMessage(i, protoMap);
                    } else if (protoMap != null) {
                        bp();
                        this.cx.set(i, protoMap);
                        onChanged();
                    } else {
                        throw new NullPointerException();
                    }
                    return this;
                }
            }

            static {
                cv.P();
            }

            private View(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) {
                this.ax = -1;
                this.ay = -1;
                P();
                UnknownFieldSet.Builder newBuilder = UnknownFieldSet.newBuilder();
                boolean z = false;
                boolean z2 = false;
                while (!z) {
                    try {
                        int readTag = codedInputStream.readTag();
                        switch (readTag) {
                            case 0:
                                z = true;
                                break;
                            case 10:
                                ByteString readBytes = codedInputStream.readBytes();
                                this.aA = 1 | this.aA;
                                this.aC = readBytes;
                                break;
                            case 18:
                                ByteString readBytes2 = codedInputStream.readBytes();
                                this.aA |= 2;
                                this.aF = readBytes2;
                                break;
                            case 26:
                                ByteString readBytes3 = codedInputStream.readBytes();
                                this.aA |= 4;
                                this.aH = readBytes3;
                                break;
                            case 34:
                                ByteString readBytes4 = codedInputStream.readBytes();
                                this.aA |= 8;
                                this.aG = readBytes4;
                                break;
                            case 40:
                                this.aA |= 16;
                                this.bb = codedInputStream.readInt64();
                                break;
                            case 50:
                                ByteString readBytes5 = codedInputStream.readBytes();
                                this.aA |= 32;
                                this.cw = readBytes5;
                                break;
                            case 58:
                                ByteString readBytes6 = codedInputStream.readBytes();
                                this.aA |= 64;
                                this.bK = readBytes6;
                                break;
                            case 66:
                                if (!(z2 & true)) {
                                    this.cx = new ArrayList();
                                    z2 |= true;
                                }
                                this.cx.add(codedInputStream.readMessage(ProtoMap.PARSER, extensionRegistryLite));
                                break;
                            case 74:
                                ByteString readBytes7 = codedInputStream.readBytes();
                                this.aA |= 128;
                                this.aM = readBytes7;
                                break;
                            case 82:
                                ByteString readBytes8 = codedInputStream.readBytes();
                                this.aA |= 256;
                                this.aD = readBytes8;
                                break;
                            case 90:
                                ByteString readBytes9 = codedInputStream.readBytes();
                                this.aA |= 512;
                                this.aE = readBytes9;
                                break;
                            case 98:
                                ByteString readBytes10 = codedInputStream.readBytes();
                                this.aA |= 1024;
                                this.bh = readBytes10;
                                break;
                            case 106:
                                ByteString readBytes11 = codedInputStream.readBytes();
                                this.aA |= 2048;
                                this.bj = readBytes11;
                                break;
                            default:
                                if (parseUnknownField(codedInputStream, newBuilder, extensionRegistryLite, readTag)) {
                                    break;
                                }
                                z = true;
                                break;
                        }
                    } catch (InvalidProtocolBufferException e) {
                        throw e.setUnfinishedMessage(this);
                    } catch (IOException e2) {
                        throw new InvalidProtocolBufferException(e2.getMessage()).setUnfinishedMessage(this);
                    } catch (Throwable th) {
                        if (z2 & true) {
                            this.cx = Collections.unmodifiableList(this.cx);
                        }
                        this.aw = newBuilder.build();
                        makeExtensionsImmutable();
                        throw th;
                    }
                }
                if (z2 & true) {
                    this.cx = Collections.unmodifiableList(this.cx);
                }
                this.aw = newBuilder.build();
                makeExtensionsImmutable();
            }

            /* synthetic */ View(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite, a aVar) {
                this(codedInputStream, extensionRegistryLite);
            }

            private View(GeneratedMessage.Builder builder) {
                super(builder);
                this.ax = -1;
                this.ay = -1;
                this.aw = builder.getUnknownFields();
            }

            /* synthetic */ View(GeneratedMessage.Builder builder, a aVar) {
                this(builder);
            }

            private View(boolean z) {
                this.ax = -1;
                this.ay = -1;
                this.aw = UnknownFieldSet.getDefaultInstance();
            }

            private void P() {
                this.aC = "";
                this.aF = "";
                this.aH = "";
                this.aG = "";
                this.bb = 0;
                this.cw = "";
                this.bK = "";
                this.cx = Collections.emptyList();
                this.aM = "";
                this.aD = "";
                this.aE = "";
                this.bh = "";
                this.bj = "";
            }

            public static View getDefaultInstance() {
                return cv;
            }

            public static final Descriptors.Descriptor getDescriptor() {
                return ProtoMsg.ao;
            }

            public static Builder newBuilder() {
                return Builder.bo();
            }

            public static Builder newBuilder(View view) {
                return newBuilder().mergeFrom(view);
            }

            public static View parseDelimitedFrom(InputStream inputStream) {
                return (View) PARSER.parseDelimitedFrom(inputStream);
            }

            public static View parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) {
                return (View) PARSER.parseDelimitedFrom(inputStream, extensionRegistryLite);
            }

            public static View parseFrom(ByteString byteString) {
                return (View) PARSER.parseFrom(byteString);
            }

            public static View parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) {
                return (View) PARSER.parseFrom(byteString, extensionRegistryLite);
            }

            public static View parseFrom(CodedInputStream codedInputStream) {
                return (View) PARSER.parseFrom(codedInputStream);
            }

            public static View parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) {
                return (View) PARSER.parseFrom(codedInputStream, extensionRegistryLite);
            }

            public static View parseFrom(InputStream inputStream) {
                return (View) PARSER.parseFrom(inputStream);
            }

            public static View parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) {
                return (View) PARSER.parseFrom(inputStream, extensionRegistryLite);
            }

            public static View parseFrom(byte[] bArr) {
                return (View) PARSER.parseFrom(bArr);
            }

            public static View parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) {
                return (View) PARSER.parseFrom(bArr, extensionRegistryLite);
            }

            public String getAppChannel() {
                Object obj = this.aE;
                if (obj instanceof String) {
                    return (String) obj;
                }
                ByteString byteString = (ByteString) obj;
                String stringUtf8 = byteString.toStringUtf8();
                if (byteString.isValidUtf8()) {
                    this.aE = stringUtf8;
                }
                return stringUtf8;
            }

            public ByteString getAppChannelBytes() {
                Object obj = this.aE;
                if (!(obj instanceof String)) {
                    return (ByteString) obj;
                }
                ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                this.aE = copyFromUtf8;
                return copyFromUtf8;
            }

            public String getAppKey() {
                Object obj = this.aD;
                if (obj instanceof String) {
                    return (String) obj;
                }
                ByteString byteString = (ByteString) obj;
                String stringUtf8 = byteString.toStringUtf8();
                if (byteString.isValidUtf8()) {
                    this.aD = stringUtf8;
                }
                return stringUtf8;
            }

            public ByteString getAppKeyBytes() {
                Object obj = this.aD;
                if (!(obj instanceof String)) {
                    return (ByteString) obj;
                }
                ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                this.aD = copyFromUtf8;
                return copyFromUtf8;
            }

            public View getDefaultInstanceForType() {
                return cv;
            }

            public String getDeviceId() {
                Object obj = this.aG;
                if (obj instanceof String) {
                    return (String) obj;
                }
                ByteString byteString = (ByteString) obj;
                String stringUtf8 = byteString.toStringUtf8();
                if (byteString.isValidUtf8()) {
                    this.aG = stringUtf8;
                }
                return stringUtf8;
            }

            public ByteString getDeviceIdBytes() {
                Object obj = this.aG;
                if (!(obj instanceof String)) {
                    return (ByteString) obj;
                }
                ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                this.aG = copyFromUtf8;
                return copyFromUtf8;
            }

            public String getLabel() {
                Object obj = this.bK;
                if (obj instanceof String) {
                    return (String) obj;
                }
                ByteString byteString = (ByteString) obj;
                String stringUtf8 = byteString.toStringUtf8();
                if (byteString.isValidUtf8()) {
                    this.bK = stringUtf8;
                }
                return stringUtf8;
            }

            public ByteString getLabelBytes() {
                Object obj = this.bK;
                if (!(obj instanceof String)) {
                    return (ByteString) obj;
                }
                ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                this.bK = copyFromUtf8;
                return copyFromUtf8;
            }

            public String getLogVersionApp() {
                Object obj = this.bj;
                if (obj instanceof String) {
                    return (String) obj;
                }
                ByteString byteString = (ByteString) obj;
                String stringUtf8 = byteString.toStringUtf8();
                if (byteString.isValidUtf8()) {
                    this.bj = stringUtf8;
                }
                return stringUtf8;
            }

            public ByteString getLogVersionAppBytes() {
                Object obj = this.bj;
                if (!(obj instanceof String)) {
                    return (ByteString) obj;
                }
                ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                this.bj = copyFromUtf8;
                return copyFromUtf8;
            }

            public String getPackageName() {
                Object obj = this.bh;
                if (obj instanceof String) {
                    return (String) obj;
                }
                ByteString byteString = (ByteString) obj;
                String stringUtf8 = byteString.toStringUtf8();
                if (byteString.isValidUtf8()) {
                    this.bh = stringUtf8;
                }
                return stringUtf8;
            }

            public ByteString getPackageNameBytes() {
                Object obj = this.bh;
                if (!(obj instanceof String)) {
                    return (ByteString) obj;
                }
                ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                this.bh = copyFromUtf8;
                return copyFromUtf8;
            }

            public Parser getParserForType() {
                return PARSER;
            }

            public String getPlatform() {
                Object obj = this.aM;
                if (obj instanceof String) {
                    return (String) obj;
                }
                ByteString byteString = (ByteString) obj;
                String stringUtf8 = byteString.toStringUtf8();
                if (byteString.isValidUtf8()) {
                    this.aM = stringUtf8;
                }
                return stringUtf8;
            }

            public ByteString getPlatformBytes() {
                Object obj = this.aM;
                if (!(obj instanceof String)) {
                    return (ByteString) obj;
                }
                ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                this.aM = copyFromUtf8;
                return copyFromUtf8;
            }

            public int getSerializedSize() {
                int i = this.ay;
                if (i != -1) {
                    return i;
                }
                int computeBytesSize = (this.aA & 1) == 1 ? CodedOutputStream.computeBytesSize(1, getTypeBytes()) + 0 : 0;
                if ((this.aA & 2) == 2) {
                    computeBytesSize += CodedOutputStream.computeBytesSize(2, getSessionIdBytes());
                }
                if ((this.aA & 4) == 4) {
                    computeBytesSize += CodedOutputStream.computeBytesSize(3, getUserIdBytes());
                }
                if ((this.aA & 8) == 8) {
                    computeBytesSize += CodedOutputStream.computeBytesSize(4, getDeviceIdBytes());
                }
                if ((this.aA & 16) == 16) {
                    computeBytesSize += CodedOutputStream.computeInt64Size(5, this.bb);
                }
                if ((this.aA & 32) == 32) {
                    computeBytesSize += CodedOutputStream.computeBytesSize(6, getViewIdBytes());
                }
                if ((this.aA & 64) == 64) {
                    computeBytesSize += CodedOutputStream.computeBytesSize(7, getLabelBytes());
                }
                for (int i2 = 0; i2 < this.cx.size(); i2++) {
                    computeBytesSize += CodedOutputStream.computeMessageSize(8, (MessageLite) this.cx.get(i2));
                }
                if ((this.aA & 128) == 128) {
                    computeBytesSize += CodedOutputStream.computeBytesSize(9, getPlatformBytes());
                }
                if ((this.aA & 256) == 256) {
                    computeBytesSize += CodedOutputStream.computeBytesSize(10, getAppKeyBytes());
                }
                if ((this.aA & 512) == 512) {
                    computeBytesSize += CodedOutputStream.computeBytesSize(11, getAppChannelBytes());
                }
                if ((this.aA & 1024) == 1024) {
                    computeBytesSize += CodedOutputStream.computeBytesSize(12, getPackageNameBytes());
                }
                if ((this.aA & 2048) == 2048) {
                    computeBytesSize += CodedOutputStream.computeBytesSize(13, getLogVersionAppBytes());
                }
                int serializedSize = computeBytesSize + getUnknownFields().getSerializedSize();
                this.ay = serializedSize;
                return serializedSize;
            }

            public String getSessionId() {
                Object obj = this.aF;
                if (obj instanceof String) {
                    return (String) obj;
                }
                ByteString byteString = (ByteString) obj;
                String stringUtf8 = byteString.toStringUtf8();
                if (byteString.isValidUtf8()) {
                    this.aF = stringUtf8;
                }
                return stringUtf8;
            }

            public ByteString getSessionIdBytes() {
                Object obj = this.aF;
                if (!(obj instanceof String)) {
                    return (ByteString) obj;
                }
                ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                this.aF = copyFromUtf8;
                return copyFromUtf8;
            }

            public long getTime() {
                return this.bb;
            }

            public String getType() {
                Object obj = this.aC;
                if (obj instanceof String) {
                    return (String) obj;
                }
                ByteString byteString = (ByteString) obj;
                String stringUtf8 = byteString.toStringUtf8();
                if (byteString.isValidUtf8()) {
                    this.aC = stringUtf8;
                }
                return stringUtf8;
            }

            public ByteString getTypeBytes() {
                Object obj = this.aC;
                if (!(obj instanceof String)) {
                    return (ByteString) obj;
                }
                ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                this.aC = copyFromUtf8;
                return copyFromUtf8;
            }

            public final UnknownFieldSet getUnknownFields() {
                return this.aw;
            }

            public String getUserId() {
                Object obj = this.aH;
                if (obj instanceof String) {
                    return (String) obj;
                }
                ByteString byteString = (ByteString) obj;
                String stringUtf8 = byteString.toStringUtf8();
                if (byteString.isValidUtf8()) {
                    this.aH = stringUtf8;
                }
                return stringUtf8;
            }

            public ByteString getUserIdBytes() {
                Object obj = this.aH;
                if (!(obj instanceof String)) {
                    return (ByteString) obj;
                }
                ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                this.aH = copyFromUtf8;
                return copyFromUtf8;
            }

            public String getViewId() {
                Object obj = this.cw;
                if (obj instanceof String) {
                    return (String) obj;
                }
                ByteString byteString = (ByteString) obj;
                String stringUtf8 = byteString.toStringUtf8();
                if (byteString.isValidUtf8()) {
                    this.cw = stringUtf8;
                }
                return stringUtf8;
            }

            public ByteString getViewIdBytes() {
                Object obj = this.cw;
                if (!(obj instanceof String)) {
                    return (ByteString) obj;
                }
                ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                this.cw = copyFromUtf8;
                return copyFromUtf8;
            }

            public ProtoMap getViewMap(int i) {
                return (ProtoMap) this.cx.get(i);
            }

            public int getViewMapCount() {
                return this.cx.size();
            }

            public List getViewMapList() {
                return this.cx;
            }

            public ProtoMapOrBuilder getViewMapOrBuilder(int i) {
                return (ProtoMapOrBuilder) this.cx.get(i);
            }

            public List getViewMapOrBuilderList() {
                return this.cx;
            }

            public boolean hasAppChannel() {
                return (this.aA & 512) == 512;
            }

            public boolean hasAppKey() {
                return (this.aA & 256) == 256;
            }

            public boolean hasDeviceId() {
                return (this.aA & 8) == 8;
            }

            public boolean hasLabel() {
                return (this.aA & 64) == 64;
            }

            public boolean hasLogVersionApp() {
                return (this.aA & 2048) == 2048;
            }

            public boolean hasPackageName() {
                return (this.aA & 1024) == 1024;
            }

            public boolean hasPlatform() {
                return (this.aA & 128) == 128;
            }

            public boolean hasSessionId() {
                return (this.aA & 2) == 2;
            }

            public boolean hasTime() {
                return (this.aA & 16) == 16;
            }

            public boolean hasType() {
                return (this.aA & 1) == 1;
            }

            public boolean hasUserId() {
                return (this.aA & 4) == 4;
            }

            public boolean hasViewId() {
                return (this.aA & 32) == 32;
            }

            /* access modifiers changed from: protected */
            public GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
                return ProtoMsg.ap.ensureFieldAccessorsInitialized(View.class, Builder.class);
            }

            public final boolean isInitialized() {
                byte b = this.ax;
                if (b == 1) {
                    return true;
                }
                if (b == 0) {
                    return false;
                }
                if (!hasType()) {
                    this.ax = 0;
                    return false;
                }
                for (int i = 0; i < getViewMapCount(); i++) {
                    if (!getViewMap(i).isInitialized()) {
                        this.ax = 0;
                        return false;
                    }
                }
                this.ax = 1;
                return true;
            }

            public Builder newBuilderForType() {
                return newBuilder();
            }

            /* access modifiers changed from: protected */
            public Builder newBuilderForType(GeneratedMessage.BuilderParent builderParent) {
                return new Builder(builderParent, (a) null);
            }

            public Builder toBuilder() {
                return newBuilder(this);
            }

            /* access modifiers changed from: protected */
            public Object writeReplace() {
                return super.writeReplace();
            }

            public void writeTo(CodedOutputStream codedOutputStream) {
                getSerializedSize();
                if ((this.aA & 1) == 1) {
                    codedOutputStream.writeBytes(1, getTypeBytes());
                }
                if ((this.aA & 2) == 2) {
                    codedOutputStream.writeBytes(2, getSessionIdBytes());
                }
                if ((this.aA & 4) == 4) {
                    codedOutputStream.writeBytes(3, getUserIdBytes());
                }
                if ((this.aA & 8) == 8) {
                    codedOutputStream.writeBytes(4, getDeviceIdBytes());
                }
                if ((this.aA & 16) == 16) {
                    codedOutputStream.writeInt64(5, this.bb);
                }
                if ((this.aA & 32) == 32) {
                    codedOutputStream.writeBytes(6, getViewIdBytes());
                }
                if ((this.aA & 64) == 64) {
                    codedOutputStream.writeBytes(7, getLabelBytes());
                }
                for (int i = 0; i < this.cx.size(); i++) {
                    codedOutputStream.writeMessage(8, (MessageLite) this.cx.get(i));
                }
                if ((this.aA & 128) == 128) {
                    codedOutputStream.writeBytes(9, getPlatformBytes());
                }
                if ((this.aA & 256) == 256) {
                    codedOutputStream.writeBytes(10, getAppKeyBytes());
                }
                if ((this.aA & 512) == 512) {
                    codedOutputStream.writeBytes(11, getAppChannelBytes());
                }
                if ((this.aA & 1024) == 1024) {
                    codedOutputStream.writeBytes(12, getPackageNameBytes());
                }
                if ((this.aA & 2048) == 2048) {
                    codedOutputStream.writeBytes(13, getLogVersionAppBytes());
                }
                getUnknownFields().writeTo(codedOutputStream);
            }
        }

        public final class ViewMsg extends GeneratedMessage implements ViewMsgOrBuilder {
            public static Parser PARSER = new s();
            public static final int VIEW_FIELD_NUMBER = 1;
            private static final ViewMsg cz = new ViewMsg(true);
            private final UnknownFieldSet aw;
            private byte ax;
            private int ay;
            /* access modifiers changed from: private */
            public List cA;

            public final class Builder extends GeneratedMessage.Builder implements ViewMsgOrBuilder {
                private int aA;
                private List cA;
                private RepeatedFieldBuilder cB;

                private Builder() {
                    this.cA = Collections.emptyList();
                    R();
                }

                private Builder(GeneratedMessage.BuilderParent builderParent) {
                    super(builderParent);
                    this.cA = Collections.emptyList();
                    R();
                }

                /* synthetic */ Builder(GeneratedMessage.BuilderParent builderParent, a aVar) {
                    this(builderParent);
                }

                private void R() {
                    if (ViewMsg.alwaysUseFieldBuilders) {
                        bw();
                    }
                }

                /* access modifiers changed from: private */
                public static Builder bu() {
                    return new Builder();
                }

                private void bv() {
                    if ((this.aA & 1) != 1) {
                        this.cA = new ArrayList(this.cA);
                        this.aA |= 1;
                    }
                }

                private RepeatedFieldBuilder bw() {
                    if (this.cB == null) {
                        List list = this.cA;
                        boolean z = true;
                        if ((this.aA & 1) != 1) {
                            z = false;
                        }
                        this.cB = new RepeatedFieldBuilder(list, z, getParentForChildren(), isClean());
                        this.cA = null;
                    }
                    return this.cB;
                }

                public static final Descriptors.Descriptor getDescriptor() {
                    return ProtoMsg.aq;
                }

                public Builder addAllView(Iterable iterable) {
                    if (this.cB == null) {
                        bv();
                        AbstractMessageLite.Builder.addAll(iterable, this.cA);
                        onChanged();
                    } else {
                        this.cB.addAllMessages(iterable);
                    }
                    return this;
                }

                public Builder addView(int i, View.Builder builder) {
                    if (this.cB == null) {
                        bv();
                        this.cA.add(i, builder.build());
                        onChanged();
                    } else {
                        this.cB.addMessage(i, builder.build());
                    }
                    return this;
                }

                public Builder addView(int i, View view) {
                    if (this.cB != null) {
                        this.cB.addMessage(i, view);
                    } else if (view != null) {
                        bv();
                        this.cA.add(i, view);
                        onChanged();
                    } else {
                        throw new NullPointerException();
                    }
                    return this;
                }

                public Builder addView(View.Builder builder) {
                    if (this.cB == null) {
                        bv();
                        this.cA.add(builder.build());
                        onChanged();
                    } else {
                        this.cB.addMessage(builder.build());
                    }
                    return this;
                }

                public Builder addView(View view) {
                    if (this.cB != null) {
                        this.cB.addMessage(view);
                    } else if (view != null) {
                        bv();
                        this.cA.add(view);
                        onChanged();
                    } else {
                        throw new NullPointerException();
                    }
                    return this;
                }

                public View.Builder addViewBuilder() {
                    return (View.Builder) bw().addBuilder(View.getDefaultInstance());
                }

                public View.Builder addViewBuilder(int i) {
                    return (View.Builder) bw().addBuilder(i, View.getDefaultInstance());
                }

                public ViewMsg build() {
                    ViewMsg buildPartial = buildPartial();
                    if (buildPartial.isInitialized()) {
                        return buildPartial;
                    }
                    throw newUninitializedMessageException(buildPartial);
                }

                public ViewMsg buildPartial() {
                    List list;
                    ViewMsg viewMsg = new ViewMsg((GeneratedMessage.Builder) this, (a) null);
                    int i = this.aA;
                    if (this.cB == null) {
                        if ((this.aA & 1) == 1) {
                            this.cA = Collections.unmodifiableList(this.cA);
                            this.aA &= -2;
                        }
                        list = this.cA;
                    } else {
                        list = this.cB.build();
                    }
                    List unused = viewMsg.cA = list;
                    onBuilt();
                    return viewMsg;
                }

                public Builder clear() {
                    super.clear();
                    if (this.cB == null) {
                        this.cA = Collections.emptyList();
                        this.aA &= -2;
                    } else {
                        this.cB.clear();
                    }
                    return this;
                }

                public Builder clearView() {
                    if (this.cB == null) {
                        this.cA = Collections.emptyList();
                        this.aA &= -2;
                        onChanged();
                    } else {
                        this.cB.clear();
                    }
                    return this;
                }

                public Builder clone() {
                    return bu().mergeFrom(buildPartial());
                }

                public ViewMsg getDefaultInstanceForType() {
                    return ViewMsg.getDefaultInstance();
                }

                public Descriptors.Descriptor getDescriptorForType() {
                    return ProtoMsg.aq;
                }

                public View getView(int i) {
                    return (View) (this.cB == null ? this.cA.get(i) : this.cB.getMessage(i));
                }

                public View.Builder getViewBuilder(int i) {
                    return (View.Builder) bw().getBuilder(i);
                }

                public List getViewBuilderList() {
                    return bw().getBuilderList();
                }

                public int getViewCount() {
                    return this.cB == null ? this.cA.size() : this.cB.getCount();
                }

                public List getViewList() {
                    return this.cB == null ? Collections.unmodifiableList(this.cA) : this.cB.getMessageList();
                }

                public ViewOrBuilder getViewOrBuilder(int i) {
                    return (ViewOrBuilder) (this.cB == null ? this.cA.get(i) : this.cB.getMessageOrBuilder(i));
                }

                public List getViewOrBuilderList() {
                    return this.cB != null ? this.cB.getMessageOrBuilderList() : Collections.unmodifiableList(this.cA);
                }

                /* access modifiers changed from: protected */
                public GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
                    return ProtoMsg.ar.ensureFieldAccessorsInitialized(ViewMsg.class, Builder.class);
                }

                public final boolean isInitialized() {
                    for (int i = 0; i < getViewCount(); i++) {
                        if (!getView(i).isInitialized()) {
                            return false;
                        }
                    }
                    return true;
                }

                public Builder mergeFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) {
                    ViewMsg viewMsg;
                    ViewMsg viewMsg2 = null;
                    try {
                        ViewMsg viewMsg3 = (ViewMsg) ViewMsg.PARSER.parsePartialFrom(codedInputStream, extensionRegistryLite);
                        if (viewMsg3 != null) {
                            mergeFrom(viewMsg3);
                        }
                        return this;
                    } catch (InvalidProtocolBufferException e) {
                        viewMsg = (ViewMsg) e.getUnfinishedMessage();
                        throw e;
                    } catch (Throwable th) {
                        th = th;
                        viewMsg2 = viewMsg;
                    }
                    if (viewMsg2 != null) {
                        mergeFrom(viewMsg2);
                    }
                    throw th;
                }

                public Builder mergeFrom(Message message) {
                    if (message instanceof ViewMsg) {
                        return mergeFrom((ViewMsg) message);
                    }
                    super.mergeFrom(message);
                    return this;
                }

                public Builder mergeFrom(ViewMsg viewMsg) {
                    if (viewMsg == ViewMsg.getDefaultInstance()) {
                        return this;
                    }
                    if (this.cB == null) {
                        if (!viewMsg.cA.isEmpty()) {
                            if (this.cA.isEmpty()) {
                                this.cA = viewMsg.cA;
                                this.aA &= -2;
                            } else {
                                bv();
                                this.cA.addAll(viewMsg.cA);
                            }
                            onChanged();
                        }
                    } else if (!viewMsg.cA.isEmpty()) {
                        if (this.cB.isEmpty()) {
                            this.cB.dispose();
                            RepeatedFieldBuilder repeatedFieldBuilder = null;
                            this.cB = null;
                            this.cA = viewMsg.cA;
                            this.aA &= -2;
                            if (ViewMsg.alwaysUseFieldBuilders) {
                                repeatedFieldBuilder = bw();
                            }
                            this.cB = repeatedFieldBuilder;
                        } else {
                            this.cB.addAllMessages(viewMsg.cA);
                        }
                    }
                    mergeUnknownFields(viewMsg.getUnknownFields());
                    return this;
                }

                public Builder removeView(int i) {
                    if (this.cB == null) {
                        bv();
                        this.cA.remove(i);
                        onChanged();
                    } else {
                        this.cB.remove(i);
                    }
                    return this;
                }

                public Builder setView(int i, View.Builder builder) {
                    if (this.cB == null) {
                        bv();
                        this.cA.set(i, builder.build());
                        onChanged();
                    } else {
                        this.cB.setMessage(i, builder.build());
                    }
                    return this;
                }

                public Builder setView(int i, View view) {
                    if (this.cB != null) {
                        this.cB.setMessage(i, view);
                    } else if (view != null) {
                        bv();
                        this.cA.set(i, view);
                        onChanged();
                    } else {
                        throw new NullPointerException();
                    }
                    return this;
                }
            }

            static {
                cz.P();
            }

            private ViewMsg(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) {
                this.ax = -1;
                this.ay = -1;
                P();
                UnknownFieldSet.Builder newBuilder = UnknownFieldSet.newBuilder();
                boolean z = false;
                boolean z2 = false;
                while (!z) {
                    try {
                        int readTag = codedInputStream.readTag();
                        if (readTag != 0) {
                            if (readTag == 10) {
                                if (!z2 || !true) {
                                    this.cA = new ArrayList();
                                    z2 |= true;
                                }
                                this.cA.add(codedInputStream.readMessage(View.PARSER, extensionRegistryLite));
                            } else if (!parseUnknownField(codedInputStream, newBuilder, extensionRegistryLite, readTag)) {
                            }
                        }
                        z = true;
                    } catch (InvalidProtocolBufferException e) {
                        throw e.setUnfinishedMessage(this);
                    } catch (IOException e2) {
                        throw new InvalidProtocolBufferException(e2.getMessage()).setUnfinishedMessage(this);
                    } catch (Throwable th) {
                        if (z2 && true) {
                            this.cA = Collections.unmodifiableList(this.cA);
                        }
                        this.aw = newBuilder.build();
                        makeExtensionsImmutable();
                        throw th;
                    }
                }
                if (z2 && true) {
                    this.cA = Collections.unmodifiableList(this.cA);
                }
                this.aw = newBuilder.build();
                makeExtensionsImmutable();
            }

            /* synthetic */ ViewMsg(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite, a aVar) {
                this(codedInputStream, extensionRegistryLite);
            }

            private ViewMsg(GeneratedMessage.Builder builder) {
                super(builder);
                this.ax = -1;
                this.ay = -1;
                this.aw = builder.getUnknownFields();
            }

            /* synthetic */ ViewMsg(GeneratedMessage.Builder builder, a aVar) {
                this(builder);
            }

            private ViewMsg(boolean z) {
                this.ax = -1;
                this.ay = -1;
                this.aw = UnknownFieldSet.getDefaultInstance();
            }

            private void P() {
                this.cA = Collections.emptyList();
            }

            public static ViewMsg getDefaultInstance() {
                return cz;
            }

            public static final Descriptors.Descriptor getDescriptor() {
                return ProtoMsg.aq;
            }

            public static Builder newBuilder() {
                return Builder.bu();
            }

            public static Builder newBuilder(ViewMsg viewMsg) {
                return newBuilder().mergeFrom(viewMsg);
            }

            public static ViewMsg parseDelimitedFrom(InputStream inputStream) {
                return (ViewMsg) PARSER.parseDelimitedFrom(inputStream);
            }

            public static ViewMsg parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) {
                return (ViewMsg) PARSER.parseDelimitedFrom(inputStream, extensionRegistryLite);
            }

            public static ViewMsg parseFrom(ByteString byteString) {
                return (ViewMsg) PARSER.parseFrom(byteString);
            }

            public static ViewMsg parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) {
                return (ViewMsg) PARSER.parseFrom(byteString, extensionRegistryLite);
            }

            public static ViewMsg parseFrom(CodedInputStream codedInputStream) {
                return (ViewMsg) PARSER.parseFrom(codedInputStream);
            }

            public static ViewMsg parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) {
                return (ViewMsg) PARSER.parseFrom(codedInputStream, extensionRegistryLite);
            }

            public static ViewMsg parseFrom(InputStream inputStream) {
                return (ViewMsg) PARSER.parseFrom(inputStream);
            }

            public static ViewMsg parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) {
                return (ViewMsg) PARSER.parseFrom(inputStream, extensionRegistryLite);
            }

            public static ViewMsg parseFrom(byte[] bArr) {
                return (ViewMsg) PARSER.parseFrom(bArr);
            }

            public static ViewMsg parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) {
                return (ViewMsg) PARSER.parseFrom(bArr, extensionRegistryLite);
            }

            public ViewMsg getDefaultInstanceForType() {
                return cz;
            }

            public Parser getParserForType() {
                return PARSER;
            }

            public int getSerializedSize() {
                int i = this.ay;
                if (i != -1) {
                    return i;
                }
                int i2 = 0;
                for (int i3 = 0; i3 < this.cA.size(); i3++) {
                    i2 += CodedOutputStream.computeMessageSize(1, (MessageLite) this.cA.get(i3));
                }
                int serializedSize = i2 + getUnknownFields().getSerializedSize();
                this.ay = serializedSize;
                return serializedSize;
            }

            public final UnknownFieldSet getUnknownFields() {
                return this.aw;
            }

            public View getView(int i) {
                return (View) this.cA.get(i);
            }

            public int getViewCount() {
                return this.cA.size();
            }

            public List getViewList() {
                return this.cA;
            }

            public ViewOrBuilder getViewOrBuilder(int i) {
                return (ViewOrBuilder) this.cA.get(i);
            }

            public List getViewOrBuilderList() {
                return this.cA;
            }

            /* access modifiers changed from: protected */
            public GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
                return ProtoMsg.ar.ensureFieldAccessorsInitialized(ViewMsg.class, Builder.class);
            }

            public final boolean isInitialized() {
                byte b = this.ax;
                if (b == 1) {
                    return true;
                }
                if (b == 0) {
                    return false;
                }
                for (int i = 0; i < getViewCount(); i++) {
                    if (!getView(i).isInitialized()) {
                        this.ax = 0;
                        return false;
                    }
                }
                this.ax = 1;
                return true;
            }

            public Builder newBuilderForType() {
                return newBuilder();
            }

            /* access modifiers changed from: protected */
            public Builder newBuilderForType(GeneratedMessage.BuilderParent builderParent) {
                return new Builder(builderParent, (a) null);
            }

            public Builder toBuilder() {
                return newBuilder(this);
            }

            /* access modifiers changed from: protected */
            public Object writeReplace() {
                return super.writeReplace();
            }

            public void writeTo(CodedOutputStream codedOutputStream) {
                getSerializedSize();
                for (int i = 0; i < this.cA.size(); i++) {
                    codedOutputStream.writeMessage(1, (MessageLite) this.cA.get(i));
                }
                getUnknownFields().writeTo(codedOutputStream);
            }
        }

        public interface ViewMsgOrBuilder extends MessageOrBuilder {
            View getView(int i);

            int getViewCount();

            List getViewList();

            ViewOrBuilder getViewOrBuilder(int i);

            List getViewOrBuilderList();
        }

        public interface ViewOrBuilder extends MessageOrBuilder {
            String getAppChannel();

            ByteString getAppChannelBytes();

            String getAppKey();

            ByteString getAppKeyBytes();

            String getDeviceId();

            ByteString getDeviceIdBytes();

            String getLabel();

            ByteString getLabelBytes();

            String getLogVersionApp();

            ByteString getLogVersionAppBytes();

            String getPackageName();

            ByteString getPackageNameBytes();

            String getPlatform();

            ByteString getPlatformBytes();

            String getSessionId();

            ByteString getSessionIdBytes();

            long getTime();

            String getType();

            ByteString getTypeBytes();

            String getUserId();

            ByteString getUserIdBytes();

            String getViewId();

            ByteString getViewIdBytes();

            ProtoMap getViewMap(int i);

            int getViewMapCount();

            List getViewMapList();

            ProtoMapOrBuilder getViewMapOrBuilder(int i);

            List getViewMapOrBuilderList();

            boolean hasAppChannel();

            boolean hasAppKey();

            boolean hasDeviceId();

            boolean hasLabel();

            boolean hasLogVersionApp();

            boolean hasPackageName();

            boolean hasPlatform();

            boolean hasSessionId();

            boolean hasTime();

            boolean hasType();

            boolean hasUserId();

            boolean hasViewId();
        }

        static {
            av.P();
        }

        private StatsMsg(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) {
            this.ax = -1;
            this.ay = -1;
            P();
            UnknownFieldSet.Builder newBuilder = UnknownFieldSet.newBuilder();
            boolean z = false;
            while (!z) {
                try {
                    int readTag = codedInputStream.readTag();
                    if (readTag == 0 || !parseUnknownField(codedInputStream, newBuilder, extensionRegistryLite, readTag)) {
                        z = true;
                    }
                } catch (InvalidProtocolBufferException e) {
                    throw e.setUnfinishedMessage(this);
                } catch (IOException e2) {
                    throw new InvalidProtocolBufferException(e2.getMessage()).setUnfinishedMessage(this);
                } catch (Throwable th) {
                    this.aw = newBuilder.build();
                    makeExtensionsImmutable();
                    throw th;
                }
            }
            this.aw = newBuilder.build();
            makeExtensionsImmutable();
        }

        /* synthetic */ StatsMsg(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite, a aVar) {
            this(codedInputStream, extensionRegistryLite);
        }

        private StatsMsg(GeneratedMessage.Builder builder) {
            super(builder);
            this.ax = -1;
            this.ay = -1;
            this.aw = builder.getUnknownFields();
        }

        /* synthetic */ StatsMsg(GeneratedMessage.Builder builder, a aVar) {
            this(builder);
        }

        private StatsMsg(boolean z) {
            this.ax = -1;
            this.ay = -1;
            this.aw = UnknownFieldSet.getDefaultInstance();
        }

        private void P() {
        }

        public static StatsMsg getDefaultInstance() {
            return av;
        }

        public static final Descriptors.Descriptor getDescriptor() {
            return ProtoMsg.O;
        }

        public static Builder newBuilder() {
            return Builder.S();
        }

        public static Builder newBuilder(StatsMsg statsMsg) {
            return newBuilder().mergeFrom(statsMsg);
        }

        public static StatsMsg parseDelimitedFrom(InputStream inputStream) {
            return (StatsMsg) PARSER.parseDelimitedFrom(inputStream);
        }

        public static StatsMsg parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) {
            return (StatsMsg) PARSER.parseDelimitedFrom(inputStream, extensionRegistryLite);
        }

        public static StatsMsg parseFrom(ByteString byteString) {
            return (StatsMsg) PARSER.parseFrom(byteString);
        }

        public static StatsMsg parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) {
            return (StatsMsg) PARSER.parseFrom(byteString, extensionRegistryLite);
        }

        public static StatsMsg parseFrom(CodedInputStream codedInputStream) {
            return (StatsMsg) PARSER.parseFrom(codedInputStream);
        }

        public static StatsMsg parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) {
            return (StatsMsg) PARSER.parseFrom(codedInputStream, extensionRegistryLite);
        }

        public static StatsMsg parseFrom(InputStream inputStream) {
            return (StatsMsg) PARSER.parseFrom(inputStream);
        }

        public static StatsMsg parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) {
            return (StatsMsg) PARSER.parseFrom(inputStream, extensionRegistryLite);
        }

        public static StatsMsg parseFrom(byte[] bArr) {
            return (StatsMsg) PARSER.parseFrom(bArr);
        }

        public static StatsMsg parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) {
            return (StatsMsg) PARSER.parseFrom(bArr, extensionRegistryLite);
        }

        public StatsMsg getDefaultInstanceForType() {
            return av;
        }

        public Parser getParserForType() {
            return PARSER;
        }

        public int getSerializedSize() {
            int i = this.ay;
            if (i != -1) {
                return i;
            }
            int serializedSize = getUnknownFields().getSerializedSize() + 0;
            this.ay = serializedSize;
            return serializedSize;
        }

        public final UnknownFieldSet getUnknownFields() {
            return this.aw;
        }

        /* access modifiers changed from: protected */
        public GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
            return ProtoMsg.P.ensureFieldAccessorsInitialized(StatsMsg.class, Builder.class);
        }

        public final boolean isInitialized() {
            byte b = this.ax;
            if (b == 1) {
                return true;
            }
            if (b == 0) {
                return false;
            }
            this.ax = 1;
            return true;
        }

        public Builder newBuilderForType() {
            return newBuilder();
        }

        /* access modifiers changed from: protected */
        public Builder newBuilderForType(GeneratedMessage.BuilderParent builderParent) {
            return new Builder(builderParent, (a) null);
        }

        public Builder toBuilder() {
            return newBuilder(this);
        }

        /* access modifiers changed from: protected */
        public Object writeReplace() {
            return super.writeReplace();
        }

        public void writeTo(CodedOutputStream codedOutputStream) {
            getSerializedSize();
            getUnknownFields().writeTo(codedOutputStream);
        }
    }

    public interface StatsMsgOrBuilder extends MessageOrBuilder {
    }

    static {
        Descriptors.FileDescriptor.internalBuildGeneratedFileFrom(new String[]{"\n\u0011mobilestats.proto\u0012\u000bmobilestats\"\u001b\n\bStatsMsg\u001a&\n\bProtoMap\u0012\u000b\n\u0003key\u0018\u0001 \u0002(\t\u0012\r\n\u0005value\u0018\u0002 \u0002(\t\u001a»\u0003\n\u0004Page\u0012\f\n\u0004type\u0018\u0001 \u0002(\t\u0012\u0012\n\nsession_id\u0018\u0002 \u0001(\t\u0012\u000f\n\u0007user_id\u0018\u0003 \u0001(\t\u0012\u0011\n\tdevice_id\u0018\u0004 \u0001(\t\u0012\u0013\n\u000bstart_mills\u0018\u0005 \u0001(\u0003\u0012\u0011\n\tend_mills\u0018\u0006 \u0001(\u0003\u0012\u0010\n\bduration\u0018\u0007 \u0001(\u0003\u0012\u000f\n\u0007page_id\u0018\b \u0001(\t\u00120\n\bpage_map\u0018\t \u0003(\u000b2\u001e.mobilestats.StatsMsg.ProtoMap\u0012\u0010\n\bplatform\u0018\n \u0001(\t\u0012\u000f\n\u0007app_key\u0018\u000b \u0001(\t\u0012\u0013\n\u000bapp_channel\u0018\f \u0001(\t\u0012\u0014\n\fpackage_name\u0018\r \u0001(\t\u0012\u0017\n\u000flog_version_app\u0018\u000e \u0001(\t\u0012\u0014\n\fvers", "ion_code\u0018\u000f \u0001(\u0005\u0012\u0014\n\fversion_name\u0018\u0010 \u0001(\t\u0012\u0012\n\nsdk_verson\u0018\u0011 \u0001(\t\u0012\u0012\n\nos_version\u0018\u0012 \u0001(\t\u0012\u0013\n\u000bdevice_name\u0018\u0013 \u0001(\t\u0012\u000f\n\u0007mcc_mnc\u0018\u0014 \u0001(\t\u0012\u000f\n\u0007cell_id\u0018\u0015 \u0001(\t\u001a3\n\u0007PageMsg\u0012(\n\u0004page\u0018\u0001 \u0003(\u000b2\u001a.mobilestats.StatsMsg.Page\u001a\u0002\n\u0005Event\u0012\f\n\u0004type\u0018\u0001 \u0002(\t\u0012\u0012\n\nsession_id\u0018\u0002 \u0001(\t\u0012\u000f\n\u0007user_id\u0018\u0003 \u0001(\t\u0012\u0011\n\tdevice_id\u0018\u0004 \u0001(\t\u0012\f\n\u0004time\u0018\u0005 \u0001(\u0003\u0012\u0010\n\bevent_id\u0018\u0006 \u0001(\t\u0012\r\n\u0005label\u0018\u0007 \u0001(\t\u00121\n\tevent_map\u0018\b \u0003(\u000b2\u001e.mobilestats.StatsMsg.ProtoMap\u0012\u0010\n\bplatform\u0018\t \u0001(\t\u0012\u000f\n\u0007app_key\u0018\n \u0001(\t\u0012\u0013\n", "\u000bapp_channel\u0018\u000b \u0001(\t\u0012\u0014\n\fpackage_name\u0018\f \u0001(\t\u0012\u0017\n\u000flog_version_app\u0018\r \u0001(\t\u001a6\n\bEventMsg\u0012*\n\u0005event\u0018\u0001 \u0003(\u000b2\u001b.mobilestats.StatsMsg.Event\u001aû\u0005\n\u0006Client\u0012\f\n\u0004type\u0018\u0001 \u0002(\t\u0012\u000f\n\u0007app_key\u0018\u0002 \u0001(\t\u0012\u0013\n\u000bapp_channel\u0018\u0003 \u0001(\t\u0012\u0012\n\nsession_id\u0018\u0004 \u0001(\t\u0012\u0011\n\tdevice_id\u0018\u0005 \u0001(\t\u0012\u000f\n\u0007user_id\u0018\u0006 \u0001(\t\u0012\u0014\n\fversion_code\u0018\u0007 \u0001(\u0005\u0012\u0014\n\fversion_name\u0018\b \u0001(\t\u0012\u0012\n\nsdk_verson\u0018\t \u0001(\t\u0012\u0012\n\nos_version\u0018\n \u0001(\t\u0012\u0010\n\bplatform\u0018\u000b \u0001(\t\u0012\u0010\n\blanguage\u0018\f \u0001(\t\u0012\u0012\n\nresolution\u0018\r \u0001(\t\u0012\u0013\n\u000bdevice_name\u0018\u000e \u0001(", "\t\u0012\u0013\n\u000bmac_address\u0018\u000f \u0001(\t\u0012\u0015\n\rhas_bluetooth\u0018\u0010 \u0001(\b\u0012\u0010\n\bhas_wifi\u0018\u0011 \u0001(\b\u0012\u0013\n\u000bhas_gravity\u0018\u0012 \u0001(\b\u0012\u000f\n\u0007mcc_mnc\u0018\u0013 \u0001(\t\u0012\u000f\n\u0007cell_id\u0018\u0014 \u0001(\t\u0012\u000b\n\u0003lac\u0018\u0015 \u0001(\t\u0012\u000f\n\u0007has_gps\u0018\u0016 \u0001(\b\u0012\u0010\n\blatitude\u0018\u0017 \u0001(\t\u0012\u0011\n\tlongitude\u0018\u0018 \u0001(\t\u0012\u0012\n\nphone_type\u0018\u0019 \u0001(\t\u0012\f\n\u0004time\u0018\u001a \u0001(\u0003\u00122\n\nclient_map\u0018\u001b \u0003(\u000b2\u001e.mobilestats.StatsMsg.ProtoMap\u00122\n\u0007network\u0018\u001c \u0001(\u000e2!.mobilestats.StatsMsg.NetworkType\u0012\u0016\n\u000enetwork_detail\u0018\u001d \u0001(\t\u0012\u0014\n\fmiui_version\u0018\u001e \u0001(\t\u0012\u000b\n\u0003key\u0018\u001f \u0001(\t\u0012\u0014\n\fpackage_name\u0018 ", " \u0001(\t\u0012\f\n\u0004imei\u0018! \u0001(\t\u0012\u0017\n\u000flog_version_app\u0018\" \u0001(\t\u0012\u0014\n\fsim_operator\u0018# \u0001(\t\u0012\u0014\n\fchannel_info\u0018$ \u0001(\t\u001a9\n\tClientMsg\u0012,\n\u0006client\u0018\u0001 \u0003(\u000b2\u001c.mobilestats.StatsMsg.Client\u001a\u0001\n\u000eClientResponse\u0012\f\n\u0004type\u0018\u0001 \u0002(\t\u00126\n\bstrategy\u0018\u0002 \u0002(\u000e2$.mobilestats.StatsMsg.ReportStrategy\u0012\u0010\n\binterval\u0018\u0003 \u0001(\r\u0012\u000f\n\u0007is_wifi\u0018\u0004 \u0001(\b\u0012\u000b\n\u0003key\u0018\u0005 \u0001(\t\u001a®\u0003\n\nProtoError\u0012\f\n\u0004type\u0018\u0001 \u0002(\t\u0012\u000f\n\u0007app_key\u0018\u0002 \u0001(\t\u0012\u0013\n\u000bapp_channel\u0018\u0003 \u0001(\t\u0012\u0012\n\nsession_id\u0018\u0004 \u0001(\t\u0012\u0010\n\bactivity\u0018\u0005 \u0001(\t\u0012\f\n\u0004time\u0018\u0006 \u0001", "(\u0003\u0012\u0011\n\tdevice_id\u0018\u0007 \u0001(\t\u0012\u0011\n\texception\u0018\b \u0001(\t\u0012\u0018\n\u0010exception_detail\u0018\t \u0001(\t\u0012\u0014\n\fnetwork_type\u0018\n \u0001(\t\u0012\u000f\n\u0007network\u0018\u000b \u0001(\t\u0012\u000b\n\u0003url\u0018\f \u0001(\t\u00121\n\terror_map\u0018\r \u0003(\u000b2\u001e.mobilestats.StatsMsg.ProtoMap\u0012\u0014\n\fversion_code\u0018\u000e \u0001(\u0005\u0012\u0014\n\fversion_name\u0018\u000f \u0001(\t\u0012\u000f\n\u0007user_id\u0018\u0010 \u0001(\t\u0012\u0013\n\u000bdevice_name\u0018\u0011 \u0001(\t\u0012\u0010\n\bplatform\u0018\u0012 \u0001(\t\u0012\u0014\n\fpackage_name\u0018\u0013 \u0001(\t\u0012\u0017\n\u000flog_version_app\u0018\u0014 \u0001(\t\u001a;\n\bErrorMsg\u0012/\n\u0005error\u0018\u0001 \u0003(\u000b2 .mobilestats.StatsMsg.ProtoError\u001a¡\u0003\n\u0005Crash\u0012\f\n\u0004type\u0018\u0001 \u0002(", "\t\u0012\u000f\n\u0007app_key\u0018\u0002 \u0001(\t\u0012\u0013\n\u000bapp_channel\u0018\u0003 \u0001(\t\u0012\u0012\n\nsession_id\u0018\u0004 \u0001(\t\u0012\f\n\u0004time\u0018\u0005 \u0001(\u0003\u0012\u0011\n\tdevice_id\u0018\u0006 \u0001(\t\u0012\u0011\n\tthrowable\u0018\u0007 \u0001(\t\u00121\n\tcrash_map\u0018\b \u0003(\u000b2\u001e.mobilestats.StatsMsg.ProtoMap\u0012\u0014\n\fversion_code\u0018\t \u0001(\u0005\u0012\u0014\n\fversion_name\u0018\n \u0001(\t\u0012\u000f\n\u0007user_id\u0018\u000b \u0001(\t\u0012\u0013\n\u000bdevice_name\u0018\f \u0001(\t\u0012\u0010\n\bplatform\u0018\r \u0001(\t\u0012\u0014\n\fmemory_class\u0018\u000e \u0001(\t\u0012\u001a\n\u0012large_memory_class\u0018\u000f \u0001(\t\u0012\u0011\n\tcrash_num\u0018\u0010 \u0001(\u0005\u0012\u0011\n\tcrash_md5\u0018\u0011 \u0001(\t\u0012\u0014\n\fpackage_name\u0018\u0012 \u0001(\t\u0012\u0017\n\u000flog_version_app\u0018\u0013 \u0001(\t\u001a6\n", "\bCrashMsg\u0012*\n\u0005crash\u0018\u0001 \u0003(\u000b2\u001b.mobilestats.StatsMsg.Crash\u001a\u0002\n\u0004View\u0012\f\n\u0004type\u0018\u0001 \u0002(\t\u0012\u0012\n\nsession_id\u0018\u0002 \u0001(\t\u0012\u000f\n\u0007user_id\u0018\u0003 \u0001(\t\u0012\u0011\n\tdevice_id\u0018\u0004 \u0001(\t\u0012\f\n\u0004time\u0018\u0005 \u0001(\u0003\u0012\u000f\n\u0007view_id\u0018\u0006 \u0001(\t\u0012\r\n\u0005label\u0018\u0007 \u0001(\t\u00120\n\bview_map\u0018\b \u0003(\u000b2\u001e.mobilestats.StatsMsg.ProtoMap\u0012\u0010\n\bplatform\u0018\t \u0001(\t\u0012\u000f\n\u0007app_key\u0018\n \u0001(\t\u0012\u0013\n\u000bapp_channel\u0018\u000b \u0001(\t\u0012\u0014\n\fpackage_name\u0018\f \u0001(\t\u0012\u0017\n\u000flog_version_app\u0018\r \u0001(\t\u001a3\n\u0007ViewMsg\u0012(\n\u0004view\u0018\u0001 \u0003(\u000b2\u001a.mobilestats.StatsMsg.View\u001aR\n\bResponse\u0012\f\n\u0004", "code\u0018\u0001 \u0002(\u0005\u0012\u000b\n\u0003msg\u0018\u0002 \u0002(\t\u0012+\n\u0003map\u0018\u0003 \u0001(\u000b2\u001e.mobilestats.StatsMsg.ProtoMap\"W\n\u000eReportStrategy\u0012\r\n\tREAL_TIME\u0010\u0000\u0012\r\n\tAPP_START\u0010\u0001\u0012\u0010\n\fONCE_PER_DAY\u0010\u0002\u0012\u0015\n\u0011SET_TIME_INTERVAL\u0010\u0003\"\u0001\n\u000bNetworkType\u0012\u0013\n\u000fNETWORK_UNKNOWN\u0010\u0000\u0012\u0010\n\fNETWORK_WIFI\u0010\u0001\u0012\u000e\n\nNETWORK_2G\u0010\u0002\u0012\u000e\n\nNETWORK_3G\u0010\u0003\u0012\u000e\n\nNETWORK_4G\u0010\u0004\u0012\u0015\n\u0011NETWORK_BLUETOOTH\u0010\u0005\u0012\u0012\n\u000eNETWORK_OTHERS\u0010\u0006B'\n\u001bcom.xiaomi.mobilestats.dataB\bProtoMsg"}, new Descriptors.FileDescriptor[0], new a());
    }

    private ProtoMsg() {
    }

    public static Descriptors.FileDescriptor getDescriptor() {
        return au;
    }

    public static void registerAllExtensions(ExtensionRegistry extensionRegistry) {
    }
}
