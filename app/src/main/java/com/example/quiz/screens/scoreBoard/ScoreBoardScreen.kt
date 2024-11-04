package com.example.quiz.screens.scoreBoard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.quiz.R
import com.example.quiz.databinding.FragmentScoreBoardScreenBinding

class ScoreBoardFragment : Fragment() {

    private lateinit var scoreAdapter: ScoreAdapter

    // Lista fictícia de pontuações para o exemplo
    private val topScores = listOf(
        ScoreItem("Player 1", 1200),
        ScoreItem("Player 2", 1100),
        ScoreItem("Player 3", 1050),
        ScoreItem("Player 4", 1000),
        ScoreItem("Player 5", 980),
        ScoreItem("Player 6", 950),
        ScoreItem("Player 7", 930),
        ScoreItem("Player 8", 900),
        ScoreItem("Player 9", 880),
        ScoreItem("Player 10", 850)
    )

    private lateinit var binding: FragmentScoreBoardScreenBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Infla o layout do fragmento usando DataBinding
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_score_board_screen, container, false)

        // Configura o RecyclerView
        binding.rvTopScores.layoutManager = LinearLayoutManager(requireContext())
        scoreAdapter = ScoreAdapter(topScores)
        binding.rvTopScores.adapter = scoreAdapter

        return binding.root
    }
}
