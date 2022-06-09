package com.example.pettelier_androidvol2;

import java.io.Serializable;

public class MatchingVO implements Serializable {
    private String mb_id;
    private String cg_serial;

    @Override
    public String toString() {
        return "MatchingVO{" +
                "mb_id='" + mb_id + '\'' +
                ", cg_serial='" + cg_serial + '\'' +
                '}';
    }

    public String getMb_id() {
        return mb_id;
    }

    public void setMb_id(String mb_id) {
        this.mb_id = mb_id;
    }

    public String getCg_serial() {
        return cg_serial;
    }

    public void setCg_serial(String cg_serial) {
        this.cg_serial = cg_serial;
    }

    public  MatchingVO(){

    }
    public MatchingVO(String mb_id, String cg_serial) {
        this.mb_id = mb_id;
        this.cg_serial = cg_serial;
    }
}
