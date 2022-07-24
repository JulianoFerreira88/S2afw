package com.github.s2afw.model.service;

import com.github.s2afw.model.Relatorio;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

import java.util.List;

public interface RelatorioDao {
    @GET("chart/{nome_relatorio}")
    Call<Relatorio> getRelatorio(@Path("nome_relatorio") String nome_relatorio);
    @GET("chart/")
    Call<List<String>> getRelatorios();
}
