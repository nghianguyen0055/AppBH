package com.example.appbh.utils;

import com.example.appbh.model.GioHang;
import com.example.appbh.model.User;

import java.util.ArrayList;
import java.util.List;

public class Utils {
    //http://192.168.1.11:8080/.../
    public static final String BASE_URL = "http://192.168.0.34:8080/appBanHang/";
    public static List<GioHang> manggiohang;
    public static List<GioHang> mangmuahang = new ArrayList<>();
    public static User user_current = new User();
}
