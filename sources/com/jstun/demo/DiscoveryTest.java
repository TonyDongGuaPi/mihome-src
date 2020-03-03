package com.jstun.demo;

import android.util.Log;
import com.jstun.core.attribute.ChangeRequest;
import com.jstun.core.attribute.ChangedAddress;
import com.jstun.core.attribute.ErrorCode;
import com.jstun.core.attribute.MappedAddress;
import com.jstun.core.attribute.MessageAttribute;
import com.jstun.core.attribute.MessageAttributeException;
import com.jstun.core.attribute.MessageAttributeInterface;
import com.jstun.core.attribute.MessageAttributeParsingException;
import com.jstun.core.header.MessageHeader;
import com.jstun.core.header.MessageHeaderInterface;
import com.jstun.core.header.MessageHeaderParsingException;
import com.jstun.core.util.UtilityException;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;

public class DiscoveryTest {
    private static final String j = "DiscoveryTest";

    /* renamed from: a  reason: collision with root package name */
    InetAddress f6209a;
    String b;
    int c;
    int d = 300;
    MappedAddress e = null;
    ChangedAddress f = null;
    boolean g = true;
    DatagramSocket h = null;
    public DiscoveryInfo i = null;

    public DiscoveryTest(InetAddress inetAddress, String str, int i2) {
        this.f6209a = inetAddress;
        this.b = str;
        this.c = i2;
    }

    public DiscoveryInfo a() throws UtilityException, SocketException, UnknownHostException, IOException, MessageAttributeParsingException, MessageAttributeException, MessageHeaderParsingException {
        this.e = null;
        this.f = null;
        this.g = true;
        this.h = null;
        this.i = new DiscoveryInfo(this.f6209a);
        b();
        this.h.close();
        return this.i;
    }

    private boolean b() throws UtilityException, SocketException, UnknownHostException, IOException, MessageAttributeParsingException, MessageHeaderParsingException {
        int i2 = this.d;
        int i3 = 0;
        while (true) {
            try {
                this.h = new DatagramSocket();
                this.h.setReuseAddress(true);
                this.h.connect(InetAddress.getByName(this.b), this.c);
                this.h.setSoTimeout(i2);
                MessageHeader messageHeader = new MessageHeader(MessageHeaderInterface.MessageHeaderType.BindingRequest);
                messageHeader.b();
                messageHeader.a((MessageAttribute) new ChangeRequest());
                byte[] d2 = messageHeader.d();
                this.h.send(new DatagramPacket(d2, d2.length));
                Log.d(j, "Test 1: Binding Request sent.");
                MessageHeader messageHeader2 = new MessageHeader();
                while (!messageHeader2.a(messageHeader)) {
                    DatagramPacket datagramPacket = new DatagramPacket(new byte[200], 200);
                    this.h.receive(datagramPacket);
                    MessageHeader c2 = MessageHeader.c(datagramPacket.getData());
                    c2.b(datagramPacket.getData());
                    messageHeader2 = c2;
                }
                this.e = (MappedAddress) messageHeader2.a(MessageAttributeInterface.MessageAttributeType.MappedAddress);
                this.f = (ChangedAddress) messageHeader2.a(MessageAttributeInterface.MessageAttributeType.ChangedAddress);
                ErrorCode errorCode = (ErrorCode) messageHeader2.a(MessageAttributeInterface.MessageAttributeType.ErrorCode);
                if (errorCode != null) {
                    this.i.a(errorCode.a(), errorCode.b());
                    Log.d(j, "Message header contains an Errorcode message attribute.");
                    return false;
                }
                if (this.e != null) {
                    if (this.f != null) {
                        this.i.a(this.e.b().b());
                        if (this.e.a() != this.h.getLocalPort() || !this.e.b().b().equals(this.h.getLocalAddress())) {
                            Log.d(j, "Node is natted.");
                        } else {
                            Log.d(j, "Node is not natted.");
                            this.g = false;
                        }
                        return true;
                    }
                }
                this.i.a(700, "The server is sending an incomplete response (Mapped Address and Changed Address message attributes are missing). The client should not retry.");
                Log.d(j, "Response does not contain a Mapped Address or Changed Address message attribute.");
                return false;
            } catch (SocketTimeoutException unused) {
                if (i3 < 3000) {
                    Log.d(j, "Test 1: Socket timeout while receiving the response.");
                    i3 += i2;
                    i2 = i3 * 2;
                    if (i2 > 1600) {
                        i2 = 1600;
                    }
                } else {
                    Log.d(j, "Test 1: Socket timeout while receiving the response. Maximum retry limit exceed. Give up.");
                    this.i.e();
                    Log.d(j, "Node is not capable of UDP communication.");
                    return false;
                }
            }
        }
    }

    private boolean c() throws UtilityException, SocketException, UnknownHostException, IOException, MessageAttributeParsingException, MessageAttributeException, MessageHeaderParsingException {
        int i2 = this.d;
        int i3 = 0;
        while (true) {
            try {
                DatagramSocket datagramSocket = new DatagramSocket();
                datagramSocket.connect(InetAddress.getByName(this.b), this.c);
                datagramSocket.setSoTimeout(i2);
                MessageHeader messageHeader = new MessageHeader(MessageHeaderInterface.MessageHeaderType.BindingRequest);
                messageHeader.b();
                ChangeRequest changeRequest = new ChangeRequest();
                changeRequest.c();
                changeRequest.d();
                messageHeader.a((MessageAttribute) changeRequest);
                byte[] d2 = messageHeader.d();
                datagramSocket.send(new DatagramPacket(d2, d2.length));
                Log.d(j, "Test 2: Binding Request sent.");
                int localPort = datagramSocket.getLocalPort();
                InetAddress localAddress = datagramSocket.getLocalAddress();
                datagramSocket.close();
                DatagramSocket datagramSocket2 = new DatagramSocket(localPort, localAddress);
                datagramSocket2.connect(this.f.b().b(), this.f.a());
                datagramSocket2.setSoTimeout(i2);
                MessageHeader messageHeader2 = new MessageHeader();
                while (!messageHeader2.a(messageHeader)) {
                    DatagramPacket datagramPacket = new DatagramPacket(new byte[200], 200);
                    datagramSocket2.receive(datagramPacket);
                    MessageHeader c2 = MessageHeader.c(datagramPacket.getData());
                    c2.b(datagramPacket.getData());
                    messageHeader2 = c2;
                }
                ErrorCode errorCode = (ErrorCode) messageHeader2.a(MessageAttributeInterface.MessageAttributeType.ErrorCode);
                if (errorCode != null) {
                    this.i.a(errorCode.a(), errorCode.b());
                    Log.d(j, "Message header contains an Errorcode message attribute.");
                    return false;
                }
                if (!this.g) {
                    this.i.c();
                    Log.d(j, "Node has open access to the Internet (or, at least the node is behind a full-cone NAT without translation).");
                } else {
                    this.i.g();
                    Log.d(j, "Node is behind a full-cone NAT.");
                }
                return false;
            } catch (SocketTimeoutException unused) {
                if (i3 < 7900) {
                    Log.d(j, "Test 2: Socket timeout while receiving the response.");
                    i3 += i2;
                    i2 = i3 * 2;
                    if (i2 > 1600) {
                        i2 = 1600;
                    }
                } else {
                    Log.d(j, "Test 2: Socket timeout while receiving the response. Maximum retry limit exceed. Give up.");
                    if (this.g) {
                        return true;
                    }
                    this.i.o();
                    Log.d(j, "Node is behind a symmetric UDP firewall.");
                    return false;
                }
            }
        }
    }

    private boolean d() throws UtilityException, SocketException, UnknownHostException, IOException, MessageAttributeParsingException, MessageHeaderParsingException {
        int i2 = this.d;
        int i3 = 0;
        while (true) {
            try {
                this.h.connect(this.f.b().b(), this.f.a());
                this.h.setSoTimeout(i2);
                MessageHeader messageHeader = new MessageHeader(MessageHeaderInterface.MessageHeaderType.BindingRequest);
                messageHeader.b();
                messageHeader.a((MessageAttribute) new ChangeRequest());
                byte[] d2 = messageHeader.d();
                this.h.send(new DatagramPacket(d2, d2.length));
                Log.d(j, "Test 1 redo with changed address: Binding Request sent.");
                MessageHeader messageHeader2 = new MessageHeader();
                while (!messageHeader2.a(messageHeader)) {
                    DatagramPacket datagramPacket = new DatagramPacket(new byte[200], 200);
                    this.h.receive(datagramPacket);
                    MessageHeader c2 = MessageHeader.c(datagramPacket.getData());
                    c2.b(datagramPacket.getData());
                    messageHeader2 = c2;
                }
                MappedAddress mappedAddress = (MappedAddress) messageHeader2.a(MessageAttributeInterface.MessageAttributeType.MappedAddress);
                ErrorCode errorCode = (ErrorCode) messageHeader2.a(MessageAttributeInterface.MessageAttributeType.ErrorCode);
                if (errorCode != null) {
                    this.i.a(errorCode.a(), errorCode.b());
                    Log.d(j, "Message header contains an Errorcode message attribute.");
                    return false;
                } else if (mappedAddress == null) {
                    this.i.a(700, "The server is sending an incomplete response (Mapped Address message attribute is missing). The client should not retry.");
                    Log.d(j, "Response does not contain a Mapped Address message attribute.");
                    return false;
                } else {
                    if (this.e.a() == mappedAddress.a()) {
                        if (this.e.b().b().equals(mappedAddress.b().b())) {
                            return true;
                        }
                    }
                    this.i.m();
                    Log.d(j, "Node is behind a symmetric NAT.");
                    return false;
                }
            } catch (SocketTimeoutException unused) {
                if (i3 < 7900) {
                    Log.d(j, "Test 1 redo with changed address: Socket timeout while receiving the response.");
                    i3 += i2;
                    i2 = i3 * 2;
                    if (i2 > 1600) {
                        i2 = 1600;
                    }
                } else {
                    Log.d(j, "Test 1 redo with changed address: Socket timeout while receiving the response.  Maximum retry limit exceed. Give up.");
                    return false;
                }
            }
        }
    }

    private void e() throws UtilityException, SocketException, UnknownHostException, IOException, MessageAttributeParsingException, MessageAttributeException, MessageHeaderParsingException {
        int i2 = this.d;
        int i3 = 0;
        while (true) {
            try {
                DatagramSocket datagramSocket = new DatagramSocket();
                datagramSocket.connect(InetAddress.getByName(this.b), this.c);
                datagramSocket.setSoTimeout(i2);
                MessageHeader messageHeader = new MessageHeader(MessageHeaderInterface.MessageHeaderType.BindingRequest);
                messageHeader.b();
                ChangeRequest changeRequest = new ChangeRequest();
                changeRequest.d();
                messageHeader.a((MessageAttribute) changeRequest);
                byte[] d2 = messageHeader.d();
                datagramSocket.send(new DatagramPacket(d2, d2.length));
                Log.d(j, "Test 3: Binding Request sent.");
                int localPort = datagramSocket.getLocalPort();
                InetAddress localAddress = datagramSocket.getLocalAddress();
                datagramSocket.close();
                DatagramSocket datagramSocket2 = new DatagramSocket(localPort, localAddress);
                datagramSocket2.connect(InetAddress.getByName(this.b), this.f.a());
                datagramSocket2.setSoTimeout(i2);
                MessageHeader messageHeader2 = new MessageHeader();
                while (!messageHeader2.a(messageHeader)) {
                    DatagramPacket datagramPacket = new DatagramPacket(new byte[200], 200);
                    datagramSocket2.receive(datagramPacket);
                    MessageHeader c2 = MessageHeader.c(datagramPacket.getData());
                    c2.b(datagramPacket.getData());
                    messageHeader2 = c2;
                }
                ErrorCode errorCode = (ErrorCode) messageHeader2.a(MessageAttributeInterface.MessageAttributeType.ErrorCode);
                if (errorCode != null) {
                    this.i.a(errorCode.a(), errorCode.b());
                    Log.d(j, "Message header contains an Errorcode message attribute.");
                    return;
                } else if (this.g) {
                    this.i.k();
                    Log.d(j, "Node is behind a restricted NAT.");
                    return;
                }
            } catch (SocketTimeoutException unused) {
                if (i3 < 7900) {
                    Log.d(j, "Test 3: Socket timeout while receiving the response.");
                    i3 += i2;
                    i2 = i3 * 2;
                    if (i2 > 1600) {
                        i2 = 1600;
                    }
                } else {
                    Log.d(j, "Test 3: Socket timeout while receiving the response. Maximum retry limit exceed. Give up.");
                    this.i.i();
                    Log.d(j, "Node is behind a port restricted NAT.");
                    return;
                }
            }
        }
    }
}
