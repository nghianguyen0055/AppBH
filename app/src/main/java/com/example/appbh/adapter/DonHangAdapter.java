package com.example.appbh.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appbh.R;
import com.example.appbh.model.DonHang;

import java.util.List;

public class DonHangAdapter extends RecyclerView.Adapter<DonHangAdapter.MyViewHolder> {
    private RecyclerView.RecycledViewPool viewPool = new RecyclerView.RecycledViewPool();
    Context context;
    List<DonHang> listdonhang;

    public DonHangAdapter(Context context, List<DonHang> listdonhang) {
        this.context = context;
        this.listdonhang = listdonhang;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_donhang, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        DonHang donHang = listdonhang.get(position);
        holder.txtdonhang.setText("Đơn Hàng: " + donHang.getId());
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(
          holder.rcvchitiet.getContext(),
          LinearLayoutManager.VERTICAL,
                false
        );
        linearLayoutManager.setInitialPrefetchItemCount(donHang.getItems().size());
        //adapter chitiet
        ChiTietAdapter chiTietAdapter = new ChiTietAdapter(context,donHang.getItems());
        holder.rcvchitiet.setLayoutManager(linearLayoutManager);
        holder.rcvchitiet.setAdapter(chiTietAdapter);
        holder.rcvchitiet.setRecycledViewPool(viewPool);
    }

    @Override
    public int getItemCount() {
        if (listdonhang != null){
            return listdonhang.size();
        }
        return 0;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        TextView txtdonhang;
        RecyclerView rcvchitiet;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            txtdonhang = itemView.findViewById(R.id.iddonhang);
            rcvchitiet = itemView.findViewById(R.id.recyclerview_chitiet);
        }
    }
}
