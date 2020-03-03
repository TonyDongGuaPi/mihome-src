package com.xiaomi.mimc.data;

import com.xiaomi.mimc.processor.OnLaunchedProcessor;
import com.xiaomi.mimc.proto.RtsSignal;

public class P2PCallSession extends RtsSession {

    /* renamed from: a  reason: collision with root package name */
    private RtsSignal.UserInfo f11190a;
    private CallState b;
    private volatile long c = -1;
    private short d = -1;
    private short e = -1;
    private volatile boolean f = false;
    private volatile long g = -1;
    private short h = -1;
    private short i = -1;
    private volatile boolean j = false;
    private boolean k;
    private byte[] l;
    private long m;
    private OnLaunchedProcessor n;

    public enum CallState {
        WAIT_SEND_CREATE_REQUEST,
        WAIT_CALL_ON_LAUNCHED,
        WAIT_SEND_INVITE_RESPONSE,
        WAIT_RECEIVE_CREATE_RESPONSE,
        RUNNING,
        WAIT_SEND_UPDATE_REQUEST,
        WAIT_RECEIVE_UPDATE_RESPONSE
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public P2PCallSession(long j2, RtsSignal.UserInfo userInfo, RtsSignal.CallType callType, CallState callState, long j3, boolean z, byte[] bArr, long j4) {
        super(callType, j2, j3);
        this.f11190a = userInfo;
        this.b = callState;
        this.k = z;
        this.l = bArr;
        this.m = j4;
    }

    public RtsSignal.UserInfo a() {
        return this.f11190a;
    }

    public void a(RtsSignal.UserInfo userInfo) {
        this.f11190a = userInfo;
    }

    public CallState b() {
        return this.b;
    }

    public P2PCallSession a(CallState callState) {
        this.b = callState;
        return this;
    }

    public synchronized long c() {
        return this.c;
    }

    public synchronized void a(long j2) {
        this.c = j2;
    }

    public short d() {
        return this.d;
    }

    public void a(short s) {
        this.d = s;
    }

    public short e() {
        return this.e;
    }

    public void b(short s) {
        this.e = s;
    }

    public synchronized long f() {
        return this.g;
    }

    public synchronized void b(long j2) {
        this.g = j2;
    }

    public short g() {
        return this.h;
    }

    public void c(short s) {
        this.h = s;
    }

    public short h() {
        return this.i;
    }

    public void d(short s) {
        this.i = s;
    }

    public boolean i() {
        return this.k;
    }

    public void a(boolean z) {
        this.k = z;
    }

    public synchronized boolean j() {
        return this.f;
    }

    public synchronized void b(boolean z) {
        this.f = z;
    }

    public synchronized boolean k() {
        return this.j;
    }

    public synchronized void c(boolean z) {
        this.j = z;
    }

    public byte[] l() {
        return this.l;
    }

    public void a(byte[] bArr) {
        this.l = bArr;
    }

    public OnLaunchedProcessor m() {
        return this.n;
    }

    public void a(OnLaunchedProcessor onLaunchedProcessor) {
        this.n = onLaunchedProcessor;
    }

    public void c(long j2) {
        this.m = j2;
    }

    public long n() {
        return this.m;
    }

    public void o() {
        p();
        q();
    }

    public void p() {
        b(false);
        a(-1);
        b(-1);
        a(-1);
    }

    public void q() {
        c(false);
        b(-1);
        d(-1);
        c(-1);
    }
}
