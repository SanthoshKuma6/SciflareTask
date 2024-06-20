package com.sciflare.task.repo

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.google.gson.JsonObject
import com.sciflare.task.api.ApiInterface
import com.sciflare.task.api.Response
import com.sciflare.task.api.RetrofitApi
import com.sciflare.task.model.Create
import com.sciflare.task.model.GetDetails
import okhttp3.ResponseBody
import org.json.JSONException
import org.json.JSONObject



class CreateRepo {

    private val serverApi by lazy {
        RetrofitApi.retrofitInstance().create(ApiInterface::class.java)
    }
    private fun getErrorBodyMessage(responseBody: ResponseBody): String {
        val errorJson = JSONObject(responseBody.string())
        return errorJson.getString("message")
    }
    val mutableLiveData = MutableLiveData<Response<Create>>()
    suspend fun sendDetails(json: JsonObject){
        mutableLiveData.value = Response.Loading(true)
        try {
            val result = serverApi.sendDetails(json)
            if (result.isSuccessful){
                mutableLiveData.value = Response.Success(result.body())
                mutableLiveData.value = Response.Loading(false)
            }else{
                mutableLiveData.value = Response.Loading(false)
                try {
                    mutableLiveData.value =
                        result.errorBody()?.let {
                            getErrorBodyMessage(it).let { error ->
                                Response.Error(error) }
                        }
                } catch (e: JSONException) {
                    Log.d("Fail1", result.message())
                    mutableLiveData.value = Response.Error(result.message())
                    e.printStackTrace()
                }
            }
        }catch (exc: Exception){
            Log.d("exc", exc.toString())
        }
    }

    val getDataMutableLiveData = MutableLiveData<Response<List<GetDetails.GetDetailsItem>>>()
    suspend fun getData(){
        getDataMutableLiveData.value = Response.Loading(true)
        try {
            val result = serverApi.getDetails()
            if (result.isSuccessful){
                getDataMutableLiveData.value = Response.Success(result.body())
                getDataMutableLiveData.value = Response.Loading(false)
            }else{
                getDataMutableLiveData.value = Response.Loading(false)
                try {
                    getDataMutableLiveData.value =
                        result.errorBody()?.let {
                            getErrorBodyMessage(it).let { error ->
                                Response.Error(error) }
                        }
                } catch (e: JSONException) {
                    Log.d("Fail1", result.message())
                    getDataMutableLiveData.value = Response.Error(result.message())
                    e.printStackTrace()
                }
            }
        }catch (exc: Exception){
            Log.d("exc", exc.toString())
        }
    }
}