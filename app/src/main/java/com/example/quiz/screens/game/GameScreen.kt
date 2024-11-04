package com.example.quiz.screens.game

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.quiz.Constants
import com.example.quiz.Questions
import com.example.quiz.R
import com.example.quiz.databinding.FragmentGameScreenBinding

class GameScreen : Fragment() {

    private lateinit var mQuestionsList: ArrayList<Questions>
    private lateinit var binding: FragmentGameScreenBinding
    private var questionIndex = 0
    private var correctAnswersCount = 0
    private var selectedOption: TextView? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_game_screen, container, false)
        mQuestionsList = Constants.getQuestions()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setQuestion()

        // Configurações de seleção de opção
        binding.tvOptionOne.setOnClickListener { selectOption(binding.tvOptionOne) }
        binding.tvOptionTwo.setOnClickListener { selectOption(binding.tvOptionTwo) }
        binding.tvOptionThree.setOnClickListener { selectOption(binding.tvOptionThree) }
        binding.tvOptionFour.setOnClickListener { selectOption(binding.tvOptionFour) }

        // Configuração do botão Responder
        binding.btnResponder.setOnClickListener { checkAnswer() }
    }

    private fun setQuestion() {
        val question = mQuestionsList[questionIndex]

        binding.tvQuestion.text = question.question
        binding.imageView.setImageResource(question.image)
        binding.tvOptionOne.text = question.optionOne
        binding.tvOptionTwo.text = question.optionTwo
        binding.tvOptionThree.text = question.optionThree
        binding.tvOptionFour.text = question.optionFour

        binding.pb.progress = questionIndex + 1
        binding.tvProgress.text = "${questionIndex + 1}/${mQuestionsList.size}"

        // Reset da seleção e visibilidade do botão
        selectedOption = null
        binding.btnResponder.visibility = View.GONE

        resetOptionBackgrounds()
    }

    private fun resetOptionBackgrounds() {
        val defaultBackground = ContextCompat.getDrawable(requireContext(), R.drawable.textview_background)
        binding.tvOptionOne.background = defaultBackground
        binding.tvOptionTwo.background = defaultBackground
        binding.tvOptionThree.background = defaultBackground
        binding.tvOptionFour.background = defaultBackground
    }

    private fun selectOption(option: TextView) {
        // Reseta o fundo das opções
        resetOptionBackgrounds()

        // Define a borda preta na opção selecionada
        option.background = ContextCompat.getDrawable(requireContext(), R.drawable.selected_option_border)
        selectedOption = option

        // Exibe o botão "Responder"
        binding.btnResponder.visibility = View.VISIBLE
    }

    private fun checkAnswer() {
        val question = mQuestionsList[questionIndex]

        selectedOption?.let { option ->
            // Verifica se a resposta está correta
            val selectedAnswerIndex = when (option) {
                binding.tvOptionOne -> 1
                binding.tvOptionTwo -> 2
                binding.tvOptionThree -> 3
                binding.tvOptionFour -> 4
                else -> -1
            }

            // Ajusta o fundo da opção selecionada para verde (correta) ou vermelha (incorreta)
            if (selectedAnswerIndex == question.correctAnswer) {
                option.background = ContextCompat.getDrawable(requireContext(), R.drawable.correct_answer_border)
                correctAnswersCount++
            } else {
                option.background = ContextCompat.getDrawable(requireContext(), R.drawable.wrong_answer_border)
            }

            // Aguarda 3 segundos antes de ir para a próxima pergunta
            Handler(Looper.getMainLooper()).postDelayed({
                goToNextQuestion()
            }, 1500)
        }
    }

    private fun goToNextQuestion() {
        if (questionIndex < mQuestionsList.size - 1) {
            questionIndex++
            setQuestion()
        } else {
            // Final do quiz: navega para a tela de pontuação
            findNavController().navigate(R.id.action_gameScreen_to_gameScore)
        }
    }
}
