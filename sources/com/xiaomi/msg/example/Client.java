package com.xiaomi.msg.example;

import com.mi.global.shop.model.Tags;
import com.xiaomi.msg.XMDTransceiver;
import com.xiaomi.msg.data.XMDPacket;
import com.xiaomi.msg.example.handler.ClientConnectionHandler;
import com.xiaomi.msg.example.handler.ClientDatagramHandler;
import com.xiaomi.msg.example.handler.ClientStreamHandler;
import com.xiaomi.msg.handler.ConnectionHandler;
import com.xiaomi.msg.handler.DatagramHandler;
import com.xiaomi.msg.handler.StreamHandler;
import com.xiaomi.msg.logger.MIMCLog;
import com.xiaomi.smarthome.framework.api.UserConfig;
import java.io.IOException;
import java.io.PrintStream;
import org.junit.Assert;

public class Client {

    /* renamed from: a  reason: collision with root package name */
    public static byte[][] f12099a = null;
    public static String b = "My house is perfect. By great good fortune I have found a housekeeper no less to my \nmind, a low-voiced, light-footed woman of discreet age, strong and deft enough to render me all the service \nI require, and not afraid of loneliness. She rises very early. By my breakfast-time there remains little to \nbe done under the roof save dressing of meals. Very rarely do I hear even a clink of crockery; never the closing \nof a door or window. Oh, blessed silence! My house is perfect. Just large enough to allow the grace of order \nin domestic circumstance; just that superfluity of inner space, to lack which is to be less than at one's ease.\n The fabric is sound; the work in wood and plaster tells of a more leisurely and a more honest age than ours. The \nstairs do not creak under my step; I am attacked by no unkindly draught; I can open or close a window without \nmuscle-ache. As to such trifles as the color and device of wall-paper, I confess my indifference; be the walls only \nplain, and I am satisfied. The first thing in one's home is comfort; let beauty of detail be added if one has the \nmeans, the patience, the eye. To me, this little book-room is beautiful, and chiefly because it is home. \nThrough the greater part of life I was homeless. Many places have I lived, some which my soul disliked, and \nsome which pleased me well; but never till now with that sense of security which makes a home. At any moment I \nmight have been driven forth by evil accident, by disturbing necessity. For all that time did I say within myself: \nSome day, perchance, I shall have a home; yet the \"perchance\" had more and more of emphasis as life went on, \nand at the moment when fate was secretly smiling on me, I had all but abandoned hope. I have my home at last. This house \nis mine on a lease of a score of years. So long I certainly shall not live; but, if I did, even so long should I have the \nmoney to pay my rent and buy my food. I am no cosmopolite. Were I to think that I should die away from England, \nthe thought would be dreadful to me. And in England, this is the place of my choice; this is my home.\n";
    private static final String c = "Client";

    /* JADX INFO: finally extract failed */
    public static void a(String[] strArr) throws InterruptedException, IOException {
        int i;
        String[] strArr2 = {"10.38.162.117", "11000", UserConfig.g, Tags.LuckyShake.VALUE_SUCCESS_CODE, UserConfig.g};
        if (strArr2.length != 5) {
            System.out.println("params: [ip] [port] [count] [size] [qps]");
            return;
        }
        try {
            MIMCLog.a("Z:\\codes\\msg-libraries-java\\xmdtransceiver\\files\\logs");
            MIMCLog.b("clientLog.txt");
            MIMCLog.a(true);
            MIMCLog.b(1);
            XMDTransceiver xMDTransceiver = new XMDTransceiver(30000);
            ClientDatagramHandler clientDatagramHandler = new ClientDatagramHandler();
            ClientConnectionHandler clientConnectionHandler = new ClientConnectionHandler();
            ClientStreamHandler clientStreamHandler = new ClientStreamHandler();
            xMDTransceiver.a((DatagramHandler) clientDatagramHandler);
            xMDTransceiver.a((ConnectionHandler) clientConnectionHandler);
            xMDTransceiver.a((StreamHandler) clientStreamHandler);
            clientStreamHandler.a(xMDTransceiver);
            int parseInt = Integer.parseInt(strArr2[2]);
            int parseInt2 = Integer.parseInt(strArr2[3]);
            int parseInt3 = Integer.parseInt(strArr2[4]);
            xMDTransceiver.b();
            f12099a = new byte[parseInt][];
            String str = strArr2[0];
            int parseInt4 = Integer.parseInt(strArr2[1]);
            int i2 = parseInt2;
            int i3 = parseInt;
            long a2 = xMDTransceiver.a(str, parseInt4, (byte[]) null, 5, (Object) null);
            MIMCLog.b("Client", String.format("client iP: %s:%d, count:%d, size:%d, qps:%d, connid:%d", new Object[]{xMDTransceiver.a().getAddress().getHostAddress(), Integer.valueOf(xMDTransceiver.a().getPort()), Integer.valueOf(i3), Integer.valueOf(i2), Integer.valueOf(parseInt3), Long.valueOf(a2)}));
            Thread.sleep(2000);
            Assert.assertTrue(clientConnectionHandler.b());
            if (clientConnectionHandler.d() != a2) {
                MIMCLog.d("Client", "ConnId not equal!");
                System.exit(0);
                return;
            }
            long j = a2;
            short a3 = xMDTransceiver.a(a2, XMDPacket.StreamType.ACK_STREAM, 100, true);
            int i4 = 0;
            while (i4 < i3) {
                int i5 = i2;
                f12099a[i4] = new byte[i5];
                a(f12099a[i4], i5, i4);
                Thread.sleep((long) (1000 / parseInt3));
                xMDTransceiver.a(j, a3, f12099a[i4], true, XMDPacket.DataPriority.P2, 3, "aaa");
                i4++;
                clientStreamHandler = clientStreamHandler;
                i2 = i5;
            }
            Thread.sleep((long) (i3 * 5));
            clientStreamHandler.f12104a.b();
            Thread.sleep(2000);
            xMDTransceiver.b(j);
            Thread.sleep(2000);
            int[] iArr = new int[1];
            a(iArr);
            PrintStream printStream = System.out;
            printStream.println("Not Equal number:" + iArr[0] + " total number:" + f12099a.length);
            i = 0;
            System.exit(i);
        } catch (Exception e) {
            e.printStackTrace();
            i = 0;
        } catch (Throwable th) {
            System.exit(0);
            throw th;
        }
    }

    public static boolean a(int[] iArr) {
        PrintStream printStream = System.out;
        printStream.println("Total recv num is " + ClientStreamHandler.b.size());
        int i = 0;
        boolean z = true;
        while (i < f12099a.length) {
            String str = new String(f12099a[i]);
            if (!ClientStreamHandler.b.contains(str)) {
                PrintStream printStream2 = System.out;
                printStream2.println("Not recv i=" + i);
                iArr[0] = iArr[0] + 1;
            } else {
                ClientStreamHandler.b.remove(str);
            }
            i++;
            z = false;
        }
        return z;
    }

    public static void a(int i, int i2, int i3, long j, short s, XMDTransceiver xMDTransceiver) throws InterruptedException {
        int i4 = i;
        int i5 = i2;
        int i6 = i3;
        for (int i7 = 0; i7 < i4 / i5; i7++) {
            for (int i8 = 0; i8 < 1000; i8++) {
                for (int i9 = 0; i9 < i5 / 1000; i9++) {
                    byte[] bArr = new byte[i6];
                    a(bArr, i6, i4);
                    xMDTransceiver.a(j, s, bArr, true, XMDPacket.DataPriority.P2, 0, (Object) null);
                }
                Thread.sleep(1);
            }
            if (i7 % 1800 == 0) {
                ((ClientStreamHandler) xMDTransceiver.h()).f12104a.b();
            }
        }
    }

    public static void a(byte[] bArr, int i, int i2) {
        for (int i3 = 0; i3 < i; i3++) {
            bArr[i3] = (byte) ((int) (Math.random() * 256.0d));
        }
        bArr[0] = (byte) ((i2 >>> 24) & 255);
        bArr[1] = (byte) ((i2 >>> 16) & 255);
        bArr[2] = (byte) ((i2 >>> 8) & 255);
        bArr[3] = (byte) (i2 & 255);
        long currentTimeMillis = System.currentTimeMillis();
        bArr[4] = (byte) ((int) ((currentTimeMillis >>> 56) & 255));
        bArr[5] = (byte) ((int) ((currentTimeMillis >>> 48) & 255));
        bArr[6] = (byte) ((int) ((currentTimeMillis >>> 40) & 255));
        bArr[7] = (byte) ((int) ((currentTimeMillis >>> 32) & 255));
        bArr[8] = (byte) ((int) ((currentTimeMillis >>> 24) & 255));
        bArr[9] = (byte) ((int) ((currentTimeMillis >>> 16) & 255));
        bArr[10] = (byte) ((int) ((currentTimeMillis >>> 8) & 255));
        bArr[11] = (byte) ((int) (currentTimeMillis & 255));
    }
}
