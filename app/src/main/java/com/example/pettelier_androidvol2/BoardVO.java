package com.example.pettelier_androidvol2;

public class BoardVO {

    private int seq;
    private String id;
    private String title;
    private String content;
    private int viewcount;
    private String date;


    public  BoardVO(){

    }
    public BoardVO(String id, String title, String date){

    }
    public BoardVO(int seq, String id, String title, String content, int viewcount, String date) {
        this.seq = seq;
        this.id = id;
        this.title = title;
        this.content = content;
        this.viewcount = viewcount;
        this.date = date;
    }

    public int getSeq() {
        return seq;
    }

    public void setSeq(int seq) {
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

    public int getViewcount() {
        return viewcount;
    }

    public void setViewcount(int viewcount) {
        this.viewcount = viewcount;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
