package com.pekyurek.emircan.presentation.ui.main.adapter

import android.annotation.SuppressLint
import android.view.ViewGroup
import androidx.navigation.fragment.FragmentNavigator
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.recyclerview.widget.RecyclerView
import com.pekyurek.emircan.R
import com.pekyurek.emircan.databinding.ItemCoinBinding
import com.pekyurek.emircan.domain.model.mapper.CoinDetail
import com.pekyurek.emircan.presentation.core.extensions.inflateDataBinding

class CoinAdapter(private val onItemClick: (navigationExtra: FragmentNavigator.Extras, coinDetail: CoinDetail) -> Unit) :
    RecyclerView.Adapter<CoinAdapter.ViewHolder>() {


    private val coinList = mutableListOf<CoinDetail>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(parent.inflateDataBinding(R.layout.item_coin))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(coinList[position])
    }

    override fun getItemCount(): Int = coinList.size

    @SuppressLint("NotifyDataSetChanged")
    fun setData(list: List<CoinDetail>) {
        coinList.clear()
        coinList.addAll(list)
        notifyDataSetChanged()
    }

    fun updatePrice(symbolId: String, price: Double) {
        val index = coinList.indexOfFirst { it.symbolId == symbolId }
        coinList.getOrNull(index)?.let {
            it.setPrice(price)
            notifyItemChanged(index)
        }
    }

    inner class ViewHolder(val binding: ItemCoinBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(coin: CoinDetail) {
            binding.coin = coin
            binding.executePendingBindings()
            binding.root.setOnClickListener {
                onItemClick.invoke(
                    FragmentNavigatorExtras(binding.ivCoinIcon to binding.ivCoinIcon.transitionName),
                    coin
                )
            }
        }
    }
}