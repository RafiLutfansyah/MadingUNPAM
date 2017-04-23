package com.rafilutfansyah.madingunpam.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.rafilutfansyah.madingunpam.R;
import com.rafilutfansyah.madingunpam.model.HomeModel;

import java.util.List;

public class HomeRecyclerViewAdapter extends RecyclerView.Adapter<HomeRecyclerViewAdapter.ViewHolder> {

    private List<HomeModel> homeModelList;
    Context context;

    public HomeRecyclerViewAdapter(List<HomeModel> homeModelList, Context context) {
        this.homeModelList = homeModelList;
        this.context = context;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        // di tutorial ini kita hanya menggunakan data String untuk tiap item
        public TextView textViewJudul;
        public TextView textViewIsi;

        public ViewHolder(View view) {
            super(view);
            textViewJudul = (TextView) view.findViewById(R.id.textview_judul);
            textViewIsi = (TextView) view.findViewById(R.id.textview_isi);
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        // membuat view baru
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.recycleview_mading, viewGroup, false);
        // mengeset ukuran view, margin, padding, dan parameter layout lainnya
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        // - mengambil elemen dari dataset (ArrayList) pada posisi tertentu
        // - mengeset isi view dengan elemen dari dataset tersebut

        HomeModel homeModel = homeModelList.get(position);

        holder.textViewJudul.setText(position + ". "+homeModel.getUsername());
        holder.textViewIsi.setText(homeModel.getName());
    }

    @Override
    public int getItemCount() {
        // menghitung ukuran dataset / jumlah data yang ditampilkan di RecyclerView
        return homeModelList.size();
    }
}