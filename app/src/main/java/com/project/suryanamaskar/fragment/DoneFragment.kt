package com.project.suryanamaskar.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.suryanamaskar.R
import com.example.suryanamaskar.databinding.FragmentDoneBinding

class DoneFragment : Fragment() {
    private var _binding: FragmentDoneBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentDoneBinding.inflate(inflater, container, false)

        binding.btnHome.setOnClickListener {
            findNavController().navigate(R.id.action_doneFragment_to_homeFragment2)
        }

        return binding.root
    }
}