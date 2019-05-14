package com.nikitamaslov.ui.forecast

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment
import com.nikitamaslov.presentation.model.DateTimeFigure
import com.nikitamaslov.presentation.model.ForecastFigure
import com.nikitamaslov.presentation.model.NotificationFigure
import com.nikitamaslov.presentation.viewmodel.ForecastViewModel
import com.nikitamaslov.ui.R
import com.nikitamaslov.ui.mapper.mapToCurrentWeather
import com.nikitamaslov.ui.mapper.mapToDateTimeForm
import com.nikitamaslov.ui.mapper.mapToFutureWeather
import com.nikitamaslov.ui.mapper.mapToNotificationForm
import com.nikitamaslov.ui.model.CurrentWeatherForm
import com.nikitamaslov.ui.model.DateTimeForm
import com.nikitamaslov.ui.model.FutureWeatherForm
import com.nikitamaslov.ui.model.TemperatureForm
import com.nikitamaslov.ui.util.extensions.observe
import com.nikitamaslov.ui.util.extensions.observeNavigationEvents
import kotlinx.android.synthetic.main.fragment_forecast.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class ForecastFragment : Fragment() {

    private val viewModel: ForecastViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_forecast, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        init()
        observeLoading()
        observeNotifications()
        observeUpdateDate()
        observeForecast()
        observeNavigationEvents(viewModel.navEvents)
    }

    private fun init() {
        future_weather_list.adapter = FutureWeatherAdapter()
        btn_location.setOnClickListener { viewModel.navigateToLocation() }
        btn_update.setOnClickListener { viewModel.updateForecast() }
        btn_preferences.setOnClickListener { viewModel.navigateToPreferences() }
    }

    private fun observeLoading() {
        observe(viewModel.loading) { isLoading: Boolean? ->
            progress_bar.visible = isLoading == true
        }
    }

    private fun observeNotifications() {
        observe(viewModel.notifications) { notification: NotificationFigure? ->
            status_container.visible = notification != null
            status_content.text = notification?.mapToNotificationForm()?.toString()
        }
    }

    private fun observeUpdateDate() {
        observe(viewModel.updateDate) { dateTime: DateTimeFigure? ->
            update_date_container.visible = dateTime != null
            update_date_content.text = dateTime?.mapToDateTimeForm()?.toPrettyString()
        }
    }

    private fun observeForecast() {
        observe(viewModel.forecasts) { forecast: ForecastFigure? ->
            current_weather_content.text = forecast?.currentWeather
                ?.mapToCurrentWeather()
                ?.toPrettyString()
            (future_weather_list.adapter as FutureWeatherAdapter).apply {
                clear()
                val items = forecast?.futureWeather
                    ?.map { it.mapToFutureWeather() }
                    ?: emptyList()
                addAll(items)
            }
        }
    }

    private inner class FutureWeatherAdapter(items: Array<FutureWeatherForm>) :
        ArrayAdapter<FutureWeatherForm>(requireContext(), 0, items) {

        constructor() : this(emptyArray())

        override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
            val view = convertView ?: inflate(parent, android.R.layout.simple_list_item_1)
            getItem(position)?.let { futureWeather ->
                view.findViewById<TextView>(android.R.id.text1).text =
                    futureWeather.toPrettyString()
            }
            return view
        }

        private fun inflate(container: ViewGroup?, @LayoutRes layoutResId: Int) =
            LayoutInflater.from(context).inflate(layoutResId, container, false)
    }

    private var View.visible: Boolean
        get() = visibility == View.VISIBLE
        set(value) {
            visibility = if (value) View.VISIBLE else View.INVISIBLE
        }

    private fun DateTimeForm.toPrettyString(): String = "$hour:$minute  $dayOfMonth-$month-$year"

    private fun TemperatureForm.Unit.toPrettyString(): String =
        when (this) {
            TemperatureForm.Unit.CELSIUS -> "C°"
            TemperatureForm.Unit.FAHRENHEIT -> "F°"
        }

    private fun TemperatureForm.toPrettyString(): String = "$value${unit.toPrettyString()}"

    private fun CurrentWeatherForm.toPrettyString(): String =
        "${descriptor.description}, type=$type, ${temperature.toPrettyString()}"

    private fun FutureWeatherForm.toPrettyString(): String =
        "${dateTime.toPrettyString()}, ${descriptor.description}, type=$type, ${temperature.toPrettyString()}"
}