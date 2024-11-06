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
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ScoreBoardFragment : Fragment() {

    private var _binding: FragmentScoreBoardBinding? = null
    private val binding get() = _binding!!

    private var playerName: String? = null
    private var score: Int? = null

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

        // Carregar o último score e nome do jogador do banco de dados
        loadLastScore()

        binding.btnViewScore.setOnClickListener {
            // Verifica se playerName e score foram carregados corretamente antes de navegar
            val action = ScoreBoardFragmentDirections.actionScoreBoardFragmentToGameScore(
                playerName = playerName ?: "Jogador",  // Valor padrão se playerName estiver nulo
                score = score ?: 0  // Valor padrão se score estiver nulo
            )
            findNavController().navigate(action)
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

    private fun loadLastScore() {
        val db = DatabaseBuilder.getInstance(requireContext())
        GlobalScope.launch(Dispatchers.IO) {
            val lastScore = db.playerScoreDao().getLastScore()
            withContext(Dispatchers.Main) {
                lastScore?.let {
                    playerName = it.playerName
                    score = it.score
                    Log.d("ScoreBoardFragment", "Último jogador: $playerName, Pontuação: $score")
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
