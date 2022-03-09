package com.example.appbh.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.appbh.R;
import com.example.appbh.adapter.DonHangAdapter;
import com.example.appbh.retrofit.ApiBanHang;
import com.example.appbh.retrofit.retrofitClient;
import com.example.appbh.utils.Utils;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class XemdonActivity extends AppCompatActivity {
    CompositeDisposable compositeDisposable = new CompositeDisposable();
    ApiBanHang apiBanHang;
    RecyclerView rcvdonhang;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xemdon);
        addView();
        initToolbar();
        getOder();

    }

    private void getOder() {
        compositeDisposable.add(apiBanHang.xemdonhang(Utils.user_current.getId())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        donhangModel -> {
                            //Toast.makeText(getApplicationContext(), donhangModel.getResult().get(0).getItems().get(0).getTenSP(), Toast.LENGTH_SHORT).show();
                            DonHangAdapter donHangAdapter = new DonHangAdapter(getApplicationContext(), donhangModel.getResult());
                            rcvdonhang.setAdapter(donHangAdapter);
                        },
                        throwable -> {

                        }
                ));
    }

    private void initToolbar() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    private void addView() {
        toolbar = findViewById(R.id.toolbarXD);
        apiBanHang = retrofitClient.getInstance(Utils.BASE_URL).create(ApiBanHang.class);
        rcvdonhang = findViewById(R.id.recyclerView_donhang);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        rcvdonhang.setLayoutManager(linearLayoutManager);
    }

    @Override
    protected void onDestroy() {
        compositeDisposable.clear();
        super.onDestroy();
    }
}