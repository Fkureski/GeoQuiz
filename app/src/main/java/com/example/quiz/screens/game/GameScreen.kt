package com.example.quiz.screens.game

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.example.quiz.R
import com.example.quiz.databinding.FragmentGameScreenBinding

class GameScreen : Fragment() {

    lateinit var binding: FragmentGameScreenBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_game_screen, container, false)
        return binding.root
    }
}