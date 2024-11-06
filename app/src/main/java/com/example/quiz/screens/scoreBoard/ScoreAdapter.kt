package com.example.quiz.screens.scoreBoard

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.quiz.R
import com.example.quiz.data.PlayerScore

class ScoreAdapter(private val scores: List<PlayerScore>) : RecyclerView.Adapter<ScoreAdapter.ScoreViewHolder>() {

    inner class ScoreViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val playerName: TextView = itemView.findViewById(R.id.tvPlayerName)
        val playerScore: TextView = itemView.findViewById(R.id.tvPlayerScore)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ScoreViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_score, parent, false)
        return ScoreViewHolder(view)
    }

    override fun onBindViewHolder(holder: ScoreViewHolder, position: Int) {
        val currentScore = scores[position]
        holder.playerName.text = currentScore.playerName
        holder.playerScore.text = currentScore.score.toString()
    }

    override fun getItemCount(): Int = scores.size
}
