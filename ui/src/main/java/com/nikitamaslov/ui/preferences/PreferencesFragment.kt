package com.nikitamaslov.ui.preferences

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.afollestad.materialdialogs.MaterialDialog
import com.afollestad.materialdialogs.list.ItemListener
import com.afollestad.materialdialogs.list.listItems
import com.nikitamaslov.presentation.model.TemperatureFigure
import com.nikitamaslov.presentation.viewmodel.PreferencesViewModel
import com.nikitamaslov.ui.R
import com.nikitamaslov.ui.mapper.mapToTemperatureUnitFigure
import com.nikitamaslov.ui.mapper.mapToTemperatureUnitForm
import com.nikitamaslov.ui.model.TemperatureForm
import com.nikitamaslov.ui.util.extensions.observe
import com.nikitamaslov.ui.util.extensions.observeNavigationEvents
import kotlinx.android.synthetic.main.fragment_preferences.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class PreferencesFragment : Fragment() {

    private val viewModel: PreferencesViewModel by viewModel()
    private val tempUnits = TemperatureForm.Unit.values()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_preferences, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setupTemperatureUnitPreference()
        observeNavigationEvents(viewModel.navEvents)
        observeTemperatureUnits()
    }

    private fun setupTemperatureUnitPreference() {
        pref_temp_unit.setOnClickListener { showTemperatureUnitPickerDialog() }
    }

    private fun observeTemperatureUnits() {
        observe(viewModel.temperatureUnits) { temperatureUnit: TemperatureFigure.Unit? ->
            temperatureUnit?.mapToTemperatureUnitForm()?.let(this::updateTempUnitPrefSummary)
        }
    }

    private fun updateTempUnitPrefSummary(value: TemperatureForm.Unit) {
        val description = getDescription(value)
        pref_temp_unit.setSummary(description)
    }

    private fun showTemperatureUnitPickerDialog() {
        MaterialDialog(requireContext())
            .listItems(
                items = tempUnits.map(this::getDescription),
                selection = onTempUnitDialogItemClick
            )
            .show()
    }

    private val onTempUnitDialogItemClick: ItemListener = { _, index, _ ->
        tempUnits[index]
            .mapToTemperatureUnitFigure()
            .let(viewModel::setTemperatureUnit)
    }

    private fun getDescription(tempUnit: TemperatureForm.Unit): String = getString(tempUnit.resId)
}