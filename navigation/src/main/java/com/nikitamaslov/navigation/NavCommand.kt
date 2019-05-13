package com.nikitamaslov.navigation

import androidx.navigation.NavDirections

sealed class NavCommand {

    data class To(val direction: NavDirections) : NavCommand()
    object Back : NavCommand()
}