package com.elhady.klivvr.ui

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.elhady.klivvr.BR
import com.elhady.klivvr.R
import com.elhady.klivvr.databinding.ItemCityBinding
import com.elhady.klivvr.domain.model.City

class CityAdapter(private var list: List<City>, private val listener: CityInteractionListener): RecyclerView.Adapter<CityAdapter.CityViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CityViewHolder {
        return CityViewHolder(DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.item_city, parent, false))
    }

    override fun onBindViewHolder(holder: CityViewHolder, position: Int) {
        val currentItem = list[position]
        holder.binding.apply {
            setVariable(BR.item, currentItem)
            setVariable(BR.listener, listener)
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setList(newList: List<City>) {
//        val diffUtil = DiffUtil.calculateDiff(CityDiffUtil(list, newList))
        list = newList
        notifyDataSetChanged()
//        diffUtil.dispatchUpdatesTo(this)
    }

    override fun getItemCount() = list.size

    class CityViewHolder(val binding: ViewDataBinding) : RecyclerView.ViewHolder(binding.root)
}