package org.cybergarage.http;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Calendar;

public class HTTPSocket {
    private InputStream sockIn = null;
    private OutputStream sockOut = null;
    private Socket socket = null;

    public HTTPSocket(Socket socket2) {
        setSocket(socket2);
        open();
    }

    public HTTPSocket(HTTPSocket hTTPSocket) {
        setSocket(hTTPSocket.getSocket());
        setInputStream(hTTPSocket.getInputStream());
        setOutputStream(hTTPSocket.getOutputStream());
    }

    public void finalize() {
        close();
    }

    private void setSocket(Socket socket2) {
        this.socket = socket2;
    }

    public Socket getSocket() {
        return this.socket;
    }

    public String getLocalAddress() {
        return getSocket().getLocalAddress().getHostAddress();
    }

    public int getLocalPort() {
        return getSocket().getLocalPort();
    }

    private void setInputStream(InputStream inputStream) {
        this.sockIn = inputStream;
    }

    public InputStream getInputStream() {
        return this.sockIn;
    }

    private void setOutputStream(OutputStream outputStream) {
        this.sockOut = outputStream;
    }

    private OutputStream getOutputStream() {
        return this.sockOut;
    }

    public boolean open() {
        Socket socket2 = getSocket();
        try {
            this.sockIn = socket2.getInputStream();
            this.sockOut = socket2.getOutputStream();
            return true;
        } catch (Exception unused) {
            return false;
        }
    }

    public boolean close() {
        try {
            if (this.sockIn != null) {
                this.sockIn.close();
            }
            if (this.sockOut != null) {
                this.sockOut.close();
            }
            getSocket().close();
            return true;
        } catch (Exception unused) {
            return false;
        }
    }

    private boolean post(HTTPResponse hTTPResponse, byte[] bArr, long j, long j2, boolean z) {
        hTTPResponse.setDate(Calendar.getInstance());
        OutputStream outputStream = getOutputStream();
        try {
            hTTPResponse.setContentLength(j2);
            outputStream.write(hTTPResponse.getHeader().getBytes());
            outputStream.write("\r\n".getBytes());
            if (z) {
                outputStream.flush();
                return true;
            }
            boolean isChunked = hTTPResponse.isChunked();
            if (isChunked) {
                outputStream.write(Long.toHexString(j2).getBytes());
                outputStream.write("\r\n".getBytes());
            }
            outputStream.write(bArr, (int) j, (int) j2);
            if (isChunked) {
                outputStream.write("\r\n".getBytes());
                outputStream.write("0".getBytes());
                outputStream.write("\r\n".getBytes());
            }
            outputStream.flush();
            return true;
        } catch (Exception unused) {
            return false;
        }
    }

    private boolean post(HTTPResponse hTTPResponse, InputStream inputStream, long j, long j2, boolean z) {
        HTTPResponse hTTPResponse2 = hTTPResponse;
        InputStream inputStream2 = inputStream;
        long j3 = j2;
        hTTPResponse2.setDate(Calendar.getInstance());
        OutputStream outputStream = getOutputStream();
        try {
            hTTPResponse2.setContentLength(j3);
            outputStream.write(hTTPResponse.getHeader().getBytes());
            outputStream.write("\r\n".getBytes());
            if (z) {
                outputStream.flush();
                return true;
            }
            boolean isChunked = hTTPResponse.isChunked();
            long j4 = 0;
            if (0 < j) {
                inputStream.skip(j);
            }
            int chunkSize = HTTP.getChunkSize();
            byte[] bArr = new byte[chunkSize];
            long j5 = (long) chunkSize;
            int read = inputStream2.read(bArr, 0, (int) (j5 < j3 ? j5 : j3));
            while (read > 0 && j4 < j3) {
                if (isChunked) {
                    outputStream.write(Long.toHexString((long) read).getBytes());
                    outputStream.write("\r\n".getBytes());
                }
                outputStream.write(bArr, 0, read);
                if (isChunked) {
                    outputStream.write("\r\n".getBytes());
                }
                j4 += (long) read;
                long j6 = j3 - j4;
                if (j5 < j6) {
                    j6 = j5;
                }
                read = inputStream2.read(bArr, 0, (int) j6);
            }
            if (isChunked) {
                outputStream.write("0".getBytes());
                outputStream.write("\r\n".getBytes());
            }
            outputStream.flush();
            return true;
        } catch (Exception unused) {
            return false;
        }
    }

    public boolean post(HTTPResponse hTTPResponse, long j, long j2, boolean z) {
        if (hTTPResponse.hasContentInputStream()) {
            return post(hTTPResponse, hTTPResponse.getContentInputStream(), j, j2, z);
        }
        return post(hTTPResponse, hTTPResponse.getContent(), j, j2, z);
    }
}
