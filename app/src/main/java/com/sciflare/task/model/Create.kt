package com.sciflare.task.model


import com.google.gson.annotations.SerializedName


data class Create(
    @SerializedName("email")
    val email: String = "",
    @SerializedName("Gender")
    val gender: String = "",
    @SerializedName("_id")
    val id: String = "",
    @SerializedName("Mobile")
    val mobile: String = "",
    @SerializedName("name")
    val name: String = ""
)