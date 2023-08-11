package com.example.core

import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer

/**
 * @author asadurrahman.qayyim
 * @date 01-Aug-2023
 */

fun <T> Fragment.observeData(data: LiveData<T>, observer: Observer<T>) {
    data.observe(viewLifecycleOwner, observer)
}

fun <T> Fragment.observeData(data: LiveData<T>, onChanged: (T?) -> Unit) {
    observeData(data, Observer { onChanged(it) })
}

fun Fragment.toast(msg: String?) {
    Toast.makeText(requireContext(), msg, Toast.LENGTH_SHORT).show()
}