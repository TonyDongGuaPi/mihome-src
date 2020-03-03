package tv.danmaku.ijk.media.player.misc;

import android.annotation.TargetApi;
import android.media.MediaFormat;
import android.media.MediaPlayer;
import android.os.Build;
import com.google.android.exoplayer2.C;
import com.taobao.weex.el.parse.Operators;

public class AndroidTrackInfo implements ITrackInfo {
    private final MediaPlayer.TrackInfo mTrackInfo;

    public static AndroidTrackInfo[] fromMediaPlayer(MediaPlayer mediaPlayer) {
        if (Build.VERSION.SDK_INT >= 16) {
            return fromTrackInfo(mediaPlayer.getTrackInfo());
        }
        return null;
    }

    private static AndroidTrackInfo[] fromTrackInfo(MediaPlayer.TrackInfo[] trackInfoArr) {
        if (trackInfoArr == null) {
            return null;
        }
        AndroidTrackInfo[] androidTrackInfoArr = new AndroidTrackInfo[trackInfoArr.length];
        for (int i = 0; i < trackInfoArr.length; i++) {
            androidTrackInfoArr[i] = new AndroidTrackInfo(trackInfoArr[i]);
        }
        return androidTrackInfoArr;
    }

    private AndroidTrackInfo(MediaPlayer.TrackInfo trackInfo) {
        this.mTrackInfo = trackInfo;
    }

    @TargetApi(19)
    public IMediaFormat getFormat() {
        MediaFormat format;
        if (this.mTrackInfo == null || Build.VERSION.SDK_INT < 19 || (format = this.mTrackInfo.getFormat()) == null) {
            return null;
        }
        return new AndroidMediaFormat(format);
    }

    @TargetApi(16)
    public String getLanguage() {
        if (this.mTrackInfo == null) {
            return C.LANGUAGE_UNDETERMINED;
        }
        return this.mTrackInfo.getLanguage();
    }

    @TargetApi(16)
    public int getTrackType() {
        if (this.mTrackInfo == null) {
            return 0;
        }
        return this.mTrackInfo.getTrackType();
    }

    @TargetApi(16)
    public String toString() {
        StringBuilder sb = new StringBuilder(128);
        sb.append(getClass().getSimpleName());
        sb.append(Operators.BLOCK_START);
        if (this.mTrackInfo != null) {
            sb.append(this.mTrackInfo.toString());
        } else {
            sb.append("null");
        }
        sb.append(Operators.BLOCK_END);
        return sb.toString();
    }

    @TargetApi(16)
    public String getInfoInline() {
        return this.mTrackInfo != null ? this.mTrackInfo.toString() : "null";
    }
}
