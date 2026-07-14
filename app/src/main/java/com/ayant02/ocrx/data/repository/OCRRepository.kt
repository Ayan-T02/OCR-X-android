package com.ayant02.ocrx.data.repository

import com.ayant02.ocrx.data.dao.OCRTextDao
import com.ayant02.ocrx.data.dao.SessionDao
import com.ayant02.ocrx.data.entity.OCRTextEntity
import com.ayant02.ocrx.data.entity.SessionEntity

class OCRRepository(
    private val sessionDao: SessionDao,
    private val textDao: OCRTextDao
) {

    suspend fun createSession(
        title: String
    ): Long {

        return sessionDao.insertSession(
            SessionEntity(
                title = title
            )
        )
    }

    suspend fun saveText(
        sessionId: Long,
        text: String
    ) {

        textDao.insertText(
            OCRTextEntity(
                sessionId = sessionId,
                text = text
            )
        )

    }

    fun getSessions() =
        sessionDao.getAllSessions()

    fun getTexts(
        sessionId: Long
    ) =
        textDao.getTextsForSession(sessionId)

    suspend fun deleteSession(
        sessionId: Long
    ) {

        sessionDao.deleteSession(sessionId)

    }
}