package com.evideo.smartpos.net.request;

import com.evideo.smartpos.utils.EVideoMap;

import java.io.Serializable;

/**
 * Http请求参数
 */
public class HttpRequest implements Serializable {

    private String machineIP;
    private String appTag;
    private String serverBaseUrl;
    private EVideoMap<Object> requestInfo;

    public HttpRequest() {
        this.machineIP = "127.0.0.1";
        this.appTag = "";
        this.serverBaseUrl = "";
        this.requestInfo = new EVideoMap<>();
    }

    public String getMachineIP() {
        return machineIP;
    }

    public String getAppTag() {
        return appTag;
    }

    public String getServerBaseUrl() {
        return serverBaseUrl;
    }

    public EVideoMap<Object> getRequestInfo() {
        return requestInfo;
    }

    public void setMachineIP(String machineIP) {
        this.machineIP = machineIP;
    }

    public void setAppTag(String appTag) {
        this.appTag = appTag;
    }

    public void setServerBaseUrl(String serverBaseUrl) {
        this.serverBaseUrl = serverBaseUrl;
    }

    public void setRequestInfo(EVideoMap<Object> requestInfo) {
        this.requestInfo = requestInfo;
    }
}
