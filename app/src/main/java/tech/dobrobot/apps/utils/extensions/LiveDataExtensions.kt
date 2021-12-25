package tech.dobrobot.apps.utils.extensions

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData

fun <T> LiveData<T>.doOnChange(owner: LifecycleOwner, f: (T) -> Unit) { observe(owner, {f(it)}) }