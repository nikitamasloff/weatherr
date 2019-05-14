package com.nikitamaslov.ui.splash

import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.nikitamaslov.presentation.viewmodel.SplashViewModel
import com.nikitamaslov.ui.R
import com.nikitamaslov.ui.util.extensions.observe
import com.nikitamaslov.ui.util.extensions.observeNavigationEvents
import kotlinx.android.synthetic.main.fragment_splash.*
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

private const val STARTUP_ANIMATION_DURATION = 2000L

class SplashFragment : Fragment() {

    private val handler: Handler by inject()
    private val viewModel: SplashViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_splash, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        postDelayed(STARTUP_ANIMATION_DURATION) {
            observeNavigationEvents(viewModel.navEvents)
            observeLoadingIndication()
        }
    }

    private fun observeLoadingIndication() {
        observe(viewModel.loading) { isLoading: Boolean? ->
            loading_indicator.visibility = if (isLoading == true) View.VISIBLE else View.INVISIBLE
        }
    }

    private fun postDelayed(delayMillis: Long, block: () -> Unit) {
        handler.postDelayed(block, delayMillis)
    }
}