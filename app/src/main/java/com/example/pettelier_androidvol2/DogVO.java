package com.example.pettelier_androidvol2;

import androidx.annotation.NonNull;

public class DogVO {
    private String id;
    private String dog_name;
    private String dog_type;
    private String dog_etc;
    private String dog_age;

    public DogVO(String id, String dog_name, String dog_type, String dog_etc, String dog_age) {
        this.id = id;
        this.dog_name = dog_name;
        this.dog_type = dog_type;
        this.dog_etc = dog_etc;
        this.dog_age = dog_age;
    }

    public DogVO(){

    }

    @Override
    public String toString() {
        return "DogVO{" +
                "id='" + id + '\'' +
                ", dog_name='" + dog_name + '\'' +
                ", dog_type='" + dog_type + '\'' +
                ", dog_etc='" + dog_etc + '\'' +
                ", dog_age='" + dog_age + '\'' +
                '}';
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDog_name() {
        return dog_name;
    }

    public void setDog_name(String dog_name) {
        this.dog_name = dog_name;
    }

    public String getDog_type() {
        return dog_type;
    }

    public void setDog_type(String dog_type) {
        this.dog_type = dog_type;
    }

    public String getDog_etc() {
        return dog_etc;
    }

    public void setDog_etc(String dog_etc) {
        this.dog_etc = dog_etc;
    }

    public String getDog_age() {
        return dog_age;
    }

    public void setDog_age(String dog_age) {
        this.dog_age = dog_age;
    }
}

