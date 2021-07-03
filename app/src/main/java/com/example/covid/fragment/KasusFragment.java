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
import com.example.covid.adapter.KasusAdapter;
import com.example.covid.model.kasus.ContentItem;
import com.example.covid.service.CovidService;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class KasusFragment extends Fragment {
    public RecyclerView rvKasus;

    public KasusFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_kasus, container, false);
    }

    @Override
    public void onViewCreated(@NonNull @NotNull View view, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        rvKasus = view.findViewById(R.id.rv_kasus);
        rvKasus.setHasFixedSize(true);
        new CovidService().getKasus(kasusListener);

        rvKasus.setLayoutManager(new LinearLayoutManager(getContext()));
    }

    CovidListener<List<ContentItem>> kasusListener = new CovidListener<List<ContentItem>>() {
        @Override
        public void onSuccess(List<ContentItem> items) {
            rvKasus.setLayoutManager(new LinearLayoutManager(getContext()));
                KasusAdapter kasusAdapter = new KasusAdapter(items);
                rvKasus.setAdapter(kasusAdapter);
        }

        @Override
        public void onFailed(String msg) {
            Log.d("Error : ", msg);
        }
    };
}