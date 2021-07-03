package com.example.covid.adapter;

import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.covid.R;
import com.example.covid.model.faskes.DataItem;
import com.example.covid.model.kasus.ContentItem;

import org.jetbrains.annotations.NotNull;

import java.util.Collections;
import java.util.List;

public class FaskesAdapter extends RecyclerView.Adapter<FaskesAdapter.ViewHolder> {

    public List<DataItem> faskesList;

    public FaskesAdapter(List<DataItem> faskesList) {
        this.faskesList = faskesList;
    }

    @NonNull
    @NotNull
    @Override
    public FaskesAdapter.ViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View faskesRow = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_faskes, parent, false);
        return new FaskesAdapter.ViewHolder(faskesRow);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull FaskesAdapter.ViewHolder holder, int position) {
        holder.bind(faskesList.get(position));
    }

    @Override
    public int getItemCount() {
        return faskesList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvNama, tvAlamat;
        Button btnMaps;

        public ViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);

            tvNama = itemView.findViewById(R.id.tv_nama_rs);
            tvAlamat = itemView.findViewById(R.id.tv_alamat);
            btnMaps = itemView.findViewById(R.id.btn_maps);
        }

        void bind(DataItem item){
            tvNama.setText(item.getNama());
            tvAlamat.setText(item.getAlamat());

            btnMaps.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Uri gmmIntentUri = Uri.parse("geo:" + item.getLongitude() + "," + item.getLatitude() + "?q=" + Uri.encode(item.getNama()));
                    Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
                    mapIntent.setPackage("com.google.android.apps.maps");
                    itemView.getContext().startActivity(mapIntent);
                }
            });
        }
    }
}
