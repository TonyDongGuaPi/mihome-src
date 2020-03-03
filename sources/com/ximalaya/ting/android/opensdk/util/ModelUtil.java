package com.ximalaya.ting.android.opensdk.util;

import android.annotation.SuppressLint;
import android.text.TextUtils;
import com.ximalaya.ting.android.opensdk.model.album.Announcer;
import com.ximalaya.ting.android.opensdk.model.live.program.Program;
import com.ximalaya.ting.android.opensdk.model.live.radio.Radio;
import com.ximalaya.ting.android.opensdk.model.live.schedule.LiveAnnouncer;
import com.ximalaya.ting.android.opensdk.model.live.schedule.Schedule;
import com.ximalaya.ting.android.opensdk.model.track.Track;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class ModelUtil {

    /* renamed from: a  reason: collision with root package name */
    static SimpleDateFormat f2260a = new SimpleDateFormat("yy:MM:dd:HH:mm");

    public static Track a(Schedule schedule) {
        if (schedule == null) {
            return null;
        }
        Track track = new Track();
        track.w(schedule.j());
        track.x(schedule.k());
        track.a("schedule");
        track.n(schedule.o());
        track.o(schedule.o());
        track.a(schedule.a());
        Program l = schedule.l();
        track.p(l.a());
        track.o(schedule.a());
        track.h(l.b());
        track.m(l.c());
        track.l(l.c());
        track.k(l.c());
        track.y(l.e());
        track.z(l.f());
        track.A(l.g());
        track.B(l.h());
        track.l(l.j());
        track.q(schedule.p());
        track.g(schedule.q());
        track.q(schedule.r());
        if (l.i() != null && l.i().size() > 0) {
            track.a(a(l.i().get(0)));
        }
        return track;
    }

    public static Track a(Radio radio, boolean z) {
        if (radio == null) {
            return null;
        }
        Track track = new Track();
        track.a(radio.a());
        track.a("radio");
        track.h(radio.h());
        track.j(radio.i());
        track.o(radio.l());
        track.y(radio.p());
        track.z(radio.q());
        track.A(radio.r());
        track.B(radio.s());
        track.q(radio.t());
        track.m(radio.v());
        track.k(radio.u());
        track.l(radio.w());
        track.q(radio.t());
        track.h(z);
        try {
            track.w(f2260a.format(new Date(radio.m())));
            track.x(f2260a.format(new Date(radio.n())));
        } catch (Exception unused) {
        }
        track.g(radio.z());
        return track;
    }

    public static Radio a(Track track) {
        if (track == null) {
            return null;
        }
        Radio radio = new Radio();
        radio.a(track.a());
        radio.a("radio");
        radio.c(track.N());
        radio.e(track.az());
        radio.f(track.aA());
        radio.g(track.aB());
        radio.h(track.aC());
        radio.i(track.aD());
        radio.b(track.X());
        radio.j(track.T());
        radio.k(track.V());
        radio.h(track.an());
        radio.b(track.X());
        radio.d(track.S());
        radio.b(track.aw());
        try {
            radio.f(f2260a.parse(track.ax()).getTime());
            radio.g(f2260a.parse(track.ay()).getTime());
        } catch (Exception unused) {
        }
        radio.i(track.y());
        return radio;
    }

    public static Schedule b(Track track) {
        if (track == null) {
            return null;
        }
        Schedule schedule = new Schedule();
        schedule.a(track.a());
        schedule.a("schedule");
        schedule.c(track.ax());
        schedule.d(track.ay());
        schedule.d(track.an());
        schedule.e(TextUtils.isEmpty(track.ad()) ? track.ab() : track.ad());
        Program program = new Program();
        program.a(track.aE());
        program.a(track.N());
        program.b(TextUtils.isEmpty(track.T()) ? track.V() : track.T());
        program.c(track.aA());
        program.e(track.aC());
        program.d(track.aB());
        program.f(track.aD());
        LiveAnnouncer a2 = a(track.I());
        if (a2 != null) {
            program.a((List<LiveAnnouncer>) new ArrayList(Arrays.asList(new LiveAnnouncer[]{a2})));
        }
        schedule.a(program);
        schedule.e(track.aF());
        schedule.f(track.K());
        schedule.c(track.X());
        return schedule;
    }

    @SuppressLint({"SimpleDateFormat"})
    public static List<Track> a(List<Schedule> list) {
        if (list == null) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        for (Schedule a2 : list) {
            arrayList.add(a(a2));
        }
        return arrayList;
    }

    public static LiveAnnouncer a(Announcer announcer) {
        if (announcer == null) {
            return null;
        }
        LiveAnnouncer liveAnnouncer = new LiveAnnouncer();
        liveAnnouncer.a(announcer.a());
        liveAnnouncer.a(announcer.b());
        liveAnnouncer.b(announcer.c());
        return liveAnnouncer;
    }

    public static Announcer a(LiveAnnouncer liveAnnouncer) {
        if (liveAnnouncer == null) {
            return null;
        }
        Announcer announcer = new Announcer();
        announcer.a(liveAnnouncer.a());
        announcer.a(liveAnnouncer.b());
        announcer.b(liveAnnouncer.c());
        return announcer;
    }

    public static Schedule a(Radio radio) {
        if (radio == null) {
            return null;
        }
        Schedule schedule = new Schedule();
        schedule.a("radio");
        String str = BaseUtil.a()[1];
        schedule.c(str + ":00:00");
        schedule.d(str + ":23:59");
        Program program = new Program();
        program.b(radio.v());
        program.c(radio.p());
        program.d(radio.q());
        program.e(radio.r());
        program.f(radio.s());
        program.a(radio.h());
        schedule.a(radio.a());
        schedule.a(program);
        schedule.e(radio.a());
        schedule.f(radio.h());
        schedule.c(radio.t());
        return schedule;
    }
}
