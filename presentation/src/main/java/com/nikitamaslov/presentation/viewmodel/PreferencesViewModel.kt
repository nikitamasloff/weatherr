package com.nikitamaslov.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.nikitamaslov.domain.interactor.preferences.ReceiveTemperatureUnitUseCase
import com.nikitamaslov.domain.interactor.preferences.SetTemperatureUnitUseCase
import com.nikitamaslov.navigation.NavEvent
import com.nikitamaslov.navigation.Navigator
import com.nikitamaslov.presentation.mapper.mapToTemperatureUnit
import com.nikitamaslov.presentation.mapper.mapToTemperatureUnitFigure
import com.nikitamaslov.presentation.model.TemperatureFigure
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch

class PreferencesViewModel(
    coroutineScope: CoroutineScope,
    private val navigator: Navigator,
    private val receiveTemperatureUnitUseCase: ReceiveTemperatureUnitUseCase,
    private val setTemperatureUnitUseCase: SetTemperatureUnitUseCase
) : ViewModel(), CoroutineScope by coroutineScope {

    val navEvents: LiveData<NavEvent> get() = navigator.navEvents

    private val _temperatureUnits = MutableLiveData<TemperatureFigure.Unit>()
    val temperatureUnits: LiveData<TemperatureFigure.Unit> get() = _temperatureUnits

    init {
        consumeTemperatureUnitChannel()
    }

    fun setTemperatureUnit(value: TemperatureFigure.Unit) {
        launch {
            setTemperatureUnitUseCase.invoke(value.mapToTemperatureUnit())
        }
    }

    private fun consumeTemperatureUnitChannel() =
        launch {
            for (temperatureUnit in receiveTemperatureUnitUseCase.invoke()) {
                _temperatureUnits.value = temperatureUnit.mapToTemperatureUnitFigure()
            }
        }

    @Suppress("unused")
    private fun navigateToForecast() {
        navigator.navigateBack()
    }

    override fun onCleared() {
        coroutineContext.cancel()
    }
}