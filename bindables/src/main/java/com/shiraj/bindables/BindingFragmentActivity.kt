package com.shiraj.bindables

import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingComponent
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.FragmentActivity

abstract class BindingFragmentActivity<T : ViewDataBinding> constructor(
    @LayoutRes private val contentLayoutId: Int
) : FragmentActivity() {

    protected var bindingComponent: DataBindingComponent? = DataBindingUtil.getDefaultComponent()

    @BindingOnly
    protected val binding: T by lazy(LazyThreadSafetyMode.NONE) {
        DataBindingUtil.setContentView(this, contentLayoutId, bindingComponent)
    }

    @BindingOnly
    protected inline fun binding(block: T.() -> Unit): T {
        return binding.apply(block)
    }
}