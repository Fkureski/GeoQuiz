package com.example.quiz.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface PlayerScoreDao {
    @Query("SELECT * FROM player_scores ORDER BY score DESC LIMIT 10")
    fun getTopScores(): LiveData<List<PlayerScore>>

    @Query("SELECT COUNT(*) FROM player_scores WHERE playerName = :playerName AND score = :score")
    suspend fun countScore(playerName: String, score: Int): Int

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertScore(score: PlayerScore)

    suspend fun insertScoreIfNotExists(score: PlayerScore) {
        val count = countScore(score.playerName, score.score)
        if (count == 0) {
            insertScore(score)
        }
    }
}

