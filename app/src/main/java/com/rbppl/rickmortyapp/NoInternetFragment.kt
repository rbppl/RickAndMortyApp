// NoInternetFragment.kt
package com.rbppl.rickmortyapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.rbppl.rickmortyapp.databinding.FragmentNoInternetBinding

class NoInternetFragment : Fragment() {

    var retryClickListener: (() -> Unit)? = null

    private lateinit var binding: FragmentNoInternetBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentNoInternetBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.retryButton.setOnClickListener {
            retryClickListener?.invoke()
        }
    }
}
