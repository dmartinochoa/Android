package com.example.myapplication

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiInterface {
    @GET("/3/movie/{category}")
    fun getMovies(
        @Path("category") category: String?,
        @Query("api_key") apiKey: String?,
        @Query("language") language: String?,
        @Query("page") page: Int
    ): Call<MovieResult?>?

    @GET("/3/search/{query]")
    fun searchMovies(
        @Query("api_key") apiKey: String?,
        @Query("language") language: String?,
        @Query("query") query: String?,
        @Query("page") page: Int

    ): Call<MovieResult?>?

}