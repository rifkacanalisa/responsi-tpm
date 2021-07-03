package com.example.covid.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.covid.CovidListener;
import com.example.covid.R;
import com.example.covid.adapter.FaskesAdapter;
import com.example.covid.adapter.KasusAdapter;
import com.example.covid.model.faskes.DataItem;
import com.example.covid.service.CovidService;

import org.jetbrains.annotations.NotNull;

import java.util.List;


public class FaskesFragment extends Fragment {
    public RecyclerView rvFaskes;

    public FaskesFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_faskes, container, false);
    }

    @Override
    public void onViewCreated(@NonNull @NotNull View view, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        rvFaskes = view.findViewById(R.id.rv_faskes);
        rvFaskes.setHasFixedSize(true);
        new CovidService().getFaskes(faskesListener);

        rvFaskes.setLayoutManager(new LinearLayoutManager(getContext()));
    }

    CovidListener<List<DataItem>> faskesListener = new CovidListener<List<DataItem>>() {
        @Override
        public void onSuccess(List<DataItem> items) {
            rvFaskes.setLayoutManager(new LinearLayoutManager(getContext()));
            FaskesAdapter faskesAdapter = new FaskesAdapter(items);
            rvFaskes.setAdapter(faskesAdapter);
        }

        @Override
        public void onFailed(String msg) {
            Log.d("Error : ", msg);
        }
    };
}