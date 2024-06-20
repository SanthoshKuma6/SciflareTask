package com.sciflare.task.model


import com.google.gson.annotations.SerializedName


/**
 * A data class in Android (using Kotlin) is a class designed to hold data with minimal boilerplate, automatically providing methods like toString(), equals(), hashCode(), and copy().
 * It's primarily used for storing and managing data in a clean and concise way
 */
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