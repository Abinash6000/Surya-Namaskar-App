package com.project.suryanamaskar.fragment

import android.os.Bundle
import android.os.CountDownTimer
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.addCallback
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.suryanamaskar.R
import com.example.suryanamaskar.databinding.FragmentStartBinding
import com.project.suryanamaskar.currRepetitions
import com.project.suryanamaskar.currRounds
import com.project.suryanamaskar.humans
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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val callback = requireActivity().onBackPressedDispatcher.addCallback(this) {
            // Handle the back button event
            startCountDownTimer!!.cancel()
            findNavController().navigate(R.id.action_startFragment_to_homeFragment)
        }

        callback.isEnabled = true
    }

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

        binding.humans.setOnClickListener {
            humans = !humans
        }

        binding.skipBtn.setOnClickListener {
            // cancel the timer
            startCountDownTimer!!.cancel()
            timeLeftInMillis = 1000L
            running = false

            // execute the same code you would execute when the timer finishes
            if(--currRounds > 0) {
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

        return binding.root
    }

    private fun pauseTimer() {
        startCountDownTimer?.cancel()
        running = false
        binding.pauseBtn.text = "Resume"
        val drawable = ContextCompat.getDrawable(requireContext(), R.drawable.round_play_arrow_24)
        binding.pauseBtn.setCompoundDrawablesWithIntrinsicBounds(null, null, drawable, null)
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
            val drawable = ContextCompat.getDrawable(requireContext(), R.drawable.round_pause_24)
            binding.pauseBtn.setCompoundDrawablesWithIntrinsicBounds(null, null, drawable, null)
        }
    }

    private fun updatePoseImage() {
        when {
            timeLeftInMillis >= (11 * timePerPose) -> {
                if(humans)
                    binding.ivPose.setImageDrawable(ContextCompat.getDrawable(requireContext(),
                        R.drawable.pose_1_12_f
                    ))
                else
                    binding.ivPose.setImageDrawable(ContextCompat.getDrawable(requireContext(),
                        R.drawable.pose_1_12
                    ))
                binding.tvMantra.text = mantras[0]
            }
            timeLeftInMillis >= (10 * timePerPose) -> {
                if(humans)
                    binding.ivPose.setImageDrawable(ContextCompat.getDrawable(requireContext(),
                        R.drawable.pose_2_11_f
                    ))
                else
                    binding.ivPose.setImageDrawable(ContextCompat.getDrawable(requireContext(),
                        R.drawable.pose_2_11
                    ))
                binding.tvMantra.text = mantras[1]
            }
            timeLeftInMillis >= (9 * timePerPose) -> {
                if(humans)
                    binding.ivPose.setImageDrawable(ContextCompat.getDrawable(requireContext(),
                        R.drawable.pose_3_10_f
                    ))
                else
                    binding.ivPose.setImageDrawable(ContextCompat.getDrawable(requireContext(),
                        R.drawable.pose_3_10
                    ))
                binding.tvMantra.text = mantras[2]
            }
            timeLeftInMillis >= (8 * timePerPose) -> {
                if(humans)
                    binding.ivPose.setImageDrawable(ContextCompat.getDrawable(requireContext(),
                        R.drawable.pose_4_9_f
                    ))
                else
                    binding.ivPose.setImageDrawable(ContextCompat.getDrawable(requireContext(),
                        R.drawable.pose_4_9
                    ))
                binding.tvMantra.text = mantras[3]
            }
            timeLeftInMillis >= (7 * timePerPose) -> {
                if(humans)
                    binding.ivPose.setImageDrawable(ContextCompat.getDrawable(requireContext(),
                        R.drawable.pose_5_8_f
                    ))
                else
                    binding.ivPose.setImageDrawable(ContextCompat.getDrawable(requireContext(),
                        R.drawable.pose_5_8
                    ))
                binding.tvMantra.text = mantras[4]
            }
            timeLeftInMillis >= (6 * timePerPose) -> {
                if(humans)
                    binding.ivPose.setImageDrawable(ContextCompat.getDrawable(requireContext(),
                        R.drawable.pose_6_f
                    ))
                else
                    binding.ivPose.setImageDrawable(ContextCompat.getDrawable(requireContext(),
                        R.drawable.pose_6
                    ))
                binding.tvMantra.text = mantras[5]
            }
            timeLeftInMillis >= (5 * timePerPose) -> {
                if(humans)
                    binding.ivPose.setImageDrawable(ContextCompat.getDrawable(requireContext(),
                        R.drawable.pose_7_f
                    ))
                else
                    binding.ivPose.setImageDrawable(ContextCompat.getDrawable(requireContext(),
                        R.drawable.pose_7
                    ))
                binding.tvMantra.text = mantras[6]
            }
            timeLeftInMillis >= (4 * timePerPose) -> {
                if(humans)
                    binding.ivPose.setImageDrawable(ContextCompat.getDrawable(requireContext(),
                        R.drawable.pose_5_8_f
                    ))
                else
                    binding.ivPose.setImageDrawable(ContextCompat.getDrawable(requireContext(),
                        R.drawable.pose_5_8
                    ))
                binding.tvMantra.text = mantras[7]
            }
            timeLeftInMillis >= (3 * timePerPose) -> {
                if(humans)
                    binding.ivPose.setImageDrawable(ContextCompat.getDrawable(requireContext(),
                        R.drawable.pose_4_9_f
                    ))
                else
                    binding.ivPose.setImageDrawable(ContextCompat.getDrawable(requireContext(),
                        R.drawable.pose_4_9
                    ))
                binding.tvMantra.text = mantras[8]
            }
            timeLeftInMillis >= (2 * timePerPose) -> {
                if(humans)
                    binding.ivPose.setImageDrawable(ContextCompat.getDrawable(requireContext(),
                        R.drawable.pose_3_10_f
                    ))
                else
                    binding.ivPose.setImageDrawable(ContextCompat.getDrawable(requireContext(),
                        R.drawable.pose_3_10
                    ))
                binding.tvMantra.text = mantras[9]
            }
            timeLeftInMillis >= (1 * timePerPose) -> {
                if(humans)
                    binding.ivPose.setImageDrawable(ContextCompat.getDrawable(requireContext(),
                        R.drawable.pose_2_11_f
                    ))
                else
                    binding.ivPose.setImageDrawable(ContextCompat.getDrawable(requireContext(),
                        R.drawable.pose_2_11
                    ))
                binding.tvMantra.text = mantras[10]
            }
            timeLeftInMillis >= (0 * timePerPose) -> {
                if(humans)
                    binding.ivPose.setImageDrawable(ContextCompat.getDrawable(requireContext(),
                        R.drawable.pose_1_12_f
                    ))
                else
                    binding.ivPose.setImageDrawable(ContextCompat.getDrawable(requireContext(),
                        R.drawable.pose_1_12
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