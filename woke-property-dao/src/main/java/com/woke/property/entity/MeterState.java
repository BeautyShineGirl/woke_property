package com.woke.property.entity;

/**
 * @author lyk
 * @description
 * @date 9:40 2018/6/20
 **/
public class MeterState {
    private int id;
    private int meterTypeId;
    private String name;
    private String cValue;
    private String sValue;
    private int sType;
    private boolean isUsing;

    public void setUsing(boolean using) {
        isUsing = using;
    }

    public boolean isUsing() {
        return isUsing;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getMeterTypeId() {
        return meterTypeId;
    }

    public void setMeterTypeId(int meterTypeId) {
        this.meterTypeId = meterTypeId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getcValue() {
        return cValue;
    }

    public void setcValue(String cValue) {
        this.cValue = cValue;
    }

    public String getsValue() {
        return sValue;
    }

    public void setsValue(String sValue) {
        this.sValue = sValue;
    }

    public int getsType() {
        return sType;
    }

    public void setsType(int sType) {
        this.sType = sType;
    }


}
