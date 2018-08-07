package com.dot.dotkotlinboilerplate.menu.listplace.adapters

import android.app.Activity
import android.databinding.DataBindingUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.dot.dotkotlinboilerplate.R
import com.dot.dotkotlinboilerplate.databinding.ItemListPlaceBinding
import com.dot.dotkotlinboilerplate.menu.listplace.models.ListPlaceResponseModel
import com.dot.dotkotlinboilerplate.menu.listplace.viewmodels.ItemListPlaceViewModel

class ListPlaceAdapter(private val activity: Activity, private var listPlace: MutableList<ListPlaceResponseModel.ListPlaceModel>): RecyclerView.Adapter<ListPlaceAdapter.ItemListPlaceViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): ItemListPlaceViewHolder {
        val binding: ItemListPlaceBinding = DataBindingUtil.inflate(LayoutInflater.from(activity), R.layout.item_list_place, parent, false)
        return ItemListPlaceViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return listPlace.size
    }

    override fun onBindViewHolder(holder: ItemListPlaceViewHolder, position: Int) {
        val fixPosition = holder.adapterPosition
        holder.bindBinding(activity, listPlace[fixPosition])
    }

    class ItemListPlaceViewHolder(val binding: ItemListPlaceBinding) : RecyclerView.ViewHolder(binding.root) {

        private lateinit var viewModel: ItemListPlaceViewModel

        fun bindBinding(activity: Activity, model: ListPlaceResponseModel.ListPlaceModel){
            viewModel = ItemListPlaceViewModel(activity, model)
            binding.itemListPlace = viewModel
            binding.executePendingBindings()
        }

    }
}