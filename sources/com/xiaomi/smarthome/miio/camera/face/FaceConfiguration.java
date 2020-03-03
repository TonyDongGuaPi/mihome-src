package com.xiaomi.smarthome.miio.camera.face;

public class FaceConfiguration {
    public static final int ERROR_CODE_ADD_FACE_MAX = 400305;
    public static final int ERROR_CODE_ADD_FIGURE_MAX = 400302;
    public static final int ERROR_CODE_ADD_MODIFY_FIGURE_EXIST = 400303;
    static final String FACE_APP_ADD_FACE = "/miot/camera/app/v1/add/face";
    static final String FACE_APP_ADD_FIGURE = "/miot/camera/app/v1/add/figure";
    static final String FACE_APP_DELETE_FACE = "/miot/camera/app/v1/delete/face";
    static final String FACE_APP_DELETE_FACES = "/miot/camera/app/v1/delete/faces";
    static final String FACE_APP_DELETE_FIGURE = "/miot/camera/app/v1/delete/figure";
    static final String FACE_APP_DELETE_FIGURES = "/miot/camera/app/v1/delete/figures";
    static final String FACE_APP_FACES_SEARCH = "/miot/camera/app/v1/faces/search";
    static final String FACE_APP_FEEDBACK = "/miot/camera/app/v1/feedback";
    static final String FACE_APP_GET_FACEIDS = "/miot/camera/app/v1/get/fileIdMetas";
    static final String FACE_APP_GET_FACES = "/miot/camera/app/v1/get/faces";
    public static final String FACE_APP_GET_FACE_IMG = "/miot/camera/app/v1/get/face/img";
    public static final String FACE_APP_GET_FIGURES = "/miot/camera/app/v1/get/figures";
    static final String FACE_APP_GET_FIGURE_BY_MARKEDFACE = "/miot/camera/app/v1/get/figureByMarkedFace";
    public static final String FACE_APP_GET_FIGURE_BY_NAME = "/miot/camera/app/v1/get/figureByName";
    static final String FACE_APP_GET_FIGURE_FACES = "/miot/camera/app/v1/get/figureFaces";
    static final String FACE_APP_GET_UNMARK_FACES = "/miot/camera/app/v1/get/unmarkFaces";
    static final String FACE_APP_MODIFY_FIGURE = "/miot/camera/app/v1/modify/figure";
    static final String FACE_APP_PUT_BABYCRY_SWITCH = "/miot/camera/app/v1/put/babyCrySwitch";
    static final String FACE_APP_PUT_FACE_SWITCH = "/miot/camera/app/v1/put/faceSwitch";
    public static final String METHOD_GET = "GET";
    public static final String METHOD_POST = "POST";
    public static final String URL_HOST = "api.io.mi.com";
    public static final String URL_HOST_PREFIX_BUSINESS = "business.smartcamera.";
    private static final String subprex = "/miot/camera";
}
