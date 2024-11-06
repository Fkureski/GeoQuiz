package com.example.quiz.screens.game

import android.os.Bundle
import android.os.CountDownTimer
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.quiz.Constants
import com.example.quiz.Questions
import com.example.quiz.R
import com.example.quiz.databinding.FragmentGameScreenBinding

class GameScreen : Fragment() {

    private lateinit var mQuestionsList: ArrayList<Questions>
    private lateinit var binding: FragmentGameScreenBinding
    private var questionIndex = 0
    private var correctAnswersCount = 0
    private var score = 0
    private var timeLeftInSeconds = 11
    private val args: GameScreenArgs by navArgs()
    private lateinit var timer: CountDownTimer

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_game_screen, container, false)
        mQuestionsList = Constants.getQuestions().apply { shuffle() }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setQuestion()
        binding.tvOptionOne.setOnClickListener { checkAnswer(binding.tvOptionOne) }
        binding.tvOptionTwo.setOnClickListener { checkAnswer(binding.tvOptionTwo) }
        binding.tvOptionThree.setOnClickListener { checkAnswer(binding.tvOptionThree) }
        binding.tvOptionFour.setOnClickListener { checkAnswer(binding.tvOptionFour) }
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
        binding.tvProgress.text = getString(R.string.progress_text, questionIndex + 1, mQuestionsList.size)
        resetOptionBackgrounds()
        startTimer()
    }

    private fun resetOptionBackgrounds() {
        val defaultBackground = ContextCompat.getDrawable(requireContext(), R.drawable.textview_background)
        binding.tvOptionOne.background = defaultBackground
        binding.tvOptionTwo.background = defaultBackground
        binding.tvOptionThree.background = defaultBackground
        binding.tvOptionFour.background = defaultBackground
    }

    private fun startTimer() {
        val totalTimeInMillis = 11000L
        binding.tvTimer.text = "10"
        binding.timerProgressBar.progress = 100

        timer = object : CountDownTimer(totalTimeInMillis, 100) {
            override fun onTick(millisUntilFinished: Long) {
                timeLeftInSeconds = (millisUntilFinished / 1000).toInt()
                val progress = (millisUntilFinished * 100 / totalTimeInMillis).toInt()
                binding.tvTimer.text = timeLeftInSeconds.toString()
                binding.timerProgressBar.progress = progress
            }

            override fun onFinish() {
                binding.tvTimer.text = "0"
                binding.timerProgressBar.progress = 0
                goToNextQuestion()
            }
        }.start()
    }


    private fun checkAnswer(option: TextView) {
        timer.cancel()
        val question = mQuestionsList[questionIndex]
        val selectedAnswerIndex = when (option) {
            binding.tvOptionOne -> 1
            binding.tvOptionTwo -> 2
            binding.tvOptionThree -> 3
            binding.tvOptionFour -> 4
            else -> -1
        }
        if (selectedAnswerIndex == question.correctAnswer) {
            option.background = ContextCompat.getDrawable(requireContext(), R.drawable.correct_answer_border)
            correctAnswersCount++
            score += calculateScoreBasedOnTime()

            Log.d("GameScreen", "Score atualizado: $score")
        } else {
            option.background = ContextCompat.getDrawable(requireContext(), R.drawable.wrong_answer_border)
        }
        Handler(Looper.getMainLooper()).postDelayed({
            goToNextQuestion()
        }, 1500)
    }

    private fun calculateScoreBasedOnTime(): Int {
        return when (timeLeftInSeconds) {
            in 9..10 -> 10
            in 8..9 -> 9
            in 7..8 -> 8
            in 6..7 -> 7
            in 5..6 -> 6
            in 4..5 -> 5
            in 3..4 -> 4
            in 2..3 -> 3
            in 1..2 -> 2
            in 0..1 -> 1
            else -> 0
        }.also { Log.d("GameScreen", "Score calculado com base no tempo: $it") }
    }


    private fun goToNextQuestion() {
        if (questionIndex < mQuestionsList.size - 1) {
            questionIndex++
            setQuestion()
        } else {
            val action = GameScreenDirections.actionGameScreenToGameScore(
                score = score,
                playerName = args.playerName
            )
            findNavController().navigate(action)
        }
    }

}
