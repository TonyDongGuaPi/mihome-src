package cn.com.fmsh.util.socket;

import java.io.DataInputStream;
import java.io.IOException;

public interface DataLengthHandle {
    int getDataSize(DataInputStream dataInputStream) throws IOException;

    byte[] initDataSize(int i);
}
