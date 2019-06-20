package com.dot.dotkotlinboilerplate.networks.repositories

import com.dot.dotkotlinboilerplate.menu.listplace.models.ListPlaceResponseModel
import com.dot.dotkotlinboilerplate.networks.ApiObserver
import com.dot.dotkotlinboilerplate.networks.ServiceFactory
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class MainRepository {

    private val compositeDisposable = CompositeDisposable()
    private val apiService = ServiceFactory.create()

    fun getListPlace(onResult: (ListPlaceResponseModel) -> Unit, onError: (Throwable) -> Unit){
        apiService.getListPlace()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : ApiObserver<ListPlaceResponseModel>(compositeDisposable) {
                    override fun onApiSuccess(data: ListPlaceResponseModel) {
                        onResult(data)
                    }
                    override fun onApiError(er: Throwable) {
                        onError(er)
                    }
                })
    }

    fun cleared(){
        compositeDisposable.clear()
    }

}