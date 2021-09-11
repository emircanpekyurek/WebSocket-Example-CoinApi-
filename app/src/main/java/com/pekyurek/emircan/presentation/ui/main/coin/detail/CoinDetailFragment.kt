package com.pekyurek.emircan.presentation.ui.main.coin.detail

import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import androidx.transition.TransitionInflater
import com.pekyurek.emircan.R
import com.pekyurek.emircan.databinding.FragmentCoinDetailBinding
import com.pekyurek.emircan.presentation.ui.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CoinDetailFragment : BaseFragment<FragmentCoinDetailBinding, CoinDetailViewModel>() {

    override val layoutResId: Int = R.layout.fragment_coin_detail

    override val viewModel: CoinDetailViewModel by viewModels()

    private val args: CoinDetailFragmentArgs by navArgs()

    override fun onInit() {
        viewModel.loadData(args.argCoinDetail.symbolId)
    }

    override fun initBinding() {
        binding.coin = args.argCoinDetail
        binding.lifecycleOwner = this
    }

    override fun initViews() {
        sharedElementEnterTransition =
            TransitionInflater.from(context).inflateTransition(android.R.transition.move)
    }

    override fun setObservers() {
        super.setObservers()
        viewModel.historicalData.observe(this) {
            binding.tvCoinHistory.text = it
        }
    }
}