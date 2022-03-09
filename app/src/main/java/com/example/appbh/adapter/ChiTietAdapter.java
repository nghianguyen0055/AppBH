package com.example.appbh.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.appbh.R;
import com.example.appbh.model.Item;

import java.text.DecimalFormat;
import java.util.List;

public class ChiTietAdapter extends RecyclerView.Adapter<ChiTietAdapter.MyViewHolder> {
    Context context;
    List<Item> itemList;

    public ChiTietAdapter(Context context, List<Item> itemList) {
        this.context = context;
        this.itemList = itemList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_chitiet, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Item item = itemList.get(position);
        holder.txtten.setText(item.getTenSP()+"");
        holder.txtsoluong.setText("Số Lượng: "+item.getSoluong());
        Glide.with(context).load(item.getHinhAnh()).into(holder.imgchitiet);
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        holder.txtgia.setText("Giá: "+ decimalFormat.format(Double.parseDouble(item.getGia()))+"Đ");
    }

    @Override
    public int getItemCount() {
        if (itemList != null){
            return itemList.size();
        }
        return 0;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        ImageView imgchitiet;
        TextView txtten, txtsoluong, txtgia;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            imgchitiet = itemView.findViewById(R.id.item_imgchitiet);
            txtten = itemView.findViewById(R.id.item_tenspchitiet);
            txtsoluong = itemView.findViewById(R.id.item_soluongchitiet);
            txtgia = itemView.findViewById(R.id.item_giachitiet);
        }
    }
}
