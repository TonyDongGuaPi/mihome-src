package com.amap.api.services.a;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import com.amap.api.services.core.AMapException;
import com.amap.api.services.interfaces.IRouteSearch;
import com.amap.api.services.route.BusRouteResult;
import com.amap.api.services.route.DriveRoutePlanResult;
import com.amap.api.services.route.DriveRouteResult;
import com.amap.api.services.route.RideRouteResult;
import com.amap.api.services.route.RouteSearch;
import com.amap.api.services.route.TruckRouteRestult;
import com.amap.api.services.route.WalkRouteResult;

public class bj implements IRouteSearch {
    /* access modifiers changed from: private */

    /* renamed from: a  reason: collision with root package name */
    public RouteSearch.OnRouteSearchListener f4323a;
    /* access modifiers changed from: private */
    public RouteSearch.OnTruckRouteSearchListener b;
    /* access modifiers changed from: private */
    public RouteSearch.OnRoutePlanSearchListener c;
    private Context d;
    /* access modifiers changed from: private */
    public Handler e = ac.a();

    public bj(Context context) {
        this.d = context.getApplicationContext();
    }

    public void setRouteSearchListener(RouteSearch.OnRouteSearchListener onRouteSearchListener) {
        this.f4323a = onRouteSearchListener;
    }

    public void setOnTruckRouteSearchListener(RouteSearch.OnTruckRouteSearchListener onTruckRouteSearchListener) {
        this.b = onTruckRouteSearchListener;
    }

    public void setOnRoutePlanSearchListener(RouteSearch.OnRoutePlanSearchListener onRoutePlanSearchListener) {
        this.c = onRoutePlanSearchListener;
    }

    public WalkRouteResult calculateWalkRoute(RouteSearch.WalkRouteQuery walkRouteQuery) throws AMapException {
        try {
            aa.a(this.d);
            if (walkRouteQuery == null) {
                throw new AMapException(AMapException.AMAP_CLIENT_INVALID_PARAMETER);
            } else if (a(walkRouteQuery.getFromAndTo())) {
                RouteSearch.WalkRouteQuery clone = walkRouteQuery.clone();
                WalkRouteResult walkRouteResult = (WalkRouteResult) new au(this.d, clone).c();
                if (walkRouteResult != null) {
                    walkRouteResult.setWalkQuery(clone);
                }
                return walkRouteResult;
            } else {
                throw new AMapException(AMapException.AMAP_CLIENT_INVALID_PARAMETER);
            }
        } catch (AMapException e2) {
            s.a(e2, "RouteSearch", "calculateWalkRoute");
            throw e2;
        }
    }

    public void calculateWalkRouteAsyn(final RouteSearch.WalkRouteQuery walkRouteQuery) {
        try {
            as.a().a(new Runnable() {
                public void run() {
                    Message obtainMessage = ac.a().obtainMessage();
                    obtainMessage.what = 102;
                    obtainMessage.arg1 = 1;
                    Bundle bundle = new Bundle();
                    WalkRouteResult walkRouteResult = null;
                    try {
                        WalkRouteResult calculateWalkRoute = bj.this.calculateWalkRoute(walkRouteQuery);
                        try {
                            bundle.putInt("errorCode", 1000);
                            obtainMessage.obj = bj.this.f4323a;
                            bundle.putParcelable("result", calculateWalkRoute);
                        } catch (AMapException e) {
                            WalkRouteResult walkRouteResult2 = calculateWalkRoute;
                            e = e;
                            walkRouteResult = walkRouteResult2;
                            try {
                                bundle.putInt("errorCode", e.getErrorCode());
                                obtainMessage.obj = bj.this.f4323a;
                                bundle.putParcelable("result", walkRouteResult);
                                obtainMessage.setData(bundle);
                                bj.this.e.sendMessage(obtainMessage);
                            } catch (Throwable th) {
                                th = th;
                                obtainMessage.obj = bj.this.f4323a;
                                bundle.putParcelable("result", walkRouteResult);
                                obtainMessage.setData(bundle);
                                bj.this.e.sendMessage(obtainMessage);
                                throw th;
                            }
                        } catch (Throwable th2) {
                            WalkRouteResult walkRouteResult3 = calculateWalkRoute;
                            th = th2;
                            walkRouteResult = walkRouteResult3;
                            obtainMessage.obj = bj.this.f4323a;
                            bundle.putParcelable("result", walkRouteResult);
                            obtainMessage.setData(bundle);
                            bj.this.e.sendMessage(obtainMessage);
                            throw th;
                        }
                    } catch (AMapException e2) {
                        e = e2;
                        bundle.putInt("errorCode", e.getErrorCode());
                        obtainMessage.obj = bj.this.f4323a;
                        bundle.putParcelable("result", walkRouteResult);
                        obtainMessage.setData(bundle);
                        bj.this.e.sendMessage(obtainMessage);
                    }
                    obtainMessage.setData(bundle);
                    bj.this.e.sendMessage(obtainMessage);
                }
            });
        } catch (Throwable th) {
            s.a(th, "RouteSearch", "calculateWalkRouteAsyn");
        }
    }

    public BusRouteResult calculateBusRoute(RouteSearch.BusRouteQuery busRouteQuery) throws AMapException {
        try {
            aa.a(this.d);
            if (busRouteQuery == null) {
                throw new AMapException(AMapException.AMAP_CLIENT_INVALID_PARAMETER);
            } else if (a(busRouteQuery.getFromAndTo())) {
                RouteSearch.BusRouteQuery clone = busRouteQuery.clone();
                BusRouteResult busRouteResult = (BusRouteResult) new l(this.d, clone).c();
                if (busRouteResult != null) {
                    busRouteResult.setBusQuery(clone);
                }
                return busRouteResult;
            } else {
                throw new AMapException(AMapException.AMAP_CLIENT_INVALID_PARAMETER);
            }
        } catch (AMapException e2) {
            s.a(e2, "RouteSearch", "calculateBusRoute");
            throw e2;
        }
    }

    public void calculateBusRouteAsyn(final RouteSearch.BusRouteQuery busRouteQuery) {
        try {
            as.a().a(new Runnable() {
                public void run() {
                    Message obtainMessage = ac.a().obtainMessage();
                    obtainMessage.what = 100;
                    obtainMessage.arg1 = 1;
                    Bundle bundle = new Bundle();
                    BusRouteResult busRouteResult = null;
                    try {
                        BusRouteResult calculateBusRoute = bj.this.calculateBusRoute(busRouteQuery);
                        try {
                            bundle.putInt("errorCode", 1000);
                            obtainMessage.obj = bj.this.f4323a;
                            bundle.putParcelable("result", calculateBusRoute);
                        } catch (AMapException e) {
                            BusRouteResult busRouteResult2 = calculateBusRoute;
                            e = e;
                            busRouteResult = busRouteResult2;
                            try {
                                bundle.putInt("errorCode", e.getErrorCode());
                                obtainMessage.obj = bj.this.f4323a;
                                bundle.putParcelable("result", busRouteResult);
                                obtainMessage.setData(bundle);
                                bj.this.e.sendMessage(obtainMessage);
                            } catch (Throwable th) {
                                th = th;
                                obtainMessage.obj = bj.this.f4323a;
                                bundle.putParcelable("result", busRouteResult);
                                obtainMessage.setData(bundle);
                                bj.this.e.sendMessage(obtainMessage);
                                throw th;
                            }
                        } catch (Throwable th2) {
                            BusRouteResult busRouteResult3 = calculateBusRoute;
                            th = th2;
                            busRouteResult = busRouteResult3;
                            obtainMessage.obj = bj.this.f4323a;
                            bundle.putParcelable("result", busRouteResult);
                            obtainMessage.setData(bundle);
                            bj.this.e.sendMessage(obtainMessage);
                            throw th;
                        }
                    } catch (AMapException e2) {
                        e = e2;
                        bundle.putInt("errorCode", e.getErrorCode());
                        obtainMessage.obj = bj.this.f4323a;
                        bundle.putParcelable("result", busRouteResult);
                        obtainMessage.setData(bundle);
                        bj.this.e.sendMessage(obtainMessage);
                    }
                    obtainMessage.setData(bundle);
                    bj.this.e.sendMessage(obtainMessage);
                }
            });
        } catch (Throwable th) {
            s.a(th, "RouteSearch", "calculateBusRouteAsyn");
        }
    }

    public DriveRouteResult calculateDriveRoute(RouteSearch.DriveRouteQuery driveRouteQuery) throws AMapException {
        try {
            aa.a(this.d);
            if (driveRouteQuery == null) {
                throw new AMapException(AMapException.AMAP_CLIENT_INVALID_PARAMETER);
            } else if (a(driveRouteQuery.getFromAndTo())) {
                RouteSearch.DriveRouteQuery clone = driveRouteQuery.clone();
                DriveRouteResult driveRouteResult = (DriveRouteResult) new w(this.d, clone).c();
                if (driveRouteResult != null) {
                    driveRouteResult.setDriveQuery(clone);
                }
                return driveRouteResult;
            } else {
                throw new AMapException(AMapException.AMAP_CLIENT_INVALID_PARAMETER);
            }
        } catch (AMapException e2) {
            s.a(e2, "RouteSearch", "calculateDriveRoute");
            throw e2;
        }
    }

    public void calculateDriveRouteAsyn(final RouteSearch.DriveRouteQuery driveRouteQuery) {
        try {
            as.a().a(new Runnable() {
                public void run() {
                    Message obtainMessage = ac.a().obtainMessage();
                    obtainMessage.what = 101;
                    obtainMessage.arg1 = 1;
                    Bundle bundle = new Bundle();
                    DriveRouteResult driveRouteResult = null;
                    try {
                        DriveRouteResult calculateDriveRoute = bj.this.calculateDriveRoute(driveRouteQuery);
                        try {
                            bundle.putInt("errorCode", 1000);
                            obtainMessage.obj = bj.this.f4323a;
                            bundle.putParcelable("result", calculateDriveRoute);
                        } catch (AMapException e) {
                            DriveRouteResult driveRouteResult2 = calculateDriveRoute;
                            e = e;
                            driveRouteResult = driveRouteResult2;
                            try {
                                bundle.putInt("errorCode", e.getErrorCode());
                                obtainMessage.obj = bj.this.f4323a;
                                bundle.putParcelable("result", driveRouteResult);
                                obtainMessage.setData(bundle);
                                bj.this.e.sendMessage(obtainMessage);
                            } catch (Throwable th) {
                                th = th;
                                obtainMessage.obj = bj.this.f4323a;
                                bundle.putParcelable("result", driveRouteResult);
                                obtainMessage.setData(bundle);
                                bj.this.e.sendMessage(obtainMessage);
                                throw th;
                            }
                        } catch (Throwable th2) {
                            DriveRouteResult driveRouteResult3 = calculateDriveRoute;
                            th = th2;
                            driveRouteResult = driveRouteResult3;
                            obtainMessage.obj = bj.this.f4323a;
                            bundle.putParcelable("result", driveRouteResult);
                            obtainMessage.setData(bundle);
                            bj.this.e.sendMessage(obtainMessage);
                            throw th;
                        }
                    } catch (AMapException e2) {
                        e = e2;
                        bundle.putInt("errorCode", e.getErrorCode());
                        obtainMessage.obj = bj.this.f4323a;
                        bundle.putParcelable("result", driveRouteResult);
                        obtainMessage.setData(bundle);
                        bj.this.e.sendMessage(obtainMessage);
                    }
                    obtainMessage.setData(bundle);
                    bj.this.e.sendMessage(obtainMessage);
                }
            });
        } catch (Throwable th) {
            s.a(th, "RouteSearch", "calculateDriveRouteAsyn");
        }
    }

    private boolean a(RouteSearch.FromAndTo fromAndTo) {
        if (fromAndTo == null || fromAndTo.getFrom() == null || fromAndTo.getTo() == null) {
            return false;
        }
        return true;
    }

    public void calculateRideRouteAsyn(final RouteSearch.RideRouteQuery rideRouteQuery) {
        try {
            as.a().a(new Runnable() {
                public void run() {
                    Message obtainMessage = ac.a().obtainMessage();
                    obtainMessage.what = 103;
                    obtainMessage.arg1 = 1;
                    Bundle bundle = new Bundle();
                    RideRouteResult rideRouteResult = null;
                    try {
                        RideRouteResult calculateRideRoute = bj.this.calculateRideRoute(rideRouteQuery);
                        try {
                            bundle.putInt("errorCode", 1000);
                            obtainMessage.obj = bj.this.f4323a;
                            bundle.putParcelable("result", calculateRideRoute);
                        } catch (AMapException e) {
                            RideRouteResult rideRouteResult2 = calculateRideRoute;
                            e = e;
                            rideRouteResult = rideRouteResult2;
                            try {
                                bundle.putInt("errorCode", e.getErrorCode());
                                obtainMessage.obj = bj.this.f4323a;
                                bundle.putParcelable("result", rideRouteResult);
                                obtainMessage.setData(bundle);
                                bj.this.e.sendMessage(obtainMessage);
                            } catch (Throwable th) {
                                th = th;
                                obtainMessage.obj = bj.this.f4323a;
                                bundle.putParcelable("result", rideRouteResult);
                                obtainMessage.setData(bundle);
                                bj.this.e.sendMessage(obtainMessage);
                                throw th;
                            }
                        } catch (Throwable th2) {
                            RideRouteResult rideRouteResult3 = calculateRideRoute;
                            th = th2;
                            rideRouteResult = rideRouteResult3;
                            obtainMessage.obj = bj.this.f4323a;
                            bundle.putParcelable("result", rideRouteResult);
                            obtainMessage.setData(bundle);
                            bj.this.e.sendMessage(obtainMessage);
                            throw th;
                        }
                    } catch (AMapException e2) {
                        e = e2;
                        bundle.putInt("errorCode", e.getErrorCode());
                        obtainMessage.obj = bj.this.f4323a;
                        bundle.putParcelable("result", rideRouteResult);
                        obtainMessage.setData(bundle);
                        bj.this.e.sendMessage(obtainMessage);
                    }
                    obtainMessage.setData(bundle);
                    bj.this.e.sendMessage(obtainMessage);
                }
            });
        } catch (Throwable th) {
            s.a(th, "RouteSearch", "calculateRideRouteAsyn");
        }
    }

    public RideRouteResult calculateRideRoute(RouteSearch.RideRouteQuery rideRouteQuery) throws AMapException {
        try {
            aa.a(this.d);
            if (rideRouteQuery == null) {
                throw new AMapException(AMapException.AMAP_CLIENT_INVALID_PARAMETER);
            } else if (a(rideRouteQuery.getFromAndTo())) {
                RouteSearch.RideRouteQuery clone = rideRouteQuery.clone();
                RideRouteResult rideRouteResult = (RideRouteResult) new an(this.d, clone).c();
                if (rideRouteResult != null) {
                    rideRouteResult.setRideQuery(clone);
                }
                return rideRouteResult;
            } else {
                throw new AMapException(AMapException.AMAP_CLIENT_INVALID_PARAMETER);
            }
        } catch (AMapException e2) {
            s.a(e2, "RouteSearch", "calculaterideRoute");
            throw e2;
        }
    }

    public TruckRouteRestult calculateTruckRoute(RouteSearch.TruckRouteQuery truckRouteQuery) throws AMapException {
        try {
            aa.a(this.d);
            if (truckRouteQuery == null) {
                throw new AMapException(AMapException.AMAP_CLIENT_INVALID_PARAMETER);
            } else if (a(truckRouteQuery.getFromAndTo())) {
                RouteSearch.TruckRouteQuery clone = truckRouteQuery.clone();
                TruckRouteRestult truckRouteRestult = (TruckRouteRestult) new at(this.d, clone).c();
                if (truckRouteRestult != null) {
                    truckRouteRestult.setTruckQuery(clone);
                }
                return truckRouteRestult;
            } else {
                throw new AMapException(AMapException.AMAP_CLIENT_INVALID_PARAMETER);
            }
        } catch (AMapException e2) {
            s.a(e2, "RouteSearch", "calculateDriveRoute");
            throw e2;
        }
    }

    public void calculateTruckRouteAsyn(final RouteSearch.TruckRouteQuery truckRouteQuery) {
        try {
            as.a().a(new Runnable() {
                public void run() {
                    Message obtainMessage = ac.a().obtainMessage();
                    obtainMessage.what = 104;
                    obtainMessage.arg1 = 17;
                    Bundle bundle = new Bundle();
                    TruckRouteRestult truckRouteRestult = null;
                    try {
                        TruckRouteRestult calculateTruckRoute = bj.this.calculateTruckRoute(truckRouteQuery);
                        try {
                            bundle.putInt("errorCode", 1000);
                            obtainMessage.obj = bj.this.b;
                            bundle.putParcelable("result", calculateTruckRoute);
                        } catch (AMapException e) {
                            TruckRouteRestult truckRouteRestult2 = calculateTruckRoute;
                            e = e;
                            truckRouteRestult = truckRouteRestult2;
                            try {
                                bundle.putInt("errorCode", e.getErrorCode());
                                obtainMessage.obj = bj.this.b;
                                bundle.putParcelable("result", truckRouteRestult);
                                obtainMessage.setData(bundle);
                                bj.this.e.sendMessage(obtainMessage);
                            } catch (Throwable th) {
                                th = th;
                                obtainMessage.obj = bj.this.b;
                                bundle.putParcelable("result", truckRouteRestult);
                                obtainMessage.setData(bundle);
                                bj.this.e.sendMessage(obtainMessage);
                                throw th;
                            }
                        } catch (Throwable th2) {
                            TruckRouteRestult truckRouteRestult3 = calculateTruckRoute;
                            th = th2;
                            truckRouteRestult = truckRouteRestult3;
                            obtainMessage.obj = bj.this.b;
                            bundle.putParcelable("result", truckRouteRestult);
                            obtainMessage.setData(bundle);
                            bj.this.e.sendMessage(obtainMessage);
                            throw th;
                        }
                    } catch (AMapException e2) {
                        e = e2;
                        bundle.putInt("errorCode", e.getErrorCode());
                        obtainMessage.obj = bj.this.b;
                        bundle.putParcelable("result", truckRouteRestult);
                        obtainMessage.setData(bundle);
                        bj.this.e.sendMessage(obtainMessage);
                    }
                    obtainMessage.setData(bundle);
                    bj.this.e.sendMessage(obtainMessage);
                }
            });
        } catch (Throwable th) {
            s.a(th, "RouteSearch", "calculateTruckRouteAsyn");
        }
    }

    public DriveRoutePlanResult calculateDrivePlan(RouteSearch.DrivePlanQuery drivePlanQuery) throws AMapException {
        try {
            aa.a(this.d);
            if (drivePlanQuery == null) {
                throw new AMapException(AMapException.AMAP_CLIENT_INVALID_PARAMETER);
            } else if (a(drivePlanQuery.getFromAndTo())) {
                DriveRoutePlanResult driveRoutePlanResult = (DriveRoutePlanResult) new v(this.d, drivePlanQuery.clone()).c();
                if (driveRoutePlanResult != null) {
                    driveRoutePlanResult.setDrivePlanQuery(drivePlanQuery);
                }
                return driveRoutePlanResult;
            } else {
                throw new AMapException(AMapException.AMAP_CLIENT_INVALID_PARAMETER);
            }
        } catch (AMapException e2) {
            s.a(e2, "RouteSearch", "calculateDrivePlan");
            throw e2;
        }
    }

    public void calculateDrivePlanAsyn(final RouteSearch.DrivePlanQuery drivePlanQuery) {
        try {
            as.a().a(new Runnable() {
                public void run() {
                    Message obtainMessage = ac.a().obtainMessage();
                    obtainMessage.what = 105;
                    obtainMessage.arg1 = 18;
                    Bundle bundle = new Bundle();
                    DriveRoutePlanResult driveRoutePlanResult = null;
                    try {
                        DriveRoutePlanResult calculateDrivePlan = bj.this.calculateDrivePlan(drivePlanQuery);
                        try {
                            bundle.putInt("errorCode", 1000);
                            obtainMessage.obj = bj.this.c;
                            bundle.putParcelable("result", calculateDrivePlan);
                        } catch (AMapException e) {
                            DriveRoutePlanResult driveRoutePlanResult2 = calculateDrivePlan;
                            e = e;
                            driveRoutePlanResult = driveRoutePlanResult2;
                            try {
                                bundle.putInt("errorCode", e.getErrorCode());
                                obtainMessage.obj = bj.this.c;
                                bundle.putParcelable("result", driveRoutePlanResult);
                                obtainMessage.setData(bundle);
                                bj.this.e.sendMessage(obtainMessage);
                            } catch (Throwable th) {
                                th = th;
                                obtainMessage.obj = bj.this.c;
                                bundle.putParcelable("result", driveRoutePlanResult);
                                obtainMessage.setData(bundle);
                                bj.this.e.sendMessage(obtainMessage);
                                throw th;
                            }
                        } catch (Throwable th2) {
                            DriveRoutePlanResult driveRoutePlanResult3 = calculateDrivePlan;
                            th = th2;
                            driveRoutePlanResult = driveRoutePlanResult3;
                            obtainMessage.obj = bj.this.c;
                            bundle.putParcelable("result", driveRoutePlanResult);
                            obtainMessage.setData(bundle);
                            bj.this.e.sendMessage(obtainMessage);
                            throw th;
                        }
                    } catch (AMapException e2) {
                        e = e2;
                        bundle.putInt("errorCode", e.getErrorCode());
                        obtainMessage.obj = bj.this.c;
                        bundle.putParcelable("result", driveRoutePlanResult);
                        obtainMessage.setData(bundle);
                        bj.this.e.sendMessage(obtainMessage);
                    }
                    obtainMessage.setData(bundle);
                    bj.this.e.sendMessage(obtainMessage);
                }
            });
        } catch (Throwable th) {
            s.a(th, "RouteSearch", "calculateTruckRouteAsyn");
        }
    }
}
