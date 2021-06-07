package com.shiraj.bindables

import androidx.databinding.Observable
import androidx.databinding.PropertyChangeRegistry
import androidx.lifecycle.ViewModel
import kotlin.reflect.KProperty

abstract class BindingViewModel : ViewModel(), BindingObservable {

    private val lock: Any = Any()

    private var propertyCallbacks: PropertyChangeRegistry? = null

    override fun addOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {
        synchronized(lock) lock@{
            val propertyCallbacks = propertyCallbacks
                ?: PropertyChangeRegistry().also { propertyCallbacks = it }
            propertyCallbacks.add(callback)
        }
    }

    override fun removeOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {
        synchronized(lock) lock@{
            val propertyCallbacks = propertyCallbacks ?: return@lock
            propertyCallbacks.remove(callback)
        }
    }

    override fun notifyPropertyChanged(property: KProperty<*>) {
        synchronized(lock) lock@{
            val propertyCallbacks = propertyCallbacks ?: return@lock
            propertyCallbacks.notifyCallbacks(this, property.bindingId(), null)
        }
    }
}