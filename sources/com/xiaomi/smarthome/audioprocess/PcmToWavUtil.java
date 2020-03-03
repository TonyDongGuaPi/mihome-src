package com.xiaomi.smarthome.audioprocess;

import android.media.AudioRecord;
import cn.com.fmsh.tsm.business.constants.Constants;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class PcmToWavUtil {

    /* renamed from: a  reason: collision with root package name */
    private int f13753a;
    private int b;
    private int c;
    private int d;

    public PcmToWavUtil() {
        this.b = 8000;
        this.c = 12;
        this.d = 2;
        this.f13753a = AudioRecord.getMinBufferSize(this.b, this.c, this.d);
    }

    public PcmToWavUtil(int i, int i2, int i3) {
        this.b = 8000;
        this.c = 12;
        this.d = 2;
        this.b = i;
        this.c = i2;
        this.d = i3;
        this.f13753a = AudioRecord.getMinBufferSize(this.b, this.c, this.d);
    }

    public void a(String str, String str2) {
        long j = (long) this.b;
        long j2 = (long) (((this.b * 16) * 2) / 8);
        byte[] bArr = new byte[this.f13753a];
        try {
            FileInputStream fileInputStream = new FileInputStream(str);
            FileOutputStream fileOutputStream = new FileOutputStream(str2);
            long size = fileInputStream.getChannel().size();
            a(fileOutputStream, size, size + 36, j, 2, j2);
            while (fileInputStream.read(bArr) != -1) {
                fileOutputStream.write(bArr);
            }
            fileInputStream.close();
            fileOutputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void a(FileOutputStream fileOutputStream, long j, long j2, long j3, int i, long j4) throws IOException {
        fileOutputStream.write(new byte[]{Constants.TagName.TERMINAL_BACK_QUESTION_FLAG, Constants.TagName.ORDER_BRIEF_INFO, Constants.TagName.TERMINAL_BASEBAND_VERSION, Constants.TagName.TERMINAL_BASEBAND_VERSION, (byte) ((int) (j2 & 255)), (byte) ((int) ((j2 >> 8) & 255)), (byte) ((int) ((j2 >> 16) & 255)), (byte) ((int) ((j2 >> 24) & 255)), 87, Constants.TagName.TERMINAL_BACK_CONTENT, Constants.TagName.QUERY_DATA_SORT_TYPE, Constants.TagName.TERMINAL_MODEL_NUMBER, 102, Constants.TagName.PUBLISH_END_TIME, Constants.TagName.ELECTRONIC_USE_TYPE, 32, 16, 0, 0, 0, 1, 0, (byte) i, 0, (byte) ((int) (j3 & 255)), (byte) ((int) ((j3 >> 8) & 255)), (byte) ((int) ((j3 >> 16) & 255)), (byte) ((int) ((j3 >> 24) & 255)), (byte) ((int) (j4 & 255)), (byte) ((int) ((j4 >> 8) & 255)), (byte) ((int) ((j4 >> 16) & 255)), (byte) ((int) ((j4 >> 24) & 255)), 4, 0, 16, 0, Constants.TagName.PAY_ORDER_LIST, 97, Constants.TagName.ELECTRONIC_USE_TYPE, 97, (byte) ((int) (j & 255)), (byte) ((int) ((j >> 8) & 255)), (byte) ((int) ((j >> 16) & 255)), (byte) ((int) ((j >> 24) & 255))}, 0, 44);
    }
}
