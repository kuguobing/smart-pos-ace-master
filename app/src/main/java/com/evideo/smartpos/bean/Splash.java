package com.evideo.smartpos.bean;

import java.io.Serializable;
import java.util.List;

public class Splash implements Serializable {

    public int code;
    public String message;
    public String ver;
    public List<DataBean> data;

    public static class DataBean {
        public int id;
        public int type;
        public int animate;
        public int duration;
        public int startTime;
        public int endTime;
        public String thumb;
        public String hash;
        public int times;
        public int skip;
        public String uri;
    }
}
