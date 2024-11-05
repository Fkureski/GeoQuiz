package com.example.quiz.screens.gameScore

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.quiz.R
import com.example.quiz.databinding.FragmentGameScoreBinding

class GameScore : Fragment() {

    private var _binding: FragmentGameScoreBinding? = null
    private val binding get() = _binding!!
    private var score: Int = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentGameScoreBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        score = arguments?.getInt("score") ?: 0
        binding.displayResult.text = getString(R.string.score_text, score)

        binding.playAgain.setOnClickListener {
            findNavController().navigate(R.id.action_gameScore_to_homeScreen)
        }

        binding.viewScoreboard.setOnClickListener {
            findNavController().navigate(R.id.action_gameScore_to_scoreBoard)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
