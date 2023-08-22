package com.project.suryanamaskar.fragment

import android.os.Bundle
import android.os.CountDownTimer
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.navigation.fragment.findNavController
import com.example.suryanamaskar.R
import com.project.suryanamaskar.currRepetitions
import com.project.suryanamaskar.currRounds
import com.example.suryanamaskar.databinding.FragmentStartBinding
import com.project.suryanamaskar.roundsCount
import java.util.Locale

class StartFragment : Fragment() {
    private var _binding: FragmentStartBinding? = null
    private val binding get() = _binding!!

    private var running = false
    var total = 0
    val startTimeInMillis = 60000L
    val timePerPose = startTimeInMillis/12
    var timeLeftInMillis = startTimeInMillis
    var startCountDownTimer: CountDownTimer? = null

    val mantras = listOf(
        "Om Mitraya Namaha",
        "Om Ravaye Namaha",
        "Om Suryaya Namaha",
        "Om Bhanave Namaha",
        "Om Khagaya Namaha",
        "Om Pushne Namaha",
        "Om Hiranya Garbhaye Namaha",
        "Om Marichaye Namaha",
        "Om Adityaya Namaha",
        "Om Savitre Namaha",
        "Om Arkaya Namaha",
        "Om Bhaskaraya Namaha"
    )

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentStartBinding.inflate(inflater, container, false)
        currRounds = roundsCount
        startTimer()

        binding.pauseBtn.setOnClickListener {
            if (running) {
                pauseTimer();
            } else {
                startTimer();
            }
        }

        return binding.root
    }

    private fun pauseTimer() {
        startCountDownTimer?.cancel()
        running = false
        binding.pauseBtn.text = "Resume"
    }

    private fun updateCountDownText() {
        val minutes = (timeLeftInMillis / 1000) / 60
        val seconds = (timeLeftInMillis / 1000) % 60
        val timeLeftFormatted = String.format(Locale.getDefault(),"%02d:%02d", minutes, seconds)
        binding.tvTime.text = timeLeftFormatted
    }

    private fun startTimer() {
        if(!running) {
            startCountDownTimer = object : CountDownTimer(timeLeftInMillis, 1000L) {
                override fun onTick(millisUntilFinished: Long) {
                    timeLeftInMillis = millisUntilFinished
                    updateCountDownText()
                    updatePoseImage()
                }

                override fun onFinish() {
                    if(--currRounds > 0) {
                        Log.d("adfasdfasdf", currRounds.toString())
                        timeLeftInMillis = startTimeInMillis
                        startCountDownTimer = null
                        running = false
                        total++
                        startTimer()
                    } else {
                        currRepetitions--
                        running = false
                        if(currRepetitions > 0)
                            findNavController().navigate(R.id.action_startFragment_to_breakFragment)
                        else
                            findNavController().navigate(R.id.action_startFragment_to_doneFragment)
                    }
                }

            }.start()

            running = true
            binding.pauseBtn.text = "Pause"
        }
    }

    private fun updatePoseImage() {
        when {
            timeLeftInMillis >= (11 * timePerPose) -> {
                binding.ivPose.setImageDrawable(ContextCompat.getDrawable(requireContext(),
                    R.drawable.pose_1_f
                ))
                binding.tvMantra.text = mantras[0]
            }
            timeLeftInMillis >= (10 * timePerPose) -> {
                binding.ivPose.setImageDrawable(ContextCompat.getDrawable(requireContext(),
                    R.drawable.pose_2_f
                ))
                binding.tvMantra.text = mantras[1]
            }
            timeLeftInMillis >= (9 * timePerPose) -> {
                binding.ivPose.setImageDrawable(ContextCompat.getDrawable(requireContext(),
                    R.drawable.pose_3_f
                ))
                binding.tvMantra.text = mantras[2]
            }
            timeLeftInMillis >= (8 * timePerPose) -> {
                binding.ivPose.setImageDrawable(ContextCompat.getDrawable(requireContext(),
                    R.drawable.pose_4_f
                ))
                binding.tvMantra.text = mantras[3]
            }
            timeLeftInMillis >= (7 * timePerPose) -> {
                binding.ivPose.setImageDrawable(ContextCompat.getDrawable(requireContext(),
                    R.drawable.pose_5_f
                ))
                binding.tvMantra.text = mantras[4]
            }
            timeLeftInMillis >= (6 * timePerPose) -> {
                binding.ivPose.setImageDrawable(ContextCompat.getDrawable(requireContext(),
                    R.drawable.pose_6_f
                ))
                binding.tvMantra.text = mantras[5]
            }
            timeLeftInMillis >= (5 * timePerPose) -> {
                binding.ivPose.setImageDrawable(ContextCompat.getDrawable(requireContext(),
                    R.drawable.pose_7_f
                ))
                binding.tvMantra.text = mantras[6]
            }
            timeLeftInMillis >= (4 * timePerPose) -> {
                binding.ivPose.setImageDrawable(ContextCompat.getDrawable(requireContext(),
                    R.drawable.pose_5_f
                ))
                binding.tvMantra.text = mantras[7]
            }
            timeLeftInMillis >= (3 * timePerPose) -> {
                binding.ivPose.setImageDrawable(ContextCompat.getDrawable(requireContext(),
                    R.drawable.pose_4_f
                ))
                binding.tvMantra.text = mantras[8]
            }
            timeLeftInMillis >= (2 * timePerPose) -> {
                binding.ivPose.setImageDrawable(ContextCompat.getDrawable(requireContext(),
                    R.drawable.pose_3_f
                ))
                binding.tvMantra.text = mantras[9]
            }
            timeLeftInMillis >= (1 * timePerPose) -> {
                binding.ivPose.setImageDrawable(ContextCompat.getDrawable(requireContext(),
                    R.drawable.pose_2_f
                ))
                binding.tvMantra.text = mantras[10]
            }
            timeLeftInMillis >= (0 * timePerPose) -> {
                binding.ivPose.setImageDrawable(ContextCompat.getDrawable(requireContext(),
                    R.drawable.pose_1_f
                ))
                binding.tvMantra.text = mantras[11]
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}