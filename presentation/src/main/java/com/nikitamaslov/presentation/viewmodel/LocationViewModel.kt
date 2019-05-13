package com.nikitamaslov.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.nikitamaslov.domain.interactor.location.ReceiveLocationUseCase
import com.nikitamaslov.domain.interactor.location.SetLocationUseCase
import com.nikitamaslov.navigation.NavEvent
import com.nikitamaslov.navigation.Navigator
import com.nikitamaslov.presentation.mapper.mapToLocation
import com.nikitamaslov.presentation.mapper.mapToLocationFigure
import com.nikitamaslov.presentation.model.LocationFigure
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch

class LocationViewModel(
    coroutineScope: CoroutineScope,
    private val navigator: Navigator,
    private val receiveLocationUseCase: ReceiveLocationUseCase,
    private val setLocationUseCase: SetLocationUseCase
) : ViewModel(), CoroutineScope by coroutineScope {

    val navEvents: LiveData<NavEvent> get() = navigator.navEvents

    private val _locations = MutableLiveData<LocationFigure?>()
    val locations: LiveData<LocationFigure?> get() = _locations

    init {
        consumeLocationChannel()
    }

    private fun consumeLocationChannel() =
        launch {
            for (location in receiveLocationUseCase.invoke()) {
                _locations.value = location?.mapToLocationFigure()
            }
        }

    fun setLocation(value: LocationFigure) {
        launch {
            setLocationUseCase.invoke(value.mapToLocation())
            navigateToForecast()
        }
    }

    private fun navigateToForecast() {
        navigator.navigateBack()
    }

    override fun onCleared() {
        coroutineContext.cancel()
    }
}