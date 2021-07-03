package com.example.covid.service;

import com.example.covid.model.faskes.FaskesResponse;
import com.example.covid.model.kasus.KasusResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface CovidAPI {
    String URL_BASE = "https://covid19-public.digitalservice.id";

    @GET("/api/v1/rekapitulasi_v2/jabar/harian")
    Call<KasusResponse> getKasus();

    @GET("/api/v1/sebaran_v2/jabar/faskes")
    Call<FaskesResponse> getFaskes();

}
