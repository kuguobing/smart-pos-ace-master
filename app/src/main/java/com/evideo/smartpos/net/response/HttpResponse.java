package com.evideo.smartpos.net.response;


public class HttpResponse<T> {

    private String errorCode;

    private String errorMessage;

    private String exceptMessage;

    private T responseInfo;

    public String getErrorCode() {
        return errorCode;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public String getExceptMessage() {
        return exceptMessage;
    }

    public T getResponseInfo() {
        return responseInfo;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public void setExceptMessage(String exceptMessage) {
        this.exceptMessage = exceptMessage;
    }

    public void setResponseInfo(T responseInfo) {
        this.responseInfo = responseInfo;
    }
}
