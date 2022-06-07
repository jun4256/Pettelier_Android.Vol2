package com.example.pettelier_androidvol2;

import java.io.Serializable;

public class QnaCmtVO implements Serializable {


    private String qna_seq;
    private String qna_cmt_content;
    private String qna_cmt_date;

    public QnaCmtVO(){

    }
    @Override
    public String toString() {
        return "QnaCmtVO{" +
                "qna_seq='" + qna_seq + '\'' +
                ", qna_cmt_content='" + qna_cmt_content + '\'' +
                ", qna_cmt_date='" + qna_cmt_date + '\'' +
                '}';
    }

    public String getQna_seq() {
        return qna_seq;
    }

    public void setQna_seq(String qna_seq) {
        this.qna_seq = qna_seq;
    }

    public String getQna_cmt_content() {
        return qna_cmt_content;
    }

    public void setQna_cmt_content(String qna_cmt_content) {
        this.qna_cmt_content = qna_cmt_content;
    }

    public String getQna_cmt_date() {
        return qna_cmt_date;
    }

    public void setQna_cmt_date(String qna_cmt_date) {
        this.qna_cmt_date = qna_cmt_date;
    }

    public QnaCmtVO(String qna_cmt_content, String qna_cmt_date) {
        this.qna_cmt_content = qna_cmt_content;
        this.qna_cmt_date = qna_cmt_date;
    }

}
