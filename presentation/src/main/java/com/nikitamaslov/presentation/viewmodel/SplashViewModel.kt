package com.nikitamaslov.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.nikitamaslov.domain.interactor.application.InitializeApplicationUseCase
import com.nikitamaslov.navigation.NavEvent
import com.nikitamaslov.navigation.Navigator
import com.nikitamaslov.ui.splash.SplashFragmentDirections
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch

class SplashViewModel(
    coroutineScope: CoroutineScope,
    private val navigator: Navigator,
    private val initializeApplicationUseCase: InitializeApplicationUseCase
) : ViewModel(), CoroutineScope by coroutineScope {

    val navEvents: LiveData<NavEvent> get() = navigator.navEvents

    private val _loading = MutableLiveData<Boolean>()
    val loading: LiveData<Boolean> get() = _loading

    init {
        initApplication()
    }

    private fun initApplication() =
        launch {
            _loading.value = true
            initializeApplicationUseCase.invoke()
            _loading.value = false
            navigateToForecast()
        }

    private fun navigateToForecast() {
        val direction = SplashFragmentDirections.toForecast()
        navigator.navigateTo(direction)
    }

    override fun onCleared() {
        coroutineContext.cancel()
    }
}