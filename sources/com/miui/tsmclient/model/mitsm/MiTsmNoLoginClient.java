package com.miui.tsmclient.model.mitsm;

import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import com.google.protobuf.ByteString;
import com.miui.tsmclient.entity.CardInfo;
import com.miui.tsmclient.model.BaseResponse;
import com.miui.tsmclient.seitsm.Exception.SeiTSMApiException;
import com.miui.tsmclient.seitsm.SeiTsmNoLoginManger;
import com.miui.tsmclient.seitsm.TsmRpcModels;
import com.miui.tsmclient.util.LogUtils;
import com.tsmclient.smartcard.terminal.IScTerminal;
import com.tsmclient.smartcard.terminal.response.ScResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class MiTsmNoLoginClient {
    protected SeiTsmNoLoginManger mSeiTsmManager = new SeiTsmNoLoginManger();

    public TsmRpcModels.TsmSessionInfo getSession(Context context, CardInfo cardInfo) throws IOException, SeiTSMApiException {
        TsmRpcModels.TsmSessionInfo createSession = this.mSeiTsmManager.createSession(context, cardInfo);
        if (createSession == null) {
            throw new SeiTSMApiException(7, "cannot get a session to start operation.");
        } else if (createSession.getResult() == 0) {
            LogUtils.d("sessionInfo : " + createSession.getSessionId());
            return createSession;
        } else {
            int result = createSession.getResult();
            throw new SeiTSMApiException(result, "getSession failed, errorCode:" + createSession.getResult());
        }
    }

    public BaseResponse unrestrictEse(Context context, Bundle bundle) throws IOException, SeiTSMApiException, InterruptedException {
        CardInfo cardInfo = new CardInfo(CardInfo.CARD_TYPE_DUMMY);
        TsmRpcModels.TsmSessionInfo session = getSession(context, cardInfo);
        TsmRpcModels.TsmAPDUCommand startSeOperation = this.mSeiTsmManager.startSeOperation(context, session, TsmRpcModels.SeOperationType.UNRESTRICT, (CardInfo) null, bundle);
        if (startSeOperation != null) {
            int result = startSeOperation.getResult();
            if (result == 0) {
                IScTerminal terminal = cardInfo.getTerminal();
                try {
                    terminal.open();
                    return executeCapdu(context, terminal, session, startSeOperation);
                } finally {
                    terminal.close();
                }
            } else {
                throw new SeiTSMApiException(result, "unRestrict ese failed, errorCode: " + result);
            }
        } else {
            throw new SeiTSMApiException(8, "unRestrict ese failed, no apdu commands");
        }
    }

    private BaseResponse executeCapdu(Context context, IScTerminal iScTerminal, TsmRpcModels.TsmSessionInfo tsmSessionInfo, TsmRpcModels.TsmAPDUCommand tsmAPDUCommand) throws IOException, SeiTSMApiException, InterruptedException {
        List<TsmRpcModels.TsmCAPDU> apdusList = tsmAPDUCommand.getApdusList();
        if (apdusList == null || apdusList.isEmpty()) {
            int result = tsmAPDUCommand.getResult();
            throw new SeiTSMApiException(result, "capdu list is null,　errorCode:" + tsmAPDUCommand.getResult());
        }
        ArrayList arrayList = new ArrayList();
        Iterator<TsmRpcModels.TsmCAPDU> it = apdusList.iterator();
        int i = 0;
        while (true) {
            if (!it.hasNext()) {
                break;
            }
            TsmRpcModels.TsmCAPDU next = it.next();
            ScResponse transmit = iScTerminal.transmit(next.getApdu().toByteArray());
            i++;
            arrayList.add(TsmRpcModels.SeAPDUResponseItem.newBuilder().setIndex(i).setResponseData(ByteString.copyFrom(transmit.getData().toBytes())).setResponseSw(ByteString.copyFrom(transmit.getStatus().toBytes())).build());
            if (TextUtils.isEmpty(next.getExpectSwRegex())) {
                LogUtils.d("no expected sw.");
                break;
            }
        }
        TsmRpcModels.TsmAPDUCommand processSeResponse = this.mSeiTsmManager.processSeResponse(context, tsmSessionInfo, arrayList);
        if (processSeResponse == null) {
            LogUtils.d("can not get next apdu command, process se response failed.");
            return new BaseResponse(8, new Object[0]);
        }
        int result2 = processSeResponse.getResult();
        if (result2 != 0) {
            throw new SeiTSMApiException(result2, "processSeResponse api returned an error, errorCode:" + result2);
        } else if (processSeResponse.getApdusList() != null && !processSeResponse.getApdusList().isEmpty()) {
            return executeCapdu(context, iScTerminal, tsmSessionInfo, processSeResponse);
        } else {
            LogUtils.d("no more apdu, execte finished!");
            return new BaseResponse(0, new Object[0]);
        }
    }
}
