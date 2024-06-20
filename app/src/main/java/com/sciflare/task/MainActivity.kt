package com.sciflare.task

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.google.gson.JsonObject
import com.sciflare.task.api.Response
import com.sciflare.task.databinding.ActivityMainBinding
import com.sciflare.task.model.Create
import com.sciflare.task.model.GetDetails
import com.sciflare.task.model.SaveData
import com.sciflare.task.roomdb.RoomAdapter
import com.sciflare.task.roomdb.RoomDao
import com.sciflare.task.roomdb.RoomFactory
import com.sciflare.task.roomdb.RoomRepo
import com.sciflare.task.roomdb.RoomViewModel
import com.sciflare.task.viewmodel.CreateViewModel
import kotlinx.coroutines.launch


class MainActivity : AppCompatActivity() {
    private val mainActivity by lazy { ActivityMainBinding.inflate(layoutInflater) }
    private  lateinit var roomViewModel  : RoomViewModel
    private  var adapter: RoomAdapter?=null
    private val viewModel: CreateViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(mainActivity.root)
        val roomRepo = RoomRepo(RoomDao.getDatabase(this))
        val factory = RoomFactory(roomRepo)
        roomViewModel = ViewModelProvider(this, factory)[RoomViewModel::class.java]
        mainActivity.createButton.setOnClickListener {
            createData()
        }
        getData()
        viewData()
    }
    private fun createData() {
        mainActivity.apply {
            if (tvName.text.toString().isEmpty()){
                Toast.makeText(this@MainActivity,"Select name", Toast.LENGTH_SHORT).show()
            }
            else if (tvEmail.text.toString().isEmpty()){
                Toast.makeText(this@MainActivity,"Select email", Toast.LENGTH_SHORT).show()
            }
            else if (tvMobile.text.toString().isEmpty()){
                Toast.makeText(this@MainActivity,"Select mobile", Toast.LENGTH_SHORT).show()
            }
            else if (gender.text.toString().isEmpty()){
                Toast.makeText(this@MainActivity,"Select gender", Toast.LENGTH_SHORT).show()
            }
            else{
                val jsonObject = JsonObject().apply {
                    addProperty("name", mainActivity.tvName.text.toString())
                    addProperty("email", mainActivity.tvEmail.text.toString())
                    addProperty("Mobile", mainActivity.tvMobile.text.toString())
                    addProperty("Gender", mainActivity.gender.text.toString())
                }
                lifecycleScope.launch {
                    viewModel.sendDetails(jsonObject)
                }
                viewModel.mutableLiveDataResponse.observe(this@MainActivity, createDataListObserver)
                Toast.makeText(this@MainActivity,"Save successfully", Toast.LENGTH_SHORT).show()
                mainActivity.tvName.text.clear()
                mainActivity.tvEmail.text.clear()
                mainActivity.tvMobile.text.clear()
                mainActivity.gender.text.clear()
            }
            }
    }
    @SuppressLint("NotifyDataSetChanged")
    private val createDataListObserver = Observer<Response<Create>> {
        when (it) {
            is Response.Success -> {
                Log.d("TAG", "${it.data}: ")
                getData()
                viewData()
                adapter?.notifyDataSetChanged()

            }
            is Response.Error -> {
                Log.d("TAG", "${it.errorMessage}: ") }
            is Response.Loading -> {}
        }
    }
    private fun getData(){
        lifecycleScope.launch {
            viewModel.getDetails()
        }
        viewModel.getMutableLiveData.observe(this,getObservableData)
    }
    @SuppressLint("NotifyDataSetChanged")
    private val getObservableData =Observer<Response<List<GetDetails.GetDetailsItem>>>{
        when (it) {
            is Response.Success -> {
                Log.d("TAG", "${it.data}: ")
                for (i in it.data!!){
                    lifecycleScope.launch {  roomViewModel.insert(SaveData(i.name,i.email,i.mobile,i.gender)) }
                }
            }
            is Response.Error -> {
                Log.d("TAG", "${it.errorMessage}: ") }
            is Response.Loading -> {}
        }
    }
    @SuppressLint("NotifyDataSetChanged")
    private fun viewData() {
        lifecycleScope.launch {
            roomViewModel.getList().collect {
                adapter= RoomAdapter(it)
                mainActivity.recyclerview.adapter = adapter
                adapter!!.notifyDataSetChanged()
            }
        }
    }
}
