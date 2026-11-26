package com.acaodigital.app;

import java.util.List;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface ApiService {

    @GET("api/acoes")
    Call<List<AcaoSocial>> listarAcoes();

    @POST("api/acoes")
    Call<AcaoSocial> criarAcao(@Body AcaoSocial acao);

    @DELETE("api/acoes/{id}")
    Call<Void> deletarAcao(@Path("id") Long id);
}