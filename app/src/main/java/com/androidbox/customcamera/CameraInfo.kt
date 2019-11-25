package com.androidbox.customcamera

import android.hardware.camera2.CameraCharacteristics
import android.util.SparseArray

class CameraInfo(val id: String, cameraCharacteristics: CameraCharacteristics) {
    val cameraFacing =
        LensFacing.fromValue(cameraCharacteristics[CameraCharacteristics.LENS_FACING])
    val supportedImageFormats =
        cameraCharacteristics[CameraCharacteristics.SCALER_STREAM_CONFIGURATION_MAP]
            ?.outputFormats?.map {format ->
            ImageFormats.fromValue(format)
        }?: listOf()
}


enum class LensFacing(val value: Int) {
    LENS_FACING_FRONT(0),
    LENS_FACING_BACK(1),
    LENS_FACING_EXTERNAL(2),
    ERROR(-1);

    companion object {
        private val CONSTANTS = SparseArray<LensFacing>()

        init {
            for (c in values()) {
                CONSTANTS.put(c.value, c)
            }
        }

        fun fromValue(value: Int?): LensFacing =
            value?.let { v ->
                CONSTANTS[v] ?: ERROR
            } ?: ERROR
    }
}

enum class ImageFormats(val value: Int) {
    DEPTH16(1144402265 ),
    DEPTH_JPEG(1768253795 ),
    DEPTH_POINT_CLOUD(257 ),
    FLEX_RGBA_8888(42),
    FLEX_RGB_888(41 ),
    HEIC(1212500294 ),
    JPEG(256 ),
    NV16(16),
    NV21(17),
    PRIVATE(34),
    RAW10(37),
    RAW12(38),
    RAW_PRIVATE(36),
    RAW_SENSOR(32),
    Y8(538982489 ),
    YUV_420_888(35),
    YUV_422_888(39),
    YUV_444_888(40),
    YUY2(20 ),
    YV12(842094169 ),
    UNKNOWN(0),

    ERROR(-1);

    companion object {
        private val CONSTANTS = SparseArray<ImageFormats>()

        init {
            for (c in values()) {
                CONSTANTS.put(c.value, c)
            }
        }

        fun fromValue(value: Int?): ImageFormats =
            value?.let { v ->
                CONSTANTS[v] ?: ERROR
            } ?: ERROR
    }
}