package org.mp4parser.muxer.tracks;

import java.util.List;
import java.util.UUID;
import org.mp4parser.boxes.iso23001.part7.CencSampleAuxiliaryDataFormat;
import org.mp4parser.muxer.Track;

public interface CencEncryptedTrack extends Track {
    UUID i();

    boolean j();

    List<CencSampleAuxiliaryDataFormat> k();
}
