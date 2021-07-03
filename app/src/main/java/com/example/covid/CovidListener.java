package com.example.covid;

public interface CovidListener<T> {
    void onSuccess(T items);
    void onFailed(String msg);
}
