package com.example.numberslight.view.recyclerview

import androidx.recyclerview.widget.DiffUtil
import com.example.numberslight.model.ListElement

class ListDiffUtils(
    private val oldList: List<ListElement>,
    private val newList: List<ListElement>
) : DiffUtil.Callback() {
    override fun getOldListSize() = oldList.size

    override fun getNewListSize() = newList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int) =
        oldList[oldItemPosition].name == newList[newItemPosition].name

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int) =
        oldList[oldItemPosition].name == newList[newItemPosition].name

}