package com.google.android.exoplayer2.extractor.ogg;

import cn.com.fmsh.tsm.business.constants.Constants;
import com.google.android.exoplayer2.Format;
import com.google.android.exoplayer2.drm.DrmInitData;
import com.google.android.exoplayer2.extractor.ogg.StreamReader;
import com.google.android.exoplayer2.util.MimeTypes;
import com.google.android.exoplayer2.util.ParsableByteArray;
import com.google.android.exoplayer2.util.Util;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

final class OpusReader extends StreamReader {
    private static final int DEFAULT_SEEK_PRE_ROLL_SAMPLES = 3840;
    private static final int OPUS_CODE = Util.getIntegerCodeForString("Opus");
    private static final byte[] OPUS_SIGNATURE = {Constants.TagName.CP_NO, Constants.TagName.ELECTRONIC_ID, Constants.TagName.ELECTRONIC_TRANSFER_FLAG, Constants.TagName.ELECTRONIC_TYPE, Constants.TagName.BUSINESS_ORDER_TYPE, Constants.TagName.ORDER_TYPE, 97, Constants.TagName.PAY_ORDER_LIST};
    private static final int SAMPLE_RATE = 48000;
    private boolean headerRead;

    OpusReader() {
    }

    public static boolean verifyBitstreamType(ParsableByteArray parsableByteArray) {
        if (parsableByteArray.bytesLeft() < OPUS_SIGNATURE.length) {
            return false;
        }
        byte[] bArr = new byte[OPUS_SIGNATURE.length];
        parsableByteArray.readBytes(bArr, 0, OPUS_SIGNATURE.length);
        return Arrays.equals(bArr, OPUS_SIGNATURE);
    }

    /* access modifiers changed from: protected */
    public void reset(boolean z) {
        super.reset(z);
        if (z) {
            this.headerRead = false;
        }
    }

    /* access modifiers changed from: protected */
    public long preparePayload(ParsableByteArray parsableByteArray) {
        return convertTimeToGranule(getPacketDurationUs(parsableByteArray.data));
    }

    /* access modifiers changed from: protected */
    public boolean readHeaders(ParsableByteArray parsableByteArray, long j, StreamReader.SetupData setupData) {
        boolean z = true;
        if (!this.headerRead) {
            byte[] copyOf = Arrays.copyOf(parsableByteArray.data, parsableByteArray.limit());
            byte b = copyOf[9] & 255;
            ArrayList arrayList = new ArrayList(3);
            arrayList.add(copyOf);
            putNativeOrderLong(arrayList, ((copyOf[11] & 255) << 8) | (copyOf[10] & 255));
            putNativeOrderLong(arrayList, 3840);
            setupData.format = Format.createAudioSampleFormat((String) null, MimeTypes.AUDIO_OPUS, (String) null, -1, -1, b, 48000, arrayList, (DrmInitData) null, 0, (String) null);
            this.headerRead = true;
            return true;
        }
        if (parsableByteArray.readInt() != OPUS_CODE) {
            z = false;
        }
        parsableByteArray.setPosition(0);
        return z;
    }

    private void putNativeOrderLong(List<byte[]> list, int i) {
        list.add(ByteBuffer.allocate(8).order(ByteOrder.nativeOrder()).putLong((((long) i) * 1000000000) / 48000).array());
    }

    private long getPacketDurationUs(byte[] bArr) {
        byte b;
        byte b2 = bArr[0] & 255;
        switch (b2 & 3) {
            case 0:
                b = 1;
                break;
            case 1:
            case 2:
                b = 2;
                break;
            default:
                b = bArr[1] & Constants.TagName.CARD_APP_ACTIVATION_STATUS;
                break;
        }
        int i = b2 >> 3;
        int i2 = i & 3;
        return ((long) b) * ((long) (i >= 16 ? 2500 << i2 : i >= 12 ? 10000 << (i2 & 1) : i2 == 3 ? 60000 : 10000 << i2));
    }
}
