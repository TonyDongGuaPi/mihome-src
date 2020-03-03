package com.alipay.zoloz.toyger.bean;

import android.graphics.Bitmap;
import com.alipay.zoloz.toyger.algorithm.TGFaceAttr;
import com.alipay.zoloz.toyger.algorithm.TGFaceState;
import com.alipay.zoloz.toyger.algorithm.TGFrame;
import com.alipay.zoloz.toyger.upload.UploadContent;
import com.taobao.weex.el.parse.Operators;

public class ToygerFrame {
    public Bitmap bestBitmap;
    public ToygerError error = ToygerError.NORMAL;
    public FrameType frameType = FrameType.INIT;
    public TGFaceAttr tgFaceAttr = new TGFaceAttr();
    public TGFaceState tgFaceState = new TGFaceState();
    public TGFrame tgFrame = new TGFrame();
    public UploadContent uploadContent;

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("ToygerFrame{frameType=");
        sb.append(this.frameType);
        sb.append(", error=");
        sb.append(this.error);
        sb.append(", tgFrame=");
        sb.append(this.tgFrame != null ? "***" : "null");
        sb.append(", tgFaceState=");
        sb.append(this.tgFaceState != null ? "***" : "null");
        sb.append(", tgFaceAttr=");
        sb.append(this.tgFaceAttr != null ? "***" : "null");
        sb.append(", uploadContent=");
        sb.append(this.uploadContent);
        sb.append(", bestBitmap=");
        sb.append(this.bestBitmap != null ? "***" : "null");
        sb.append(Operators.BLOCK_END);
        return sb.toString();
    }
}
