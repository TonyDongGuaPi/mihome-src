package org.mp4parser.streaming.output.mp4;

import java.util.LinkedList;
import org.mp4parser.Box;
import org.mp4parser.boxes.iso14496.part12.DataEntryUrlBox;
import org.mp4parser.boxes.iso14496.part12.DataInformationBox;
import org.mp4parser.boxes.iso14496.part12.DataReferenceBox;
import org.mp4parser.boxes.iso14496.part12.FileTypeBox;
import org.mp4parser.boxes.iso14496.part12.HandlerBox;
import org.mp4parser.boxes.iso14496.part12.HintMediaHeaderBox;
import org.mp4parser.boxes.iso14496.part12.MediaBox;
import org.mp4parser.boxes.iso14496.part12.MediaInformationBox;
import org.mp4parser.boxes.iso14496.part12.NullMediaHeaderBox;
import org.mp4parser.boxes.iso14496.part12.SampleSizeBox;
import org.mp4parser.boxes.iso14496.part12.SampleTableBox;
import org.mp4parser.boxes.iso14496.part12.SampleToChunkBox;
import org.mp4parser.boxes.iso14496.part12.SoundMediaHeaderBox;
import org.mp4parser.boxes.iso14496.part12.StaticChunkOffsetBox;
import org.mp4parser.boxes.iso14496.part12.SubtitleMediaHeaderBox;
import org.mp4parser.boxes.iso14496.part12.TimeToSampleBox;
import org.mp4parser.boxes.iso14496.part12.TrackBox;
import org.mp4parser.boxes.iso14496.part12.TrackHeaderBox;
import org.mp4parser.boxes.iso14496.part12.VideoMediaHeaderBox;
import org.mp4parser.boxes.sampleentry.VisualSampleEntry;
import org.mp4parser.streaming.StreamingTrack;
import org.mp4parser.streaming.extensions.DimensionTrackExtension;
import org.mp4parser.streaming.extensions.TrackIdTrackExtension;

public abstract class DefaultBoxes {
    /* access modifiers changed from: protected */
    public abstract Box b();

    /* access modifiers changed from: protected */
    public abstract Box c(StreamingTrack streamingTrack);

    public Box a() {
        LinkedList linkedList = new LinkedList();
        linkedList.add("isom");
        linkedList.add("iso2");
        linkedList.add(VisualSampleEntry.c);
        linkedList.add("iso6");
        linkedList.add("mp41");
        return new FileTypeBox("isom", 512, linkedList);
    }

    /* access modifiers changed from: protected */
    public Box a(StreamingTrack streamingTrack) {
        HandlerBox handlerBox = new HandlerBox();
        handlerBox.a(streamingTrack.b());
        return handlerBox;
    }

    /* access modifiers changed from: protected */
    public Box b(StreamingTrack streamingTrack) {
        MediaBox mediaBox = new MediaBox();
        mediaBox.a(c(streamingTrack));
        mediaBox.a(a(streamingTrack));
        mediaBox.a(d(streamingTrack));
        return mediaBox;
    }

    /* access modifiers changed from: protected */
    public Box d(StreamingTrack streamingTrack) {
        MediaInformationBox mediaInformationBox = new MediaInformationBox();
        if (streamingTrack.b().equals("vide")) {
            mediaInformationBox.a((Box) new VideoMediaHeaderBox());
        } else if (streamingTrack.b().equals("soun")) {
            mediaInformationBox.a((Box) new SoundMediaHeaderBox());
        } else if (streamingTrack.b().equals("text")) {
            mediaInformationBox.a((Box) new NullMediaHeaderBox());
        } else if (streamingTrack.b().equals("subt")) {
            mediaInformationBox.a((Box) new SubtitleMediaHeaderBox());
        } else if (streamingTrack.b().equals("hint")) {
            mediaInformationBox.a((Box) new HintMediaHeaderBox());
        } else if (streamingTrack.b().equals("sbtl")) {
            mediaInformationBox.a((Box) new NullMediaHeaderBox());
        }
        mediaInformationBox.a((Box) c());
        mediaInformationBox.a(e(streamingTrack));
        return mediaInformationBox;
    }

    /* access modifiers changed from: protected */
    public Box e(StreamingTrack streamingTrack) {
        SampleTableBox sampleTableBox = new SampleTableBox();
        sampleTableBox.a((Box) streamingTrack.d());
        sampleTableBox.a((Box) new TimeToSampleBox());
        sampleTableBox.a((Box) new SampleToChunkBox());
        sampleTableBox.a((Box) new SampleSizeBox());
        sampleTableBox.a((Box) new StaticChunkOffsetBox());
        return sampleTableBox;
    }

    /* access modifiers changed from: protected */
    public DataInformationBox c() {
        DataInformationBox dataInformationBox = new DataInformationBox();
        DataReferenceBox dataReferenceBox = new DataReferenceBox();
        dataInformationBox.a((Box) dataReferenceBox);
        DataEntryUrlBox dataEntryUrlBox = new DataEntryUrlBox();
        dataEntryUrlBox.b(1);
        dataReferenceBox.a((Box) dataEntryUrlBox);
        return dataInformationBox;
    }

    /* access modifiers changed from: protected */
    public Box f(StreamingTrack streamingTrack) {
        TrackBox trackBox = new TrackBox();
        trackBox.a(g(streamingTrack));
        trackBox.a(b(streamingTrack));
        return trackBox;
    }

    /* access modifiers changed from: protected */
    public Box g(StreamingTrack streamingTrack) {
        TrackHeaderBox trackHeaderBox = new TrackHeaderBox();
        trackHeaderBox.a(((TrackIdTrackExtension) streamingTrack.a(TrackIdTrackExtension.class)).a());
        DimensionTrackExtension dimensionTrackExtension = (DimensionTrackExtension) streamingTrack.a(DimensionTrackExtension.class);
        if (dimensionTrackExtension != null) {
            trackHeaderBox.b((double) dimensionTrackExtension.b());
            trackHeaderBox.a((double) dimensionTrackExtension.a());
        }
        return trackHeaderBox;
    }
}
