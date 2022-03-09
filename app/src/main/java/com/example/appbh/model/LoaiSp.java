package com.example.appbh.model;

public class LoaiSp {
    private int id;
    private String tenSP;
    private String HinhAnh;

    public LoaiSp(String tenSP, String hinhAnh) {
        this.tenSP = tenSP;
        HinhAnh = hinhAnh;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTenSP() {
        return tenSP;
    }

    public void setTenSP(String tenSP) {
        this.tenSP = tenSP;
    }

    public String getHinhAnh() {
        return HinhAnh;
    }

    public void setHinhAnh(String hinhAnh) {
        HinhAnh = hinhAnh;
    }
}
