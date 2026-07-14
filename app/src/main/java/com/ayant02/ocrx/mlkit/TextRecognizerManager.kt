package com.ayant02.ocrx.mlkit

import com.google.mlkit.vision.text.TextRecognition
import com.google.mlkit.vision.text.latin.TextRecognizerOptions

object TextRecognizerManager {

    val recognizer by lazy {

        TextRecognition.getClient(
            TextRecognizerOptions.DEFAULT_OPTIONS
        )

    }

}