package com.shiraj.bindables

import androidx.databinding.Observable
import kotlin.reflect.KFunction
import kotlin.reflect.KProperty

interface BindingObservable : Observable {

    fun notifyPropertyChanged(property: KProperty<*>)

    fun notifyPropertyChanged(function: KFunction<*>)

    fun notifyPropertyChanged(bindingId: Int)

    fun notifyAllPropertiesChanged()

    fun clearAllProperties()

}