package com.example.demo.model;

public class NhanVien {
    private String name;
    private int luong;

    private int age;


    public NhanVien(String name, int luong, int age) {
        this.name = name;
        this.luong = luong;
        this.age = age;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getLuong() {
        return luong;
    }

    public void setLuong(int luong) {
        this.luong = luong;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
