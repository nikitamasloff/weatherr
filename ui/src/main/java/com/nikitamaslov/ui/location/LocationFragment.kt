package com.nikitamaslov.ui.location

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.nikitamaslov.presentation.model.LocationFigure
import com.nikitamaslov.presentation.viewmodel.LocationViewModel
import com.nikitamaslov.ui.R
import com.nikitamaslov.ui.mapper.mapToLocationFigure
import com.nikitamaslov.ui.mapper.mapToLocationForm
import com.nikitamaslov.ui.model.LocationForm
import com.nikitamaslov.ui.util.extensions.observe
import com.nikitamaslov.ui.util.extensions.observeNavigationEvents
import kotlinx.android.synthetic.main.fragment_location.*
import org.koin.androidx.viewmodel.ext.android.viewModel

private const val STUB_CITY = "City0"
private const val STUB_COUNTRY = "Country0"

class LocationFragment : Fragment() {

    private val viewModel: LocationViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_location, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        init()
        observeNavigationEvents(viewModel.navEvents)
        observeLocations()
    }

    private fun init() {
        btn_apply.setOnClickListener(onApplyClickListener)
    }

    private fun observeLocations() {
        observe(viewModel.locations) { location: LocationFigure? ->
            current_location_content.text =
                location?.mapToLocationForm()?.toPrettyString().toString()
            if (location != null) {
                val (latitude, longitude) = location.mapToLocationForm().coordinates
                et_latitude.setText(latitude.toString())
                et_longitude.setText(longitude.toString())
            }
        }
    }

    private val onApplyClickListener = View.OnClickListener {
        val latitude = try {
            et_latitude.text.toString().toDouble()
        } catch (e: NumberFormatException) {
            et_latitude.requestFocus()
            return@OnClickListener
        }

        val longitude = try {
            et_longitude.text.toString().toDouble()
        } catch (e: NumberFormatException) {
            et_longitude.requestFocus()
            return@OnClickListener
        }

        val coordinates = LocationForm.Coordinates(latitude, longitude)
        val stubDescriptor = LocationForm.Descriptor(STUB_CITY, STUB_COUNTRY)
        val location = LocationForm(stubDescriptor, coordinates)
        viewModel.setLocation(location.mapToLocationFigure())
    }

    private fun LocationForm.toPrettyString(): String =
        "${descriptor.city}, ${descriptor.country}  ${coordinates.latitude}, ${coordinates.longitude}"
}