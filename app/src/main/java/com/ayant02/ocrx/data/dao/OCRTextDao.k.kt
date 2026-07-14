package com.ayant02.ocrx.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.ayant02.ocrx.data.entity.OCRTextEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface OCRTextDao {

    @Insert
    suspend fun insertText(
        text: OCRTextEntity
    )

    @Query("""
        SELECT * FROM ocr_texts
        WHERE sessionId = :sessionId
        ORDER BY timestamp ASC
    """)
    fun getTextsForSession(
        sessionId: Long
    ): Flow<List<OCRTextEntity>>

    @Query("DELETE FROM ocr_texts WHERE sessionId = :sessionId")
    suspend fun deleteTextsForSession(
        sessionId: Long
    )
}