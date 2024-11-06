package com.example.quiz.screens.scoreBoard

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
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
    }

    private fun observeScores() {
        val db = DatabaseBuilder.getInstance(requireContext())
        db.playerScoreDao().getTopScores().observe(viewLifecycleOwner, Observer { scores ->
            binding.recyclerViewScores.adapter = ScoreAdapter(scores)
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
