package com.example.pettelier_androidvol2;

import java.io.Serializable;

public class CageVO implements Serializable {

    private String cg_serial;
    private String cg_gas;
    private String cg_temp;
    private String cg_angle;
    private String cg_time;
    private String cg_hum;
    private String cg_motor;

    public CageVO(String cg_serial, String cg_gas, String cg_temp, String cg_angle, String cg_time, String cg_hum,String cg_motor) {
        this.cg_serial = cg_serial;
        this.cg_gas = cg_gas;
        this.cg_temp = cg_temp;
        this.cg_angle = cg_angle;
        this.cg_time = cg_time;
        this.cg_hum = cg_hum;
        this.cg_motor = cg_motor;
    }

    public CageVO(String cg_serial, String cg_motor) {
        this.cg_serial = cg_serial;
        this.cg_motor = cg_motor;
    }

    public String getCg_serial() {
        return cg_serial;
    }

    public void setCg_serial(String cg_serial) {
        this.cg_serial = cg_serial;
    }

    public String getCg_gas() {
        return cg_gas;
    }

    public void setCg_gas(String cg_gas) {
        this.cg_gas = cg_gas;
    }

    public String getCg_temp() {
        return cg_temp;
    }

    public void setCg_temp(String cg_temp) {
        this.cg_temp = cg_temp;
    }

    public String getCg_angle() {
        return cg_angle;
    }

    public void setCg_angle(String cg_angle) {
        this.cg_angle = cg_angle;
    }

    public String getCg_time() {
        return cg_time;
    }

    public void setCg_time(String cg_time) {
        this.cg_time = cg_time;
    }

    public String getCg_hum() {
        return cg_hum;
    }

    public void setCg_hum(String cg_hum) {
        this.cg_hum = cg_hum;
    }

    public String getCg_motor() {
        return cg_motor;
    }

    public void setCg_motor(String cg_motor) {
        this.cg_motor = cg_motor;
    }

    @Override
    public String toString() {
        return "CageVO{" +
                "cg_serial='" + cg_serial + '\'' +
                ", cg_gas='" + cg_gas + '\'' +
                ", cg_temp='" + cg_temp + '\'' +
                ", cg_angle='" + cg_angle + '\'' +
                ", cg_time='" + cg_time + '\'' +
                ", cg_hum='" + cg_hum + '\'' +
                ", cg_motor='" + cg_motor + '\'' +
                '}';
    }
}
