package com.liulishuo.filedownloader.message;

import com.liulishuo.filedownloader.util.FileDownloadUtils;
import junit.framework.Assert;

public interface BlockCompleteMessage {
    MessageSnapshot a();

    public static class BlockCompleteMessageImpl extends MessageSnapshot implements BlockCompleteMessage {
        private final MessageSnapshot b;

        public byte b() {
            return 4;
        }

        public BlockCompleteMessageImpl(MessageSnapshot messageSnapshot) {
            super(messageSnapshot.c());
            Assert.assertTrue(FileDownloadUtils.a("can't create the block complete message for id[%d], status[%d]", Integer.valueOf(messageSnapshot.c()), Byte.valueOf(messageSnapshot.b())), messageSnapshot.b() == -3);
            this.b = messageSnapshot;
        }

        public MessageSnapshot a() {
            return this.b;
        }
    }
}
