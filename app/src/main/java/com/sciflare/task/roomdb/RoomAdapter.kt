package com.sciflare.task.roomdb

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.sciflare.task.databinding.AdapterLayoutBinding
import com.sciflare.task.model.SaveData


/**
 * A RecyclerAdapter in Android is a custom adapter used with a RecyclerView to bind data to its layout, creating efficient and flexible lists or grids.
 * It manages the creation and recycling of view holders to optimize performance.
 */

class RoomAdapter(private val saveData : List<SaveData>?,) : RecyclerView.Adapter<RoomAdapter.MainViewHolder>() {
    inner class MainViewHolder(private val binding: AdapterLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun setView(sample: SaveData) {
            binding.tvName.text = sample.name.toString()
            binding.tvEmail.text = sample.email.toString()
            binding.tvMobile.text = sample.mobile.toString()
            binding.tvGender.text = sample.gender.toString()
        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        return MainViewHolder(
            AdapterLayoutBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        saveData?.get(position)?.let { holder.setView(it) }
        holder.setIsRecyclable(false)
    }

    override fun getItemCount(): Int {
        return saveData!!.size
    }

}

