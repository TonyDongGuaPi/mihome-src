package org.mp4parser.muxer.container.mp4;

import com.google.android.exoplayer2.C;
import com.taobao.weex.el.parse.Operators;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.channels.ReadableByteChannel;
import org.mp4parser.IsoFile;
import org.mp4parser.boxes.iso14496.part12.SchemeTypeBox;
import org.mp4parser.boxes.iso14496.part12.TrackBox;
import org.mp4parser.muxer.CencMp4TrackImplImpl;
import org.mp4parser.muxer.FileRandomAccessSourceImpl;
import org.mp4parser.muxer.Movie;
import org.mp4parser.muxer.Mp4TrackImpl;
import org.mp4parser.muxer.RandomAccessSource;
import org.mp4parser.muxer.Track;
import org.mp4parser.support.AbstractContainerBox;
import org.mp4parser.tools.Path;

public class MovieCreator {
    public static Movie a(String str) throws IOException {
        File file = new File(str);
        FileInputStream fileInputStream = new FileInputStream(file);
        Movie a2 = a(fileInputStream.getChannel(), new FileRandomAccessSourceImpl(new RandomAccessFile(file, "r")), str);
        fileInputStream.close();
        return a2;
    }

    public static Movie a(ReadableByteChannel readableByteChannel, RandomAccessSource randomAccessSource, String str) throws IOException {
        IsoFile isoFile = new IsoFile(readableByteChannel);
        Movie movie = new Movie();
        for (TrackBox next : isoFile.d().a(TrackBox.class)) {
            SchemeTypeBox schemeTypeBox = (SchemeTypeBox) Path.a((AbstractContainerBox) next, "mdia[0]/minf[0]/stbl[0]/stsd[0]/enc.[0]/sinf[0]/schm[0]");
            if (schemeTypeBox == null || (!schemeTypeBox.e().equals(C.CENC_TYPE_cenc) && !schemeTypeBox.e().equals(C.CENC_TYPE_cbc1))) {
                long g = next.d().g();
                movie.a((Track) new Mp4TrackImpl(g, isoFile, randomAccessSource, str + Operators.ARRAY_START_STR + next.d().g() + Operators.ARRAY_END_STR));
            } else {
                long g2 = next.d().g();
                movie.a((Track) new CencMp4TrackImplImpl(g2, isoFile, randomAccessSource, str + Operators.ARRAY_START_STR + next.d().g() + Operators.ARRAY_END_STR));
            }
        }
        movie.a(isoFile.d().f().k());
        return movie;
    }
}
