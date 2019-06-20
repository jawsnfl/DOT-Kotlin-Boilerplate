package com.dot.dotkotlinboilerplate.menu.listplace.views

import androidx.databinding.DataBindingUtil
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.view.MenuItem
import com.dot.dotkotlinboilerplate.R
import com.dot.dotkotlinboilerplate.databinding.DetailActivityMainBinding
import com.dot.dotkotlinboilerplate.menu.listplace.models.ListPlaceResponseModel
import com.dot.dotkotlinboilerplate.menu.listplace.viewmodels.DetailMainViewModel

class DetailMainActivity: AppCompatActivity() {

    companion object {
        const val EXTRA_DATA_LIST = "extra_data_list"
    }

    private lateinit var binding: DetailActivityMainBinding
    private lateinit var viewModel: DetailMainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setupBinding()
        setupToolbar()
        setupData()
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if (item?.itemId==android.R.id.home) {
            onBackPressed()
        }
        return super.onOptionsItemSelected(item!!)
    }

    private fun setupBinding(){
        binding = DataBindingUtil.setContentView(this, R.layout.detail_activity_main)
        viewModel = DetailMainViewModel()
        binding.detail = viewModel
    }

    private fun setupToolbar(){
        setSupportActionBar(binding.toolbarDetailMain)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    private fun setupData(){
        if (intent.extras!=null) {
            val listPlaceModel: ListPlaceResponseModel.ListPlaceModel = intent.getParcelableExtra(EXTRA_DATA_LIST)!!
            viewModel.showData(listPlaceModel)
        }
    }

}