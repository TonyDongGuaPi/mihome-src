package com.mics.core.business;

import android.util.Pair;
import com.mics.constant.API;
import com.mics.core.MiCS;
import com.mics.core.data.request.PullMessage;
import com.mics.core.data.request.ReadOffset;
import com.mics.core.data.request.SendText;
import com.mics.core.data.request.Session;
import com.mics.core.data.request.SessionChooseService;
import com.mics.core.data.request.SessionCreate;
import com.mics.core.data.request.SessionLeavingMessage;
import com.mics.core.data.request.SessionSubmitHumanScore;
import com.mics.core.data.request.SessionSubmitRobotScore;
import com.mics.core.data.response.BaseResponse;
import com.mics.core.data.response.ChatListResponse;
import com.mics.core.data.response.MessageResponse;
import com.mics.core.data.response.QueryServiceListResponse;
import com.mics.core.data.response.QueuePositionResponse;
import com.mics.core.data.response.SendResponse;
import com.mics.core.data.response.SessionCreateResponse;
import com.mics.network.HttpUtil;
import com.mics.util.GsonUtil;
import java.lang.reflect.Type;

class ChatController {
    ChatController() {
    }

    static SessionCreateResponse a(String str, String str2) {
        SessionCreate sessionCreate = new SessionCreate();
        sessionCreate.setMerchantId(str);
        sessionCreate.setGid(str2);
        sessionCreate.setHeader(new Session.Header());
        return (SessionCreateResponse) a(API.a(), GsonUtil.a(sessionCreate), SessionCreateResponse.class);
    }

    static SessionCreateResponse a(String str) {
        Session session = new Session();
        Session.Header header = new Session.Header();
        header.setSessionId(str);
        session.setHeader(header);
        return (SessionCreateResponse) a(API.b(), GsonUtil.a(session), SessionCreateResponse.class);
    }

    static BaseResponse b(String str) {
        Session session = new Session();
        Session.Header header = new Session.Header();
        header.setSessionId(str);
        session.setHeader(header);
        return (BaseResponse) a(API.c(), GsonUtil.a(session), BaseResponse.class);
    }

    static QueryServiceListResponse c(String str) {
        Session session = new Session();
        Session.Header header = new Session.Header();
        header.setSessionId(str);
        session.setHeader(header);
        return (QueryServiceListResponse) a(API.d(), GsonUtil.a(session), QueryServiceListResponse.class);
    }

    static BaseResponse b(String str, String str2) {
        SessionChooseService sessionChooseService = new SessionChooseService();
        Session.Header header = new Session.Header();
        header.setSessionId(str2);
        sessionChooseService.setHeader(header);
        sessionChooseService.setQueueId(str);
        return (BaseResponse) a(API.e(), GsonUtil.a(sessionChooseService), BaseResponse.class);
    }

    static BaseResponse a(int i, String str, boolean z, String str2, String str3, String str4) {
        SessionSubmitHumanScore sessionSubmitHumanScore = new SessionSubmitHumanScore();
        sessionSubmitHumanScore.setComment(str);
        sessionSubmitHumanScore.setIsSolved(z ? 1 : 0);
        sessionSubmitHumanScore.setPhone(str4);
        sessionSubmitHumanScore.setScore(i);
        sessionSubmitHumanScore.setSubSessionId(str2);
        Session.Header header = new Session.Header();
        header.setSessionId(str3);
        sessionSubmitHumanScore.setHeader(header);
        return (BaseResponse) a(API.g(), GsonUtil.a(sessionSubmitHumanScore), BaseResponse.class);
    }

    static BaseResponse a(String str, boolean z, String str2) {
        SessionSubmitRobotScore sessionSubmitRobotScore = new SessionSubmitRobotScore();
        Session.Header header = new Session.Header();
        header.setSessionId(str2);
        sessionSubmitRobotScore.setHeader(header);
        sessionSubmitRobotScore.setIsSatisfied(z ? 1 : 0);
        sessionSubmitRobotScore.setQuestionId(str);
        return (BaseResponse) a(API.h(), GsonUtil.a(sessionSubmitRobotScore), BaseResponse.class);
    }

    static BaseResponse c(String str, String str2) {
        SessionLeavingMessage sessionLeavingMessage = new SessionLeavingMessage();
        Session.Header header = new Session.Header();
        header.setSessionId(str);
        sessionLeavingMessage.setHeader(header);
        sessionLeavingMessage.setQueueId(str2);
        return (BaseResponse) a(API.i(), GsonUtil.a(sessionLeavingMessage), BaseResponse.class);
    }

    static QueuePositionResponse d(String str) {
        Session session = new Session();
        Session.Header header = new Session.Header();
        header.setSessionId(str);
        session.setHeader(header);
        return (QueuePositionResponse) a(API.j(), GsonUtil.a(session), QueuePositionResponse.class);
    }

    static MessageResponse a(String str, long j, int i) {
        PullMessage pullMessage = new PullMessage();
        pullMessage.setRoomId(str);
        pullMessage.setFromMsgId(j);
        pullMessage.setBatchSize(i);
        return (MessageResponse) a(API.m(), GsonUtil.a(pullMessage), MessageResponse.class);
    }

    static Pair<String, ChatListResponse> a() {
        String a2 = HttpUtil.a(API.o(), "{\"userId\":\"" + MiCS.a().n() + "\"}");
        return new Pair<>(a2, (ChatListResponse) GsonUtil.a(a2, (Type) ChatListResponse.class));
    }

    static BaseResponse e(String str) {
        return (BaseResponse) a(API.q(), "{\"roomId\":\"" + str + "\"}", BaseResponse.class);
    }

    static BaseResponse b() {
        return (BaseResponse) a(API.r(), "", BaseResponse.class);
    }

    static MessageResponse b(String str, long j, int i) {
        PullMessage pullMessage = new PullMessage();
        pullMessage.setRoomId(str);
        pullMessage.setFromMsgId(j);
        pullMessage.setBatchSize(i);
        return (MessageResponse) a(API.n(), GsonUtil.a(pullMessage), MessageResponse.class);
    }

    static BaseResponse a(String str, long j) {
        ReadOffset readOffset = new ReadOffset();
        readOffset.setMaxReadMsgId(j);
        readOffset.setRoomId(str);
        return (BaseResponse) a(API.p(), GsonUtil.a(readOffset), BaseResponse.class);
    }

    static SendText d(String str, String str2) {
        SendText sendText = new SendText();
        sendText.setConnectionId(MiCS.a().m());
        sendText.setContent(str2);
        sendText.setRoomId(str);
        sendText.setUserId(MiCS.a().n());
        return sendText;
    }

    static SendResponse a(SendText sendText) {
        return a(sendText, SendText.PIC);
    }

    static SendResponse b(SendText sendText) {
        return a(sendText, SendText.TEXT);
    }

    private static SendResponse a(SendText sendText, String str) {
        sendText.setMsgType(str);
        return (SendResponse) a(API.k(), GsonUtil.a(sendText), SendResponse.class);
    }

    private static <T> T a(String str, String str2, Class<T> cls) {
        return GsonUtil.a(HttpUtil.a(str, str2), (Type) cls);
    }
}
