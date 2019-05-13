package com.nikitamaslov.navigation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.navigation.NavDirections

interface Navigator {

    val navEvents: LiveData<NavEvent>

    fun navigateTo(direction: NavDirections)

    fun navigateBack()
}

internal class NavigatorImpl : Navigator {

    private val _navEvents = MutableLiveData<NavEvent>()
    override val navEvents: LiveData<NavEvent> get() = _navEvents

    override fun navigateTo(direction: NavDirections) {
        val navCommand = NavCommand.To(direction)
        _navEvents.value = NavEvent(navCommand)
    }

    override fun navigateBack() {
        val navCommand = NavCommand.Back
        _navEvents.value = NavEvent(navCommand)
    }
}