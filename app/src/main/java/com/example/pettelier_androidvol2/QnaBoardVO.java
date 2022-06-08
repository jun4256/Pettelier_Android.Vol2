package com.example.pettelier_androidvol2;

import java.io.Serializable;

public class QnaBoardVO implements Serializable {

    private String qna_seq;
    private String mb_id;
    private String qna_title;
    private String qna_content;
    private String qna_date;

    public QnaBoardVO(String qna_seq, String mb_id, String qna_title, String qna_content, String qna_date) {
        this.qna_seq = qna_seq;
        this.mb_id = mb_id;
        this.qna_title = qna_title;
        this.qna_content = qna_content;
        this.qna_date = qna_date;
    }

    public QnaBoardVO(){

    }

    @Override
    public String toString() {
        return "QnaBoardVO{" +
                "qna_seq='" + qna_seq + '\'' +
                ", mb_id='" + mb_id + '\'' +
                ", qna_title='" + qna_title + '\'' +
                ", qna_content='" + qna_content + '\'' +
                ", qna_date='" + qna_date + '\'' +
                '}';
    }

    public String getQna_seq() {
        return qna_seq;
    }

    public void setQna_seq(String qna_seq) {
        this.qna_seq = qna_seq;
    }

    public String getMb_id() {
        return mb_id;
    }

    public void setMb_id(String mb_id) {
        this.mb_id = mb_id;
    }

    public String getQna_title() {
        return qna_title;
    }

    public void setQna_title(String qna_title) {
        this.qna_title = qna_title;
    }

    public String getQna_content() {
        return qna_content;
    }

    public void setQna_content(String qna_content) {
        this.qna_content = qna_content;
    }

    public String getQna_date() {
        return qna_date;
    }

    public void setQna_date(String qna_date) {
        this.qna_date = qna_date;
    }
}




