package com.alipay.mobile.security.bio.service;

public abstract class BioUploadServiceCore<Request> extends BioService {
    public abstract BioUploadResult upload(Request request, boolean z);
}
