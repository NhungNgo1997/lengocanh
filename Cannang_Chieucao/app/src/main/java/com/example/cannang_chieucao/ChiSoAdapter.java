package com.example.cannang_chieucao;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class ChiSoAdapter extends RecyclerView.Adapter<ChiSoAdapter.ViewHolder> {
    ArrayList<ChiSo> chiso;
    Layout layout;
    Context context;


    public ChiSoAdapter(ArrayList<ChiSo> nhanvien, int item, Context context) {
        this.chiso = nhanvien;
        this.layout = layout;
        this.context = context;

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View itemView = layoutInflater.inflate(R.layout.item, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.tvhoten.setText(chiso.get(position).getHoTen());
        holder.tvchieucao.setText(chiso.get(position).getChieucao()+"");
        holder.tvcannang.setText(chiso.get(position).getCannang()+"");
    }

    @Override
    public int getItemCount() {
        return chiso.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvhoten, tvchieucao, tvcannang;

        public ViewHolder(View itemView) {
            super(itemView);
            tvhoten = (TextView) itemView.findViewById(R.id.tvten);
            tvchieucao = (TextView) itemView.findViewById(R.id.tvchieucao);
            tvcannang = (TextView) itemView.findViewById(R.id.tvcannang);
        }
    }
}

