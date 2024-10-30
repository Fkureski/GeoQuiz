package com.example.quiz.screens.scoreBoard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.quiz.R

class ScoreBoardFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
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

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Infla o layout do fragmento
        val view = inflater.inflate(R.layout.fragment_score_board_screen, container, false)

        // Configura a RecyclerView
        recyclerView = view.findViewById(R.id.rv_top_scores)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        scoreAdapter = ScoreAdapter(topScores)
        recyclerView.adapter = scoreAdapter

        return view
    }
}
