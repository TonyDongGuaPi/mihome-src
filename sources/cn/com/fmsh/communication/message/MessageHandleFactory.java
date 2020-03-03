package cn.com.fmsh.communication.message;

import cn.com.fmsh.communication.message.core.MessageHandler;

public class MessageHandleFactory {
    private static MessageHandler messageHandler;

    private MessageHandleFactory() {
    }

    public static MessageHandler getMessageHandler() {
        if (messageHandler == null) {
            messageHandlerInit();
        }
        return messageHandler;
    }

    private static synchronized void messageHandlerInit() {
        synchronized (MessageHandleFactory.class) {
            if (messageHandler == null) {
                messageHandler = new MessageHandler();
            }
        }
    }
}
