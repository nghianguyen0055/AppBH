package com.example.appbh.retrofit;

import com.example.appbh.model.LoaiSpModel;
import com.example.appbh.model.SanPhamMoi;
import com.example.appbh.model.SanPhamMoiModel;
import com.example.appbh.model.UserModel;
import com.example.appbh.model.donhangModel;

import io.reactivex.rxjava3.core.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface ApiBanHang {
    @GET("getloaiSP.php")
    Observable<LoaiSpModel> getLoaiSp();

    @GET("getSPmoi.php")
    Observable<SanPhamMoiModel> getSpMoi();

    @POST("chitiet.php")
    @FormUrlEncoded
    Observable<SanPhamMoiModel> getSanPham(
      @Field("page") int page,
      @Field("loai") int loai
    );


    @POST("dangki.php")
    @FormUrlEncoded
    Observable<UserModel> dangki(
            @Field("email") String email,
            @Field("pass") String pass,
            @Field("username") String usesername,
            @Field("mobile") String mobile
    );

    @POST("dangnhap.php")
    @FormUrlEncoded
    Observable<UserModel> dangnhap(
            @Field("email") String email,
            @Field("pass") String pass
    );

    @POST("resetpass.php")
    @FormUrlEncoded
    Observable<UserModel> resetpass(
            @Field("email") String email
    );


    @POST("donhang.php")
    @FormUrlEncoded
    Observable<UserModel> CreateOder(
            @Field("email") String email,
            @Field("sdt") String sdt,
            @Field("tongtien") String tongtien,
            @Field("iduser") int id,
            @Field("diachi") String diachi,
            @Field("soluong") int soluong,
            @Field("chitiet") String chitiet
    );


    @POST("xemdonhang.php")
    @FormUrlEncoded
    Observable<donhangModel> xemdonhang(
            @Field("iduser") int id
    );

    @POST("timkiem.php")
    @FormUrlEncoded
    Observable<SanPhamMoiModel> search(
            @Field("search") String search
    );
}
