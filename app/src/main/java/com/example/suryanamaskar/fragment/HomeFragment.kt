package com.example.suryanamaskar.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.suryanamaskar.R
import com.example.suryanamaskar.currRepetitions
import com.example.suryanamaskar.currRounds
import com.example.suryanamaskar.dataStore
import com.example.suryanamaskar.databinding.FragmentHomeBinding
import com.example.suryanamaskar.repetitionsCount
import com.example.suryanamaskar.repetitionsKey
import com.example.suryanamaskar.roundsCount
import com.example.suryanamaskar.roundsKey
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

class HomeFragment : Fragment() {
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentHomeBinding.inflate(inflater, container, false)

        binding.btnStart.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_startFragment)
        }

        binding.btnRounds.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_roundsFragment)
        }

        loadSettings()

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun loadSettings() {
        val roundsCountFlow: Flow<Int> = requireContext().dataStore.data
            .map { preferences ->
                // No type safety.
                preferences[roundsKey] ?: 3
            }

        val repetitionsCountFlow: Flow<Int> = requireContext().dataStore.data
            .map { preferences ->
                preferences[repetitionsKey] ?: 2
            }

        lifecycleScope.launch {
            roundsCount = roundsCountFlow.first()
            repetitionsCount = repetitionsCountFlow.first()

            // update current counters
            currRounds = roundsCount
            currRepetitions = repetitionsCount
        }
    }
}