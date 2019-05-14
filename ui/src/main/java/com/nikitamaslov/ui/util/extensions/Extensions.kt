package com.nikitamaslov.ui.util.extensions

import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.nikitamaslov.navigation.NavCommand
import com.nikitamaslov.navigation.NavEvent

internal fun <T> Fragment.observe(src: LiveData<T>, observer: (T?) -> Unit) {
    src.observe(viewLifecycleOwner, Observer(observer))
}

@Suppress("FINAL_UPPER_BOUND")
internal fun <T : NavEvent> Fragment.observeNavigationEvents(navEvents: LiveData<T>) {
    val navController = findNavController()
    navEvents.observe(viewLifecycleOwner, Observer { navEvent: NavEvent? ->
        when (val navCommand = navEvent?.getNavCommandIfNotHandled()) {
            is NavCommand.To -> navController.navigate(navCommand.direction)
            is NavCommand.Back -> navController.navigateUp()
        }
    })
}