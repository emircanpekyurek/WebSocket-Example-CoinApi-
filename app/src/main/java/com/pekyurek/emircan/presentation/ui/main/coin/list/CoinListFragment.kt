package com.pekyurek.emircan.presentation.ui.main.coin.list

import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.pekyurek.emircan.R
import com.pekyurek.emircan.databinding.FragmentCoinListBinding
import com.pekyurek.emircan.presentation.ui.base.BaseFragment
import com.pekyurek.emircan.presentation.ui.main.adapter.CoinAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CoinListFragment : BaseFragment<FragmentCoinListBinding, CoinListViewModel>() {

    override val layoutResId: Int = R.layout.fragment_coin_list

    override val viewModel: CoinListViewModel by viewModels()

    private val coinAdapter by lazy {
        CoinAdapter { navigatorExtra, coin ->
            val action = CoinListFragmentDirections.actionCoinListFragmentToCoinDetailFragment(coin)
            findNavController().navigate(action, navigatorExtra)
        }
    }

    override fun initBinding() {
        super.initBinding()
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
    }

    override fun initViews() {
        initRecyclerView()
    }

    private fun initRecyclerView() {
        binding.rvCoin.apply {
            adapter = coinAdapter
            itemAnimator = null
        }
    }

    override fun onInit() {
        initAnimation()
        viewModel.loadData()
    }

    private fun initAnimation() {
        postponeEnterTransition()
        binding.rvCoin.viewTreeObserver.addOnPreDrawListener {
            startPostponedEnterTransition()
            true
        }
    }

    override fun setObservers() {
        super.setObservers()
        viewModel.assetDetailList.observe(this) { list ->
            coinAdapter.setData(list)
            viewModel.connectWebSocket(list.map { it.symbolId })
        }

        viewModel.coinUpdate.observe(this) {
            coinAdapter.updatePrice(it.symbolId, it.price)
        }
    }

}