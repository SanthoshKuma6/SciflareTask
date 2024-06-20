package com.sciflare.task.api

import com.google.gson.JsonObject
import com.sciflare.task.model.Create
import com.sciflare.task.model.GetDetails
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST


interface ApiInterface {
    @POST("create")
    suspend fun sendDetails(@Body params: JsonObject): Response<Create>

  @GET("create")
    suspend fun getDetails(): Response<List<GetDetails.GetDetailsItem>>
}