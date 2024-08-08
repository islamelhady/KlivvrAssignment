package com.elhady.klivvr.ui

import androidx.recyclerview.widget.DiffUtil
import com.elhady.klivvr.domain.model.City

class CityDiffUtil(private val mOldList: List<City>, private val mNewList: List<City>): DiffUtil.Callback() {
    override fun getOldListSize() = mOldList.size

    override fun getNewListSize() = mNewList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return  mOldList[oldItemPosition].id == mNewList[newItemPosition].id
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return true
    }
}