package com.tsmclient.smartcard.model;

import android.os.Parcel;
import android.os.Parcelable;
import cn.com.fmsh.tsm.business.constants.Constants;
import com.google.gson.annotations.SerializedName;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ConfigRules implements Parcelable {
    public static final Parcelable.Creator<ConfigRules> CREATOR = new Parcelable.Creator<ConfigRules>() {
        public ConfigRules createFromParcel(Parcel parcel) {
            ConfigRules configRules = new ConfigRules();
            configRules.readFromParcel(parcel);
            return configRules;
        }

        public ConfigRules[] newArray(int i) {
            return new ConfigRules[i];
        }
    };
    public static final int EXCEPTION_CARD_STATUS = 1;
    public static final int EXCEPTION_UNPROCESSABLE = 0;
    public static final int FOR_EXTERNAL = 2;
    public static final int FOR_INTERNAL = 1;
    public static final int INVALID_OFFSET = -1;
    public static final int INVALID_VERSION = -1;
    @SerializedName("cardNumValidDateCommands")
    public List<ParseDataCommand> mCardNumValidDateCommandList;
    @SerializedName("cardType")
    public String mCardType;
    @SerializedName("otherVerifyCommands")
    public List<Command> mOtherVerifyCommandList;
    @SerializedName("readBalanceCommands")
    public List<ParseDataCommand> mReadBalanceCommandList;
    @SerializedName("readCardStatusCommands")
    public List<ParseDataCommand> mReadCardStatusCommandList;
    @SerializedName("readRecordCommand")
    private ReadRecordCommand mReadRecordCommand;
    @SerializedName("readRecordCommands")
    private List<ReadRecordCommand> mReadRecordCommandList;
    @SerializedName("selectVerifyCommands")
    public List<Command> mSelectVerifyCommandList;
    @SerializedName("version")
    public String mVersion;

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.mCardType);
        parcel.writeString(this.mVersion);
        parcel.writeTypedList(this.mSelectVerifyCommandList);
        parcel.writeTypedList(this.mOtherVerifyCommandList);
        parcel.writeTypedList(this.mCardNumValidDateCommandList);
        parcel.writeTypedList(this.mReadBalanceCommandList);
        parcel.writeParcelable(this.mReadRecordCommand, i);
        parcel.writeTypedList(this.mReadRecordCommandList);
    }

    /* access modifiers changed from: private */
    public void readFromParcel(Parcel parcel) {
        this.mCardType = parcel.readString();
        this.mVersion = parcel.readString();
        this.mSelectVerifyCommandList = new ArrayList();
        parcel.readTypedList(this.mSelectVerifyCommandList, Command.CREATOR);
        this.mOtherVerifyCommandList = new ArrayList();
        parcel.readTypedList(this.mOtherVerifyCommandList, Command.CREATOR);
        this.mCardNumValidDateCommandList = new ArrayList();
        parcel.readTypedList(this.mCardNumValidDateCommandList, ParseDataCommand.CREATOR);
        this.mReadBalanceCommandList = new ArrayList();
        parcel.readTypedList(this.mReadBalanceCommandList, ParseDataCommand.CREATOR);
        this.mReadRecordCommand = (ReadRecordCommand) parcel.readParcelable(ReadRecordCommand.class.getClassLoader());
        this.mReadRecordCommandList = new ArrayList();
        parcel.readTypedList(this.mReadRecordCommandList, ReadRecordCommand.CREATOR);
    }

    public List<ReadRecordCommand> getReadRecordCommandList() {
        ArrayList arrayList = new ArrayList();
        if (this.mReadRecordCommand != null) {
            arrayList.add(this.mReadRecordCommand);
        }
        if (this.mReadRecordCommandList != null && !this.mReadRecordCommandList.isEmpty()) {
            arrayList.addAll(this.mReadRecordCommandList);
        }
        return arrayList;
    }

    public static class Command implements Parcelable {
        public static final Parcelable.Creator<Command> CREATOR = new Parcelable.Creator<Command>() {
            public Command createFromParcel(Parcel parcel) {
                Command command = new Command();
                command.readFromParcel(parcel);
                return command;
            }

            public Command[] newArray(int i) {
                return new Command[i];
            }
        };
        @SerializedName("apdu")
        public String mApdu;
        @SerializedName("expectData")
        public String mExpectData;
        @SerializedName("expectStatus")
        public String mExpectStatus;
        @SerializedName("flag")
        private int mFlag = 1;
        @SerializedName("maxVersion")
        public int mMaxVersion = -1;
        @SerializedName("minVersion")
        public int mMinVersion = -1;
        @SerializedName("preConditionExpectData")
        public String mPreConditionExpectData;
        @SerializedName("preConditionExpectStatus")
        public String mPreConditionExpectStatus;
        @SerializedName("preConditionKey")
        public String mPreConditionKey;

        public int describeContents() {
            return 0;
        }

        public boolean isExecute(boolean z) {
            int i = z ? 1 : 2;
            return (this.mFlag & i) == i;
        }

        public void writeToParcel(Parcel parcel, int i) {
            parcel.writeString(this.mApdu);
            parcel.writeString(this.mExpectStatus);
            parcel.writeString(this.mExpectData);
            parcel.writeString(this.mPreConditionKey);
            parcel.writeString(this.mPreConditionExpectStatus);
            parcel.writeString(this.mPreConditionExpectData);
            parcel.writeInt(this.mMaxVersion);
            parcel.writeInt(this.mMinVersion);
            parcel.writeInt(this.mFlag);
        }

        /* access modifiers changed from: protected */
        public void readFromParcel(Parcel parcel) {
            this.mApdu = parcel.readString();
            this.mExpectStatus = parcel.readString();
            this.mExpectData = parcel.readString();
            this.mPreConditionKey = parcel.readString();
            this.mPreConditionExpectStatus = parcel.readString();
            this.mPreConditionExpectData = parcel.readString();
            this.mMaxVersion = parcel.readInt();
            this.mMinVersion = parcel.readInt();
            this.mFlag = parcel.readInt();
        }
    }

    public static class ParseDataCommand extends Command {
        public static final Parcelable.Creator<ParseDataCommand> CREATOR = new Parcelable.Creator<ParseDataCommand>() {
            public ParseDataCommand createFromParcel(Parcel parcel) {
                ParseDataCommand parseDataCommand = new ParseDataCommand();
                parseDataCommand.readFromParcel(parcel);
                return parseDataCommand;
            }

            public ParseDataCommand[] newArray(int i) {
                return new ParseDataCommand[i];
            }
        };
        @SerializedName("elements")
        public List<Element> mElementList;
        @SerializedName("expectResponse")
        public String mExpectResponse;
        @SerializedName("responseExceptionType")
        public int mResponseExceptionType;
        @SerializedName("responseKey")
        public String mResponseKey;
        @SerializedName("responseMap")
        public Map<String, String> mResponseMap;

        public void writeToParcel(Parcel parcel, int i) {
            super.writeToParcel(parcel, i);
            parcel.writeTypedList(this.mElementList);
            parcel.writeString(this.mResponseKey);
            parcel.writeMap(this.mResponseMap);
            parcel.writeString(this.mExpectResponse);
            parcel.writeInt(this.mResponseExceptionType);
        }

        /* access modifiers changed from: protected */
        public void readFromParcel(Parcel parcel) {
            super.readFromParcel(parcel);
            this.mElementList = new ArrayList();
            parcel.readTypedList(this.mElementList, Element.CREATOR);
            this.mResponseKey = parcel.readString();
            this.mResponseMap = new HashMap();
            parcel.readMap(this.mResponseMap, getClass().getClassLoader());
            this.mExpectResponse = parcel.readString();
            this.mResponseExceptionType = parcel.readInt();
        }
    }

    public static class Element implements Parcelable {
        public static final Parcelable.Creator<Element> CREATOR = new Parcelable.Creator<Element>() {
            public Element createFromParcel(Parcel parcel) {
                Element element = new Element();
                element.readFromParcel(parcel);
                return element;
            }

            public Element[] newArray(int i) {
                return new Element[i];
            }
        };
        @SerializedName("expectValue")
        public String mExpectValue;
        @SerializedName("hexLength")
        public int mHexLength;
        @SerializedName("hexOffset")
        public int mHexOffset = -1;
        @SerializedName("key")
        public String mKey;
        @SerializedName("length")
        public int mLength;
        @SerializedName("offset")
        public int mOffset;
        @SerializedName("prefixHex")
        public String mPrefixHex;
        @SerializedName("suffixHex")
        public String mSuffixHex;
        @SerializedName("valueExceptionType")
        public int mValueExceptionType;
        @SerializedName("valueMap")
        public Map<String, String> mValueMap;

        public int describeContents() {
            return 0;
        }

        public void writeToParcel(Parcel parcel, int i) {
            parcel.writeString(this.mKey);
            parcel.writeInt(this.mOffset);
            parcel.writeInt(this.mLength);
            parcel.writeInt(this.mHexOffset);
            parcel.writeInt(this.mHexLength);
            parcel.writeString(this.mPrefixHex);
            parcel.writeString(this.mSuffixHex);
            parcel.writeMap(this.mValueMap);
            parcel.writeString(this.mExpectValue);
            parcel.writeInt(this.mValueExceptionType);
        }

        /* access modifiers changed from: protected */
        public void readFromParcel(Parcel parcel) {
            this.mKey = parcel.readString();
            this.mOffset = parcel.readInt();
            this.mLength = parcel.readInt();
            this.mHexOffset = parcel.readInt();
            this.mHexLength = parcel.readInt();
            this.mPrefixHex = parcel.readString();
            this.mSuffixHex = parcel.readString();
            this.mValueMap = new HashMap();
            parcel.readMap(this.mValueMap, getClass().getClassLoader());
            this.mExpectValue = parcel.readString();
            this.mValueExceptionType = parcel.readInt();
        }
    }

    public static class ReadRecordCommand implements Parcelable {
        public static final Parcelable.Creator<ReadRecordCommand> CREATOR = new Parcelable.Creator<ReadRecordCommand>() {
            public ReadRecordCommand createFromParcel(Parcel parcel) {
                ReadRecordCommand readRecordCommand = new ReadRecordCommand();
                readRecordCommand.readFromParcel(parcel);
                return readRecordCommand;
            }

            public ReadRecordCommand[] newArray(int i) {
                return new ReadRecordCommand[i];
            }
        };
        @SerializedName("flag")
        private int mFlag = 3;
        @SerializedName("preReadCommands")
        public List<Command> mPreReadCommandList;
        @SerializedName("instructions")
        private List<ReadRecordInstruction> mReadRecordInstructionList;

        public int describeContents() {
            return 0;
        }

        public boolean isExecute(boolean z) {
            int i = z ? 1 : 2;
            return (this.mFlag & i) == i;
        }

        public List<ReadRecordInstruction> getReadRecordInstructionList() {
            return this.mReadRecordInstructionList;
        }

        public boolean isReadRecordInstructionListEmpty() {
            return this.mReadRecordInstructionList == null || this.mReadRecordInstructionList.isEmpty();
        }

        public void writeToParcel(Parcel parcel, int i) {
            parcel.writeTypedList(this.mPreReadCommandList);
            parcel.writeTypedList(this.mReadRecordInstructionList);
        }

        /* access modifiers changed from: protected */
        public void readFromParcel(Parcel parcel) {
            this.mPreReadCommandList = new ArrayList();
            parcel.readTypedList(this.mPreReadCommandList, Command.CREATOR);
            this.mReadRecordInstructionList = new ArrayList();
            parcel.readTypedList(this.mReadRecordInstructionList, ReadRecordInstruction.CREATOR);
        }
    }

    public static class ReadRecordInstruction implements Parcelable {
        public static final Parcelable.Creator<ReadRecordInstruction> CREATOR = new Parcelable.Creator<ReadRecordInstruction>() {
            public ReadRecordInstruction createFromParcel(Parcel parcel) {
                ReadRecordInstruction readRecordInstruction = new ReadRecordInstruction();
                readRecordInstruction.readFromParcel(parcel);
                return readRecordInstruction;
            }

            public ReadRecordInstruction[] newArray(int i) {
                return new ReadRecordInstruction[i];
            }
        };
        @SerializedName("p2")
        public String mP2;
        @SerializedName("sfi")
        public String mSfi;
        @SerializedName("skipOnceRead")
        public boolean mSkipOnceRead;
        @SerializedName("skipParsingYear")
        private boolean mSkipParsingYear;

        public int describeContents() {
            return 0;
        }

        public byte getP2() {
            return get(Constants.TagName.USER_PLATFORM_TYPE, this.mP2);
        }

        public byte getSfi() {
            return get((byte) 0, this.mSfi);
        }

        public boolean isSkipParsingYear() {
            return this.mSkipParsingYear;
        }

        public void writeToParcel(Parcel parcel, int i) {
            parcel.writeString(this.mP2);
            parcel.writeString(this.mSfi);
            parcel.writeByte(this.mSkipOnceRead ? (byte) 1 : 0);
            parcel.writeByte(this.mSkipParsingYear ? (byte) 1 : 0);
        }

        /* access modifiers changed from: protected */
        public void readFromParcel(Parcel parcel) {
            this.mP2 = parcel.readString();
            this.mSfi = parcel.readString();
            boolean z = false;
            this.mSkipOnceRead = parcel.readByte() == 1;
            if (parcel.readByte() == 1) {
                z = true;
            }
            this.mSkipParsingYear = z;
        }

        /* JADX WARNING: Code restructure failed: missing block: B:2:0x0006, code lost:
            r3 = com.tsmclient.smartcard.Coder.hexStringToBytes(r3);
         */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        private byte get(byte r2, java.lang.String r3) {
            /*
                r1 = this;
                boolean r0 = android.text.TextUtils.isEmpty(r3)
                if (r0 != 0) goto L_0x0012
                byte[] r3 = com.tsmclient.smartcard.Coder.hexStringToBytes(r3)
                if (r3 == 0) goto L_0x0012
                int r0 = r3.length
                if (r0 <= 0) goto L_0x0012
                r2 = 0
                byte r2 = r3[r2]
            L_0x0012:
                return r2
            */
            throw new UnsupportedOperationException("Method not decompiled: com.tsmclient.smartcard.model.ConfigRules.ReadRecordInstruction.get(byte, java.lang.String):byte");
        }
    }
}
