package com.nikitamaslov.navigation

class NavEvent(private val navCommand: NavCommand) {

    var hasBeenHandled: Boolean = false
        private set

    fun getNavCommandIfNotHandled(): NavCommand? =
        navCommand.takeIf { !hasBeenHandled }?.also { hasBeenHandled = true }

    fun peekNavCommand(): NavCommand = navCommand
}