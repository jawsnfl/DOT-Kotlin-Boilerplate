package com.dot.dotkotlinboilerplate.menu.listplace.viewmodels

import androidx.databinding.ObservableField
import com.dot.dotkotlinboilerplate.menu.listplace.models.ListPlaceResponseModel

class DetailMainViewModel {

    var name: ObservableField<String> = ObservableField()
    var location: ObservableField<String> = ObservableField()
    var image: ObservableField<String> = ObservableField()

    fun showData(listPlaceModel: ListPlaceResponseModel.ListPlaceModel){
        this.name.set(listPlaceModel.name)
        this.location.set(listPlaceModel.location)
        this.image.set(listPlaceModel.image)
    }

}