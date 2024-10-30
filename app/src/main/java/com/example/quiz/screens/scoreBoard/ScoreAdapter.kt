package com.example.quiz.screens.scoreBoard

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.quiz.R

class ScoreAdapter(private val scores: List<ScoreItem>) : RecyclerView.Adapter<ScoreAdapter.ScoreViewHolder>() {

    inner class ScoreViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val playerName: TextView = itemView.findViewById(R.id.tv_player_name)
        val playerScore: TextView = itemView.findViewById(R.id.tv_player_score)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ScoreViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_score, parent, false)
        return ScoreViewHolder(view)
    }

    override fun onBindViewHolder(holder: ScoreViewHolder, position: Int) {
        val scoreItem = scores[position]
        holder.playerName.text = scoreItem.playerName
        holder.playerScore.text = scoreItem.playerScore.toString()
    }

    override fun getItemCount(): Int = scores.size
}
