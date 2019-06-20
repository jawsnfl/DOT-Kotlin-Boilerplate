package com.dot.dotkotlinboilerplate.menu.listplace.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.databinding.ObservableField
import com.dot.dotkotlinboilerplate.menu.listplace.models.ListPlaceResponseModel
import com.dot.dotkotlinboilerplate.networks.ApiObserver
import com.dot.dotkotlinboilerplate.networks.ServiceFactory

import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class MainViewModel(application: Application): AndroidViewModel(application) {

    private val compositeDisposable = CompositeDisposable()
    private val apiService = ServiceFactory.create()

    var isLoading: ObservableField<Boolean>  = ObservableField()
    var listDataResponse: MutableLiveData<ListPlaceResponseModel> = MutableLiveData()
    var error: MutableLiveData<Throwable> = MutableLiveData()

    fun requestListPlace(){
        isLoading.set(true)
        apiService.getListPlace()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : ApiObserver<ListPlaceResponseModel>(compositeDisposable) {
                    override fun onApiSuccess(data: ListPlaceResponseModel) {
                        isLoading.set(false)
                        listDataResponse.value = data
                    }

                    override fun onApiError(er: Throwable) {
                        isLoading.set(false)
                        error.value = er
                    }

                })


    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }

}