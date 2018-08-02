package com.evideo.smartpos.bean;

import java.io.Serializable;
import java.util.List;

public class Splash implements Serializable {

    private int code;
    private String message;
    private String ver;
    private List<DataBean> data;

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public String getVer() {
        return ver;
    }

    public List<DataBean> getData() {
        return data;
    }


    public void setCode(int code) {
        this.code = code;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setVer(String ver) {
        this.ver = ver;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        private int id;
        private int type;
        private int animate;
        private int duration;
        private int startTime;
        private int endTime;
        private String thumb;
        private String hash;
        private int times;
        private int skip;
        private String uri;

        public int getId() {
            return id;
        }

        public int getType() {
            return type;
        }

        public int getAnimate() {
            return animate;
        }

        public int getDuration() {
            return duration;
        }

        public int getStartTime() {
            return startTime;
        }

        public int getEndTime() {
            return endTime;
        }

        public String getThumb() {
            return thumb;
        }

        public String getHash() {
            return hash;
        }

        public int getTimes() {
            return times;
        }

        public int getSkip() {
            return skip;
        }

        public String getUri() {
            return uri;
        }

        public void setId(int id) {
            this.id = id;
        }

        public void setType(int type) {
            this.type = type;
        }

        public void setAnimate(int animate) {
            this.animate = animate;
        }

        public void setDuration(int duration) {
            this.duration = duration;
        }

        public void setStartTime(int startTime) {
            this.startTime = startTime;
        }

        public void setEndTime(int endTime) {
            this.endTime = endTime;
        }

        public void setThumb(String thumb) {
            this.thumb = thumb;
        }

        public void setHash(String hash) {
            this.hash = hash;
        }

        public void setTimes(int times) {
            this.times = times;
        }

        public void setSkip(int skip) {
            this.skip = skip;
        }

        public void setUri(String uri) {
            this.uri = uri;
        }
    }
}
