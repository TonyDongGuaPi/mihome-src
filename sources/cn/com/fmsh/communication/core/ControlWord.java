package cn.com.fmsh.communication.core;

import cn.com.fmsh.tsm.business.constants.Constants;

public class ControlWord {
    private static /* synthetic */ int[] $SWITCH_TABLE$cn$com$fmsh$communication$core$ControlWord$CommandType;
    private CommandType commandType;
    private Direction direction;
    private boolean haveNews;
    private byte reponseCode;
    private MessageType type;

    /* JADX WARNING: Can't wrap try/catch for region: R(11:3|4|5|6|7|8|9|10|11|12|14) */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Missing exception handler attribute for start block: B:10:0x0027 */
    /* JADX WARNING: Missing exception handler attribute for start block: B:6:0x0015 */
    /* JADX WARNING: Missing exception handler attribute for start block: B:8:0x001e */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    static /* synthetic */ int[] $SWITCH_TABLE$cn$com$fmsh$communication$core$ControlWord$CommandType() {
        /*
            int[] r0 = $SWITCH_TABLE$cn$com$fmsh$communication$core$ControlWord$CommandType
            if (r0 == 0) goto L_0x0005
            return r0
        L_0x0005:
            cn.com.fmsh.communication.core.ControlWord$CommandType[] r0 = cn.com.fmsh.communication.core.ControlWord.CommandType.values()
            int r0 = r0.length
            int[] r0 = new int[r0]
            cn.com.fmsh.communication.core.ControlWord$CommandType r1 = cn.com.fmsh.communication.core.ControlWord.CommandType.BUSINESS_ERROR     // Catch:{ NoSuchFieldError -> 0x0015 }
            int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0015 }
            r2 = 4
            r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0015 }
        L_0x0015:
            cn.com.fmsh.communication.core.ControlWord$CommandType r1 = cn.com.fmsh.communication.core.ControlWord.CommandType.CLOSESESSION     // Catch:{ NoSuchFieldError -> 0x001e }
            int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001e }
            r2 = 3
            r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001e }
        L_0x001e:
            cn.com.fmsh.communication.core.ControlWord$CommandType r1 = cn.com.fmsh.communication.core.ControlWord.CommandType.HEARTBEAT     // Catch:{ NoSuchFieldError -> 0x0027 }
            int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0027 }
            r2 = 1
            r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0027 }
        L_0x0027:
            cn.com.fmsh.communication.core.ControlWord$CommandType r1 = cn.com.fmsh.communication.core.ControlWord.CommandType.OPENSESSION     // Catch:{ NoSuchFieldError -> 0x0030 }
            int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0030 }
            r2 = 2
            r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0030 }
        L_0x0030:
            $SWITCH_TABLE$cn$com$fmsh$communication$core$ControlWord$CommandType = r0
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: cn.com.fmsh.communication.core.ControlWord.$SWITCH_TABLE$cn$com$fmsh$communication$core$ControlWord$CommandType():int[]");
    }

    public MessageType getType() {
        return this.type;
    }

    public void setType(MessageType messageType) {
        this.type = messageType;
    }

    public Direction getDirection() {
        return this.direction;
    }

    public void setDirection(Direction direction2) {
        this.direction = direction2;
    }

    public CommandType getCommandType() {
        return this.commandType;
    }

    public void setCommandType(CommandType commandType2) {
        this.commandType = commandType2;
    }

    public boolean isHaveNews() {
        return this.haveNews;
    }

    public void setHaveNews(boolean z) {
        this.haveNews = z;
    }

    public byte getReponseCode() {
        return this.reponseCode;
    }

    public void setReponseCode(byte b) {
        this.reponseCode = b;
    }

    public byte toBytes() {
        byte b;
        if (this.type == MessageType.CONTROL) {
            b = (byte) -128;
            switch ($SWITCH_TABLE$cn$com$fmsh$communication$core$ControlWord$CommandType()[this.commandType.ordinal()]) {
                case 2:
                    b = (byte) (b | 32);
                    break;
                case 3:
                    b = (byte) (b | 64);
                    break;
                case 4:
                    b = (byte) (b | Constants.TagName.MAIN_ORDER);
                    break;
            }
        } else {
            b = 0;
        }
        if (this.direction != Direction.RESPONSE) {
            return b;
        }
        byte b2 = (byte) (b & 16);
        if (this.commandType == CommandType.HEARTBEAT || this.type == MessageType.BUSINESS) {
            return this.haveNews ? (byte) (b2 | 1) : b2;
        }
        return (byte) (b2 | (this.reponseCode & 15));
    }

    public void fromBytes(byte b) {
        if ((b & 128) != 0) {
            this.type = MessageType.CONTROL;
            switch ((byte) (((byte) (b & Constants.TagName.MAIN_ORDER)) >> 5)) {
                case 0:
                    this.commandType = CommandType.HEARTBEAT;
                    break;
                case 1:
                    this.commandType = CommandType.OPENSESSION;
                    break;
                case 2:
                    this.commandType = CommandType.CLOSESESSION;
                    break;
                case 3:
                    this.commandType = CommandType.BUSINESS_ERROR;
                    break;
            }
        } else {
            this.type = MessageType.BUSINESS;
        }
        if (((byte) (b & 16)) == 0) {
            this.direction = Direction.REQUEST;
        } else {
            this.direction = Direction.RESPONSE;
        }
        if (this.direction != Direction.RESPONSE) {
            return;
        }
        if (this.type == MessageType.BUSINESS) {
            if ((b & 15) == 0) {
                this.haveNews = false;
            } else {
                this.haveNews = true;
            }
        } else if (this.commandType != CommandType.HEARTBEAT) {
            this.reponseCode = (byte) (b & 15);
        } else if ((b & 15) == 0) {
            this.haveNews = false;
        } else {
            this.haveNews = true;
        }
    }

    public enum MessageType {
        BUSINESS(0),
        CONTROL(1);
        
        private int value;

        private MessageType(int i) {
            this.value = i;
        }
    }

    public enum Direction {
        REQUEST(0),
        RESPONSE(1);
        
        private int value;

        private Direction(int i) {
            this.value = i;
        }
    }

    public enum CommandType {
        HEARTBEAT(0),
        OPENSESSION(1),
        CLOSESESSION(2),
        BUSINESS_ERROR(3);
        
        private int value;

        private CommandType(int i) {
            this.value = i;
        }
    }
}
