package com.xiaomi.infra.galaxy.fds.android;

import com.xiaomi.infra.galaxy.fds.android.exception.GalaxyFDSClientException;
import com.xiaomi.infra.galaxy.fds.android.model.FDSObject;
import com.xiaomi.infra.galaxy.fds.android.model.ObjectMetadata;
import com.xiaomi.infra.galaxy.fds.android.model.ProgressListener;
import com.xiaomi.infra.galaxy.fds.android.model.PutObjectResult;
import com.xiaomi.infra.galaxy.fds.android.model.UserParam;
import java.io.File;
import java.io.InputStream;
import java.util.List;

public interface GalaxyFDSClient {
    FDSObject a(String str, long j, List<UserParam> list, ProgressListener progressListener) throws GalaxyFDSClientException;

    FDSObject a(String str, String str2) throws GalaxyFDSClientException;

    FDSObject a(String str, String str2, long j, List<UserParam> list) throws GalaxyFDSClientException;

    FDSObject a(String str, String str2, long j, List<UserParam> list, ProgressListener progressListener) throws GalaxyFDSClientException;

    FDSObject a(String str, String str2, long j, List<UserParam> list, ProgressListener progressListener, boolean z) throws GalaxyFDSClientException;

    ObjectMetadata a(String str, File file, List<UserParam> list, ProgressListener progressListener) throws GalaxyFDSClientException;

    ObjectMetadata a(String str, String str2, File file) throws GalaxyFDSClientException;

    ObjectMetadata a(String str, String str2, File file, List<UserParam> list) throws GalaxyFDSClientException;

    ObjectMetadata a(String str, String str2, File file, List<UserParam> list, ProgressListener progressListener) throws GalaxyFDSClientException;

    ObjectMetadata a(String str, String str2, File file, List<UserParam> list, ProgressListener progressListener, boolean z) throws GalaxyFDSClientException;

    PutObjectResult a(String str, File file) throws GalaxyFDSClientException;

    PutObjectResult a(String str, File file, List<UserParam> list) throws GalaxyFDSClientException;

    PutObjectResult a(String str, InputStream inputStream, ObjectMetadata objectMetadata) throws GalaxyFDSClientException;

    PutObjectResult a(String str, InputStream inputStream, ObjectMetadata objectMetadata, List<UserParam> list) throws GalaxyFDSClientException;

    PutObjectResult a(String str, InputStream inputStream, ObjectMetadata objectMetadata, List<UserParam> list, ProgressListener progressListener) throws GalaxyFDSClientException;

    PutObjectResult a(String str, String str2, InputStream inputStream, ObjectMetadata objectMetadata) throws GalaxyFDSClientException;

    PutObjectResult a(String str, String str2, InputStream inputStream, ObjectMetadata objectMetadata, List<UserParam> list) throws GalaxyFDSClientException;

    PutObjectResult a(String str, String str2, InputStream inputStream, ObjectMetadata objectMetadata, List<UserParam> list, ProgressListener progressListener) throws GalaxyFDSClientException;

    PutObjectResult b(String str, File file, List<UserParam> list, ProgressListener progressListener) throws GalaxyFDSClientException;

    PutObjectResult b(String str, String str2, File file) throws GalaxyFDSClientException;

    PutObjectResult b(String str, String str2, File file, List<UserParam> list) throws GalaxyFDSClientException;

    PutObjectResult b(String str, String str2, File file, List<UserParam> list, ProgressListener progressListener) throws GalaxyFDSClientException;

    boolean b(String str, String str2) throws GalaxyFDSClientException;
}
