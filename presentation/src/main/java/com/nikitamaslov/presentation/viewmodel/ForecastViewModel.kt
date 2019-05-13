package com.nikitamaslov.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.nikitamaslov.domain.interactor.forecast.ReceiveForecastUseCase
import com.nikitamaslov.domain.interactor.forecast.UpdateForecastUseCase
import com.nikitamaslov.navigation.NavEvent
import com.nikitamaslov.navigation.Navigator
import com.nikitamaslov.presentation.mapper.mapToForecastFigure
import com.nikitamaslov.presentation.model.DateTimeFigure
import com.nikitamaslov.presentation.model.ForecastFigure
import com.nikitamaslov.presentation.model.NotificationFigure
import com.nikitamaslov.ui.forecast.ForecastFragmentDirections
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch

class ForecastViewModel(
    coroutineScope: CoroutineScope,
    private val navigator: Navigator,
    private val receiveForecastUseCase: ReceiveForecastUseCase,
    private val updateForecastUseCase: UpdateForecastUseCase
) : ViewModel(), CoroutineScope by coroutineScope {

    val navEvents: LiveData<NavEvent> get() = navigator.navEvents

    private val _forecasts = MutableLiveData<ForecastFigure?>()
    val forecasts: LiveData<ForecastFigure?> get() = _forecasts

    private val _loading = MutableLiveData<Boolean>()
    val loading: LiveData<Boolean> get() = _loading

    private val _notifications = MutableLiveData<NotificationFigure?>()
    val notifications: LiveData<NotificationFigure?> get() = _notifications

    private val _updateDate = MutableLiveData<DateTimeFigure?>()
    val updateDate: LiveData<DateTimeFigure?> get() = _updateDate

    init {
        consumeForecastResponseChannel()
    }

    private fun consumeForecastResponseChannel() =
        launch {
            for (forecast in receiveForecastUseCase.invoke()) {
                _forecasts.value = forecast?.mapToForecastFigure()
            }
        }

    fun updateForecast() {
        launch {
            updateForecastUseCase.invoke()
        }
    }

    fun navigateToLocation() {
        val direction = ForecastFragmentDirections.toLocation()
        navigator.navigateTo(direction)
    }

    fun navigateToPreferences() {
        val direction = ForecastFragmentDirections.toPreferences()
        navigator.navigateTo(direction)
    }

    override fun onCleared() {
        coroutineContext.cancel()
    }
}