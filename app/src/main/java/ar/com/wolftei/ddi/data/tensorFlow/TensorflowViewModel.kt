package ar.com.wolftei.ddi.data.tensorFlow

import android.content.Context
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.ViewModel
import com.google.android.gms.tflite.client.TfLiteInitializationOptions
import dagger.hilt.android.lifecycle.HiltViewModel
import org.tensorflow.lite.task.gms.vision.TfLiteVision
import org.tensorflow.lite.task.gms.vision.detector.ObjectDetector
import javax.inject.Inject

@HiltViewModel
class TensorflowViewModel @Inject constructor(): ViewModel() {
   /* val modelName = "lite-model_mobilenetv2-coco_dr_1.tflite"
    val options = TfLiteInitializationOptions.builder()
        .setEnableGpuDelegateSupport(true)
        .build()
    val tflite = TfLiteVision.initialize(context, options).addOnSuccessListener {
        objectDetectorListener.onInitialized()
    }.addOnFailureListener {
        // Called if the GPU Delegate is not supported on the device
        TfLiteVision.initialize(context).addOnSuccessListener {
            objectDetectorListener.onInitialized()
        }.addOnFailureListener{
            objectDetectorListener.onError("TfLiteVision failed to initialize: "
                    + it.message)
        }
    }

    val optionsBuilder =
        ObjectDetector.ObjectDetectorOptions.builder()
            .setScoreThreshold(threshold)
            .setMaxResults(maxResults)
    try {
        optionsBuilder.useGpu()
    } catch(e: Exception) {
        objectDetectorListener.onError("GPU is not supported on this device")
    }

    val objectDetector =
    ObjectDetector.createFromFileAndOptions(
    context, modelName, optionsBuilder.build())

*/
}