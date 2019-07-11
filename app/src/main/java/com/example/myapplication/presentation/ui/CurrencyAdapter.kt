package com.example.myapplication.presentation.ui

import android.content.Context
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.myapplication.R
import com.example.myapplication.domain.models.CurrencyRateModel
import kotlinx.android.synthetic.main.item_currency.view.*
import timber.log.Timber


class CurrencyAdapter(
    private val context: Context,
    inline val update: (currency: String, price: Float) -> Unit
) : RecyclerView.Adapter<CurrencyAdapter.CurrencyViewHolder>() {

    var rates: ArrayList<CurrencyRateModel>? = null
    val textWatchers = hashMapOf<Int, TextWatcher>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CurrencyViewHolder =
        CurrencyViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_currency, parent, false)
        )

    override fun getItemCount(): Int =
        rates?.size ?: 0

    override fun onBindViewHolder(holder: CurrencyViewHolder, position: Int) {
        rates?.also {
            it[position].also { model ->
                holder.bindTo(model)
            }
        }
    }

    fun updateList(curs: List<CurrencyRateModel>) {
        if (rates != null) {
            rates?.also {
                val diffResult = DiffUtil.calculateDiff(CurrencyDiffCallback(it, curs))
                it.clear()
                it.addAll(curs)
                diffResult.dispatchUpdatesTo(this)
            }
        } else {
            rates = ArrayList(curs)
            notifyDataSetChanged()
        }
    }

    inner class CurrencyViewHolder(private val item: View) : RecyclerView.ViewHolder(item) {

        fun bindTo(currency: CurrencyRateModel) {
            if (item.input_price.hashCode() in textWatchers.keys) {
                item.input_price.removeTextChangedListener(textWatchers[item.input_price.hashCode()])
            }

            if (item.textView_code.text != currency.currency) {
                try {
                    Glide.with(context)
                        .load(
                            context.resources.getIdentifier(
                                "flag_${currency.currency.toLowerCase()}",
                                "drawable",
                                context.packageName
                            )
                        )
                        .apply(RequestOptions.circleCropTransform())
                        .into(item.imageView)
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }

            item.textView_code.text = currency.currency
            item.textView_currency.text =
                context.resources.getString(processCurrency(currency.currency))
            item.input_price.setText(String.format("%.0f", currency.price).trim())
            if (currency.base) {
                item.setOnClickListener { }
                val textWatcher = object : TextWatcher {
                    override fun afterTextChanged(s: Editable?) {
                    }

                    override fun beforeTextChanged(
                        s: CharSequence?,
                        start: Int,
                        count: Int,
                        after: Int
                    ) {
                    }

                    override fun onTextChanged(
                        s: CharSequence?,
                        start: Int,
                        before: Int,
                        count: Int
                    ) {
                        Timber.i("Text changed $s")
                        rates?.also {
                            s?.also { num ->
                                if (num.matches("-?\\d+(\\.\\d+)?".toRegex())) {
                                    update.invoke(it[0].currency, num.toString().toFloat())
                                }
                            }
                        }
                    }
                }
                textWatchers[item.input_price.hashCode()] = textWatcher
                item.input_price.addTextChangedListener(textWatcher)
                item.textView_code.isClickable = true
                item.textView_currency.isClickable = true
                item.input_price.isClickable = true
                item.input_price.isFocusableInTouchMode = true
                item.input_price.invalidate()
            } else {
                item.textView_code.isClickable = false
                item.textView_currency.isClickable = false
                item.input_price.isClickable = false
                item.input_price.isFocusable = false
                item.setOnClickListener {
                    Timber.i("Currency clicked ${currency.currency}")
                    update.invoke(currency.currency, currency.price)
                }
            }

        }

        private fun processCurrency(currency: String): Int {
            return when (currency) {
                "AUD" -> R.string.currency_gbp
                "BGN" -> R.string.currency_gbp
                "BRL" -> R.string.currency_gbp
                "CAD" -> R.string.currency_gbp
                "CHF" -> R.string.currency_gbp
                "CNY" -> R.string.currency_gbp
                "CZK" -> R.string.currency_gbp
                "DKK" -> R.string.currency_gbp
                "GBP" -> R.string.currency_gbp
                "HKD" -> R.string.currency_gbp
                "HRK" -> R.string.currency_gbp
                "HUF" -> R.string.currency_gbp
                "IDR" -> R.string.currency_gbp
                "ILS" -> R.string.currency_gbp
                "INR" -> R.string.currency_gbp
                "ISK" -> R.string.currency_gbp
                "JPY" -> R.string.currency_gbp
                "KRW" -> R.string.currency_gbp
                "MXN" -> R.string.currency_gbp
                "MYR" -> R.string.currency_gbp
                "NOK" -> R.string.currency_gbp
                "NZD" -> R.string.currency_gbp
                "PHP" -> R.string.currency_gbp
                "PLN" -> R.string.currency_gbp
                "RON" -> R.string.currency_gbp
                "RUB" -> R.string.currency_gbp
                "SEK" -> R.string.currency_gbp
                "SGD" -> R.string.currency_gbp
                "THB" -> R.string.currency_gbp
                "TRY" -> R.string.currency_gbp
                "ZAR" -> R.string.currency_gbp
                "EUR" -> R.string.currency_gbp
                "USD" -> R.string.currency_gbp
                else -> R.string.currency_unknown
            }
        }
    }

}