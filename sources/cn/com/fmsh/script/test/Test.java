package cn.com.fmsh.script.test;

import cn.com.fmsh.communication.message.IMessageHandler;
import cn.com.fmsh.communication.message.ITag;
import cn.com.fmsh.communication.message.MessageHandleFactory;
import cn.com.fmsh.communication.message.exception.FMCommunicationMessageException;
import cn.com.fmsh.script.ApduFilterDataInit;
import cn.com.fmsh.script.ApduHandler;
import cn.com.fmsh.script.ScriptHandler;
import cn.com.fmsh.script.ScriptHandlerFactory;
import cn.com.fmsh.script.bean.ApduReponseList;
import cn.com.fmsh.script.bean.ApduRequestList;
import cn.com.fmsh.script.bean.ApduResponse;
import cn.com.fmsh.script.constants.ScriptToolsConst;
import cn.com.fmsh.script.core.FilterPolicy;
import cn.com.fmsh.script.exception.FMScriptHandleException;
import cn.com.fmsh.tsm.business.constants.Constants;
import cn.com.fmsh.util.FM_Bytes;
import cn.com.fmsh.util.Util4Java;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

public class Test {
    private IMessageHandler messageHandler = MessageHandleFactory.getMessageHandler();

    public void load() {
        this.messageHandler = MessageHandleFactory.getMessageHandler();
        try {
            try {
                this.messageHandler.loadDefine(new FileInputStream(new File("C:/temp/message-tag.xml")));
            } catch (FMCommunicationMessageException e) {
                e.printStackTrace();
            }
        } catch (FileNotFoundException e2) {
            e2.printStackTrace();
        }
    }

    private class ApduHandler4Test implements ApduHandler {
        public void close() {
        }

        public boolean connect() {
            return true;
        }

        public boolean isConnect() {
            return false;
        }

        private ApduHandler4Test() {
        }

        /* synthetic */ ApduHandler4Test(Test test, ApduHandler4Test apduHandler4Test) {
            this();
        }

        public byte[] transceive(byte[] bArr) throws FMScriptHandleException {
            byte[] bArr2 = new byte[2];
            bArr2[0] = Constants.TagName.SYSTEM_VERSION;
            return bArr2;
        }

        public ApduHandler.ApduHandlerType getApduHandlerType() {
            return ApduHandler.ApduHandlerType.OPEN_MOBILE;
        }

        public boolean open(byte[] bArr) {
            PrintStream printStream = System.out;
            printStream.println("open aid:" + FM_Bytes.bytesToHexString(bArr));
            return true;
        }
    }

    private class FilterDataInit4Test implements ApduFilterDataInit {
        private FilterDataInit4Test() {
        }

        /* synthetic */ FilterDataInit4Test(Test test, FilterDataInit4Test filterDataInit4Test) {
            this();
        }

        public List<FilterPolicy> getFilterPolicies() {
            ArrayList arrayList = new ArrayList();
            FilterPolicy filterPolicy = new FilterPolicy();
            filterPolicy.setCla((byte) 0);
            filterPolicy.setIns(ScriptToolsConst.TagName.CommandMultiple);
            filterPolicy.addFilterData(new byte[0]);
            arrayList.add(filterPolicy);
            return arrayList;
        }
    }

    public void testSingle() {
        ApduReponseList apduReponseList;
        ScriptHandler scriptHandler = ScriptHandlerFactory.getInstance().getScriptHandler(new ApduHandler4Test(this, (ApduHandler4Test) null));
        scriptHandler.setApduFilterDataInit(new FilterDataInit4Test(this, (FilterDataInit4Test) null));
        ApduRequestList apduRequestList = new ApduRequestList();
        System.out.println("=======================test==============================================");
        ITag createTag = this.messageHandler.createTag((byte) ScriptToolsConst.TagName.CommandSingle);
        try {
            byte[] bArr = new byte[9];
            bArr[0] = 1;
            bArr[1] = 5;
            bArr[3] = ScriptToolsConst.TagName.CommandMultiple;
            bArr[7] = Constants.TagName.SYSTEM_VERSION;
            createTag.addValue(bArr);
        } catch (FMCommunicationMessageException e) {
            e.printStackTrace();
        }
        try {
            apduRequestList.fromTag(createTag);
        } catch (FMScriptHandleException e2) {
            System.out.println(Util4Java.getExceptionInfo(e2));
        } catch (FMCommunicationMessageException e3) {
            System.out.println(Util4Java.getExceptionInfo(e3));
        }
        try {
            apduReponseList = scriptHandler.execute(apduRequestList);
        } catch (FMScriptHandleException e4) {
            e4.printStackTrace();
            apduReponseList = null;
        }
        ApduResponse[] apduResponse = apduReponseList.getApduResponse();
        System.out.println("apduReponses.length:" + apduResponse.length);
        for (ApduResponse result : apduResponse) {
            System.out.println(FM_Bytes.bytesToHexString(result.getResult()));
        }
    }

    public void testM() {
        ApduReponseList apduReponseList;
        ScriptHandler scriptHandler = ScriptHandlerFactory.getInstance().getScriptHandler(new ApduHandler4Test(this, (ApduHandler4Test) null));
        scriptHandler.setApduFilterDataInit(new FilterDataInit4Test(this, (FilterDataInit4Test) null));
        ApduRequestList apduRequestList = new ApduRequestList();
        System.out.println("=======================test==============================================");
        ITag createTag = this.messageHandler.createTag((byte) ScriptToolsConst.TagName.ScriptDown);
        ITag createTag2 = this.messageHandler.createTag((byte) ScriptToolsConst.TagName.CommandSingle);
        ITag createTag3 = this.messageHandler.createTag((byte) ScriptToolsConst.TagName.CommandMultiple);
        ITag createTag4 = this.messageHandler.createTag((byte) ScriptToolsConst.TagName.CommandMultiple);
        ITag createTag5 = this.messageHandler.createTag((byte) ScriptToolsConst.TagName.CommandMultiple);
        try {
            byte[] bArr = new byte[8];
            bArr[0] = 1;
            bArr[2] = ScriptToolsConst.TagName.CommandMultiple;
            bArr[6] = -1;
            bArr[7] = -1;
            createTag2.addValue(bArr);
            createTag.addValue(createTag2);
            ITag createTag6 = this.messageHandler.createTag((byte) ScriptToolsConst.TagName.TagSerial);
            createTag6.addValue(2);
            createTag3.addValue(createTag6);
            ITag createTag7 = this.messageHandler.createTag((byte) ScriptToolsConst.TagName.TagApdu);
            byte[] bArr2 = new byte[5];
            bArr2[1] = ScriptToolsConst.TagName.CommandMultiple;
            createTag7.addValue(bArr2);
            createTag3.addValue(createTag7);
            ITag createTag8 = this.messageHandler.createTag((byte) ScriptToolsConst.TagName.TagExpectationAndNext);
            byte[] bArr3 = new byte[3];
            bArr3[0] = Constants.TagName.SYSTEM_VERSION;
            createTag8.addValue(bArr3);
            createTag3.addValue(createTag8);
            ITag createTag9 = this.messageHandler.createTag((byte) ScriptToolsConst.TagName.TagExpectationAndNext);
            byte[] bArr4 = new byte[3];
            bArr4[0] = Constants.TagName.MAIN_ORDER_ID;
            bArr4[2] = -1;
            createTag9.addValue(bArr4);
            createTag3.addValue(createTag9);
            createTag.addValue(createTag3);
            ITag createTag10 = this.messageHandler.createTag((byte) ScriptToolsConst.TagName.TagSerial);
            createTag10.addValue(3);
            createTag4.addValue(createTag10);
            ITag createTag11 = this.messageHandler.createTag((byte) ScriptToolsConst.TagName.TagApdu);
            byte[] bArr5 = new byte[5];
            bArr5[1] = ScriptToolsConst.TagName.CommandMultiple;
            createTag11.addValue(bArr5);
            createTag4.addValue(createTag11);
            ITag createTag12 = this.messageHandler.createTag((byte) ScriptToolsConst.TagName.TagExpectationAndNext);
            byte[] bArr6 = new byte[3];
            bArr6[0] = Constants.TagName.SYSTEM_VERSION;
            bArr6[2] = 4;
            createTag12.addValue(bArr6);
            createTag4.addValue(createTag12);
            ITag createTag13 = this.messageHandler.createTag((byte) ScriptToolsConst.TagName.TagExpectationAndNext);
            byte[] bArr7 = new byte[3];
            bArr7[0] = Constants.TagName.MAIN_ORDER_ID;
            bArr7[2] = -1;
            createTag13.addValue(bArr7);
            createTag4.addValue(createTag13);
            createTag.addValue(createTag4);
            ITag createTag14 = this.messageHandler.createTag((byte) ScriptToolsConst.TagName.TagSerial);
            createTag14.addValue(4);
            createTag5.addValue(createTag14);
            ITag createTag15 = this.messageHandler.createTag((byte) ScriptToolsConst.TagName.TagApdu);
            byte[] bArr8 = new byte[5];
            bArr8[1] = ScriptToolsConst.TagName.CommandMultiple;
            createTag15.addValue(bArr8);
            createTag5.addValue(createTag15);
            ITag createTag16 = this.messageHandler.createTag((byte) ScriptToolsConst.TagName.TagExpectationAndNext);
            byte[] bArr9 = new byte[3];
            bArr9[0] = Constants.TagName.SYSTEM_VERSION;
            bArr9[2] = -1;
            createTag16.addValue(bArr9);
            createTag5.addValue(createTag16);
            ITag createTag17 = this.messageHandler.createTag((byte) ScriptToolsConst.TagName.TagExpectationAndNext);
            byte[] bArr10 = new byte[3];
            bArr10[0] = Constants.TagName.MAIN_ORDER_ID;
            bArr10[2] = -1;
            createTag17.addValue(bArr10);
            createTag5.addValue(createTag17);
            createTag.addValue(createTag5);
        } catch (FMCommunicationMessageException e) {
            System.out.println(Util4Java.getExceptionInfo(e));
        }
        try {
            apduRequestList.fromTag(createTag);
        } catch (FMScriptHandleException e2) {
            System.out.println(Util4Java.getExceptionInfo(e2));
        } catch (FMCommunicationMessageException e3) {
            System.out.println(Util4Java.getExceptionInfo(e3));
        }
        try {
            apduReponseList = scriptHandler.execute(apduRequestList);
        } catch (FMScriptHandleException e4) {
            e4.printStackTrace();
            apduReponseList = null;
        }
        ApduResponse[] apduResponse = apduReponseList.getApduResponse();
        System.out.println("apduReponses.length:" + apduResponse.length);
        for (ApduResponse apduResponse2 : apduResponse) {
            System.out.println("id:" + apduResponse2.getId() + "  reponseCode:" + FM_Bytes.bytesToHexString(apduResponse2.getResult()));
        }
    }

    public static void main(String[] strArr) {
        Test test = new Test();
        test.load();
        test.testM();
    }
}
