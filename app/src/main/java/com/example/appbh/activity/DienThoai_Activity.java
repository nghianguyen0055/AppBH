package com.example.appbh.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Toast;

import com.example.appbh.R;
import com.example.appbh.adapter.DienthoaiAdapter;
import com.example.appbh.model.SanPhamMoi;
import com.example.appbh.model.SanPhamMoiModel;
import com.example.appbh.retrofit.ApiBanHang;
import com.example.appbh.retrofit.retrofitClient;
import com.example.appbh.utils.Utils;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class DienThoai_Activity extends AppCompatActivity {
    private Toolbar toolbar;
    RecyclerView recyclerView;
    ApiBanHang apiBanHang;
    CompositeDisposable compositeDisposable = new CompositeDisposable();
    int page = 1;
    int loai;
    DienthoaiAdapter adapterDt;
    List<SanPhamMoi> sanPhamMoiList;
    LinearLayoutManager linearLayoutManager;
    Handler handler = new Handler();
    boolean isloading = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dien_thoai);
        apiBanHang = retrofitClient.getInstance(Utils.BASE_URL).create(ApiBanHang.class);

        loai = getIntent().getIntExtra("loai", 1);
        addView();
        actionToolbar();
        getdata(page);
        addEventLoading();
    }

    private void addEventLoading() {
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (isloading == false){
                    if (linearLayoutManager.findLastCompletelyVisibleItemPosition() == sanPhamMoiList.size()-1){
                        isloading = true;
                        loadmore();
                    }
                }
            }
        });
    }

    private void loadmore() {
        handler.post(new Runnable() {
            @Override
            public void run() {
                sanPhamMoiList.add(null);
                adapterDt.notifyItemInserted(sanPhamMoiList.size()-1);
            }
        });
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                //remove null
                sanPhamMoiList.remove(sanPhamMoiList.size()-1);
                adapterDt.notifyItemRemoved(sanPhamMoiList.size());
                page = page+1;
                getdata(page);
                adapterDt.notifyDataSetChanged();
                isloading = false;
            }
        },2000);
    }

    private void getdata(int page) {
        compositeDisposable.add(apiBanHang.getSanPham(page, loai)
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(
                sanPhamMoiModel -> {
                    if (sanPhamMoiModel.isSuccess()){
                        if (adapterDt == null) {
                            sanPhamMoiList = sanPhamMoiModel.getResult();
                            adapterDt = new DienthoaiAdapter(getApplicationContext(), sanPhamMoiList);
                            recyclerView.setAdapter(adapterDt);
                        }else {
                            int vitri = sanPhamMoiList.size()-1;
                            int soluong = sanPhamMoiModel.getResult().size();
                            for (int i = 0; i<soluong; i++){
                                sanPhamMoiList.add(sanPhamMoiModel.getResult().get(i));
                            }
                            adapterDt.notifyItemRangeInserted(vitri, soluong);
                        }
                    }else {
                        Toast.makeText(getApplicationContext(), "Hết dữ liệu",Toast.LENGTH_SHORT).show();
                        isloading = true;
                    }

                }, throwable -> {
                    Toast.makeText(getApplicationContext(), "Không kết nối", Toast.LENGTH_SHORT).show();
                }
        ));
    }

    private void actionToolbar() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        if (loai == 1){
            toolbar.setTitle("Điện Thoại");
        }else {
            toolbar.setTitle("LapTop");
        }
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    private void addView() {
        toolbar = findViewById(R.id.toolbar);
        recyclerView = findViewById(R.id.recyclerview_dt);
        linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setHasFixedSize(true);
        sanPhamMoiList = new ArrayList<>();
    }

    @Override
    protected void onDestroy() {
        compositeDisposable.clear();
        super.onDestroy();
    }
}