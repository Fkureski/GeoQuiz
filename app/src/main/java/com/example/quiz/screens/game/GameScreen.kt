package com.example.quiz.screens.game

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
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

        binding.tvOptionOne.setOnClickListener { handleAnswerSelection(1) }
        binding.tvOptionTwo.setOnClickListener { handleAnswerSelection(2) }
        binding.tvOptionThree.setOnClickListener { handleAnswerSelection(3) }
        binding.tvOptionFour.setOnClickListener { handleAnswerSelection(4) }
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
    }

    private fun handleAnswerSelection(selectedOption: Int) {
        val question = mQuestionsList[questionIndex]

        if (selectedOption == question.correctAnswer) {
            correctAnswersCount++

            if (questionIndex < mQuestionsList.size - 1) {
                questionIndex++
                setQuestion()
            } else {
                if (correctAnswersCount == mQuestionsList.size) {
                    findNavController().navigate(R.id.action_gameScreen_to_gameScore)
                } else {
                    findNavController().navigate(R.id.action_gameScreen_to_homeScreen)
                }
            }
        } else {
            findNavController().navigate(R.id.action_gameScreen_to_homeScreen)
        }
    }
}
