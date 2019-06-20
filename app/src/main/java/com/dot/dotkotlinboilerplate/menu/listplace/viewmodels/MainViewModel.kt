package com.dot.dotkotlinboilerplate.menu.listplace.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.databinding.ObservableField
import com.dot.dotkotlinboilerplate.menu.listplace.models.ListPlaceResponseModel
import com.dot.dotkotlinboilerplate.networks.repositories.MainRepository

class MainViewModel(application: Application): AndroidViewModel(application) {

    var isLoading: ObservableField<Boolean>  = ObservableField()
    var listDataResponse: MutableLiveData<ListPlaceResponseModel> = MutableLiveData()
    var error: MutableLiveData<Throwable> = MutableLiveData()

    private var mainRepository = MainRepository()

    fun requestListPlace(){
        isLoading.set(true)
        mainRepository.getListPlace({
            isLoading.set(false)
            listDataResponse.postValue(it)
        },{
            isLoading.set(false)
        })
    }

    override fun onCleared(){
        super.onCleared()
        mainRepository.cleared()
    }

}