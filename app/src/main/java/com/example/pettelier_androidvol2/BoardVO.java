package com.example.pettelier_androidvol2;

import java.io.Serializable;

public class BoardVO implements Serializable {

    private static final long serialVersionUID = 1L;


    private String seq;
    private String id;
    private String title;
    private String content;
    private String viewcount;
    private String date;


    public  BoardVO(){

    }
    public BoardVO(String seq, String id, String title, String date){
        this.seq = seq;
        this.id = id;
        this.title = title;
        this.date = date;
    }
    public BoardVO(String seq, String id, String title, String content, String viewcount, String date) {
        this.seq = seq;
        this.id = id;
        this.title = title;
        this.content = content;
        this.viewcount = viewcount;
        this.date = date;
    }

    public String getSeq() {
        return seq;
    }

    public void setSeq(String seq) {
        this.seq = seq;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getViewcount() {
        return viewcount;
    }

    public void setViewcount(String viewcount) {
        this.viewcount = viewcount;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
