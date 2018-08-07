package com.dot.dotkotlinboilerplate.menu.listplace.viewmodels

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.MutableLiveData
import android.databinding.ObservableField
import com.dot.dotkotlinboilerplate.menu.listplace.models.ListPlaceResponseModel
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
        compositeDisposable.add(
            apiService.getListPlace()
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(
                            {
                                isLoading.set(false)
                                listDataResponse.value = it
                            },
                            {
                                isLoading.set(false)
                                error.value = it
                            }
                    )
        )
    }

    fun destroy(){
        compositeDisposable.clear()
    }

}