package com.example.pettelier_androidvol2;

public class MemberVO {
    private String id;
    private String pw;
    private String nick;
    private String name;
    private String phone;
    private String address;
    private String joindate;
    private String type;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPw() {
        return pw;
    }

    public void setPw(String pw) {
        this.pw = pw;
    }

    public String getNick() {
        return nick;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getJoindate() {
        return joindate;
    }

    public void setJoindate(String joindate) {
        this.joindate = joindate;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public MemberVO(){

    }

    public MemberVO(String id, String pw, String nick, String name, String phone, String address, String joindate, String type) {
        this.id = id;
        this.pw = pw;
        this.nick = nick;
        this.name = name;
        this.phone = phone;
        this.address = address;
        this.joindate = joindate;
        this.type = type;
    }

    @Override
    public String toString() {
        return "MemberVO{" +
                "id='" + id + '\'' +
                ", pw='" + pw + '\'' +
                ", nick='" + nick + '\'' +
                ", name='" + name + '\'' +
                ", phone='" + phone + '\'' +
                ", address='" + address + '\'' +
                ", joindate='" + joindate + '\'' +
                ", type='" + type + '\'' +
                '}';
    }
}
