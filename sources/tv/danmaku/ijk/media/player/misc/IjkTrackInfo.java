package tv.danmaku.ijk.media.player.misc;

import android.text.TextUtils;
import com.facebook.share.internal.ShareConstants;
import com.google.android.exoplayer2.C;
import com.taobao.weex.el.parse.Operators;
import tv.danmaku.ijk.media.player.IjkMediaMeta;

public class IjkTrackInfo implements ITrackInfo {
    private IjkMediaMeta.IjkStreamMeta mStreamMeta;
    private int mTrackType = 0;

    public IjkTrackInfo(IjkMediaMeta.IjkStreamMeta ijkStreamMeta) {
        this.mStreamMeta = ijkStreamMeta;
    }

    public void setMediaMeta(IjkMediaMeta.IjkStreamMeta ijkStreamMeta) {
        this.mStreamMeta = ijkStreamMeta;
    }

    public IMediaFormat getFormat() {
        return new IjkMediaFormat(this.mStreamMeta);
    }

    public String getLanguage() {
        return (this.mStreamMeta == null || TextUtils.isEmpty(this.mStreamMeta.mLanguage)) ? C.LANGUAGE_UNDETERMINED : this.mStreamMeta.mLanguage;
    }

    public int getTrackType() {
        return this.mTrackType;
    }

    public void setTrackType(int i) {
        this.mTrackType = i;
    }

    public String toString() {
        return getClass().getSimpleName() + Operators.BLOCK_START + getInfoInline() + "}";
    }

    public String getInfoInline() {
        StringBuilder sb = new StringBuilder(128);
        switch (this.mTrackType) {
            case 1:
                sb.append("VIDEO");
                sb.append(", ");
                sb.append(this.mStreamMeta.getCodecShortNameInline());
                sb.append(", ");
                sb.append(this.mStreamMeta.getBitrateInline());
                sb.append(", ");
                sb.append(this.mStreamMeta.getResolutionInline());
                break;
            case 2:
                sb.append(Constants.J);
                sb.append(", ");
                sb.append(this.mStreamMeta.getCodecShortNameInline());
                sb.append(", ");
                sb.append(this.mStreamMeta.getBitrateInline());
                sb.append(", ");
                sb.append(this.mStreamMeta.getSampleRateInline());
                break;
            case 3:
                sb.append("TIMEDTEXT");
                sb.append(", ");
                sb.append(this.mStreamMeta.mLanguage);
                break;
            case 4:
                sb.append(ShareConstants.SUBTITLE);
                break;
            default:
                sb.append("UNKNOWN");
                break;
        }
        return sb.toString();
    }
}
