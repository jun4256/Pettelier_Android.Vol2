package com.example.pettelier_androidvol2;

import java.io.Serializable;

public class CmtVO implements Serializable {

    private String board_seq;
    private String cmt_seq;
    private String mb_id;
    private String cmt_content;
    private String cmt_date;

    public  CmtVO(){

    }

    public CmtVO(String cmt_seq, String mb_id, String cmt_content, String cmt_date) {
        this.cmt_seq = cmt_seq;
        this.mb_id = mb_id;
        this.cmt_content = cmt_content;
        this.cmt_date = cmt_date;
    }

    public CmtVO(String board_seq, String cmt_seq, String mb_id, String cmt_content, String cmt_date) {
        this.board_seq = board_seq;
        this.cmt_seq = cmt_seq;
        this.mb_id = mb_id;
        this.cmt_content = cmt_content;
        this.cmt_date = cmt_date;
    }

    public String getBoard_seq() {
        return board_seq;
    }

    public void setBoard_seq(String board_seq) {
        this.board_seq = board_seq;
    }

    public String getCmt_seq() {
        return cmt_seq;
    }

    public void setCmt_seq(String cmt_seq) {
        this.cmt_seq = cmt_seq;
    }

    public String getMb_id() {
        return mb_id;
    }

    public void setMb_id(String mb_id) {
        this.mb_id = mb_id;
    }

    public String getCmt_content() {
        return cmt_content;
    }

    public void setCmt_content(String cmt_content) {
        this.cmt_content = cmt_content;
    }

    public String getCmt_date() {
        return cmt_date;
    }

    public void setCmt_date(String cmt_date) {
        this.cmt_date = cmt_date;
    }
}
