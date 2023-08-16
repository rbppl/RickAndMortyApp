// MainActivity.kt
package com.rbppl.rickmortyapp

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.rbppl.rickmortyapp.presentation.characters.CharactersFragment
import com.rbppl.rickmortyapp.presentation.locations.LocationsFragment

class MainActivity : AppCompatActivity() {

    private lateinit var viewPager: ViewPager2
    private lateinit var tabLayout: TabLayout

    private var noInternetFragment: NoInternetFragment? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewPager = findViewById(R.id.viewPager)
        tabLayout = findViewById(R.id.tabLayout)

        if (isInternetAvailable()) {
            setupViewPager()
        } else {
            showNoInternetFragment()
        }
    }

    private fun setupViewPager() {
        val fragmentList = listOf(CharactersFragment(), LocationsFragment())
        val adapter = ViewPagerAdapter(this, fragmentList)
        viewPager.adapter = adapter

        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            tab.text = if (position == 0) "Characters" else "Locations"
        }.attach()
    }

    private fun isInternetAvailable(): Boolean {
        val connectivityManager =
            getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val network = connectivityManager.activeNetwork
        val capabilities = connectivityManager.getNetworkCapabilities(network)
        return capabilities != null &&
                (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) ||
                        capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR))
    }

    private fun showNoInternetFragment() {
        val fragmentTransaction = supportFragmentManager.beginTransaction()
        noInternetFragment = NoInternetFragment()
        noInternetFragment?.retryClickListener = {
            if (isInternetAvailable()) {
                setupViewPager()
                removeNoInternetFragment()
            } else {
                // Retry again or show some message to the user
            }
        }
        fragmentTransaction.replace(R.id.fragment_container, noInternetFragment!!)
        fragmentTransaction.commit()
    }

    private fun removeNoInternetFragment() {
        noInternetFragment?.let {
            val fragmentTransaction = supportFragmentManager.beginTransaction()
            fragmentTransaction.remove(it)
            fragmentTransaction.commit()
            noInternetFragment = null
        }
    }
}
