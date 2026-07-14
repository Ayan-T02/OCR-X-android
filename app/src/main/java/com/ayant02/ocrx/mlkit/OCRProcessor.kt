package com.ayant02.ocrx.mlkit

import com.google.mlkit.vision.common.InputImage

class OCRProcessor {

    fun process(
        image: InputImage,
        onSuccess: (OCRResult) -> Unit,
        onFailure: (Exception) -> Unit
    ) {

        TextRecognizerManager
            .recognizer
            .process(image)
            .addOnSuccessListener {

                onSuccess(

                    OCRResult(

                        text = it.text

                    )

                )

            }
            .addOnFailureListener {

                onFailure(it)

            }

    }

}
