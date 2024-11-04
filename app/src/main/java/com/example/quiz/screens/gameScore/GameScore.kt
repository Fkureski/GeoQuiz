package com.example.quiz.screens.gameScore

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.example.quiz.R
import com.example.quiz.databinding.FragmentGameScoreBinding

class GameScore : Fragment() {

    lateinit var binding: FragmentGameScoreBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_game_score, container, false)
        return binding.root
    }
}