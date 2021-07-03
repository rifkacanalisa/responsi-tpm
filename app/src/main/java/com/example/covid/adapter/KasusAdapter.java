package com.example.covid.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.covid.R;
import com.example.covid.model.kasus.ContentItem;

import org.jetbrains.annotations.NotNull;

import java.util.Collections;
import java.util.List;

public class KasusAdapter extends RecyclerView.Adapter<KasusAdapter.ViewHolder> {

    public List<ContentItem> kasusList;

    public KasusAdapter(List<ContentItem> kasusList) {
        Collections.reverse(kasusList);
        this.kasusList = kasusList;
    }
    @NonNull
    @NotNull
    @Override
    public KasusAdapter.ViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View kasusRow = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_kasus, parent, false);
        return new ViewHolder(kasusRow);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull KasusAdapter.ViewHolder holder, int position) {
        holder.bind(kasusList.get(position));
    }

    @Override
    public int getItemCount() {
        return kasusList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvTanggal, tvKonfirmasi, tvMeninggal, tvSembuh;
        String sKonfirm = "Terkonfirmasi ", sMeninggal = "Meninggal ", sSembuh = "Sembuh ", sKasus = " Kasus";
        int konfirm, meninggal, sembuh;
        String combineKonfirm, combineMeninggal, combineSembuh;
        public ViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);

            tvTanggal = itemView.findViewById(R.id.tv_tanggal);
            tvKonfirmasi = itemView.findViewById(R.id.tv_terkonfirmasi);
            tvMeninggal = itemView.findViewById(R.id.tv_meninggal);
            tvSembuh = itemView.findViewById(R.id.tv_sembuh);
        }

        void bind(ContentItem item){
            konfirm = item.getConfirmationDiisolasi();
            meninggal = item.getConfirmationMeninggal();
            sembuh = item.getConfirmationSelesai();

            combineKonfirm = sKonfirm.concat(String.valueOf(konfirm));
            combineKonfirm = combineKonfirm.concat(sKasus);

            combineMeninggal = sMeninggal.concat(String.valueOf(meninggal));
            combineMeninggal = combineMeninggal.concat(sKasus);

            combineSembuh = sSembuh.concat(String.valueOf(sembuh));
            combineSembuh = combineSembuh.concat(sKasus);

            tvTanggal.setText(item.getTanggal());
            tvKonfirmasi.setText(combineKonfirm);
            tvMeninggal.setText(combineMeninggal);
            tvSembuh.setText(combineSembuh);
        }
    }
}
