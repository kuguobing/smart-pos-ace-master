package com.evideo.smartpos.bean;


import java.io.Serializable;

public class GuestInfo implements Serializable {

    private String guestID;
    private String guestType;
    private String guestName;
    private String telephone;
    private String sex;
    private String birthDay; //生日
    private String lunarFlag; //生日类型
    private Boolean hasbirthDay; //是否过生日

    private String customerName;
    private String customerType;
    private String carNum;
    private String birthDateCN;
    private String lunarFlagCN;

    public GuestInfo() {
        clearGuestInfo();
    }


    public String getGuestID() {
        return guestID;
    }

    public String getGuestType() {
        return guestType;
    }

    public String getGuestName() {
        return guestName;
    }

    public String getTelephone() {
        return telephone;
    }

    public String getSex() {
        return sex;
    }

    public String getBirthDay() {
        return birthDay;
    }

    public String getLunarFlag() {
        return lunarFlag;
    }

    public Boolean getHasbirthDay() {
        return hasbirthDay;
    }

    public String getCustomerName() {
        return customerName;
    }

    public String getCustomerType() {
        return customerType;
    }

    public String getCarNum() {
        return carNum;
    }

    public String getBirthDateCN() {
        return birthDateCN;
    }

    public String getLunarFlagCN() {
        return lunarFlagCN;
    }


    public void setGuestID(String guestID) {
        this.guestID = guestID;
    }

    public void setGuestType(String guestType) {
        this.guestType = guestType;
    }

    public void setGuestName(String guestName) {
        this.guestName = guestName;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public void setBirthDay(String birthDay) {
        this.birthDay = birthDay;
    }

    public void setLunarFlag(String lunarFlag) {
        this.lunarFlag = lunarFlag;
    }

    public void setHasbirthDay(Boolean hasbirthDay) {
        this.hasbirthDay = hasbirthDay;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public void setCustomerType(String customerType) {
        this.customerType = customerType;
    }

    public void setCarNum(String carNum) {
        this.carNum = carNum;
    }

    public void setBirthDateCN(String birthDateCN) {
        this.birthDateCN = birthDateCN;
    }

    public void setLunarFlagCN(String lunarFlagCN) {
        this.lunarFlagCN = lunarFlagCN;
    }

    public void clearGuestInfo() {
        this.guestID = "0";
        this.guestType = "0";
        this.guestName = "";
        this.telephone = "";
        this.sex = "先生";
        this.birthDay = "";
        this.hasbirthDay = false;
        this.lunarFlag = "";

        this.customerName = "";
        this.customerType = "";
        this.carNum = "";
        this.birthDateCN = "";
        this.lunarFlagCN = "";
    }
}
