package com.alipay.zoloz.toyger.face;

import android.util.Log;
import com.alibaba.fastjson.JSON;
import com.alipay.zoloz.toyger.algorithm.TGFrame;
import com.alipay.zoloz.toyger.blob.Blob;
import com.alipay.zoloz.toyger.blob.BlobElem;
import com.alipay.zoloz.toyger.blob.BlobManager;
import com.alipay.zoloz.toyger.blob.Content;
import com.alipay.zoloz.toyger.blob.FaceInfo;
import com.alipay.zoloz.toyger.blob.Meta;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class FaceBlobManagerJson extends FaceBlobManager {
    private List<BlobElem> mMonitorBlobElems;

    public boolean isUTF8() {
        return true;
    }

    public FaceBlobManagerJson() {
    }

    public FaceBlobManagerJson(ToygerFaceBlobConfig toygerFaceBlobConfig) {
        super(toygerFaceBlobConfig);
    }

    public byte[] generateBlob(List<ToygerFaceInfo> list, Map<String, Object> map) {
        Meta generateMeta = generateMeta(list, map);
        ArrayList arrayList = new ArrayList();
        for (ToygerFaceInfo next : list) {
            BlobElem blobElem = new BlobElem();
            blobElem.type = "face";
            blobElem.subType = getBlobElemType(next);
            blobElem.version = "1.0";
            blobElem.idx = 0;
            blobElem.content = processFrame(next.frame);
            if (blobElem.content == null) {
                Log.e("BlobManager", "failed to generate element content");
                return null;
            }
            blobElem.faceInfos = new ArrayList();
            blobElem.faceInfos.add(generateFaceInfo(next));
            arrayList.add(blobElem);
        }
        Blob blob = new Blob();
        blob.blobElem = arrayList;
        blob.blobVersion = "1.0";
        Content content = new Content();
        content.blob = blob;
        content.meta = generateMeta;
        return JSON.toJSONString(content).getBytes();
    }

    public void addMonitorImage(TGFrame tGFrame) {
        BlobElem generateMonitorBlob = generateMonitorBlob(tGFrame);
        if (this.mMonitorBlobElems == null) {
            this.mMonitorBlobElems = new ArrayList();
        }
        if (generateMonitorBlob != null) {
            this.mMonitorBlobElems.add(generateMonitorBlob);
        }
    }

    public byte[] getMonitorBlob() {
        Meta generateMeta = generateMeta((List<ToygerFaceInfo>) null, (Map<String, Object>) null);
        Blob blob = new Blob();
        blob.blobElem = this.mMonitorBlobElems;
        blob.blobVersion = "1.0";
        Content content = new Content();
        content.blob = blob;
        content.meta = generateMeta;
        String jSONString = JSON.toJSONString(content);
        this.mMonitorBlobElems.clear();
        return jSONString.getBytes();
    }

    private BlobElem generateMonitorBlob(TGFrame tGFrame) {
        BlobElem blobElem = new BlobElem();
        blobElem.type = "face";
        blobElem.subType = BlobManager.SUB_TYPE_SURVEILLANCE;
        blobElem.version = "1.0";
        blobElem.idx = this.mMonitorBlobElems == null ? 0 : this.mMonitorBlobElems.size();
        blobElem.content = processFrame(tGFrame, 160, 15);
        if (blobElem.content == null) {
            Log.e("BlobManager", "failed to generate element content");
            return null;
        }
        Log.i("BlobManager", "monitor image length:" + blobElem.content.length);
        return blobElem;
    }

    /* access modifiers changed from: protected */
    public FaceInfo generateFaceInfo(ToygerFaceInfo toygerFaceInfo) {
        FaceInfo faceInfo = new FaceInfo();
        int i = toygerFaceInfo.frame.rotation % 180 == 0 ? toygerFaceInfo.frame.width : toygerFaceInfo.frame.height;
        int i2 = i == toygerFaceInfo.frame.width ? toygerFaceInfo.frame.height : toygerFaceInfo.frame.width;
        int intValue = (i <= this.config.getDesiredWidth().intValue() || this.config.getDesiredWidth().intValue() <= 0) ? i : this.config.getDesiredWidth().intValue();
        faceInfo.rect = convertFaceRegion(((ToygerFaceAttr) toygerFaceInfo.attr).region(), intValue, (int) ((((float) intValue) / ((float) i)) * ((float) i2)), toygerFaceInfo.frame.rotation, false);
        faceInfo.quality = ((ToygerFaceAttr) toygerFaceInfo.attr).quality();
        return faceInfo;
    }

    private Meta generateMeta(List<ToygerFaceInfo> list, Map<String, Object> map) {
        Meta meta = new Meta();
        meta.type = "zface";
        meta.score = map;
        meta.serialize = 1;
        return meta;
    }
}
