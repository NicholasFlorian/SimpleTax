package com.example.simpletax.taxApi;

import com.example.simpletax.domain.DeductibleForm;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface DeductibleService {

    @GET("deductibles")
    Call<List<DeductibleForm>> getDeductibles();
}
