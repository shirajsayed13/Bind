package com.shiraj.bindables

import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingComponent
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding

abstract class BindingActivity<T : ViewDataBinding> constructor(
    @LayoutRes private val contentLayoutId: Int
) : AppCompatActivity() {

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