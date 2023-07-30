package com.kang.api

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path


interface BaseApi {
    @GET("users/{user}/repos")
    fun listRepos(@Path("user") user: String?): Call<List<String?>?>?
}