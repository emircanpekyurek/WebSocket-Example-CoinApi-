package com.pekyurek.emircan.presentation.ui.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.CallSuper
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding
import com.pekyurek.emircan.presentation.core.dialog.ExceptionDialog
import com.pekyurek.emircan.presentation.core.dialog.LoadingFragmentDialog

abstract class BaseFragment<Binding : ViewBinding, VM : BaseViewModel> : Fragment() {

    abstract val viewModel: VM
    abstract val layoutResId: Int

    protected lateinit var binding: Binding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        if (::binding.isInitialized.not()) {
            binding = DataBindingUtil.inflate(inflater, layoutResId, container, false)
            initBinding()
            initViews()
            setObservers()
            onInit()
        }
        return binding.root
    }

    open fun onInit() {}

    open fun initBinding() {}

    open fun initViews() {}

    @CallSuper
    open fun setObservers() {
        viewModel.loading.observe(viewLifecycleOwner) { showLoading ->
            if (showLoading) LoadingFragmentDialog.show(parentFragmentManager) else LoadingFragmentDialog.dismiss()
        }

        viewModel.showErrorPopup.observe(viewLifecycleOwner) { exception ->
            context?.let { ExceptionDialog(it, exception).show() }
        }
    }
}