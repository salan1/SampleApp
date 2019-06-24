package com.example.myapplication.presentation.ui

import androidx.recyclerview.widget.DiffUtil
import com.example.myapplication.domain.models.CurrencyRateModel

class CurrencyDiffCallback(
        private val oldProducts: List<CurrencyRateModel>,
        private val newProducts: List<CurrencyRateModel>
) : DiffUtil.Callback() {

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
            (oldProducts[oldItemPosition].currency == oldProducts[oldItemPosition].currency)

    override fun getOldListSize(): Int = oldProducts.size

    override fun getNewListSize(): Int = newProducts.size

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
            oldProducts[oldItemPosition] == newProducts[newItemPosition]

}