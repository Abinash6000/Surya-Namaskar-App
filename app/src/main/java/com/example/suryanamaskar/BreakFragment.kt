package com.example.suryanamaskar

import android.os.Bundle
import android.os.CountDownTimer
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.suryanamaskar.databinding.FragmentBreakBinding

class BreakFragment : Fragment() {
    private var breakTimer: CountDownTimer? = null
    private var _binding: FragmentBreakBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentBreakBinding.inflate(inflater, container, false)

        startBreakTimer()

        binding.skipBtn.setOnClickListener {
            breakTimer?.cancel()
            findNavController().navigate(R.id.action_breakFragment_to_startFragment)
        }

        return binding.root
    }

    private fun startBreakTimer() {
            breakTimer = object : CountDownTimer(3000L, 1000L) {
                override fun onTick(millisUntilFinished: Long) {
                    binding.tvBreakTime.text = (millisUntilFinished/1000).toString()
                }

                override fun onFinish() {
                    // navigate to break fragment
                    findNavController().navigate(R.id.action_breakFragment_to_startFragment)
                }
            }.start()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}