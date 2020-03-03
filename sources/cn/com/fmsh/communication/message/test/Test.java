package cn.com.fmsh.communication.message.test;

import cn.com.fmsh.FM_Exception;
import cn.com.fmsh.communication.message.IMessage;
import cn.com.fmsh.communication.message.IMessageHandler;
import cn.com.fmsh.communication.message.ITag;
import cn.com.fmsh.communication.message.MessageHandleFactory;
import cn.com.fmsh.communication.message.exception.FMCommunicationMessageException;
import cn.com.fmsh.tsm.business.constants.Constants;
import cn.com.fmsh.util.FM_Bytes;
import cn.com.fmsh.util.Util4Java;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.PrintStream;

public class Test {
    private IMessageHandler messageHandler = null;

    public void load() {
        this.messageHandler = MessageHandleFactory.getMessageHandler();
        try {
            this.messageHandler.loadDefine(new FileInputStream(new File("E:/document/appsys/05_application_sw/FM323-面向NFC终端的网上业务接入及数据交换系统/12_temp/技术方案/设计文档/配置文件/message_source.xml")));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (FMCommunicationMessageException e2) {
            e2.printStackTrace();
        }
    }

    public void createMessageTest4Byte() {
        IMessage iMessage;
        System.out.println("createMessageTest===================================");
        byte[] bArr = new byte[58];
        bArr[0] = 16;
        bArr[1] = Framer.ENTER_FRAME_PREFIX;
        bArr[2] = 2;
        bArr[3] = 20;
        bArr[8] = 1;
        bArr[13] = 1;
        bArr[18] = 1;
        bArr[23] = 1;
        bArr[24] = 3;
        bArr[25] = 32;
        bArr[26] = 1;
        bArr[27] = 2;
        bArr[28] = 3;
        bArr[29] = 4;
        bArr[30] = 2;
        bArr[31] = 2;
        bArr[32] = 3;
        bArr[33] = 4;
        bArr[34] = 1;
        bArr[35] = 2;
        bArr[36] = 3;
        bArr[37] = 4;
        bArr[38] = 2;
        bArr[39] = 2;
        bArr[40] = 3;
        bArr[41] = 4;
        bArr[42] = 1;
        bArr[43] = 2;
        bArr[44] = 3;
        bArr[45] = 4;
        bArr[46] = 2;
        bArr[47] = 2;
        bArr[48] = 3;
        bArr[49] = 4;
        bArr[50] = 1;
        bArr[51] = 2;
        bArr[52] = 3;
        bArr[53] = 4;
        bArr[54] = 2;
        bArr[55] = 2;
        bArr[56] = 3;
        bArr[57] = 4;
        try {
            iMessage = this.messageHandler.createMessage(bArr);
        } catch (FM_Exception e) {
            System.out.println(Util4Java.getExceptionInfo(e));
            iMessage = null;
        }
        if (iMessage == null) {
            System.out.println("createMessageTest fail");
        }
        System.out.println(iMessage.getCode());
        int tagCount = iMessage.getTagCount();
        PrintStream printStream = System.out;
        printStream.println("count:" + tagCount);
        try {
            ITag tag4Index = iMessage.getTag4Index(0);
            PrintStream printStream2 = System.out;
            printStream2.println("tagID:" + tag4Index.getId());
        } catch (FMCommunicationMessageException e2) {
            e2.printStackTrace();
        }
        boolean isValid = iMessage.isValid();
        PrintStream printStream3 = System.out;
        printStream3.println("isValid:" + isValid);
    }

    public void createMessageTest() {
        System.out.println("createMessageTest===================================");
        byte[] bArr = new byte[58];
        bArr[0] = 16;
        bArr[1] = Framer.ENTER_FRAME_PREFIX;
        bArr[2] = 2;
        bArr[3] = 20;
        bArr[8] = 1;
        bArr[13] = 1;
        bArr[18] = 1;
        bArr[23] = 1;
        bArr[24] = 3;
        bArr[25] = 32;
        bArr[26] = 1;
        bArr[27] = 2;
        bArr[28] = 3;
        bArr[29] = 4;
        bArr[30] = 2;
        bArr[31] = 2;
        bArr[32] = 3;
        bArr[33] = 4;
        bArr[34] = 1;
        bArr[35] = 2;
        bArr[36] = 3;
        bArr[37] = 4;
        bArr[38] = 2;
        bArr[39] = 2;
        bArr[40] = 3;
        bArr[41] = 4;
        bArr[42] = 1;
        bArr[43] = 2;
        bArr[44] = 3;
        bArr[45] = 4;
        bArr[46] = 2;
        bArr[47] = 2;
        bArr[48] = 3;
        bArr[49] = 4;
        bArr[50] = 1;
        bArr[51] = 2;
        bArr[52] = 3;
        bArr[53] = 4;
        bArr[54] = 2;
        bArr[55] = 2;
        bArr[56] = 3;
        bArr[57] = 4;
        IMessage createMessage = this.messageHandler.createMessage(1021);
        ITag createTag = this.messageHandler.createTag((byte) 2);
        ITag createTag2 = this.messageHandler.createTag((byte) 3);
        try {
            createTag.addValue("tigertigertigertiger");
            createTag2.addValue("tigertigertigertigertigertiger12");
        } catch (FMCommunicationMessageException e) {
            e.printStackTrace();
        }
        boolean isValid = createTag.isValid();
        PrintStream printStream = System.out;
        printStream.println("tag02isValid:" + isValid);
        boolean isValid2 = createTag2.isValid();
        PrintStream printStream2 = System.out;
        printStream2.println("tag03isValid:" + isValid2);
        try {
            createMessage.addTag(createTag);
            createMessage.addTag(createTag2);
        } catch (FMCommunicationMessageException e2) {
            e2.printStackTrace();
        }
        System.out.println(createMessage.getCode());
        int tagCount = createMessage.getTagCount();
        PrintStream printStream3 = System.out;
        printStream3.println("count:" + tagCount);
        try {
            ITag tag4Index = createMessage.getTag4Index(0);
            PrintStream printStream4 = System.out;
            printStream4.println("tagID:" + tag4Index.getId());
        } catch (FMCommunicationMessageException e3) {
            e3.printStackTrace();
        }
        boolean isValid3 = createMessage.isValid();
        PrintStream printStream5 = System.out;
        printStream5.println("isValid:" + isValid3);
        try {
            byte[] bytes = createMessage.toBytes();
            PrintStream printStream6 = System.out;
            printStream6.println("message:" + FM_Bytes.bytesToHexString(bytes));
        } catch (FMCommunicationMessageException e4) {
            System.out.println(Util4Java.getExceptionInfo(e4));
        }
    }

    public void createTagTest() {
        try {
            MessageHandleFactory.getMessageHandler().createTag((byte) 1).addValue(new byte[]{2});
        } catch (FMCommunicationMessageException e) {
            e.printStackTrace();
        }
    }

    public void test() {
        try {
            IMessage createMessage = this.messageHandler.createMessage(FM_Bytes.hexStringToBytes("8821B103FFFFFFBA06313131313131B3030A0A0AB6030B0B0BB70732323232323232B806333333333333B400"));
            System.out.println(createMessage.getCode());
            int tagCount = createMessage.getTagCount();
            PrintStream printStream = System.out;
            printStream.println("count:" + tagCount);
            System.out.println(FM_Bytes.bytesToHexString(createMessage.getTag4Id(-79).getBytesVal()));
        } catch (FMCommunicationMessageException e) {
            e.printStackTrace();
        }
    }

    public void create() {
        FM_Bytes.hexStringToBytes("8821B103FFFFFFBA06313131313131B3030A0A0AB6030B0B0BB70732323232323232B806333333333333B400");
        try {
            IMessage createMessage = this.messageHandler.createMessage(9000);
            ITag createTag = this.messageHandler.createTag((byte) Constants.TagName.BUSINESS_HANDLE_RESULT);
            createTag.addValue(new byte[1]);
            createMessage.addTag(createTag);
            System.out.println(FM_Bytes.bytesToHexString(createMessage.toBytes()));
        } catch (FMCommunicationMessageException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] strArr) {
        new Test().load();
    }
}
