package com.nikitamaslov.delegated.network

import android.content.Context
import android.net.ConnectivityManager
import com.nikitamaslov.api.util.network.NetworkManager

internal class NetworkManagerImpl(private val applicationContext: Context) : NetworkManager {

    override val isConnected: Boolean get() = isConnected(applicationContext)

    private fun isConnected(context: Context): Boolean {
        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE)
                as? ConnectivityManager
        val networkInfo = connectivityManager?.activeNetworkInfo
        return networkInfo?.isConnected == true
    }
}