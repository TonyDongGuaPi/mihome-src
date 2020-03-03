package tv.danmaku.ijk.media.player.misc;

import android.annotation.TargetApi;
import android.media.MediaFormat;
import com.taobao.weex.el.parse.Operators;

public class AndroidMediaFormat implements IMediaFormat {
    private final MediaFormat mMediaFormat;

    public AndroidMediaFormat(MediaFormat mediaFormat) {
        this.mMediaFormat = mediaFormat;
    }

    @TargetApi(16)
    public int getInteger(String str) {
        if (this.mMediaFormat == null) {
            return 0;
        }
        return this.mMediaFormat.getInteger(str);
    }

    @TargetApi(16)
    public String getString(String str) {
        if (this.mMediaFormat == null) {
            return null;
        }
        return this.mMediaFormat.getString(str);
    }

    @TargetApi(16)
    public String toString() {
        StringBuilder sb = new StringBuilder(128);
        sb.append(getClass().getName());
        sb.append(Operators.BLOCK_START);
        if (this.mMediaFormat != null) {
            sb.append(this.mMediaFormat.toString());
        } else {
            sb.append("null");
        }
        sb.append(Operators.BLOCK_END);
        return sb.toString();
    }
}
