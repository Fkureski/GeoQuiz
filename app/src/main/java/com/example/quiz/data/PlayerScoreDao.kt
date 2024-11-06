package com.example.quiz.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface PlayerScoreDao {

    @Insert
    suspend fun insertScore(score: PlayerScore)

    @Query("SELECT * FROM player_scores ORDER BY score DESC LIMIT 10")
    fun getTopScores(): LiveData<List<PlayerScore>>
}

