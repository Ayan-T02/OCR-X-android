package com.ayant02.ocrx.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.ayant02.ocrx.data.entity.SessionEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface SessionDao {

    @Insert
    suspend fun insertSession(
        session: SessionEntity
    ): Long

    @Query("SELECT * FROM sessions ORDER BY createdAt DESC")
    fun getAllSessions(): Flow<List<SessionEntity>>

    @Query("DELETE FROM sessions WHERE id = :sessionId")
    suspend fun deleteSession(
        sessionId: Long
    )
}