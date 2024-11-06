package com.example.quiz.screens.gameScore

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.quiz.R
import com.example.quiz.data.DatabaseBuilder
import com.example.quiz.data.PlayerScore
import com.example.quiz.databinding.FragmentGameScoreBinding
import kotlinx.coroutines.launch

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

        // Verifique se o score e o playerName foram corretamente recebidos
        val playerName = args.playerName ?: "Jogador"
        val score = args.score

        // Exibir o resultado no TextView
        binding.displayResult.text = getString(R.string.score_text, playerName, score)

        // Chame a função de salvar a pontuação se ela não for zero
        if (score > 0) {
            savePlayerScore(playerName, score)
        }

        // Configuração do botão "Jogar Novamente"
        binding.playAgain.setOnClickListener {
            findNavController().navigate(R.id.action_gameScore_to_homeScreen)
        }

        // Controle de visibilidade do botão "Ver Scoreboard"
        binding.viewScoreboard.visibility = if (score > 0) View.VISIBLE else View.GONE
        binding.viewScoreboard.setOnClickListener {
            val action = GameScoreDirections.actionGameScoreToScoreBoard(fromGameScore = true)
            findNavController().navigate(action)
        }
    }

    private fun savePlayerScore(playerName: String, score: Int) {
        lifecycleScope.launch {
            val db = DatabaseBuilder.getInstance(requireContext())
            val playerScore = PlayerScore(playerName = playerName, score = score)
            db.playerScoreDao().insertScoreIfNotExists(playerScore)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
