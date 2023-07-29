package com.example.suryanamaskar.fragment

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import androidx.lifecycle.lifecycleScope
import com.example.suryanamaskar.R
import com.example.suryanamaskar.currRepetitions
import com.example.suryanamaskar.currRounds
import com.example.suryanamaskar.dataStore
import com.example.suryanamaskar.databinding.FragmentRoundsBinding
import com.example.suryanamaskar.repetitionsCount
import com.example.suryanamaskar.repetitionsKey
import com.example.suryanamaskar.roundsCount
import com.example.suryanamaskar.roundsKey
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlin.math.round



class RoundsFragment : Fragment() {
    private var _binding: FragmentRoundsBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentRoundsBinding.inflate(inflater, container, false)

        binding.tvTotal.text = resources.getString(R.string.total, roundsCount!!* repetitionsCount!!)
        binding.etRounds.setText(roundsCount.toString())
        binding.etRepetitions.setText(repetitionsCount.toString())

        // save the rounds and repetitions using data store and update the total text view
        binding.btnSave.setOnClickListener {
            lifecycleScope.launch {
                saveCounts()
            }
        }

        return binding.root
    }

    private suspend fun saveCounts() {
        context?.dataStore?.edit { settings ->
            roundsCount = binding.etRounds.text.toString().toInt()
            repetitionsCount = binding.etRepetitions.text.toString().toInt()
            settings[roundsKey] = roundsCount!!
            settings[repetitionsKey] = repetitionsCount!!
        }

        // update ui
        val total = roundsCount!!* repetitionsCount!!
        binding.tvTotal.text = resources.getString(R.string.total, total)

        // update current counters
        currRounds = roundsCount
        currRepetitions = repetitionsCount
    }

}