package com.dot.dotkotlinboilerplate.menu.listplace.views

import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.databinding.DataBindingUtil
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import android.widget.Toast
import com.dot.dotkotlinboilerplate.R
import com.dot.dotkotlinboilerplate.databinding.ActivityMainBinding
import com.dot.dotkotlinboilerplate.menu.listplace.adapters.ListPlaceAdapter
import com.dot.dotkotlinboilerplate.menu.listplace.models.ListPlaceResponseModel.ListPlaceModel
import com.dot.dotkotlinboilerplate.menu.listplace.models.ListPlaceResponseModel
import com.dot.dotkotlinboilerplate.menu.listplace.viewmodels.MainViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity: AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: MainViewModel

    private lateinit var adapter: ListPlaceAdapter
    private var listPlace: MutableList<ListPlaceModel> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setupBinding()
        setupToolbar()
        setupSwipeRefresh()
        setupRecycler()

        viewModel.requestListPlace()

    }

    override fun onDestroy() {
        super.onDestroy()
        viewModel.destroy()
    }

    private fun setupBinding(){
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        viewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)
        binding.main = viewModel

        viewModel.listDataResponse.observe(this, Observer {
            onListDataChange(it)
        })
        viewModel.error.observe(this, Observer {
            handlingError(it)
        })
    }

    private fun setupToolbar(){
        setSupportActionBar(binding.toolbarMain)
    }

    private fun setupSwipeRefresh(){
        binding.swipeRefreshLayoutMain.setOnRefreshListener {
            binding.swipeRefreshLayoutMain.isRefreshing = false
            viewModel.requestListPlace()
        }
    }

    private fun setupRecycler(){
        val lManager = LinearLayoutManager(this)
        binding.recyclerViewMain.layoutManager = lManager
        binding.recyclerViewMain.setHasFixedSize(true)

        adapter = ListPlaceAdapter(this, listPlace)
        binding.recyclerViewMain.adapter = adapter
    }

    private fun onListDataChange(listPlaceResponseModel: ListPlaceResponseModel?){
        listPlace.clear()
        listPlace.addAll(listPlaceResponseModel?.data!!)
        recyclerViewMain.post {
            adapter.notifyDataSetChanged()
        }
    }

    private fun handlingError(throwable: Throwable?){
        Toast.makeText(this, throwable?.message, Toast.LENGTH_SHORT).show()
    }

}