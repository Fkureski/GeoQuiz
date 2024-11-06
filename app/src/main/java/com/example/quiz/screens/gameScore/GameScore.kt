package com.example.quiz.screens.gameScore

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.quiz.R
import com.example.quiz.databinding.FragmentGameScoreBinding

class GameScore : Fragment() {

    private var _binding: FragmentGameScoreBinding? = null
    private val binding get() = _binding!!
    private val args: GameScoreArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentGameScoreBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val playerName = args.playerName
        val score = args.score

        binding.displayResult.text = getString(R.string.score_text, playerName, score)

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
