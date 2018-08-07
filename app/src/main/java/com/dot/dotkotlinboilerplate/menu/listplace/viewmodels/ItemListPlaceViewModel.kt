package com.dot.dotkotlinboilerplate.menu.listplace.viewmodels

import android.app.Activity
import android.content.Intent
import android.databinding.ObservableField
import android.view.View
import com.dot.dotkotlinboilerplate.menu.listplace.models.ListPlaceResponseModel.ListPlaceModel
import com.dot.dotkotlinboilerplate.menu.listplace.views.DetailMainActivity

class ItemListPlaceViewModel(private val activity: Activity, private val model: ListPlaceModel) {

    var title: ObservableField<String?> = ObservableField(model.name)
    var location: ObservableField<String?> = ObservableField(model.location)
    var imageUrl: ObservableField<String?> = ObservableField(model.image)

    fun clickItemListPlace(view: View){
        val intent = Intent(activity, DetailMainActivity::class.java)
        intent.putExtra(DetailMainActivity.EXTRA_DATA_LIST, model)
        activity.startActivity(intent)
    }

}