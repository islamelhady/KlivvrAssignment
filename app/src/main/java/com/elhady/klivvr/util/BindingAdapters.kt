package com.elhady.klivvr.util

import android.view.View
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.elhady.klivvr.domain.model.City
import com.elhady.klivvr.ui.CityAdapter

@BindingAdapter(value = ["app:items"])
fun  RecyclerView.setRecyclerItems(items: List<City>?) {
    (adapter as CityAdapter).setList(items ?: emptyList())
    smoothScrollToPosition(0)
}

@BindingAdapter(value = ["app:isVisible"])
fun View.isVisibleOrGone(isVisible: Boolean?) {
    if (isVisible == true) {
        this.visibility = View.VISIBLE
    } else {
        this.visibility = View.GONE
    }
}