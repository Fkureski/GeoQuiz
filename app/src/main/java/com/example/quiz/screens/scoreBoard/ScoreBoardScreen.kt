package com.example.quiz.screens.scoreBoard

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.quiz.R
import com.example.quiz.data.DatabaseBuilder
import com.example.quiz.databinding.FragmentScoreBoardBinding

class ScoreBoardFragment : Fragment() {

    private var _binding: FragmentScoreBoardBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentScoreBoardBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.recyclerViewScores.layoutManager = LinearLayoutManager(requireContext())
        observeScores()

        binding.btnViewScore.setOnClickListener {
            findNavController().navigate(R.id.action_scoreBoardFragment_to_gameScore)
        }

        binding.btnPlayAgain.setOnClickListener {
            findNavController().navigate(R.id.action_scoreBoardFragment_to_homeScreen)
        }
    }

    private fun observeScores() {
        val db = DatabaseBuilder.getInstance(requireContext())
        db.playerScoreDao().getTopScores().observe(viewLifecycleOwner, Observer { scores ->
            if (!scores.isNullOrEmpty()) {
                Log.d("ScoreBoardFragment", "Dados recebidos: ${scores.size} registros")
                binding.recyclerViewScores.adapter = ScoreAdapter(scores)
            } else {
                Log.d("ScoreBoardFragment", "Nenhum dado encontrado no banco de dados.")
            }
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
