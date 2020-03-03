package com.xiaomi.mobilestats.common;

import com.xiaomi.mobilestats.data.ProtoMsg;
import com.xiaomi.mobilestats.object.Msg;
import java.io.File;
import java.io.FileInputStream;

public class ProtoUtil {
    private static String readFile(String str) {
        String str2;
        try {
            File file = new File(str);
            if (!file.exists()) {
                return "";
            }
            FileInputStream fileInputStream = new FileInputStream(file);
            StringBuffer stringBuffer = new StringBuffer();
            byte[] bArr = new byte[1014];
            while (true) {
                int read = fileInputStream.read(bArr);
                if (read == -1) {
                    break;
                }
                stringBuffer.append(new String(bArr, 0, read));
            }
            str2 = stringBuffer.toString();
            try {
                fileInputStream.close();
            } catch (Exception e) {
                e = e;
            }
            return str2;
        } catch (Exception e2) {
            e = e2;
            str2 = "";
            e.printStackTrace();
            return str2;
        }
    }

    public static Msg readProtoInfoFromFile(int i, String str) {
        Msg msg;
        byte[] encodeBytesData;
        byte[] unEncrpt;
        ProtoMsg.StatsMsg.PageMsg parseFrom;
        byte[] unEncrpt2;
        ProtoMsg.StatsMsg.EventMsg parseFrom2;
        byte[] unEncrpt3;
        ProtoMsg.StatsMsg.ClientMsg parseFrom3;
        byte[] unEncrpt4;
        ProtoMsg.StatsMsg.ErrorMsg parseFrom4;
        byte[] unEncrpt5;
        ProtoMsg.StatsMsg.CrashMsg parseFrom5;
        ProtoMsg.StatsMsg.Crash crash;
        byte[] unEncrpt6;
        ProtoMsg.StatsMsg.ViewMsg parseFrom6;
        Msg msg2 = null;
        if (StrUtil.isEmpty(str)) {
            return null;
        }
        try {
            if (new File(str).length() > 16384) {
                return null;
            }
            String readFile = readFile(str);
            if (StrUtil.isEmpty(readFile)) {
                return null;
            }
            String[] split = readFile.split("\n");
            int i2 = 5;
            if (i != 5) {
                i2 = 7;
                if (i != 7) {
                    i2 = 9;
                    if (i != 9) {
                        i2 = 11;
                        if (i != 11) {
                            if (i != 13) {
                                i2 = 17;
                                if (i == 17) {
                                    ProtoMsg.StatsMsg.ViewMsg.Builder newBuilder = ProtoMsg.StatsMsg.ViewMsg.newBuilder();
                                    for (int i3 = 0; i3 < split.length; i3++) {
                                        if (!(StrUtil.isEmpty(split[i3]) || (unEncrpt6 = AESUtils.unEncrpt(split[i3])) == null || (parseFrom6 = ProtoMsg.StatsMsg.ViewMsg.parseFrom(unEncrpt6)) == null)) {
                                            newBuilder.addView(parseFrom6.getView(0));
                                        }
                                    }
                                    ProtoMsg.StatsMsg.ViewMsg build = newBuilder.build();
                                    if (build != null) {
                                        if (build.getViewCount() > 0) {
                                            encodeBytesData = StrUtil.encodeBytesData(build.toByteArray());
                                        } else {
                                            msg = new Msg();
                                            msg.setFlag(true);
                                            return msg;
                                        }
                                    }
                                }
                            } else {
                                ProtoMsg.StatsMsg.CrashMsg.Builder newBuilder2 = ProtoMsg.StatsMsg.CrashMsg.newBuilder();
                                for (int i4 = 0; i4 < split.length; i4++) {
                                    if (!(StrUtil.isEmpty(split[i4]) || (unEncrpt5 = AESUtils.unEncrpt(split[i4])) == null || (parseFrom5 = ProtoMsg.StatsMsg.CrashMsg.parseFrom(unEncrpt5)) == null || (crash = parseFrom5.getCrash(0)) == null)) {
                                        int crashNumByMD5 = CrashHandler.getInstance().getCrashNumByMD5(crash.getCrashMd5());
                                        if (crashNumByMD5 > 1) {
                                            crash = EncodeProtoUtil.updateCrash(crash, crashNumByMD5);
                                        }
                                        newBuilder2.addCrash(crash);
                                    }
                                }
                                ProtoMsg.StatsMsg.CrashMsg build2 = newBuilder2.build();
                                if (build2 != null) {
                                    if (build2.getCrashCount() > 0) {
                                        msg = NetworkUtil.postProtoInfo(13, StrUtil.encodeBytesData(build2.toByteArray()));
                                    } else {
                                        msg = new Msg();
                                        try {
                                            msg.setFlag(true);
                                        } catch (Exception e) {
                                            msg2 = msg;
                                            e = e;
                                        }
                                    }
                                    msg2 = msg;
                                }
                                if (msg2 != null) {
                                    if (msg2.isFlag()) {
                                        CrashHandler.getInstance().clearCrashMD5List();
                                    }
                                }
                            }
                            return msg2;
                        }
                        ProtoMsg.StatsMsg.ErrorMsg.Builder newBuilder3 = ProtoMsg.StatsMsg.ErrorMsg.newBuilder();
                        for (int i5 = 0; i5 < split.length; i5++) {
                            if (!(StrUtil.isEmpty(split[i5]) || (unEncrpt4 = AESUtils.unEncrpt(split[i5])) == null || (parseFrom4 = ProtoMsg.StatsMsg.ErrorMsg.parseFrom(unEncrpt4)) == null)) {
                                newBuilder3.addError(parseFrom4.getError(0));
                            }
                        }
                        ProtoMsg.StatsMsg.ErrorMsg build3 = newBuilder3.build();
                        if (build3 != null) {
                            if (build3.getErrorCount() > 0) {
                                encodeBytesData = StrUtil.encodeBytesData(build3.toByteArray());
                            } else {
                                msg = new Msg();
                                msg.setFlag(true);
                                return msg;
                            }
                        }
                        return msg2;
                    }
                    ProtoMsg.StatsMsg.ClientMsg.Builder newBuilder4 = ProtoMsg.StatsMsg.ClientMsg.newBuilder();
                    for (int i6 = 0; i6 < split.length; i6++) {
                        if (!(StrUtil.isEmpty(split[i6]) || (unEncrpt3 = AESUtils.unEncrpt(split[i6])) == null || (parseFrom3 = ProtoMsg.StatsMsg.ClientMsg.parseFrom(unEncrpt3)) == null)) {
                            newBuilder4.addClient(parseFrom3.getClient(0));
                        }
                    }
                    ProtoMsg.StatsMsg.ClientMsg build4 = newBuilder4.build();
                    if (build4 != null) {
                        if (build4.getClientCount() > 0) {
                            encodeBytesData = StrUtil.encodeBytesData(build4.toByteArray());
                        } else {
                            msg = new Msg();
                            msg.setFlag(true);
                            return msg;
                        }
                    }
                    return msg2;
                }
                ProtoMsg.StatsMsg.EventMsg.Builder newBuilder5 = ProtoMsg.StatsMsg.EventMsg.newBuilder();
                for (int i7 = 0; i7 < split.length; i7++) {
                    if (!(StrUtil.isEmpty(split[i7]) || (unEncrpt2 = AESUtils.unEncrpt(split[i7])) == null || (parseFrom2 = ProtoMsg.StatsMsg.EventMsg.parseFrom(unEncrpt2)) == null)) {
                        newBuilder5.addEvent(parseFrom2.getEvent(0));
                    }
                }
                ProtoMsg.StatsMsg.EventMsg build5 = newBuilder5.build();
                if (build5 != null) {
                    if (build5.getEventCount() > 0) {
                        encodeBytesData = StrUtil.encodeBytesData(build5.toByteArray());
                    } else {
                        msg = new Msg();
                        msg.setFlag(true);
                        return msg;
                    }
                }
                return msg2;
            }
            ProtoMsg.StatsMsg.PageMsg.Builder newBuilder6 = ProtoMsg.StatsMsg.PageMsg.newBuilder();
            for (int i8 = 0; i8 < split.length; i8++) {
                if (!(StrUtil.isEmpty(split[i8]) || (unEncrpt = AESUtils.unEncrpt(split[i8])) == null || (parseFrom = ProtoMsg.StatsMsg.PageMsg.parseFrom(unEncrpt)) == null)) {
                    newBuilder6.addPage(parseFrom.getPage(0));
                }
            }
            ProtoMsg.StatsMsg.PageMsg build6 = newBuilder6.build();
            if (build6 != null) {
                if (build6.getPageCount() > 0) {
                    encodeBytesData = StrUtil.encodeBytesData(build6.toByteArray());
                } else {
                    msg = new Msg();
                    msg.setFlag(true);
                    return msg;
                }
            }
            return msg2;
            return NetworkUtil.postProtoInfo(i2, encodeBytesData);
        } catch (Exception e2) {
            e = e2;
            e.printStackTrace();
            return msg2;
        }
    }
}
