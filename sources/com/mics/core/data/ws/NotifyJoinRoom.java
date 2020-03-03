package com.mics.core.data.ws;

import com.mics.core.data.response.MessageResponse;
import java.util.List;

public class NotifyJoinRoom extends NotifyMessage {
    private Body body;

    public Body getBody() {
        return this.body;
    }

    public void setBody(Body body2) {
        this.body = body2;
    }

    public static class Body {
        private boolean morePreMsg;
        private List<MessageResponse.Data.Message> newMessages;

        public boolean isMorePreMsg() {
            return this.morePreMsg;
        }

        public void setMorePreMsg(boolean z) {
            this.morePreMsg = z;
        }

        public List<MessageResponse.Data.Message> getNewMessages() {
            return this.newMessages;
        }

        public void setNewMessages(List<MessageResponse.Data.Message> list) {
            this.newMessages = list;
        }
    }
}
