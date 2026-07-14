package com.ayant02.ocrx.mlkit

import androidx.camera.core.ImageAnalysis
import androidx.camera.core.ImageProxy
import com.google.mlkit.vision.common.InputImage

@androidx.camera.core.ExperimentalGetImage
class ImageAnalyzer(
    private val onDetected: (String) -> Unit
) : ImageAnalysis.Analyzer {

    private val processor = OCRProcessor()

    override fun analyze(imageProxy: ImageProxy) {

        val mediaImage = imageProxy.image

        if (mediaImage != null) {

            val image = InputImage.fromMediaImage(
                mediaImage,
                imageProxy.imageInfo.rotationDegrees
            )

            processor.process(

                image,

                onSuccess = {

                    onDetected(it.text)

                    imageProxy.close()

                },

                onFailure = {

                    imageProxy.close()

                }

            )

        } else {

            imageProxy.close()

        }

    }

}

