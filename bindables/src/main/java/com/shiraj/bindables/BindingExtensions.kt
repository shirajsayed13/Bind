package com.shiraj.bindables

import kotlin.reflect.KProperty


internal fun KProperty<*>.bindingId(): Int {
    return BindingManager.getBindingIdByProperty(this)
}
