package com.example.quiz.screens.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.findNavController
import com.example.quiz.R
import com.example.quiz.databinding.FragmentHomeScreenBinding


class HomeScreen : Fragment() {

    private lateinit var binding: FragmentHomeScreenBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home_screen, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.startBtn.setOnClickListener {
            val playerName = binding.etName.text.toString().trim()
            if (playerName.isEmpty()) {
                Toast.makeText(requireContext(), "Por favor, insira um nome para começar!", Toast.LENGTH_SHORT).show()
            } else {
                val action = HomeScreenDirections.actionHomeScreenToGameScreen(playerName)
                findNavController().navigate(action)
            }
        }
    }
}
